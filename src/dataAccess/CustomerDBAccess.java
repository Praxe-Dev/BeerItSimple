package dataAccess;

import model.Customer;
import model.Entity;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDBAccess implements CustomerDataAccess{
    private Connection connection;

    public CustomerDBAccess() {
        this.connection = DBConnection.getDBConnection();
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        String sqlInstruction = "SELECT c.*, e.*\n" +
                                "FROM customer c JOIN entity e\n" +
                                "ON c.EntityId = e.id";
        ArrayList<Customer> customerList;
        try {
            ResultSet data = connection.createStatement().executeQuery(sqlInstruction);

            customerList = new ArrayList<>();
            Customer test;
            Entity entity;
            String mail;
            String fax;
            String VATNumber;

            while(data.next()) {
                entity = new Entity(
                        data.getInt("id"),
                        data.getString("contactName"),
                        data.getString("phoneNumber"),
                        data.getInt("houseNumber"),
                        data.getString("street"),
                        data.getString("CityLabel"),
                        data.getInt("CityZipCode")
                );

                test = new Customer(entity);

                mail = data.getString("mail");
                if (!data.wasNull()) {
                    test.getEntity().setMail(mail);
                }

                fax = data.getString("fax");
                if (!data.wasNull()) {
                    test.getEntity().setFax(fax);
                }

                VATNumber = data.getString("VATNumber");
                if (!data.wasNull()) {
                    test.getEntity().setVATNumber(VATNumber);
                }

                customerList.add(test);
            }

            return customerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int create(Customer c) throws SQLException {
        int status = 0;
        try {
            String sql = "INSERT INTO customer(EntityId,RankId,subscriptionDate) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, c.getEntity().getId());
            preparedStatement.setInt(2, c.getRank().getId());
            preparedStatement.setDate(3, Date.valueOf(java.time.LocalDate.now()));
            status = preparedStatement.executeUpdate();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return status;
    }
}
