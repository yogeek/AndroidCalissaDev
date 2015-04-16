package com.yogidev.android.calissa;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

// AsyckTask to download image (url given )
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
   ImageView bmImage;
   Drawable defaultImage;
   
   //constructor
   public DownloadImageTask(ImageView bmImage, Drawable defaultImage) {
       this.bmImage = bmImage;
       this.defaultImage = defaultImage;
   }
   
   // laoding picture and put it into bitmap 
   protected Bitmap doInBackground(String... urls) {
       String urldisplay = urls[0];
       Bitmap mIcon11 = null;
       try {
         InputStream in = new java.net.URL(urldisplay).openStream();
         mIcon11 = BitmapFactory.decodeStream(in);
       } catch (Exception e) {
           Log.e("Error", e.getMessage());
           e.printStackTrace();
       }
       return mIcon11;
   }
   
   //after downloading
   protected void onPostExecute(Bitmap result) {
       if (result != null)
    	   bmImage.setImageBitmap(result);
       else
    	   bmImage.setImageDrawable(defaultImage);
   }
 }
