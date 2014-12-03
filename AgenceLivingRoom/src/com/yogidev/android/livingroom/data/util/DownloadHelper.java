package com.yogidev.android.livingroom.data.util;

import java.io.File;
import java.util.List;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;

public class DownloadHelper {
	
	private Context context;
	
	//constructor
	public DownloadHelper(Context ctx, String destination) {
		this.context = ctx;
	}
	
	/**
	 * Download (HTTP) a file located at "url" with the native DownloadManager in the given directory with the given name
	 * 
	 * url : URL of the distant file
	 * destinationFile : the local file name
	 * destinationPath : directory in which the file will be downloaded
	 * example : 
	 * 			- DEFAULT : getExternalFilesDir() = private app dir
	 * 			- Environment.DIRECTORY_DOWNLOADS = default download dir on phone
	 * 			- getExternalCacheDir() = private app cache dir
	 * 			
	 * 
	 * @param url
	 * @param destinationPath
	 * @param destinationfile
	 */
	public void downloadFile(String url, String destinationPath, String destinationFile) {
		if (isDownloadManagerAvailable(context)) {
			DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
			request.setDescription("Récupération vignette");
			request.setTitle("Get image");
			// If no destination given, take the defaults app dir
			String destinationDir = destinationPath.isEmpty()?context.getFilesDir().getAbsolutePath():destinationPath;
			// in order for this if to run, you must use the android 3.2 to compile your app
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				request.allowScanningByMediaScanner();
				//			request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
				request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
			}
			request.setDestinationInExternalPublicDir(destinationDir, new File(destinationPath,destinationFile).getPath());

			// get download service and enqueue file
			DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
			manager.enqueue(request);
		}
		else {
			new AlertDialog.Builder(context)
			.setTitle("Erreur")
			.setMessage("Fonctionnalité non disponible sur votre version d'android")
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					// do nothing
				}
			}).show();
		}
	}

	/**
	 * @param context used to check the device version and DownloadManager information
	 * @return true if the download manager is available
	 */
	public static boolean isDownloadManagerAvailable(Context context) {
	    try {
	        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
	            return false;
	        }
	        Intent intent = new Intent(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_LAUNCHER);
	        intent.setClassName("com.android.providers.downloads.ui", "com.android.providers.downloads.ui.DownloadList");
	        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
	                PackageManager.MATCH_DEFAULT_ONLY);
	        return list.size() > 0;
	    } catch (Exception e) {
	        return false;
	    }
	}

}
