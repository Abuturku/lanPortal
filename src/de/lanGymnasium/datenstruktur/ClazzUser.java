package de.lanGymnasium.datenstruktur;

import java.util.logging.Logger;

import javax.persistence.*;

import com.google.appengine.api.datastore.Key;


@Entity
public class ClazzUser {
	private static final Logger log = Logger.getLogger(ClazzUser.class.getName());
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	
	private Key clazz;
	private Key user;

	public Key getKey() {
		return key;
	}

	public Key getClazz() {
		return clazz;
	}

	public Key getUser() {
		log.info("getUser wurde aufgerufen!");
		return user;
	}

	public ClazzUser(Key clazz, Key user) {
		this.clazz = clazz;
		this.user = user;
	}
}
