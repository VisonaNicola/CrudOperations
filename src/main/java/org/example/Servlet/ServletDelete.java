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
 * This servlet is used to delete a track from the db.
 */
@WebServlet("/ServletDelete")
public class ServletDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDelete() {
        super();
    }

	/**
	 * Get the lists of tracks from db so that it can be visualized in deletetrack.jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET REQUEST ON /ServletDelete");

		request.setAttribute("choice", 1);
		request.setAttribute("destination", "deletetrack.jsp");
		request.getRequestDispatcher("/ServletGetInfo").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST REQUEST ON /ServletDelete");
		doDelete(request,response);
	}

	/**
	 * Delete the selected track from db
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DELETE REQUEST ON /ServletDelete");
		String trackid = request.getParameter("trackid");	//get id of the track from form
		
		try {
			DataSource ds = (DataSource) new InitialContext().lookup("java:/sqliteds");	//get connection through datasource
			Connection conn = ds.getConnection();
			
			Statement stmt=conn.createStatement();
			stmt.executeUpdate("DELETE FROM  tracks WHERE TrackId = "+trackid);	//execute delete query
			
		}catch (SQLException e) {//TODO redirect to error page
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		response.sendRedirect("/CrudOperations-0.0.1-SNAPSHOT/ServletSelect");	//redirect to servletselect to update session data
	}

}
