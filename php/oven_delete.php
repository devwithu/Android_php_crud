<?php

$id = $_POST['id'];

require_once 'example_con.php';

$sql = "DELETE FROM oven WHERE id = '$id'";

$result = mysqli_query($con, $sql);

if ($result)
{
    echo "Success";
}
else
{
    echo "Failed";
}

?>
