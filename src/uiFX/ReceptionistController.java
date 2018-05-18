/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package uiFX;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

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
    private ChoiceBox<?> gender;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submit(ActionEvent event) {
    }

    @FXML
    private void goBack(ActionEvent event) {
    }
    
}
