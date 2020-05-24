package business;

import dataAccess.RankDBAccess;
import dataAccess.RankDataAccess;
import exception.ConnectionException;
import exception.DataQueryException;
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

    public ArrayList<Rank> getAllRanks() throws DataQueryException {
        return dao.getAllRanks();
    }
}
