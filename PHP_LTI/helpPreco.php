<?php include('server.php');?>
<?php //include('AtualizaPreco.php');?>
<?php  
// echo $_POST["cod_edificio"];
$bd = mysqli_connect('192.168.43.16:3306','Todos','','bd') or die("Impossivel conectar"); 
 $output = '';  
 if(isset($_POST["cod_edificio"]))  
 {  
      if($_POST["cod_edificio"] != '')  
      {  
    //global $codEdif;
   // $codEdif=$_POST["cod_edificio"];
    $mysql = "SELECT preco from edificio WHERE cod_edificio = '".$_POST["cod_edificio"]."'";
    $buscaPreco = mysqli_query($bd,$mysql);
    $preco = mysqli_fetch_assoc($buscaPreco);
    $envPreco = $preco["preco"];

   //$precoEnv = 'Preço: <input type="text" name="preco1" value="'. $envPreco.'"><button type="submit" name = "Alterar" class="btnAlterar">Alterar</button>';
  //$precoEnv = 'Preço: <input type="text" name="preco1" value="'.$envPreco.'"><a href="helpPreco.php?Alterar="'.$envPreco.'"alt="edit">Alterar</a>';
    $precoEnv = 'Preço: <input type="text" name="preco1" value="'. $envPreco.'">';
    echo $precoEnv;
  }
 }

 /*if (isset($_POST['Alterar'])){
  $novoPreco = $_POST['preco1'];
  $update = "UPDATE edificio SET preco= '$novoPreco' WHERE cod_edificio='".$_POST["cod_edificio"]."'";
  $up = mysqli_query($bd, $update);
  header("location: Admin.php");
}*/

if (isset($_GET['Alterar'])) {
  $novoPreco = $_POST['preco1'];
  $update = "UPDATE edificio SET preco= '$novoPreco' WHERE cod_edificio='".$_POST["cod_edificio"]."'";
  $up = mysqli_query($bd, $update);
  }

?>