package levy.daniel.application.apptechnic.generationcode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.IEcriveur;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.impl.EcriveurAbstractClass;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.impl.EcriveurInterface;
import levy.daniel.application.apptechnic.generationcode.impl.GestionnaireFiles;

/**
 * CLASSE <b>GenerateurMetier</b> :<br/>
 * .<br/>
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
public class GenerateurMetier {

	// ************************ATTRIBUTS************************************/

	/**
	 * gestionnaire : GestionnaireFiles :<br/>
	 * GestionnaireFiles.<br/>
	 */
	private final transient IGestionnaireFiles gestionnaire 
		= new GestionnaireFiles();

	/**
	 * ecriveurInterface : EcriveurInterface :<br/>
	 * EcriveurInterface.<br/>
	 */
	private final transient IEcriveur ecriveurInterface 
		= new EcriveurInterface();
	
	
	/**
	 * ecriveurAbstractClass : EcriveurAbstractClass :<br/>
	 * EcriveurAbstractClass.<br/>
	 */
	private final transient IEcriveur ecriveurAbstractClass 
		= new EcriveurAbstractClass();

	
	/**
	 * conceptModelise : String :<br/>
	 * concept modélisé par ce générateur.<br/>
	 * nom du package avec une majuscule en première position.<br/>
	 */
	private transient String conceptModelise;
	
	
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
	 * Sous-Package "impl" de l'objet metier à générer 
	 * sous model/metier/packageObjetMetier.<br/>
	 * Par exemple : model/metier/profil/impl<br/>
	 */
	private transient File sousPackageObjetMetierImpl;
	
	
	/**
	 * nomSimpleInterface : String :<br/>
	 * Nom simple de l'interface à générer.<br/>
	 * Par exemple "IProfil".<br/>
	 */
	private transient String nomSimpleInterface;
	
	
	/**
	 * iObjetMetier : File :<br/>
	 * Interface (Fichier Java) de l'objet métier à générer.<br/>
	 * Par exemple : "IProfil.java" pour l'objet métier 
	 * Profil.java<br/>
	 */
	private transient File iObjetMetier;

	
	/**
	 * nomSimpleAbstractClass : String :<br/>
	 * Nom simple de la Classe Abstraite à générer.<br/>
	 * Par exemple "AbstractProfil".<br/>
	 */
	private transient String nomSimpleAbstractClass;
	
	
	/**
	 * abstractObjetMetier : File :<br/>
	 * Classe Abstraite (Fichier Java) de l'objet métier à générer.<br/>
	 * Par exemple : "AbstractProfil.java" pour l'objet métier 
	 * Profil.java<br/>
	 */
	private transient File abstractObjetMetier;
	
		
	/**
	 * nomSimpleObjetMetier : String :<br/>
	 * Nom simple de l'Objet metier à générer.<br/>
	 * Par exemple "ProfilSimple".<br/>
	 */
	private transient String nomSimpleObjetMetier;
	
	
	/**
	 * objetMetier : File :<br/>
	 * Objet métier (Fichier Java) à générer.<br/>
	 * Par exemple : Profil.java<br/>
	 */
	private transient File objetMetier;
	
	
		
	/**
	 * nomSimpleObjetMetierForm : String :<br/>
	 * Nom simple de l'Objet metier "Formulaire" à générer.<br/>
	 * Par exemple "ProfilSimpleForm".<br/>
	 */
	private transient String nomSimpleObjetMetierForm;
	
	
	/**
	 * objetMetierForm : File :<br/>
	 * Objet métier "Formulaire" (Fichier Java) à générer.<br/>
	 * Par exemple : ProfilForm.java<br/>
	 */
	private transient File objetMetierForm;


	
	/**
	 * mapAttributs : Map&lt;String,String&gt; :<br/>
	 * <ul>
	 * Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 */
	private transient Map<String, String> mapAttributs;
	
	
	/**
	 * mapAttributsEquals : Map&lt;String,String&gt; :<br/>
	 * <ul>
	 * Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) utilisés dans equals() avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 */
	private transient Map<String, String> mapAttributsEquals;
	
		
	/**
	 * mapRg : Map&lt;String, List&lt;String&gt;&gt; :<br/>
	 * <ul>
	 * Map&lt;String, List&lt;String&gt;&gt; ordonnée 
	 * contenant les listes de RG par attribut avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>List&lt;String&gt; : Liste des RG s'appliquant à l'attribut</li>
	 * </ul>
	 */
	private transient Map<String, List<String>> mapRg;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
			= LogFactory.getLog(GenerateurMetier.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR GenerateurMetier() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>alimente this.pathPackageMetier en demandant le chemin 
	 * du package <b>model.metier</b> au 
	 * <b>BundleConfigurationProjetManager</b>.</li>
	 * </ul>
	 * <br/>
	 */
	public GenerateurMetier() {
		
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
	 * String pNomPackage
	 * , String pNomInterface
	 * , String pNomObjetMetier
	 * , Map<String, String> pMapAttributs
	 * , Map<String, String> pMapAttributsEquals) :<br/>
	 * <ul>
	 * <li><b>Génère le code model.metier</b>.</li>
	 * <li>alimente this.conceptModelise.</li>
	 * <li>alimente this.nomSimpleInterface.</li>
	 * <li>alimente this.nomSimpleObjetMetier.</li>
	 * <li>alimente la map des attributs de l'objet métier.</li>
	 * <li>alimente la map des attributs de l'objet métier 
	 * utilisés dans equals().</li>
	 * <li>alimente la map des RG this.mapRg.</li>
	 * <li>Génère le package pNomPackage sous model/metier 
	 * et alimente this.packageObjetMetier.</li>
	 * <li>Génère le package pNomPackage.impl.</li>
	 * <li>Génère l'Interface vide de l'objet métier.</li>
	 * <li>Génère la classe abstraite vide de l'objet métier.</li>
	 * <li>Génère la classe vide de l'objet métier.</li>
	 * <li>Génère le code de l'interface 
	 * dans this.iObjetMetier.</li>
	 * <li>génère le code de la classe abstraite 
	 * dans this.abstractObjetMetier.</li>
	 * </ul>
	 * retourne si pNomPackage est blank.<br/>
	 * retourne si pNomInterface est blank.<br/>
	 * retourne si pNomObjetMetier est blank.<br/>
	 * retourne si pNomPackage n'est pas conforme.<br/>
	 * retourne si pNomInterface n'est pas conforme.<br/>
	 * <br/>
	 *
	 * @param pNomPackage
	 * @param pNomInterface
	 * @param pNomObjetMetier
	 * @param pMapAttributs 
	 * @param pMapAttributsEquals 
	 * @param pMapRg 
	 * 
	 * @throws IOException
	 */
	public void genererObjetMetier(
			final String pNomPackage
				, final String pNomInterface
					, final String pNomObjetMetier
						, final Map<String, String> pMapAttributs
							, final Map<String, String> pMapAttributsEquals
								, final Map<String, List<String>> pMapRg) 
			throws IOException {
		
		/* retourne si pNomPackage est blank. */
		if (StringUtils.isAllBlank(pNomPackage)) {
			return;
		}
		
		/* retourne si pNomInterface est blank. */
		if (StringUtils.isAllBlank(pNomInterface)) {
			return;
		}
		
		/* retourne si pNomObjetMetier est blank. */
		if (StringUtils.isAllBlank(pNomObjetMetier)) {
			return;
		}
		
		/* retourne si pNomPackage n'est pas conforme. */
		if (!conformeNomPackage(pNomPackage)) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Nom de package non conforme : " 
						+ pNomPackage);
			}
			
			return;
		}
		
		/* retourne si pNomInterface n'est pas conforme. */
		if (!conformeNomInterface(pNomInterface)) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Nom d'interface non conforme : " 
						+ pNomInterface);
			}
			
			return;
		}
		
		/* retourne si pNomObjetMetier n'est pas conforme. */
		if (!conformeNomClasse(pNomObjetMetier)) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Nom de classe non conforme : " 
						+ pNomObjetMetier);
			}
			
			return;
		}

		/* alimente this.conceptModelise. */
		this.conceptModelise 
			= this.mettrePremiereEnMajusculeEtGarder(pNomPackage);
		
		/* alimente this.nomSimpleInterface. */
		this.nomSimpleInterface = pNomInterface;
		
		/* alimente this.nomSimpleObjetMetier. */
		this.nomSimpleObjetMetier = pNomObjetMetier;
		
		/* alimente la map des attributs de l'objet métier. */
		this.mapAttributs = pMapAttributs;
		
		/* alimente la map des attributs de l'objet métier 
		 * utilisés dans equals(). */
		this.mapAttributsEquals = pMapAttributsEquals;
		
		/* alimente la map des RG this.mapRg. */
		this.mapRg = pMapRg;
		
		/* Génère le package pNomPackage devant contenir 
		 * l'objet métier généré pNomObjetMetier sous model/metier. */
		/* alimente this.packageObjetMetier */
		this.genererPackageObjetMetier(pNomPackage);
		
		/* Génère le package pNomPackage.impl devant contenir 
		 * les objet métier généré concrets pNomObjetMetier 
		 * sous model/metier/packageObjetMetier */
		this.genererSousPackageObjetMetierImpl();
		
		/* Génère l'Interface vide de l'objet métier. */
		this.genererInterfaceObjetMetier(pNomInterface);
		
		/* Génère la classe abstraite vide de l'objet métier. */
		this.genererAbstractObjetMetier(pNomInterface);
		
		/* Génère la classe vide de l'objet métier. */
		this.genererObjetMetier(pNomObjetMetier);
		
		/* Génère le code de l'interface dans this.iObjetMetier. */
		this.ecriveurInterface.ecrireCode(this.iObjetMetier, this);
		
		/* génère le code de la classe abstraite dans this.abstractObjetMetier. */
		this.ecriveurAbstractClass.ecrireCode(this.abstractObjetMetier, this);
		
	} // Fin de genererObjetMetier(...).___________________________________


	
	
	
	/**
	 * method getConceptModelise() :<br/>
	 * Getter du concept modélisé par ce générateur.<br/>
	 * nom du package avec une majuscule en première position.<br/>
	 * <br/>
	 *
	 * @return conceptModelise : String.<br/>
	 */
	public final String getConceptModelise() {	
		return this.conceptModelise;
	} // Fin de getConceptModelise().______________________________________



	/**
	 * method getPathPackageMetier() :<br/>
	 * Getter du path absolu du repertoire model/metier.<br/>
	 * <br/>
	 *
	 * @return pathPackageMetier : String.<br/>
	 */
	public final String getPathPackageMetier() {
		return this.pathPackageMetier;
	} // Fin de getPathPackageMetier().____________________________________


	
	/**
	 * method getPackageObjetMetier() :<br/>
	 * Getter du Package de l'objet metier à générer 
	 * sous model/metier/packageObjetMetier.<br/>
	 * Par exemple : model/metier/profil<br/> 
	 * <br/>
	 *
	 * @return packageObjetMetier : File.<br/>
	 */
	public final File getPackageObjetMetier() {
		return this.packageObjetMetier;
	} // Fin de getPackageObjetMetier().___________________________________


	
	/**
	 * method getSousPackageObjetMetierImpl() :<br/>
	 * Getter du Sous-Package "impl" de l'objet metier à générer 
	 * sous model/metier/packageObjetMetier.<br/>
	 * Par exemple : model/metier/profil/impl<br/>
	 * <br/>
	 *
	 * @return sousPackageObjetMetierImpl : File.<br/>
	 */
	public final File getSousPackageObjetMetierImpl() {
		return this.sousPackageObjetMetierImpl;
	} // Fin de getSousPackageObjetMetierImpl().___________________________

	
		
	/**
	 * method getNomSimpleInterface() :<br/>
	 * Getter du Nom simple de l'interface à générer.<br/>
	 * Par exemple "IProfil".<br/>
	 * <br/>
	 *
	 * @return nomSimpleInterface : String.<br/>
	 */
	public final String getNomSimpleInterface() {
		return this.nomSimpleInterface;
	} // Fin de getNomSimpleInterface().___________________________________
	


	/**
	 * method getiObjetMetier() :<br/>
	 * Getter de l'Interface de l'objet métier à générer.<br/>
	 * Par exemple : "IProfil.java" pour l'objet métier Profil.java<br/>
	 * <br/>
	 *
	 * @return iObjetMetier : File.<br/>
	 */
	public final File getiObjetMetier() {
		return this.iObjetMetier;
	} // Fin de getiObjetMetier()._________________________________________


		
	/**
	 * method getNomSimpleAbstractClass() :<br/>
	 * Getter du Nom simple de la Classe Abstraite à générer.<br/>
	 * Par exemple "AbstractProfil".<br/>
	 * <br/>
	 *
	 * @return nomSimpleAbstractClass : String.<br/>
	 */
	public final String getNomSimpleAbstractClass() {	
		return this.nomSimpleAbstractClass;
	} // Fin de getNomSimpleAbstractClass()._______________________________



	/**
	 * method getAbstractObjetMetier() :<br/>
	 * Getter de la Classe Abstraite de l'objet métier à générer.<br/>
	 * Par exemple : "AbstractProfil.java" pour l'objet métier 
	 * Profil.java<br/>
	 * <br/>
	 *
	 * @return abstractObjetMetier : File.<br/>
	 */
	public final File getAbstractObjetMetier() {
		return this.abstractObjetMetier;
	} // Fin de getAbstractObjetMetier().__________________________________


		
	/**
	 * method getNomSimpleObjetMetier() :<br/>
	 * Getter du Nom simple de l'Objet metier à générer.<br/>
	 * Par exemple "ProfilSimple".<br/>
	 * <br/>
	 *
	 * @return nomSimpleObjetMetier : String.<br/>
	 */
	public final String getNomSimpleObjetMetier() {	
		return this.nomSimpleObjetMetier;
	} // Fin de getNomSimpleObjetMetier()._________________________________



	/**
	 * method getObjetMetier() :<br/>
	 * Getter de l'Objet métier à générer.<br/>
	 * Par exemple : Profil.java<br/>
	 * <br/>
	 *
	 * @return objetMetier : File.<br/>
	 */
	public final File getObjetMetier() {
		return this.objetMetier;
	} // Fin de getObjetMetier().__________________________________________


	
	/**
	 * method getMapAttributs() :<br/>
	 * <ul>
	 * Getter de la Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 *
	 * @return mapAttributs : Map<String,String>.<br/>
	 */
	public final Map<String, String> getMapAttributs() {
		return this.mapAttributs;
	} // Fin de getMapAttributs()._________________________________________


	
	/**
	 * method getMapAttributsEquals() :<br/>
	 * <ul>
	 * Getter de la Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) utilisés dans equals() avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 *
	 * @return mapAttributsEquals : Map<String,String>.<br/>
	 */
	public final Map<String, String> getMapAttributsEquals() {
		return this.mapAttributsEquals;
	} // Fin de getMapAttributsEquals().___________________________________


		
	/**
	 * method getMapRg() :<br/>
	 * <ul>
	 * Getter de la Map&lt;String, List&lt;String&gt;&gt; ordonnée 
	 * contenant les listes de RG par attribut avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>List&lt;String&gt; : Liste des RG s'appliquant à l'attribut</li>
	 * </ul>
	 *
	 * @return mapRg : Map<String,List<String>>.<br/>
	 */
	public final Map<String, List<String>> getMapRg() {
		return this.mapRg;
	} // Fin de getMapRg().________________________________________________



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
	 * <li>alimente this.nomSimpleAbstractClass.</li>
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
		
		/* alimente this.nomSimpleAbstractClass. */
		this.nomSimpleAbstractClass 
			= this.genererNomAbstractClass(pNomInterface);
		
		final String nomFichier 
			=  this.nomSimpleAbstractClass + ".java";
		
		this.abstractObjetMetier
			= this.gestionnaire
			.creerFichierDansPackage(
					nomFichier, this.packageObjetMetier);
		
	} // Fin de genererAbstractObjetMetier(...).___________________________
	

	
	/**
	 * method genererObjetMetier(
	 * String pNomObjetMetier) :<br/>
	 * <ul>
	 * <li>Génère le fichier vide pNomObjetMetier.java 
	 * sous packageObjetMetier.impl.</li>
	 * <li>alimente this.objetMetier.</li>
	 * <li>Ne génère le fichier vide que si il n'existe pas déjà.</li>
	 * <li>Par exemple : genererObjetMetier("ProfilSimple") 
	 * génère model/metier/profil/impl/ProfilSimple.java</li>
	 * </ul>
	 *
	 * @param pNomObjetMetier : String : 
	 * Nom de l'objet métier comme "ProfilSimple" 
	 * pour ProfilSimple.java.<br/>
	 * 
	 * @throws IOException
	 */
	private void genererObjetMetier(
			final String pNomObjetMetier) 
					throws IOException {
		
		final String nomFichier = pNomObjetMetier + ".java";
		
		this.objetMetier 
			= this.gestionnaire
			.creerFichierDansPackage(
					nomFichier, this.sousPackageObjetMetierImpl);
		
	} // Fin de genererObjetMetier(...).___________________________________
	
	
	
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
		 * poursuit par une Majuscule
		 * poursuit par CamelCase. */
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
	
	
	
	/**
	 * method conformeNomPackage(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Contrôle que pString est conforme aux noms de package java
	 * , à savoir que des lettres minuscules et des chiffres 
	 * (pas de majuscules ou de caractères spéciaux).</li>
	 * <li>accepte "profil", "profil7", ...</li>
	 * <li>refuse "Profil", "profil_7", ...</li>
	 * </ul>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : boolean : true si conforme.<br/>
	 */
	private boolean conformeNomPackage(
			final String pString) {
		
		boolean resultat = false;
		
		/* Pattern sous forme de String. */
		/* - Commence par I
		 * - poursuit par une Majuscule
		 * - poursuit CamelCase. */
		final String patternString = "([a-z0-9]*)";
		
		/* Instanciation d'un Pattern. */
		final Pattern pattern = Pattern.compile(patternString);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcher = pattern.matcher(pString);
		
		/* Recherche du Pattern. */
		final boolean trouve = matcher.matches();
		
		if (trouve) {
			resultat = true;
		}
		
		return resultat;
		
	} // Fin de conformeNomPackage(...).___________________________________
	
	
	
	/**
	 * method conformeNomInterface(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Contrôle que pString est conforme aux noms des Interfaces
	 * , à savoir I, puis une majuscule, puis une suite camelCase 
	 * sans caractères spéciaux.</li>
	 * <li>accepte "IProfil", "IProfilSimple", "IProfil7", ...</li>
	 * <li>refuse "Profil", "IProfil_Simple", "iProfil", ...</li>
	 * </ul>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : boolean : true si conforme.<br/>
	 */
	private boolean conformeNomInterface(
			final String pString) {
		
		boolean resultat = false;
		
		/* Pattern sous forme de String. */
		/* - Commence par I
		 * - poursuit par une Majuscule
		 * - poursuit camelCase. */
		final String patternString = "(^I)([A-Z][a-z][a-zA-Z0-9]*$)";
		
		/* Instanciation d'un Pattern. */
		final Pattern pattern = Pattern.compile(patternString);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcher = pattern.matcher(pString);
		
		/* Recherche du Pattern. */
		final boolean trouve = matcher.find();
		
		if (trouve) {
			resultat = true;
		}
		
		return resultat;
		
	} // Fin de conformeNomInterface(...)._________________________________
	
	
	
	/**
	 * method conformeNomClasse(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Contrôle que pString est conforme aux noms des Classes
	 * , à savoir une majuscule, puis une suite camelCase 
	 * sans caractères spéciaux.</li>
	 * <li>accepte "Profil", "ProfilSimple", "ProfilSimple7", ...</li>
	 * <li>refuse "IProfil", "profil", "ProfilSimple_7", ...</li>
	 * </ul>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : boolean : true si conforme.<br/>
	 */
	private boolean conformeNomClasse(
			final String pString) {
		
		boolean resultat = false;
		
		/* Pattern sous forme de String. */
		/* - Commence par une Majuscule
		 * - poursuit camelCase. */
		final String patternString = "(^[A-Z][a-z][a-zA-Z0-9]*$)";
		
		/* Instanciation d'un Pattern. */
		final Pattern pattern = Pattern.compile(patternString);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcher = pattern.matcher(pString);
		
		/* Recherche du Pattern. */
		final boolean trouve = matcher.find();
		
		if (trouve) {
			resultat = true;
		}
		
		return resultat;
		
	} // Fin de conformeNomClasse(...).____________________________________
	

		
	/**
	 * method trouverCamel(
	 * String pString) :<br/>
	 * <ul>
	 * <li><b>Décompose un mot camelCase</b> et retourne une liste 
	 * des sous-mots le composant</li>
	 * <ul>
	 * <li>trouverCamel("abel") retourne "abel".</li>
	 * <li>trouverCamel("Abel") retourne "Abel".</li>
	 * <li>trouverCamel("abel7Medard") retourne "abel7" 
	 * et "Medard".</li>
	 * <li>trouverCamel("AbelMedardGoro") retourne "Abel"
	 * , "Medard", "Goro".</li>
	 * <li>trouverCamel("AbstractProfilSimple") retourne "Abstract"
	 * , "Profil", "Simple".</li>
	 * </ul>
	 * </ul>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : List&lt;String&gt; : Liste des "bosses" du chameau.<br/>
	 */
	private List<String> trouverCamel(
			final String pString) {
		
		final List<String> resultat = new ArrayList<String>();
		
		/* Pattern sous forme de String. */
		/* - Commence par une Majuscule ou une minuscule
		 * - poursuit par des minuscules. */
		final String patternStringDebut = "^([A-Z][a-z0-9]*|[a-z0-9]*)";
		
		/* Instanciation d'un Pattern. */
		final Pattern patternDebut = Pattern.compile(patternStringDebut);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcherDebut = patternDebut.matcher(pString);
		
		String suite = null;
		
		/* Recherche des ocurrences du Pattern. */
		while (matcherDebut.find()) {
			
			final String lu = matcherDebut.group();
			resultat.add(lu);
			suite = StringUtils.removeStart(pString, lu);
			
		}
		
		if (!StringUtils.isBlank(suite)) {
			
			/* Pattern sous forme de String. */
			/* - Commence par I
			 * - poursuit par une Majuscule
			 * - poursuit CamelCase. */
			final String patternString = "([A-Z][a-z0-9]*)";
			
			/* Instanciation d'un Pattern. */
			final Pattern pattern = Pattern.compile(patternString);
			
			/* Instanciation d'un moteur de recherche Matcher. */
			final Matcher matcher = pattern.matcher(suite);
			
			/* Recherche des ocurrences du Pattern. */
			while (matcher.find()) {
				
				final String lu = matcher.group();
				resultat.add(lu);		
				
			}
			
		}
				
		return resultat;
	
	} // Fin de trouverCamel(...)._________________________________________
	
	
	
	/**
	 * method mettrePremiereEnMajusculeEtGarder(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Met la première lettre de chaque mots séparés 
	 * par des espaces en majuscule.</li>
	 * <li>Conserve les autres lettres de chaque mots séparés 
	 * par un espace.</li>
	 * <li>Par exemple : "premier" est transformé en "Premier".</li>
	 * <li>"PREMIER" est transformé en "PREMIER".</li>
	 * <li>WordUtils.capitalize("i am FINE") = "I Am FINE"</li>
	 * </ul>
	 * retourne null si pString == null.<br/>
	 * <br/>
	 * 
	 *
	 * @param pString : String. <br/>
	 * 
	 * @return : String.<br/>
	 */
	private String mettrePremiereEnMajusculeEtGarder(
			final String pString) {
		
		/* retourne null si pString == null. */
		if (pString == null) {
			return null;
		}
		
		return WordUtils.capitalize(pString);
		
	} // Fin de mettrePremiereEnMajusculeEtGarder(...).____________________
	
	
	
} // FIN DE LA CLASSE GenerateurCode.----------------------------------------
