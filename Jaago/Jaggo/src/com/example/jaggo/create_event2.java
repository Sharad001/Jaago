package com.example.jaggo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class create_event2 extends Activity{
	String event_name,event_desc,event_venue,event_time,event_date,id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		setContentView(R.layout.create_event2);
		id = extras.getString("id");
		Toast.makeText(getBaseContext(), id + "is the id recieved",
				Toast.LENGTH_SHORT).show();
		Button button = (Button) findViewById(R.id.event_button);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final EditText nameField = (EditText) findViewById(R.id.event_name);
				event_name = nameField.getText().toString();
				final EditText descField = (EditText) findViewById(R.id.event_desc);
				event_desc = descField.getText().toString();

				final EditText venueField = (EditText) findViewById(R.id.event_venue);
				event_venue = venueField.getText().toString();
				final EditText timeField = (EditText) findViewById(R.id.event_time);
				event_time = timeField.getText().toString();
				final EditText dateField = (EditText) findViewById(R.id.event_date);
				event_date = dateField.getText().toString();
				postData();
				finish();
				
				

				
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
				"http://192.168.137.1/mis/create_event.php");

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("cause_id", id));
			
			nameValuePairs.add(new BasicNameValuePair("name", event_name));
			nameValuePairs.add(new BasicNameValuePair("description", event_desc));
			nameValuePairs.add(new BasicNameValuePair("venue", event_venue));
			nameValuePairs.add(new BasicNameValuePair("time", event_time));
			nameValuePairs.add(new BasicNameValuePair("date", event_date));
			

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			System.out.println("Executed\n");
			Toast.makeText(getApplicationContext(), "Perfect",
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
	


}
