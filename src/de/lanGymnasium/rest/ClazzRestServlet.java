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

import de.lanGymnasium.datenstruktur.Clazz;
import de.lanGymnasium.lan.EMF;

@Path("/clazz")
public class ClazzRestServlet {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Clazz> getClazzes() {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery("SELECT c FROM Clazz c");

		@SuppressWarnings("unchecked")
		List<Clazz> list = (List<Clazz>)query.getResultList();
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
	public void deleteClazz(@PathParam("id") long id) {
		EntityManager em = EMF.createEntityManager();
		Clazz clazz = em.find(Clazz.class, KeyFactory.createKey("Clazz", id));
		System.out.println("remove "+clazz);
		em.remove(clazz);
		em.close();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Clazz getClazz(@PathParam("id") long id) {
		EntityManager em = EMF.createEntityManager();
		Clazz clazz = em.find(Clazz.class, KeyFactory.createKey("Clazz", id));
		System.out.println("get "+clazz);
		em.close();
		
		return clazz;
	}
}
