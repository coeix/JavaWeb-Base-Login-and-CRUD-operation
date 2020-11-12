package com.login.corso2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
/*The Data Access Object (DAO) pattern is a structural pattern that allows us 
 * to isolate the application/business layer from the persistence layer 
 * (usually a relational database, but it could be any other persistence mechanism) 
 * using an abstract API.
 * The functionality of this API is to hide from the application all the complexities 
 * involved in performing CRUD operations (Create, Read, Update, Delete) in the underlying storage mechanism. 
 * This permits both layers to evolve separately without knowing anything about each other.
 * 
 * Serve per isolare tutte le parti del db dalla parte di gestione dati e dalla parte di interfaccia utente 
 * in classi separate che però poi siano di facile utilizzo dal programma principale
 * suddiviso in 4 classi che si dividono il lavoro (classi client : che sono la parte dell'applicazione
 * del programma che ha bisogno di accedere al db) 
 * https://www.youtube.com/watch?v=ONgiEhCtjHs da min 24
 *
*/

import com.login.corso2.model.Utente;

public interface UtenteDao {

	int createUser(String nome, String cognome, String email, String password)throws SQLException;
	Utente loginUser(String email, String password) throws SQLException;
	ResultSet getUsers() throws SQLException;
	void deleteUser(int id) throws SQLException;
	void updateUser(int _id, Utente _utente) throws SQLException;
	
}
