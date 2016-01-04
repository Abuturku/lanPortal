package de.lanGymnasium.datenstruktur;

import java.util.Date;

import javax.persistence.*;

import com.google.appengine.api.datastore.Key;

@Entity
public class Note implements INote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	
	@OneToOne
	private Key teacher;
	@OneToOne
	private Key student;
	private Date timestamp;
	private String text;

	public Note(Key teacher, Key student, Date date, String text) {
		this.teacher = teacher;
		this.student = student;
		this.timestamp = date;
		this.text = text;
	}

	@Override
	public Key getTeacher() {
		return this.teacher;
	}

	@Override
	public Key getStudent() {
		return this.student;
	}

	@Override
	public Date getTimestamp() {
		return this.timestamp;
	}

	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public Key getKey() {
		return this.key;
	}
}
