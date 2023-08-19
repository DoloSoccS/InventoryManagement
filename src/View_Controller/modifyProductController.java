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


    public void sendProduct(Product product){
        productNameField.setText(product.getName());
        productInventoryField.setText(String.valueOf(product.getStock()));
        productPriceField.setText(String.valueOf(product.getPrice()));
        maxStock.setText(String.valueOf(product.getMax()));
        minStock.setText(String.valueOf(product.getMin()));
        removePartTableView.setItems(product.getAllAssociatedParts());
        productIDField.setText(String.valueOf(product.getId()));
        selectedProduct = product;
    }

    public modifyProductController(int index, Product selectedProduct) {
        this.index = index;
        this.selectedProduct = selectedProduct;
    }


    @FXML
    ObservableList<Part> onActionSearchPartField(ActionEvent event) {
        return Inventory.getAllParts();

    }

    @FXML
    void onActionAddAssociatedPart(ActionEvent event) {

    }

    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {
        Part byePart = removePartTableView.getSelectionModel().getSelectedItem();
        selectedProduct.deleteAssociatedPart(byePart);
    }

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Populates the selected products attributes
//        productIDField.setText(String.valueOf(selectedProduct.getId()));
//        productNameField.setText(selectedProduct.getName());
//        productInventoryField.setText(String.valueOf(selectedProduct.getStock()));
//        productPriceField.setText(String.valueOf(selectedProduct.getPrice()));
//        maxStock.setText(String.valueOf(selectedProduct.getMax()));
//        minStock.setText(String.valueOf(selectedProduct.getMin()));;


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

 //       removePartTableView.setItems(selectedProduct.getAllAssociatedParts());
        removePartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        removePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        removeInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        removePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public modifyProductController() {

    }
}
