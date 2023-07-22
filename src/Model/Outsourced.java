package Model;

public class Outsourced extends Part {
    //Private member

    private String companyName;

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
}
