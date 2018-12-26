package levy.daniel.application.model.services.utilitaires.copieurconcept.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.services.utilitaires.copieurconcept.ICopieurConceptService;

/**
 * CLASSE CopieurConceptServiceTest :<br/>
 * Test JUnit de la classe CopieurConceptService.<br/>
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
 * @since 13 déc. 2018
 *
 */
public class CopieurConceptServiceTest {

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
		= LogFactory.getLog(CopieurConceptServiceTest.class);

	
	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public CopieurConceptServiceTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * .<br/>
	 * <ul>
	 * <li></li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testCopierConcept() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE CopieurConceptServiceTest - méthode testCopierConcept() ********** ");
		}

		// *****************************************************************
		final Path projetSourcePath 
			= Paths.get(".").toAbsolutePath().normalize();
		final Path projetCiblePath 
			= Paths.get("D:/Donnees/eclipse/eclipseworkspace/depot_concepts");
		final String nomConcept = "developpeur";
		final ICopieurConceptService copieur = new CopieurConceptService();
		// *****************************************************************
		
		copieur.copierConcept(projetSourcePath, projetCiblePath, nomConcept);
		
		assertTrue(BIDON, 1 == 1);
		
	} // Fin de testCopierConcept()._______________________________________

	
	
} // FIN DE LA CLASSE CopieurConceptServiceTest.-----------------------------
