package com.yogidev.android.livingroom.data.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.yogidev.android.livingroom.data.util.Coordinate;

/**
 * Classe représentant une référence de la base de données 
 * 
 * @author YoGi
 *
 */
public class Reference implements Parcelable {
	
	// Ref
	private long id = 0;
	
	// Titre de la référence
	private String titreRef = "";
	
	// Type de référence
	private String typeRef = "";
	
	// Titre pour l'administrateur (non vu par les visiteurs)
	private String titreAdmin = "";
	
	// Information interne
	private String infoInterne = "";
	
	// Location ou Vente
	private String locVente = "";
	
	// isLocation
	private boolean isLocation = true;
	
	// Appartement ou Maison
	private String appartementMaison = "";
	
	// Ville
	private String ville = "";
	
	// Quartier
	private String quartier = "";
	
	// Loyer charges comprises (ou Prix FAI dans le cas d'une vente)
	private int loyerOuPrix = 0;
	
	// Charges (ou Charges de copropriété dans le cas d'une vente)
	private int chargesOuCopro = 0;
	
	// Dépôt de garantie (ou taxe foncière dans le cas d'une vente)
	private int depotOuTaxe = 0;
	
	// Frais d'agence (en % dans le cas d'une vente)
	private double fraisAgence = 0.0;
	
	// Latitude/Longitude
	private Coordinate latLon = null;
	
	// Descriptif
	private String descriptif = "";
	
	// Surface (en m2)
	private double surface = 0.0;
	
	// Nombre de lots de la copropriété
	private int nbLotCopro = 0;
	
	// DPE
	private String dpe = "";
	
	// GES
	private String ges = "";
	
	// Equipements
	private List<String> listeEquipements = null;
	
	// URL Vignette
	private String vignette = null;
	
	// Liste des URL des photos
	private ArrayList<String> photos = null;
	
	// Video youtube
	private String videoYoutube = null;
	
	// Disponible ?
	private boolean disponible = true;
	
	// Nouveauté ?
	private boolean nouveaute = true;
	
	// Visible ?
	private boolean visible = true;
	
	// Libre à partir du
	private Date libreDate = null;
	
	public Reference() {
	}


	/**
	 * Constructeur
	 * 
	 */
	
	public Reference(long id, String titreRef, String typeRef, String ville, 
			String quartier, String locVente, int loyerOuPrix, double surface, String vignette, String description) {
		this.id = id;
		this.titreRef = titreRef;
		this.typeRef = typeRef;
		this.ville = ville;
		this.quartier = quartier;
		this.setLocVente(locVente);
		this.loyerOuPrix = loyerOuPrix;
		this.surface = surface;
		this.vignette = vignette;
		this.photos = new ArrayList<String>();
		this.descriptif = description;
	}
	
	public void addPhoto(String photo) {
		this.photos.add(photo);
	}
	
	public void addPhotoList(ArrayList<String> photoList) {
		this.photos.addAll(photoList);
	}
	
	public void removePhoto(String photo) {
		this.photos.remove(photo);
	}
	

	public Reference(long id, String titreRef, String typeRef,
			String titreAdmin, String infoInterne, String locVente,
			String appartementMaison, String ville, String quartier,
			int loyerOuPrix, int chargesOuCopro, int depotOuTaxe,
			double fraisAgence, Coordinate latLon, String descriptif,
			double surface, int nbLotCopro, String dpe, String ges,
			List<String> listeEquipements, String vignette, String videoYoutube,
			boolean disponible, boolean nouveaute, boolean visible,
			Date libreDate) {
		super();
		this.id = id;
		this.titreRef = titreRef;
		this.typeRef = typeRef;
		this.titreAdmin = titreAdmin;
		this.infoInterne = infoInterne;
		this.locVente = locVente;
		this.appartementMaison = appartementMaison;
		this.ville = ville;
		this.quartier = quartier;
		this.loyerOuPrix = loyerOuPrix;
		this.chargesOuCopro = chargesOuCopro;
		this.depotOuTaxe = depotOuTaxe;
		this.fraisAgence = fraisAgence;
		this.latLon = latLon;
		this.descriptif = descriptif;
		this.surface = surface;
		this.nbLotCopro = nbLotCopro;
		this.dpe = dpe;
		this.ges = ges;
		this.listeEquipements = listeEquipements;
		this.vignette = vignette;
		this.videoYoutube = videoYoutube;
		this.disponible = disponible;
		this.nouveaute = nouveaute;
		this.visible = visible;
		this.libreDate = libreDate;
	}
	
	
	
