<?php

$number = $_POST['number'];

$file = '/xampp/htdocs/Prefeitura/Editais/1.pdf';
$filename = 'Edital.pdf'; /* Note: Always use .pdf at the end. */

header("Content-Type: application/octet-stream php");
header("Content-Disposition: inline; filename=" . urlencode($file));   
header("Content-Type: application/octet-stream");
header("Content-Type: application/download");
header("Content-Description: File Transfer");            
header("Content-Length: " . filesize($file));
readfile($file);
/*
flush(); // this doesn't really matter.
$fp = fopen($file, "r");
while (!feof($fp))
{
    echo fread($fp, 65536);
    flush(); // this is essential for large downloads
} 
fclose($fp); */


?>