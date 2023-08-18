package Model;

public class InHouse extends Part {
    //Private class member
    private int machineId;

    //Constructor
    public InHouse(int machineId) {
        this.machineId = machineId;
    }

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
}
