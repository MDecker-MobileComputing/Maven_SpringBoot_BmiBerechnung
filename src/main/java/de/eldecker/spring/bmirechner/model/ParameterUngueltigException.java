package de.eldecker.spring.bmirechner.model;


/**
 * Exception-Klasse für den Fall, dass für die BMI-Berechnung
 * Werte im ungültigen Bereich übergeben werden, z.B. zu
 * großes Gewicht.
 */
@SuppressWarnings("serial")
public class ParameterUngueltigException extends Exception {

	/**
	 * Konstruktor zur Erzeugung eines Exception-Objekts zur Signalisierung
	 * eines ungültigen Paramters.
	 * 
	 * @param fehlerbeschreibung Beschreibung, welcher Parameter im ungültigen
	 *                           Bereich lag
	 */
	public ParameterUngueltigException( String fehlerbeschreibung ) {
		
		super( fehlerbeschreibung );
	}
	
}
