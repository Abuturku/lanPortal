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
	
	private Long clazzID;
	private Long userID;

	public Key getKey() {
		return key;
	}

	public long getClazzID() {
		return clazzID;
	}

	public long getUserID() {
		log.info("getUser wurde aufgerufen!");
		return userID;
	}

	public ClazzUser(Long clazz, Long user) {
		this.clazzID = clazz;
		this.userID = user;
	}
}
