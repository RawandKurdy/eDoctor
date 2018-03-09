/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package edoctorjava;

/**
 *
 * @author rawan
 */
public class logged {
    
    private String user_id;
    private String user_name;
    private boolean admin;
    //Only Doctors have admin privilage

    public logged(String user_id, String user_name, boolean admin) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.admin=admin;
    }

    public String getDoctor_id() {
        return user_id;
    }

    public String getDoctor_name() {
        return user_name;
    }

    public void setDoctor_id(String user_id) {
        this.user_id = user_id;
    }

    public void setDoctor_name(String user_name) {
        this.user_name = user_name;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }
    
    
}
