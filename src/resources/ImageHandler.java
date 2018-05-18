/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author rawan
 */
public class ImageHandler {
    
       //used when inserting a image to db so it process the file to an input stream so its easily used
    public static FileInputStream returnImageBytes(String path){
        
        try {
            FileInputStream fis = null;
            File file = new File("myPhoto.png");
            fis = new FileInputStream(file);
            return fis;
        } catch (FileNotFoundException ex) {
            logger.appendnewLog(ex.getMessage());
            Logger.getLogger(Illneses.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
    }
    
    public static Image returnImage (InputStream in){
        InputStream stream = in;
        //            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            int a1 = stream.read();
//            while (a1 >= 0) {
//                output.write((char) a1);
//                a1 = stream.read();
//            }
//            Image myImage = Toolkit.getDefaultToolkit().createImage(output.toByteArray());
      return new Image(stream);

//    output.close();
    }
    
}
