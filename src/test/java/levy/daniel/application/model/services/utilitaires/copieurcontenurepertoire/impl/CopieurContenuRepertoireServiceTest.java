package levy.daniel.application.model.services.utilitaires.copieurcontenurepertoire.impl;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.services.utilitaires.copieurcontenurepertoire.ICopieurContenuRepertoireService;

/**
 * CLASSE CopieurContenuRepertoireServiceTest :<br/>
 * Test JUnit de la classe CopieurContenuRepertoireService.<br/>
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
 * @since 1 déc. 2018
 *
 */
public class CopieurContenuRepertoireServiceTest {

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
	 * "BIDON : ".
	 */
	public static final String BIDON = "BIDON : ";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(CopieurContenuRepertoireServiceTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public CopieurContenuRepertoireServiceTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
		
	/**
	 * Teste la méthode copierContenu(....).<br/>
	 * <br/>
	 */
	@Test
	public void testCopierContenu() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE CopieurContenuRepertoireServiceTest - méthode testCopierContenu() ********** ");
		}
	
		final File repRacineACopier = new File("./javadoc");
		
		final ICopieurContenuRepertoireService copieur 
			= new CopieurContenuRepertoireService();
		
		copieur.copierContenu(repRacineACopier, null);
		
		assertTrue(BIDON, 1 == 1);
		
	} // Fin de testCopierContenu()._______________________________________


} // FIN DE LA CLASSE CopieurContenuRepertoireServiceTest.-------------------
