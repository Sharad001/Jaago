package com.example.jaggo;

//package com.example.json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//import java.util.List;
//import org.apache.http.NameValuePair;
//import android.app.ListActivity;
//import android.content.Intent;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//import android.widget.TextView;

public class causepage extends Activity {

	// Progress Dialog
	// private ProgressDialog pDialog;

	// Creating JSON Parser object

	public String id;
	Button list_event, create_event;
	// products JSONArray
	int counter;
	Button upvote, downvote,comm,ccom;
	TextView display;
	ListView list;
	public String change="0";
	public int flag=0;
	static final String KEY_NAME = "name";
	static final String KEY_DES = "description";
	static final String KEY_TIME = "time";
	public ArrayList<ArrayList<String>> commentList = new ArrayList<ArrayList<String>>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		setContentView(R.layout.scrollview);
		flag=0;
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
		private static final String TAG_PRODUCTS = "cause";
		private static final String TAG_PID = "id";
		private static final String TAG_NAME = "title";
		private static final String TAG_DES = "description";
		private static final String TAG_TA1 = "tag_1";
		private static final String TAG_TA2 = "tag_2";
		private static final String TAG_TA3 = "tag_3";
		private static final String TAG_IMG = "image";
		private static final String TAG_CT = "city";
		private static final String TAG_VOT = "votes";
		private static final String TAG_TIM = "timestamp";
		String url_all_products = "http://192.168.137.1/mis/get_details.php?id="
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

						String titl = c.getString(TAG_NAME);
						String desc = c.getString(TAG_DES);
						String tag1 = c.getString(TAG_TA1);
						String tag2 = c.getString(TAG_TA2);
						String tag3 = c.getString(TAG_TA3);
						String city = c.getString(TAG_CT);
						String votes = c.getString(TAG_VOT);
						String timest = c.getString(TAG_TIM);
						String pid = c.getString(TAG_PID);
						row.add(titl);
						row.add(desc);
						row.add(tag1);
						row.add(tag2);
						row.add(tag3);
						row.add(city);
						row.add(votes);
						row.add(timest);
						row.add(pid);
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
					upvote = (Button) findViewById(R.id.upvote);
					downvote = (Button) findViewById(R.id.downvote);
					display = (TextView) findViewById(R.id.score);
					counter=Integer.parseInt(row.get(6));
					display.setText(Integer.toString(counter));
					comm = (Button) findViewById(R.id.comment);
					ccom = (Button) findViewById(R.id.ccomment);
					ccom.setOnClickListener(new View.OnClickListener() {

						public void onClick(View v) {
						//	counter++;
							//change="1";
						//	display.setText(Integer.toString(counter));
							//postData();
							Toast.makeText(getBaseContext(),row.get(8),
									Toast.LENGTH_SHORT).show();

							Intent n=new Intent(causepage.this, comment.class);

							n.putExtra("id", row.get(8));
							//t.putExtra("description", row.get(1));
							//t.putExtra("name" , "admin");
							startActivityForResult(n, 1);
							
						}
					});
					comm.setOnClickListener(new View.OnClickListener() {

						public void onClick(View v) {
						//	counter++;
							//change="1";
						//	display.setText(Integer.toString(counter));
							//postData();
							func2();
						}
					});

					upvote.setOnClickListener(new View.OnClickListener() {

						public void onClick(View v) {
						//	counter++;
							if(flag==0){
								counter++;
								flag=(flag+1)%2;
							}
							change="1";
							display.setText(Integer.toString(counter));
							postData();
						}
					});
					downvote.setOnClickListener(new View.OnClickListener() {

						public void onClick(View v) {
							//counter--;
							if(flag!=0){
								counter--;
								flag=(flag+1)%2;
							}
							change="1";
							display.setText(Integer.toString(counter));
							postData();
						}
					});
					TextView t = (TextView) findViewById(R.id.title2);
					t.setText(row.get(0));

					TextView n = (TextView) findViewById(R.id.mycity);
					n.setText(row.get(5));

					TextView a = (TextView) findViewById(R.id.mydesc);
					a.setText(row.get(1));
						
				
					list_event=(Button)findViewById(R.id.list_event);
					create_event=(Button)findViewById(R.id.create_event);
					list_event.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent t=new Intent(causepage.this, CustomizedListViewEvent.class);

