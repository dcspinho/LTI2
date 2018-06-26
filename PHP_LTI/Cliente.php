
<?php include('server.php');?>

<?php
// $bd = mysqli_connect('192.168.43.75:3306','Todos','','bd') or die("Impossivel conectar"); 
$bd = mysqli_connect('192.168.43.16:3306','Todos','','bd') or die("Impossivel conectar"); 
function achaEdificios($bd){
    $output = '';
    $SelecEdif = "SELECT designacao,cod_edificio FROM edificio WHERE cod_utilizador='".$_SESSION['cod']."'";
    $buscaEdif = mysqli_query($bd, $SelecEdif);

    while($row = mysqli_fetch_array($buscaEdif)){
      
      $output .= '<option value ="'.$row["cod_edificio"].'">'.$row["designacao"].'</option>';
         
    }
    return $output; 
  }

?>

 <!DOCTYPE html>  
<html lang="pt-pt">

<title>Depois login</title>
<link rel="stylesheet" type="text/css" href="styleCli.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>  
<style type="text/css">
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}

.listaEd{
  width:200px;
  height:53px;
  float:left;
}
.preco{
  width:260px;
  height:30px;
  float:left;
}

</style>

<meta charset = "UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<body>
<img src="logo.png" alt="logotipo Universidade do Minho" width="100" height="80">
<div class="cliente">
  <h1>Cliente</h1> 
</div>
  <div class="Geral" >
<form name = "form1" action = "" method = "post">
  <div class= "logout" >
    
    <button type="submit" name = "logOut" class="btn">Logout</button>
  </div>

<br>
<h3>Lista de Edificios</h3>

<br>
  <div class="listaEd" id="listaEd">
   <select name="edificio" id="edificio" onchange = "return showPreco();">  
        <option value="">Selecionar Edificio</option>
        <?php echo achaEdificios($bd); ?>  
    </select>  
   
  </div>
  
  <div class="preco" id="preco" style="visibility:hidden">
  </div>

    <!--<div class="btnAlterar" id="btnAlterar" style="visibility:hidden">
        <button type="submit" name = "Alterar" class="btnAlterar">Alterar</button>
    </div>-->
    <div class="row" id="show_valor">  
            <?//php echo Dados_Edif($bd);?>
    <div class="row" id="nada">      
    </div>  
   <br></br>

  
</div>
</form>
</body>
</html>
<script>  
 $(document).ready(function(){  
      $('#edificio').change(function(){  
           var cod_edificio = $(this).val();  
           $.ajax({  
                url:"help2.php",    
                //url:"ConsumoMensa.php", 
                method:"POST",  
                data:{cod_edificio:cod_edificio},  
                success:function(data){  
                     $('#show_valor').html(data);  
                }  
           });  
      });  
 });  
 </script> 

 <script>  
 $(document).ready(function(){  
      $('#edificio').change(function(){  
           var cod_edificio = $(this).val();  
           $.ajax({  
                url:"helpPreco.php",  
                method:"POST",  
                data:{cod_edificio:cod_edificio},  
                success:function(data){  
                     $('#preco').html(data);  
                }  
           });  
      });  
 });  
 </script> 

 <!-- <script>  
 $(document).ready(function(){  
      $('#edificio').change(function(){  
           var cod_edificio = $(this).val();  
           $.ajax({  
                url:"server.php",  
                method:"POST",  
                data:{cod_edificio:cod_edificio},  
                success:function(data){  
                     $('#nada').html(data);  
                }  
           });  
      });  
 });  
 </script> -->

 <script>
  function showPreco(){
    var selectEdif = document.getElementById('edificio');
    var userInput = selectEdif.options[selectEdif.selectedIndex].value;

    if (userInput){
      document.getElementById('preco').style.visibility='visible';
    //  document.getElementById('btnAlterar').style.visibility='visible';
      //document.getElementById("demo").innerHTML = "You selected: " + userInput;
    } else{
      document.getElementById('preco').style.visibility='hidden';
     // document.getElementById('btnAlterar').style.visibility='hidden';
    }
    return false;
  }
</script>


