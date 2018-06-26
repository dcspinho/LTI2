<?php// include('helpPreco.php');?>

<?php  
$bd = mysqli_connect('192.168.43.16:3306','Todos','','bd') or die("Impossivel conectar"); 

if (isset($_POST['Alterar'])){
  $novoPreco = $_POST['preco1'];
  $update = "UPDATE edificio SET preco= '$novoPreco' WHERE cod_edificio='".$_POST["cod_edificio"]."'";
  $up = mysqli_query($bd, $update);
 // header("location: Admin.php");
}

?>