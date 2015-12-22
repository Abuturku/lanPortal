package de.lanGymnasium.lan;

import java.io.IOException;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import de.lanGymnasium.datenstruktur.User;

public class LoginChecker {
	private static final Logger log = Logger.getLogger(LoginChecker.class.getName());
	
	public static User checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		UserService userService = UserServiceFactory.getUserService();
		
		String thisURL = req.getRequestURI();
		
		if (userService.isUserLoggedIn()) {
			EntityManager em = EMF.createEntityManager();
			User user = em.find(User.class, KeyFactory.createKey("User", userService.getCurrentUser().getUserId()));
			if (user == null) {
				log.warning("User tried to log in with ID " + getCurrentUserID());
			}else{
				
			}
			em.close();
			return user;
	    } else {
			resp.sendRedirect(userService.createLoginURL(thisURL));
			return null;
	    }
	}
	
	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		UserService userService = UserServiceFactory.getUserService();
		log.info("User logged out with ID " + getCurrentUserID());
		resp.sendRedirect(userService.createLogoutURL("login"));
	}
	
	public static String getCurrentUserID(){
		UserService userService = UserServiceFactory.getUserService();
		return userService.getCurrentUser().getUserId();
	}
}
