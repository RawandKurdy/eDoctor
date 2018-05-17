/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package resources;

import java.sql.Time;

/**
 *
 * @author rawan
 */
public class patient_session {
    
    private int id;
    private int appointment_id;
    private Time duration;
    private int prescription_id;
    private double cost;
    private String description;
    
    public static final String id_KEY = "ID";
    public static final String appointment_id_KEY = "APPOINTMENT_ID";
    public static final String duration_KEY = "DURATION";
    public static final String prescription_KEY = "PRESCRIPTION";
    public static final String cost_KEY = "COST";
    public static final String description_KEY = "DESCRIPTION";
    public static final String Table_Name = "PATIENT_SESSION";

    public patient_session(int id, int appointment_id, Time duration, int prescription_id, double cost, String description) {
        this.id = id;
        this.appointment_id = appointment_id;
        this.duration = duration;
        this.prescription_id = prescription_id;
        this.cost = cost;
        this.description = description;
    }
    
    public patient_session(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public int getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
}
