package de.eldecker.spring.bmirechner.logik;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Bean-Klasse für Zählen der in einer HTTP-Sitzung durchgeführten
 * Berechnungen.
 */
@Component
@SessionScope
public class NutzungsZaehler {

	private static final Logger LOG = LoggerFactory.getLogger( NutzungsZaehler.class ); 
	
	/** Klassenvariable zum Zählen der Instanzen dieser Klasse. */
	private static int sBeanCounter = 0;
	
	/** Zähler für Anzahl der durchgeführten Berechnungen. */
	private int _anzahlBerechnungen = 0;

	
	/**
	 * Konstruktor, gibt Nr der erzeugten Instanz auf Logger aus.
	 */
	public NutzungsZaehler() {
	
		sBeanCounter++;
		LOG.info( "SessionBean Nr. " + sBeanCounter + " angelegt." );
	}
	
	
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
