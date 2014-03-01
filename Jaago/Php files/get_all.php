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
if(isset($_GET['min_id']))
{
	$min_id=$_GET['min_id'];
	if(!isset($_GET['number_of_records']))
		$_GET['number_of_records']=12;
	$number_of_records=$_GET['number_of_records'];
	$result = mysql_query("SELECT *FROM causes order by votes desc limit $number_of_records offset $min_id") or die(mysql_error());

	// check for empty result
	if (mysql_num_rows($result) > 0) {
		// looping through all results
		// products node
		$response["causes"] = array();

		while ($row = mysql_fetch_array($result)) {

			// temp user array
			$product = array();
			$product["id"] = $row["id"];
			$product["title"] = $row["title"];
			$product["description"] = $row["description"];
			$product["image"] = $row["image"];
			$product["city"] = $row["city"];
			$product["tag_1"] = $row["tag_1"];
			$product["tag_2"] = $row["tag_2"];
			$product["tag_3"] = $row["tag_3"];
			$product["votes"] = $row["votes"];
			$product["timestamp"] = $row["timestamp"];

			// push single product into final response array
			array_push($response["causes"], $product);
		}
		// success
		$response["success"] = 1;

		// echoing JSON response
	//	echo $response["causes"][0]["id"];
	//	echo $response["causes"][1]["id"];
	//	echo $response["causes"][2]["id"];
		echo json_encode($response);
	} else {
		// no products found
		$response["success"] = 0;
		$response["message"] = "No products found";

	//	echo no users JSON
		echo json_encode($response);
	}
}
else
{
	$response["success"] = 0;
	$response["message"] = "No more events found";

	// echo no users JSON
	echo json_encode($response);

}

?>
