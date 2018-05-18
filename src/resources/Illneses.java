/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package resources;

import javafx.scene.image.Image;

/**
 *
 * @author rawan
 */
public class Illneses {
    
    private int id;
    private String name;
    private String description;
    private String doctor_type;
    private Image cleanimg;
    private Image effectimg;
    
    public static final String id_KEY = "ID";
    public static final String name_KEY = "NAME";
    public static final String description_KEY = "DESCRIPTION";
    public static final String doctor_type_KEY = "DOCTOR_TYPE";
    public static final String cleanimg_KEY = "CLEANIMG";
    public static final String effectimg_KEY = "EFFECTIMG";
    public static final String Table_Name = "ILLNESES";

    public Illneses(int id, String name, String description, String doctor_type, Image cleanimg, Image effectimg) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.doctor_type = doctor_type;
        this.cleanimg = cleanimg;
        this.effectimg = effectimg;
    }
    
     public Illneses(int id, String name, String description, String doctor_type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.doctor_type = doctor_type;
    }

    public Illneses() {
    }
     
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoctor_type() {
        return doctor_type;
    }

    public void setDoctor_type(String doctor_type) {
        this.doctor_type = doctor_type;
    }

    public Image getCleanimg() {
        return cleanimg;
    }

    public void setCleanimg(Image cleanimg) {
        this.cleanimg = cleanimg;
    }

    public Image getEffectimg() {
        return effectimg;
    }

    public void setEffectimg(Image effectimg) {
        this.effectimg = effectimg;
    }
    
    

    
}
