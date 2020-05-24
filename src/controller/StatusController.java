package controller;

import business.StatusBusiness;
import exception.ConnectionException;
import exception.DataQueryException;
import model.Status;

import java.util.ArrayList;

public class StatusController {
    private StatusBusiness statusBusiness;

    public StatusController() throws ConnectionException {
        this.statusBusiness = new StatusBusiness();
    }

    public ArrayList<Status> getAllStatus() throws DataQueryException {
        return statusBusiness.getAllStatus();
    }
}
