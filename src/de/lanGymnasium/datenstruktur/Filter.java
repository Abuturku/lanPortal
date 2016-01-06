package de.lanGymnasium.datenstruktur;

public class Filter {

	private String schoolID;
	private int grade;
	private String letter;
	private String teacherID;
	private String studentID;

	public Filter(String schoolID, int grade, String letter, String teacherID,
			String studentID) {
		this.schoolID = schoolID;
		this.grade = grade;
		this.letter = letter;
		this.teacherID = teacherID;
		this.studentID = studentID;
	}
	
	
	public Filter() {

	}

	public String getSchoolID() {
		return schoolID;
	}

	public int getGrade() {
		return grade;
	}

	public String getLetter() {
		return letter;
	}

	public String getTeacherID() {
		return teacherID;
	}

	public String getStudentID() {
		return studentID;
	}

	@Override
	public String toString() {
		return "Filter: schoolID: " + schoolID + " | grade: " + grade
				+ " | letter: " + letter + " | teacherID: " + teacherID + " | studentID: " + studentID;
	}
}
