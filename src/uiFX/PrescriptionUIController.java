/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package uiFX;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import resources.prescription;
import resources.requirements;

/**
 * FXML Controller class
 *
 * @author rawan
 */
public class PrescriptionUIController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField dosage;
    @FXML
    private JFXDatePicker todate;
    @FXML
    private Button done;
    @FXML
    private Button back;
    @FXML
    private JFXTextArea details;
    @FXML
    private JFXDatePicker fromdate;

    //variables
    int idofPres;
    Scene oldScene;
    Button setButtonOfOldFrame;
    Button ViewButtonOfOldFrame;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setText(String.valueOf(requirements.getCountforTable(prescription.Table_Name)+1));
        id.setEditable(false);
    }
    public void initOldvalues(int idofPres,Scene oldScene,Button set,Button view){
    this.idofPres=idofPres;
    this.oldScene=oldScene;
    setButtonOfOldFrame=set;
    ViewButtonOfOldFrame=view;
    
    }    

    @FXML
    private void submit(ActionEvent event) {
        
    }
    
    public void detailMode(){
        done.setVisible(false);
        prescription pres=requirements.returnPrescription(idofPres);
        id.setText(String.valueOf(pres.getId()));
        dosage.setText(pres.getDosage());
        dosage.setEditable(false);
        fromdate.setValue(pres.getFrom_Date().toLocalDate());
        fromdate.setEditable(false);
        todate.setValue(pres.getTo_Date().toLocalDate());
        todate.setEditable(false);
        details.setText(pres.getDetails());
        details.setEditable(false);
    
    }
    
    @FXML
    private void goBack(ActionEvent event) {
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(oldScene);
         window.show();
    }
    
}
