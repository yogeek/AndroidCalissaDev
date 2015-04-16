package com.yogidev.android.calissa.data.bean;

import java.io.Serializable;

import com.yogidev.android.calissa.R;

/**
 * Classe représentant une recherche de référence 
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

	
	@Override
	public boolean equals(Object o) {
		
		boolean ret = false;
		
		if (o != null && o instanceof Recherche) {
			Recherche another = (Recherche)o;
			if (! this.getName().isEmpty())
				ret = this.getName().equals(another.getName());
			else {
				
				ret =  this.getVille().equals(another.getVille())  
						&& this.getQuartier().equals(another.getQuartier())
						&& this.getType().equals(another.getType())
						&& this.isLocation() == another.isLocation() 	 
						&& this.getLoyer().equals(another.getLoyer());
			}
		}
		return ret;
	}
	
	public int getIconDrawable() {
		String markerType = this.getType() + "-" + (this.isLocation()?"Location":"Vente");
		int marker = R.drawable.loupe;
		if (markerType.equals("T1-Location"))
			marker = R.drawable.marqueur_location_t1;
		if (markerType.equals("T2-Location"))
			marker = R.drawable.marqueur_location_t2;
		if (markerType.equals("T3-Location"))
			marker = R.drawable.marqueur_location_t3;
		if (markerType.equals("T4-Location"))
			marker = R.drawable.marqueur_location_t4;
		if (markerType.equals("T5-Location"))
			marker = R.drawable.marqueur_location_t5;
		if (markerType.equals("T1-Vente"))
			marker = R.drawable.marqueur_vente_t1;
		if (markerType.equals("T2-Vente"))
			marker = R.drawable.marqueur_vente_t2;
		if (markerType.equals("T3-Vente"))
			marker = R.drawable.marqueur_vente_t3;
		if (markerType.equals("T4-Vente"))
			marker = R.drawable.marqueur_vente_t4;
		if (markerType.equals("T5-Vente"))
			marker = R.drawable.marqueur_vente_t5;
		
		return marker;
		
	}

}
