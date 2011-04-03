<? include ("frame.inc"); ?>
    <h1>Dokumentation: Mail</h1>
    <p>Ab Version 1.3</p>
    <p>In JVerein ist ein einfaches Mail-Programm integriert. Hiermit ist es m�glich,
       einfache Textmails mit Dateianh�ngen an einzelne Mitglieder, Gruppen von Mitglieder oder alle 
       Mitglieder zu verschicken. Der Empfang von Mails ist nicht vorgesehen.</p>
       
    <p><b>Konfiguration</b></p>
    <p>Die Konfiguration ist unter <a href='administration_einstellungen.php#mail'>Einstellungen</a> 
    beschrieben.</p>
    
    <p><b>Mail-Vorlagen</b></p>
    <p>F�r den Fall, dass immer �hnliche Mails versandt werden, kann die Tipparbeit durch
       Mailvorlagen minimiert werden.</p>
    <img src='images/Mailvorlagen.jpg' class='screenshot'>
	<p>Durch einen Doppelklick auf den Betreff oder durch einen Klick auf neu �ffnet
	   sich das Bearbeitungsfenster.</p>
    <img src='images/Mailvorlage.jpg' class='screenshot'>
   
   <p><b>Mails</b></p>
   <p>In der Liste aller Mails wird der Betreff, das Bearbeitungs- und das Versanddatum angezeigt.</p>
   <img src='images/Mails.jpg' class='screenshot'>
   <p>Durch einen Klick auf neu �ffnet sich ein Mail-Vorlagen-Auswahlfenster:</p>
   <img src='images/MailvorlagenAuswahl.jpg' class='screenshot'>
   <p>Entweder wird eine Mailvorlage ausgew�hlt oder es geht ohne Vorlage weiter.</p>
   <p>Ein Doppelklick auf eine Mail �ffnet das Bearbeitungsfenster.</p>
   <img src='images/Mail.jpg' class='screenshot'>
   <p>Durch einen Klick auf hinzuf�gen �ffnet sich folgendes Auswahlfenster:</p>
   <img src='images/Mailempfaengerauswahl.jpg' class='screenshot'>
   <p>Durch einen Klick auf Eigenschaften �ffnet sich ein Eigenschaftenauswahldialog. Alle Mitglieder
   mit den ausgew�hlten Eigenschaften werden hinzugef�gt. Mit "alle" werden alle Mitglieder ausgew�hlt. Mit
   "keinen" wird die komplette Auswahl r�ckg�ngig gemacht.</p>
   <p>Der Mailversand kann auch �ber einen Rechtsklick auf ein Mitglied ausgel�st werden:</p>
   <img src='images/MitgliedMailversand.jpg' class='screenshot'>
   <p><b>Variable</b></p>
   <p>Im Betreff und im Text k�nnen Variable eingef�gt werden, die beim Mailversand mit den
   konkreten Daten gef�llt werden.</p>
   <table border="1">
   <tr><th>Name</th><th>Inhalt</th></tr>
   <tr><td>$email</td><td>Emailadresse des Empf�ngers</td></tr>
   <tr><td>$tagesdatum</td><td>Aktuelles Datum in der Form tt.mm.jjjj</td></tr>
   <tr><td>$vormonat</td><td>Vormonat im Format mm.jjjj</td></tr>
   <tr><td>$aktuellermonat</td><td>Aktueller Monat im Format mm.jjjj</td></tr>
   <tr><td>$folgemonat</td><td>Folgemonat im Format mm.jjjj</td></tr>
	 <tr><td>$empf.id</td><td>Interne Mitgliedsnummer</td></tr>
	 <tr><td>$empf.externemitgliedsnummer</td><td>externe Mitgliedsnummer</td></tr>
	 <tr><td>$empf.adresstyp</td><td>interne Nummer des Adresstyps</td></tr>
	 <tr><td>$empf.personentyp</td><td>Personentyp: n=nat�rliche Person, j=juristische Person</td></tr>
   <tr><td>$empf.anrede</td><td>Anrede</td></tr>
   <tr><td>$empf.titel</td><td>Titel</td></tr>
   <tr><td>$empf.name</td><td>Name</td></tr>
   <tr><td>$empf.vorname</td><td>Vorname</td></tr>
   <tr><td>$empf.adressierungszusatz</td><td>Adressierungszusatz</td></tr>
   <tr><td>$empf.strasse</td><td>Stra�e</td></tr>
   <tr><td>$empf.plz</td><td>Postleitzahl</td></tr>
   <tr><td>$empf.ort</td><td>Ort</td></tr>
   <tr><td>$empf.staat</td><td>Staat</td></tr>
   <tr><td>$empf.blz</td><td>Bankleitzahl</td></tr>
   <tr><td>$empf.konto</td><td>Kontonummer</td></tr>
   <tr><td>$empf.iban</td><td>IBAN</td></tr>
   <tr><td>$empf.kontoinhaber</td><td>Kontoinhaber</td></tr>
   <tr><td>$empf.geschlecht</td><td>Geschlecht m oder w</td></tr>
   <tr><td>$empf.telefonprivat</td><td>Telefon privat</td></tr>
   <tr><td>$empf.telefondienstlich</td><td>Telefon dienstlich</td></tr>
   <tr><td>$empf.handy</td><td>Handy</td></tr>
   <tr><td>$empf.email</td><td>Email-Adresse</td></tr>
   <tr><td>$empf.zahlungsweg</td><td>Schl�ssel des Zahlungsweges</td></tr>
   <tr><td>$empf.zahlungsrhytmus</td><td>Schl�ssel des Zahlungsrhytmus</td></tr>
   <tr><td>$empf.geburtsdatum</td><td>Geburtsdatum. Formatierung: $!{dateformat.format(${empf.geburtsdatum})} </td></tr>
   <tr><td>$empf.eintritt</td><td>Eintrittsdatum. Formatierung: $!{dateformat.format(${empf.eintrittsdatum})} </td></tr>
   <tr><td>$empf.austritt</td><td>Austrittsdatum. Formatierung: $!{dateformat.format(${empf.austrittsdatum})} </td></tr>
   <tr><td>$empf.kuendigung</td><td>K�ndigungsdatum. Formatierung: $!{dateformat.format(${empf.kuendigung})} </td></tr>
   <tr><td>$empf.sterbetag</td><td>Sterbetag. Formatierung: $!{dateformat.format(${empf.sterbetag})} </td></tr>
   <tr><td>$empf.beitragsgruppe.bezeichnung</td><td>Bezeichnung der Beitragsgruppe. </td></tr>
   <tr><td>$empf.beitragsgruppe.betrag</td><td>Bezeichnung der Beitragsgruppe. Formatierung: $!{decimalformat.format(${empf.beitragsgruppe.betrag})}</td></tr>
   <tr><td>$empf.beitragsgruppe.beitragsart</td><td>Beitragsart</td></tr>
   <tr><td>$empf.beitragsgruppe.arbeitseinsatzstunden</td><td>Anzahl Stunden Arbeitseinsatz</td></tr>
   <tr><td>$empf.beitragsgruppe.arbeitseinsatzbetrag</td><td>Betrag pro Stunde fehlenden Arbeitseinsatzes. Formatierung: $!{decimalformat.format($empf.beitragsgruppe.arbeitseinsatzbetrag})} </td></tr>
   <tr><td>$empf.zahlerid</td><td>Interne Mitgliedsnummer des zahlenden Mitglieds </td></tr>
   <tr><td>$empf.vermerk1</td><td>1. Vermerk </td></tr>
   <tr><td>$empf.vermerk2</td><td>2. Vermerk </td></tr>
   <tr><td>$empf.eingabedatum</td><td>Datum der Ersteingabe des Datensatzes. Formatierung: $!{dateformat.format(${empf.eingabedatum})} </td></tr>
   <tr><td>$empf.letzteaenderung</td><td>Datum der letzten �nderung des Datensatzes. Formatierung: $!{dateformat.format(${empf.letzteaenderung})} </td></tr>
   <tr><td>$empf.namevorname</td><td>Name, Vorname </td></tr>
   <tr><td>$empf.vornamename</td><td>Vorname Name</td></tr>
   <tr><td>$empf.anschrift</td><td>Formatierte Anschrift</td></tr>
 </table>
 <p>Ab Version 1.5</p>
 <p>Die Zusatzfelder k�nnen �ber $zusatzfeld. adressiert werden. Beispiel: Es wurde das Zusatzfeld "nickname" eingerichtet. Dann kann das Zusatzfeld mit $zusatzfeld.nickname ausgegeben werden.</p>
 <p>Die Formatierungen von Betr�gen und Datumsfeldern funktioniert ab Version 1.5.</p>
    <!-- 
    $Log: mail.php,v $
    Revision 1.6  2011/04/03 10:02:26  jost
    Ausgabe der Zusatzfelder

    Revision 1.5  2011-03-20 19:24:17  jost
    Zus�tzliche Felder

    Revision 1.4  2010-03-27 20:11:16  jost
    EigenschaftenAuswahl �berarbeitet.

    Revision 1.3  2010/02/16 18:07:29  jost
    *** empty log message ***

    Revision 1.2  2010/02/04 18:38:56  jost
    Zus�tzliche Datenfelder

    Revision 1.1  2010/02/01 21:03:42  jost
    Neu: Einfache Mailfunktion

    -->
        
<? include ("footer.inc"); ?>

