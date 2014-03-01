<?php


// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET['event_id'])) {
//	echo $_GET["title"];
	$event_id = $_GET['event_id'];

	// get a product from products table
	$query = "SELECT *FROM events WHERE event_id = '$event_id'";
//	echo $query;
	$result = mysql_query($query);
//	echo $result;

	if (!empty($result)) {
		// check for empty result
		if (mysql_num_rows($result) > 0) {

			$result = mysql_fetch_array($result);

			$product = array();
			$product["event_id"] = $result["event_id"];
			$product["cause_id"] = $result["cause_id"];
			$product["description"] = $result["description"];
			$product["name"] = $result["name"];
			$product["venue"] = $result["venue"];
			$product["time"] = $result["time"];
			$product["date"] = $result["date"];
			// success
			$response["success"] = 1;

			// user node
			$response["event"] = array();

			array_push($response["event"], $product);

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
