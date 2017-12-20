<html><script>console.log("Olá, chegou no userSession");</script></html>

<?php

if (isset($_POST['name'])){

session_start([
    'cookie_lifetime' => 3600,
    'read_and_close'  => true,
]);

$_SESSION["user"] = $_POST['name'];
$_SESSION["password"] = $_POST['password'];
$_SESSION["type"] = $_POST['identification'];
$_SESSION["name"] = $_POST['name'];


/*if($_SESSION["type"]=="branch"){
    include 'mainUser.php';
}
if($_SESSION["type"]=="cnae"){
    include 'companyUser.php';
}*/
}
    
    

?>