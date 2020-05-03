package dataAccess;

import model.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrderDBAccess implements OrderDataAccess {
    private Connection connection;

    public OrderDBAccess() {
        this.connection = DBConnection.getDBConnection();
    }

    @Override
    public ArrayList<Order> getAllOrders() throws SQLException {
        String sqlInstruction = "SELECT o.*, s.*, p.*, c.*, r.*, e.*, city.* FROM `order` o\n"+
                "JOIN status s ON s.id = o.StatusNumber\n" +
                "JOIN paymentmethod p ON p.id = o.paymentMethodId\n" +
                "JOIN customer c ON c.EntityId = o.CustomerEntityId\n" +
                "JOIN `rank` r ON r.id = c.RankId\n" +
                "JOIN entity e ON e.id = o.CustomerEntityId\n" +
                "JOIN city ON e.CityLabel = city.label AND e.CityZipCode = city.zipCode";
        ArrayList<Order> orderList;
        try {
            ResultSet data = connection.createStatement().executeQuery(sqlInstruction);

            orderList = new ArrayList<>();
            Order order;
            Customer customer;
            Entity entity;
            City city;
            Rank rank;
            Status status;
            PaymentMethod paymentMethod;

            GregorianCalendar calendar = null;
            GregorianCalendar subscribtionDateGregorian = null;

            while(data.next()) {
                city = new City(
                        data.getString("e.CityLabel"),
                        data.getInt("e.CityZipCode")
                );

                entity = new Entity(
                        data.getInt("e.id"),
                        data.getString("e.mail"),
                        data.getString("e.contactName"),
                        data.getString("e.phoneNumber"),
                        data.getInt("e.houseNumber"),
                        data.getString("e.street"),
                        data.getString("e.bankAccountNumber"),
                        data.getString("e.businessNumber"),
                        city
                );

                java.sql.Date subscribtionDate = data.getDate("c.subscribtionDate");
                if (!data.wasNull()) {
                    subscribtionDateGregorian = new GregorianCalendar();
                    subscribtionDateGregorian.setTime(subscribtionDate);
                }

                rank = new Rank(
                        data.getInt("r.id"),
                        data.getString("r.label"),
                        data.getInt("r.creditLimit")
                );

                customer = new Customer(
                        entity,
                        rank,
                        subscribtionDateGregorian
                );

                status = new Status(
                        data.getInt("s.id"),
                        data.getString("s.label")
                );

                paymentMethod = new PaymentMethod(
                        data.getInt("p.id"),
                        data.getString("p.label")
                );

                java.sql.Date startingDate = data.getDate("startingDate");
                if (startingDate != null) {
                    calendar = new GregorianCalendar();
                    calendar.setTime(startingDate);
                }

                order = new Order(
                        customer,
                        data.getInt("o.reference"),
                        data.getBoolean("o.isPaid"),
                        calendar,
                        status,
                        paymentMethod
                );

                String sqlDelivery = "SELECT d.*, emp.*, e.*, c.* FROM delivery d\n" +
                        "JOIN employee emp ON emp.EntityId = d.EmployeeEntityId\n" +
                        "JOIN entity e ON e.id = d.EmployeeEntityId\n" +
                        "JOIN city c ON e.CityLabel = c.label AND e.CityZipCode = c.zipCode\n" +
                        "WHERE d.OrderReference = ?";
                PreparedStatement preparedStatementDelivery = connection.prepareStatement(sqlDelivery);
                preparedStatementDelivery.setInt(1, order.getReference());
                ResultSet dataDelivery = preparedStatementDelivery.executeQuery();
//                dataDelivery.next();
                if(dataDelivery.next()){
                    GregorianCalendar plannedDateG = null;
                    GregorianCalendar deliveredDateG = null;

                    java.sql.Date plannedDate = dataDelivery.getDate("d.plannedDate");
                    if (plannedDate != null) {
                        plannedDateG = new GregorianCalendar();
                        plannedDateG.setTime(plannedDate);
                    }

                    java.sql.Date deliveredDate = dataDelivery.getDate("d.deliveredDate");
                    if (deliveredDate != null) {
                        deliveredDateG = new GregorianCalendar();
                        deliveredDateG.setTime(deliveredDate);
                    }

                    City cityD = new City(
                            dataDelivery.getString("c.label"),
                            dataDelivery.getInt("c.zipCode")
                    );

                    Entity entityDeliveryMan = new Entity(
                            data.getInt("e.id"),
                            data.getString("e.mail"),
                            data.getString("e.contactName"),
                            data.getString("e.phoneNumber"),
                            data.getInt("e.houseNumber"),
                            data.getString("e.street"),
                            data.getString("e.bankAccountNumber"),
                            data.getString("e.businessNumber"),
                            cityD
                    );

                    Employee deliveryMan = new Employee(
                            dataDelivery.getInt("emp.EntityId"),
                            dataDelivery.getInt("emp.RoleId"),
                            dataDelivery.getString("emp.password"),
                            entityDeliveryMan);

                    Delivery delivery = new Delivery(
                            deliveryMan,
                            dataDelivery.getInt("d.id"),
                            plannedDateG,
                            deliveredDateG,
                            order
                    );
                    order.setDelivery(delivery);
                }

                String sqlOrderLine = "SELECT o.*, p.*, v.*, provider.*, e.*, city.* FROM OrderLine o\n"+
                        "JOIN product p ON p.code = o.Productcode\n" +
                        "JOIN vatcode v ON v.rate = p.VATCodeRate\n" +
                        "JOIN provider ON provider.entityId = p.ProviderEntityId\n" +
                        "JOIN entity e ON e.id = provider.entityId\n" +
                        "JOIN city c ON c.label = provider.entityId\n" +
                        "JOIN city ON e.CityLabel = city.label AND e.CityZipCode = city.zipCode\n" +
                        "WHERE Orderreference = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlOrderLine);
                preparedStatement.setInt(1, order.getReference());
                try {
                    ResultSet dataOrderLine = preparedStatement.executeQuery();
                    while(dataOrderLine.next()){
                        //Add all orderline to already created order object.
                        City providerCity = new City(
                                data.getString("e.CityLabel"),
                                data.getInt("e.CityZipCode")
                        );

                        Entity providerEntity = new Entity(
                                dataOrderLine.getInt("e.id"),
                                data.getString("e.mail"),
                                data.getString("e.contactName"),
                                data.getString("e.phoneNumber"),
                                data.getInt("e.houseNumber"),
                                data.getString("e.street"),
                                data.getString("e.bankAccountNumber"),
                                data.getString("e.businessNumber"),
                                providerCity
                        );

                        Provider provider = new Provider(
                                providerEntity,
                                dataOrderLine.getString("provider.providerType")
                        );

                        Product product = new Product(
                                provider,
                                dataOrderLine.getInt("p.code"),
                                dataOrderLine.getString("p.label"),
                                dataOrderLine.getDouble("p.unitPrice"),
                                dataOrderLine.getInt("p.currentStock"),
                                dataOrderLine.getInt("p.maxStock"),
                                dataOrderLine.getInt("p.minStock"),
                                dataOrderLine.getInt("v.rate")
                        );
                        OrderLine orderLine = new OrderLine(
                                product,
                                order,
                                dataOrderLine.getInt("o.quantity"),
                                dataOrderLine.getDouble("o.salesUnitPrice")
                        );
                        order.addOrderLine(orderLine);
                    }
                } catch(SQLException ex){
                    throw ex;
                }

                orderList.add(order);
            }
        } catch (SQLException e) {
            throw e;
        }

        return orderList;
    }


    public boolean create(Order order) {
        int affectedRow = 0;
        String sqlInstructionOrder = "INSERT INTO `order` (startingDate, isPaid, StatusNumber, paymentMethodId, CustomerEntityId)\n" +
                                     "VALUES (?,?,?,?,?)";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sqlInstructionOrder, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setDate(1, Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setDate(1, new java.sql.Date(order.getStartingDate().getTimeInMillis()));
            preparedStatement.setBoolean(2, order.getPaid());
            preparedStatement.setInt(3, order.getStatus().getId());
            preparedStatement.setInt(4, order.getPaymentMethod().getId());
            preparedStatement.setInt(5, order.getCustomer().getEntity().getId());

            affectedRow = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (affectedRow != 0) {
            try {
                ResultSet genretadKeys = preparedStatement.getGeneratedKeys();
                if (genretadKeys.next()) {
                    int orderId = genretadKeys.getInt(1);

                    if (order.getDelivery() != null) {
                        String sqlDelivery = "INSERT INTO delivery (plannedDate, OrderReference, EmployeeEntityId)\n" +
                                             "VALUES (?,?,?)";

                        PreparedStatement preparedStatementDelivery = connection.prepareStatement(sqlDelivery);
                        preparedStatementDelivery.setDate(1, new java.sql.Date(order.getDelivery().getPlannedDate().getTimeInMillis()));
                        preparedStatementDelivery.setInt(2, orderId);
                        preparedStatementDelivery.setInt(3, order.getDelivery().getEmployee().getId());

                        preparedStatementDelivery.executeUpdate();
                    }

                    String sqlOrderLine = "INSERT INTO orderline (productCode, Orderreference, quantity, salesUnitPrice)\n" +
                                          "VALUES(?,?,?,?)";
                    PreparedStatement preparedStatementOrderLine = connection.prepareStatement(sqlOrderLine);

                    for (OrderLine line : order.getOrderLineList()) {
                        preparedStatementOrderLine.setInt(1, line.getProduct().getCode());
                        preparedStatementOrderLine.setInt(2, orderId);
                        preparedStatementOrderLine.setInt(3, line.getQuantity());
                        preparedStatementOrderLine.setDouble(4, line.getSalesUnitPrice());

                        preparedStatementOrderLine.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {

                String protection = "rollback to security1";
                PreparedStatement preparedStatement1 = null;
                connection.prepareStatement(protection).executeUpdate();
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
