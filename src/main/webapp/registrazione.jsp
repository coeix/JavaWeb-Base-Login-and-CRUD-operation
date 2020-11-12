<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>Registrazione</title>
	<style>
		body {
			margin: 6%;
			background-color: ghostwhite;
		}
		div{
			border-radius: 20px;
			padding: 2%;
			border-style: solid;
			border-color: lightgrey;
			background-color: white;
		}
		input{
			padding: 6px;
			background-color: ghostwhite;
			border-radius: 5px;
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
	<%	
		if(request.getAttribute("problema") != null){
			int errore = (int) request.getAttribute("problema"); 
			if(errore == 2){
	%>
	<div>
				<h1>UTENTE NON REGISTRATO</h1>
				<h2>La tua email non risulta nel database!!! Effettua una nuova registrazione</h2>
	</div>
	<br/><br/>
	<%	}}
	%>
	<div id="Registrazione">
		<!-- Il valore Post viene utilizzato quando non ci sono dati sensibili da trattare
		POST -> invia i parametri tramite HTML POST
		GET  -> invia i parametri tramite URL  -->
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
	<br/><br/>
	<div>Se non sei registrato <a href="login.jsp">Accedi qui</a></div>