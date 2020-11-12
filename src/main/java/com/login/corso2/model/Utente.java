package com.login.corso2.model;

public class Utente {
	
	private String nome,cognome,email,password;
	private int id;
	
	public Utente (int _id,String _nome, String _cognome,String _email, String _password) {
		
		this.id=_id;
		this.nome=_nome;
		this.cognome=_cognome;
		this.email=_email;
		this.password=_password;
	}

	public void setId(int _id) {
		this.id = _id;
	}
	
	public void setNome(String _nome) {
		this.nome = _nome;
	}

	public void setCognome(String _cognome) {
		this.cognome = _cognome;
	}
	
	public void setPassword(String _password) {
		this.password = _password;
	}

	public void setEmail(String _email) {
		this.email = _email;
	}

	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getId () {
		return id;
	}

	@Override
	public String toString() {
		return "Utente [nome: " + nome + ", cognome: " + cognome + ", email=" + email + ", password: " + password + ", id=" + id + "]";
	}

	
}
