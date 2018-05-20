/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author rawan
 */
public class dbUtility {
    final String dbFileName="eDoctorsDump.sql";
    private static final String username = "root";
    private static final String password = "root";
    private static final String dbName="eDoctor";
    
    public boolean exportDB(String path){
        try {
            File backupFile = new File(path+File.separator+dbFileName);
             String[] command = new String[]{"mysqldump ", "-u"+username, "-p"+password, dbName};

        ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(command));
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.to(backupFile));

        Process process = processBuilder.start();
        
        BufferedReader r=new BufferedReader(new InputStreamReader(process.getInputStream()));
    String s;
    String full="";
    while((s=r.readLine())!=null)
    {
        full+=s;
        logger.appendnewLog(s);
        System.out.println(s);
    }
        
        process.waitFor();
            return true;
        } catch (Exception e) {
            logger.appendnewLog(e.getMessage());
            return false;
        }
    
    }
    
    public boolean importDB(String path){
        try {
        File backupFile = new File(path);
        String[] command= new String[]{"mysql ", "-u"+username, "-p"+password, dbName};
        ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(command));;
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectInput(ProcessBuilder.Redirect.from(backupFile));

        Process process = processBuilder.start();
        
        BufferedReader r=new BufferedReader(new InputStreamReader(process.getInputStream()));
    String s;
    String full="";
    while((s=r.readLine())!=null)
    {
        full+=s;
        logger.appendnewLog(s);
        System.out.println(s);
    }
process.waitFor();
            return true;
        } catch (Exception e) {
            logger.appendnewLog(e.getMessage());
            return false;
        }
    }
    
    
}
