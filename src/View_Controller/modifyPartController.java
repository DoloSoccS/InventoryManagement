package View_Controller;

import Model.InHouse;
import Model.Outsourced;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class modifyPartController implements Initializable {

    Stage stage;
    Parent scene;
    Part part;
    int index;

    @FXML private RadioButton inHouseRadioButton;
    @FXML private RadioButton outsourcedRadioButton;
    @FXML private TextField partID;
    @FXML private TextField partName;
    @FXML private TextField partInventory;
    @FXML private TextField partPrice;
    @FXML private TextField maxStock;
    @FXML private TextField minStock;
    @FXML private TextField partSource;
    @FXML private ToggleGroup sourceToggleGroup;
    @FXML private Label partSourceLbl;

    public modifyPartController(int index, Part part) {
        this.part = part;
        this.index = index;
    }

    @FXML
    void onActionInHouse(ActionEvent event) {

    }

    @FXML
    void onActionOutsource(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
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
        partID.setText(Integer.toString(part.getId()));
        partName.setText(part.getName());
        partInventory.setText(Integer.toString(part.getStock()));
        partPrice.setText(Double.toString(part.getPrice()));
        maxStock.setText(Integer.toString(part.getMin()));
        minStock.setText(Integer.toString(part.getMax()));

        if(part instanceof InHouse){
            inHouseRadioButton.setSelected(true);
            partSourceLbl.setText("Machine ID");
            partSource.setText(Integer.toString(((InHouse) part).getMachineId()));
        } else {
            outsourcedRadioButton.setSelected(true);
            partSourceLbl.setText("Company Name");
            partSource.setText(((Outsourced) part).getCompanyName());
        }

    }
}
