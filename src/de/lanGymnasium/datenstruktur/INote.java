package de.lanGymnasium.datenstruktur;

import java.util.Date;

public interface INote {
	public long getTeacher();

	public long getStudent();

	public Date getTimestamp();

	public String getText();

	public long getKey();
}