	//------------------------------------ parcel part BEGIN ------------------------------------
//	public Reference(Parcel in){
//
//		//		String[] data= new String[28];
//		//		in.readStringArray(data);
//
//		this.id= in.readLong();
//		this.titreRef= in.readString();
//		this.titreAdmin = in.readString();
//		this.infoInterne = in.readString();
//		this.locVente = in.readString();
//		this.appartementMaison = in.readString();
//		this.ville = in.readString();
//		this.quartier = in.readString();
//		this.loyerOuPrix = in.readInt();
//		this.chargesOuCopro = in.readInt();
//		this.depotOuTaxe = in.readInt();
//		this.fraisAgence = in.readDouble();
//		//		this.latLon = data[12];
//		this.descriptif = in.readString();
//		this.surface = in.readDouble();
//		this.nbLotCopro = in.readInt();
//		this.dpe = in.readString();
//		this.ges = in.readString();
//		this.listeEquipements = (ArrayList<String>) in.readSerializable();
//		this.vignette = in.readString();
//		this.videoYoutube = in.readString();
//		//		this.disponible = in.readBooleanArray();
//		//		this.nouveaute = Boolean.parseBoolean(data[22]);
//		//		this.visible = Boolean.parseBoolean(data[23]);
//		//		this.libreDate = in.read
//	}
//
//	@Override
//	public int describeContents() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void writeToParcel(Parcel dest, int flags) {
//		// TODO Auto-generated method stub
//
//		dest.writeLong(this.id);
//		dest.writeString(this.titreRef);
//		dest.writeString(this.titreAdmin);
//		dest.writeString(this.infoInterne);
//		dest.writeString(this.locVente);
//		dest.writeString(this.appartementMaison);
//		dest.writeString(this.ville);
//		dest.writeString(this.quartier);
//		dest.writeInt(this.loyerOuPrix);
//		dest.writeInt(this.chargesOuCopro);
//		dest.writeDouble(this.fraisAgence);
//		//	this.latLon = data[12];
//		dest.writeString(this.descriptif);
//		dest.writeDouble(this.surface);
//		dest.writeInt(this.nbLotCopro);
//		dest.writeString(this.dpe);
//		dest.writeString(this.ges);
//		dest.writeSerializable((Serializable) this.listeEquipements);
//		dest.writeString(this.vignette);
//		//	this.videoYoutube = data[20];
//		//	this.disponible = in.readBooleanArray();
//		//	this.nouveaute = Boolean.parseBoolean(data[22]);
//		//	this.visible = Boolean.parseBoolean(data[23]);
//		//	this.libreDate = in.read
//
//
//	}
//
//	public static final Parcelable.Creator<Reference> CREATOR= new Parcelable.Creator<Reference>() {
//
//		@Override
//		public Reference createFromParcel(Parcel source) {
//			// TODO Auto-generated method stub
//			return new Reference(source);  //using parcelable constructor
//		}
//
//		@Override
//		public Reference[] newArray(int size) {
//			// TODO Auto-generated method stub
//			return new Reference[size];
//		}
//	};
	
