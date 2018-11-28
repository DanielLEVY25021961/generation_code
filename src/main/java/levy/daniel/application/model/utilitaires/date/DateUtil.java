package levy.daniel.application.model.utilitaires.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * CLASSE DateUtil :<br/>
 * UTILITAIRE pour les <b>java.time.LocalDate</b>.<br/>
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
public final class DateUtil {

	// ************************ATTRIBUTS************************************/

	/**
	 * datePattern : String :<br/>
	 * Motif pour la lecture/écriture des Dates.<br/>
	 * Par exemple : "dd MMMM yyyy" pour 17 mars 1984.<br/>
	 */
	private static String datePattern = "dd MMMM yyyy";

	
	/**
	 * DATE_FORMATTER : DateTimeFormatter :<br/>
	 * DateTimeFormatter pour la lecture/écriture des Dates.<br/>
	 */
	private static final DateTimeFormatter DATE_FORMATTER 
		= DateTimeFormatter.ofPattern(datePattern);

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(DateUtil.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR DateUtil() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	private DateUtil() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * <ul>
	 * <li><b>Formate</b> une <i>java.time.LocalDate</i> 
	 * en </i>String</i>.</li>
	 * <li>Retourne la <b>LocalDate</b> pDate sous forme 
	 * de <b>String</b>.</li>
	 * <li>Le Motif {@link DateUtil#getDatePattern} 
	 * est utilisé pour le formatage.</li>
	 * </ul>
	 * retourne null si pDate == null.<br/>
	 * <br/>
	 *
	 * @param pDate : java.time.LocalDate : date à formater en String.<br/>
	 * 
	 * @return : String : la date formatée selon 
	 * {@link DateUtil#getDatePattern}.<br/>
	 */
	public static String format(
			final LocalDate pDate) {
		
		if (pDate == null) {
			return null;
		}
		
		return DATE_FORMATTER.format(pDate);
		
	} // Fin de format(...)._______________________________________________
	


	/**
	 * <ul>
	 * <li><b>Parse</b> une <i>String</i> en 
	 * <i>java.time.LocalDate</i>.</li>
	 * <li>Convertit une String respectant le format 
	 * {@link DateUtil#getDatePattern} en un 
	 * Objet {@link java.time.LocalDate}.</li>
	 * </ul>
	 * Retourne null si pDateString == null.<br/>
	 * Retourne null si pDateString n'a pu être convertie 
	 * car non conforme au motif {@link DateUtil#getDatePattern}.<br/>
	 * <br/>
	 *
	 * @param pDateString : String : date sous forme de string.<br/>
	 * 
	 * @return LocalDate.<br/>
	 */
	public static LocalDate parse(
			final String pDateString) {
		
		try {
			
			if (pDateString != null) {
				return DATE_FORMATTER.parse(pDateString, LocalDate::from);
			}
			
			return null;
		}
		catch (DateTimeParseException e) {
			return null;
		}
		
	} // Fin de parse(...).________________________________________________


	
	/**
	 * <ul>
	 * <li>Vérifie si une date sous forme de String est conforme 
	 * au MOTIF {@link DateUtil#getDatePattern}.</li>
	 * </ul>
	 *
	 * @param pDateString
	 * @return true if the String is a valid date
	 */
	public static boolean validDate(
			final String pDateString) {
		// Try to parse the String.
		return DateUtil.parse(pDateString) != null;
	} // Fin de validDate(...).____________________________________________



	/**
	 * method getDatePattern() :<br/>
	 * Getter du Motif pour la lecture/écriture des Dates.<br/>
	 * <br/>
	 *
	 * @return datePattern : String.<br/>
	 */
	public static String getDatePattern() {
		return datePattern;
	} // Fin de getDatePattern().__________________________________________



	/**
	* method setDatePattern(
	* String pDatePattern) :<br/>
	* Setter du Motif pour la lecture/écriture des Dates.<br/>
	* <br/>
	*
	* @param pDatePattern : String : 
	* valeur à passer à datePattern.<br/>
	*/
	public static void setDatePattern(
			final String pDatePattern) {
		datePattern = pDatePattern;
	} // Fin de setDatePattern(...)._______________________________________



	/**
	 * method getDateFormatter() :<br/>
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return DATE_FORMATTER : DateTimeFormatter.<br/>
	 */
	public static DateTimeFormatter getDateFormatter() {
		return DATE_FORMATTER;
	}
	   

	
	
	
} // FIN DE LA CLASSE DateUtil.----------------------------------------------
