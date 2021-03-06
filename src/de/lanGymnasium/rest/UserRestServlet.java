package de.lanGymnasium.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	public User getUser(@PathParam("id") Long id) {
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

		// System.out.println("Suche user: " + queryString);
		Query query = em.createQuery(queryString);

		User user = (User) query.getSingleResult();
		em.close();

		// System.out.println("User gefunden: " + list.get(0).getKey());

		return user;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userSchool")
	public School getLoggedInUserSchool() {
		User user = getLoggedInUser();

		String queryString = "SELECT c FROM ClazzUser c  WHERE userID = "
				+ user.getKey().getId();
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery(queryString);
		em.clear();

		@SuppressWarnings("unchecked")
		List<ClazzUser> clazzUsers = (List<ClazzUser>) query.getResultList();

		Clazz clazz = em.find(Clazz.class,
				KeyFactory.createKey("Clazz", clazzUsers.get(0).getClazzID()));
		em.clear();

		School school = em.find(School.class,
				KeyFactory.createKey("School", clazz.getSchoolID()));

		return school;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userSchool/{id}")
	public School getUserSchool(@PathParam("id") Long id) {

		String queryString = "SELECT c FROM ClazzUser c  WHERE userID = " + id;
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery(queryString);
		em.clear();

		@SuppressWarnings("unchecked")
		List<ClazzUser> clazzUsers = (List<ClazzUser>) query.getResultList();
		log.info("clazzUser: " + clazzUsers.get(0).getKey());

		Clazz clazz = em.find(Clazz.class,
				KeyFactory.createKey("Clazz", clazzUsers.get(0).getClazzID()));
		em.clear();
		log.info("clazz: " + clazz.getKey());
		School school = em.find(School.class,
				KeyFactory.createKey("School", clazz.getSchoolID()));

		return school;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userClasses/{id}")
	public List<Clazz> getUserClazzes(@PathParam("id") Long id) {
		String queryString = "SELECT c FROM ClazzUser c  WHERE userID = " + id;
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery(queryString);
		em.clear();

		@SuppressWarnings("unchecked")
		List<ClazzUser> clazzUsers = (List<ClazzUser>) query.getResultList();

		List<Clazz> clazzes = new ArrayList<Clazz>();

		for (ClazzUser clazzUser : clazzUsers) {
			Clazz c = em.find(Clazz.class,
					KeyFactory.createKey("Clazz", clazzUser.getClazzID()));
			em.clear();
			clazzes.add(c);
		}

		return clazzes;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/teachers")
	public List<User> getTeachers() {
		String queryString = "SELECT u FROM User u";
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery(queryString);
		em.clear();

		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) query.getResultList();

		ArrayList<User> teachers = new ArrayList<User>();
		for (User user : userList) {
			if (user.isTeacher()) {
				teachers.add(user);
			}
		}
		return teachers;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/pupils")
	public List<User> getPupils() {
		String queryString = "SELECT u FROM User u";
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery(queryString);
		em.clear();

		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) query.getResultList();

		ArrayList<User> pupil = new ArrayList<User>();
		for (User user : userList) {
			if (!user.isTeacher()) {
				pupil.add(user);
			}
		}
		return pupil;
	}

	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/userWithoutID")
	public User getUserWithoutID(User user) {
		EntityManager em = EMF.createEntityManager();
		Query query = em
				.createQuery("SELECT u FROM User u WHERE familyName = '"
						+ user.getFamilyName() + "' AND firstName = '"
						+ user.getFirstName() + "' AND teacher = "
						+ user.isTeacher());

		List<User> users = query.getResultList();
		em.close();

		return users.get(0);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/edit/{id}")
	public void editUser(@PathParam("id") long id, User user) {
		log.info("Edit Clazz: " + id);
		EntityManager em = EMF.createEntityManager();
		User foundUser = em.find(User.class, KeyFactory.createKey("User", id));
		if (foundUser != null) {
			foundUser.setFamilyName(user.getFamilyName());
			foundUser.setFirstName(user.getFirstName());
			foundUser.setTeacher(user.isTeacher());
			em.merge(foundUser);
		} else {
			em.persist(user);
		}

		em.close();
	}
	
	 
}
