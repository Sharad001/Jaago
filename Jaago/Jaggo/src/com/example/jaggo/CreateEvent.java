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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateEvent extends Activity{

	String title1;
	String desc1;
	String image1;
	String city1;
	String t1;
	String t2;
	String t3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_event);
		Button button = (Button) findViewById(R.id.button1);

		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final EditText titleField = (EditText) findViewById(R.id.title);
				title1 = titleField.getText().toString();
				final EditText descField = (EditText) findViewById(R.id.desc);
				desc1 = descField.getText().toString();

				final EditText cityField = (EditText) findViewById(R.id.city);
				city1 = cityField.getText().toString();
				final EditText tag1Field = (EditText) findViewById(R.id.t1);
				t1 = tag1Field.getText().toString();
				final EditText tag2Field = (EditText) findViewById(R.id.t2);
				t2 = tag2Field.getText().toString();
				final EditText tag3Field = (EditText) findViewById(R.id.t3);
				t3 = tag3Field.getText().toString();
				postData();

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
				"http://192.168.137.1/mis/create_cause.php");

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("id", "12345"));
			nameValuePairs.add(new BasicNameValuePair("title", title1));
			nameValuePairs.add(new BasicNameValuePair("description", desc1));
			nameValuePairs.add(new BasicNameValuePair("image", image1));
			nameValuePairs.add(new BasicNameValuePair("city", city1));
			nameValuePairs.add(new BasicNameValuePair("tag_1", t1));
			nameValuePairs.add(new BasicNameValuePair("tag_2", t2));
			nameValuePairs.add(new BasicNameValuePair("tag_3", t3));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
		//	System.out.println("Executed\n");
	//		Toast.makeText(getApplicationContext(), "Perfect",
			//		Toast.LENGTH_LONG).show();

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
	public void onBackPressed() {
		super.onBackPressed();
		Intent returni = getIntent();
		returni.putExtra("change", "1");
		setResult(RESULT_OK, returni);
		finish();
		}

}
