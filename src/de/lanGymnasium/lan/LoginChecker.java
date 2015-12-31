package de.lanGymnasium.lan;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import de.lanGymnasium.datenstruktur.Clazz;
import de.lanGymnasium.datenstruktur.ClazzUser;
import de.lanGymnasium.datenstruktur.IClazz;
import de.lanGymnasium.datenstruktur.ISchool;
import de.lanGymnasium.datenstruktur.IUser;
import de.lanGymnasium.datenstruktur.School;
import de.lanGymnasium.datenstruktur.User;

public class LoginChecker {
	public static void iniData() {
		// IClazz neunA = new Clazz(9, 'a', 6614661952700416L, null);
		// IClazz vierC = new Clazz(4, 'c', 1234, null);
		// EntityManager em = EMF.createEntityManager();
		// em.persist(neunA);
		// em.close();
		// em = EMF.createEntityManager();
		// em.persist(vierC);
		// em.close();
		
		// IUser niklas = new User("Niklas", "Nikisch","185804764220139124118",
		// false, null);
		// em = EMF.createEntityManager();
		// em.persist(niklas);
		// em.close();
		
		// ISchool school = new School("LAN-Gymnasium");
		// em = EMF.createEntityManager();
		// em.persist(school);
		// em.close();

		// ClazzUser clazzUser = new ClazzUser(4573968371548160L,
		// 5488762045857792L);
		// em = EMF.createEntityManager();
		// em.persist(clazzUser);
		// em.close();

	}

	public static User checkLogin(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		iniData();
		String thisURL = req.getRequestURI();

		if (userService.isUserLoggedIn()) {

			EntityManager em = EMF.createEntityManager();
			String queryString = "SELECT u FROM User u  WHERE googleID = '"
					+ userService.getCurrentUser().getUserId() + "'";

			System.out.println("Suche user: " + queryString);
			Query query = em.createQuery(queryString);

			@SuppressWarnings("unchecked")
			List<User> list = (List<User>) query.getResultList();
			em.close();
			System.out.println("Listengroesse: " + list.size());

			if (list.size() == 0) {
				System.out.println("User tried to log in with ID "
						+ getCurrentUserID());
				return null;
			} else {
				return list.get(0);
			}
		} else {
			resp.sendRedirect(userService.createLoginURL(thisURL));
			return null;
		}
	}

	public static void logout(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		System.out.println("User logged out with ID " + getCurrentUserID());
		resp.sendRedirect(userService.createLogoutURL("login"));
	}

	public static String getCurrentUserID() {
		UserService userService = UserServiceFactory.getUserService();
		return userService.getCurrentUser().getUserId();
	}
}
