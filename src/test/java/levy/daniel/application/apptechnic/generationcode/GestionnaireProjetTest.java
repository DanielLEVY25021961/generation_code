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
	 * NOM_PROJET : String :<br/>
	 * "Nom du projet : ".<br/>
	 */
	public static final String NOM_PROJET 
		= "Nom du projet : ";

	
	/**
	 * PATH_PROJET : String :<br/>
	 * "Path du projet : ".<br/>
	 */
	public static final String PATH_PROJET 
		= "Path du projet : ";
	
	
	/**
	 * NOM_PROJET_NON_NULL : String :<br/>
	 * "Le nom du projet ne doit pas être null : ".<br/>
	 */
	public static final String NOM_PROJET_NON_NULL 
		= "Le nom du projet ne doit pas être null : ";

	
	/**
	 * PATH_PROJET_NON_NULL : String :<br/>
	 * "Le path du projet ne doit pas être null : ".<br/>
	 */
	public static final String PATH_PROJET_NON_NULL 
		= "Le path du projet ne doit pas être null : ";

	
	/**
	 * PROJET_DOIT_EXISTER : String :<br/>
	 * "Le projet visé par nomProjet doit exister : ".<br/>
	 */
	public static final String PROJET_DOIT_EXISTER 
		= "Le projet visé par nomProjet doit exister : ";

	
	/**
	 * PROJET_DOIT_REPERTOIRE : String :<br/>
	 * "Le projet visé par nomProjet doit être un répertoire : ".<br/>
	 */
	public static final String PROJET_DOIT_REPERTOIRE 
		= "Le projet visé par nomProjet doit être un répertoire : ";
	
	
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
	 * <li>Teste la méthode getPathWorkspaceString(null) :</li>
	 * <li>garantit que getPathWorkspaceString(null) 
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

		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();

		final String pathTest = null;
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(pathTest, null, null);
		
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
		
		/* garantit que getPathWorkspaceString(null) 
		 * fournit un path par défaut.*/
		assertNotNull(PATH_WORKSPACE_NON_NULL, pathString);
		assertNotNull(PATH_WORKSPACE_NON_NULL, path);
		assertTrue(WORKSPACE_DOIT_EXISTER, file.exists());
		assertTrue(WORKSPACE_DOIT_REPERTOIRE, file.isDirectory());
		
	} // Fin de testGetPathWorkspaceStringNull().__________________________
	
	
	
	/**
	 * method testGetPathWorkspaceStringInexistant() :<br/>
	 * <ul>
	 * <li>Teste la méthode getPathWorkspaceString(inexistant) :</li>
	 * <li>garantit que getPathWorkspaceString(inexistant) 
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

		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();

		final String pathTest = "D:/toto/tata/titi";
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(pathTest, null, null);
		
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
		
		/* garantit que getPathWorkspaceString(inexistant) 
		 * fournit un path par défaut.*/
		assertNotNull(PATH_WORKSPACE_NON_NULL, pathString);
		assertNotNull(PATH_WORKSPACE_NON_NULL, path);
		assertTrue(WORKSPACE_DOIT_EXISTER, file.exists());
		assertTrue(WORKSPACE_DOIT_REPERTOIRE, file.isDirectory());
		
	} // Fin de testGetPathWorkspaceStringInexistant.______________________
	
	
	
	/**
	 * method testGetPathWorkspaceString() :<br/>
	 * <ul>
	 * <li>Teste la méthode getPathWorkspaceString(pathTest) :</li>
	 * <li>garantit que getPathWorkspaceString(pathTest) 
	 * prend le path fourni.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathWorkspaceString() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest 
		= "D:/Donnees/eclipse/eclipseworkspace";
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(pathTest, null, null);
		
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
		
		/* garantit que getPathWorkspaceString(existant) 
		 * prend en compte le path.*/
		assertNotNull(PATH_WORKSPACE_NON_NULL, pathString);
		assertNotNull(PATH_WORKSPACE_NON_NULL, path);
		
		assertEquals("Le path pris en compte doit "
				+ "être le path fourni (existant) : "
					, pathTest, pathString);
		assertTrue(WORKSPACE_DOIT_EXISTER, file.exists());
		assertTrue(WORKSPACE_DOIT_REPERTOIRE, file.isDirectory());
		
	} // Fin de testGetPathWorkspaceString().______________________________

	
		
	/**
	 * method testGetNomProjetStringNull() :<br/>
	 * <ul>
	 * <li>Teste la méthode getNomProjetString(null) :</li>
	 * <li>garantit que getNomProjetString(null) 
	 * fournit un nom par défaut.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetNomProjetStringNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();

		final String pathTest = null;
		final String nomProjetTest = null;
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(pathTest, nomProjetTest, null);
		
		final String nomProjet 
			= GestionnaireProjet.getNomProjet();
		final String pathProjetString 
			= GestionnaireProjet.getPathProjetString();
		final Path pathProjet 
			= GestionnaireProjet.getPathProjet();		
		final File file 
			= GestionnaireProjet.getFileProjet();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetNomProjetStringNull()");
			System.out.println(NOM_PROJET + nomProjet);
			System.out.println(PATH_PROJET + pathProjetString);
			System.out.println(PATH_PROJET + pathProjet.toString());
		}
		
		/* garantit que getNomProjetString(null) 
		 * fournit un nom par défaut.*/
		assertNotNull(NOM_PROJET_NON_NULL, nomProjet);
		assertNotNull(PATH_PROJET_NON_NULL, pathProjetString);
		assertNotNull(PATH_PROJET_NON_NULL, pathProjet);
		assertTrue(PROJET_DOIT_EXISTER, file.exists());
		assertTrue(PROJET_DOIT_REPERTOIRE, file.isDirectory());
		
	} // Fin de testGetNomProjetStringNull().__________________________
	
	
	
	/**
	 * method testGetNomProjetStringInexistant() :<br/>
	 * <ul>
	 * <li>Teste la méthode getNomProjetString(inexistant) :</li>
	 * <li>garantit que getNomProjetString(inexistant) 
	 * fournit un nom par défaut.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetNomProjetStringInexistant() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = "toto_pouet_pouet";
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(pathTest, nomProjetTest, null);
		
		final String nomProjet 
			= GestionnaireProjet.getNomProjet();
		final String pathProjetString 
			= GestionnaireProjet.getPathProjetString();
		final Path pathProjet 
			= GestionnaireProjet.getPathProjet();		
		final File file 
			= GestionnaireProjet.getFileProjet();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetNomProjetStringInexistant()");
			System.out.println(NOM_PROJET + nomProjet);
			System.out.println(PATH_PROJET + pathProjetString);
			System.out.println(PATH_PROJET + pathProjet.toString());
		}
		
		/* garantit que getNomProjetString(inexistant) 
		 * fournit un nom par défaut.*/
		assertNotNull(NOM_PROJET_NON_NULL, nomProjet);
		assertNotNull(PATH_PROJET_NON_NULL, pathProjetString);
		assertNotNull(PATH_PROJET_NON_NULL, pathProjet);
		assertTrue(PROJET_DOIT_EXISTER, file.exists());
		assertTrue(PROJET_DOIT_REPERTOIRE, file.isDirectory());
		
	} // Fin de testGetNomProjetStringInexistant.______________________
	
	
	
	/**
	 * method testGetNomProjetString() :<br/>
	 * <ul>
	 * <li>Teste la méthode getNomProjetString(pathTest) :</li>
	 * <li>garantit que getNomProjetString(projet existant) 
	 * prend le nom fourni.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetNomProjetString() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = "generation_code_test";
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(pathTest, nomProjetTest, null);
		
		final String nomProjet 
			= GestionnaireProjet.getNomProjet();
		final String pathProjetString 
			= GestionnaireProjet.getPathProjetString();
		final Path pathProjet 
			= GestionnaireProjet.getPathProjet();		
		final File file 
			= GestionnaireProjet.getFileProjet();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetNomProjetString()");
			System.out.println(NOM_PROJET + nomProjet);
			System.out.println(PATH_PROJET + pathProjetString);
			System.out.println(PATH_PROJET + pathProjet.toString());
		}
		
		/* garantit que getNomProjetString(existant) 
		 * prend le nom fourni.*/
		assertNotNull(NOM_PROJET_NON_NULL, nomProjet);
		
		assertEquals("nomProjet doit valoir nomProjetTest : "
				, nomProjetTest, nomProjet);
		
		assertNotNull(PATH_PROJET_NON_NULL, pathProjetString);
		assertNotNull(PATH_PROJET_NON_NULL, pathProjet);
		assertTrue(PROJET_DOIT_EXISTER, file.exists());
		assertTrue(PROJET_DOIT_REPERTOIRE, file.isDirectory());
		
	} // Fin de testGetNomProjetString().______________________________



	/**
	 * method testGetNomRepertoireSrcString() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetNomRepertoireSrcString() throws Exception {
						
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = "generation_code_test";
		final String nomRepertoireSrcTest = null;
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest, nomProjetTest, nomRepertoireSrcTest);

		final String nomRepertoireSrc 
			= GestionnaireProjet.getNomRepertoireSrc();
		final String pathRepertoireSrcString 
			= GestionnaireProjet.getPathRepertoireSrcString();
		final Path pathRepertoireSrc 
			= GestionnaireProjet.getPathRepertoireSrc();		
		final File file 
			= GestionnaireProjet.getFileRepertoireSrc();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetNomRepertoireSrcString()");
			System.out.println(NOM_PROJET + nomRepertoireSrc);
			System.out.println(PATH_PROJET + pathRepertoireSrcString);
			System.out.println(PATH_PROJET + pathRepertoireSrc.toString());
		}

		
	} // Fin de testGetNomRepertoireSrcString().___________________________
	
	
	
} // FIN DE LA CLASSE GestionnaireProjetTest.--------------------------------
