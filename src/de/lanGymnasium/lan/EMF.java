package de.lanGymnasium.lan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF {
	private static EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("transactions-optional");

	synchronized public static EntityManager createEntityManager() {
		return emfInstance.createEntityManager();
	}
}