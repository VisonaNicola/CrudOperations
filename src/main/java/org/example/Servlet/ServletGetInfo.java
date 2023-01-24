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
import org.example.Bean.Track;
import org.example.Bean.Tracks;

/**
 * This servlet handles all the get requests of the other servlet, to avoid duplicate code
 */
@WebServlet("/ServletGetInfo")
public class ServletGetInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGetInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int choice = (Integer)request.getAttribute("choice");	//get which kind of data the servlet needs from db (1=tracks, 2= albums + genres)
		String destination = (String)request.getAttribute("destination");	//get the original destination
		//System.out.println(choice + " " + destination);
		if(choice == 1) {	//tracks
			try {	//get all tracks from db
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
				
			} catch (SQLException e) {	//TODO redirect to error page
				System.err.println(e.getMessage());
				e.printStackTrace();
			} catch (NamingException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}else if(choice == 2) {	//albums + genres
			try {	//get all albums and genres from db
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
			} catch (SQLException e) {//TODO redirect to error page
				System.err.println(e.getMessage());
				e.printStackTrace();
			} catch (NamingException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		//forward to original destination
		request.getRequestDispatcher(destination).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
