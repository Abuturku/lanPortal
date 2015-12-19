package de.lanGymnasium.datenstruktur;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.images.Image;

public interface IUser {
	public String getFirstName();

	public String getFamilyName();

	public String setFirstName(String firstName);

	public String setFamilyName(String familyName);

	public String getPassword();

	public String setPassword(String password);

	public Key getKey();

	public Image getPicture();

	public void setPicture(Image picture);
	
	public boolean isTeacher();
	
	public Key getSchool();

	boolean equals(User user);
}
