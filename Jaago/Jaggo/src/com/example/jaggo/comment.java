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

public class comment extends Activity{
	public String id,title,desc1;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	Bundle extras = getIntent().getExtras();
		setContentView(R.layout.comments);
		id = extras.getString("id");
		Toast.makeText(getBaseContext(),id,
				Toast.LENGTH_SHORT).show();
		Button button = (Button) findViewById(R.id.bt1);

		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		//final EditText titleField = (EditText) findViewById(R.id.name);
		//title = titleField.getText().toString();
		final EditText descField = (EditText) findViewById(R.id.desc);
		desc1 = descField.getText().toString();
		postData();
	}});
	}
	
	public void postData() {
		// Create a new HttpClient and Post Header
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();

		StrictMode.setThreadPolicy(policy);
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(
				"http://192.168.137.1/mis/create_comment.php");

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("cause_id", id));
			nameValuePairs.add(new BasicNameValuePair("comment", desc1));
			nameValuePairs.add(new BasicNameValuePair("username", "admin"));

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
		Intent n= new Intent();
		n.putExtra("name", "1");
		setResult(RESULT_OK, n);
		finish();
		}
	
}


