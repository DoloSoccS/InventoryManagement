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

/**
 * This controller class is for modifying Part objects
 */
public class modifyPartController implements Initializable {

    /**
     * Stage and scene variables for displaying the GUI window
     * modifyPartScreenAlert is used to display on screen messages for events
     * modifyPart is used to reference the selected Part object on this screen
     * index is used to store that object's index in the Inventory.allParts list
     * isInHouse is used to declare whether an object is InHouse or Outsourced
     */
    Stage stage;
    Parent scene;
    Part modifyPart;
    int index;
    private boolean isInHouse;
    Alert modifyPartScreenAlert;

    /**
     * FXID's for the corresponding RadioButtons, TextFields, Labels and ToggleGroup in the FXML file
     */
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

    /**
     * standard constructor for instantiating the controller without provided values
     */
    public modifyPartController(){}

    /**
     * standard constructor for instantiating the controller with the index and
     * Part attributes that are stored
     */
    public modifyPartController(int index, Part part) {
        this.index = index;
        this.modifyPart = part;
    }

    /**
     * method is used indicate the InHouse RadioButton is selected and makes that object
     * provide the Machine ID value
     */
    @FXML
    void onActionInHouse(ActionEvent event) {
        isInHouse = true;
        inHouseRadioButton.setSelected(true);
        outsourcedRadioButton.setSelected(false);
        partSourceLbl.setText("Machine ID");
        partSource.setPromptText("Machine ID");
    }

    /**
     * method is used indicate the Outsourced RadioButton is selected and makes that object
     * provide the Company Name value
     */
    @FXML
    void onActionOutsource(ActionEvent event) {
        isInHouse = false;
        inHouseRadioButton.setSelected(false);
        outsourcedRadioButton.setSelected(true);
        partSourceLbl.setText("Company Name");
        partSource.setPromptText("Company Name");
    }

    /**
     *
     * This method will populate the object's attribute labels and
     * set the modifyPart object to allow for updating values
     */
    public void sendPart(int index, Part part){
        modifyPart = part;
        this.index = index;
        if(modifyPart instanceof InHouse){
            isInHouse = true;
            inHouseRadioButton.setSelected(true);
            partSourceLbl.setText("Machine ID");
            partSource.setText(Integer.toString(((InHouse) modifyPart).getMachineId()));
        } else {
            outsourcedRadioButton.setSelected(true);
            partSourceLbl.setText("Company Name");
            partSource.setText(((Outsourced) modifyPart).getCompanyName());
        }
        partID.setText(String.valueOf(modifyPart.getId()));
        partName.setText(modifyPart.getName());
        partInventory.setText(String.valueOf(modifyPart.getStock()));
        partPrice.setText(String.valueOf(modifyPart.getPrice()));
        maxStock.setText(String.valueOf(modifyPart.getMax()));
        minStock.setText(String.valueOf(modifyPart.getMin()));
    }

    /**
     * Save method adds a new Part to the Inventory list after validating the
     * correct data types and required information is input for the correlating
     * object types. It also removes the old Part object if the type changes from InHouse to
     * Outsource or vice versa
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {

        //auto increment added to save method to prevent incrementing without use
        int id = Integer.parseInt(partID.getText());
        String name = partName.getText();
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
                Part moddedPart = new InHouse(id, name, price, stock, min, max, machineId);
                if (!(((InHouse) moddedPart).notValid(name, price, stock, min, max))) {
                    Inventory.updatePart(this.index, moddedPart);
                    mainMenu(event);
                } else {
                    modifyPartScreenAlert = new Alert(Alert.AlertType.ERROR);
                    modifyPartScreenAlert.setTitle("Save Failed!");
                    modifyPartScreenAlert.setContentText(((InHouse) moddedPart).foundError);
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
                Inventory.deletePart(modifyPart);
                stock = Integer.parseInt(partInventory.getText());
                price = Double.parseDouble(partPrice.getText());
                max = Integer.parseInt(maxStock.getText());
                min = Integer.parseInt(minStock.getText());

                String companyName = partSource.getText();
                Part moddedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                if(!(((Outsourced) moddedPart).notValid(name, companyName, price, stock, min, max))) {
                    Inventory.addPart(moddedPart);
                    mainMenu(event);
                }
                else {
                    modifyPartScreenAlert = new Alert(Alert.AlertType.ERROR);
                    modifyPartScreenAlert.setTitle("Save Failed!");
                    modifyPartScreenAlert.setContentText(((Outsourced) moddedPart).foundError);
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

    /**
     * method is used to cancel the modify part function and returns to the Main Menu
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * method is used to get the initial state of the modify Part screen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * method is used to return to the Main Menu screen when executed by other methods
     */
    private void mainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
}
