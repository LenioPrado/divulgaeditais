<?php
   if(isset($_FILES['file']))
   {
      date_default_timezone_set("Brazil/East"); //Definindo timezone padrão
    $number = $_POST['number'];
       
       $obj = new stdClass();
        $obj->number = $_POST['number'];
        $obj->modality = $_POST['modality'];
        $obj->object = $_POST['object'];
       $obj->trading_date = $_POST['trading_date'];
       $objData = json_encode( $obj);
       
       
       $filePath = '/xampp/htdocs/Prefeitura/notice.json';
       $point = filesize($filePath);

        if (is_writable($filePath)) {
            $fh = fopen($filePath, 'r+');
            $stat = fstat($fh);
            ftruncate($fh, $stat['size']-1);
            fclose($fh);

            $fp = fopen($filePath, "a");
            //fseek($fp, ($point-2) , SEEK_SET);
            fwrite($fp, ","); 
            fwrite($fp, $objData); 
            fwrite($fp, "]"); 
            fclose($fp);
        } 

       
       
      $ext = strtolower(substr($_FILES['file']['name'],-4)); //Pegando extensão do arquivo
      //$new_name = date("Y.m.d-H.i.s") . $ext; 
        $new_name = $number . $ext;//Definindo um novo nome para o arquivo
      $dir = '/Users/Vanessa Furtado/Desktop/Editais/'; //Diretório para uploads

      move_uploaded_file($_FILES['file']['tmp_name'], $dir.$new_name); //Fazer upload do arquivo
   }
?>



