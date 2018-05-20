<?php include('server.php') ?>
<!DOCTYPE html>
<html>
<head>
	<title>Registration system PHP and MySQL</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="header">
	<h3><img src="user_add.png" width="150" height="150" /></h3>
		<h2>Register</h2>
	</div>
	
	<form method="post" action="register.php">

		<?php include('errors.php'); ?>
		<div class="input-group">
			<label>First Name</label>
			<input required=""  type="text" name="First_name" value="<?php echo $username; ?>">
		</div>
		<div class="input-group">
		<label>Last Name</label>
			<input required=""  type="text" name="Last_name" value="<?php echo $username; ?>">
		</div>
		<div class="input-group">
		<label>Gender</label>
			<select name="Gender">
				<option value="Male">Male</option>
				<option value="Female">Female</option>
				
			</select>
		</div>
		<div class="input-group">
		<label>Date Of Birthday</label>
			<input required=""  type="date" name="Date_of_Birthday" data-date-format="DD-MM-YYYY" value="2018-01-01">
		</div>
		<div class="input-group">
			<label>Email</label>
			<input required=""  type="email" name="email" value="<?php echo $email; ?>">
		</div>
		<div class="input-group">
			<label>Address</label>
			<input required=""  type="text" name="Address" value="<?php echo $email; ?>">
		</div>
		<div class="input-group">
			<label>Phone_No</label>
			<input required=""  type="number" name="Phone_number" value="<?php echo $email; ?>">
		</div>
		<div class="input-group">
			<label>Info</label>
			<input required=""  type="text" name="info" value="<?php echo $email; ?>">
		</div>
		<div class="input-group">
			<label>Username</label>
			<input required=""  type="text" name="username" value="<?php echo $username; ?>">
		</div>
		
		<div class="input-group">
			<label>Password</label>
			<input required=""  type="password" name="password_1">
		</div>
		<div class="input-group">
			<label>Confirm password</label>
			<input required=""  type="password" name="password_2">
		</div>
		<div class="input-group">
			<button type="submit" class="btn" name="reg_user">Register</button>
		</div>
		<p>
			Already a member? <a href="login.php">Sign in</a>
		</p>
	</form>
</body>
</html>