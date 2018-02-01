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
 * <li>Fournit pour chaque composant du Path d'un répertoire 
 * dans un projet MAVEN :
 * <ol>
 * <li>le nom du composant (par exemple pathRelMainJava 
 * susceptible de contenir main/java)</li>
 * <li>le path absolu du répertoire sous forme de String 
 * (par exemple pathMainJavaString)</li>
 * <li>le path absolu du répertoire sous forme de Path 
 * (par exemple pathMainJava)</li>
 * <li>le File modélisant le répertoire 
 * (par exemple fileMainJava)</li>
 * </ol>
 * </li>
 * <li>
 * classe chargée de fournir des SINGLETONS pour :
 * <ol>
 * <li>l'emplacement du <b>Workspace Eclipse</b> 
 * dans lequel générer le code <b>pathWorkspace</b>.</li>
 * <li>l'emplacement du <b>projet Eclipse</b> 
 * dans lequel générer le code <b>pathProjet</b>.</li>
 * <li>l'emplacement du <b>répertoire des sources</b> (src)
 * dans lequel générer le code <b>pathRepertoiresrc</b>.</li>
 * <li>l'emplacement de la <b>racine des sources</b> 
 * (src/main/java) dans lequel générer les .java 
 * du main <b>pathMainJava</b>.</li>
 * <li>l'emplacement de la <b>racine des ressources</b> 
 * (src/main/resources) dans lequel stocker les ressources 
 * du main <b>pathMainResources</b>.</li>
 * <li>l'emplacement de la <b>racine des sources de test</b> 
 * (src/test/java) dans lequel générer les .java 
 * de test <b>pathTestJava</b>.</li>
 * <li>l'emplacement de la <b>racine des ressources des tests</b> 
 * (src/test/resources) dans lequel stocker les ressources 
 * des tests <b>pathTestResources</b>.</li>
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
	 * pathRelMainJava : String :<br/>
	 * <ul>
	 * <li>path <i>relatif</i> des sources java par rapport à src.</li>
	 * <li>sous forme de <b>String</b>.</li>
	 * <li>Par exemple :<br/>
	 * <code>main/java</code></li>
	 * </ul>
	 */
	private static String pathRelMainJava;

	
	/**
	 * pathMainJavaString : String :<br/>
	 * <ul>
	 * <li><b>path du répertoire de la RACINE des sources .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java
	 * </code></li>
	 * </ul>
	 */
	private static String pathMainJavaString;

	
	/**
	 * pathMainJava : Path :<br/>
	 * <ul>
	 * <li><b>path du répertoire de la RACINE des sources .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java
	 * </code></li>
	 * </ul>
	 */
	private static Path pathMainJava;

		
	/**
	 * fileMainJava : File :<br/>
	 * <ul>
	 * <li><b>File modélisant le répertoire 
	 * de la RACINE des sources .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java
	 * </code></li>
	 * </ul>
	 */
	private static File fileMainJava;
	
	
	
	/**
	 * pathRelMainResources : String :<br/>
	 * <ul>
	 * <li>path <i>relatif</i> des ressources main par rapport à src.</li>
	 * <li>sous forme de <b>String</b>.</li>
	 * <li>Par exemple :<br/>
	 * <code>main/resources</code></li>
	 * </ul>
	 */
	private static String pathRelMainResources;

	
	/**
	 * pathMainResourcesString : String :<br/>
	 * <ul>
	 * <li><b>path du répertoire de la RACINE des ressources main</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathMainResources = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainResources.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/resources
	 * </code></li>
	 * </ul>
	 */
	private static String pathMainResourcesString;

	
	/**
	 * pathMainResources : Path :<br/>
	 * <ul>
	 * <li><b>path du répertoire de la RACINE des ressources main</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathMainResources = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainResources.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/resources
	 * </code></li>
	 * </ul>
	 */
	private static Path pathMainResources;

		
	/**
	 * fileMainResources : File :<br/>
	 * <ul>
	 * <li><b>File modélisant le répertoire 
	 * de la RACINE des ressources main</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathMainResources = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainResources.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/resources
	 * </code></li>
	 * </ul>
	 */
	private static File fileMainResources;
		
	
	/**
	 * pathRelTestJava : String :<br/>
	 * <ul>
	 * <li>path <i>relatif</i> des sources des tests 
	 * java par rapport à src.</li>
	 * <li>sous forme de <b>String</b>.</li>
	 * <li>Par exemple :<br/>
	 * <code>test/java</code></li>
	 * </ul>
	 */
	private static String pathRelTestJava;

	
	/**
	 * pathTestJavaString : String :<br/>
	 * <ul>
	 * <li><b>path du répertoire de la RACINE des sources 
	 * des test .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java
	 * </code></li>
	 * </ul>
	 */
	private static String pathTestJavaString;

	
	/**
	 * pathTestJava : Path :<br/>
	 * <ul>
	 * <li><b>path du répertoire de la RACINE des sources 
	 * des tests .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java
	 * </code></li>
	 * </ul>
	 */
	private static Path pathTestJava;

		
	/**
	 * fileTestJava : File :<br/>
	 * <ul>
	 * <li><b>File modélisant le répertoire 
	 * de la RACINE des sources des tests .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java
	 * </code></li>
	 * </ul>
	 */
	private static File fileTestJava;
	
		
	/**
	 * pathRelTestResources : String :<br/>
	 * <ul>
	 * <li>path <i>relatif</i> des ressources de test 
	 * par rapport à src.</li>
	 * <li>sous forme de <b>String</b>.</li>
	 * <li>Par exemple :<br/>
	 * <code>test/resources</code></li>
	 * </ul>
	 */
	private static String pathRelTestResources;

	
	/**
	 * pathTestResourcesString : String :<br/>
	 * <ul>
	 * <li><b>path du répertoire de la RACINE 
	 * des ressources des tests</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathTestResources = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestResources.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/resources
	 * </code></li>
	 * </ul>
	 */
	private static String pathTestResourcesString;

	
	/**
	 * pathTestResources : Path :<br/>
	 * <ul>
	 * <li><b>path du répertoire de la RACINE 
	 * des ressources des tests</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathTestResources = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestResources.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/resources
	 * </code></li>
	 * </ul>
	 */
	private static Path pathTestResources;

		
	/**
	 * fileTestResources : File :<br/>
	 * <ul>
	 * <li><b>File modélisant le répertoire 
	 * de la RACINE des ressources des tests</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathTestResources = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestResources.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/resources
	 * </code></li>
	 * </ul>
	 */
	private static File fileTestResources;
		

	/**
	 * groupId : String :<br/>
	 * <ul>
	 * <li><b>groupId Maven</b> du projet.<br/>
	 * <li>Par Exemple : <br/>
	 * <code>levy.daniel.application</code></li>
	 * </ul>
	 */
	private static String groupId;
	
	
	/**
	 * pathRelGroupIdString : String :<br/>
	 * <ul>
	 * <li>path relatif du groupId Maven du projet par rapport 
	 * au path absolu des sources java.</li>
	 * <li>Par Exemple : <br/>
	 * <code>levy/daniel/application</code></li>
	 * </li>
	 */
	private static String pathRelGroupIdString;


	private static String pathGroupidMainJavaString;
	
	
	private static String pathGroupidTestJavaString;
	
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
		alimenterAttributs(null, null, null, null, null, null, null, null);
	}
	

	
	public static void alimenterAttributs(final String pNomProjet) throws Exception {
		alimenterAttributs(null, pNomProjet, null, null, null, null, null, null);
	}
	
	
	
	
	
	
	
	/**
	 * method alimenterAttributs() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pPathWorkspaceString
	 * @param pNomProjet
	 * @param pNomRepertoireSrc
	 * @param pPathRelMainJava
	 * @param pPathRelMainResources
	 * @param pPathRelTestJava
	 * @param pPathRelTestResources
	 * @param pGroupId
	 * @throws Exception : void :  .<br/>
	 */
	public static void alimenterAttributs(
			final String pPathWorkspaceString
				, final String pNomProjet
					, final String pNomRepertoireSrc
						, final String pPathRelMainJava
							, final String pPathRelMainResources
								, final String pPathRelTestJava
									, final String pPathRelTestResources
										, final String pGroupId) 
				throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			alimenterPathWorkspaceString(pPathWorkspaceString);
			alimenterNomProjet(pNomProjet);
			alimenterNomRepertoireSrc(pNomRepertoireSrc);
			alimenterPathRelMainJava(pPathRelMainJava);
			alimenterPathRelMainResources(pPathRelMainResources);
			alimenterPathRelTestJava(pPathRelTestJava);
			alimenterPathRelTestResources(pPathRelTestResources);
			
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
	 * method alimenterPathRelMainJava() :<br/>
	 * <ul>
	 * <li><b>alimente pathRelMainJava</b>.</li>
	 * <li><b>alimente pathMainJavaString</b>.</li>
	 * <li><b>alimente pathMainJava</b>.</li>
	 * <li><b>alimente fileMainJava</b>.</li>
	 * <ol>
	 * <li>alimente pathRelMainJava avec pString si pString 
	 * n'est pas blank et pointe sur un répertoire existant.</li>
	 * <li><b>crée d'abord le répertoire</b>, 
	 * puis alimente pathRelMainJava avec pString si pString 
	 * n'est pas blank et pointe sur un répertoire inexistant.</li>
	 * <li>alimente pathRelMainJava avec une valeur par défaut 
	 * si pString est blank.</li>
	 * </ol>
	 * <li>la valeur par défaut est :</li>
	 * <ol>
	 * <li>le nom du chemin relatif dans 
	 * <b>configuration_projet.properties</b> si il existe.</li>
	 * <li><b>main/java</b> sinon.</li>
	 * </ol>
	 * </ul>
	 *
	 * @param pString : String : 
	 * chemin relatif (archétype MAVEN) dans 
	 * lequel générer le code.<br/>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterPathRelMainJava(
			final String pString) throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (pathRelMainJava == null) {
				
				if (parametreExistant(pString)) {
					
					final Path path = fabriquerPath(pathRepertoireSrc, pString);
					final String pathString = path.toString();
					
					if (existeDossier(pathString)) {
						alimenterAttributsMainJava(pString);						
					} else {
						
						/* création du répertoire. */
						creerRepertoire(path);
						
						alimenterAttributsMainJava(pString);
					}
										
				} else {
					alimenterMainJavaParDefaut();
				}
								
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterPathRelMainJava(...)._____________________________
	

	
	/**
	 * method alimenterAttributsMainJava(
	 * String pString) :<br/>
	 * <ul>
	 * <li>passe pString à pathRelMainJava.</li>
	 * <li>passe fabriquerPath(pathRepertoireSrc, pString) 
	 * à pathMainJava.</li>
	 * <li>passe pathMainJava.toString() à 
	 * pathMainJavaString.</li>
	 * <li>passe pathMainJava.toFile() à fileMainJava.</li>
	 * </ul>
	 * ne fait rien si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 */
	private static void alimenterAttributsMainJava(
			final String pString) {
		
		/* ne fait rien si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return;
		}
		
		pathRelMainJava = pString;
		pathMainJava = fabriquerPath(pathRepertoireSrc, pString);
		pathMainJavaString = pathMainJava.toString();
		fileMainJava = pathMainJava.toFile();
		
	} // Fin de alimenterAttributsMainJava(...).___________________________
	

	
	/**
	 * method alimenterMainJavaParDefaut() :<br/>
	 * <ul>
	 * <li>Calcule la valeur par défaut de pathRelMainJava :
	 * <ol>
	 * <li>dans config_projet.properties si elle existe</li>
	 * <li>main/java sinon.</li>
	 * </ol>  
	 * </li>
	 * <li>passe la valeur par défaut à pathRelMainJava.</li>
	 * <li>passe fabriquerPath(pathRepertoireSrc, pathRelMainJava) 
	 * à pathMainJava.</li>
	 * <li>passe pathMainJava.toString() à 
	 * pathMainJavaString.</li>
	 * <li>passe pathMainJava.toFile() à fileMainJava.</li>
	 * <li>crée le répertoire par défaut si il n'existe pas.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	private static void alimenterMainJavaParDefaut() 
			throws IOException {
		
		String valeurDefautConfig;
		String valeurDefaut = null;
		
		try {
			
			/* récupération de la valeur par défaut dans 
			 * configuration_projet.properties via 
			 * BundleConfigurationProjetManager. */
			valeurDefautConfig 
				= BundleConfigurationProjetManager.getPathRelMainJava();
			
			if (StringUtils.isBlank(valeurDefautConfig)) {
				valeurDefaut = "main/java";
			} else {
				valeurDefaut = valeurDefautConfig;
			}
			
		} catch (Exception e) {
			valeurDefaut = "main/java";
		}
				
		pathRelMainJava = valeurDefaut;
		pathMainJava = fabriquerPath(pathRepertoireSrc, pathRelMainJava);
		pathMainJavaString = pathMainJava.toString();
		fileMainJava = pathMainJava.toFile();
		
		/* crée le répertoire par défaut si il n'existe pas. */
		if (!fileMainJava.exists()) {
			Files.createDirectories(pathMainJava);
		}
		
	} // Fin de alimenterMainJavaParDefaut().______________________________
	

		
	/**
	 * method alimenterPathRelMainResources() :<br/>
	 * <ul>
	 * <li><b>alimente pathRelMainResources</b>.</li>
	 * <li><b>alimente pathMainResourcesString</b>.</li>
	 * <li><b>alimente pathMainResources</b>.</li>
	 * <li><b>alimente fileMainResources</b>.</li>
	 * <ol>
	 * <li>alimente pathRelMainResources avec pString si pString 
	 * n'est pas blank et pointe sur un répertoire existant.</li>
	 * <li><b>crée d'abord le répertoire</b>, 
	 * puis alimente pathRelMainResources avec pString si pString 
	 * n'est pas blank et pointe sur un répertoire inexistant.</li>
	 * <li>alimente pathRelMainResources avec une valeur par défaut 
	 * si pString est blank.</li>
	 * </ol>
	 * <li>la valeur par défaut est :</li>
	 * <ol>
	 * <li>le nom du chemin relatif dans 
	 * <b>configuration_projet.properties</b> si il existe.</li>
	 * <li><b>main/resources</b> sinon.</li>
	 * </ol>
	 * </ul>
	 *
	 * @param pString : String : 
	 * chemin relatif (archétype MAVEN) dans 
	 * lequel générer le code.<br/>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterPathRelMainResources(
			final String pString) throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (pathRelMainResources == null) {
				
				if (parametreExistant(pString)) {
					
					final Path path = fabriquerPath(pathRepertoireSrc, pString);
					final String pathString = path.toString();
					
					if (existeDossier(pathString)) {
						alimenterAttributsMainResources(pString);						
					} else {
						
						/* création du répertoire. */
						creerRepertoire(path);
						
						alimenterAttributsMainResources(pString);
					}
										
				} else {
					alimenterMainResourcesParDefaut();
				}
								
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterPathRelMainResources(...).________________________
	
	
	
	/**
	 * method alimenterAttributsMainResources(
	 * String pString) :<br/>
	 * <ul>
	 * <li>passe pString à pathRelMainResources.</li>
	 * <li>passe fabriquerPath(pathRepertoireSrc, pString) 
	 * à pathMainResources.</li>
	 * <li>passe pathMainResources.toString() à 
	 * pathMainResourcesString.</li>
	 * <li>passe pathMainResources.toFile() à fileMainResources.</li>
	 * </ul>
	 * ne fait rien si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 */
	private static void alimenterAttributsMainResources(
			final String pString) {
		
		/* ne fait rien si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return;
		}
		
		pathRelMainResources = pString;
		pathMainResources = fabriquerPath(pathRepertoireSrc, pString);
		pathMainResourcesString = pathMainResources.toString();
		fileMainResources = pathMainResources.toFile();
		
	} // Fin de alimenterAttributsMainResources(...).______________________
	
	
	
	/**
	 * method alimenterMainResourcesParDefaut() :<br/>
	 * <ul>
	 * <li>Calcule la valeur par défaut de pathRelMainResources :
	 * <ol>
	 * <li>dans config_projet.properties si elle existe</li>
	 * <li>main/resources sinon.</li>
	 * </ol>  
	 * </li>
	 * <li>passe la valeur par défaut à pathRelMainResources.</li>
	 * <li>passe fabriquerPath(pathRepertoireSrc, pathRelMainResources) 
	 * à pathMainResources.</li>
	 * <li>passe pathMainResources.toString() à 
	 * pathMainResourcesString.</li>
	 * <li>passe pathMainResources.toFile() à fileMainResources.</li>
	 * <li>crée le répertoire par défaut si il n'existe pas.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	private static void alimenterMainResourcesParDefaut() 
			throws IOException {
		
		String valeurDefautConfig;
		String valeurDefaut = null;
		
		try {
			
			/* récupération de la valeur par défaut dans 
			 * configuration_projet.properties via 
			 * BundleConfigurationProjetManager. */
			valeurDefautConfig 
				= BundleConfigurationProjetManager
					.getPathRelMainResources();
			
			if (StringUtils.isBlank(valeurDefautConfig)) {
				valeurDefaut = "main/resources";
			} else {
				valeurDefaut = valeurDefautConfig;
			}
			
		} catch (Exception e) {
			valeurDefaut = "main/resources";
		}
				
		pathRelMainResources = valeurDefaut;
		pathMainResources 
			= fabriquerPath(pathRepertoireSrc, pathRelMainResources);
		pathMainResourcesString = pathMainResources.toString();
		fileMainResources = pathMainResources.toFile();
		
		/* crée le répertoire par défaut si il n'existe pas. */
		if (!fileMainResources.exists()) {
			Files.createDirectories(pathMainResources);
		}
		
	} // Fin de alimenterMainResourcesParDefaut().______________________________
	

		
	/**
	 * method alimenterPathRelTestJava() :<br/>
	 * <ul>
	 * <li><b>alimente pathRelTestJava</b>.</li>
	 * <li><b>alimente pathTestJavaString</b>.</li>
	 * <li><b>alimente pathTestJava</b>.</li>
	 * <li><b>alimente fileTestJava</b>.</li>
	 * <ol>
	 * <li>alimente pathRelTestJava avec pString si pString 
	 * n'est pas blank et pointe sur un répertoire existant.</li>
	 * <li><b>crée d'abord le répertoire</b>, 
	 * puis alimente pathRelTestJava avec pString si pString 
	 * n'est pas blank et pointe sur un répertoire inexistant.</li>
	 * <li>alimente pathRelTestJava avec une valeur par défaut 
	 * si pString est blank.</li>
	 * </ol>
	 * <li>la valeur par défaut est :</li>
	 * <ol>
	 * <li>le nom du chemin relatif dans 
	 * <b>configuration_projet.properties</b> si il existe.</li>
	 * <li><b>test/java</b> sinon.</li>
	 * </ol>
	 * </ul>
	 *
	 * @param pString : String : 
	 * chemin relatif (archétype MAVEN) dans 
	 * lequel générer le code.<br/>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterPathRelTestJava(
			final String pString) throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (pathRelTestJava == null) {
				
				if (parametreExistant(pString)) {
					
					final Path path 
						= fabriquerPath(pathRepertoireSrc, pString);
					final String pathString = path.toString();
					
					if (existeDossier(pathString)) {
						alimenterAttributsTestJava(pString);						
					} else {
						
						/* création du répertoire. */
						creerRepertoire(path);
						
						alimenterAttributsTestJava(pString);
					}
										
				} else {
					alimenterTestJavaParDefaut();
				}
								
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterPathRelTestJava(...)._____________________________
	
	
	
	/**
	 * method alimenterAttributsTestJava(
	 * String pString) :<br/>
	 * <ul>
	 * <li>passe pString à pathRelTestJava.</li>
	 * <li>passe fabriquerPath(pathRepertoireSrc, pString) 
	 * à pathTestJava.</li>
	 * <li>passe pathTestJava.toString() à 
	 * pathTestJavaString.</li>
	 * <li>passe pathTestJava.toFile() à fileTestJava.</li>
	 * </ul>
	 * ne fait rien si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 */
	private static void alimenterAttributsTestJava(
			final String pString) {
		
		/* ne fait rien si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return;
		}
		
		pathRelTestJava = pString;
		pathTestJava = fabriquerPath(pathRepertoireSrc, pString);
		pathTestJavaString = pathTestJava.toString();
		fileTestJava = pathTestJava.toFile();
		
	} // Fin de alimenterAttributsTestJava(...).___________________________
	
	
	
	/**
	 * method alimenterTestJavaParDefaut() :<br/>
	 * <ul>
	 * <li>Calcule la valeur par défaut de pathRelTestJava :
	 * <ol>
	 * <li>dans config_projet.properties si elle existe</li>
	 * <li>test/java sinon.</li>
	 * </ol>  
	 * </li>
	 * <li>passe la valeur par défaut à pathRelTestJava.</li>
	 * <li>passe fabriquerPath(pathRepertoireSrc, pathRelTestJava) 
	 * à pathTestJava.</li>
	 * <li>passe pathTestJava.toString() à 
	 * pathTestJavaString.</li>
	 * <li>passe pathTestJava.toFile() à fileTestJava.</li>
	 * <li>crée le répertoire par défaut si il n'existe pas.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	private static void alimenterTestJavaParDefaut() 
			throws IOException {
		
		String valeurDefautConfig;
		String valeurDefaut = null;
		
		try {
			
			/* récupération de la valeur par défaut dans 
			 * configuration_projet.properties via 
			 * BundleConfigurationProjetManager. */
			valeurDefautConfig 
				= BundleConfigurationProjetManager.getPathRelTestJava();
			
			if (StringUtils.isBlank(valeurDefautConfig)) {
				valeurDefaut = "test/java";
			} else {
				valeurDefaut = valeurDefautConfig;
			}
			
		} catch (Exception e) {
			valeurDefaut = "test/java";
		}
				
		pathRelTestJava = valeurDefaut;
		pathTestJava = fabriquerPath(pathRepertoireSrc, pathRelTestJava);
		pathTestJavaString = pathTestJava.toString();
		fileTestJava = pathTestJava.toFile();
		
		/* crée le répertoire par défaut si il n'existe pas. */
		if (!fileTestJava.exists()) {
			Files.createDirectories(pathTestJava);
		}
		
	} // Fin de alimenterTestJavaParDefaut().______________________________
	
	
		
	/**
	 * method alimenterPathRelTestResources() :<br/>
	 * <ul>
	 * <li><b>alimente pathRelTestResources</b>.</li>
	 * <li><b>alimente pathTestResourcesString</b>.</li>
	 * <li><b>alimente pathTestResources</b>.</li>
	 * <li><b>alimente fileTestResources</b>.</li>
	 * <ol>
	 * <li>alimente pathRelTestResources avec pString si pString 
	 * n'est pas blank et pointe sur un répertoire existant.</li>
	 * <li><b>crée d'abord le répertoire</b>, 
	 * puis alimente pathRelTestResources avec pString si pString 
	 * n'est pas blank et pointe sur un répertoire inexistant.</li>
	 * <li>alimente pathRelTestResources avec une valeur par défaut 
	 * si pString est blank.</li>
	 * </ol>
	 * <li>la valeur par défaut est :</li>
	 * <ol>
	 * <li>le nom du chemin relatif dans 
	 * <b>configuration_projet.properties</b> si il existe.</li>
	 * <li><b>test/resources</b> sinon.</li>
	 * </ol>
	 * </ul>
	 *
	 * @param pString : String : 
	 * chemin relatif (archétype MAVEN) dans 
	 * lequel générer le code.<br/>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterPathRelTestResources(
			final String pString) throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (pathRelTestResources == null) {
				
				if (parametreExistant(pString)) {
					
					final Path path 
						= fabriquerPath(pathRepertoireSrc, pString);
					final String pathString = path.toString();
					
					if (existeDossier(pathString)) {
						alimenterAttributsTestResources(pString);						
					} else {
						
						/* création du répertoire. */
						creerRepertoire(path);
						
						alimenterAttributsTestResources(pString);
					}
										
				} else {
					alimenterTestResourcesParDefaut();
				}
								
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterPathRelTestResources(...).________________________
	
	
	
	/**
	 * method alimenterAttributsTestResources(
	 * String pString) :<br/>
	 * <ul>
	 * <li>passe pString à pathRelTestResources.</li>
	 * <li>passe fabriquerPath(pathRepertoireSrc, pString) 
	 * à pathTestResources.</li>
	 * <li>passe pathTestResources.toString() à 
	 * pathTestResourcesString.</li>
	 * <li>passe pathTestResources.toFile() à fileTestResources.</li>
	 * </ul>
	 * ne fait rien si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 */
	private static void alimenterAttributsTestResources(
			final String pString) {
		
		/* ne fait rien si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return;
		}
		
		pathRelTestResources = pString;
		pathTestResources = fabriquerPath(pathRepertoireSrc, pString);
		pathTestResourcesString = pathTestResources.toString();
		fileTestResources = pathTestResources.toFile();
		
	} // Fin de alimenterAttributsTestResources(...).______________________
	
	
	
	/**
	 * method alimenterTestResourcesParDefaut() :<br/>
	 * <ul>
	 * <li>Calcule la valeur par défaut de pathRelTestResources :
	 * <ol>
	 * <li>dans config_projet.properties si elle existe</li>
	 * <li>test/resources sinon.</li>
	 * </ol>  
	 * </li>
	 * <li>passe la valeur par défaut à pathRelTestResources.</li>
	 * <li>passe fabriquerPath(pathRepertoireSrc, pathRelTestResources) 
	 * à pathTestResources.</li>
	 * <li>passe pathTestResources.toString() à 
	 * pathTestResourcesString.</li>
	 * <li>passe pathTestResources.toFile() à fileTestResources.</li>
	 * <li>crée le répertoire par défaut si il n'existe pas.</li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	private static void alimenterTestResourcesParDefaut() 
			throws IOException {
		
		String valeurDefautConfig;
		String valeurDefaut = null;
		
		try {
			
			/* récupération de la valeur par défaut dans 
			 * configuration_projet.properties via 
			 * BundleConfigurationProjetManager. */
			valeurDefautConfig 
				= BundleConfigurationProjetManager
					.getPathRelTestResources();
			
			if (StringUtils.isBlank(valeurDefautConfig)) {
				valeurDefaut = "test/resources";
			} else {
				valeurDefaut = valeurDefautConfig;
			}
			
		} catch (Exception e) {
			valeurDefaut = "test/resources";
		}
				
		pathRelTestResources = valeurDefaut;
		pathTestResources 
			= fabriquerPath(pathRepertoireSrc, pathRelTestResources);
		pathTestResourcesString = pathTestResources.toString();
		fileTestResources = pathTestResources.toFile();
		
		/* crée le répertoire par défaut si il n'existe pas. */
		if (!fileTestResources.exists()) {
			Files.createDirectories(pathTestResources);
		}
		
	} // Fin de alimenterTestResourcesParDefaut().______________________________
	
	

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
	 * method getPathRelMainJava() :<br/>
	 * <ul>
	 * <li>Getter du path <i>relatif</i> des sources java 
	 * par rapport à src.</li>
	 * <li>sous forme de <b>String</b>.</li>
	 * <li>Par exemple :<br/>
	 * <code>main/java</code></li>
	 * </ul>
	 *
	 * @return pathRelMainJava : String.<br/>
	 */
	public static String getPathRelMainJava() {
		return pathRelMainJava;
	} // Fin de getPathRelMainJava().______________________________________


	
	/**
	 * method getPathMainJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du répertoire de la 
	 * RACINE des sources .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java
	 * </code></li>
	 * </ul>
	 *
	 * @return pathMainJavaString : String.<br/>
	 */
	public static String getPathMainJavaString() {
		return pathMainJavaString;
	} // Fin de getPathMainJavaString().___________________________________


	
	/**
	 * method getPathMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du répertoire de la 
	 * RACINE des sources .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java
	 * </code></li>
	 * </ul>
	 *
	 * @return pathMainJava : Path.<br/>
	 */
	public static Path getPathMainJava() {
		return pathMainJava;
	} // Fin de getPathMainJava()._________________________________________


	
	/**
	 * method getFileMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le répertoire 
	 * de la RACINE des sources .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java
	 * </code></li>
	 * </ul>
	 *
	 * @return fileMainJava : File.<br/>
	 */
	public static File getFileMainJava() {
		return fileMainJava;
	} // Fin de getFileMainJava()._________________________________________


		
	/**
	 * method getPathRelMainResources() :<br/>
	 *<ul>
	 * <li>Getter du path <i>relatif</i> des ressources 
	 * main par rapport à src.</li>
	 * <li>sous forme de <b>String</b>.</li>
	 * <li>Par exemple :<br/>
	 * <code>main/resources</code></li>
	 * </ul>
	 *
	 * @return pathRelMainResources : String.<br/>
	 */
	public static String getPathRelMainResources() {
		return pathRelMainResources;
	} // Fin de getPathRelMainResources()._________________________________


	
	/**
	 * method getPathMainResourcesString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du répertoire de la RACINE 
	 * des ressources main</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathMainResources = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainResources.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/resources
	 * </code></li>
	 * </ul>
	 *
	 * @return pathMainResourcesString : String.<br/>
	 */
	public static String getPathMainResourcesString() {
		return pathMainResourcesString;
	} // Fin de getPathMainResourcesString().______________________________


	
	/**
	 * method getPathMainResources() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du répertoire de la RACINE 
	 * des ressources main</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathMainResources = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainResources.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/resources
	 * </code></li>
	 * </ul>
	 *
	 * @return pathMainResources : Path.<br/>
	 */
	public static Path getPathMainResources() {
		return pathMainResources;
	} // Fin de getPathMainResources().____________________________________


	
	/**
	 * method getFileMainResources() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le répertoire 
	 * de la RACINE des ressources main</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathMainResources = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainResources.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/resources
	 * </code></li>
	 * </ul>
	 *
	 * @return fileMainResources : File.<br/>
	 */
	public static File getFileMainResources() {
		return fileMainResources;
	} // Fin de getFileMainResources().____________________________________


		
	/**
	 * method getPathRelTestJava() :<br/>
	 * <ul>
	 * <li>Getter du path <i>relatif</i> des sources des tests 
	 * java par rapport à src.</li>
	 * <li>sous forme de <b>String</b>.</li>
	 * <li>Par exemple :<br/>
	 * <code>test/java</code></li>
	 * </ul>
	 *
	 * @return pathRelTestJava : String.<br/>
	 */
	public static String getPathRelTestJava() {
		return pathRelTestJava;
	} // Fin de getPathRelTestJava().______________________________________
	
	
	
	/**
	 * method getPathTestJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du répertoire de la RACINE des sources 
	 * des test .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java
	 * </code></li>
	 * </ul>
	 *
	 * @return pathTestJavaString : String.<br/>
	 */
	public static String getPathTestJavaString() {
		return pathTestJavaString;
	} // Fin de getPathTestJavaString().___________________________________
	
	
	
	/**
	 * method getPathTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du répertoire de la RACINE des sources 
	 * des tests .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java
	 * </code></li>
	 * </ul>
	 *
	 * @return pathTestJava : Path.<br/>
	 */
	public static Path getPathTestJava() {
		return pathTestJava;
	} // Fin de getPathTestJava()._________________________________________
	
	
	
	/**
	 * method getFileTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le répertoire 
	 * de la RACINE des sources des tests .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java
	 * </code></li>
	 * </ul>
	 *
	 * @return fileTestJava : File.<br/>
	 */
	public static File getFileTestJava() {
		return fileTestJava;
	} // Fin de getFileTestJava()._________________________________________
	
	
		
	/**
	 * method getPathRelTestResources() :<br/>
	 * <ul>
	 * <li>Getter du path <i>relatif</i> des ressources de test 
	 * par rapport à src.</li>
	 * <li>sous forme de <b>String</b>.</li>
	 * <li>Par exemple :<br/>
	 * <code>test/resources</code></li>
	 * </ul>
	 *
	 * @return pathRelTestResources : String.<br/>
	 */
	public static String getPathRelTestResources() {
		return pathRelTestResources;
	} // Fin de getPathRelTestResources()._________________________________


	
	/**
	 * method getPathTestResourcesString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du répertoire de la RACINE 
	 * des ressources des tests</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathTestResources = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestResources.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/resources
	 * </code></li>
	 * </ul>
	 *
	 * @return pathTestResourcesString : String.<br/>
	 */
	public static String getPathTestResourcesString() {
		return pathTestResourcesString;
	} // Fin de getPathTestResourcesString().______________________________


	
	/**
	 * method getPathTestResources() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du répertoire de la RACINE 
	 * des ressources des tests</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathTestResources = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestResources.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/resources
	 * </code></li>
	 * </ul>
	 *
	 * @return pathTestResources : Path.<br/>
	 */
	public static Path getPathTestResources() {
		return pathTestResources;
	} // Fin de getPathTestResources().____________________________________


	
	/**
	 * method getFileTestResources() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le répertoire 
	 * de la RACINE des ressources des tests</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathTestResources = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestResources.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/resources
	 * </code></li>
	 * </ul>
	 *
	 * @return fileTestResources : File.<br/>
	 */
	public static File getFileTestResources() {
		return fileTestResources;
	} // Fin de getFileTestResources().____________________________________



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
		
		nomRepertoireSrc = null;
		pathRepertoireSrcString = null;
		pathRepertoireSrc = null;
		fileRepertoireSrc = null;
		
		pathRelMainJava = null;
		pathMainJavaString = null;
		pathMainJava = null;
		fileMainJava = null;
		
		pathRelMainResources = null;
		pathMainResourcesString = null;
		pathMainResources = null;
		fileMainResources = null;
		
		pathRelTestJava = null;
		pathTestJavaString = null;
		pathTestJava = null;
		fileTestJava = null;
		
		pathRelTestResources = null;
		pathTestResourcesString = null;
		pathTestResources = null;
		fileTestResources = null;

	} // Fin de reinitialiserAttributs().__________________________________

	

} // FIN DE LA CLASSE GestionnaireProjet.------------------------------------
