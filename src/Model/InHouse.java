package Model;

public class InHouse extends Part {
    //Private class member
    private int machineId;
    public String foundError;

    //Constructor


    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    public InHouse(){}

    //Public methods


    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public boolean notValid(String name, Double price, Integer stock, Integer min, Integer max) {
        /**
         * first portion checks to make sure a name was input
         */
        if(name.isBlank()){
            foundError = "No name input";
            return true;
        }

        /**
         * checks to make sure inventory, max and min levels are correct
         */
        if (!(stock >= min && stock <= max)) {
            foundError = "Inventory Level must >= minimum and <= maximum.";
            return true;
        }


        //return line
        return false;
    }
}
