<?php include('server.php');
$id = "id";
if(isset($_COOKIE[$id]) AND $_COOKIE[$id]!=""){ header('Location: index.php'); exit; }
 ?>
<!DOCTYPE html>
<html>
<head>
	<title>Registration system PHP and MySQL</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

	<div class="header">
		<h2><img src="logomain.png" width="300" height="150" /></h2>
		<h3>Login</h3>
	</div>
	
	<form method="post" action="login.php">

		<?php include('errors.php'); ?>

		<div class="input-group">
			<label>Username</label>
			<input required="" type="text" name="username" >
		</div>
		<div class="input-group">
			<label>Password</label>
			<input required="" type="password" name="password">
		</div>
		<div class="input-group">
			<button type="submit" class="btn" name="login_user">Login</button>
		</div>
		<p>
			Not yet a member? <a href="register.php">Sign up</a>
		</p>
	</form>


</body>
</html>