package dataAccess;

import exception.DataQueryException;
import model.Rank;

import java.util.ArrayList;

public interface RankDataAccess {
    ArrayList<Rank> getAllRanks() throws DataQueryException;
}
