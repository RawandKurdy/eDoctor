
package uiFX;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import resources.alerts;
import resources.doctor;
import resources.logger;
import resources.receptionist;
import resources.req_info;
import resources.requirements;
import resources.validation;

/**
 * FXML Controller class
 *
 * @author rawandkurdy
 */
public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    @FXML
    private Button register;
    @FXML
    private Label statues;
    //Statues Color & Font?!
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    
    //Doctors
    ArrayList<doctor> Docslist;
    
    //Receptionists
    ArrayList<receptionist> recpList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //First we check somethings to define either to enable register button or not
        if(priorchecks()){
        Docslist=requirements.returnAllDoctor();
        recpList=requirements.returnAllReceptionist();
        
        //Register Implemented //One Doctor only for now [Initialized at first Start]
        register.setDisable(true);
        }
        
    }    

    /**loges you in to the application
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void login(ActionEvent event) throws IOException {
        
        if(validator()){
     //if we have users in our system
     boolean found=false;
     boolean usernamedoesntexist=true;
     doctor doc = null;
     receptionist recep = null;
     
     if(Docslist.size()+recpList.size()>0){
         String username=this.username.getText();
         String password=requirements.MD5(this.password.getText());
         //first we start with Doctors list
         for (doctor object : Docslist) {
             if(object.getUser_Name().equalsIgnoreCase(username) ){
                 if(object.getPassword().equals(password)){
             found=true;
             usernamedoesntexist=false;
             doc=object;
             recep=null;
             break;}
                 else{
                     usernamedoesntexist=false;
             alerts.msg("Password", "Entered password is wrong","", AlertType.ERROR);
                 }
             }
            
         }
         //if didnt found it their we try the receptionist too
         if(!found){
           for (receptionist object : recpList) {
             if(object.getUsername().equalsIgnoreCase(username) ){
                 if(object.getPassword().equals(password)){
             recep=object;
             doc=null;
             found=true;
             usernamedoesntexist=false;
             
             //this detects if the receptionist account has been disabled or not
             if(recep.isDischarged()==true){
                 found=false;
             alerts.msg("Account Suspension", "Your Account have been disabled","Please Contact the Admins", AlertType.ERROR);
             }
             
             break;}
                     else{
                     usernamedoesntexist=false;
                     alerts.msg("Password", "Entered password is wrong","", AlertType.ERROR);
                 }
             }
            
         }
         }
         
         
      if(found){
      
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("options.fxml"));
        Parent optionsUIparent = loader.load();
        Scene optionsUI = new Scene(optionsUIparent);
        
        //access the controller and call a method
        OptionsController controller = loader.getController();
        controller.initLoggedUser(doc, recep,((Node)event.getSource()).getScene(),this.username,this.password);
        controller.doctorOrRecep();
      
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(optionsUI);
        window.show();
      }
     
     
     
     if(usernamedoesntexist){
         
       alerts.msg("Username", "Username Doesnt exist","Check your Username", AlertType.ERROR);
       
     }
     }
        }
     }

    /**registers a doctor 
     * used only at initial setup
     * currently multiple doctors are not implemented
     * @param event 
     */
    @FXML
    private void register(ActionEvent event) {
        if(initlizer())
            logger.appendnewLog("eDoctors have been setup successfully");
        else
            logger.appendnewLog("Failure in setting up eDoctors");
    }
    
    /**init eDoctors App
     * 
     * @return 
     */
    public boolean initlizer() {
        String contextTMP = "Please enter your new ";
        String[] columns = {"First Name", "Last Name", "Gender", "Date of Birth (YYYY-MM-DD)", "Email",
            "Phone Number", "Specialty", "Doctor type", "Username", "Password"};
        doctor newDoc = new doctor();
        newDoc.setId(1);

        validation validate = new validation();
        int columnsNo = 10;
        boolean columnIsSet = false;
        for (int x = 0; x < columnsNo; x++) {
            while (!columnIsSet) {
                if (x == 0) {
                    String in = alerts.inputDialog(columns[x], contextTMP + columns[x]);

                    if (validate.isNotNullandEmpty(columns[x], in)) {
                        newDoc.setFirst_Name(in);
                        columnIsSet = true;
                    } else {
                        alerts.warningMSG(columns[x] + " can not be empty");
                    }

                }

                if (x == 1) {
                    String in = alerts.inputDialog(columns[x], contextTMP + columns[x]);
                    if (validate.isNotNullandEmpty(columns[x], in)) {
                        newDoc.setLast_Name(in);
                        columnIsSet = true;
                    } else {
                        alerts.warningMSG(columns[x] + " can not be empty");
                    }
                }
                if (x == 2) {
                    String in = alerts.inputDialog(columns[x], contextTMP + columns[x]);
                    if (validate.isNotNullandEmpty(columns[x], in)) {
                        newDoc.setGender(in);
                        columnIsSet = true;
                    } else {
                        alerts.warningMSG(columns[x] + " can not be empty");
                    }

                }
                if (x == 3) {
                    String in = alerts.inputDialog(columns[x], contextTMP + columns[x]);
                    if (validate.isNotNullandEmpty(columns[x], in)) {
                        try {
                            newDoc.setDateOfBirth(Date.valueOf(in));
                            columnIsSet = true;
                        } catch (IllegalArgumentException e) {
                            alerts.warningMSG(columns[x] + " wrong input");
                        }

                    } else {
                        alerts.warningMSG(columns[x] + " can not be empty");
                    }

                }
                if (x == 4) {
                    String in = alerts.inputDialog(columns[x], contextTMP + columns[x]);
                    if (validate.emailValidator(columns[x], in)) {
                        newDoc.setEmail(in);
                        columnIsSet = true;
                    } else {
                        alerts.warningMSG(columns[x] + " can not be empty or be without @");
                    }

                }
                if (x == 5) {
                    String in = alerts.inputDialog(columns[x], contextTMP + columns[x]);
                    if (validate.isItaNum(columns[x], in)) {
                        newDoc.setPhone_Number(in);
                        columnIsSet = true;
                    } else {
                        alerts.warningMSG(columns[x] + " can not be empty or contain characters");
                    }

                }
                if (x == 6) {
                    String in = alerts.inputDialog(columns[x], contextTMP + columns[x]);
                    if (validate.isNotNullandEmpty(columns[x], in)) {
                        newDoc.setSpecialty(in);
                        columnIsSet = true;
                    } else {
                        alerts.warningMSG(columns[x] + " can not be empty");
                    }

                }
                if (x == 7) {
                    String in = alerts.inputDialog(columns[x], contextTMP + columns[x]);
                    if (validate.isNotNullandEmpty(columns[x], in)) {
                        newDoc.setType(in);
                        columnIsSet = true;
                    } else {
                        alerts.warningMSG(columns[x] + " can not be empty");
                    }

                }
                if (x == 8) {
                    String in = alerts.inputDialog(columns[x], contextTMP + columns[x]);
                    if (validate.isNotNullandEmpty(columns[x], in)) {
                        newDoc.setUser_Name(in);
                        columnIsSet = true;
                    } else {
                        alerts.warningMSG(columns[x] + " can not be empty");
                    }

                }
                if (x == 9) {
                    String in = alerts.inputDialog(columns[x], contextTMP + columns[x]);
                    if (validate.isNotNullandEmpty(columns[x], in)) {
                        newDoc.setPassword(in);
                        columnIsSet = true;
                    } else {
                        alerts.warningMSG(columns[x] + " can not be empty");
                    }
                }

            }

            columnIsSet = false;
        }
        
        req_info insert=requirements.insertToDoctor(newDoc);
        if(insert.isInserted()){
        alerts.msg("eDoctors", "Succesfully Registered", "Please restart your app to take effect", AlertType.INFORMATION);
        return true;
        }

        else{
            alerts.warningMSG("Failed to Register");
        return false;
        }
    }
    
    /**does prior checks like
     * is db available
     * is there any doctors registered
     * @return 
     */
    public boolean priorchecks(){
    //First db Check
    if(resources.requirements.isDBavailable()){
        //now lets check if there is an Doctor in our db
        if(resources.requirements.getCountforTable(doctor.Table_Name)<=0){
        statues.setText("No Doctor Registered");
        login.setDisable(true);
        return false;}
        else{
            return true;}
    }
    else {
    statues.setText("DB is not available");
    login.setDisable(true);
    return false;}
    
    }
    
    /**validates the login form
     * 
     * @return 
     */
     public boolean validator(){
    
        boolean validation=true;
        
        validation test=new validation();
        
        validation&=test.isNotNullandEmpty("Username", username.getText());
        validation&=test.isNotNullandEmpty("Password", password.getText());
        if(validation)
            return validation;
        else{
        alerts.warningMSG(test.errormsg);
        return validation;
        }
    }
    
}
