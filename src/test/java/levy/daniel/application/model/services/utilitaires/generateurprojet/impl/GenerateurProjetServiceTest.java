package levy.daniel.application.model.services.utilitaires.generateurprojet.impl;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.services.utilitaires.generateurprojet.IGenerateurProjetService;

/**
 * CLASSE GenerateurProjetServiceTest :<br/>
 * Test JUnit de la classe GenerateurProjetService.<br/>
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
 * @since 26 nov. 2018
 *
 */
public class GenerateurProjetServiceTest {

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
		= LogFactory.getLog(GenerateurProjetServiceTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public GenerateurProjetServiceTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________



	/**
	 * .<br/>
	 * <ul>
	 * <li></li>
	 * </ul>
	 * @throws IOException 
	 */
	@Test
	public void testGenerer() throws IOException {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE GenerateurProjetServiceTest - méthode testGenerer() ********** ");
		}
		
		final Path projetCiblePath = Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/depot_concepts");
		
		final IGenerateurProjetService generateur = new GenerateurProjetService();
		
		generateur.generer(projetCiblePath);
		
		assertTrue(BIDON, 1 == 1);
		
	} // Fin de testGenerer()._____________________________________________

	

} // FIN DE LA CLASSE GenerateurProjetServiceTest.---------------------------
