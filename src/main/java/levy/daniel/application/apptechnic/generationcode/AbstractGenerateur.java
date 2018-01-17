package levy.daniel.application.apptechnic.generationcode;

import java.io.File;
import java.io.IOException;

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
public abstract class AbstractGenerateur {

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
	 * par exemple : model/metier ou model/dao/metier.<br/>
	 */
	protected transient String pathPackage;
	
	
	/**
	 * packageSousCouche : File :<br/>
	 * Package du fichier java à générer 
	 * sous pathPackage/packageSousCouche.<br/>
	 * Par exemple : model/metier/profil ou model/dao/metier/profil<br/> 
	 */
	protected transient File packageSousCouche;
	
	
	/**
	 * sousPackageImpl : File :<br/>
	 * Sous-Package "impl" de l'objet metier à générer 
	 * sous model/metier/packageSousCouche.<br/>
	 * Par exemple : model/metier/profil/impl<br/>
	 */
	protected transient File sousPackageImpl;
	

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
	 * en demandant le chemin du package  au 
	 * <b>BundleConfigurationProjetManager</b>.</li>
	 * </ul>
	 * <br/>
	 */
	public AbstractGenerateur() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	


	/**
	 * method genererPackageSousCouche(
	 * String pNomPackage) :<br/>
	 * <ul>
	 * <li>Génère le package pNomPackage devant 
	 * contenir le fichier java généré sous pathPackage/packageSousCouche.</li>
	 * <li>alimente this.packageSousCouche avec le package généré.</li>
	 * <li>Par exemple : genererPackageSousCouche("profil") 
	 * génère le package model/metier/profil</li>
	 * </ul>
	 *
	 * @param pNomPackage : String : nom du package devant 
	 * contenir l'objet métier généré.<br/>
	 * 
	 * @throws IOException
	 */
	protected void genererPackageSousCouche(
			final String pNomPackage) 
			throws IOException {
		
		this.packageSousCouche 
			= this.gestionnaire
				.creerSousPackage(this.pathPackage, pNomPackage);
		
	} // Fin de genererPackageSousCouche(...)._____________________________
	

	
	/**
	 * method getPathPackage() :<br/>
	 * Getter du path absolu du repertoire visé 
	 * par this.pathPackage.<br/>
	 * <br/>
	 *
	 * @return pathPackage : String.<br/>
	 */
	public final String getPathPackage() {
		return this.pathPackage;
	} // Fin de getPathPackage().__________________________________________


	
	/**
	 * method getPackageSousCouche() :<br/>
	 * Getter du Package du fichier java à générer.<br/>
	 * Par exemple : model/metier/profil<br/> 
	 * <br/>
	 *
	 * @return packageSousCouche : File.<br/>
	 */
	public final File getPackageSousCouche() {
		return this.packageSousCouche;
	} // Fin de getPackageSousCouche().____________________________________


	
	/**
	 * method getSousPackageImpl() :<br/>
	 * Getter du Sous-Package "impl" du fichier java à générer.<br/>
	 * Par exemple : model/metier/profil/impl<br/>
	 * <br/>
	 *
	 * @return sousPackageImpl : File.<br/>
	 */
	public final File getSousPackageImpl() {
		return this.sousPackageImpl;
	} // Fin de getSousPackageImpl().______________________________________

	

	
} // FIN DE LA CLASSE AbstractGenerateur.------------------------------------
