package com.yogidev.android.livingroom;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.yogidev.android.livingroom.SwipeListView.SwipeListViewCallback;
import com.yogidev.android.livingroom.data.bean.Reference;
import com.yogidev.android.livingroom.data.mock.MockReferenceList;

public class ReferenceListActivity extends ListActivity implements SwipeListViewCallback {
	
//	private ListView mListView;
	private ReferenceListAdapter mAdapter;
	
	private static final int REFERENCE_DESCRIPTION_CODE = 4;
	
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
	    
		// ---------------------------------- begin code for Droid Inpector (search for this line in the project to suppress all occurences)
	    //		ViewServer.get(this).addWindow(this); 
		// ---------------------------------- end code for Droid Inpector
	    
	    // Inflate the view from XML
	    setContentView(R.layout.reference_list_view);
	    
	    // Set background
	    this.findViewById(R.id.containerReferenceList).setBackground(PreferencesManager.getInstance().getBackgoundColorPref());
	    
	    // SimpleListView to manage the swipe on items
	    SwipeListView l = new SwipeListView(this, this);
		l.exec();
		
		// TODO : replace with database request result !!
        mAdapter = new ReferenceListAdapter(this, MockReferenceList.REFERENCE_LIST);
        
	    setListAdapter(mAdapter);
	    
        // get content of the "recherche" object
//        String[] rechercheDetails;
//        if (objetbunble != null) {
//        	rechercheDetails = (String[]) objetbunble.get("recherche");
//        
//	        // AlertDialog to display the content
//			AlertDialog.Builder adbRechercheContent = new AlertDialog.Builder(this);
//			adbRechercheContent.setTitle("Détails de la recherche");
//			adbRechercheContent.setPositiveButton("Ok", null);
//			String message = "Ville : "    + rechercheDetails[0] + "/n";
//			message 	  += "Quartier : " + rechercheDetails[1] + "/n";
//			message 	  += "Type : "     + rechercheDetails[2] + "/n";
//			message 	  += "Location : " + rechercheDetails[3] + "/n";
//			message 	  += "Loyer : " + rechercheDetails[4] + "/n";
//			adbRechercheContent.setMessage(message);
//			adbRechercheContent.show();
//        }
//        else {
//        	AlertDialog.Builder adbRechercheContent = new AlertDialog.Builder(this);
//			adbRechercheContent.setTitle("Détails de la recherche");
//			adbRechercheContent.setPositiveButton("Ok", null);
//			adbRechercheContent.setMessage("Recherche vide !!!");
//        }
	    
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
		
		final Reference item = (Reference) adapter.getItem(position);	
		Toast.makeText(this, "Affichage de la référence '" + item.getTitreRef() + "' !", Toast.LENGTH_SHORT).show();
		// set the flag to make the "delete" button disappear
		mAdapter.DELETE_POS = mAdapter.INVALID;
		mAdapter.notifyDataSetChanged();
		
		// Launch the Description activity
		Intent intent = new Intent(ReferenceListActivity.this, ReferenceDescriptionActivity.class);
		// Store the parceable Reference
		intent.putExtra("currentReference", item);
		startActivityForResult(intent, REFERENCE_DESCRIPTION_CODE);
	}
	
    @Override
    public void onDestroy() {
        super.onDestroy();
		// ---------------------------------- begin code for Droid Inpector (search for this line in the project to suppress all occurences)
        //        ViewServer.get(this).removeWindow(this); 
		// ---------------------------------- end code for Droid Inpector
    }
 
    @Override
    public void onResume() {
        super.onResume();
		// ---------------------------------- begin code for Droid Inpector (search for this line in the project to suppress all occurences)
        //        ViewServer.get(this).setFocusedWindow(this);
		// ---------------------------------- end code for Droid Inpector
    }
	

}
