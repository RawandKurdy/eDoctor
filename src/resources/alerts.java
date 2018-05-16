
package resources;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author rawandkurdy
 */
public class alerts {
    
        public static void warningMSG(String msg){
        warningMSG(msg, Alert.AlertType.ERROR);
    }
    
       public static void warningMSG(String msg,Alert.AlertType type){
    Alert alert = new Alert(type);
alert.setTitle("Error");
alert.setHeaderText(msg);

alert.showAndWait();
    }
       
       public static Optional<ButtonType> confirmationDialog(){
           Alert alert = new Alert(AlertType.CONFIRMATION);
           alert.setTitle("Confirmation Dialog");
           alert.setHeaderText("Are you sure about deleting this record?");
           alert.setContentText("This action cannot be undo'ed");

           Optional<ButtonType> result = alert.showAndWait();
           return result;

       
       }
    
}
