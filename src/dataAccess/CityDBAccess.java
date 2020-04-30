package dataAccess;

import model.City;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CityDBAccess implements CityDataAccess {
    private Connection connection;

    public CityDBAccess() {
        this.connection = DBConnection.getDBConnection();
    }


    @Override
    public ArrayList<City> getAllCities() {
        String sqlInstruction = "SELECT * FROM City";

        ArrayList<City> cityList = new ArrayList<>();

        try {
            ResultSet data = connection.createStatement().executeQuery(sqlInstruction);

            City city;

            while (data.next()) {
                //System.out.println("Label : " + data.getString("Label"));
                //System.out.println("zipCode : " + data.getInt("zipCode"));

                city = new City(data.getString("Label"), data.getInt("zipCode"));
                cityList.add(city);
            }

            return cityList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
