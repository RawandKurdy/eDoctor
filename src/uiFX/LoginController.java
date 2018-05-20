
package uiFX;

import java.io.IOException;
import java.net.URL;
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
import resources.receptionist;
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
        }
        
        //Register Not  Implemented //One Doctor only currently
        register.setDisable(true);
    }    

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

    @FXML
    private void register(ActionEvent event) {
        //not implemented
    }
    
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
    register.setDisable(true);
    return false;}
    
    }
    
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
