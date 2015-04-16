package com.yogidev.android.calissa.data.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe gérant la connexion à la base de données. (pattern singleton)
 * 
 * @author YoGi
 *
 */

public class DatabaseConnection {

	/**
	 * URL de connection
	 */
	private static String url = "jdbc:postgresql://localhost:5432/Societe";
	
	/**
	 * Nom du user
	 */
	private static String user = "db_login";
	
	/**
	 * Mot de passe du user
	 */
	private static String passwd = "db_password";
	
	/**
	 * Objet Connection
	 */
	private static Connection connect;
	

	/**
	 * Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
	 * @return
	 */
	public static Connection getInstance(){
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return connect;	
	}	

}
