package com.yogidev.android.calissa;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adapter class for the Griv View layout (zomm gallery activity)
 * 
 * @author YoGi
 *
 */
public class GridViewAdapter extends ArrayAdapter {
	private Context context;
	private int layoutResourceId;
	private ArrayList data = new ArrayList();

	public GridViewAdapter(Context context, int layoutResourceId, ArrayList data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.imageTitle = (TextView) row.findViewById(R.id.text);
			holder.image = (ImageView) row.findViewById(R.id.image);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		// TODO : create an ImageItem class to process image with a title
//		ImageItem item = data.get(position);
//		holder.imageTitle.setText(item.getTitle());
//		holder.image.setImageBitmap(item.getImage());
		
		// Fill the imageView with the downloaded photo
		holder.imageTitle.setText("Photo " + (position+1));
		new DownloadImageTask(holder.image,getContext().getResources().getDrawable(R.drawable.logo)).execute((String)data.get(position));
		
		return row;
	}

	static class ViewHolder {
		TextView imageTitle;
		ImageView image;
	}
}
