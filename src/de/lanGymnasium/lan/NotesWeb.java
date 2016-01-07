package de.lanGymnasium.lan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.lanGymnasium.datenstruktur.User;

@SuppressWarnings("serial")
public class NotesWeb extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = LoginChecker.checkLogin(req, resp);
		if (user == null) {
			resp.getWriter()
					.write("<p>Sie sind nicht im System registriert! Bitte wenden Sie sich an einen Administrator!</p><br> "
							+ "<p><a href=\"login\">Zur�ck</p>");
			// Hier kommt noch der Redirect zu Lindas Login Denied Seite.
		}
		System.out.println(req.getParameter("noteID"));
		resp.getWriter().write(req.getParameter("noteID"));
		req.getRequestDispatcher("note.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("MUH:" + req + "BUH" + req.getParameter("noteID"));
		// req.getRequestDispatcher("note.html#" + req.getParameter("noteID"))
		// .forward(req, resp);
	}
}
