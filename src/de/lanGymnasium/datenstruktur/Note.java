package de.lanGymnasium.datenstruktur;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Note implements INote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long key;
	
	@OneToOne
	private long teacher;
	@OneToOne
	private long student;
	private Date timestamp;
	private String text;

	public Note(long teacher, long student, Date date, String text) {
		this.teacher = teacher;
		this.student = student;
		this.timestamp = date;
		this.text = text;
	}

	@Override
	public long getTeacher() {
		return this.teacher;
	}

	@Override
	public long getStudent() {
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
	public long getKey() {
		return this.key;
	}
}
