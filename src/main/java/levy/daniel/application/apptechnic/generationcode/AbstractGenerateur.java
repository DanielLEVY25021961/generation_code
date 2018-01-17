package levy.daniel.application.apptechnic.generationcode;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.impl.GestionnaireFiles;

/**
 * class AbstractGenerateur :<br/>
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
 * @since 17 janv. 2018
 *
 */
public abstract class AbstractGenerateur implements IGenerateur {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_ABSTRACT_GENERATEUR : String :<br/>
	 * "Classe AbstractGenerateur".<br/>
	 */
	public static final String CLASSE_ABSTRACT_GENERATEUR 
		= "Classe AbstractGenerateur";
	


	/**
	 * gestionnaire : GestionnaireFiles :<br/>
	 * GestionnaireFiles.<br/>
	 */
	protected final transient IGestionnaireFiles gestionnaire 
		= new GestionnaireFiles();

	
	/**
	 * pathPackage : String :<br/>
	 * path absolu du repertoire contenant les 
	 * arborescences à générer.<br/>
	 * par exemple : model/metier pour un GenerateurMetier 
	 * ou model/dao/metier pour un GenerateurDao.<br/>
	 */
	protected transient String pathPackage;
	
	
	/**
	 * packageSousCouche : File :<br/>
	 * Package du fichier java à générer 
	 * sous pathPackage/packageSousCouche.<br/>
	 * Par exemple : model/metier/profil pour un GenerateurMetier 
	 * ou model/dao/metier/profil pour un GenerateurDao.<br/> 
	 */
	protected transient File packageSousCouche;
	
	
	/**
	 * sousPackageImpl : File :<br/>
	 * Sous-Package "impl" de l'objet metier à générer 
	 * sous model/metier/packageSousCouche.<br/>
	 * Par exemple : model/metier/profil/impl pour un GenerateurMetier 
	 * ou model/metier/dao/profil/impl pour un GenerateurDao.<br/>
	 */
	protected transient File sousPackageImpl;
	
	
	/**
	 * conceptModelise : String :<br/>
	 * concept modélisé par ce générateur.<br/>
	 * Par exemple : Profil.<br/>
	 * nom du package avec une majuscule en première position.<br/>
	 */
	protected transient String conceptModelise;
	
	
	/**
	 * nomSimpleInterface : String :<br/>
	 * Nom simple de l'interface à générer.<br/>
	 * Par exemple "IProfil".<br/>
	 */
	protected transient String nomSimpleInterface;
	
	
	/**
	 * interfaceJava : File :<br/>
	 * Interface (Fichier Java) de l'objet métier à générer.<br/>
	 * Par exemple : "IProfil.java" pour l'objet métier 
	 * Profil.java<br/>
	 */
	protected transient File interfaceJava;

	
	/**
	 * nomSimpleAbstractClass : String :<br/>
	 * Nom simple de la Classe Abstraite à générer.<br/>
	 * Par exemple "AbstractProfil".<br/>
	 */
	protected transient String nomSimpleAbstractClass;
	
	
	/**
	 * abstractClass : File :<br/>
	 * Classe Abstraite (Fichier Java) de l'objet métier à générer.<br/>
	 * Par exemple : "AbstractProfil.java" pour l'objet métier 
	 * Profil.java<br/>
	 */
	protected transient File abstractClass;
	
		
	/**
	 * nomSimpleObjetMetier : String :<br/>
	 * Nom simple de l'Objet metier à générer.<br/>
	 * Par exemple "ProfilSimple".<br/>
	 */
	protected transient String nomSimpleObjetMetier;
	
	
	/**
	 * objetMetier : File :<br/>
	 * Objet métier (Fichier Java) à générer.<br/>
	 * Par exemple : Profil.java<br/>
	 */
	protected transient File objetMetier;
	
	
		
	/**
	 * nomSimpleObjetMetierForm : String :<br/>
	 * Nom simple de l'Objet metier "Formulaire" à générer.<br/>
	 * Par exemple "ProfilSimpleForm".<br/>
	 */
	protected transient String nomSimpleObjetMetierForm;
	
	
	/**
	 * objetMetierForm : File :<br/>
	 * Objet métier "Formulaire" (Fichier Java) à générer.<br/>
	 * Par exemple : ProfilForm.java<br/>
	 */
	protected transient File objetMetierForm;

	
	/**
	 * mapAttributs : Map&lt;String,String&gt; :<br/>
	 * <ul>
	 * Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 */
	protected transient Map<String, String> mapAttributs;
	
	
	/**
	 * mapAttributsEquals : Map&lt;String,String&gt; :<br/>
	 * <ul>
	 * Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) utilisés dans equals() avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 */
	protected transient Map<String, String> mapAttributsEquals;
	
		
	/**
	 * mapRg : Map&lt;String, List&lt;String&gt;&gt; :<br/>
	 * <ul>
	 * Map&lt;String, List&lt;String&gt;&gt; ordonnée 
	 * contenant les listes de RG par attribut avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>List&lt;String&gt; : Liste des RG s'appliquant à l'attribut</li>
	 * </ul>
	 */
	protected transient Map<String, List<String>> mapRg;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(AbstractGenerateur.class);

