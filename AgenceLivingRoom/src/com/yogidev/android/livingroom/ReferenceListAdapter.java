package com.yogidev.android.livingroom;

import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yogidev.android.livingroom.data.bean.Reference;

public class ReferenceListAdapter extends BaseAdapter {

	private List<Reference> values;
	private Context context;
	protected final int INVALID = -1;
	protected int DELETE_POS = -1;
	protected int SWIPE_POS = -1;
	private ReferenceListAdapter classReference;  
	private View currentView = null;
	private float originViewX = 0;
	private float originDeleteX = 0;

	public ReferenceListAdapter(Context context, List<Reference> values) {
//		super(context, R.layout.reference_list_row, values);
		this.context = context;
		this.values = values;
		this.classReference = this;
	}
	
	public void addItem(Reference item) {
		values.add(item);
		notifyDataSetChanged();
	}
	
	public void addItemAll(List<Reference> item) {
		values.addAll(item);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return values.size();
	}

	@Override
	public Reference getItem(int position) {
		return values.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	/**
	 * Delete the item at the given position
	 * 
	 * @param pos
	 */
	public void deleteItem(int pos) {
		// Remove the item of the list 
		values.remove(pos);
		// reset the DELETE_POS flag
		DELETE_POS = INVALID;
		
		notifyDataSetChanged();
	}
	
	/**
	 * Execute when the user swipe on a item of the list
	 * 
	 * @param isRight
	 * @param position
	 */
	public void onSwipeItem(boolean isRight, int position) {
		
		SWIPE_POS = position;
		
		// Swipe to the left : position the DELETE_POS flag to be able to display the "delete" button in getView()
		if (isRight == false) {
			DELETE_POS = position;
		} 
		// Swipe to the right, do nothing
		else if (DELETE_POS == position) {
			DELETE_POS = INVALID;
		}
		
		notifyDataSetChanged();
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		View view = convertView;
		
		if (convertView == null) {
			// Inflate the view
//			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			view = inflater.inflate(R.layout.reference_list_row, parent, false);
			view = LayoutInflater.from(context).inflate(R.layout.reference_list_row, null);
		}
		

		Reference ref = values.get(position);

		// Icon
		ImageView imgView = (ImageView) view.findViewById(R.id.icon);
		// Load the picture from the URL into the imageView (giving a default image in case of downloading error)
		new DownloadImageTask(imgView,context.getResources().getDrawable(R.drawable.logo)).execute(ref.getVignette());

		// Title
		TextView firstLine = (TextView) view.findViewById(R.id.firstLine);
		firstLine.setText(ref.getVille() + " - " + ref.getQuartier());
		// Details
		TextView secondLine = (TextView) view.findViewById(R.id.secondLine);
		secondLine.setText(ref.getTypeRef() + " - " + ref.getSurfaceInteger() + "m²");	
		// Prix
		TextView thirdLine = (TextView) view.findViewById(R.id.thirdLine);
		String prix = Integer.toString(ref.getLoyerOuPrix()) + "€" + (ref.isLocation()?"/mois":"");
		thirdLine.setText(prix);

		// Delete button (displayed on left swipe event)
		final ImageButton deleteButton = (ImageButton) view.findViewById(R.id.deleteRow);
		
		if (DELETE_POS == position && SWIPE_POS == DELETE_POS) { 
			
			currentView = view;
			
			// animate the row
			currentView.animate().setDuration(200).x(-200)
			.withEndAction(new Runnable() {
				public void run() {
					currentView.animate().x(0);
				}
			});

			
			deleteButton.setVisibility(View.VISIBLE);
			
			deleteButton.getBackground().setAlpha(100);
			
			// animate the button
			deleteButton.animate().setDuration(200).translationX(+200)
			.withStartAction(new Runnable() {
				public void run() {
					deleteButton.animate().translationX(0);
				}
			});
		} 
		// delete button not visible
		else {
			deleteButton.setVisibility(View.GONE);
		}
			
		
		deleteButton.setOnClickListener(new View.OnClickListener() {

			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Override
			public void onClick(final View v) {
				final Reference item = (Reference) classReference.getItem(position);
				currentView.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {

					@Override
					public void run() {
						currentView.setAlpha(1);
						// Delete item in the list
						deleteItem(position);
						classReference.notifyDataSetChanged();
						Toast.makeText(context, "Item " + item.getTitreRef() + " deleted", Toast.LENGTH_SHORT).show();
					}
				});

			}
		});

		return view;
	}
	
}
