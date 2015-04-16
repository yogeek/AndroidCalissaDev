package com.yogidev.android.calissa;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.ColorDrawable;

import com.yogidev.android.calissa.data.bean.Recherche;
import com.yogidev.android.calissa.data.util.Constants;

public class PreferencesManager {

	// Preferences File
	public static final String PREFS_NAME = "CalissaPrefsFile";
	// Preferences Mode
	public static final int PREFS_MODE = 0;
	
	// Opacity
	public static final int TRANPARENCY = 200;
	
	// Theme
	public static final int THEME_LIGHT = R.style.AppTheme_Light;
	public static final int THEME_DARK = R.style.AppTheme_Dark;
	
	// Background color
	public static final int BG_COLOR_LIGHT = R.color.actionbar_background_light;
	public static final int BG_COLOR_DARK = R.color.actionbar_background_dark;
	
	// Full Screen mode
	public static final String FULL_SCREEN_KEY = "pref_key_full_screen_settings";
	public static final String NOTIFICATION_KEY = "pref_key_notification_on";
	
	private static final String THEME_INT_KEY = "THEME";
	private static SharedPreferences mPreferences;
	private static PreferencesManager mInstance;
	private static Editor mEditor;

	private PreferencesManager() {
	}

	public static PreferencesManager getInstance() {
		if (mInstance == null) {
			Context context = CalissaApplication.mContext;
			mInstance = new PreferencesManager();
			mPreferences = context.getSharedPreferences(PREFS_NAME, PREFS_MODE);
			mEditor = mPreferences.edit();
		}
		return mInstance;
	}

	public void saveThemePref(Integer value) {
		mEditor.putInt(THEME_INT_KEY, value).apply();
	}

	public Integer getThemePref() {
		return mPreferences.getInt(THEME_INT_KEY, THEME_LIGHT);
	}
	
	public SharedPreferences getSharedPref() {
		return mPreferences;
	}
	
	public Editor getPrefEditor() {
		return mEditor;
	}

	/**
	 * Return the background color according to the current theme
	 */
	public ColorDrawable getBackgoundColorPref() {
		ColorDrawable cd;
		if (getThemePref()==THEME_LIGHT) {
			cd = new ColorDrawable(CalissaApplication.mContext.getResources().getColor(BG_COLOR_LIGHT));
		}
		else {
			cd = new ColorDrawable(CalissaApplication.mContext.getResources().getColor(BG_COLOR_DARK));
			
		}
			
		return cd;
	}
	
	public void saveCurrrentRecherchePref(Recherche currentRecherche) {
		Editor editor = getPrefEditor();
		editor.putString(Constants.RECHERCHE_VILLE, currentRecherche.getVille());
		editor.putString(Constants.RECHERCHE_QUARTIER, currentRecherche.getQuartier());
		editor.putString(Constants.RECHERCHE_TYPE, currentRecherche.getType());
		editor.putBoolean(Constants.RECHERCHE_IS_LOCATION, currentRecherche.isLocation());
		editor.putString(Constants.RECHERCHE_PRIX, currentRecherche.getLoyer());
		editor.commit();
	}
	
	/**
	 * Save a shared preference
	 * 
	 * @param themeId
	 * @param apptheme
	 */
//	public void savePref(String themeId, int apptheme) {
//		// We need an Editor object to make preference changes.
//	      // All objects are from android.context.Context
//	      SharedPreferences settings = CalissaApplication.mContext.getSharedPreferences(PREFS_NAME, PREFS_MODE);
//	      SharedPreferences.Editor editor = settings.edit();
//	      // Save pref theme
//	      editor.putInt(themeId, apptheme);
//	      // Commit the edits!
//	      editor.commit();
//	}

}
