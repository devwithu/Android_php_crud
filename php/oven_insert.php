<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST')
{
    header("Content-type:application/json");

    require_once 'example_con.php';

    $sheet_name = $_POST['sheet_name'];
    $start_time = $_POST['start_time'];
    $count = $_POST['count'];
    $process = $_POST['process'];


    $sql = "INSERT INTO oven(sheet_name, start_time, count, process)
            VALUES(' $sheet_name', '$start_time', '$count', '$process')";
    
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

