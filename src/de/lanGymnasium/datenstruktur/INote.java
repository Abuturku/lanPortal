package de.lanGymnasium.datenstruktur;

import java.util.Date;

import com.google.appengine.api.datastore.Key;

public interface INote {
	public Long getTeacher();

	public Long getStudent();

	public Date getTimestamp();

	public String getText();

	public Key getKey();
}
