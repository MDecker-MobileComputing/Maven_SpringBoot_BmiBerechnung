package de.eldecker.spring.bmirechner.rest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.eldecker.spring.bmirechner.logik.BmiRechner;
import de.eldecker.spring.bmirechner.logik.NutzungsZaehler;
import de.eldecker.spring.bmirechner.model.BmiErgebnisRecord;
import de.eldecker.spring.bmirechner.model.ParameterUngueltigException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * RestController-Klasse mit REST-Endpunkt für Berechnung von BMI.
 */
@RestController
@RequestMapping( "/api/v1" )
public class BmiRestController {

    private static final Logger LOG = LoggerFactory.getLogger( BmiRestController.class );
    
    /** Service-Bean für eigentliche Berechnung. */
    private BmiRechner _bmiRechner;
    
    /** Session-Bean */
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
     * Globaler Exception-Handler für ungültige Parameterwerte.
     * 
     * @param ex Exception wegen ungültigem Parameter als Input für Berechnung
     * 
     * @return String mit Fehlerbeschreibung
     */
    @ExceptionHandler( ParameterUngueltigException.class )
    public ResponseEntity<String> exceptionBehandeln( ParameterUngueltigException ex ) {

        final String fehlerText = "Fehler bei Suchanfrage: " + ex.getMessage(); 
        LOG.error( fehlerText );
        
        return new ResponseEntity<>( fehlerText, BAD_REQUEST );
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
     * @param kg URL-Parameter mit Körpergewicht in Kilogramm, muss zwischen 6 und 592 liegen
     * 
     * @param cm URL-Parameter mit Körpergröße in Zentimeter, muss zwischen 63 und 251 liegen
     * 
     * @return Immer Status-Code 200 (OK), JSON mit Ergebnis
     * 
     * @throws ParameterUngueltigException Wenn einer der beiden Parameter einen Wert außerhalb 
     *                                     dem gültigen Bereich hat.                                     
     */
    @GetMapping( "/bmi" )
    public ResponseEntity<BmiErgebnisRecord> bmiBerechnung( @RequestParam("kg") int kg, 
                                                            @RequestParam("cm") int cm ) 
                                                    throws ParameterUngueltigException {
        
        final int nutzungsZaehler = _nutzungsZaehler.erhoeheBerechnungsZaehler();
        if ( nutzungsZaehler > 3 ) {
            
            LOG.warn( "Request wegen ausgeschoepften Nutzungs-Quota abgebrochen." );
            
            final BmiErgebnisRecord erg = 
                    new BmiErgebnisRecord( false, 0, "Nutzungs-Quota ausgeschöpft" );
            
            return ResponseEntity.status( OK ).body( erg );
        }
        
        
        if ( kg <   6 ) { throw new ParameterUngueltigException( "Wert kg=" + kg + " zu klein" ); }
        if ( kg > 592 ) { throw new ParameterUngueltigException( "Wert kg=" + kg + " zu groß"  ); }

        if ( cm <  63 ) { throw new ParameterUngueltigException( "Wert cm=" + cm + " zu klein" ); }
        if ( cm > 251 ) { throw new ParameterUngueltigException( "Wert cm=" + cm + " zu groß"  ); } 
        
        
        final BmiErgebnisRecord bmi = _bmiRechner.bmiBerechnen( cm, kg );
        
        return ResponseEntity.status( OK ).body( bmi );
    }
    
}
