package dataAccess;

import exception.DataQueryException;
import model.Status;

import java.util.ArrayList;

public interface StatusDataAccess {
    ArrayList<Status> getAllStatus() throws DataQueryException;
}
