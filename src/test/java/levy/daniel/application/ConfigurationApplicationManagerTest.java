package levy.daniel.application;

import static org.junit.Assert.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


/**
 * CLASSE ConfigurationApplicationManagerTest :<br/>
 * Test JUnnit de la classe ConfigurationApplicationManager.<br/>
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
 * @since 14 juil. 2018
 *
 */
public class ConfigurationApplicationManagerTest {
	
	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	/**
	 * "unused".
	 */
	public static final String UNUSED 
		= "unused";
	
	/**
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(ConfigurationApplicationManagerTest.class);


	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ConfigurationApplicationManagerTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * Teste la méthode getPathRessourcesExternes().<br/>
	 * <ul>
	 * <li>.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetPathRessourcesExternes() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ConfigurationApplicationManagerTest - méthode testGetPathRessourcesExternes() ********** ");
		}

		final String pathRessourcesExternesString = ConfigurationApplicationManager.getPathRessourcesExternes();
		
		final Path pathRessourcesExternes 
			= Paths.get(pathRessourcesExternesString);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("pathRessourcesExternesString : " 
					+ pathRessourcesExternesString);
		}
		
		assertNotNull(
				"Le Path des ressources externes (String) ne doit pas être null : "
					, pathRessourcesExternesString);
		
		assertNotNull(
				"Le Path des ressources externes ne doit pas être null : "
					, pathRessourcesExternes);
		
	} // Fin de testGetPathRessourcesExternes().___________________________

	
	
} // FIN DE LA CLASSE ConfigurationApplicationManagerTest.-------------------
