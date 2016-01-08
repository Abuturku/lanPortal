package de.lanGymnasium.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.KeyFactory;

import de.lanGymnasium.datenstruktur.Clazz;
import de.lanGymnasium.datenstruktur.ClazzUser;
import de.lanGymnasium.datenstruktur.Filter;
import de.lanGymnasium.datenstruktur.User;
import de.lanGymnasium.lan.EMF;

@Path("/filter")
public class FilterRestServlet {
	private static final Logger log = Logger.getLogger(UserRestServlet.class
			.getName());

	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/students")
	public List<User> filterStudents(Filter filter) {

		EntityManager em = EMF.createEntityManager();
		log.info("Suche Schueler: " + filter.toString());

		Query query = em
				.createQuery("SELECT u FROM User u WHERE teacher = false");
		List<User> users = (List<User>) query.getResultList();
		em.close();
		if (filter.getSchoolID() != null
				&& !filter.getSchoolID().equals("null")) {
			log.info("Filtere nach Schule");
			users = filterSchool(filter.getSchoolID(), users);
		}
		if (filter.getGrade() != 0) {
			log.info("Filtere nach Stufe");
			users = filterGrade(filter.getGrade(), users);
		}
		if (filter.getLetter() != null && !filter.getLetter().equals("null")) {
			log.info("Filtere nach Buchstabe");
			users = filterLetter(filter.getLetter(), users);
		}
		if (filter.getTeacherID() != null
				&& !filter.getTeacherID().equals("null")) {
			log.info("Filtere nach Lehrer");
			users = filterTeacher(filter.getTeacherID(), users);
		}

		log.info("Gebe " + users.size() + " Schueler zurueck");
		return users;
	}

	@SuppressWarnings("unchecked")
	private List<User> filterTeacher(String teacherID, List<User> users) {

		ArrayList<User> returnUserList = new ArrayList<User>();
		EntityManager em = EMF.createEntityManager();
		log.info("Suche Lehrer: " + teacherID);
		Query query = em
				.createQuery("SELECT c FROM ClazzUser c WHERE userID = "
						+ teacherID);
		List<ClazzUser> clazzUsers = (List<ClazzUser>) query.getResultList();
		em.close();

		for (User user : users) {
			for (ClazzUser clazzUser : clazzUsers) {
				em = EMF.createEntityManager();
				query = em
						.createQuery("SELECT c FROM ClazzUser c WHERE userID = "
								+ user.getKey().getId()
								+ " AND clazzID = "
								+ clazzUser.getClazzID());

				if (query.getResultList().size() > 0) {
					returnUserList.add(user);
				}

				em.close();
			}
		}
		return returnUserList;
	}

	@SuppressWarnings("unchecked")
	private List<User> filterLetter(String letter, List<User> users) {
		ArrayList<User> returnUserList = new ArrayList<User>();
		EntityManager em = EMF.createEntityManager();
		log.info("Suche Buchstabe: " + letter);
		Query query = em.createQuery("SELECT c FROM Clazz c WHERE letter = '"
				+ letter + "'");
		List<Clazz> clazzs = (List<Clazz>) query.getResultList();
		em.close();

		for (Clazz clazz : clazzs) {
			em = EMF.createEntityManager();
			query = em.createQuery("SELECT c FROM ClazzUser c WHERE clazzID = "
					+ clazz.getKey().getId());
			List<ClazzUser> clazzUsers = (List<ClazzUser>) query
					.getResultList();
			em.close();
			for (User user : users) {
				for (ClazzUser clazzUser : clazzUsers) {
					if (user.getKey().getId() == clazzUser.getUserID()) {
						returnUserList.add(user);
					}
				}
			}
		}
		return returnUserList;
	}

