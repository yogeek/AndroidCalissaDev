package com.yogidev.android.calissa.data.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe g�rant la connexion � la base de donn�es. (pattern singleton)
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
	 * M�thode qui va nous retourner notre instance et la cr�er si elle n'existe pas
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
