<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST')
{
    header("Content-type:application/json");

    require_once 'example_con.php';

    $name = $_POST['name'];
    $hobby = $_POST['hobby'];

    $sql = "INSERT INTO person(name, hobby)
            VALUES('$name', '$hobby')";
    
    if (mysqli_query($con, $sql))
    {
        $response['success'] = true;
        $response['message'] = "추가 완료";
    }
    else
    {
        $response['success'] = false;
        $response['message'] = "추가 실패";
    }
}
else
{
    $response['success'] = false;
    $response['message'] = "POST로 오지 않음";
}

echo json_encode($response);

?>
