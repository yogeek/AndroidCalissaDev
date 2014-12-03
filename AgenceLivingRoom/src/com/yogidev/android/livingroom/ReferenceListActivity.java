package com.yogidev.android.livingroom;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.yogidev.android.livingroom.SwipeListView.SwipeListViewCallback;
import com.yogidev.android.livingroom.data.bean.Recherche;
import com.yogidev.android.livingroom.data.bean.Reference;
import com.yogidev.android.livingroom.data.mock.MockReferenceList;
import com.yogidev.android.livingroom.data.util.Constants;
import com.yogidev.android.livingroom.data.util.SerialTool;

public class ReferenceListActivity extends ListActivity implements SwipeListViewCallback {
	
//	private ListView mListView;
	private ReferenceListAdapter mAdapter;
	
	public static final int SETTING_OPTIONS_CODE = 1;
	public static final int SAVE_REFERENCE_OPTIONS_CODE = 2;
	private static final int REFERENCE_DESCRIPTION_CODE = 4;
	
	// The bundle to pass and receive data to and from other activities
	Bundle objetbunble;
	
	// Current "Recherche" details
	Recherche currentRecherche;
	
	// is the "Recherche" already saved
	boolean alreadySaved = false;
	
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
	    
        /// get content of the "recherche" object
	    currentRecherche = (Recherche) objetbunble.getSerializable(Constants.CURRENT_RECHERCHE);
       	
	    // Get the Action Bar 
	    ActionBar actionBar = getActionBar();
	    // Enable the app icon as an Up button
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    
	    // Set title
	    actionBar.setTitle(getResources().getQuantityString(R.plurals.numberOfResults, mAdapter.getCount(), mAdapter.getCount()));
	    // Set logo
	    actionBar.setDisplayUseLogoEnabled(true);
	
	}
	
	
	/**
	 * Inflate the action bar menu
	 * 
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result_recherche, menu);
		return true;
	}
	
	/**
	 * Call after invalidateOptionsMenu()
	 * 
	 */
	public boolean onPrepareOptionsMenu(Menu menu) {
	    MenuItem starButton = menu.findItem(R.id.action_saveRecherche);     
	    if ( !(SerialTool.getAlreadySavedName(currentRecherche, getApplicationContext())).isEmpty()) {
	        starButton.setIcon(android.R.drawable.btn_star_big_on);
	    } 
	    else{
	    	starButton.setIcon(android.R.drawable.btn_star_big_off);
	    }
	    return super.onPrepareOptionsMenu(menu);

	}

	/**
	 * Manage the action bar options
	 * 
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		Intent intent = null;
		final Context ctx = getApplicationContext();

		switch (item.getItemId()) {
		
			case R.id.action_home:
				// back to Home Activity
				intent = new Intent(this, HomeActivity.class);
				intent.putExtras(objetbunble);
		        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        startActivity(intent);
				return true;	
		
			case R.id.action_settings:
				// Launch Settings Activity
				intent = new Intent(ReferenceListActivity.this, SettingsActivity.class);
				intent.putExtras(objetbunble);
				startActivityForResult(intent, SETTING_OPTIONS_CODE);
				return true;
				
			case R.id.action_saveRecherche:
				// Check if the Recherche is not already saved
				final String name = SerialTool.getAlreadySavedName(currentRecherche,ctx);
				if (!name.isEmpty()) {
					// Recherche already saved => rename ?
					new AlertDialog.Builder(ReferenceListActivity.this)
					.setTitle("Recherche existante")
					.setMessage("Cette recherche est déjà enregistrée sous le nom '" + name + "'")
					.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							// do nothing
						}
					}).setNegativeButton("Renommer", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							// delete the Recherche to save it with another name on the next AlertDialog
							SerialTool.deleteRecherche(name, ctx);
							// Ask for a name
							showSaveRechercheDialog();
						
							// Save it also into SharedPrefs
							Editor editor = PreferencesManager.getInstance().getPrefEditor();
							editor.putString(Constants.RECHERCHE_VILLE, currentRecherche.getVille());
							editor.putString(Constants.RECHERCHE_QUARTIER, currentRecherche.getQuartier());
							editor.putString(Constants.RECHERCHE_TYPE, currentRecherche.getType());
							editor.putBoolean(Constants.RECHERCHE_IS_LOCATION, currentRecherche.isLocation());
							editor.putString(Constants.RECHERCHE_PRIX, currentRecherche.getLoyer());
							editor.commit();
						}
					}).show();
				}
				else {
					// new Recherche to save
					showSaveRechercheDialog();
				}
				
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	// Show an AlertDialog to save the Recherche choosing a name 
	public void showSaveRechercheDialog() {
		final Context ctx = getApplicationContext();
		final EditText input = new EditText(ctx);
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ReferenceListActivity.this);
		dialogBuilder.setTitle("Enregistrer la recherche");
		dialogBuilder.setMessage("Choisir un nom pour la recherche");
		dialogBuilder.setView(input);
		dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int whichButton) {
	            String value = input.getText().toString(); 
	            if (value.isEmpty()) {
	            	Toast.makeText(ctx, "Le nom ne peut pas être vide.",Toast.LENGTH_SHORT).show();
	            }
	            else {
		            // Check if the name already exists
		            if (SerialTool.isRechercheNameFree(value, ctx)) {
			            // Save the current "Recherche" object
			            currentRecherche.setName(value);
			            SerialTool.saveRecherche(currentRecherche, ctx);
			            Toast.makeText(ctx, "Recherche '" + value + "' enregistrée !",Toast.LENGTH_SHORT).show();
			            // Call invalidateOptionsMenu to force the onPrepareOptionsMenu call (to update the action bar menu items)
			            invalidateOptionsMenu();
		            }
		            else {
		            	Toast.makeText(ctx, "Ce nom est déjà utilisé. Veuillez en choisir un autre.",Toast.LENGTH_SHORT).show();
		            	input.setText("");
		            }
	            }
	        }
	    });
		dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int whichButton) {
	            // Do nothing.
	        }
	    });
		input.setRawInputType(InputType.TYPE_CLASS_TEXT);
		input.setTextColor(R.color.black);
		dialogBuilder.show();
//	    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
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
		intent.putExtra(Constants.CURRENT_REFERENCE, item);
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
