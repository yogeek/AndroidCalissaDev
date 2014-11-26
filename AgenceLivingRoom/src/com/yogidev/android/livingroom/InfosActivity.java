package com.yogidev.android.livingroom;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class InfosActivity extends Activity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    // Restore pref theme
	    setTheme(PreferencesManager.getInstance().getThemePref());
	    
	    // Inflate the view from XML
	    setContentView(R.layout.info);
	    
	    // Set background
	    findViewById(R.id.containerInfo).setBackground(PreferencesManager.getInstance().getBackgoundColorPref());
	    
	    TextView textAdress = (TextView) findViewById(R.id.textInfoAdress);
	    textAdress.setMovementMethod(LinkMovementMethod.getInstance());
	    
	    
	    // Get the Action Bar 
	    ActionBar actionBar = getActionBar();
	    // Enable the app icon as an Up button
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    // Set title
	    actionBar.setTitle(R.string.action_infos);
	    // Set logo
	    // actionBar.setLogo(R.drawable.rouages);
	    
	    // to display the icon and not the logo
//	    actionBar.setDisplayUseLogoEnabled(false);
//	    actionBar.setDisplayHomeAsUpEnabled(true);
	    
	
	}
	
}
