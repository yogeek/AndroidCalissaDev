package com.yogidev.android.livingroom;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class HomeActivity extends Activity {
	
	public static final int SETTING_OPTIONS_CODE = 1;
	public static final int ABOUT_OPTIONS_CODE = 2;
	public static final int FIND_REFERENCE_OPTIONS_CODE = 3;
	public static final int CONTACT_CARD_OPTIONS_CODE = 4;
	public static final int MAP_ACTIVITY_OPTIONS_CODE = 5;
	
	Bundle objetbunble;
	
	// L'identifiant de la chaîne de caractères qui contient le résultat de l'intent
	public final static String SETTINGS_BUTTONS = "com.yogidev.android.intent.settings.Boutons";
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Reset Prefs
//		PreferencesManager.getInstance().getPrefEditor().clear().commit();
//		
//		
//		boolean isFullScreen = PreferencesManager.getInstance().getSharedPref().getBoolean(PreferencesManager.FULL_SCREEN_KEY, false);
//		boolean isNotifOn = PreferencesManager.getInstance().getSharedPref().getBoolean(PreferencesManager.NOTIFICATION_KEY, false);
//		
//		Toast.makeText(this, "FULL SCREEN = " + isFullScreen, Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "NOTIF = " + isNotifOn, Toast.LENGTH_SHORT).show();
		
		SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		if (SP.getBoolean("pref_key_full_screen_on",false)) {
			hideSystemUI();
		}
		
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
     * Go to "MapActivity"
     * 
     * @param view
     */
	public void onCarteClicked(View view) {
		// Launch ReferenceListActivity
		Intent intent = new Intent(HomeActivity.this, MapActivity.class);
		intent.putExtras(objetbunble);
		startActivityForResult(intent, MAP_ACTIVITY_OPTIONS_CODE);
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
		switch (requestCode) {
		case SETTING_OPTIONS_CODE:

			SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

			boolean isFullScreenOn = SP.getBoolean("pref_key_full_screen_on", false);
			boolean isNotificationOn = SP.getBoolean("pref_key_notification_on",false);

			if (isFullScreenOn) {
				hideSystemUI();
			}
			else {
				showSystemUI();
			}

			break;
		}


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
	
	// This snippet hides the system bars.
	private void hideSystemUI() {
	    // Set the IMMERSIVE flag.
	    // Set the content to appear under the system bars so that the content
	    // doesn't resize when the system bars hide and show.
		getWindow().getDecorView().setSystemUiVisibility(
	            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
	            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
	            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
	            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
	            | View.SYSTEM_UI_FLAG_IMMERSIVE);
	}

	// This snippet shows the system bars. It does this by removing all the flags
	// except for the ones that make the content appear under the system bars.
	private void showSystemUI() {
		getWindow().getDecorView().setSystemUiVisibility(
	            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
	            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
	            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
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
