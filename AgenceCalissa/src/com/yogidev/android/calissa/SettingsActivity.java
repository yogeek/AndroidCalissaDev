package com.yogidev.android.calissa;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class SettingsActivity extends Activity {
	
	
	public final static String NOTIFICATION_ON = "Oui";
	public final String NOTIFICATION_OFF = "Non";
	private String notificationChoice = NOTIFICATION_OFF;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
	    
        // Set background
        this.findViewById(android.R.id.content).setBackground(PreferencesManager.getInstance().getBackgoundColorPref());
        
//	    setContentView(R.layout.settings);
	    
	    // Get the Action Bar 
	    ActionBar actionBar = getActionBar();
	    // Enable the app icon as an Up button
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    // Set title
	    actionBar.setTitle(R.string.action_settings);
	    // Set logo
	     actionBar.setLogo(R.drawable.rouages);
	    
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        boolean isFullScreenOn = SP.getBoolean("pref_key_full_screen_on", false);
        boolean isNotificationOn = SP.getBoolean("pref_key_notification_on",false);
        
        if (isFullScreenOn) {
        	Toast.makeText(this, "Passage en mode plein écran", Toast.LENGTH_SHORT).show();
        	hideSystemUI();
        }
        
        if (isNotificationOn) {
        	Toast.makeText(this, "Les notifications sont activées", Toast.LENGTH_SHORT).show();
        }
	    
	
	}
	
	// Go back on Up button because "Settings" is in multiple screen
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}	
	}

	
	
	// This method hides the system bars and resize the content
	public void hideSystemUI() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
				| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
				// remove the following flag for version < API 19
				| View.SYSTEM_UI_FLAG_IMMERSIVE); 
	} 

	public void onToggleClicked(View view) {
	    // Is the toggle on?
	    boolean on = ((ToggleButton) view).isChecked();
	    
	    if (on) {
	        // Enable notification
	    	this.notificationChoice = NOTIFICATION_ON;
	    	Toast.makeText(this, "Passage en mode plein écran", Toast.LENGTH_SHORT).show();
	    	hideSystemUI();
	        
	    } else {
	        // Disable notification
	    	this.notificationChoice = NOTIFICATION_OFF;
	    	Toast.makeText(this, "Mode plein écran désactivé !", Toast.LENGTH_SHORT).show();
	    }
	}
	

}
