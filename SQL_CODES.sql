create database edoctor;
use edoctor;
   /* This Stores Doctors INFORMATION*/
   CREATE TABLE  DOCTOR 
   (    ID INTEGER AUTO_INCREMENT,
   FIRST_NAME VARCHAR(30), 
    LAST_NAME VARCHAR(30), 
    GENDER VARCHAR(8), 
    DATE_OF_BIRTH DATE, 
    EMAIL VARCHAR(100), 
    PHONE_NO VARCHAR(20),
    SPECIALTY VARCHAR(50), 
    TYPE VARCHAR(20), 
    NAME VARCHAR(60) NOT NULL , 
    PASSWORD VARCHAR(200) NOT NULL ,
     PRIMARY KEY (ID) 
	 
   );
   
      /* This Stores Receptionist INFORMATION*/
   CREATE TABLE  RECEPTIONIST 
   (    ID INTEGER AUTO_INCREMENT, 
	FIRST_NAME VARCHAR(30), 
    LAST_NAME VARCHAR(30), 
    GENDER VARCHAR(8), 
    DATE_OF_BIRTH DATE, 
    EMAIL VARCHAR(100), 
    PHONE_NO VARCHAR(20), 
    SALARY DOUBLE,
    USER_NAME VARCHAR(60) NOT NULL, 
    PASSWORD VARCHAR(200) NOT NULL ,
    DISCHARGED BOOLEAN,
     PRIMARY KEY (ID)
   );
   
      /* This Stores Patients INFORMATION*/
   CREATE TABLE  PATIENT 
   (    ID INTEGER AUTO_INCREMENT, 
    FIRST_NAME VARCHAR(30), 
    LAST_NAME VARCHAR(30), 
    GENDER VARCHAR(8), 
    DATE_OF_BIRTH DATE, 
    EMAIL VARCHAR(100), 
    ADDRESS VARCHAR(100), 
    PHONE_NO VARCHAR(20), 
    INFO VARCHAR(1000),
    USER_NAME VARCHAR(60) NOT NULL UNIQUE , 
    PASSWORD VARCHAR(200) NOT NULL ,
     PRIMARY KEY (ID) 
   );

      /* This Stores Appointments INFORMATION*/
   CREATE TABLE APPOINTMENT (
  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  PATIENT_ID INTEGER DEFAULT NULL,
  foreign key (PATIENT_ID) references PATIENT(id),
  DOCTOR_ID INTEGER DEFAULT NULL,
  foreign key (DOCTOR_ID) references DOCTOR(id),
  DATE_OF_APPOINTMENT date DEFAULT NULL
);
   
      /* This Stores Patient prescriptions INFORMATION*/
   CREATE TABLE  PRESCRIPTION 
   (    ID INTEGER AUTO_INCREMENT, 
    DOSAGE VARCHAR(100), 
    DETAILS VARCHAR(2000), 
    FROM_DATE DATE, 
    TO_DATE DATE,
     PRIMARY KEY (ID) 
   ) ;
   
      /* This Stores patient session INFORMATION*/
   CREATE TABLE  PATIENT_SESSION 
   (    ID INTEGER AUTO_INCREMENT, 
    APPOINTMENT_ID INTEGER , 
    foreign key (appointment_id) references APPOINTMENT(id),
    DURATION time, 
    PRESCRIPTION INTEGER ,
    foreign key (prescription) references PRESCRIPTION(id),
     COST DOUBLE NOT NULL,
     DESCRIPTION VARCHAR(1500),
     PRIMARY KEY (ID)
   );

   
      /* This Stores temporary/requested/pending patient appointments INFORMATION*/
   
   CREATE TABLE  REQUESTED_APPOINTMENT 
   (    ID INTEGER AUTO_INCREMENT, 
    PATIENT_ID INTEGER NOT NULL , 
    DOCTOR_ID INTEGER NOT NULL , 
    DATE_OF_APPOINTMENT DATE NOT NULL ,
    STATUS BOOLEAN, 
     PRIMARY KEY (ID) , 
     UNIQUE (PATIENT_ID, DOCTOR_ID, DATE_OF_APPOINTMENT) 
   ) ;
      /* Designed By Rawand F. 
      LAST_UPDATE: 15th May 2018 :0336p
      */
