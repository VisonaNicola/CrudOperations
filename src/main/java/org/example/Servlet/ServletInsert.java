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
        // TODO Auto-generated constructor stub
    }

	/**
	 * Get the list of albums and genres from db to create the form in inserttrack.jsp properly
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET REQUEST ON /ServletInsert");

		try {
			DataSource ds = (DataSource) new InitialContext().lookup("java:/sqliteds");//get connection through datasource
			Connection conn = ds.getConnection();
			
			//get all albums
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT AlbumId, Title FROM albums");
			
			Albums albums = new Albums();	//create album bean
			
			//add albums to bean
			while(rs.next()){  
				albums.addAlbum(new Album(rs.getInt(1),rs.getString(2)));
			}
			
			//get all genres
			rs = stmt.executeQuery("SELECT GenreId, Name FROM genres");
			
			Genres genres = new Genres();	//create genres bean
			
			//add genres to bean
			while(rs.next()){  
				genres.addGenre(new Genre(rs.getInt(1),rs.getString(2)));
			}
			
			//add beans to session
			HttpSession session = request.getSession();
			session.setAttribute("albums", albums);
			session.setAttribute("genres", genres);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("inserttrack.jsp").forward(request, response);
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
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/CrudOperations-0.0.1-SNAPSHOT/ServletSelect");
	}

}
