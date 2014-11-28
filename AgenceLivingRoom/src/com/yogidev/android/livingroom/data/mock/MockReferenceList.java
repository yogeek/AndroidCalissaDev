package com.yogidev.android.livingroom.data.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yogidev.android.livingroom.data.bean.Reference;
import com.yogidev.android.livingroom.data.util.Coordinate;

public final class MockReferenceList {
	
	// Liste de références 
	public final static List<Reference> REFERENCE_LIST =   new ArrayList<Reference>(Arrays.asList(
			
			new Reference(1142, 
					"T3 - 60 m² NEUF BBC, avec 1 parking", 
					"T3", 
					"Toulouse", 
					"Les Chalets", 
					"Vente", 
					95000, 
					60, 
					"http://agence-livingroom.com/references/1142/photo_reference.jpg", 
					"Rue des chalets, dans immeuble ancien de deux niveaux avec 12 logements, cet appartement lumineux avec vue sur un jardin ombragé et calme, est constitué d'une belle pièce à vivre avec une mezzanine, un petit coin cuisine équipée et une salle d'eau récemment rénovée. Il y a aussi un cellier privatif. Actuellement loué : 435 E HC"),
		    
			new Reference(1057, 
					"T2 - 51m² avec parking", 
					"T2", 
					"Toulouse", 
					"Argoulets", 
					"Location", 
					800, 
					51, 
					"http://www.agence-livingroom.com/references/1057/photo_reference.jpg", 
					"127 route de Launaguet, au calme dans une résidence récente et fermée. T2 de 46,50m² au rez de chaussée d'un immeuble de deux étages avec un balcon et une place de parking. Appartement en parfait état composé d'une entrée, un séjour avec accès au balcon et à la cuisine, (La cuisine est équipée et possède un cellier), une chambre avec un placard, une salle de bains et des wc séparés. Carrelage au sol dans les pièces principales."),
		    
			new Reference(1100, 
					"T4 - 90 m²", 
					"T4", 
					"Toulouse", 
					"Matabiau", 
					"Vente", 
					253150, 
					90, 
					"http://www.agence-livingroom.com/references/1100/photo_reference.jpg", 
					"60 CHEMIN RAYNAL, proche du lac de la Maourine, idéal pour un peu de footing ou des promenades, intermarché à proximité, et transports (métro + bus(36 - 19 - 38)) accessibles rapidement. Appartement T2 de 41m² avec balcon de 7m² dans résidence fermée, au calme. Chauffage individuel au gaz. 1 parking."),
		    
			new Reference(1129, 
					"T5 - 104 m² ", 
					"T5", 
					"Toulouse", 
					"Hypercentre", 
					"Vente", 
					547000, 
					104, 
					"http://agence-livingroom.com/references/1129/photo_reference.jpg", 
					"A 10 mn à pied de la Place du Capitole, parmi les emplacements les plus recherchés en hyper-centre, Le Patio de Tounis est situé sur l’avenue de la Garonnette et s’intègre harmonieusement dans l’environnement prestigieux de la Garonne, des quais de Tounis et de la Daurade. L’architecture contemporaine, aux lignes épurées, met en valeur de grandes façades avec parements, de larges ouvertures, spacieux balcons et terrasses avec pergolas en attique. Conçu spécialement pour l’accession en résidence principale, Le Patio de Tounis dispose d’un large choix d’appartements, du studio au 5 pièces, ainsi que des T3 duplex. Spacieux et accueillants, les logements reçoivent chaleureusement leurs hôtes, particulièrement les familles qui partageront agréablement des espaces de vie aussi confortables que fonctionnels. Le salon inondé de soleil, la cuisine et la salle de bains parfaitement équipées, reflètent tout un art de vivre au quotidien. T5 104m² au 2ème étage. Livraison 2eme trimestre 14"),
			
			new Reference(1022, 
					"T2 - 37m² en bord de garonne", 
					"T2", 
					"Toulouse", 
					"Amidonniers", 
					"Location", 
					571, 
					37, 
					"http://agence-livingroom.com/references/1022/photo_reference.jpg", 
					"bus. T2 de 37 m² avec vue dégagée. Séjour entièrement carrelé, kitchenette équipée et aménagée (plaques 2 feux au gaz, réfrigérateur), chambre, salle de bains avec WC. Double vitrage. Chauffage au gaz. 1 PARKING"),
			
			new Reference(1110, 
					"Grand Selve - T3 - 60 m² NEUF BBC, avec 1 parking", 
					"T3", 
					"Toulouse", 
					"Grand Selve", 
					"Vente", 
					194250, 
					60, 
					"http://www.agence-livingroom.com/references/1110/photo_reference.jpg", 
					"Quartier GRAND SELVE, au 16 rue Simone Henry, proche du métro et de l’accès rocade. Bus TISSEO 26. Dans résidence neuve BBC avec ascenseur, visiophone et digicodes. Appartement T3 traversant de 60.23m² très bien agencé et lumineux. Au rez de chaussée surélevé. Entrée, séjour très lumineux avec accès sur agréable balcon de 6.56m², kitchenette équipée, 2 chambres (10 et 13m²) avec chacune un placard, salle de bains et wc séparés. 1 parking en sous sol. Chauffage au gaz collectif. Logement actuellement loué 530 € HC."),
	
			new Reference(1120, 
					"Providence - T1Bis - 33 m² , avec 1 parking", 
					"T1", 
					"Toulouse", 
					"Guilhemery", 
					"Location", 
					520, 
					33, 
					"http://www.agence-livingroom.com/references/1110/photo_reference.jpg", 
					"Quartier GRAND SELVE, au 16 rue Simone Henry, proche du métro et de l’accès rocade. Bus TISSEO 26. Dans résidence neuve BBC avec ascenseur, visiophone et digicodes. Appartement T3 traversant de 60.23m² très bien agencé et lumineux. Au rez de chaussée surélevé. Entrée, séjour très lumineux avec accès sur agréable balcon de 6.56m², kitchenette équipée, 2 chambres (10 et 13m²) avec chacune un placard, salle de bains et wc séparés. 1 parking en sous sol. Chauffage au gaz collectif. Logement actuellement loué 530 € HC.")
			));
	
	
	// Equipements
	static {
		
		
		REFERENCE_LIST.get(0).setListeEquipements(new ArrayList<String>(Arrays.asList("Parking","Gardien")));
		REFERENCE_LIST.get(0).setLatLon(new Coordinate(43.5748164f, 1.4767395999999735f));
		REFERENCE_LIST.get(0).setDpe("F");
		REFERENCE_LIST.get(0).setGes("D");
		REFERENCE_LIST.get(0).setFraisAgence(2);
		REFERENCE_LIST.get(0).setDepotOuTaxe(1129);
		REFERENCE_LIST.get(0).setChargesOuCopro(550);
		REFERENCE_LIST.get(0).addPhoto("http://agence-livingroom.com/references/1142/photo_1709.jpg");
		REFERENCE_LIST.get(0).addPhoto("http://agence-livingroom.com/references/1142/photo_1710.jpg");
		REFERENCE_LIST.get(0).addPhoto("http://agence-livingroom.com/references/1142/photo_1708.jpg");
		REFERENCE_LIST.get(0).addPhoto("http://agence-livingroom.com/references/1142/photo_1711.jpg");
		REFERENCE_LIST.get(0).addPhoto("http://agence-livingroom.com/references/1142/photo_1713.jpg");
		REFERENCE_LIST.get(0).addPhoto("http://agence-livingroom.com/references/1142/photo_1714.jpg");
		REFERENCE_LIST.get(0).addPhoto("http://agence-livingroom.com/references/1142/photo_1715.jpg");
		REFERENCE_LIST.get(0).addPhoto("http://agence-livingroom.com/references/1142/photo_1716.jpg");
		
		REFERENCE_LIST.get(1).setListeEquipements(new ArrayList<String>(Arrays.asList("Double vitrage","Parking")));
		REFERENCE_LIST.get(1).setLatLon(new Coordinate(43.4989035f, 1.3074595f));
		REFERENCE_LIST.get(1).setDpe("A");
		REFERENCE_LIST.get(1).setGes("G");
		REFERENCE_LIST.get(1).setFraisAgence(460);
		REFERENCE_LIST.get(1).setDepotOuTaxe(460);
		REFERENCE_LIST.get(1).setChargesOuCopro(50);
		REFERENCE_LIST.get(1).addPhoto("http://agence-livingroom.com/references/1142/photo_1708.jpg");
		REFERENCE_LIST.get(1).addPhoto("http://agence-livingroom.com/references/1142/photo_1710.jpg");
		REFERENCE_LIST.get(1).addPhoto("http://agence-livingroom.com/references/1142/photo_1709.jpg");
		REFERENCE_LIST.get(1).addPhoto("http://agence-livingroom.com/references/1142/photo_1711.jpg");
		REFERENCE_LIST.get(1).addPhoto("http://agence-livingroom.com/references/1142/photo_1713.jpg");
		REFERENCE_LIST.get(1).addPhoto("http://agence-livingroom.com/references/1142/photo_1714.jpg");
		REFERENCE_LIST.get(1).addPhoto("http://agence-livingroom.com/references/1142/photo_1715.jpg");
		REFERENCE_LIST.get(1).addPhoto("http://agence-livingroom.com/references/1142/photo_1716.jpg");
		
		REFERENCE_LIST.get(2).setListeEquipements(new ArrayList<String>(Arrays.asList("Double vitrage","Gardien")));
		REFERENCE_LIST.get(2).setLatLon(new Coordinate(43.594593f, 1.443270f));
		REFERENCE_LIST.get(2).setDpe("A");
		REFERENCE_LIST.get(2).setGes("B");
		REFERENCE_LIST.get(2).setFraisAgence(2);
		REFERENCE_LIST.get(2).setDepotOuTaxe(1129);
		REFERENCE_LIST.get(2).setChargesOuCopro(550);
		REFERENCE_LIST.get(2).addPhoto("http://agence-livingroom.com/references/1142/photo_1709.jpg");
		REFERENCE_LIST.get(2).addPhoto("http://agence-livingroom.com/references/1142/photo_1710.jpg");
		REFERENCE_LIST.get(2).addPhoto("http://agence-livingroom.com/references/1142/photo_1708.jpg");
		REFERENCE_LIST.get(2).addPhoto("http://agence-livingroom.com/references/1142/photo_1711.jpg");
		REFERENCE_LIST.get(2).addPhoto("http://agence-livingroom.com/references/1142/photo_1713.jpg");
		REFERENCE_LIST.get(2).addPhoto("http://agence-livingroom.com/references/1142/photo_1714.jpg");
		REFERENCE_LIST.get(2).addPhoto("http://agence-livingroom.com/references/1142/photo_1715.jpg");
		REFERENCE_LIST.get(2).addPhoto("http://agence-livingroom.com/references/1142/photo_1716.jpg");
		
		REFERENCE_LIST.get(3).setListeEquipements(new ArrayList<String>(Arrays.asList("Parking")));
		REFERENCE_LIST.get(3).setLatLon(new Coordinate(43.5875071f, 1.457837100000006f));
		REFERENCE_LIST.get(3).setDpe("C");
		REFERENCE_LIST.get(3).setGes("C");
		REFERENCE_LIST.get(3).setFraisAgence(2);
		REFERENCE_LIST.get(3).setDepotOuTaxe(1017);
		REFERENCE_LIST.get(3).setChargesOuCopro(550);
		REFERENCE_LIST.get(3).addPhoto("http://agence-livingroom.com/references/1129/photo_1526.jpg");
		REFERENCE_LIST.get(3).addPhoto("http://agence-livingroom.com/references/1129/photo_1530.jpg");
		REFERENCE_LIST.get(3).addPhoto("http://agence-livingroom.com/references/1129/photo_1531.jpg");
		REFERENCE_LIST.get(3).addPhoto("http://agence-livingroom.com/references/1129/photo_1532.jpg");
		REFERENCE_LIST.get(3).addPhoto("http://agence-livingroom.com/references/1129/photo_1533.jpg");
		REFERENCE_LIST.get(3).addPhoto("http://agence-livingroom.com/references/1129/photo_1528.jpg");
		
		REFERENCE_LIST.get(4).setListeEquipements(new ArrayList<String>(Arrays.asList("Parking")));
		REFERENCE_LIST.get(4).setLatLon(new Coordinate(43.605308f, 1.4264141999999537f));
		REFERENCE_LIST.get(4).setDpe("A");
		REFERENCE_LIST.get(4).setGes("A");
		REFERENCE_LIST.get(4).setFraisAgence(460);
		REFERENCE_LIST.get(4).setDepotOuTaxe(460);
		REFERENCE_LIST.get(4).setChargesOuCopro(50);
		REFERENCE_LIST.get(4).addPhoto("http://agence-livingroom.com/references/1142/photo_1709.jpg");
		REFERENCE_LIST.get(4).addPhoto("http://agence-livingroom.com/references/1142/photo_1710.jpg");
		REFERENCE_LIST.get(4).addPhoto("http://agence-livingroom.com/references/1142/photo_1708.jpg");
		REFERENCE_LIST.get(4).addPhoto("http://agence-livingroom.com/references/1142/photo_1711.jpg");
		REFERENCE_LIST.get(4).addPhoto("http://agence-livingroom.com/references/1142/photo_1713.jpg");
		REFERENCE_LIST.get(4).addPhoto("http://agence-livingroom.com/references/1142/photo_1714.jpg");
		REFERENCE_LIST.get(4).addPhoto("http://agence-livingroom.com/references/1142/photo_1715.jpg");
		REFERENCE_LIST.get(4).addPhoto("http://agence-livingroom.com/references/1142/photo_1716.jpg");
		
		REFERENCE_LIST.get(5).setListeEquipements(new ArrayList<String>(Arrays.asList("Parking")));
		REFERENCE_LIST.get(5).setLatLon(new Coordinate(43.561351f, 1.409631f));
		REFERENCE_LIST.get(5).setDpe("A");
		REFERENCE_LIST.get(5).setGes("A");
		REFERENCE_LIST.get(5).setFraisAgence(2);
		REFERENCE_LIST.get(5).setDepotOuTaxe(1017);
		REFERENCE_LIST.get(5).setChargesOuCopro(550);
		REFERENCE_LIST.get(5).addPhoto("http://agence-livingroom.com/references/1022/photo_205.jpg");
		REFERENCE_LIST.get(5).addPhoto("http://agence-livingroom.com/references/1022/photo_207.jpg");
		REFERENCE_LIST.get(5).addPhoto("http://agence-livingroom.com/references/1022/photo_208.jpg");
		REFERENCE_LIST.get(5).addPhoto("http://agence-livingroom.com/references/1022/photo_209.jpg");
		REFERENCE_LIST.get(5).addPhoto("http://agence-livingroom.com/references/1022/photo_210.jpg");
		REFERENCE_LIST.get(5).addPhoto("http://agence-livingroom.com/references/1022/photo_211.jpg");
		REFERENCE_LIST.get(5).addPhoto("http://agence-livingroom.com/references/1022/photo_212.jpg");
		
		REFERENCE_LIST.get(6).setListeEquipements(new ArrayList<String>(Arrays.asList("Parking")));
		REFERENCE_LIST.get(6).setLatLon(new Coordinate(43.6557568f, 1.469586f));
		REFERENCE_LIST.get(6).setDpe("A");
		REFERENCE_LIST.get(6).setGes("A");
		REFERENCE_LIST.get(6).setFraisAgence(460);
		REFERENCE_LIST.get(6).setDepotOuTaxe(460);
		REFERENCE_LIST.get(6).setChargesOuCopro(50);
		REFERENCE_LIST.get(6).addPhoto("http://agence-livingroom.com/references/1022/photo_205.jpg");
		REFERENCE_LIST.get(6).addPhoto("http://agence-livingroom.com/references/1022/photo_207.jpg");
		REFERENCE_LIST.get(6).addPhoto("http://agence-livingroom.com/references/1022/photo_208.jpg");
		REFERENCE_LIST.get(6).addPhoto("http://agence-livingroom.com/references/1022/photo_209.jpg");
		REFERENCE_LIST.get(6).addPhoto("http://agence-livingroom.com/references/1022/photo_210.jpg");
		REFERENCE_LIST.get(6).addPhoto("http://agence-livingroom.com/references/1022/photo_211.jpg");
		REFERENCE_LIST.get(6).addPhoto("http://agence-livingroom.com/references/1022/photo_212.jpg");
	}

}
