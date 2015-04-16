package com.yogidev.android.calissa.data.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import android.content.Context;
import android.widget.Toast;

import com.yogidev.android.calissa.data.bean.Recherche;

public class SerialTool {

	public static String getRechercheSerialFile(Recherche p) {
		return p.getName() + Constants.RECHERCHE_SERIAL_FILE;
	}

	public static String getRechercheSerialFile(String name) {
		return name + Constants.RECHERCHE_SERIAL_FILE;
	}


	/**
	 * -------------------------------------------------------------------------------------
	 * SAVE functions
	 * -------------------------------------------------------------------------------------
	 */

	public static void saveRecherche(Recherche p, Context ctx) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			String fileName = getRechercheSerialFile(p);
			fos = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(p); 
			oos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(ctx, "Erreur de Sauvegarde",Toast.LENGTH_SHORT).show(); 
		}catch(IOException e){
			e.printStackTrace();
			Toast.makeText(ctx, "Erreur de Sauvegarde",Toast.LENGTH_SHORT).show(); 
		} finally {
			try {
				oos.close();
				fos.close();
			} catch (IOException e) {
				Toast.makeText(ctx, "Erreur de Sauvegarde",Toast.LENGTH_SHORT).show(); 
			}

		}
	}

	/**
	 * -------------------------------------------------------------------------------------
	 * RESTORE functions
	 * -------------------------------------------------------------------------------------
	 */

	public static Recherche restoreRecherche(Recherche p, Context ctx) {

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try
		{
			fis = ctx.openFileInput(getRechercheSerialFile(p));
			ois = new ObjectInputStream(fis);
			p = (Recherche)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(ctx, "Erreur de Chargement" + e,Toast.LENGTH_SHORT).show(); 
		}catch(IOException e){
			e.printStackTrace();
			Toast.makeText(ctx, "Erreur de Chargement" + e,Toast.LENGTH_SHORT).show(); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(ctx, "Erreur de Chargement" + e,Toast.LENGTH_SHORT).show(); 
		}

		return p;
	}
	
	public static boolean isRechercheNameFree(String name, Context ctx) {
		boolean isFree = false;
		try
		{
			ctx.openFileInput(getRechercheSerialFile(name));
		} catch (FileNotFoundException e) {
			isFree = true;
		}
		return isFree;
	}

	public static Recherche restoreRecherche(String nom, Context ctx) {

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Recherche p = null;
		try
		{
			fis = ctx.openFileInput(getRechercheSerialFile(nom));
			ois = new ObjectInputStream(fis);
			p = (Recherche)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(ctx, "Erreur de Chargement" + e,Toast.LENGTH_SHORT).show(); 
		}catch(IOException e){
			e.printStackTrace();
			Toast.makeText(ctx, "Erreur de Chargement" + e,Toast.LENGTH_SHORT).show(); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(ctx, "Erreur de Chargement" + e,Toast.LENGTH_SHORT).show(); 
		}

		return p;
	}
	
	public static List<Recherche> getAllSavedRecherche(Context ctx) {

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Recherche r = null;
		List<Recherche> resultList = new ArrayList<Recherche>();
		try
		{
			File myDirectory = ctx.getFilesDir();
			if (myDirectory.exists() && myDirectory.isDirectory()){
				// pattern to find files escaping the "."
			    final Pattern p = Pattern.compile(".+" + (Constants.RECHERCHE_SERIAL_FILE).replace(".", "\\."));

			    
			    File[] fl = myDirectory.listFiles();
			    
			    
			    File[] flists = myDirectory.listFiles(new FileFilter() {
			        @Override
			        public boolean accept(File file) {
			            return p.matcher(file.getName()).matches();
			        }
			    });

			    for (File f : flists) {
			    	fis = ctx.openFileInput(f.getName());
					ois = new ObjectInputStream(fis);
					r = (Recherche)ois.readObject();
					resultList.add(r);
					ois.close();
			    }
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(ctx, "Erreur de Chargement" + e,Toast.LENGTH_SHORT).show(); 
		}catch(IOException e){
			e.printStackTrace();
			Toast.makeText(ctx, "Erreur de Chargement" + e,Toast.LENGTH_SHORT).show(); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(ctx, "Erreur de Chargement" + e,Toast.LENGTH_SHORT).show(); 
		}

		return resultList;
	}
	
	// is the Recherche already in the favorites ?
	public static String getAlreadySavedName(Recherche currentRecherche, Context ctx) {
		String name = "";
		if (getAllSavedRecherche(ctx).contains(currentRecherche)) {
			int index = getAllSavedRecherche(ctx).indexOf(currentRecherche);
			name = getAllSavedRecherche(ctx).get(index).getName();
		}
		return name;
	}


	/**
	 * -------------------------------------------------------------------------------------
	 * DELETE functions
	 * -------------------------------------------------------------------------------------
	 */

	public static boolean deleteRecherche(Recherche p, Context ctx) {
		return ctx.deleteFile(getRechercheSerialFile(p));
	}

	public static boolean deleteRecherche(String nom, Context ctx) {
		return ctx.deleteFile(getRechercheSerialFile(nom));
	}

}