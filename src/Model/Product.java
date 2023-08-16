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

    //Constructor Creation done with 'Alt + Insert' shortcut
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    //Public Getters and Setters created with 'Alt + Insert' shortcut
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    //Methods to add, delete and get retrieve associated Parts

    public void addAssociatedPart(Part part){
    }

    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        return false;
    }

    public ObservableList<Part> getAllAssociatedParts(){
        return null;
    }

}
