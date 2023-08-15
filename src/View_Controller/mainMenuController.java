package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class mainMenuController {

    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<?, ?> partIDMainMenu;

    @FXML
    private TableColumn<?, ?> partNameMainMenu;

    @FXML
    private TextField partSearchMainMenu;

    @FXML
    private TableColumn<?, ?> partsInventoryLevelMainMenu;

    @FXML
    private TableView<?> partsMainMenuTableView;

    @FXML
    private TableColumn<?, ?> partsPriceMainMenu;

    @FXML
    private TableColumn<?, ?> productIDMainMenu;

    @FXML
    private TableColumn<?, ?> productInventoryLevelMainMenu;

    @FXML
    private TableColumn<?, ?> productNameMainMenu;

    @FXML
    private TextField productSearchMainMenu;

    @FXML
    private TableView<?> productsMainMenuTableView;

    @FXML
    private TableColumn<?, ?> productsPriceMainMenu;

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/addPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/addProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeletePart(ActionEvent event) throws IOException {

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) throws IOException {

    }

    @FXML
    void onActionExit(ActionEvent event) throws IOException {
        System.exit(0);
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/modifyPart.fxml"));
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

    public mainMenuController() {
    }
}
