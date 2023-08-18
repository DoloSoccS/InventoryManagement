package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.ObservableList;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class mainMenuController implements Initializable {

    //Is the Product pane supposed to hold a Product  TableColumn Type ?

    Stage stage;
    Parent scene;
    Alert mainScreenAlert;


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
        Part selectedPart = partsMainMenuTableView.getSelectionModel().getSelectedItem();
        Inventory.deletePart(selectedPart);
        partsMainMenuTableView.setItems(Inventory.getAllParts());
    }

    //method for searching for parts
    @FXML
    ObservableList<Part> onActionPartSearch(ActionEvent event) throws IOException {
        //try catch
        int id = Integer.parseInt(partSearchMainMenu.getText());
        if(!partSearchMainMenu.getText().isBlank())
        {
        for(Part part : Inventory.getAllParts()) {
            if (part.getId() == id)
                Inventory.partSearchedByID.add(part);
                partsMainMenuTableView.setItems(Inventory.partSearchedByID());
                return Inventory.partSearchedByID();
        }

        }
        partsMainMenuTableView.setItems(Inventory.getAllParts());
        return Inventory.getAllParts();
    }

    @FXML
    ObservableList<Product> onActionProductSearch(ActionEvent event) throws IOException {
        String name = productSearchMainMenu.getText();
        for(Product product : Inventory.getAllProducts()) {
            if (product.getName() == name)
                Inventory.productSearchedByName.add(product);
            return Inventory.productSearchedByName;

        }
        return Inventory.getAllProducts();
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
        try{
            Product selectedProduct = productsMainMenuTableView.getSelectionModel().getSelectedItem();
            if(selectedProduct == null) {
                mainScreenAlert = new Alert(Alert.AlertType.ERROR);
                mainScreenAlert.setTitle("Unable to Delete !");
                mainScreenAlert.setContentText("Part not Selected.");
                mainScreenAlert.show();
            }
            else {
                mainScreenAlert = new Alert(Alert.AlertType.CONFIRMATION);
                mainScreenAlert.setTitle("Deleting Product.");
                mainScreenAlert.setContentText("Are you sure you want to delete this product ?");
                Optional<ButtonType> option = mainScreenAlert.showAndWait();
                if(option.isPresent() && option.get() == ButtonType.OK){
                    Inventory.deleteProduct(selectedProduct);
                    productsMainMenuTableView.setItems(Inventory.getAllProducts());
                }
            }
        }
        catch (Exception e) {
            mainScreenAlert = new Alert(Alert.AlertType.ERROR);
            mainScreenAlert.setTitle("Unable to Delete !");
            mainScreenAlert.setContentText("Either No Product Selected or Selected Product has Associated Parts.");
            mainScreenAlert.show();
        }
    }

    //Main Menu button for exiting the program
    @FXML
    void onActionExit(ActionEvent event) throws IOException {
        mainScreenAlert = new Alert(Alert.AlertType.CONFIRMATION);
        mainScreenAlert.setTitle("Exiting Program.");
        mainScreenAlert.setContentText("Are you sure you want to close the program ?");
        Optional<ButtonType> option = mainScreenAlert.showAndWait();
        if(option.isPresent() && option.get() == ButtonType.OK){
        System.exit(0);
        }
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
