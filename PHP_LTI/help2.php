 <!--<?php  
 //load_data.php  
// echo $_POST["cod_edificio"];
$bd = mysqli_connect('localhost','root','','bd') or die("Impossivel conectar"); 
 $output = '';  
 if(isset($_POST["cod_edificio"]))  
 {  
      if($_POST["cod_edificio"] != '')  
      {  
          $sql = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."'";  
      }  
      else  
      {  
           $sql = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."'";  
      }  
      $result = mysqli_query($bd, $sql);  
      while($row = mysqli_fetch_array($result))  
      {  
           $output .= '<div class="col-md-3"><div style="border:1px solid #ccc; padding:10px; margin-bottom:10px;">'.$row["valor"].'</div></div>';  
      }  
      echo $output;  
 }
 else{
  $x="erro";
  echo $x;
 }  
 ?> -->
 <?php  

// echo $_POST["cod_edificio"];
$bd = mysqli_connect('localhost','root','','bd') or die("Impossivel conectar"); 
 $output = '';  
 if(isset($_POST["cod_edificio"]))  
 {  
      if($_POST["cod_edificio"] != '')  
      {  
          $sql = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."'";  
      }  
      /*else  
      {  
           $sql = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."'";  
      }  */
      $result = mysqli_query($bd, $sql);

      if($row = mysqli_fetch_array($result)){
      ?>
       <table>
            <tr>
              <th>Valor de Consumo (kWh)</th>
              <th>Data</th>
              <th>Hora</th>
            </tr> 

         <?php   $output .= '   <td>'.$row["valor"].'</td>
                          <td>'.$row["timestamp_date"].'</td>
                          <td>'.$row["timestamp_time"].'</td>
                        </tr>';        
      }
      while($row = mysqli_fetch_array($result))  
      {  
           $output .= '   <td>'.$row["valor"].'</td>
                          <td>'.$row["timestamp_date"].'</td>
                          <td>'.$row["timestamp_time"].'</td>
                        </tr>';  
      }  
      echo $output; ?>
      </tr>
      </table>  

<?php


    $mysql = "SELECT preco from edificio WHERE cod_edificio = '".$_POST["cod_edificio"]."'";
    $buscaPreco = mysqli_query($bd,$mysql);
    $preco = mysqli_fetch_assoc($buscaPreco);
    $envPreco = $preco["preco"];
    $_SESSION['envPreco'] = $envPreco;
 }
 
?>