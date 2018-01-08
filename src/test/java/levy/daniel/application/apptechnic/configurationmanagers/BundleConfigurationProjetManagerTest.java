package levy.daniel.application.apptechnic.configurationmanagers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.apptechnic.exceptions.technical.AbstractRunTimeTechnicalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.BundleManquantRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.CleManquanteRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.CleNullRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantRunTimeException;


/**
 * class BundleConfigurationProjetManagerTest :<br/>
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
 * @since 4 janv. 2018
 *
 */
public class BundleConfigurationProjetManagerTest {

	// ************************ATTRIBUTS************************************/

	
	/**
	 * AFFICHAGE_GENERAL : Boolean :<br/>
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	
	/**
	 * RAPPORT_CONFIGURATION : String :<br/>
	 * "RAPPORT DE CONFIGURATION : ".<br/>
	 */
	public static final String RAPPORT_CONFIGURATION 
		= "RAPPORT DE CONFIGURATION : ";
	
	/**
	 * RAPPORT_CONFIG_NE_DOIT : String :<br/>
	 * "Le rapport de configuration ne doit ".<br/>
	 */
	public static final String RAPPORT_CONFIG_NE_DOIT 
		= "Le rapport de configuration ne doit ";

	
	/**
	 * RAPPORT_UTILISATEUR : String :<br/>
	 * "RAPPORT UTILISATEUR : ".<br/>
	 */
	public static final String RAPPORT_UTILISATEUR 
		= "RAPPORT UTILISATEUR : ";

	
	/**
	 * RAPPORT_UTILISATEUR_NE_DOIT : String :<br/>
	 * "Le rapport utilisateur ne doit ".<br/>
	 */
	public static final String RAPPORT_UTILISATEUR_NE_DOIT 
		= "Le rapport utilisateur ne doit ";

	
	/**
	 * LISTE_EXCEPTIONS : String :<br/>
	 * "LISTE DES EXCEPTIONS ENCAPSULEES DANS e : ".<br/>
	 */
	public static final String LISTE_EXCEPTIONS 
		= "LISTE DES EXCEPTIONS ENCAPSULEES DANS e : ";
	
	
	/**
	 * CAS_EXCEPTION : String :<br/>
	 * "pas être null en cas d'Exception : ".<br/>
	 */
	public static final String CAS_EXCEPTION 
		= "pas être null en cas d'Exception : ";

	
	/**
	 * CAUSE_BUNDLEMANQUANT : String :<br/>
	 * "La cause de la BundleManquantRunTimeException ".<br/>
	 */
	public static final String CAUSE_BUNDLEMANQUANT 
		= "La cause de la BundleManquantRunTimeException ";
	
	
	/**
	 * DOIT_ETRE_BUNDLEMANQUANT : String :<br/>
	 * "doit être une BundleManquantRunTimeException : ".<br/>
	 */
	public static final String DOIT_ETRE_BUNDLEMANQUANT 
		= "doit être une BundleManquantRunTimeException : ";

	
	/**
	 * DOIT_ETRE_MISSINGRESOURCE : String :<br/>
	 * "doit être une MissingResourceException : ".<br/>
	 */
	public static final String DOIT_ETRE_MISSINGRESOURCE 
		= "doit être une MissingResourceException : ";
	
	
	/**
	 * CAUSE_CLEMANQUANTERUNTIME : String :<br/>
	 * "La cause de la CleManquanteRunTimeException ".<br/>
	 */
	public static final String CAUSE_CLEMANQUANTERUNTIME 
		= "La cause de la CleManquanteRunTimeException ";
	
	
	/**
	 * DOIT_ETRE_CLEMANQUANTE : String :<br/>
	 * "doit être une CleManquanteRunTimeException : ".<br/>
	 */
	public static final String DOIT_ETRE_CLEMANQUANTE 
		= "doit être une CleManquanteRunTimeException : ";
	
	
	/**
	 * CAUSE_CLENULL : String :<br/>
	 * "La cause de la CleNullRunTimeException ".<br/>
	 */
	public static final String CAUSE_CLENULL 
		= "La cause de la CleNullRunTimeException ";
	
	
	/**
	 * DOIT_ETRE_CLENULL : String :<br/>
	 * "doit être une CleNullRunTimeException : ".<br/>
	 */
	public static final String DOIT_ETRE_CLENULL 
		= "doit être une CleNullRunTimeException : ";
	

