package levy.daniel.application.apptechnic.configurationmanagers;

import static org.junit.Assert.*;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


/**
 * class LocaleManagerTest :<br/>
 * Test JUnit de la classe LocaleManager.<br/>
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
 * @since 2 oct. 2017
 *
 */
public class LocaleManagerTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * AFFICHAGE_GENERAL : Boolean :<br/>
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	
	/**
	 * LOCALE_PAR_DEFAUT : Locale :<br/>
	 * Locale de la plateforme par défaut.<br/>
	 */
	public static final Locale LOCALE_PAR_DEFAUT = Locale.getDefault();

	
	/**
	 * LOCALE_FR : Locale :<br/>
	 * new Locale("fr", "FR").<br/>
	 */
	public static final Locale LOCALE_FR = new Locale("fr", "FR");

	
	/**
	 * LOCALE_US : Locale :<br/>
	 * new Locale("en", "US").<br/>
	 */
	public static final Locale LOCALE_US = new Locale("en", "US");
	

	/**
	 * LOCALE_PLATEFORME : String :<br/>
	 * "la Locale de la plateforme : ".<br/>
	 */
	public static final String LOCALE_PLATEFORME 
		= "la Locale de la plateforme : ";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(LocaleManagerTest.class);

	// *************************METHODES************************************/

	 /**
	 * method CONSTRUCTEUR LocaleManagerTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public LocaleManagerTest() {
		super();
	} // Fin de  CONSTRUCTEUR D'ARITE NULLE._______________________________


	
	/**
	 * method testLocaleManager() :<br/>
	 * <ul>
	 * Teste le bon fonctionnement du LocaleManager.<br/>
	 * <li>garantit que getLocaleApplication() retourne initialement
	 * la Locale de la plateforme.</li>
	 * <li>garantit que getLocaleApplication() retourne un singleton.</li>
	 * <li>garantit que setLocaleApplication(Locale pLocale) 
	 * permet de changer la locale.</li>
	 * </ul>
	 */
	@Test
	public void testLocaleManager() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		final Locale localeInitiale1 
			= LocaleManager.getLocaleApplication();
		
		/* garantit que getLocaleApplication() retourne initialement
		 * la Locale de la plateforme. */
		assertEquals("getLocaleApplication() doit retourner "
				+ LOCALE_PLATEFORME
					, LOCALE_PAR_DEFAUT, localeInitiale1);
		
		final Locale localeInitiale2 
		= LocaleManager.getLocaleApplication();
		
		/* garantit que getLocaleApplication() retourne un singleton. */
		assertTrue(
				"getLocaleApplication() doit retourner un singleton : "
					, localeInitiale1 == localeInitiale2); // NOPMD by dan on 02/10/17 19:52
		
		/* Change la Locale. */
		/* garantit que setLocaleApplication(Locale pLocale) 
		 * permet de changer la locale. */
		LocaleManager.setLocaleApplication(LOCALE_US);
		
		final Locale locale1 
		= LocaleManager.getLocaleApplication();
		
		assertEquals("La nouvelle Locale doit être la Locale US : "
				, LOCALE_US, locale1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LOCALE CHANGEE : " 
					+ locale1.getDisplayName());
		}
		
		final Locale locale2 
		= LocaleManager.getLocaleApplication();
		
		/* garantit que getLocaleApplication() retourne un singleton. */
		assertTrue(
				"getLocaleApplication() doit retourner un singleton : "
					, locale1 == locale2); // NOPMD by dan on 02/10/17 19:52
		
	} // Fin de testLocaleManager()._______________________________________
	
	
	
	/**
	 * method testRecupererLocaleIHM() :<br/>
	 * <ul>
	 * Teste la méthode recupererLocaleIHM(String pLocaleString) :<br/>
	 * <li>garantit que recupererLocaleIHM(null) retourne 
	 * la Locale de la plateforme.</li>
	 * <li>garantit que recupererLocaleIHM(inexistant) retourne 
	 * la Locale de la plateforme.</li>
	 * <li>garantit que recupererLocaleIHM(existant) retourne 
	 * la Locale existante voulue.</li>
	 * </ul>
	 */
	@Test
	public void testRecupererLocaleIHM() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		final Locale localeDefaut 
			= LocaleManager.recupererLocaleIHM(null);
		
		/* garantit que recupererLocaleIHM(null) retourne 
		 * la Locale de la plateforme. */
		assertEquals("recupererLocaleIHM(null) doit retourner "
				+ LOCALE_PLATEFORME
					, LOCALE_PAR_DEFAUT, localeDefaut);
		
		final String localeBresilString = "Portuguais (Brésil)";
		final Locale localeBresil 
			= LocaleManager.recupererLocaleIHM(localeBresilString);
		
		/* garantit que recupererLocaleIHM(inexistant) retourne 
		 * la Locale de la plateforme. */
		assertEquals("recupererLocaleIHM(inexistant) doit retourner "
				+ LOCALE_PLATEFORME
					, LOCALE_PAR_DEFAUT, localeBresil);
		
		
		final String localeFranceString = "Français (France)";
		final Locale localeFrance 
			= LocaleManager.recupererLocaleIHM(localeFranceString);
		
		/* garantit que recupererLocaleIHM(existant) retourne 
		 * la Locale existante voulue. */
		assertEquals("recupererLocaleIHM(existant) doit retourner "
				+ "la Locale voule : "
					, LOCALE_FR, localeFrance);
		
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("LOCALE FRANCE : " 
					+ localeFrance.getDisplayName());
			System.out.println("LOCALE US : " 
						+ LOCALE_US.getDisplayName());
		}
		
	} // Fin de testRecupererLocaleIHM().__________________________________


	
	/**
	 * method testRecupererLocaleBase() :<br/>
	 * <ul>
	 * Teste la méthode recupererLocaleBase(String pLangue
	 * , String pPays) : <br/>
	 * <li>garantit que recupererLocaleBase(blank) retourne 
	 * la Locale de la plateforme.</li>
	 * <li>garantit que recupererLocaleBase(incohérent) retourne 
	 * la Locale de la plateforme.</li>
	 * <li>garantit que recupererLocaleBase(inexistant) retourne 
	 * la Locale de la plateforme.</li>
	 * </ul>
	 */
	@Test
	public void testRecupererLocaleBase() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		final Locale localeNull 
			= LocaleManager.recupererLocaleBase(null, "  ");
		
		/* garantit que recupererLocaleBase(blank) retourne 
		 * la Locale de la plateforme. */
		assertEquals("recupererLocaleBase(blank) doit retourner "
				+ LOCALE_PLATEFORME
					, LOCALE_PAR_DEFAUT, localeNull);
		
		final Locale localeIncoherent 
		= LocaleManager.recupererLocaleBase("fr", "DE");
		
		/* garantit que recupererLocaleBase(incohérent) retourne 
		 * la Locale de la plateforme. */
		assertEquals("recupererLocaleBase(incohérent) doit retourner "
				+ LOCALE_PLATEFORME
					, LOCALE_PAR_DEFAUT, localeIncoherent);
		
		final Locale localeInexistant 
		= LocaleManager.recupererLocaleBase("de", "DE");
		
		/* garantit que recupererLocaleBase(inexistant) retourne 
		 * la Locale de la plateforme. */
		assertEquals("recupererLocaleBase(inexistant) doit retourner "
				+ LOCALE_PLATEFORME
					, LOCALE_PAR_DEFAUT, localeInexistant);
		
		final Locale localeExistant 
			= LocaleManager.recupererLocaleBase("en", "US");
		
		/* garantit que recupererLocaleBase(existant) retourne 
		 * la Locale voulue. */
		assertEquals("recupererLocaleBase(existant) doit retourner "
				+ "la Locale voulue : "
					, LOCALE_US, localeExistant);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println("LOCALE EXISTANTE : " 
					+ localeExistant.getDisplayName());
			System.out.println();
			
			System.out.println("LOCALE ALLEMAGNE : " 
					+ Locale.GERMANY.getDisplayName());
			System.out.println("LANGUE LOCALE ALLEMAGNE : " 
					+ Locale.GERMANY.getLanguage());
			System.out.println("PAYS LOCALE ALLEMAGNE : " 
					+ Locale.GERMANY.getCountry());
			System.out.println("LOCALE US : " 
						+ LOCALE_US.getDisplayName());
			
		}
		
	} // Fin de testRecupererLocaleBase()._________________________________
	
	
	
	/**
	 * method testGetLocaleParDefaut() :<br/>
	 * <ul>
	 * Teste la méthode getLocaleParDefaut() : <br/>
	 * </ul>
	 */
	@Test
	public void testGetLocaleParDefaut() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		final Locale localeParDefaut 
			= LocaleManager.getLocaleParDefaut();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println("Locale toString() : " 
					+ localeParDefaut.toString());
			System.out.println("Langage : " 
					+ localeParDefaut.getLanguage());
			System.out.println("Pays : " 
					+ localeParDefaut.getCountry());
			System.out.println("Variant : " 
					+ localeParDefaut.getVariant());
			System.out.println("getDisplayCountry() : " 
					+ localeParDefaut.getDisplayCountry());
			System.out.println("getDisplayLanguage() : " 
					+ localeParDefaut.getDisplayLanguage());
			System.out.println("getDisplayName() : " 
					+ localeParDefaut.getDisplayName());
			
		}
		
	} // Fin de testGetLocaleParDefaut().__________________________________
	

	
} // FIN DE LA CLASSE LocaleManagerTest.-------------------------------------
