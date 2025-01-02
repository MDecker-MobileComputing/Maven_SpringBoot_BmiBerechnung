package de.eldecker.spring.bmirechner.logik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

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
	
    @Autowired
    private HttpServletRequest _httpRequest;

	
	/**
	 * Konstruktor, gibt Nr der erzeugten Instanz auf Logger aus.
	 */
	public NutzungsZaehler( HttpServletRequest httpRequest ) {
	
	    _httpRequest = httpRequest;
	    
		sBeanCounter++;
		
		final String sessionId = getSessionId();
		LOG.info( "SessionBean Nr. " + sBeanCounter + " angelegt, SessionID=" + sessionId );
	}
	
	
	/**
	 * Anzahl der durchgeführten Berechnungen +1 erhöhen und
	 * neuen Zählerwert zurückgeben.
	 * 
	 * @return Gesamtzahl der Berechnungen nach Erhöhung
	 */
	public int erhoeheBerechnungsZaehler() {
		
		_anzahlBerechnungen++;
		
		LOG.info( "Nutzungszähler für Session {} erhöt auf: {}", 
		          getSessionId(), _anzahlBerechnungen ); 
		
		return _anzahlBerechnungen;
	}	
	
	
	/**
	 * ID für HTTP-Session abfragen (jsessionid).
	 * 
	 * @return Sitzungs-ID; kann leerer String sein, aber nicht {@code null}
	 */
	public String getSessionId() {
	    
        return Optional.ofNullable( _httpRequest )
                       .map( HttpServletRequest::getSession )
                       .map( HttpSession::getId )
                       .orElse( "" );
	}
	
}
