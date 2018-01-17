package levy.daniel.application.apptechnic.generationcode;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
	 * method generer(
	 * String pNomPackage
	 * , String pNomInterface
	 * , String pNomObjetMetier
	 * , Map<String, String> pMapAttributs
	 * , Map<String, String> pMapAttributsEquals) :<br/>
	 * <ul>
	 * <li><b>Génère le code model.metier</b>.</li>
	 * <li>Traite les mauvais paramètres.</li>
	 * <li>Génère les packages this.packageSousCouche 
	 * et génère le package sousPackageImpl.</li>
	 * <li>alimente tous les attributs.</li>
	 * 
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
	void generer(
			String pNomPackage
				, String pNomInterface
					, String pNomObjetMetier
			, Map<String, String> pMapAttributs
				, Map<String, String> pMapAttributsEquals
					, Map<String, List<String>> pMapRg) throws IOException;



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
	 * method getConceptModelise() :<br/>
	 * Getter du concept modélisé par ce générateur.<br/>
	 * nom du package avec une majuscule en première position.<br/>
	 * <br/>
	 *
	 * @return conceptModelise : String.<br/>
	 */
	String getConceptModelise();



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
	 * method getNomSimpleObjetMetier() :<br/>
	 * Getter du Nom simple de l'Objet metier à générer.<br/>
	 * Par exemple "ProfilSimple".<br/>
	 * <br/>
	 *
	 * @return nomSimpleObjetMetier : String.<br/>
	 */
	String getNomSimpleObjetMetier();



	/**
	 * method getObjetMetier() :<br/>
	 * Getter de l'Objet métier à générer.<br/>
	 * Par exemple : Profil.java<br/>
	 * <br/>
	 *
	 * @return objetMetier : File.<br/>
	 */
	File getObjetMetier();



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
	Map<String, String> getMapAttributs();


	
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
	Map<String, String> getMapAttributsEquals();



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
	Map<String, List<String>> getMapRg();
	
	

} // FIN DE L'INTERFACE IGenerateur.-----------------------------------------
