/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package resources;

import java.util.Objects;

/**
 *Validation Class
 * Have many different type of validation checks
 * @author rawan
 */
public class validation {

    public String errormsg;

    public validation() {
        errormsg = "";
    }

    /**check if the input value is not null and not empty plus
     * then if its a number
     * 
     * @param nameofField
     * @param input
     * @return 
     */
    public boolean isItaNum(String nameofField, String input) {
        boolean nullcheck = isNotNullandEmpty(nameofField, input);

        if (nullcheck) {
            try {
                double a = Double.valueOf(input);
                return true;

            } catch (NumberFormatException e) {
                errormsg += nameofField + " entered value is not correct \n";
                logger.appendnewLog("wrong input have been detected for " + nameofField + " field");
                return false;
            }
        } else {
            return nullcheck;
        }
    }

    /**only checks if not null and not empty
     * 
     * @param nameofField
     * @param input
     * @return 
     */
    public boolean isNotNullandEmpty(String nameofField, String input) {
        if (input == null || input.trim().isEmpty()) {
            errormsg += nameofField + " is empty \n";
            logger.appendnewLog("null input have been detected for " + nameofField + " field");
            return false;
        } else {
            return true;
        }

    }

    /**email validator checks if the email format is correct
     * and the input is not null and not empty
     * @param nameofField
     * @param input
     * @return 
     */
    public boolean emailValidator(String nameofField, String input) {
        boolean nullcheck = isNotNullandEmpty(nameofField, input);
        if (nullcheck) {
            if (input.contains("@")) {
                return true;
            } else {
                errormsg += nameofField + " is an Email field ,wrong value detected \n";
                logger.appendnewLog("wrong input have been detected for " + nameofField + " field");
                return false;
            }

        } else {
            return nullcheck;
        }

    }
    
    /**checks if the object is not null
     * 
     * @param nameofField
     * @param ob
     * @return 
     */
    public boolean objectNullCheck(String nameofField,Object ob){
        try {
            Objects.requireNonNull(ob);
            return true;
        } catch (NullPointerException e) {
            errormsg += nameofField + " empty value detected \n";
            logger.appendnewLog("empty input have been detected for " + nameofField + " field");
            return false;
        }
    }
    
    /**phone number validator
     * 
     * @param nameofField
     * @param input
     * @return 
     */
    public boolean phoneNumberValidator(String nameofField,String input){
       boolean nullcheck=isNotNullandEmpty(nameofField, input);
        if(nullcheck){
        if(input.matches(".*[a-zA-Z]+.*")){
        errormsg += nameofField + " wrong value detected \n";
        logger.appendnewLog("wrong input value have been detected for " + nameofField + " field");
            return false;
        }
        
        else
            return true;
    
        
        }
        else
            return nullcheck;
    }
}
