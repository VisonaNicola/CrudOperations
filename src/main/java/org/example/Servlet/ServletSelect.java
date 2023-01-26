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
    }

	/**
	 * Get tracks info from db
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET REQUEST ON /ServletSelect");

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
		request.getRequestDispatcher("gettracks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST REQUEST ON /ServletSelect");	
	}

}
