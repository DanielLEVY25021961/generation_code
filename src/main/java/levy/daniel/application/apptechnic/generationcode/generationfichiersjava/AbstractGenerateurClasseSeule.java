package levy.daniel.application.apptechnic.generationcode.generationfichiersjava;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE ABSTRAITE <b>AbstractGenerateurClasseSeule</b> :<br/>
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
 * @since 4 févr. 2018
 *
 */
public abstract class AbstractGenerateurClasseSeule 
						extends AbstractGenerateurFichiersJava {

	// ************************ATTRIBUTS************************************/
	/**
	 * CLASSE_ABSTRACT_GENERATEUR_CLASSE_SEULE : String :<br/>
	 * "Classe AbstractGenerateurClasseSeule".<br/>
	 */
	public static final String CLASSE_ABSTRACT_GENERATEUR_CLASSE_SEULE 
		= "Classe AbstractGenerateurClasseSeule";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(AbstractGenerateurClasseSeule.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR AbstractGenerateurClasseSeule() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractGenerateurClasseSeule() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
} // FIN DE LA CLASSE AbstractGenerateurClasseSeule.-------------------------
