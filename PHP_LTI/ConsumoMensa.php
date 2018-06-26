 <?php  
$bd = mysqli_connect('192.168.43.16:3306','Todos','','bd') or die("Impossivel conectar"); 
 $output = '';  
 if(isset($_POST["cod_edificio"]))  
 {  
      if($_POST["cod_edificio"] != '')  
      {  
          $sql = "SELECT valor FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."'";
          $sql1 = "SELECT valor, max(timeS_date), min(timeS_date) FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."' and Month(timeS_date)=1";
          $sql13 = "SELECT valor FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."' and Month(timeS_date)=1 and timeS_date =  min(timeS_date) ";
          $sql2 = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."' and Month(timeS_date)=2";
          $sql3 = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."' and Month(timeS_date)=3";  
          $sql4 = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."' and Month(timeS_date)=4";
          $sql5 = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."' and Month(timeS_date)=5";
          $sql6 = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."' and Month(timeS_date)=6";
          $sql7 = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."' and Month(timeS_date)=7";
          $sql8 = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."' and Month(timeS_date)=8";
          $sql9 = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."' and Month(timeS_date)=9";
          $sql10 = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."' and Month(timeS_date)=10";
          $sql11 = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."' and Month(timeS_date)=11";
          $sql12 = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."' and Month(timeS_date)=12";
      }  
      /*else  
      {  
           $sql = "SELECT * FROM consumo WHERE cod_edificio = '".$_POST["cod_edificio"]."'";  
      }  */
      $result = mysqli_query($bd, $sql);
      
      $row1 = reset(mysqli_fetch_array($result));
$row = end(mysqli_fetch_array($result));
      if($row3 = mysqli_fetch_array($result)){
      ?>
       <table>
            <tr>
              <th>Valor de Consumo (kWh)</th>
              <th>Data</th>
              <th>Hora</th>
            </tr> 

         <?php   $output .= '   <td>'.$row.'</td>
                                <td>'.$row1.'</td>
                               
                              </tr>';        
      }
      while($row = mysqli_fetch_array($result))  
      {  
           $output .= '   <td>'.$row["valor"].'</td>
                          <td>'.$row["timeS_date"].'</td>
                          <td>'.$row["timeS_time"].'</td>
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