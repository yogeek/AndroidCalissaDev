package com.yogidev.android.livingroom.data.bean;

import java.io.Serializable;

/**
 * Classe repr�sentant une recherche de r�f�rence 
 * 
 * @author YoGi
 *
 */

public class Recherche implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name = "";
	
	private String ville = "";
	
	private String quartier = "";
	
	private String type = "";
	
	private boolean isLocation = true;
	
	private String loyer = "";
	
	
	/**
	 * Constructeur
	 * 
	 */

	public Recherche(String name, String ville, String quartier, String type,
			boolean isLocation, String loyer) {
		this.name = name;
		this.ville = ville;
		this.quartier = quartier;
		this.type = type;
		this.isLocation = isLocation;
		this.loyer = loyer;
	}
	

	public Recherche(String ville, String quartier, String type,
			boolean isLocation, String loyer) {
		this.ville = ville;
		this.quartier = quartier;
		this.type = type;
		this.isLocation = isLocation;
		this.loyer = loyer;
	}


	/**
	 * toString()
	 */
	public String toString(){
		String str = 	"Recherche : " + this.getName() + "\n";
		str += 			"Ville : " + this.getVille() + "\n";
		str += 			"Quartier : " + this.getQuartier() + "\n";
		str += 			"Type : " + this.getType() + "\n";
		str += 			"Location/Vente : " + (this.isLocation()?"Location":"Vente") + "\n";
		str += 			"Loyer : " + this.getLoyer() + "\n";
		str +=			"\n.....................................\n";
		
		return str;
	}
	
	
	/** 
	 * Accesseurs
	 * 
	 */

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isLocation() {
		return isLocation;
	}

	public void setLocation(boolean isLocation) {
		this.isLocation = isLocation;
	}

	public String getLoyer() {
		return loyer;
	}

	public void setLoyer(String loyer) {
		this.loyer = loyer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
