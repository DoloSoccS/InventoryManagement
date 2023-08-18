import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;


public class InventoryManagement extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        addTestData();

        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

            launch(args);
        }

   void addTestData() {
        //Add InHouse Parts
        Part a1 = new InHouse(1, "Part A1", 2.99, 10, 5, 100, 101);
        Part a2 = new InHouse(3, "Part A2", 4.99, 11, 5, 100, 103);
        Part b = new InHouse(2, "Part B", 3.99, 9, 5, 100, 102);
        Inventory.addPart(a1);
        Inventory.addPart(b);
        Inventory.addPart(a2);
        //Add OutSourced Parts
        Part o1 = new Outsourced(6, "Part 01", 2.99, 10, 5, 100, "ACME Co.");
        Part p = new Outsourced(7, "Part P", 3.99, 9, 5, 100, "ACME Co.");
        Part q = new Outsourced(8, "Part Q", 2.99, 10, 5, 100, "FLORIDA Co.");
        Inventory.addPart(o1);
        Inventory.addPart(p);
        Inventory.addPart(q);

        //Add Product

        Product p1 = new Product(1, "BF Goodrich AT", 169.99, 4, 1, 10);
        p1.addAssociatedPart(o1);
        p1.addAssociatedPart(p);

        Product p2 = new Product(2, "Picnic Basket", 4.99, 8, 1, 10);
        p2.addAssociatedPart(p);
        p2.addAssociatedPart(q);
        p2.deleteAssociatedPart(q);

        Product p3 = new Product(3, "Soft Drink", 0.99, 3, 1, 4);
        p3.addAssociatedPart(b);
        p3.addAssociatedPart(a2);
        p3.addAssociatedPart(o1);

        Inventory.addProduct(p1);
        Inventory.addProduct(p2);
        Inventory.addProduct(p3);
    }

}