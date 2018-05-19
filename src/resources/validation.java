/*
 * Copyright 2017 Rawand Kurdy 
 * Please dont reuse or change anything in this materials unless you got permissions from the coder ##RKY 
 * If you find any violation , please pay a visit to our site and contact us //www.rky.cu.cc
 */
package resources;

/**
 *
 * @author rawan
 */
public class validation {

    public String errormsg;

    public validation() {
        errormsg = "";
    }

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

    public boolean isNotNullandEmpty(String nameofField, String input) {
        if (input == null || input.trim().isEmpty()) {
            errormsg += nameofField + " is empty \n";
            logger.appendnewLog("null input have been detected for " + nameofField + " field");
            return false;
        } else {
            return true;
        }

    }

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
}
