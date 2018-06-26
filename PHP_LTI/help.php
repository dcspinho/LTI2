<?php 

$bd = mysqli_connect('localhost','root','','bd') or die("Impossivel conectar");
$cEdif = '';
if(isset($_POST["cod_edificio"]))  
 {  
 	if($_POST["cod_edificio"] != ''){
	$codEdif="SELECT cod_edificio FROM edificio where designacao = '".$_POST["cod_edificio"]."'";
	$cod_E = mysqli_query($bd,$codEdif);
	$cEdif = '<option value="">Selecione algo </option>';

	while ($codigoE=mysqli_fetch_array($cod_E)){

		$cEdif .= '<option value="'.$codigoE["cod_edificio"].'">'.$codigoE["cod_edificio"].'</option>';

	}
	}
	 else  
	 {  
	         $sql = "SELECT * FROM edificio";  
	 }  

echo $cEdif;
}
else{
	$xx='erro';
	echo $xx;
}
?>