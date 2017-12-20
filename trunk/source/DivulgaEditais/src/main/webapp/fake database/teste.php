<?php
/*$number = $_POST['number'];

$local_file = '/Users/Vanessa Furtado/Desktop/Editais/1.pdf';
$download_file = 'Edital.pdf'; */

header("Content-Length: " . filesize ('/xampp/htdocs/Prefeitura/Editais/1.pdf' ) ); 
header("Content-type: application/octet-stream"); 
header("Content-disposition: attachment;     
filename=".basename('/xampp/htdocs/Prefeitura/Editais/1.pdf'));
header('Expires: 0');
header('Cache-Control: must-revalidate, post-check=0, pre-check=0');
$filepath = readfile('/xampp/htdocs/Prefeitura/Editais/1.pdf');

?>