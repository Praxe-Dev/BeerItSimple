package dataAccess;

import exception.CustomerException;
import model.City;
import model.Customer;
import model.Entity;
import model.Rank;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CustomerDBAccess implements CustomerDataAccess{
    private Connection connection;

    public CustomerDBAccess() {
        this.connection = DBConnection.getDBConnection();
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        String sqlInstruction = "SELECT customer.*, e.*, r.*, city.*\n" +
                                "FROM customer JOIN entity e ON customer.EntityId = e.id\n" +
                                "JOIN `rank` r ON r .id = customer.RankId\n" +
                                "JOIN city ON e.CityLabel = city.label AND e.CityZipCode = city.zipCode";
        ArrayList<Customer> customerList;
        try {
            ResultSet data = connection.createStatement().executeQuery(sqlInstruction);

            customerList = new ArrayList<>();
            Customer customer;
            Entity entity;
            City city;
            Rank rank;

            String mail;
            String fax;
            String VATNumber;
            GregorianCalendar calendar = null;

            while(data.next()) {
                city = new City(
                        data.getString("CityLabel"),
                        data.getInt("CityZipCode")
                );

                entity = new Entity(
                        data.getInt("id"),
                        data.getString("contactName"),
                        data.getString("phoneNumber"),
                        data.getInt("houseNumber"),
                        data.getString("street"),
                        city
                );

                rank = new Rank(
                        data.getInt("r.id"),
                        data.getString("r.label"),
                        data.getInt("r.creditLimit")
                );

                mail = data.getString("mail");
                if (!data.wasNull()) {
                    entity.setMail(mail);
                }

                fax = data.getString("fax");
                if (!data.wasNull()) {
                    entity.setFax(fax);
                }

                VATNumber = data.getString("VATNumber");
                if (!data.wasNull()) {
                    entity.setVATNumber(VATNumber);
                }

                java.sql.Date subscriptionDate = data.getDate("subscribtionDate");
                if (!data.wasNull()) {
                    calendar = new GregorianCalendar();
                    calendar.setTime(subscriptionDate);
                }

                customer = new Customer(entity, rank, calendar);

                customerList.add(customer);
            }

            return customerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean create(Customer c) throws SQLException {
        int affectedRowsEntity = 0;
        int affectedRowsCustomer = 0;
        int entityID = 0;
        PreparedStatement preparedStatementEntity = null;

        //Entity
        try {
            String sqlEntity = "INSERT INTO entity(mail,contactName, phoneNumber, houseNumber, street, fax, bankAccountNumber, businessNumber, VATNumber, Citylabel, CityZipCode) values(?,?,?,?,?,?,?,?,?,?,?)";
            Entity entity = c.getEntity();
            City city = c.getEntity().getCity();
            preparedStatementEntity = connection.prepareStatement(sqlEntity, Statement.RETURN_GENERATED_KEYS);
            preparedStatementEntity.setString(1, entity.getMail());
            preparedStatementEntity.setString(2, entity.getContactName());
            preparedStatementEntity.setString(3, entity.getPhoneNumber());
            preparedStatementEntity.setInt(4, entity.getHouseNumber());
            preparedStatementEntity.setString(5, entity.getStreet());
            preparedStatementEntity.setString(6, entity.getFax());
            preparedStatementEntity.setString(7, entity.getBankAccountNumber());
            preparedStatementEntity.setString(8, entity.getBusinessNumber());
            preparedStatementEntity.setString(9, entity.getVATNumber());
            preparedStatementEntity.setString(10, city.getLabel());
            preparedStatementEntity.setInt(11, city.getZipCode());
            affectedRowsEntity = preparedStatementEntity.executeUpdate();
        }catch(SQLException ex) {
                throw ex;
        }
        //Customer
        if(affectedRowsEntity != 0) {
            try (ResultSet generatedKeys = preparedStatementEntity.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    String sqlCustomer = "INSERT INTO customer(`EntityId`,`RankId`,`subscribtionDate`) values(?,?,?)";
                    PreparedStatement preparedStatementCustomer = connection.prepareStatement(sqlCustomer);
                    entityID = generatedKeys.getInt(1);
                    preparedStatementCustomer.setInt(1, entityID);
                    preparedStatementCustomer.setInt(2, c.getRank().getId());
                    preparedStatementCustomer.setDate(3, Date.valueOf(java.time.LocalDate.now()));
                    affectedRowsCustomer = preparedStatementCustomer.executeUpdate();


                } else {
                    throw new SQLException("No ID obtained from entity.");
                }
            } catch(SQLException ex){
                String sqlEntityDel = "DELETE FROM entity WHERE id = ?";
                PreparedStatement preparedStatementEntityDel = connection.prepareStatement(sqlEntityDel);
                preparedStatementEntityDel.setInt(1, entityID);
                preparedStatementEntityDel.executeUpdate();
            }
        } else {
            throw new SQLException("Creating Entity failed.");
        }
        return affectedRowsCustomer != 0;
    }
}
