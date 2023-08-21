package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//java: package javafx.collections does not exist
//check javafx version. may have to download SceneBuilder first
//SceneBuilder install did not build project
//Needed to install JavaFX .jar files to clear error
public class Product {
    //Private members

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    public String foundError;


    //Constructor Creation done with 'Alt + Insert' shortcut
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    /**
     * Blank constructor for initializing instances without values
     */
    public Product() {

    }

    /**
     * Get methods
     */
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }


    /**
     * Set methods
     */
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Method to add an associated Part to a Product object
     *
     */
    public void addAssociatedPart(Part part){
        if(part != null) {
            associatedParts.add(part);
        }
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
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

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
            System.out.println("Stock" + stock + "max" + max + "min" + min);

            return true;

        }

        return false;
    }
}


