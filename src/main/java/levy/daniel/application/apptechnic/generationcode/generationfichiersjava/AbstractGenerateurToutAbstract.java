package levy.daniel.application.apptechnic.generationcode.generationfichiersjava;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.ecriveurs.IEcriveur;

/**
 * CLASSE ABSTRAITE <b>AbstractGenerateurToutAbstract</b> :<br/>
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
public abstract class AbstractGenerateurToutAbstract 
	extends AbstractGenerateurFichiersJava {

	// ************************ATTRIBUTS************************************/
	/**
	 * CLASSE_ABSTRACT_GENERATEUR_TOUT_ABSTRACT : String :<br/>
	 * "Classe AbstractGenerateurToutAbstract".<br/>
	 */
	public static final String CLASSE_ABSTRACT_GENERATEUR_TOUT_ABSTRACT 
		= "Classe AbstractGenerateurToutAbstract";
	
	

	/**
	 * ecriveurInterface : EcriveurMetierInterface :<br/>
	 * EcriveurMetierInterface.<br/>
	 */
	protected transient IEcriveur ecriveurInterface;
	
	
	/**
	 * ecriveurAbstractClass : EcriveurMetierAbstractClass :<br/>
	 * EcriveurMetierAbstractClass.<br/>
	 */
	protected transient IEcriveur ecriveurAbstractClass;


	/**
	 * ecriveurConcreteClass : IEcriveur :<br/>
	 * EcriveurMetierConcreteClass.<br/>
	 */
	protected transient IEcriveur ecriveurConcreteClass;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(AbstractGenerateurToutAbstract.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR AbstractGenerateurToutAbstract() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractGenerateurToutAbstract() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

		
	/**
	 * {@inheritDoc}
	 * <ul>
	 * <li>alimente this.nomSimpleInterface.</li>
	 * <li>alimente this.nomSimpleAbstractClass.</li>
	 * <li>alimente this.nomSimpleConcreteClass.</li>
	 * </ul>
	 */
	@Override
	protected void alimenterAttributs() {
		
		/* alimente this.nomSimpleInterface. */
		this.alimenterNomSimpleInterface(conceptModelise);
		
		/* alimente this.nomSimpleAbstractClass. */
		this.alimenterNomSimpleAbstractClass();
		
		/* alimente this.nomSimpleConcreteClass. */
		this.alimenterNomSimpleConcreteClass(nomClassMetier);

	} // Fin de alimenterAttributs().______________________________________



	/**
	 * method alimenterNomSimpleInterface(
	 * String pConceptModelise) :<br/>
	 * <ul>
	 * <li>Fournit le Nom simple de l'interface à générer.</li>
	 * <li><b>alimente this.nomSimpleInterface</b>.</li>
	 * <li>Par exemple "IProfil" pour une interface métier.</li>
	 * <li>Par exemple "IDaoProfil" pour une interface Dao.</li>
	 * </ul>
	 *
	 * @param pConceptModelise : String.<br/>
	 */
	protected abstract void alimenterNomSimpleInterface(
			String pConceptModelise);

	
	
	/**
	 * method alimenterNomSimpleAbstractClass() :<br/>
	 * <ul>
	 * <li>Fournit le Nom simple de la classe abstraite à générer.</li>
	 * <li><b>alimente this.nomSimpleAbstractClass</b>.</li>
	 * <li>Par exemple "AbstractProfil" pour 
	 * une classe abstraite métier.</li>
	 * <li>Par exemple "AbstractDaoProfil" 
	 * pour une classe abstraite Dao.</li>
	 * <li>Déduit le nom de la classe abstraite à partir du nom 
	 * de l'interface this.nomSimpleInterface.<br/>
	 * </ul>
	 * ne fait rien si this.nomSimpleInterface est blank.<br/>
	 * <br/>
	 */
	private void alimenterNomSimpleAbstractClass() {
		
		/* ne fait rien si this.nomSimpleInterface est blank. */
		if (StringUtils.isBlank(this.nomSimpleInterface)) {
			return;
		}
		
		this.nomSimpleAbstractClass 
			= genererNomAbstractClass(this.nomSimpleInterface);
		
	} // Fin de alimenterNomSimpleAbstractClass()._________________________
	

	
	/**
	 * method alimenterNomSimpleConcreteClass(
	 * String pNomObjetMetier) :<br/>
	 * <ul>
	 * <li>Fournit le Nom simple de la classe concrète à générer.</li>
	 * <li><b>alimente this.nomSimpleConcreteClass</b>.</li>
	 * <li>Par exemple "ProfilSimple" pour une classe métier.</li>
	 * <li>Par exemple "DaoProfilSimple" pour une classe Dao.</li>
	 * </ul>
	 *
	 * @param pNomObjetMetier : String.<br/>
	 */
	protected abstract void alimenterNomSimpleConcreteClass(
			String pNomObjetMetier);
	
	
	
	
	/**
	 * {@inheritDoc}
	 * <ul>
	 * <li>génère et alimente this.interfaceJava.</li>
	 * <li>génère et alimente this.abstractClass.</li>
	 * <li>génère et alimente this.concreteClass.</li>
	 * <li>génère et alimente this.objetMetierForm.</li>
	 * </ul>
	 */
	@Override
	protected void genererFichiersJava() throws IOException {
		
		/* Génère l'Interface vide de l'objet métier. */
		this.genererInterfaceJava(this.nomSimpleInterface);
		
		/* Génère la classe abstraite vide de l'objet métier. */
		this.genererAbstractClass();
		
		/* Génère la classe vide de l'objet métier. */
		this.genererConcreteClass(this.nomSimpleConcreteClass);
		
	} // Fin de genererFichiersJava()._____________________________________
	
	
	
	/**
	 * method genererInterfaceJava(
	 * String pNomInterface) :<br/>
	 * <ul>
	 * <li>Génère le fichier vide pNomInterface.java 
	 * sous packageSousCouche.</li>
	 * <li>alimente this.iObjetMetier.</li>
	 * <li>Ne génère le fichier vide que si il n'existe pas déjà.</li>
	 * <li>Par exemple : genererInterfaceObjetMetier("IProfil") 
	 * génère model/metier/profil/IProfil.java</li>
	 * </ul>
	 *
	 * @param pNomInterface : String : 
	 * Nom de l'interface comme "IProfil" pour IProfil.java.<br/>
	 * 
	 * @throws IOException
	 */
	private void genererInterfaceJava(
			final String pNomInterface) 
					throws IOException {
		
		final String nomFichier = pNomInterface + ".java";
		
		this.interfaceJava 
			= this.gestionnaireFiles
			.creerFichierDansPackage(
					nomFichier, this.packageSousCouche);
		
	} // Fin de genererInterfaceJava(...)._________________________________
	

		
	/**
	 * method genererAbstractClass() :<br/>
	 * <ul>
	 * <li>Génère le fichier vide abstractClass.java 
	 * sous packageSousCouche.</li>
	 * <li>alimente this.AbstractObjetMetier.</li>
	 * <li>Ne génère le fichier vide que si il n'existe pas déjà.</li>
	 * <li>Par exemple : genererAbstractObjetMetier() 
	 * génère model/metier/profil/AbstractProfil.java</li>
	 * </ul>
	 *
	 * @param pNomInterface : String : 
	 * Nom de l'interface comme "IProfil" pour IProfil.java.<br/>
	 * 
	 * @throws IOException
	 */
	private void genererAbstractClass() 
					throws IOException {
		
		final String nomFichier 
			=  this.nomSimpleAbstractClass + ".java";
		
		this.abstractClass
			= this.gestionnaireFiles
			.creerFichierDansPackage(
					nomFichier, this.packageSousCouche);
		
	} // Fin de genererAbstractClass(...)._________________________________
	

	
	/**
	 * method genererConcreteClass(
	 * String pNomConcreteClass) :<br/>
	 * <ul>
	 * <li>Génère le fichier vide pNomConcreteClass.java 
	 * sous packageSousCouche.impl.</li>
	 * <li>alimente this.objetMetier.</li>
	 * <li>Ne génère le fichier vide que si il n'existe pas déjà.</li>
	 * <li>Par exemple : genererObjetMetier("ProfilSimple") 
	 * génère model/metier/profil/impl/ProfilSimple.java</li>
	 * </ul>
	 *
	 * @param pNomConcreteClass : String : 
	 * Nom de l'objet métier comme "ProfilSimple" 
	 * pour ProfilSimple.java.<br/>
	 * 
	 * @throws IOException
	 */
	private void genererConcreteClass(
			final String pNomConcreteClass) 
					throws IOException {
		
		final String nomFichier = pNomConcreteClass + ".java";
		
		this.concreteClass 
			= this.gestionnaireFiles
			.creerFichierDansPackage(
					nomFichier, this.sousPackageImpl);
		
	} // Fin de genererConcreteClass(...).___________________________________
	

	
} // FIN DE LA CLASSE AbstractGenerateurToutAbstract.------------------------
