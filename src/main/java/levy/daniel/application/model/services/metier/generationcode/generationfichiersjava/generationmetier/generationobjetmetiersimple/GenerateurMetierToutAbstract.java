package levy.daniel.application.model.services.metier.generationcode.generationfichiersjava.generationmetier.generationobjetmetiersimple;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.generationcode.GestionnaireProjet;
import levy.daniel.application.model.services.metier.generationcode.ecriveurs.IEcriveur;
import levy.daniel.application.model.services.metier.generationcode.ecriveurs.model.metier.impl.EcriveurMetierAbstractClass;
import levy.daniel.application.model.services.metier.generationcode.ecriveurs.model.metier.impl.EcriveurMetierConcreteClass;
import levy.daniel.application.model.services.metier.generationcode.ecriveurs.model.metier.impl.EcriveurMetierConcreteClassForm;
import levy.daniel.application.model.services.metier.generationcode.ecriveurs.model.metier.impl.EcriveurMetierInterface;
import levy.daniel.application.model.services.metier.generationcode.generationfichiersjava.AbstractGenerateurToutAbstract;




/**
 * CLASSE <b>GenerateurMetierToutAbstract</b> :<br/>
 * Générateur pour les objets metier avec 
 * [interface - classe abstraite - classe concrète].<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Expressions régulières, RegEx, regex, <br/>
 * créer nom classe abstraite à partir interface, génération nom,<br/>
 * regex conforme nom package, regex conforme nom interface, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 5 janv. 2018
 *
 */
public class GenerateurMetierToutAbstract 
							extends AbstractGenerateurToutAbstract {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_GENERATEUR_METIER : String :<br/>
	 * "Classe GenerateurMetierToutAbstract".<br/>
	 */
	public static final String CLASSE_GENERATEUR_METIER 
		= "Classe GenerateurMetierToutAbstract";
	
	
	/**
	 * ecriveurConcreteClassForm : IEcriveur :<br/>
	 * EcriveurMetierConcreteClassForm.<br/>
	 */
	private final transient IEcriveur ecriveurConcreteClassForm 
		= new EcriveurMetierConcreteClassForm();
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
			= LogFactory.getLog(GenerateurMetierToutAbstract.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR GenerateurMetierToutAbstract() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>instancie this.ecriveurInterface.</li>
	 * <li>instancie this.ecriveurAbstractClass.</li>
	 * <li>instancie this.ecriveurConcreteClass.</li>
	 * </ul>
	 */
	public GenerateurMetierToutAbstract() {
		
		super();
				
		this.ecriveurInterface = new EcriveurMetierInterface();
		this.ecriveurAbstractClass = new EcriveurMetierAbstractClass();
		this.ecriveurConcreteClass = new EcriveurMetierConcreteClass();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void genererHook() throws Exception {
		
		/* Génère le code de l'interface 
		 * dans this.interfaceJava. */
		/* génère le code de la classe abstraite 
		 * dans this.abstractClass. */
		/* génère le code de la classe concrète 
		 * dans this.concreteClass. */
		super.genererHook();
		
		/* génère le fichier vide classMetierForm. */
		this.genererClassMetierForm();

		/* génère le code de la classe concrète 
		 * Formulaire dans classMetierForm. */
		this.ecriveurConcreteClassForm.ecrireCode(classMetierForm, this);
				
	} // Fin de genererObjetMetier(...).___________________________________

	
	
	/**
	 * method genererClassMetierForm() :<br/>
	 * <ul>
	 * <li>Génère le fichier vide nomClassMetierForm.java 
	 * sous packageSousCouche.impl.</li>
	 * <li>alimente classMetierForm.</li>
	 * <li>Ne génère le fichier vide que si il n'existe pas déjà.</li>
	 * <li>Par exemple : genererClassMetierForm() 
	 * génère model/metier/profil/impl/ProfilSimpleForm.java</li>
	 * </ul>
	 *
	 * @throws IOException
	 */
	private void genererClassMetierForm() throws IOException {
		
		final String nomFichier = nomClassMetierForm + ".java";
		
		classMetierForm 
			= this.gestionnaireFiles
			.creerFichierDansPackage(
					nomFichier, this.sousPackageImpl);
		
	} // Fin de genererClassMetierForm(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void alimenterPathPackage() throws Exception {
		
		this.pathCouche = GestionnaireProjet.getPathModelMainJava();
		
		final String pathBaseAntislash 
			= GestionnaireProjet.getPathModelMainJavaString();
	
		this.pathCoucheString = retournerPathGenerique(pathBaseAntislash);
		
		this.fileCouche = this.pathCouche.toFile();
		
		this.pathRelCouche = PATH_MAIN_JAVA.relativize(this.pathCouche);
		
		this.pathRelCoucheJavaString 
			= remplacerAntiSlashparPoint(this.pathRelCouche.toString());
		
		this.pathPackageString 
			= GestionnaireProjet.getPathMetierMainJavaString();
		
	} // Fin de alimenterPathPackage().____________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void alimenterNomSimpleInterface(
			final String pConceptModelise) {
		
		this.nomSimpleInterface = nomInterfaceMetier;
		
	} // Fin de alimenterNomSimpleInterface(...).__________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void alimenterNomSimpleConcreteClass(
			final String pNomObjetMetier) {
		
		this.nomSimpleConcreteClass = pNomObjetMetier;
		
	} // Fin de alimenterNomSimpleConcreteClass(...).______________________
	

	
} // FIN DE LA CLASSE GenerateurCode.----------------------------------------
