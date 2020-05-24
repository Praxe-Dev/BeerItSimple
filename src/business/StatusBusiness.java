package business;

import dataAccess.StatusDBAccess;
import dataAccess.StatusDataAccess;
import exception.ConnectionException;
import exception.DataQueryException;
import model.Status;

import java.util.ArrayList;

public class StatusBusiness {
    private StatusDataAccess dao;

    public StatusBusiness() throws ConnectionException {
        this.dao = new StatusDBAccess();
    }


    public ArrayList<Status> getAllStatus() throws DataQueryException {
        return dao.getAllStatus();
    }
}
