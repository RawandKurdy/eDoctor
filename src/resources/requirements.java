/*
 * Copyright 2018 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package resources;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rawan
 */
public class requirements {

    private static final String url = "jdbc:mysql://localhost:3306/eDoctor";
    private static final String username = "root";
    private static final String password = "root";
    private static final byte[] encryptionKey = "MZygpewJsCpRrfOr".getBytes(StandardCharsets.UTF_8);
    private static boolean encryptionSwitch=true;

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
        }
        return null;
    }

    //patient table Operations//Start
    public static boolean insertToPatient(patient p) {
        System.out.println(p);
        String sqlquery = "INSERT INTO Patient (" + patient.id_KEY + ", " + patient.first_Name_KEY + ", " + patient.last_Name_KEY + ", "
                + patient.gender_KEY + ", " + patient.dateOfBirth_KEY + ", " + patient.email_KEY + ", " + patient.address_KEY + ", " + patient.phone_Number_KEY
                + ", " + patient.info_KEY + ", " + patient.user_Name_KEY + ", " + patient.password_KEY + ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, p.getId());
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
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            System.out.println("Fail!");
        }

        return false;
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
            System.out.println("Fail!");
        }
        return null;
    }

    //patient table Operations//End
    
    
    
    
    //Receptionist table Operations//Start
    
    
    //Add a new receptionist
    public static boolean insertToReceptionist(receptionist r) {
        System.out.println(r);
        String sqlquery = "INSERT INTO Receptionist (" + receptionist.id_KEY + ", " + receptionist.first_Name_KEY + ", " + receptionist.last_Name_KEY + ", "
                + receptionist.gender_KEY + ", " + receptionist.dateOfBirth_KEY + ", " + receptionist.email_KEY + ", " + receptionist.phone_Number_KEY + ", " + receptionist.salary_KEY
                + ") VALUES (?,?,?,?,?,?,?,?)";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, r.getId());
            SQLstatement.setString(2, r.getFirst_Name());
            SQLstatement.setString(3, r.getLast_Name());
            SQLstatement.setString(4, r.getGender());
            SQLstatement.setDate(5, r.getDateOfBirth());
            SQLstatement.setString(6, r.getEmail());
            SQLstatement.setString(7, r.getPhone_Number());
            SQLstatement.setDouble(8, r.getSalary());

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            System.out.println("Fail!");
        }

        return false;
    }

    //update an existing receptionist
    public static boolean updateReceptionist(receptionist r, receptionist old) {
        System.out.println(r);
        String sqlquery = "UPDATE Receptionist SET " + receptionist.first_Name_KEY + "=? ,"
                + receptionist.last_Name_KEY + "=? ," + receptionist.gender_KEY + "=? ," + receptionist.dateOfBirth_KEY + "=? ," + receptionist.email_KEY + "=? ," + receptionist.phone_Number_KEY + "=? ,"
                + receptionist.salary_KEY + "=? " + "WHERE " + receptionist.id_KEY + "=? ";
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

            SQLstatement.setInt(8, r.getId());
            
            //debugging stufff will be cleaned later if needed
            System.out.println(SQLstatement.toString());

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Updated successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
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
               tmpreceptionist = new receptionist(id, first_Name, last_Name, gender, dateOfBirth, email, phone_Number, salary);
               System.out.println("Retrieved successfully!");
                return tmpreceptionist;
            }

        } catch (SQLException e) {

            System.out.println(e);
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
               tmpreceptionist = new receptionist(id, first_Name, last_Name, gender, dateOfBirth, email, phone_Number, salary);
               tmparrayList.add(tmpreceptionist);
                count++;
            }
            System.out.println("Retrieved successfully!");
            System.out.println("No of Retrieved ROWS are : " + count);
            return tmparrayList;

        } catch (SQLException e) {

            System.out.println(e);
            System.out.println("Fail!");
        }
        return null;
    }
    
    
    //Receptionist table Operations///End
    
    //Doctor table Operations//Start
    public static boolean insertToDoctor(doctor d) {
        System.out.println(d);
        String sqlquery = "INSERT INTO Doctor (" + doctor.id_KEY + ", " + doctor.first_Name_KEY + ", " + doctor.last_Name_KEY + ", "
                + doctor.gender_KEY + ", " + doctor.dateOfBirth_KEY + ", " + doctor.email_KEY + ", " + doctor.phone_Number_KEY + ", " + doctor.specialty_KEY
                + ", " + doctor.type_KEY + ", " + doctor.user_Name_KEY + ", " + doctor.password_KEY + ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, d.getId());
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
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            System.out.println("Fail!");
        }

        return false;
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
            System.out.println("Fail!");
        }
        return null;
    }

    //Doctor table Operations//End
    
    //AES Encryption//Start
    
    public static String doEncryption(String txt){
    AdvancedEncryptionStandard advancedEncryptionStandard = new AdvancedEncryptionStandard(
        encryptionKey);
    
    if(!txt.equals("") & txt!=null){
        byte[] plainText = txt.getBytes(StandardCharsets.UTF_8);
        try {
            byte[] cipherText = advancedEncryptionStandard.encrypt(plainText);
           return new String(cipherText);
        } catch (Exception ex) {
            Logger.getLogger(requirements.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    return "";}
    
      public static String doDecryption(String txt){
    AdvancedEncryptionStandard advancedEncryptionStandard = new AdvancedEncryptionStandard(
        encryptionKey);
    
    if(!txt.equals("") & txt!=null){
        byte[] cipherText = txt.getBytes(StandardCharsets.UTF_8);
        try {
            byte[] decryptedCipherText = advancedEncryptionStandard.decrypt(cipherText);
           return new String(decryptedCipherText);
        } catch (Exception ex) {
            Logger.getLogger(requirements.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    return "";}
    
    
    //AES Encryption//End
      
      
      
    //Prescription operations//Start
      
      //insert an entry to prescription
    public static boolean insertToPrescription(prescription p) {
        System.out.println(p);
        String sqlquery = "INSERT INTO PRESCRIPTION (" + prescription.id_KEY + ", " + prescription.dosage_KEY + ", " + prescription.details_KEY + ", "
                + prescription.fromDate_KEY + ", " + prescription.toDate_KEY + ") VALUES (?,?,?,?,?)";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, p.getId());
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
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            System.out.println("Fail!");
        }

        return false;
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
            System.out.println("Fail!");
        }
        return null;
    }
      
      
    
    //Prescription operations//End
      
    
    //Appointment operations//Start
    
    //Inserts an appointment & And also can be used with getAppointment later to interchange
   //requested appointments and actual appointments tables which is nearly identical interm of structure
    public static boolean insertToAppointment(appointment a) {
        System.out.println(a);
        String sqlquery = "INSERT INTO appointment (" + appointment.id_KEY + ", " + appointment.patient_id_KEY + ", " + appointment.doctor_id_KEY + ", "
                + appointment.date_KEY + ") VALUES (?,?,?,?)";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, a.getId());
            SQLstatement.setInt(2, a.getPatient_id());
            SQLstatement.setInt(3, a.getDoctor_id());
            SQLstatement.setDate(4, a.getDate());
            
            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
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
            System.out.println("Fail!");
        }
        return null;
    }

    //Returns All Appointments 
    public static ArrayList<appointment> returnAllAppointment() {
        ArrayList<appointment> tmparrayList = new ArrayList<>();

        appointment tmpAppointment;

        String sqlquery = "SELECT * FROM  appointment";

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
               
                 tmparrayList.add(tmpAppointment);
                count++;
            }
            System.out.println("Retrieved successfully!");
            System.out.println("No of Retrieved ROWS are : " + count);
            return tmparrayList;

        } catch (SQLException e) {

            System.out.println(e);
            System.out.println("Fail!");
        }
        return null;
    }
    
    //Appointment operations//End
    
    //receptionist_credentials operations//Start
    
    //Insert or creates access for a receptionist to use the app
    public static boolean insertToReceptionist_credentials(receptionist_credentials r) {
        System.out.println(r);
        String sqlquery = "INSERT INTO RECEPTIONIST_CREDENTIALS (" + receptionist_credentials.id_KEY + ", " + receptionist_credentials.username_KEY + ", " + receptionist_credentials.password_KEY + ", "
                + receptionist_credentials.receptionist_id_KEY +", "+receptionist_credentials.discharged_KEY+ ") VALUES (?,?,?,?,?)";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            SQLstatement.setInt(1, r.getId());
            SQLstatement.setString(2, r.getUsername());
            SQLstatement.setString(3, MD5(r.getPassword()));
            SQLstatement.setInt(4, r.getReceptionist_id());
            SQLstatement.setBoolean(5, r.isDischarged()); //Sets the boolean value
            
            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            System.out.println("Fail!");
        }

        return false;
    }
    //updates an existing credential for a receptionist
    public static boolean updateReceptionist_credentials(receptionist_credentials r, receptionist_credentials old) {
        System.out.println(r);
        String sqlquery = "UPDATE RECEPTIONIST_CREDENTIALS SET " + receptionist_credentials.username_KEY + "=? ,"
                + receptionist_credentials.password_KEY + "=? ," + receptionist_credentials.receptionist_id_KEY + "=? "+receptionist_credentials.discharged_KEY + "=? "+ "WHERE " + receptionist_credentials.id_KEY + "=? ";

        try (Connection tmp = connectDB()) {

            PreparedStatement SQLstatement = tmp.prepareStatement(sqlquery);
            if (!r.getUsername().equals(old.getUsername())) {
                SQLstatement.setString(1, r.getUsername());
            } else {
                SQLstatement.setString(1, old.getUsername());
            }
            
            if (r.getPassword()!= null & !r.getPassword().equals("")) {
                 SQLstatement.setString(2,MD5(r.getPassword()));
          
            } else {
                 SQLstatement.setString(2, old.getPassword());
   
            }

            if (r.getReceptionist_id()!=old.getReceptionist_id()) {
                SQLstatement.setInt(3, r.getReceptionist_id());
            } else {
                SQLstatement.setInt(3, old.getReceptionist_id());
            }
            
             SQLstatement.setBoolean(4, r.isDischarged());
            //In GUI we must consider setting the old value to a location which the user can easily see
            //old state
            
            
            SQLstatement.setInt(5, r.getId());
            
            //debug stuff
            System.out.println(SQLstatement.toString());

            int rowsInserted = SQLstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Updated successfully!");
                return true;
            }

        } catch (SQLException e) {

            System.out.println(e);
            System.out.println("Fail!");
        }

        return false;
    }

    //deletes an access of a receptionist
    public static boolean deleteFromReceptionist_credentials(int id) {

        String sqlquery = "DELETE FROM  RECEPTIONIST_CREDENTIALS WHERE " + receptionist_credentials.id_KEY + "=?";

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
            System.out.println("Fail!");
        }

        return false;
    }

    //Returns the access of a receptionist
    public static receptionist_credentials returnReceptionist_credentials(int req_id) {
        receptionist_credentials tmpReceptionist_credentials;

        String sqlquery = "SELECT * FROM  RECEPTIONIST_CREDENTIALS WHERE " + receptionist_credentials.id_KEY + " =" + req_id;

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);
            while (queryResult.next()) {
                     int id=queryResult.getInt(1);
                     String username=queryResult.getString(2);
                     String password=queryResult.getString(3);
                     int receptionist_id=queryResult.getInt(4);
                     boolean discharged=queryResult.getBoolean(5);
                     tmpReceptionist_credentials = new receptionist_credentials(id, username, password, receptionist_id, discharged);
                System.out.println("Retrieved successfully!");
                return tmpReceptionist_credentials;
            }

        } catch (SQLException e) {

            System.out.println(e);
            System.out.println("Fail!");
        }
        return null;
    }

  // Returns the access of a All receptionist
   //has no use at the moment (nothing more than select command)
    
    public static ArrayList<receptionist_credentials> returnAllReceptionist_credentials() {
        ArrayList<receptionist_credentials> tmparrayList = new ArrayList<>();

        receptionist_credentials tmpReceptionist_credentials;

        String sqlquery = "SELECT * FROM  RECEPTIONIST_CREDENTIALS";

        try (Connection tmp = connectDB()) {

            Statement SQLstatement = tmp.createStatement();
            ResultSet queryResult = SQLstatement.executeQuery(sqlquery);

            int count = 0;
            while (queryResult.next()) {  
                     int id=queryResult.getInt(1);
                     String username=queryResult.getString(2);
                     String password=queryResult.getString(3);
                     int receptionist_id=queryResult.getInt(4);
                     boolean discharged=queryResult.getBoolean(5);
                     tmpReceptionist_credentials = new receptionist_credentials(id, username, password, receptionist_id, discharged);
               
               
                 tmparrayList.add(tmpReceptionist_credentials);
                count++;
            }
            System.out.println("Retrieved successfully!");
            System.out.println("No of Retrieved ROWS are : " + count);
            return tmparrayList;

        } catch (SQLException e) {

            System.out.println(e);
            System.out.println("Fail!");
        }
        return null;
    }
    
    //receptionist_credentials operations//End
}
