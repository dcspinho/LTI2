<?php
include('server.php');
$bd = mysqli_connect('192.168.43.16:3306','Todos','','bd') or die("Impossivel conectar");

function achaUsers($bd) {
    $output = '';
    $SelecUser = "SELECT distinct nome_utilizador, cod_utilizador FROM utilizador";
    $buscaUser = mysqli_query($bd, $SelecUser);

    while ($row = mysqli_fetch_array($buscaUser)) {
        $output .= '<option value ="' . $row["cod_utilizador"] . '">' . $row["nome_utilizador"] . '</option>';
    }
    return $output;
}

function achaEdificios($bd) {
    $output = '';
    $SelecEdif = "SELECT designacao,cod_edificio FROM edificio WHERE cod_utilizador='" . $_POST['cod_utilizador'] . "'";
    $buscaEdif = mysqli_query($bd, $SelecEdif);

    while ($row = mysqli_fetch_array($buscaEdif)) {
        $output .= '<option value ="' . $row["cod_edificio"] . '">' . $row["designacao"] . '</option>';
    }
    return $output;
}
?>

<!DOCTYPE html>
<html lang="pt-pt">

    <title>Gerir edifícios</title>
    <link rel="stylesheet" type="text/css" href="styleAdmin.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>  

    <meta charset = "UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <body>
        <img src="logo.png" alt="logotipo Universidade do Minho" width="100" height="80">
        <div class="administrador">
            <h1>Gerir</h1>
        </div>
        <form name = "fila1" action = "" method = "post">
            <button type="submit" name = "logOut" class="btn">Logout</button>
            <button type="submit" name = "Anterior" class="btn">Página anterior</button>

            <br /><br />  
            <div class="listaUsers" id="listaUsers" >
                Utilizadores existentes:
                <select name="user" id="user"> 
                    <?php echo achaUsers($bd); ?>  
                </select>  
            </div>

            <br /><br />  
            <div class="row" id="show_valor"> 

            </div> 

            <div class="listaEd" id="listaEd" style="visibility:hidden">
                <select name="edificio" id="edificio" onchange = "return showEdificio();">  
                    <option value="">Selecionar Edificio</option>
                    <?php echo achaEdificios($bd); ?>  
                </select>  

            </div>

            <div class="preco" id="preco" style="visibility:hidden">
            </div>
            <div class="row" id="show_valorr">  
                <?//php echo Dados_Edif($bd);?>

            </div>  
            <br></br>
        </form>
    </body>
</html>

<script>
    $(document).ready(function () {
        $('#user').change(function () {
            var cod_utilizador = $(this).val();
            $.ajax({
                url: "help_gerir.php",
                method: "POST",
                data: {cod_utilizador: cod_utilizador},
                success: function (data) {
                    $('#show_valor').html(data);
                }
            });
        });
    });

    $(document).ready(function () {
        $('#edificio').change(function () {
            var cod_edificio = $(this).val();
            $.ajax({
                url: "help2.php",
                method: "POST",
                data: {cod_edificio: cod_edificio},
                success: function (data) {
                    $('#show_valorr').html(data);
                }
            });
        });
    });

    function showEdificio() {
        var selectUser = document.getElementById('user');
        var userInput = selectUser.options[selectUser.selectedIndex].value;

        if (userInput) {
            document.getElementById('listaEd').style.visibility = 'visible';
        } else {
            document.getElementById('listaEd').style.visibility = 'hidden';
        }
        return false;
    }
</script> 