package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class modifyPartController {

    Stage stage;
    Parent scene;

    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private TextField maxStock;

    @FXML
    private TextField minStock;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private TextField partID;

    @FXML
    private TextField partInventory;

    @FXML
    private TextField partName;

    @FXML
    private TextField partPrice;

    @FXML
    private TextField partSource;

    @FXML
    private ToggleGroup sourceToggleGroup;

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public modifyPartController() {

    }
}
