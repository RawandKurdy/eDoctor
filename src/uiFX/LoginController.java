
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import resources.doctor;
import resources.receptionist;
import resources.requirements;

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
        
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
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
                 Alert alert = new Alert(AlertType.ERROR);
                       alert.setTitle("Password");
                       alert.setHeaderText("Entered password is wrong");
                    //   alert.setContentText("try again");
                       alert.showAndWait();
                 }
             }
            
         }
         //if didnt found it their we try the receptionist too
         if(!found){
           for (receptionist object : recpList) {
             if(object.getUsername().equalsIgnoreCase(username) ){
                 if(object.getPassword().equals(password)){
             found=true;
             usernamedoesntexist=false;
             recep=object;
             doc=null;
             break;}
                     else{
                     usernamedoesntexist=false;
                 Alert alert = new Alert(AlertType.ERROR);
                       alert.setTitle("Password");
                       alert.setHeaderText("Entered password is wrong");
                    //   alert.setContentText("try again");
                       alert.showAndWait();
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
        controller.initLoggedUser(doc, recep);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(optionsUI);
        window.show();
      }
     
     
     
     if(usernamedoesntexist){
     
     Alert alert = new Alert(AlertType.ERROR);
                       alert.setTitle("Username");
                       alert.setHeaderText("Username Doesnt exist");
                     alert.setContentText("Username is case-sensitive");
                       alert.showAndWait();
     }
     }
       
     }

    @FXML
    private void register(ActionEvent event) {
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
    
}
