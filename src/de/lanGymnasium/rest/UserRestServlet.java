package de.lanGymnasium.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import de.lanGymnasium.datenstruktur.Clazz;
import de.lanGymnasium.datenstruktur.ClazzUser;
import de.lanGymnasium.datenstruktur.School;
import de.lanGymnasium.datenstruktur.User;
import de.lanGymnasium.lan.EMF;

@Path("/user")
public class UserRestServlet {	
	private static final Logger log = Logger.getLogger(UserRestServlet.class.getName());
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery("SELECT u FROM User u");

		@SuppressWarnings("unchecked")
		List<User> list = (List<User>)query.getResultList();
		em.close();
		
		return list;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User newUser(User user) {
		System.out.print(user);
		EntityManager em = EMF.createEntityManager();
		em.persist(user);
		em.close();
		return user;
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteUser(@PathParam("id") String id) {
		EntityManager em = EMF.createEntityManager();
		User user = em.find(User.class, KeyFactory.createKey("User", id));
		System.out.println("remove " + user);
		em.remove(user);
		em.close();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public User getUser(@PathParam("id") String id) {
		EntityManager em = EMF.createEntityManager();
		User user = em.find(User.class, KeyFactory.createKey("User", id));
		System.out.println("get "+user);
		em.close();
		
		return user;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/loggedInUser")
	public User getLoggedInUser(){
		UserService userService = UserServiceFactory.getUserService();
		EntityManager em = EMF.createEntityManager();
		User user = em.find(User.class, KeyFactory.createKey("User", userService.getCurrentUser().getUserId()));
		log.info("User gefunden: " + user.getKey());
		em.close();
		return user;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userSchool")
	public School getUserSchool(){
		EntityManager em = EMF.createEntityManager();
		User user = getLoggedInUser();
		Clazz clazz = null;
		
		Query query = em.createQuery("SELECT c FROM ClazzUser c");
		log.info("Query ausgeführt: " + query.toString());

		@SuppressWarnings("unchecked")
		List<ClazzUser> clazzUserList = (List<ClazzUser>)query.getResultList();
		log.info("Länge: " + clazzUserList.size());
		
		
		for (ClazzUser clazzUser : clazzUserList) {	
			log.info("clazzUser: " + clazzUser);
			Key user2 = clazzUser.getUser();
			Key user3 = user.getKey();
			log.info("user2: " + user2);
			log.info("user3: " + user3);
			if (clazzUser.getUser().compareTo(user.getKey())==0) {

				log.info("getClazz: " + clazzUser.getClazz());
				log.info("getClazz: " + clazzUser.getClazz().getId());
				clazz = em.find(Clazz.class, clazzUser.getClazz());
				em.close();
				break;
			}
		}
		
		if (clazz != null) {
			em = EMF.createEntityManager();
			School school = em.find(School.class, clazz.getSchool());
			em.close();
			return school;
		}else{
			return null;
		}
	}
}
