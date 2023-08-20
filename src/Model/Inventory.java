package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int autoPartID = 1000;
    private static int autoProductID = 1;
    public Alert PartScreenAlert;

    public static void addPart(Part newPart) {
        allParts.add(newPart);
        autoPartID++;
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
        autoProductID++;
    }

    public static Part lookupPart(int partID) {
        for (Part part : allParts) {
            if (part.getId() == partID) {
                return part;
            }
        }
        return null;
    }

    public static Product lookupProduct(int productID) {
        for (Product product : allProducts){
            if (product.getId() == productID){
                return product;
            }
        }
        return null;
    }

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

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);

    }

    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

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


    public static ObservableList<Part> getAllParts(){
        System.out.println(allParts);
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        System.out.println(allProducts);
        return allProducts;
    }

    public static int nextPartID() {
        return autoPartID;
    }

    public static int nextProductID() {
        return autoProductID;
    }
}

