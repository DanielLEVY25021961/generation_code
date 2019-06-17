package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.IGestionnaireTemplates;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.impl.GestionnaireTemplates;


/**
 * CLASSE GestionnairePropertiesTest :<br/>
 * Test JUnit de la classe GestionnaireProperties.<br/>
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
public class GestionnairePropertiesTest {
	
	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	/**
	 * " : ".<br/>
	 */
	public static final String DEUX_POINTS = " : ";

	/**
	 * "labels".<br/>
	 */
	public static final String NOM_BASE_LABEL = "labels";
	
	/**
	 * "messages".<br/>
	 */
	public static final String NOM_BASE_MESSAGE = "messages";
	
	/**
	 * "anglais (Etats-Unis)".<br/>
	 */
	public static final String LOCALE_US_STRING = "anglais (Etats-Unis)";
	
	/**
	 * "français (France)".<br/>
	 */
	public static final String LOCALE_FR_STRING = "français (France)";
	
	/**
	 * "test1".<br/>
	 */
	public static final String TEST1 = "test1";
	
	/**
	 * "test2".<br/>
	 */
	public static final String TEST2 = "test2";
	
	/**
	 * "CONSTRUCTEUR COMPLET - path absolu du properties avec la Locale ".<br/>
	 */
	public static final String CONSTRUCTEUR_COMPLET 
		= "CONSTRUCTEUR COMPLET - path absolu du properties avec la Locale ";
	
	
	/**
	 * IGestionnaireTemplates.<br/>
	 */
	private static transient IGestionnaireTemplates gestionnaireTemplate;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GestionnairePropertiesTest.class);
	

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public GestionnairePropertiesTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	 * Teste le constructeur complet 
	 * et le constructeur d'arité nulle.<br/>
	 * <ul>
	 * <li>garantit que le constructeur complet 
	 * alimente pathAbsoluFichierProperties.</li>
	 * <li>garantit que le constructeur complet 
	 * alimente fichierProperties.</li>
	 * <li>garantit que le constructeur complet pointe 
	 * sur le bon properties.</li>
	 * <li>garantit que le constructeur d'arité nulle + setters 
	 * alimente pathAbsoluFichierProperties.</li>
	 * <li>garantit que le constructeur d'arité nulle + setters 
	 * alimente fichierProperties.</li>
	 * <li>garantit que le constructeur d'arité nulle + setters 
	 * pointe sur le bon properties.</li>
	 * <li>garantit que les setters modifient 
	 * les attributs calculés.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	@Test
	public void testConstructeurs() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE GestionnairePropertiesTest - méthode testConstructeurs() ********** ");
		}

		final String commententaireUS = "COMMENTAIRE US";
		final Map<String, String> mapPropertiesUS 
			= new LinkedHashMap<String, String>();
		mapPropertiesUS.put(TEST1, "value1");
		mapPropertiesUS.put(TEST2, "value2");
		
		final String commententaireFR = "COMMENTAIRE FR";
		final Map<String, String> mapPropertiesFR 
			= new LinkedHashMap<String, String>();
		mapPropertiesFR.put(TEST1, "valeur1");
		mapPropertiesFR.put(TEST2, "valeur2");
		
		// CONSTRUCTION AVEC LE CONSTRUCTEUR COMPLET
		// ET LE LANGAGE ANGLAIS US. ****************************************
		final String nomBaseFichierProperties = NOM_BASE_LABEL;
		LocaleManager.selectionnerLocaleApplication(LOCALE_US_STRING);
		
		// Récupération de la Locale courante auprès du LocaleManager.
		final Locale localeCouranteUS = LocaleManager.getLocaleApplication();
		
		/* Instanciation du Gestionnaire avec le constructeur complet. */
		final GestionnaireProperties gestionnairePropertiesCompletUS 
			= new GestionnaireProperties(
					nomBaseFichierProperties
						, localeCouranteUS
							, commententaireUS
								, mapPropertiesUS);
		
		/* récupération du path du properties auprès du gestionnaire. */
		final Path pathPropCompletUS 
			= gestionnairePropertiesCompletUS.getPathAbsoluFichierProperties();
		/* récupération du File properties auprès du gestionnaire. */
		final File filePropCompletUS 
			= gestionnairePropertiesCompletUS.getFichierProperties();
					
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(
					CONSTRUCTEUR_COMPLET  
						+ localeCouranteUS.toString() 
							+ DEUX_POINTS
							+ pathPropCompletUS.toString());
		}

		/* garantit que le constructeur complet 
		 * alimente pathAbsoluFichierProperties. */
		assertNotNull(
				"Le path du properties US ne doit pas être null : "
					, pathPropCompletUS);
		/* garantit que le constructeur complet 
		 * alimente fichierProperties. */
		assertNotNull(
				"Le file du properties US ne doit pas être null : "
					, filePropCompletUS);
		/* garantit que le constructeur complet 
		 * pointe sur le bon properties. */
		assertEquals(
				"le nom du filePropCompletUS doit être labels_en_US.properties : "
					, "labels_en_US.properties"
						, filePropCompletUS.getName());
		
		
		// CONSTRUCTION AVEC LE CONSTRUCTEUR D'ARITE NULLE
		// ET LE LANGAGE ANGLAIS US. ****************************************
		/* Instanciation du Gestionnaire avec le constructeur d'arité nulle. */
		final GestionnaireProperties gestionnairePropertiesAriteNulle 
			= new GestionnaireProperties();
		/* utilisation des setters. */
		gestionnairePropertiesAriteNulle
			.setNomBaseFichierProperties(nomBaseFichierProperties);
		gestionnairePropertiesAriteNulle.setLocale(localeCouranteUS);
		gestionnairePropertiesAriteNulle.setCommentaire(commententaireUS);
		gestionnairePropertiesAriteNulle.setMapPropertiesInitiales(mapPropertiesUS);
		
		/* récupération du path du properties auprès du gestionnaire. */
		final Path pathPropUsAriteNulle 
			= gestionnairePropertiesAriteNulle
				.getPathAbsoluFichierProperties();
		/* récupération du File properties auprès du gestionnaire. */
		final File filePropUSAriteNulle 
			= gestionnairePropertiesAriteNulle
				.getFichierProperties();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(
					"CONSTRUCTEUR NULL - path absolu du properties avec la Locale "  
						+ localeCouranteUS.toString() 
							+ DEUX_POINTS
							+ pathPropUsAriteNulle.toString());
		}
		
		/* garantit que le constructeur d'arité nulle + setters 
		 * alimente pathAbsoluFichierProperties. */
		assertNotNull(
				"Le path du properties US ne doit pas être null : "
					, pathPropUsAriteNulle);
		/* garantit que le constructeur d'arité nulle + setters 
		 * alimente fichierProperties. */
		assertNotNull(
				"Le file du properties US ne doit pas être null : "
					, filePropUSAriteNulle);
		/* garantit que le constructeur d'arité nulle + setters 
		 * pointe sur le bon properties. */
		assertEquals(
				"le nom du filePropCompletUS doit être labels_en_US.properties : "
					, "labels_en_US.properties"
						, filePropUSAriteNulle.getName());

		
		// CHANGEMENT DE LOCALE AVEC LE CONSTRUCTEUR D'ARITE NULLE
		// PASSAGE AU LANGAGE FRANCAIS FR. ****************************************
		LocaleManager.selectionnerLocaleApplication(LOCALE_FR_STRING);
		// Récupération de la Locale courante auprès du LocaleManager.
		final Locale localeCouranteFR = LocaleManager.getLocaleApplication();
		
		/* utilisation des setters. */
		gestionnairePropertiesAriteNulle.setLocale(localeCouranteFR);
		gestionnairePropertiesAriteNulle.setCommentaire(commententaireFR);
		gestionnairePropertiesAriteNulle.setMapPropertiesInitiales(mapPropertiesFR);
		
		/* récupération du path du properties auprès du gestionnaire. */
		final Path pathPropFRAriteNulle 
			= gestionnairePropertiesAriteNulle
				.getPathAbsoluFichierProperties();
		
		/* récupération du File properties auprès du gestionnaire. */
		final File filePropFRAriteNulle 
			= gestionnairePropertiesAriteNulle
				.getFichierProperties();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(
					"CONSTRUCTEUR NULL - path absolu du properties avec la Locale "  
						+ localeCouranteFR.toString() 
							+ DEUX_POINTS
							+ pathPropFRAriteNulle.toString());
		}
		
		/* garantit que les setters modifient les attributs calculés. */
		assertEquals(
				"le nom du filePropCompletUS doit être labels_fr_FR.properties après setLocale(française) : "
					, "labels_fr_FR.properties"
						, filePropFRAriteNulle.getName());

	} // Fin de testConstructeurs()._______________________________________


	
	/**
	 * Teste l'équivalence entre le constructeur complet 
	 * et le constructeur d'arité nulle + setters.<br/>
	 * <ul>
	 * <li>garantit que les gestionnaires instanciés 
	 * via le constructeur complet ou d'arité nulle + setters 
	 * sont equals().</li>
	 * <li>garantit que les pathAbsoluFichierProperties générés 
	 * par le constructeur complet ou d'arité nulle + setters 
	 * sont equals().</li>
	 * <li>garantit que les fichierProperties générés 
	 * par le constructeur complet ou d'arité nulle + setters 
	 * sont equals().</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@Test
	public void testEgaliteConstructeurs() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE GestionnairePropertiesTest - méthode testEgaliteConstructeurs() ********** ");
		}

		final String commententaireUS = "COMMENTAIRE US";
		final Map<String, String> mapPropertiesUS 
			= new LinkedHashMap<String, String>();
		mapPropertiesUS.put(TEST1, "value1");
		mapPropertiesUS.put(TEST2, "value2");
		
		final String commententaireFR = "COMMENTAIRE FR";
		final Map<String, String> mapPropertiesFR 
			= new LinkedHashMap<String, String>();
		mapPropertiesFR.put(TEST1, "valeur1");
		mapPropertiesFR.put(TEST2, "valeur2");

		final String nomBaseFichierProperties = NOM_BASE_MESSAGE;
		LocaleManager.selectionnerLocaleApplication(LOCALE_FR_STRING);
		// Récupération de la Locale courante auprès du LocaleManager.
		final Locale localeCouranteFR = LocaleManager.getLocaleApplication();
		
		// CONSTRUCTION AVEC LE CONSTRUCTEUR COMPLET
		// ET LE LANGAGE FRANCAIS FR. ****************************************
		/* Instanciation du Gestionnaire avec le constructeur complet. */
		final GestionnaireProperties gestionnairePropertiesCompletFR 
			= new GestionnaireProperties(
					nomBaseFichierProperties
						, localeCouranteFR
							, commententaireFR
								, mapPropertiesFR);
		
		/* récupération du path. */
		final Path pathCompletFR 
			= gestionnairePropertiesCompletFR
				.getPathAbsoluFichierProperties();
		
		/* récupération du File. */
		final File filePropertiesCompletFR 
			= gestionnairePropertiesCompletFR.getFichierProperties();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(
					CONSTRUCTEUR_COMPLET  
						+ localeCouranteFR.toString() 
							+ DEUX_POINTS
							+ pathCompletFR.toString());
		}
		
		
		// CONSTRUCTION AVEC LE CONSTRUCTEUR D'ARITE NULLE + SETTERS
		// ET LE LANGAGE FRANCAIS FR. ****************************************
		/* Instanciation du Gestionnaire avec 
		 * le constructeur d'arité nulle + setters. */
		final GestionnaireProperties gestionnairePropertiesAriteNulleFR 
			= new GestionnaireProperties();
		
		/* utilisation des setters. */
		gestionnairePropertiesAriteNulleFR
			.setNomBaseFichierProperties(nomBaseFichierProperties);
		gestionnairePropertiesAriteNulleFR.setLocale(localeCouranteFR);
		gestionnairePropertiesAriteNulleFR.setCommentaire(commententaireFR);
		gestionnairePropertiesAriteNulleFR.setMapPropertiesInitiales(mapPropertiesFR);
		
		/* récupération du path. */
		final Path pathAriteNulleFR 
			= gestionnairePropertiesAriteNulleFR
				.getPathAbsoluFichierProperties();
		
		/* récupération du File. */
		final File filePropertiesAriteNulleFR 
			= gestionnairePropertiesAriteNulleFR.getFichierProperties();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(
					"CONSTRUCTEUR NULL - path absolu du properties avec la Locale "  
						+ localeCouranteFR.toString() 
							+ DEUX_POINTS
							+ pathAriteNulleFR.toString());
		}

		
		/* garantit que les gestionnaires instanciés via le 
		 * constructeur complet ou d'arité nulle + setters sont equals(). */
		assertEquals("les gestionnaires instanciés via le constructeur complet "
				+ "ou d'arité nulle + setters doivent être equals() : "
				, gestionnairePropertiesCompletFR
					, gestionnairePropertiesAriteNulleFR);
		
		/* garantit que les pathAbsoluFichierProperties générés 
		 * par le constructeur complet ou d'arité nulle + setters 
		 * sont equals(). */
		assertEquals("Les path générés doivent être equals : "
				, pathCompletFR
					, pathAriteNulleFR);
		
		/* garantit que les fichierProperties générés 
		 * par le constructeur complet ou d'arité nulle + setters 
		 * sont equals(). */
		assertEquals("Les fichiers properties générés doivent être equals : "
				, filePropertiesCompletFR
					, filePropertiesAriteNulleFR);

	} // Fin de testEgaliteConstructeurs().________________________________
	

	
	/**
	 * teste la méthode creerFichierSurDisqueEtRemplir().<br/>
	 * <ul>
	 * <li>garantit que creerFichierSurDisqueEtRemplir() 
	 * crée le properties sur disque.</li>
	 * <li>garantit que creerFichierSurDisqueEtRemplir() 
	 * alimente le properties sur disque avec des valeurs en dur.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@Test
	public void testCreerFichierSurDisqueEtRemplir() throws Exception {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE GestionnairePropertiesTest - méthode testCreerFichierSurDisqueEtRemplir() ********** ");
		}

		/* chemin relatif du template de commentaire 
		 * par rapport aux ressources internes src/main/resources. */
		final String cheminRelatifTemplate 
			= "commentaires_properties/commentaires_labels_properties.txt";

		final String commententaireUS 
			= creerCommentaire(cheminRelatifTemplate, Locale.US);
		
		final Map<String, String> mapPropertiesUS 
			= new LinkedHashMap<String, String>();
		mapPropertiesUS.put(TEST1, "value1");
		mapPropertiesUS.put(TEST2, "value2");
		
		final String commententaireFR 
			= creerCommentaire(cheminRelatifTemplate, Locale.FRANCE);
		final Map<String, String> mapPropertiesFR 
			= new LinkedHashMap<String, String>();
		mapPropertiesFR.put(TEST1, "valeur1");
		mapPropertiesFR.put(TEST2, "valeur2");

		final String nomBaseFichierProperties = "properties_test";
		LocaleManager.selectionnerLocaleApplication(LOCALE_FR_STRING);
		// Récupération de la Locale courante auprès du LocaleManager.
		final Locale localeCouranteFR = LocaleManager.getLocaleApplication();
		
		// CONSTRUCTION AVEC LE CONSTRUCTEUR COMPLET
		// ET LE LANGAGE FRANCAIS FR. ****************************************
		/* Instanciation du Gestionnaire avec le constructeur complet. */
		final GestionnaireProperties gestionnairePropertiesCompletFR 
			= new GestionnaireProperties(
					nomBaseFichierProperties
						, localeCouranteFR
							, commententaireFR
								, mapPropertiesFR);
		
		/* récupération du path. */
		final Path pathCompletFR 
			= gestionnairePropertiesCompletFR
				.getPathAbsoluFichierProperties();
		
		/* récupération du File. */
		final File filePropertiesCompletFR 
			= gestionnairePropertiesCompletFR.getFichierProperties();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(
					CONSTRUCTEUR_COMPLET  
						+ localeCouranteFR.toString() 
							+ DEUX_POINTS
							+ pathCompletFR.toString());
		}
		
		// CREATION DU FICHIER SUR DISQUE.
		final File fichierPropertiesFR 
			= gestionnairePropertiesCompletFR.creerFichierSurDisqueEtRemplir();
		
		/* garantit que creerFichierSurDisqueEtRemplir() crée le 
		 * properties sur disque. */
		assertTrue(
				"fichierProperties créé sur disque : "
					, fichierPropertiesFR.exists());
		
		/* garantit que creerFichierSurDisqueEtRemplir() alimente le 
		 * properties sur disque avec des valeurs en dur. */
		assertFalse(
				"fichierProperties créé sur disque non vide : "
					, fichierPropertiesFR.length() == 0);
		
		// CONSTRUCTION AVEC LE CONSTRUCTEUR COMPLET
		// ET LE LANGAGE ANGLAIS US. ****************************************
		/* Instanciation du Gestionnaire avec le constructeur complet. */
		final GestionnaireProperties gestionnairePropertiesCompletUS 
			= new GestionnaireProperties(
					nomBaseFichierProperties
						, Locale.US
							, commententaireUS
								, mapPropertiesUS);
		
		/* récupération du path. */
		final Path pathCompletUS 
			= gestionnairePropertiesCompletUS
				.getPathAbsoluFichierProperties();
		
		/* récupération du File. */
		final File filePropertiesCompletUS 
			= gestionnairePropertiesCompletUS
				.getFichierProperties();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(
					CONSTRUCTEUR_COMPLET  
						+ Locale.US.toString() 
							+ DEUX_POINTS
							+ pathCompletUS.toString());
		}
		
		// CREATION DU FICHIER SUR DISQUE.
		final File fichierPropertiesUS 
			= gestionnairePropertiesCompletUS
				.creerFichierSurDisqueEtRemplir();
		
		/* garantit que creerFichierSurDisqueEtRemplir() crée le 
		 * properties sur disque. */
		assertTrue(
				"fichierProperties créé sur disque : "
					, fichierPropertiesUS.exists());
		
		/* garantit que creerFichierSurDisqueEtRemplir() alimente le 
		 * properties sur disque avec des valeurs en dur. */
		assertFalse(
				"fichierProperties créé sur disque non vide : "
					, fichierPropertiesUS.length() == 0);

	} // Fin de testCreerFichierSurDisqueEtRemplir().______________________
	

	
	/**
	 * .<br/>
	 * <ul>
	 * <li>.</li>
	 * </ul>
	 *
	 * @param pCheminRelatifCommentaire : String : 
	 * chemin relatif du template par rapport aux 
	 * ressources internes src/main/resources.<br/>
	 * @param pLocale : Locale.<br/>
	 * 
	 * @return
	 * @throws Exception :  :  .<br/>
	 */
	private static String creerCommentaire(
			final String pCheminRelatifCommentaire
				, final Locale pLocale) throws Exception {
		
		/* variable à substituer dans le template. */
		final String[] variables = {"{$Locale}", "{$langue}"};
		/* valeur de substitution. */
		final String[] substituants = {pLocale.toString(), LocaleManager.fournirLangueEtPaysEnFrancais(pLocale)};
				
		final String commentaire 
			= gestionnaireTemplate.fournirTemplateSubstitueSousFormeString(
					pCheminRelatifCommentaire, variables, substituants);
		
		return commentaire;
		
	} // Fin de creerCommentaire(...)._____________________________________

	
	
	/**
	 * Avant tout test de la présente classe.<br/>
	 * @throws Exception 
	 */
	@BeforeClass
	public static final void setUpBeforeClass() throws Exception {
		gestionnaireTemplate = new GestionnaireTemplates();
	} // Fin de setUpBeforeClass().________________________________________
	
	
	
} // FIN DE LA CLASSE GestionnairePropertiesTest.----------------------------
