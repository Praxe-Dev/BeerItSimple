package dataAccess;

import exception.*;
import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.sql.Date;

public class OrderDBAccess implements OrderDataAccess {
    private Connection connection;

    public OrderDBAccess() throws ConnectionException {
        this.connection = DBConnection.getInstance();
    }

    @Override
    public ArrayList<Order> getAllOrders() throws DataQueryException {
        String sqlInstruction = "SELECT o.*, s.*, p.* FROM `order` o\n" +
                "JOIN status s ON s.id = o.StatusNumber\n" +
                "JOIN paymentmethod p ON p.id = o.paymentMethodId\n";

        ArrayList<Order> orderList = new ArrayList<>();
        try {
            ResultSet data = connection.createStatement().executeQuery(sqlInstruction);

            orderList = new ArrayList<>();
            Order order;
            Status status;
            Integer customerId;
            PaymentMethod paymentMethod;

            GregorianCalendar calendar = null;


            while (data.next()) {

                customerId = data.getInt("o.CustomerEntityId");

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
                        null,
                        data.getInt("o.reference"),
                        data.getBoolean("o.isPaid"),
                        calendar,
                        status,
                        paymentMethod
                );

                setCustomerFromId(order, customerId);

                setDeliveryFromOrder(order);

                String sqlOrderLine = "SELECT o.*, p.*, v.* FROM OrderLine o\n" +
                        "JOIN product p ON p.code = o.Productcode\n" +
                        "JOIN vatcode v ON v.rate = p.VATCodeRate\n" +
                        "WHERE Orderreference = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlOrderLine);
                preparedStatement.setInt(1, order.getReference());

                ResultSet dataOrderLine = preparedStatement.executeQuery();
                while (dataOrderLine.next()) {
                    Product product = new Product(
                            null,
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

                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DataQueryException();
        }

        return orderList;
    }

    public Order getOrder(int reference) throws NoRowSelected {
        Order order = null;
        Status status = null;
        PaymentMethod paymentMethod = null;

        String sqlInstruction = "SELECT o.*, p.*, s.*\n" +
                "FROM `order` o JOIN paymentmethod p ON o.paymentMethodId = p.id\n" +
                "JOIN `status` s ON o.statusNumber = s.id\n" +
                "WHERE o.`reference` = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, reference);

            ResultSet data = preparedStatement.executeQuery();

            if (data.next()) {

                int customerId = data.getInt("o.CustomerEntityId");

                status = new Status(
                        data.getInt("s.id"),
                        data.getString("s.label")
                );

                paymentMethod = new PaymentMethod(
                        data.getInt("p.id"),
                        data.getString("p.label")
                );

                GregorianCalendar startingDate = new GregorianCalendar();
                startingDate.setTime(data.getDate("o.startingDate"));
                order = new Order(
                        null,
                        data.getInt("o.reference"),
                        data.getBoolean("o.isPaid"),
                        startingDate,
                        status,
                        paymentMethod,
                        null
                );

                setDeliveryFromOrder(order);
                setCustomerFromId(order, customerId);
                setOrderLineFromOrder(order);

            }
        } catch (SQLException e) {
            throw new NoRowSelected();
        }

        return order;
    }

    @Override
    public ArrayList<Order> getAllOrdersBetweenDates(LocalDate startingDate, LocalDate endDate) {
        String sqlInstruction = "SELECT o.*, s.*, p.* FROM `order` o\n" +
                "JOIN status s ON s.id = o.StatusNumber\n" +
                "JOIN paymentmethod p ON p.id = o.paymentMethodId\n" +
                "WHERE ? <= o.startingDate AND ? >= o.startingDate";

        ArrayList<Order> orderList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
//            java.sql.Date start = new Date(0);
            Date start = Date.valueOf(startingDate);
            Date end = Date.valueOf(endDate);

            preparedStatement.setDate(1, start);
            preparedStatement.setDate(2, end);

            ResultSet data = preparedStatement.executeQuery();

            Order order;
            Status status;
            Integer customerId;
            PaymentMethod paymentMethod;
            GregorianCalendar calendar = null;

            while (data.next()) {
                customerId = data.getInt("o.CustomerEntityId");

                status = new Status(
                        data.getInt("s.id"),
                        data.getString("s.label")
                );

                paymentMethod = new PaymentMethod(
                        data.getInt("p.id"),
                        data.getString("p.label")
                );

                java.sql.Date startDate = data.getDate("startingDate");
                if (startDate != null) {
                    calendar = new GregorianCalendar();
                    calendar.setTime(startDate);
                }

                order = new Order(
                        null,
                        data.getInt("o.reference"),
                        data.getBoolean("o.isPaid"),
                        calendar,
                        status,
                        paymentMethod
                );

                setCustomerFromId(order, customerId);
                setDeliveryFromOrder(order);
                setOrderLineFromOrder(order);

                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    @Override
    public ArrayList<Order> getOrdersFromRanks(Rank rank, Status status, Boolean isPaid) {

        String sqlInstruction = "SELECT DISTINCT o.*, s.*, p.* FROM `order` o\n" +
                "JOIN status s ON s.id = o.StatusNumber\n" +
                "JOIN paymentmethod p ON p.id = o.paymentMethodId\n" +
                "JOIN customer ON customer.RankId = ?\n";

        if (status != null) {
            sqlInstruction += "WHERE s.id = ?\n";
            if (isPaid != null) {
                sqlInstruction += "AND o.isPaid = ?";
            }
        } else if (isPaid != null) {
            sqlInstruction += "WHERE o.isPaid = ?";
        }

        ArrayList<Order> orders = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, rank.getId());

            if (status != null) {
                preparedStatement.setInt(2, status.getId());
                if (isPaid != null) {
                    preparedStatement.setBoolean(3, isPaid);
                }
            } else if (isPaid != null) {
                preparedStatement.setBoolean(2, isPaid);
            }

            ResultSet data = preparedStatement.executeQuery();

            Order o;
            Status s;
            Integer customerId;
            PaymentMethod paymentMethod;
            GregorianCalendar calendar = null;

            while (data.next()) {
                customerId = data.getInt("o.CustomerEntityId");

                s = new Status(
                        data.getInt("s.id"),
                        data.getString("s.label")
                );

                paymentMethod = new PaymentMethod(
                        data.getInt("p.id"),
                        data.getString("p.label")
                );

                java.sql.Date startDate = data.getDate("startingDate");
                if (startDate != null) {
                    calendar = new GregorianCalendar();
                    calendar.setTime(startDate);
                }

                o = new Order(
                        null,
                        data.getInt("o.reference"),
                        data.getBoolean("o.isPaid"),
                        calendar,
                        s,
                        paymentMethod);

                setCustomerFromId(o, customerId);
                setDeliveryFromOrder(o);

                orders.add(o);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return orders;
    }

    public boolean create(Order order) throws SQLException, SQLManageException {
        int affectedRow = 0;
        String sqlInstructionOrder = "INSERT INTO `order` (startingDate, isPaid, StatusNumber, paymentMethodId, CustomerEntityId)\n" +
                "VALUES (?,?,?,?,?)";

        PreparedStatement preparedStatement = null;

//        try {
        preparedStatement = connection.prepareStatement(sqlInstructionOrder, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setDate(1, Date.valueOf(java.time.LocalDate.now()));
        preparedStatement.setDate(1, new java.sql.Date(order.getStartingDate().getTimeInMillis()));
        preparedStatement.setBoolean(2, order.getPaid());
        preparedStatement.setInt(3, order.getStatus().getId());
        preparedStatement.setInt(4, order.getPaymentMethod().getId());
        preparedStatement.setInt(5, order.getCustomer().getEntity().getId());

        affectedRow = preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        if (affectedRow != 0) {
//            try {
            ResultSet genretadKeys = preparedStatement.getGeneratedKeys();
            if (genretadKeys.next()) {
                int orderId = genretadKeys.getInt(1);

                if (order.getDelivery() != null) {
                    String sqlDelivery = "INSERT INTO delivery (plannedDate, OrderReference, EmployeeEntityId)\n" +
                            "VALUES (?,?,?)";

                    PreparedStatement preparedStatementDelivery = connection.prepareStatement(sqlDelivery);
                    preparedStatementDelivery.setDate(1, new java.sql.Date(order.getDelivery().getPlannedDate().getTimeInMillis()));
                    preparedStatementDelivery.setInt(2, orderId);
                    preparedStatementDelivery.setInt(3, order.getDelivery().getEmployee().getEntity().getId());

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
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        } else {
//            try {

            String protection = "rollback to security1";
            PreparedStatement preparedStatement1 = null;
            connection.prepareStatement(protection).executeUpdate();
            return false;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }

        return true;
    }

    public Rank updateCustomerRank(Customer customer) throws SQLManageException {
        //Get All Orders and orderlines
        Rank newRank = null;
        String sqlInstruction = "SELECT sum(ol.salesUnitPrice*ol.quantity) AS ordersSum FROM `order` JOIN `orderline` ol ON ol.Orderreference = order.reference  WHERE order.CustomerEntityId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, customer.getEntity().getId());
            ResultSet data = preparedStatement.executeQuery();
            if (data.next()) {
                //Search new rank
                String sqlSearch = "SELECT * FROM `rank` WHERE minAmountOrder <= ? ORDER BY id DESC LIMIT 1";
                PreparedStatement preparedRankStatement = connection.prepareStatement(sqlSearch);
                preparedRankStatement.setDouble(1, data.getDouble("ordersSum"));
                ResultSet dataRank = preparedRankStatement.executeQuery();
                if (dataRank.next()) {
                    newRank = new Rank(
                            dataRank.getInt("id"),
                            dataRank.getString("label"),
                            dataRank.getInt("creditLimit"),
                            dataRank.getInt("minAmountOrder")
                    );
                    if (customer.getRank().getId() != newRank.getId()) {
                        //Update rank
                        String updateRank = "UPDATE customer SET RankId = ? WHERE EntityId = ?";
                        PreparedStatement preparedUpdateRank = connection.prepareStatement(updateRank);
                        preparedUpdateRank.setInt(1, newRank.getId());
                        preparedUpdateRank.setInt(2, customer.getEntity().getId());
                        preparedUpdateRank.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            new SQLManageException(e);
        }
        return newRank;
    }

    public boolean deleteOrder(Order order) throws DeletionExceiption {
        try {

            String sqlInstruction = "SET SQL_SAFE_UPDATES = 0";
            connection.prepareStatement(sqlInstruction).execute();


            String sqlOrderline = "DELETE FROM orderline\n" +
                    "WHERE Orderreference = ?;\n";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlOrderline);
            preparedStatement.setInt(1, order.getReference());
            preparedStatement.executeUpdate();

            if (order.getDelivery() != null) {
                String sqlDelivery = /*"SET SQL_SAFE_UPDATES = 0;\n" +*/
                        "DELETE FROM delivery\n" +
                                "WHERE OrderReference = ?;\n";

                PreparedStatement preparedStatementDelivery = connection.prepareStatement(sqlDelivery);
                preparedStatementDelivery.setInt(1, order.getReference());
                preparedStatementDelivery.executeUpdate();
            }

            sqlInstruction = "SET SQL_SAFE_UPDATES = 1";
            connection.prepareStatement(sqlInstruction).execute();

            String sqlOrder = "DELETE FROM `order`\n" +
                    "WHERE `reference` = ?";
            PreparedStatement preparedStatementOrder = connection.prepareStatement(sqlOrder);
            preparedStatementOrder.setInt(1, order.getReference());
            preparedStatementOrder.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DeletionExceiption();
        }

        return true;
    }

    private void setCustomerFromId(Order order, int customerId) throws SQLException {
        Entity entity;
        if (customerId != 0) {

            Customer customer;
            Entity customerEntity;
            City city;
            Rank rank;
            GregorianCalendar subscriptionDateGregorian = null;

            String sqlCustomerInstruction = "SELECT c.*, e.*, r.*, city.*\n" +
                    "FROM customer c JOIN entity e ON e.id = c.Entityid\n" +
                    "JOIN `rank` r ON r.id = c.RankId\n" +
                    "JOIN city ON e.CityLabel = city.label AND e.CityZipCode = city.zipCode\n" +
                    "WHERE c.EntityId = ?";

            PreparedStatement preparedStatementCustomer = connection.prepareStatement(sqlCustomerInstruction);
            preparedStatementCustomer.setInt(1, customerId);

            ResultSet dataCustomer = preparedStatementCustomer.executeQuery();
            dataCustomer.next();

            city = new City(
                    dataCustomer.getString("city.label"),
                    dataCustomer.getInt("city.zipCode")
            );

            entity = new Entity(
                    dataCustomer.getInt("e.id"),
                    dataCustomer.getString("e.mail"),
                    dataCustomer.getString("e.contactName"),
                    dataCustomer.getString("e.phoneNumber"),
                    dataCustomer.getInt("e.houseNumber"),
                    dataCustomer.getString("e.street"),
                    dataCustomer.getString("e.bankAccountNumber"),
                    dataCustomer.getString("e.businessNumber"),
                    city
            );

            Date subscriptionDate = dataCustomer.getDate("c.subscriptionDate");
            if (!dataCustomer.wasNull()) {
                subscriptionDateGregorian = new GregorianCalendar();
                subscriptionDateGregorian.setTime(subscriptionDate);
            }

            rank = new Rank(
                    dataCustomer.getInt("r.id"),
                    dataCustomer.getString("r.label"),
                    dataCustomer.getInt("r.creditLimit")
            );

            customer = new Customer(
                    entity,
                    rank,
                    subscriptionDateGregorian
            );
            order.setCustomer(customer);
        }
    }

    private void setOrderLineFromOrder(Order order) throws SQLException {
        String sqlOrderLine = "SELECT o.*, p.*\n" +
                "FROM orderline o JOIN product p ON o.Productcode = p.code\n" +
                "WHERE o.Orderreference = ?;";
        ArrayList<OrderLine> orderLines = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(sqlOrderLine);
        preparedStatement.setInt(1, order.getReference());

        ResultSet dataOrderLine = preparedStatement.executeQuery();
        while (dataOrderLine.next()) {
            Product product = new Product(
                    null,
                    dataOrderLine.getInt("p.code"),
                    dataOrderLine.getString("p.label"),
                    dataOrderLine.getDouble("p.unitPrice"),
                    dataOrderLine.getInt("p.currentStock"),
                    dataOrderLine.getInt("p.maxStock"),
                    dataOrderLine.getInt("p.minStock"),
                    dataOrderLine.getInt("p.VATCodeRate")
            );
            OrderLine orderLine = new OrderLine(
                    product,
                    order,
                    dataOrderLine.getInt("o.quantity"),
                    dataOrderLine.getDouble("o.salesUnitPrice")
            );
            order.addOrderLine(orderLine);
        }
    }

    private void setDeliveryFromOrder(Order order) throws SQLException {
        String sqlDelivery = "SELECT d.*, emp.*, e.*, c.*, r.* FROM delivery d\n" +
                "LEFT OUTER JOIN employee emp ON emp.EntityId = d.EmployeeEntityId\n" +
                "LEFT OUTER JOIN entity e ON e.id = emp.EntityId\n" +
                "LEFT OUTER JOIN city c ON e.CityLabel = c.label AND e.CityZipCode = c.zipCode\n" +
                "LEFT OUTER JOIN role r ON r.id = emp.RoleId\n" +
                "WHERE d.OrderReference = ?";
        PreparedStatement preparedStatementDelivery = connection.prepareStatement(sqlDelivery);

        preparedStatementDelivery.setInt(1, order.getReference());
        ResultSet dataDelivery = preparedStatementDelivery.executeQuery();
//                dataDelivery.next();
        if (dataDelivery.next()) {
            GregorianCalendar plannedDateG = null;
            GregorianCalendar deliveredDateG = null;

            Date plannedDate = dataDelivery.getDate("d.plannedDate");
            if (plannedDate != null) {
                plannedDateG = new GregorianCalendar();
                plannedDateG.setTime(plannedDate);
            }

            Date deliveredDate = dataDelivery.getDate("d.deliveredDate");
            if (deliveredDate != null) {
                deliveredDateG = new GregorianCalendar();
                deliveredDateG.setTime(deliveredDate);
            }

            City cityD = new City(
                    dataDelivery.getString("c.label"),
                    dataDelivery.getInt("c.zipCode")
            );

            Entity entityDeliveryMan = new Entity(
                    dataDelivery.getInt("e.id"),
                    dataDelivery.getString("e.mail"),
                    dataDelivery.getString("e.contactName"),
                    dataDelivery.getString("e.phoneNumber"),
                    dataDelivery.getInt("e.houseNumber"),
                    dataDelivery.getString("e.street"),
                    dataDelivery.getString("e.bankAccountNumber"),
                    dataDelivery.getString("e.businessNumber"),
                    cityD
            );

            Role role = new Role(
                    dataDelivery.getInt("r.id"),
                    dataDelivery.getString("r.name")
            );

            Employee deliveryMan = new Employee(
                    entityDeliveryMan,
                    role,
                    dataDelivery.getString("emp.password")
            );

            Delivery delivery = new Delivery(
                    deliveryMan,
                    dataDelivery.getInt("d.id"),
                    plannedDateG,
                    deliveredDateG,
                    order
            );
            order.setDelivery(delivery);
        }
    }

    public boolean updateOrder(Order order) throws SQLManageException {
        int affectedRow = 0;

        String sqlInstruction = "UPDATE `order` SET `isPaid` = ?, `statusNumber` = ?, `paymentMethodId` = ? WHERE `reference` = ?";

        try {
            //Order update
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setBoolean(1, order.getPaid());
            preparedStatement.setInt(2, order.getStatus().getId());
            preparedStatement.setInt(3, order.getPaymentMethod().getId());
            preparedStatement.setInt(4, order.getReference());
            preparedStatement.executeUpdate();

            //Delivery update
            if (order.getDelivery() != null) {
                updateDelivery(order);
            } else {
                deleteDelivery(order);
            }

            //Orderlines update
            updateOrderLines(order);

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLManageException(e);
        } catch (SQLException e) {
            throw new SQLManageException(e);
        }

        return true;
    }

    private void deleteDelivery(Order order) throws SQLManageException {
        String sqlDeleteDelivery = "DELETE FROM delivery WHERE OrderReference = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteDelivery);
            preparedStatement.setInt(1, order.getReference());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLManageException(e);
        }
    }

    private void updateDelivery(Order order) throws SQLManageException {
        //Delivery select
        String sqlDeliveryInstruction = "SELECT * FROM delivery WHERE OrderReference = ?";
        try {
            PreparedStatement preparedStatement2 = connection.prepareStatement(sqlDeliveryInstruction);
            preparedStatement2.setInt(1, order.getReference());
            ResultSet dataDelivery = preparedStatement2.executeQuery();
            if (dataDelivery.next()) {
                //Delivery update
                Date deliveredDate = null;
                if (order.getDelivery().getDeliveredDate() != null) {
                    deliveredDate = new Date(order.getDelivery().getDeliveredDate().getTimeInMillis());
                }
                String updateDelivery = "UPDATE `delivery`\n" +
                        "SET\n" +
                        " `plannedDate` = ?,\n" +
                        " `deliveredDate` = ?\n" +
                        "WHERE `id` = ?";
                PreparedStatement preparedStatementDelivery = connection.prepareStatement(updateDelivery);
                preparedStatementDelivery.setDate(1, new java.sql.Date(order.getDelivery().getPlannedDate().getTimeInMillis()));
                preparedStatementDelivery.setDate(2, deliveredDate);
                preparedStatementDelivery.setInt(3, dataDelivery.getInt("id"));
                preparedStatementDelivery.executeUpdate();
            } else {
                //Create delivery
                String sqlDelivery = "INSERT INTO delivery (plannedDate, OrderReference, EmployeeEntityId)\n" +
                        "VALUES (?,?,?)";

                PreparedStatement preparedStatementDelivery = connection.prepareStatement(sqlDelivery);
                preparedStatementDelivery.setDate(1, new java.sql.Date(order.getDelivery().getPlannedDate().getTimeInMillis()));
                preparedStatementDelivery.setInt(2, order.getReference());
                preparedStatementDelivery.setInt(3, order.getDelivery().getEmployee().getEntity().getId());

                preparedStatementDelivery.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLManageException(e);
        }
    }

    private void updateOrderLines(Order order) throws SQLManageException {
        //Check if update necessary for all orderLine
        for (OrderLine orderLine : order.getOrderLineList()) {
            //Chercher si une ligne existe pour ce produit. Si oui, update la quantité. Si non, l'ajouter.
            String selectOrderLine = "SELECT * FROM orderline WHERE Orderreference = ? AND Productcode = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectOrderLine);
                preparedStatement.setInt(1, order.getReference());
                preparedStatement.setInt(2, orderLine.getProduct().getCode());

                ResultSet data = preparedStatement.executeQuery();
                if (data.next()) {
                    //Update quantity if necessary
                    Integer currentQuantity = data.getInt("quantity");
                    if (currentQuantity != orderLine.getQuantity()) {
                        String updateOrderLine = "UPDATE orderLine SET quantity = ? WHERE Orderreference = ? AND Productcode = ?";
                        PreparedStatement preparedStatementUpdate = connection.prepareStatement(updateOrderLine);
                        preparedStatementUpdate.setInt(1, orderLine.getQuantity());
                        preparedStatementUpdate.setInt(2, order.getReference());
                        preparedStatementUpdate.setInt(3, orderLine.getProduct().getCode());
                        preparedStatementUpdate.executeUpdate();
                    }
                } else {
                    //Add new line to this order
                    String sqlOrderLine = "INSERT INTO orderline (Productcode, Orderreference, quantity, salesUnitPrice)\n" +
                            "VALUES(?,?,?,?)";
                    try {
                        PreparedStatement preparedStatementOrderLine = connection.prepareStatement(sqlOrderLine);
                        preparedStatementOrderLine.setInt(1, orderLine.getProduct().getCode());
                        preparedStatementOrderLine.setInt(2, order.getReference());
                        preparedStatementOrderLine.setInt(3, orderLine.getQuantity());
                        preparedStatementOrderLine.setDouble(4, orderLine.getSalesUnitPrice());

                        preparedStatementOrderLine.executeUpdate();
                    } catch (SQLException e) {
                        throw new SQLManageException(e);
                    }
                }
            } catch (SQLException e) {
                throw new SQLManageException(e);
            }
        }
        checkOrderLinesToDelete(order);
    }

    private void checkOrderLinesToDelete(Order order) throws SQLManageException {
        //Loop on all order line of mentionned order if exist in order.getOrderLineList. If not, need to delete !
        String selectOrderLine = "SELECT * FROM orderline WHERE Orderreference = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectOrderLine);
            preparedStatement.setInt(1, order.getReference());

            ResultSet data = preparedStatement.executeQuery();
            ArrayList<OrderLine> orderLinesExists = new ArrayList<>();
            while (data.next()) {
                //Select product from this orderline to create OrderLine object
                String selectProduct = "SELECT * FROM product WHERE code = ?";
                PreparedStatement preparedStatementProduct = connection.prepareStatement(selectProduct);
                preparedStatementProduct.setInt(1, data.getInt("Productcode"));

                ResultSet dataProduct = preparedStatementProduct.executeQuery();
                if (dataProduct.next()) {
                    Product product = new Product(dataProduct.getInt("code"));
                    OrderLine orderLine = new OrderLine(product, order, data.getInt("quantity"), data.getDouble("salesUnitPrice"));
                    orderLinesExists.add(orderLine);
                }
            }

            ArrayList<OrderLine> orderLinesFromUpdate = order.getOrderLineList();
            for (OrderLine orderLineExist : orderLinesExists) {
                //Check if this code product and reference order exist in order.getOrderLineList. If not, delete from database.
                for (int i = 0; i < orderLinesFromUpdate.size(); i++) {
                    if (orderLinesFromUpdate.get(i).getProduct().getCode() == orderLineExist.getProduct().getCode()) {
                        break;
                    }

                    if (i + 1 == orderLinesFromUpdate.size()) {
                        //Delete orderLineExist from db
                        String deleteOrderLine = "DELETE FROM orderline WHERE Productcode = ? AND Orderreference = ?";
                        PreparedStatement preparedDeleteOrderLine = connection.prepareStatement(deleteOrderLine);
                        preparedDeleteOrderLine.setInt(1, orderLineExist.getProduct().getCode());
                        preparedDeleteOrderLine.setInt(2, order.getReference());

                        preparedDeleteOrderLine.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLManageException(e);
        }
    }

    public ArrayList<Order> getAllOrdersFromZipCode(City city) throws SQLManageException {
        ArrayList<Order> orderArrayList = new ArrayList<>();
        String sqlInstruction = "SELECT o.*, s.*, p.*, e.* FROM `order` o\n" +
                "JOIN `status` s ON s.id = o.StatusNumber\n" +
                "JOIN paymentmethod p ON p.id = o.paymentMethodId\n" +
                "JOIN `entity` e ON e.id = o.CustomerEntityId\n" +
                "WHERE e.CityZipCode = ? AND e.Citylabel = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, city.getZipCode());
            preparedStatement.setString(2, city.getLabel());
            ResultSet data = preparedStatement.executeQuery();

            Order order;
            Status status;
            Integer customerId;
            PaymentMethod paymentMethod;

            GregorianCalendar calendar = null;


            while (data.next()) {

                customerId = data.getInt("o.CustomerEntityId");

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
                        null,
                        data.getInt("o.reference"),
                        data.getBoolean("o.isPaid"),
                        calendar,
                        status,
                        paymentMethod
                );

                setCustomerFromId(order, customerId);

                setDeliveryFromOrder(order);

                String sqlOrderLine = "SELECT o.*, p.*, v.*, provider.*, e.*, city.* FROM OrderLine o\n" +
                        "JOIN product p ON p.code = o.Productcode\n" +
                        "JOIN vatcode v ON v.rate = p.VATCodeRate\n" +
                        "JOIN provider ON provider.entityId = p.ProviderEntityId\n" +
                        "JOIN entity e ON e.id = provider.entityId\n" +
                        "JOIN city c ON c.label = provider.entityId\n" +
                        "JOIN city ON e.CityLabel = city.label AND e.CityZipCode = city.zipCode\n" +
                        "WHERE Orderreference = ?";
                PreparedStatement preparedStatement2 = connection.prepareStatement(sqlOrderLine);
                preparedStatement2.setInt(1, order.getReference());
                try {
                    ResultSet dataOrderLine = preparedStatement2.executeQuery();
                    while (dataOrderLine.next()) {
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
                } catch (SQLException ex) {
                    throw ex;
                }

                orderArrayList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderArrayList;
    }

    public ArrayList<Order> getAllOrdersFromCustomer(Customer customer) throws SQLManageException {
        ArrayList<Order> orderArrayList = new ArrayList<>();
        String sqlInstruction = "SELECT o.*, s.*, p.* FROM `order` o\n" +
                "JOIN status s ON s.id = o.StatusNumber\n" +
                "JOIN paymentmethod p ON p.id = o.paymentMethodId\n" +
                "WHERE o.CustomerEntityId = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, customer.getEntity().getId());
            ResultSet data = preparedStatement.executeQuery();

            Order order;
            Status status;
            Integer customerId;
            PaymentMethod paymentMethod;

            GregorianCalendar calendar = null;


            while (data.next()) {

                customerId = data.getInt("o.CustomerEntityId");

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
                        null,
                        data.getInt("o.reference"),
                        data.getBoolean("o.isPaid"),
                        calendar,
                        status,
                        paymentMethod
                );

                setCustomerFromId(order, customerId);

                setDeliveryFromOrder(order);

                String sqlOrderLine = "SELECT o.*, p.*, v.*, provider.*, e.*, city.* FROM OrderLine o\n" +
                        "JOIN product p ON p.code = o.Productcode\n" +
                        "JOIN vatcode v ON v.rate = p.VATCodeRate\n" +
                        "JOIN provider ON provider.entityId = p.ProviderEntityId\n" +
                        "JOIN entity e ON e.id = provider.entityId\n" +
                        "JOIN city c ON c.label = provider.entityId\n" +
                        "JOIN city ON e.CityLabel = city.label AND e.CityZipCode = city.zipCode\n" +
                        "WHERE Orderreference = ?";
                PreparedStatement preparedStatement2 = connection.prepareStatement(sqlOrderLine);
                preparedStatement2.setInt(1, order.getReference());
                try {
                    ResultSet dataOrderLine = preparedStatement2.executeQuery();
                    while (dataOrderLine.next()) {
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
                } catch (SQLException ex) {
                    throw ex;
                }

                orderArrayList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderArrayList;
    }
}
