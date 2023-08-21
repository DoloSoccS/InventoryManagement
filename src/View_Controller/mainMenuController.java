package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
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

    Stage stage;
    Parent scene;
    Alert mainScreenAlert;


    //fxid variables for all the javafx controls on the Main Menu
    @FXML private Label mainMenuLabel;

    //Search field, TableView and TableColumns for Parts
    @FXML private TextField partSearchMainMenu;
    @FXML private TableView<Part> partsMainMenuTableView;
    @FXML private TableColumn<Part, Integer> partIDMainMenu;
    @FXML private TableColumn<Part, String> partNameMainMenu;
    @FXML private TableColumn<Part, Integer> partsInventoryLevelMainMenu;
    @FXML private TableColumn<Part, Double> partsPriceMainMenu;

    //Search field, TableView and TableColumns for Products

    @FXML private TextField productSearchMainMenu;
    @FXML private TableView<Product> productsMainMenuTableView;
    @FXML private TableColumn<Product, Integer> productIDMainMenu;
    @FXML private TableColumn<Product, String> productNameMainMenu;
    @FXML private TableColumn<Product, Integer> productInventoryLevelMainMenu;
    @FXML private TableColumn<Product, Integer> productsPriceMainMenu;


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
        Part selectedPart = partsMainMenuTableView.getSelectionModel().getSelectedItem();
        int index = partsMainMenuTableView.getSelectionModel().getSelectedIndex();
        if (selectedPart == null) {
            mainScreenAlert = new Alert(Alert.AlertType.ERROR);
            mainScreenAlert.setTitle("Unable to Modify !");
            mainScreenAlert.setContentText("No Part Selected.");
            mainScreenAlert.show();
        } else {
            mainScreenAlert = new Alert(Alert.AlertType.CONFIRMATION);
            mainScreenAlert.setTitle("Modifying Part.");
            mainScreenAlert.setContentText("Are you sure you want to modify this part ?");
            Optional<ButtonType> option = mainScreenAlert.showAndWait();
            if (option.isPresent() && option.get() == ButtonType.OK) {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View_Controller/modifyPart.fxml"));
                loader.load();
                modifyPartController controller = loader.getController();
                controller.sendPart(index,selectedPart);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
    }

    @FXML
    void onActionDeletePart(ActionEvent event) throws IOException {

        Part selectedPart = partsMainMenuTableView.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            mainScreenAlert = new Alert(Alert.AlertType.ERROR);
            mainScreenAlert.setTitle("Unable to Delete !");
            mainScreenAlert.setContentText("No Part Selected.");
            mainScreenAlert.show();
        } else {
            mainScreenAlert = new Alert(Alert.AlertType.CONFIRMATION);
            mainScreenAlert.setTitle("Deleting Part.");
            mainScreenAlert.setContentText("Are you sure you want to delete this part ?");
            Optional<ButtonType> option = mainScreenAlert.showAndWait();
            if(option.isPresent() && option.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
                //This refreshes the main menu to its default state
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
                partsMainMenuTableView.setItems(Inventory.getAllParts());
                System.out.println(Inventory.getAllParts());
            }

        }
    }

    //method for searching for parts
    @FXML
    void onActionPartSearch(ActionEvent event) throws IOException {

        if(!partSearchMainMenu.getText().trim().isEmpty()){
            try{
                int searchId = Integer.parseInt(partSearchMainMenu.getText());
                Part found = Inventory.lookupPart(searchId);
                if(found != null){
                    partsMainMenuTableView.getSelectionModel().select(found);
                    partSearchMainMenu.setText("");
                } else {
                    partSearchMainMenu.setText("");
                    mainScreenAlert = new Alert(Alert.AlertType.ERROR);
                    mainScreenAlert.setTitle("Search");
                    mainScreenAlert.setContentText("No ID Found.");
                    mainScreenAlert.showAndWait();
                    partsMainMenuTableView.setItems(Inventory.getAllParts());
                }

            } catch(NumberFormatException nfe){
                String searchName = partSearchMainMenu.getText();
                ObservableList<Part> found;
                found = Inventory.lookupPart(searchName);
                if(!found.isEmpty()){
                    partsMainMenuTableView.setItems(found);
                    partSearchMainMenu.setText("");
                } else {
                    partSearchMainMenu.setText("");
                    mainScreenAlert = new Alert(Alert.AlertType.ERROR);
                    mainScreenAlert.setTitle("Search");
                    mainScreenAlert.setContentText("No Name Found.");
                    mainScreenAlert.showAndWait();
                    partsMainMenuTableView.setItems(Inventory.getAllParts());
                    System.out.println(nfe.getCause());
                }
            }
        } else {
            partSearchMainMenu.setText("");
            mainScreenAlert = new Alert(Alert.AlertType.ERROR);
            mainScreenAlert.setTitle("Search");
            mainScreenAlert.setContentText("No Part Entered.");
            mainScreenAlert.showAndWait();
            partsMainMenuTableView.setItems(Inventory.getAllParts());
        }



    }

    @FXML
    void onActionProductSearch(ActionEvent event) throws IOException {
        if(!productSearchMainMenu.getText().trim().isEmpty()){
            try{
                int searchId = Integer.parseInt(productSearchMainMenu.getText());
                Product found;
                found = Inventory.lookupProduct(searchId);
                if(found != null){
                    productsMainMenuTableView.getSelectionModel().select(found);
                    productSearchMainMenu.setText("");
                } else {

                    productSearchMainMenu.setText("");
                    mainScreenAlert = new Alert(Alert.AlertType.ERROR);
                    mainScreenAlert.setTitle("Search");
                    mainScreenAlert.setContentText("No Product Found.");
                    mainScreenAlert.showAndWait();
                    productsMainMenuTableView.setItems(Inventory.getAllProducts());
                }

            } catch(NumberFormatException nfe){
                String searchName = productSearchMainMenu.getText();
                ObservableList<Product> found;
                found = Inventory.lookupProduct(searchName);
                if(!found.isEmpty()){
                    productsMainMenuTableView.setItems(found);
                    productSearchMainMenu.setText("");
                } else {
                    productSearchMainMenu.setText("");
                    mainScreenAlert = new Alert(Alert.AlertType.ERROR);
                    mainScreenAlert.setTitle("Search");
                    mainScreenAlert.setContentText("No Product Found.");
                    mainScreenAlert.showAndWait();
                    productsMainMenuTableView.setItems(Inventory.getAllProducts());
                }

            }
        } else {

            mainScreenAlert = new Alert(Alert.AlertType.ERROR);
            mainScreenAlert.setTitle("Search");
            mainScreenAlert.setContentText("No Product Entered.");
            mainScreenAlert.showAndWait();
            productsMainMenuTableView.setItems(Inventory.getAllProducts());
            productSearchMainMenu.setText("");
        }

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

        Product selectedProduct = productsMainMenuTableView.getSelectionModel().getSelectedItem();
        int index = productsMainMenuTableView.getSelectionModel().getSelectedIndex();
        if (selectedProduct == null) {
            mainScreenAlert = new Alert(Alert.AlertType.ERROR);
            mainScreenAlert.setTitle("Unable to Modify !");
            mainScreenAlert.setContentText("No Product Selected.");
            mainScreenAlert.show();
        } else {
            mainScreenAlert = new Alert(Alert.AlertType.CONFIRMATION);
            mainScreenAlert.setTitle("Modifying Product.");
            mainScreenAlert.setContentText("Are you sure you want to modify this product ?");
            Optional<ButtonType> option = mainScreenAlert.showAndWait();
            if (option.isPresent() && option.get() == ButtonType.OK) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View_Controller/modifyProduct.fxml"));
                loader.load();
                modifyProductController controller = loader.getController();
                controller.sendProduct(index, selectedProduct);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) throws IOException {

            Product selectedProduct = productsMainMenuTableView.getSelectionModel().getSelectedItem();
            if(selectedProduct == null || !selectedProduct.getAllAssociatedParts().isEmpty()){
                mainScreenAlert = new Alert(Alert.AlertType.ERROR);
                mainScreenAlert.setTitle("Unable to Delete !");
                mainScreenAlert.setContentText("Either No Product Selected or Selected Product has Associated Parts.");
                mainScreenAlert.show();
            }
            else{
                mainScreenAlert = new Alert(Alert.AlertType.CONFIRMATION);
                mainScreenAlert.setTitle("Deleting Product.");
                mainScreenAlert.setContentText("Are you sure you want to delete this product ?");
                Optional<ButtonType> option = mainScreenAlert.showAndWait();
                if(option.isPresent() && option.get() == ButtonType.OK){
                    Inventory.deleteProduct(selectedProduct);
                    //this only deletes the product but keeps the current state of the main menu
                    productsMainMenuTableView.setItems(Inventory.getAllProducts());
                    System.out.println(Inventory.getAllProducts());
                }
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

    //Initializes the class for one-time use by the main method in InventoryManagement file
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
