package model;

import javax.swing.*;

public class Employee extends Entity {
    private Integer roleId;
    private String password;
    private Entity entity;

    public Employee(Integer id, String mail, String contactName, String phoneNumber, Integer houseNumber, String street, String fax, String bankAccountNumber, String VATNumber, String cityLabel, Integer cityZipCode, int roleId, String password ) {
        super(id, mail, contactName, phoneNumber, houseNumber, street, fax, bankAccountNumber, VATNumber, cityLabel, cityZipCode);
        this.roleId = roleId;
        this.password = password;
    }


}
