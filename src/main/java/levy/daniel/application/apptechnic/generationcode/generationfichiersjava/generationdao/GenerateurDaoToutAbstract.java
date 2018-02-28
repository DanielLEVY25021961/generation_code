package levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationdao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.GestionnaireProjet;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.model.dao.impl.EcriveurDaoAbstract;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.model.dao.impl.EcriveurDaoConcrete;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.model.dao.impl.EcriveurDaoInterface;
import levy.daniel.application.apptechnic.generationcode.generationfichiersjava.AbstractGenerateurToutAbstract;

/**
 * CLASSE <b>GenerateurDaoToutAbstract</b> :<br/>
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
public class GenerateurDaoToutAbstract extends AbstractGenerateurToutAbstract {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_GENERATEUR_DAO : String :<br/>
	 * "Classe GenerateurDaoToutAbstract".<br/>
	 */
	public static final String CLASSE_GENERATEUR_DAO 
		= "Classe GenerateurDaoToutAbstract";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(GenerateurDaoToutAbstract.class);

	// *************************METHODES************************************/
	
	 /**
	 * method CONSTRUCTEUR GenerateurDaoToutAbstract() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>instancie this.ecriveurInterface.</li>
	 * <li>instancie this.ecriveurAbstractClass.</li>
	 * <li>instancie this.ecriveurConcreteClass.</li>
	 * </ul>
	 */
	public GenerateurDaoToutAbstract() {
		
		super();
		
		this.ecriveurInterface = new EcriveurDaoInterface();
		this.ecriveurAbstractClass = new EcriveurDaoAbstract();
		this.ecriveurConcreteClass = new EcriveurDaoConcrete();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void alimenterPathPackage() throws Exception {
		
		this.pathCouche = GestionnaireProjet.getPathDaoMainJava();
		
		final String pathBaseAntislash 
			= GestionnaireProjet.getPathDaoMainJavaString();
		
		this.pathCoucheString = retournerPathGenerique(pathBaseAntislash);
		
		this.fileCouche = this.pathCouche.toFile();
		
		this.pathRelCouche = PATH_MAIN_JAVA.relativize(this.pathCouche);
		
		this.pathRelCoucheJavaString 
			= remplacerAntiSlashparPoint(this.pathRelCouche.toString());
		
		this.pathPackageString =  this.pathCoucheString + "/metier";
		
	} // Fin de alimenterPathPackage().____________________________________
	

	
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


	
} // FIN DE LA CLASSE GenerateurDaoToutAbstract.-----------------------------------------
