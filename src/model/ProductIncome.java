package model;

import java.text.DecimalFormat;

public class ProductIncome {
    private static int totalProductSold = 0;
    private Integer code;
    private String label;
    private Integer amountSold;
    private Double income;
    private Double salePercentage;

    public ProductIncome (Product p) {
        this.code = p.getCode();
        this.label = p.getLabel();

        this.amountSold = 0;
        this.income = 0.0;
    }

    public Integer getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public int getAmountSold() {
        return amountSold;
    }

    public Double getIncome() {
        return income;
    }

//    public String getSalePercentage() {
//        DecimalFormat df = new DecimalFormat("0.00");
//        return df.format(salePercentage);
//    }
    public double getSalePercentage() {
        return (double) Math.round(salePercentage * 100) / 100;
    }

    public void addAmountSold (OrderLine ol) {
        totalProductSold += ol.getQuantity();
        amountSold += ol.getQuantity();
        income += ol.getSalesUnitPrice() * ol.getQuantity();
    }

    public void calculPercentage() {
        this.salePercentage = ((double) amountSold / totalProductSold * 100);
    }

    public double getSalePercentageNumber() {
        return this.salePercentage;
    }

    public static void resetTotalSold() {
        totalProductSold = 0;
    }

    public double getPercentage() {
        if (salePercentage.isNaN())
            return 0;
        else {
            return salePercentage;
        }
    }

    @Override
    public String toString() {
        return "ProductIncome{" +
                "code=" + code +
                ", name='" + label + '\'' +
                ", amountSold=" + amountSold +
                ", income=" + income +
                ", salePercentage=" + salePercentage +
                '}';
    }
}
