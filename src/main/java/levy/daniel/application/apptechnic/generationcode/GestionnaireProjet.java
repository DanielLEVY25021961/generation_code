package levy.daniel.application.apptechnic.generationcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.configurationmanagers.ManagerPaths;

/**
 * CLASSE <b>GestionnaireProjet</b> :<br/>
 * <ul>
 * <li>Classe <b>utilitaire (méthodes static)</b> chargée de gérer 
 * les données de configuration du code à générer 
 * (chemin du Workspace, nom du projet, path des sources java, ...).</li>
 * <li>
 * classe chargée de fournir des SINGLETONS pour :
 * <ol>
 * <li>l'emplacement du <b>Workspace Eclipse</b> 
 * dans lequel générer le code <b>pathWorkspace</b>.</li>
 * <li>l'emplacement du <b>projet Eclipse</b> 
 * dans lequel générer le code <b>pathProjet</b>.</li>
 * <li>l'emplacement du <b>répertoire des sources</b> (src)
 * dans lequel générer le code <b>pathRepertoiresrc</b>.</li>
 * <li></li>
 * </ol>
 * </li>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * path.resolve, <br/>
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
	
	
	/**
	 * nomProjet : String :<br/>
	 * <ul>
	 * <li><b>nom du projet Eclipse</b> 
	 * dont on va générer le code.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>projet_users
	 * </code></li>
	 * </ul>
	 */
	private static String nomProjet;
	
	
	/**
	 * pathProjetString : String :<br/>
	 * <ul>
	 * <li><b>path du projet Eclipse</b> 
	 * dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathProjet = pathWorkspace + /nomProjet</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users
	 * </code></li>
	 * </ul>
	 */
	private static String pathProjetString;
	
	
	/**
	 * pathProjet : Path :<br/>
	 * <ul>
	 * <li><b>path du projet Eclipse</b> 
	 * dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathProjet = pathWorkspace + /nomProjet</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users
	 * </code></li>
	 * </ul>
	 */
	private static Path pathProjet;
	
	
	/**
	 * fileProjet : File :<br/>
	 * <ul>
	 * <li><b>File modélisant le projet Eclipse</b> 
	 * dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathProjet = pathWorkspace + /nomProjet</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users
	 * </code></li>
	 * </ul>
	 */
	private static File fileProjet;
	

	
	/**
	 * nomRepertoireSrc : String :<br/>
	 * <ul>
	 * <li><b>nom du répertoire des sources</b> (src) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>src
	 * </code></li>
	 * </ul>
	 */
	private static String nomRepertoireSrc;
	
	
	/**
	 * pathRepertoireSrcString : String :<br/>
	 * <ul>
	 * <li><b>path du répertoire des sources</b>  (src)
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathRepertoireSrc = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users/src
	 * </code></li>
	 * </ul>
	 */
	private static String pathRepertoireSrcString;
	
	
	/**
	 * pathRepertoireSrc : Path :<br/>
	 * <ul>
	 * <li><b>path du répertoire des sources</b>  (src)
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathRepertoireSrc = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users/src
	 * </code></li>
	 * </ul>
	 */
	private static Path pathRepertoireSrc;
	
	
	/**
	 * fileRepertoireSrc : File :<br/>
	 * <ul>
	 * <li><b>File modélisant le répertoire des sources</b> (src)
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathRepertoireSrc = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users/src
	 * </code></li>
	 * </ul>
	 */
	private static File fileRepertoireSrc;
	

	
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


	
	
	public static void alimenterAttributs() throws Exception {
		alimenterAttributs(null, null, null);
	}
	

	
	public static void alimenterAttributs(final String pNomProjet) throws Exception {
		alimenterAttributs(null, pNomProjet, null);
	}
	
	
	
	/**
	 * method alimenterAttributs() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pPathWorkspaceString
	 * @param pNomProjet
	 * @param pNomRepertoireSrc
	 * @throws Exception : void :  .<br/>
	 */
	public static void alimenterAttributs(final String pPathWorkspaceString, final String pNomProjet, final String pNomRepertoireSrc) 
				throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			alimenterPathWorkspaceString(pPathWorkspaceString);
			alimenterNomProjet(pNomProjet);
			alimenterNomRepertoireSrc(pNomRepertoireSrc);
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterAttributs(...).___________________________________
	
	
	
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
	 * method alimenterNomProjet(
	 * String pString) :<br/>
	 * <ul>
	 * <li><b>alimente nomProjet</b>.</li>
	 * <li><b>alimente pathProjetString</b>.</li>
	 * <li><b>alimente pathProjet</b>.</li>
	 * <li><b>alimente fileProjet</b>.</li>
	 * <ol>
	 * <li>alimente nomProjet avec pString si pString 
	 * n'est pas blank et si pathProjet pointe 
	 * sur un dossier existant.</li>
	 * <li>alimente nomProjet avec une valeur par défaut 
	 * si pString n'est pas blank mais si pathProjet 
	 * ne pointe sur rien ou sur un fichier simple.</li>
	 * <li>alimente nomProjet avec une valeur par défaut 
	 * si pString est blank.</li>
	 * </ol>
	 * <li>la valeur par défaut est :</li>
	 * <ol>
	 * <li>le nom du <b>présent</b> projet.</li>
	 * </ol>
	 * </ul>
	 *
	 * @param pString : String : 
	 * nom du projet dans lequel générer le code.<br/>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterNomProjet(
			final String pString) throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (nomProjet == null) {
				
				if (parametreExistant(pString)) {
					
					if (existeProjet(pString)) {
						alimenterAttributsProjet(pString);						
					} else {
						alimenterProjetParDefaut();
					}
										
				} else {
					alimenterProjetParDefaut();
				}
								
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterNomProjet(...).___________________________________
	

	
	/**
	 * method alimenterAttributsProjet(
	 * String pString) :<br/>
	 * <ul>
	 * <li>passe pString à nomProjet.</li>
	 * <li>passe fabriquerPath(pathWorkspace, pString) à pathProjet.</li>
	 * <li>passe pathProjet.toString() à pathProjetString.</li>
	 * <li>passe pathProjet.toFile() à fileProjet.</li>
	 * </ul>
	 * ne fait rien si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 */
	private static void alimenterAttributsProjet(
			final String pString) {
		
		/* ne fait rien si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return;
		}
		
		nomProjet = pString;
		pathProjet 
			= fabriquerPath(pathWorkspace, pString);
		pathProjetString = pathProjet.toString();
		fileProjet = pathProjet.toFile();
		
	} // Fin de alimenterAttributsProjet(...)._____________________________
	
	
	
	/**
	 * method alimenterProjetParDefaut() :<br/>
	 * <ul>
	 * <li>alimente nomProjet avec le nom du présent projet Eclipse.</li>
	 * <li>alimente pathProjetString avec le présent projet Eclipse.</li>
	 * <li>alimente pathProjet avec le présent projet Eclipse.</li>
	 * <li>alimente fileProjet avec le présent projet Eclipse.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	private static void alimenterProjetParDefaut() throws IOException {
		
		nomProjet = ManagerPaths.getNomPresentProjet();
		pathProjet = fabriquerPath(pathWorkspace, nomProjet);
		pathProjetString = pathProjet.toString();
		fileProjet = pathProjet.toFile();
		
	} // Fin de alimenterProjetParDefaut().________________________________
	

	
	/**
	 * method alimenterNomRepertoireSrc(
	 * String pString) :<br/>
	 * <ul>
	 * <li><b>alimente nomRepertoireSrc</b>.</li>
	 * <li><b>alimente pathRepertoireSrcString</b>.</li>
	 * <li><b>alimente pathRepertoireSrc</b>.</li>
	 * <li><b>alimente fileRepertoireSrc</b>.</li>
	 * <ol>
	 * <li>alimente nomRepertoireSrc avec pString si pString 
	 * n'est pas blank et pointe sur un répertoire existant.</li>
	 * <li><b>crée d'abord le répertoire</b>, 
	 * puis alimente nomRepertoireSrc avec pString si pString 
	 * n'est pas blank et pointe sur un répertoire inexistant.</li>
	 * <li>alimente nomRepertoireSrc avec une valeur par défaut 
	 * si pString est blank.</li>
	 * </ol>
	 * <li>la valeur par défaut est :</li>
	 * <ol>
	 * <li>le nom du repertoire des sources dans 
	 * <b>configuration_projet.properties</b> si il existe.</li>
	 * <li><b>src</b> sinon.</li>
	 * </ol>
	 * </ul>
	 *
	 * @param pString : String : 
	 * nom du répertoire des sources du projet dans 
	 * lequel générer le code.<br/>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterNomRepertoireSrc(
			final String pString) throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (nomRepertoireSrc == null) {
				
				if (parametreExistant(pString)) {
					
					final Path pathSrc = fabriquerPath(pathProjet, pString);
					final String pathSrcString = pathSrc.toString();
					
					if (existeDossier(pathSrcString)) {
						alimenterAttributsRepertoireSrc(pString);						
					} else {
						
						/* création du répertoire. */
						creerRepertoire(pathSrc);
						
						alimenterAttributsRepertoireSrc(pString);
					}
										
				} else {
					alimenterRepertoireSrcParDefaut();
				}
								
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterNomRepertoireSrc(...).___________________________________
	

	
	/**
	 * method alimenterAttributsRepertoireSrc(
	 * String pString) :<br/>
	 * <ul>
	 * <li>passe pString à nomRepertoireSrc.</li>
	 * <li>passe fabriquerPath(pathProjet, pString) 
	 * à pathRepertoireSrc.</li>
	 * <li>passe pathRepertoireSrc.toString() à 
	 * pathRepertoireSrcString.</li>
	 * <li>passe pathRepertoireSrc.toFile() à fileRepertoireSrc.</li>
	 * </ul>
	 * ne fait rien si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 */
	private static void alimenterAttributsRepertoireSrc(
			final String pString) {
		
		/* ne fait rien si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return;
		}
		
		nomRepertoireSrc = pString;
		pathRepertoireSrc = fabriquerPath(pathProjet, pString);
		pathRepertoireSrcString = pathRepertoireSrc.toString();
		fileRepertoireSrc = pathRepertoireSrc.toFile();
		
	} // Fin de alimenterAttributsRepertoireSrc(...).______________________
	
	
	
	/**
	 * method alimenterRepertoireSrcParDefaut() :<br/>
	 * <ul>
	 * <li>Affecte le nom du répertoire des sources du 
	 * config_projet.properties si il existe, "src" sinon.</li>
	 * <li>passe le nom par défaut à nomRepertoireSrc.</li>
	 * <li>passe fabriquerPath(pathProjet, nomRepertoireSrc) 
	 * à pathRepertoireSrc.</li>
	 * <li>passe pathRepertoireSrc.toString() à 
	 * pathRepertoireSrcString.</li>
	 * <li>passe pathRepertoireSrc.toFile() à fileRepertoireSrc.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	private static void alimenterRepertoireSrcParDefaut() 
			throws IOException {
		
		String repertoireSrcConfig;
		String repSrc = null;
		
		try {
			
			repertoireSrcConfig 
				= BundleConfigurationProjetManager.getNomRepertoireSrc();
			
			if (StringUtils.isBlank(repertoireSrcConfig)) {
				repSrc = "src";
			} else {
				repSrc = repertoireSrcConfig;
			}
			
		} catch (Exception e) {
			repSrc = "src";
		}
				
		nomRepertoireSrc = repSrc;
		pathRepertoireSrc = fabriquerPath(pathProjet, nomRepertoireSrc);
		pathRepertoireSrcString = pathRepertoireSrc.toString();
		fileRepertoireSrc = pathRepertoireSrc.toFile();
		
	} // Fin de alimenterRepertoireSrcParDefaut().________________________________
	

	
	/**
	 * method creerRepertoire(
	 * Path pPath) :<br/>
	 * <ul>
	 * <li><b>Crée le répertoire situé à pPath</b></li>
	 * <li>Ne crée le répertoire que si il n'existe pas déjà.</li>
	 * <li>crée toute l'arborescence nécessaire dans pPath si nécessaire.</li>
	 * </ul>
	 * ne fait rien si pPath == null.<br/>
	 * <br/>
	 *
	 * @param pPath : Path.<br/>
	 */
	private static void creerRepertoire(
			final Path pPath) {
		
		synchronized (GestionnaireProjet.class) {
			
			/* ne fait rien si pPath == null. */
			if (pPath == null) {
				return;
			}
			
			try {
				
				final File file = pPath.toFile();
				
				if (!file.exists()) {
					Files.createDirectories(pPath);
				}
								
			} catch (IOException e) {
				if (LOG.isFatalEnabled()) {
					LOG.fatal("Impossible de créer le répertoire : " 
							+ pPath.toString(), e);
				}
			}
			
			
		} // Fin de synchronized._______________________
		
	} // Fin de creerRepertoire(...).______________________________________

	
	
	/**
	 * method fabriquerPath(
	 * Path pPath
	 * , String pString) :<br/>
	 * <ul>
	 * <li>fabrique un nouveau Path en augmentant pPath de pString.</li>
	 * <li>Par exemple : <br/>
	 * <code>fabriquerPath(pathWorkspace, "generation_code")</code> 
	 * retourne 
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/generation_code</code> 
	 * alors que pathWorkspace vaut 
	 * D:/Donnees/eclipse/eclipseworkspace_neon
	 * </li>
	 * <li>utilise pPath.resolve(pString).</li>
	 * </ul>
	 * retourne null si pPath == null.<br/>
	 * retourne null si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pPath : java.nio.file.Path.<br/>
	 * @param pString : chemin à rajouter au path.<br/>
	 * 
	 * @return : Path : Path augmenté de pString.<br/>
	 */
	private static Path fabriquerPath(
			final Path pPath
				, final String pString) {
		
		/* retourne null si pPath == null. */
		if (pPath == null) {
			return null;
		}
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		final Path resultat = pPath.resolve(pString);
		
		return resultat;
		
	} // Fin de fabriquerPath(...).________________________________________
	
	
	
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
	 * method existeProjet(
	 * String pString) :<br/>
	 * <ul>
	 * <li>retourne un boolean stipulant si 
	 * un projet existe au chemin pathWorkspace/pString.</li>
	 * <li>retourne true si un dossier existe au chemin 
	 * pathWorkspace/pString.</li>
	 * </ul>
	 * retourne false si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : boolean : 
	 * true si un dossier existe au chemin pathWorkspace/pString.<br/>
	 */
	private static boolean existeProjet(
			final String pString) {
		
		/* retourne false si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return false;
		}
		
		final Path path = fabriquerPath(pathWorkspace, pString);
		final File file = path.toFile();
		
		/* retourne true si un dossier existe au chemin pString. */
		if (file.exists() && file.isDirectory()) {
			return true;
		}
		
		return false;
		
	} // Fin de existeProjet(...)._________________________________________
	
	
	
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
	 * method getNomProjet() :<br/>
	 * <ul>
	 * <li>Getter du <b>nom du projet Eclipse</b> 
	 * dont on va générer le code.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>projet_users
	 * </code></li>
	 * </ul>
	 *
	 * @return nomProjet : String.<br/>
	 */
	public static String getNomProjet() {
		return nomProjet;
	} // Fin de getNomProjet().____________________________________________


	
	/**
	 * method getPathProjetString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du projet Eclipse</b> 
	 * dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathProjet = pathWorkspace + /nomProjet</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users
	 * </code></li>
	 * </ul>
	 *
	 * @return pathProjetString : String.<br/>
	 */
	public static String getPathProjetString() {
		return pathProjetString;
	} // Fin de getPathProjetString()._______________________________________


	
	/**
	 * method getPathProjet() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du projet Eclipse</b> 
	 * dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathProjet = pathWorkspace + /nomProjet</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users
	 * </code></li>
	 * </ul>
	 *
	 * @return pathProjet : Path.<br/>
	 */
	public static Path getPathProjet() {
		return pathProjet;
	} // Fin de getPathProjet().___________________________________________


	
	/**
	 * method getFileProjet() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le projet Eclipse</b> 
	 * dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathProjet = pathWorkspace + /nomProjet</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users
	 * </code></li>
	 * </ul>
	 *
	 * @return fileProjet : File.<br/>
	 */
	public static File getFileProjet() {
		return fileProjet;
	} // Fin de getFileProjet().___________________________________________


	
	/**
	 * method getNomRepertoireSrc() :<br/>
	 * <ul>
	 * <li>Getter du <b>nom du répertoire des sources</b> (src) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>src
	 * </code></li>
	 * </ul>
	 *
	 * @return nomRepertoireSrc : String.<br/>
	 */
	public static String getNomRepertoireSrc() {
		return nomRepertoireSrc;
	} // Fin de getNomRepertoireSrc()._____________________________________


	
	/**
	 * method getPathRepertoireSrcString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du répertoire des sources</b>  (src)
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathRepertoireSrc = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users/src
	 * </code></li>
	 * </ul>
	 *
	 * @return pathRepertoireSrcString : String.<br/>
	 */
	public static String getPathRepertoireSrcString() {
		return pathRepertoireSrcString;
	} // Fin de getPathRepertoireSrcString().______________________________


	
	/**
	 * method getPathRepertoireSrc() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du répertoire des sources</b>  (src)
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathRepertoireSrc = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users/src
	 * </code></li>
	 * </ul>
	 *
	 * @return pathRepertoireSrc : Path.<br/>
	 */
	public static Path getPathRepertoireSrc() {
		return pathRepertoireSrc;
	} // Fin de getPathRepertoireSrc().____________________________________


	
	/**
	 * method getFileRepertoireSrc() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le répertoire des sources</b> 
	 * (src) dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathRepertoireSrc = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users/src
	 * </code></li>
	 * </ul>
	 *
	 * @return fileRepertoireSrc : File.<br/>
	 */
	public static File getFileRepertoireSrc() {
		return fileRepertoireSrc;
	} // Fin de getFileRepertoireSrc().____________________________________


	
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
		
		nomProjet = null;
		pathProjetString = null;
		pathProjet = null;
		fileProjet = null;
		
	} // Fin de reinitialiserAttributs().__________________________________

	

} // FIN DE LA CLASSE GestionnaireProjet.------------------------------------
