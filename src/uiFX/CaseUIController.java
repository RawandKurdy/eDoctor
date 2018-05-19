/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package uiFX;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import resources.Illneses;
import resources.alerts;
import resources.cases;
import resources.logger;
import resources.requirements;
import resources.validation;
import resources.valueHolder;

/**
 * FXML Controller class
 *
 * @author rawan
 */
public class CaseUIController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField pateint_id;
    @FXML
    private JFXTextField session_id;
    @FXML
    private JFXDatePicker date;
    @FXML
    private Button done;
    @FXML
    private Button back;
    @FXML
    private JFXTextArea notes;
    @FXML
    private ChoiceBox<Illneses> illneses;
    @FXML
    private Button illnessView;
    

    //variables
    valueHolder idofCase;
    Scene oldScene;
    Button setButtonOfOldFrame;
    Button ViewButtonOfOldFrame;
    cases casetmp;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setText(String.valueOf(requirements.getCountforTable(cases.Table_Name)+1));
        id.setEditable(false);
        session_id.setEditable(false);
        pateint_id.setEditable(false);
        illneses.setItems(getIllneses());
    }    
    
   //gets the illneses
    public ObservableList<Illneses> getIllneses(){
    ObservableList<Illneses> list =FXCollections.observableArrayList();
    list.addAll(requirements.returnAllillneses());
    return list;
    }

    @FXML
    private void submit(ActionEvent event) {
        if(validator()){
        casetmp.setId(-5); // -5 meaning in buffer
        casetmp.setIllnes_id(illneses.getValue().getId());
        casetmp.setNotes(notes.getText());
        casetmp.setDate_of(Date.valueOf(date.getValue()));
        setButtonOfOldFrame.setDisable(true);
            goBack(event);
        
        }
       
    }
    
      public boolean validator(){
    
        boolean validation=true;
        
        validation test=new validation();
        
        validation&=test.isNotNullandEmpty("Notes", notes.getText());
        validation&=test.objectNullCheck("Date", date.getValue());
        validation&=test.objectNullCheck("illness", illneses.getValue());
        if(validation)
            return validation;
        else{
        alerts.warningMSG(test.errormsg);
        return validation;
        }
    }
    
    public void initoldValues(Scene s,valueHolder idofCase,Button set,Button view,cases casetmp){
        oldScene=s;
        this.idofCase=idofCase;
        setButtonOfOldFrame=set;
        ViewButtonOfOldFrame=view;
        this.casetmp=casetmp;
    }
    
    public void detailsMode(){
        // -5 meaning in buffer
        if(casetmp.getId()==-5){
        pateint_id.setText(String.valueOf(casetmp.getPatient_id()));
        illneses.setValue(requirements.returnIllnesWithImage(casetmp.getIllnes_id()));
        date.setValue(casetmp.getDate_of().toLocalDate());
        notes.setText(casetmp.getNotes());
        }
        else {
        cases c=requirements.returnCaseWithDescription(idofCase.getNumber());
        id.setText(String.valueOf(c.getId()));
        pateint_id.setText(String.valueOf(c.getPatient_id()));
        session_id.setText(String.valueOf(c.getPatient_session_id()));
        illneses.setValue(requirements.returnIllnesWithImage(c.getIllnes_id()));
        date.setValue(c.getDate_of().toLocalDate());
        notes.setText(c.getNotes());
        }
        date.setEditable(false);
        notes.setEditable(false);
        done.setVisible(false);
        session_id.setEditable(false);
        pateint_id.setEditable(false);
    }

    @FXML
    private void goBack(ActionEvent event) {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(oldScene);
         window.show();
    }

    @FXML
    private void onIllnessViewClick(ActionEvent event) {
        
          try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("illnessUI.fxml"));
            Parent illnessUIparent = loader.load();
            Scene optionsUI = new Scene(illnessUIparent);
            
            //access the controller and call a method
            IllnessUIController controller = loader.getController();
            controller.initOldValues(illneses.getValue().getId(), ((Node)event.getSource()).getScene(),null);
            controller.detailMode();
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(optionsUI);
            window.show();
        } catch (IOException ex) {
            logger.appendnewLog(ex.getMessage());
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
