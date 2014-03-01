package com.example.jaggo;

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

import com.example.jaggo.CustomizedListView.LoadAllProducts;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

    String username;
    String password;
    private ProgressDialog pDialog;
    private String url_all;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";
    JSONArray products = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button button = (Button) findViewById(R.id.signup);
        Button button1 = (Button) findViewById(R.id.login);

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final EditText titleField = (EditText) findViewById(R.id.username);
                username = titleField.getText().toString();
                final EditText descField = (EditText) findViewById(R.id.password);
                password = descField.getText().toString();
                postData();
               // finish();

            }
        });
        
        button1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final EditText titleField = (EditText) findViewById(R.id.username);
                username = titleField.getText().toString();
                final EditText descField = (EditText) findViewById(R.id.password);
                password = descField.getText().toString();
                func();

            }
        });

    }
    


    public void postData() {
        // Create a new HttpClient and Post Header
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();

        StrictMode.setThreadPolicy(policy);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(
                "http://192.168.137.1/mis/register.php");

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("user", username));
            nameValuePairs.add(new BasicNameValuePair("password", password ));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            System.out.println("Executed\n");
            Toast.makeText(getApplicationContext(), "Successfully Signed up !!",
                    Toast.LENGTH_LONG).show();

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
    }
    public void func() {
    	
globals.link="http://192.168.137.1/mis/login.php?user="+username+"&password="+password;
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

int flag=0;
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
		   pDialog = new ProgressDialog(MainActivity.this);
			 pDialog.setMessage("Logging in ....");
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
				flag=1;
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
				//pDialog.setMessage("Logging Failed !!!");
			 pDialog.dismiss();
			if(flag==1)
			{
				Intent i = new Intent(MainActivity.this,CustomizedListView.class);
	               	i.putExtra("username", username);
	                startActivity(i);
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Wrong Login Details",
                        Toast.LENGTH_SHORT).show();
			}
			}
			}
	);
	}

}



}