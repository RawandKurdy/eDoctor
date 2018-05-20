/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package uiFX;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import resources.alerts;
import resources.appointment;
import resources.cases;
import resources.logger;
import resources.patient_session;
import resources.req_info;
import resources.requirements;
import resources.validation;
import resources.valueHolder;

/**
 * FXML Controller class
 *
 * @author rawan
 */
public class SessionUIController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField appointment_id;
    @FXML
    private JFXTextField duration;
    @FXML
    private Button done;
    @FXML
    private Button back;
    @FXML
    private JFXButton createPres;
    @FXML
    private JFXButton viewPresc;
    @FXML
    private JFXButton createCase;
    @FXML
    private JFXButton viewCase;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField cost;

    //variables
    int idofSession;
    boolean safe;
    Scene oldScene;
    LocalTime start;
    LocalTime finish;
    valueHolder idofPrescription;
    valueHolder idofCase;
    cases casetmp;
    appointment appointment;
    ObservableList<patient_session> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idofPrescription = new valueHolder();
        idofCase = new valueHolder();
        id.setText(String.valueOf(requirements.getCountforTable(patient_session.Table_Name) + 1));
        id.setEditable(false);
        idofPrescription.setNumber(0);
        idofCase.setNumber(0);
        viewCase.setDisable(true);
        viewPresc.setDisable(true);
        casetmp = new cases();
        start = LocalTime.now();
        safe = false;
        appointment_id.setEditable(false);
        duration.setEditable(false);
    }

    public void initOldValues(appointment app, Scene oldScene, ObservableList<patient_session> list) {
        initOldValues(app, oldScene, list, 0);
    }

    public void initOldValues(appointment app, Scene oldScene, ObservableList<patient_session> list, int session_id) {
        appointment = app;
        this.oldScene = oldScene;
        this.list = list;
        idofSession = session_id;
    }

    public void initOldValues(Scene oldScene, int session_id) {

        initOldValues(null, oldScene, null, session_id);

    }

    @FXML
    private void submit(ActionEvent event) {
        finish = LocalTime.now();

        String durationInString = requirements.caculateDuration(start, finish);

        if (validator()) {

            patient_session tmp = new patient_session(0, appointment.getId(), durationInString,
                    idofPrescription.getNumber(), Double.valueOf(cost.getText()), description.getText());
            req_info test = requirements.insertToPatient_Session(tmp);
            if (test.isInserted()) {
                safe = true;

                if (casetmp.getId() == -5) { //-5 means its in the buffer
                    casetmp.setId(0);
                    casetmp.setPatient_session_id(test.getId());
                    casetmp.setPatient_id(appointment.getPatient_id());
                    req_info test2 = requirements.insertToCases(casetmp);
                    if (test2.isInserted()) {
                        idofCase.setNumber(test2.getId()); // no reason
                    } else {
                        logger.appendnewLog("Failed to insert a case");
                    }

                }
                tmp.setId(test.getId());
                list.add(tmp);
                goBack(event);
            } else {
                alerts.warningMSG("Failed to insert session");
                logger.appendnewLog("Failed to insert into session");
            }

        }

    }

    public boolean validator() {

        boolean validation = true;

        validation test = new validation();

        if (idofPrescription.getNumber() == 0) {
            validation &= false;
            logger.appendnewLog("empty input have been detected for prescription field");
            test.errormsg += "Prescription have not been created \n";
        }
        validation &= test.isNotNullandEmpty("Description", description.getText());
        validation &= test.isItaNum("Cost", cost.getText());

        if (validation) {
            return validation;
        } else {
            alerts.warningMSG(test.errormsg);
            return validation;
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        if (!safe) {
            if (idofPrescription.getNumber() != 0) {
                requirements.deleteFromPrescription(idofPrescription.getNumber());
            }
        }
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(oldScene);
        window.show();
    }

    public void detailMode() {
        patient_session session = requirements.returnPatientSession(idofSession);
        id.setText(String.valueOf(session.getId()));
        appointment_id.setText(String.valueOf(session.getAppointment_id()));
        appointment_id.setEditable(false);
        duration.setText(session.getDuration());
        duration.setEditable(false);
        idofPrescription.setNumber(session.getPrescription_id());
        idofCase.setNumber(requirements.getIDofCaseBySession(session.getId()));
        description.setText(session.getDescription());
        description.setEditable(false);
        done.setVisible(false);
        createCase.setDisable(true);
        createPres.setDisable(true);
        viewCase.setDisable(false);
        viewPresc.setDisable(false);
        cost.setText(String.valueOf(session.getCost()));
        cost.setEditable(false);
        if (idofCase.getNumber() == 0) {
            viewCase.setDisable(true);
        }
        safe=true;
    }

    @FXML
    private void onCreatePres(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("prescriptionUI.fxml"));
            Parent PrescriptionUIparent = loader.load();
            Scene PrescriptionUI = new Scene(PrescriptionUIparent);

            //access the controller and call a method
            PrescriptionUIController controller = loader.getController();
            controller.initOldvalues(idofPrescription, ((Node) event.getSource()).getScene(), createPres, viewPresc);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(PrescriptionUI);
            window.show();
        } catch (IOException ex) {
            logger.appendnewLog(ex.getMessage());
        }

    }

    @FXML
    private void onViewPresc(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("prescriptionUI.fxml"));
            Parent PrescriptionUIparent = loader.load();
            Scene PrescriptionUI = new Scene(PrescriptionUIparent);

            //access the controller and call a method
            PrescriptionUIController controller = loader.getController();
            controller.initOldvalues(idofPrescription, ((Node) event.getSource()).getScene(), createPres, viewPresc);
            controller.detailMode();

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(PrescriptionUI);
            window.show();
        } catch (IOException ex) {
            logger.appendnewLog(ex.getMessage());
        }

    }

    @FXML
    private void onCreateCase(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("caseUI.fxml"));
            Parent CaseUIparent = loader.load();
            Scene CaseUI = new Scene(CaseUIparent);

            //access the controller and call a method
            CaseUIController controller = loader.getController();
            controller.initoldValues(((Node) event.getSource()).getScene(), idofCase, createCase, viewCase, casetmp);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(CaseUI);
            window.show();
        } catch (IOException ex) {
            logger.appendnewLog(ex.getMessage());
        }

    }

    @FXML
    private void onViewCase(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("caseUI.fxml"));
            Parent CaseUIparent = loader.load();
            Scene CaseUI = new Scene(CaseUIparent);

            //access the controller and call a method
            CaseUIController controller = loader.getController();
            controller.initoldValues(((Node) event.getSource()).getScene(), idofCase, createCase, viewCase, casetmp);
            controller.detailsMode();

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(CaseUI);
            window.show();
        } catch (IOException ex) {
            logger.appendnewLog(ex.getMessage());
        }
    }

}
