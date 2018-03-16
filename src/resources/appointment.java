
package resources;

import java.sql.Date;

/**
 *
 * @author rawandkurdy
 */
public class appointment {
    private int id;
    private int patient_id;
    private int doctor_id;
    private Date date;
    public static final String id_KEY = "ID";
    public static final String patient_id_KEY = "PATIENT_ID";
    public static final String doctor_id_KEY = "DOCTOR_ID";
    public static final String date_KEY = "DATE_OF_APPOINTMENT";
    public static final String Table_Name = "PASSWORD";

    public appointment(int id, int patient_id, int doctor_id, Date date) {
        this.id = id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
