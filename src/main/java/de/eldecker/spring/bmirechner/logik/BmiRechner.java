package de.eldecker.spring.bmirechner.logik;

import org.springframework.stereotype.Service;

import de.eldecker.spring.bmirechner.model.BmiErgebnisRecord;


/**
 * Bean-Klasse mit eigentlicher BMI-Berechnung. 
 */
@Service
public class BmiRechner {

	/**
	 * Methode zur BMI-Berechnung. Die Eingabeparameter werden nicht daraufhin 
	 * überprüft, ob sie in einem sinnvollen Bereich sind.
	 * 
	 * @param cm Körpergröße in cm
	 * 
	 * @param kg Körpergewicht in kg
	 * 
	 * @return Objekt mit BMI-Wert, {@code erfolg=true}
	 */
	public BmiErgebnisRecord bmiBerechnen( int cm, int kg ) {
		
        final double groesseMeter  = cm / 100.0;
        final double bmiUngerundet = kg / ( groesseMeter * groesseMeter );
        final int    bmiGerundet   = (int)( bmiUngerundet * 100 / 100.0 );
        
        final BmiErgebnisRecord ergebnis = new BmiErgebnisRecord( true, bmiGerundet, "N/A" );
        return ergebnis;
	}
	
}
