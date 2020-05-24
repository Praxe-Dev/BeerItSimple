package business;

import dataAccess.RankDBAccess;
import dataAccess.RankDataAccess;
import exception.ConnectionException;
import model.Rank;
import java.util.ArrayList;

public class RankBusiness {
    private RankDataAccess dao;

    public RankBusiness() throws ConnectionException {
        setDao(new RankDBAccess());
    }

    public void setDao(RankDataAccess dao) {
        this.dao = dao;
    }

    public ArrayList<Rank> getAllRanks() {
        return dao.getAllRanks();
    }
}
