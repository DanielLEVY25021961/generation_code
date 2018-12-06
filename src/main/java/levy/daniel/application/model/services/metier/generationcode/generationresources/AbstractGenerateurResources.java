package levy.daniel.application.model.services.metier.generationcode.generationresources;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.generationcode.AbstractGenerateur;



/**
 * CLASSE ABSTRAITE <b>AbstractGenerateurResources</b> :<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 24 janv. 2018
 *
 */
public abstract class AbstractGenerateurResources extends AbstractGenerateur {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ABSTRACT_GENERATEUR_RESOURCES : String :<br/>
	 * "Classe AbstractGenerateurResources".<br/>
	 */
	public static final String CLASSE_ABSTRACT_GENERATEUR_RESOURCES 
		= "Classe AbstractGenerateurResources";

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(AbstractGenerateurResources.class);

	
	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR AbstractGenerateurResources() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractGenerateurResources() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void genererHook() throws Exception {
		// TODO Auto-generated method stub

	}

	

} // FIN DE LA CLASSE AbstractGenerateurResources.-------------------------
