<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="com.login.corso2.model.Utente"
    import="java.sql.ResultSet"
    import="java.sql.ResultSetMetaData"
    import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
	<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!-- -------------------------------------- CSS ------------------------------------------ -->
	<style>
		body {
			margin: 6%;
			background-color: ghostwhite;
		}
		div.up{
			border-radius: 20px;
			padding: 2%;
			border-style: solid;
			border-color: lightgrey;
			background-color: white;
		}
		div.contenuto{
			border-radius: 20px;
			padding: 2%;
			border-style: solid;
			border-color: lightgrey;
			background-color: white;
		}
		table.tabella {
		  	margin-left: 5%;
		  	border-collapse: collapse;
		}
		table, th, td {
  			border: 1px solid black;
  			padding: 6px;
		}
		th {
			background-color: ghostwhite;
		}
		h1, h2{
			color: navy;
		}
		p {
			margin-left: 10px;
		}
		input[type=submit], input[type=reset]{
			font-weight: bold;
			padding: 6px 12px;
			background-color: ghostwhite;
			margin: 4px 2px;
			cursor: pointer;
			border-radius: 5px;
		}
	</style>
<!-- ---------------------------------- FINE CSS ------------------------------------------ -->
	<title> Il tuo profilo utente</title>
	
<!-- -------------------------------------- SESSIONE -------------------------------------- -->
<%
	int colCount = 0;
	Utente _utente = null;
	ResultSet _rs = null;
	ResultSetMetaData rsmtadta = null;
	//To get a session without creating a new one if not exist
	
	if (session == null) {
		// no session, return an error page
%>	
		<h1>Nessuna sessione Attiva</h1>
		<p>Effettua il <a href="login.jsp">Login</a></p>
<%
    }     
	else{
		// session retrieved, continue with servlet operations
		// controllo se l'utennte passato è valido ed esiste e lo stampo 
		if(session.getAttribute("utenteLoggato") != null){
			_utente = (Utente) session.getAttribute("utenteLoggato"); 
%>
<!-- ---------------------------------- FINE SESSIONE -------------------------------------- -->

			<div class="up">
				<h1>Benvenuto <%= _utente.getNome() %> <%= _utente.getCognome() %></h1>
				<p>la tua email è: <c:out value="${_utente.getEmail()}"/></p>
				<p>A quanto pare sei Admin della pagina e puoi vedere tutto quanto</p>
			</div>
			<br/><br/>
	<div class="container contenuto">
		<div class="tab-content" id="v-pills-tabContent">
			<div class="row">
				<div class="col-9">
					<div class="tab-pane fade show active" id="lista" role="tabpanel" aria-labelledby="v-pills-home-tab">
			<%			// Stampo la tabella degli utenti
						if(request.getAttribute("rs") != null) {
							_rs = (ResultSet) request.getAttribute("rs"); 
							// Create a ResultSetMetaData object
							rsmtadta = _rs.getMetaData();
							// Trova il numero delle colonne da generare
							colCount = rsmtadta.getColumnCount();
		%>
					      	<h2>Tabella <%= rsmtadta.getTableName(1) %></h2>
							<table class="tabella">
								
								<tr>
<% 								// creazione dell'intestazione
								for (int i=1; i<=colCount; i++) {
										// Get column name %>
										<th><%= rsmtadta.getColumnName(i) %></th>
<% 								}
%>
										<th>Delete</th>
										<th>Update</th>
								</tr>
<%								// Creazione delle righe della tabella che contengono i dati del db
								// per ogni riga
								while (_rs.next()){
%>
									<tr>
<%										// per ogni cella
										for (int i=1; i<=colCount; i++) {
											// Get column name
%>
											<td><%= _rs.getString(i) %></td>
<%										}
										// aggiungo le due celle di elimina e modifica 
%>
										<td>
											<form action=delete method='POST'>
												<input type="hidden" name="emailChiamante" value="<%= _utente.getEmail() %>">
												<input type="hidden" name="pswChiamante" value="<%= _utente.getPassword() %>">
												<input type="hidden" name="idDaRimuovere" value="<%= _rs.getString(1) %>">
												<input id="delete" type="submit" value="Delete">
											</form>
										</td>
										<td>
											<form action=viewUser method='POST'>
												<input type="hidden" name="userDaModificare" value="<%= _rs.getString(4) %>">
												<input type="hidden" name="pswDaModificare" value="<%= _rs.getString(5) %>">
												<input id="update" type="submit" value="Update">
											</form>
										</td>
									</tr>
<%								}
%>
							</table>

<%						} 
						else {
%>
						<h1>Tabella vuota</h1>
				
<%						}
%>
					</div>
<!-- -------------------------------------- REGISTRAZIONE UTENTE -------------------------------------- -->
					<div class="tab-pane fade" id="registraUtente" role="tabpanel" aria-labelledby="v-pills-profile-tab">
						<form action=registrazione method='POST'>
							<table>
								<tr>
									<td><p id="nome"/> Nome:</td>
									<td><input type='text' name='nome'></td>
								</tr>
								<tr>
									<td><p id="cognome"/> Cognome:</td>
									<td><input type='text' name='cognome'></td>
								</tr>
								<tr>
									<td><p id="email"/> Email:</td>
									<td><input type='text' name='email'></td>	
								</tr>
								<tr>
									<td><p id="password"> Password:</td>
									<td><input type='text' name='password'></td>
								</tr>
							</table>
							<input id="registrati" type='submit' value='Registrati'>
							<input type="reset" value="Reset"><br>									
						</form>
					</div>
				<!--  chiusura della  class="col-9"-->
				</div>
<!-- -------------------------------------- NAV BAR LATERALE -------------------------------------- -->
				<div class="col-3">
					<br/><br/>
					<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
						<a class="nav-link active" id="v-pills-home" data-toggle="pill" href="lista" role="tab" aria-controls="v-pills-home" aria-selected="true">Lista utenti</a>
					    <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#registraUtentee" role="tab" aria-controls="v-pills-profile" aria-selected="false">Nuovo utente</a>
					    <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab" aria-controls="v-pills-messages" aria-selected="false">Messages</a>
					    <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings" role="tab" aria-controls="v-pills-settings" aria-selected="false">Settings</a>
				    </div>
			    </div>
	    	<!--  chiusura della  class="row"-->
			</div>
		<!--  chiusura della class="tab-content" -->
		</div>
	<!--  chiusura della class="container contenuto" -->
	</div>
<!-- -------------------------------------- NAV BAR LATERALE -------------------------------------- -->	
				
<% 		}
		else { 
%>
			<h1>Accesso non effettuato</h1>
			<p>Effettua il <a href="login.jsp">Login</a></p>
<% 		}
	}
%>
