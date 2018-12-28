package levy.daniel.application.model.utilitaires.date;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


/**
 * CLASSE DateUtilTest :<br/>
 * Test JUnit de la classe 
 * {@link levy.daniel.application.model.utilitaires.date.DateUtil}}.<br/>
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
 * @since 18 mai 2018
 *
 */
public class DateUtilTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * AFFICHAGE_GENERAL : Boolean :<br/>
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	/**
	 * UNUSED : String :<br/>
	 * "unused".<br/>
	 */
	private static final String UNUSED ="unused";

	/**
	 * SEP_MOINS_ARERE : String :<br/>
	 * " - ".<br/>
	 */
	public static final String SEP_MOINS_ARERE = " - ";
	
	/**
	 * ETOILES : String :<br/>
	 * " ****************** ".<br/>
	 */
	public static final String ETOILES 
		= " ****************** ";
	
	/**
	 * CLASSE_DATEUTILTEST : String :<br/>
	 * "CLASSE DateUtilTest".<br/>
	 */
	public static final String CLASSE_DATEUTILTEST 
		= "CLASSE DateUtilTest";

	
	/**
	 * METHOD_TEST_FORMAT : String :<br/>
	 * "Méthode testFormat()".<br/>
	 */
	public static final String METHOD_TEST_FORMAT 
		= "Méthode testFormat()";

	/**
	 * METHOD_TEST_PARSE : String :<br/>
	 * "Méthode testParse()".<br/>
	 */
	public static final String METHOD_TEST_PARSE 
		= "Méthode testParse()";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(DateUtilTest.class);
	

	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR DateUtilTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public DateUtilTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * teste la méthode 
	 * {@link DateUtil#format(java.time.LocalDate)}.<br/>
	 * garantit que :
	 * <ul>
	 * <li>format(null) retourne null.</li>
	 * <li>format(LocalDate valide) retourne une chaine 
	 * de caractères valide.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFormat() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* format(null) retourne null. */
		final String dateStringNull = DateUtil.format(null);
		
		assertNull(
				"DateUtil.format(null) doit retourner null : "
					, dateStringNull);
		
		/* format(date valide) retourne une chaine de caractères valide. */
		final String dateStringReel 
			= DateUtil.format(LocalDate.of(1961, 2, 25));
		
		assertTrue(
				"La date String doit être valide : "
					, DateUtil.validDate(dateStringReel));
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
			System.out.print(ETOILES);
			System.out.println(CLASSE_DATEUTILTEST + SEP_MOINS_ARERE 
					+ METHOD_TEST_FORMAT + ETOILES);
			System.out.println("LocalDate formatée : " + dateStringReel);
		}
				
	} // Fin de testFormat().______________________________________________


	
	/**
	 * teste la méthode 
	 * {@link DateUtil#parse(String)}.<br/>
	 * garantit que :
	 * <ul>
	 * <li>parse(null) retourne null.</li>
	 * <li>parse(dateString invalide) retourne null.</li>
	 * <li>parse(dateString valide) retourne une LocalDate valide.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testParse() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* parse(null) retourne null. */
		final String stringNull = null;
		LocalDate dateNull = null;
		dateNull = DateUtil.parse(stringNull);
				
		assertNull(
				"DateUtil.parse(stringNull) == null : "
					, dateNull);

		
		/* parse(dateString invalide) retourne null. */
		final String stringInvalide = "25/02/1961";
		LocalDate dateInvalide = null;
		dateInvalide = DateUtil.parse(stringInvalide);
		
		assertNull(
				"DateUtil.parse(stringInvalide) == null : "
					, dateInvalide);
		
		/* parse(dateString valide) retourne LocalDate valide. */
		final String stringValide = "25 février 1961";
		LocalDate dateValide = null;
		dateValide = DateUtil.parse(stringValide);
		
		assertNotNull(
				"DateUtil.parse(stringValide) == LocalDateValide : "
					, dateValide);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
			System.out.print(ETOILES);
			System.out.println(CLASSE_DATEUTILTEST + SEP_MOINS_ARERE 
					+ METHOD_TEST_PARSE + ETOILES);
			System.out.println("LocalDate parsée : " + dateValide.toString());
			
		}
		
	} // Fin de testParse()._______________________________________________
	

		
	/**
	 * teste la méthode fournirLodalDate(String).<br/>
	 * <ul>
	 * <li>garantit que fournirLocalDate(null) retourne null.</li>
	 * <li>garantit que fournirLocalDate("dd MMMM yyyy") (12 février 1960) 
	 * n'est pas null et vaut la bonne date.</li>
	 * <li>garantit que fournirLocalDate("dd/MM/yyyy") (12/02/1960) 
	 * n'est pas null et vaut la bonne date.</li>
	 * <li>garantit que fournirLocalDate("yyyy-MMM-dd") (1960-févr.-12) 
	 * n'est pas null et vaut la bonne date.</li>
	 * <li>garantit que fournirLocalDate("yyyy-MM-dd") (1960-02-12) 
	 * n'est pas null et vaut la bonne date.</li>
	 * <li>garantit que fournirLocalDate(dateInexistante) retourne null.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirLocalDate() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE ContactSimpleDTOTest - méthode testFournirLocalDate() ********** ");		
		}
		
		final DateTimeFormatter dateFormatterAffichage 
			= DateTimeFormatter.ofPattern("dd MMMM yyyy")
				.withLocale(Locale.getDefault());
		
		final String dateNullString = null;
		final String dateAffichageString = "12 février 1960";
		final String dateSaisieString = "12/02/1960";
		final String dateIso1String = "1960-févr.-12";
		final String dateIsoString = "1960-02-12";
		final String dateInexistanteString = "toto";
		
		final LocalDate dateNull = DateUtil.fournirLocalDate(dateNullString);
		final LocalDate dateAffichage = DateUtil.fournirLocalDate(dateAffichageString);
		final LocalDate dateSaisie = DateUtil.fournirLocalDate(dateSaisieString);
		final LocalDate dateIso1 = DateUtil.fournirLocalDate(dateIso1String);
		final LocalDate dateIso = DateUtil.fournirLocalDate(dateIsoString);
		final LocalDate dateInexistante = DateUtil.fournirLocalDate(dateInexistanteString);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			if (dateNull != null) {
				System.out.println("dateNull (null) = " + dateFormatterAffichage.format(dateNull));
			} else {
				System.out.println("dateNull (null) = null");
			}
			
			if (dateAffichage != null) {
				System.out.println("dateAffichage (12 février 1960) = " + dateFormatterAffichage.format(dateAffichage));
			} else {
				System.out.println("dateAffichage (12 février 1960) = null");
			}
			
			if (dateSaisie != null) {
				System.out.println("dateSaisie (12/02/1960) = " + dateFormatterAffichage.format(dateSaisie));
			} else {
				System.out.println("dateSaisie (12/02/1960) = null");
			}
			
			if (dateIso1 != null) {
				System.out.println("dateIso1 (1960-févr.-12) = " + dateFormatterAffichage.format(dateIso1));
			} else {
				System.out.println("dateIso1 (1960-févr.-12) = null");
			}
			
			if (dateIso != null) {
				System.out.println("dateIso (1960-02-12) = " + dateFormatterAffichage.format(dateIso));
			} else {
				System.out.println("dateIso (1960-02-12) = null");
			}
			
			if (dateInexistante != null) {
				System.out.println("dateInexistante (toto) = " + dateFormatterAffichage.format(dateInexistante));
			} else {
				System.out.println("dateInexistante (toto) = null");
			}
			
		}

		/* garantit que fournirLocalDate(null) retourne null. */
		assertNull("dateNull doit être null : ", dateNull);
		
		/* garantit que fournirLocalDate("dd MMMM yyyy") (12 février 1960) n'est pas null et vaut la bonne date. */
		assertNotNull("dateAffichage ne doit pas être null : ", dateAffichage);
		assertEquals("", LocalDate.of(1960, 2, 12), dateAffichage);
		
		/* garantit que fournirLocalDate("dd/MM/yyyy") (12/02/1960) n'est pas null et vaut la bonne date. */
		assertNotNull("dateSaisie ne doit pas être null : ", dateSaisie);
		assertEquals("", LocalDate.of(1960, 2, 12), dateSaisie);
		
		/* garantit que fournirLocalDate("yyyy-MMM-dd") (1960-févr.-12) n'est pas null et vaut la bonne date. */
		assertNotNull("dateIso1 ne doit pas être null : ", dateIso1);
		assertEquals("", LocalDate.of(1960, 2, 12), dateIso1);
		
		/* garantit que fournirLocalDate("yyyy-MM-dd") (1960-02-12) n'est pas null et vaut la bonne date. */
		assertNotNull("dateIso ne doit pas être null : ", dateIso);
		assertEquals("", LocalDate.of(1960, 2, 12), dateIso);
		
		/* garantit que fournirLocalDate(dateInexistante) retourne null. */
		assertNull("dateInexistante doit être null : ", dateInexistante);
		
	} // Fin de testFournirLocalDate().____________________________________

	
	
} // FIN DE LA CLASSE DateUtilTest.------------------------------------------
