package Model;

/**
 * Provided Part class that creates standalone part Object
 */
public abstract class Part {

    /**
     * id holds an int value for the part's ID
     * name holds the String value for the part's name
     * price holds the double value for the part's price
     * stock holds the int value for the current inventory level of the part
     * min holds the int value of the minimum stock level
     * max holds the int value of the maximum stock level
     */
        private int id;
        private String name;
        private double price;
        private int stock;
        private int min;
        private int max;

    /**
     * standard constructor for declaring a Part object before assigned values
     */
    public Part(){}

    /**
     * constructor used with the super method to instantiate an InHouse or Outsourced Part object
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        }
            //Set methods to assign values

    /**
     * Part class set methods
     */
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setPrice(double price) {this.price = price;}
    public void setStock(int stock) {this.stock = stock;}
    public void setMin(int min) {this.min = min;}
    public void setMax(int max) {this.max = max;}

            //Get methods to return values

    /**
     * Part class get methods
     */
    public int getId() {return id;}
    public String getName() {return name;}
    public double getPrice() {return price;}
    public int getStock() {return stock;}
    public int getMin() {return min;}
    public int getMax() {return max;}
}