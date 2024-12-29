package de.eldecker.spring.bmirechner.rest;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.eldecker.spring.bmirechner.model.BmiErgebnisRecord;


@RestController
@RequestMapping( "/api/v1" )
public class BmiRestController {

	/**
	 * BMI-Wert berechnen
	 * <br><br>
	 * 
	 * URL bei lokalem Aufruf:
	 * <pre>
	 * http://localhost:8080/api/v1/bmi
	 * </pre>
	 * 
	 * @param kg URL-Parameter mit Gewicht in Kilogramm
	 * 
	 * @param cm URL-Parameter mit Größe in Zentimeter
	 * 
	 * @return Immer Status-Code 200 (OK), JSON mit Ergebnis
	 */
	@GetMapping( "/bmi" )
	public ResponseEntity<BmiErgebnisRecord> bmiBerechnung( @RequestParam("kg") int kg, 
			                                                @RequestParam("cm") int cm ) {
		
		final BmiErgebnisRecord bmi = new BmiErgebnisRecord( true, 30, "Normalgewicht" );
		
		return ResponseEntity.status( OK ).body( bmi );
	}
	
}
