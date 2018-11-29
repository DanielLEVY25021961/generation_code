package levy.daniel.application.model.services.utilitaires.managerpaths;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * CLASSE ManagerPathsTest :<br/>
 * Test JUnit de la classe ManagerPaths.<br/>
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
 * @since 21 nov. 2018
 *
 */
public class ManagerPathsTest {

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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(ManagerPathsTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public ManagerPathsTest() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * teste la méthode getPathUniteCouranteString().<br/>
	 * <ul>
	 * <li>garantit que getPathUniteCouranteString() 
	 * retourne l'unité (disque) du projet Eclipse courant.</li>
	 * </ul>
	 * @throws IOException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetPathUniteCouranteString() throws IOException {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ManagerPathsTest - méthode testGetPathUniteCouranteString() ********** ");
		}
		
		final String uniteCourante = ManagerPaths.getPathUniteCouranteString();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Unité courante du présent projet : " + uniteCourante);
		}
		
		assertNotNull(
				"l'unité courante ne doit pas être null : "
					, uniteCourante);
				
	} // Fin de testGetPathUniteCouranteString().__________________________


		
	/**
	 * teste la méthode getPathPresentProjetString().<br/>
	 * <ul>
	 * <li>garantit que getPathPresentProjetString() 
	 * retourne le projet Eclipse courant.</li>
	 * </ul>
	 * @throws IOException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetPathPresentProjetString() throws IOException {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ManagerPathsTest - méthode testGetPathPresentProjetString() ********** ");
		}
		
		final String projetCourant = ManagerPaths.getPathPresentProjetString();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("présent projet : " + projetCourant);
		}
		
		assertNotNull(
				"present projet ne doit pas être null : "
					, projetCourant);
				
	} // Fin de testGetPathPresentProjetString().__________________________


	
	/**
	 * teste la méthode getPathPresentWorkspaceString().<br/>
	 * <ul>
	 * <li>garantit que getPathPresentWorkspaceString() 
	 * retourne le Workspace du projet Eclipse courant.</li>
	 * </ul>
	 * @throws IOException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testGetPathPresentWorkspaceString() throws IOException {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ManagerPathsTest - méthode testGetPathPresentWorkspaceString() ********** ");
		}
		
		final String workspaceCourant = ManagerPaths.getPathPresentWorkspaceString();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Workspace du présent projet : " + workspaceCourant);
		}
		
		assertNotNull(
				"Workspace du present projet ne doit pas être null : "
					, workspaceCourant);
				
	} // Fin de testGetPathPresentWorkspaceString()._______________________



} // FIN DE LA CLASSE ManagerPathsTest.--------------------------------------
