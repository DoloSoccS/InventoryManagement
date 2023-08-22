package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Product class that is used to create Product objects that may or may not contain Part objects
 *
 */
public class Product {
    //Private members

    /**
     * associatedParts holds the Product objects associated part objects
     * id holds an int value for the product's ID
     * name holds the String value for the product's name
     * price holds the double value for the product's price
     * stock holds the int value for the current inventory level of the part
     * min holds the int value of the minimum stock level
     * max holds the int value of the maximum stock level
     * foundError is used to store the String value of any validity errors
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    public String foundError;


    /**
     * standard constructor to instantiate a Product object
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    /**
     * Blank constructor for instantiating instances without values
     */
    public Product() {

    }

    /**
     * Get methods
     */
    public int getId() {return id;}
    public String getName() {return name;}
    public double getPrice() {return price;}
    public int getStock() {return stock;}
    public int getMin() {return min;}
    public int getMax() {return max;}


    /**
     * Set methods
     */
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setPrice(double price) {this.price = price;}
    public void setStock(int stock) {this.stock = stock;}
    public void setMin(int min) {this.min = min;}
    public void setMax(int max) {this.max = max;}

    /**
     * Method to add an associated Part to a Product object
     *
     */
    public void addAssociatedPart(Part part){
        if(part != null) {associatedParts.add(part);}
    }

    /**
     * Method to delete an associated Part of a Product object
     *
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        if (selectedAssociatedPart != null) {
            for (int i = 0; i < associatedParts.size(); i++) {
                if (associatedParts.get(i) == selectedAssociatedPart) {
                    associatedParts.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to return all the associated Parts of the Product object
     *
     */
    public ObservableList<Part> getAllAssociatedParts(){return associatedParts;}

    /**
     * first portion checks to make sure a name was input for Part and Company
     * then checks to make sure price is not negative and at least zero
     * finally checks to make sure inventory, max and min levels are correct
     */
    public boolean notValid(String name, Double price, Integer stock, Integer min, Integer max) {

        if(name.isBlank()){
            foundError = "Name required for Product";
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


