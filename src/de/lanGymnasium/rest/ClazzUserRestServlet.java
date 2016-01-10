package de.lanGymnasium.rest;

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

import de.lanGymnasium.datenstruktur.Clazz;
import de.lanGymnasium.datenstruktur.ClazzUser;
import de.lanGymnasium.datenstruktur.Note;
import de.lanGymnasium.datenstruktur.School;
import de.lanGymnasium.lan.EMF;

@Path("/clazzUser")
public class ClazzUserRestServlet {
	private static final Logger log = Logger
			.getLogger(ClazzUserRestServlet.class.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClazzUser> getClazzUsers() {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery("SELECT c FROM ClazzUser c");

		@SuppressWarnings("unchecked")
		List<ClazzUser> list = (List<ClazzUser>) query.getResultList();
		em.close();

		return list;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ClazzUser newClazzUser(ClazzUser clazzUser) {
		System.out.print(clazzUser);
		EntityManager em = EMF.createEntityManager();
		em.persist(clazzUser);
		em.close();
		return clazzUser;
	}

	@DELETE
	@Path("/{id}")
	public void deleteClazzUser(@PathParam("id") Long id) {
		EntityManager em = EMF.createEntityManager();
		ClazzUser clazzUser = em.find(ClazzUser.class,
				KeyFactory.createKey("ClazzUser", id));
		System.out.println("remove " + clazzUser);
		em.remove(clazzUser);
		em.close();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public ClazzUser getClazzUser(@PathParam("id") Long id) {
		EntityManager em = EMF.createEntityManager();
		ClazzUser clazzUser = em.find(ClazzUser.class,
				KeyFactory.createKey("ClazzUser", id));
		System.out.println("get " + clazzUser);
		em.close();

		return clazzUser;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/edit/{id}")
	public void editClazzUser(@PathParam("id") long id, ClazzUser clazzUser) {
		log.info("Edit ClazzUser: " + id);

		EntityManager em = EMF.createEntityManager();
		ClazzUser foundClazzUser = em.find(ClazzUser.class,
				KeyFactory.createKey("ClazzUser", id));
		if (foundClazzUser != null) {
			foundClazzUser.setClazzID(clazzUser.getClazzID());
			foundClazzUser.setUserID(clazzUser.getUserID());
			em.merge(foundClazzUser);
		} else {
			em.persist(clazzUser);
		}

		em.close();
	}

	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/clazzUserWithoutID")
	public ClazzUser getClazzUserWithoutID(ClazzUser clazzUser) {
		EntityManager em = EMF.createEntityManager();
		Query query = em
				.createQuery("SELECT c FROM ClazzUser c WHERE userID = "
						+ clazzUser.getUserID() + " AND clazzID = "
						+ clazzUser.getClazzID());
		log.info("Suche ClazzUser mit userID: " + clazzUser.getUserID()
				+ " und clazzID: " + clazzUser.getClazzID());
		List<ClazzUser> clazzUsers = query.getResultList();
		em.close();
		if (clazzUsers.size() == 0) {
			return null;
		}
		return clazzUsers.get(0);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/deleteWithUserID/{id}")
	public void deleteWithUserID(@PathParam("id") long userID) {
		EntityManager em = EMF.createEntityManager();
		Query query = em
				.createQuery("SELECT c FROM ClazzUser c WHERE userID = "
						+ userID);
		log.info("Loesche alle ClazzUser des Users: " + userID);
		@SuppressWarnings("unchecked")
		List<ClazzUser> clazzUserList = query.getResultList();
		em.close();
		for (ClazzUser clazzUser : clazzUserList) {
			deleteClazzUser(clazzUser.getKey().getId());
		}
	}
}
