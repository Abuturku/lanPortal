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

import de.lanGymnasium.datenstruktur.Note;
import de.lanGymnasium.lan.EMF;

@Path("/note")
public class NoteRestServlet {
	private static final Logger log = Logger.getLogger(NoteRestServlet.class
			.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> getNotes() {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery("SELECT n FROM Note n");

		@SuppressWarnings("unchecked")
		List<Note> list = (List<Note>) query.getResultList();
		em.close();

		return list;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Note newNote(Note note) {
		System.out.print(note);
		EntityManager em = EMF.createEntityManager();
		em.persist(note);
		em.close();
		return note;
	}

	@DELETE
	@Path("/{id}")
	public void deleteNote(@PathParam("id") Long id) {
		EntityManager em = EMF.createEntityManager();
		Note note = em.find(Note.class, KeyFactory.createKey("Note", id));
		System.out.println("remove " + note);
		em.remove(note);
		em.close();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Note getNote(@PathParam("id") Long id) {
		EntityManager em = EMF.createEntityManager();
		Note note = em.find(Note.class, KeyFactory.createKey("Note", id));
		System.out.println("get " + note);
		em.close();

		return note;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("studentNotes/{id}")
	public List<Note> getStudentNotes(@PathParam("id") Long id) {
		EntityManager em = EMF.createEntityManager();

		Query q = em
				.createQuery("SELECT n FROM Note n WHERE studentID = " + id);
		log.info("Query: SELECT n FROM Note n WHERE studentID = " + id);
		@SuppressWarnings("unchecked")
		List<Note> notes = (List<Note>) q.getResultList();
		log.info("Notizenliste Größe: " + notes.size());
		em.close();

		return notes;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("teacherNotes/{id}")
	public List<Note> getTeacherNotes(@PathParam("id") Long id) {
		EntityManager em = EMF.createEntityManager();
		Query q = em.createQuery("SELECT n FROM Note n WHERE teacherID = " + id);
		log.info("Query: SELECT n FROM Note n WHERE teacherID = " + id);
		@SuppressWarnings("unchecked")
		List<Note> notes = (List<Note>) q.getResultList();
		log.info("Notizenliste Größe: " + notes.size());
		em.close();
		
		return notes;
	}
}
