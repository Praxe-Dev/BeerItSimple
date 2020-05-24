package dataAccess;

import exception.ConnectionException;
import exception.DataQueryException;
import model.City;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CityDBAccess implements CityDataAccess {
    private Connection connection;

    public CityDBAccess() throws ConnectionException {
        this.connection = DBConnection.getInstance();
    }


    @Override
    public ArrayList<City> getAllCities() throws DataQueryException {
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
            throw new DataQueryException();
        }

        return cityList;
    }
}
