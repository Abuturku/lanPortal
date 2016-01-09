//Url Parameter holen
var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

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

//-----------------Sonderseiten--------------------
function onclickReturnToMyLAN(){
	window.location.href="mylan";
}