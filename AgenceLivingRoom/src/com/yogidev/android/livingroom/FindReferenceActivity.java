package com.yogidev.android.livingroom;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class FindReferenceActivity extends Activity {
	
	protected static final int CODE_FIND_REFERENCE = 2;
	
	private Spinner spinnerVille;
	private Spinner spinnerQuartier;
	private Spinner spinnerType;
	private Spinner spinnerLoyer;
	private RadioButton radioButtonLouer;
	private ImageButton buttonRechercher;
	private TextView textQuartier;
	
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
		setContentView(R.layout.find_reference);
	    
		// set background layer
		findViewById(R.id.containerFind).setBackground(PreferencesManager.getInstance().getBackgoundColorPref());
		// set transparency 
		//getWindow().getDecorView().getRootView().setAlpha(PreferencesManager.TRANPARENCY);
	    
		// Spinner Ville
	    spinnerVille = (Spinner) findViewById(R.id.SpinnerVille);
	    // Spinner Quartier
	    textQuartier = (TextView) findViewById(R.id.TextViewQuartier);
	    spinnerQuartier = (Spinner) findViewById(R.id.SpinnerQuartier);
	    spinnerQuartier.setVisibility(View.VISIBLE);
	    // Spinner Type
	    spinnerType = (Spinner) findViewById(R.id.SpinnerType);
	    
	    // Radio location/vente
	    radioButtonLouer = (RadioButton) findViewById(R.id.radioLouer);
	    
	    // Spinner Loyer
	    spinnerLoyer = (Spinner) findViewById(R.id.SpinnerLoyer);
	    
	    // Button Rechercher
	    buttonRechercher = (ImageButton) findViewById(R.id.ButtonRechercher);
	    
	    // hide the SpinnerQuartier if ville != "Toulouse"
	    spinnerVille.setOnItemSelectedListener( 
	    		new OnItemSelectedListener() {

	    			@Override
	    			public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long id) {
	    				String currentVille = parent.getItemAtPosition(pos).toString();
	    				if (currentVille.equals(getResources().getString(R.string.Toulouse))) {
	    					textQuartier.setVisibility(View.VISIBLE);
	    					findViewById(R.id.SpinnerQuartier).setVisibility(View.VISIBLE);
	    				}
	    				else {
	    					textQuartier.setVisibility(View.GONE);
	    					findViewById(R.id.SpinnerQuartier).setVisibility(View.GONE);
	    				}
	    			}

	    			@Override
	    			public void onNothingSelected(AdapterView<?> arg0) {
	    			}

	    		});
//	    ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.villes_array, R.layout.spinner_item);
//	    adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
//	    spinnerVille.setAdapter(adapter);
	    

	    buttonRechercher.setOnClickListener(
	    		new OnClickListener() {
	    			@Override
	    			public void onClick(View v) {
	    				try {
	    					// get values
	    					String ville = (String)spinnerVille.getSelectedItem();
	    					String quartier = (String)spinnerQuartier.getSelectedItem();
	    					String type = (String)spinnerType.getSelectedItem();
	    					boolean isLocation = radioButtonLouer.isChecked();
	    					String loyer = (String)spinnerLoyer.getSelectedItem();
	    						    					
	    					
	    					// TODO : Enregistrer la recherche
	    					// Recherche recherche = new Recherche(ville, quartier, type, isLocation, loyer);
	    					// SerialTool.saveRecherche(recherche, getApplicationContext());
	    					objetbunble.putStringArray("recherche", new String[]{ville,quartier,type,Boolean.toString(isLocation),loyer});
	    					
	    					// Launch RechercheListActivity
	    					Intent intent = new Intent(FindReferenceActivity.this, ReferenceListActivity.class);
	    					intent.putExtras(objetbunble);
	    					startActivityForResult(intent, CODE_FIND_REFERENCE );

	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}
	    			}
	    		}
	    		);
	    
	    
	    // Get the Action Bar 
	    ActionBar actionBar = getActionBar();
	    // Enable the app icon as an Up button
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    // Set title
	    actionBar.setTitle(getString(R.string.titreTrouverLogement));
	    // Set logo
	    //actionBar.setLogo(R.drawable.loupe);
	    actionBar.setDisplayUseLogoEnabled(true);
	    
	}
	
	
	/**
	 * Listener on the radio button "A louer / A vendre"
	 * 
	 * Behaviour : hide SpinnerLoyer if radioVendre is on
	 */
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radioLouer:
	            if (checked) {
	            	findViewById(R.id.SpinnerLoyer).setVisibility(View.VISIBLE);
	            	spinnerLoyer.setVisibility(View.VISIBLE);
	            }
	            break;
	        case R.id.radioVendre:
	            if (checked) {
	            	findViewById(R.id.SpinnerLoyer).setVisibility(View.GONE);
	            	spinnerLoyer.setVisibility(View.GONE);
	            }
	            break;
	    }
	}
	
	/**
	 * Inflate the action bar menu
	 * 
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
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
		switch (item.getItemId()) {
		
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			Intent homeIntent = new Intent(this,HomeActivity.class);
			homeIntent.putExtras(objetbunble);
			setResult(Activity.RESULT_OK, homeIntent);
			finish();
			return true;
			
		case R.id.action_settings:
			// Launch Settings Activity
			intent = new Intent(this, SettingsActivity.class);
			intent.putExtras(objetbunble);
			startActivityForResult(intent, HomeActivity.SETTING_OPTIONS_CODE);
			return true;

		case R.id.menu_toggleTheme:
			// Change Theme and save it into preferences
			int currentTheme =PreferencesManager.getInstance().getThemePref();
			if (currentTheme == R.style.AppTheme_Dark) {
				PreferencesManager.getInstance().saveThemePref(R.style.AppTheme_Light);
			} else {
				PreferencesManager.getInstance().saveThemePref(R.style.AppTheme_Dark);
			}
			this.recreate();
			return true;

		case R.id.action_info:
			// Launch About Activity
			intent = new Intent(this, InfosActivity.class);
			intent.putExtras(objetbunble);
			startActivityForResult(intent, HomeActivity.ABOUT_OPTIONS_CODE);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
