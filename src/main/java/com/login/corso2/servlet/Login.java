package com.login.corso2.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.corso2.dao.UtenteDaoImpl;
import com.login.corso2.model.Utente;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//blocco della macro operazione recupero dei dati proveniente dalla JSP
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("- Login.java - Login utente: " + email);
		
		int segnaleErrore = 0;
		Utente utente = null;
		ResultSet rs = null;
		
		UtenteDaoImpl utenteDaoImpl = null;
		
		try {
			//BLOCCO OBBLIGATORIO che serve per far si che venga recuperata o creata una 
			//instanza dello strumento predisposto all'interazione con il db (strumento dao)
			//sintassi defiita singleton
			utenteDaoImpl = UtenteDaoImpl.getDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			utente = utenteDaoImpl.loginUser(email,password);
			rs = utenteDaoImpl.getUsers();
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			// Qui si blocca per qualsiasi tipo di errore ma non per un risultato = null
			System.out.println("Errore Login");	
			segnaleErrore = 1;
			
			e.printStackTrace();
		}
		if (utente != null) {
			
			System.out.println("Utente = "+utente);
			System.out.println("ResultSet = "+rs);
			RequestDispatcher rd; //creo l'istanza
			//request è un'attributo di RD
			request.setAttribute("utente",utente);
			request.setAttribute("rs",rs);
			HttpSession session=request.getSession();
			session.setAttribute("utenteLoggato",utente);
			rd = getServletContext().getRequestDispatcher("/profilo.jsp");
			rd.forward(request, response);
		}
		else {
			System.out.println("Utente non registrato lo rimando alla pagina di registrazione");
			//è il vigile che assegna la destinazione alla variabile
			RequestDispatcher rd; //creo l'istanza
			//request è un'attributo di RD
			segnaleErrore = 2;
			request.setAttribute("problema", segnaleErrore);
			rd = getServletContext().getRequestDispatcher("/registrazione.jsp");
			rd.forward(request, response);
		}
	}

}
