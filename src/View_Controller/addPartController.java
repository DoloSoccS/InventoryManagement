package View_Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class addPartController implements Initializable {

    Stage stage;
    Parent scene;
    Alert addPartScreenAlert;

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

    private boolean isInHouse;

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

    /**
     * Save method adds a new Part to the Inventory list after validating the
     * correct data types and required information is input for the correlating
     * object types.
     */
    @FXML
    void onActionSave(ActionEvent event) throws Exception {

        //auto increment added to save method to prevent incrementing without use
        int id = Integer.parseInt(partID.getText());
        String name = partName.getText();
        Part addedPart;
        int stock;
        double price;
        int max;
        int min;
        if(isInHouse){
            try {
                stock = Integer.parseInt(partInventory.getText());
                price = Double.parseDouble(partPrice.getText());
                max = Integer.parseInt(maxStock.getText());
                min = Integer.parseInt(minStock.getText());


                int machineId = Integer.parseInt(partSource.getText());
                addedPart = new InHouse(id, name, price, stock, min, max, machineId);
                if (!(((InHouse) addedPart).notValid(name, price, stock, min, max))) {
                    Inventory.addPart(addedPart);
                    mainMenu(event);
                } else {
                    addPartScreenAlert = new Alert(Alert.AlertType.ERROR);
                    addPartScreenAlert.setTitle("Save Failed!");
                    addPartScreenAlert.setContentText(((InHouse) addedPart).foundError);
                    addPartScreenAlert.show();
                }
            }catch(Exception e){

                addPartScreenAlert = new Alert(Alert.AlertType.ERROR);
                addPartScreenAlert.setTitle("Save Failed!");
                addPartScreenAlert.setContentText("Must use numbers for Inv, Price, Max, Min and Machine ID");
                addPartScreenAlert.show();
            }
        }
        else {
            try{
                stock = Integer.parseInt(partInventory.getText());
                price = Double.parseDouble(partPrice.getText());
                max = Integer.parseInt(maxStock.getText());
                min = Integer.parseInt(minStock.getText());

                String companyName = partSource.getText();
                addedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                if(!(((Outsourced) addedPart).notValid(name, companyName, price, stock, min, max))) {
                    Inventory.addPart(addedPart);
                    mainMenu(event);
                }
                else {
                    addPartScreenAlert = new Alert(Alert.AlertType.ERROR);
                    addPartScreenAlert.setTitle("Save Failed!");
                    addPartScreenAlert.setContentText(((Outsourced) addedPart).foundError);
                    addPartScreenAlert.show();
                }
            }catch(Exception e){
                addPartScreenAlert = new Alert(Alert.AlertType.ERROR);
                addPartScreenAlert.setTitle("Save Failed!");
                addPartScreenAlert.setContentText("Must use numbers for Inv, Price, Max and Min");
                addPartScreenAlert.show();
            }
        }
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        addPartScreenAlert = new Alert(Alert.AlertType.CONFIRMATION);
        addPartScreenAlert.setTitle("Abort Operation");
        addPartScreenAlert.setContentText("Are you sure you want to cancel adding a part ?");
        Optional<ButtonType> option = addPartScreenAlert.showAndWait();
        if(option.isPresent() && option.get() == ButtonType.OK){
            mainMenu(event);
        }
    }

    public addPartController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partID.setText( String.valueOf(Inventory.nextPartID()));
        isInHouse = true;
        inHouseRadioButton.setSelected(true);
        partSourceLbl.setText("Machine ID");
        partSource.setPromptText("Machine ID");

    }

    private void mainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
