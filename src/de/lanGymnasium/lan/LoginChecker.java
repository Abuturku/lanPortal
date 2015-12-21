package de.lanGymnasium.lan;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import de.lanGymnasium.datenstruktur.User;

public class LoginChecker {
	public static User checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		UserService userService = UserServiceFactory.getUserService();
		
		String thisURL = req.getRequestURI();
		
		if (userService.isUserLoggedIn()) {
			EntityManager em = EMF.createEntityManager();
			User user = em.find(User.class, KeyFactory.createKey("User", userService.getCurrentUser().getUserId()));
			em.close();
			return user;
	    } else {
			resp.sendRedirect(userService.createLoginURL(thisURL));
			return null;
	    }
	}
	
	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		UserService userService = UserServiceFactory.getUserService();
		resp.sendRedirect(userService.createLogoutURL("login"));
	}
}
