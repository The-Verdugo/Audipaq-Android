<?php
include 'conexion.php';
$nombre=$_GET['nombre'];


$consulta ="SELECT * FROM persona INNER JOIN empresa ON persona.fk_id_empresa = empresa.id_empresa WHERE persona.nombre_persona= '$nombre'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado->fetch_array()){
	$persona[] = array_map('utf8_encode',$fila);
}
echo json_encode($persona);
$resultado -> close();
?>