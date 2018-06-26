 <?php include('server.php');?>
 <?php   
 //load_data_select.php  
 $bd = mysqli_connect('localhost','root','','bd') or die("Impossivel conectar"); 
 function fill_brand($bd)  
 {  
     $output = '';
    $SelecEdif = "SELECT designacao,cod_edificio FROM edificio WHERE cod_utilizador='".$_SESSION['cod']."'";
    $buscaEdif = mysqli_query($bd, $SelecEdif);

    while($row = mysqli_fetch_array($buscaEdif)){
      
      $output .= '<option value ="'.$row["cod_edificio"].'">'.$row["designacao"].'</option>';   
    }
    return $output; 
  } 
 /*function fill_product($bd)  
 {  
      $output = '';  
      //$sql = "SELECT * FROM Consumo";  
      //$result = mysqli_query($bd, $sql);  
      //while($row = mysqli_fetch_array($result)) 

      {  
           $output .= '<div class="col-md-3">';  
           $output .= '<div style="border:1px solid #ccc; padding:20px; margin-bottom:20px;">'.$row["valor"].'';  
           $output .=     '</div>';  
           $output .=     '</div>';  
      }  
      return $output;  
 }*/  
 ?>  
 <!DOCTYPE html>  
 <html>  
      <head>  
           <title>Webslesson Tutorial | Multiple Image Upload</title>  
           <!--<link rel="stylesheet" type="text/css" href="styleCli.css">-->
           <link rel="stylesheet"/>  
           <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
           <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>  
      </head>  
      <body>  
           <br /><br />  
           <div class="container">  
                <h3>  
                     <select name="edificio" id="edificio">  
                          <option value="">Selecionar Edificio</option>
                          <?php echo fill_brand($bd); ?>  
                     </select>  
                     <br /><br />  
                     <div class="row" id="show_product">  
                          <?//php echo fill_product($bd);?>  
                     </div>  
                </h3>  
           </div>  
      </body>  
 </html>  
 <script>  
 $(document).ready(function(){  
      $('#edificio').change(function(){  
           var cod_edificio = $(this).val();  
           $.ajax({  
                url:"help2.php",  
                method:"POST",  
                data:{cod_edificio:cod_edificio},  
                success:function(data){  
                     $('#show_product').html(data);  
                }  
           });  
      });  
 });  
 </script> 