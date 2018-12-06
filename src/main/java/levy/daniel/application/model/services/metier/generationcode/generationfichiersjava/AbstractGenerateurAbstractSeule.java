package levy.daniel.application.model.services.metier.generationcode.generationfichiersjava;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE ABSTRAITE <b>AbstractGenerateurAbstractSeule</b> :<br/>
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
 * @since 4 févr. 2018
 *
 */
public abstract class AbstractGenerateurAbstractSeule 
				extends AbstractGenerateurFichiersJava {

	// ************************ATTRIBUTS************************************/
	/**
	 * CLASSE_ABSTRACT_GENERATEUR_ABSTRACT_SEULE : String :<br/>
	 * "Classe AbstractGenerateurAbstractSeule".<br/>
	 */
	public static final String CLASSE_ABSTRACT_GENERATEUR_ABSTRACT_SEULE 
		= "Classe AbstractGenerateurAbstractSeule";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(AbstractGenerateurAbstractSeule.class);

	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR AbstractGenerateurAbstractSeule() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractGenerateurAbstractSeule() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
} // FIN DE LA CLASSE AbstractGenerateurAbstractSeule.-----------------------
