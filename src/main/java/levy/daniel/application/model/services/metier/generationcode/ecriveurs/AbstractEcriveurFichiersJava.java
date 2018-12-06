package levy.daniel.application.model.services.metier.generationcode.ecriveurs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * CLASSE ABSTRAITE <b>AbstractEcriveurFichiersJava</b> :<br/>
 * <ul>
 * </ul>
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
 * @since 28 janv. 2018
 *
 */
public abstract class AbstractEcriveurFichiersJava 
							extends AbstractEcriveur {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ABSTRACT_ECRIVEUR_FICHIERS_JAVA : String :<br/>
	 * "Classe AbstractEcriveurFichiersJava".<br/>
	 */
	public static final String CLASSE_ABSTRACT_ECRIVEUR_FICHIERS_JAVA 
		= "Classe AbstractEcriveurFichiersJava";

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(AbstractEcriveurFichiersJava.class);

	// *************************METHODES************************************/

	 /**
	 * method CONSTRUCTEUR AbstractEcriveurFichiersJava() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractEcriveurFichiersJava() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method fournirTypeFichierJava() :<br/>
	 * <ul>
	 * <li>Fournit le type de fichier Java pour la ligne finale.</li>
	 * <li>Par exemple "L'INTERFACE " pour une interface java.</li>
	 * <li>Par exemple "LA CLASSE " pour une classe java.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 */
	protected abstract String fournirTypeFichierJava();

	

} // FIN DE LA CLASSE AbstractEcriveurFichiersJava.--------------------------
