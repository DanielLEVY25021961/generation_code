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
	 * nomPackage : String :<br/>
	 * <b>nom du package à créer dans chaque couche</b>.<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "profil".<br/>
	 */
	protected static String nomPackage;
	
	
	/**
	 * conceptModelise : String :<br/>
	 * <b>concept modélisé par ce générateur</b>.<br/>
	 * nom du package à créer dans chaque couche 
	 * avec une majuscule en première position.<br/>
	 * Par exemple : Profil pour le nomPackage "profil".<br/>
	 */
	protected static String conceptModelise;
	

	/**
	 * nomInterfaceMetier : String :<br/>
	 * <b>nom de l'interface de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "IProfil".<br/>
	 */
	protected static String nomInterfaceMetier;
	
	
	/**
	 * nomAbstractClassMetier : String :<br/>
	 * <b>nom de la classe abstraite de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * déduit du nom de l'interface metier 
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "AbstractProfil".<br/>
	 */
	protected static String nomAbstractClassMetier;
	
	
	/**
	 * nomClassMetier : String :<br/>
	 * <b>nom de la classe concrète de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.impl).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "Profil".<br/>
	 */
	protected static String nomClassMetier;
	
			
	/**
	 * nomClassMetierForm : String :<br/>
	 * Nom simple de l'Objet metier "Formulaire" à générer.<br/>
	 * Par exemple "ProfilSimpleForm" pour un ProfilSimple.<br/>
	 */
	protected static String nomClassMetierForm;
	
	
	/**
	 * classMetierForm : File :<br/>
	 * Objet métier "Formulaire" (Fichier Java) à générer.<br/>
	 * Par exemple : ProfilForm.java pour un ProfilSimple.java.<br/>
	 */
	protected static File classMetierForm;

	
	/**
	 * mapAttributs : Map&lt;String,String&gt; :<br/>
	 * <ul>
	 * Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 */
	protected static Map<String, String> mapAttributs;
	
	
	/**
	 * mapAttributsEquals : Map&lt;String,String&gt; :<br/>
	 * <ul>
	 * Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) utilisés dans equals() avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 */
	protected static Map<String, String> mapAttributsEquals;
	
		
	/**
	 * mapRg : Map&lt;String, List&lt;String&gt;&gt; :<br/>
	 * <ul>
	 * Map&lt;String, List&lt;String&gt;&gt; ordonnée 
	 * contenant les listes de RG par attribut avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>List&lt;String&gt; : Liste des RG s'appliquant à l'attribut</li>
	 * </ul>
	 */
	protected static Map<String, List<String>> mapRg;

	
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
	 * nomSimpleInterface : String :<br/>
	 * Nom simple de l'interface java à générer.<br/>
	 * Par exemple "IProfil" pour l'objet métier 
	 * Profil.java ou "IDaoProfil" pour l'objet DAO 
	 * DaoProfilSimple.java.<br/>
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
	 * nomSimpleConcreteClass : String :<br/>
	 * Nom simple du Fichier Java concret à générer.<br/>
	 * Par exemple : <br/>
	 * <code>ProfilSimple</code> dans la couche metier.profil 
	 * ou <code>DaoProfilSimple</code> dans 
	 * la couche DAO.metier.profil.<br/>
	 */
	protected transient String nomSimpleConcreteClass;
	
	
	/**
	 * concreteClass : File :<br/>
	 * Fichier Java concret à générer.<br/>
	 * Par exemple : <br/>
	 * <code>Profil.java</code> dans la couche metier.profil 
	 * ou <code>DaoProfil.java</code> dans 
	 * la couche DAO.metier.profil.<br/>
	 */
	protected transient File concreteClass;
	
	

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
	 * method configurer(
	 * String pNomPackage
	 * , String pNomInterface
	 * , String pNomObjetMetier
	 * , Map&lt;String, String&gt; pMapAttributs
	 * , Map<String, String> pMapAttributsEquals
	 * , Map&lt;String, List&lt;String&gt;&gt; pMapRg) :<br/>
	 * <ul>
	 * <li>alimente tous les attributs static des Générateurs.</li>
	 * </ul>
	 * retourne si pNomPackage est blank.<br/>
	 * retourne si pNomInterface est blank.<br/>
	 * retourne si pNomObjetMetier est blank.<br/>
	 * retourne si pNomPackage n'est pas conforme.<br/>
	 * retourne si pNomInterface n'est pas conforme.<br/>
	 * <br/>
	 *
	 * @param pNomPackage : String : .<br/>
	 * @param pNomInterface : String : .<br/>
	 * @param pNomObjetMetier : String : .<br/>
	 * @param pMapAttributs : Map&lt;String, String&gt; :  .<br/>
	 * @param pMapAttributsEquals : Map&lt;String, String&gt; : .<br/>
	 * @param pMapRg : Map&lt;String, List&lt;String&gt;&gt; :  .<br/>
	 */
	public static void configurer(
			final String pNomPackage
				, final String pNomInterface
					, final String pNomObjetMetier
			, final Map<String, String> pMapAttributs
				, final Map<String, String> pMapAttributsEquals
					, final Map<String, List<String>> pMapRg) {
		
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
		
		
		/* alimente tous les attributs static des Générateurs. */
		alimenterAttributsStatic(pNomPackage
				, pNomInterface
					, pNomObjetMetier
						, pMapAttributs
							, pMapAttributsEquals
								, pMapRg);

		
	} // Fin de configurer(...).___________________________________________
	

	
	/**
	 * method alimenterAttributsStatic(
	 * String pNomPackage
	 * , String pNomInterface
	 * , String pNomObjetMetier
	 * , Map&lt;String, String&gt; pMapAttributs
	 * , Map<String, String> pMapAttributsEquals
	 * , Map&lt;String, List&lt;String&gt;&gt; pMapRg) :<br/>
	 * <ul>
	 * <li><b>Alimente tous les attributs static des Generateurs</b>.</li>
	 * <li>alimente nomPackage.</li>
	 * <li>alimente conceptModelise.</li>
	 * <li>alimente nomInterfaceMetier.</li>
	 * <li>alimente nomAbstractClassMetier.</li>
	 * <li>alimente nomClasseMetier.</li>
	 * <li>alimente nomClasseMetierForm.</li>
	 * <li>alimente mapAttributs.</li>
	 * <li>alimente mapAttributsEquals.</li>
	 * <li>alimente mapRg.</li>
	 * </ul>
	 *
	 * @param pNomPackage : String : .<br/>
	 * @param pNomInterface : String : .<br/>
	 * @param pNomObjetMetier : String : .<br/>
	 * @param pMapAttributs : Map&lt;String, String&gt; :  .<br/>
	 * @param pMapAttributsEquals : Map&lt;String, String&gt; : .<br/>
	 * @param pMapRg : Map&lt;String, List&lt;String&gt;&gt; :  .<br/>
	 */
	private static void alimenterAttributsStatic(
			final String pNomPackage
				, final String pNomInterface
					, final String pNomObjetMetier
			, final Map<String, String> pMapAttributs
				, final Map<String, String> pMapAttributsEquals
					, final Map<String, List<String>> pMapRg) {
		
		/* alimente nomPackage. */
		alimenterNomPackage(pNomPackage);
		
		/* alimente conceptModelise. */
		alimenterConceptModelise(pNomPackage);
		
		/* alimente nomInterfaceMetier. */
		alimenterNomInterfaceMetier(pNomInterface);
		
		/* alimente nomAbstractClassMetier. */
		alimenterNomAbstractClassMetier(pNomInterface);
		
		/* alimente nomClasseMetier. */
		alimenterNomClassMetier(pNomObjetMetier);
		
		/* alimente nomClasseMetierForm. */
		alimenterNomClassMetierForm();
		
		/* alimente mapAttributs. */
		alimenterMapAttributs(pMapAttributs);
		
		/* alimente mapAttributsEquals. */
		alimenterMapAttributsEquals(pMapAttributsEquals);
		
		/* alimente mapRg. */
		alimenterMapRg(pMapRg);

	} // Fin de alimenterAttributsStatic(...)._____________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void generer() 
			throws Exception {
		
		/* GENERATION DES PACKAGES. */
		/* génère le package this.packageSousCouche. */
		/* génère le package sousPackageImpl. */
		this.genererPackages(nomPackage);
		
		/* ALIMENTATION DES ATTRIBUTS D'INSTANCE. */
		this.alimenterAttributs();
		
		/* GENERATION DES FICHIERS JAVA VIDES. */
		this.genererFichiersJava();
		
		/* APPEL DU HOOK. */
		this.genererHook();
		
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
	 * method alimenterAttributs() :<br/>
	 * <ul>
	 * <li>alimente this.nomSimpleInterface.</li>
	 * <li>alimente this.nomSimpleAbstractClass.</li>
	 * <li>alimente this.nomSimpleConcreteClass.</li>
	 * </ul>
	 */
	private void alimenterAttributs() {
		
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
	 * method genererFichiersJava() :<br/>
	 * <ul>
	 * <li>génère et alimente this.interfaceJava.</li>
	 * <li>génère et alimente this.abstractClass.</li>
	 * <li>génère et alimente this.concreteClass.</li>
	 * <li>génère et alimente this.objetMetierForm.</li>
	 * </ul>
	 *
	 * @throws IOException 
	 */
	private void genererFichiersJava() throws IOException {
		
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
			= this.gestionnaire
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
			= this.gestionnaire
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
			= this.gestionnaire
			.creerFichierDansPackage(
					nomFichier, this.sousPackageImpl);
		
	} // Fin de genererConcreteClass(...).___________________________________
	
	
	
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
	private static String genererNomAbstractClass(
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
	private static boolean conformeNomPackage(
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
	private static boolean conformeNomInterface(
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
	private static boolean conformeNomClasse(
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
	private static List<String> trouverCamel(
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
	private static String mettrePremiereEnMajusculeEtGarder(
			final String pString) {
		
		/* retourne null si pString == null. */
		if (pString == null) {
			return null;
		}
		
		return WordUtils.capitalize(pString);
		
	} // Fin de mettrePremiereEnMajusculeEtGarder(...).____________________
	

		
	/**
	 * method alimenterNomPackage() :<br/>
	 * Alimenteur du <b>nom du package à créer dans chaque couche</b>.<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "profil".<br/>
	 * <br/>
	 * 
	 * @param pNomPackage : String : 
	 * nom du package à créer dans chaque couche.<br/>
	 *
	 * @return nomPackage : String.<br/>
	 */
	private static String alimenterNomPackage(
			final String pNomPackage) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (nomPackage == null) {
				nomPackage = pNomPackage;
			}
			
			return nomPackage;
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterNomPackage(...).__________________________________


		
	/**
	 * method alimenterConceptModelise() :<br/>
	 * Alimenteur du <b>concept modélisé par ce générateur</b>.<br/>
	 * nom du package à créer dans chaque couche 
	 * avec une majuscule en première position.<br/>
	 * Par exemple : Profil pour le nomPackage "profil".<br/>
	 * <br/>
	 * 
	 * @param pNomPackage : String : 
	 * nom du package à créer dans chaque couche.<br/>
	 *
	 * @return conceptModelise : String.<br/>
	 */
	private static String alimenterConceptModelise(
			final String pNomPackage) {	
		
		synchronized (AbstractGenerateur.class) {
			
			if (conceptModelise == null) {
				conceptModelise = WordUtils.capitalize(pNomPackage);
			}
			
			return conceptModelise;
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterConceptModelise().________________________________


	
	/**
	 * method alimenterNomInterfaceMetier(
	 * String pNomInterface) :<br/>
	 * Alimenteur du <b>nom de l'interface de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "IProfil".<br/>
	 * <br/>
	 * 
	 * @param pNomInterface : String. 
	 *
	 * @return nomInterfaceMetier : String.<br/>
	 */
	private static String alimenterNomInterfaceMetier(
			final String pNomInterface) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (nomInterfaceMetier == null) {
				nomInterfaceMetier = pNomInterface;
			}
			
			return nomInterfaceMetier;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterNomInterfaceMetier(...).__________________________



	/**
	 * method alimenterNomAbstractClassMetier() :<br/>
	 * Alimenteur du <b>nom de la classe abstraite de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * déduit du nom de l'interface metier 
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "AbstractProfil".<br/>
	 *
	 *  @param pNomInterface : String. 
	 * 
	 * @return nomAbstractClassMetier : String .<br/>
	 */
	private static String alimenterNomAbstractClassMetier(
			final String pNomInterface) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (nomAbstractClassMetier == null) {
				nomAbstractClassMetier 
					= genererNomAbstractClass(pNomInterface);
			}
			
			return nomAbstractClassMetier;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterNomAbstractClassMetier(...).______________________


		
	/**
	 * method alimenterNomClassMetier(String)(
	 * String pNomObjetMetier) :<br/>
	 * Alimenteur du <b>nom de la classe concrète de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.impl).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "Profil".<br/>
	 * <br/>
	 *
	 * @param pNomObjetMetier : String.
	 * 
	 * @return nomClassMetier : String.<br/>
	 */
	private static String alimenterNomClassMetier(
			final String pNomObjetMetier) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (nomClassMetier == null) {
				nomClassMetier = pNomObjetMetier;
			}
			
			return nomClassMetier;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterNomClassMetier(String)(...).______________________


		
	/**
	 * method alimenterNomClassMetierForm() :<br/>
	 * Alimenteur du <b>nom de la classe concrète de l'Objet metier 
	 * "Formulaire" à créer dans la  couche metier</b> 
	 * (sous model.metier.impl).<br/>
	 * déduit de this.nomClassMetier.<br/>
	 * par exemple : "ProfilForm".<br/>
	 *
	 * @return nomClassMetierForm : String.<br/>
	 */
	private static String alimenterNomClassMetierForm() {
		
		synchronized (AbstractGenerateur.class) {
			
			if (nomClassMetierForm == null) {
				nomClassMetierForm = nomClassMetier + "Form";
			}
			
			return nomClassMetierForm;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterNomClassMetierForm()._____________________________
	
	
	
	/**
	 * method alimenterMapAttributs(
	 * Map&lt;String,String&gt; pMapAttributs) :<br/>
	 * <ul>
	 * Alimenteur de la Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 *
	 * @param pMapAttributs : Map&lt;String,String&gt;.<br/>
	 * 
	 * @return mapAttributs : Map&lt;String,String&gt;.<br/>
	 */
	private static Map<String, String> alimenterMapAttributs(
			final Map<String, String> pMapAttributs) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (mapAttributs == null) {
				mapAttributs = pMapAttributs;
			}
			
			return mapAttributs;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterMapAttributs().___________________________________
	
	
	
	/**
	 * method alimenterMapAttributsEquals(
	 * Map&lt;String,String&gt; pMapAttributsEquals) :<br/>
	 * <ul>
	 * Alimenteur de la Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) utilisés dans equals() avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 *
	 * @param pMapAttributsEquals : Map&lt;String,String&gt;.<br/>
	 * 
	 * @return mapAttributsEquals : Map&lt;String,String&gt;.<br/>
	 */
	private static Map<String, String> alimenterMapAttributsEquals(
			final Map<String, String> pMapAttributsEquals) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (mapAttributsEquals == null) {
				mapAttributsEquals = pMapAttributsEquals;
			}
			
			return mapAttributsEquals;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterMapAttributsEquals()._____________________________
	
	
		
	/**
	 * method alimenterMapRg(
	 * Map&lt;String, List&lt;String&gt;&gt; pMapRg) :<br/>
	 * <ul>
	 * Alimenteur de la Map&lt;String, List&lt;String&gt;&gt; ordonnée 
	 * contenant les listes de RG par attribut avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>List&lt;String&gt; : Liste des RG s'appliquant à l'attribut</li>
	 * </ul>
	 *
	 * @param pMapRg : Map&lt;String, List&lt;String&gt;&gt;.<br/>
	 * 
	 * @return mapRg : Map&lt;String, List&lt;String&gt;&gt;.<br/>
	 */
	private static Map<String, List<String>> alimenterMapRg(
			final Map<String, List<String>> pMapRg) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (mapRg == null) {
				mapRg = pMapRg;
			}
			
			return mapRg;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterMapRg().__________________________________________
	
	
	
	/**
	 * method getNomPackage() :<br/>
	 * Getter du <b>nom du package à créer dans chaque couche</b>.<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "profil".<br/>
	 * <br/>
	 * 
	 * @return nomPackage : String.<br/>
	 */
	public static final String getNomPackage() {
		return nomPackage;
	} // Fin de getNomPackage().___________________________________________


	
	/**
	 * method getConceptModelise() :<br/>
	 * Getter du <b>concept modélisé par ce générateur</b>.<br/>
	 * nom du package à créer dans chaque couche 
	 * avec une majuscule en première position.<br/>
	 * Par exemple : Profil pour le nomPackage "profil".<br/>
	 * <br/>
	 * 
	 * @return conceptModelise : String.<br/>
	 */
	public static final String getConceptModelise() {
		return conceptModelise;
	} // Fin de getConceptModelise().______________________________________


	
	/**
	 * method getNomInterfaceMetier() :<br/>
	 * Getter du <b>nom de l'interface de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "IProfil".<br/>
	 * <br/>
	 * 
	 * @return nomInterfaceMetier : String.<br/>
	 */
	public static final String getNomInterfaceMetier() {
		return nomInterfaceMetier;
	} // Fin de getNomInterfaceMetier().___________________________________


	
	/**
	 * method getNomAbstractClassMetier() :<br/>
	 * Getter du <b>nom de la classe abstraite de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * déduit du nom de l'interface metier 
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "AbstractProfil".<br/>
	 *
	 * @return nomAbstractClassMetier : String .<br/>
	 */
	public static final String getNomAbstractClassMetier() {
		return nomAbstractClassMetier;
	} // Fin de getNomAbstractClassMetier()._______________________________


	
	/**
	 * method getNomClassMetier() :<br/>
	 * Getter du <b>nom de la classe concrète de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.impl).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "Profil".<br/>
	 * <br/>
	 *
	 * @return nomClassMetier : String.<br/>
	 */
	public static final String getNomClassMetier() {
		return nomClassMetier;
	} // Fin de getNomClassMetier()._______________________________________


		
	/**
	 * method getNomClassMetierForm() :<br/>
	 * Getter du <b>nom de la classe concrète de l'Objet metier 
	 * "Formulaire" à créer dans la  couche metier</b> 
	 * (sous model.metier.impl).<br/>
	 * déduit de this.nomClassMetier.<br/>
	 * par exemple : "ProfilForm".<br/>
	 *
	 * @return nomClassMetierForm : String.<br/>
	 */
	public static final String getNomClassMetierForm() {
		return nomClassMetierForm;
	} // Fin de getNomClassMetierForm().___________________________________



	/**
	 * method getMapAttributs() :<br/>
	 * <ul>
	 * Getter de la Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 *
	 * @return mapAttributs : Map&lt;String,String&gt;.<br/>
	 */
	public static final Map<String, String> getMapAttributs() {
		return mapAttributs;
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
	 * @return mapAttributsEquals : Map&lt;String,String&gt;.<br/>
	 */
	public static final Map<String, String> getMapAttributsEquals() {
		return mapAttributsEquals;
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
	 * @return mapRg : Map&lt;String, List&lt;String&gt;&gt;.<br/>
	 */
	public static final Map<String, List<String>> getMapRg() {
		return mapRg;
	} // Fin de getMapRg().________________________________________________



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
	public final String getNomSimpleConcreteClass() {	
		return this.nomSimpleConcreteClass;
	} // Fin de getNomSimpleConcreteClass()._______________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getConcreteClass() {
		return this.concreteClass;
	} // Fin de getConcreteClass().________________________________________


	
} // FIN DE LA CLASSE AbstractGenerateur.------------------------------------
