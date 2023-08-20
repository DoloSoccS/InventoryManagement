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

        Product p1 = new Product(1, "BF Goodrich AT", 169.99, 4, 1, 10);
        p1.addAssociatedPart(sheer);
        p1.addAssociatedPart(two);

        Product p2 = new Product(2, "Toolkit", 40.99, 8, 1, 10);
        p2.addAssociatedPart(one);
        p2.addAssociatedPart(wrench);
        p2.deleteAssociatedPart(bolt);

        Product p3 = new Product(3, "2 Ton Jack", 109.99, 3, 1, 4);
        p3.addAssociatedPart(three);
        p3.addAssociatedPart(bolt);
        p3.addAssociatedPart(bolt);

        Inventory.addProduct(p1);
        Inventory.addProduct(p2);
        Inventory.addProduct(p3);
    }

}