	@SuppressWarnings("unchecked")
	private List<User> filterGrade(int grade, List<User> users) {
		ArrayList<User> returnUserList = new ArrayList<User>();
		EntityManager em = EMF.createEntityManager();
		log.info("Suche Stufe: " + grade);
		Query query = em.createQuery("SELECT c FROM Clazz c WHERE grade = "
				+ grade);
		List<Clazz> clazzs = (List<Clazz>) query.getResultList();
		em.close();

		for (Clazz clazz : clazzs) {
			em = EMF.createEntityManager();
			query = em.createQuery("SELECT c FROM ClazzUser c WHERE clazzID = "
					+ clazz.getKey().getId());
			List<ClazzUser> clazzUsers = (List<ClazzUser>) query
					.getResultList();
			em.close();
			for (User user : users) {
				for (ClazzUser clazzUser : clazzUsers) {
					if (user.getKey().getId() == clazzUser.getUserID()) {
						returnUserList.add(user);
					}
				}
			}
		}
		return returnUserList;
	}

	@SuppressWarnings("unchecked")
	private List<User> filterSchool(String schoolID, List<User> users) {
		ArrayList<User> returnUserList = new ArrayList<User>();
		EntityManager em = EMF.createEntityManager();
		log.info("Suche Schule: " + schoolID);
		Query query = em.createQuery("SELECT c FROM Clazz c WHERE schoolID = "
				+ schoolID);
		List<Clazz> clazzs = (List<Clazz>) query.getResultList();
		em.close();

		for (Clazz clazz : clazzs) {
			em = EMF.createEntityManager();
			query = em.createQuery("SELECT c FROM ClazzUser c WHERE clazzID = "
					+ clazz.getKey().getId());
			List<ClazzUser> clazzUsers = (List<ClazzUser>) query
					.getResultList();
			em.close();
			for (User user : users) {
				for (ClazzUser clazzUser : clazzUsers) {
					if (user.getKey().getId() == clazzUser.getUserID()) {
						returnUserList.add(user);
					}
				}
			}
		}
		return returnUserList;
	}

	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/teachers")
	public List<User> filterTeachers(Filter filter) {

		EntityManager em = EMF.createEntityManager();
		log.info("Suche Lehrer: " + filter.toString());

		Query query = em
				.createQuery("SELECT u FROM User u WHERE teacher = true");
		List<User> users = (List<User>) query.getResultList();
		em.close();
		if (filter.getSchoolID() != null
				&& !filter.getSchoolID().equals("null")) {
			log.info("Filtere nach Schule");
			users = filterSchool(filter.getSchoolID(), users);
		}
		if (filter.getGrade() != 0) {
			log.info("Filtere nach Stufe");
			users = filterGrade(filter.getGrade(), users);
		}
		if (filter.getLetter() != null && !filter.getLetter().equals("null")) {
			log.info("Filtere nach Buchstabe");
			users = filterLetter(filter.getLetter(), users);
		}
		if (filter.getStudentID() != null
				&& !filter.getStudentID().equals("null")) {
			log.info("Filtere nach Schueler");
			users = filterStudent(filter.getStudentID(), users);
		}

		log.info("Gebe " + users.size() + " Lehrer zurueck");
		return users;
	}

