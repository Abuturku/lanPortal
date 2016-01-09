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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.KeyFactory;

import de.lanGymnasium.datenstruktur.Clazz;
import de.lanGymnasium.datenstruktur.ClazzUser;
import de.lanGymnasium.datenstruktur.School;
import de.lanGymnasium.datenstruktur.User;
import de.lanGymnasium.lan.EMF;

@Path("/clazz")
public class ClazzRestServlet {
	private static final Logger log = Logger.getLogger(UserRestServlet.class
			.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Clazz> getClazzes() {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery("SELECT c FROM Clazz c");

		@SuppressWarnings("unchecked")
		List<Clazz> list = (List<Clazz>) query.getResultList();
		em.close();

		return list;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Clazz newClazz(Clazz clazz) {
		System.out.print(clazz);
		EntityManager em = EMF.createEntityManager();
		em.persist(clazz);
		em.close();
		return clazz;
	}

	@DELETE
	@Path("/{id}")
	public void deleteClazz(@PathParam("id") Long id) {
		EntityManager em = EMF.createEntityManager();
		Clazz clazz = em.find(Clazz.class, KeyFactory.createKey("Clazz", id));
		System.out.println("remove " + clazz);
		em.remove(clazz);
		em.close();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Clazz getClazz(@PathParam("id") Long id) {
		EntityManager em = EMF.createEntityManager();
		Clazz clazz = em.find(Clazz.class, KeyFactory.createKey("Clazz", id));
		System.out.println("get " + clazz);
		em.close();

		return clazz;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/grades")
	public List<Integer> getGrades() {
		String queryString = "SELECT c FROM Clazz c";
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery(queryString);
		em.clear();

		@SuppressWarnings("unchecked")
		List<Clazz> clazzList = (List<Clazz>) query.getResultList();

		ArrayList<Integer> grades = new ArrayList<Integer>();
		for (Clazz clazz : clazzList) {
			boolean inList = false;
			for (Integer grade : grades) {
				if (clazz.getGrade() == grade.intValue()) {
					inList = true;
					break;
				}
			}
			if (!inList) {
				grades.add(clazz.getGrade());
			}
		}
		return grades;

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/letters")
	public List<String> getLetters() {
		String queryString = "SELECT c FROM Clazz c";
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery(queryString);
		em.clear();

		@SuppressWarnings("unchecked")
		List<Clazz> clazzList = (List<Clazz>) query.getResultList();

		ArrayList<String> letters = new ArrayList<String>();
		for (Clazz clazz : clazzList) {
			boolean inList = false;
			for (String letter : letters) {
				if (clazz.getLetter().equals(letter)) {
					inList = true;
					break;
				}
			}
			if (!inList) {
				letters.add(clazz.getLetter());
			}
		}
		return letters;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/schoolByClazz/{id}")
	public School getSchoolByClazzId(@PathParam("id") Long id) {
		EntityManager em = EMF.createEntityManager();
		Clazz clazz = em.find(Clazz.class, KeyFactory.createKey("Clazz", id));
		em.clear();
		School school = em.find(School.class,
				KeyFactory.createKey("School", clazz.getSchoolID()));
		em.close();
		return school;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/usersByClazz/{id}")
	public List<User> getUsersByClazz(@PathParam("id") Long id) {

		List<ClazzUser> clazzUsers = getClazzUsersByClazzId(id);
		List<User> users = new ArrayList<User>();

		EntityManager em = EMF.createEntityManager();
		for (ClazzUser clazzUser : clazzUsers) {
			User u = em.find(User.class,
					KeyFactory.createKey("User", clazzUser.getUserID()));
			users.add(u);
		}
		log.info("users länge: " + users.size());

		return users;
	}

	@SuppressWarnings("unchecked")
	public static List<Clazz> getClazzesBySchoolId(Long schoolID) {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery("SELECT c FROM Clazz c WHERE schoolID = "
				+ schoolID);
		List<Clazz> clazzes = (List<Clazz>) query.getResultList();
		em.close();
		return clazzes;
	}

	@SuppressWarnings("unchecked")
	public static List<ClazzUser> getClazzUsersByClazzId(Long clazzID) {
		EntityManager em = EMF.createEntityManager();
		Query query = em
				.createQuery("SELECT c FROM ClazzUser c WHERE clazzID = "
						+ clazzID);
		List<ClazzUser> clazzUsers = (List<ClazzUser>) query.getResultList();
		em.close();
		return clazzUsers;
	}

	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/clazzWithoutID")
	public Clazz getClazzWithoutID(Clazz clazz) {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery("SELECT c FROM Clazz c WHERE grade = "
				+ clazz.getGrade() + " AND letter = '" + clazz.getLetter()
				+ "' AND schoolID = " + clazz.getSchoolID());

		@SuppressWarnings("unchecked")
		List<Clazz> clazzs = query.getResultList();
		em.close();

		return clazzs.get(0);
	}
}
