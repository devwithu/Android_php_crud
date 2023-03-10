<?php

header("Content-type:application/json");

require_once 'example_con.php';

$id = $_POST['id'];
$name = $_POST['name'];
$hobby = $_POST['hobby'];

$sql = "UPDATE person SET name = '$name', hobby = '$hobby' WHERE id = '$id'";

$result = mysqli_query($con, $sql);

?>

