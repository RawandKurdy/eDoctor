/*
 * Copyright 2018 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package resources;

import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author rawan
 */
public class requirements {

    private static final String url = "jdbc:mysql://localhost:3306/eDoctor";
    private static final String username = "root";
    private static final String password = "root";
    private static boolean encryptionSwitch=true;
    private static req_info fail=new req_info(0, false); //not successful state

    //Connection to MYSQL Database on the server
    public static Connection connectDB() {
        System.out.println("Connecting database...");

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database is available!");
            return connection;
        } catch (SQLException e) {
            // return null;
            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            throw new IllegalStateException("Cannot connect the database!", e);

        }
    }
    
    //DB Statues
    public static boolean isDBavailable() {
         System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)){
        System.out.println("Database is available!");
        return true;
            
        } catch (SQLException e) {
            
            //Debugging stuff
            System.out.println(e);
             //logger
            logger.appendnewLog(e.toString());
            return false;

        }
    }

    
    //MD5 Encyption Method ,used for securing an input like a password
    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            //logger
            logger.appendnewLog(e.toString());
        }
        return null;
    }

    //patient table Operations//Start
    public static req_info insertToPatient(patient p) {
        System.out.println(p);
        String sqlquery = "INSERT INTO Patient (" + patient.id_KEY + ", " + patient.first_Name_KEY + ", " + patient.last_Name_KEY + ", "
                + patient.gender_KEY + ", " + patient.dateOfBirth_KEY + ", " + patient.email_KEY + ", " + patient.address_KEY + ", " + patient.phone_Number_KEY
                + ", " + patient.info_KEY + ", " + patient.user_Name_KEY + ", " + patient.password_KEY + ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        req_info state;
        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery,Statement.RETURN_GENERATED_KEYS);
            //change back to object.getId() if removed the auto increment
            SQLstatement.setInt(1,0); 
            SQLstatement.setString(2, p.getFirst_Name());
            SQLstatement.setString(3, p.getLast_Name());
            SQLstatement.setString(4, p.getGender());
            SQLstatement.setDate(5, p.getDateOfBirth());
            SQLstatement.setString(6, p.getEmail());
            SQLstatement.setString(7, p.getAddress());
            SQLstatement.setString(8, p.getPhone_Number());
            SQLstatement.setString(9, p.getInfo());
            SQLstatement.setString(10, p.getUser_Name());
            SQLstatement.setString(11, MD5(p.getPassword()));

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted successfully!");
                state=returnStatus(SQLstatement);
                state.setInserted(true);
                return state;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return fail;
    }

    public static boolean updatePatient(patient p, patient old) {
        System.out.println(p);
        String sqlquery = "UPDATE Patient SET " + patient.first_Name_KEY + "=? ,"
                + patient.last_Name_KEY + "=? ," + patient.gender_KEY + "=? ," + patient.dateOfBirth_KEY + "=? ," + patient.email_KEY + "=? ," + patient.address_KEY + "=? ,"
                + patient.phone_Number_KEY + "=? ," + patient.info_KEY + "=? ," + patient.user_Name_KEY + "=? ," + patient.password_KEY + "=? " + "WHERE " + patient.id_KEY + "=? ";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            if (!p.getFirst_Name().equals(old.getFirst_Name())) {
                SQLstatement.setString(1, p.getFirst_Name());
            } else {
                SQLstatement.setString(1, old.getFirst_Name());
            }
            if (!p.getLast_Name().equals(old.getLast_Name())) {
                SQLstatement.setString(2, p.getLast_Name());
            } else {
                SQLstatement.setString(2, old.getLast_Name());
            }

            if (!p.getGender().equals(old.getGender())) {
                SQLstatement.setString(3, p.getGender());
            } else {
                SQLstatement.setString(3, old.getGender());
            }

            if (!p.getDateOfBirth().equals(old.getDateOfBirth())) {
                SQLstatement.setDate(4, p.getDateOfBirth());
            } else {
                SQLstatement.setDate(4, old.getDateOfBirth());
            }
            if (!p.getEmail().equals(old.getEmail())) {
                SQLstatement.setString(5, p.getEmail());
            } else {
                SQLstatement.setString(5, old.getEmail());
            }

            if (!p.getAddress().equals(old.getAddress())) {
                SQLstatement.setString(6, p.getAddress());
            } else {
                SQLstatement.setString(6, old.getAddress());
            }

            if (!p.getPhone_Number().equals(old.getPhone_Number())) {
                SQLstatement.setString(7, p.getPhone_Number());
            } else {
                SQLstatement.setString(7, old.getPhone_Number());
            }

            if (!p.getInfo().equals(old.getInfo())) {
                SQLstatement.setString(8, p.getInfo());
            } else {
                SQLstatement.setString(8, old.getInfo());
            }

            if (!p.getUser_Name().equals(old.getUser_Name())) {
                SQLstatement.setString(9, p.getUser_Name());
            } else {
                SQLstatement.setString(9, old.getUser_Name());
            }

            if (p.getPassword() != null & !p.getPassword().equals("")) {
                SQLstatement.setString(10, MD5(p.getPassword()));
            } else {
                SQLstatement.setString(10, old.getPassword());
            }

            SQLstatement.setInt(11, p.getId());
            System.out.println(SQLstatement.toString());

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Updated successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    }

    public static boolean deleteFromPatient(int id) {

        String sqlquery = "DELETE FROM  Patient WHERE " + patient.id_KEY + "=?";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, id);

            int rowsDeleted = SQLstatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Deleted successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    }

    //Returns a single patient using the id
    public static patient returnPatient(int req_id) {
        patient tmpPatient;

        String sqlquery = "SELECT * FROM  Patient WHERE " + patient.id_KEY + " =" + req_id;

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);
            while (queryResult.next()) {
                int id = queryResult.getInt(1);
                String first_Name = queryResult.getString(2);
                String last_Name = queryResult.getString(3);
                String gender = queryResult.getString(4);
                Date dateOfBirth = queryResult.getDate(5);
                String email = queryResult.getString(6);
                String address = queryResult.getString(7);
                String phone_Number = queryResult.getString(8);
                String info = queryResult.getString(9);
                String user_Name = queryResult.getString(10);
                String password = queryResult.getString(11);
                tmpPatient = new patient(id, first_Name, last_Name, gender, dateOfBirth, email, address, phone_Number, info, user_Name, password);
                System.out.println("Retrieved successfully!");
                return tmpPatient;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }

    //Returns All Patients
    public static ArrayList<patient> returnAllPatient() {
        ArrayList<patient> tmparrayList = new ArrayList<>();

        patient tmpPatient;

        String sqlquery = "SELECT * FROM  Patient";

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);

            int count = 0;
            while (queryResult.next()) {
                int id = queryResult.getInt(1);
                String first_Name = queryResult.getString(2);
                String last_Name = queryResult.getString(3);
                String gender = queryResult.getString(4);
                Date dateOfBirth = queryResult.getDate(5);
                String email = queryResult.getString(6);
                String address = queryResult.getString(7);
                String phone_Number = queryResult.getString(8);
                String info = queryResult.getString(9);
                String user_Name = queryResult.getString(10);
                String password = queryResult.getString(11);
                tmpPatient = new patient(id, first_Name, last_Name, gender, dateOfBirth, email, address, phone_Number, info, user_Name, password);
                tmparrayList.add(tmpPatient);
                count++;
            }
            System.out.println("Retrieved successfully!");
            System.out.println("No of Retrieved ROWS are : " + count);
            return tmparrayList;

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }

    //patient table Operations//End
    
    
    
    
    //Receptionist table Operations//Start
    
    
    //Add a new receptionist
    public static req_info insertToReceptionist(receptionist r) {
        System.out.println(r);
        String sqlquery = "INSERT INTO Receptionist (" + receptionist.id_KEY + ", " + receptionist.first_Name_KEY + ", " + receptionist.last_Name_KEY + ", "
                + receptionist.gender_KEY + ", " + receptionist.dateOfBirth_KEY + ", " + receptionist.email_KEY + ", " + receptionist.phone_Number_KEY + ", " + receptionist.salary_KEY
                + ", " + receptionist.username_KEY + ", " + receptionist.password_KEY + ", "
                +receptionist.discharged_KEY+
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        req_info state;
        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery,Statement.RETURN_GENERATED_KEYS);
            //change back to object.getId() if removed the auto increment
            SQLstatement.setInt(1,0); 
            SQLstatement.setString(2, r.getFirst_Name());
            SQLstatement.setString(3, r.getLast_Name());
            SQLstatement.setString(4, r.getGender());
            SQLstatement.setDate(5, r.getDateOfBirth());
            SQLstatement.setString(6, r.getEmail());
            SQLstatement.setString(7, r.getPhone_Number());
            SQLstatement.setDouble(8, r.getSalary());
            SQLstatement.setString(9, r.getUsername());
            SQLstatement.setString(10,MD5(r.getPassword()));
            SQLstatement.setBoolean(11, r.isDischarged());

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted successfully!");
                state=returnStatus(SQLstatement);
                state.setInserted(true);
                return state;
            }

        } catch (SQLException e) {

            System.out.println(e);
             //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return fail;
    }

    //update an existing receptionist
    public static boolean updateReceptionist(receptionist r, receptionist old) {
        System.out.println(r);
        String sqlquery = "UPDATE Receptionist SET " + receptionist.first_Name_KEY + "=? ,"
                + receptionist.last_Name_KEY + "=? ," + receptionist.gender_KEY + "=? ," + receptionist.dateOfBirth_KEY + "=? ," + receptionist.email_KEY + "=? ," + receptionist.phone_Number_KEY + "=? ,"
                + receptionist.salary_KEY + "=? ," 
                + receptionist.username_KEY + "=? ,"
                + receptionist.password_KEY + "=? ," +receptionist.discharged_KEY + "=? "
                + "WHERE " + receptionist.id_KEY + "=? ";
        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            if (!r.getFirst_Name().equals(old.getFirst_Name())) {
                SQLstatement.setString(1, r.getFirst_Name());
            } else {
                SQLstatement.setString(1, old.getFirst_Name());
            }
            if (!r.getLast_Name().equals(old.getLast_Name())) {
                SQLstatement.setString(2, r.getLast_Name());
            } else {
                SQLstatement.setString(2, old.getLast_Name());
            }

            if (!r.getGender().equals(old.getGender())) {
                SQLstatement.setString(3, r.getGender());
            } else {
                SQLstatement.setString(3, old.getGender());
            }

            if (!r.getDateOfBirth().equals(old.getDateOfBirth())) {
                SQLstatement.setDate(4, r.getDateOfBirth());
            } else {
                SQLstatement.setDate(4, old.getDateOfBirth());
            }
            if (!r.getEmail().equals(old.getEmail())) {
                SQLstatement.setString(5, r.getEmail());
            } else {
                SQLstatement.setString(5, old.getEmail());
            }

            if (!r.getPhone_Number().equals(old.getPhone_Number())) {
                SQLstatement.setString(6, r.getPhone_Number());
            } else {
                SQLstatement.setString(6, old.getPhone_Number());
            }

            if (r.getSalary()!=old.getSalary()) {
                SQLstatement.setDouble(7, r.getSalary());
            } else {
                SQLstatement.setDouble(7, old.getSalary());
            }
            
            if (!r.getUsername().equals(old.getUsername())) {
                SQLstatement.setString(8, r.getUsername());
            } else {
                SQLstatement.setString(8, old.getUsername());
            }
            
            if (r.getPassword()!= null & !r.getPassword().equals("")) {
                 SQLstatement.setString(9,MD5(r.getPassword()));
          
            } else {
                 SQLstatement.setString(9, old.getPassword());
   
            }
            
             SQLstatement.setBoolean(10, r.isDischarged());
            //In GUI we must consider setting the old value to a location which the user can easily see
            //old state
     
            
            SQLstatement.setInt(11, r.getId());
            
          
            
            //debugging stufff will be cleaned later if needed
            System.out.println(SQLstatement.toString());

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Updated successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
             //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    }
    
    //delete a receptionist if needed
    public static boolean deleteFromReceptionist(int id) {

        String sqlquery = "DELETE FROM  Receptionist WHERE " + receptionist.id_KEY + "=?";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, id);

            int rowsDeleted = SQLstatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Deleted successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
             //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    }

    //Returns a single receptionist using the id
    public static receptionist returnReceptionist(int req_id) {
        receptionist tmpreceptionist;

        String sqlquery = "SELECT * FROM  Receptionist WHERE " + receptionist.id_KEY + " =" + req_id;

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);
            while (queryResult.next()) {
                int id = queryResult.getInt(1);
                String first_Name = queryResult.getString(2);
                String last_Name = queryResult.getString(3);
                String gender = queryResult.getString(4);
                Date dateOfBirth = queryResult.getDate(5);
                String email = queryResult.getString(6);
                String phone_Number = queryResult.getString(7);
                double salary = queryResult.getDouble(8);
                String username=queryResult.getString(9);
                String password=queryResult.getString(10);
                boolean discharged=queryResult.getBoolean(11);
               tmpreceptionist = new receptionist(id, first_Name, last_Name, gender, dateOfBirth, email, phone_Number, salary,username,password,discharged);
               System.out.println("Retrieved successfully!");
                return tmpreceptionist;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }

    //Returns All Receptionists
    public static ArrayList<receptionist> returnAllReceptionist() {
        ArrayList<receptionist> tmparrayList = new ArrayList<>();

        receptionist tmpreceptionist;

        String sqlquery = "SELECT * FROM  Receptionist";

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);

            int count = 0;
            while (queryResult.next()) {
                int id = queryResult.getInt(1);
                String first_Name = queryResult.getString(2);
                String last_Name = queryResult.getString(3);
                String gender = queryResult.getString(4);
                Date dateOfBirth = queryResult.getDate(5);
                String email = queryResult.getString(6);
                String phone_Number = queryResult.getString(7);
                double salary = queryResult.getDouble(8);
                String username=queryResult.getString(9);
                String password=queryResult.getString(10);
                boolean discharged=queryResult.getBoolean(11);
               tmpreceptionist = new receptionist(id, first_Name, last_Name, gender, dateOfBirth, email, phone_Number, salary,username,password,discharged);
               tmparrayList.add(tmpreceptionist);
                count++;
            }
            System.out.println("Retrieved successfully!");
            System.out.println("No of Retrieved ROWS are : " + count);
            return tmparrayList;

        } catch (SQLException e) {

            System.out.println(e);
             //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }
    
    
    //Receptionist table Operations///End
    
    //Doctor table Operations//Start
    public static req_info insertToDoctor(doctor d) {
        System.out.println(d);
        String sqlquery = "INSERT INTO Doctor (" + doctor.id_KEY + ", " + doctor.first_Name_KEY + ", " + doctor.last_Name_KEY + ", "
                + doctor.gender_KEY + ", " + doctor.dateOfBirth_KEY + ", " + doctor.email_KEY + ", " + doctor.phone_Number_KEY + ", " + doctor.specialty_KEY
                + ", " + doctor.type_KEY + ", " + doctor.user_Name_KEY + ", " + doctor.password_KEY + ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        req_info state;
        

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery,Statement.RETURN_GENERATED_KEYS);
            //change back to object.getId() if removed the auto increment
            SQLstatement.setInt(1,0); 
            SQLstatement.setString(2, d.getFirst_Name());
            SQLstatement.setString(3, d.getLast_Name());
            SQLstatement.setString(4, d.getGender());
            SQLstatement.setDate(5, d.getDateOfBirth());
            SQLstatement.setString(6, d.getEmail());
            SQLstatement.setString(7, d.getPhone_Number());
            SQLstatement.setString(8, d.getSpecialty());
            SQLstatement.setString(9, d.getType());
            SQLstatement.setString(10, d.getUser_Name());
            SQLstatement.setString(11, MD5(d.getPassword()));

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted successfully!");
                state=returnStatus(SQLstatement);
                state.setInserted(true);
                return state;
            }

        } catch (SQLException e) {

            System.out.println(e);
             //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return fail;
    }

    public static boolean updateDoctor(doctor d, doctor old) {
        System.out.println(d);
        String sqlquery = "UPDATE Doctor SET " + doctor.first_Name_KEY + "=? ,"
                + doctor.last_Name_KEY + "=? ," + doctor.gender_KEY + "=? ," + doctor.dateOfBirth_KEY + "=? ," + doctor.email_KEY + "=? ," + doctor.phone_Number_KEY + "=? ,"
                + doctor.specialty_KEY + "=? ," + doctor.type_KEY + "=? ," + doctor.user_Name_KEY + "=? ," + doctor.password_KEY + "=? " + "WHERE " + doctor.id_KEY + "=? ";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            if (!d.getFirst_Name().equals(old.getFirst_Name())) {
                SQLstatement.setString(1,d.getFirst_Name());
            } else {
                SQLstatement.setString(1, old.getFirst_Name());
            }
            if (!d.getLast_Name().equals(old.getLast_Name())) {
                SQLstatement.setString(2, d.getLast_Name());
            } else {
                SQLstatement.setString(2, old.getLast_Name());
            }

            if (!d.getGender().equals(old.getGender())) {
                SQLstatement.setString(3, d.getGender());
            } else {
                SQLstatement.setString(3, old.getGender());
            }

            if (!d.getDateOfBirth().equals(old.getDateOfBirth())) {
                SQLstatement.setDate(4, d.getDateOfBirth());
            } else {
                SQLstatement.setDate(4, old.getDateOfBirth());
            }
            if (!d.getEmail().equals(old.getEmail())) {
                SQLstatement.setString(5, d.getEmail());
            } else {
                SQLstatement.setString(5, old.getEmail());
            }


            if (!d.getPhone_Number().equals(old.getPhone_Number())) {
                SQLstatement.setString(6,d.getPhone_Number());
            } else {
                SQLstatement.setString(6, old.getPhone_Number());
            }
            
               if (!d.getSpecialty().equals(old.getSpecialty())) {
                SQLstatement.setString(7, d.getSpecialty());
            } else {
                SQLstatement.setString(7, old.getSpecialty());
            }

            if (!d.getType().equals(old.getType())) {
                SQLstatement.setString(8, d.getType());
            } else {
                SQLstatement.setString(8, old.getType());
            }

            if (!d.getUser_Name().equals(old.getUser_Name())) {
                SQLstatement.setString(9, d.getUser_Name());
            } else {
                SQLstatement.setString(9, old.getUser_Name());
            }

            if (d.getPassword() != null & !d.getPassword().equals("")) {
                SQLstatement.setString(10, MD5(d.getPassword()));
            } else {
                SQLstatement.setString(10, old.getPassword());
            }

            SQLstatement.setInt(11, d.getId());
            System.out.println(SQLstatement.toString());

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Updated successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
             //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    }

    //Un used method incase of //Single user 
    public static boolean deleteFromDoctor(int id) {

        String sqlquery = "DELETE FROM  Doctor WHERE " + doctor.id_KEY + "=?";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, id);

            int rowsDeleted = SQLstatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Deleted successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    }

        //Returns a single Doctor using the id
    public static doctor returnDoctor(int req_id) {
        doctor tmpDoctor;

        String sqlquery = "SELECT * FROM  Doctor WHERE " + doctor.id_KEY + " =" + req_id;

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);
            while (queryResult.next()) {
                int id = queryResult.getInt(1);
                String first_Name = queryResult.getString(2);
                String last_Name = queryResult.getString(3);
                String gender = queryResult.getString(4);
                Date dateOfBirth = queryResult.getDate(5);
                String email = queryResult.getString(6);
                String phone_Number = queryResult.getString(7);
                String specialty = queryResult.getString(8);
                String type = queryResult.getString(9);
                String user_Name = queryResult.getString(10);
                String password = queryResult.getString(11);
                tmpDoctor =new doctor(id, first_Name, last_Name, gender, dateOfBirth, email, phone_Number, specialty, type, user_Name, password);
                System.out.println("Retrieved successfully!");
                return tmpDoctor;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }

    //Returns All Doctors 
    //This method is not used unless of multiple doctors usage or multi-doctor clinic
    public static ArrayList<doctor> returnAllDoctor() {
        ArrayList<doctor> tmparrayList = new ArrayList<>();

        doctor tmpDoctor;

        String sqlquery = "SELECT * FROM  Doctor";

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);

            int count = 0;
            while (queryResult.next()) {
                 int id = queryResult.getInt(1);
                String first_Name = queryResult.getString(2);
                String last_Name = queryResult.getString(3);
                String gender = queryResult.getString(4);
                Date dateOfBirth = queryResult.getDate(5);
                String email = queryResult.getString(6);
                String phone_Number = queryResult.getString(7);
                String specialty = queryResult.getString(8);
                String type = queryResult.getString(9);
                String user_Name = queryResult.getString(10);
                String password = queryResult.getString(11);
                tmpDoctor =new doctor(id, first_Name, last_Name, gender, dateOfBirth, email, phone_Number, specialty, type, user_Name, password);
                tmparrayList.add(tmpDoctor);
                count++;
            }
            System.out.println("Retrieved successfully!");
            System.out.println("No of Retrieved ROWS are : " + count);
            return tmparrayList;

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }

    //Doctor table Operations//End
    
    //AES Encryption//Start
    
    public static String doEncryption(String txt){
    
    if(!txt.equals("") & txt!=null){
        try {
           return AdvancedEncryptionStandard.encrypt(txt);
        } catch (Exception ex) {
            //logger
            logger.appendnewLog(ex.toString());
            Logger.getLogger(requirements.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    return "";}
    
      public static String doDecryption(String txt){
    
    if(!txt.equals("") & txt!=null){
        try {
            return AdvancedEncryptionStandard.decrypt(txt);
        } catch (Exception ex) {
             //logger
            logger.appendnewLog(ex.toString());
            Logger.getLogger(requirements.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    return "";}
    
    
    //AES Encryption//End
      
      
      
    //Prescription operations//Start
      
      //insert an entry to prescription
    public static req_info insertToPrescription(prescription p) {
        System.out.println(p);
        String sqlquery = "INSERT INTO PRESCRIPTION (" + prescription.id_KEY + ", " + prescription.dosage_KEY + ", " + prescription.details_KEY + ", "
                + prescription.fromDate_KEY + ", " + prescription.toDate_KEY + ") VALUES (?,?,?,?,?)";

        req_info state;
        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery,Statement.RETURN_GENERATED_KEYS);
            //change back to object.getId() if removed the auto increment
            SQLstatement.setInt(1,0); 
            SQLstatement.setString(2, p.getDosage());
            if(encryptionSwitch)
                SQLstatement.setString(3, doEncryption(p.getDetails()));
            else
                SQLstatement.setString(3, p.getDetails());
    
            
            SQLstatement.setDate(4, p.getFrom_Date());
            SQLstatement.setDate(5, p.getTo_Date());
      
            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted successfully!");
                state=returnStatus(SQLstatement);
                state.setInserted(true);
                return state;
            }

        } catch (SQLException e) {

            System.out.println(e);
             //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return fail;
    }
    //updates an existing prescription
    public static boolean updatePrescription(prescription p, prescription old) {
        System.out.println(p);
        String sqlquery = "UPDATE PRESCRIPTION SET " + prescription.dosage_KEY + "=? ,"
                + prescription.details_KEY + "=? ," + prescription.fromDate_KEY + "=? ," + prescription.toDate_KEY + "=? " + "WHERE " + prescription.id_KEY + "=? ";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            if (!p.getDosage().equals(old.getDosage())) {
                SQLstatement.setString(1, p.getDosage());
            } else {
                SQLstatement.setString(1, old.getDosage());
            }
            if (!p.getDetails().equals(old.getDetails())) {
            if(encryptionSwitch)
                 SQLstatement.setString(2, doEncryption(p.getDetails()));
            else
             SQLstatement.setString(2, p.getDetails());


            } else {if(encryptionSwitch)
                 SQLstatement.setString(2, doEncryption(old.getDetails()));
            else
             SQLstatement.setString(2, old.getDetails());

            }

            if (!p.getFrom_Date().equals(old.getFrom_Date())) {
                SQLstatement.setDate(3, p.getFrom_Date());
            } else {
                SQLstatement.setDate(3, old.getFrom_Date());
            }

            if (!p.getTo_Date().equals(old.getTo_Date())) {
                SQLstatement.setDate(4, p.getTo_Date());
            } else {
                SQLstatement.setDate(4, old.getTo_Date());
            }

            SQLstatement.setInt(5, p.getId());
            System.out.println(SQLstatement.toString());

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Updated successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
             //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    }

    //deletes a prescription
    public static boolean deleteFromPrescription(int id) {

        String sqlquery = "DELETE FROM  PRESCRIPTION WHERE " + prescription.id_KEY + "=?";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, id);

            int rowsDeleted = SQLstatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Deleted successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    }

    //Returns a single prescription using the id
    public static prescription returnPrescription(int req_id) {
        prescription tmpPrescription;

        String sqlquery = "SELECT * FROM  PRESCRIPTION WHERE " + prescription.id_KEY + " =" + req_id;

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);
            while (queryResult.next()) {
                 int id=queryResult.getInt(1);
                 String dosage=queryResult.getString(2);
                 String details;
                 if(encryptionSwitch)
                 details=doDecryption(queryResult.getString(3));
                 else
                 details=queryResult.getString(3);
                 
                 Date from_Date=queryResult.getDate(4);
                 Date to_Date=queryResult.getDate(5);
                 tmpPrescription=new prescription(id, dosage, details, from_Date, to_Date);
                System.out.println("Retrieved successfully!");
                return tmpPrescription;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }

    //Returns All Prescriptions
    //Typically useless but declared incase of a use if discovered 
    public static ArrayList<prescription> returnAllPrescription() {
        ArrayList<prescription> tmparrayList = new ArrayList<>();

        prescription tmpPrescription;

        String sqlquery = "SELECT * FROM  PRESCRIPTION";

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);

            int count = 0;
            while (queryResult.next()) {  
                 int id=queryResult.getInt(1);
                 String dosage=queryResult.getString(2);
                 String details;
                 if(encryptionSwitch)
                 details=doDecryption(queryResult.getString(3));
                 else
                 details=queryResult.getString(3);
                 
                 Date from_Date=queryResult.getDate(4);
                 Date to_Date=queryResult.getDate(5);
                 tmpPrescription=new prescription(id, dosage, details, from_Date, to_Date);
                 tmparrayList.add(tmpPrescription);
                count++;
            }
            System.out.println("Retrieved successfully!");
            System.out.println("No of Retrieved ROWS are : " + count);
            return tmparrayList;

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }
      
      
    
    //Prescription operations//End
      
    
    //Appointment operations//Start
    
    //Inserts an appointment & And also can be used with getAppointment later to interchange
   //requested appointments and actual appointments tables which is nearly identical interm of structure
    public static req_info insertToAppointment(appointment a) {
        System.out.println(a);
        String sqlquery = "INSERT INTO appointment (" + appointment.id_KEY + ", " + appointment.patient_id_KEY + ", " + appointment.doctor_id_KEY + ", "
                + appointment.date_KEY + ") VALUES (?,?,?,?)";
        req_info state;
        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery,Statement.RETURN_GENERATED_KEYS);
      
           //change back to object.getId() if removed the auto increment
            SQLstatement.setInt(1,0); 
            SQLstatement.setInt(2, a.getPatient_id());
            SQLstatement.setInt(3, a.getDoctor_id());
            SQLstatement.setDate(4, a.getDate());
            
            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted successfully!");
                state=returnStatus(SQLstatement);
                state.setInserted(true);
                System.out.println(state);
                return state;
            }

        } catch (SQLException e) {

            System.out.println(e);
             //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        
        return fail;
    }
    
    public static boolean updateReqAppointmentStatus(boolean state,int id){
    String sqlquery = "UPDATE requested_appointment SET STATUS =?   WHERE " + appointment.id_KEY + "=?";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setBoolean(1, state);
            SQLstatement.setInt(2, id);

            int rowsUpdated = SQLstatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Updated Status successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    
    }
    
    //updates an existing appointment
    public static boolean updateAppointment(appointment a, appointment old) {
        System.out.println(a);
        String sqlquery = "UPDATE appointment SET " + appointment.patient_id_KEY + "=? ,"
                + appointment.doctor_id_KEY + "=? ," + appointment.date_KEY + "=? "+ "WHERE " + appointment.id_KEY + "=? ";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            if (a.getPatient_id()!=old.getPatient_id()) {
                SQLstatement.setInt(1, a.getPatient_id());
            } else {
                SQLstatement.setInt(1, old.getPatient_id());
            }
            
            if (a.getDoctor_id()!=old.getDoctor_id()) {
                 SQLstatement.setInt(2, a.getDoctor_id());
          
            } else {
                 SQLstatement.setInt(2, old.getDoctor_id());
   
            }

            if (!a.getDate().equals(old.getDate())) {
                SQLstatement.setDate(3, a.getDate());
            } else {
                SQLstatement.setDate(3, old.getDate());
            }
            
            SQLstatement.setInt(4, a.getId());
            
            //debug stuff
            System.out.println(SQLstatement.toString());

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Updated successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    }

    //deletes an appointment
    public static boolean deleteFromAppointment(int id) {

        String sqlquery = "DELETE FROM  appointment WHERE " + appointment.id_KEY + "=?";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, id);

            int rowsDeleted = SQLstatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Deleted successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    }

    //Returns a single appointment using the id
    public static appointment returnAppointment(int req_id) {
        appointment tmpAppointment;

        String sqlquery = "SELECT * FROM  appointment WHERE " + appointment.id_KEY + " =" + req_id;

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);
            while (queryResult.next()) {
                  int id=queryResult.getInt(1);
                  int patient_id=queryResult.getInt(2);
                  int doctor_id=queryResult.getInt(3);
                  Date date=queryResult.getDate(4);
                  tmpAppointment = new appointment(id, patient_id, doctor_id, date);
                System.out.println("Retrieved successfully!");
                return tmpAppointment;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }

    //Returns All Appointments 
    public static ArrayList<appointment> returnAllAppointmentUsingDoctorID(int req_id,String table_Name) {
        ArrayList<appointment> tmparrayList = new ArrayList<>();

        appointment tmpAppointment;
        String sqlquery="";
        if(req_id==-1)
         sqlquery ="SELECT * FROM  "+table_Name ;
        else
         sqlquery ="SELECT * FROM  "+table_Name+" WHERE "+appointment.doctor_id_KEY+" ="+req_id +" AND DATE(DATE_OF_APPOINTMENT) = DATE(NOW())";
                   
        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);

            int count = 0;
            while (queryResult.next()) {  
                  int id=queryResult.getInt(1);
                  int patient_id=queryResult.getInt(2);
                  int doctor_id=queryResult.getInt(3);
                  Date date=queryResult.getDate(4);
                  tmpAppointment = new appointment(id, patient_id, doctor_id, date);
                  
               if(table_Name.equalsIgnoreCase(appointment.Table_Name_Requested)){
                  boolean status=queryResult.getBoolean(5);
                  tmpAppointment.setStatus(status);
                  }
               
                 tmparrayList.add(tmpAppointment);
                count++;
            }
            System.out.println("Retrieved successfully!");
            System.out.println("No of Retrieved ROWS are : " + count);
            return tmparrayList;

        } catch (SQLException e) {

            System.out.println(e);
             //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }
    
     public static ArrayList<appointment> returnAllAppointment(String table_Name) {return returnAllAppointmentUsingDoctorID(-1,table_Name);}
    
    //Appointment operations//End
     
   
    //patient_Session table Operations//Start
    public static req_info insertToPatient_Session(patient_session p) {
        System.out.println(p);
        String sqlquery = "INSERT INTO PATIENT_SESSION (" + patient_session.id_KEY + ", "
                + patient_session.appointment_id_KEY + ", " + patient_session.duration_KEY + ", "
                + patient_session.prescription_KEY + ", " + patient_session.cost_KEY + ", " + patient_session.description_KEY 
                + ") VALUES (?,?,?,?,?,?)";

        req_info state;
        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery,Statement.RETURN_GENERATED_KEYS);
            //change back to object.getId() if removed the auto increment
            SQLstatement.setInt(1,0); 
            SQLstatement.setInt(2, p.getAppointment_id());
            SQLstatement.setString(3, p.getDuration());
            SQLstatement.setInt(4, p.getPrescription_id());
            SQLstatement.setDouble(5, p.getCost());
            if(encryptionSwitch){
            SQLstatement.setString(6, doEncryption(p.getDescription()));}
            else{
            SQLstatement.setString(6, p.getDescription());}

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted successfully!");
                state=returnStatus(SQLstatement);
                state.setInserted(true);
                return state;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return fail;
    }

    public static boolean updatePatient_Session(patient_session p, patient_session old) {
        //not required because sessions are made instantly and doesnt require updating
        return false;
    }

    public static boolean deleteFromPatient_Session(int id) {

        String sqlquery = "DELETE FROM  PATIENT_SESSION WHERE " + patient_session.id_KEY + "=?";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, id);

            int rowsDeleted = SQLstatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Deleted successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    }

    //Returns a single patient_session using the id wiith description
    public static patient_session returnPatientSession(int req_id) {
        patient_session tmpPatientSession;

        String sqlquery = "SELECT * FROM  PATIENT_SESSION WHERE " + patient_session.id_KEY + " =" + req_id;

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);
            while (queryResult.next()) {
                int id = queryResult.getInt(1);
                int appointment_id = queryResult.getInt(2);
                String duration = queryResult.getString(3);
                int prescription_id = queryResult.getInt(4);
                Double cost = queryResult.getDouble(5);
                String description;
                
                if(encryptionSwitch)
                    description=doDecryption(queryResult.getString(6));
                else
                    description=queryResult.getString(6);
                
                tmpPatientSession = new patient_session(id, appointment_id, duration, prescription_id, cost, description);
                System.out.println("Retrieved successfully!");
                return tmpPatientSession;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }

    //Returns All Patient Sessions without description
    public static ArrayList<patient_session> returnAllPatientSessions() {
        ArrayList<patient_session> tmparrayList = new ArrayList<>();

        patient_session tmpPatientSession;
        
        //without description column ,these are the only required fields
        String attributes=patient_session.id_KEY +" ,"+patient_session.appointment_id_KEY+" ,"+patient_session.duration_KEY
                +" ,"+patient_session.prescription_KEY+" ,"+patient_session.cost_KEY;

        String sqlquery = "SELECT "+ attributes +" FROM  PATIENT_SESSION";

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);

            int count = 0;
            while (queryResult.next()) {
                int id = queryResult.getInt(1);
                int appointment_id = queryResult.getInt(2);
                String duration = queryResult.getString(3);
                int prescription_id = queryResult.getInt(4);
                Double cost = queryResult.getDouble(5);
              //  String description;
                //removed the description from getAll cuz wastes time and wont be that helpful
//                if(encryptionSwitch)
//                    description=doDecryption(queryResult.getString(6));
//                else
//                    description=queryResult.getString(6);
                
                tmpPatientSession = new patient_session(id, appointment_id, duration, prescription_id, cost);
                tmparrayList.add(tmpPatientSession);
                count++;
            }
            System.out.println("Retrieved successfully!");
            System.out.println("No of Retrieved ROWS are : " + count);
            return tmparrayList;

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }

    //patient session table Operations//End
    
     
       //Illneses table Operations//Start
    public static req_info insertToIllneses(Illneses i) {

        String sqlquery = "INSERT INTO ILLNESES (" + Illneses.id_KEY + ", "
                + Illneses.name_KEY + ", " + Illneses.description_KEY + ", "
                + Illneses.doctor_type_KEY + ", " + Illneses.cleanimg_KEY + ", " + Illneses.effectimg_KEY 
                + ") VALUES (?,?,?,?,?,?)";

        req_info state;
        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery,Statement.RETURN_GENERATED_KEYS);
            //change back to object.getId() if removed the auto increment
            SQLstatement.setInt(1,0); 
            SQLstatement.setString(2, i.getName());
            SQLstatement.setString(3, i.getDescription());
            SQLstatement.setString(4, i.getDoctor_type());
            SQLstatement.setBinaryStream(5, ImageHandler.returnImageBytes(i.getCleanimgPath()));
            SQLstatement.setBinaryStream(6, ImageHandler.returnImageBytes(i.getEffectimgPath()));

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted successfully!");
                state=returnStatus(SQLstatement);
                state.setInserted(true);
                return state;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return fail;
    }

    public static boolean updateIllneses(Illneses i, Illneses old) {
        //not required ,currently isnot implemented
        return false;
    }

    public static boolean deleteFromIllneses(int id) {

        String sqlquery = "DELETE FROM  ILLNESES WHERE " + Illneses.id_KEY + "=?";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, id);

            int rowsDeleted = SQLstatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Deleted successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    }

    //Returns a single illnes with image using the id
    public static Illneses returnIllnesWithImage(int req_id) {
        Illneses tmpIllness;

        String sqlquery = "SELECT * FROM  ILLNESES WHERE " + Illneses.id_KEY + " =" + req_id;

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);
            while (queryResult.next()) {
                int id = queryResult.getInt(1);
                String name = queryResult.getString(2);
                String description = queryResult.getString(3);
                String doctor_type = queryResult.getString(4);
                Image cleanimg= ImageHandler.returnImage(queryResult.getBinaryStream(5));
                Image effectimg= ImageHandler.returnImage(queryResult.getBinaryStream(6));
                
                tmpIllness=new Illneses(id, name, description, doctor_type, cleanimg, effectimg);
                System.out.println("Retrieved successfully!");
                return tmpIllness;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }

    //Returns All Illneses without Images
    public static ArrayList<Illneses> returnAllillneses() {
        ArrayList<Illneses> tmparrayList = new ArrayList<>();
        
        Illneses tmpIllness;

        String sqlquery = "SELECT "+ Illneses.id_KEY+" ,"+ Illneses.name_KEY+" ,"+ Illneses.description_KEY+" ,"+ Illneses.doctor_type_KEY+" FROM  ILLNESES";

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);

            int count = 0;
            while (queryResult.next()) {
                  int id = queryResult.getInt(1);
                String name = queryResult.getString(2);
                String description = queryResult.getString(3);
                String doctor_type = queryResult.getString(4);
                
                tmpIllness=new Illneses(id, name, description, doctor_type);
                tmparrayList.add(tmpIllness);
                count++;
            }
            System.out.println("Retrieved successfully!");
            System.out.println("No of Retrieved ROWS are : " + count);
            return tmparrayList;

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }

    //Illneses table Operations//End
    
    
    
    
           //cases table Operations//Start
    public static req_info insertToCases(cases c) {

        String sqlquery = "INSERT INTO CASES (" + cases.id_KEY + ", "
                + cases.ill_id_KEY + ", " + cases.patient_id_KEY + ", "
                + cases.patient_session_id_KEY + ", " + cases.date_of_KEY + ", " + cases.notes_KEY 
                + ") VALUES (?,?,?,?,?,?)";

        req_info state;
        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery,Statement.RETURN_GENERATED_KEYS);
            //change back to object.getId() if removed the auto increment
            SQLstatement.setInt(1,0); 
            SQLstatement.setInt(2, c.getIllnes_id());
            SQLstatement.setInt(3, c.getPatient_id());
            SQLstatement.setInt(4, c.getPatient_session_id());
            SQLstatement.setDate(5, c.getDate_of());
            if(encryptionSwitch){
            SQLstatement.setString(6,doEncryption(c.getNotes()));
            }
            else{
            SQLstatement.setString(6,c.getNotes());}

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted successfully!");
                state=returnStatus(SQLstatement);
                state.setInserted(true);
                return state;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return fail;
    }

    public static boolean updateCases(cases c, cases old) {
        //not required ,currently isnot implemented
        return false;
    }

    public static boolean deleteFromCases(int id) {

        String sqlquery = "DELETE FROM  CASES WHERE " + cases.id_KEY + "=?";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, id);

            int rowsDeleted = SQLstatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Deleted successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }

        return false;
    }

    //Returns a single cases with description using the id
    public static cases returnCaseWithDescription(int req_id) {
        cases tmpcase;

        String sqlquery = "SELECT * FROM  CASES WHERE " + cases.id_KEY + " =" + req_id;

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);
            while (queryResult.next()) {
                int id = queryResult.getInt(1);
                int illnes_id = queryResult.getInt(2);
                int patient_id = queryResult.getInt(3);
                int patient_session_id = queryResult.getInt(4);
                Date date_of= queryResult.getDate(5);
                String notes;
                if(encryptionSwitch){
                notes=doDecryption(queryResult.getString(6));
                }
                else
                {
                notes=queryResult.getString(6);
                }
                
                tmpcase = new cases(id, illnes_id, patient_id, patient_session_id, date_of, notes);
                System.out.println("Retrieved successfully!");
                return tmpcase;
            }

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }

    //Returns All cases without description
    public static ArrayList<cases> returnAllCases() {
        ArrayList<cases> tmparrayList = new ArrayList<>();
        
        cases tmpCase;

        String sqlquery = "SELECT "+ cases.id_KEY+" ,"+ cases.ill_id_KEY+" ,"+ cases.patient_id_KEY+" ,"
                + cases.patient_session_id_KEY+" ,"+cases.date_of_KEY+" FROM  CASES";

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);

            int count = 0;
            while (queryResult.next()) {
                
                int id = queryResult.getInt(1);
                int illnes_id = queryResult.getInt(2);
                int patient_id = queryResult.getInt(3);
                int patient_session_id = queryResult.getInt(4);
                Date date_of= queryResult.getDate(5);
                
                tmpCase = new cases(id, illnes_id, patient_id, patient_session_id, date_of);
                tmparrayList.add(tmpCase);
                count++;
            }
            System.out.println("Retrieved successfully!");
            System.out.println("No of Retrieved ROWS are : " + count);
            return tmparrayList;

        } catch (SQLException e) {

            System.out.println(e);
            //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");
        }
        return null;
    }

    //Cases table Operations//End
    
    
    public static int getCountforTable(String tableName){
    String sqlquery = "SELECT COUNT(ID) FROM  "+tableName;
       int count = 0;
        try (Connection tmp = connectDB()) {
            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);
            while (queryResult.next()) { 
            count=queryResult.getInt(1);}
            return count;
        } catch (SQLException e) {
            System.out.println(e);
             //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");}
        return 0;}
    
    
    public static req_info returnStatus(Statement s) throws SQLException{
        int id = 0;
        req_info tmp=new req_info();
        
        try (ResultSet rs = s.getGeneratedKeys()) {
            if (rs.next()){
                id=rs.getInt(1);
            }       }
        tmp.setId(id);
        
        return tmp;
    }
    
    //to caculate the duration for patient session
    public static String caculateDuration(LocalTime sooner,LocalTime later){
        Duration duration = Duration.between ( sooner, later );
        
        return duration.toString();
    }
    
    public static int getIDofCaseBySession(int session_id){
    
    String sqlquery = "SELECT ID FROM  "+cases.Table_Name+" WHERE "+cases.patient_session_id_KEY +"="+session_id;
       int id = 0;
        try (Connection tmp = connectDB()) {
            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);
            while (queryResult.next()) { 
            id=queryResult.getInt(1);}
            return id;
        } catch (SQLException e) {
            System.out.println(e);
             //logger
            logger.appendnewLog(e.toString());
            System.out.println("Fail!");}
        return 0;
    }


}
