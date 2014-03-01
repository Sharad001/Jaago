package com.example.jaggo;



import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdaptercomment extends BaseAdapter {

	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;
//	public ImageLoader imageLoader;

	public LazyAdaptercomment(Activity a, ArrayList<HashMap<String, String>> d) {
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//imageLoader = new ImageLoader(activity.getApplicationContext());
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.commentlist, null);

		TextView title = (TextView) vi.findViewById(R.id.title); // title

		//TextView id = (TextView) vi.findViewById(R.id.ids); // duration

		TextView description = (TextView) vi.findViewById(R.id.description);
		//TextView tag1 = (TextView) vi.findViewById(R.id.tag1);
		//TextView tag2 = (TextView) vi.findViewById(R.id.tag2);
		//TextView tag3 = (TextView) vi.findViewById(R.id.tag3);

		//TextView city = (TextView) vi.findViewById(R.id.city);
		//TextView votes = (TextView) vi.findViewById(R.id.votes);
		TextView timestamp = (TextView) vi.findViewById(R.id.timestamp);

		ImageView image = (ImageView) vi.findViewById(R.id.list_image); // thumb
																		// image

		HashMap<String, String> song = new HashMap<String, String>();
		song = data.get(position);

		// Setting all values in listview
		title.setText(song.get(causepage.KEY_NAME));
		//id.setText(song.get(CustomizedListView.KEY_ID));
		description.setText(song.get(causepage.KEY_DES));
		//tag1.setText(song.get(CustomizedListView.KEY_TA1));
		//tag2.setText(song.get(CustomizedListView.KEY_TA2));
		//tag3.setText(song.get(CustomizedListView.KEY_TA3));
		//city.setText(song.get(CustomizedListView.KEY_CT));
		//votes.setText(song.get(CustomizedListView.KEY_VT));
		timestamp.setText(song.get(causepage.KEY_TIME));
		//imageLoader.DisplayImage(song.get(CustomizedListView.KEY_IMG), image);
		return vi;
	}
}