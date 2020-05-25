<?php
include 'conexion.php';
$user=$_GET['user'];
$pass=$_GET['pass'];


$consulta ="select * from persona where correo_electronico= '$user' and contrasena= '$pass'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado->fetch_array()){
	$persona[] = array_map('utf8_encode',$fila);
}
echo json_encode($persona);
$resultado -> close();
?>