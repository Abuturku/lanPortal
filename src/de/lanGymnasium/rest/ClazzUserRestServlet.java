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

import de.lanGymnasium.datenstruktur.ClazzUser;
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

}
