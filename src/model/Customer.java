package model;

public class Customer extends Entity {
    private Integer rankId;
    private String date;

    public Customer(Integer id, String mail, String contactName, String phoneNumber, Integer houseNumber, String street, String fax, String bankAccountNumber, String VATNumber, String cityLabel, Integer cityZipCode, Integer rankId) {
        super(id, mail, contactName, phoneNumber, houseNumber, street, fax, bankAccountNumber, VATNumber, cityLabel, cityZipCode);
        setRankId(rankId);
    }

    public Customer(Integer id, String contactName, String phoneNumber, Integer houseNumber, String street, String bankAccountNumber, String cityLabel, Integer cityZipCode, Integer rankId) {
        super(id, null, contactName, phoneNumber, houseNumber, street, null, bankAccountNumber, null, cityLabel, cityZipCode);
        setRankId(rankId);
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "rankId=" + rankId +
                ", date='" + date + '\'' +
                '}';
    }
}
