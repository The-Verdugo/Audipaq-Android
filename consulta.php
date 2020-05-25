<?php
include 'conexion.php';
$tipo=$_GET['tipo'];


$consulta ="select * from persona where fk_id_tipo= '$tipo'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado->fetch_array()){
	$persona[] = array_map('utf8_encode',$fila);
}
echo json_encode($persona);
$resultado -> close();
?>