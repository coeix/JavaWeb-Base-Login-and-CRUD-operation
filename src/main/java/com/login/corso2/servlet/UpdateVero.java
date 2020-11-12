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

/**
 * Servlet implementation class Login
 */
@WebServlet("/update")
public class UpdateVero extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateVero() {
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
		String _id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int id = Integer.parseInt(_id);
		Utente utente= new Utente(id,nome,cognome,email,password);
		Utente _utente=null;
		
		System.out.println(" - UpdateVero.java - Modifica in corso di " + nome + " " + cognome);
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
			utenteDaoImpl.updateUser(id,utente);
			//_utente = utenteDaoImpl.loginUser(_emailChiamante,_pswChiamante);
			rs = utenteDaoImpl.getUsers();
			System.out.println("::::::::: Stampo utente chiamante = "+_utente);
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			// Qui si blocca per qualsiasi tipo di errore ma non per un risultato = null
			System.out.println("Errore UpdateVero");	
			e.printStackTrace();
		}
		
		RequestDispatcher rd; //creo l'istanza
		//request è un'attributo di RD
		request.setAttribute("rs",rs);
		rd = getServletContext().getRequestDispatcher("/profilo.jsp");
		rd.forward(request, response);
	}

}
