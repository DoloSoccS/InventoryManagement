package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts;

    public static void addPart(Part newPart) {

    }

    public static void addProduct(Product newProduct) {

    }

    public static Part lookupPart(int partID) {
        return null;
    }

    public static Product lookupProduct(int productID) {
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        return null;
    }

    public static void updatePart(int index, Part selectedPart) {

    }

    public static void updateProduct(int index, Product selectedProduct) {

    }

    public static boolean deletePart(Part selectedPart) {
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        return false;
    }

    public static ObservableList<Part> getAllParts(){
        return null;
    }

    public static ObservableList<Product> getAllProducts(){
        return null;
    }
}