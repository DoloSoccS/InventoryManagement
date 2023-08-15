package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class mainMenuController {

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
    void onActionAddPart(ActionEvent event) {

    }

    @FXML
    void onActionAddProduct(ActionEvent event) {

    }

    @FXML
    void onActionDeletePart(ActionEvent event) {

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void onActionExit(ActionEvent event) {

    }

    @FXML
    void onActionModifyPart(ActionEvent event) {

    }

    @FXML
    void onActionModifyProduct(ActionEvent event) {

    }

    public mainMenuController() {
    }
}
