package levy.daniel.application.apptechnic.configurationmanagers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.apptechnic.exceptions.technical.impl.BundleManquantRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.CleManquanteRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.CleNullRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantRunTimeException;


/**
 * class ConfigurationBundlesManagerTest :<br/>
 * Test JUnit de la classe ConfigurationBundlesManager.<br/>
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
public class ConfigurationBundlesManagerTest {

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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(ConfigurationBundlesManagerTest.class);


	
	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR ConfigurationBundlesManagerTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public ConfigurationBundlesManagerTest() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method testGetBundleApplication() :<br/>
	 * teste la méthode getBundleApplication() qui charge 
	 * un properties INTERNE (dans le classpath).
	 * <ul>
	 * <li>garantit que bundleApplication n'est pas null 
	 * (application.properties sous la racine).</li>
	 * <li>garantit que getBundleApplication() retourne un Singleton.</li>
	 * <li>garantit que rapportConfigurationCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que rapportUtilisateurCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que l'absence de application.properties jette une 
	 * BundleManquantRunTimeException provoquée par une 
	 * MissingResourceException.</li>
	 * </ul>
	 * @throws Exception 
	 */
	@Test
	public void testGetBundleApplication() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************


		try {
			final ResourceBundle bundleApplication1 
				= ConfigurationBundlesManager.getBundleApplication();
			
			/* garantit que bundleApplication n'est pas null. */
			assertNotNull("bundleApplication ne doit pas être null : "
					, bundleApplication1);
			
			final ResourceBundle bundleApplication2 
			= ConfigurationBundlesManager.getBundleApplication();
			
			/* garantit que getBundleApplication() retourne un Singleton. */
			assertTrue(
					"bundleApplication1 doit être la même "
					+ "instance que bundleApplication2"
					, bundleApplication1 == bundleApplication2);   // NOPMD by dan on 02/10/17 23:15
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
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
				
	} // Fin de testGetBundleApplication().________________________________
	

	
	/**
	 * method testGetBundleRessourcesExternes() :<br/>
	 * teste la méthode getBundleRessourcesExternes() qui charge 
	 * un properties INTERNE (dans le classpath).
	 * <ul>
	 * <li>garantit que bundleRessourcesExternes n'est pas null 
	 * (application.properties sous la racine).</li>
	 * <li>garantit que getBundleRessourcesExternes() retourne un Singleton.</li>
	 * <li>garantit que rapportConfigurationCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que rapportUtilisateurCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que l'absence de 
	 * configuration_ressources_externes.properties jette une 
	 * BundleManquantRunTimeException provoquée par une 
	 * MissingResourceException.</li>
	 * </ul>
	 * @throws Exception 
	 */
	@Test
	public void testGetBundleRessourcesExternes() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************


		try {
			final ResourceBundle bundleRessourcesExternes1 
				= ConfigurationBundlesManager.getBundleRessourcesExternes();
			
			/* garantit que bundleRessourcesExternes n'est pas null. */
			assertNotNull("bundleRessourcesExternes ne doit pas être null : "
					, bundleRessourcesExternes1);
			
			final ResourceBundle bundleRessourcesExternes2 
			= ConfigurationBundlesManager.getBundleRessourcesExternes();
			
			/* garantit que getBundleRessourcesExternes() retourne un Singleton. */
			assertTrue(
					"bundleRessourcesExternes1 doit être la même "
					+ "instance que bundleRessourcesExternes2"
					, bundleRessourcesExternes1 == bundleRessourcesExternes2);   // NOPMD by dan on 02/10/17 23:15
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
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
			 * MissingResourceException. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_MISSINGRESOURCE
					, e.getCause() instanceof MissingResourceException);
			
		}
				
	} // Fin de testGetBundleRessourcesExternes()._________________________
	

	
	/**
	 * method testGetPathRessourcesExternes() :<br/>
	 * <ul>
	 * teste la méthode getPathRessourcesExternes() : <br/>
	 * <li>garantit que pathRessourcesExternes n'est pas null.</li>
	 * <li>garantit que getPathRessourcesExternes() 
	 * retourne un singleton.</li>
	 * <li>garantit que rapportConfigurationCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que rapportUtilisateurCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que l'absence de 
	 * configuration_ressources_externes.properties jette une 
	 * BundleManquantRunTimeException provoquée par une 
	 * BundleManquantRunTimeException.</li>
	 * <li>garantit que l'absence de la clé dans 
	 * configuration_ressources_externes.properties jette 
	 * une CleManquanteRunTimeException provoquée par une 
	 * MissingResourceException.</li>
	 * <li>garantit que l'absence de valeur associée à la clé 
	 * jette une CleNullRunTimeException.</li>
	 * <li> garantit qu'un répertoire inexistant ou fichier simple 
	 * jette une FichierInexistantRunTimeException.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathRessourcesExternes() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		String pathRessourcesExternes1 = null;
		String pathRessourcesExternes2 = null;
		
		try {
			
			pathRessourcesExternes1 
				= ConfigurationBundlesManager.getPathRessourcesExternes();
			
			pathRessourcesExternes2 
			= ConfigurationBundlesManager.getPathRessourcesExternes();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("PATH DES RESSOURCES EXTERNES : " 
						+ pathRessourcesExternes1);
			}
			
			/* garantit que pathRessourcesExternes n'est pas null. */
			assertNotNull("pathRessourcesExternes1 ne doit pas être null : "
					, pathRessourcesExternes1);
			
			assertEquals("pathRessourcesExternes1 == pathRessourcesExternes2 : "
					, pathRessourcesExternes1, pathRessourcesExternes2);
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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

	} // Fin de testGetPathRessourcesExternes().___________________________


	
	/**
	 * method testGetPathRapportsControle() :<br/>
	 * <ul>
	 * teste la méthode getPathRapportsControle() : <br/>
	 * <li>garantit que getPathRapportsControle() n'est pas null.</li>
	 * <li>garantit que getPathRapportsControle() 
	 * retourne un singleton.</li>
	 * <li>garantit que rapportConfigurationCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que rapportUtilisateurCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que l'absence de 
	 * configuration_ressources_externes.properties jette une 
	 * BundleManquantRunTimeException provoquée par une 
	 * BundleManquantRunTimeException.</li>
	 * <li>garantit que l'absence de la clé dans 
	 * configuration_ressources_externes.properties jette 
	 * une CleManquanteRunTimeException provoquée par une 
	 * MissingResourceException.</li>
	 * <li>garantit que l'absence de valeur associée à la clé 
	 * jette une CleNullRunTimeException.</li>
	 * <li> garantit qu'un répertoire inexistant ou fichier simple 
	 * jette une FichierInexistantRunTimeException.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathRapportsControle() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		String pathRapportsControle1 = null;
		String pathRapportsControle2 = null;
		
		try {
			
			pathRapportsControle1 
				= ConfigurationBundlesManager.getPathRapportsControle();
			
			pathRapportsControle2 
			= ConfigurationBundlesManager.getPathRapportsControle();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("PATH DES RAPPORTS DE CONTROLE : " 
						+ pathRapportsControle1);
			}
			
			/* garantit que pathRapportsControle n'est pas null. */
			assertNotNull("pathRapportsControle1 ne doit pas être null : "
					, pathRapportsControle1);
			
			assertEquals("pathRapportsControle1 == pathRapportsControle2 : "
					, pathRapportsControle1, pathRapportsControle2);
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
		
	} // Fin de testGetPathRapportsControle()._____________________________

	
	
	/**
	 * method testGetPathLogs() :<br/>
	 * <ul>
	 * teste la méthode getPathLogs() : <br/>
	 * <li>garantit que getPathLogs() n'est pas null.</li>
	 * <li>garantit que getPathLogs() 
	 * retourne un singleton.</li>
	 * <li>garantit que rapportConfigurationCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que rapportUtilisateurCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que l'absence de 
	 * configuration_ressources_externes.properties jette une 
	 * BundleManquantRunTimeException provoquée par une 
	 * BundleManquantRunTimeException.</li>
	 * <li>garantit que l'absence de la clé dans 
	 * configuration_ressources_externes.properties jette 
	 * une CleManquanteRunTimeException provoquée par une 
	 * MissingResourceException.</li>
	 * <li>garantit que l'absence de valeur associée à la clé 
	 * jette une CleNullRunTimeException.</li>
	 * <li> garantit qu'un répertoire inexistant ou fichier simple 
	 * jette une FichierInexistantRunTimeException.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathLogs() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		String pathLogs1 = null;
		String pathLogs2 = null;
		
		try {
			
			pathLogs1 
				= ConfigurationBundlesManager.getPathLogs();
			
			pathLogs2 
			= ConfigurationBundlesManager.getPathLogs();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("PATH DES LOGS : " 
						+ pathLogs1);
			}
			
			/* garantit que pathLogs n'est pas null. */
			assertNotNull("pathLogs1 ne doit pas être null : "
					, pathLogs1);
			
			assertEquals("pathLogs1 == pathLogs2 : "
					, pathLogs1, pathLogs2);
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
		
	} // Fin de testGetPathLogs()._________________________________________


		
	/**
	 * method testGetPathData() :<br/>
	 * <ul>
	 * teste la méthode getPathData() : <br/>
	 * <li>garantit que getPathData() n'est pas null.</li>
	 * <li>garantit que getPathData() 
	 * retourne un singleton.</li>
	 * <li>garantit que rapportConfigurationCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que rapportUtilisateurCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que l'absence de 
	 * configuration_ressources_externes.properties jette une 
	 * BundleManquantRunTimeException provoquée par une 
	 * BundleManquantRunTimeException.</li>
	 * <li>garantit que l'absence de la clé dans 
	 * configuration_ressources_externes.properties jette 
	 * une CleManquanteRunTimeException provoquée par une 
	 * MissingResourceException.</li>
	 * <li>garantit que l'absence de valeur associée à la clé 
	 * jette une CleNullRunTimeException.</li>
	 * <li> garantit qu'un répertoire inexistant ou fichier simple 
	 * jette une FichierInexistantRunTimeException.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPathData() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		String pathData1 = null;
		String pathData2 = null;
		
		try {
			
			pathData1 
				= ConfigurationBundlesManager.getPathData();
			
			pathData2 
			= ConfigurationBundlesManager.getPathData();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println("PATH DES DATA : " 
						+ pathData1);
			}
			
			/* garantit que pathData n'est pas null. */
			assertNotNull("pathData1 ne doit pas être null : "
					, pathData1);
			
			assertEquals("pathData1 == pathData2 : "
					, pathData1, pathData2);
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
			= ConfigurationBundlesManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationBundlesManager.getRapportUtilisateurCsv();
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				
				System.out.println();
				System.out.println(RAPPORT_CONFIGURATION);
				System.out.println(rapportConfigurationCsv);
				
				System.out.println();
				System.out.println(RAPPORT_UTILISATEUR);
				System.out.println(rapportUtilisateurCsv);
				
				System.out.println();
				System.out.print(LISTE_EXCEPTIONS);
				System.out.println(e.listeExceptionsToString());
				
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
		
	} // Fin de testGetPathData()._________________________________________

	

} // FIN DE LA CLASSE ConfigurationBundlesManagerTest.-----------------------
