package de.lanGymnasium.datenstruktur;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class School implements ISchool{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	
	private String name;
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public School(String name) {
		this.name = name;
	}
	
	public Key getKey(){
		return this.key;
	}
}
