package de.lanGymnasium.datenstruktur;

import javax.persistence.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.images.Image;

@Entity
public class Clazz implements IClazz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;

	private int grade;
	private char letter;
	private Image picture;
	// @OneToMany
	// private ArrayList<Long> students;
	// @OneToMany
	// private ArrayList<Long> teachers;
	private Long school;

	public Clazz(int grade, char letter, Image picture) {
		this.grade = grade;
		this.letter = letter;
		this.picture = picture;
	}

	// public Clazz(int grade, char letter, Image picture, ArrayList<Long>
	// students, ArrayList<Long> teachers) {
	// this(grade, letter, picture);
	// this.students = students;
	// this.teachers = teachers;
	// }

	@Override
	public int getGrade() {
		return this.grade;
	}

	@Override
	public char getLetter() {
		return this.letter;
	}

	@Override
	public Image getPicture() {
		return this.picture;
	}

	@Override
	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public char setLetter(char letter) {
		return this.letter = letter;
	}

	@Override
	public Image setPicture(Image picture) {
		return this.picture = picture;
	}

	@Override
	public Key getKey() {
		return this.key;
	}

	public Long getSchool() {
		return school;
	}

	// @Override
	// public ArrayList<Long> getStudents() {
	// return students;
	// }
	//
	// @Override
	// public void setSchueler(ArrayList<Long> students) {
	// this.students = students;
	// }
	//
	// @Override
	// public ArrayList<Long> getTeacher() {
	// return teachers;
	// }
	//
	// @Override
	// public void setLehrer(ArrayList<Long> teachers) {
	// this.teachers = teachers;
	// }
}
