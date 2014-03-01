package com.example.jaggo;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class CustomizedListViewEvent extends Activity{
	// All static variables
	
	// XML node keys

	static final String KEY_ID = "cause_id";
	static final String KEY_E_ID = "event_id";
	static final String KEY_DES = "description";
	static final String KEY_NAME = "name";
	static final String KEY_VENUE = "venue";
	static final String KEY_TIME = "time";
	static final String KEY_DATE = "date";
	public ArrayList<ArrayList<String>> causeList = new ArrayList<ArrayList<String>>();
	public  ArrayList<ArrayList<String>> eventList = new ArrayList<ArrayList<String>>();
	public ArrayList<ArrayList<String>> commentList = new ArrayList<ArrayList<String>>();
	ListView list;	
	LazyAdaptorEvent adapter;
	Button more,create_event;
	String id;

	@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.mainlist);
			Bundle extras = getIntent().getExtras();
			id = extras.getString("id");
			Toast.makeText(getBaseContext(), id + "is the id recieved",
					Toast.LENGTH_SHORT).show();
			//globals.cause.clear();
			//globals.comment.clear();
			globals.event.clear();
			globals.count=0;
			func();	
			//func1();
			//func2();
			// looping through all song nodes <song>
			/*more=(Button)findViewById(R.id.more);
			
			  more.setOnClickListener(new View.OnClickListener() {
			 
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					globals.count=globals.count+5;
					func();
				}
			});*/
		}

	public void func() {
		//Toast.makeText(getBaseContext(),"array0", 
			//	Toast.LENGTH_SHORT).show();
		// Progress Dialog
		//final ProgressDialog pDialog;

		// Creating JSON Parser object
//		Toast.makeText(getBaseContext(),Integer.toString(globals.count), 
	//			Toast.LENGTH_SHORT).show();
		new LoadAllProducts().execute();
		// Response from Edit Product Activity
	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {
		final JSONParser jParser = new JSONParser();
		//String s=Integer.toString(globals.count);
		//String s="0";
		final String url_all_products = "http://192.168.137.1/mis/get_events.php?cause_id="+id;
		

		// JSON Node names
		final String TAG_SUCCESS = "success";
		
		final String TAG_PID = "cause_id";
		final String TAG_PRODUCTS="events";
		final String TAG_DES = "description";
		final String TAG_NAME = "name";
		final String TAG_VENUE = "venue";
		final String TAG_TIME = "time";
		final String TAG_DATE = "date";
		final String TAG_ID = "event_id";
		
		// products JSONArray
		JSONArray products=null;



		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
			protected void onPreExecute() {
				super.onPreExecute();
				//Toast.makeText(getBaseContext(),"array0start", 
					//	Toast.LENGTH_SHORT).show();
				/*   pDialog = new ProgressDialog(CustomizedListView.this);
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
					//eventList=globals.event ;
					products = json.getJSONArray(TAG_PRODUCTS);
					//globals.count=products.length();
					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);
						ArrayList<String> row = new ArrayList<String> ();
						// Storing each json item in variable
						String id = c.getString(TAG_PID);
						row.add(id);
						String title = c.getString(TAG_NAME);
						row.add(title);
						String des  = c.getString(TAG_DES);
						row.add(des);
						String venue = c.getString(TAG_VENUE);
						row.add(venue);
						String time = c.getString(TAG_TIME);
						row.add(time);
						String date = c.getString(TAG_DATE);
						row.add(date);
						String event_id = c.getString(TAG_ID);
						row.add(event_id);
						
						eventList.add(row);
					}
					//globals.event=eventList;
					

				}

				/*else {
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
			//    pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
					public void run() {
					//Toast.makeText(getBaseContext(),"here "+Integer.toString(globals.count), 
					//Toast.LENGTH_SHORT).show();
					/**
					 * Updating parsed JSON data into ListView
					 */
					Toast.makeText(getBaseContext(),"size of list is "+Integer.toString(eventList.size()), 
							Toast.LENGTH_SHORT).show();
					final ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
					for (int i = 0; i < (eventList.size()); i++) {
					// creating new HashMap
					HashMap<String, String> map = new HashMap<String, String>();
					ArrayList<String> e = (ArrayList<String>) eventList.get(i);
					// adding each child node to HashMap key => value
					map.put(KEY_ID, e.get(0));
					map.put(KEY_NAME, e.get(1));
					map.put(KEY_DES, e.get(2));
					map.put(KEY_VENUE, e.get(3));
					map.put(KEY_TIME, e.get(4));
					map.put( KEY_DATE, e.get(5));
					map.put( KEY_E_ID, e.get(6));
					// adding HashList to ArrayList
					songsList.add(map);
					}

					if(songsList.size()>0)
					{
					list=(ListView)findViewById(R.id.eventlist);

					// Getting adapter by passing xml data ArrayList
					adapter=new LazyAdaptorEvent(CustomizedListViewEvent.this, songsList);        
					list.setAdapter(adapter);


					// Click event for single list row
					list.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
								Intent t=new Intent(CustomizedListViewEvent.this, eventpage.class);
								
								
								String st=songsList.get(position).get(KEY_E_ID);
								Toast.makeText(getBaseContext(),st, 
										Toast.LENGTH_SHORT).show();
								t.putExtra("id", st);
								startActivity(t);	

							}
							});	
					}					

					}
			});

		}

	};
	public void onBackPressed() {
		super.finish();
		}


};