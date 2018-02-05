package levy.daniel.application.apptechnic.generationcode.ecriveurs;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.IGenerateur;
import levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationmetier.generationobjetmetiersimple.GenerateurMetierToutAbstract;


/**
 * CLASSE ABSTRAITE <b>AbstractEcriveur</b> :<br/>
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
 * @since 28 janv. 2018
 *
 */
public abstract class AbstractEcriveur implements IEcriveur {

	// ************************ATTRIBUTS************************************/
	/**
	 * CLASSE_ABSTRACT_ECRIVEUR : String :<br/>
	 * "Classe AbstractEcriveur".<br/>
	 */
	public static final String CLASSE_ABSTRACT_ECRIVEUR 
		= "Classe AbstractEcriveur";

	
	/**
	 * generateurCode : IGenerateur :<br/>
	 * Generateur de code 
	 * (GenerateurMetierToutAbstract, GenerateurDaoToutAbstract, ...).<br/>
	 */
	protected transient IGenerateur generateurCode;

	
	/**
	 * nomPackage : String :<br/>
	 * <b>nom du package à créer dans chaque couche</b>.<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "profil".<br/>
	 */
	protected transient String nomPackage;
	
	
	/**
	 * conceptModelise : String :<br/>
	 * concept modélisé.<br/>
	 */
	protected transient String conceptModelise;
	

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
	= LogFactory.getLog(AbstractEcriveur.class);
	

	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR AbstractEcriveur() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractEcriveur() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void ecrireCode(
			File pFile, GenerateurMetierToutAbstract pGenerateurMetierToutAbstract);


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherListeString(
			final List<String> pListe) {
					
			/* Retourne null si pListe est null. */
			if (pListe == null) {
				return null;
			}
			
			final StringBuilder stb = new StringBuilder();
			
			for (final String ligne : pListe) {
				
				stb.append(ligne);
				stb.append(NEWLINE);
				
			}
			
			return stb.toString();
			
	} // Fin de afficherListeString(
	 // List<String> pListe).______________________________________________

	

} // FIN DE LA CLASSE AbstractEcriveur.--------------------------------------
