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
 * This servlet is used to update a track in the db.
 */
@WebServlet("/ServletUpdate")
public class ServletUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUpdate() {
        super();
    }

	/**
	 * Get tracks info from db so that they can be visualized correctly into updatetrack.jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET REQUEST ON /ServletUpdate");

		request.setAttribute("choice", 1);
		request.setAttribute("destination", "updatetrack.jsp");
		request.getRequestDispatcher("/ServletGetInfo").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST REQUEST ON /ServletUpdate");
		doPut(request, response);
	}

	/**
	 * Update info of a track in the db
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("PUT REQUEST ON /ServletUpdate");
		
		//get parameters from form
		String trackid = request.getParameter("trackid");
//		String name = request.getParameter("name");
//		int albumid = Integer.parseInt(request.getParameter("albumid"));
//		int genreid = Integer.parseInt(request.getParameter("genreid"));
//		String composer = request.getParameter("composer");
		int choice = Integer.parseInt(request.getParameter("choice"));
		String value = request.getParameter("value");
		
		
		try {
			DataSource ds = (DataSource) new InitialContext().lookup("java:/sqliteds");//get connection through datasource
			Connection conn = ds.getConnection();
			
			
			Statement stmt=conn.createStatement();
			
			//create ad hoc query according to which information the user wants to modify
			String query = "UPDATE tracks SET ";
			switch(choice) {
				case 1:{
					query+="Name='"+value+"' WHERE TrackId="+trackid;
					break;
				}
				case 2:{
					query+="AlbumId="+value+" WHERE TrackId="+trackid;
					break;
				}
				case 3:{
					query+="GenreId="+value+" WHERE TrackId="+trackid;
					break;
				}
				case 4:{
					query+="Composer='"+value+"' WHERE TrackId="+trackid;
					break;
				}
				default:{
					query = "";
				}
			}
			//System.out.println(query);
			//execute query
			stmt.executeUpdate(query);
		}catch (SQLException e) {	//TODO redirect to error page
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		response.sendRedirect("/CrudOperations-0.0.1-SNAPSHOT/ServletSelect");
	}

}
