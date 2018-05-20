
package resources;

import java.io.File;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author rawandkurdy
 */
public class alerts {
        /**warning msg 
         * 
         * @param msg 
         */
        public static void warningMSG(String msg){
        msg("Error",msg,"", Alert.AlertType.ERROR);
    }
    
        /**msg builder ,easily build your alerts
         * 
         * @param title
         * @param header
         * @param content
         * @param type 
         */
       public static void msg(String title,String header,String content,Alert.AlertType type){
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(header);
    if(!content.equals(""))
        alert.setContentText(content);
    alert.showAndWait();
    }
       
       /**
       *a basic confirmation dialog for certain uses if needed
     * @param title
     * @param header
     * @param content
     * @return 
       */
       public static Optional<ButtonType> confirmationDialog(String title,String header,String content){
           Alert alert = new Alert(AlertType.CONFIRMATION);
           alert.setTitle(title);
           alert.setHeaderText(header);
           alert.setContentText(content);

           Optional<ButtonType> result = alert.showAndWait();
           return result;

       
       }
       
       /** delete confirmation alert
        * 
        * @return 
        */
       public static Optional<ButtonType> confirmationDialogDelete(){
       return confirmationDialog("Confirmation Dialog", "Are you sure about deleting this record?", "This action cannot be undo'ed");
       }
       
       /** easily chooses your files through filechooser
        * 
        * @return 
        */
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
       
       /** file chooser specially for .sql files
        * 
        * @return 
        */
       public static String sqlfileChooser(){
        
    String path="";
    FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Open SQL File");
fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
);                 
fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("SQL", "*.sql"),
    new FileChooser.ExtensionFilter("All Files", "*.*")
    
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
       
       /**Directory chooser
        * 
        * @return 
        */
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
    
       /** input dialog 
        * 
        * @param title
        * @param context
        * @return 
        */
       public static String inputDialog(String title,String context){
           String input="";
       TextInputDialog dialog = new TextInputDialog(title);
       dialog.setContentText(context);
       Optional<String> result;
       result = dialog.showAndWait();
        if (result.isPresent()){
        input= result.get();
        }
       return input;}
}