	//------------------------------------ parcel part END ------------------------------------
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////
	protected Reference(Parcel in) {
		id = in.readLong();
		titreRef = in.readString();
		typeRef = in.readString();
		titreAdmin = in.readString();
		infoInterne = in.readString();
		locVente = in.readString();
		isLocation = in.readByte() != 0x00;
		appartementMaison = in.readString();
		ville = in.readString();
		quartier = in.readString();
		loyerOuPrix = in.readInt();
		chargesOuCopro = in.readInt();
		depotOuTaxe = in.readInt();
		fraisAgence = in.readDouble();
		latLon = (Coordinate) in.readValue(Coordinate.class.getClassLoader());
		descriptif = in.readString();
		surface = in.readDouble();
		nbLotCopro = in.readInt();
		dpe = in.readString();
		ges = in.readString();
		if (in.readByte() == 0x01) {
			listeEquipements = new ArrayList<String>();
			in.readList(listeEquipements, String.class.getClassLoader());
		} else {
			listeEquipements = null;
		}
		vignette = in.readString();
		if (in.readByte() == 0x01) {
			photos = new ArrayList<String>();
			in.readList(photos, String.class.getClassLoader());
		} else {
			photos = null;
		}
		videoYoutube = in.readString();
		disponible = in.readByte() != 0x00;
		nouveaute = in.readByte() != 0x00;
		visible = in.readByte() != 0x00;
		long tmpLibreDate = in.readLong();
		libreDate = tmpLibreDate != -1 ? new Date(tmpLibreDate) : null;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(titreRef);
		dest.writeString(typeRef);
		dest.writeString(titreAdmin);
		dest.writeString(infoInterne);
		dest.writeString(locVente);
		dest.writeByte((byte) (isLocation ? 0x01 : 0x00));
		dest.writeString(appartementMaison);
		dest.writeString(ville);
		dest.writeString(quartier);
		dest.writeInt(loyerOuPrix);
		dest.writeInt(chargesOuCopro);
		dest.writeInt(depotOuTaxe);
		dest.writeDouble(fraisAgence);
		dest.writeValue(latLon);
		dest.writeString(descriptif);
		dest.writeDouble(surface);
		dest.writeInt(nbLotCopro);
		dest.writeString(dpe);
		dest.writeString(ges);
		if (listeEquipements == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(listeEquipements);
		}
		dest.writeString(vignette);
		if (photos == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(photos);
		}
		dest.writeString(videoYoutube);
		dest.writeByte((byte) (disponible ? 0x01 : 0x00));
		dest.writeByte((byte) (nouveaute ? 0x01 : 0x00));
		dest.writeByte((byte) (visible ? 0x01 : 0x00));
		dest.writeLong(libreDate != null ? libreDate.getTime() : -1L);
	}

	public static final Parcelable.Creator<Reference> CREATOR = new Parcelable.Creator<Reference>() {
		@Override
		public Reference createFromParcel(Parcel in) {
			return new Reference(in);
		}

		@Override
		public Reference[] newArray(int size) {
			return new Reference[size];
		}
	};
///////////////////////////////////////////////////////////////////////////////////////////
	
	

	/**
	 * toString()
	 */
	public String toString(){
		String str = 	"Reference : " + this.getId() + "\n";
		str += 			"Titre : " + this.getTitreRef() + "\n";
		str += 			"Type : " + this.getTypeRef() + "\n";
		str += 			"Titre admin : " + this.getTitreAdmin() + "\n";
		str += 			"Info interne : " + this.getInfoInterne() + "\n";
		str += 			"Location/Vente : " + this.getLocVente() + "\n";
		str += 			"Appartement/Maison : " + this.getAppartementMaison() + "\n";
		str += 			"Ville : " + this.getVille() + "\n";
		str += 			"Quartier : " + this.getQuartier() + "\n";
		str += 			"Loyer ou Prix : " + this.getLoyerOuPrix() + "\n";
		str +=			"\n.....................................\n";
		
		return str;
	}


	/** 
	 * Accesseurs
	 * 
	 */
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTypeRef() {
		return typeRef;
	}


