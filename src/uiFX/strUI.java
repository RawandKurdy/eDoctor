/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author rawandkurdy
 */
public class strUI extends Application {
    
    static String appname="eDoctors v0.2 Alpha";
     static String appabout="Developed By Rawand \n aka Rawand Kurdy \n with MaRS Group \n for University of Human Development"
             + "\n 4th Stage Project \n Copyright 2018";

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("uiFX/login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle(appname);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
