package levy.daniel.application.apptechnic.generationcode.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class GestionnaireFiles :<br/>
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
 * @since 5 janv. 2018
 *
 */
public class GestionnaireFiles {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(GestionnaireFiles.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR GestionnaireFiles() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public GestionnaireFiles() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
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
	public File creerSousPackage(
			final String pPathPackage
				, final String pNomPackage) throws IOException {
		
		/* retourne null si pPathPackage est blank. */
		if (StringUtils.isBlank(pPathPackage)) {
			return null;
		}
		
		/* retourne null si pNomPackage est blank. */
		if (StringUtils.isBlank(pNomPackage)) {
			return null;
		}
		
		final File packagePere = new File(pPathPackage);
				
		/* retourne null si packagePere visé par 
		 * pPathPackage n'existe pas. */
		if (!packagePere.exists()) {
			return null;
		}
		
		/* retourne null si packagePere visé par pPathPackage 
		 * n'est pas un répertoire. */
		if (!packagePere.isDirectory()) {
			return null;
		}
		
				
		final Path pathPackagePere = packagePere.toPath();
		
		/* rajoute pNomPackage au path du package père. */
		final Path pathPackageACreer 
			= pathPackagePere.resolve(pNomPackage);
		
		/* CREATION DU REPERTOIRE SI INEXISTANT. */
		if (!Files.exists(pathPackageACreer)) {
			Files.createDirectory(pathPackageACreer);
		}
		
		return pathPackageACreer.toFile();
		
	} // Fin de creerSousPackage(...)._____________________________________
	
	
	
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
	public File creerSousPackage(
			final File pPackagePere
				, final String pNomPackage) throws IOException {
		
		/* retourne null si pPackagePere == null. */
		if (pPackagePere == null) {
			return null;
		}
		
		/* retourne null si pPackagePere n'existe pas. */
		if (!pPackagePere.exists()) {
			return null;
		}
		
		/* retourne null si pPackagePere n'est pas un répertoire. */
		if (!pPackagePere.isDirectory()) {
			return null;
		}
		
		/* retourne null si pNomPackage est blank. */
		if (StringUtils.isBlank(pNomPackage)) {
			return null;
		}
		
		final Path pathPackagePere = pPackagePere.toPath();
		
		/* rajoute pNomPackage au path du package père. */
		final Path pathPackageACreer 
			= pathPackagePere.resolve(pNomPackage);
		
		/* CREATION DU REPERTOIRE SI INEXISTANT. */
		if (!Files.exists(pathPackageACreer)) {
			Files.createDirectory(pathPackageACreer);
		}
		
		return pathPackageACreer.toFile();
		
	} // Fin de creerSousPackage(....).____________________________________
	

	
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
	public File creerFichierDansPackage(
			final String pNomFile, final File pPackage) 
			throws IOException {
		
		/* retourne null si pNomFile est blank. */
		if (StringUtils.isBlank(pNomFile)) {
			return null;
		}
		
		/* retourne null si pPackage == null. */
		if (pPackage == null) {
			return null;
		}
		
		/* retourne null si pPackage n'existe pas. */
		if (!pPackage.exists()) {
			return null;
		}
		
		/* retourne null si pPackage n'est pas un répertoire. */
		if (!pPackage.isDirectory()) {
			return null;
		}

		final Path pathPackage = pPackage.toPath();
		
		/* rajoute pNomPackage au path du package père. */
		final Path pathFichierACreer 
			= pathPackage.resolve(pNomFile);
		
		/* CREATION DU FICHIER SI INEXISTANT. */
		if (!Files.exists(pathFichierACreer)) {
			Files.createFile(pathFichierACreer);
		}
		
		return pathFichierACreer.toFile();
		
	} // Fin de creerFichierDansPackage(...).______________________________
	
	
	
} // FIN DE LA CLASSE GestionnaireFiles.-------------------------------------
