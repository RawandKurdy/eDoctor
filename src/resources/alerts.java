
package resources;

import javafx.scene.control.Alert;

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
    
}
