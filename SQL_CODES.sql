create database edoctor;
use edoctor;
   /* This Stores Doctors INFORMATION*/
   CREATE TABLE  DOCTOR 
   (    ID INTEGER,
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
   (    ID INTEGER, 
	FIRST_NAME VARCHAR(30), 
    LAST_NAME VARCHAR(30), 
    GENDER VARCHAR(8), 
    DATE_OF_BIRTH DATE, 
    EMAIL VARCHAR(100), 
    PHONE_NO VARCHAR(20), 
    SALARY DOUBLE,
    USER_NAME VARCHAR(60) NOT NULL, 
    PASSWORD VARCHAR(200) NOT NULL ,
    DISCHARGED BOOLEAN
     PRIMARY KEY (ID)
   );
   
      /* This Stores Patients INFORMATION*/
   CREATE TABLE  PATIENT 
   (    ID INTEGER, 
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
   CREATE TABLE `appointment` (
  `ID` int(11) NOT NULL,
  `PATIENT_ID` int(11) DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `DATE_OF_APPOINTMENT` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
   
      /* This Stores Patient prescriptions INFORMATION*/
   CREATE TABLE  PRESCRIPTION 
   (    ID INTEGER, 
    DOSAGE VARCHAR(100), 
    DETAILS VARCHAR(2000), 
    FROM_DATE DATE, 
    TO_DATE DATE,
     PRIMARY KEY (ID) 
   ) ;
   
      /* This Stores patient session INFORMATION*/
   CREATE TABLE  PATIENT_SESSION 
   (    ID INTEGER, 
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
   (    ID INTEGER, 
    PATIENT_ID INTEGER NOT NULL , 
    DOCTOR_ID INTEGER NOT NULL , 
    DATE_OF_APPOINTMENT DATE NOT NULL , 
     PRIMARY KEY (ID) , 
     UNIQUE (PATIENT_ID, DOCTOR_ID, DATE_OF_APPOINTMENT) 
   ) ;
      /* Designed By Rawand F. 
      LAST_UPDATE: 3rd April 2018 :0236p
      */
