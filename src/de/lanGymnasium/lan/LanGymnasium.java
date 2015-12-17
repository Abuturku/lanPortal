package de.lanGymnasium.lan;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LanGymnasium extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//resp.sendRedirect("index.html");
		resp.getWriter().write("Moin");
	}
}