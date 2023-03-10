<?php

header("Content-type:application/json");

require_once 'example_con.php';

$id = $_POST['id'];
$sheet_name = $_POST['sheet_name'];
$start_time = $_POST['start_time'];
$count = $_POST['count'];
$process = $_POST['process'];

$sql = "UPDATE oven SET sheet_name = '$sheet_name', start_time = '$start_time' , count = '$count', process = '$process' WHERE id = '$id'";

$result = mysqli_query($con, $sql);

?>
