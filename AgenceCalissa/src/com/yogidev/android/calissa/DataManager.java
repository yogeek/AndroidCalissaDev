package com.yogidev.android.calissa;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Classe permettant de gérer la source de données de l'agence.
 * Actuellement : un fichier html extrait de la page d'administration du site web et contenant les différents biens en ligne
 * 
 * 
 * @author YoGi
 *
 */
public class DataManager {
	
//	public final static String URL = "http://www.agence-livingroom.com/admin.php?tri=&locvente=location_et_vente&enligne=1";
	
	
	/**
	 * Méthode permettant de récupérer le code HTML de la page dont l'adresse est indiquée en paramètre
	 * 
	 * @param adresse
	 * @return string contenent le code de la page
	 */
	public static String getIpFrom(String adresse) { 
		String toreturn = null;
		try { 
			//		creation d'un objet URL 
			URL url = new URL(adresse); 
			//		on etablie une connection a cette url 
			URLConnection uc = url.openConnection(); 
			//		on y cree un flux de lecture 
			InputStream in = uc.getInputStream(); 
			//		on lit le premier bit 
			int c = in.read(); 
			//		on cree un StringBuilder pour par la suite y ajouter tout les bit lus 
			StringBuilder build = new StringBuilder(); 
			//		tant que c n'est pas egale au bit indiquant la fin d'un flux... 
			while (c != -1) { 
				build.append((char) c); 
				//		...on l'ajoute dasn le StringBuilder... 
				c = in.read(); 
				//		...on lit le suivant 
			} 
			//		on retourne le code de la page 
			toreturn = build.toString(); 

		} catch (MalformedURLException e) { 

			e.printStackTrace(); 
		} catch (IOException e) { 

			e.printStackTrace(); 
		}
		return toreturn;
	}
	
	

}
