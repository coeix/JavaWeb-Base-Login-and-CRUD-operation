<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="com.login.corso2.model.Utente"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>Aggiorna Utente</title>
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
		.id{
			color: grey;
		}
	</style>

<%	
	Utente _utente = null;
	// controllo se l'utente passato è valido ed esiste e lo stampo 
	if(request.getAttribute("utente") != null){
	_utente = (Utente) request.getAttribute("utente");
%>
		<div class="up">
			<h1>Update dell'utente <%= _utente.getNome() %> <%= _utente.getCognome() %></h1>
		</div>
		<br/><br/>

	<div id="Update">
		<form action=update method='POST'>
			<table>
				<tr>
					<td><p class="id"/> Id:</td> 
					<td><input class="id" type='text' name='id' value='<%= _utente.getId() %>' readonly="readonly"></td>
				</tr>
				<tr>
					<td><p id="nome"/> Nome:</td>
					<td><input type='text' name='nome' value='<%= _utente.getNome() %>'></td>
				</tr>
				<tr>
					<td><p id="cognome"/> Cognome:</td>
					<td><input type='text' name='cognome' value='<%= _utente.getCognome() %>'></td>
				</tr>
				<tr>
					<td><p id="email"> Email:</td>
					<td><input type='text' name='email' value='<%= _utente.getEmail() %>'></td>	
				</tr>
				<tr>
					<td><p id="password"/> Password:</td>
					<td><input type='text' name='password' value='<%= _utente.getPassword() %>'></td>
				</tr>
			</table>	
			<input id="modifica" type='submit' value='Modifica'>
			<input type="reset" value="Reset"><br>
			
		</form>
			
	</div>
	<% } %>