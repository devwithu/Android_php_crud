<?php

header("Content-type:application/json");

require_once 'example_con.php';

$name = $_GET['name'];
$hobby = $_GET['hobby'];

$sql = "SELECT * FROM person WHERE name = '$name' AND hobby = '$hobby'";
//$sql = "SELECT * FROM person WHERE name = 'kim' AND hobby = 'music'";
//$sql = "SELECT * FROM person ";

$result = mysqli_query($con, $sql);

$data = mysqli_num_rows($result);

if ($data > 0)
{
    $error = "ok";
    //echo json_encode(array("response" => $error, "name" => "Kim", "hobby" => "music"));
    echo json_encode(array("response" => $error, "name" => $name, "hobby" => $hobby));
}
else
{
    $error = "failed";
    echo json_encode(array("response" => $error));
}

mysqli_close($con);

?>

