package Main;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;

/**
 * Main class of application
 *
 * FUTURE ENHANCEMENTS
 * Side-by-Side view of Add and Modify functions to allow convenience in creating similar
 * objects
 */
public class InventoryManagement extends Application {

    /**
     * start method executes java application
     */
    @Override
    public void start(Stage stage) throws Exception {

        addTestData();

        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * main method as necessary for all java programs
     * includes launch method to run javafx application with given parameters(files)
     */
    public static void main(String[] args) {
            launch(args);
        }

    /**
     * sample data to instantiate multiple Part and Product objects
     * also includes methods to add those parts to the respective Inventory lists
     */
    void addTestData() {

        //Add InHouse Parts
        Part wrench = new InHouse(1, "Wrench", 2.99, 10, 5, 100, 101);
        Part bolt = new InHouse(3, "Bolt", 4.99, 11, 5, 100, 103);
        Part sheer = new InHouse(2, "Sheer", 3.99, 9, 5, 100, 102);
        Inventory.addPart(wrench);
        Inventory.addPart(bolt);
        Inventory.addPart(sheer);

        //Add OutSourced Parts
        Part one = new Outsourced(6, "Part 1", 7.99, 10, 5, 100, "Rimac");
        Part two = new Outsourced(7, "Part 2", 3.99, 9, 5, 100, "Mopar");
        Part three = new Outsourced(8, "Part 3", 52.99, 10, 5, 100, "Hennessey Performance");
        Inventory.addPart(one);
        Inventory.addPart(two);
        Inventory.addPart(three);

        //Add Product
        Product firstProduct = new Product(1, "Air Compressor", 169.99, 4, 1, 10);
        firstProduct.addAssociatedPart(sheer);
        firstProduct.addAssociatedPart(two);

        Product secondProduct = new Product(2, "Toolkit", 40.99, 8, 1, 10);
        secondProduct.addAssociatedPart(one);
        secondProduct.addAssociatedPart(wrench);
        secondProduct.deleteAssociatedPart(bolt);

        Product thirdProduct = new Product(3, "3 Ton Jack", 109.99, 3, 1, 4);
        thirdProduct.addAssociatedPart(three);
        thirdProduct.addAssociatedPart(bolt);
        thirdProduct.addAssociatedPart(bolt);

        Inventory.addProduct(firstProduct);
        Inventory.addProduct(secondProduct);
        Inventory.addProduct(thirdProduct);
    }

}