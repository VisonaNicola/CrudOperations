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
        // TODO Auto-generated constructor stub
    }

	/**
	 * Get tracks info from db
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET REQUEST ON /ServletSelect");
		try {
			DataSource ds = (DataSource) new InitialContext().lookup("java:/sqliteds");//get connection through datasource
			Connection conn = ds.getConnection();
			
			Tracks tracks = new Tracks();//create tracks bean
			
			//get all tracks from db
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
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("gettracks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST REQUEST ON /ServletSelect");	
	}

}
