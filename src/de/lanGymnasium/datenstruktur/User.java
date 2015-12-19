package de.lanGymnasium.datenstruktur;

import javax.persistence.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.images.Image;

@Entity
public class User implements IUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private String firstName;
	private String familyName;
	private String password;
	private Image picture;
	private boolean isTeacher;
	@ManyToOne
	private Key school;

	public User(String firstName, String familyName, String password,
			boolean isTeacher, Image picture, Key school) {
		this.firstName = firstName;
		this.familyName = familyName;
		this.password = password;
		this.isTeacher = isTeacher;
		this.picture = picture;
		this.school = school;
	}
	
	@Override
	public boolean equals(User user){
		if (user.getSchool().compareTo(this.school) == 0){
			if (user.getFirstName().equals(this.firstName)) {
				if (user.getFamilyName().equals(this.familyName)) {
					if (user.getPassword().equals(this.password)) {
						return true;
					}
				}
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
	public String getPassword() {
		return this.password;
	}

	@Override
	public String setPassword(String password) {
		return this.password = password;
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
	
	public Key getSchool() {
		return school;
	}
	
	public void setSchool(Key school){
		this.school = school;
	}
}
