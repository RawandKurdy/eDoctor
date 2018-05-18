/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package resources;

import java.sql.Date;

/**
 *
 * @author rawan
 */
public class cases {
    
    private int id;
    private int illnes_id;
    private int patient_id;
    private int patient_session_id;
    private Date date_of;
    private String notes;
    
    public static final String id_KEY = "ID";
    public static final String ill_id_KEY = "ILL_ID";
    public static final String patient_id_KEY = "PATIENT_ID";
    public static final String patient_session_id_KEY = "PATIENT_SESSION_ID";
    public static final String date_of_KEY = "DATEof";
    public static final String notes_KEY = "NOTES";
    public static final String Table_Name = "CASES";

    public cases(int id, int illnes_id, int patient_id, int patient_session_id, Date date_of, String notes) {
        this.id = id;
        this.illnes_id = illnes_id;
        this.patient_id = patient_id;
        this.patient_session_id = patient_session_id;
        this.date_of = date_of;
        this.notes = notes;
    }
    
      public cases(int id, int illnes_id, int patient_id, int patient_session_id, Date date_of) {
        this.id = id;
        this.illnes_id = illnes_id;
        this.patient_id = patient_id;
        this.patient_session_id = patient_session_id;
        this.date_of = date_of;
    }

    public cases() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIllnes_id() {
        return illnes_id;
    }

    public void setIllnes_id(int illnes_id) {
        this.illnes_id = illnes_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getPatient_session_id() {
        return patient_session_id;
    }

    public void setPatient_session_id(int patient_session_id) {
        this.patient_session_id = patient_session_id;
    }

    public Date getDate_of() {
        return date_of;
    }

    public void setDate_of(Date date_of) {
        this.date_of = date_of;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
    
}