	/**
	 * CAUSE_FICHIERINEXISTANT : String :<br/>
	 * "La cause de la FichierInexistantRunTimeException ".<br/>
	 */
	public static final String CAUSE_FICHIERINEXISTANT 
		= "La cause de la FichierInexistantRunTimeException ";
	
	
	/**
	 * DOIT_ETRE_FICHIERINEXISTANT : String :<br/>
	 * "doit être une FichierInexistantRunTimeException : ".<br/>
	 */
	public static final String DOIT_ETRE_FICHIERINEXISTANT 
		= "doit être une FichierInexistantRunTimeException : ";
	
	
	
	/**
	 * TIRETS : String :<br/>
	 * "--------------------------------------------------------".<br/>
	 */
	public static final String TIRETS 
	= "--------------------------------------------------------";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(BundleConfigurationProjetManagerTest.class);

	
	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR BundleConfigurationProjetManagerTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public BundleConfigurationProjetManagerTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method testGetBundleConfigurationProjet() :<br/>
	 * <ul>
	 * <li>teste la méthode getBundleConfigurationProjet().</li>
	 * <li>garantit que getBundleConfigurationProjet() 
	 * retourne un Singleton.</li>
	 * </ul>
	 * @throws Exception 
	 */
	@Test
	public void testGetBundleConfigurationProjet() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		try {
			
			final ResourceBundle bundleCP 
			= BundleConfigurationProjetManager
				.getBundleConfigurationProjet();
			
			final ResourceBundle bundleCP2 
			= BundleConfigurationProjetManager
				.getBundleConfigurationProjet();
		
			/* garantit que l'objet n'est pas null. */
			assertNotNull(
					"Le Bundle ne doit pas être null : ", bundleCP);
						
			/* garantit que l'objet est un Singleton. */
			assertSame("getBundleConfigurationProjet() doit "
					+ "retourner la même instance (Singleton) : "
						, bundleCP
							, bundleCP2);
			
		} catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetBundleConfigurationProjet()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ "pas être null en cas d'Exception : "
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de application.properties jette 
			 * une BundleManquantRunTimeException provoquée par une 
			 * MissingResourceException. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_MISSINGRESOURCE
					, e.getCause() instanceof MissingResourceException);
			
		}		
		
	} // Fin de testGetBundleConfigurationProjet().________________________
	

	
	/**
	 * method testGetPathWorkspace() :<br/>
	 * <ul>
	 * <li>teste la méthode getNomProjet().</li>
	 * <li>garantit que getNomProjet() retourne un Singleton.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathWorkspace() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		String path1 = null;
		String path2 = null;
		
		try {
			
			path1 
				= BundleConfigurationProjetManager.getNomProjet();
			
			path2 
				= BundleConfigurationProjetManager.getNomProjet();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("testGetPathWorkspace()"); // NOPMD by dan on 05/01/18 20:04
				System.out.println("PATH DU WORKSPACE : " 
						+ path1);
			}
			
			/* garantit que l'objet n'est pas null. */
			assertNotNull("l'objet ne doit pas être null : " // NOPMD by dan on 05/01/18 20:04
					, path1);
			
			/* garantit que l'objet est un Singleton. */
			assertSame("l'objet doit être un Singleton : " // NOPMD by dan on 05/01/18 20:04
					, path1, path2);
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathWorkspace()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de 
			 * configuration_ressources_externes.properties jette 
			 * une BundleManquantRunTimeException provoquée par une 
			 * BundleManquantRunTimeException. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
		
		}
		catch (CleManquanteRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathWorkspace()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de la clé dans 
			 * configuration_ressources_externes.properties jette 
			 * une CleManquanteRunTimeException provoquée par une 
			 * MissingResourceException. */
			assertTrue(CAUSE_CLEMANQUANTERUNTIME
					+ DOIT_ETRE_MISSINGRESOURCE
					, e.getCause() instanceof MissingResourceException);
		
		}
		catch (CleNullRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathWorkspace()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
		catch (FichierInexistantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathWorkspace()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
				
	} // Fin de testGetPathWorkspace().____________________________________
	

	
	/**
	 * method testGetNomProjet() :<br/>
	 * <ul>
	 * <li>teste la méthode getNomProjet().</li>
	 * <li>garantit que getNomProjet() retourne un Singleton.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@Test
	public void testGetNomProjet() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		String nomProjet1 = null;
		String nomProjet2 = null;
		
		try {
			
			nomProjet1 
				= BundleConfigurationProjetManager.getNomProjet();
			
			nomProjet2 
				= BundleConfigurationProjetManager.getNomProjet();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("testGetNomProjet()"); // NOPMD by dan on 05/01/18 20:04
				System.out.println("NOM DU PROJET : " 
						+ nomProjet1);
			}
			
			/* garantit que l'objet n'est pas null. */
			assertNotNull("l'objet ne doit pas être null : "
					, nomProjet1);
			
			/* garantit que l'objet est un Singleton. */
			assertSame("l'objet doit être un Singleton : "
					, nomProjet1, nomProjet2);
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetNomProjet()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de 
			 * configuration_ressources_externes.properties jette 
			 * une BundleManquantRunTimeException provoquée par une 
			 * BundleManquantRunTimeException. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
		
		}
		catch (CleManquanteRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetNomProjet()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de la clé dans 
			 * configuration_ressources_externes.properties jette 
			 * une CleManquanteRunTimeException provoquée par une 
			 * MissingResourceException. */
			assertTrue(CAUSE_CLEMANQUANTERUNTIME
					+ DOIT_ETRE_MISSINGRESOURCE
					, e.getCause() instanceof MissingResourceException);
		
		}
		catch (CleNullRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetNomProjet()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
		catch (FichierInexistantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetNomProjet()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
									
	} // Fin de testGetNomProjet().________________________________________
	
	
	
	/**
	 * method testGetNomRepertoireSrc() :<br/>
	 * <ul>
	 * <li>teste la méthode getNomRepertoireSrc().</li>
	 * <li>garantit que getNomRepertoireSrc() retourne un Singleton.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@Test
	public void testGetNomRepertoireSrc() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		String nomRepertoireSrc1 = null;
		String nomRepertoireSrc2 = null;
		
		try {
			
			nomRepertoireSrc1 
				= BundleConfigurationProjetManager.getNomRepertoireSrc();
			
			nomRepertoireSrc2 
				= BundleConfigurationProjetManager.getNomRepertoireSrc();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("testGetNomRepertoireSrc() : "); // NOPMD by dan on 05/01/18 20:04
				System.out.println("NOM DU REPERTOIRE SRC : " 
						+ nomRepertoireSrc1);
			}
			
			/* garantit que l'objet n'est pas null. */
			assertNotNull("l'objet ne doit pas être null : "
					, nomRepertoireSrc1);
			
			/* garantit que l'objet est un Singleton. */
			assertSame("l'objet doit être un Singleton : "
					, nomRepertoireSrc1, nomRepertoireSrc2);
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetNomRepertoireSrc() : "
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de 
			 * configuration_ressources_externes.properties jette 
			 * une BundleManquantRunTimeException provoquée par une 
			 * BundleManquantRunTimeException. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
		
		}
		catch (CleManquanteRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetNomRepertoireSrc() : "
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de la clé dans 
			 * configuration_ressources_externes.properties jette 
			 * une CleManquanteRunTimeException provoquée par une 
			 * MissingResourceException. */
			assertTrue(CAUSE_CLEMANQUANTERUNTIME
					+ DOIT_ETRE_MISSINGRESOURCE
					, e.getCause() instanceof MissingResourceException);
		
		}
		catch (CleNullRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetNomRepertoireSrc() : "
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
		catch (FichierInexistantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetNomRepertoireSrc() : "
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
									
	} // Fin de testGetNomRepertoireSrc()._________________________________
	

	
	/**
	 * method testGetPathMainJava() :<br/>
	 * <ul>
	 * <li>teste la méthode getPathMainJava().</li>
	 * <li>garantit que getPathMainJava() retourne un Singleton.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@Test
	public void testGetPathMainJava() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		String pathMainJava1 = null;
		String pathMainJava2 = null;
		
		try {
			
			pathMainJava1 
				= BundleConfigurationProjetManager.getPathMainJava();
			
			pathMainJava2 
				= BundleConfigurationProjetManager.getPathMainJava();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("testGetPathMainJava()"); // NOPMD by dan on 05/01/18 20:04
				System.out.println("NOM DU PATH MAIN JAVA : " 
						+ pathMainJava1);
			}
			
			/* garantit que l'objet n'est pas null. */
			assertNotNull("l'objet ne doit pas être null : "
					, pathMainJava1);
			
			/* garantit que l'objet est un Singleton. */
			assertSame("l'objet doit être un Singleton : "
					, pathMainJava1, pathMainJava2);
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathMainJava()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de 
			 * configuration_ressources_externes.properties jette 
			 * une BundleManquantRunTimeException provoquée par une 
			 * BundleManquantRunTimeException. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
		
		}
		catch (CleManquanteRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathMainJava()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de la clé dans 
			 * configuration_ressources_externes.properties jette 
			 * une CleManquanteRunTimeException provoquée par une 
			 * MissingResourceException. */
			assertTrue(CAUSE_CLEMANQUANTERUNTIME
					+ DOIT_ETRE_MISSINGRESOURCE
					, e.getCause() instanceof MissingResourceException);
		
		}
		catch (CleNullRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathMainJava()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
		catch (FichierInexistantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathMainJava()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
									
	} // Fin de testGetPathMainJava()._________________________________
	

		
	/**
	 * method testGetPathMainResources() :<br/>
	 * <ul>
	 * <li>teste la méthode getPathMainResources().</li>
	 * <li>garantit que getPathMainResources() retourne un Singleton.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@Test
	public void testGetPathMainResources() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		String pathMainResources1 = null;
		String pathMainResources2 = null;
		
		try {
			
			pathMainResources1 
				= BundleConfigurationProjetManager.getPathMainResources();
			
			pathMainResources2 
				= BundleConfigurationProjetManager.getPathMainResources();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("testGetPathMainResources()"); // NOPMD by dan on 05/01/18 20:04
				System.out.println("NOM DU PATH MAIN RESOURCES : " 
						+ pathMainResources1);
			}
			
			/* garantit que l'objet n'est pas null. */
			assertNotNull("l'objet ne doit pas être null : "
					, pathMainResources1);
			
			/* garantit que l'objet est un Singleton. */
			assertSame("l'objet doit être un Singleton : "
					, pathMainResources1, pathMainResources2);
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathMainResources()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de 
			 * configuration_ressources_externes.properties jette 
			 * une BundleManquantRunTimeException provoquée par une 
			 * BundleManquantRunTimeException. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
		
		}
		catch (CleManquanteRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathMainResources()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de la clé dans 
			 * configuration_ressources_externes.properties jette 
			 * une CleManquanteRunTimeException provoquée par une 
			 * MissingResourceException. */
			assertTrue(CAUSE_CLEMANQUANTERUNTIME
					+ DOIT_ETRE_MISSINGRESOURCE
					, e.getCause() instanceof MissingResourceException);
		
		}
		catch (CleNullRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathMainResources()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
		catch (FichierInexistantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathMainResources()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
									
	} // Fin de testGetPathMainResources()._________________________________
	

	
	/**
	 * method testGetPathTestJava() :<br/>
	 * <ul>
	 * <li>teste la méthode getPathTestJava().</li>
	 * <li>garantit que getPathTestJava() retourne un Singleton.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@Test
	public void testGetPathTestJava() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		String pathTestJava1 = null;
		String pathTestJava2 = null;
		
		try {
			
			pathTestJava1 
				= BundleConfigurationProjetManager.getPathTestJava();
			
			pathTestJava2 
				= BundleConfigurationProjetManager.getPathTestJava();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("testGetPathTestJava()"); // NOPMD by dan on 05/01/18 20:04
				System.out.println("NOM DU PATH TEST JAVA : " 
						+ pathTestJava1);
			}
			
			/* garantit que l'objet n'est pas null. */
			assertNotNull("l'objet ne doit pas être null : "
					, pathTestJava1);
			
			/* garantit que l'objet est un Singleton. */
			assertSame("l'objet doit être un Singleton : "
					, pathTestJava1, pathTestJava2);
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathTestJava()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de 
			 * configuration_ressources_externes.properties jette 
			 * une BundleManquantRunTimeException provoquée par une 
			 * BundleManquantRunTimeException. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
		
		}
		catch (CleManquanteRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathTestJava()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de la clé dans 
			 * configuration_ressources_externes.properties jette 
			 * une CleManquanteRunTimeException provoquée par une 
			 * MissingResourceException. */
			assertTrue(CAUSE_CLEMANQUANTERUNTIME
					+ DOIT_ETRE_MISSINGRESOURCE
					, e.getCause() instanceof MissingResourceException);
		
		}
		catch (CleNullRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathTestJava()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
		catch (FichierInexistantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathTestJava()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
									
	} // Fin de testGetPathTestJava()._________________________________
	

		
	/**
	 * method testGetPathTestResources() :<br/>
	 * <ul>
	 * <li>teste la méthode getPathTestResources().</li>
	 * <li>garantit que getPathTestResources() retourne un Singleton.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@Test
	public void testGetPathTestResources() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		String pathResourcesJava1 = null;
		String pathResourcesJava2 = null;
		
		try {
			
			pathResourcesJava1 
				= BundleConfigurationProjetManager.getPathTestResources();
			
			pathResourcesJava2 
				= BundleConfigurationProjetManager.getPathTestResources();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("testGetPathTestResources()"); // NOPMD by dan on 05/01/18 20:04
				System.out.println("NOM DU PATH RESOURCES JAVA : " 
						+ pathResourcesJava1);
			}
			
			/* garantit que l'objet n'est pas null. */
			assertNotNull("l'objet ne doit pas être null : "
					, pathResourcesJava1);
			
			/* garantit que l'objet est un Singleton. */
			assertSame("l'objet doit être un Singleton : "
					, pathResourcesJava1, pathResourcesJava2);
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathTestResources()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de 
			 * configuration_ressources_externes.properties jette 
			 * une BundleManquantRunTimeException provoquée par une 
			 * BundleManquantRunTimeException. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
		
		}
		catch (CleManquanteRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathTestResources()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de la clé dans 
			 * configuration_ressources_externes.properties jette 
			 * une CleManquanteRunTimeException provoquée par une 
			 * MissingResourceException. */
			assertTrue(CAUSE_CLEMANQUANTERUNTIME
					+ DOIT_ETRE_MISSINGRESOURCE
					, e.getCause() instanceof MissingResourceException);
		
		}
		catch (CleNullRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathTestResources()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
		catch (FichierInexistantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetPathTestResources()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
									
	} // Fin de testGetPathTestResources()._________________________________
	

	
	/**
	 * method testGetRacineMainJava() :<br/>
	 * <ul>
	 * <li>teste la méthode getRacineMainJava().</li>
	 * <li>garantit que getRacineMainJava() retourne un Singleton.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetRacineMainJava() throws Exception {
						
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		final String racineMainJava 
			= BundleConfigurationProjetManager.getRacineMainJava();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetRacineMainJava()");
			System.out.println("PATH ABSOLU MAIN JAVA : " 
					+ racineMainJava);
		}
		
	} // Fin de testGetRacineMainJava().___________________________________
	
	
	
	/**
	 * method testGetRacineMainResources() :<br/>
	 * <ul>
	 * <li>teste la méthode getRacineMainResources().</li>
	 * <li>garantit que getRacineMainResources() retourne un Singleton.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetRacineMainResources() throws Exception {
						
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		final String racineMainResources 
			= BundleConfigurationProjetManager.getRacineMainResources();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetRacineMainResources()");
			System.out.println("PATH ABSOLU MAIN RESOURCES : " 
					+ racineMainResources);
		}
		
	} // Fin de testGetRacineMainResources().______________________________
	

	
	/**
	 * method testGetRacineTestJava() :<br/>
	 * <ul>
	 * <li>teste la méthode getRacineTestJava().</li>
	 * <li>garantit que getRacineTestJava() retourne un Singleton.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetRacineTestJava() throws Exception {
						
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		final String racineTestJava 
			= BundleConfigurationProjetManager.getRacineTestJava();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetRacineTestJava()");
			System.out.println("PATH ABSOLU TEST JAVA : " 
					+ racineTestJava);
		}
		
	} // Fin de testGetRacineTestJava().___________________________________
	

	
	/**
	 * method testGetRacineTestResources() :<br/>
	 * <ul>
	 * <li>teste la méthode getRacineTestResources().</li>
	 * <li>garantit que getRacineTestResources() retourne un Singleton.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetRacineTestResources() throws Exception {
						
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		final String racineTestResources 
			= BundleConfigurationProjetManager.getRacineTestResources();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetRacineTestResources()");
			System.out.println("PATH ABSOLU TEST RESOURCES : " 
					+ racineTestResources);
		}
		
	} // Fin de testGetRacineTestResources().______________________________
	

		
	/**
	 * method testGetGroupid() :<br/>
	 * <ul>
	 * <li>teste la méthode getGroupid().</li>
	 * <li>garantit que getGroupid() retourne un Singleton.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@Test
	public void testGetGroupid() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		String groupid1 = null;
		String groupid2 = null;
		
		try {
			
			groupid1 
				= BundleConfigurationProjetManager.getGroupid();
			
			groupid2 
				= BundleConfigurationProjetManager.getGroupid();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("testGetGroupid()"); // NOPMD by dan on 05/01/18 20:04
				System.out.println("GROUPID : " 
						+ groupid1);
				System.out.println("PATH GROUPID : " 
						+ BundleConfigurationProjetManager
							.getPathGroupid());
			}
			
			/* garantit que l'objet n'est pas null. */
			assertNotNull("l'objet ne doit pas être null : "
					, groupid1);
			
			/* garantit que l'objet est un Singleton. */
			assertSame("l'objet doit être un Singleton : "
					, groupid1, groupid2);
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetGroupid()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de 
			 * configuration_ressources_externes.properties jette 
			 * une BundleManquantRunTimeException provoquée par une 
			 * BundleManquantRunTimeException. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
		
		}
		catch (CleManquanteRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetGroupid()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
			/* garantit que l'absence de la clé dans 
			 * configuration_ressources_externes.properties jette 
			 * une CleManquanteRunTimeException provoquée par une 
			 * MissingResourceException. */
			assertTrue(CAUSE_CLEMANQUANTERUNTIME
					+ DOIT_ETRE_MISSINGRESOURCE
					, e.getCause() instanceof MissingResourceException);
		
		}
		catch (CleNullRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetGroupid()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
		catch (FichierInexistantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= BundleConfigurationProjetManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= BundleConfigurationProjetManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				afficher(
						"testGetGroupid()"
						, rapportConfigurationCsv
						, rapportUtilisateurCsv
						, e);
				
			}
			
			/* garantit que rapportConfigurationCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_CONFIG_NE_DOIT
					+ CAS_EXCEPTION
					, rapportConfigurationCsv);
			
			/* garantit que rapportUtilisateurCsv 
			 * n'est pas null en cas d'Exception. */
			assertNotNull(RAPPORT_UTILISATEUR_NE_DOIT
					+ CAS_EXCEPTION
					, rapportUtilisateurCsv);
			
		}
									
	} // Fin de testGetGroupid()._________________________________
	

	
	/**
	 * method testGetPathAppTechnic() :<br/>
	 * <ul>
	 * <li>teste la méthode getPathAppTechnic().</li>
	 * <li>garantit que getPathAppTechnic() retourne un Singleton.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathAppTechnic() throws Exception {
						
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		final String racineTestResources 
			= BundleConfigurationProjetManager.getPathAppTechnic();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetPathAppTechnic()");
			System.out.println("PATH ABSOLU apptechnic : " 
					+ racineTestResources);
		}
		
	} // Fin de testGetPathAppTechnic().___________________________________

	
		
	/**
	 * method testGetPathControllers() :<br/>
	 * <ul>
	 * <li>teste la méthode getPathControllers().</li>
	 * <li>garantit que getPathControllers() retourne un Singleton.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathControllers() throws Exception {
						
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		final String racineTestResources 
			= BundleConfigurationProjetManager.getPathControllers();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetPathControllers()");
			System.out.println("PATH ABSOLU controllers : " 
					+ racineTestResources);
		}
		
	} // Fin de testGetPathControllers().__________________________________
	

		
	/**
	 * method testGetPathModel() :<br/>
	 * <ul>
	 * <li>teste la méthode getPathModel().</li>
	 * <li>garantit que getPathModel() retourne un Singleton.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathModel() throws Exception {
						
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
	
		final String racineTestResources 
			= BundleConfigurationProjetManager.getPathModel();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetPathModel()");
			System.out.println("PATH ABSOLU model : " 
					+ racineTestResources);
			
			System.out.println("PATH ABSOLU dao : " 
					+ BundleConfigurationProjetManager.getPathDao());
			System.out.println("PATH ABSOLU metier : " 
					+ BundleConfigurationProjetManager.getPathMetier());
			System.out.println("PATH ABSOLU services : " 
					+ BundleConfigurationProjetManager.getPathServices());
		}
		
	} // Fin de testGetPathModel().________________________________________
	

		
	/**
	 * method testGetPathVues() :<br/>
	 * <ul>
	 * <li>teste la méthode getPathVues().</li>
	 * <li>garantit que getPathVues() retourne un Singleton.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathVues() throws Exception {
						
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
	
		final String racineTestResources 
			= BundleConfigurationProjetManager.getPathVues();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("testGetPathVues()");
			System.out.println("PATH ABSOLU vues : " 
					+ racineTestResources);
		}
		
	} // Fin de testGetPathVues()._________________________________________
	


	/**
	 * method afficher(
	 * String pMethode
	 * , String pRapportConfigurationCsv
	 * , String pRapportUtilisateurCsv
	 * , AbstractRunTimeTechnicalException pE) :<br/>
	 * <ul>
	 * <li>Affiche à la console le rapport développeur.</li>
	 * <li>Affiche à la console le rapport utilisateur.</li>
	 * </ul>
	 *
	 * @param pMethode : String.<br/>
	 * @param pRapportConfigurationCsv : String.<br/>
	 * @param pRapportUtilisateurCsv : String.<br/>
	 * @param pE : AbstractRunTimeTechnicalException.<br/>
	 */
	private void afficher(
			final String pMethode
			, final String pRapportConfigurationCsv
				, final String pRapportUtilisateurCsv
					, final AbstractRunTimeTechnicalException pE) {
		
		System.out.println(TIRETS);
		System.out.println(pMethode);
		System.out.println();
		System.out.println(RAPPORT_CONFIGURATION);
		System.out.println(pRapportConfigurationCsv);
		
		System.out.println();
		System.out.println(RAPPORT_UTILISATEUR);
		System.out.println(pRapportUtilisateurCsv);
		
		System.out.println();
		System.out.print(LISTE_EXCEPTIONS);
		System.out.println(pE.listeExceptionsToString());
		System.out.println(TIRETS);
		
	} // Fin de afficher(...)._____________________________________________
	
	


} // FIN DE LA CLASSE BundleConfigurationProjetManagerTest.------------------
