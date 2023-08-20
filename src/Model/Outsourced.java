package Model;

public class Outsourced extends Part {
    //Private member

    private String companyName;
    public String foundError;

    //Constructors

    public Outsourced(String companyName) {
        this.companyName = companyName;
    }

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    //Public methods


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    /**
     * first portion checks to make sure a name was input for Part and Company
     * then checks to make sure price is not negative and greater than zero
     * finally checks to make sure inventory, max and min levels are correct
     */
    public boolean notValid(String name, String companyName, Double price, Integer stock, Integer min, Integer max) {

        if(name.isBlank() || companyName.isBlank()){
            foundError = "Name required for Part and Company";
            return true;
        }

        if (price < 0) {
            foundError = "Price cannot be negative";
            return true;
        }

        if (!(stock >= min && stock <= max)) {
            foundError = "Inventory Level must be greater than or equal to minimum and less than or equal to maximum.";
            return true;
        }

        return false;
    }
}
