package de.lanGymnasium.lan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.lanGymnasium.datenstruktur.School;


@SuppressWarnings("serial")
public class Login extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect("webapp/index.html");
		
		PrintWriter writer = resp.getWriter();
		
		EntityManager em = EMF.createEntityManager();
		String s = "SELECT s FROM School s";
		//writer.write("query "+s);
		Query query = em.createQuery(s);

		@SuppressWarnings("unchecked")
		List<School> list = (List<School>)query.getResultList();
		
//		writer.write("\nfound "+list.size()+" schools");
//		System.out.println("\nfound "+list.size()+" schools");
		
		for (School school: list) {
			writer.write("\n"+school.getName());
			System.out.println("\nfound school " + school.getName());
		}
		
		writer.flush();
		writer.close();
		em.close();
	}
}
