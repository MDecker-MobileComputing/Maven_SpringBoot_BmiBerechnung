package de.eldecker.spring.bmirechner.logik;

import org.springframework.stereotype.Service;

import de.eldecker.spring.bmirechner.model.BmiErgebnisRecord;


/**
 * Bean-Klasse mit eigentlicher BMI-Berechnung. 
 * <br><br>
 * 
 * Erklärung Formel und Schwellwerte siehe:
 * https://www.barmer.de/gesundheit-verstehen/leben/abnehmen-diaet/body-mass-index/bmi-rechner-1004244
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
        
        String interpretation = "";
        if      ( bmiGerundet < 18.5 ) { interpretation = "Untergewicht";  }         	        
        else if ( bmiGerundet < 25   ) { interpretation = "Normalgewicht"; }
        else { interpretation = "Übergewicht"; }
        
        final BmiErgebnisRecord ergebnis = 
					        		new BmiErgebnisRecord( true, 
					        		                       bmiGerundet, 
					        		                       interpretation );
        return ergebnis;
	}
	
}
