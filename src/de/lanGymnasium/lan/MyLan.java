package de.lanGymnasium.lan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.lanGymnasium.datenstruktur.User;

@SuppressWarnings("serial")
public class MyLan extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = LoginChecker.checkLogin(req, resp);
		if (user == null) {
			resp.sendRedirect("loginFailed.html");
		}
		else{
			req.getRequestDispatcher("mylan.html").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
