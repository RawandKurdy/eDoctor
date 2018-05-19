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
public class valueHolder {
    //used when transfering data between scenes because primitive data types doesnt support referencing
    int number;
    double doubleNumber;
    boolean b;
    float f;

    public valueHolder() {
    }
    

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getDoubleNumber() {
        return doubleNumber;
    }

    public void setDoubleNumber(double doubleNumber) {
        this.doubleNumber = doubleNumber;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }
    
    
}
