package levy.daniel.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.GenerateurCode;

/**
 * class Application :<br/>
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
 * @since 5 janv. 2018
 *
 */
public final class Application {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(Application.class);


	// *************************METHODES************************************/	
	
	 /**
	 * method CONSTRUCTEUR Application() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	private Application() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	 * method main(
	 * String[] pArgs) :<br/>
	 * <ul>
	 * <li>.</li>
	 * <li>.</li>
	 * </ul>
	 *
	 * @param pArgs :  :  .<br/>
	 * @throws Exception 
	 */
	public static void main(
			final String[] pArgs) throws Exception {
		
		final GenerateurCode generateurCode = new GenerateurCode();
		
		generateurCode.genererObjetMetier("profil", "IProfil", "profilSimple");
		

	} // Fin de main(...)._________________________________________________
	


} // FIN DE LA CLASSE Application.-------------------------------------------
