package levy.daniel.application.apptechnic.generationcode;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.generationcode.impl.EcriveurAbstractClass;
import levy.daniel.application.apptechnic.generationcode.impl.EcriveurInterface;
import levy.daniel.application.apptechnic.generationcode.impl.GestionnaireFiles;

/**
 * class GenerateurCode :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Expressions régulières, RegEx, regex, <br/>
 * créer nom classe abstraite à partir interface, génération nom,<br/>
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
public class GenerateurCode {

	// ************************ATTRIBUTS************************************/

	/**
	 * gestionnaire : GestionnaireFiles :<br/>
	 * GestionnaireFiles.<br/>
	 */
	private final transient GestionnaireFiles gestionnaire 
		= new GestionnaireFiles();

	/**
	 * ecriveurInterface : EcriveurInterface :<br/>
	 * EcriveurInterface.<br/>
	 */
	private final transient EcriveurInterface ecriveurInterface 
		= new EcriveurInterface();
	
	
	/**
	 * ecriveurAbstractClass : EcriveurAbstractClass :<br/>
	 * EcriveurAbstractClass.<br/>
	 */
	private final transient EcriveurAbstractClass ecriveurAbstractClass 
		= new EcriveurAbstractClass();

	
	/**
	 * pathPackageMetier : String :<br/>
	 * path absolu du repertoire model/metier.<br/>
	 */
	private transient String pathPackageMetier;
	
	
	/**
	 * packageObjetMetier : File :<br/>
	 * Package de l'objet metier à générer 
	 * sous model/metier/packageObjetMetier.<br/>
	 * Par exemple : model/metier/profil<br/> 
	 */
	private transient File packageObjetMetier;
	
	
	/**
	 * sousPackageObjetMetierImpl : File :<br/>
	 * Sous-Package "Impl" de l'objet metier à générer 
	 * sous model/metier/packageObjetMetier.<br/>
	 * Par exemple : model/metier/profil/impl<br/>
	 */
	private transient File sousPackageObjetMetierImpl;
	
	
	/**
	 * iObjetMetier : File :<br/>
	 * Interface de l'objet métier à générer.<br/>
	 */
	private transient File iObjetMetier;
	
	
	/**
	 * abstractObjetMetier : File :<br/>
	 * Classe Abstraite de l'objet métier à générer.<br/>
	 */
	private transient File abstractObjetMetier;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(GenerateurCode.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR GenerateurCode() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public GenerateurCode() {
		
		super();
		
		try {
			
			this.pathPackageMetier 
				= BundleConfigurationProjetManager.getPathMetier();
		}
		catch (Exception e) {
			this.pathPackageMetier = null;
		}
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * method genererObjetMetier(
	 * ) :<br/>
	 * <ul>
	 * <li>Génère le package pNomPackage sous model/metier.</li>
	 * <li>Génère le package pNomPackage.impl.</li>
	 * </ul>
	 * retourne null si pNomPackage est blank.<br/>
	 * retourne null si pNomInterface est blank.<br/>
	 * <br/>
	 *
	 * @param pNomPackage
	 * @param pNomInterface
	 * @param pNomObjetMetier
	 * @return File
	 * @throws IOException :  :  .<br/>
	 */
	public File genererObjetMetier(
			final String pNomPackage
				, final String pNomInterface
					, final String pNomObjetMetier) 
			throws IOException {
		
		/* retourne null si pNomPackage est blank. */
		if (StringUtils.isAllBlank(pNomPackage)) {
			return null;
		}
		
		/* retourne null si pNomInterface est blank. */
		if (StringUtils.isAllBlank(pNomInterface)) {
			return null;
		}
		
		/* Génère le package pNomPackage devant contenir 
		 * l'objet métier généré pNomObjetMetier sous model/metier */
		this.genererPackageObjetMetier(pNomPackage);
		
		/* Génère le package pNomPackage.impl devant contenir 
		 * les objet métier généré concrets pNomObjetMetier 
		 * sous model/metier/packageObjetMetier */
		this.genererSousPackageObjetMetierImpl();
		
		/* Génère l'Interface vide de l'objet métier. */
		this.genererInterfaceObjetMetier(pNomInterface);
		
		/* Génère la classe abstraite vide de l'objet métier. */
		this.genererAbstractObjetMetier(pNomInterface);
		
		this.ecriveurInterface.ecrireCode(this.iObjetMetier);
		
		this.ecriveurAbstractClass.ecrireCode(this.abstractObjetMetier);
		
		/* package levy.daniel.application.model.metier.profil; */
		/* public interface IProfil { */
		return null;
		
	} // Fin de genererObjetMetier(...).___________________________________


	
	/**
	 * method genererPackageObjetMetier(
	 * String pNomPackage) :<br/>
	 * <ul>
	 * <li>Génère le package pNomPackage devant 
	 * contenir l'objet métier généré sous model/metier.</li>
	 * <li>alimente this.packageObjetMetier avec le package généré.</li>
	 * <li>Par exemple : genererPackageObjetMetier("profil") 
	 * génère le package model/metier/profil</li>
	 * </ul>
	 *
	 * @param pNomPackage : String : nom du package devant 
	 * contenir l'objet métier généré.<br/>
	 * 
	 * @throws IOException
	 */
	private void genererPackageObjetMetier(
			final String pNomPackage) 
			throws IOException {
		
		this.packageObjetMetier 
			= this.gestionnaire
				.creerSousPackage(this.pathPackageMetier, pNomPackage);
		
	} // Fin de genererPackageObjetMetier(...).____________________________
	

	
	/**
	 * method genererSousPackageObjetMetierImpl() :<br/>
	 * <ul>
	 * <li>Génère le package "impl" devant 
	 * contenir l'objet métier généré concret sous 
	 * model/metier/packageObjetmetier.</li>
	 * <li>alimente this.sousPackageObjetMetierImpl 
	 * avec le package généré.</li>
	 * <li>Par exemple : genererSousPackageObjetMetierImpl() 
	 * avec this.packageObjetmetier = profil 
	 * génère le package model/metier/profil/impl</li>
	 * </ul>
	 *
	 * @throws IOException
	 */
	private void genererSousPackageObjetMetierImpl() 
			throws IOException {
		
		this.sousPackageObjetMetierImpl 
			= this.gestionnaire
				.creerSousPackage(this.packageObjetMetier, "impl");
		
	} // Fin de genererSousPackageObjetMetierImpl()._______________________
	

	
	/**
	 * method genererInterfaceObjetMetier(
	 * String pNomInterface) :<br/>
	 * <ul>
	 * <li>Génère le fichier vide pNomInterface.java 
	 * sous packageObjetMetier.</li>
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
	private void genererInterfaceObjetMetier(
			final String pNomInterface) 
					throws IOException {
		
		final String nomFichier = pNomInterface + ".java";
		
		this.iObjetMetier 
			= this.gestionnaire
			.creerFichierDansPackage(
					nomFichier, this.packageObjetMetier);
		
	} // Fin de genererInterfaceObjetMetier(...).__________________________
	

		
	/**
	 * method genererAbstractObjetMetier(
	 * String pNomInterface) :<br/>
	 * <ul>
	 * <li>Génère le fichier vide abstractObjetMetier.java 
	 * sous packageObjetMetier.</li>
	 * <li>alimente this.AbstractObjetMetier.</li>
	 * <li>Ne génère le fichier vide que si il n'existe pas déjà.</li>
	 * <li>Par exemple : genererAbstractObjetMetier("IProfil") 
	 * génère model/metier/profil/AbstractProfil.java</li>
	 * </ul>
	 *
	 * @param pNomInterface : String : 
	 * Nom de l'interface comme "IProfil" pour IProfil.java.<br/>
	 * 
	 * @throws IOException
	 */
	private void genererAbstractObjetMetier(
			final String pNomInterface) 
					throws IOException {
		
		final String nomFichier 
			= this.genererNomAbstractClass(pNomInterface) + ".java";
		
		this.abstractObjetMetier
			= this.gestionnaire
			.creerFichierDansPackage(
					nomFichier, this.packageObjetMetier);
		
	} // Fin de genererAbstractObjetMetier(...).___________________________
	
	
	
	/**
	 * method genererNomAbstractClass(
	 * String pNomInterface) :<br/>
	 * <ul>
	 * <li>Génère le nom de la classe abstraite à partir 
	 * du nom d'une Interface de type "IObjetMetier".</li>
	 * <li>Par exemple :  genererNomAbstractClass("IProfil") 
	 * retourne AbstractProfil.</li>
	 * </ul>
	 * retourne null si pNomInterface est blank.<br/>
	 * <br/>
	 *
	 * @param pNomInterface : String : nom de l'interface 
	 * du type "IObjetMetier".<br/>
	 * 
	 * @return : String : nom de la classe abstraite 
	 * du type "AbstractObjetMetie"r.<br/>
	 */
	private String genererNomAbstractClass(
			final String pNomInterface) {
		
		/* retourne null si pNomInterface est blank. */
		if (StringUtils.isBlank(pNomInterface)) {
			return null;
		}
		
		/* Pattern sous forme de String. */
		/* Commence par I
		 * poursuit par une virgule
		 * CamelCase. */
		final String patternString = "(^I)([A-Z][a-zA-Z0-9]*$)";
		
		/* Instanciation d'un Pattern. */
		final Pattern pattern = Pattern.compile(patternString);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcher = pattern.matcher(pNomInterface);
		
		/* Recherche du Pattern. */
		final boolean trouve = matcher.find();
		
		String group2 = null;
		
		String resultatString = null;
		
		if (trouve) {
			
			group2 = matcher.group(2);
			
			resultatString = "Abstract" + group2;
			
		}
		
		return resultatString;
		
	} // Fin de genererNomAbstractClass(...).______________________________
	
	
	
} // FIN DE LA CLASSE GenerateurCode.----------------------------------------
