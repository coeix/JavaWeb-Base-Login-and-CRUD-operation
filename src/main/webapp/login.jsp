<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>Login</title>

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
	
	<div id="Login">
		<!-- Il valore Post viene utilizzato quando non ci sono dati sensibili da trattare
		POST -> invia i parametri tramite HTML POST
		GET  -> invia i parametri tramite URL  -->
		<form action=login method='POST'>
			<table>
				<tr>
					<td><p id="email"/> Email:</td>
					<td><input type='text' name='email'></td>
				</tr>
				<tr>
					<td><p id="password"/> Password:</td>
					<td><input type='text' name='password'></td>
				</tr>
			</table>
			<input id="login" type='submit' value='Login'> 
			<input type="reset" value="Reset"><br>
			
		</form>
			
	</div>
	<br/><br/>
	<div>Se non sei ancora registrato procedi <a href="registrazione.jsp">qui con una nuova registrazione</a></div>