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
 * This class holds the functions for updating a product's attributes along with adding and removing
 * it's associated parts.
 */
public class modifyProductController implements Initializable {

    /**
     * Stage and scene variables for displaying the GUI window
     * selectedProduct is used as an initial object instance
     * index is referenced in the Inventory.allProducts list
     */
    //Variables to reference window
    Stage stage;
    Parent scene;
    Product selectedProduct;
    int index;

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
     * FXID's for the corresponding Search Field, Add Part TableView and TableColumns in the FXML file
     */
    //Part Search, add TableView and TableColumn variables
    @FXML private TextField searchPartField;
    @FXML private TableView<Part> addPartTableView;
    @FXML private TableColumn<Part, Integer> addPartID;
    @FXML private TableColumn<Part, String> addPartName;
    @FXML private TableColumn<Part, Integer> addInventoryLevel;
    @FXML private TableColumn<Part, Double> addPrice;

    /**
     * FXID's for the corresponding Asscoiated Parts TableView and TableColumns in the FXML file
     */
    //Remove Part TableView and TableColumn variables
    @FXML private TableView<Part> removePartTableView;
    @FXML private TableColumn<Part, Integer> removePartID;
    @FXML private TableColumn<Part, String> removePartName;
    @FXML private TableColumn<Part, Integer> removeInventoryLevel;
    @FXML private TableColumn<Part, Double> removePrice;


    /**
     *
     * This method will populate the object's attributes labels and
     * set the selectProduct object to allow for adding and removing the
     *                associated parts.
     */
    public void sendProduct(int index, Product product){
        selectedProduct = product;
        this.index = index;
        productNameField.setText(selectedProduct.getName());
        productInventoryField.setText(String.valueOf(selectedProduct.getStock()));
        productPriceField.setText(String.valueOf(selectedProduct.getPrice()));
        maxStock.setText(String.valueOf(selectedProduct.getMax()));
        minStock.setText(String.valueOf(selectedProduct.getMin()));
        removePartTableView.setItems(selectedProduct.getAllAssociatedParts());
        productIDField.setText(String.valueOf(selectedProduct.getId()));
    }

    /**
     * this method searches the available Parts to add.
     * If nothing is entered, a "No Part Entered" message displays
     * If no ID or name is found, a correlating message also displays
     * Once the operation is run, the method resets the search field to blank
     */
    @FXML void onActionSearchPartField(ActionEvent event) {
        Alert ScreenAlert;
        if(!searchPartField.getText().trim().isEmpty()){
            try{
                int searchId = Integer.parseInt(searchPartField.getText());
                Part found = Inventory.lookupPart(searchId);
                if(found != null){
                    addPartTableView.getSelectionModel().select(found);
                    searchPartField.setText("");
                } else {
                    searchPartField.setText("");
                    ScreenAlert = new Alert(Alert.AlertType.ERROR);
                    ScreenAlert.setTitle("Search");
                    ScreenAlert.setContentText("No ID Found.");
                    ScreenAlert.showAndWait();
                    addPartTableView.setItems(Inventory.getAllParts());
                }

            } catch(NumberFormatException nfe){
                String searchName = searchPartField.getText();
                ObservableList<Part> found;
                found = Inventory.lookupPart(searchName);
                if(!found.isEmpty()){
                    addPartTableView.setItems(found);
                    searchPartField.setText("");
                } else {
                    searchPartField.setText("");
                    ScreenAlert = new Alert(Alert.AlertType.ERROR);
                    ScreenAlert.setTitle("Search");
                    ScreenAlert.setContentText("No Name Found.");
                    ScreenAlert.showAndWait();
                    addPartTableView.setItems(Inventory.getAllParts());
                }
            }
        } else {
            searchPartField.setText("");
            ScreenAlert = new Alert(Alert.AlertType.ERROR);
            ScreenAlert.setTitle("Search");
            ScreenAlert.setContentText("No Part Entered.");
            ScreenAlert.showAndWait();
            addPartTableView.setItems(Inventory.getAllParts());
        }

    }

    /**
     * adds an associated part to the Product object
     */
    @FXML void onActionAddAssociatedPart(ActionEvent event) {
        Part newPart = addPartTableView.getSelectionModel().getSelectedItem();
        if(newPart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Part Selected");
            Optional<ButtonType> option = alert.showAndWait();
        }
        selectedProduct.addAssociatedPart(newPart);

    }

    /**
     * removes an associated part from the Product object
     */
    @FXML void onActionRemoveAssociatedPart(ActionEvent event) {
        Alert mainScreenAlert = new Alert(Alert.AlertType.CONFIRMATION);
        mainScreenAlert.setTitle("Deleting Part.");
        mainScreenAlert.setContentText("Are you sure you want to delete this part ?");
        Optional<ButtonType> option = mainScreenAlert.showAndWait();
        if(option.isPresent() && option.get() == ButtonType.OK) {
        Part byePart = removePartTableView.getSelectionModel().getSelectedItem();
        selectedProduct.deleteAssociatedPart(byePart);}
    }

    /**
     * saves the updated product object and places it back into the allProducts list while
     * retaining the ID
     *
     * RUNTIME ERROR

     InvocationTargetException

     Invocation target exception was the hierarchy class exception for NumberFormatException.
     The "nfe" exception was due to a bad input into a variable given the data type i.e.,
     trying to put a String into and int variable. That was solved by casting the .getText()
     return value into the proper type such as int or double.
     */
    @FXML void onActionSaveProduct(ActionEvent event) throws IOException {

        try{
            selectedProduct.setId(Integer.parseInt(productIDField.getText()));
            selectedProduct.setName(productNameField.getText());
            selectedProduct.setPrice(Double.parseDouble(productPriceField.getText()));
            selectedProduct.setStock(Integer.parseInt(productInventoryField.getText()));
            selectedProduct.setMin(Integer.parseInt(minStock.getText()));
            selectedProduct.setMax(Integer.parseInt(maxStock.getText()));

            if(!selectedProduct.notValid(selectedProduct.getName(), selectedProduct.getPrice(), selectedProduct.getStock(), selectedProduct.getMin(), selectedProduct.getMax())){
                Inventory.updateProduct(Integer.parseInt(productIDField.getText())-1, selectedProduct);
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();}
            else{
                Alert screenAlert;
                screenAlert = new Alert(Alert.AlertType.ERROR);
                screenAlert.setTitle("Save Failed!");
                screenAlert.setContentText(selectedProduct.foundError);
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
     * cancels the Product modification and returns to the Main Menu
     */
    @FXML void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * This method pre-populates the addPartTableView with getAllParts() method as well
     * as sets the table column names.
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

        //Populates the Parts table...
        addPartTableView.setItems(Inventory.getAllParts());
        addPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        addPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        addInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        removePartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        removePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        removeInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        removePrice.setCellValueFactory(new PropertyValueFactory<>("price"));



    }

    /**
     * Standard class constructor that holds the provided Product index and values
     */
    public modifyProductController(int index, Product p) {
        this.index = index;
        this.selectedProduct = p;
    }

    /**
     * Standard class constructor for instantiating the class without values provided
     */
    public modifyProductController(){}

}
