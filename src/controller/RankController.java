package controller;

import business.RankBusiness;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Rank;

import java.util.ArrayList;

public class RankController {
    private RankBusiness rankBusiness;

    public RankController(){ this.rankBusiness = new RankBusiness(); }

    public ObservableList getAllRanks(){
        ArrayList<String> rankList = rankBusiness.getAllRanks();
        return FXCollections.observableList(rankList);
    }

}
