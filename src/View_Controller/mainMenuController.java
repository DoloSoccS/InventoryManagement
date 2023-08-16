package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class mainMenuController implements Initializable {

    //Is the Product pane supposed to hold a Product  TableColumn Type ?

    Stage stage;
    Parent scene;


    //fxid variables for all the javafx controls on the Main Menu
    @FXML
    private Label mainMenuLabel;

    //Search field, TableView and TableColumns for Parts
    @FXML
    private TextField partSearchMainMenu;
    @FXML
    private TableView<Part> partsMainMenuTableView;
    @FXML
    private TableColumn<Part, Integer> partIDMainMenu;
    @FXML
    private TableColumn<Part, String> partNameMainMenu;
    @FXML
    private TableColumn<Part, Integer> partsInventoryLevelMainMenu;
    @FXML
    private TableColumn<Part, Double> partsPriceMainMenu;

    //Search field, TableView and TableColumns for Products

    @FXML
    private TextField productSearchMainMenu;
    @FXML
    private TableView<Product> productsMainMenuTableView;
    @FXML
    private TableColumn<Product, Integer> productIDMainMenu;
    @FXML
    private TableColumn<Product, String> productNameMainMenu;
    @FXML
    private TableColumn<Product, Integer> productInventoryLevelMainMenu;
    @FXML
    private TableColumn<Product, Integer> productsPriceMainMenu;


    //Methods for adding, modifying and deleting Parts from the Main Menu TableView
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/addPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/modifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeletePart(ActionEvent event) throws IOException {

    }

    //Methods for adding, modifying and deleting Products from the Main Menu TableView
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/addProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/modifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) throws IOException {

    }

    //Main Menu button for exiting the program
    @FXML
    void onActionExit(ActionEvent event) throws IOException {
        System.exit(0);
    }

    //Constructor for the mainMenuController class
    public mainMenuController() {
    }

    //Initializes the class for one-time use by the main() method in InventoryManagement file
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Populates the Parts table...
        partsMainMenuTableView.setItems(Inventory.getAllParts());
        partIDMainMenu.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameMainMenu.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryLevelMainMenu.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceMainMenu.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Populates the Products table...
        productsMainMenuTableView.setItems(Inventory.getAllProducts());
        productIDMainMenu.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameMainMenu.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelMainMenu.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceMainMenu.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
