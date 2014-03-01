<?php

/*
 * Following code will list all the products
 */

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// get all products from products table
if(isset($_GET['user']))
{
	$user = $_GET['user'];	
    $password = $_GET['password'];
	$result = mysql_query("SELECT * FROM user where user = '$user' and password = '$password'") or die(mysql_error());

	// check for empty result
	if (mysql_num_rows($result) > 0) {
		
		// success
		$response["success"] = 1;
		$response["message"] = "user found";

		// echoing JSON response
		echo json_encode($response);
	} else {
		// no products found
		$response["success"] = 0;
		$response["message"] = "No such user found";

		// echo no users JSON
		echo json_encode($response);
	}
}
else
{
	$response["success"] = 0;
	$response["message"] = "missing fields";

	// echo no users JSON
	echo json_encode($response);

}

?>
