
package uiFX;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //First we check somethings to define either to enable register button or not
        priorchecks();
        
    }    

    @FXML
    private void login(ActionEvent event) {
     //TODO
    }

    @FXML
    private void register(ActionEvent event) {
    }
    
    public boolean priorchecks(){
    //First db Check
    if(resources.requirements.isDBavailable()){
        //now lets check if there is an Doctor in our db
        if(resources.requirements.returnAllDoctor().isEmpty()){
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
