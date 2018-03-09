/*
 * Copyright 2018 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package edoctorjava;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author rawan
 */
public class requirements {

    private static final String url = "jdbc:mysql://localhost:3306/eDoctor";
    private static final String username = "root";
    private static final String password = "root";

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

    //patient table Operations//Endd
}
