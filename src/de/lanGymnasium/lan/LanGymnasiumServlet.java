package de.lanGymnasium.lan;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LanGymnasiumServlet extends HttpServlet {
//	public void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws IOException {
//		// resp.sendRedirect("index.html");
//
//		resp.setContentType("text/text"); // resp.setContentType("application/json");
//		resp.setCharacterEncoding("utf-8");
//		resp.setHeader("Cache-Control", "no-cache, must-revalidate");
//		resp.setHeader("Access-Control-Allow-Origin", "*");
//
//		PrintWriter writer = resp.getWriter();
//		try {
//			initData();
//			writer.flush();
//		} catch (Exception e) {
//			writer.write("PANIK: " + e.getMessage() + " ");
//			for (StackTraceElement element : e.getStackTrace()) {
//				writer.write("\n" + element.toString());
//			}
//			writer.flush();
//		}
//
//		{// Alle Eintraege ausgeben
//			writer.write("Eintraege im Datastore: ");
//
//			// Lehrer ausgeben
//			EntityManager em = EMF.createEntityManager();
//			String s = "SELECT t FROM Lehrer t";
//			Query query = em.createQuery(s);
//
//			@SuppressWarnings("unchecked")
//			List<IUser> llist = (List<IUser>) query.getResultList();
//
//			for (IUser user : llist) {
//				writer.write("\nUser: " + user.getFirstName() + " "
//						+ user.getFamilyName() + ", ID: "
//						+ user.getKey().getId());
//			}
//			em.close();
//
//			writer.write("\n");
//
//			// Schueler ausgeben
//			em = EMF.createEntityManager();
//			s = "SELECT s FROM Schueler s";
//			query = em.createQuery(s);
//
//			@SuppressWarnings("unchecked")
//			List<IUser> slist = (List<IUser>) query.getResultList();
//
//			for (IUser schueler : slist) {
//				writer.write("\nSchueler: " + schueler.getFirstName() + " "
//						+ schueler.getFamilyName() + ", ID: "
//						+ schueler.getKey().getId());
//			}
//			em.close();
//
//			writer.write("\n");
//
//			// Klassen ausgeben
//			em = EMF.createEntityManager();
//			s = "SELECT k FROM Klasse k";
//			query = em.createQuery(s);
//
////			@SuppressWarnings("unchecked")
////			List<IClazz> klist = (List<IClazz>) query.getResultList();
//
////			for (IClazz klasse : klist) {
////				// writer.write("\nKlasse: " + klasse.getGrade()
////				// + klasse.getLetter() + ", ID: "
////				// + klasse.getKey().getId() + "\n " + klasse.getTeacher()
////				// + "\n " + klasse.getStudents());
////			}
//			em.close();
//
//			writer.write("\n");
//
//			// Notizen
//			em = EMF.createEntityManager();
//			s = "SELECT n FROM Notiz n";
//			query = em.createQuery(s);
//
//			@SuppressWarnings("unchecked")
//			List<INote> nlist = (List<INote>) query.getResultList();
//
//			for (INote notiz : nlist) {
//				writer.write("\nNotiz: " + notiz.getTimestamp() + ", ID: "
//						+ notiz.getKey().getId() + ", Inhalt: "
//						+ notiz.getText() + "\n " + notiz.getTeacher() + ", "
//						+ notiz.getStudent());
//			}
//			em.close();
//		}
//
//		writer.close();
//	}
//
//	private void initData() throws Exception {
////		IClazz fuenfA = new Clazz(5, 'a', null);
//		
//		School school = new School("LAN-Gymnasium");
//		
//		IUser linda = new User("Linda", "Latreider", false, null);
//		IUser niklas = new User("Niklas", "Nikisch", false, null);
//
//		IUser einstein = new User("Albert", "Einstein", true, null);
//		IUser dumbledore = new User("Albus", "Dumbledore", true, null);
//
//		EntityManager em = EMF.createEntityManager();
//		em.persist(school);
//		em.close();
//		em = EMF.createEntityManager();
//		em.persist(linda);
//		em.close();
//		em = EMF.createEntityManager();
//		em.persist(niklas);
//		em.close();
//		em = EMF.createEntityManager();
//		em.persist(einstein);
//		em.close();
//		em = EMF.createEntityManager();
//		em.persist(dumbledore);
//		em.close();
//
//		ArrayList<Key> schueler = new ArrayList<Key>();
//		schueler.add(linda.getKey());
//		schueler.add(niklas.getKey());
//
//		ArrayList<Key> lehrer = new ArrayList<Key>();
//		lehrer.add(einstein.getKey());
//		lehrer.add(dumbledore.getKey());
//
////		fuenfA.setSchueler(schueler);
////		fuenfA.setLehrer(lehrer);
//
//		em = EMF.createEntityManager();
////		em.persist(fuenfA);
//		em.close();
//
//		INote notiz1 = new Note(dumbledore.getKey(), niklas.getKey(), new Date(
//				System.currentTimeMillis()), "5 Punkte f�r Gryffindor");
//		INote notiz2 = new Note(einstein.getKey(), niklas.getKey(), new Date(
//				System.currentTimeMillis()), "E=mc^2");
//		INote notiz3 = new Note(
//				einstein.getKey(),
//				linda.getKey(),
//				new Date(System.currentTimeMillis()),
//				"Insanity: doing the same thing over and over again and expecting different results.");
//		em = EMF.createEntityManager();
//		em.persist(notiz1);
//		em.close();
//		em = EMF.createEntityManager();
//		em.persist(notiz2);
//		em.close();
//		em = EMF.createEntityManager();
//		em.persist(notiz3);
//		em.close();
//	}
//	/*
//	 * private void initData() throws Exception { // IKlasse klasse = new
//	 * Klasse(5, 'a', new ArrayList<ISchueler>(), new ArrayList<ILehrer>(),
//	 * null); // ILehrer lehrer = new Lehrer("Niklas", "Nikisch", "1234", new
//	 * ArrayList<IKlasse>(), new ArrayList<INotiz>(), // null); // ISchueler
//	 * schueler = new Schueler("Linda", "Latreider", "1234", klasse, new
//	 * ArrayList<INotiz>()); // INotiz notiz = new Notiz(lehrer, schueler, new
//	 * Date(System.currentTimeMillis()), "Ich bin doof");
//	 * 
//	 * 
//	 * EntityManager em = EMF.createEntityManager(); em.persist(klasse);
//	 * em.close(); em = EMF.createEntityManager(); em.persist(lehrer);
//	 * em.close(); em = EMF.createEntityManager(); em.persist(schueler);
//	 * em.close(); em = EMF.createEntityManager(); em.persist(notiz);
//	 * em.close();
//	 * 
//	 * em = EMF.createEntityManager(); klasse = em.find(Klasse.class,
//	 * KeyFactory.createKey("Klasse", klasse.getKey().getId()));
//	 * klasse.getSchueler().add(schueler); klasse.getLehrer().add(lehrer);
//	 * 
//	 * 
//	 * // lehrer = em.find(Lehrer.class, KeyFactory.createKey("Lehrer",
//	 * lehrer.getKey().getId())); // lehrer.getKlassen().add(klasse); //
//	 * lehrer.getNotizen().add(notiz); // // schueler = em.find(Schueler.class,
//	 * KeyFactory.createKey("Schueler", schueler.getKey().getId())); //
//	 * schueler.getNotizen().add(notiz);
//	 * 
//	 * // em.merge(lehrer); // em.close(); // em = EMF.createEntityManager();
//	 * em.merge(klasse); // em.close(); // em = EMF.createEntityManager(); //
//	 * em.merge(schueler); // em.close(); // em = EMF.createEntityManager(); //
//	 * em.merge(notiz); // em.close(); }
//	 */
}
