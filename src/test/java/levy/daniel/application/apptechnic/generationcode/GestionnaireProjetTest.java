package levy.daniel.application.apptechnic.generationcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * class GestionnaireProjetTest :<br/>
 * Test JUnit de la classe GestionnaireProjet.<br/>
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
 * @since 30 janv. 2018
 *
 */
public class GestionnaireProjetTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * AFFICHAGE_GENERAL : Boolean :<br/>
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;


	/**
	 * CHEMIN_WORKSPACE : String :<br/>
	 * "CHEMIN DU WORKSPACE : ".<br/>
	 */
	public static final String CHEMIN_WORKSPACE 
		= "CHEMIN DU WORKSPACE : ";
	
	/**
	 * PATH_WORKSPACE : String :<br/>
	 * "PATH DU WORKSPACE : ".<br/>
	 */
	public static final String PATH_WORKSPACE 
		= "PATH DU WORKSPACE : ";
	
	/**
	 * PATH_WORKSPACE_NON_NULL : String :<br/>
	 * "le path du Workspace ne doit pas être null : ".<br/>
	 */
	public static final String PATH_WORKSPACE_NON_NULL 
		= "le path du Workspace ne doit pas être null : ";
	
	/**
	 * WORKSPACE_DOIT_EXISTER : String :<br/>
	 * "Le Workspace doit exister : ".<br/>
	 */
	public static final String WORKSPACE_DOIT_EXISTER 
		= "Le Workspace doit exister : ";
	
	/**
	 * WORKSPACE_DOIT_REPERTOIRE : String :<br/>
	 * "Le Workspace doit être un répertoire : ".<br/>
	 */
	public static final String WORKSPACE_DOIT_REPERTOIRE 
		= "Le Workspace doit être un répertoire : ";
	
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GestionnaireProjetTest.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR GestionnaireProjetTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public GestionnaireProjetTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method testGetPathWorkspaceStringNull() :<br/>
	 * <ul>
	 * <li>Teste la méthode alimenterPathWorkspaceString(null) :</li>
	 * <li>garantit que alimenterPathWorkspaceString(null) 
	 * fournit un path par défaut.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathWorkspaceStringNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		final String pathTest = null;
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet.alimenterAttributs(pathTest);
		
		final String pathString 
			= GestionnaireProjet.getPathWorkspaceString();
		final Path path 
			= GestionnaireProjet.getPathWorkspace();
		final File file 
			= GestionnaireProjet.getFileWorkspace();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetPathWorkspaceStringNull()");
			System.out.println(CHEMIN_WORKSPACE + pathString);
			System.out.println(PATH_WORKSPACE + path.toString());
		}
		
		/* garantit que alimenterPathWorkspaceString(null) 
		 * fournit un path par défaut.*/
		assertNotNull(PATH_WORKSPACE_NON_NULL, pathString);
		assertNotNull(PATH_WORKSPACE_NON_NULL, path);
		assertTrue(WORKSPACE_DOIT_EXISTER, file.exists());
		assertTrue(WORKSPACE_DOIT_REPERTOIRE, file.isDirectory());
		
	} // Fin de testGetPathWorkspaceStringNull().__________________________
	
	
	
	/**
	 * method testGetPathWorkspaceStringInexistant() :<br/>
	 * <ul>
	 * <li>Teste la méthode alimenterPathWorkspaceString(inexistant) :</li>
	 * <li>garantit que alimenterPathWorkspaceString(inexistant) 
	 * fournit un path par défaut.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathWorkspaceStringInexistant() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		final String pathTest = "D:/toto/tata/titi";
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet.alimenterAttributs(pathTest);
		
		final String pathString 
			= GestionnaireProjet.getPathWorkspaceString();
		final Path path 
			= GestionnaireProjet.getPathWorkspace();
		final File file 
			= GestionnaireProjet.getFileWorkspace();
		
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetPathWorkspaceStringInexistant()");
			System.out.println(CHEMIN_WORKSPACE + pathString);
			System.out.println(PATH_WORKSPACE + path.toString());
		}
		
		/* garantit que alimenterPathWorkspaceString(inexistant) 
		 * fournit un path par défaut.*/
		assertNotNull(PATH_WORKSPACE_NON_NULL, pathString);
		assertNotNull(PATH_WORKSPACE_NON_NULL, path);
		assertTrue(WORKSPACE_DOIT_EXISTER, file.exists());
		assertTrue(WORKSPACE_DOIT_REPERTOIRE, file.isDirectory());
		
	} // Fin de testGetPathWorkspaceStringInexistant.______________________
	
	
	
	/**
	 * method testGetPathWorkspaceString() :<br/>
	 * <ul>
	 * <li>Teste la méthode alimenterPathWorkspaceString(pathTest) :</li>
	 * <li>garantit que alimenterPathWorkspaceString(pathTest) 
	 * prend le path fourni.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathWorkspaceString() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************

		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest 
		= "D:/Donnees/eclipse/eclipseworkspace";
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet.alimenterAttributs(pathTest);
		
		final String pathString 
			= GestionnaireProjet.getPathWorkspaceString();
		final Path path 
			= GestionnaireProjet.getPathWorkspace();
		final File file 
		= GestionnaireProjet.getFileWorkspace();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetPathWorkspaceString()");
			System.out.println(CHEMIN_WORKSPACE + pathString);
			System.out.println(PATH_WORKSPACE + path.toString());
		}
		
		/* garantit que alimenterPathWorkspaceString(existant) 
		 * prend en compte le path.*/
		assertNotNull(PATH_WORKSPACE_NON_NULL, pathString);
		assertNotNull(PATH_WORKSPACE_NON_NULL, path);
		
		assertEquals("Le path pris en compte doit "
				+ "être le path fourni (existant) : "
					, pathTest, pathString);
		assertTrue(WORKSPACE_DOIT_EXISTER, file.exists());
		assertTrue(WORKSPACE_DOIT_REPERTOIRE, file.isDirectory());
		
	} // Fin de testGetPathWorkspaceString().______________________________

	

} // FIN DE LA CLASSE GestionnaireProjetTest.--------------------------------
