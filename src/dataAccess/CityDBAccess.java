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
                city = new City(data.getString("Label"), data.getInt("zipCode"));
                cityList.add(city);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cityList;
    }
}
