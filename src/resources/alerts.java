
package resources;

import java.io.File;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author rawandkurdy
 */
public class alerts {
    
        public static void warningMSG(String msg){
        msg("Error",msg,"", Alert.AlertType.ERROR);
    }
    
       public static void msg(String title,String header,String content,Alert.AlertType type){
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(header);
    if(!content.equals(""))
        alert.setContentText(content);
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
       
       public static String ImagefileChooser(){
        
    String path="";
    FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Open Resource File");
fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
);                 
fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("All Images", "*.*"),
    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
    new FileChooser.ExtensionFilter("GIF", "*.gif"),
    new FileChooser.ExtensionFilter("BMP", "*.bmp"),
    new FileChooser.ExtensionFilter("PNG", "*.png")
);

File f= fileChooser.showOpenDialog(null); 
    if(f!=null){
        String test="You chose to open this file: " +
        f.getName();
   System.out.println(test);
          logger.appendnewLog(test);
          path=f.getPath();
        System.out.println(path);
        logger.appendnewLog(path);
       
    }

    return path;}
       
       public static String sqlfileChooser(){
        
    String path="";
    FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Open SQL File");
fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
);                 
fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("All Files", "*.*"),
    new FileChooser.ExtensionFilter("SQL", "*.sql")
);

File f= fileChooser.showOpenDialog(null); 
    if(f!=null){
        String test="You chose to open this file: " +
        f.getName();
        System.out.println(test);
        logger.appendnewLog(test);
        path=f.getPath();
        System.out.println(path);
        logger.appendnewLog(path);
       
    }

    return path;}
       
       
       public static String DirectoryChooser(){
       String path="";
       
       DirectoryChooser directoryChooser = new DirectoryChooser();
       directoryChooser.setTitle("Select Directory");
       directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File dir = directoryChooser.showDialog(null);
        if (dir != null)
            path=dir.getAbsolutePath();
        
           System.out.println(path);
       
       return path;}
    
}