	@SuppressWarnings("unchecked")
	private List<User> filterStudent(String studentID, List<User> users) {
		ArrayList<User> returnUserList = new ArrayList<User>();
		EntityManager em = EMF.createEntityManager();
		log.info("Suche Schueler: " + studentID);
		Query query = em
				.createQuery("SELECT c FROM ClazzUser c WHERE userID = "
						+ studentID);
		List<ClazzUser> clazzUsers = (List<ClazzUser>) query.getResultList();
		em.close();

		for (User user : users) {
			for (ClazzUser clazzUser : clazzUsers) {
				em = EMF.createEntityManager();
				query = em
						.createQuery("SELECT c FROM ClazzUser c WHERE userID = "
								+ user.getKey().getId()
								+ " AND clazzID = "
								+ clazzUser.getClazzID());

				if (query.getResultList().size() > 0) {
					returnUserList.add(user);
				}

				em.close();
			}
		}
		return returnUserList;
	}

	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/clazzes")
	public List<Clazz> filterClazzes(Filter filter) {
		// Hier MUSS Schule gesetzt sein, damit �berhaupt gefiltert wird. Eine
		// Filterung ohne Ber�cksichtigung der Schule w�rde das ganze
		// nur un�bersichtlich machen

		log.info("Suche Lehrer: " + filter.toString());
		List<Clazz> clazzs = null;

		if (filter.getSchoolID() != null
				&& !filter.getSchoolID().equals("null")) {
			log.info("Filtere nach Schule");

			if (filter.getGrade() != 0) {
				log.info("Filtere nach Stufe");
				EntityManager em = EMF.createEntityManager();
				Query query = em
						.createQuery("SELECT c FROM Clazz c WHERE grade = "
								+ filter.getGrade() + " AND schoolID = "
								+ filter.getSchoolID());
				clazzs = (List<Clazz>) query.getResultList();
				em.close();
			} else {
				EntityManager em = EMF.createEntityManager();
				Query query = em
						.createQuery("SELECT c FROM Clazz c WHERE schoolID = "
								+ filter.getSchoolID());
				clazzs = (List<Clazz>) query.getResultList();
				em.close();
			}
			if (filter.getStudentID() != null
					&& !filter.getStudentID().equals("null")) {
				log.info("Filtere nach Buchstabe");
				clazzs = filterClazzUser(filter.getStudentID(), clazzs);
			}
			if (filter.getTeacherID() != null
					&& !filter.getTeacherID().equals("null")) {
				log.info("Filtere nach Schueler");
				clazzs = filterClazzUser(filter.getTeacherID(), clazzs);
			}
		} else {
			clazzs = new ArrayList<Clazz>();
		}

		log.info("Gebe " + clazzs.size() + " Klassen zurueck");
		return clazzs;
	}

	// @SuppressWarnings("unchecked")
	// @POST
	// @Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Path("/clazzesTeacher")
	// public List<Clazz> filterClazzesFree(Filter filter) {
	// // Hier MUSS Schule gesetzt sein, damit �berhaupt gefiltert wird. Eine
	// // Filterung ohne Ber�cksichtigung der Schule w�rde das ganze
	// // nur un�bersichtlich machen
	//
	// log.info("Suche Lehrer: " + filter.toString());
	// ArrayList<Clazz> clazzs = new ArrayList<Clazz>();
	// EntityManager em = EMF.createEntityManager();
	// Query query = em
	// .createQuery("SELECT c FROM ClazzUser c WHERE userID = "
	// + filter.getTeacherID());
	// List<ClazzUser> clazzUsers = (List<ClazzUser>) query.getResultList();
	// em.close();
	// em = EMF.createEntityManager();
	// for (ClazzUser clazzUser : clazzUsers) {
	// Clazz clazz = em.find(Clazz.class,
	// KeyFactory.createKey("Clazz", clazzUser.getClazzID()));
	// clazzs.add(clazz);
	// }
	//
	// log.info("Gebe " + clazzs.size() + " Klassen zurueck");
	// return clazzs;
	// }

	@SuppressWarnings("unchecked")
	private List<Clazz> filterClazzUser(String user, List<Clazz> clazzs) {
		ArrayList<Clazz> returnClazzList = new ArrayList<Clazz>();
		EntityManager em = EMF.createEntityManager();
		log.info("Suche User: " + user);
		Query query = em
				.createQuery("SELECT c FROM ClazzUser c WHERE userID = " + user);
		List<ClazzUser> clazzUsers = (List<ClazzUser>) query.getResultList();
		em.close();

		for (ClazzUser clazzUser : clazzUsers) {
			for (Clazz clazz : clazzs) {
				if (clazz.getKey().getId() == clazzUser.getClazzID()) {
					returnClazzList.add(clazz);
				}
			}
		}

		return returnClazzList;
	}

}
