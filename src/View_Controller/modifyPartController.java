package View_Controller;

import Model.*;
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
    Part selectedPart;
    int index;
    private boolean isInHouse;
    Alert modifyPartScreenAlert;

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

    public modifyPartController() {}

    public modifyPartController(int index, Part part) {
        this.selectedPart = part;
        this.index = index;
    }

    @FXML
    void onActionInHouse(ActionEvent event) {
        isInHouse = true;
        inHouseRadioButton.setSelected(true);
        outsourcedRadioButton.setSelected(false);
        partSourceLbl.setText("Machine ID");
        partSource.setPromptText("Machine ID");
    }

    @FXML
    void onActionOutsource(ActionEvent event) {
        isInHouse = false;
        inHouseRadioButton.setSelected(false);
        outsourcedRadioButton.setSelected(true);
        partSourceLbl.setText("Company Name");
        partSource.setPromptText("Company Name");
    }


    public void sendPart(Part part){
        selectedPart = part;
        if(selectedPart instanceof InHouse){
            inHouseRadioButton.setSelected(true);
            partSourceLbl.setText("Machine ID");
            partSource.setText(Integer.toString(((InHouse) selectedPart).getMachineId()));
        } else {
            outsourcedRadioButton.setSelected(true);
            partSourceLbl.setText("Company Name");
            partSource.setText(((Outsourced) selectedPart).getCompanyName());
        }
        partID.setText(String.valueOf(selectedPart.getId()));
        partName.setText(selectedPart.getName());
        partInventory.setText(String.valueOf(selectedPart.getStock()));
        partPrice.setText(String.valueOf(selectedPart.getPrice()));
        maxStock.setText(String.valueOf(selectedPart.getMax()));
        minStock.setText(String.valueOf(selectedPart.getMin()));
    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {

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
                    Inventory.updatePart(this.index, addedPart);
                    mainMenu(event);
                } else {
                    modifyPartScreenAlert = new Alert(Alert.AlertType.ERROR);
                    modifyPartScreenAlert.setTitle("Save Failed!");
                    modifyPartScreenAlert.setContentText(((InHouse) addedPart).foundError);
                    modifyPartScreenAlert.show();
                }
            }catch(Exception e){

                modifyPartScreenAlert = new Alert(Alert.AlertType.ERROR);
                modifyPartScreenAlert.setTitle("Save Failed!");
                modifyPartScreenAlert.setContentText("Must use numbers for Inv, Price, Max, Min and Machine ID");
                modifyPartScreenAlert.show();
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
                    modifyPartScreenAlert = new Alert(Alert.AlertType.ERROR);
                    modifyPartScreenAlert.setTitle("Save Failed!");
                    modifyPartScreenAlert.setContentText(((Outsourced) addedPart).foundError);
                    modifyPartScreenAlert.show();
                }
            }catch(Exception e){
                modifyPartScreenAlert = new Alert(Alert.AlertType.ERROR);
                modifyPartScreenAlert.setTitle("Save Failed!");
                modifyPartScreenAlert.setContentText("Must use numbers for Inv, Price, Max and Min");
                modifyPartScreenAlert.show();
            }
        }
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
    }

    private void mainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
}
