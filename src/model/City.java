package model;

public class City {
    String label;
    Integer zipCode;

    public City(String label, int zipCode) {
        setLabel(label);
        setZipCode(zipCode);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        if (zipCode < 1000 || zipCode > 9999) {
            // TODO: throw error ZipCode
            System.out.println("Erreur - zipCode non valide");
        } else {
            this.zipCode = zipCode;
        }
    }

    @Override
    public String toString() {
        return zipCode + " " + label;
    }
}
