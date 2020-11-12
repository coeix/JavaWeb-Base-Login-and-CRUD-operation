<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<title>Nuovo utente registrato</title>
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
	</style>
<%
	int errore = (int) request.getAttribute("problema"); 
	String messaggio = "";
	if(errore == 1){
		messaggio = "inserisci una mail valida e prova nuovamente a registrarti";
	}
%>
	<div>
		<% if(errore != 1){ %>
			<h1>UTENTE REGISTRATO</h1>	
		<% } 
		else { %>
			<h1><%=messaggio%></h1>
		<% }  %>
	</div>
	<br/><br/>
	<div>Continua nel tuo <a href="profilo.jsp">profilo</a></div>