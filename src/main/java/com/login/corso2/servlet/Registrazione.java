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

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrazione() {
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
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println(" - Registrazione.java - Registrazione in corso di " + nome + " " + cognome);
		
		int segnaleErrore = 0;
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
			utenteDaoImpl.createUser(nome,cognome,email,password);
			rs = utenteDaoImpl.getUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Errore Registrazione");	
			segnaleErrore = 1;
			
			e.printStackTrace();
		}
		
		System.out.println("Stampo il valore del segnale Errore Registrazione" + segnaleErrore);
		
		//è il vigile che assegna la destinazione alla variabile
		RequestDispatcher rd; //creo l'istanza
		//request è un'attributo di RD
		request.setAttribute("rs",rs);
		rd = getServletContext().getRequestDispatcher("/profilo.jsp");
		rd.forward(request, response);
	}

}
