/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package uiFX;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import resources.Illneses;
import resources.OSValidator;
import resources.alerts;
import resources.appointment;
import resources.dbUtility;
import resources.doctor;
import resources.logger;
import resources.patient;
import resources.patient_session;
import resources.receptionist;
import resources.req_info;
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
    
    @FXML
    private TableView<patient_session> sessionsTable;
    @FXML
    private TableColumn<patient_session, Integer> patient_session_id;
    @FXML
    private TableColumn<patient_session, Integer> patient_session_appointment_id;
    @FXML
    private TableColumn<patient_session, String> patient_session_duration;
    @FXML
    private TableColumn<patient_session, Integer> patient_session_prescription_id;
    @FXML
    private TableColumn<patient_session, Double> patient_session_cost;
    
    @FXML
    private TableView<receptionist> receptionistTable;
    @FXML
    private TableColumn<receptionist, Integer> receptionist_id;
    @FXML
    private TableColumn<receptionist, String> receptionist_firstname;
    @FXML
    private TableColumn<receptionist, String> receptionist_lastname;
    @FXML
    private TableColumn<receptionist, String> receptionist_gender;
    @FXML
    private TableColumn<receptionist, Date> receptionist_dateofbirth;
    @FXML
    private TableColumn<receptionist, Boolean> receptionist_state;
    
    @FXML
    private TableView<Illneses> IllnesesTable;
    @FXML
    private TableColumn<Illneses, Integer> illness_id;
    @FXML
    private TableColumn<Illneses, String> Illness_name;
    @FXML
    private TableColumn<Illneses, String> illness_description;
    @FXML
    private TableColumn<Illneses, String> illness_doctor_type;
    
    //Either a doctor or a receptionist uses this app
    doctor loggedDoctor;
    receptionist loggedreceptionist;
    Scene oldScene;
    private TextField username,password;
    
    @FXML
    private Menu backupmenu;
    @FXML
    private Button ViewDetails_Appointment;
    @FXML
    private Button AddAppointment;
    @FXML
    private Button deleteAppointment;
    @FXML
    private Button editAppointment;
    @FXML
    private Button add_patient;
    @FXML
    private Button edit_patient;
    @FXML
    private Button delete_patient;
    @FXML
    private Button details_patient;
    @FXML
    private Button add_PatientSession;
    @FXML
    private Button delete_PatientSession;
    @FXML
    private Button showDetail_PatientSession;
    @FXML
    private Button add_receptionist;
    @FXML
    private Button edit_receptionist;
    @FXML
    private Button delete_receptionist;
    @FXML
    private Button details_receptionist;
    @FXML
    private Button add_illness;
    @FXML
    private Button delete_Illness;
    @FXML
    private Button showDetail_Illness;
    @FXML
    private Button edit_PatientSession;
    @FXML
    private Button update_illness;
    @FXML
    private Button Start_Appointment;
    @FXML
    private Menu filemenu;
    @FXML
    private Menu actionsmenu;
    @FXML
    private MenuItem RequestedAppointments_info;
    @FXML
    private MenuItem logout_MenuItem;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Tab appointmentTab;
    @FXML
    private Tab patientsTab;
    @FXML
    private Tab sessionTab;
    @FXML
    private Tab receptionistTab;
    @FXML
    private Tab illnessTab;
    
    /**Receives PARAMETER from previous scene
     * 
     * @param a
     * @param b
     * @param oldScene
     * @param username
     * @param password 
     */
    public void initLoggedUser(doctor a ,receptionist b,Scene oldScene, TextField username,TextField password){
    loggedDoctor=a;
        System.out.println(loggedDoctor);
    loggedreceptionist=b;
        System.out.println(loggedreceptionist);
        appointmentsTable.setItems(getAppointments());
       this.oldScene=oldScene;
       this.username=username;
       this.password=password;
    }
    /**reset login details of the previous scene
     * 
     */
    private void resetloginDetails(){
    username.setText("");
    password.setText("");
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
        
        //setting the columns of  patient session table
        patient_session_appointment_id.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        patient_session_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        patient_session_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        patient_session_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        patient_session_prescription_id.setCellValueFactory(new PropertyValueFactory<>("prescription_id"));
        sessionsTable.setItems(getPatientSessions());
        
        //setting up the columns for receptionist
        receptionist_dateofbirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        receptionist_firstname.setCellValueFactory(new PropertyValueFactory<>("first_Name"));
        receptionist_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        receptionist_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        receptionist_lastname.setCellValueFactory(new PropertyValueFactory<>("last_Name"));
        receptionist_state.setCellValueFactory(new PropertyValueFactory<>("discharged"));
        receptionistTable.setItems(getReceptionists());
        
        //setting up the columns for illness table
        Illness_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        illness_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        illness_doctor_type.setCellValueFactory(new PropertyValueFactory<>("doctor_type"));
        illness_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        IllnesesTable.setItems(getIllneses());
        
        //Disabled Buttons
        add_PatientSession.setDisable(true);
        edit_PatientSession.setDisable(true);
        
        update_illness.setDisable(true);
        
                
        
       
    }

    /**Gets the Appointments based on criteria
     * 
     * @return 
     */
    public ObservableList<appointment>  getAppointments()
    {
        ObservableList<appointment> appointments = FXCollections.observableArrayList();
        if(loggedDoctor!=null){
            nameofDoctor.setText("Dr."+loggedDoctor.getFirst_Name());
        appointments.addAll(resources.requirements.returnAllAppointmentUsingDoctorID(loggedDoctor.getId(),appointment.Table_Name));
        }
        else if(loggedreceptionist!=null){
            nameofDoctor.setText("Recep."+loggedreceptionist.getUsername());
        appointments.addAll(resources.requirements.returnAllAppointment(appointment.Table_Name));
        }
        
        return appointments;
}    
    
     /**Gets the patients
      * 
      * @return 
      */
    public ObservableList<patient>  getPatients()
    {
        ObservableList<patient> patients = FXCollections.observableArrayList();
        patients.addAll(requirements.returnAllPatient());
        return patients;
}  
    
    /**gets the patient sessions
     * 
     * @return 
     */
    public ObservableList<patient_session> getPatientSessions(){
    ObservableList<patient_session> list=FXCollections.observableArrayList();
    list.addAll(requirements.returnAllPatientSessions());
    return list;
    }
    
    /**gets the receptionists
     * 
     * @return 
     */
    public ObservableList<receptionist> getReceptionists(){
    ObservableList<receptionist> list = FXCollections.observableArrayList();
    list.addAll(requirements.returnAllReceptionist());
    return list;
    }
    
    /**gets the illneses
     * 
     * @return 
     */
    public ObservableList<Illneses> getIllneses(){
    ObservableList<Illneses> list =FXCollections.observableArrayList();
    list.addAll(requirements.returnAllillneses());
    return list;
    }
    
    /**Viewing detail of an appointment
     * 
     * @param event 
     */
    @FXML
    private void onViewDetailsAppointment(ActionEvent event) {
        
        
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("uiFX/AppointmentUI.fxml"));
            Parent AppointmentUIParent =loader.load();
            Scene AppointmentUIScene = new Scene(AppointmentUIParent);
            AppointmentUIController controller=loader.getController();
            controller.initOldSceneAndObservable(((Node)event.getSource()).getScene(),appointmentsTable.getItems(),loggedDoctor,appointmentsTable.getSelectionModel().getSelectedItem());
            controller.applyDetailsTheme();
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(AppointmentUIScene);
            window.show();
        } catch (IOException ex) {
            resources.logger.appendnewLog(ex.getMessage());
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**adding an appointment
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void onAddAppointment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("uiFX/AppointmentUI.fxml"));
        Parent AppointmentUIParent =loader.load();
        Scene AppointmentUIScene = new Scene(AppointmentUIParent);
        AppointmentUIController controller=loader.getController();
        controller.initOldSceneAndObservable(((Node)event.getSource()).getScene(),appointmentsTable.getItems(),loggedDoctor);
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(AppointmentUIScene);
        window.show();
    }

    /**deleting an appointment
     * 
     * @param event 
     */
    @FXML
    private void onDeleteAppointment(ActionEvent event) {
        
        ObservableList<appointment>  allAppointments=appointmentsTable.getItems();
        appointment tmp= appointmentsTable.getSelectionModel().getSelectedItem();
         if(alerts.confirmationDialogDelete().get()==ButtonType.OK){
        if(requirements.deleteFromAppointment(tmp.getId()))
            allAppointments.remove(tmp);
         else
            alerts.warningMSG("Failed to Delete please check the log");
        
        }
        else{
        //do nothing
        }
        

    }

    /**editing an appointment
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void onEditAppointment(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("uiFX/AppointmentUI.fxml"));
        Parent AppointmentUIParent =loader.load();
        Scene AppointmentUIScene = new Scene(AppointmentUIParent);
        AppointmentUIController controller=loader.getController();
        controller.initOldSceneAndObservable(((Node)event.getSource()).getScene(),appointmentsTable.getItems(),loggedDoctor,appointmentsTable.getSelectionModel().getSelectedItem());
        controller.applyUpdateTheme();
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(AppointmentUIScene);
        window.show();
    }
    

    /**adding a patient
     * 
     * @param event 
     */
    @FXML
    private void addpatient(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("uiFX/patientUI.fxml"));
            Parent patientUIParent =loader.load();
            Scene patientUIScene = new Scene(patientUIParent);
            patientUIController controller=loader.getController();
            controller.initializeVariables(((Node)event.getSource()).getScene(), patientsTable.getItems());
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(patientUIScene);
            window.show();
        } catch (IOException ex) {
            resources.logger.appendnewLog(ex.getMessage());
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**editing a patient
     * 
     * @param event 
     */
    @FXML
    private void editpatient(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("uiFX/patientUI.fxml"));
            Parent patientUIParent =loader.load();
            Scene patientUIScene = new Scene(patientUIParent);
            patientUIController controller=loader.getController();
            controller.initializeVariables(((Node)event.getSource()).getScene(),
                    patientsTable.getSelectionModel().getSelectedItem(), patientsTable.getItems());
            controller.updateMode("Patient Update");
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(patientUIScene);
            window.show();
        } catch (IOException ex) {
            resources.logger.appendnewLog(ex.getMessage());
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**deleting a patient
     * 
     * @param event 
     */
    @FXML
    private void deletepatient(ActionEvent event) {
           ObservableList<patient>  allpatients=patientsTable.getItems();
        patient tmp= patientsTable.getSelectionModel().getSelectedItem();
        if(alerts.confirmationDialogDelete().get()==ButtonType.OK){
        if(requirements.deleteFromPatient(tmp.getId()))
            allpatients.remove(tmp);
        else
            alerts.warningMSG("Failed to Delete please check the log");
        
        }
        else{
        //do nothing
        }
    }

    /**view the details of patient
     * 
     * @param event 
     */
    @FXML
    private void detailspatient(ActionEvent event) {
        
            try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("uiFX/patientUI.fxml"));
            Parent patientUIParent =loader.load();
            Scene patientUIScene = new Scene(patientUIParent);
            patientUIController controller=loader.getController();
            controller.initializeVariables(((Node)event.getSource()).getScene(),
                    patientsTable.getSelectionModel().getSelectedItem(), patientsTable.getItems());
            controller.detailsmode();
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(patientUIScene);
            window.show();
        } catch (IOException ex) {
            resources.logger.appendnewLog(ex.getMessage());
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**logout of the application
     * 
     * @param event 
     */
    @FXML
    private void logout(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) menuBar).getScene().getWindow();
        resetloginDetails();
         window.setScene(oldScene);
         window.show();
        } catch (Exception e) {
            logger.appendnewLog(e.getMessage());
            alerts.warningMSG("Logout error ,check log");
        }
    }

    /**exits or terminates the application
     * 
     * @param event 
     */
    @FXML
    private void exit(ActionEvent event) {
        logger.appendnewLog("Application has been terminated through the exit menu");
        System.exit(0);
    }

    /**exports the current db
     * 
     * @param event 
     */
    @FXML
    private void exportBackup(ActionEvent event) {
        if(OSValidator.isWindows()){
        String path=alerts.DirectoryChooser();
        if(!path.equals("")){
            if(dbUtility.exportDB(path)){
            alerts.msg("Database Utility", "Export Done", "Check Log for more Info", Alert.AlertType.INFORMATION);
            }
            else{
                alerts.warningMSG("Failed to Export Database ,Check log");
            
            }
        }
    }
        else
        alerts.warningMSG("This action is not supported on "+OSValidator.getOS());

    }

    /**imports a db
     * 
     * @param event 
     */
    @FXML
    private void ImportBackup(ActionEvent event) {
        if(OSValidator.isWindows()){
         String path=alerts.sqlfileChooser();
        if(!path.equals("")){
            if(dbUtility.importDB(path)){
            alerts.msg("Database Utility", "Import Done", "Restart the app to take effect \n Check Log for more Info", Alert.AlertType.INFORMATION);
            }
            else{
                alerts.warningMSG("Failed to Import Database ,Check Log");
            
            }
        }
        }
        else
        alerts.warningMSG("This action is not supported on "+OSValidator.getOS());
    }

    /**shows the about of the application
     * 
     * @param event 
     */
    @FXML
    private void about(ActionEvent event) {
        alerts.msg("About", strUI.appname, strUI.appabout, Alert.AlertType.INFORMATION);
    }

    /**adds a patient session
     * not implemented
     * @param event 
     */
    @FXML
    private void addPatientSession(ActionEvent event) {
        //Not Implemented 
        //no reason to have
    }

    /**deletes a patient session
     * 
     * @param event 
     */
    @FXML
    private void deletePatientSession(ActionEvent event) {
        
        
        ObservableList<patient_session>  list=sessionsTable.getItems();
        patient_session tmp= sessionsTable.getSelectionModel().getSelectedItem();
         if(alerts.confirmationDialogDelete().get()==ButtonType.OK){
        if(requirements.deleteFromPatient_Session(tmp.getId()))
            list.remove(tmp);
         else
            alerts.warningMSG("Failed to Delete please check the log");
        
        }
        else{
        //do nothing
        }
        

    
    }

    /**shows the details of a patient session
     * 
     * @param event 
     */
    @FXML
    private void showDetailsPatientSession(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("uiFX/sessionUI.fxml"));
            Parent SessionUIparent = loader.load();
            Scene SessionUI = new Scene(SessionUIparent);
            
            //access the controller and call a method
            SessionUIController controller = loader.getController();
            controller.initOldValues(((Node)event.getSource()).getScene(), sessionsTable.getSelectionModel().getSelectedItem().getId());
            controller.detailMode();
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(SessionUI);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    /**adds a receptionist
     * 
     * @param event 
     */
    @FXML
    private void addReceptionist(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("uiFX/receptionist.fxml"));
            Parent RecepUIparent = loader.load();
            Scene RecepUI = new Scene(RecepUIparent);
            
            //access the controller and call a method
            ReceptionistController controller = loader.getController();
            controller.initOldcomponents(receptionistTable.getItems(), null,((Node)event.getSource()).getScene());
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(RecepUI);
            window.show();
        } catch (IOException ex) {
            logger.appendnewLog(ex.getMessage());
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**to edit a receptionist
     * 
     * @param event 
     */
    @FXML
    private void editReceptionist(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("uiFX/receptionist.fxml"));
            Parent RecepUIparent = loader.load();
            Scene RecepUI = new Scene(RecepUIparent);
            
            //access the controller and call a method
            ReceptionistController controller = loader.getController();
            controller.initOldcomponents(receptionistTable.getItems(), receptionistTable.getSelectionModel().getSelectedItem(),((Node)event.getSource()).getScene());
            controller.updateMode();
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(RecepUI);
            window.show();
        } catch (IOException ex) {
            logger.appendnewLog(ex.getMessage());
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**deletes a receptionist
     * 
     * @param event 
     */
    @FXML
    private void deleteReceptionist(ActionEvent event) {
        
        ObservableList<receptionist>  list=receptionistTable.getItems();
        receptionist tmp= receptionistTable.getSelectionModel().getSelectedItem();
         if(alerts.confirmationDialogDelete().get()==ButtonType.OK){
        if(requirements.deleteFromReceptionist(tmp.getId()))
            list.remove(tmp);
         else
            alerts.warningMSG("Failed to Delete please check the log");
        
        }
        else{
        //do nothing
        }
    }

    /**viewing the details of a receptionist
     * 
     * @param event 
     */
    @FXML
    private void detailsReceptionist(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("uiFX/receptionist.fxml"));
            Parent RecepUIparent = loader.load();
            Scene RecepUI = new Scene(RecepUIparent);
            
            //access the controller and call a method
            ReceptionistController controller = loader.getController();
            controller.initOldcomponents(receptionistTable.getItems(), receptionistTable.getSelectionModel().getSelectedItem(),((Node)event.getSource()).getScene());
            controller.updateMode();
            controller.detailsModeMod();
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(RecepUI);
            window.show();
        } catch (IOException ex) {
            logger.appendnewLog(ex.getMessage());
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**adds an illness
     * 
     * @param event 
     */
    @FXML
    private void addIllness(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("uiFX/illnessUI.fxml"));
            Parent illnessUIparent = loader.load();
            Scene optionsUI = new Scene(illnessUIparent);
            
            //access the controller and call a method
            IllnessUIController controller = loader.getController();
            controller.initOldValues(0, ((Node)event.getSource()).getScene(), IllnesesTable.getItems());
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(optionsUI);
            window.show();
        } catch (IOException ex) {
            logger.appendnewLog(ex.getMessage());
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**deletes an illness
     * 
     * @param event 
     */
    @FXML
    private void deleteIllness(ActionEvent event) {
        
        ObservableList<Illneses>  list=IllnesesTable.getItems();
        Illneses tmp= IllnesesTable.getSelectionModel().getSelectedItem();
         if(alerts.confirmationDialogDelete().get()==ButtonType.OK){
        if(requirements.deleteFromIllneses(tmp.getId()))
            list.remove(tmp);
         else
            alerts.warningMSG("Failed to Delete please check the log");
         }
        else{
        //do nothing
        }
    }
    
/**shows  the details of an illness
 * 
 * @param event 
 */
    @FXML
    private void showDetailsIllness(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("uiFX/illnessUI.fxml"));
            Parent illnessUIparent = loader.load();
            Scene optionsUI = new Scene(illnessUIparent);
            
            //access the controller and call a method
            IllnessUIController controller = loader.getController();
            controller.initOldValues(IllnesesTable.getSelectionModel().getSelectedItem().getId(), ((Node)event.getSource()).getScene(),null);
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

    /**updates a patient session
     * not implemented
     * @param event 
     */
    @FXML
    private void updatePatientSession(ActionEvent event) {
        //not implemented 
        //not practical
    }

    /**updates an illness 
     * not implemented
     * @param event 
     */
    @FXML
    private void UpdateIllness(ActionEvent event) {
        //not implemented
        //not needed
    }

    /**starts a patient session based on an appointment
     * 
     * @param event 
     */
    @FXML
    private void onStartAppointment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("uiFX/sessionUI.fxml"));
            Parent SessionUIparent = loader.load();
            Scene SessionUI = new Scene(SessionUIparent);
            
            //access the controller and call a method
            SessionUIController controller = loader.getController();
            controller.initOldValues(appointmentsTable.getSelectionModel().getSelectedItem(), ((Node)event.getSource()).getScene(),sessionsTable.getItems());
            
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(SessionUI);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(OptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**gets the requested appointments
     * used by the receptionist
     * @param event 
     */
    @FXML
    private void getRequestAppointments(ActionEvent event) {
        ArrayList<appointment> tmplist =requirements.returnAllAppointment("requested_appointment");
        ArrayList<appointment> list =new ArrayList<>();
        for (appointment object : tmplist) {
            if(object.getStatus()==false)
                list.add(object);
        }
        if(list.size()>0){
        
            List<appointment> choices = new ArrayList<>();
            choices.addAll(list);

            ChoiceDialog<appointment> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Requested Appointments");
            dialog.setHeaderText("Which appointments you would like to accept?");
            dialog.setContentText("Choose the appointment");

            // Traditional way to get the response value.
            Optional<appointment> result = dialog.showAndWait();
            if (result.isPresent()){
                appointment tmp=result.get();
                int oldid=tmp.getId();
                tmp.setId(0);
                req_info insert=requirements.insertToAppointment(tmp);
                if(insert.isInserted()){
                    requirements.updateReqAppointmentStatus(true,oldid );
                    tmp.setId(insert.getId());
                    appointmentsTable.getItems().add(tmp);
                alerts.msg("Appointment Requests", "Success", "The appointment has been accepted", Alert.AlertType.INFORMATION);
                
                }
                else{
                tmp.setId(oldid);
                logger.appendnewLog("Failed to accept appointment");
                alerts.warningMSG("Failed to accept Appointment ,Please check the log");
                
                }
                                  }
        
        }
        
        else{
        alerts.warningMSG("You dont have any requested appointments");
        
        }
        
    }
    
    /**disables the sections which are not allowed to access by
     * patient and/or receptionist
     */
    public void doctorOrRecep(){
    //disables and enables functionalities as user logged in
    if(loggedDoctor!=null || loggedreceptionist==null){
    RequestedAppointments_info.setDisable(true);
    
    }
    else if(loggedreceptionist!=null || loggedDoctor==null){
    backupmenu.setDisable(true);
    Start_Appointment.setDisable(true);
    sessionTab.setDisable(true);
    receptionistTab.setDisable(true);
    illnessTab.setDisable(true);
    }
    
    //not implemented currently (due to not need or usefulness)
    add_PatientSession.setDisable(true);
    edit_PatientSession.setDisable(true);
    update_illness.setDisable(true);
    }
    
}
