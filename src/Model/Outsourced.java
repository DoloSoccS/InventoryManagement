package Model;

/**
 * Outsourced class inherits from the Part class
 */
public class Outsourced extends Part {
    //Private member

    /**
     * companyName holds the String value for the company Part is sourced from
     * foundError is used to store the error message for anything that is wrong
     *          with the notValid method
     */
    private String companyName;
    public String foundError;

    //Constructors

    /**
     * standard constructor for instantiating the class with only the companyName provided
     */
    public Outsourced(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Outsourced class constructor. Uses the super method to call the Part class' constructor.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    //Public methods

    /**
     * class' get and set methods
     */
    public String getCompanyName() {return companyName;}
    public void setCompanyName(String companyName) {this.companyName = companyName;}

    /**
     * first portion checks to make sure a name was input for Part and Company
     * then checks to make sure price is not negative and at least zero
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
