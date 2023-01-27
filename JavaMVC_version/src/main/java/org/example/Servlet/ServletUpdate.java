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

		int choice = Integer.parseInt(request.getParameter("choice"));
		String value = request.getParameter("value");

		
		try {
			TracksDao tdao = new TracksDao();
			tdao.update(trackid, choice, value);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/CrudOperations-0.0.1-SNAPSHOT/ServletSelect");
	}

}
