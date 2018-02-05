package levy.daniel.application.apptechnic.generationcode.generationfichiersjava;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE ABSTRAITE <b>AbstractGenerateurInterfaceSeule</b> :<br/>
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
public class AbstractGenerateurInterfaceSeule 
				extends AbstractGenerateurFichiersJava {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ABSTRACT_GENERATEUR_INTERFACE_SEULE : String :<br/>
	 * "Classe AbstractGenerateurInterfaceSeule".<br/>
	 */
	public static final String CLASSE_ABSTRACT_GENERATEUR_INTERFACE_SEULE 
		= "Classe AbstractGenerateurInterfaceSeule";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(AbstractGenerateurInterfaceSeule.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR AbstractGenerateurInterfaceSeule() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractGenerateurInterfaceSeule() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void genererFichiersJava() throws IOException {

		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void alimenterAttributs() {

		// TODO Auto-generated method stub
		
	}
	
	
	
} // FIN DE LA CLASSE AbstractGenerateurInterfaceSeule.----------------------
