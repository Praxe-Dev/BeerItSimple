package controller;

import business.RankBusiness;
import exception.ConnectionException;
import exception.DataQueryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Rank;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RankController {
    private RankBusiness rankBusiness;

    public RankController() throws ConnectionException { this.rankBusiness = new RankBusiness(); }

    public ArrayList<Rank> getAllRanks() throws DataQueryException {
        return rankBusiness.getAllRanks();
    }

}
