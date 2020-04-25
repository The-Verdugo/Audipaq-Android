<?php
$hostname='localhost';
$database='audipaq';
$username='root';
$password='';

$conexion=new mysqli($hostname,$username,$password,$database);
if($conexion->connect_errno){
    echo "error de conexion";
}
?>