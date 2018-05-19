/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package uiFX;

import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import resources.alerts;
import resources.receptionist;
import resources.req_info;
import resources.requirements;
import resources.validation;

/**
 * FXML Controller class
 *
 * @author rawan
 */
public class ReceptionistController implements Initializable {

    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField f_name;
    @FXML
    private JFXTextField l_name;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private ChoiceBox<String> gender;
    @FXML
    private Button done;
    @FXML
    private Button back;
    @FXML
    private JFXTextField salary;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private CheckBox discharged;

     Scene oldScene;
     receptionist recep,old;
     ObservableList<receptionist> list;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          ArrayList<String> genders=new ArrayList<>( Arrays.asList("Male","Female"));
          ObservableList<String> list = FXCollections.observableArrayList(genders);
               gender.setItems(list);
               id.setText(String.valueOf(requirements.getCountforTable(receptionist.Table_Name)+1));
               id.setEditable(false);
    }    

    public void initOldcomponents(ObservableList<receptionist> list,receptionist old,Scene s){
    oldScene=s;
    this.old=old;
    this.list=list;
    }
    
    @FXML
    private void submit(ActionEvent event) {
        
        int x= old==null ? 0 : 1;
        
        if(validator(x)){
        recep =new receptionist();
        recep.setId(0);
        recep.setFirst_Name(f_name.getText());
        recep.setLast_Name(l_name.getText());
        recep.setPhone_Number(phone.getText());
        recep.setEmail(email.getText());
        recep.setDateOfBirth(Date.valueOf(dob.getValue()));
        recep.setGender(gender.getValue());
        recep.setSalary(Double.valueOf(salary.getText()));
        recep.setUsername(username.getText());
        recep.setPassword(password.getText());
        recep.setDischarged(discharged.isSelected());
        
        if(old==null){
            
            req_info req=requirements.insertToReceptionist(recep);
            if(req.isInserted()){
            recep.setId(req.getId());
            list.add(recep);
                goBack(event);
            }
            else
                alerts.warningMSG("Failed to Insert please check the log");
        }
        else{
            recep.setId(old.getId());
          if(requirements.updateReceptionist(recep, old)){
          list.remove(old);
          list.add(recep);
              goBack(event);
          }
          else
               alerts.warningMSG("Failed to update please check the log");

        
        }
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(oldScene);
         window.show();
    }
    
    public void updateMode(){
    if(old!=null){
    done.setText("Update");
    id.setText(String.valueOf(old.getId()));
    f_name.setText(old.getFirst_Name());
    l_name.setText(old.getLast_Name());
    phone.setText(old.getPhone_Number());
    email.setText(old.getEmail());
    dob.setValue(old.getDateOfBirth().toLocalDate());
    gender.setValue(old.getGender());
    salary.setText(String.valueOf(old.getSalary()));
    username.setText(old.getUsername());
    discharged.setSelected(old.isDischarged());
    
    
    }
    }
      public void detailsModeMod(){
      done.setVisible(false);
      f_name.setEditable(false);
      l_name.setEditable(false);
      dob.setEditable(false);
      email.setEditable(false);
      password.setEditable(false);
      username.setEditable(false);
      phone.setEditable(false);
      salary.setEditable(false);
    
    }
       public boolean validator(int x){
           //x == 1 means updating 
           //x == 0 means inserting
    
        boolean validation=true;
        
        validation test=new validation();
        
        validation&=test.isNotNullandEmpty("First Name", f_name.getText());
        validation&=test.isNotNullandEmpty("Last Name", l_name.getText());
        validation&=test.objectNullCheck("Date of Birth", dob.getValue());
        validation&=test.emailValidator("user email", email.getText());
        if(x==0)
            validation&=test.isNotNullandEmpty("Password", password.getText());
        validation&=test.isNotNullandEmpty("Username", username.getText());
        validation&=test.phoneNumberValidator("Phone No", phone.getText());
        validation&=test.isItaNum("Salary", salary.getText());
        validation&=test.isNotNullandEmpty("Gender", gender.getValue());
        
        if(validation)
            return validation;
        else{
        alerts.warningMSG(test.errormsg);
        return validation;
        }
    }
}
