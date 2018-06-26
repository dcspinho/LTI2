<?php
include('server.php');
/* function alert($msg){
  echo '<script type="text/javascript">
  <!--
  alert("'.$msg.'");
  //-->
  </script>';} */
$bd = mysqli_connect('192.168.43.16:3306','Todos','','bd') or die("Impossivel conectar");

function achaUsers($bd) {

    $output = '';
    $SelecUser = "SELECT distinct nome_utilizador FROM utilizador";
    $buscaUser = mysqli_query($bd, $SelecUser);

    while ($row = mysqli_fetch_array($buscaUser)) {

        $output .= '<option value ="' . $row["nome_utilizador"] . '">' . $row["nome_utilizador"] . '</option>';
    }
    return $output;
}
?>

<!DOCTYPE html>
<html lang="pt-pt">

    <title>Administrador</title>
    <link rel="stylesheet" type="text/css" href="styleAdmin.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>  



    <meta charset = "UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <body>
        <img src="logo.png" alt="logotipo Universidade do Minho" width="100" height="80">

        <form name = "fila1" action = "" method = "post">
            <div class= "logout" >
                <button type="submit" name = "logOut" class="btn">Logout</button>
            </div>
            <br>
            <div class="Botoes" id="Botoes">
                <input type="submit" id="Add_user" name = "Add_user" class="btn" value="Adicionar utilizador">
                <input type="submit" id="Eliminar_user" name = "Eliminar_user" class="btn" value="Eliminar utilizador" style="visibility:hidden">
                <input type="submit" id="Gerir" name = "Gerir" class="btn" value="Gerir edifícios" style="visibility:hidden">
                <input type="submit" id="Consultar" name = "Consultar" class="btn" value="Consultar" style="visibility:hidden">
            </div>


            <div class="listaUsers" id="listaUsers" style="visibility:hidden">>
                Utilizadores :
                <select name="user" id="user"> 
                    <option value="">Selecionar Utilizador</option>
                    <?php echo achaUsers($bd); ?>  
                </select>  
                <br /><br />  
                <div class="row" id="show_valor">  

                </div> 
            </div>
            <br>
        </form>
    </body>
</html>


<script>
    $(document).ready(function () {
        $('#user').change(function () {
            var nome_utilizador = $(this).val();
            $.ajax({
                url: "help_admin.php",
                method: "POST",
                data: {nome_utilizador: nome_utilizador},
                success: function (data) {
                    $('#show_valor').html(data);
                }
            });
        });
    });
</script> 


