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

import org.example.Bean.Album;
import org.example.Bean.Albums;
import org.example.Bean.Genre;
import org.example.Bean.Genres;

/**
 * This servlet is used to insert a track into the db.
 */
@WebServlet("/ServletInsert")
public class ServletInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInsert() {
        super();
    }

	/**
	 * Get the list of albums and genres from db to create the form in inserttrack.jsp properly
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET REQUEST ON /ServletInsert");

		request.setAttribute("choice", 2);
		request.setAttribute("destination", "inserttrack.jsp");
		request.getRequestDispatcher("/ServletGetInfo").forward(request, response);
	}

	/**
	 * Add a new track to db with info from inserttrack.jsp form
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST REQUEST ON /ServletInsert");
		
		//get parameters from form
		String name = request.getParameter("name");
		int albumid = Integer.parseInt(request.getParameter("albumid"));
		int mediatype = 1;
		int genreid = Integer.parseInt(request.getParameter("genreid"));
		String composer = request.getParameter("composer");
		int milliseconds = 	343719;
		int bytes = 11170334;
		double unitprice = 4.99;
		
		try {
			DataSource ds = (DataSource) new InitialContext().lookup("java:/sqliteds");//get connection through datasource
			Connection conn = ds.getConnection();
			
			//execute insert query
			Statement stmt=conn.createStatement();
			String query = "INSERT INTO tracks (Name, AlbumId, MediatypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice) values ('"+name+"', '"+albumid+"', '"+mediatype+"', '"+genreid+"', '"+composer+"', '"+milliseconds+"', '"+bytes+"', '"+unitprice+"')";
			//System.out.println(query);
			stmt.executeUpdate(query);
		} catch (SQLException e) {//TODO redirect to error page
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		response.sendRedirect("/CrudOperations-0.0.1-SNAPSHOT/ServletSelect");
	}

}
