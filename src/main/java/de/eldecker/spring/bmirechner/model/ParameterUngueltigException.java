package de.eldecker.spring.bmirechner.model;


/**
 * Exception-Klasse für den Fall, dass für die BMI-Berechnung
 * Werte im ungültigen Bereich übergeben werden.
 */
@SuppressWarnings("serial")
public class ParameterUngueltigException extends Exception {

	public ParameterUngueltigException( String fehlerbeschreibung ) {
		
		super( fehlerbeschreibung );
	}
	
}
