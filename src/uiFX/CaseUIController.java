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
import java.util.ArrayList;
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
import resources.cases;
import resources.logger;
import resources.patient_session;
import resources.requirements;

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
    int idofCase;
    Scene oldScene;
    Button setButtonOfOldFrame;
    Button ViewButtonOfOldFrame;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setText(String.valueOf(requirements.getCountforTable(cases.Table_Name)+1));
        id.setEditable(false);
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
       
    }
    public void initoldValues(Scene s,int idofCase,Button set,Button view){
        oldScene=s;
        this.idofCase=idofCase;
        setButtonOfOldFrame=set;
        ViewButtonOfOldFrame=view;
    }
    
    public void detailsMode(){
        cases c=requirements.returnCaseWithDescription(idofCase);
        id.setText(String.valueOf(c.getId()));
        pateint_id.setText(String.valueOf(c.getPatient_id()));
        pateint_id.setEditable(false);
        session_id.setText(String.valueOf(c.getPatient_session_id()));
        session_id.setEditable(false);
        illneses.setValue(requirements.returnIllnesWithImage(c.getIllnes_id()));
        date.setValue(c.getDate_of().toLocalDate());
        date.setEditable(false);
        notes.setText(c.getNotes());
        notes.setEditable(false);
        done.setVisible(false);
        
    
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
            controller.initOldValues(illneses.getSelectionModel().getSelectedItem().getId(), ((Node)event.getSource()).getScene(),null);
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
