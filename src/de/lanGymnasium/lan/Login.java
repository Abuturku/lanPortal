package de.lanGymnasium.lan;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.lanGymnasium.datenstruktur.School;
import de.lanGymnasium.datenstruktur.User;

@SuppressWarnings("serial")
public class Login extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.getRequestDispatcher("login.html").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String school = req.getParameter("school");
		String firstName = req.getParameter("firstName");
		String familyName = req.getParameter("familyName");
		String password = req.getParameter("password");
		boolean isTeacher = Boolean.parseBoolean(req.getParameter("isTeacher"));
		
		System.out.println(school);
		System.out.println(firstName);
		System.out.println(familyName);
		System.out.println(password);
		System.out.println(isTeacher);
		
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery("SELECT s FROM School s WHERE name='" + school + "'");
		
		@SuppressWarnings("unchecked")
		List<School> schoolList = (List<School>)query.getResultList();
		
		if (schoolList.size() == 0) {
			System.out.println("no");
//			resp.sendError(123, "Schule nicht vorhanden!");
		}
		else{
//			User newUser = new User(firstName, familyName, password, isTeacher, null, schoolList.get(0).getKey());
			
			String queryString = "SELECT u FROM User u WHERE firstName='" + firstName + "' AND familyName='" + familyName 
					+ "' AND password='" + password + "' AND isTeacher=" + isTeacher + " AND school='" + schoolList.get(0).getKey();
			
			System.out.println(queryString);
			
			query = em.createQuery(queryString);
			
			@SuppressWarnings("unchecked")
			List<User> userList = (List<User>)query.getResultList();
			System.out.println(userList.toString());
			if (userList.size() != 0) {
				System.out.println("sayonara");
			}
		}
		
	}
}
