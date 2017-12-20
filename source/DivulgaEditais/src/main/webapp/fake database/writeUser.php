<?php
if (isset($_POST['name'])){
    date_default_timezone_set("Brazil/East"); //Definindo timezone padrão
        $obj = new stdClass();
        $obj->name = $_POST['name'];
        $obj->email = $_POST['email'];
        $obj->password = $_POST['password'];
        $obj->identification = $_POST['identification'];
        $obj->zipcode = $_POST['zipcode'];
        $obj->street = $_POST['street'];
        $obj->number = $_POST['number'];
        $obj->complement = $_POST['complement'];
        $obj->neighbourhood = $_POST['neighbourhood'];
        $obj->city = $_POST['city'];
        $obj->state = $_POST['state'];
        $obj->primaryPhoneNumber = $_POST['primaryPhoneNumber'];
        $obj->secondPhoneNumber = $_POST['secondPhoneNumber'];
        $obj->mainPerson = $_POST['mainPerson'];

       $objData = json_encode( $obj);
       
       
       $filePath = '/xampp/htdocs/Prefeitura/users.json';
       $point = filesize($filePath);

        if (is_writable($filePath)) {
            $fh = fopen($filePath, 'r+');
            $stat = fstat($fh);
            ftruncate($fh, $stat['size']-1);
            fclose($fh);

            $fp = fopen($filePath, "a");
            fwrite($fp, ","); 
            fwrite($fp, $objData); 
            fwrite($fp, "]"); 
            fclose($fp);
        }
    
   /* $myEmail = $obj->email;
    $myPassword = $obj->password;
    $myType = $obj->identification;
    
    include_file "userSession.php";*/
}
?>




