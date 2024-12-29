package de.eldecker.spring.bmirechner.rest;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.eldecker.spring.bmirechner.logik.BmiRechner;
import de.eldecker.spring.bmirechner.logik.NutzungsZaehler;
import de.eldecker.spring.bmirechner.model.BmiErgebnisRecord;
import de.eldecker.spring.bmirechner.model.ParameterUngueltigException;


/**
 * RestController-Klasse mit REST-Endpunkt für Berechnung von BMI.
 */
@RestController
@RequestMapping( "/api/v1" )
public class BmiRestController {

	/** Service-Bean für eigentliche Berechnung. */
	private BmiRechner _bmiRechner;
	
	private NutzungsZaehler _nutzungsZaehler;
	
	/**
	 * Konstruktor für Dependency Injection.
	 */
	@Autowired
	public BmiRestController( BmiRechner bmiRechner,
			                  NutzungsZaehler nutzungsZaehler ) {
	
		_bmiRechner      = bmiRechner;
		_nutzungsZaehler = nutzungsZaehler;
	}

	
	/**
	 * Body Mass Index (BMI) berechnen.
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
	 * 
	 * @throws ParameterUngueltigException Wenn einer der beiden Parameter
	 *                                     einen Wert außerhalb dem gültigen
	 *                                     Bereich hat.
	 */
	@GetMapping( "/bmi" )
	public ResponseEntity<BmiErgebnisRecord> bmiBerechnung( @RequestParam("kg") int kg, 
			                                                @RequestParam("cm") int cm ) 
			                                        throws ParameterUngueltigException {
		
		final int nutzungsZaehler = _nutzungsZaehler.erhoeheBerechnungsZaehler();
		if ( nutzungsZaehler > 3 ) {
			
			final BmiErgebnisRecord erg = new BmiErgebnisRecord( false, 0, "Nutzungs-Quota ausgeschöpft" );
			return ResponseEntity.status( OK ).body( erg );
		}
		
		
		if ( kg < 30 ) {
			
			throw new ParameterUngueltigException( "Wert kg=" + kg + " zu klein" );
		}
		if ( kg > 500 ) {
			
			throw new ParameterUngueltigException( "Wert kg=" + kg + " zu groß" );
		}

		if ( cm < 100 ) {
			
			throw new ParameterUngueltigException( "Wert cm=" + kg + " zu klein" );
		}
		if ( cm > 250 ) {
			
			throw new ParameterUngueltigException( "Wert cm=" + kg + " zu groß" );
		}
		
		
		final BmiErgebnisRecord bmi = _bmiRechner.bmiBerechnen( cm, kg );
		
		return ResponseEntity.status( OK ).body( bmi );
	}
	
}
