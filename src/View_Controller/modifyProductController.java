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
 * @author DeShawn Houston
 */
public class modifyProductController implements Initializable {


    //Variables to reference window
    Stage stage;
    Parent scene;
    Product selectedProduct;
    int index;

    //fxid variables for all the TextFields in the Modify Product window
    @FXML private TextField productIDField;
    @FXML private TextField productNameField;
    @FXML private TextField productInventoryField;
    @FXML private TextField productPriceField;
    @FXML private TextField maxStock;
    @FXML private TextField minStock;


    //Part Search, add TableView and TableColumn variables
    @FXML private TextField searchPartField;
    @FXML private TableView<Part> addPartTableView;
    @FXML private TableColumn<Part, Integer> addPartID;
    @FXML private TableColumn<Part, String> addPartName;
    @FXML private TableColumn<Part, Integer> addInventoryLevel;
    @FXML private TableColumn<Part, Double> addPrice;


    //Remove Part TableView and TableColumn variables
    @FXML private TableView<Part> removePartTableView;
    @FXML private TableColumn<Part, Integer> removePartID;
    @FXML private TableColumn<Part, String> removePartName;
    @FXML private TableColumn<Part, Integer> removeInventoryLevel;
    @FXML private TableColumn<Part, Double> removePrice;


    /**
     *
     * @param product holds the Product object selected from the main menu
     * This method will populate the object's attributes labels and
     * set the selectProduct object to allow for adding and removing the
     *                associated parts.
     */
    public void sendProduct(int index, Product product){
        selectedProduct = product;
        this.index = index;
        productNameField.setText(selectedProduct.getName());
        productInventoryField.setText(String.valueOf(product.getStock()));
        productPriceField.setText(String.valueOf(product.getPrice()));
        maxStock.setText(String.valueOf(product.getMax()));
        minStock.setText(String.valueOf(product.getMin()));
        removePartTableView.setItems(product.getAllAssociatedParts());
        productIDField.setText(String.valueOf(product.getId()));
    }


    @FXML ObservableList<Part> onActionSearchPartField(ActionEvent event) {
        return Inventory.getAllParts();

    }

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

    @FXML void onActionRemoveAssociatedPart(ActionEvent event) {
        Alert mainScreenAlert = new Alert(Alert.AlertType.CONFIRMATION);
        mainScreenAlert.setTitle("Deleting Part.");
        mainScreenAlert.setContentText("Are you sure you want to delete this part ?");
        Optional<ButtonType> option = mainScreenAlert.showAndWait();
        if(option.isPresent() && option.get() == ButtonType.OK) {
        Part byePart = removePartTableView.getSelectionModel().getSelectedItem();
        selectedProduct.deleteAssociatedPart(byePart);}
    }

    @FXML void onActionSaveProduct(ActionEvent event) throws IOException {


        try{
            Product modifyProduct = new Product(
                    Integer.parseInt(productIDField.getText()),
                    productNameField.getText(),
                    Double.parseDouble(productPriceField.getText()),
                    Integer.parseInt(productInventoryField.getText()),
                    Integer.parseInt(minStock.getText()),
                    Integer.parseInt(maxStock.getText())
            );
            if(!modifyProduct.notValid(modifyProduct.getName(), modifyProduct.getPrice(), modifyProduct.getStock(), modifyProduct.getMin(), modifyProduct.getMax())){
                Inventory.updateProduct(this.index, modifyProduct);
                for (int i = 0; i < modifyProduct.getAllAssociatedParts().size(); i++) {
                    modifyProduct.addAssociatedPart(modifyProduct.getAllAssociatedParts().get(i));
                }
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();}
            else{
                Alert screenAlert;
                screenAlert = new Alert(Alert.AlertType.ERROR);
                screenAlert.setTitle("Save Failed!");
                screenAlert.setContentText(modifyProduct.foundError);
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
     * Standard class constructor
     */
    public modifyProductController(int index, Product p) {
        this.index = index;
        this.selectedProduct = p;
    }

    public modifyProductController(){}
}
