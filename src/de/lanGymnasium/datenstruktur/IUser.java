package de.lanGymnasium.datenstruktur;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.images.Image;

public interface IUser {
	public String getFirstName();

	public String getFamilyName();

	public String setFirstName(String firstName);

	public String setFamilyName(String familyName);

	public Key getKey();

	public Image getPicture();

	public void setPicture(Image picture);

	public boolean isTeacher();

	public boolean equals(User user);

	public void setGoogleID(String googleID);
	
	public String getGoogleID();
}
