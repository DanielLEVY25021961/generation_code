package levy.daniel.application.apptechnic.configurationmanagers;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


/**
 * class ManagerPathsTest :<br/>
 * .<br/>
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
 * @since 27 janv. 2018
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
	 * PATH : String :<br/>
	 * "PATH : ".<br/>
	 */
	public static final String PATH = "PATH : ";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(ManagerPathsTest.class);

	// *************************METHODES************************************/


	
	 /**
	 * method CONSTRUCTEUR ManagerPathsTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public ManagerPathsTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method testGetPathUniteCouranteString() :<br/>
	 * <ul>
	 * <li>Teste la méthode getPathUniteCouranteString().</li>
	 * <li>garantit que getPathUniteCouranteString() 
	 * ne retourne pas null.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@Test
	public void testGetPathUniteCouranteString() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		final String path 
			= ManagerPaths.getPathUniteCouranteString();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetPathUniteCouranteString() : ");
			System.out.println(PATH + path);
		}
		
		/* garantit que getPathUniteCouranteString() 
		 * ne retourne pas null. */
		assertNotNull("Le path de l'unite ne doit pas être null : "
				, path);
		
	} // Fin de testGetPathUniteCouranteString().__________________________

	
	
	/**
	 * method testGetPathUniteCourante() :<br/>
	 * <ul>
	 * <li>Teste la méthode getPathUniteCourante().</li>
	 * <li>garantit que getPathUniteCourante() 
	 * ne retourne pas null.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@Test
	public void testGetPathUniteCourante() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		final Path path 
			= ManagerPaths.getPathUniteCourante();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetPathUniteCourante() : ");
			System.out.println(PATH + path.toString());
		}
		
		/* garantit que getPathUniteCourante() 
		 * ne retourne pas null. */
		assertNotNull("Le path de l'unité ne doit pas être null : "
				, path);
		
	} // Fin de testGetPathUniteCourante().__________________________


	
	/**
	 * method testGetPathPresentWorkspaceString() :<br/>
	 * <ul>
	 * <li>Teste la méthode getPathPresentWorkspaceString().</li>
	 * <li>garantit que getPathPresentWorkspaceString() 
	 * ne retourne pas null.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@Test
	public void testGetPathPresentWorkspaceString() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		final String path 
			= ManagerPaths.getPathPresentWorkspaceString();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetPathPresentWorkspaceString() : ");
			System.out.println(PATH + path);
		}
		
		/* garantit que getPathPresentWorkspaceString() 
		 * ne retourne pas null. */
		assertNotNull("Le path du workspace ne doit pas être null : "
				, path);
		
	} // Fin de testGetPathPresentWorkspaceString().__________________________

	
	
	/**
	 * method testGetPathPresentWorkspace() :<br/>
	 * <ul>
	 * <li>Teste la méthode getPathPresentWorkspace().</li>
	 * <li>garantit que getPathPresentWorkspace() 
	 * ne retourne pas null.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@Test
	public void testGetPathPresentWorkspace() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		final Path path 
			= ManagerPaths.getPathPresentWorkspace();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetPathPresentWorkspace() : ");
			System.out.println(PATH + path.toString());
		}
		
		/* garantit que getPathPresentWorkspace() 
		 * ne retourne pas null. */
		assertNotNull("Le path du workspace ne doit pas être null : "
				, path);
		
	} // Fin de testGetPathPresentWorkspace().__________________________


	
	/**
	 * method testGetPathPresentProjetString() :<br/>
	 * <ul>
	 * <li>Teste la méthode getPathPresentProjetString().</li>
	 * <li>garantit que getPathPresentProjetString() 
	 * ne retourne pas null.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@Test
	public void testGetPathPresentProjetString() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		final String path 
			= ManagerPaths.getPathPresentProjetString();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetPathPresentProjetString() : ");
			System.out.println(PATH + path);
		}
		
		/* garantit que getPathPresentProjetString() 
		 * ne retourne pas null. */
		assertNotNull("Le path du projet ne doit pas être null : "
				, path);
		
	} // Fin de testGetPathPresentProjetString().__________________________

	
	
	/**
	 * method testGetPathPresentProjet() :<br/>
	 * <ul>
	 * <li>Teste la méthode getPathPresentProjet().</li>
	 * <li>garantit que getPathPresentProjet() 
	 * ne retourne pas null.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	@Test
	public void testGetPathPresentProjet() throws IOException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		final Path path 
			= ManagerPaths.getPathPresentProjet();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetPathPresentProjet() : ");
			System.out.println(PATH + path.toString());
		}
		
		/* garantit que getPathPresentProjet() 
		 * ne retourne pas null. */
		assertNotNull("Le path du projet ne doit pas être null : "
				, path);
		
	} // Fin de testGetPathPresentProjet().__________________________

	

} // FIN DE LA CLASSE ManagerPathsTest.--------------------------------------
