<?php

/*
 * Following code will list all the events
 */

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// get all products from products table
$query="";
if(isset($_GET['cause_id']))
{
	$cause_id=$_GET['cause_id'];
	$query="SELECT * FROM comments where cause_id=$cause_id";
}
else
{
	$query="SELECT * FROM comments";
}
$result = mysql_query($query) or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
	// looping through all results
	// products node
	$response["comments"] = array();

	while ($row = mysql_fetch_array($result)) {

		// temp user array
		$product = array();
		$product["id"] = $row["id"];
		$product["cause_id"] = $row["cause_id"];
		$product["comment"] = $row["comment"];
		$product["timestamp"] = $row["timestamp"];
		$product["username"] = $row["username"];

		// push single product into final response array
		array_push($response["comments"], $product);
	}
	// success
	$response["success"] = 1;

	// echoing JSON response
	echo json_encode($response);
} else {
	// no products found
	$response["success"] = 0;
	$response["message"] = "No products found";

	// echo no users JSON
	echo json_encode($response);
}

?>
