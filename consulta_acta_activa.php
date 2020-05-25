<?php
include 'conexion.php';
$id=$_GET['id'];



$consulta = "SELECT * FROM acta 
INNER JOIN persona ON acta.fk_id_auditor = persona.id_persona 
INNER JOIN area ON acta.fk_id_area=area.id_area
INNER JOIN departamento on acta.fk_id_departamento= departamento.id_departamento
INNER JOIN status on acta.fk_id_status = status.id_status
where acta.fk_id_status=1 && persona.id_persona=$id";

$resultado = $conexion -> query($consulta);

while($fila=$resultado->fetch_array()){
	$persona[] = array_map('utf8_encode',$fila);
}
echo json_encode($persona);
$resultado -> close();
?>