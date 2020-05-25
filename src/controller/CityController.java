package controller;

import business.CityBusiness;
import business.RankBusiness;
import exception.ConnectionException;
import exception.DataQueryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.City;

import java.util.ArrayList;

public class CityController {
    private CityBusiness cityBusiness;

    public CityController() throws ConnectionException { this.cityBusiness = new CityBusiness(); }

    public ArrayList<City> getAllCities() throws DataQueryException {
        return cityBusiness.getAllCities();
    }
}
