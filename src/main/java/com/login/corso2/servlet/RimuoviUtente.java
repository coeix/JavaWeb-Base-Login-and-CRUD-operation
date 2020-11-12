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
 * Servlet implementation class Login
 */
@WebServlet("/delete")
public class RimuoviUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviUtente() {
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
		String idDaRimuovere = request.getParameter("idDaRimuovere");
		int _idDaRimuovere = Integer.parseInt(idDaRimuovere);
		
		System.out.println("- Delete.java - id da eliminare: " + _idDaRimuovere);
		
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
			//System.out.println(" ::::: Rimuovo "+_idDaRimuovere+ " "+_utenteDaRimuovere);
			utenteDaoImpl.deleteUser(_idDaRimuovere);
			rs = utenteDaoImpl.getUsers();
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			// Qui si blocca per qualsiasi tipo di errore ma non per un risultato = null
			System.out.println("Errore di qualcosa");	
			e.printStackTrace();
		}

		//System.out.println("ResultSet = "+rs);
		RequestDispatcher rd; //creo l'istanza
		//request è un'attributo di RD
		request.setAttribute("rs",rs);
		rd = getServletContext().getRequestDispatcher("/profilo.jsp");
		rd.forward(request, response);
	
	}

}
