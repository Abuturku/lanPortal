package de.lanGymnasium.datenstruktur;

import javax.persistence.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.images.Image;

@Entity
public class User implements IUser{
	@Id
	private Key key;
	private String firstName;
	private String familyName;
	private Image picture;
	private boolean isTeacher;

	public User(String firstName, String familyName,
			boolean isTeacher, Image picture) {
		this.firstName = firstName;
		this.familyName = familyName;
		this.isTeacher = isTeacher;
		this.picture = picture;
	}
	
	@Override
	public boolean equals(User user){
		if (user.getFirstName().equals(this.firstName)) {
			if (user.getFamilyName().equals(this.familyName)) {	
					return true;
			}
		}
		return false;
	}
	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public String getFamilyName() {
		return this.familyName;
	}

	@Override
	public String setFirstName(String firstName) {
		return this.firstName = firstName;
	}

	@Override
	public String setFamilyName(String familyName) {
		return this.familyName = familyName;
	}

	@Override
	public Key getKey() {
		return this.key;
	}

	@Override
	public Image getPicture() {
		return picture;
	}

	@Override
	public void setPicture(Image picture) {
		this.picture = picture;
	}

	@Override
	public boolean isTeacher() {
		return this.isTeacher;
	}
}
