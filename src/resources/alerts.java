
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
       
       //a basic confirmation dialog for certain uses if needed
       public static Optional<ButtonType> confirmationDialog(String title,String header,String content){
           Alert alert = new Alert(AlertType.CONFIRMATION);
           alert.setTitle(title);
           alert.setHeaderText(header);
           alert.setContentText(content);

           Optional<ButtonType> result = alert.showAndWait();
           return result;

       
       }
       public static Optional<ButtonType> confirmationDialogDelete(){
       return confirmationDialog("Confirmation Dialog", "Are you sure about deleting this record?", "This action cannot be undo'ed");
       }
    
}
