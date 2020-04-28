package business;

import dataAccess.RankDBAccess;
import dataAccess.RankDataAccess;
import model.Rank;
import java.util.ArrayList;

public class RankBusiness {
    private RankDataAccess dao;

    public RankBusiness() {
        setDao(new RankDBAccess());
    }

    public void setDao(RankDataAccess dao) {
        this.dao = dao;
    }

    public ArrayList<String> getAllRanks() {
        ArrayList<String> arrayListTransformed = new ArrayList<>();
        for (Rank rank: dao.getAllRanks()) {
            arrayListTransformed.add("(" + rank.getId() + ") " + rank.getLabel() + " (" + rank.getCreditLimit() + "€)");
        }
        
        return arrayListTransformed;
    }
}
