package View_Controller;

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
        try{
            Part selectedPart = partsMainMenuTableView.getSelectionModel().getSelectedItem();
            int selectedIndex = Inventory.getAllParts().indexOf(selectedPart);
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModifyPart.fxml"));
            View_Controller.modifyPartController controller = new View_Controller.modifyPartController(selectedIndex, selectedPart);
            Parent root = loader.load();
            stage.setTitle("Modify Part");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(IOException e) {
            mainScreenAlert = new Alert(Alert.AlertType.ERROR);
            mainScreenAlert.setContentText("Please select a valid Part");
            mainScreenAlert.setTitle("Part Selection Error");
            mainScreenAlert.show();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e);
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
            }

        }
    }

    //method for searching for parts
    @FXML
    void onActionPartSearch(ActionEvent event) throws IOException {
        //try int catch String
        try {
            ObservableList<Part> searched = FXCollections.observableArrayList();
            int id = Integer.parseInt(partSearchMainMenu.getText());
            searched.add(Inventory.lookupPart(id));
            if(!searched.isEmpty()) {
                partsMainMenuTableView.setItems(searched);
            }

        }catch(Exception e){

            ObservableList<Part> searched = FXCollections.observableArrayList();
            String name = partSearchMainMenu.getText();
            searched = Inventory.lookupPart(name);

            if(!(searched == null) || !(searched.isEmpty()) || !(name.length() == 0)){
                partsMainMenuTableView.setItems(searched);
            }
            else{
                mainScreenAlert = new Alert(Alert.AlertType.ERROR);
                mainScreenAlert.setTitle("Search");
                mainScreenAlert.setContentText("No Part Found or Entered.");
                mainScreenAlert.showAndWait();
                partsMainMenuTableView.setItems(Inventory.getAllParts());
            }

        }

    }

    @FXML
    void onActionProductSearch(ActionEvent event) throws IOException {
        /*currently throwing errors with observable list. Not sure why it is doing that.
         the exception is a NumberFormatException because there is an empty string being entered.
         Still haven't been able to do a combined int and String search yet.
         Issue is identical between Part search and Product search.
         */
        try{
            ObservableList<Product> searched = FXCollections.observableArrayList();
            int id = Integer.parseInt(productSearchMainMenu.getText());
            searched.add(Inventory.lookupProduct(id));
            if(!searched.isEmpty()){
                productsMainMenuTableView.setItems(searched);
            }
        } catch (Exception e) {
            mainScreenAlert = new Alert(Alert.AlertType.ERROR);
            mainScreenAlert.setTitle("Search");
            mainScreenAlert.setContentText("No Product Input for Search.");
            mainScreenAlert.showAndWait();
            productsMainMenuTableView.setItems(Inventory.getAllProducts());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getCause());
            e.printStackTrace();

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
//       try {
            Product selectedProduct = productsMainMenuTableView.getSelectionModel().getSelectedItem();
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

                    int index = Inventory.getAllProducts().indexOf(selectedProduct);



                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/View_Controller/modifyProduct.fxml"));
                    loader.load();
                    modifyProductController controller = loader.getController();
                    controller.sendProduct(selectedProduct);


                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    Parent scene = loader.getRoot();
//                    stage.setTitle("Modify Product");
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }
//        }catch(Exception e){
//           mainScreenAlert = new Alert(Alert.AlertType.ERROR);
//           mainScreenAlert.setTitle("Unable to Modify !");
//           mainScreenAlert.setContentText("Selection Error Occurred.");
//           mainScreenAlert.show();
//           System.out.println(e);
//       }
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) throws IOException {

            Product selectedProduct = productsMainMenuTableView.getSelectionModel().getSelectedItem();
            if(selectedProduct == null){
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
