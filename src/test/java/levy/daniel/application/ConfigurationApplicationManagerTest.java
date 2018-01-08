package levy.daniel.application;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.apptechnic.exceptions.technical.impl.BundleManquantRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.CleManquanteRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.CleNullRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantRunTimeException;


/**
 * class ConfigurationApplicationManagerTest :<br/>
 * Test JUnit de la classe ConfigurationApplicationManager.<br/>
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
public class ConfigurationApplicationManagerTest {

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
	 * RAPPORT_UTILISATEUR : String :<br/>
	 * "RAPPORT UTILISATEUR : ".<br/>
	 */
	public static final String RAPPORT_UTILISATEUR 
		= "RAPPORT UTILISATEUR : ";

	
	/**
	 * LISTE_EXCEPTIONS : String :<br/>
	 * "LISTE DES EXCEPTIONS ENCAPSULEES DANS e : ".<br/>
	 */
	public static final String LISTE_EXCEPTIONS 
		= "LISTE DES EXCEPTIONS ENCAPSULEES DANS e : ";
	
	
	/**
	 * RAPPORT_CONFIG_NE_DOIT : String :<br/>
	 * "Le rapport de configuration ne doit ".<br/>
	 */
	public static final String RAPPORT_CONFIG_NE_DOIT 
		= "Le rapport de configuration ne doit ";

	
	/**
	 * RAPPORT_UTILISATEUR_NE_DOIT : String :<br/>
	 * "Le rapport utilisateur ne doit ".<br/>
	 */
	public static final String RAPPORT_UTILISATEUR_NE_DOIT 
	= "Le rapport utilisateur ne doit ";


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
		= LogFactory.getLog(ConfigurationApplicationManagerTest.class);


	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR ConfigurationApplicationManagerTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public ConfigurationApplicationManagerTest() {
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
	 * <li>Garantit que le rapport fourni par 
	 * getRapportConfigurationCsv() est null 
	 * si pas de problème d'import.</li>
	 * <li>garantit que rapportConfigurationCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que rapportUtilisateurCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que l'absence de application.properties jette une 
	 * BundleManquantRunTimeException provoquée par une 
	 * BundleManquantRunTimeException provenant de ConfigurationBundlesManager.</li>
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
				= ConfigurationApplicationManager.getBundleApplication();
			
			/* garantit que bundleApplication n'est pas null. */
			assertNotNull("bundleApplication ne doit pas être null : "
					, bundleApplication1);
			
			final ResourceBundle bundleApplication2 
			= ConfigurationApplicationManager.getBundleApplication();
			
			/* garantit que getBundleApplication() retourne un Singleton. */
			assertTrue(
					"bundleApplication1 doit être la même "
					+ "instance que bundleApplication2"
					, bundleApplication1 == bundleApplication2);   // NOPMD by dan on 02/10/17 23:16
			
			/* Récupère le rapport de chargement de la configuration. */
			final String rapportCsv 
				= ConfigurationApplicationManager
					.getRapportConfigurationCsv();
			
			/* Garantit que le rapport fourni par getRapportConfigurationCsv() 
			 * est null si pas de problème d'import. */
			assertNull("Le rapport est null si pas de pb d'import : "
					, rapportCsv);

		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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

			/* garantit que l'absence de application.properties jette 
			 * une BundleManquantRunTimeException provoquée par une 
			 * BundleManquantRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
			
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
	 * <li>Garantit que le rapport fourni par 
	 * getRapportConfigurationCsv() est null 
	 * si pas de problème d'import.</li>
	 * <li>garantit que rapportConfigurationCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que rapportUtilisateurCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que l'absence de application.properties jette une 
	 * BundleManquantRunTimeException provoquée par une 
	 * BundleManquantRunTimeException provenant de ConfigurationBundlesManager.</li>
	 * </ul>
	 * 
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
				= ConfigurationApplicationManager.getBundleRessourcesExternes();
			
			/* garantit que bundleRessourcesExternes n'est pas null. */
			assertNotNull("bundleRessourcesExternes ne doit pas être null : "
					, bundleRessourcesExternes1);
			
			final ResourceBundle bundleRessourcesExternes2 
			= ConfigurationApplicationManager.getBundleRessourcesExternes();
			
			/* garantit que getBundleRessourcesExternes() retourne un Singleton. */
			assertTrue(
					"bundleRessourcesExternes1 doit être la même "
					+ "instance que bundleRessourcesExternes2"
					, bundleRessourcesExternes1 == bundleRessourcesExternes2);   // NOPMD by dan on 02/10/17 23:16
			
			/* Récupère le rapport de chargement de la configuration. */
			final String rapportCsv 
				= ConfigurationApplicationManager
					.getRapportConfigurationCsv();
			
			/* Garantit que le rapport fourni par getRapportConfigurationCsv() 
			 * est null si pas de problème d'import. */
			assertNull("Le rapport est null si pas de pb d'import : "
					, rapportCsv);

		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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

			/* garantit que l'absence de application.properties jette 
			 * une BundleManquantRunTimeException provoquée par une 
			 * BundleManquantRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
			
		}
				
	} // Fin de testGetBundleRessourcesExternes().________________________________
	

	
	/**
	 * method testGetPathRessourcesExternes() :<br/>
	 * teste la méthode getPathRessourcesExternes() qui fournit 
	 * la path des ressources Externes.
	 * <ul>
	 * <li>garantit que getPathRessourcesExternes() n'est pas null.</li>
	 * <li>garantit que getPathRessourcesExternes() retourne un Singleton.</li>
	 * <li>Garantit que le rapport fourni par 
	 * getRapportConfigurationCsv() est null 
	 * si pas de problème d'import.</li>
	 * <li>garantit que rapportConfigurationCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que rapportUtilisateurCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que l'absence de 
	 * configuration_ressources_externes.properties jette une 
	 * BundleManquantRunTimeException provoquée par une 
	 * BundleManquantRunTimeException provenant de ConfigurationBundlesManager.</li>
	 * <li>garantit que l'absence de clé jette une 
	 * CleManquanteRunTimeException provoquée 
	 * par une CleManquanteRunTimeException provenant 
	 * de ConfigurationBundlesManager.</li>
	 * <li>garantit que l'absence de valeur pour la clé jette 
	 * une CleNullRunTimeException provoquée par une CleNullRunTimeException 
	 * provenant de ConfigurationBundlesManager.</li>
	 * <li>garantit que l'absence de valeur pour la clé jette 
	 * une FichierInexistantRunTimeException provoquée 
	 * par une FichierInexistantRunTimeException 
	 * provenant de ConfigurationBundlesManager.</li>
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
		
		try {
			
			final String pathRessourcesExternes1 
				= ConfigurationApplicationManager
					.getPathRessourcesExternes();
			
			/* garantit que getPathRessourcesExternes() n'est pas null. */
			assertNotNull("getPathRessourcesExternes() ne doit pas être null : "
					, pathRessourcesExternes1);
			
			final String pathRessourcesExternes2 
			= ConfigurationApplicationManager
				.getPathRessourcesExternes();
			
			/* garantit que getPathRessourcesExternes() retourne un Singleton. */
			assertTrue(
					"pathRessourcesExternes1 doit être la même "
					+ "instance que pathRessourcesExternes2"
					, pathRessourcesExternes1 == pathRessourcesExternes2); // NOPMD by dan on 05/10/17 15:34
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("PATH VERS LES RESSOURCES EXTERNES : " 
						+ pathRessourcesExternes1);
				System.out.println();
			}
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
			 * BundleManquantRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
			

		}
		catch (CleManquanteRunTimeException cleManquanteExc) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
				System.out.println(cleManquanteExc.listeExceptionsToString());
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

			/* garantit que l'absence de clé jette 
			 * une CleManquanteRunTimeException provoquée par une 
			 * CleManquanteRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_CLEMANQUANTERUNTIME
					+ DOIT_ETRE_CLEMANQUANTE
					, cleManquanteExc.getCause() 
					instanceof CleManquanteRunTimeException);
			
		}
		catch (CleNullRunTimeException exc) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
				System.out.println(exc.listeExceptionsToString());
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

			/* garantit que l'absence de valeur pour la clé jette 
			 * une CleNullRunTimeException provoquée par une 
			 * CleNullRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_CLENULL
					+ DOIT_ETRE_CLENULL
					, exc.getCause() 
					instanceof CleNullRunTimeException);
			
		}
		catch (FichierInexistantRunTimeException exc) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
				System.out.println(exc.listeExceptionsToString());
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

			/* garantit que l'absence de valeur pour la clé jette 
			 * une FichierInexistantRunTimeException provoquée par une 
			 * FichierInexistantRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_FICHIERINEXISTANT
					+ DOIT_ETRE_FICHIERINEXISTANT
					, exc.getCause() 
					instanceof FichierInexistantRunTimeException);
			
		}
		
	} // Fin de testGetPathRessourcesExternes().___________________________
		

	
	/**
	 * method testGetPathRapportsControle() :<br/>
	 * teste la méthode getPathRapportsControle() qui fournit 
	 * la path du répertoire des rapports de contrôle.
	 * <ul>
	 * <li>garantit que getPathRapportsControle() n'est pas null.</li>
	 * <li>garantit que getPathRapportsControle() retourne un Singleton.</li>
	 * <li>Garantit que le rapport fourni par 
	 * getRapportConfigurationCsv() est null 
	 * si pas de problème d'import.</li>
	 * <li>garantit que rapportConfigurationCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que rapportUtilisateurCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que l'absence de 
	 * configuration_ressources_externes.properties jette une 
	 * BundleManquantRunTimeException provoquée par une 
	 * BundleManquantRunTimeException provenant de ConfigurationBundlesManager.</li>
	 * <li>garantit que l'absence de clé jette une 
	 * CleManquanteRunTimeException provoquée 
	 * par une CleManquanteRunTimeException provenant 
	 * de ConfigurationBundlesManager.</li>
	 * <li>garantit que l'absence de valeur pour la clé jette 
	 * une CleNullRunTimeException provoquée par une CleNullRunTimeException 
	 * provenant de ConfigurationBundlesManager.</li>
	 * <li>garantit que l'absence de valeur pour la clé jette 
	 * une FichierInexistantRunTimeException provoquée 
	 * par une FichierInexistantRunTimeException 
	 * provenant de ConfigurationBundlesManager.</li>
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
		
		try {
			
			final String pathRapportsControle1 
				= ConfigurationApplicationManager
					.getPathRapportsControle();
			
			/* garantit que getPathRapportsControle() n'est pas null. */
			assertNotNull("getPathRapportsControle() ne doit pas être null : "
					, pathRapportsControle1);
			
			final String pathRapportsControle2 
			= ConfigurationApplicationManager
				.getPathRapportsControle();
			
			/* garantit que getPathRapportsControle() retourne un Singleton. */
			assertTrue(
					"pathRapportsControle1 doit être la même "
					+ "instance que pathRapportsControle2"
					, pathRapportsControle1 == pathRapportsControle2); // NOPMD by dan on 05/10/17 15:34
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("PATH VERS LES RAPPORTS DE CONTROLE : " 
						+ pathRapportsControle1);
				System.out.println();
			}
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
			 * BundleManquantRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
			

		}
		catch (CleManquanteRunTimeException cleManquanteExc) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
				System.out.println(cleManquanteExc.listeExceptionsToString());
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

			/* garantit que l'absence de clé jette 
			 * une CleManquanteRunTimeException provoquée par une 
			 * CleManquanteRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_CLEMANQUANTERUNTIME
					+ DOIT_ETRE_CLEMANQUANTE
					, cleManquanteExc.getCause() 
					instanceof CleManquanteRunTimeException);
			
		}
		catch (CleNullRunTimeException exc) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
				System.out.println(exc.listeExceptionsToString());
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

			/* garantit que l'absence de valeur pour la clé jette 
			 * une CleNullRunTimeException provoquée par une 
			 * CleNullRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_CLENULL
					+ DOIT_ETRE_CLENULL
					, exc.getCause() 
					instanceof CleNullRunTimeException);
			
		}
		catch (FichierInexistantRunTimeException exc) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
				System.out.println(exc.listeExceptionsToString());
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

			/* garantit que l'absence de valeur pour la clé jette 
			 * une FichierInexistantRunTimeException provoquée par une 
			 * FichierInexistantRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_FICHIERINEXISTANT
					+ DOIT_ETRE_FICHIERINEXISTANT
					, exc.getCause() 
					instanceof FichierInexistantRunTimeException);
			
		}
		
	} // Fin de testGetPathRapportsControle().___________________________

	
		
	/**
	 * method testGetPathLogs() :<br/>
	 * teste la méthode getPathLogs() qui fournit 
	 * la path des logs.
	 * <ul>
	 * <li>garantit que getPathLogs() n'est pas null.</li>
	 * <li>garantit que getPathLogs() retourne un Singleton.</li>
	 * <li>Garantit que le rapport fourni par 
	 * getRapportConfigurationCsv() est null 
	 * si pas de problème d'import.</li>
	 * <li>garantit que rapportConfigurationCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que rapportUtilisateurCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que l'absence de 
	 * configuration_ressources_externes.properties jette une 
	 * BundleManquantRunTimeException provoquée par une 
	 * BundleManquantRunTimeException provenant de ConfigurationBundlesManager.</li>
	 * <li>garantit que l'absence de clé jette une 
	 * CleManquanteRunTimeException provoquée 
	 * par une CleManquanteRunTimeException provenant 
	 * de ConfigurationBundlesManager.</li>
	 * <li>garantit que l'absence de valeur pour la clé jette 
	 * une CleNullRunTimeException provoquée par une CleNullRunTimeException 
	 * provenant de ConfigurationBundlesManager.</li>
	 * <li>garantit que l'absence de valeur pour la clé jette 
	 * une FichierInexistantRunTimeException provoquée 
	 * par une FichierInexistantRunTimeException 
	 * provenant de ConfigurationBundlesManager.</li>
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
		
		try {
			
			final String pathLogs1 
				= ConfigurationApplicationManager
					.getPathLogs();
			
			/* garantit que getPathLogs() n'est pas null. */
			assertNotNull("getPathLogs() ne doit pas être null : "
					, pathLogs1);
			
			final String pathLogs2 
			= ConfigurationApplicationManager
				.getPathLogs();
			
			/* garantit que getPathLogs() retourne un Singleton. */
			assertTrue(
					"pathLogs1 doit être la même "
					+ "instance que pathLogs2"
					, pathLogs1 == pathLogs2); // NOPMD by dan on 05/10/17 15:34
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("PATH VERS LES LOGS : " 
						+ pathLogs1);
				System.out.println();
			}
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
			 * BundleManquantRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
			

		}
		catch (CleManquanteRunTimeException cleManquanteExc) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
				System.out.println(cleManquanteExc.listeExceptionsToString());
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

			/* garantit que l'absence de clé jette 
			 * une CleManquanteRunTimeException provoquée par une 
			 * CleManquanteRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_CLEMANQUANTERUNTIME
					+ DOIT_ETRE_CLEMANQUANTE
					, cleManquanteExc.getCause() 
					instanceof CleManquanteRunTimeException);
			
		}
		catch (CleNullRunTimeException exc) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
				System.out.println(exc.listeExceptionsToString());
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

			/* garantit que l'absence de valeur pour la clé jette 
			 * une CleNullRunTimeException provoquée par une 
			 * CleNullRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_CLENULL
					+ DOIT_ETRE_CLENULL
					, exc.getCause() 
					instanceof CleNullRunTimeException);
			
		}
		catch (FichierInexistantRunTimeException exc) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
				System.out.println(exc.listeExceptionsToString());
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

			/* garantit que l'absence de valeur pour la clé jette 
			 * une FichierInexistantRunTimeException provoquée par une 
			 * FichierInexistantRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_FICHIERINEXISTANT
					+ DOIT_ETRE_FICHIERINEXISTANT
					, exc.getCause() 
					instanceof FichierInexistantRunTimeException);
			
		}
		
	} // Fin de testGetPathLogs()._________________________________________
		

			
	/**
	 * method testGetPathData() :<br/>
	 * teste la méthode getPathData() qui fournit 
	 * la path des logs.
	 * <ul>
	 * <li>garantit que getPathData() n'est pas null.</li>
	 * <li>garantit que getPathData() retourne un Singleton.</li>
	 * <li>Garantit que le rapport fourni par 
	 * getRapportConfigurationCsv() est null 
	 * si pas de problème d'import.</li>
	 * <li>garantit que rapportConfigurationCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que rapportUtilisateurCsv n'est pas null 
	 * en cas d'Exception.</li>
	 * <li>garantit que l'absence de 
	 * configuration_ressources_externes.properties jette une 
	 * BundleManquantRunTimeException provoquée par une 
	 * BundleManquantRunTimeException provenant de ConfigurationBundlesManager.</li>
	 * <li>garantit que l'absence de clé jette une 
	 * CleManquanteRunTimeException provoquée 
	 * par une CleManquanteRunTimeException provenant 
	 * de ConfigurationBundlesManager.</li>
	 * <li>garantit que l'absence de valeur pour la clé jette 
	 * une CleNullRunTimeException provoquée par une CleNullRunTimeException 
	 * provenant de ConfigurationBundlesManager.</li>
	 * <li>garantit que l'absence de valeur pour la clé jette 
	 * une FichierInexistantRunTimeException provoquée 
	 * par une FichierInexistantRunTimeException 
	 * provenant de ConfigurationBundlesManager.</li>
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
		
		try {
			
			final String pathData1 
				= ConfigurationApplicationManager
					.getPathData();
			
			/* garantit que getPathData() n'est pas null. */
			assertNotNull("getPathData() ne doit pas être null : "
					, pathData1);
			
			final String pathData2 
			= ConfigurationApplicationManager
				.getPathData();
			
			/* garantit que getPathData() retourne un Singleton. */
			assertTrue(
					"pathData1 doit être la même "
					+ "instance que pathData2"
					, pathData1 == pathData2); // NOPMD by dan on 05/10/17 15:34
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println();
				System.out.println("PATH VERS LES DATA : " 
						+ pathData1);
				System.out.println();
			}
			
		}
		catch (BundleManquantRunTimeException e) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
			 * BundleManquantRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_BUNDLEMANQUANT
					+ DOIT_ETRE_BUNDLEMANQUANT
					, e.getCause() instanceof BundleManquantRunTimeException);
			
	
		}
		catch (CleManquanteRunTimeException cleManquanteExc) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
				System.out.println(cleManquanteExc.listeExceptionsToString());
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
	
			/* garantit que l'absence de clé jette 
			 * une CleManquanteRunTimeException provoquée par une 
			 * CleManquanteRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_CLEMANQUANTERUNTIME
					+ DOIT_ETRE_CLEMANQUANTE
					, cleManquanteExc.getCause() 
					instanceof CleManquanteRunTimeException);
			
		}
		catch (CleNullRunTimeException exc) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
				System.out.println(exc.listeExceptionsToString());
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
	
			/* garantit que l'absence de valeur pour la clé jette 
			 * une CleNullRunTimeException provoquée par une 
			 * CleNullRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_CLENULL
					+ DOIT_ETRE_CLENULL
					, exc.getCause() 
					instanceof CleNullRunTimeException);
			
		}
		catch (FichierInexistantRunTimeException exc) {
			
			/* rapport développeurs.*/
			final String rapportConfigurationCsv 
			= ConfigurationApplicationManager.getRapportConfigurationCsv();
			
			/* rapport utilisateurs. */
			final String rapportUtilisateurCsv 
			= ConfigurationApplicationManager.getRapportUtilisateurCsv();
			
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
				System.out.println(exc.listeExceptionsToString());
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
	
			/* garantit que l'absence de valeur pour la clé jette 
			 * une FichierInexistantRunTimeException provoquée par une 
			 * FichierInexistantRunTimeException provenant 
			 * de ConfigurationBundlesManager. */
			assertTrue(CAUSE_FICHIERINEXISTANT
					+ DOIT_ETRE_FICHIERINEXISTANT
					, exc.getCause() 
					instanceof FichierInexistantRunTimeException);
			
		}
		
	} // Fin de testGetPathData()._________________________________________
		
	
	
} // FIn DE LA CLASSE ConfigurationApplicationManagerTest.-------------------
