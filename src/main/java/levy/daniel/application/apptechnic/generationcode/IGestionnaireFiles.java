package levy.daniel.application.apptechnic.generationcode;

import java.io.File;
import java.io.IOException;

/**
 * INTERFACE <b>IGestionnaireFiles</b> :<br/>
 * Spécialiste de l'écriture sur disque.<br/>
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
public interface IGestionnaireFiles {

	/**
	 * method creerSousPackage(
	 * String pPathPackage
	 * , String pNomPackage) :<br/>
	 * <ul>
	 * <li>Créer un sous-Package pNomPackage de pPathPackage.</li>
	 * <li>Ne tente la création que si le package 
	 * pNomPackage n'existe pas déjà.</li>
	 * <li>retourne le package existant sinon.</li>
	 * <li>Par exemple : creerSousPackage(pathMetier, "profil") 
	 * crée un sous-package "profil" 
	 * sous "model/metier".</li>
	 * </ul>
	 * retourne null si pPathPackage est blank.<br/>
	 * retourne null si pNomPackage est blank.<br/>
	 * retourne null si packagePere visé par pPathPackage 
	 * n'existe pas.<br/>
	 * retourne null si packagePere visé par pPathPackage 
	 * n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pPathPackage : String : chemin du package père.<br/>
	 * @param pNomPackage : String : nom du package à créer.<br/>
	 * 
	 * @return : File : le package créé.<br/>
	 * 
	 * @throws IOException 
	 */
	File creerSousPackage(String pPathPackage, String pNomPackage) 
			throws IOException;

	
	
	/**
	 * method creerSousPackage(
	 * File pPackagePere
	 * , String pNomPackage) :<br/>
	 * <ul>
	 * <li>Créer un sous-Package pNomPackage 
	 * sous le package pPackagePere.</li>
	 * <li>Ne tente la création que si le package 
	 * pNomPackage n'existe pas déjà.</li>
	 * <li>retourne le package existant sinon.</li>
	 * <li>Par exemple : creerSousPackage(packageMetier, "profil") 
	 * crée un sous-package "profil" 
	 * sous "model/metier".</li>
	 * </ul>
	 * retourne null si pPackagePere == null.<br/>
	 * retourne null si pPackagePere n'existe pas.<br/>
	 * retourne null si pPackagePere n'est pas un répertoire.<br/>
	 * retourne null si pNomPackage est blank.<br/>
	 * <br/>
	 * 
	 *
	 * @param pPackagePere : File : Package sous lequel 
	 * on veut créer pNomPackage.<br/>
	 * @param pNomPackage : String : nom du package à créer.<br/>
	 * 
	 * @return : File : le package créé.<br/>
	 * 
	 * @throws IOException
	 */
	File creerSousPackage(File pPackagePere, String pNomPackage) 
			throws IOException;
	
	

	/**
	 * method creerFichierDansPackage(
	 * String pNomFile
	 * , File pPackage) :<br/>
	 * <ul>
	 * <li>Crée et retourne le fichier pNomFile 
	 * sous le package pPackage.</li>
	 * <li>Ne tente la création que si le fichier 
	 * pNomFile n'existe pas déjà.</li>
	 * <li>retourne le fichier existant sinon.</li>
	 * <li>Le fichier créé est vide.</li>
	 * <li>Par exemple : creerFichierDansPackage(
	 * "IProfil.java", packageProfil) crée le fichier 
	 * model/metier/profil/IProfil.java</li>
	 * </ul>
	 * retourne null si pNomFile est blank.<br/>
	 * retourne null si pPackage == null.<br/>
	 * retourne null si pPackage n'existe pas.<br/>
	 * retourne null si pPackage n'est pas un répertoire.<br/>
	 * <br/>
	 * 
	 *
	 * @param pNomFile : String : nom du fichier à créer.<br/>
	 * @param pPackage : File : package sous lequel créer pNomFile.<br/>
	 * 
	 * @return : File : fichier créé.<br/>
	 * 
	 * @throws IOException 
	 */
	File creerFichierDansPackage(String pNomFile, File pPackage) 
			throws IOException;

	

} // FIN DE L'INTERFACE IGestionnaireFiles.----------------------------------