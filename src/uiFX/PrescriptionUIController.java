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
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import resources.alerts;
import resources.logger;
import resources.prescription;
import resources.req_info;
import resources.requirements;
import resources.validation;
import resources.valueHolder;

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
    valueHolder idofPres;
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
    
    /**init the old values thats gotten from the prev. scene
     * 
     * @param idofPres
     * @param oldScene
     * @param set
     * @param view 
     */
    public void initOldvalues(valueHolder idofPres,Scene oldScene,Button set,Button view){
    this.idofPres=idofPres;
    this.oldScene=oldScene;
    setButtonOfOldFrame=set;
    ViewButtonOfOldFrame=view;
    
    }
    
    /**validates the prescription form
     * 
     * @return 
     */
    public boolean validator(){
    
        boolean validation=true;
        
        validation test=new validation();
        
        validation&=test.isNotNullandEmpty("Dosage", dosage.getText());
        validation&=test.isNotNullandEmpty("Details", details.getText());
        validation&=test.objectNullCheck("From Date", fromdate.getValue());
        validation&=test.objectNullCheck("To Date", todate.getValue());
        if(validation)
            return validation;
        else{
        alerts.warningMSG(test.errormsg);
        return validation;
        }
    }
    

    /**submits the form for insertion
     * 
     * @param event 
     */
    @FXML
    private void submit(ActionEvent event) {
        if(validator()){
            prescription tmp=new prescription(0, dosage.getText(), details.getText(), Date.valueOf(fromdate.getValue()), Date.valueOf(todate.getValue()));
            req_info test=requirements.insertToPrescription(tmp);
            if(test.isInserted()){
            tmp.setId(test.getId());
            idofPres.setNumber(test.getId());
            setButtonOfOldFrame.setDisable(true);
            goBack(event);
            }
            else{
            alerts.warningMSG("Failed to insert");
                logger.appendnewLog("Failed to insert an entry to prescription table");
            }
        
            }
        
    }
    /**applies detail mode which disables editing
     * 
     */ 
    public void detailMode(){
        done.setVisible(false);
        prescription pres=requirements.returnPrescription(idofPres.getNumber());
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
    
    /**goes back to the prev. scene
     * 
     * @param event 
     */
    
    @FXML
    private void goBack(ActionEvent event) {
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(oldScene);
         window.show();
    }
    
}
