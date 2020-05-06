package controller;

import business.StatusBusiness;
import model.Status;

import java.util.ArrayList;

public class StatusController {
    private StatusBusiness statusBusiness;

    public StatusController() {
        this.statusBusiness = new StatusBusiness();
    }

    public ArrayList<Status> getAllStatus() {
        return statusBusiness.getAllStatus();
    }
}
