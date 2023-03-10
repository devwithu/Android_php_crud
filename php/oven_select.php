<?php

header("Content-type:application/json");

require_once 'example_con.php';

$name = $_GET['name'];
$hobby = $_GET['hobby'];

$sql = mysqli_query($con, "SELECT * FROM oven");

$response = array();

while($row = mysqli_fetch_assoc($sql))
{
    array_push($response, array(
        'id' => $row['id'],
        'sheet_name' => $row['sheet_name'],
        'start_time' => $row['start_time'],
        'count' => $row['count'],
        'process' => $row['process']
    ));
}

echo json_encode($response);

?>
