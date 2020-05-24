package business;

import dataAccess.StatusDBAccess;
import dataAccess.StatusDataAccess;
import exception.ConnectionException;
import model.Status;

import java.util.ArrayList;

public class StatusBusiness {
    private StatusDataAccess dao;

    public StatusBusiness() throws ConnectionException {
        this.dao = new StatusDBAccess();
    }


    public ArrayList<Status> getAllStatus() {
        return dao.getAllStatus();
    }
}
