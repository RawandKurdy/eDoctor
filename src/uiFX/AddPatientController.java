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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

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
    private ChoiceBox<?> gender;
    @FXML
    private Button register;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    

    @FXML
    private void Submit(ActionEvent event) {
    }

    @FXML
    private void goBack(ActionEvent event) {
    }
    
}
