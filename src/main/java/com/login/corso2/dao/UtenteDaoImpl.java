package com.login.corso2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;

import com.login.corso2.model.Utente;

public class UtenteDaoImpl implements UtenteDao{

	private static UtenteDaoImpl utenteDaoImpl;
	private static Connection con;

	public static UtenteDaoImpl getDao() throws SQLException, ClassNotFoundException {
		
		if ( utenteDaoImpl == null) {
			
			utenteDaoImpl = new UtenteDaoImpl ();
		}
			
			return utenteDaoImpl;
	}
	
	private UtenteDaoImpl () throws SQLException, ClassNotFoundException {

		System.out.println("Collegamento a Database in corso...!");
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/mydb";
		con = DriverManager.getConnection(url,"root","root");
		System.out.println("Collegamento effettuato con successo!");	
	}
	
	public int createUser(String nome, String cognome, String email, String password) throws SQLException {

		String sql = "INSERT INTO users (nome, cognome, email, password) VALUES (?, ?, ?, ?)";
		//con prepairedStatement creiamo la stringa in questo modo:
		//INSERT INTO users (nome=?, cognome=?, email=?, password=?)
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, nome);
		ps.setString(2, cognome);
		ps.setString(3, email);
		ps.setString(4, password);
		//metodo valido solo per INSERT - UPDATE - DELETE quindi non SELECT
		ps.executeUpdate(); 
		
		// ResulSet è di tipo contenitore e in questo caso rs conterrà l'id della chiave generata di ps 
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next() == true)
			//1 sta per la prima colonna: quindi restituisce il valore contenuto nella prima colonna
			//getInt o getString
			return rs.getInt(1);
		else
			return -1;
	}
	
	//LOGIN
	public Utente loginUser(String email, String password) throws SQLException{
		String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, email);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		//System.out.println("qui ci arrivo con rs=" +rs);
		if(rs.next() == true) {
			String _nome = rs.getString("nome");
        	String _cognome = rs.getString("cognome");
        	String _email = rs.getString("email");
        	String _password = rs.getString("password");
        	int _id = rs.getInt(1);
        	Utente _utente = new Utente (_id,_nome,_cognome,_email,_password);
        	System.out.println("Ho recuperato l'utente richiesto \n "+
        			_id + ", " +_nome + ", " + _cognome + ", " + _email);
			return _utente;
		}
		else
			return null;
	}
	//Recupero tutti gli utenti
	public ResultSet getUsers() throws SQLException{
		
		//ArrayList <Utente> tuttiGliUtenti = new ArrayList <>();
		
		String sql = "SELECT * FROM users";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
/*		while (rs.next()) {
			int _id = rs.getInt(1);
			String _nome = rs.getString("nome");
        	String _cognome = rs.getString("cognome");
        	String _email = rs.getString("email");
        	String _password = rs.getString("password");
        	Utente _utente = new Utente (_id,_nome,_cognome,_email,_password);
        	tuttiGliUtenti.add(_utente);
		}
*/
		return rs;
	}
	
	public void deleteUser(int id) throws SQLException{
		String sql = "DELETE FROM users WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		//System.out.println(" ----- Provo ad eliminare "+id);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	public void updateUser(int _id, Utente _utente) throws SQLException {
		String sql = "UPDATE users SET nome=?, cognome=?, email=?,password=? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		//System.out.println(" ----- Provo ad eliminare "+id);
		ps.setString(1, _utente.getNome());
		ps.setString(2, _utente.getCognome());
		ps.setString(3, _utente.getEmail());
		ps.setString(4, _utente.getPassword());
		ps.setInt(5, _id);
		ps.executeUpdate();
	}

}
