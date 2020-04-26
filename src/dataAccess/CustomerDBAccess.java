package dataAccess;

import model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            String mail;
            String fax;
            String businessNumber;
            String VATNumber;

            while(data.next()) {
                test = new Customer(data.getInt("id"),
                        data.getString("contactName"),
                        data.getString("phoneNumber"),
                        data.getInt("houseNumber"),
                        data.getString("street"),
                        data.getString("businessNumber"),
                        data.getString("CityLabel"),
                        data.getInt("CityZipCode"),
                        2
                );

                mail = data.getString("mail");
                if (!data.wasNull()) {
                    test.setMail(mail);
                }

                fax = data.getString("fax");
                if (!data.wasNull()) {
                    test.setFax(fax);
                }

                VATNumber = data.getString("VATNumber");
                if (!data.wasNull()) {
                    test.setVATNumber(VATNumber);
                }

                customerList.add(test);
            }

            return customerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