	public void setTypeRef(String typeRef) {
		this.typeRef = typeRef;
	}


	public String getTitreAdmin() {
		return titreAdmin;
	}


	public void setTitreAdmin(String titreAdmin) {
		this.titreAdmin = titreAdmin;
	}


	public String getInfoInterne() {
		return infoInterne;
	}


	public void setInfoInterne(String infoInterne) {
		this.infoInterne = infoInterne;
	}


	public String getLocVente() {
		return locVente;
	}


	public void setLocVente(String locVente) {
		this.locVente = locVente;
		setLocation(locVente.equals("Location"));
	}
	
	
	public boolean isLocation() {
		return this.locVente.equals("Location");
	}


	public void setLocation(boolean isLocation) {
		this.isLocation = isLocation;
		this.locVente = isLocation?"Location":"Vente";
	}


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


	public int getPrix() {
		return loyerOuPrix;
	}


	public void setPrix(int prix) {
		this.loyerOuPrix = prix;
	}
	
	
	public boolean isDisponible() {
		return disponible;
	}


	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


	public boolean isNouveaute() {
		return nouveaute;
	}


	public void setNouveaute(boolean nouveaute) {
		this.nouveaute = nouveaute;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	

	public String getTitreRef() {
		return titreRef;
	}


	public void setTitreRef(String titreRef) {
		this.titreRef = titreRef;
	}


	public String getAppartementMaison() {
		return appartementMaison;
	}


	public void setAppartementMaison(String appartementMaison) {
		this.appartementMaison = appartementMaison;
	}


	public int getLoyerOuPrix() {
		return loyerOuPrix;
	}


	public void setLoyerOuPrix(int loyerOuPrix) {
		this.loyerOuPrix = loyerOuPrix;
	}


	public int getChargesOuCopro() {
		return chargesOuCopro;
	}


	public void setChargesOuCopro(int chargesOuCopro) {
		this.chargesOuCopro = chargesOuCopro;
	}


	public int getDepotOuTaxe() {
		return depotOuTaxe;
	}


	public void setDepotOuTaxe(int depotOuTaxe) {
		this.depotOuTaxe = depotOuTaxe;
	}


	public double getFraisAgence() {
		return fraisAgence;
	}


	public void setFraisAgence(double fraisAgence) {
		this.fraisAgence = fraisAgence;
	}


	public Coordinate getLatLon() {
		return latLon;
	}


	public void setLatLon(Coordinate latLon) {
		this.latLon = latLon;
	}


	public String getDescriptif() {
		return descriptif;
	}


	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}


	public double getSurface() {
		return surface;
	}
	
	public int getSurfaceInteger() {
		return (int)surface;
	}


	public void setSurface(double surface) {
		this.surface = surface;
	}


	public int getNbLotCopro() {
		return nbLotCopro;
	}


	public void setNbLotCopro(int nbLotCopro) {
		this.nbLotCopro = nbLotCopro;
	}


	public String getDpe() {
		return dpe;
	}


	public void setDpe(String dpe) {
		this.dpe = dpe;
	}


	public String getGes() {
		return ges;
	}


	public void setGes(String ges) {
		this.ges = ges;
	}


	public List<String> getListeEquipements() {
		return listeEquipements;
	}


	public void setListeEquipements(List<String> listeEquipements) {
		this.listeEquipements = listeEquipements;
	}


	public String getVignette() {
		return vignette;
	}


	public void setVignette(String vignette) {
		this.vignette = vignette;
	}


	public String getVideoYoutube() {
		return videoYoutube;
	}


	public void setVideoYoutube(String videoYoutube) {
		this.videoYoutube = videoYoutube;
	}


	public Date getLibreDate() {
		return libreDate;
	}


	public void setLibreDate(Date libreDate) {
		this.libreDate = libreDate;
	}


	public ArrayList<String> getPhotos() {
		return photos;
	}


	public void setPhotos(ArrayList<String> photos) {
		this.photos = photos;
	}


}
