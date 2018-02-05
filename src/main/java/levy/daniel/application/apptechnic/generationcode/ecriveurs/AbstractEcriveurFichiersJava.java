package levy.daniel.application.apptechnic.generationcode.ecriveurs;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationmetier.generationobjetmetiersimple.GenerateurMetierToutAbstract;


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
	 * {@inheritDoc}
	 */
	@Override
	public abstract void ecrireCode(
			File pFile, GenerateurMetierToutAbstract pGenerateurMetierToutAbstract);

	

} // FIN DE LA CLASSE AbstractEcriveurFichiersJava.--------------------------
