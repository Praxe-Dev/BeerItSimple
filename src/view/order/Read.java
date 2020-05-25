package view.order;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import controller.OrderController;
import exception.ConnectionException;
import exception.DataQueryException;
import exception.NoRowSelected;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.*;
import utils.PopUp;
import view.View;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Read extends View {
    @FXML
    private Label orderReference;
    @FXML
    private JFXTextField customer;
    @FXML
    private JFXTextField paymentMethod;
    @FXML
    private JFXCheckBox deliveryCheck;
    @FXML
    private Group deliveryDisplay;
    @FXML
    private JFXTextField deliveryDate;
    @FXML
    private JFXButton okBtn;
    @FXML
    private TableView<OrderLineTableFormat> tableArticle;
    @FXML
    private TableColumn<OrderLineTableFormat, String> article;
    @FXML
    private TableColumn<OrderLineTableFormat, Double> price;
    @FXML
    private TableColumn<OrderLineTableFormat, Integer> quantity;
    @FXML
    private TableColumn<OrderLineTableFormat, Double> totalExclVat;
    @FXML
    private TableColumn<OrderLineTableFormat, Double> totalInclVat;
    @FXML
    private Text totalAmountExclVat;
    @FXML
    private Text totalAmountVatOnly;
    @FXML
    private Text totalAmountVatInc;
    @FXML
    private JFXButton invoiceBtn;

    private Order selectedOrder;

    @Override
    public void init() {
        okBtn.setOnAction(e -> closeWindow());
//        orderController = new OrderController();
//        Order order = orderController.getOrder();
        orderReference.setText("[" + selectedOrder.getReference() + "]");

        if (selectedOrder.getCustomer() != null)
            customer.setText(selectedOrder.getCustomer().getEntity().getContactName());
        else
            customer.setText("Deleted Customer");

        paymentMethod.setText(selectedOrder.getPaymentMethod().getLabel());

        deliveryCheck.setSelected(selectedOrder.getDelivery() != null);

        deliveryDisplay.setVisible(deliveryCheck.isSelected());
        if (deliveryDisplay.isVisible()) {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            String formatedDate = fmt.format(selectedOrder.getDelivery().getPlannedDate().getTime());
            deliveryDate.setText(formatedDate);
        }

        initTable();

        double amountExclVat = 0;
        double amountOnlyVat = 0;
        for (OrderLineTableFormat ol : tableArticle.getItems()) {
            amountExclVat += ol.getExclVat();
            amountOnlyVat += ol.getExclVat() * (ol.getVatCodeRate() / 100.0);
        }

        double totalAmount = amountExclVat + amountOnlyVat;
        totalAmountExclVat.setText(String.format("%.2f", amountExclVat));
        totalAmountVatOnly.setText(String.format("%.2f", amountOnlyVat));
        totalAmountVatInc.setText(String.format("%.2f", totalAmount));

        invoiceBtn.setOnAction(e -> {
            generatePDF();
        });
    }

    private void initTable() {
        try {

            article.setCellValueFactory(new PropertyValueFactory<>("product"));
            price.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            totalExclVat.setCellValueFactory(new PropertyValueFactory<>("exclVat"));
            totalInclVat.setCellValueFactory(new PropertyValueFactory<>("inclVat"));

            ArrayList<OrderLineTableFormat> orderLines = new ArrayList<>();

            for (OrderLine ol : new OrderController().getOrder(selectedOrder.getReference()).getOrderLineList()) {
                orderLines.add(new OrderLineTableFormat(ol.getProduct(), ol.getQuantity()));
            }

            tableArticle.getItems().addAll(orderLines);
        } catch (ConnectionException | DataQueryException e) {
            showError(e.getTypeError(), e.getMessage());
        }
    }

    public void generatePDF() {
        Document invoice = new Document();
        final Paragraph space = new Paragraph("\n\n\n\n\n\n");
        final BaseColor themeColor = new BaseColor(72, 141, 171);

        try {
            PdfWriter writer = PdfWriter.getInstance(invoice, new FileOutputStream("./src/PDFInvoices/invoice_" + selectedOrder.getReference() + ".pdf"));
            invoice.open();

            Image image = Image.getInstance("C:\\Users\\lejeu\\IdeaProjects\\BeerItSimple\\src\\ressources\\logoClear.png");
            image.scaleToFit(100f,100f);

            // Title
            Paragraph title = new Paragraph("Invoice n°" + selectedOrder.getReference());
            title.setAlignment(Element.ALIGN_CENTER);
            title.getFont().setSize(30f);
            title.add(image);
            invoice.add(title);

            Paragraph date = new Paragraph("\nDate : " + LocalDate.now().toString());
            date.setAlignment(Element.ALIGN_RIGHT);
            invoice.add(date);

            // Company address
            Paragraph companyAddress = new Paragraph(
                    "BeerItSimple\n"+
                    "Rue du pont, 29\n" +
                    "0659/99.99.99");
            invoice.add(companyAddress);

            // Customer address
            Paragraph customerAddress = new Paragraph(
                            selectedOrder.getCustomer().getEntity().getContactName() +
                            "\n" + selectedOrder.getCustomer().getEntity().getStreet() + ", " + selectedOrder.getCustomer().getEntity().getHouseNumber() +
                            "\n" + selectedOrder.getCustomer().getEntity().getPhoneNumber() +
                            (selectedOrder.getCustomer().getEntity().getMail() != null ? "\n" +selectedOrder.getCustomer().getEntity().getMail() : "N/A" ) + "\n\n"
            );
            customerAddress.setAlignment(Element.ALIGN_RIGHT);

            invoice.add(customerAddress);

            invoice.add(space);

            // Order detail
            PdfPTable orderDetail = new PdfPTable(4);
            orderDetail.setWidthPercentage(105);
            orderDetail.setSpacingBefore(11f);
            orderDetail.setSpacingAfter(11f);

            float[] colWidth = {4f,2f,2f,2f};
            orderDetail.setWidths(colWidth);



            Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            PdfPCell product = new PdfPCell(new Phrase("Product", bold));
            product.setHorizontalAlignment(Element.ALIGN_CENTER);
            product.setPadding(5);
            product.setBackgroundColor(themeColor);
            PdfPCell qty = new PdfPCell(new Phrase("Quantity", bold));
            qty.setHorizontalAlignment(Element.ALIGN_CENTER);
            qty.setPadding(5);
            qty.setBackgroundColor(themeColor);
            PdfPCell price = new PdfPCell(new Phrase("Unit price", bold));
            price.setHorizontalAlignment(Element.ALIGN_CENTER);
            price.setPadding(5);
            price.setBackgroundColor(themeColor);
            PdfPCell total = new PdfPCell(new Phrase("Total", bold));
            total.setHorizontalAlignment(Element.ALIGN_CENTER);
            total.setPadding(5);
            total.setBackgroundColor(themeColor);

            orderDetail.addCell(product);
            orderDetail.addCell(qty);
            orderDetail.addCell(price);
            orderDetail.addCell(total);

            fillInvoiceTable(orderDetail);

            invoice.add(orderDetail);

            // add the balance due
            Paragraph due = new Paragraph("Balance due : " + totalAmountExclVat.getText() + "€");
            due.setAlignment(Element.ALIGN_RIGHT);
//            due.getFont().setColor(BaseColor.WHITE);
            invoice.add(due);

            invoice.add(space);
            Paragraph footer = new Paragraph("Thank for your trust in our products, in the hope you'll come back soon !");
            footer.getFont().setSize(16f);
//            footer.getFont().setColor(BaseColor.WHITE);
            footer.setAlignment(Element.ALIGN_CENTER);
            invoice.add(footer);

            invoice.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        PopUp.showSuccess("Invoice generated", "Invoice generated succesfuly, you can retrive it in the PDFInvoices package !");
    }

    void fillInvoiceTable(PdfPTable orderDetail) {
        for (OrderLine ol : selectedOrder.getOrderLineList()) {
            PdfPCell productRow = new PdfPCell(new Phrase(ol.getProduct().toString()));
            productRow.setPadding(3);
            PdfPCell quantityRow = new PdfPCell(new Phrase(ol.getQuantity().toString()));
            quantityRow.setPadding(3);
            quantityRow.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell unitPriceRow = new PdfPCell(new Phrase(ol.getSalesUnitPrice().toString() + " €"));
            unitPriceRow.setPadding(3);
            unitPriceRow.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell totalAmountRow = new PdfPCell(new Phrase(ol.getFormatedTotal()));
            totalAmountRow.setPadding(3);
            totalAmountRow.setHorizontalAlignment(Element.ALIGN_RIGHT);

            orderDetail.addCell(productRow);
            orderDetail.addCell(quantityRow);
            orderDetail.addCell(unitPriceRow);
            orderDetail.addCell(totalAmountRow);
        }
    }

    public void setOrder(Order order) {
        this.selectedOrder = order;
    }
}
