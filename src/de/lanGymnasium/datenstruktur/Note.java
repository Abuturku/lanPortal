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
	private Long teacherID;
	@OneToOne
	private Long studentID;
	private Date timestamp;
	private String text;

	public Note(Long teacher, Long student, Date date, String text) {
		this.teacherID = teacher;
		this.studentID = student;
		this.timestamp = date;
		this.text = text;
	}

	@Override
	public Long getTeacher() {
		return this.teacherID;
	}

	@Override
	public Long getStudent() {
		return this.studentID;
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
