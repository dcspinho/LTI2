<?php include('server.php'); ?>

<!DOCTYPE html>
<html lang="pt-pt">
    <title>Administrador</title>
    <link rel="stylesheet" type="text/css" href="styleAdmin.css">
    <meta charset = "UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <body>
        <img src="logo.png" alt="logotipo Universidade do Minho" width="100" height="80">
        <div class="administrador">
            <h1>Administrador</h1>
        </div>
        <div class="Geral">

            <form name = "fila1" action = "" method = "post">
                <div class= "logout" >
                    <button type="submit" name = "logOut" class="btn">Logout</button>
                </div>
                <br>
                <button type="submit" name = "Add_user" class="btn">Adicionar utilizador</button>
                <button type="submit" name = "Add_user" class="btn">Eliminar utilizador</button>
                <button type="submit" name = "Add_user" class="btn">Gerir edifícios</button>
                <button type="submit" name = "Add_user" class="btn">Consultar</button>

            </form>
        </div>
    </body>
</html>