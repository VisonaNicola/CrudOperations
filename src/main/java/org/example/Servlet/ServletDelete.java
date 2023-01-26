package org.example.Servlet;

import java.io.IOException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.Bean.Tracks;
import org.example.Dao.TracksDao;

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
		try {
			TracksDao tdao = new TracksDao();
			HttpSession session = request.getSession();
			Tracks tracks = new Tracks();
			tracks.addTracks(tdao.getAll());
			session.setAttribute("tracks", tracks);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("deletetrack.jsp").forward(request, response);

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
		int trackid =Integer.parseInt( request.getParameter("trackid"));	//get id of the track from form
		
		try {
			TracksDao tdao = new TracksDao();
			tdao.delete(trackid);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/CrudOperations-0.0.1-SNAPSHOT/ServletSelect");	//redirect to servletselect to update session data
	}

}
