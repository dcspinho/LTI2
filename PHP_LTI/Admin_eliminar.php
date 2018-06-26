<?php
include('server.php');
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

function Botao_Eliminar() {
    echo $_POST["nome_utilizador"];
    ?><script>
        if (confirm('Tem certeza que deseja remover este utilizador?')) {
    <?php
    //echo user_eliminar(null);
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
?>

<!DOCTYPE html>
<html lang="pt-pt">

    <title>Eliminar utilizador</title>
    <link rel="stylesheet" type="text/css" href="styleAdmin.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>  

    <meta charset = "UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <body>
        <img src="logo.png" alt="logotipo Universidade do Minho" width="100" height="80">
        <div class="administrador">
            <h1>Eliminar utilizador</h1>
        </div>
        <form name = "fila1" action = "" method = "post">
            <div class= "logout" >
                <button type="submit" name = "logOut" class="btn">Logout</button>
                <button type="submit" name = "Anterior" class="btn">Página anterior</button>
            </div>
            <br>
            <div class="listaUsers" id="listaUsers" >
                Utilizadores existentes:
                <select name="user" id="user"> 
                    <?php echo achaUsers($bd); ?>  
                </select>  
                <br /><br />  
                <div class="row" id="show_valor">  

                </div> 
            </div>

            <br>
            <button type="submit" name = "ELIMINAR" class="btn" onclick="return Botao_Eliminar()">ELIMINAR</button>
        </form>
    </body>
</html>

<script>
        $(document).ready(function () {
            $('#user').change(function () {
                var nome_utilizador = $(this).val();
                $.ajax({
                    url: "help_eliminar.php",
                    method: "POST",
                    data: {nome_utilizador: nome_utilizador},
                    success: function (data) {
                        $('#show_valor').html(data);
                    }
                });
            });
        });
</script> 