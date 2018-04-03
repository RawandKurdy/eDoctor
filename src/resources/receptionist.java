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
public class receptionist {
       private int id;
    private String first_Name;
    private String last_Name;
    private String gender;
    private Date dateOfBirth;
    private String email;
    private String phone_Number;
    private double salary;
    private String username;
    private String password;
    boolean discharged;
    public static final String id_KEY = "ID";
    public static final String first_Name_KEY = "FIRST_NAME";
    public static final String last_Name_KEY = "LAST_NAME";
    public static final String gender_KEY = "GENDER";
    public static final String dateOfBirth_KEY = "DATE_OF_BIRTH";
    public static final String email_KEY = "EMAIL";
    public static final String phone_Number_KEY = "PHONE_NO";
    public static final String salary_KEY = "SALARY";
    public static final String username_KEY = "USER_NAME";
    public static final String password_KEY = "PASSWORD";
    public static final String discharged_KEY = "DISCHARGED";
    public static final String Table_Name = "RECEPTIONIST";

    public receptionist(int id, String first_Name, String last_Name, String gender, Date dateOfBirth, String email, String phone_Number, double salary, String username, String password, boolean discharged) {
        this.id = id;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone_Number = phone_Number;
        this.salary = salary;
        this.username = username;
        this.password = password;
        this.discharged = discharged;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    @Override
    public String toString() {
        return "receptionist{" + "id=" + id + ", first_Name=" + first_Name + ", last_Name=" + last_Name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", phone_Number=" + phone_Number + ", salary=" + salary + '}';
    }
 
    

    
}