	// *************************METHODES************************************/
	
	 /**
	 * method CONSTRUCTEUR GenerateurMetier() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>alimente dans chaque <b>Generateur concret</b> this.pathPackage 
	 * en demandant le chemin du package au 
	 * <b>BundleConfigurationProjetManager</b>.</li>
	 * </ul>
	 * <br/>
	 */
	public AbstractGenerateur() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void generer(
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

		/* génère le package this.packageSousCouche. */
		/* génère le package sousPackageImpl. */
		this.genererPackages(pNomPackage);
		
		/* alimente tous les attributs. */
		this.alimenterAttributs(pNomPackage
				, pNomInterface
					, pNomObjetMetier
						, pMapAttributs
							, pMapAttributsEquals
								, pMapRg);
		

		this.genererObjetsMetier(pNomInterface, pNomObjetMetier);
		
	} // Fin de generer(...).______________________________________________
	
	

	/**
	 * method genererHook(
	 * ) :<br/>
	 * <ul>
	 * <li>.</li>
	 * <li>.</li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	protected abstract void genererHook() throws Exception;
	

	
	/**
	 * method genererPackages(
	 * String pNomPackage) :<br/>
	 * <ul>
	 * <li>génère le package <b>this.packageSousCouche</b>.</li>
	 * <li>génère le package <b>sousPackageImpl</b>.</li>
	 * </ul>
	 * 
	 * @throws IOException
	 */
	private void genererPackages(
			final String pNomPackage) 
					throws IOException {
		
		/* génère le package this.packageSousCouche. */
		this.genererPackageSousCouche(pNomPackage);
		
		/* génère le package sousPackageImpl. */
		this.genererSousPackageImpl();
		
	} // Fin de genererPackages(...).______________________________________
	
	
	
	/**
	 * method genererPackageSousCouche(
	 * String pNomPackage) :<br/>
	 * <ul>
	 * <li>Génère le package pNomPackage devant 
	 * contenir le fichier java généré sous 
	 * pathPackage/packageSousCouche.</li>
	 * <li><b>alimente this.packageSousCouche</b> 
	 * avec le package généré.</li>
	 * <li>Par exemple : genererPackageSousCouche("profil") 
	 * génère le package model/metier/profil 
	 * pour un Generateur d'Objets metier</li>
	 * </ul>
	 *
	 * @param pNomPackage : String : nom du package devant 
	 * contenir l'objet métier généré.<br/>
	 * 
	 * @throws IOException
	 */
	private void genererPackageSousCouche(
			final String pNomPackage) 
					throws IOException {
		
		this.packageSousCouche 
			= this.gestionnaire
				.creerSousPackage(this.pathPackage, pNomPackage);
		
	} // Fin de genererPackageSousCouche(...)._____________________________
	

	
	/**
	 * method genererSousPackageImpl() :<br/>
	 * <ul>
	 * <li>Génère le package "impl" devant 
	 * contenir l'objet métier généré concret sous 
	 * pathPackage/packageSousCouche.</li>
	 * <li><b>alimente this.sousPackageImpl</b> 
	 * avec le package généré.</li>
	 * <li>Par exemple : genererSousPackageImpl() 
	 * avec this.packageSousCouche = profil 
	 * génère le package model/metier/profil/impl 
	 * pour un Generateur d'Objets metier.</li>
	 * </ul>
	 *
	 * @throws IOException
	 */
	private void genererSousPackageImpl() 
			throws IOException {
		
		this.sousPackageImpl 
			= this.gestionnaire
				.creerSousPackage(this.packageSousCouche, "impl");
		
	} // Fin de genererSousPackageImpl().__________________________________
	

	
	/**
	 * method alimenterAttributs(
	 * String pNomPackage) :<br/>
	 * <ul>
	 * <li><b>Alimente tous les attributs</b>.</li>
	 * <li>alimente this.conceptModelise.</li>
	 * </ul>
	 *
	 * @param pNomPackage : String.<br/>
	 */
	private void alimenterAttributs(
			final String pNomPackage
				, final String pNomInterface
					, final String pNomObjetMetier
			, final Map<String, String> pMapAttributs
				, final Map<String, String> pMapAttributsEquals
					, final Map<String, List<String>> pMapRg) {
		
		/* alimente this.conceptModelise. */
		this.alimenterConceptModelise(pNomPackage);
		
		/* alimente this.nomSimpleObjetMetier. */
		this.nomSimpleObjetMetier = pNomObjetMetier;
		
		/* alimente this.nomSimpleInterface. */
		this.alimenterNomSimpleInterface(pNomInterface);
		
		
		
		/* alimente la map des attributs de l'objet métier. */
		this.mapAttributs = pMapAttributs;
		
		/* alimente la map des attributs de l'objet métier 
		 * utilisés dans equals(). */
		this.mapAttributsEquals = pMapAttributsEquals;
		
		/* alimente la map des RG this.mapRg. */
		this.mapRg = pMapRg;

		
	} // Fin de alimenterAttributs(...).___________________________________
	

	
	/**
	 * method alimenterNomSimpleInterface(
	 * String pNomInterface) :<br/>
	 * <ul>
	 * <li>.</li>
	 * <li>.</li>
	 * </ul>
	 *
	 * @param pNomInterface :  :  .<br/>
	 */
	protected abstract void alimenterNomSimpleInterface(String pNomInterface);
	
	
	
