package Model;

/**
 * InHouse class inherits from the Part class
 */
public class InHouse extends Part {
    //Private class member
    /**
     * machineId is the value for the InHouse class specifically
     * foundError is used to store the error message for anything that is wrong
     *          with the notValid method
     */
    private int machineId;
    public String foundError;

    //Constructors

    /**
     * InHouse class constructor. Uses the super method to call the Part class' constructor.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * standard blank constructor to instantiate the class without values
     */
    public InHouse(){}


    /**
     * get and set method for machineId value
     */
    public int getMachineId() {return machineId;}
    public void setMachineId(int machineId) {this.machineId = machineId;}

    /**
     * this method is used to validate the inputs for the class
     */
    public boolean notValid(String name, Double price, Integer stock, Integer min, Integer max) {
        /**
         * first portion checks to make sure a name was provided
         */
        if(name.isBlank()){
            foundError = "No name input";
            return true;
        }

        /**
         * checks to see if price is 0 or greater
         */
        if (price < 0) {
            foundError = "Price cannot be negative";
            return true;
        }

        /**
         * this portion checks to make sure inventory, max and min levels are correct
         */
        if (!(stock >= min && stock <= max)) {
            foundError = "Inventory Level must >= minimum and <= maximum.";
            return true;
        }

        return false;
    }
}
