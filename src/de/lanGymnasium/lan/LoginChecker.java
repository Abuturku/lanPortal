package de.lanGymnasium.lan;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import de.lanGymnasium.datenstruktur.User;

public class LoginChecker {
	// private static final Logger log = Logger.getLogger(LoginChecker.class
	// .getName());

	public static User checkLogin(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		UserService userService = UserServiceFactory.getUserService();

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
