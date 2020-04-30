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

    public ArrayList<Rank> getAllRanks() {
        return dao.getAllRanks();
    }
}
