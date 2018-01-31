package levy.daniel.application.apptechnic.generationcode;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.configurationmanagers.ManagerPaths;

/**
 * CLASSE <b>GestionnaireProjet</b> :<br/>
 * Classe <b>utilitaire (méthodes static)</b> chargée de gérer 
 * les données de configuration du code à générer 
 * (chemin du Workspace, nom du projet, path des sources java, ...).<br/>
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
 * @since 30 janv. 2018
 *
 */
public final class GestionnaireProjet {

	// ************************ATTRIBUTS************************************/

	/**
	 * GESTIONNAIRE_PROJET : String :<br/>
	 * "Classe GestionnaireProjet".<br/>
	 */
	public static final String GESTIONNAIRE_PROJET 
		= "Classe GestionnaireProjet";

	
	/**
	 * pathWorkspaceString : String :<br/>
	 * <ul>
	 * <li><b>path du workspace Eclipse</b> dans lequel est 
	 * situé le projet dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon</code></li>
	 * </ul>
	 */
	private static String pathWorkspaceString;

	
	/**
	 * pathWorkspace : Path :<br/>
	 * <ul>
	 * <li><b>path du workspace Eclipse</b> dans lequel est 
	 * situé le projet dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon</code></li>
	 * </ul>
	 */
	private static Path pathWorkspace;
	
	
	/**
	 * fileWorkspace : File :<br/>
	 * <ul>
	 * <li><b>File modélisant le workspace Eclipse</b> dans lequel est 
	 * situé le projet dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon</code></li>
	 * </ul>
	 */
	private static File fileWorkspace;
	
	
	private static String nomProjet;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GestionnaireProjet.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR GestionnaireProjet() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	private GestionnaireProjet() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	
	/**
	 * method alimenterAttributs(
	 * ) :<br/>
	 * <ul>
	 * <li>.</li>
	 * <li>.</li>
	 * </ul>
	 *
	 * @param pPathWorkspaceString
	 * @throws Exception :  :  .<br/>
	 */
	public static void alimenterAttributs(final String pPathWorkspaceString) 
				throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			alimenterPathWorkspaceString(pPathWorkspaceString);
			
		} // Fin de synchronized._______________________
		
	}
	
	
	
	/**
	 * method alimenterPathWorkspaceString(
	 * String pString) :<br/>
	 * <ul>
	 * <li><b>alimente pathWorkspaceString</b>.</li>
	 * <li><b>alimente pathWorkspace</b>.</li>
	 * <li><b>alimente fileWorkspace</b>.</li>
	 * <ol>
	 * <li>alimente pathWorkspaceString avec pString si pString 
	 * n'est pas blank et pointe sur un dossier existant.</li>
	 * <li>alimente pathWorkspaceString avec une valeur par défaut 
	 * si pString n'est pas blank mais ne pointe sur rien ou 
	 * sur un fichier simple.</li>
	 * <li>alimente pathWorkspaceString avec une valeur par défaut 
	 * si pString est blank.</li>
	 * </ol>
	 * <li>la valeur par défaut est :</li>
	 * <ol>
	 * <li>la valeur du path stockée dans config_projet.properties si elle existe.</li>
	 * <li>le path du présent Workspace sinon.</li>
	 * </ol>
	 * </ul>
	 *
	 * @param pString : String : chemin absolu du Workspace.<br/>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterPathWorkspaceString(
			final String pString) throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (pathWorkspaceString == null) {
				
				if (parametreExistant(pString)) {
					
					if (existeDossier(pString)) {
						pathWorkspaceString = pString;
					} else {
						pathWorkspaceString 
							= fournirPathWorkspaceParDefaut();
					}					
				} else {
					pathWorkspaceString 
						= fournirPathWorkspaceParDefaut();
				}
				
				/* alimente pathWorkspace. */
				alimenterPathWorkspace();
				
				/* alimente fileWorkspace. */
				alimenterFileWorkspace();
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterPathWorkspaceString(...)._________________________
	

	
	/**
	 * method alimenterPathWorkspace() :<br/>
	 * <ul>
	 * <li><b>alimente pathWorkspace</b> 
	 * à partir de pathWorkspaceString.</li>
	 * <li>Utilise Paths.get(pathWorkspaceString).</li>
	 * </ul>
	 * ne fait rien si pathWorkspaceString est null.<br/>
	 * <br/>
	 */
	private static void alimenterPathWorkspace() {
		
		/* ne fait rien si pathWorkspaceString est null. */
		if (pathWorkspaceString == null) {
			return;
		}
		
		pathWorkspace = Paths.get(pathWorkspaceString);
		
	} // Fin de alimenterPathWorkspace().__________________________________
	
	
	
	/**
	 * method alimenterFileWorkspace() :<br/>
	 * <ul>
	 * <li><b>alimente fileWorkspace</b> 
	 * à partir de pathWorkspace.</li>
	 * <li>Utilise pathWorkspace.toFile().</li>
	 * </ul>
	 * ne fait rien si pathWorkspace est null.<br/>
	 * <br/>
	 */
	private static void alimenterFileWorkspace() {
				
		/* ne fait rien si pathWorkspace est null. */
		if (pathWorkspace == null) {
			return;
		}

		fileWorkspace = pathWorkspace.toFile();
		
	} // Fin de alimenterFileWorkspace().__________________________________
	
	
	
	/**
	 * method fournirPathWorkspaceParDefaut() :<br/>
	 * <ul>
	 * <li>retourne un chemin pour le Workspace Eclipse par défaut.</li>
	 * <li>retourne le path du Workspace dans configuration_projet.properties si il existe.</li>
	 * <li>retourne le path du présent Workspace sinon.</li>
	 * </ul>
	 *
	 * @return String : path d'un Workspace par défaut.<br/>
	 * 
	 * @throws Exception
	 */
	private static String fournirPathWorkspaceParDefaut() 
				throws Exception {
		
		/* récupération du path du Workspace dans 
		 * configuration_projet.properties. */
		final String pathWorkspaceConfProp 
			= BundleConfigurationProjetManager.getPathWorkspace();
		
		/* retourne le path du Workspace dans 
		 * configuration_projet.properties si il existe. */
		if (parametreExistant(pathWorkspaceConfProp)) {
			return pathWorkspaceConfProp;
		}
		
		/* retourne le path du présent Workspace sinon. */
		final String pathPresentWorkspaceString 
			= ManagerPaths.getPathPresentWorkspaceString();
		
		return pathPresentWorkspaceString;
		
	} // Fin de fournirPathWorkspaceParDefaut().___________________________
	
	
	
	/**
	 * method parametreExistant(
	 * String pString) :<br/>
	 * <ul>
	 * <li>retourne un boolean stipulant si 
	 * pString n'est pas blank.</li>
	 * <li>retourne true si pString n'est pas blank.</li>
	 * </ul>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : boolean : true si pString n'est pas blank.<br/>
	 */
	private static boolean parametreExistant(
			final String pString) {
		
		if (!StringUtils.isBlank(pString)) {
			return true;
		}
		
		return false;
		
	} // Fin de parametreExistant(...).____________________________________
	

	
	/**
	 * method existeDossier(
	 * String pString) :<br/>
	 * <ul>
	 * <li>retourne un boolean stipulant si 
	 * un dossier existe au chemin pString.</li>
	 * <li>retourne true si un dossier existe au chemin pString.</li>
	 * </ul>
	 * retourne false si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : boolean : 
	 * true si un dossier existe au chemin pString.<br/>
	 */
	private static boolean existeDossier(
			final String pString) {
		
		/* retourne false si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return false;
		}
		
		final Path path = Paths.get(pString);
		final File file = path.toFile();
		
		/* retourne true si un dossier existe au chemin pString. */
		if (file.exists() && file.isDirectory()) {
			return true;
		}
		
		return false;
		
	} // Fin de existeDossier(...).________________________________________
	
	
	
	/**
	 * method getPathWorkspaceString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du workspace Eclipse</b> dans lequel est 
	 * situé le projet dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon</code></li>
	 * </ul>
	 *
	 * @return pathWorkspaceString : String.<br/>
	 */
	public static String getPathWorkspaceString() {
		return pathWorkspaceString;
	} // Fin de getPathWorkspaceString().__________________________________
	
	
	
	/**
	 * method getPathWorkspace() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du workspace Eclipse</b> dans lequel est 
	 * situé le projet dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon</code></li>
	 * </ul>
	 *
	 * @return pathWorkspace : java.nio.file.Path.<br/>
	 */
	public static Path getPathWorkspace() {	
		return pathWorkspace;
	} // Fin de getPathWorkspace().________________________________________


	
	/**
	 * method getFileWorkspace() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le workspace Eclipse</b> 
	 * dans lequel est 
	 * situé le projet dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon</code></li>
	 * </ul>
	 *
	 * @return fileWorkspace : File.<br/>
	 */
	public static File getFileWorkspace() {	
		return fileWorkspace;
	} // Fin de getFileWorkspace().________________________________________



	/**
	 * method reinitialiserAttributs() :<br/>
	 * <ul>
	 * <li>Met à null tous les Singletons en atribut</li>
	 * <li>A n'utiliser que pour les tests.</li>
	 * </ul>
	 */
	public static void reinitialiserAttributs() {
		pathWorkspaceString = null;
		pathWorkspace = null;
		fileWorkspace = null;
	} // Fin de reinitialiserAttributs().__________________________________

	

} // FIN DE LA CLASSE GestionnaireProjet.------------------------------------
