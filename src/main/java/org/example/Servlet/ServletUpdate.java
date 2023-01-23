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
        // TODO Auto-generated constructor stub
    }

	/**
	 * Get tracks info from db so that they can be visualized correctly into updatetrack.jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET REQUEST ON /ServletUpdate");
		try {
			Tracks tracks = new Tracks();	//create tracks bean
			DataSource ds = (DataSource) new InitialContext().lookup("java:/sqliteds");//get connection through datasource
			Connection conn = ds.getConnection();
			
			//get tracks from db
			Statement stmt=conn.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT tracks.TrackId, tracks.Name, albums.Title, genres.Name as genre, tracks.Composer FROM tracks, genres, albums WHERE tracks.AlbumId = albums.AlbumId AND genres.GenreId = tracks.GenreId ORDER BY tracks.TrackId");
			
			//add tracks to bean
			while(rs.next()){  
				//System.out.println(rs.getInt(1)+"; "+rs.getString(2)+"; "+rs.getString(3)+"; "+rs.getString(4)+"; "+rs.getString(5));
				tracks.addTrack(new Track(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			
			//add bean to session
			HttpSession session = request.getSession();
			session.setAttribute("tracks", tracks);
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("updatetrack.jsp").forward(request, response);
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
		}catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/CrudOperations-0.0.1-SNAPSHOT/ServletSelect");
	}

}
