<?php 
$id = "id";
if(!isset($_COOKIE[$id]) AND $_COOKIE[$id]==""){ header('Location: login.php'); exit; }

	if (isset($_GET['logout'])) {
	    unset($_COOKIE['id']);
	    setcookie('id', null, time() - (86400 * 30 * 1), '/');
	    header("Location: login.php");
	    exit;
	}

?>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" href=" dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href=" dist/css/skins/skin-blue.css.css">

</head>
<body>
	
	<div class="content">

		<!-- notification message -->
			<div class="error success" >
				<h3>
					Request an Appointment
				</h3>
			</div>

        <form id="f" action="appointment_requist.php" method="GET" style="">
            
             
           
 <div class="form-group">
                <label>Date:</label>

                <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="date" name="date"  >
				  
                </div>
				<label>Doctor's Name:</label>
				<div class="input-group">
					<select name="doctorID">
						<option value="1">Bone(Radiography)</option>
						<option value="2">Dentistry</option>
					</select>
				</div>
                <!-- /.input group -->
              </div>
                            <div class="bootstrap-timepicker">
                <div class="form-group">

                  <div class="input-group">
				                      <div class="input-group-addon">
                      <i class="fa fa-clock-o"></i>
                    </div>


                  </div>
                  <!-- /.input group -->
                </div>
                <!-- /.form group -->
              </div>
            <div>patient ID: </div>
            <div><input type="text" id="name" name="name" value="<?=$_COOKIE[$id];?>" /></div>

            <div class="space"><input type="submit" value="Save" /></div>
        </form>

        <script type="text/javascript">

        $("#f").submit(function () {
            var f = $("#f");
            $.post(f.attr("action"), f.serialize(), function (result) {
                DayPilot.Modal.close(result);
            });
            return false;
        });

        $("#cancel").click(function() {
            DayPilot.Modal.close();
            return false;
        });

        $(document).ready(function () {
            $("#name").focus();
        });

        </script>
    
		<!-- logged in user information -->
		<?php  if (isset($_COOKIE[$id])) : ?>
			<p>Welcome <strong><?php echo $_COOKIE[$id]; ?></strong></p>
			<p> <a href="index.php?logout='1'" style="color: red;">logout</a> </p>
		<?php endif ?>
	</div>
		
</body>


</html>