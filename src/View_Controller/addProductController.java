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

public class addProductController implements Initializable {

    //Variables to reference window
    Product selectedProduct = new Product();
    Stage stage;
    Parent scene;
    Alert mainScreenAlert;

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
        Part newPart = addPartTableView.getSelectionModel().getSelectedItem();
        if(newPart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Part Selected");
            Optional<ButtonType> option = alert.showAndWait();
        } else {
            selectedProduct.addAssociatedPart(newPart);
        }

        removePartTableView.setItems(selectedProduct.getAllAssociatedParts());
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
        Alert mainScreenAlert = new Alert(Alert.AlertType.CONFIRMATION);
        mainScreenAlert.setTitle("Deleting Part.");
        mainScreenAlert.setContentText("Are you sure you want to delete this part ?");
        Optional<ButtonType> option = mainScreenAlert.showAndWait();
        if(option.isPresent() && option.get() == ButtonType.OK) {
            Part byePart = removePartTableView.getSelectionModel().getSelectedItem();
            selectedProduct.deleteAssociatedPart(byePart);}

    }

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




    //Saves Product and returns to Main Menu if inputs are compatible
    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {

        //auto increment added to save method to prevent incrementing without use
        int id = Inventory.nextProductID();
        String name = productNameField.getText();
        Product selectedProduct;

        try{
            int stock = Integer.parseInt(productInventoryField.getText());
            double price = Double.parseDouble(productPriceField.getText());
            int max = Integer.parseInt(maxStock.getText());
            int min = Integer.parseInt(minStock.getText());
            selectedProduct = new Product(id, name, price, stock, min, max);
            if(!selectedProduct.notValid(selectedProduct.getName(), selectedProduct.getPrice(), selectedProduct.getStock(), selectedProduct.getMin(), selectedProduct.getMax())){
                Inventory.addProduct(new Product(id, name, price, stock, min, max));
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

        productIDField.setText(String.valueOf(Inventory.nextProductID()));
    }

    public addProductController(){

    }
}
