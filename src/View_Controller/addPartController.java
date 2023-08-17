package View_Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addPartController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private TextField partID;

    @FXML
    private TextField partName;

    @FXML
    private TextField partInventory;

    @FXML
    private TextField partPrice;

    @FXML
    private TextField maxStock;

    @FXML
    private TextField minStock;

    @FXML
    private Label partSourceLbl;

    @FXML
    private TextField partSource;

    @FXML
    private ToggleGroup sourceToggleGroup;

    boolean isInHouse;

    //onAction needed for FXML file to have program realize button was selected.
    @FXML
    void onActionInHouse(ActionEvent event) {
        isInHouse = true;
        inHouseRadioButton.setSelected(true);
        outsourcedRadioButton.setSelected(false);
        partSourceLbl.setText("Machine ID");
        partSource.setPromptText("Machine ID");
    }

    @FXML
    void onActionOutsourced(ActionEvent event) {
        isInHouse = false;
        inHouseRadioButton.setSelected(false);
        outsourcedRadioButton.setSelected(true);
        partSourceLbl.setText("Company Name");
        partSource.setPromptText("Company Name");
    }
    //Saves the new Part with the added values if compatible then goes back to Main Menu
    @FXML
    void onActionSave(ActionEvent event) throws IOException {

        //auto increment added to save method to prevent incrementing without use

        int id = Inventory.nextPartID();
        String name = partName.getText();
        int stock = Integer.parseInt(partInventory.getText());
        double price = Double.parseDouble(partPrice.getText());
        int max = Integer.parseInt(maxStock.getText());
        int min = Integer.parseInt(minStock.getText());
        String source = partSource.getText();
        int machineId;
        String companyName;

        if (inHouseRadioButton.isSelected()) {

            machineId = Integer.parseInt(partSource.getText());
            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
        } else {

            companyName = partSource.getText();
            Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
        }



        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
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

    public addPartController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
