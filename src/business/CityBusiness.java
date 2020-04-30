package business;

import dataAccess.CityDBAccess;
import dataAccess.CityDataAccess;
import model.City;

import java.util.ArrayList;

public class CityBusiness {

    private CityDataAccess dao;

    public CityBusiness() {
        this.dao = new CityDBAccess();
    }

    /*
    public ArrayList<String> getAllCities() {
        ArrayList<String> cityList = new ArrayList<>();

        for (City c : dao.getAllCities()) {
            cityList.add(c.toString());
        }

        return cityList;
    }
    */

    public ArrayList<City> getAllCities() {
        return dao.getAllCities();
    }
}