							t.putExtra("id", id);
							startActivity(t);

						}
					});
					create_event.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent t=new Intent(causepage.this, create_event2.class);

							t.putExtra("id", id);
							startActivityForResult(t,1);
						}
					});

					 /* TextView g= (TextView) findViewById(R.id.timestamp);
					  g.setText(row.get(7));
					 */


				}
			});

		}

	}
	public void postData() {
		// Create a new HttpClient and Post Header
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(
				"http://192.168.137.1/mis/update_cause.php");

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("cause_id", id));
			nameValuePairs.add(new BasicNameValuePair("votes", Integer.toString(counter)));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			//System.out.println("Executed\n");
			//Toast.makeText(getApplicationContext(), "Perfect",
				//	Toast.LENGTH_LONG).show();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.toString(),
					Toast.LENGTH_LONG).show();
			// TODO Auto-generated catch block
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.toString(),
					Toast.LENGTH_LONG).show();
			// TODO Auto-generated catch block
		}
	//	new LoadAllProducts().execute();
	}
	public void onBackPressed() {
		super.onBackPressed();
		Intent returni = getIntent();
		returni.putExtra("change", "1");
		setResult(RESULT_OK, returni);
		finish();
		}
	
	public void func2(){
		new LoadAllProducts2().execute();
		// Response from Edit Product Activity
	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts2 extends AsyncTask<String, String, String> {
		final String url_all_products = "http://192.168.137.1/mis/get_comments.php?cause_id="+id;

		// JSON Node names
		final String TAG_SUCCESS = "success";
		final String TAG_PRODUCTS = "comments";
		final String TAG_PID = "id";
		final String TAG_CID = "cause_id";
		final String TAG_COMM = "comment";
		final String TAG_NAME = "username";
		final String TAG_TIM = "timestamp";

		// Progress Dialog
		//final ProgressDialog pDialog;

		// Creating JSON Parser object
		final JSONParser jParser = new JSONParser();


		// products JSONArray
		JSONArray products=null;


		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
			protected void onPreExecute() {
				super.onPreExecute();
				/*pDialog = new ProgressDialog(CustomizedListView.this);
				  pDialog.setMessage("Loading products. Please wait...");
				  pDialog.setIndeterminate(false);
				  pDialog.setCancelable(false);
				  pDialog.show();*/
			}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			// List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_products, "GET");

			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					products = json.getJSONArray(TAG_PRODUCTS);

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);
						ArrayList<String> row = new ArrayList<String> ();
						// Storing each json item in variable
						String id = c.getString(TAG_PID);
						row.add(id);
						String cid = c.getString(TAG_CID);
						row.add(cid);
						String com = c.getString(TAG_COMM);
						row.add(com);
						String title = c.getString(TAG_NAME);
						row.add(title);
						String timest = c.getString(TAG_TIM);
						row.add(timest);
						commentList.add(row);
					}
				} /*else {
				// no products found
				// Launch Add New product Activity
				Intent i = new Intent(getApplicationContext(),
				NewProductActivity.class);
				// Closing all previous activities
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				}*/
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			//pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
					public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 */
					//Toast.makeText(getBaseContext(),"array2",
					//	Toast.LENGTH_SHORT).show();
					//	Uri _resultUri =
						
					ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

					for (int i = 0; i < commentList.size(); i++) {
					// creating new HashMap
					HashMap<String, String> map;
					map= new HashMap<String, String>();
					ArrayList<String> e = (ArrayList<String>) commentList.get(i);
					// adding each child node to HashMap key => value
					//map.put(KEY_ID, e.get(0));
					map.put(KEY_DES, e.get(2));
					map.put(KEY_NAME, e.get(3));
					map.put(KEY_TIME, e.get(4));

					// adding HashList to ArrayList
					songsList.add(map);
					}
					

					list=(ListView)findViewById(R.id.commentlist);
					// Getting adapter by passing xml data ArrayList
					LazyAdaptercomment adapter = new LazyAdaptercomment(causepage.this, songsList);
					list.setAdapter(adapter);

					
					}


			});

		}
	};
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // Check which request we're responding to
		//super.onActivityResult(requestCode, resultCode, data);
		
	    if (requestCode == 1) {
	    	
	        // Make sure the request was successful
	        ///if (resultCode == RESULT_OK) {
	     func2();   	
	            // The user picked a contact.
	 //   	globals.cause.clear();
	   //     	func();
					//	Uri _resultUri =
	        	
	            // The Intent's data Uri identifies which contact was selected.

	            // Do something with the contact here (bigger example below)
	    //    }
	    }
	}
}
