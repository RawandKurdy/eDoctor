<?php 
	session_start();

	// variable declaration
	
	$username = "";
	$email    = "";
	$errors = array(); 

	// connect to database
	$edoctor = mysqli_connect('localhost', 'root', 'root', 'edoctor');

	// REGISTER USER
	if (isset($_POST['reg_user'])) {
		// receive all input values from the form
	
		$username = mysqli_real_escape_string($edoctor, $_POST['username']);
		$email = mysqli_real_escape_string($edoctor, $_POST['email']);
		$password_1 = mysqli_real_escape_string($edoctor, $_POST['password_1']);
		$password_2 = mysqli_real_escape_string($edoctor, $_POST['password_2']);
		$Fn = mysqli_real_escape_string($edoctor, $_POST['First_name']);
		$ln = mysqli_real_escape_string($edoctor, $_POST['Last_name']);
		$Gen = mysqli_real_escape_string($edoctor, $_POST['Gender']);
		$date2 = mysqli_real_escape_string($edoctor, $_POST['Date_of_Birthday']);
		$Add = mysqli_real_escape_string($edoctor, $_POST['Address']);
		$Phone = mysqli_real_escape_string($edoctor, $_POST['Phone_number']);
		$info = mysqli_real_escape_string($edoctor, $_POST['info']);
		
		
		

		// form validation: ensure that the form is correctly filled
		if (empty($username)) { array_push($errors, "Username is required"); }
		if (empty($email)) { array_push($errors, "Email is required"); }
		if (empty($password_1)) { array_push($errors, "Password is required"); }

		if ($password_1 != $password_2) {
			array_push($errors, "The two passwords do not match");
		}
		// register user if there are no errors in the form
		if (count($errors) == 0) {
			$password = md5($password_1); //md5 password
			$query = "INSERT INTO patient (id,FIRST_NAME,LAST_NAME,ADDRESS,PHONE_NO,INFO,USER_NAME, EMAIL, PASSWORD,Gender , DATE_OF_BIRTH ) 
					  VALUES(NULL, '$Fn', '$ln','$Add','$Phone','$info','$username', '$email','$password','$Gen', '$date2')";
			mysqli_query($edoctor, $query);
			header('location: index.php');
            $coookieName = 'id';
            $cookieValue = $username;
            $cookieTime = time() + (87600 * 365 * 1);
            setcookie($coookieName, $cookieValue, $cookieTime, "/");
            header('location: index.php');
            echo 'Logged in';
		}

	}

	// ... 

	// LOGIN USER
	if (isset($_POST['login_user'])) {
		$username = mysqli_real_escape_string($edoctor, $_POST['username']);
		$password = mysqli_real_escape_string($edoctor, $_POST['password']);
		$password =md5($password);

            $sql = "SELECT * FROM patient WHERE USER_NAME = '$username' AND PASSWORD = '$password'";
            $result = mysqli_query($edoctor, $sql);
            if (@mysqli_num_rows($result) > 0) {
                while($row = mysqli_fetch_assoc($result)) {
                        // Heshtnawa bo maway yak Sal
                        $coookieName = 'id';
                        $cookieValue = $row['USER_NAME'];
                        $cookieTime = time() + (87600 * 365 * 1);
                        setcookie($coookieName, $cookieValue, $cookieTime, "/");
                        header('location: index.php');
                        echo 'Logged in';
                }
              }else {
                echo 'Username or Password wrong!';
              }
	}

?>