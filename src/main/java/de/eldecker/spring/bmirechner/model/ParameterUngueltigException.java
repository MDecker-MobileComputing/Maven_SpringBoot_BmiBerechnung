package de.eldecker.spring.bmirechner.model;


@SuppressWarnings("serial")
public class ParameterUngueltigException extends Exception {

	public ParameterUngueltigException( String fehlerbeschreibung ) {
		
		super( fehlerbeschreibung );
	}
	
}
