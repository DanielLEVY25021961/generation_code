package levy.daniel.application.apptechnic.generationcode;

import java.io.File;

/**
 * INTERFACE <b>IGenerateur</b> :<br/>
 * Generateur de code.<br/>
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
 * @since 17 janv. 2018
 *
 */
public interface IGenerateur {

	
	/**
	 * method generer() :<br/>
	 * <ul>
	 * <li><b>Génère le code model.metier</b>.</li>
	 * <li>Génère les packages this.packageSousCouche 
	 * et this.sousPackageImpl.</li>
	 * <li>alimente tous les attributs d'instance.</li>
	 * <li>Génère tous les fichiers java vides.</li>
	 * 
	 * <li>Génère le code de l'interface 
	 * dans this.iObjetMetier.</li>
	 * <li>génère le code de la classe abstraite 
	 * dans this.abstractObjetMetier.</li>
	 * </ul>	 *
	 * 
	 * @throws Exception
	 */
	void generer() throws Exception;



	/**
	 * method getPathPackage() :<br/>
	 * Getter du path absolu du repertoire visé 
	 * par this.pathPackage.<br/>
	 * <br/>
	 *
	 * @return pathPackage : String.<br/>
	 */
	String getPathPackage();
	


	/**
	 * method getPackageSousCouche() :<br/>
	 * Getter du Package du fichier java à générer.<br/>
	 * Par exemple : model/metier/profil<br/> 
	 * <br/>
	 *
	 * @return packageSousCouche : File.<br/>
	 */
	File getPackageSousCouche();



	/**
	 * method getSousPackageImpl() :<br/>
	 * Getter du Sous-Package "impl" du fichier java à générer.<br/>
	 * Par exemple : model/metier/profil/impl<br/>
	 * <br/>
	 *
	 * @return sousPackageImpl : File.<br/>
	 */
	File getSousPackageImpl();



	/**
	 * method getNomSimpleInterface() :<br/>
	 * Getter du Nom simple de l'interface à générer.<br/>
	 * Par exemple "IProfil".<br/>
	 * <br/>
	 *
	 * @return nomSimpleInterface : String.<br/>
	 */
	String getNomSimpleInterface();



	/**
	 * method getInterfaceJava() :<br/>
	 * Getter de l'Interface de l'objet métier à générer.<br/>
	 * Par exemple : "IProfil.java" pour l'objet métier Profil.java<br/>
	 * <br/>
	 *
	 * @return interfaceJava : File.<br/>
	 */
	File getInterfaceJava();



	/**
	 * method getNomSimpleAbstractClass() :<br/>
	 * Getter du Nom simple de la Classe Abstraite à générer.<br/>
	 * Par exemple "AbstractProfil".<br/>
	 * <br/>
	 *
	 * @return nomSimpleAbstractClass : String.<br/>
	 */
	String getNomSimpleAbstractClass();



	/**
	 * method getAbstractClass() :<br/>
	 * Getter de la Classe Abstraite de l'objet métier à générer.<br/>
	 * Par exemple : "AbstractProfil.java" pour l'objet métier 
	 * Profil.java<br/>
	 * <br/>
	 *
	 * @return abstractClass : File.<br/>
	 */
	File getAbstractClass();



	/**
	 * method getNomSimpleConcreteClass() :<br/>
	 * Getter du Nom simple du Fichier Java concret à générer.<br/>
	 * Par exemple : <br/>
	 * <code>ProfilSimple</code> dans la couche metier.profil 
	 * ou <code>DaoProfilSimple</code> dans 
	 * la couche DAO.metier.profil.<br/>
	 * <br/>
	 *
	 * @return nomSimpleConcreteClass : String.<br/>
	 */
	String getNomSimpleConcreteClass();



	/**
	 * method getConcreteClass() :<br/>
	 * Getter du Fichier Java concret à générer.<br/>
	 * Par exemple : <br/>
	 * <code>ProfilSimple.java</code> dans la couche metier.profil 
	 * ou <code>DaoProfilSimple.java</code> dans 
	 * la couche DAO.metier.profil.<br/>
	 * <br/>
	 *
	 * @return concreteClass : File.<br/>
	 */
	File getConcreteClass();



} // FIN DE L'INTERFACE IGenerateur.-----------------------------------------
