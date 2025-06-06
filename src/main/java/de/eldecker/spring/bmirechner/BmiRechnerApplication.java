package de.eldecker.spring.bmirechner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Klasse mit Einstiegsmethode.
 */
@SpringBootApplication
public class BmiRechnerApplication {

	
	/**
	 * Einstiegsmethode 
	 * 
	 * @param args Kommandozeilenargumente werden an Spring Boot
	 *             durchgereicht.
	 */
	public static void main( String[] args ) {
		
		SpringApplication.run( BmiRechnerApplication.class, args );
	}

}
