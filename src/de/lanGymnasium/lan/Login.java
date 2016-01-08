package de.lanGymnasium.lan;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.lanGymnasium.datenstruktur.User;

@SuppressWarnings("serial")
public class Login extends HttpServlet {
	private static final Logger log = Logger.getLogger(Login.class.getName());
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		User user = LoginChecker.checkLogin(req, resp);
		if (user != null) {
			log.info("User logged in with ID " + LoginChecker.getCurrentUserID());
			resp.sendRedirect("mylan");
		}else{
			resp.getWriter().write("<p>Sie sind nicht im System registriert! Bitte wenden Sie sich an einen Administrator!</p><br> " 
					+ "<p><a href=\"login\">Zurück</p>");
			//Hier kommt noch der Redirect zu Lindas Login Denied Seite.
		}
		//req.getRequestDispatcher("login.html").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
