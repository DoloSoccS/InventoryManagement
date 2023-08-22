package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory class is used to provide all the methods and storage variables for both
 *      Part subclasses and the Product class
 */
public class Inventory {
    /**
     * allParts holds the list containing all InHouse and Outsourced objects
     * allProducts holds the list for all Product objects
     * autoPartID and autoProductID are used to keep track of the auto-generated ID values
     *      for both classes
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int autoPartID = 1000;
    private static int autoProductID = 1;

    /**
     *addPart and addProduct add the object of their respective classes to their respective lists
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
        autoPartID++;
    }

    /**
     *addPart and addProduct add the object of their respective classes to their respective lists
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
        autoProductID++;
    }

    /**
     * lookupPart and lookupProduct search the allParts or allProducts list for the given object
     *  one looks it up by String name and the other by int ID
     */
    public static Part lookupPart(int partID) {
        for (Part part : allParts) {
            if (part.getId() == partID) {
                return part;
            }
        }
        return null;
    }

    /**
     * lookupPart and lookupProduct search the allParts or allProducts list for the given object
     *  one looks it up by String name and the other by int ID
     */
    public static Product lookupProduct(int productID) {
        for (Product product : allProducts){
            if (product.getId() == productID){
                return product;
            }
        }
        return null;
    }

    /**
     * lookupPart and lookupProduct search the allParts or allProducts list for the given object
     *  one looks it up by String name and the other by int ID
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> foundItem = FXCollections.observableArrayList();
        if(!partName.isBlank()){
            for (Part part : allParts) {
                if (part.getName().toLowerCase().contains( partName.toLowerCase())) {
                    foundItem.add(part);
                }
            }
            return foundItem;
        }
        return foundItem;

    }

    /**
     * lookupPart and lookupProduct search the allParts or allProducts list for the given object
     *  one looks it up by String name and the other by int ID
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> foundItem = FXCollections.observableArrayList();
        if(!productName.isBlank())
            for (Product product : allProducts) {
                if (product.getName().toLowerCase().contains( productName.toLowerCase())) {
                    foundItem.add(product);
                }
            }
        return foundItem;
    }

    /**
     * updatePart and updateProduct modifies the object inside the list with any changes made
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);

    }

    /**
     * updatePart and updateProduct modifies the object inside the list with any changes made
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /**
     * deletePart and deleteProduct removes the given object from the list
     */
    public static boolean deletePart(Part selectedPart) {
        if (selectedPart != null) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i) == selectedPart) {
                    allParts.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * deletePart and deleteProduct removes the given object from the list
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if (selectedProduct != null) {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i) == selectedProduct) {
                    allProducts.remove(i);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * getAllParts and getAllProducts returns the list with all the objects in that list
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * getAllParts and getAllProducts returns the list with all the objects in that list
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /**
     * nextPartID and nextProductID provide the next int value for the auto-generated ID for
     *      new objects
     */
    public static int nextPartID() {
        return autoPartID;
    }

    /**
     * nextPartID and nextProductID provide the next int value for the auto-generated ID for
     *      new objects
     */
    public static int nextProductID() {
        return autoProductID;
    }
}

