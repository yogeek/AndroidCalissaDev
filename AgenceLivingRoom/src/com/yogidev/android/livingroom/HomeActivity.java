package com.yogidev.android.livingroom;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class HomeActivity extends Activity {
	
	public static final int SETTING_OPTIONS_CODE = 1;
	public static final int ABOUT_OPTIONS_CODE = 2;
	public static final int FIND_REFERENCE_OPTIONS_CODE = 3;
	public static final int CONTACT_CARD_OPTIONS_CODE = 4;
	
	Bundle objetbunble;
	
	// L'identifiant de la chaîne de caractères qui contient le résultat de l'intent
	public final static String SETTINGS_BUTTONS = "com.yogidev.android.intent.settings.Boutons";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Restore pref theme
		setTheme(PreferencesManager.getInstance().getThemePref());
		
		// Get the Bundle sent by the previous activity
	    objetbunble  = getIntent().getExtras();
	    
	    // Create the bundle if null
	    if (objetbunble == null) {
	    	objetbunble = new Bundle();
	    }
		
		// Check internet connection
		checkConnection();

		
		// ---------------------------------- begin code for Droid Inpector (search for this line in the project to suppress all occurences)
		//		ViewServer.get(this).addWindow(this); 
		// ---------------------------------- end code for Droid Inpector
		
	    // Inflate the view from XML
		setContentView(R.layout.activity_home);
		
		// Use of the "logo" defined in the Manifest instead of the default "icon" for the ActionBar
		getActionBar().setDisplayUseLogoEnabled(true);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.containerHome, new PlaceholderFragment()).commit();
		}
	}
	

    @Override
    public void onSaveInstanceState (Bundle savedInstanceState) {
    	// Save UI state changes to the savedInstanceState.
    	// This bundle will be passed to onCreate if the process is killed and restarted.
//    	savedInstanceState.putInt(THEME_ID, mThemeId);
    	
    	// Always call the superclass so it can save the view hierarchy state
    	super.onSaveInstanceState(savedInstanceState);
        
    }
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
       
        // Restore state members from saved instance
//        mThemeId = savedInstanceState.getInt(THEME_ID);
    }
    
    /**
     * Go to "FindReferenceActivity"
     * 
     * @param view
     */
	public void onFindClicked(View view) {
		// Launch ReferenceListActivity
		Intent intent = new Intent(HomeActivity.this, FindReferenceActivity.class);
		intent.putExtras(objetbunble);
		startActivityForResult(intent, FIND_REFERENCE_OPTIONS_CODE);
	}
	
	/**
     * Go to "ContactCardFlipActivity"
     * 
     * @param view
     */
	public void onContactClicked(View view) {
		// Launch ReferenceListActivity
		Intent intent = new Intent(HomeActivity.this, ContactCardFlipActivity.class);
		intent.putExtras(objetbunble);
		startActivityForResult(intent, CONTACT_CARD_OPTIONS_CODE);
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
		
			case R.id.action_settings:
				// Launch Settings Activity
				intent = new Intent(HomeActivity.this, SettingsActivity.class);
				intent.putExtras(objetbunble);
				startActivityForResult(intent, SETTING_OPTIONS_CODE);
				return true;
				
			case R.id.menu_toggleTheme:
				// Change Theme and save it into preferences
				int currentTheme =PreferencesManager.getInstance().getThemePref();
				if (currentTheme == PreferencesManager.THEME_DARK) {
					PreferencesManager.getInstance().saveThemePref(PreferencesManager.THEME_LIGHT);
				} else {
					PreferencesManager.getInstance().saveThemePref(PreferencesManager.THEME_DARK);
				}
				this.recreate();
				return true;
				
			case R.id.action_info:
				// Launch Info Activity
				intent = new Intent(HomeActivity.this, InfosActivity.class);
				intent.putExtras(objetbunble);
				startActivityForResult(intent, ABOUT_OPTIONS_CODE);
				return true;
			
			default:
				return super.onOptionsItemSelected(item);
		}
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		// On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
	    if (requestCode == SETTING_OPTIONS_CODE) {
	      // On vérifie aussi que l'opération s'est bien déroulée
	      if (resultCode == RESULT_OK) {
	        // On affiche le bouton qui a été choisi
	      	Toast.makeText(this, "Les notifications ont été " + ((data.getStringExtra(SETTINGS_BUTTONS).equals(SettingsActivity.NOTIFICATION_ON))?"activées":"désactivées"), Toast.LENGTH_SHORT).show();
	      }
	    }
	    
	    System.out.println("In IT !!");
	    
		// Restore pref theme
		setTheme(PreferencesManager.getInstance().getThemePref());
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
					HomeActivity.this.finish();
					HomeActivity.this.startActivity(HomeActivity.this.getIntent());
				} 
				else 
					HomeActivity.this.recreate();
			}
		}, 1);
	}
	
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_home, container,	false);
			return rootView;
		}
		
	}
	
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//		// ---------------------------------- begin code for Droid Inpector (search for this line in the project to suppress all occurences)
//		ViewServer.get(this).addWindow(this); 
//		// ---------------------------------- end code for Droid Inpector
//    }
// 
    @Override
    public void onResume() {
        super.onResume();
        checkConnection();
		// ---------------------------------- begin code for Droid Inpector (search for this line in the project to suppress all occurences)
		//ViewServer.get(this).addWindow(this); 
		// ---------------------------------- end code for Droid Inpector
    }
    
	public boolean isConnectionOK() {
		ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
	    boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
	    return isConnected;
	}
	
	public void checkConnection() {
	    // Check connectivity
		if (!isConnectionOK()) {
			AlertDialog.Builder adbNoNetwork = new AlertDialog.Builder(this);
			adbNoNetwork.setTitle("Info");
			adbNoNetwork.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
		           }
		       });
			adbNoNetwork.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User cancelled the dialog
		        	   finish();
		           }
		       });
			adbNoNetwork.setMessage("Aucune connexion internet. Voulez-vous vérifier vos paramètres de connexion ?");
			adbNoNetwork.show();
		}
	}
}
