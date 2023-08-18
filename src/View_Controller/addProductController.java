package View_Controller;

import Model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addProductController implements Initializable {

    //Variables to reference window
    Stage stage;
    Parent scene;

    //fxid variables for all the TextFields in the Modify Product window
    @FXML
    private TextField productIDField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productInventoryField;
    @FXML
    private TextField productPriceField;
    @FXML
    private TextField maxStock;
    @FXML
    private TextField minStock;

    //Part Search, add TableView and TableColumn variables
    @FXML
    private TextField searchPartField;
    @FXML
    private TableView<Part> addPartTableView;
    @FXML
    private TableColumn<Part, Integer> addPartID;
    @FXML
    private TableColumn<Part, String> addPartName;
    @FXML
    private TableColumn<Part, Integer> addInventoryLevel;
    @FXML
    private TableColumn<Part, Double> addPrice;

    //Remove Part TableView and TableColumn variables
    @FXML
    private TableView<Part> removePartTableView;
    @FXML
    private TableColumn<Part, Integer> removePartID;
    @FXML
    private TableColumn<Part, String> removePartName;
    @FXML
    private TableColumn<Part, Integer> removeInventoryLevel;
    @FXML
    private TableColumn<Part, Double> removePrice;


    @FXML
    void onActionAddAssociatedPart(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {

    }

    @FXML
    ObservableList<Part> onActionSearchPartField(ActionEvent event) {
        return Inventory.getAllParts();

    }


    //Saves Product and returns to Main Menu if inputs are compatible
    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {

        //auto increment added to save method to prevent incrementing without use

        int id = Inventory.nextProductID();
        String name = productNameField.getText();
        int stock = Integer.parseInt(productInventoryField.getText());
        double price = Double.parseDouble(productPriceField.getText());
        int max = Integer.parseInt(maxStock.getText());
        int min = Integer.parseInt(minStock.getText());
        Inventory.addProduct(new Product(id, name, price, stock, min, max));

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Populates the Parts table...
        addPartTableView.setItems(Inventory.getAllParts());
        addPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        addPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        addInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Populates Associated Parts table...

        /* removePartTableView.setItems(Product.getAllAssociatedParts());
        * cannot be used because the .getAllAssociatedParts() method is not static.
        * A class must be instantiated first.
        */

        removePartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        removePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        removeInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        removePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public addProductController(){

    }
}
