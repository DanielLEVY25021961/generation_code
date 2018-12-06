package levy.daniel.application.model.services.metier.generationcode;

import static org.junit.Assert.*;

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
	 * PATH_RELATIF : String :<br/>
	 * "PATH RELATIF : ".<br/>
	 */
	public static final String PATH_RELATIF 
		= "PATH RELATIF : ";

	
	/**
	 * PATH_ABSOLU : String :<br/>
	 * "PATH ABSOLU : ".<br/>
	 */
	public static final String PATH_ABSOLU 
		= "PATH ABSOLU : ";

	
	/**
	 * FILE : String :<br/>
	 * "FILE : ".<br/>
	 */
	public static final String FILE 
		= "FILE : ";

	
	/**
	 * PATH_RELATIF_NON_NULL : String :<br/>
	 * "Le path relatif ne doit pas être null : ".<br/>
	 */
	public static final String PATH_RELATIF_NON_NULL 
		= "Le path relatif ne doit pas être null : ";

	
	/**
	 * PATH_NON_NULL : String :<br/>
	 * "Le Path ne doit pas être null : ".<br/>
	 */
	public static final String PATH_NON_NULL 
		= "Le Path ne doit pas être null : ";

	
	/**
	 * REP_NON_NULL : String :<br/>
	 * "Le répertoire ne doit pas être null : ".<br/>
	 */
	public static final String REP_NON_NULL 
		= "Le répertoire ne doit pas être null : ";

	
	/**
	 * REP_EXISTE : String :<br/>
	 * "Le répertoire doit exister : ".<br/>
	 */
	public static final String REP_EXISTE 
		= "Le répertoire doit exister : ";
	
	
	/**
	 * REP_DIRECTORY : String :<br/>
	 * "Le répertoire doit être une Directory : ".<br/>
	 */
	public static final String REP_DIRECTORY 
		= "Le répertoire doit être une Directory : ";

	
	/**
	 * PROJET_GENERATION_CODE_TEST : String :<br/>
	 * "generation_code_test".<br/>
	 */
	public static final String PROJET_GENERATION_CODE_TEST 
		= "generation_code_test";
	
	
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
			.alimenterAttributs(
					pathTest
						, null
							, null
								, null
									, null
										, null
											, null
												, null);
		
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
		.alimenterAttributs(
				pathTest
					, null
						, null
							, null
								, null
									, null
										, null
											, null);
		
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
		.alimenterAttributs(
				pathTest
					, null
						, null
							, null
								, null
									, null
										, null
											, null);
		
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
			.alimenterAttributs(
					pathTest
						, nomProjetTest
							, null
								, null
									, null
										, null
											, null
												, null);
		
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
			.alimenterAttributs(
					pathTest
						, nomProjetTest
							, null
								, null
									, null
										, null
											, null
												, null);

		
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
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
						, nomProjetTest
							, null
								, null
									, null
										, null
											, null
												, null);

		
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
	 * method testGetPathRepertoireSrc() :<br/>
	 * <ul>
	 * <li>Teste la méthode getPathRepertoireSrc(src) :</li>
	 * <li>garantit que getPathRepertoireSrc(src) 
	 * prend le nom fourni.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathRepertoireSrc() throws Exception {
						
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
						, nomProjetTest
							, nomRepertoireSrcTest
								, null
									, null
										, null
											, null
												, null);

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

		/* garantit que getPathRepertoireSrc(existant) 
		 * prend le nom fourni.*/
		assertNotNull("Le répertoire src doit être non null : ", nomRepertoireSrc);
		
		assertEquals("Le répertoire src doit être src : "
				, "src", nomRepertoireSrc);
		
		assertNotNull("Le pathString du repertoire "
				+ "src doit être non null : "
					, pathRepertoireSrcString);
		assertNotNull("Le path du répertoire src doit être non null : "
				, pathRepertoireSrc);
		assertTrue("le répertoire src doit exister : "
				, file.exists());
		assertTrue("le répertoire src doit être un Directory : "
				, file.isDirectory());
		
	} // Fin de testGetPathRepertoireSrc().________________________________
	

	
	/**
	 * method testMainJavaNull() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives à pathMainJava.</li>
	 * <li>test avec un paramètre null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testMainJavaNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = null;

		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, null
								, null
									, null
										, null);

		final String pathRel 
			= GestionnaireProjet.getPathRelMainJava();
		final String pathString 
			= GestionnaireProjet.getPathMainJavaString();
		final Path path 
			= GestionnaireProjet.getPathMainJava();		
		final File file 
			= GestionnaireProjet.getFileMainJava();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testMainJavaNull()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathString);
			System.out.println(PATH_ABSOLU + path.toString());
			System.out.println(FILE + file.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathString);
		assertNotNull(PATH_NON_NULL, path);
		assertNotNull(REP_NON_NULL, file);
		assertTrue(REP_EXISTE, file.exists());
		assertTrue(REP_DIRECTORY, file.isDirectory());
		
	} // Fin de testMainJavaNull().________________________________________
	

	
	/**
	 * method testMainJavaInexistant() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives à pathMainJava.</li>
	 * <li>test avec un paramètre pointant sur un 
	 * repertoire inexistant.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testMainJavaInexistant() throws Exception {
						
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = "maintest/javatest";

		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, null
								, null
									, null
										, null);

		final String pathRel 
		= GestionnaireProjet.getPathRelMainJava();
		final String pathString 
			= GestionnaireProjet.getPathMainJavaString();
		final Path path 
			= GestionnaireProjet.getPathMainJava();		
		final File file 
			= GestionnaireProjet.getFileMainJava();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testMainJavaInexistant()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathString);
			System.out.println(PATH_ABSOLU + path.toString());
			System.out.println(FILE + file.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathString);
		assertNotNull(PATH_NON_NULL, path);
		assertNotNull(REP_NON_NULL, file);
		assertTrue(REP_EXISTE, file.exists());
		assertTrue(REP_DIRECTORY, file.isDirectory());
	
	} // Fin de testMainJavaInexistant().__________________________________

	
		
	/**
	 * method testMainJava() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives à pathMainJava.</li>
	 * <li>test avec un paramètre pointant sur un repertoire existant.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testMainJava() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = "main/java";
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, null
								, null
									, null
										, null);
		
		final String pathRel 
		= GestionnaireProjet.getPathRelMainJava();
		final String pathString 
			= GestionnaireProjet.getPathMainJavaString();
		final Path path 
			= GestionnaireProjet.getPathMainJava();		
		final File file 
			= GestionnaireProjet.getFileMainJava();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testMainJava()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathString);
			System.out.println(PATH_ABSOLU + path.toString());
			System.out.println(FILE + file.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathString);
		assertNotNull(PATH_NON_NULL, path);
		assertNotNull(REP_NON_NULL, file);
		assertTrue(REP_EXISTE, file.exists());
		assertTrue(REP_DIRECTORY, file.isDirectory());
	
	} // Fin de testMainJava().____________________________________________


	
	/**
	 * method testMainResourcesNull() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives à pathMainResources.</li>
	 * <li>test avec un paramètre null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testMainResourcesNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = null;
		final String pathRelMainResourcesTest = null;

		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, pathRelMainResourcesTest
								, null
									, null
										, null);

		final String pathRel 
			= GestionnaireProjet.getPathRelMainResources();
		final String pathString 
			= GestionnaireProjet.getPathMainResourcesString();
		final Path path 
			= GestionnaireProjet.getPathMainResources();		
		final File file 
			= GestionnaireProjet.getFileMainResources();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testMainResourcesNull()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathString);
			System.out.println(PATH_ABSOLU + path.toString());
			System.out.println(FILE + file.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathString);
		assertNotNull(PATH_NON_NULL, path);
		assertNotNull(REP_NON_NULL, file);
		assertTrue(REP_EXISTE, file.exists());
		assertTrue(REP_DIRECTORY, file.isDirectory());
		
	} // Fin de testMainResourcesNull().___________________________________
	

	
	/**
	 * method testMainResourcesInexistant() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives 
	 * à pathMainResources.</li>
	 * <li>test avec un paramètre pointant sur un 
	 * repertoire inexistant.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testMainResourcesInexistant() throws Exception {
						
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = null;
		final String pathRelMainResourcesTest = "maintest/resourcestest";

		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, pathRelMainResourcesTest
								, null
									, null
										, null);

		final String pathRel 
		= GestionnaireProjet.getPathRelMainResources();
		final String pathString 
			= GestionnaireProjet.getPathMainResourcesString();
		final Path path 
			= GestionnaireProjet.getPathMainResources();		
		final File file 
			= GestionnaireProjet.getFileMainResources();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testMainResourcesInexistant()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathString);
			System.out.println(PATH_ABSOLU + path.toString());
			System.out.println(FILE + file.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathString);
		assertNotNull(PATH_NON_NULL, path);
		assertNotNull(REP_NON_NULL, file);
		assertTrue(REP_EXISTE, file.exists());
		assertTrue(REP_DIRECTORY, file.isDirectory());
	
	} // Fin de testMainResourcesInexistant().__________________________________

	
		
	/**
	 * method testMainResources() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives à pathMainResources.</li>
	 * <li>test avec un paramètre pointant sur un repertoire existant.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testMainResources() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = null;
		final String pathRelMainResourcesTest = "main/resources";
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, pathRelMainResourcesTest
								, null
									, null
										, null);
		
		final String pathRel 
		= GestionnaireProjet.getPathRelMainResources();
		final String pathString 
			= GestionnaireProjet.getPathMainResourcesString();
		final Path path 
			= GestionnaireProjet.getPathMainResources();		
		final File file 
			= GestionnaireProjet.getFileMainResources();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testMainResources()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathString);
			System.out.println(PATH_ABSOLU + path.toString());
			System.out.println(FILE + file.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathString);
		assertNotNull(PATH_NON_NULL, path);
		assertNotNull(REP_NON_NULL, file);
		assertTrue(REP_EXISTE, file.exists());
		assertTrue(REP_DIRECTORY, file.isDirectory());
	
	} // Fin de testMainResources()._______________________________________


	
	/**
	 * method testTestJavaNull() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives à pathTestJava.</li>
	 * <li>test avec un paramètre null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testTestJavaNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = null;
		final String pathRelMainResourcesTest = null;
		final String pathRelTestJavaTest = null;
		final String pathRelTestResourcesTest = null;
		final String groupId = null;

		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, pathRelMainResourcesTest
								, pathRelTestJavaTest
									, pathRelTestResourcesTest
										, groupId);

		final String pathRel 
			= GestionnaireProjet.getPathRelTestJava();
		final String pathString 
			= GestionnaireProjet.getPathTestJavaString();
		final Path path 
			= GestionnaireProjet.getPathTestJava();		
		final File file 
			= GestionnaireProjet.getFileTestJava();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testTestJavaNull()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathString);
			System.out.println(PATH_ABSOLU + path.toString());
			System.out.println(FILE + file.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathString);
		assertNotNull(PATH_NON_NULL, path);
		assertNotNull(REP_NON_NULL, file);
		assertTrue(REP_EXISTE, file.exists());
		assertTrue(REP_DIRECTORY, file.isDirectory());
		
	} // Fin de testTestJavaNull().________________________________________
	

	
	/**
	 * method testTestJavaInexistant() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives à pathTestJava.</li>
	 * <li>test avec un paramètre pointant sur un 
	 * repertoire inexistant.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testTestJavaInexistant() throws Exception {
						
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = null;
		final String pathRelMainResourcesTest = null;
		final String pathRelTestJavaTest = "testtest/javatest";
		final String pathRelTestResourcesTest = null;
		final String groupId = null;

		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, pathRelMainResourcesTest
								, pathRelTestJavaTest
									, pathRelTestResourcesTest
										, groupId);

		final String pathRel 
			= GestionnaireProjet.getPathRelTestJava();
		final String pathString 
			= GestionnaireProjet.getPathTestJavaString();
		final Path path 
			= GestionnaireProjet.getPathTestJava();		
		final File file 
			= GestionnaireProjet.getFileTestJava();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testTestJavaNull()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathString);
			System.out.println(PATH_ABSOLU + path.toString());
			System.out.println(FILE + file.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathString);
		assertNotNull(PATH_NON_NULL, path);
		assertNotNull(REP_NON_NULL, file);
		assertTrue(REP_EXISTE, file.exists());
		assertTrue(REP_DIRECTORY, file.isDirectory());
		
	} // Fin de testTestJavaInexistant().__________________________________

	
		
	/**
	 * method testTestJava() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives à pathTestJava.</li>
	 * <li>test avec un paramètre pointant sur un repertoire existant.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testTestJava() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = null;
		final String pathRelMainResourcesTest = null;
		final String pathRelTestJavaTest = "test/java";
		final String pathRelTestResourcesTest = null;
		final String groupId = null;

		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, pathRelMainResourcesTest
								, pathRelTestJavaTest
									, pathRelTestResourcesTest
										, groupId);

		final String pathRel 
			= GestionnaireProjet.getPathRelTestJava();
		final String pathString 
			= GestionnaireProjet.getPathTestJavaString();
		final Path path 
			= GestionnaireProjet.getPathTestJava();		
		final File file 
			= GestionnaireProjet.getFileTestJava();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testTestJavaNull()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathString);
			System.out.println(PATH_ABSOLU + path.toString());
			System.out.println(FILE + file.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathString);
		assertNotNull(PATH_NON_NULL, path);
		assertNotNull(REP_NON_NULL, file);
		assertTrue(REP_EXISTE, file.exists());
		assertTrue(REP_DIRECTORY, file.isDirectory());
		
	} // Fin de testTestJava().____________________________________________


	
	/**
	 * method testTestResourcesNull() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives à pathTestResources.</li>
	 * <li>test avec un paramètre null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testTestResourcesNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = null;
		final String pathRelMainResourcesTest = null;
		final String pathRelTestJavaTest = null;
		final String pathRelTestResourcesTest = null;
		final String groupId = null;

		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, pathRelMainResourcesTest
								, pathRelTestJavaTest
									, pathRelTestResourcesTest
										, groupId);

		final String pathRel 
			= GestionnaireProjet.getPathRelTestResources();
		final String pathString 
			= GestionnaireProjet.getPathTestResourcesString();
		final Path path 
			= GestionnaireProjet.getPathTestResources();		
		final File file 
			= GestionnaireProjet.getFileTestResources();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testTestResourcesNull()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathString);
			System.out.println(PATH_ABSOLU + path.toString());
			System.out.println(FILE + file.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathString);
		assertNotNull(PATH_NON_NULL, path);
		assertNotNull(REP_NON_NULL, file);
		assertTrue(REP_EXISTE, file.exists());
		assertTrue(REP_DIRECTORY, file.isDirectory());
		
	} // Fin de testTestResourcesNull().___________________________________
	
	
	
	/**
	 * method testTestResourcesInexistant() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives à pathTestResources.</li>
	 * <li>test avec un paramètre inexistant.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testTestResourcesInexistant() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = null;
		final String pathRelMainResourcesTest = null;
		final String pathRelTestJavaTest = null;
		final String pathRelTestResourcesTest = "testtest/resourcestest";
		final String groupId = null;

		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, pathRelMainResourcesTest
								, pathRelTestJavaTest
									, pathRelTestResourcesTest
										, groupId);

		final String pathRel 
			= GestionnaireProjet.getPathRelTestResources();
		final String pathString 
			= GestionnaireProjet.getPathTestResourcesString();
		final Path path 
			= GestionnaireProjet.getPathTestResources();		
		final File file 
			= GestionnaireProjet.getFileTestResources();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testTestResourcesInexistant()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathString);
			System.out.println(PATH_ABSOLU + path.toString());
			System.out.println(FILE + file.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathString);
		assertNotNull(PATH_NON_NULL, path);
		assertNotNull(REP_NON_NULL, file);
		assertTrue(REP_EXISTE, file.exists());
		assertTrue(REP_DIRECTORY, file.isDirectory());
		
	} // Fin de testTestResourcesInexistant()._____________________________
	

	
	/**
	 * method testTestResources() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives à pathTestResources.</li>
	 * <li>test avec un paramètre existant.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testTestResources() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = null;
		final String pathRelMainResourcesTest = null;
		final String pathRelTestJavaTest = null;
		final String pathRelTestResourcesTest = "test/resources";
		final String groupId = null;

		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, pathRelMainResourcesTest
								, pathRelTestJavaTest
									, pathRelTestResourcesTest
										, groupId);

		final String pathRel 
			= GestionnaireProjet.getPathRelTestResources();
		final String pathString 
			= GestionnaireProjet.getPathTestResourcesString();
		final Path path 
			= GestionnaireProjet.getPathTestResources();		
		final File file 
			= GestionnaireProjet.getFileTestResources();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testTestResources()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathString);
			System.out.println(PATH_ABSOLU + path.toString());
			System.out.println(FILE + file.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathString);
		assertNotNull(PATH_NON_NULL, path);
		assertNotNull(REP_NON_NULL, file);
		assertTrue(REP_EXISTE, file.exists());
		assertTrue(REP_DIRECTORY, file.isDirectory());
		
	} // Fin de testTestResources()._______________________________________
	

	
	/**
	 * method testTestGroupIdNull() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives à pathGroupId.</li>
	 * <li>test avec un paramètre null.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testTestGroupIdNull() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = null;
		final String pathRelMainResourcesTest = null;
		final String pathRelTestJavaTest = null;
		final String pathRelTestResourcesTest = null;
		final String groupId = null;
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, pathRelMainResourcesTest
								, pathRelTestJavaTest
									, pathRelTestResourcesTest
										, groupId);

		final String pathRel 
			= GestionnaireProjet.getPathRelGroupId();
		final String pathStringMainJava 
			= GestionnaireProjet.getPathGroupIdMainJavaString();
		final String pathStringTestJava 
			= GestionnaireProjet.getPathGroupIdTestJavaString();
		final Path pathMainJava 
			= GestionnaireProjet.getPathGroupIdMainJava();
		final Path pathTestJava 
			= GestionnaireProjet.getPathGroupIdTestJava();
		final File fileMainJava 
			= GestionnaireProjet.getFileGroupIdMainJava();
		final File fileTestJava 
			= GestionnaireProjet.getFileGroupIdTestJava();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testTestResources()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathStringMainJava);
			System.out.println(PATH_ABSOLU + pathStringTestJava);
			System.out.println(PATH_ABSOLU + pathMainJava.toString());
			System.out.println(PATH_ABSOLU + pathTestJava.toString());
			System.out.println(FILE + fileMainJava.getName());
			System.out.println(FILE + fileTestJava.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathStringMainJava);
		assertNotNull(PATH_NON_NULL, pathStringTestJava);
		assertNotNull(PATH_NON_NULL, pathMainJava);
		assertNotNull(PATH_NON_NULL, pathTestJava);
		assertNotNull(REP_NON_NULL, fileMainJava);
		assertTrue(REP_EXISTE, fileMainJava.exists());
		assertTrue(REP_EXISTE, fileTestJava.exists());
		assertTrue(REP_DIRECTORY, fileMainJava.isDirectory());
		assertTrue(REP_DIRECTORY, fileTestJava.isDirectory());
		
	} // Fin de testTestGroupIdNull()._____________________________________
	

	
	/**
	 * method testTestGroupId() :<br/>
	 * <ul>
	 * <li>teste l'ensemble des méthodes relatives à pathGroupId.</li>
	 * <li>test avec un paramètre existant.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testTestGroupId() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* remet à null les attributs. */
		GestionnaireProjet.reinitialiserAttributs();
		
		final String pathTest = null;
		final String nomProjetTest = PROJET_GENERATION_CODE_TEST;
		final String nomRepertoireSrcTest = null;
		final String pathRelMainJavaTest = null;
		final String pathRelMainResourcesTest = null;
		final String pathRelTestJavaTest = null;
		final String pathRelTestResourcesTest = null;
		final String groupId = "levy/daniel/application";
		
		/* Alimentation des attributs du GestionnaireProjet. */
		GestionnaireProjet
			.alimenterAttributs(
					pathTest
					, nomProjetTest
					, nomRepertoireSrcTest
						, pathRelMainJavaTest
							, pathRelMainResourcesTest
								, pathRelTestJavaTest
									, pathRelTestResourcesTest
										, groupId);

		final String pathRel 
			= GestionnaireProjet.getPathRelGroupId();
		final String pathStringMainJava 
			= GestionnaireProjet.getPathGroupIdMainJavaString();
		final String pathStringTestJava 
			= GestionnaireProjet.getPathGroupIdTestJavaString();
		final Path pathMainJava 
			= GestionnaireProjet.getPathGroupIdMainJava();
		final Path pathTestJava 
			= GestionnaireProjet.getPathGroupIdTestJava();
		final File fileMainJava 
			= GestionnaireProjet.getFileGroupIdMainJava();
		final File fileTestJava 
			= GestionnaireProjet.getFileGroupIdTestJava();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testTestResources()");
			System.out.println(PATH_RELATIF + pathRel);
			System.out.println(PATH_ABSOLU + pathStringMainJava);
			System.out.println(PATH_ABSOLU + pathStringTestJava);
			System.out.println(PATH_ABSOLU + pathMainJava.toString());
			System.out.println(PATH_ABSOLU + pathTestJava.toString());
			System.out.println(FILE + fileMainJava.getName());
			System.out.println(FILE + fileTestJava.getName());
		}
		
		assertNotNull(PATH_RELATIF_NON_NULL, pathRel);
		assertNotNull(PATH_NON_NULL, pathStringMainJava);
		assertNotNull(PATH_NON_NULL, pathStringTestJava);
		assertNotNull(PATH_NON_NULL, pathMainJava);
		assertNotNull(PATH_NON_NULL, pathTestJava);
		assertNotNull(REP_NON_NULL, fileMainJava);
		assertTrue(REP_EXISTE, fileMainJava.exists());
		assertTrue(REP_EXISTE, fileTestJava.exists());
		assertTrue(REP_DIRECTORY, fileMainJava.isDirectory());
		assertTrue(REP_DIRECTORY, fileTestJava.isDirectory());
		
	} // Fin de testTestGroupId()._________________________________________
	
		
	
} // FIN DE LA CLASSE GestionnaireProjetTest.--------------------------------
