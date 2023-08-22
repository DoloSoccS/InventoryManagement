package View_Controller;

import Model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * add product controller is used to create new Product objects
 */
public class addProductController implements Initializable {

    /**
     * Stage and Scene are used to display the add product screen
     * newProduct is declared to hold the new Product object's values
     * mainScreenAlert is used in the search field for displaying error messages
     */
    //Variables to reference window
    Stage stage;
    Parent scene;
    Product newProduct = new Product();
    Alert mainScreenAlert;

    /**
     * FXID's for the corresponding TextFields in the FXML file
     */
    //fxid variables for all the TextFields in the Modify Product window
    @FXML private TextField productIDField;
    @FXML private TextField productNameField;
    @FXML private TextField productInventoryField;
    @FXML private TextField productPriceField;
    @FXML private TextField maxStock;
    @FXML private TextField minStock;

    /**
     * FXID's for the corresponding Search Field, Add Parts TableView and TableColumns in the FXML file
     */
    //Part Search, add TableView and TableColumn variables
    @FXML private TextField searchPartField;
    @FXML private TableView<Part> addPartTableView;
    @FXML private TableColumn<Part, Integer> addPartID;
    @FXML private TableColumn<Part, String> addPartName;
    @FXML private TableColumn<Part, Integer> addInventoryLevel;
    @FXML private TableColumn<Part, Double> addPrice;

    /**
     * FXID's for the corresponding Associated Parts TableView and TableColumns in the FXML file
     */
    //Remove Part TableView and TableColumn variables
    @FXML private TableView<Part> removePartTableView;
    @FXML private TableColumn<Part, Integer> removePartID;
    @FXML private TableColumn<Part, String> removePartName;
    @FXML private TableColumn<Part, Integer> removeInventoryLevel;
    @FXML private TableColumn<Part, Double> removePrice;

    /**
     * adds an associated part to the Product object
     */
    @FXML
    void onActionAddAssociatedPart(ActionEvent event) {
        Part newPart = addPartTableView.getSelectionModel().getSelectedItem();
        if(newPart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Part Selected");
            Optional<ButtonType> option = alert.showAndWait();
        } else {
            newProduct.addAssociatedPart(newPart);
        }

        removePartTableView.setItems(newProduct.getAllAssociatedParts());
    }

    /**
     * cancels the Product creation and returns to the Main Menu
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * removes an associated part from the Product object
     */
    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {
        Alert mainScreenAlert = new Alert(Alert.AlertType.CONFIRMATION);
        mainScreenAlert.setTitle("Deleting Part.");
        mainScreenAlert.setContentText("Are you sure you want to delete this part ?");
        Optional<ButtonType> option = mainScreenAlert.showAndWait();
        if(option.isPresent() && option.get() == ButtonType.OK) {
            Part byePart = removePartTableView.getSelectionModel().getSelectedItem();
            newProduct.deleteAssociatedPart(byePart);}

    }

    /**
     * this method searches the available Parts to add.
     * If nothing is entered, a "No Part Entered" message displays
     * If no ID or name is found, a correlating message also displays
     * Once the operation is run, the method resets the search field to blank
     */
    @FXML
    void onActionSearchPartField(ActionEvent event) {
        if (!searchPartField.getText().trim().isEmpty()) {
            try {
                int searchId = Integer.parseInt(searchPartField.getText());
                Part found = Inventory.lookupPart(searchId);
                if (found != null) {
                    addPartTableView.getSelectionModel().select(found);
                    searchPartField.setText("");
                } else {
                    searchPartField.setText("");
                    mainScreenAlert = new Alert(Alert.AlertType.ERROR);
                    mainScreenAlert.setTitle("Search");
                    mainScreenAlert.setContentText("No ID Found.");
                    mainScreenAlert.showAndWait();
                    addPartTableView.setItems(Inventory.getAllParts());
                }

            } catch (NumberFormatException nfe) {
                String searchName = searchPartField.getText();
                ObservableList<Part> found;
                found = Inventory.lookupPart(searchName);
                if (!found.isEmpty()) {
                    addPartTableView.setItems(found);
                } else {
                    mainScreenAlert = new Alert(Alert.AlertType.ERROR);
                    mainScreenAlert.setTitle("Search");
                    mainScreenAlert.setContentText("No Name Found.");
                    mainScreenAlert.showAndWait();
                    addPartTableView.setItems(Inventory.getAllParts());
                    System.out.println(nfe.getCause());
                }
            }
        } else {
            mainScreenAlert = new Alert(Alert.AlertType.ERROR);
            mainScreenAlert.setTitle("Search");
            mainScreenAlert.setContentText("No Part Entered.");
            mainScreenAlert.showAndWait();
            addPartTableView.setItems(Inventory.getAllParts());
        }
    }

    /**
     * saves the updated product object and places it back into the allProducts list while
     * retaining the ID
     */
    //Saves Product and returns to Main Menu if inputs are compatible
    @FXML void onActionSaveProduct(ActionEvent event) throws IOException {

        try{
            newProduct.setId(Inventory.nextProductID());
            newProduct.setName(productNameField.getText());
            newProduct.setPrice(Double.parseDouble(productPriceField.getText()));
            newProduct.setStock(Integer.parseInt(productInventoryField.getText()));
            newProduct.setMin(Integer.parseInt(minStock.getText()));
            newProduct.setMax(Integer.parseInt(maxStock.getText()));

            if(!newProduct.notValid(newProduct.getName(), newProduct.getPrice(), newProduct.getStock(), newProduct.getMin(), newProduct.getMax())){
                Inventory.addProduct(newProduct);
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();}
            else{
                Alert screenAlert;
                screenAlert = new Alert(Alert.AlertType.ERROR);
                screenAlert.setTitle("Save Failed!");
                screenAlert.setContentText(newProduct.foundError);
                screenAlert.show();
            }
        }catch(Exception e){
            Alert screenAlert;
            screenAlert = new Alert(Alert.AlertType.ERROR);
            screenAlert.setTitle("Save Failed!");
            screenAlert.setContentText("Must use numbers for Inv, Price, Max and Min");
            screenAlert.show();
        }

    }

    /**
     * This method pre-populates the addPartTableView with getAllParts() method as well
     * as sets the table column names.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Populates the Parts table...
        addPartTableView.setItems(Inventory.getAllParts());
        addPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        addPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        addInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Populates Associated Parts table...
        removePartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        removePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        removeInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        removePrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Displays the given Product ID value
        productIDField.setText(String.valueOf(Inventory.nextProductID()));
    }

    /**
     * Standard class constructor for instantiating the class without values provided
     */
    public addProductController(){}
}
