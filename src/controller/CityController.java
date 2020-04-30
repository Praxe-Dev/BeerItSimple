package controller;

import business.CityBusiness;
import business.RankBusiness;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.City;

import java.util.ArrayList;

public class CityController {
    private CityBusiness cityBusiness;

    public CityController(){ this.cityBusiness = new CityBusiness(); }

    //Changement comme indiqué lors de la réunion Teams
    /*
    public ObservableList<String> getAllCities() {
        return FXCollections.observableList(cityBusiness.getAllCities());
    }
     */

    public ArrayList<City> getAllCities() {
        return cityBusiness.getAllCities();
    }
}
