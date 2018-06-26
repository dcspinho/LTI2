
<?php
session_start();

$erros = array();
$username = "";
$password = "";

$db = mysqli_connect('192.168.43.16:3306','Todos','','bd') or die("Impossivel conectar");
//$db = mysqli_connect('localhost','Todos','','bd') or die("Impossivel conectar"); 
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

/*if (isset($_POST['Alterar'])){
 
  $novoPreco = $_POST['preco1'];
  $update = "UPDATE edificio SET preco= '$novoPreco' WHERE cod_edificio='".$_POST["cod_edificio"]."'";
  $up = mysqli_query($db, $update);
}*/

if (isset($_POST['Gerir'])) {
    header("location: Admin_gerir.php");
}

if (isset($_POST['Add_user'])) {
    header("location: Admin_adicionar.php");
}
if (isset($_POST['Eliminar_user'])) {
    header("location: Admin_eliminar.php");
}
if (isset($_POST['Consultar'])) {
    header("location: Admin_consultar.php");
}

if (isset($_POST['Anterior'])) {
    header("location: Admin.php");
}

if (isset($_POST['ADICIONAR'])) {
    $error = 0;

    if (!isset($_POST['tipo_user'])) {
        ?>
        <script>alert("Selecionar tipo de utilizador!")</script>
        <?php
        $error = 1;
    } else if ($_POST['Utilizador'] == "") {
        ?>
        <script>alert('Introduzir nome de utilizador válido!')</script>
        <?php
    }
    $sql = "SELECT nome_utilizador FROM utilizador";
    $result = mysqli_query($db, $sql);
    $tipo = mysqli_fetch_assoc($result);

    while ($row = $result->fetch_assoc()) {
        if ($row["nome_utilizador"] == $_POST['Utilizador']) {
            //echo "id: " . $row["nome_utilizador"] . "<br>";
            ?>
            <script>alert('Utilizador já existente!')</script>
            <?php
            $error = 1;
        }
    }

    if ($_POST['Palavra-passe'] != $_POST['Confirmar']) {
        ?>
        <script>alert('Palavra-passe não corresponde!')</script>
        <?php
        $error = 1;
    }
    if ($error == 0) {
        /* fazer query adicionar */
        /*if ($_GET['tipo_user'] == "Cliente") {
            $sql = "insert into Utilizador values (null, '" . $_POST['Utilizador'] . "', '" . $_POST['Confirmar'] . "', 1)";
        } else if ($_GET['tipo_user'] == "Gestor") {*/
            $sql = "insert into Utilizador values (null, '" . $_POST['Utilizador'] . "', '" . $_POST['Confirmar'] . "', 2)";
        /*}*/

        $result = mysqli_query($db, $sql);
        if ($result) {
            ?>
            <script>alert('Adicionado com sucesso!')</script>
            <?php
        } else {
            ?>
            <script>alert('Erro '.mysqli_query($db, $sql).'!!')</script>
            <?php
        }
    }
}

if (isset($_POST['ELIMINAR'])) {
    if ($_POST['nome_utilizador']) {
        ?><script>
                    if (confirm('Tem certeza que deseja remover este utilizador?')) {
        <?php
        echo user_eliminar(null);
        /* $sql = "SELECT nome_utilizador FROM utilizador";
          $result = mysqli_query($db, $sql);
          $tipo = mysqli_fetch_assoc($result);

          while ($row = $result->fetch_assoc()) {
          if ($row["nome_utilizador"] == $_POST['Utilizador']) {
          //echo "id: " . $row["nome_utilizador"] . "<br>";
          ?>
          < script > alert('Utilizador já existente!')</script>
          <?php
          $error = 1;
          }
          } */
        ?>
            }
        </script>
        <?php
    }
}
?>
