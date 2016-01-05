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
		// Falls kein Feld gesetzt ist
		if (filter.getSchoolID().equals("null") 
				&& filter.getGrade() == 0
				&& filter.getLetter().equals("null")
				&& filter.getTeacherID().equals("null")) {
			Query query = em.createQuery("SELECT u FROM User u WHERE isTeacher = false");
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
					if (!filter.getTeacherID().equals("null")) {

					}
					// Falls Lehrer nicht gesetzt ist
					else {

					}
					// Falls Klasse nicht gesetzt ist, aber...
				} else {
					// Falls Lehrer gesetzt ist
					if (!filter.getTeacherID().equals("null")) {

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
					if (!filter.getTeacherID().equals("null")) {

					}
					// Falls Lehrer nicht gesetzt ist
					else {

					}
					// Falls Klasse nicht gesetzt ist, aber...
				} else {
					// ...falls Lehrer gesetzt ist
					if (!filter.getTeacherID().equals("null")) {

					}
					// ...falls Lehrer nicht gesetzt ist
					else {
						return filterUsersBySchool(filter);
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
	@Path("/teachers")
	public List<User> filterTeachers(Filter filter) {
		EntityManager em = EMF.createEntityManager();
		log.info("Suche Lehrer: " + filter.toString());
		// Falls kein Feld gesetzt ist
		if (filter.getSchoolID().equals("null") 
				&& filter.getGrade() == 0
				&& filter.getLetter().equals("null")
				&& filter.getStudentID().equals("null")) {
			Query query = em.createQuery("SELECT u FROM User u WHERE isTeacher = true");
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
		//Hier MUSS Schule gesetzt sein, damit überhaupt gefiltert wird. Eine Filterung ohne Berücksichtigung der Schule würde das ganze
		//nur unübersichtlich machen
		EntityManager em = EMF.createEntityManager();
		log.info("Suche Klassen: " + filter.toString());
		//Falls Stufe gesetzt
		if (!filter.getSchoolID().equals("null") 
				&& filter.getGrade() != 0 
				&& filter.getStudentID().equals("null")
				&& filter.getTeacherID().equals("null")) {
			
		}
		//Falls Schueler gesetzt
		else if (!filter.getSchoolID().equals("null") 
				&& filter.getGrade() == 0
				&& !filter.getStudentID().equals("null")
				&& filter.getTeacherID().equals("null")) {
			
		}
		//Falls Lehrer gesetzt
		else if (!filter.getSchoolID().equals("null") 
				&& filter.getGrade() == 0
				&& filter.getStudentID().equals("null")
				&& !filter.getTeacherID().equals("null")) {
			
		}
		//Falls Stufe und Schueler gesetzt sind
		else if (!filter.getSchoolID().equals("null") 
				&& filter.getGrade() != 0 
				&& !filter.getStudentID().equals("null")
				&& filter.getTeacherID().equals("null")) {
			
		}
		//Falls Stufe und Lehrer gesetzt
		else if (!filter.getSchoolID().equals("null") 
				&& filter.getGrade() != 0 
				&& filter.getStudentID().equals("null")
				&& !filter.getTeacherID().equals("null")) {
			
		}
		//Falls Schueler und Lehrer gesetzt
		else if (!filter.getSchoolID().equals("null") 
				&& filter.getGrade() == 0 
				&& !filter.getStudentID().equals("null")
				&& !filter.getTeacherID().equals("null")) {
			
		}
		//Falls alle gesetzt sind
		else if (!filter.getSchoolID().equals("null") 
				&& filter.getGrade() != 0 
				&& !filter.getStudentID().equals("null")
				&& !filter.getTeacherID().equals("null")) {
			
		}
		//Falls Stufe, Lehrer und Schueler nicht gesetzt sind
		else if (!filter.getSchoolID().equals("null")
				&& filter.getGrade() == 0
				&& filter.getStudentID().equals("null")
				&& filter.getTeacherID().equals("null")){
			
			Query query = em.createQuery("SELECT c FROM Clazz c WHERE schoolID = " + filter.getSchoolID());
			em.clear();
			return (List<Clazz>) query.getResultList();
		}
		else {
			return new ArrayList<Clazz>();
		}
		
		return null;
	}

	public List<User> filterUsersBySchool(Filter filter) {
		Long schoolID = Long.valueOf(filter.getSchoolID());

		List<Clazz> clazzes = ClazzRestServlet.getClazzesBySchoolId(schoolID);

		List<Long> userIDs = new ArrayList<Long>();
		for (Clazz clazz : clazzes) {
			List<ClazzUser> clazzUsers = ClazzRestServlet.getClazzUsersByClazzId(clazz.getKey().getId());

			for (ClazzUser clazzUser : clazzUsers) {
				userIDs.add(clazzUser.getUserID());
			}
		}
		List<User> users = new ArrayList<User>();

		EntityManager em = EMF.createEntityManager();
		for (Long userID : userIDs) {
			User user = em.find(User.class, KeyFactory.createKey("User", userID));
			users.add(user);
		}

		return users;
	}
}
