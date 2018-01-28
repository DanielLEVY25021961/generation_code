package levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationdao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.generationcode.AbstractGenerateur;

/**
 * class GenerateurDao :<br/>
 * Générateur pour les DAOs.<br/>
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
 * @since 17 janv. 2018
 *
 */
public class GenerateurDao extends AbstractGenerateur {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_GENERATEUR_DAO : String :<br/>
	 * "Classe GenerateurDao".<br/>
	 */
	public static final String CLASSE_GENERATEUR_DAO 
		= "Classe GenerateurDao";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(GenerateurDao.class);

	// *************************METHODES************************************/
	
	 /**
	 * method CONSTRUCTEUR GenerateurDao() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>alimente this.pathPackage en demandant le chemin 
	 * du package <b>model.dao.metier</b> au 
	 * <b>BundleConfigurationProjetManager</b>.</li>
	 * </ul>
	 * <br/>
	 */
	public GenerateurDao() {
		
		super();
		
		try {
			
			this.pathPackage 
				= BundleConfigurationProjetManager.getPathDao() 
				+ "/metier";
		}
		catch (Exception e) {
			this.pathPackage = null;
		}
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void genererHook() throws Exception {
						
		/**/
		
	} // Fin de genererObjetMetier(...).___________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void alimenterNomSimpleInterface(
			final String pConceptModelise) {
		
		this.nomSimpleInterface = "IDao" + pConceptModelise;
		
	} // Fin de alimenterNomSimpleInterface(...).__________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void alimenterNomSimpleConcreteClass(
			final String pNomObjetMetier) {
		
		this.nomSimpleConcreteClass = "Dao" + pNomObjetMetier;
		
	} // Fin de alimenterNomSimpleConcreteClass(...).______________________

	
	
} // FIN DE LA CLASSE GenerateurDao.-----------------------------------------
