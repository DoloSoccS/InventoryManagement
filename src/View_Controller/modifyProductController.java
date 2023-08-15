package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class modifyProductController {

    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<?, ?> addInventoryLevel;

    @FXML
    private TableColumn<?, ?> addPartID;

    @FXML
    private TableColumn<?, ?> addPartName;

    @FXML
    private TableView<?> addPartTableView;

    @FXML
    private TableColumn<?, ?> addPrice;

    @FXML
    private TextField maxStock;

    @FXML
    private TextField minStock;

    @FXML
    private TextField productIDField;

    @FXML
    private TextField productInventoryField;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productPriceField;

    @FXML
    private TableColumn<?, ?> removeInventoryLevel;

    @FXML
    private TableColumn<?, ?> removePartID;

    @FXML
    private TableColumn<?, ?> removePartName;

    @FXML
    private TableView<?> removePartTableView;

    @FXML
    private TableColumn<?, ?> removePrice;

    @FXML
    private TextField searchPartField;

    @FXML
    void onActionAddAssociatedPart(ActionEvent event) {

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

    }

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public modifyProductController() {

    }
}