	/**
	 * method alimenterConceptModelise(
	 * String pNomPackage) :<br/>
	 * <ul>
	 * <li>alimente this.conceptModelise.</li>
	 * <li>nom du package avec une majuscule en première position.</li>
	 * <li>Par exemple : Profil pour un objet metier 
	 * profil/ProfilSimple.</li>
	 * </ul>
	 *
	 * @param pNomPackage : String.<br/>
	 */
	private void alimenterConceptModelise(
			final String pNomPackage) {
		
		this.conceptModelise 
			= this.mettrePremiereEnMajusculeEtGarder(pNomPackage);
		
	} // Fin de alimenterConceptModelise(...)._____________________________
	

	
	/**
	 * method genererObjetsMetier(
	 * String pNomInterface
	 * , String pNomObjetMetier) :<br/>
	 * <ul>
	 * <li>.</li>
	 * <li>.</li>
	 * </ul>
	 *
	 * @param pNomInterface
	 * @param pNomObjetMetier :  :  .<br/>
	 * 
	 * @throws IOException 
	 */
	private void genererObjetsMetier(
			final String pNomInterface
				, final String pNomObjetMetier) throws IOException {
		
		/* Génère l'Interface vide de l'objet métier. */
		this.genererInterfaceObjetMetier(pNomInterface);
		
		/* Génère la classe abstraite vide de l'objet métier. */
		this.genererAbstractObjetMetier(pNomInterface);
		
		/* Génère la classe vide de l'objet métier. */
		this.genererObjetMetier(pNomObjetMetier);
		
	} // Fin de genererObjetsMetier(...).________________________________
	
	
	
	/**
	 * method genererInterfaceObjetMetier(
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
	private void genererInterfaceObjetMetier(
			final String pNomInterface) 
					throws IOException {
		
		final String nomFichier = pNomInterface + ".java";
		
		this.interfaceJava 
			= this.gestionnaire
			.creerFichierDansPackage(
					nomFichier, this.packageSousCouche);
		
	} // Fin de genererInterfaceObjetMetier(...).__________________________
	

		
	/**
	 * method genererAbstractObjetMetier(
	 * String pNomInterface) :<br/>
	 * <ul>
	 * <li>Génère le fichier vide abstractClass.java 
	 * sous packageSousCouche.</li>
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
		
		this.abstractClass
			= this.gestionnaire
			.creerFichierDansPackage(
					nomFichier, this.packageSousCouche);
		
	} // Fin de genererAbstractObjetMetier(...).___________________________
	

	
	/**
	 * method genererObjetMetier(
	 * String pNomObjetMetier) :<br/>
	 * <ul>
	 * <li>Génère le fichier vide pNomObjetMetier.java 
	 * sous packageSousCouche.impl.</li>
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
					nomFichier, this.sousPackageImpl);
		
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
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPathPackage() {
		return this.pathPackage;
	} // Fin de getPathPackage().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getPackageSousCouche() {
		return this.packageSousCouche;
	} // Fin de getPackageSousCouche().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getSousPackageImpl() {
		return this.sousPackageImpl;
	} // Fin de getSousPackageImpl().______________________________________

		
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getConceptModelise() {	
		return this.conceptModelise;
	} // Fin de getConceptModelise().______________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomSimpleInterface() {
		return this.nomSimpleInterface;
	} // Fin de getNomSimpleInterface().___________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getInterfaceJava() {
		return this.interfaceJava;
	} // Fin de getInterfaceJava().________________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomSimpleAbstractClass() {	
		return this.nomSimpleAbstractClass;
	} // Fin de getNomSimpleAbstractClass()._______________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getAbstractClass() {
		return this.abstractClass;
	} // Fin de getAbstractClass().________________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomSimpleObjetMetier() {	
		return this.nomSimpleObjetMetier;
	} // Fin de getNomSimpleObjetMetier()._________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getObjetMetier() {
		return this.objetMetier;
	} // Fin de getObjetMetier().__________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Map<String, String> getMapAttributs() {
		return this.mapAttributs;
	} // Fin de getMapAttributs()._________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Map<String, String> getMapAttributsEquals() {
		return this.mapAttributsEquals;
	} // Fin de getMapAttributsEquals().___________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Map<String, List<String>> getMapRg() {
		return this.mapRg;
	} // Fin de getMapRg().________________________________________________
	
	
	
} // FIN DE LA CLASSE AbstractGenerateur.------------------------------------
