package de.eldecker.spring.bmirechner.model;


/**
 * Ergebnis-Record für BMI-Berechnung, wird von REST-Endpunkt
 * nach JSON serialisiert und an Client geschickt.
 * 
 * @param erfolg {@code true} gdw. die BMI-Berechnung erfolgreich war;
 *               in {@code bmi} steht dann der berechnete Wert, in 
 *               {@code nachricht} die Interpretation (z.B. "Normalgewicht");
 *               wenn nicht erfolgreich, dann steht in {@code nachricht}
 *               eine Fehlermeldung
 *               
 * @param bmi BMI-Wert (Body Mass Index); darf nur ausgewertet werden, wenn
 *            {@code erfolg=true}
 *            
 * @param nachricht Bei {@code erfolg=true} Interpretation von {@code bmi},
 *                  sonst Fehlermeldung.            
 */
public record BmiErgebnisRecord( boolean erfolg,
		                         int bmi,
		                         String nachricht
		                       ) {

}
