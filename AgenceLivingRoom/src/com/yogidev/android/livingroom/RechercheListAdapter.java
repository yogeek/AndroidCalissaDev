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
import android.widget.TextView;
import android.widget.Toast;

import com.yogidev.android.livingroom.data.bean.Recherche;
import com.yogidev.android.livingroom.data.util.SerialTool;

public class RechercheListAdapter extends BaseAdapter {

	private List<Recherche> values;
	private Context context;
	protected final int INVALID = -1;
	protected int DELETE_POS = -1;
	protected int SWIPE_POS = -1;
	private RechercheListAdapter classRecherche;  
	private View currentView = null;

	public RechercheListAdapter(Context context, List<Recherche> values) {
//		super(context, R.layout.reference_list_row, values);
		this.context = context;
		this.values = values;
		this.classRecherche = this;
	}
	
	public void addItem(Recherche item) {
		values.add(item);
		notifyDataSetChanged();
	}
	
	public void addItemAll(List<Recherche> item) {
		values.addAll(item);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return values.size();
	}

	@Override
	public Recherche getItem(int position) {
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
		
		System.out.println("onSwipeItem [ " + (isRight?"RIGHT":"LEFT") + " ] at : " + position);
		
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
//			view = inflater.inflate(R.layout.Recherche_list_row, parent, false);
			view = LayoutInflater.from(context).inflate(R.layout.recherche_list_row, null);
		}
		

		Recherche ref = values.get(position);

		// Image de la recherche
//		ImageView icon = (ImageView) view.findViewById(R.id.icon);
//		icon.setImageDrawable(this.context.getResources().getDrawable(ref.getIconDrawable()));
		
		// Nom de la recherche
		TextView titleLine = (TextView) view.findViewById(R.id.titleLine);
		titleLine.setText(ref.getName());
		// Ville/Quartier
		TextView firstLine = (TextView) view.findViewById(R.id.firstLine);
		firstLine.setText(ref.getVille() + (ref.getQuartier().isEmpty()?"":" - " + ref.getQuartier()));
		// Details
		TextView secondLine = (TextView) view.findViewById(R.id.secondLine);
		secondLine.setText("Type : " + ref.getType());	
		// Location + Loyer / Vente
		TextView thirdLine = (TextView) view.findViewById(R.id.thirdLine);
		String prix = ref.isLocation()?ref.getLoyer():"Vente";
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
				final Recherche item = (Recherche) classRecherche.getItem(position);
				currentView.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {

					@Override
					public void run() {
						currentView.setAlpha(1);
						// Delete item in the list
						deleteItem(position);
						classRecherche.notifyDataSetChanged();
						// Delete the saved file
						SerialTool.deleteRecherche(item.getName(), context);
						Toast.makeText(context, "Recherche " + item.getName() + " supprimée", Toast.LENGTH_SHORT).show();
					}
				});

			}
		});

		return view;
	}
	
}
