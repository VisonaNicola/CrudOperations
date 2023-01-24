package org.example.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.example.Bean.Track;
import org.example.Bean.Tracks;


/**
 * This servlet is used to select tracks from the db.
 */
@WebServlet("/ServletSelect")
public class ServletSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSelect() {
        super();
    }

	/**
	 * Get tracks info from db
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET REQUEST ON /ServletSelect");

		request.setAttribute("choice", 1);
		request.setAttribute("destination", "gettracks.jsp");
		request.getRequestDispatcher("/ServletGetInfo").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST REQUEST ON /ServletSelect");	
	}

}
