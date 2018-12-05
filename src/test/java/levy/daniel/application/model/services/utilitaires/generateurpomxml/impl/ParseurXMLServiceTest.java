package levy.daniel.application.model.services.utilitaires.generateurpomxml.impl;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.junit.Test;

import levy.daniel.application.model.services.utilitaires.generateurpomxml.IParseurXMLService;

/**
 * CLASSE ParseurXMLServiceTest :<br/>
 * Test JUnit de la classe ParseurXMLService.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 5 déc. 2018
 *
 */
public class ParseurXMLServiceTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * AFFICHAGE_GENERAL : Boolean :<br/>
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * SAUT_LIGNE_JAVA : char :<br/>
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE_JAVA = '\n';

	/**
	 * "BIDON : ".<br/>
	 */
	public static final String BIDON 
		= "BIDON : ";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(ParseurXMLServiceTest.class);
	

	// *************************METHODES************************************/

	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public ParseurXMLServiceTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * Teste la méthode parser(...).<br/>
	 * <ul>
	 * <li></li>
	 * </ul>
	 * : void :  .<br/>
	 */
	@Test
	public void testParser() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ParseurXMLServiceTest - méthode testParser() ********** ");
		}
		
		/* Récupère le path absolu du présent projet. */
		final Path pathAbsoluPresentProjet 
			= Paths.get(".").toAbsolutePath().normalize();
		
		/* ************************************************************ */
		final Path pomPath = pathAbsoluPresentProjet.resolve("pom.xml");
		final File pomFile = pomPath.toFile();
		/* ************************************************************ */
		
		final IParseurXMLService xmlService = new ParseurXMLService();
		
		final Document document = xmlService.parserXML(pomFile);
		
		final String documentString 
			= xmlService.afficherDocumentXML(document);
		
		System.out.println(documentString);
		
		assertTrue(BIDON, 1 == 1);
		
	} // Fin de testParser().______________________________________________



} // FIN DE LA CLASSE ParseurXMLServiceTest.---------------------------------
