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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import resources.alerts;
import resources.patient;
import resources.requirements;

/**
 * FXML Controller class
 *
 * @author rawan
 */
public class AddPatientController implements Initializable {

    @FXML
    private JFXTextField f_name;
    @FXML
    private JFXTextField l_name;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField phoneno;
    @FXML
    private JFXTextArea info;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private ChoiceBox<String> gender;
    @FXML
    private Button register;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    
    //Variables
    Scene oldScene;
    patient old,updated;
    ObservableList<patient>  patients;
    @FXML
    private JFXTextField username;
    @FXML
    private Label title_label;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> genders=new ArrayList<>( Arrays.asList("Male","Female"));
        
        ObservableList<String> list = FXCollections.observableArrayList(genders);
        gender.setItems(list);
      
    }

public void initializeVariables(Scene sc,patient old,ObservableList<patient> list){

    this.oldScene=sc;
    this.old=old;
    this.patients=list;
}
public void initializeVariables(Scene sc,ObservableList<patient> list){
    initializeVariables(sc, null, list);
}
public  void updateMode(String mode){
title_label.setText(mode);
f_name.setText(old.getFirst_Name());
l_name.setText(old.getLast_Name());
gender.setValue(old.getGender());
dob.setValue(old.getDateOfBirth().toLocalDate());
email.setText(old.getEmail());
address.setText(old.getAddress());
phoneno.setText(old.getPhone_Number());
info.setText(old.getInfo());
username.setText(old.getUser_Name());

}

    @FXML
    private void Submit(ActionEvent event) {
        updated=new patient();
        updated.setFirst_Name(f_name.getText());
        updated.setLast_Name(l_name.getText());
        updated.setGender(gender.getValue());
        updated.setDateOfBirth(Date.valueOf(dob.getValue()));
        updated.setEmail(email.getText());
        updated.setAddress(address.getText());
        updated.setPhone_Number(phoneno.getText());
        updated.setInfo(info.getText());
        updated.setPassword(info.getText());
        updated.setUser_Name(username.getText());
        if(old==null){
        //means insertion nothing special
        resources.req_info inserted=requirements.insertToPatient(updated);
        if(inserted.isInserted()){
            updated.setId(inserted.getId());
        patients.add(updated);
            goToPrev(event);
        }
        
        else
            alerts.warningMSG("Failed to Insert please check the log");
        
        }
        else{
        //means an Update is in process
        updated.setId(old.getId());
        if(requirements.updatePatient(updated,old)){
        patients.remove(old);
        patients.add(updated);
            goToPrev(event);
        }
        else
            alerts.warningMSG("Failed to update please check the log");
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        goToPrev(event);
    }
    
    public void goToPrev(ActionEvent e){
    Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
         window.setScene(oldScene);
         window.show();}

public void detailsmode(){
    updateMode(old.getFirst_Name()+"'s Info");
    register.setVisible(false);
    f_name.setEditable(false);
    l_name.setEditable(false);
    address.setEditable(false);
    dob.setEditable(false);
    email.setEditable(false);
    info.setEditable(false);
    password.setEditable(false);
    phoneno.setEditable(false);
    username.setEditable(false);
    

}
}
