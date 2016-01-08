package de.lanGymnasium.datenstruktur;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.images.Image;

public interface IClazz {
	public int getGrade();

	public String getLetter();

	public Image getPicture();

	public void setGrade(int grade);

	public String setLetter(String letter);

	public Image setPicture(Image picture);

	public Key getKey();
//
//	public ArrayList<Long> getStudents();
//
//	public void setSchueler(ArrayList<Long> students);
//
//	public ArrayList<Long> getTeacher();
//
//	public void setLehrer(ArrayList<Long> teacher);
}
