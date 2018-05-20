<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Request an Appointment</title>

    </head>
    <body>
        <?php

$id = "id";
$patient_id = $_COOKIE['id'];
$dateAndTime = $_GET['date'];
$doctorID = $_GET['doctorID'];


$servername = "localhost";
$username = "root";
$password = "root";
$dbname = "edoctor";
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
echo "Connected successfully \n";

$presql = "SELECT id FROM patient WHERE USER_NAME = '".$patient_id."';";
$result = $conn->query($presql);
$row = $result->fetch_assoc();
$idofpatient = $row["id"];
	$sql ="INSERT INTO requested_appointment  (PATIENT_ID, DOCTOR_ID, DATE_OF_APPOINTMENT)
VALUES ( ".$idofpatient." , ".$doctorID.", '".$dateAndTime."')";

if ($conn->query($sql) === TRUE) {
    echo "Your Appointment have been successfully requested \n". "<br>";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();

        ?>
       
</html>
