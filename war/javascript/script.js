//Hinweis: Admin Account kann man nicht löschen!!!


//-----------index.html
function onclickLogin(form) {
	var school = form.login_ddb_inst.value;
	var fname = form.login_inpt_fname.value;
	var lname = form.login_inpt_lname.value;
	var passwd = form.login_inpt_passwd.value;
	window.location.href="mylan.html";
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
	window.location.href="mylan.html";
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
	window.location.href="maint.html";
}
function onclickResetPasswd_mylan(){
	window.location.href="resetPasswd.html";
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
	window.location.href="teachers.html";
/**
	TODO: Filter Schüler setzen
*/
}
//Lehrer, Schueler
function onclickMyClasses_mylan(){
	window.location.href="classes.html";
/**
	TODO: Filter Lehrer bzw. Schüler (je nach angemeldetem Benutzer) setzen
*/
}
//Lehrer
function onclickMyPupils_mylan(){
	window.location.href="pupils.html";
/**
	TODO: Filter Lehrer setzen
*/
}
//Admin, Lehrer, Schueler
function onclickChangePasswd_mylan(){
	window.location.href="newPasswd.html";
}
//Lehrer, Schuler
function onclickChangeMe_mylan(){
	window.location.href="maint.html";
/**
	TODO: Alles bis auf das relevante div ausblenden
*/
}



//-------------teachers.html
/**
	TODO:
	- Filter-Funktionen implementieren
	- Bei Klick auf Tabellenzeile Inhalte des entsprechenden Lehrers in der Preview laden
		- Bevor dies erfolgt ist: Preview Fenster leer lassen
	- Preview Notizen:
		- Man muss eine Zeile der Notizen Tabelle aktivieren/auswählen können
		- Vorschau des Inhalts der ausgewählten Notiz in Textarea Laden
		- Bei Klick auf Button Open die ausgewählte Notiz in note.html laden
			(-> Hierzu könnte der bereits geladene Inhalt verwendet werden, statt erneut abzufragen)
*/
function onclickTeacher_ddbFilter_inst(){
	
}
function onclickTeacher_ddbFilter_grade(){
	
}
function onclickTeacher_ddbFilter_class(){
	
}
function onclickTeacher_ddbFilter_pupil(){
	
}

function onclickTeacher_OpenNote(){
	window.location.href="note.html";
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
	- Filter-Funktionen implementieren
	- Bei Klick auf Tabellenzeile Inhalte des entsprechenden Schülers in der Preview laden
		- Bevor dies erfolgt ist: Preview Fenster leer lassen
	- Preview Notizen:
		- Man muss eine Zeile der Notizen Tabelle aktivieren/auswählen können
		- Vorschau des Inhalts der ausgewählten Notiz in Textarea Laden
		- Bei Klick auf Button Open die ausgewählte Notiz in note.html laden
			(-> Hierzu könnte der bereits geladene Inhalt verwendet werden, statt erneut abzufragen)
*/
function onclickPupil_ddbFilter_inst(){
	
}
function onclickPupil_ddbFilter_grade(){
	
}
function onclickPupil_ddbFilter_class(){
	
}
function onclickPupil_ddbFilter_teacher(){
	
}


function onclickPupil_OpenNote(){
	window.location.href="note.html";
}
function onclickPupil_noteTable_addNote(){
	window.location.href="note.html";
/**
	TODO
	- Diese Funktion steht nur Lehrern zur Verfügung
	- Lehrer, Zeitpunkt der Bearbeitung, Schüler Input-Felder initialisieren
	- leere Textarea initialisieren
*/
}


//-----------------Sonderseiten--------------------
function onclickReturnToMyLAN(){
	window.location.href="mylan.html";
}

//note.html
function onclickReturnToPupils(){
	window.location.href="pupil.html";
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

//newPasswd.html
function onclickSaveNewPw(form){
	var old_passwd = form.chngPw_inpt_oldPw.value;
	var new_passwd1 = form.chngPw_inpt_newPw1.value;
	var new_passwd2 = form.chngPw_inpt_newPw2.value;
/**
	TODO
	- Prüfen, ob altes Passwort korrekt
	- Prüfen, ob neue Passwörter übereinstimmen
	- Bei Fehler, entsprechende Meldung per Popup
	- Falls alles Korrekt: Passwort des Benutzers ändern + Popup mit Bestätigung
*/
	window.location.href="mylan.html";
}

//resetPasswd.html
function onclickResetPw(form){
	var school = form.login_ddb_inst.value;
	var fname = form.rstPw_inpt_fname.value;
	var lname = form.rstPw_inpt_lname.value;
/**
	TODO
	- Prüfen, ob Benutzer vorhanden
	- Bei Fehler, entsprechende Meldung per Popup
	- Falls alles Korrekt: Passwort des Benutzers ändern + Popup mit Bestätigung
*/
	window.location.href="mylan.html";
}
















