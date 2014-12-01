package com.yogidev.android.livingroom;

import java.util.List;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.yogidev.android.livingroom.SwipeListView.SwipeListViewCallback;
import com.yogidev.android.livingroom.data.bean.Recherche;
import com.yogidev.android.livingroom.data.util.Constants;
import com.yogidev.android.livingroom.data.util.SerialTool;

public class RechercheListActivity extends ListActivity implements SwipeListViewCallback {
	
	private RechercheListAdapter mAdapter;
	
	private static final int RECHERCHE_LIST_CODE = 4;
	
	// The bundle to pass and receive data to and from other activities
	Bundle objetbunble;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    // Restore pref theme
	    setTheme(PreferencesManager.getInstance().getThemePref());
	    
	    // Get the Bundle sent by the previous activity
	    objetbunble  = getIntent().getExtras();
		
	    // Create the bundle if null
	    if (objetbunble == null) {
	    	objetbunble = new Bundle();
	    }
	    
	    // Inflate the view from XML
	    setContentView(R.layout.recherche_list_view);
	    
	    // Set background
	    this.findViewById(R.id.containerRechercheList).setBackground(PreferencesManager.getInstance().getBackgoundColorPref());
	    
	    // SimpleListView to manage the swipe on items
	    SwipeListView l = new SwipeListView(this, this);
		l.exec();
		
		// Retrieve all the saved "recherche" objects 
		List<Recherche> rechercheList = SerialTool.getAllSavedRecherche(getApplicationContext());
		
        mAdapter = new RechercheListAdapter(this, rechercheList);
        
	    setListAdapter(mAdapter);
	    
	    
	    // Get the Action Bar 
	    ActionBar actionBar = getActionBar();
	    // Enable the app icon as an Up button
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    
	    // Set title
	    actionBar.setTitle(getResources().getQuantityString(R.plurals.numberOfResults, mAdapter.getCount(), mAdapter.getCount()));
	    // Set logo
	    //actionBar.setLogo(R.drawable.partage);
	    actionBar.setDisplayUseLogoEnabled(true);
	
	}
	
	
	@Override
	public void onSwipeItem(boolean isRight, int position) {
		// Call the onSwipeItem of the adapter
		mAdapter.onSwipeItem(isRight, position);
	}

	
	@Override
	public void onItemClickListener(ListAdapter adapter, int position) {
		
		System.out.println("EVENT 'onItemClickListener' on " + position);
		
		final Recherche item = (Recherche) adapter.getItem(position);	
		Toast.makeText(this, "Affichage de la recherche '" + item.getName() + "' !", Toast.LENGTH_SHORT).show();
		
		// set the flag to make the "delete" button disappear
		mAdapter.DELETE_POS = mAdapter.INVALID;
		mAdapter.notifyDataSetChanged();
		
		// Save it also into SharedPrefs
		Editor editor = PreferencesManager.getInstance().getPrefEditor();
		editor.putString(Constants.RECHERCHE_VILLE, item.getVille());
		editor.putString(Constants.RECHERCHE_QUARTIER, item.getQuartier());
		editor.putString(Constants.RECHERCHE_TYPE, item.getType());
		editor.putBoolean(Constants.RECHERCHE_IS_LOCATION, item.isLocation());
		editor.putString(Constants.RECHERCHE_PRIX, item.getLoyer());
		editor.commit(); 
		
		// Launch the Description activity
		Intent intent = new Intent(RechercheListActivity.this, FindReferenceActivity.class);
		intent.putExtra(Constants.CURRENT_REFERENCE, item);
		startActivityForResult(intent, RECHERCHE_LIST_CODE);
	}
	
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
 
    @Override
    public void onResume() {
        super.onResume();
    }
	

}
