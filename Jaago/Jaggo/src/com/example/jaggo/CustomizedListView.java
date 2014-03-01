package com.example.jaggo;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CustomizedListView extends Activity{
	// All static variables

	// XML node keys

	static final String KEY_ID = "id";
	static final String KEY_TITLE = "title";
	static final String KEY_DES = "description";
	static final String KEY_TA1 = "tag1";
	static final String KEY_TA2 = "tag2";
	static final String KEY_TA3 = "tag3";
	static final String KEY_IMG = "votes";
	static final String KEY_CT = "city";
	static final String KEY_VT = "votes";
	static final String KEY_TS = "timestamp";
	public ArrayList<ArrayList<String>> causeList = new ArrayList<ArrayList<String>>();
	public  ArrayList<ArrayList<String>> eventList = new ArrayList<ArrayList<String>>();
	public ArrayList<ArrayList<String>> commentList = new ArrayList<ArrayList<String>>();
	ListView list;
	LazyAdapter adapter;
	Button more,create_event,x;
	EditText y;
	final int change = 0;

	@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			globals.cause.clear();
			globals.comment.clear();
			globals.event.clear();
			globals.count=0;
			x=(Button)findViewById(R.id.b1);
			y=(EditText)findViewById(R.id.editText1);
		    create_event=(Button)findViewById(R.id.create_event);
			create_event.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent t=new Intent(CustomizedListView.this,CreateEvent.class);
					startActivityForResult(t,1);
				}
			});
			x.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String h=y.getText().toString();
					updatebycity(h);
					//Intent t=new Intent(CustomizedListView.this,CreateEvent.class);
					//startActivity(t);
				}
			});
			func();

			//func1();
			//func2();
			// looping through all song nodes <song>
			more=(Button)findViewById(R.id.more);

			  more.setOnClickListener(new View.OnClickListener() {


				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//Toast.makeText(getBaseContext(),Integer.toString(globals.cause.size()),
						//	Toast.LENGTH_SHORT).show();
					func();
				}
			});
		}

    public void updatebycity(String h)
	{
	//	Toast.makeText(getBaseContext(),h,
		//		Toast.LENGTH_SHORT).show();
		if(h.length()>0)
		{
		globals.link="http://192.168.137.1/mis/filter_causes.php?city="+h;
		globals.cause.clear();
		new LoadAllProducts().execute();
		}
		
	}

	public void func() {
	
                   String s=Integer.toString(globals.cause.size());
		globals.link="http://192.168.137.1/mis/get_all.php?min_id="+s+"&number_of_records=5";
	//			Toast.LENGTH_SHORT).show();
		new LoadAllProducts().execute();
		// Response from Edit Product Activity
	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {
		final JSONParser jParser = new JSONParser();

		//String s="0";
		final String url_all_products = globals.link;


		// JSON Node names
		final String TAG_SUCCESS = "success";
		final String TAG_PRODUCTS = "causes";
		final String TAG_PID = "id";
		final String TAG_NAME = "title";
		final String TAG_DES = "description";
		final String TAG_TA1 = "tag_1";
		final String TAG_TA2 = "tag_2";
		final String TAG_TA3 = "tag_3";
		final String TAG_IMG = "votes";
		final String TAG_CT = "city";
		final String TAG_VOT = "votes";
		final String TAG_TIM = "timestamp";

		// products JSONArray
		JSONArray products=null;

        ProgressDialog pDialog;

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
			protected void onPreExecute() {
				super.onPreExecute();
				//Toast.makeText(getBaseContext(),"array0start",
					//	Toast.LENGTH_SHORT).show();
				   pDialog = new ProgressDialog(CustomizedListView.this);
					 pDialog.setMessage("Loading Data. Please wait...");
					 pDialog.setIndeterminate(false);
					 pDialog.setCancelable(false);
					 pDialog.show();
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
					causeList=globals.cause ;
					products = json.getJSONArray(TAG_PRODUCTS);

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
						String tag1 = c.getString(TAG_TA1);
						row.add(tag1);
						String tag2 = c.getString(TAG_TA2);
						row.add(tag2);
						String tag3 = c.getString(TAG_TA3);
						row.add(tag3);
						String img = c.getString(TAG_IMG);
						row.add(img);
						String city = c.getString(TAG_CT);
						row.add(city);
						String votes = c.getString(TAG_VOT);
						row.add(votes);
						String timest = c.getString(TAG_TIM);
						row.add(timest);
						causeList.add(row);
					}
					globals.cause=causeList;
					//globals.count+=causeList.size();


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
				//	Toast.makeText(getBaseContext(),"here"+Integer.toString(causeList.size()),
					//Toast.LENGTH_SHORT).show();
					 pDialog.dismiss();
					/**
					 * Updating parsed JSON data into ListView
					 */
					//Toast.makeText(getBaseContext(),"size of list is "+Integer.toString(globals.cause.size()),
						//	Toast.LENGTH_SHORT).show();
					final ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
					for (int i = 0; i < (causeList.size()); i++) {
					// creating new HashMap
					HashMap<String, String> map = new HashMap<String, String>();
					ArrayList<String> e = (ArrayList<String>) causeList.get(i);
					// adding each child node to HashMap key => value
					map.put(KEY_ID, e.get(0));
					map.put(KEY_TITLE, e.get(1));
					map.put(KEY_DES, e.get(2));
					map.put(KEY_TA1, e.get(3));
					map.put(KEY_TA2, e.get(4));
					map.put( KEY_TA3, e.get(5));
					map.put( KEY_IMG, e.get(6));
					map.put( KEY_CT, e.get(7));
					map.put( KEY_VT, e.get(8));
					map.put( KEY_TS, e.get(9));

					// adding HashList to ArrayList
					songsList.add(map);
					}

                    if(songsList.size()>0)
					{
					list=(ListView)findViewById(R.id.list);

					// Getting adapter by passing xml data ArrayList
					adapter=new LazyAdapter(CustomizedListView.this, songsList);
					list.setAdapter(adapter);


					// Click event for single list row
					list.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
								Intent t=new Intent(CustomizedListView.this, causepage.class);
								/*Object obj = list.getAdapter().getItem(position);
								int v = obj.hashCode("KEY_ID");*/

								String st=songsList.get(position).get(KEY_ID);
								Toast.makeText(getBaseContext(),st,
										Toast.LENGTH_SHORT).show();
								t.putExtra("id", st);
								
								startActivityForResult(t,1);

							}
							});
                        

					}
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
	        	
	            // The user picked a contact.
	    	globals.cause.clear();
	        	func();
	
	    //    }
	    }
	}
};
