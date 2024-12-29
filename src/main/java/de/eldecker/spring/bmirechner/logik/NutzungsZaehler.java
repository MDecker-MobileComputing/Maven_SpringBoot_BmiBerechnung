package de.eldecker.spring.bmirechner.logik;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


/**
 * Bean-Klasse für Zählen der in einer HTTP-Sitzung durchgeführten
 * Berechnungen.
 */
@Component
@SessionScope
public class NutzungsZaehler {

	/** Zähler für Anzahl der durchgeführten Berechnungen. */
	private int _anzahlBerechnungen = 0;

	
	/**
	 * Anzahl der durchgeführten Berechnungen +1 erhöhen und
	 * neuen Zählerwert zurückgeben.
	 * 
	 * @return Gesamtzahl der Berechnungen nach Erhöhung
	 */
	public int erhoeheBerechnungsZaehler() {
		
		_anzahlBerechnungen++;
		
		return _anzahlBerechnungen;
	}	
}
