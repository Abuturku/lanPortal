package de.lanGymnasium.lan;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Login extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect("webapp/index.html");
		
		PrintWriter writer = resp.getWriter();
		
		
		writer.flush();
		writer.close();
	}
}
