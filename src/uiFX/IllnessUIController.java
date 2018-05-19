/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package uiFX;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import resources.Illneses;
import resources.ImageHandler;
import resources.alerts;
import resources.req_info;
import resources.requirements;

/**
 * FXML Controller class
 *
 * @author rawan
 */
public class IllnessUIController implements Initializable {

    @FXML
    private ImageView cleanIMG;
    @FXML
    private JFXButton loadbutton1;
    @FXML
    private ImageView effectIMG;
    @FXML
    private JFXButton loadbutton2;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton backButton;
    @FXML
    private JFXTextArea description;

    //variables 
    int id;
    Scene oldScene;
    ObservableList<Illneses> list;
    Illneses illness;
    
    String path1,path2;
    @FXML
    private JFXTextField type;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        path1="";
        path2="";
    }    

    @FXML
    private void onLoadButton1(ActionEvent event) {
        String path=alerts.fileChooser();
        
        if(path!=""){
        cleanIMG.setImage(ImageHandler.returnImage(ImageHandler.returnImageBytes(path)));
        path1=path;
        }
        else
            path1="";
    }

    @FXML
    private void onLoadButton2(ActionEvent event) {
      String path=alerts.fileChooser();
        
        if(!path.equals("")){
        effectIMG.setImage(ImageHandler.returnImage(ImageHandler.returnImageBytes(path)));
        path2=path;
        }
        else
            path2="";
    }

    @FXML
    private void onSave(ActionEvent event) {
        if(!path1.equals("") & !path2.equals("")){
        Illneses tmp=new Illneses(0, name.getText(),description.getText() ,type.getText(), path1, path2);
            req_info req=requirements.insertToIllneses(tmp);
            if(req.isInserted()==true){
            tmp.setId(req.getId());
            list.add(tmp);
                onBack(event);
            }
            else
            {alerts.warningMSG("Failed to insert check the log");}
        }
        else
            alerts.warningMSG("Load both Images ,first");
    }

    @FXML
    private void onBack(ActionEvent event) {
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(oldScene);
         window.show();
    }
    public void initOldValues(int id,Scene s,ObservableList<Illneses> list){
    this.id=id;
    oldScene=s;
    this.list=list;
    }
    
    public void detailMode(){
    saveButton.setVisible(false);
    loadbutton1.setVisible(false);
    loadbutton2.setVisible(false);
    
    illness = requirements.returnIllnesWithImage(id);
    name.setText(illness.getName());
    description.setText(illness.getDescription());
    cleanIMG.setImage(illness.getCleanimg());
    effectIMG.setImage(illness.getEffectimg());
    type.setText(illness.getDoctor_type());
    
    name.setEditable(false);
    description.setEditable(false);
    type.setEditable(false);
    }
}
