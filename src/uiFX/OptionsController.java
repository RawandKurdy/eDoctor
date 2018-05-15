/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package uiFX;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import resources.appointment;
import resources.doctor;
import resources.patient;
import resources.receptionist;
import resources.requirements;

/**
 * FXML Controller class
 *
 * @author rawan
 */
public class OptionsController implements Initializable {

    @FXML
    private Color x2;
    @FXML
    private Font x1;
    @FXML
    private Label nameofDoctor;
    @FXML
    private Color x21;
    @FXML
    private Font x11;
    @FXML
    private TableView<appointment> appointmentsTable;
    @FXML
    private TableColumn<appointment, Integer> appointment_Id;
    @FXML
    private TableColumn<appointment, Date> date_appointment;
    @FXML
    private TableColumn<appointment, Integer> patient_id_appointment;
    
 
    @FXML
    private TableView<?> sessionsTable;
    @FXML
    private TableView<?> receptionistTable;
    

    @FXML
    private TableView<patient> patientsTable;
    @FXML
    private TableColumn<patient, Integer> patient_id;
    @FXML
    private TableColumn<patient, String> patient_firstname;
    @FXML
    private TableColumn<patient, String> patient_lastname;
    @FXML
    private TableColumn<patient, String> patient_gender;
    @FXML
    private TableColumn<patient, Date> patient_dateofbirth;
    
    
    
    
    //Either a doctor or a receptionist uses this app
    doctor loggedDoctor;
    receptionist loggedreceptionist;
    @FXML
    private Button TodaysOnlyAppointments;
    @FXML
    private Button ViewDetails_Appointment;
    @FXML
    private Button AddAppointment;
    @FXML
    private Button deleteAppointment;
    @FXML
    private Button editAppointment;
    //Receives PARAMETER from previous scene
    public void initLoggedUser(doctor a ,receptionist b){
    loggedDoctor=a;
        System.out.println(loggedDoctor);
    loggedreceptionist=b;
        System.out.println(loggedreceptionist);
        appointmentsTable.setItems(getAppointments());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up the columns in the appointment table
        appointment_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date_appointment.setCellValueFactory(new PropertyValueFactory<>("date"));
        patient_id_appointment.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        appointmentsTable.setItems(getAppointments());
        
        //set up the columns in the patients table
        patient_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        patient_firstname.setCellValueFactory(new PropertyValueFactory<>("first_Name"));
        patient_lastname.setCellValueFactory(new PropertyValueFactory<>("last_Name"));
        patient_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        patient_dateofbirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        patientsTable.setItems(getPatients());
        
    }

    //Gets the Appointments based on criterias
    public ObservableList<appointment>  getAppointments()
    {
        ObservableList<appointment> appointments = FXCollections.observableArrayList();
        if(loggedDoctor!=null){
            nameofDoctor.setText(loggedDoctor.getFirst_Name());
        appointments.addAll(resources.requirements.returnAllAppointmentUsingDoctorID(loggedDoctor.getId()));
        }
        else if(loggedreceptionist!=null){
            nameofDoctor.setText(loggedreceptionist.getUsername());
        appointments.addAll(resources.requirements.returnAllAppointment());
        }
        
        return appointments;
}    
    
     //Gets the patients
    public ObservableList<patient>  getPatients()
    {
        ObservableList<patient> patients = FXCollections.observableArrayList();
        patients.addAll(requirements.returnAllPatient());
        return patients;
}  

    @FXML
    private void showTodaysOnlyAppointments(ActionEvent event) {
        Date today=Date.valueOf(LocalDate.now());
        System.out.println(today);
        //TODO
    }
    
    @FXML
    private void onViewDetailsAppointment(ActionEvent event) {
    }

    @FXML
    private void onAddAppointment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddAppointment.fxml"));
        Parent addAppointmentParent =loader.load();
        Scene addAppointmentScene = new Scene(addAppointmentParent);
        AddAppointmentController controller=loader.getController();
        controller.initOldSceneAndObservable(((Node)event.getSource()).getScene(),appointmentsTable.getItems(),loggedDoctor);
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(addAppointmentScene);
        window.show();
    }

    @FXML
    private void onDeleteAppointment(ActionEvent event) {
        
        ObservableList<appointment>  allAppointments=appointmentsTable.getItems();
        appointment tmp= appointmentsTable.getSelectionModel().getSelectedItem();
        if(requirements.deleteFromAppointment(tmp.getId()))
            allAppointments.remove(tmp);
        

    }

    @FXML
    private void onEditAppointment(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddAppointment.fxml"));
        Parent addAppointmentParent =loader.load();
        Scene addAppointmentScene = new Scene(addAppointmentParent);
        AddAppointmentController controller=loader.getController();
        controller.initOldSceneAndObservable(((Node)event.getSource()).getScene(),appointmentsTable.getItems(),loggedDoctor,appointmentsTable.getSelectionModel().getSelectedItem());
        controller.applyUpdateTheme();
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(addAppointmentScene);
        window.show();
    }
    
}
