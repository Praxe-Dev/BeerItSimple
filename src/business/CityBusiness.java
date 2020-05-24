package business;

import dataAccess.CityDBAccess;
import dataAccess.CityDataAccess;
import exception.ConnectionException;
import model.City;

import java.util.ArrayList;

public class CityBusiness {

    private CityDataAccess dao;

    public CityBusiness() throws ConnectionException {
        this.dao = new CityDBAccess();
    }

    public ArrayList<City> getAllCities() {
        return dao.getAllCities();
    }
}
