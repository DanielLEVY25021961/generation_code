package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences;

import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;


/**
 * CLASSE GestionnaireLabelsPropertiesTest :<br/>
 * Test JUnit de la classe GestionnaireLabelsProperties.<br/>
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
 * @since 22 juil. 2018
 *
 */
public class GestionnaireLabelsPropertiesTest {
	
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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GestionnaireLabelsPropertiesTest.class);


	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public GestionnaireLabelsPropertiesTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	

	/**
	 * teste la méthode creerFichierProperties(Locale).<br/>
	 * <ul>
	 * <li>.</li>
	 * </ul>
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	@Test
	public void testcreerFichierProperties() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE GestionnaireLabelsPropertiesTest - méthode testcreerFichierProperties() ********** ");
		}

		LocaleManager.selectionnerLocaleApplication(LOCALE_US_STRING);
		
		// Récupération de la Locale courante auprès du LocaleManager.
		final Locale localeCouranteUS = LocaleManager.getLocaleApplication();
				
		GestionnaireLabelsProperties.creerFichierProperties(localeCouranteUS);
		GestionnaireLabelsProperties.creerFichierProperties(Locale.FRANCE);
		
		assertTrue("BIDON : ", 1 == 1);
		
	} // Fin de testcreerFichierProperties().______________________________

	
	
} // FIN DE LA CLASSE GestionnaireLabelsPropertiesTest.----------------------
