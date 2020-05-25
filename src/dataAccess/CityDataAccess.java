package dataAccess;

import exception.DataQueryException;
import model.City;

import java.util.ArrayList;

public interface CityDataAccess {
    ArrayList<City> getAllCities() throws DataQueryException;
}
