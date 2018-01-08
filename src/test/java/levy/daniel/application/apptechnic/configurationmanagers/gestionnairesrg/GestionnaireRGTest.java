package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg;

import static org.junit.Assert.assertNotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


/**
 * class GestionnaireRGTest :<br/>
 * Test JUnit de la Classe GestionnaireRG.<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 12 oct. 2017
 *
 */
public class GestionnaireRGTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * AFFICHAGE_GENERAL : Boolean :<br/>
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(GestionnaireRGTest.class);


	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR GestionnaireRGTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public GestionnaireRGTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * method testRG() :<br/>
	 * Vérifie que le GestionnaireRG publie la liste des RG.<br/>
	 * <br/>
	 */
	@Test
	public void testRG() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(
					GestionnaireRG.afficherListeRGImplementeesCsv());
		}
				
		assertNotNull(
				"La Map des RG ne doit pas être null : "
						, GestionnaireRG.getMapRG());
		
	} // Fin de testRG().__________________________________________________
	

	
} // FIN DE LA CLASSE GestionnaireRGTest.------------------------------------
