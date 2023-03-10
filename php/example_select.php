<?php

header("Content-type:application/json");

require_once 'example_con.php';

$name = $_GET['name'];
$hobby = $_GET['hobby'];

$sql = mysqli_query($con, "SELECT * FROM person");

$response = array();

while($row = mysqli_fetch_assoc($sql))
{
    array_push($response, array(
        'id' => $row['id'],
        'name' => $row['name'],
        'hobby' => $row['hobby']
    ));
}

echo json_encode($response);

mysqli_close($con);

?>

