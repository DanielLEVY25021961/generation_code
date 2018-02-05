package levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationmetier.generationobjetmetiersimple;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.GestionnaireProjet;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.IEcriveur;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.model.metier.impl.EcriveurMetierAbstractClass;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.model.metier.impl.EcriveurMetierConcreteClass;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.model.metier.impl.EcriveurMetierConcreteClassForm;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.model.metier.impl.EcriveurMetierInterface;
import levy.daniel.application.apptechnic.generationcode.generationfichiersjava.AbstractGenerateurToutAbstract;


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
	 * <li>alimente this.pathPackage en demandant le chemin 
	 * du package <b>model.metier</b> au 
	 * <b>GestionnaireProjet</b>.</li>
	 * </ul>
	 * <br/>
	 */
	public GenerateurMetierToutAbstract() {
		
		super();
		
		try {
			
			this.pathPackage 
				= GestionnaireProjet.getPathMetierMainJavaString();
		}
		catch (Exception e) {
			this.pathPackage = null;
		}
		
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
		this.ecriveurInterface.ecrireCode(this.interfaceJava, this);
		
		/* génère le code de la classe abstraite 
		 * dans this.abstractClass. */
		this.ecriveurAbstractClass.ecrireCode(this.abstractClass, this);
		
		/* génère le code de la classe concrète 
		 * dans this.concreteClass. */
		this.ecriveurConcreteClass.ecrireCode(this.concreteClass, this);

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
