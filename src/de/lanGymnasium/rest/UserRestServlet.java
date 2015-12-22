package de.lanGymnasium.rest;

import java.util.List;

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

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import de.lanGymnasium.datenstruktur.School;
import de.lanGymnasium.datenstruktur.User;
import de.lanGymnasium.lan.EMF;

@Path("/user")
public class UserRestServlet {	
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
		em.close();
		return user;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userSchool")
	public School getUserSchool(){
		EntityManager em = EMF.createEntityManager();
		User user = getLoggedInUser();
		School school = em.find(School.class, user.getSchool());
		return school;
	}
}
