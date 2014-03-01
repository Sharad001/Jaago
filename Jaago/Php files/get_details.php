<?php


// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET['id'])) {
//	echo $_GET["title"];
	$id = $_GET['id'];

	// get a product from products table
	$query = "SELECT *FROM causes WHERE id = '$id'";
//	echo $query;
	$result = mysql_query($query);
//	echo $result;

	if (!empty($result)) {
		// check for empty result
		if (mysql_num_rows($result) > 0) {

			$result = mysql_fetch_array($result);

			$product = array();
			$product["id"] = $result["id"];
			$product["title"] = $result["title"];
			$product["description"] = $result["description"];
			$product["image"] = $result["image"];
			$product["city"] = $result["city"];
			$product["tag_1"] = $result["tag_1"];
			$product["tag_2"] = $result["tag_2"];
			$product["tag_3"] = $result["tag_3"];
			$product["votes"] = $result["votes"];
			$product["timestamp"] = $result["timestamp"];
			// success
			$response["success"] = 1;

			// user node
			$response["cause"] = array();

			array_push($response["cause"], $product);

			// echoing JSON response
			echo json_encode($response);
		} else {
			// no product found
			$response["success"] = 0;
			$response["message"] = "No result found";

			// echo no users JSON
			echo json_encode($response);
		}
	} else {
		// no product found
		$response["success"] = 0;
		$response["message"] = "No result found";

		// echo no users JSON
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
