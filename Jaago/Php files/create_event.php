<?php
 
/*
 * Following code will create a new product row
 * All product details are read from HTTP POST Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['cause_id'])) {
 
    $cause_id = $_POST['cause_id'];
    $description="";	
   if (isset($_POST['description'])) $description = $_POST['description'];
    $name="";
   if (isset($_POST['name'])) $name = $_POST['name'];
    $time="";
    if (isset($_POST['time']))$time = $_POST['time'];
    $date="";
   if (isset($_POST['date'])) $date = $_POST['date'];
    $venue="";
   if (isset($_POST['venue'])) $venue = $_POST['venue'];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO `events` (`event_id`, `cause_id`, `name`, `description`, `venue`, `date`, `time`) VALUES (NULL, '$cause_id', '$name', '$description', '$venue', '$date','$time');");
 
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Product successfully created.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
 
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>
