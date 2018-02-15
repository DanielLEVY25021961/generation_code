package levy.daniel.application.apptechnic.generationcode.generationfichiersjava;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.AbstractGenerateur;

/**
 * CLASSE ABSTRAITE <b>AbstractGenerateurFichiersJava</b> :<br/>
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
 * @since 22 janv. 2018
 *
 */
public abstract class AbstractGenerateurFichiersJava extends AbstractGenerateur {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_ABSTRACT_GENERATEUR_FICHIERSJAVA : String :<br/>
	 * "Classe AbstractGenerateurFichiersJava".<br/>
	 */
	public static final String CLASSE_ABSTRACT_GENERATEUR_FICHIERSJAVA 
		= "Classe AbstractGenerateurFichiersJava";

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(AbstractGenerateurFichiersJava.class);

	
	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR AbstractGenerateurFichiersJava() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractGenerateurFichiersJava() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	 * method genererFichiersJava() :<br/>
	 * <ul>
	 * <li>crée le fichier vide et alimente this.interfaceJava.</li>
	 * <li>crée le fichier vide et alimente this.abstractClass.</li>
	 * <li>crée le fichier vide et alimente this.concreteClass.</li>
	 * <li>crée le fichier vide et alimente this.objetMetierForm.</li>
	 * </ul>
	 *
	 * @throws IOException 
	 */
	@Override
	protected abstract void genererFichiersJava() throws IOException;
	
	
	
} // FIN DE LA CLASSE AbstractGenerateurFichiersJava.----------------------
