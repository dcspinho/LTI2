<?php
session_start();

$erros = array();
$username = "";
$password = "";

//$db = mysqli_connect('192.168.43.75:3306','Todos','','bd') or die("Impossivel conectar");
$db = mysqli_connect('localhost','root','','bd') or die("Impossivel conectar"); 
//echo"ECHO";

 if (isset($_POST['login'])){

	$username = mysqli_real_escape_string($db,$_POST['username']);
	$password = mysqli_real_escape_string($db,$_POST['pass']);

	if (empty($username)) {
	  	array_push($erros, "Inserir nome de utilizador");
	}
	 if (empty($password)) {
	  	array_push($erros, "Inserir password");
	 }

	 if (count($erros) == 0) {
  	$password = $password;
  	$query = "SELECT * FROM utilizador WHERE nome_utilizador='$username' AND password='$password'";
    $verificaT = "SELECT cod_tipo FROM utilizador WHERE nome_utilizador='$username' AND password='$password'";
  	$results = mysqli_query($db, $query);
    $verificaTipo = mysqli_query($db, $verificaT);
    $tipo = mysqli_fetch_assoc($verificaTipo);


    $qCod = "SELECT cod_utilizador FROM utilizador WHERE nome_utilizador='$username'";
    $verificaCod = mysqli_query($db, $qCod);
    $cod_u = mysqli_fetch_assoc($verificaCod);
    $cod = $cod_u["cod_utilizador"];

    $_SESSION['cod'] = $cod;
    
  	if (mysqli_num_rows($results) == 1) {
  	  $_SESSION['username'] = $username;
  	  $_SESSION['success'] = "You are now logged in";
      if( $tipo["cod_tipo"]== 1){
        // $_SESSION['cod'] = $cod;
  	     header('location: Cliente.php');
      }
      else{
          header('location: Admin.php');
      }
  	}else {
  		array_push($erros, "Combinação errada de username e password");
  	}
  }
}

/*if (isset($_POST['btnEdif'])){
  $codE = "SELECT cod_edificio FROM edificio WHERE designacao = '".$_SESSION['desig']."'";
  $result = mysqli_query($db, $codE);
  $cod_E = mysqli_fetch_assoc($result);

  $cod_Edif = $cod_E["cod_edificio"];
  $_SESSION['cod_Edif'] = $cod_Edif;

}*/

if (isset($_POST['logOut'])){
   session_destroy();
    unset($_SESSION['username']);
    header("location: web2.php");
}

if (isset($_POST['logO'])){
   session_destroy();
    unset($_SESSION['username']);
    header("location: web2.php");
}

?>