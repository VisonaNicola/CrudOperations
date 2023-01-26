package org.example.Servlet;

import java.io.IOException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.Bean.Albums;
import org.example.Bean.Genres;
import org.example.Dao.AlbumsDao;
import org.example.Dao.GenresDao;
import org.example.Dao.TracksDao;


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
		try {
			AlbumsDao adao = new AlbumsDao();
			GenresDao gdao = new GenresDao();
			
			Albums albums = new Albums();
			albums.addAlbums(adao.getAll());
			
			Genres genres = new Genres();
			genres.addGenres(gdao.getAll());
			
			HttpSession session = request.getSession();
			session.setAttribute("albums", albums);
			session.setAttribute("genres", genres);
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
		int genreid = Integer.parseInt(request.getParameter("genreid"));
		String composer = request.getParameter("composer");

		
		try {
			TracksDao tdao = new TracksDao();
			tdao.add(name, albumid, genreid, composer);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/CrudOperations-0.0.1-SNAPSHOT/ServletSelect");
	}

}
