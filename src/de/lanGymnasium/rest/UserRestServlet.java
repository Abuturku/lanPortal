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
	private static final Logger log = Logger.getLogger(UserRestServlet.class
			.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery("SELECT u FROM User u");

		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.getResultList();
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
		System.out.println("get " + user);
		em.close();

		return user;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/loggedInUser")
	public User getLoggedInUser() {
		UserService userService = UserServiceFactory.getUserService();
		EntityManager em = EMF.createEntityManager();
		String queryString = "SELECT u FROM User u  WHERE googleID = '"
				+ userService.getCurrentUser().getUserId() + "'";

		System.out.println("Suche user: " + queryString);
		Query query = em.createQuery(queryString);

		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.getResultList();
		em.close();

		System.out.println("User gefunden: " + list.get(0).getKey());

		return list.get(0);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userSchool")
	public School getUserSchool() {

		User user = getLoggedInUser();
		EntityManager em = EMF.createEntityManager();
		String queryString = "SELECT c FROM ClazzUser c  WHERE userID = "
				+ user.getKey().getId();
		System.out.println("Query: " + queryString);
		Query query = em.createQuery(queryString);
		em.clear();
		@SuppressWarnings("unchecked")
		List<ClazzUser> clazzUserList = (List<ClazzUser>) query.getResultList();
		log.info("Laenge: " + clazzUserList.size());

		queryString = "SELECT c FROM Clazz c";
		System.out.println("Query: " + queryString);

		query = em.createQuery(queryString);
		em.clear();
		@SuppressWarnings("unchecked")
		List<Clazz> clazzList = (List<Clazz>) query.getResultList();
		log.info("Laenge: " + clazzList.size());

		for (Clazz clazz : clazzList) {
			log.info("Pruefe: " + clazz.getKey().getId() + " == "
					+ clazzUserList.get(0).getClazzID());
			if (clazz.getKey().getId() == clazzUserList.get(0).getClazzID()) {

				queryString = "SELECT s FROM School s";
				System.out.println("Query: " + queryString);

				query = em.createQuery(queryString);
				em.clear();
				@SuppressWarnings("unchecked")
				List<School> schoolList = (List<School>) query.getResultList();
				log.info("Laenge: " + schoolList.size());

				for (School school : schoolList) {
					log.info("Pruefe: " + school.getKey().getId() + " == "
							+ clazz.getSchool());
					if (school.getKey().getId() == clazz.getSchool()) {
						return school;
					}
				}
			}
		}
		return null;

	}
}
