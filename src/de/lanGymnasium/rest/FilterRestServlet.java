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
import de.lanGymnasium.rest.ClazzRestServlet;

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
<<<<<<< Updated upstream
		// Falls kein Feld gesetzt ist
		if (filter.getSchoolID().equals("null") 
				&& filter.getGrade() == 0
				&& filter.getLetter().equals("null")
				&& filter.getTeacherID().equals("null")) {
			Query query = em.createQuery("SELECT u FROM User u WHERE teacher = false");
			em.clear();
			return (List<User>) query.getResultList();
=======
		Query query = em
				.createQuery("SELECT u FROM User u WHERE teacher = false");
		List<User> users = (List<User>) query.getResultList();
		em.close();
		System.out.println(users.size());
		if (!filter.getSchoolID().equals("null")) {
			log.info("Filtere nach Schule");
			users = filterSchool(filter.getSchoolID(), users);
		}
		if (filter.getGrade() != 0) {
			log.info("Filtere nach Stufe");
			users = filterGrade(filter.getGrade(), users);
		}
		if (!filter.getLetter().equals("null")) {
			log.info("Filtere nach Buchstabe");
			users = filterLetter(filter.getLetter(), users);
		}
		if (!filter.getTeacherID().equals("null")) {
			log.info("Filtere nach Lehrer");
			users = filterTeacher(filter.getTeacherID(), users);
>>>>>>> Stashed changes
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
								+ clazzUser.getUserID());
				em.close();
				if (query.getResultList().size() > 0) {
					returnUserList.add(user);
				}
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
		// Falls kein Feld gesetzt ist
		if (filter.getSchoolID().equals("null") && filter.getGrade() == 0
				&& filter.getLetter().equals("null")
				&& filter.getStudentID().equals("null")) {
<<<<<<< Updated upstream
			Query query = em.createQuery("SELECT u FROM User u WHERE teacher = true");
=======
			Query query = em
					.createQuery("SELECT u FROM User u WHERE isTeacher = true");
>>>>>>> Stashed changes
			em.clear();
			return (List<User>) query.getResultList();
		}
		// Falls Schule gesetzt ist
		else if (!filter.getSchoolID().equals("null")) {
			// Falls Stufe gesetzt ist
			if (filter.getGrade() != 0) {
				// Falls Klasse gesetzt ist
				if (!filter.getLetter().equals("null")) {
					// Falls Lehrer gesetzt ist
					if (!filter.getStudentID().equals("null")) {

					}
					// Falls Lehrer nicht gesetzt ist
					else {

					}
					// Falls Klasse nicht gesetzt ist, aber...
				} else {
					// Falls Lehrer gesetzt ist
					if (!filter.getStudentID().equals("null")) {

					}
					// Falls Lehrer nicht gesetzt ist
					else {

					}
				}
				// Falls Stufe nicht gesetzt ist, aber...
			} else {
				// ...falls Klasse gesetzt ist
				if (!filter.getLetter().equals("null")) {
					// Falls Lehrer gesetzt ist
					if (!filter.getStudentID().equals("null")) {

					}
					// Falls Lehrer nicht gesetzt ist
					else {

					}
					// Falls Klasse nicht gesetzt ist, aber...
				} else {
					// ...falls Lehrer gesetzt ist
					if (!filter.getStudentID().equals("null")) {

					}
					// ...falls Lehrer nicht gesetzt ist
					else {
						List<User> users = filterUsersBySchool(filter);
						User.removeTeachers(users);
						return users;
					}
				}
			}
		}
		return null;
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
		EntityManager em = EMF.createEntityManager();
		log.info("Suche Klassen: " + filter.toString());
		// Falls Stufe gesetzt
		if (!filter.getSchoolID().equals("null") && filter.getGrade() != 0
				&& filter.getStudentID().equals("null")
				&& filter.getTeacherID().equals("null")) {

		}
		// Falls Schueler gesetzt
		else if (!filter.getSchoolID().equals("null") && filter.getGrade() == 0
				&& !filter.getStudentID().equals("null")
				&& filter.getTeacherID().equals("null")) {

		}
		// Falls Lehrer gesetzt
		else if (!filter.getSchoolID().equals("null") && filter.getGrade() == 0
				&& filter.getStudentID().equals("null")
				&& !filter.getTeacherID().equals("null")) {

		}
		// Falls Stufe und Schueler gesetzt sind
		else if (!filter.getSchoolID().equals("null") && filter.getGrade() != 0
				&& !filter.getStudentID().equals("null")
				&& filter.getTeacherID().equals("null")) {

		}
		// Falls Stufe und Lehrer gesetzt
		else if (!filter.getSchoolID().equals("null") && filter.getGrade() != 0
				&& filter.getStudentID().equals("null")
				&& !filter.getTeacherID().equals("null")) {

		}
		// Falls Schueler und Lehrer gesetzt
		else if (!filter.getSchoolID().equals("null") && filter.getGrade() == 0
				&& !filter.getStudentID().equals("null")
				&& !filter.getTeacherID().equals("null")) {

		}
		// Falls alle gesetzt sind
		else if (!filter.getSchoolID().equals("null") && filter.getGrade() != 0
				&& !filter.getStudentID().equals("null")
				&& !filter.getTeacherID().equals("null")) {

		}
		// Falls Stufe, Lehrer und Schueler nicht gesetzt sind
		else if (!filter.getSchoolID().equals("null") && filter.getGrade() == 0
				&& filter.getStudentID().equals("null")
				&& filter.getTeacherID().equals("null")) {

			Query query = em
					.createQuery("SELECT c FROM Clazz c WHERE schoolID = "
							+ filter.getSchoolID());
			em.clear();
			return (List<Clazz>) query.getResultList();
		} else {
			return new ArrayList<Clazz>();
		}

		return null;
	}

	public List<User> filterUsersBySchool(Filter filter) {
		Long schoolID = Long.valueOf(filter.getSchoolID());

		List<Clazz> clazzes = ClazzRestServlet.getClazzesBySchoolId(schoolID);

		List<Long> userIDs = new ArrayList<Long>();
		for (Clazz clazz : clazzes) {
			List<ClazzUser> clazzUsers = ClazzRestServlet
					.getClazzUsersByClazzId(clazz.getKey().getId());

			for (ClazzUser clazzUser : clazzUsers) {
				userIDs.add(clazzUser.getUserID());
			}
		}
		List<User> users = new ArrayList<User>();

		EntityManager em = EMF.createEntityManager();
		for (Long userID : userIDs) {
			User user = em.find(User.class,
					KeyFactory.createKey("User", userID));
			users.add(user);
		}

		return users;
	}
}
