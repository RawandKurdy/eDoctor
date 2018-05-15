/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package uiFX;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import resources.appointment;
import resources.doctor;
import resources.requirements;

/**
 * FXML Controller class
 *
 * @author rawan
 */
public class AddAppointmentController implements Initializable {

    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField pateint_id;
    @FXML
    private JFXTextField doctor_id;
    @FXML
    private JFXDatePicker date;
    @FXML
    private Button done;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setText(String.valueOf(requirements.getCountforTable(appointment.Table_Name)+1));
        
    }    

    @FXML
    private void submit(ActionEvent event) {
        appointment tmp=new appointment(Integer.valueOf(id.getText()),Integer.valueOf(pateint_id.getText()) , Integer.valueOf(doctor_id.getText()), Date.valueOf(date.getValue()));
        if(old==null){
        //means insertion nothing special
        if(requirements.insertToAppointment(tmp).isInserted()){
        allAppointments.add(tmp);
            goToPrev(event);
        }
        }
        else{
        //means an Update is in process
        if(requirements.updateAppointment(tmp,old)){
        allAppointments.remove(old);
        allAppointments.add(tmp);
            goToPrev(event);
        }
        }
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        goToPrev(event);
       }
    
    //if doctor was logged
    doctor doctor;
   @FXML
    private void updateDocID(MouseEvent event) {
        //Automatically Enters Logged Doctor_ID
   if(doctor!=null)
    doctor_id.setText(String.valueOf(doctor.getId()));
    }
    
    //From Old Scene
    ObservableList<appointment>  allAppointments;
    
     //To Return Back to the Old Scene
    Scene oldScene;
    public void initOldSceneAndObservable(Scene s,ObservableList<appointment>  allAppointments,doctor doc,appointment old){
    oldScene=s;
    this.allAppointments=allAppointments;
    doctor=doc;
    this.old=old;}
    
    public void initOldSceneAndObservable(Scene s,ObservableList<appointment>  allAppointments,doctor doc){
        initOldSceneAndObservable(s, allAppointments, doc, null);}
    
    public void goToPrev(ActionEvent e){
    Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
         window.setScene(oldScene);
         window.show();}
    
    
    //appointment (old)
    appointment old;
    public void applyUpdateTheme(){
    done.setText("UPDATE");
    id.setText(String.valueOf(old.getId()));
    pateint_id.setText(String.valueOf(old.getPatient_id()));
    doctor_id.setText(String.valueOf(old.getDoctor_id()));
    date.setValue(old.getDate().toLocalDate());
    }

   
}
