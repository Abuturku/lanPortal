//Hinweis: Admin Account kann man nicht löschen!!!


//-----------index.html
function onclickLogin(form) {
	var school = form.login_ddb_inst.value;
	var fname = form.login_inpt_fname.value;
	var lname = form.login_inpt_lname.value;
	var passwd = form.login_inpt_passwd.value;
	window.location.href="mylan";
}
/**
	TODO
	Die Dropdownbox login_ddb_inst mit den Namen der Schulen befüllen
	Wenn keine Schulen registriert, --- angeben
	-> es ist auch keine Anmeldung möglich, da Benutzer etc. nur 
		registriert werden können, wenn ihre Schule vorhanden ist.
	
	Login Abfrage:
	1) Benutzerdaten in Datenbank vorhanden? dann
	2) Passwort korrekt?
	wenn 1) falsch:
	window.alert("Benutzer nicht registriert");
	wenn 2) falsch:
	window.alert("Benutzername oder Passwort sind falsch");
	
	Login erfolgreich:
	-> erster Login? Aufforderung, Passwort zu ändern,
		Weiterleitung auf Passwort ändern Seite
	-> sonst: Weiterleitung auf MyLan.html
*/

//-------------adminLogin.html
function onclickAdminLogin(form) {
	var passwd = form.login_inpt_passwd.value;
	window.location.href="mylan";
	//window.alert("Falsches Passwort");
}

/**
	TODO: Footer Info einbauen.
	Fuer Lehrer/Schueler:
	var schoolname;
	var loginName;
	document.getElementById("footer_txt_logininfo").innerHTML="Angemeldet in der Schule "+ schoolname +" als "+loginName;
	
	Fuer Admin:
	document.getElementById("footer_txt_logininfo").innerHTML="Angemeldet im LAN-Portal als Administrator";
*/

//----------myLAN.html
/**
	TODO
	Visibility und Berechtigungen je nach Rollen (Admin/Lehrer/Schueler) richtig setzen
	Auf der weitergeleiteten Seite die jeweiligen Eingaben korrekt setzen
*/
//Admin Funktionen
function onclickMaintenance_mylan(){
	window.location.href="maint";
}
//Lehrer, Schueler
function onclickMyProfile_mylan(){
/**
	TODO
	- Wenn Lehrer angemeldet:
		Teachers.html aufrufen und den Lehrer selektieren
	- Wenn Schüler angemeldet:
		Pupils.html aufrufen und den Schüler selektieren
*/
}
//Schueler
function onclickMyTeachers_mylan(){
	window.location.href="teachers";
/**
	TODO: Filter Schüler setzen
*/
}
//Lehrer, Schueler
function onclickMyClasses_mylan(){
	window.location.href="classes";
/**
	TODO: Filter Lehrer bzw. Schüler (je nach angemeldetem Benutzer) setzen
*/
}
//Lehrer
function onclickMyPupils_mylan(){
	window.location.href="pupils";
/**
	TODO: Filter Lehrer setzen
*/
}

//Lehrer, Schuler
function onclickChangeMe_mylan(){
	window.location.href="maint";
/**
	TODO: Alles bis auf das relevante div ausblenden
*/
}



//-------------teachers.html
/**
	TODO:
	- Bei Klick auf Tabellenzeile Inhalte des entsprechenden Lehrers in der Preview laden
		- Bevor dies erfolgt ist: Preview Fenster leer lassen
	- Preview Notizen:
		- Man muss eine Zeile der Notizen Tabelle aktivieren/auswählen können
		- Vorschau des Inhalts der ausgewählten Notiz in Textarea Laden
		- Bei Klick auf Button Open die ausgewählte Notiz in note.html laden
			(-> Hierzu könnte der bereits geladene Inhalt verwendet werden, statt erneut abzufragen)
*/

function onclickTeacher_OpenNote(){
	window.location.href="note";
}

//-----------------classes.html
/**
	TODO:
	- Filter-Funktionen implementieren
	- Bei Klick auf Tabellenzeile Inhalte der entsprechenden Klasse in der Preview laden
		- Bevor dies erfolgt ist: Preview Fenster leer lassen
*/
function onclickClass_ddbFilter_inst(){
	
}
function onclickClass_ddbFilter_grade(){
	
}
function onclickClass_ddbFilter_teacher(){
	
}
function onclickClass_ddbFilter_pupil(){
	
}


//------------------pupils.html
/**
	TODO:
	- Bei Klick auf Tabellenzeile Inhalte des entsprechenden Schülers in der Preview laden
		- Bevor dies erfolgt ist: Preview Fenster leer lassen
	- Preview Notizen:
		- Man muss eine Zeile der Notizen Tabelle aktivieren/auswählen können
		- Vorschau des Inhalts der ausgewählten Notiz in Textarea Laden
		- Bei Klick auf Button Open die ausgewählte Notiz in note.html laden
			(-> Hierzu könnte der bereits geladene Inhalt verwendet werden, statt erneut abzufragen)
*/

function onclickPupil_OpenNote(){
	window.location.href="note";
}
function onclickPupil_noteTable_addNote(){
	window.location.href="note";
/**
	TODO
	- Diese Funktion steht nur Lehrern zur Verfügung
	- Lehrer, Zeitpunkt der Bearbeitung, Schüler Input-Felder initialisieren
	- leere Textarea initialisieren
*/
}


//-----------------Sonderseiten--------------------
function onclickReturnToMyLAN(){
	window.location.href="mylan";
}

//note.html
function onclickReturnToPupils(){
	window.location.href="pupils";
}
function onclickSaveNote(form){
	var noteTeacher = form.note_inpt_tName.value;
	var noteTimestamp = form.note_inpt_ts.value;
	var notePupil = form.note_inpt_pName.value;
	var noteContent = form.note_txtArea_content.value;
	
/**
	TODO: Inhalt abspeichern/updaten
*/
}