package com.example.jaggo;

//package com.example.json;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class eventpage extends Activity {

	// Progress Dialog
	// private ProgressDialog pDialog;

	// Creating JSON Parser object

	public String id;
	TextView display;
	String titl;
	String desc ;
	String venue ;
	String date ;
	String time ;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		setContentView(R.layout.showevent);
		id = extras.getString("id");
		//Toast.makeText(getBaseContext(), id + "is the id recieved",
				//Toast.LENGTH_SHORT).show();
		new LoadAllProducts().execute();
	}

	// Response from Edit Product Activity

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {
		JSONParser jParser = new JSONParser();
		// ArrayList<ArrayList<String>> productsList = new
		// ArrayList<ArrayList<String>>();

		// JSON Node names
		private static final String TAG_SUCCESS = "success";
		private static final String TAG_PRODUCTS = "event";
		private static final String TAG_NAME = "name";
		private static final String TAG_DES = "description";
		private static final String TAG_TA1 = "venue";
		private static final String TAG_TA3 = "time";
		private static final String TAG_TA2 = "date";
		String url_all_products = "http://192.168.137.1/mis/event_detail.php?event_id="
				+ id;
		// String url_all_p =
		// "http://192.168.1.7/Angelhack/app/get_comments.php?cause_id=" +
		// Integer.toString(id);
		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		ArrayList<String> row = new ArrayList<String>();
		JSONArray products = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			/*
			 * pDialog = new ProgressDialog(causepage.this);
			 * pDialog.setMessage("Loading products. Please wait...");
			 * pDialog.setIndeterminate(false); pDialog.setCancelable(false);
			 * pDialog.show();
			 */
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			// List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			// String temp="aman";
			JSONObject json = jParser.makeHttpRequest(url_all_products, "GET");

			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);
				// success=0;
				if (success == 1) {

					products = json.getJSONArray(TAG_PRODUCTS);

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);
						// ArrayList<String> row = new ArrayList<String> ();
						// Storing each json item in variable
						// String id = c.getString(TAG_PID);

						 titl = c.getString(TAG_NAME);
						 desc = c.getString(TAG_DES);
						 venue = c.getString(TAG_TA1);
						 date = c.getString(TAG_TA2);
						 time = c.getString(TAG_TA3);
						row.add(titl);
						row.add(desc);
						row.add(venue);
						row.add(time);
						row.add(date);
						
					}

				}

			} /*
			 * else { // no products found // Launch Add New product Activity
			 * Intent i = new Intent(getApplicationContext(),
			 * NewProductActivity.class); // Closing all previous activities
			 * i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); startActivity(i); }
			 */
			catch (JSONException e) {
				e.printStackTrace();
				Toast.makeText(getBaseContext(), e.toString(),
						Toast.LENGTH_SHORT).show();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			// pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					TextView t = (TextView) findViewById(R.id.title2);
					t.setText(row.get(0));

					TextView n = (TextView) findViewById(R.id.mydesc);
					n.setText(row.get(1));

					TextView a = (TextView) findViewById(R.id.mycity);
					a.setText(row.get(2));
						
					TextView b = (TextView) findViewById(R.id.time);
					b.setText(row.get(3));

					TextView v = (TextView) findViewById(R.id.date);
					v.setText(row.get(4));
					 /* TextView g= (TextView) findViewById(R.id.timestamp);
					  g.setText(row.get(7));
					 */
					Button reminder=(Button) findViewById(R.id.reminder);
					reminder.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							
							Calendar cal = Calendar.getInstance();              
							Intent intent = new Intent(Intent.ACTION_EDIT);
							intent.setType("vnd.android.cursor.item/event");
							intent.putExtra("beginTime", cal.getTimeInMillis());
							intent.putExtra("allDay", true);
							intent.putExtra("eventLocation", venue);
							intent.putExtra("description", desc);
							intent.putExtra("rrule", "FREQ=YEARLY");
							intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
							intent.putExtra("title", titl);
							startActivity(intent);
								
						}
					});

				}
			});

		}

	}
	
	



}