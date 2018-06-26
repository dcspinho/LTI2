<?php
include('server.php');
$bd = mysqli_connect('192.168.43.16:3306','Todos','','bd') or die("Impossivel conectar");
$carregou_tipo_user = false;

function achaUsers($bd) {

    $output = '';
    $SelecUser = "SELECT distinct nome_utilizador FROM utilizador";
    $buscaUser = mysqli_query($bd, $SelecUser);

    while ($row = mysqli_fetch_array($buscaUser)) {

        $output .= '<option value ="' . $row["nome_utilizador"] . '">' . $row["nome_utilizador"] . '</option>';
    }
    return $output;
}

function setTipo_user($novo) {
    $carregou_tipo_user = $novo;
}

function getTipo_user() {
    "alert('Selecionar o tipo de utilizador!')";
    return $carregou_tipo_user;
}

function obterNovo_user(){
    return $_POST['Utilizador'];
}

function obterTipo_user(){
    return $_POST['tipo_user'];
}

function obterADICIONAR(){
    return $_POST['ADICIONAR'];
}
?>

<!DOCTYPE html>
<html lang="pt-pt">

    <title>Adicionar utilizador</title>
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
            
                <input type="radio" name="tipo_user" value="Cliente" onclick=""/>Cliente
                <input type="radio" name="tipo_user" value="Gestor"/>Gestor
                            
            <br>
            <p>
                <br>
                
                Utilizador: <input type="text" name="Utilizador" value=""  onclick="return setTipo_user(true)"/>
                
            </p>
            <p>
                Palavra-passe: <input type="password" name="Palavra-passe" value="" />
            </p>
            <p>
                Confirmar palavra-passe: <input type="password" name="Confirmar" value="" />
            </p>
        </p>
        <br>
        <button type="submit" name = "ADICIONAR" class="btn">ADICIONAR</button>
    </form>
</body>
</html>

<!--<script>
    $(document).ready(function () {
        $('#Utilizador').change(function () {
            var nome_utilizador = $(this).val();
            $.ajax({
                url: "help_add_user.php",
                method: "POST",
                data: {nome_utilizador: nome_utilizador},
                success: function (data) {
                    $('#show_valor').html(data);
                }
            });
        });
    });
</script> -->


