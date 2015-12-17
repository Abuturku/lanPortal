package de.lanGymnasium.datenstruktur;

import java.util.ArrayList;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.images.Image;

public interface IClazz {
	public int getGrade();

	public char getLetter();

	public Image getPicture();

	public void setGrade(int grade);

	public char setLetter(char letter);

	public Image setPicture(Image picture);

	public Key getKey();

	public ArrayList<Key> getStudents();

	public void setSchueler(ArrayList<Key> students);

	ArrayList<Key> getTeacher();

	void setLehrer(ArrayList<Key> teacher);
}
