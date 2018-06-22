<?php include('server.php');?>

<!DOCTYPE html>
<html lang="pt-pt">
<head>
<title>Interface Web</title>
<link rel="stylesheet" type="text/css" href="styleLogin.css">
</head>
<body>
<img src="logo.png" width="100" height="80">
<div class ="header">
	<h2 style="color:white">Login</h2>
</div>
<form method="post">
	<?php include('erros.php'); ?>
	<div class="input-group">
		<label>Username</label>
		<br>
		<input type="text" name="username">
	</div>
		<br>
	<div class="input-group">
		<label>Password</label>
		<br>
		<input type="password" name="pass">
	</div>
	<br>
	<div class="input-group">
		<button type="submit" name="login" class="btn">Login</button>
	</div>

</form>
</body>
</html>