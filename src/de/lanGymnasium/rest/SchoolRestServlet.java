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

import de.lanGymnasium.datenstruktur.School;
import de.lanGymnasium.lan.EMF;

@Path("/school")
public class SchoolRestServlet {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<School> getSchool() {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery("SELECT s FROM School s");

		@SuppressWarnings("unchecked")
		List<School> list = (List<School>) query.getResultList();
		em.close();

		return list;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public School newSchool(School school) {
		System.out.print(school);
		EntityManager em = EMF.createEntityManager();
		em.persist(school);
		em.close();
		return school;
	}

	@DELETE
	@Path("/{id}")
	public void deleteSchool(@PathParam("id") long id) {
		EntityManager em = EMF.createEntityManager();
		School school = em.find(School.class,
				KeyFactory.createKey("School", id));
		System.out.println("remove " + school);
		em.remove(school);
		em.close();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public School getSchool(@PathParam("id") long id) {
		EntityManager em = EMF.createEntityManager();
		School school = em.find(School.class,
				KeyFactory.createKey("School", id));
		System.out.println("get " + school);
		em.close();

		return school;
	}

	
}
