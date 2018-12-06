package levy.daniel.application.model.services.metier.generationcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesbundles.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairespaths.ManagerPaths;
import levy.daniel.application.model.utilitaires.gestionnairesiofichiers.GestionnaireFichiers;


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
	 * METHODE_LIRE_STRINGS_DANS_FILE : String :<br/>
	 * "méthode lireStringsDansFile(File pFile, Charset pCharset)".<br/>
	 */
	public static final String METHODE_LIRE_STRINGS_DANS_FILE 
		= "méthode lireStringsDansFile(File pFile, Charset pCharset)";


	/**
	 * METHODE_ECRIRESTRINGDANSFILE : String :<br/>
	 * "méthode ecrireStringDansFile(
	 * File pFile, String pString, Charset pCharset)".<br/>
	 */
	public static final String METHODE_ECRIRESTRINGDANSFILE 
		= "méthode ecrireStringDansFile(File pFile, ....)";

	
	/**
	 * METHODE_EXISTLIGNEDANSFICHIER : String :<br/>
	 * "méthode existLigneDansFichier(...)".<br/>
	 */
	public static final String METHODE_EXISTLIGNEDANSFICHIER 
		= "méthode existLigneDansFichier(...)";
	
	
	/**
	 * METHODE_EXISTLIGNECOMMENCANT : String :<br/>
	 * "méthode existLigneCommencant(...)".<br/>
	 */
	public static final String METHODE_EXISTLIGNECOMMENCANT 
		= "méthode existLigneCommencant(...)";
	

	//*****************************************************************/
	//**************************** BOM_UTF-8 **************************/
	//*****************************************************************/
	/**
	 * BOM_UTF : char :<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	public static final char BOM_UTF_8 = '\uFEFF';

	
	
	//*****************************************************************/
	//*********************** SAUTS DE LIGNE **************************/
	//*****************************************************************/	
	/**
	 * SAUTDELIGNE_UNIX : String :<br/>
	 * Saut de ligne généré par les éditeurs Unix.<br/>
	 * "\n" (Retour Ligne = LINE FEED (LF)).
	 */
	public static final String SAUTDELIGNE_UNIX = "\n";
	
	/**
	 * SAUTDELIGNE_MAC : String :<br/>
	 * Saut de ligne généré par les éditeurs Mac.<br/>
	 * "\r" (Retour Chariot RC = CARRIAGE RETURN (CR))
	 */
	public static final String SAUTDELIGNE_MAC = "\r";
	
	/**
	 * SAUTDELIGNE_DOS_WINDOWS : String :<br/>
	 * Saut de ligne généré par les éditeurs DOS/Windows.<br/>
	 * "\r\n" (Retour Chariot RC + Retour Ligne LF).
	 */
	public static final String SAUTDELIGNE_DOS_WINDOWS = "\r\n";
	
	/**
	 * NEWLINE : String :<br/>
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE 
		= System.getProperty("line.separator");


	
	
	//*****************************************************************/
	//************************** SEPARATEURS **************************/
	//*****************************************************************/

	/**
	 * SEPARATEUR_FICHIERS : String :<br/>
	 * <ul>
	 * <li>Séparateur de fichiers de la plateforme.</li>
	 * <li>fourni par System.getProperty("file.separator").</li>
	 * <li>antislash '\' sur la plateforme Windows.</li>
	 * </ul>
	 */
	public static final String SEPARATEUR_FICHIERS 
		= System.getProperty("file.separator");
	

	/**
	 * SEP_ESPACE : String :<br/>
	 * " ".<br/>
	 */
	public static final String SEP_ESPACE = " ";
	
	/**
	 * SEP_PV : String :<br/>
	 * Séparateur pour les CSV ";".<br/>
	 */
	public static final String SEP_PV = ";";
	
	/**
	 * SEP_2PTS_AERE : String :<br/>
	 * " : ".<br/>
	 */
	public static final String SEP_2PTS_AERE = " : ";
	
	/**
	 * SEPARATEUR_MOINS_AERE : String :<br/>
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";
	
	/**
	 * EGAL : String :<br/>
	 * " = ".<br/>
	 */
	public static final String EGAL = " = ";
	
	/**
	 * UNDERSCORE : String :<br/>
	 * "_".<br/>
	 */
	public static final String UNDERSCORE = "_";
	
	/**
	 * SLASH : char :<br/>
	 * '/'.<br/>
	 */
	public static final char SLASH = '/';
	
	/**
	 * ANTISLASH : char :<br/>
	 * '\'.<br/>
	 * ATTENTION : antislash est un caractère spécial 
	 * qui doit être échappé en Java ('\\')<br/>
	 */
	public static final char ANTISLASH = '\\';
	
	/**
	 * POINT : char :<br/>
	 * '.'.<br/>
	 */
	public static final char POINT = '.';
	
	/**
	 * POINT_VIRGULE : char :<br/>
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';

	
	//*****************************************************************/
	//************************** CHARSETS *****************************/
	//*****************************************************************/	
	/**
	 * CHARSET_UTF8 : Charset :<br/>
	 * Charset.forName("UTF-8").<br/>
	 * Eight-bit Unicode (or UCS) Transformation Format.<br/> 
	 */
	public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");

	
	// ****************************************************/
	// MESSAGES.
	// ****************************************************/
	/**
	 * MESSAGE_FICHIER_NULL : String :<br/>
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si le fichier est null.<br/>
	 * "Le fichier passé en paramètre est null".<br/>
	 */
	public static final String MESSAGE_FICHIER_NULL 
		= "Le fichier passé en paramètre est null";
	
	
	/**
	 * MESSAGE_FICHIER_INEXISTANT : String :<br/>
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si le fichier est inexistant.<br/>
	 * "Le fichier passé en paramètre est inexistant : "
	 */
	public static final String MESSAGE_FICHIER_INEXISTANT 
		= "Le fichier passé en paramètre est inexistant : ";
	
	
	/**
	 * MESSAGE_FICHIER_REPERTOIRE : String :<br/>
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si le fichier est un répertoire.<br/>
	 * "Le fichier passé en paramètre est un répertoire : ".<br/>
	 */
	public static final String MESSAGE_FICHIER_REPERTOIRE 
		= "Le fichier passé en paramètre est un répertoire : ";
	
	
	/**
	 * MESSAGE_STRING_BLANK : String :<br/>
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si la String passée en paramètre est blank.<br/>
	 * "La chaine de caractères passée en paramètre est blank (null ou vide) : "
	 */
	public static final String MESSAGE_STRING_BLANK 
		= "La chaine de caractères passée en paramètre est blank (null ou vide) : ";
	
	
	/**
	 * MESSAGE_EXCEPTION : String :<br/>
	 * "Exception GRAVE : ".<br/>
	 */
	public static final String MESSAGE_EXCEPTION = "Exception GRAVE : ";
	

	// ****************************************************/
	// VARIABLES DE TEMPLATES.
	// ****************************************************/
	
	/**
	 * VARIABLE_NEW_DATE : String :<br/>
	 * "{$date}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	public static final String VARIABLE_NEW_DATE 
		= "{$date}";

	
	/**
	 * VARIABLE_PACKAGE : String :<br/>
	 * "{$chemin.package}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	public static final String VARIABLE_PACKAGE 
		= "{$chemin.package}";
	
	
	/**
	 * VARIABLE_PATH_PROJET : String :<br/>
	 * "{$pathProjet}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	public static final String VARIABLE_PATH_PROJET 
		= "{$pathProjet}";

	
	/**
	 * VARIABLE_NOM_PROJET : String :<br/>
	 * "{$nomProjet}".<br/>
	 */
	public static final String VARIABLE_NOM_PROJET 
		= "{$nomProjet}";

	
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
	 * <li>Singleton.</li>
	 * <li>Par Exemple : <br/>
	 * <code>levy.daniel.application</code></li>
	 * </ul>
	 */
	private static String groupId;
	
	
	/**
	 * pathRelGroupId : String :<br/>
	 * <ul>
	 * <li><b>path relatif du groupId Maven</b> du projet par rapport 
	 * au path absolu des sources java 
	 * (pathMainJava pour les sources du main 
	 * et pathTestJava pour les sources des tests).</li>
	 * <li>Singleton.</li>
	 * <li>sous forme de String.</li>
	 * <li>Par Exemple : <br/>
	 * <code>levy/daniel/application</code></li>
	 * </li>
	 */
	private static String pathRelGroupId;


	/**
	 * pathGroupIdMainJavaString : String :<br/>
	 * <ul>
	 * <li><b>path du PACKAGE PERE des sources main Java</b>
	 * (comprenant le GroupId) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathGroupidMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application
	 * </code></li>
	 * </ul>
	 */
	private static String pathGroupIdMainJavaString;
	
	
	/**
	 * pathGroupIdMainJava : Path :<br/>
	 * <ul>
	 * <li><b>path du PACKAGE PERE des sources main Java</b>
	 * (comprenant le GroupId) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathGroupidMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application
	 * </code></li>
	 * </ul>
	 */
	private static Path pathGroupIdMainJava;
	
	
	/**
	 * fileGroupIdMainJava : File :<br/>
	 * <ul>
	 * <li><b>File modélisant le PACKAGE PERE des sources main Java</b>
	 * (comprenant le GroupId) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathGroupidMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application
	 * </code></li>
	 * </ul>
	 */
	private static File fileGroupIdMainJava;
	
	
	/**
	 * pathGroupIdTestJavaString : String :<br/>
	 * <ul>
	 * <li><b>path du PACKAGE PERE des sources des tests Java</b>
	 * (comprenant le GroupId) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathGroupIdTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application
	 * </code></li>
	 * </ul>
	 */
	private static String pathGroupIdTestJavaString;

	
	/**
	 * pathGroupIdTestJava : Path :<br/>
	 * <ul>
	 * <li><b>path du PACKAGE PERE des sources des tests Java</b>
	 * (comprenant le GroupId) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathGroupIdTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application
	 * </code></li>
	 * </ul>
	 */
	private static Path pathGroupIdTestJava;

	
	/**
	 * fileGroupIdTestJava : File :<br/>
	 * <ul>
	 * <li><b>File modélisant le PACKAGE PERE des sources 
	 * des tests Java</b> (comprenant le GroupId) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathGroupIdTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application
	 * </code></li>
	 * </ul>
	 */
	private static File fileGroupIdTestJava;

		
	/**
	 * PATH_REL_APPTECHNIC : String :<br/>
	 * <ul>
	 * <li><b>path relatif du répertoire apptechnic</b> 
	 * du projet par rapport 
	 * au path absolu du groupId
	 * (pathGroupIdMainJava pour les sources du main 
	 * et pathGroupIdTestJava pour les sources des tests).</li>
	 * <li>Singleton.</li>
	 * <li>sous forme de String.</li>
	 * <li>Par Exemple : <br/>
	 * <code>appTechnic</code></li>
	 * </li>
	 * </ul>
	 */
	private static final String PATH_REL_APPTECHNIC 
		= "apptechnic";
	
	
	/**
	 * pathAppTechnicMainJavaString : String :<br/>
	 * <ul>
	 * <li><b>path absolu du repertoire apptechnic</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathAppTechnicMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_APPTECHNIC.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * apptechnic</code>
	 * </li>
	 * </ul>
	 */
	private static String pathAppTechnicMainJavaString;
	
	
	/**
	 * pathAppTechnicMainJava : Path :<br/>
	 * <ul>
	 * <li><b>path absolu du repertoire apptechnic</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathAppTechnicMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_APPTECHNIC.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * apptechnic</code>
	 * </li>
	 * </ul>
	 */
	private static Path pathAppTechnicMainJava;
	

	/**
	 * fileAppTechnicMainJava : File :<br/>
	 * <ul>
	 * <li><b>File modélisant le repertoire apptechnic</b> 
	 * dans les sources Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathAppTechnicMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_APPTECHNIC.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * apptechnic</code>
	 * </li>
	 * </ul>
	 */
	private static File fileAppTechnicMainJava;
	

	/**
	 * pathAppTechnicTestJavaString : String :<br/>
	 * <ul>
	 * <li><b>path absolu du repertoire apptechnic</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathAppTechnicTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_APPTECHNIC.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * apptechnic</code>
	 * </li>
	 * </ul>
	 */
	private static String pathAppTechnicTestJavaString;

	
	/**
	 * pathAppTechnicTestJava : Path :<br/>
	 * <ul>
	 * <li><b>path absolu du repertoire apptechnic</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathAppTechnicTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_APPTECHNIC.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * apptechnic</code>
	 * </li>
	 * </ul>
	 */
	private static Path pathAppTechnicTestJava;

	
	/**
	 * fileAppTechnicTestJava : File :<br/>
	 * <ul>
	 * <li><b>File modélisant le repertoire apptechnic</b> 
	 * dans les tests Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathAppTechnicTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_APPTECHNIC.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * apptechnic</code>
	 * </li>
	 * </ul>
	 */
	private static File fileAppTechnicTestJava;

			
	/**
	 * PATH_REL_CONTROLLERS : String :<br/>
	 * <ul>
	 * <li><b>path relatif du répertoire controllers</b> 
	 * du projet par rapport 
	 * au path absolu du groupId
	 * (pathGroupIdMainJava pour les sources du main 
	 * et pathGroupIdTestJava pour les sources des tests).</li>
	 * <li>Singleton.</li>
	 * <li>sous forme de String.</li>
	 * <li>Par Exemple : <br/>
	 * <code>controllers</code></li>
	 * </li>
	 * </ul>
	 */
	private static final String PATH_REL_CONTROLLERS 
		= "controllers";
	
	
	/**
	 * pathControllersMainJavaString : String :<br/>
	 * <ul>
	 * <li><b>path absolu du repertoire controllers</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathControllersMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_CONTROLLERS.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * controllers</code>
	 * </li>
	 * </ul>
	 */
	private static String pathControllersMainJavaString;
	
	
	/**
	 * pathControllersMainJava : Path :<br/>
	 * <ul>
	 * <li><b>path absolu du repertoire controllers</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathControllersMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_CONTROLLERS.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * controllers</code>
	 * </li>
	 * </ul>
	 */
	private static Path pathControllersMainJava;
	
	
	/**
	 * fileControllersMainJava : File :<br/>
	 * <ul>
	 * <li><b>File modélisant le repertoire controllers</b> 
	 * dans les sources Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathControllersMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_CONTROLLERS.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * controllers</code>
	 * </li>
	 * </ul>
	 */
	private static File fileControllersMainJava;
	
	
	/**
	 * pathControllersTestJavaString : String :<br/>
	 * <ul>
	 * <li><b>path absolu du repertoire controllers</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathControllersTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_CONTROLLERS.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * controllers</code>
	 * </li>
	 * </ul>
	 */
	private static String pathControllersTestJavaString;
	
	
	/**
	 * pathControllersTestJava : Path :<br/>
	 * <ul>
	 * <li><b>path absolu du repertoire controllers</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathControllersTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_CONTROLLERS.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * controllers</code>
	 * </li>
	 * </ul>
	 */
	private static Path pathControllersTestJava;
	
	
	/**
	 * fileControllersTestJava : File :<br/>
	 * <ul>
	 * <li><b>File modélisant le repertoire controllers</b> 
	 * dans les tests Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathControllersTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_CONTROLLERS.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * controllers</code>
	 * </li>
	 * </ul>
	 */
	private static File fileControllersTestJava;
	
	
		
	/**
	* PATH_REL_MODEL : String :<br/>
	* <ul>
	* <li><b>path relatif du répertoire model</b> 
	* du projet par rapport 
	* au path absolu du groupId
	* (pathGroupIdMainJava pour les sources du main 
	* et pathGroupIdTestJava pour les sources des tests).</li>
	* <li>Singleton.</li>
	* <li>sous forme de String.</li>
	* <li>Par Exemple : <br/>
	* <code>model</code></li>
	* </li>
	* </ul>
	*/
	private static final String PATH_REL_MODEL 
		= "model";
	
	
	/**
	* pathModelMainJavaString : String :<br/>
	* <ul>
	* <li><b>path absolu du repertoire model</b> 
	* dans les sources Java.</li>
	* <li>path sous forme de <b>String</b>.</li>
	* <li>pathModelMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_MODEL.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model</code>
	* </li>
	* </ul>
	*/
	private static String pathModelMainJavaString;
	
	
	/**
	* pathModelMainJava : Path :<br/>
	* <ul>
	* <li><b>path absolu du repertoire model</b> 
	* dans les sources Java.</li>
	* <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	* <li>pathModelMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_MODEL.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model</code>
	* </li>
	* </ul>
	*/
	private static Path pathModelMainJava;
	
	
	/**
	* fileModelMainJava : File :<br/>
	* <ul>
	* <li><b>File modélisant le repertoire model</b> 
	* dans les sources Java.</li>
	* <li>java.io.File.</li>
	* <li>pathModelMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_MODEL.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model</code>
	* </li>
	* </ul>
	*/
	private static File fileModelMainJava;
	
	
	/**
	* pathModelTestJavaString : String :<br/>
	* <ul>
	* <li><b>path absolu du repertoire model</b> 
	* dans les tests Java.</li>
	* <li>path sous forme de <b>String</b>.</li>
	* <li>pathModelTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_MODEL.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model</code>
	* </li>
	* </ul>
	*/
	private static String pathModelTestJavaString;
	
	
	/**
	* pathModelTestJava : Path :<br/>
	* <ul>
	* <li><b>path absolu du repertoire model</b> 
	* dans les tests Java.</li>
	* <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	* <li>pathModelTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_MODEL.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model</code>
	* </li>
	* </ul>
	*/
	private static Path pathModelTestJava;
	
	
	/**
	* fileModelTestJava : File :<br/>
	* <ul>
	* <li><b>File modélisant le repertoire model</b> 
	* dans les tests Java.</li>
	* <li>java.io.File.</li>
	* <li>pathModelTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_MODEL.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model</code>
	* </li>
	* </ul>
	*/
	private static File fileModelTestJava;
	
		
	/**
	* PATH_REL_VUES : String :<br/>
	* <ul>
	* <li><b>path relatif du répertoire vues</b> 
	* du projet par rapport 
	* au path absolu du groupId
	* (pathGroupIdMainJava pour les sources du main 
	* et pathGroupIdTestJava pour les sources des tests).</li>
	* <li>Singleton.</li>
	* <li>sous forme de String.</li>
	* <li>Par Exemple : <br/>
	* <code>vues</code></li>
	* </li>
	* </ul>
	*/
	private static final String PATH_REL_VUES 
		= "vues";
	
	
	/**
	* pathVuesMainJavaString : String :<br/>
	* <ul>
	* <li><b>path absolu du repertoire vues</b> 
	* dans les sources Java.</li>
	* <li>path sous forme de <b>String</b>.</li>
	* <li>pathVuesMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_VUES.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* vues</code>
	* </li>
	* </ul>
	*/
	private static String pathVuesMainJavaString;
	
	
	/**
	* pathVuesMainJava : Path :<br/>
	* <ul>
	* <li><b>path absolu du repertoire vues</b> 
	* dans les sources Java.</li>
	* <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	* <li>pathVuesMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_VUES.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* vues</code>
	* </li>
	* </ul>
	*/
	private static Path pathVuesMainJava;
	
	
	/**
	* fileVuesMainJava : File :<br/>
	* <ul>
	* <li><b>File modélisant le repertoire vues</b> 
	* dans les sources Java.</li>
	* <li>java.io.File.</li>
	* <li>pathVuesMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_VUES.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* vues</code>
	* </li>
	* </ul>
	*/
	private static File fileVuesMainJava;
	
	
	/**
	* pathVuesTestJavaString : String :<br/>
	* <ul>
	* <li><b>path absolu du repertoire vues</b> 
	* dans les tests Java.</li>
	* <li>path sous forme de <b>String</b>.</li>
	* <li>pathVuesTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_VUES.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* vues</code>
	* </li>
	* </ul>
	*/
	private static String pathVuesTestJavaString;
	
	
	/**
	* pathVuesTestJava : Path :<br/>
	* <ul>
	* <li><b>path absolu du repertoire vues</b> 
	* dans les tests Java.</li>
	* <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	* <li>pathVuesTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_VUES.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* vues</code>
	* </li>
	* </ul>
	*/
	private static Path pathVuesTestJava;
	
	
	/**
	* fileVuesTestJava : File :<br/>
	* <ul>
	* <li><b>File modélisant le repertoire vues</b> 
	* dans les tests Java.</li>
	* <li>java.io.File.</li>
	* <li>pathVuesTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_VUES.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* vues</code>
	* </li>
	* </ul>
	*/
	private static File fileVuesTestJava;
	
			
	/**
	* PATH_REL_DAO : String :<br/>
	* <ul>
	* <li><b>path relatif du répertoire  "model/dao"</b> 
	* du projet par rapport 
	* au path absolu du groupId
	* (pathGroupIdMainJava pour les sources du main 
	* et pathGroupIdTestJava pour les sources des tests).</li>
	* <li>Singleton.</li>
	* <li>sous forme de String.</li>
	* <li>Par Exemple : <br/>
	* <code>"model/dao"</code></li>
	* </li>
	* </ul>
	*/
	private static final String PATH_REL_DAO 
		= PATH_REL_MODEL + "/dao";
	
	
	/**
	* pathDaoMainJavaString : String :<br/>
	* <ul>
	* <li><b>path absolu du repertoire "model/dao"</b> 
	* dans les sources Java.</li>
	* <li>path sous forme de <b>String</b>.</li>
	* <li>pathDaoMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_DAO.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model/dao</code>
	* </li>
	* </ul>
	*/
	private static String pathDaoMainJavaString;
	
	
	/**
	* pathDaoMainJava : Path :<br/>
	* <ul>
	* <li><b>path absolu du repertoire "model/dao"</b> 
	* dans les sources Java.</li>
	* <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	* <li>pathDaoMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_DAO.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model/dao</code>
	* </li>
	* </ul>
	*/
	private static Path pathDaoMainJava;
	
	
	/**
	* fileDaoMainJava : File :<br/>
	* <ul>
	* <li><b>File modélisant le repertoire "model/dao"</b> 
	* dans les sources Java.</li>
	* <li>java.io.File.</li>
	* <li>pathDaoMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_DAO.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model/dao</code>
	* </li>
	* </ul>
	*/
	private static File fileDaoMainJava;
	
	
	/**
	* pathDaoTestJavaString : String :<br/>
	* <ul>
	* <li><b>path absolu du repertoire "model/dao"</b> 
	* dans les tests Java.</li>
	* <li>path sous forme de <b>String</b>.</li>
	* <li>pathDaoTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_DAO.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model/dao</code>
	* </li>
	* </ul>
	*/
	private static String pathDaoTestJavaString;
	
	
	/**
	* pathDaoTestJava : Path :<br/>
	* <ul>
	* <li><b>path absolu du repertoire "model/dao"</b> 
	* dans les tests Java.</li>
	* <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	* <li>pathDaoTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_DAO.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model/dao</code>
	* </li>
	* </ul>
	*/
	private static Path pathDaoTestJava;
	
	
	/**
	* fileDaoTestJava : File :<br/>
	* <ul>
	* <li><b>File modélisant le repertoire "model/dao"</b> 
	* dans les tests Java.</li>
	* <li>java.io.File.</li>
	* <li>pathDaoTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_DAO.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model/dao</code>
	* </li>
	* </ul>
	*/
	private static File fileDaoTestJava;
	
			
	/**
	* PATH_REL_METIER : String :<br/>
	* <ul>
	* <li><b>path relatif du répertoire  "model/metier"</b> 
	* du projet par rapport 
	* au path absolu du groupId
	* (pathGroupIdMainJava pour les sources du main 
	* et pathGroupIdTestJava pour les sources des tests).</li>
	* <li>Singleton.</li>
	* <li>sous forme de String.</li>
	* <li>Par Exemple : <br/>
	* <code>"model/metier"</code></li>
	* </li>
	* </ul>
	*/
	private static final String PATH_REL_METIER 
	= PATH_REL_MODEL + "/metier";
	
	
	/**
	* pathMetierMainJavaString : String :<br/>
	* <ul>
	* <li><b>path absolu du repertoire "model/metier"</b> 
	* dans les sources Java.</li>
	* <li>path sous forme de <b>String</b>.</li>
	* <li>pathMetierMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_METIER.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model/metier</code>
	* </li>
	* </ul>
	*/
	private static String pathMetierMainJavaString;
	
	
	/**
	* pathMetierMainJava : Path :<br/>
	* <ul>
	* <li><b>path absolu du repertoire "model/metier"</b> 
	* dans les sources Java.</li>
	* <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	* <li>pathMetierMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_METIER.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model/metier</code>
	* </li>
	* </ul>
	*/
	private static Path pathMetierMainJava;
	
	
	/**
	* fileMetierMainJava : File :<br/>
	* <ul>
	* <li><b>File modélisant le repertoire "model/metier"</b> 
	* dans les sources Java.</li>
	* <li>java.io.File.</li>
	* <li>pathMetierMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_METIER.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model/metier</code>
	* </li>
	* </ul>
	*/
	private static File fileMetierMainJava;
	
	
	/**
	* pathMetierTestJavaString : String :<br/>
	* <ul>
	* <li><b>path absolu du repertoire "model/metier"</b> 
	* dans les tests Java.</li>
	* <li>path sous forme de <b>String</b>.</li>
	* <li>pathMetierTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_METIER.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model/metier</code>
	* </li>
	* </ul>
	*/
	private static String pathMetierTestJavaString;
	
	
	/**
	* pathMetierTestJava : Path :<br/>
	* <ul>
	* <li><b>path absolu du repertoire "model/metier"</b> 
	* dans les tests Java.</li>
	* <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	* <li>pathMetierTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_METIER.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model/metier</code>
	* </li>
	* </ul>
	*/
	private static Path pathMetierTestJava;
	
	
	/**
	* fileMetierTestJava : File :<br/>
	* <ul>
	* <li><b>File modélisant le repertoire "model/metier"</b> 
	* dans les tests Java.</li>
	* <li>java.io.File.</li>
	* <li>pathMetierTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_METIER.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model/metier</code>
	* </li>
	* </ul>
	*/
	private static File fileMetierTestJava;
	
			
	/**
	* PATH_REL_SERVICES : String :<br/>
	* <ul>
	* <li><b>path relatif du répertoire  "model/services"</b> 
	* du projet par rapport 
	* au path absolu du groupId
	* (pathGroupIdMainJava pour les sources du main 
	* et pathGroupIdTestJava pour les sources des tests).</li>
	* <li>Singleton.</li>
	* <li>sous forme de String.</li>
	* <li>Par Exemple : <br/>
	* <code>"model/services"</code></li>
	* </li>
	* </ul>
	*/
	private static final String PATH_REL_SERVICES 
	= PATH_REL_MODEL + "/services";
	
	
	/**
	* pathServicesMainJavaString : String :<br/>
	* <ul>
	* <li><b>path absolu du repertoire "model/services"</b> 
	* dans les sources Java.</li>
	* <li>path sous forme de <b>String</b>.</li>
	* <li>pathServicesMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_SERVICES.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model/services</code>
	* </li>
	* </ul>
	*/
	private static String pathServicesMainJavaString;
	
	
	/**
	* pathServicesMainJava : Path :<br/>
	* <ul>
	* <li><b>path absolu du repertoire "model/services"</b> 
	* dans les sources Java.</li>
	* <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	* <li>pathServicesMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_SERVICES.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model/services</code>
	* </li>
	* </ul>
	*/
	private static Path pathServicesMainJava;
	
	
	/**
	* fileServicesMainJava : File :<br/>
	* <ul>
	* <li><b>File modélisant le repertoire "model/services"</b> 
	* dans les sources Java.</li>
	* <li>java.io.File.</li>
	* <li>pathServicesMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_SERVICES.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model/services</code>
	* </li>
	* </ul>
	*/
	private static File fileServicesMainJava;
	
	
	/**
	* pathServicesTestJavaString : String :<br/>
	* <ul>
	* <li><b>path absolu du repertoire "model/services"</b> 
	* dans les tests Java.</li>
	* <li>path sous forme de <b>String</b>.</li>
	* <li>pathServicesTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_SERVICES.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model/services</code>
	* </li>
	* </ul>
	*/
	private static String pathServicesTestJavaString;
	
	
	/**
	* pathServicesTestJava : Path :<br/>
	* <ul>
	* <li><b>path absolu du repertoire "model/services"</b> 
	* dans les tests Java.</li>
	* <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	* <li>pathServicesTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_SERVICES.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model/services</code>
	* </li>
	* </ul>
	*/
	private static Path pathServicesTestJava;
	
	
	/**
	* fileServicesTestJava : File :<br/>
	* <ul>
	* <li><b>File modélisant le repertoire "model/services"</b> 
	* dans les tests Java.</li>
	* <li>java.io.File.</li>
	* <li>pathServicesTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_SERVICES.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model/services</code>
	* </li>
	* </ul>
	*/
	private static File fileServicesTestJava;
	

	/**
	 * FILE_LOG4J_XML : File :<br/>
	 * log4j2.xml.<br/>
	 */
	private static final File FILE_LOG4J_XML 
		= new File("./src/main/resources/log4j2.xml");

	
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
	 * method alimenterAttributs() :
	 * <ul>
	 * <li>Alimente tous les attributs avec les valeurs par défaut.</li>
	 * <li>Utilise le présent Workspace.</li>
	 * <li>Génère le code dans le présent projet.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	public static void alimenterAttributs() 
			throws Exception {
		alimenterAttributs(null
				, null
				, null
				, null, null, null, null, null);
	} // Fin de alimenterAttributs().______________________________________
	

	
	/**
	 * method alimenterAttributs(
	 * String pNomProjet) :
	 * <ul>
	 * <li>Alimente tous les atttributs avec les valeurs par défaut.</li>
	 * <li>Utilise le présent Workspace.</li>
	 * <li>Ne prend en paramètre que le nom du projet.</li>
	 * <li>Génère le code dans projet passé en paramètre.</li>
	 * </ul>
	 *
	 * @param pNomProjet : String : nom du projet. 
	 * Le projet courant si ce paramètre est null.<br/>
	 * 
	 * @throws Exception
	 */
	public static void alimenterAttributs(
			final String pNomProjet) throws Exception {
		alimenterAttributs(
				null
				, pNomProjet
				, null, null, null, null, null, null);
	} // Fin de alimenterAttributs(...).___________________________________
	
	
		
	/**
	 * method alimenterAttributs(
	 * String pPathWorkspaceString
	 * , String pNomProjet
	 * , String pNomRepertoireSrc
	 * , String pPathRelMainJava
	 * , String pPathRelMainResources
	 * , String pPathRelTestJava
	 * , String pPathRelTestResources
	 * , String pGroupId) :<br/>
	 * <ul>
	 * <li>alimente tous les attributs avec les paramètres.</li>
	 * <li>passe une valeur par défaut pour chaque paramètre 
	 * si ce paramètre est null.</li>
	 * </ul>
	 *
	 * @param pPathWorkspaceString : String : 
	 * chemin du Workspace dans lequel générer le code. 
	 * Prend une valeur par défaut si ce paramètre est null.<br/>
	 * @param pNomProjet : String : nom du projet. 
	 * Le projet courant si ce paramètre est null.<br/>
	 * @param pNomRepertoireSrc : String : nom du répertoire des src. 
	 * Prend une valeur par défaut si le paramètre est null.<br/>
	 * @param pPathRelMainJava : String : chemin relatif des main/java. 
	 * Prend une valeur par défaut si le paramètre est null.<br/>
	 * @param pPathRelMainResources : String : 
	 * chemin relatif des main/resources. 
	 * Prend une valeur par défaut si le paramètre est null.<br/>
	 * @param pPathRelTestJava : String : chemin relatif des test/java. 
	 * Prend une valeur par défaut si le paramètre est null.<br/>
	 * @param pPathRelTestResources : String : chemin relatif 
	 * des test/resources. 
	 * Prend une valeur par défaut si le paramètre est null.<br/>
	 * @param pGroupId : String : chemin relatif du GroupId 
	 * (levy/daniel/application). 
	 * Prend une valeur par défaut si le paramètre est null.<br/>
	 * 
	 * @throws Exception
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
			alimenterPathRelGroupId(pGroupId);
			
			alimenterAppTechnic();
			alimenterControllers();
			alimenterModel();
			alimenterVues();
			alimenterDao();
			alimenterMetier();
			alimenterServices();

			/* Crée tous les répertoires directement sous projet. */
			creerRepertoiresSousProjet();
			
			/* Génère tous les fichiers de ressources. */
			genererFichiersRessources();
			
			/* copie de Log4j2.xml. */
			copierSousMainResources(FILE_LOG4J_XML);
			
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
	 * <li>retourne le path du Workspace dans 
	 * configuration_projet.properties si il existe.</li>
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
					
					final Path path 
						= fabriquerPath(pathRepertoireSrc, pString);
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
	 * 
	 * @throws Exception 
	 */
	private static void alimenterAttributsMainJava(
			final String pString) throws Exception {
		
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
	 * @throws Exception 
	 */
	private static void alimenterMainJavaParDefaut() 
			throws Exception {
		
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
	 * method alimenterPathRelGroupId() :<br/>
	 * <ul>
	 * <li><b>alimente pathRelGroupId</b>.</li>
	 * <li><b>alimente groupId</b>.</li>
	 * <li><b>alimente pathGroupIdMainJavaString</b>.</li>
	 * <li><b>alimente pathGroupIdMainJava</b>.</li>
	 * <li><b>alimente fileGroupIdMainJava</b>.</li>
	 * <li><b>alimente pathGroupIdTestJavaString</b>.</li>
	 * <li><b>alimente pathGroupIdTestJava</b>.</li>
	 * <li><b>alimente fileGroupIdTestJava</b>.</li>
	 * <ol>
	 * <li>alimente pathRelGroupId avec pString si pString 
	 * n'est pas blank et pointe sur un répertoire existant.</li>
	 * <li><b>crée d'abord le répertoire</b>, 
	 * puis alimente pathRelGroupId avec pString si pString 
	 * n'est pas blank et pointe sur un répertoire inexistant.</li>
	 * <li>alimente pathRelGroupId avec une valeur par défaut 
	 * si pString est blank.</li>
	 * </ol>
	 * <li>la valeur par défaut est :</li>
	 * <ol>
	 * <li>le nom du chemin relatif dans 
	 * <b>configuration_projet.properties</b> si il existe.</li>
	 * <li><b>levy/daniel/application</b> sinon.</li>
	 * </ol>
	 * </ul>
	 *
	 * @param pString : String : 
	 * chemin relatif (archétype MAVEN) dans 
	 * lequel générer le code.<br/>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterPathRelGroupId(
			final String pString) throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (pathRelGroupId == null) {
				
				if (parametreExistant(pString)) {
					
					final Path pathMainJavaGId
						= fabriquerPath(pathMainJava, pString);
					final Path pathTestJavaGId
						= fabriquerPath(pathTestJava, pString);
					
					final String pathMainJavaGIdString 
						= pathMainJavaGId.toString();
					final String pathTestJavaGIdString 
						= pathTestJavaGId.toString();
					
					if (existeDossier(pathMainJavaGIdString) 
							&& existeDossier(pathTestJavaGIdString)) {
						alimenterAttributsGroupId(pString);						
					} else {
						
						/* création des répertoires. */
						creerRepertoire(pathMainJavaGId);
						creerRepertoire(pathTestJavaGId);
						
						alimenterAttributsGroupId(pString);
					}
										
				} else {
					alimenterGroupIdParDefaut();
				}
								
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterPathRelGroupId(...).________________________
	

	
	/**
	 * method alimenterAttributsGroupId(
	 * String pString) :
	 * <ul>
	 * <li>passe pString à pathRelTestResources.</li>
	 * <li>passe fabriquerPath(pathMainJava, pString) 
	 * à pathGroupIdMainJava.</li>
	 * <li>passe fabriquerPath(pathTestJava, pString) 
	 * à pathGroupIdTestJava</li>
	 * <li>passe pathGroupIdMainJava.toString() à 
	 * pathGroupIdMainJavaString.</li>
	 * <li>passe pathGroupIdTestJava.toString() 
	 * à pathGroupIdTestJavaString.</li>
	 * <li>passe pathGroupIdMainJava.toFile() 
	 * à fileGroupIdMainJava.</li>
	 * <li>passe pathGroupIdTestJava.toFile() 
	 * à fileGroupIdTestJava.</li>
	 * <li>crée le package-info si il n'existe pas déjà.</li>
	 * </ul>
	 * ne fait rien si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterAttributsGroupId(
			final String pString) throws Exception {
		
		/* ne fait rien si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return;
		}
		
		pathRelGroupId = pString;
		pathGroupIdMainJava = fabriquerPath(pathMainJava, pString);
		pathGroupIdTestJava = fabriquerPath(pathTestJava, pString);
		pathGroupIdMainJavaString = pathGroupIdMainJava.toString();
		pathGroupIdTestJavaString = pathGroupIdTestJava.toString();
		fileGroupIdMainJava = pathGroupIdMainJava.toFile();
		fileGroupIdTestJava = pathGroupIdTestJava.toFile();
		
		/* crée le package-info si il n'existe pas déjà. */
		creerPackageInfo(pathGroupIdMainJava);
		
	} // Fin de alimenterAttributsGroupId(...).____________________________
	


	/**
	 * method alimenterGroupIdParDefaut() :
	 * <ul>
	 * <li>Calcule la valeur par défaut de pathRelGroupId :
	 * <ol>
	 * <li>dans config_projet.properties si elle existe</li>
	 * <li>levy/daniel/application sinon.</li>
	 * </ol>  
	 * </li>
	 * <li>passe la valeur par défaut à pathRelGroupId.</li>
	 * <li>passe fabriquerPath(pathMainJava, pathRelGroupId) 
	 * à pathGroupIdMainJava.</li>
	 * <li>passe fabriquerPath(pathTestJava, pathRelGroupId) 
	 * à pathGroupIdTestJava.</li>
	 * <li>passe pathGroupIdMainJava.toString() à 
	 * pathGroupIdMainJavaString.</li>
	 * <li>passe pathGroupIdTestJava.toString() à 
	 * pathGroupIdTestJavaString.</li>
	 * <li>passe pathGroupIdMainJava.toFile() à 
	 * fileGroupIdMainJava.</li>
	 * <li>passe pathGroupIdTestJava.toFile() à 
	 * fileGroupIdTestJava.</li>
	 * <li>crée le répertoire fileGroupIdMainJava 
	 * par défaut si il n'existe pas.</li>
	 * <li>crée le répertoire fileGroupIdTestJava 
	 * par défaut si il n'existe pas.</li>
	 * <li>crée le package-info si il n'existe pas déjà.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterGroupIdParDefaut() 
			throws Exception {
		
		String valeurDefautConfig;
		String valeurDefaut = null;
		
		try {
			
			/* récupération de la valeur par défaut dans 
			 * configuration_projet.properties via 
			 * BundleConfigurationProjetManager. */
			valeurDefautConfig 
				= BundleConfigurationProjetManager
					.getPathRelGroupId();
			
			if (StringUtils.isBlank(valeurDefautConfig)) {
				valeurDefaut = "levy/daniel/application";
			} else {
				valeurDefaut = valeurDefautConfig;
			}
			
		} catch (Exception e) {
			valeurDefaut = "levy/daniel/application";
		}
				
		pathRelGroupId = valeurDefaut;
		pathGroupIdMainJava = fabriquerPath(pathMainJava, pathRelGroupId);
		pathGroupIdTestJava = fabriquerPath(pathTestJava, pathRelGroupId);
		pathGroupIdMainJavaString = pathGroupIdMainJava.toString();
		pathGroupIdTestJavaString = pathGroupIdTestJava.toString();
		fileGroupIdMainJava = pathGroupIdMainJava.toFile();
		fileGroupIdTestJava = pathGroupIdTestJava.toFile();
		
		/* crée le répertoire par défaut si il n'existe pas. */
		if (!fileGroupIdMainJava.exists()) {
			Files.createDirectories(pathGroupIdMainJava);
		}
		
		/* crée le répertoire par défaut si il n'existe pas. */
		if (!fileGroupIdTestJava.exists()) {
			Files.createDirectories(pathGroupIdTestJava);
		}
		
		/* crée le package-info si il n'existe pas déjà. */
		creerPackageInfo(pathGroupIdMainJava);
		
	} // Fin de alimenterGroupIdParDefaut()._______________________________
	

	
	/**
	 * method alimenterAppTechnic() :<br/>
	 * <ul>
	 * <li>Alimente tous les attributs relatifs à apptechnic.</li>
	 * <li>Crée les répertoires si ils n'existent pas déjà.</li>
	 * <li>crée le package-info si il n'existe pas déjà.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterAppTechnic() throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (pathAppTechnicMainJava == null) {
				
				pathAppTechnicMainJava 
					= fabriquerPath(
							pathGroupIdMainJava, PATH_REL_APPTECHNIC);
				pathAppTechnicMainJavaString 
					= pathAppTechnicMainJava.toString();
				fileAppTechnicMainJava 
					= pathAppTechnicMainJava.toFile();
				
				if (!fileAppTechnicMainJava.exists()) {
					Files.createDirectories(pathAppTechnicMainJava);
				}
				
				/* crée le package-info si il n'existe pas déjà. */
				creerPackageInfo(pathAppTechnicMainJava);
				
				
				pathAppTechnicTestJava 
				= fabriquerPath(
						pathGroupIdTestJava, PATH_REL_APPTECHNIC);
				pathAppTechnicTestJavaString 
					= pathAppTechnicTestJava.toString();
				fileAppTechnicTestJava 
					= pathAppTechnicTestJava.toFile();
				
				if (!fileAppTechnicTestJava.exists()) {
					Files.createDirectories(pathAppTechnicTestJava);
				}
				
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterAppTechnic()._____________________________________
	

	
	/**
	 * method alimenterControllers() :<br/>
	 * <ul>
	 * <li>Alimente tous les attributs relatifs à controllers.</li>
	 * <li>Crée les répertoires si ils n'existent pas déjà.</li>
	 * <li>crée le package-info si il n'existe pas déjà.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterControllers() throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (pathControllersMainJava == null) {
				
				pathControllersMainJava 
					= fabriquerPath(
							pathGroupIdMainJava, PATH_REL_CONTROLLERS);
				pathControllersMainJavaString 
					= pathControllersMainJava.toString();
				fileControllersMainJava 
					= pathControllersMainJava.toFile();
				
				if (!fileControllersMainJava.exists()) {
					Files.createDirectories(pathControllersMainJava);
				}
				
				/* crée le package-info si il n'existe pas déjà. */
				creerPackageInfo(pathControllersMainJava);
				
				
				pathControllersTestJava 
				= fabriquerPath(
						pathGroupIdTestJava, PATH_REL_CONTROLLERS);
				pathControllersTestJavaString 
					= pathControllersTestJava.toString();
				fileControllersTestJava 
					= pathControllersTestJava.toFile();
				
				if (!fileControllersTestJava.exists()) {
					Files.createDirectories(pathControllersTestJava);
				}
				
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterControllers().____________________________________
	

	
	/**
	 * method alimenterModel() :<br/>
	 * <ul>
	 * <li>Alimente tous les attributs relatifs à model.</li>
	 * <li>Crée les répertoires si ils n'existent pas déjà.</li>
	 * <li>crée le package-info si il n'existe pas déjà.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterModel() throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (pathModelMainJava == null) {
				
				pathModelMainJava 
					= fabriquerPath(
							pathGroupIdMainJava, PATH_REL_MODEL);
				pathModelMainJavaString 
					= pathModelMainJava.toString();
				fileModelMainJava 
					= pathModelMainJava.toFile();
				
				if (!fileModelMainJava.exists()) {
					Files.createDirectories(pathModelMainJava);
				}
				
				/* crée le package-info si il n'existe pas déjà. */
				creerPackageInfo(pathModelMainJava);
				
				
				pathModelTestJava 
				= fabriquerPath(
						pathGroupIdTestJava, PATH_REL_MODEL);
				pathModelTestJavaString 
					= pathModelTestJava.toString();
				fileModelTestJava 
					= pathModelTestJava.toFile();
				
				if (!fileModelTestJava.exists()) {
					Files.createDirectories(pathModelTestJava);
				}
				
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterModel().__________________________________________
	

	
	/**
	 * method alimenterVues() :<br/>
	 * <ul>
	 * <li>Alimente tous les attributs relatifs à vues.</li>
	 * <li>Crée les répertoires si ils n'existent pas déjà.</li>
	 * <li>crée le package-info si il n'existe pas déjà.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterVues() throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (pathVuesMainJava == null) {
				
				pathVuesMainJava 
					= fabriquerPath(
							pathGroupIdMainJava, PATH_REL_VUES);
				pathVuesMainJavaString 
					= pathVuesMainJava.toString();
				fileVuesMainJava 
					= pathVuesMainJava.toFile();
				
				if (!fileVuesMainJava.exists()) {
					Files.createDirectories(pathVuesMainJava);
				}
				
				/* crée le package-info si il n'existe pas déjà. */
				creerPackageInfo(pathVuesMainJava);
				
				
				pathVuesTestJava 
				= fabriquerPath(
						pathGroupIdTestJava, PATH_REL_VUES);
				pathVuesTestJavaString 
					= pathVuesTestJava.toString();
				fileVuesTestJava 
					= pathVuesTestJava.toFile();
				
				if (!fileVuesTestJava.exists()) {
					Files.createDirectories(pathVuesTestJava);
				}
				
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterVues().__________________________________________
	

	
	/**
	 * method alimenterDao() :<br/>
	 * <ul>
	 * <li>Alimente tous les attributs relatifs à model/dao.</li>
	 * <li>Crée les répertoires si ils n'existent pas déjà.</li>
	 * <li>crée le package-info si il n'existe pas déjà.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterDao() throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (pathDaoMainJava == null) {
				
				pathDaoMainJava 
					= fabriquerPath(
							pathGroupIdMainJava, PATH_REL_DAO);
				pathDaoMainJavaString 
					= pathDaoMainJava.toString();
				fileDaoMainJava 
					= pathDaoMainJava.toFile();
				
				if (!fileDaoMainJava.exists()) {
					Files.createDirectories(pathDaoMainJava);
				}
				
				/* crée le package-info si il n'existe pas déjà. */
				creerPackageInfo(pathDaoMainJava);
				
				
				pathDaoTestJava 
				= fabriquerPath(
						pathGroupIdTestJava, PATH_REL_DAO);
				pathDaoTestJavaString 
					= pathDaoTestJava.toString();
				fileDaoTestJava 
					= pathDaoTestJava.toFile();
				
				if (!fileDaoTestJava.exists()) {
					Files.createDirectories(pathDaoTestJava);
				}
				
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterDao().____________________________________________

	
	
	/**
	 * method alimenterMetier() :<br/>
	 * <ul>
	 * <li>Alimente tous les attributs relatifs à model/metier.</li>
	 * <li>Crée les répertoires si ils n'existent pas déjà.</li>
	 * <li>crée le package-info si il n'existe pas déjà.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterMetier() throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (pathMetierMainJava == null) {
				
				pathMetierMainJava 
					= fabriquerPath(
							pathGroupIdMainJava, PATH_REL_METIER);
				pathMetierMainJavaString 
					= pathMetierMainJava.toString();
				fileMetierMainJava 
					= pathMetierMainJava.toFile();
				
				if (!fileMetierMainJava.exists()) {
					Files.createDirectories(pathMetierMainJava);
				}
				
				/* crée le package-info si il n'existe pas déjà. */
				creerPackageInfo(pathMetierMainJava);
				
				
				pathMetierTestJava 
				= fabriquerPath(
						pathGroupIdTestJava, PATH_REL_METIER);
				pathMetierTestJavaString 
					= pathMetierTestJava.toString();
				fileMetierTestJava 
					= pathMetierTestJava.toFile();
				
				if (!fileMetierTestJava.exists()) {
					Files.createDirectories(pathMetierTestJava);
				}
				
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterMetier()._________________________________________
	

	
	/**
	 * method alimenterServices() :<br/>
	 * <ul>
	 * <li>Alimente tous les attributs relatifs à model/services.</li>
	 * <li>Crée les répertoires si ils n'existent pas déjà.</li>
	 * <li>crée le package-info si il n'existe pas déjà.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void alimenterServices() throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			if (pathServicesMainJava == null) {
				
				pathServicesMainJava 
					= fabriquerPath(
							pathGroupIdMainJava, PATH_REL_SERVICES);
				pathServicesMainJavaString 
					= pathServicesMainJava.toString();
				fileServicesMainJava 
					= pathServicesMainJava.toFile();
				
				if (!fileServicesMainJava.exists()) {
					Files.createDirectories(pathServicesMainJava);
				}
				
				/* crée le package-info si il n'existe pas déjà. */
				creerPackageInfo(pathServicesMainJava);
				
				
				pathServicesTestJava 
				= fabriquerPath(
						pathGroupIdTestJava, PATH_REL_SERVICES);
				pathServicesTestJavaString 
					= pathServicesTestJava.toString();
				fileServicesTestJava 
					= pathServicesTestJava.toFile();
				
				if (!fileServicesTestJava.exists()) {
					Files.createDirectories(pathServicesTestJava);
				}
				
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterServices()._______________________________________
	

	
	/**
	 * method creerRepertoiresSousProjet() :<br/>
	 * <ul>
	 * <li><b>Crée tous les répertoires directement 
	 * sous le projet</b>.</li>
	 * <li>conception_appli</li>
	 * <li>data</li>
	 * <li>javadoc</li>
	 * <li>rapports_controle</li>
	 * <li>ressources_externes</li>
	 * </ul>
	 *
	 * @throws IOException
	 */
	private static void creerRepertoiresSousProjet() 
			throws IOException {
		
		creerRepertoireConceptionAppli();
		creerRepertoireData();
		creerRepertoireJavadoc();
		creerRepertoireRapportsControle();
		creerRepertoireRessourcesExternes();
		
	} // Fin de creerRepertoiresSousProjet().______________________________

	
	
	/**
	 * method creerRepertoireConceptionAppli() :<br/>
	 * <ul>
	 * <li>Crée le répertoire conception_appli 
	 * directement sous le projet.</li>
	 * <li>crée également ses sous répertoires et sous-fichiers.</li>
	 * </ul>
	 *
	 * @throws IOException
	 */
	private static void creerRepertoireConceptionAppli() 
				throws IOException {
		
		creerRepertoireSousProjet("conception_appli");			
		final Path pathConceptionAppli 
			= pathProjet.resolve("conception_appli");
		creerRepertoireSousRepertoire(
				pathConceptionAppli, "docs");
		creerRepertoireSousRepertoire(
				pathConceptionAppli, "repostory EA13");
		final Path pathDocs = pathConceptionAppli.resolve("docs");
		copierFichierSousRepertoire(pathDocs
				, new File("./conception_appli/docs/docs.TXT"));
		
	} // Fin de creerRepertoireConceptionAppli().__________________________
	

	
	/**
	 * method creerRepertoireData() :<br/>
	 * <ul>
	 * <li>Crée le répertoire data 
	 * directement sous le projet.</li>
	 * <li>crée également ses sous répertoires et sous-fichiers.</li>
	 * </ul>
	 *
	 * @throws IOException
	 */
	private static void creerRepertoireData() 
			throws IOException {
		
		creerRepertoireSousProjet("data");
		final Path pathData = pathProjet.resolve("data");
		creerRepertoireSousRepertoire(pathData, "scripts_sql");
		copierFichierSousRepertoire(pathData
				, new File("./data/data.TXT"));
		
	} // Fin de creerRepertoireData()._____________________________________
	

	
	/**
	 * method creerRepertoireJavadoc() :<br/>
	 * <ul>
	 * <li>Crée le répertoire javadoc 
	 * directement sous le projet.</li>
	 * <li>crée également ses sous répertoires et sous-fichiers.</li>
	 * </ul>
	 *
	 * @throws IOException
	 */
	private static void creerRepertoireJavadoc() 
			throws IOException {
		
		creerRepertoireSousProjet("javadoc");
		final Path pathJavadoc = pathProjet.resolve("javadoc");
		creerRepertoireSousRepertoire(pathJavadoc, "images");
		
	} // Fin de creerRepertoireJavadoc().__________________________________
	

	
	/**
	 * method creerRepertoireRapportsControle() :<br/>
	 * <ul>
	 * <li>Crée le répertoire rapports_controle 
	 * directement sous le projet.</li>
	 * <li>crée également ses sous répertoires et sous-fichiers.</li>
	 * </ul>
	 *
	 * @throws IOException
	 */
	private static void creerRepertoireRapportsControle() 
			throws IOException {
		
		creerRepertoireSousProjet("rapports_controle");
		
	} // Fin de creerRepertoireRapportsControle()._________________________
	

	
	/**
	 * method creerRepertoireRessourcesExternes() :<br/>
	 * <ul>
	 * <li>Crée le répertoire ressources_externes 
	 * directement sous le projet.</li>
	 * <li>crée également ses sous répertoires et sous-fichiers.</li>
	 * </ul>
	 *
	 * @throws IOException
	 */
	private static void creerRepertoireRessourcesExternes() 
			throws IOException {
		
		creerRepertoireSousProjet("ressources_externes");
		
	} // Fin de creerRepertoireRessourcesExternes()._______________________
	

	
	/**
	 * method genererFichiersRessources() :<br/>
	 * <ul>
	 * <li><b>Génère toutes les ressources du projet cible</b>.</li>
	 * <li>configuration_ressources_externes_fr_FR.properties</li>
	 * <li>application_fr_FR.properties</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void genererFichiersRessources() throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			/* configuration_ressources_externes_fr_FR.properties */
			genererFichierConfigRessourcesExternes();
			
			/* application_fr_FR.properties */
			genererFichierApplicationProp();
			
		} // Fin de synchronized.___________________________________
				
	} // Fin de genererFichiersRessources()._______________________________

	
	
	/**
	 * method genererFichierConfigRessourcesExternes() :<br/>
	 * <ul>
	 * <li>génère le fichier 
	 * configuration_ressources_externes_fr_FR.properties 
	 * dans le projet cible.</li>
	 * <li>ne génère ce fichier que si il n'existe pas déjà.<br/>
	 * <li>ne fait rien si le code a déjà été généré 
	 * dans le fichier cible.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void genererFichierConfigRessourcesExternes() 
			throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			final String cheminFichier 
			= "./src/main/resources/configuration_"
					+ "ressources_externes_fr_FR.properties";
			
			final String cheminTemplate 
			= "main_resources/configuration_ressources_externes.txt";
			
			final String ligneIdentifiante = "ressourcesexternes=";
		
			final File fileConfigRessourcesExt 
			= new File(cheminFichier);
		
			/* crée le fichier vide dans le projet cible. */
			final File fileACreer 
				= creerFichierVideDansProjetCible(
						fileConfigRessourcesExt);
			
			/* ne fait rien si le code a déjà été généré 
			 * dans le fichier cible. */
			if (existLigneCommencant(
					fileACreer
						, CHARSET_UTF8
							, ligneIdentifiante)) {
				return;
			}
			
			/* lit le template. */
			final List<String> listTemplate 
				= lireTemplate(cheminTemplate);
			
			/* substitue les variables dans le template. */
			final List<String> listSubst1 
				= substituerVariablesDansLigne(
					listTemplate
						, VARIABLE_PATH_PROJET
							, retournerPathGenerique(pathProjetString));
			
			/* écrit le template substitué dans le fichier cible vide. */
			ecrireCode(listSubst1, fileACreer);
			
		} // Fin de synchronized.___________________________________

	} // Fin de genererFichierConfigRessourcesExternes().___________________
	
	

	/**
	 * method genererFichierApplicationProp() :<br/>
	 * <ul>
	 * <li>génère le fichier 
	 * application_fr_FR.properties 
	 * dans le projet cible.</li>
	 * <li>ne génère ce fichier que si il n'existe pas déjà.<br/>
	 * <li>ne fait rien si le code a déjà été généré 
	 * dans le fichier cible.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void genererFichierApplicationProp() 
			throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			final String cheminFichier 
			= "./src/main/resources/application_fr_FR.properties";
			
			final String cheminTemplate 
			= "main_resources/application.txt";
			
			final String ligneIdentifiante = "application.titre";
		
			final File fileConfigRessourcesExt 
			= new File(cheminFichier);
		
			/* crée le fichier vide dans le projet cible. */
			final File fileACreer 
				= creerFichierVideDansProjetCible(
						fileConfigRessourcesExt);
			
			/* ne fait rien si le code a déjà été généré 
			 * dans le fichier cible. */
			if (existLigneCommencant(
					fileACreer
						, CHARSET_UTF8
							, ligneIdentifiante)) {
				return;
			}
			
			/* lit le template. */
			final List<String> listTemplate 
				= lireTemplate(cheminTemplate);
			
			/* substitue les variables dans le template. */
			final List<String> listSubst1 
				= substituerVariablesDansLigne(
					listTemplate
						, VARIABLE_NOM_PROJET
							, nomProjet);
			
			/* écrit le template substitué dans le fichier cible vide. */
			ecrireCode(listSubst1, fileACreer);
			
		} // Fin de synchronized.___________________________________

	} // Fin de genererFichierApplicationProp().___________________________
	
	
	
	/**
	 * method creerPackageInfo(
	 * Path pPath) :<br/>
	 * <ul>
	 * <li>Crée un fichier package-info.java vide sous pPath 
	 * si il n'existe pas déjà.</li>
	 * <li>Ecrit le squelette du package-info.java.</li>
	 * </ul>
	 * ne fait rien si pPath==null.<br/>
	 * <br/>
	 *
	 * @param pPath : Path.<br/>
	 * 
	 * @throws Exception 
	 */
	private static void creerPackageInfo(
			final Path pPath) throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			/* ne fait rien si pPath==null. */
			if (pPath == null) {
				return;
			}
			
			/* Crée le path vers le fichier package-info.java. */
			final Path pathPackageInfo 
				= fabriquerPath(pPath, "package-info.java");
			final File filePackageInfo 
				= pathPackageInfo.toFile();
			
			final String pathTemplate 
				= "./src/main/resources/templates/package-info.txt";
			
			final File template = new File(pathTemplate);
			
			/* Créé le fichier vide si il n'existe pas déjà. */
			if (!filePackageInfo.exists()) {
				
				Files.createFile(pathPackageInfo);
				
				/* lit le template. */
				final List<String> list 
					= lireStringsDansFile(template, null);
				
				final List<String> listSubst1 
					= substituerVariablesDansLigne(
							list
							, VARIABLE_NEW_DATE
								, afficherDateDuJour());
				
				final List<String> listSubst2 
				= substituerVariablesDansLigne(
						listSubst1
						, VARIABLE_PACKAGE
							, creerPathJavaPackage(pPath));
				
				/* *************** */
				/* ENREGISTREMENT. */
				/* *************** */
				for (final String ligne : listSubst2) {

					if (StringUtils.isBlank(ligne)) {

						ecrireStringDansFile(
								filePackageInfo
									, ""
										, CHARSET_UTF8, NEWLINE);
						
					} else {

						ecrireStringDansFile(
								filePackageInfo
									, ligne
										, CHARSET_UTF8, NEWLINE);
						
					}
					
				} // Fin de l'écriture de la liste._________
					
			} // Fin de if (!filePackageInfo.exists()).______
						
		} // Fin de synchronized.______________________________
		
	} // Fin de creerPackageInfo(...)._____________________________________
	

	
	/**
	 * method creerPathJavaPackage(
	 * Path pPath) :<br/>
	 * <ul>
	 * <li>Crée le chemin Java du Package visé par pPath 
	 * relativement à pathMainJava.</li>
	 * <li>Par exemple :<br/>
	 * <code>levy.daniel.application.apptechnic</code>.</li>
	 * </ul>
	 *
	 * @param pPath : java.nio.file.Path.<br/>
	 * 
	 * @return : String : package Java.<br/>
	 */
	private static String creerPathJavaPackage(
			final Path pPath) {
		
		synchronized (GestionnaireProjet.class) {
			
			/* retourne null si pPath==null. */
			if (pPath == null) {
				return null;
			}
			
			/* RELATIVIZE : EXTRACTION DU PATH RELATIF. */
			final Path pathRelatif 
				= pathMainJava.relativize(pPath);
			
			/* Transformation du path relatif en String 
			 * avec des antislash. */
			final String pathRelatifAntiSlash 
				= pathRelatif.toString();
			
			/* Transformation en path Java avec des points. */
			final String pathRelatifPoint 
				= remplacerAntiSlashparPoint(
						pathRelatifAntiSlash);
			
			return pathRelatifPoint;
			
		} // Fin de synchronized._______________________
		
	} // Fin de creerPackage(...)._________________________________________
	

		
	/**
	 * method remplacerAntiSlashparPoint(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Remplace les antislashs '\' dans pString par des points '.'.</li>
	 * <li>Exemple : "levy\daniel\application" 
	 * remplacé par "levy.daniel.application".</li>
	 * <li>ATTENTION : antislash est un caractère spécial 
	 * qui doit être échappé en Java ('\\')</li>
	 * </ul>
	 * retourne null si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : String : String dans laquelle les antislashs 
	 * ont été remplacés par des points.<br/>
	 */
	private static String remplacerAntiSlashparPoint(
			final String pString) {
		
		synchronized (GestionnaireProjet.class) {
			
			/* retourne null si pString est blank. */
			if (StringUtils.isBlank(pString)) {
				return null;
			}
			
			String resultat = null;
			
			resultat = StringUtils.replaceChars(
					pString, ANTISLASH, POINT);
			
			return resultat;

			
		} // Fin de synchronized._______________________
		
	} // Fin de remplacerAntiSlashparPoint(...)._______________________________
	

	
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
	 * method copierSousMainResources(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>copie pFile sous src/main/resources dans le projet cible.</li>
	 * <li>conserve le nom de pFile dans le projet cible.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 */
	private static void copierSousMainResources(
			final File pFile) {
		
		synchronized (GestionnaireProjet.class) {
			
			/* ne fait rien si pFile est null. */
			if (pFile == null) {
				return;
			}
			
			/* ne fait rien si pFile n'existe pas. */
			if (!pFile.exists()) {
				return;
			}
			
			final Path pathDestination 
				= pathMainResources.resolve(pFile.getName());
			
			final File fileDestination = pathDestination.toFile();
			
			GestionnaireFichiers
				.copierFichierSansRemplacement(pFile, fileDestination);
			
		} // Fin de synchronized._________________________
		
	} // Fin de copierSousMainResources(...).______________________________
	

	
	/**
	 * method copierFichierSousRepertoire(
	 * Path pRepPath
	 * , File pFile) :<br/>
	 * <ul>
	 * <li>recopie le fichier pFile sous pRepPath.</li>
	 * <li>crée le répertoire pRepPath si il n'existe pas déjà.</li>
	 * </ul>
	 * ne fait rien si pRepPath == null.<br/>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pRepPath : Path : 
	 * Path du répertoire sous lequel copier le fichier.<br/>
	 * @param pFile : File : 
	 * fichier à copier sous le répertoire pRepPath.<br/>
	 * @throws IOException 
	 */
	private static void copierFichierSousRepertoire(
			final Path pRepPath, final File pFile) throws IOException {
		
		synchronized (GestionnaireProjet.class) {
			
			/* ne fait rien si pRepPath == null. */
			if (pRepPath == null) {
				return;
			}
			
			/* ne fait rien si pFile est null. */
			if (pFile == null) {
				return;
			}
			
			/* ne fait rien si pFile n'existe pas. */
			if (!pFile.exists()) {
				return;
			}
			
			final File fileRepPath = pRepPath.toFile();
			
			/* crée le répertoire à pRepPath si il n'existe pas déjà. */
			if (!fileRepPath.exists()) {
				Files.createDirectories(pRepPath);
			}
			
			final Path pathDestination 
				= pRepPath.resolve(pFile.getName());
			
			final File fileDestination = pathDestination.toFile();
			
			GestionnaireFichiers
				.copierFichierSansRemplacement(pFile, fileDestination);
			
		} // Fin de synchronized._________________________
		
	}
	
	
	
	/**
	 * method creerRepertoireSousProjet(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Crée le répertoire pString vide 
	 * directement sous le projet cible.</li>
	 * <li>ne crée le répertoire pString que 
	 * si il n'existe pas déjà.</li>
	 * </ul>
	 * ne fait rien si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String : nom du répertoire à créer.<br/>
	 * 
	 * @throws IOException 
	 */
	private static void creerRepertoireSousProjet(
			final String pString) throws IOException {
		
		synchronized (GestionnaireProjet.class) {
			
			/* ne fait rien si pString est blank. */
			if (StringUtils.isBlank(pString)) {
				return;
			}
			
			final Path pathRepACreer = pathProjet.resolve(pString);
			final File fileRepACreer = pathRepACreer.toFile();
			
			if (!fileRepACreer.exists()) {
				Files.createDirectories(pathRepACreer);
			}
						
		} // Fin de synchronized._________________________
		
	} // Fin de creerRepertoireSousProjet(...).____________________________
	

	
	/**
	 * method creerRepertoireSousRepertoire(
	 * Path pRepPath
	 * , String pString) :<br/>
	 * <ul>
	 * <li>Crée le répertoire pString vide 
	 * directement sous le répertoire pRepPAth.</li>
	 * <li>crée le répertoire pRepPath si il n'existe pas déjà.</li>
	 * <li>ne crée le répertoire pString que 
	 * si il n'existe pas déjà.</li>
	 * </ul>
	 * ne fait rien si pRepPath == null.<br/>
	 * ne fait rien si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pRepPath : Path : 
	 * Path du répertoire sous lequel copier le fichier.<br/>
	 * @param pString : String : nom du répertoire à créer.<br/>
	 * 
	 * @throws IOException
	 */
	private static void creerRepertoireSousRepertoire(
			final Path pRepPath
				, final String pString) throws IOException {
		
		synchronized (GestionnaireProjet.class) {
			
			/* ne fait rien si pRepPath == null. */
			if (pRepPath == null) {
				return;
			}

			/* ne fait rien si pString est blank. */
			if (StringUtils.isBlank(pString)) {
				return;
			}
			
			final File fileRepPath = pRepPath.toFile();
			
			/* crée le répertoire pRepPath si il n'existe pas déjà. */
			if (!fileRepPath.exists()) {
				Files.createDirectories(pRepPath);
			}
			
			final Path pathRepACreer = pRepPath.resolve(pString);
			final File fileRepACreer = pathRepACreer.toFile();
			
			if (!fileRepACreer.exists()) {
				Files.createDirectories(pathRepACreer);
			}
						
		} // Fin de synchronized._________________________
		
	} // Fin de creerRepertoireSousRepertoire(...).________________________
	

	
	/**
	 * method creerFichierVideDansProjetCible(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>Crée dans le projet cible un fichier vide</b> 
	 * portant le même nom et au même endroit relatif que pFile.</li>
	 * <li>retourne le fichier créé si il n'existait pas 
	 * ou le fichier existant.</li>
	 * <li>Ne crée ce fichier vide que si il n'existe pas 
	 * déjà dans le projet cible.</li>
	 * </ul>
	 * retourne null si pFile == null.<br/>
	 * retourne null si pFile n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pFile : File : 
	 * fichier du présent projet à créer dans le projet cible.<br/>
	 * 
	 * @return : File : fichier créé dans le projet cible.<br/>
	 * 
	 * @throws IOException 
	 */
	private static File creerFichierVideDansProjetCible(
			final File pFile) throws IOException {
		
		synchronized (GestionnaireProjet.class) {
			
			/* retourne null si pFile == null. */
			if (pFile == null) {
				return null;
			}
			
			/* retourne null si pFile n'existe pas. */
			if (!pFile.exists()) {
				return null;
			}
			
			final Path pathACopier = pFile.toPath();
			
			/* File désignant le projet courant. */
			final File filePresentProjet = new File(".");
			final Path pathPresentProjet 
				= filePresentProjet.toPath();
			
			/* RELATIVIZE : calcul du path relatif. */
			final Path pathACopierRelatif 
				= pathPresentProjet.relativize(pathACopier);
			
			/* RESOLVE : calcul du path dans le projet cible. */
			final Path pathDestination 
				= pathProjet.resolve(pathACopierRelatif);
			
			final File resultat = pathDestination.toFile();
			
			if (!resultat.exists()) {
				Files.createFile(pathDestination);
			}
						
			return resultat;
			
		} // Fin de synchronized._________________________
		
	} // Fin de creerFichierVideDansProjetCible(...).______________________
	
	
	
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
	 * method lireStringsDansFile(
	 * File pFile
	 * , Charset pCharset) :<br/>
	 * <ul>
	 * <li><b>Lit le contenu</b> d'un fichier texte 
	 * (fichier simple contenant du texte) pFile.</li>
	 * <li>Décode le contenu d'un fichier texte 
	 * (fichier simple contenant du texte) pFile 
	 * avec le Charset pCharset</li>
	 * <li><b>Retourne la liste des lignes</b> 
	 * du fichier simple texte pFile 
	 * lues avec le Charset pCharset.</li>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 
	 * si pCharset est null.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas.<br/>
	 * - Retourne null si pFile est un répertoire.<br/>
	 * - Retourne null en cas d'Exception loggée 
	 * (MalformedInputException, ...).<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier simple textuel à lire.<br/>
	 * @param pCharset : Charset : le Charset à utiliser pour 
	 * lire le fichier pFile.<br/>
	 * 
	 * @return : List&lt;String&gt; : Liste des lignes lues.<br/>
	 * 
	 * @throws Exception en cas d'Exception loggée 
	 * (IOException, MalformedInputException, ...).<br/>
	 */
	private static List<String> lireStringsDansFile(
			final File pFile
				, final Charset pCharset) throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			/* Retourne null si pFile est null. */
			if (pFile == null) {
				return null;
			}
			
			/* Retourne null si pFile n'existe pas. */
			if (!pFile.exists()) {
				return null;
			}
			
			/* Retourne null si pFile est un répertoire. */
			if (pFile.isDirectory()) {
				return null;
			}
			
			/* Utilise automatiquement le CHARSET_UTF8 si pCharset est null. */
			Charset charset = null;
			
			if (pCharset == null) {
				charset = CHARSET_UTF8;
			}
			else {
				charset = pCharset;
			}
			
			/* Récupère le Path de pFile. */
			final Path pathFichier = pFile.toPath();
			
			try {
				
				// *****************************************************
				/* Retourne la liste des lignes lues avec le charset. */
				return Files.readAllLines(pathFichier, charset);
				
			} 
			
			catch (MalformedInputException malformedInputException) {
				
				final String message 
				=  "Impossible de lire le contenu du fichier '" 
				+ pFile.getName() 
				+ "' avec le Charset " 
				+ charset.displayName(Locale.getDefault()) 
				+ " à cause d'un caractère qui ne peut être "
				+ "écrit dans ce Charset (MalformedInputException)";
				
				/* LOG de niveau Error. */
				loggerError(fournirNomClasse()
						, METHODE_LIRE_STRINGS_DANS_FILE 
						+ SEPARATEUR_MOINS_AERE 
						+ message
						, malformedInputException);
				
				/* retourne null en cas d'Exception loggée 
				 * (IOException, MalformedInputException, ...). */
				return null;

			}
			
			catch (IOException ioe) {
				
				/* LOG de niveau Error. */
				loggerError(fournirNomClasse()
						, METHODE_LIRE_STRINGS_DANS_FILE
						, ioe);
				
				final String message 
				= fournirNomClasse() 
				+ SEPARATEUR_MOINS_AERE 
				+ METHODE_LIRE_STRINGS_DANS_FILE 
				+ SEPARATEUR_MOINS_AERE 
				+ "Impossible de lire le contenu du fichier '" 
				+ pFile.getName() 
				+ "' avec le Charset " 
				+ charset.displayName(Locale.getDefault());
				
				/* jette une Exception en cas d'Exception loggée 
				 * (IOException, MalformedInputException, ...). */
				throw new Exception(message, ioe);
			
			}

		} // Fin de synchronized._______________________________
			
	} // Fin de lireStringsDansFile(
	 // File pFile
	 // , Charset pCharset)._______________________________________________
	

	
	/**
	 * method ecrireStringDansFile(
	 * File pFile
	 * , String pString
	 * , Charset pCharset
	 * , String pSautLigne) :<br/>
	 * <ul>
	 * <li><b>Ecrit la String pString</b> à 
	 * la <b>fin</b> du File pFile 
	 * avec un encodage pCharset et en substituant 
	 * les sauts de ligne déjà existants 
	 * <b>à l'intérieur de</b> pString par pSautLigne.</li>
	 * <li>N'efface ni le fichier ni son contenu 
	 * si il est déjà existant.</li>
	 * <ul>
	 * <li>Crée pFile sur disque si il n'existe pas.</li>
	 * <li>Crée sur disque les répertoires parents de pFile 
	 * (arborescence) si il n'existent pas.</li>
	 * <li>Ecrit la String pString à la fin du fichier 
	 * pFile si pFile est déjà existant et rempli
	 * (utilisation de FileOutputStream(pFile, true)).</li>
	 * <li>rajoute automatiquement 
	 * un saut de ligne à la fin de pString.</li>
	 * <li>Substitue automatiquement pSautLigne aux sauts de ligne 
	 * EXISTANTS dans pString si nécessaire.</li>
	 * <li>Utilise FileOutputStream, 
	 * new OutputStreamWriter(fileOutputStream, charset) 
	 * et BufferedWriter pour écrire.</li>
	 * <li>Ecriture dans un fichier, écriture sur disque.</li>
	 * <li>Passe automatiquement le Charset à CHARSET_UTF8 
	 * si pCharset est null.</li>
	 * <li>Passe automatiquement le saut de ligne à NEWLINE 
	 * (saut de ligne de la plateforme) si pSautLigne est blank.</li>
	 * </ul>
	 * </ul>
	 * <br/>
	 * - retourne null si le pFile est null.<br/>
	 * - retourne null si le pFile est un répertoire.<br/>
	 * - retourne null en cas d'Exception loggée 
	 * (FileNotFoundException, IOException).<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dans lequel on écrit.<br/>
	 * @param pString : String : String que l'on copie dans pFile.<br/>
	 * @param pCharset : Charset : Charset pour encoder le fichier.<br/>
	 * @param pSautLigne : String : Saut de ligne que l'on veut 
	 * dans pFile de sortie 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix).<br/>
	 * 
	 * @return : File : Le fichier dans lequel on a écrit pString.<br/>
	 */
	private static File ecrireStringDansFile(
			final File pFile
				, final String pString
					, final Charset pCharset
						, final String pSautLigne) {
		
		synchronized (GestionnaireProjet.class) {
			
			/* retourne null si le pFile est null. */
			if (pFile == null) {
				
				/* LOG de niveau INFO. */
				loggerInfo(
						fournirNomClasse()
							, METHODE_ECRIRESTRINGDANSFILE
								, MESSAGE_FICHIER_NULL);
				
				/* retour de null. */
				return null;
				
			} // Fin de if (pFile == null).______________________
			
			/* retourne null si le pFile est un répertoire. */
			if (pFile.isDirectory()) {
				
				/* LOG de niveau INFO. */
				loggerInfo(
						fournirNomClasse()
							, METHODE_ECRIRESTRINGDANSFILE
								, MESSAGE_FICHIER_REPERTOIRE
									, pFile.getAbsolutePath());
				
				/* retour de null. */
				return null;
				
			} // Fin de if (pFile.isDirectory())._________________
			
			
			final Path pathFichier = pFile.toPath();
			final Path pathRepertoirePere = pathFichier.getParent();
			File repertoirePere = null;
			
			if (pathRepertoirePere != null) {
				repertoirePere = pathRepertoirePere.toFile();
			} else {
				return null;
			}
			
					
			/* Crée pFile sur disque si il n'existe pas. */
			if (!pFile.exists()) {
							
				try {
					
					/* Crée sur disque les répertoires parents de pFile 
					 * (arborescence) si il n'existent pas. */
					if (!repertoirePere.exists()) {
						Files.createDirectories(pathRepertoirePere);
					}
					
					/* Crée le fichier sur disque si il n'existe pas. */
					Files.createFile(pathFichier);
					
				} catch (IOException ioe) {
					
					/* LOG de niveau Error. */
					loggerError(
							fournirNomClasse()
								, METHODE_ECRIRESTRINGDANSFILE
									, ioe);
					
					/* retour de null. */
					return null;
				}
				
			} // Fin de if (!pFile.exists())._____________________
			
						
			Charset charset = null;
			
			/* Passe automatiquement le charset à UTF-8 si pCharset est null. */
			if (pCharset == null) {
				charset = CHARSET_UTF8;
			}
			else {
				charset = pCharset;
			}
			
			String sautLigne = null;
			
			/* Passe automatiquement le saut de ligne à NEWLINE 
			 * (saut de ligne de la plateforme) si pSautLigne est blank. */
			if (StringUtils.isBlank(pSautLigne)) {
				sautLigne = NEWLINE;
			} else {
				sautLigne = pSautLigne;
			}
			
			// ECRITURE SUR DISQUE ***************
			FileOutputStream fileOutputStream = null;
			OutputStreamWriter outputStreamWriter = null;
			BufferedWriter bufferedWriter = null;
			
			try {
				
				/* Ouverture d'un FileOutputStream sur le fichier. */
				/* Ecrit la String pString à la fin du fichier pFile 
				 * si pFile est déjà existant et rempli sur le disque. */
				fileOutputStream 
					= new FileOutputStream(pFile, true);
				
				/* Ouverture d'un OutputStreamWriter 
				 * sur le FileOutputStream en lui passant le Charset. */
				outputStreamWriter 
					= new OutputStreamWriter(fileOutputStream, charset);
				
				/* Ouverture d'un tampon d'écriture 
				 * BufferedWriter sur le OutputStreamWriter. */
				bufferedWriter 
					= new BufferedWriter(outputStreamWriter);
				
				// ECRITURE.
				/* Substitue automatiquement sautLigne aux sauts de ligne 
				 * dans pString si nécessaire. */
				String stringAEcrire = null;
				
				if (pString.length() == 0) {
					stringAEcrire = "";
				} else {
					stringAEcrire = substituerSautLigne(pString, sautLigne);
				}
				
				bufferedWriter.write(stringAEcrire);
				
				/* RAJOUTE AUTOMATIQUEMENT UN SAUT DE LIGNE. */
				bufferedWriter.write(sautLigne);
				bufferedWriter.flush();
				
				// Retour du fichier. 
				return pFile;
				
			} catch (FileNotFoundException fnfe) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasse()
							, MESSAGE_EXCEPTION				
								, fnfe);
				
				/* retour de null. */
				return null;
				
			} catch (IOException ioe) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasse()
							, MESSAGE_EXCEPTION				
								, ioe);
				
				/* retour de null. */
				return null;
			}
			
			finally {
				
				if (bufferedWriter != null) {
					try {
						
						bufferedWriter.close();
						
					} catch (IOException ioe1) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasse()
									, MESSAGE_EXCEPTION				
										, ioe1);
					}
				} // Fin de if (bufferedWriter != null).__________
				
				if (outputStreamWriter != null) {
					try {
						
						outputStreamWriter.close();
						
					} catch (IOException ioe2) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasse()
									, MESSAGE_EXCEPTION				
										, ioe2);
					}
				} // Fin de if (outputStreamWriter != null).______
				
				if (fileOutputStream != null) {
					try {
						
						fileOutputStream.close();
						
					} catch (IOException ioe3) {
						
						//* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasse()
									, MESSAGE_EXCEPTION				
										, ioe3);
					}
				}
				
			} // Fin du finally.____________________________				
			
		} // Fin de synchronized.________________________________
		
	} // Fin de ecrireStringDansFile(...)._________________________________
	

	
	/**
	 * method existLigneDansFichier(
	 * File pFile
	 * , Charset pCharsetLecture
	 * , String pLigne) :<br/>
	 * <ul>
	 * <li><b>Détermine si la ligne pLigne existe dans pFile</b>.</li>
	 * <li>Retourne true si c'est le cas.</li>
	 * <li>Lit le fichier textuel simple 
	 * pFile avec l'encodage pCharsetLecture.</li>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour la lecture 
	 * si pCharsetLecture est null.</li>
	 * </ul>
	 * Retourne false si pFile est null.<br/>
	 * Retourne false si pFile n'existe pas sur le disque.<br/>
	 * Retourne false si pFile est un répertoire.<br/>
	 * Retourne false si pLigne est null.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier textuel simple dans lequel 
	 * on veut savoir si la ligne pLigne existe.<br/>
	 * @param pCharsetLecture : Charset : Charset dans lequel 
	 * le fichier simple textuel pFile est encodé. 
	 * On le lit donc avec ce Charset.<br/>
	 * @param pLigne : String : ligne à rechercher.<br/>
	 * 
	 * @return : boolean : true si la ligne existe.<br/>
	 */
	private static boolean existLigneDansFichier(
			final File pFile
				, final Charset pCharsetLecture
					, final String pLigne) {
		
		synchronized (GestionnaireProjet.class) {
			
			/* Retourne false si pFile est null. */
			if (pFile == null) {
				return false;
			}
			
			/* Retourne false si pFile n'existe pas sur le disque. */
			if (!pFile.exists()) {
				return false;
			}
			
			/* Retourne false si pFile est un répertoire. */
			if (pFile.isDirectory()) {
				return false;
			}
			
			/* Retourne false si pLigne est null. */
			if (pLigne == null) {
				return false;
			}

			Charset charsetLecture = null;
			
			/* Utilise automatiquement le CHARSET_UTF8 si 
			 * pCharsetLecture est null. */			
			if (pCharsetLecture == null) {
				charsetLecture = CHARSET_UTF8;
			}
			else {
				charsetLecture = pCharsetLecture;
			}
			
			// ****************************************************
			/* LECTURE. */
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader bufferedReader = null;
			
			boolean resultat = false;
			
			try {

				/* LECTURE DU FICHIER AVEC CHARSET charsetLecture. */
				inputStream = new FileInputStream(pFile);
				inputStreamReader 
					= new InputStreamReader(inputStream, charsetLecture);
				bufferedReader = new BufferedReader(inputStreamReader);


				String ligneLue = null;
				
				/* BOUCLE SUR LES LIGNES DE pFile. */
				while (true) {
															
					/* Lecture de la ligne. */
					ligneLue = bufferedReader.readLine();
					
					/* Fin de boucle à la fin du fichier. */
					if (ligneLue == null) {
						break;
					}
					
					/* capture le numéro de ligne si pLigne est trouvée. */
					if (StringUtils.equals(pLigne, ligneLue)) {
						resultat = true;
						break;
					}

				} // Fin de BOUCLE SUR LES LIGNES DE pFile._____
				
			} catch (FileNotFoundException e) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasse()
						, METHODE_EXISTLIGNEDANSFICHIER
						, e);
				
				/* retourne false. */
				return false;
				
			} catch (IOException e) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasse()
						, METHODE_EXISTLIGNEDANSFICHIER
						, e);
				
				/* retourne false. */
				return false;
				
			}
			
			finally {
				
				/* Fermeture des flux de lecture. */
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasse()
								, METHODE_EXISTLIGNEDANSFICHIER
								, e);
					}
				}
				if (inputStreamReader != null) {
					try {
						inputStreamReader.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasse()
								, METHODE_EXISTLIGNEDANSFICHIER
								, e);
					}
				}
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasse()
								, METHODE_EXISTLIGNEDANSFICHIER
								, e);
					}
				}
												
			} // Fin du finally._______________________________

			return resultat;
			
		} // Fin de synchronized._____________________________
		
	} // Fin de existLigneDansFichier(...).________________________________
	
	
	
	/**
	 * method existLigneCommencant(
	 * File pFile
	 * , Charset pCharsetLecture
	 * , String pLigne) :<br/>
	 * <ul>
	 * <li><b>Détermine si une ligne commençant par pLigne 
	 * existe dans pFile</b>.</li>
	 * <li>Retourne true si c'est le cas.</li>
	 * <li>Lit le fichier textuel simple 
	 * pFile avec l'encodage pCharsetLecture.</li>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour la lecture 
	 * si pCharsetLecture est null.</li>
	 * </ul>
	 * Retourne false si pFile est null.<br/>
	 * Retourne false si pFile n'existe pas sur le disque.<br/>
	 * Retourne false si pFile est un répertoire.<br/>
	 * Retourne false si pLigne est null.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier textuel simple dans lequel 
	 * on veut savoir si la ligne commençant par pLigne existe.<br/>
	 * @param pCharsetLecture : Charset : Charset dans lequel 
	 * le fichier simple textuel pFile est encodé. 
	 * On le lit donc avec ce Charset.<br/>
	 * @param pLigne : String : ligne à rechercher.<br/>
	 * 
	 * @return : boolean : true si la ligne existe.<br/>
	 */
	private static boolean existLigneCommencant(
			final File pFile
				, final Charset pCharsetLecture
					, final String pLigne) {
		
		synchronized (GestionnaireProjet.class) {
			
			/* Retourne false si pFile est null. */
			if (pFile == null) {
				return false;
			}
			
			/* Retourne false si pFile n'existe pas sur le disque. */
			if (!pFile.exists()) {
				return false;
			}
			
			/* Retourne false si pFile est un répertoire. */
			if (pFile.isDirectory()) {
				return false;
			}
			
			/* Retourne false si pLigne est null. */
			if (pLigne == null) {
				return false;
			}

			Charset charsetLecture = null;
			
			/* Utilise automatiquement le CHARSET_UTF8 si 
			 * pCharsetLecture est null. */			
			if (pCharsetLecture == null) {
				charsetLecture = CHARSET_UTF8;
			}
			else {
				charsetLecture = pCharsetLecture;
			}
			
			// ****************************************************
			/* LECTURE. */
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader bufferedReader = null;
			
			boolean resultat = false;
			
			try {

				/* LECTURE DU FICHIER AVEC CHARSET charsetLecture. */
				inputStream = new FileInputStream(pFile);
				inputStreamReader 
					= new InputStreamReader(inputStream, charsetLecture);
				bufferedReader = new BufferedReader(inputStreamReader);


				String ligneLue = null;
				
				/* BOUCLE SUR LES LIGNES DE pFile. */
				while (true) {
															
					/* Lecture de la ligne. */
					ligneLue = bufferedReader.readLine();
					
					/* Fin de boucle à la fin du fichier. */
					if (ligneLue == null) {
						break;
					}
					
					/* capture le numéro de ligne si pLigne est trouvée. */
					if (StringUtils.startsWith(ligneLue, pLigne)) {
						resultat = true;
						break;
					}

				} // Fin de BOUCLE SUR LES LIGNES DE pFile._____
				
			} catch (FileNotFoundException e) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasse()
						, METHODE_EXISTLIGNECOMMENCANT
						, e);
				
				/* retourne false. */
				return false;
				
			} catch (IOException e) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasse()
						, METHODE_EXISTLIGNECOMMENCANT
						, e);
				
				/* retourne false. */
				return false;
				
			}
			
			finally {
				
				/* Fermeture des flux de lecture. */
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasse()
								, METHODE_EXISTLIGNECOMMENCANT
								, e);
					}
				}
				if (inputStreamReader != null) {
					try {
						inputStreamReader.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasse()
								, METHODE_EXISTLIGNECOMMENCANT
								, e);
					}
				}
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasse()
								, METHODE_EXISTLIGNECOMMENCANT
								, e);
					}
				}
												
			} // Fin du finally._______________________________

			return resultat;
			
		} // Fin de synchronized._____________________
				
	} // Fin de existLigneCommencant(...)._________________________________
	

	
	/**
	 * method lireRessource(
	 * String pString) :<br/>
	 * <ul>
	 * <li><b>Lit le contenu d'une ressource pString</b> 
	 * sous ./src/main/resources dans le présent projet 
	 * et injecte le contenu dans une List&lt;String&gt;.</li>
	 * <li>lit le contenu en CHARSET_UTF8.</li>
	 * <li>retourne le contenu de la ressource 
	 * sous forme de List&lt;String&gt;</li>
	 * <li>Par exemple :<br/>
	 * <code>lireRessource("configuration_ressources
	 * _externes_fr_FR.properties")</code> injecte 
	 * le contenu de la ressource dans la liste retournée.
	 * </li>
	 * </ul>
	 *
	 * @param pString : String : 
	 * nom d'une ressource sous src/main/resources.<br/>
	 * 
	 * @return List&lt;String&gt; : 
	 * contenu de la ressourcce sous forme de liste.<br/>
	 *  
	 * @throws Exception
	 */
	private static List<String> lireRessource(
			final String pString) throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			/* retourne null si pString est blank. */
			if (StringUtils.isBlank(pString)) {
				return null;
			}
			
			final String stringRessourceBase 
				= "./src/main/resources/";
			final String stringRessource 
				= stringRessourceBase + pString;
			final File fileRessource = new File(stringRessource);
			
			/* retourne null si la ressource n'existe pas. */
			if (!fileRessource.exists()) {
				return null;
			}
			
			final List<String> listeLignes 
				= lireStringsDansFile(fileRessource, CHARSET_UTF8);
			
			return listeLignes;
			
		} // Fin de synchronized._______________________
		
	} // Fin de lireRessource(...).________________________________________
	


	/**
	 * method lireTemplate(
	 * String pString) :<br/>
	 * <ul>
	 * <li><b>Lit le contenu d'un template pString</b> 
	 * sous ./src/main/resources/templates dans le présent projet 
	 * et injecte le contenu dans une List&lt;String&gt;.</li>
	 * <li>pString ne doit contenir que le <i>path relatif 
	 * sous templates</i> comme 
	 * <code>hashcode/debut_hashcode.txt</code>.</li>
	 * <li>lit le contenu en CHARSET_UTF8.</li>
	 * <li>retourne le contenu du template
	 * sous forme de List&lt;String&gt;</li>
	 * <li>Par exemple :<br/>
	 * <code>lireTemplate("model/javadoc_getter
	 * _classform.txt")</code> injecte 
	 * le contenu de la ressource dans la liste retournée.
	 * </li>
	 * </ul>
	 *
	 * @param pString : String : 
	 * nom d'une ressource sous ./src/main/resources/template.<br/>
	 * 
	 * @return List&lt;String&gt; : 
	 * contenu de la ressourcce sous forme de liste.<br/>
	 *  
	 * @throws Exception
	 */
	private static List<String> lireTemplate(
			final String pString) throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			/* retourne null si pString est blank. */
			if (StringUtils.isBlank(pString)) {
				return null;
			}
			
			final String stringRessourceBase 
				= "./src/main/resources/templates/";
			final String stringRessource 
				= stringRessourceBase + pString;
			final File fileRessource = new File(stringRessource);
			
			/* retourne null si la ressource n'existe pas. */
			if (!fileRessource.exists()) {
				return null;
			}
			
			final List<String> listeLignes 
				= lireStringsDansFile(fileRessource, CHARSET_UTF8);
			
			return listeLignes;
			
		} // Fin de synchronized._______________________
		
	} // Fin de lireTemplate(...)._________________________________________
	

	
	/**
	 * method ecrireCode(
	 * List&lt;String&gt; pList, File pFile) :<br/>
	 * <ul>
	 * <li>Ecrit le contenu de la liste pList dans pFile.</li>
	 * <li>Ecrit pList à la fin du contenu de pFile.</li>
	 * <li>Ne contrôle pas si le contenu de pList a déjà 
	 * été injecté dans pFile. 
	 * Rajoute pList à la fin de pFile à chaque 
	 * appel de la méthode.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas ou pFile n' est 
	 * pas un fichier simple.<br/>
	 * ne fait rien si pList == null.<br/>
	 * <br/>
	 * 
	 * @param pList : List&lt;String&gt;.<br/>
	 * @param pFile : File.<br/>
	 */
	private static void ecrireCode(
			final List<String> pList
				, final File pFile) throws Exception {
		
		synchronized (GestionnaireProjet.class) {
			
			/* ne fait rien si pFile est null. */
			if (pFile == null) {
				return;
			}
			
			/* ne fait rien si pFile n'existe pas ou pFile 
			 * n' est pas un fichier simple. */
			if (!pFile.exists() || !pFile.isFile()) {
				return;
			}
			
			/* ne fait rien si pList == null. */
			if (pList == null) {
				return;
			}
			
			for (final String ligne : pList) {
				
				if (StringUtils.isBlank(ligne)) {
					
					ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);					
				}				
				else {
					
					ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
			
		} // Fin de synchronized._________________________________
				
	} // Fin de ecrireCode(...).___________________________________________


	
	/**
	 * method substituerSautLigne(
	 * String pString
	 * , String pSautLigne) :<br/>
	 * <ul>
	 * <li><b>Substitue les sauts</b> de ligne <b>à l'intérieur</b> 
	 * de pString 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * par les sauts de ligne pSautLigne.</li>
	 * </ul>
	 * - retourne null si pString est blank (null ou vide).<br/>
	 * - retourne null si pSautLigne est blank (null ou vide).<br/>
	 * <br/>
	 *
	 * @param pString : String : String à corriger.<br/>
	 * @param pSautLigne : String : saut de ligne à substituer.<br/>
	 * 
	 * @return : String : La String dans laquelle les sauts de ligne 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * ont été substitués par les sauts de ligne pSautLigne.<br/>
	 */
	private static String substituerSautLigne(
			final String pString
				, final String pSautLigne) {
		
		/* retourne null si pString est blank (null ou vide). */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		/* retourne null si pSautLigne est blank (null ou vide). */
		
		/* Recherche des sauts de ligne DOS/Windows. */
		if (StringUtils.contains(pString, SAUTDELIGNE_DOS_WINDOWS)) {
			
			final String resultat 
				= StringUtils.replace(
						pString, SAUTDELIGNE_DOS_WINDOWS, pSautLigne);
			
			return resultat;
		}
		
		/* Recherche des sauts de ligne Mac. */
		if (StringUtils.contains(pString, SAUTDELIGNE_MAC)) {
			
			final String resultat 
				= StringUtils.replace(
						pString, SAUTDELIGNE_MAC, pSautLigne);
			
			return resultat;
		}
		
		/* Recherche des sauts de ligne Unix. */
		if (StringUtils.contains(pString, SAUTDELIGNE_UNIX)) {
			
			final String resultat 
				= StringUtils.replace(
						pString, SAUTDELIGNE_UNIX, pSautLigne);
			
			return resultat;
		}
		
		/* Retourne la chaîne inchangée 
		 * si il n'y avait pas de saut de ligne. */
		return pString;
			
	} // Fin de substituerSautLigne(
	 // String pString
	 // , String pSautLigne).______________________________________________
	

	
	/**
	 * method substituerVariablesDansLigne(
	 * List&lt;String&gt; pListe
	 * , String pVariable
	 * , String pSubstituant) :<br/>
	 * <ul>
	 * <li><b>Substitue</b> <i>pSubstituant</i> à la 
	 * variable <i>pVariable</i> 
	 * dans chaque ligne de pListe.</li>
	 * <li>Par exemple : <br/>
	 * Substitue "levy.daniel.application.model.metier" 
	 * à {$pathmetier} dans chaque ligne.</li>
	 * </ul>
	 * retourne null si pListe est null.<br/>
	 * <br/>
	 *
	 * @param pListe : List&lt;String&gt;
	 * @param pVariable : String : variable à substituer
	 * @param pSubstituant : String : valeur à substituer à variable.<br/>
	 * 
	 * @return : List&lt;String&gt; : Liste des lignes 
	 * avec les variables substituées.<br/>
	 */
	private static List<String> substituerVariablesDansLigne(
			final List<String> pListe
				, final String pVariable
					, final String pSubstituant) {

		synchronized (GestionnaireProjet.class) {
			
			/* retourne null si pListe est null. */
			if (pListe == null) {
				return null;
			}
			
			final List<String> resultat = new ArrayList<String>();
			
			for (final String ligne : pListe) {
				
				final String nouvelleLigne 
					= StringUtils.replace(ligne, pVariable, pSubstituant);
				
				resultat.add(nouvelleLigne);
				
			}
			
			return resultat;

		} // Fin de synchronized._______________________________
		
	} // Fin de substituerVariablesDansLigne.______________________________
	


	/**
	 * method retournerPathGenerique(
	 * String pPathString) :<br/>
	 * <ul>
	 * <li><b>Remplace les séparateurs de fichier antislash</b> 
	 * dans pPath par des <b>séparateurs génériques slash '/'</b>.</li>
	 * <li>Par exemple : <br/>
	 * <code>retournerPathGenerique("D:\Donnees
	 * \eclipse\eclipseworkspace_neon\generation_code")</code> 
	 * retourne 
	 * <code>
	 * "D:/Donnees/eclipse/eclipseworkspace_neon/generation_code"
	 * </code>
	 * </li>
	 * </ul>
	 *
	 * @param pPathString : String.<br/>
	 * 
	 * @return : String : String avec des slashes
	 *  à la place des antislash.<br/>
	 */
	private static String retournerPathGenerique(
			final String pPathString) {
		
		/* retourne null si pPathString est blank. */
		if (StringUtils.isBlank(pPathString)) {
			return null;
		}
		
		final String resultat 
			= StringUtils.replaceChars(pPathString, '\\', SLASH);
		
		return resultat;
		
	} // Fin de retournerPathGenerique(...)._______________________________
	
	
	
	/**
	 * method afficherDateDuJour() :<br/>
	 * <ul>
	 * <li>Retourne une String pour l'affichage de la date du jour.</li>
	 * <li>Par exemple : 12 janvier 2018.</li>
	 * </ul>
	 *
	 * @return : String : Date du jour.<br/>
	 */
	private static String afficherDateDuJour() {
		
		synchronized (GestionnaireProjet.class) {
			
			final Date dateDuJour = new Date();
			
			final Locale localeFr = new Locale("fr", "FR");
			
			/* 12 janvier 2018. */
			final DateFormat dfDateFrancaise 
			= new SimpleDateFormat("dd MMMM yyyy", localeFr);
			
			return dfDateFrancaise.format(dateDuJour);
			
		} // Fin de synchronized._______________________________
				
	} // Fin de afficherDateDuJour().______________________________________

	
	
	/**
	 * method fournirDate(
	 * String pDateString) :<br/>
	 * Prend en paramètre une String compatible avec une Date
	 * au format "dd/MM/yyyy" ('25/02/1961' par exemple) et
	 * retourne une String.<br/>
	 * <br/>
	 *
	 * @param pDateString : la Date sous forme de String
	 * au format '25/02/1961'.<br/>
	 * 
	 * @return Date.<br/>
	 * 
	 * @throws ParseException : lorsque la date rentrée
	 * sous forme de String ne respecte pas le format
	 * "dd/MM/yyyy" ou n'est pas compatible avec une date
	 * (45/122/2012 par exemple).<br/>
	 */
	private static Date fournirDate(
							final String pDateString) 
												throws ParseException {
		
		synchronized (GestionnaireProjet.class) {
			
			final Locale localeFr = new Locale("fr", "FR");
			
			final DateFormat dfDateFrancaise 
			= new SimpleDateFormat("dd/MM/yyyy", localeFr);
					
			/* Indispensable pour générer une exception si la chaine
			 * de caractères ne peut être une date comme 322/47/2011. */
			dfDateFrancaise.setLenient(false);
			
			return dfDateFrancaise.parse(pDateString);
		
		} // Fin de synchronized._______________________________
	
	} // Fin de fournirDate(
	 // String pDateString)._______________________________________________
	

	
	/**
	 * method fournirNomClasse() :<br/>
	 * <ul>
	 * <li>Retourne "Classe GestionnaireProjet".</li>
	 * </ul>
	 *
	 * @return : String : "Classe GestionnaireProjet".<br/>
	 */
	private static String fournirNomClasse() {
		return GESTIONNAIRE_PROJET;
	} // Fin de fournirNomClasse().________________________________________


	
	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage) :<br/>
	 * <ul>
	 * <li>Crée un message de niveau INFO dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 */
	private static void loggerInfo(
			final String pClasse
				, final String pMethode
					, final String pMessage) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null || pMessage == null) {
			return;
		}
		
		/* LOG de niveau INFO. */			
		if (LOG.isInfoEnabled()) {
			
			final String message 
			= pClasse 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ pMessage;
			
			LOG.info(message);
		}
		
	} // Fin de la classe loggerInfo(
	 // String pClasse
	 // , String pMethode
	 // , String pMessage).________________________________________________
	

	
	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage
	 * , String pComplement) :<br/>
	 * <ul>
	 * <li>Créée un message de niveau INFO dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 * @param pComplement : String : Complément au message de la méthode.<br/>
	 */
	private static void loggerInfo(
			final String pClasse
				, final String pMethode
					, final String pMessage
						, final String pComplement) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null 
				|| pMessage == null || pComplement == null) {
			return;
		}
		
		/* LOG de niveau INFO. */			
		if (LOG.isInfoEnabled()) {
			
			final String message 
			= pClasse 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ pMessage
			+ pComplement;
			
			LOG.info(message);
		}
		
	} // Fin de loggerInfo(
	 // String pClasse
	 // , String pMethode
	 // , String pMessage
	 // , String pComplement)._____________________________________________
	
	
	
	/**
	 * method loggerError(
	 * String pClasse
	 * , String pMethode
	 * , Exception pException) :<br/>
	 * <ul>
	 * <li>Crée un message de niveau ERROR dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pException : Exception : Exception transmise 
	 * par la méthode appelante.<br/>
	 */
	private static void loggerError(
			final String pClasse
				, final String pMethode
					, final Exception pException) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null || pException == null) {
			return;
		}
		
		/* LOG de niveau ERROR. */			
		if (LOG.isErrorEnabled()) {
			
			final String message 
			= pClasse 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE 
			+ pException.getMessage();
			
			LOG.error(message, pException);
			
		}
		
	} // Fin de loggerError(
	 // String pClasse
	 // , String pMethode
	 // , Exception pException).___________________________________________
	
	
	
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
	 * method getGroupId() :<br/>
	 * <ul>
	 * <li>Getter du <b>groupId Maven</b> du projet.<br/>
	 * <li>Singleton.</li>
	 * <li>Par Exemple : <br/>
	 * <code>levy.daniel.application</code></li>
	 * </ul>
	 *
	 * @return groupId : String.<br/>
	 */
	public static String getGroupId() {	
		return groupId;
	} // Fin de getGroupId().______________________________________________


	
	/**
	 * method getPathRelGroupId() :<br/>
	 * <ul>
	 * <li>Getter du <b>path relatif du groupId Maven</b> 
	 * du projet par rapport 
	 * au path absolu des sources java 
	 * (pathMainJava pour les sources du main 
	 * et pathTestJava pour les sources des tests).</li>
	 * <li>Singleton.</li>
	 * <li>sous forme de String.</li>
	 * <li>Par Exemple : <br/>
	 * <code>levy/daniel/application</code></li>
	 * </li>
	 *
	 * @return pathRelGroupId : String.<br/>
	 */
	public static String getPathRelGroupId() {	
		return pathRelGroupId;
	} // Fin de getPathRelGroupId()._______________________________________


	
	/**
	 * method getPathGroupIdMainJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du PACKAGE PERE des sources main Java</b>
	 * (comprenant le GroupId) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathGroupidMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application
	 * </code></li>
	 * </ul>
	 *
	 * @return pathGroupIdMainJavaString : String.<br/>
	 */
	public static String getPathGroupIdMainJavaString() {	
		return pathGroupIdMainJavaString;
	} // Fin de getPathGroupIdMainJavaString().____________________________


	
	/**
	 * method getPathGroupIdMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du PACKAGE PERE des sources main Java</b>
	 * (comprenant le GroupId) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathGroupidMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application
	 * </code></li>
	 * </ul>
	 *
	 * @return pathGroupIdMainJava : Path.<br/>
	 */
	public static Path getPathGroupIdMainJava() {	
		return pathGroupIdMainJava;
	} // Fin de getPathGroupIdMainJava().__________________________________


	
	/**
	 * method getFileGroupIdMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le PACKAGE PERE 
	 * des sources main Java</b>
	 * (comprenant le GroupId) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathGroupidMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application
	 * </code></li>
	 * </ul>
	 *
	 * @return fileGroupIdMainJava : File.<br/>
	 */
	public static File getFileGroupIdMainJava() {	
		return fileGroupIdMainJava;
	} // Fin de getFileGroupIdMainJava().__________________________________


	
	/**
	 * method getPathGroupIdTestJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du PACKAGE PERE des sources des tests Java</b>
	 * (comprenant le GroupId) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathGroupIdTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application
	 * </code></li>
	 * </ul>
	 *
	 * @return pathGroupIdTestJavaString : String.<br/>
	 */
	public static String getPathGroupIdTestJavaString() {	
		return pathGroupIdTestJavaString;
	} // Fin de getPathGroupIdTestJavaString().____________________________


	
	/**
	 * method getPathGroupIdTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du PACKAGE PERE des sources des tests Java</b>
	 * (comprenant le GroupId) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathGroupIdTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application
	 * </code></li>
	 * </ul>
	 *
	 * @return pathGroupIdTestJava : Path.<br/>
	 */
	public static Path getPathGroupIdTestJava() {	
		return pathGroupIdTestJava;
	} // Fin de getPathGroupIdTestJava().__________________________________


	
	/**
	 * method getFileGroupIdTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le PACKAGE PERE des sources 
	 * des tests Java</b> (comprenant le GroupId) 
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>java.io.File.</li>
	 * <li>pathGroupIdTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application
	 * </code></li>
	 * </ul>
	 *
	 * @return fileGroupIdTestJava : File.<br/>
	 */
	public static File getFileGroupIdTestJava() {	
		return fileGroupIdTestJava;
	} // Fin de getFileGroupIdTestJava().__________________________________


	
	/**
	 * method getPathRelApptechnic() :<br/>
	 * <ul>
	 * <li>Getter du <b>path relatif du répertoire apptechnic</b> 
	 * du projet par rapport 
	 * au path absolu du groupId
	 * (pathGroupIdMainJava pour les sources du main 
	 * et pathGroupIdTestJava pour les sources des tests).</li>
	 * <li>Singleton.</li>
	 * <li>sous forme de String.</li>
	 * <li>Par Exemple : <br/>
	 * <code>appTechnic</code></li>
	 * </li>
	 * </ul>
	 *
	 * @return PATH_REL_APPTECHNIC : String.<br/>
	 */
	public static String getPathRelApptechnic() {	
		return PATH_REL_APPTECHNIC;
	} // Fin de getPathRelApptechnic().____________________________________


	
	/**
	 * method getPathAppTechnicMainJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire apptechnic</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathAppTechnicMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_APPTECHNIC.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * apptechnic</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathAppTechnicMainJavaString : String.<br/>
	 */
	public static String getPathAppTechnicMainJavaString() {	
		return pathAppTechnicMainJavaString;
	} // Fin de getPathAppTechnicMainJavaString()._________________________


	
	/**
	 * method getPathAppTechnicMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire apptechnic</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathAppTechnicMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_APPTECHNIC.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * apptechnic</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathAppTechnicMainJava : Path.<br/>
	 */
	public static Path getPathAppTechnicMainJava() {
		return pathAppTechnicMainJava;
	} // Fin de getPathAppTechnicMainJava()._______________________________


		
	/**
	 * method getFileAppTechnicMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le repertoire apptechnic</b> 
	 * dans les sources Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathAppTechnicMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_APPTECHNIC.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * apptechnic</code>
	 * </li>
	 * </ul>
	 *
	 * @return fileAppTechnicMainJava : File.<br/>
	 */
	public static File getFileAppTechnicMainJava() {	
		return fileAppTechnicMainJava;
	} // Fin de getFileAppTechnicMainJava()._______________________________



	/**
	 * method getPathAppTechnicTestJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire apptechnic</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathAppTechnicTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_APPTECHNIC.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * apptechnic</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathAppTechnicTestJavaString : String.<br/>
	 */
	public static String getPathAppTechnicTestJavaString() {	
		return pathAppTechnicTestJavaString;
	} // Fin de getPathAppTechnicTestJavaString()._________________________


	
	/**
	 * method getPathAppTechnicTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire apptechnic</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathAppTechnicTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_APPTECHNIC.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * apptechnic</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathAppTechnicTestJava : Path.<br/>
	 */
	public static Path getPathAppTechnicTestJava() {	
		return pathAppTechnicTestJava;
	} // Fin de getPathAppTechnicTestJava()._______________________________


	
	/**
	 * method getFileAppTechnicTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le repertoire apptechnic</b> 
	 * dans les tests Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathAppTechnicTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_APPTECHNIC.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * apptechnic</code>
	 * </li>
	 * </ul>
	 *
	 * @return fileAppTechnicTestJava : File.<br/>
	 */
	public static File getFileAppTechnicTestJava() {	
		return fileAppTechnicTestJava;
	} // Fin de getFileAppTechnicTestJava()._______________________________


	
	/**
	 * method getPathRelControllers() :<br/>
	 * <ul>
	 * <li>Getter du <b>path relatif du répertoire controllers</b> 
	 * du projet par rapport 
	 * au path absolu du groupId
	 * (pathGroupIdMainJava pour les sources du main 
	 * et pathGroupIdTestJava pour les sources des tests).</li>
	 * <li>Singleton.</li>
	 * <li>sous forme de String.</li>
	 * <li>Par Exemple : <br/>
	 * <code>controllers</code></li>
	 * </li>
	 * </ul>
	 *
	 * @return PATH_REL_CONTROLLERS : String.<br/>
	 */
	public static String getPathRelControllers() {	
		return PATH_REL_CONTROLLERS;
	} // Fin de getPathRelControllers().___________________________________


	
	/**
	 * method getPathControllersMainJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire controllers</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathControllersMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_CONTROLLERS.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * controllers</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathControllersMainJavaString : String.<br/>
	 */
	public static String getPathControllersMainJavaString() {	
		return pathControllersMainJavaString;
	} // Fin de getPathControllersMainJavaString().________________________


	
	/**
	 * method getPathControllersMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire controllers</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathControllersMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_CONTROLLERS.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * controllers</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathControllersMainJava : Path.<br/>
	 */
	public static Path getPathControllersMainJava() {
		return pathControllersMainJava;
	} // Fin de getPathControllersMainJava().______________________________


		
	/**
	 * method getFileControllersMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le repertoire controllers</b> 
	 * dans les sources Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathControllersMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_CONTROLLERS.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * controllers</code>
	 * </li>
	 * </ul>
	 *
	 * @return fileControllersMainJava : File.<br/>
	 */
	public static File getFileControllersMainJava() {	
		return fileControllersMainJava;
	} // Fin de getFileControllersMainJava().______________________________



	/**
	 * method getPathControllersTestJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire controllers</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathControllersTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_CONTROLLERS.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * controllers</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathControllersTestJavaString : String.<br/>
	 */
	public static String getPathControllersTestJavaString() {	
		return pathControllersTestJavaString;
	} // Fin de getPathControllersTestJavaString().________________________


	
	/**
	 * method getPathControllersTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire controllers</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathControllersTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_CONTROLLERS.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * controllers</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathControllersTestJava : Path.<br/>
	 */
	public static Path getPathControllersTestJava() {	
		return pathControllersTestJava;
	} // Fin de getPathControllersTestJava().______________________________


	
	/**
	 * method getFileControllersTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le repertoire controllers</b> 
	 * dans les tests Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathControllersTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_CONTROLLERS.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * controllers</code>
	 * </li>
	 * </ul>
	 *
	 * @return fileControllersTestJava : File.<br/>
	 */
	public static File getFileControllersTestJava() {	
		return fileControllersTestJava;
	} // Fin de getFileControllersTestJava().______________________________



	/**
	 * method getPathRelModel() :<br/>
	 * <ul>
	* <li>Getter du <b>path relatif du répertoire model</b> 
	* du projet par rapport 
	* au path absolu du groupId
	* (pathGroupIdMainJava pour les sources du main 
	* et pathGroupIdTestJava pour les sources des tests).</li>
	* <li>Singleton.</li>
	* <li>sous forme de String.</li>
	* <li>Par Exemple : <br/>
	* <code>model</code></li>
	* </li>
	* </ul>
	 *
	 * @return PATH_REL_MODEL : String.<br/>
	 */
	public static String getPathRelModel() {	
		return PATH_REL_MODEL;
	} // Fin de getPathModelMainJavaString().______________________________

	
	
	/**
	 * method getPathModelMainJavaString() :<br/>
	 * <ul>
	* <li>Getter du <b>path absolu du repertoire model</b> 
	* dans les sources Java.</li>
	* <li>path sous forme de <b>String</b>.</li>
	* <li>pathModelMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_MODEL.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model</code>
	* </li>
	* </ul>
	 *
	 * @return pathModelMainJavaString : String.<br/>
	 */
	public static String getPathModelMainJavaString() {	
		return pathModelMainJavaString;
	} // Fin de getPathModelMainJavaString().______________________________


	
	/**
	 * method getPathModelMainJava() :<br/>
	 * <ul>
	* <li>Getter du <b>path absolu du repertoire model</b> 
	* dans les sources Java.</li>
	* <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	* <li>pathModelMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_MODEL.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model</code>
	* </li>
	* </ul>
	 *
	 * @return pathModelMainJava : Path.<br/>
	 */
	public static Path getPathModelMainJava() {	
		return pathModelMainJava;
	} // Fin de getPathModelMainJava().____________________________________



	/**
	 * method getFileModelMainJava() :<br/>
	 * <ul>
	* <li>Getter du <b>File modélisant le repertoire model</b> 
	* dans les sources Java.</li>
	* <li>java.io.File.</li>
	* <li>pathModelMainJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	* + /pathRelGroupIdString +/PATH_REL_MODEL.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/main/java/levy/daniel/application/
	* model</code>
	* </li>
	* </ul>
	 *
	 * @return fileModelMainJava : File.<br/>
	 */
	public static File getFileModelMainJava() {	
		return fileModelMainJava;
	} // Fin de getFileModelMainJava().____________________________________



	/**
	 * method getPathModelTestJavaString() :<br/>
	 * <ul>
	* <li>Getter du <b>path absolu du repertoire model</b> 
	* dans les tests Java.</li>
	* <li>path sous forme de <b>String</b>.</li>
	* <li>pathModelTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_MODEL.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model</code>
	* </li>
	* </ul>
	 *
	 * @return pathModelTestJavaString : String.<br/>
	 */
	public static String getPathModelTestJavaString() {	
		return pathModelTestJavaString;
	} // Fin de getPathModelTestJavaString().______________________________


	
	/**
	 * method getPathModelTestJava() :<br/>
	 * <ul>
	* <li>Getter du <b>path absolu du repertoire model</b> 
	* dans les tests Java.</li>
	* <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	* <li>pathModelTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_MODEL.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model</code>
	* </li>
	* </ul>
	 *
	 * @return pathModelTestJava : Path.<br/>
	 */
	public static Path getPathModelTestJava() {	
		return pathModelTestJava;
	} // Fin de getPathModelTestJava().____________________________________

	
		
	/**
	 * method getFileModelTestJava() :<br/>
	 * <ul>
	* <li>Getter du <b>File modélisant le repertoire model</b> 
	* dans les tests Java.</li>
	* <li>java.io.File.</li>
	* <li>pathModelTestJava = pathWorkspace 
	* + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	* + /pathRelGroupIdString +/PATH_REL_MODEL.</li>
	* <li>Singleton.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	* projet_users/src/test/java/levy/daniel/application/
	* model</code>
	* </li>
	* </ul>
	 *
	 * @return fileModelTestJava : File.<br/>
	 */
	public static File getFileModelTestJava() {	
		return fileModelTestJava;
	} // Fin de getFileModelTestJava().____________________________________



	/**
	 * method getPathRelVues() :<br/>
	 * <ul>
	 * <li>Getter du <b>path relatif du répertoire vues</b> 
	 * du projet par rapport 
	 * au path absolu du groupId
	 * (pathGroupIdMainJava pour les sources du main 
	 * et pathGroupIdTestJava pour les sources des tests).</li>
	 * <li>Singleton.</li>
	 * <li>sous forme de String.</li>
	 * <li>Par Exemple : <br/>
	 * <code>vues</code></li>
	 * </li>
	 * </ul>
	 *
	 * @return PATH_REL_VUES : String.<br/>
	 */
	public static String getPathRelVues() {	
		return PATH_REL_VUES;
	} // Fin de getPathRelVues().__________________________________________


	
	/**
	 * method getPathVuesMainJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire vues</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathVuesMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_VUES.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * vues</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathVuesMainJavaString : String.<br/>
	 */
	public static String getPathVuesMainJavaString() {	
		return pathVuesMainJavaString;
	} // Fin de getPathVuesMainJavaString()._______________________________


	
	/**
	 * method getPathVuesMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire vues</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathVuesMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_VUES.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * vues</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathVuesMainJava : Path.<br/>
	 */
	public static Path getPathVuesMainJava() {
		return pathVuesMainJava;
	} // Fin de getPathVuesMainJava()._____________________________________


		
	/**
	 * method getFileVuesMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le repertoire vues</b> 
	 * dans les sources Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathVuesMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_VUES.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * vues</code>
	 * </li>
	 * </ul>
	 *
	 * @return fileVuesMainJava : File.<br/>
	 */
	public static File getFileVuesMainJava() {	
		return fileVuesMainJava;
	} // Fin de getFileVuesMainJava()._____________________________________



	/**
	 * method getPathVuesTestJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire vues</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathVuesTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_VUES.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * vues</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathVuesTestJavaString : String.<br/>
	 */
	public static String getPathVuesTestJavaString() {	
		return pathVuesTestJavaString;
	} // Fin de getPathVuesTestJavaString()._______________________________


	
	/**
	 * method getPathVuesTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire vues</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathVuesTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_VUES.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * vues</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathVuesTestJava : Path.<br/>
	 */
	public static Path getPathVuesTestJava() {	
		return pathVuesTestJava;
	} // Fin de getPathVuesTestJava()._____________________________________


	
	/**
	 * method getFileVuesTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le repertoire vues</b> 
	 * dans les tests Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathVuesTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_VUES.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * vues</code>
	 * </li>
	 * </ul>
	 *
	 * @return fileVuesTestJava : File.<br/>
	 */
	public static File getFileVuesTestJava() {	
		return fileVuesTestJava;
	} // Fin de getFileVuesTestJava()._____________________________________


	
	/**
	 * method getPathRelDao() :<br/>
	 * <ul>
	 * <li>Getter du <b>path relatif du répertoire model/dao</b> 
	 * du projet par rapport 
	 * au path absolu du groupId
	 * (pathGroupIdMainJava pour les sources du main 
	 * et pathGroupIdTestJava pour les sources des tests).</li>
	 * <li>Singleton.</li>
	 * <li>sous forme de String.</li>
	 * <li>Par Exemple : <br/>
	 * <code>model/dao</code></li>
	 * </li>
	 * </ul>
	 *
	 * @return PATH_REL_DAO : String.<br/>
	 */
	public static String getPathRelDao() {	
		return PATH_REL_DAO;
	} // Fin de getPathRelDao().___________________________________________


	
	/**
	 * method getPathDaoMainJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire model/dao</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathDaoMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_DAO.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * model/dao</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathDaoMainJavaString : String.<br/>
	 */
	public static String getPathDaoMainJavaString() {	
		return pathDaoMainJavaString;
	} // Fin de getPathDaoMainJavaString().________________________________


	
	/**
	 * method getPathDaoMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire model/dao</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathDaoMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_DAO.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * model/dao</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathDaoMainJava : Path.<br/>
	 */
	public static Path getPathDaoMainJava() {
		return pathDaoMainJava;
	} // Fin de getPathDaoMainJava().______________________________________


		
	/**
	 * method getFileDaoMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le repertoire model/dao</b> 
	 * dans les sources Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathDaoMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_DAO.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * model/dao</code>
	 * </li>
	 * </ul>
	 *
	 * @return fileDaoMainJava : File.<br/>
	 */
	public static File getFileDaoMainJava() {	
		return fileDaoMainJava;
	} // Fin de getFileDaoMainJava().______________________________________



	/**
	 * method getPathDaoTestJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire model/dao</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathDaoTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_DAO.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * model/dao</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathDaoTestJavaString : String.<br/>
	 */
	public static String getPathDaoTestJavaString() {	
		return pathDaoTestJavaString;
	} // Fin de getPathDaoTestJavaString().________________________________


	
	/**
	 * method getPathDaoTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire model/dao</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathDaoTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_DAO.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * model/dao</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathDaoTestJava : Path.<br/>
	 */
	public static Path getPathDaoTestJava() {	
		return pathDaoTestJava;
	} // Fin de getPathDaoTestJava().______________________________________


	
	/**
	 * method getFileDaoTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le repertoire model/dao</b> 
	 * dans les tests Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathDaoTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_DAO.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * model/dao</code>
	 * </li>
	 * </ul>
	 *
	 * @return fileDaoTestJava : File.<br/>
	 */
	public static File getFileDaoTestJava() {	
		return fileDaoTestJava;
	} // Fin de getFileDaoTestJava().______________________________________


	
	/**
	 * method getPathRelMetier() :<br/>
	 * <ul>
	 * <li>Getter du <b>path relatif du répertoire model/metier</b> 
	 * du projet par rapport 
	 * au path absolu du groupId
	 * (pathGroupIdMainJava pour les sources du main 
	 * et pathGroupIdTestJava pour les sources des tests).</li>
	 * <li>Singleton.</li>
	 * <li>sous forme de String.</li>
	 * <li>Par Exemple : <br/>
	 * <code>model/metier</code></li>
	 * </li>
	 * </ul>
	 *
	 * @return PATH_REL_METIER : String.<br/>
	 */
	public static String getPathRelMetier() {	
		return PATH_REL_METIER;
	} // Fin de getPathRelMetier().________________________________________


	
	/**
	 * method getPathMetierMainJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire model/metier</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathMetierMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_METIER.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * model/metier</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathMetierMainJavaString : String.<br/>
	 */
	public static String getPathMetierMainJavaString() {	
		return pathMetierMainJavaString;
	} // Fin de getPathMetierMainJavaString()._____________________________


	
	/**
	 * method getPathMetierMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire model/metier</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathMetierMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_METIER.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * model/metier</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathMetierMainJava : Path.<br/>
	 */
	public static Path getPathMetierMainJava() {
		return pathMetierMainJava;
	} // Fin de getPathMetierMainJava().___________________________________


		
	/**
	 * method getFileMetierMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le repertoire model/metier</b> 
	 * dans les sources Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathMetierMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_METIER.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * model/metier</code>
	 * </li>
	 * </ul>
	 *
	 * @return fileMetierMainJava : File.<br/>
	 */
	public static File getFileMetierMainJava() {	
		return fileMetierMainJava;
	} // Fin de getFileMetierMainJava().___________________________________



	/**
	 * method getPathMetierTestJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire model/metier</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathMetierTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_METIER.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * model/metier</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathMetierTestJavaString : String.<br/>
	 */
	public static String getPathMetierTestJavaString() {	
		return pathMetierTestJavaString;
	} // Fin de getPathMetierTestJavaString()._____________________________


	
	/**
	 * method getPathMetierTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire model/metier</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathMetierTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_METIER.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * model/metier</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathMetierTestJava : Path.<br/>
	 */
	public static Path getPathMetierTestJava() {	
		return pathMetierTestJava;
	} // Fin de getPathMetierTestJava().___________________________________


	
	/**
	 * method getFileMetierTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le repertoire model/metier</b> 
	 * dans les tests Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathMetierTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_METIER.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * model/metier</code>
	 * </li>
	 * </ul>
	 *
	 * @return fileMetierTestJava : File.<br/>
	 */
	public static File getFileMetierTestJava() {	
		return fileMetierTestJava;
	} // Fin de getFileMetierTestJava().___________________________________


	
	/**
	 * method getPathRelServices() :<br/>
	 * <ul>
	 * <li>Getter du <b>path relatif du répertoire model/services</b> 
	 * du projet par rapport 
	 * au path absolu du groupId
	 * (pathGroupIdMainJava pour les sources du main 
	 * et pathGroupIdTestJava pour les sources des tests).</li>
	 * <li>Singleton.</li>
	 * <li>sous forme de String.</li>
	 * <li>Par Exemple : <br/>
	 * <code>model/services</code></li>
	 * </li>
	 * </ul>
	 *
	 * @return PATH_REL_SERVICES : String.<br/>
	 */
	public static String getPathRelServices() {	
		return PATH_REL_SERVICES;
	} // Fin de getPathRelServices().______________________________________


	
	/**
	 * method getPathServicesMainJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire model/services</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathServicesMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_SERVICES.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * model/services</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathServicesMainJavaString : String.<br/>
	 */
	public static String getPathServicesMainJavaString() {	
		return pathServicesMainJavaString;
	} // Fin de getPathServicesMainJavaString().___________________________


	
	/**
	 * method getPathServicesMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire model/services</b> 
	 * dans les sources Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathServicesMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_SERVICES.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * model/services</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathServicesMainJava : Path.<br/>
	 */
	public static Path getPathServicesMainJava() {
		return pathServicesMainJava;
	} // Fin de getPathServicesMainJava()._________________________________


		
	/**
	 * method getFileServicesMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le repertoire model/services</b> 
	 * dans les sources Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathServicesMainJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava 
	 * + /pathRelGroupIdString +/PATH_REL_SERVICES.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java/levy/daniel/application/
	 * model/services</code>
	 * </li>
	 * </ul>
	 *
	 * @return fileServicesMainJava : File.<br/>
	 */
	public static File getFileServicesMainJava() {	
		return fileServicesMainJava;
	} // Fin de getFileServicesMainJava()._________________________________



	/**
	 * method getPathServicesTestJavaString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire model/services</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>String</b>.</li>
	 * <li>pathServicesTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_SERVICES.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * model/services</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathServicesTestJavaString : String.<br/>
	 */
	public static String getPathServicesTestJavaString() {	
		return pathServicesTestJavaString;
	} // Fin de getPathServicesTestJavaString().___________________________


	
	/**
	 * method getPathServicesTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du repertoire model/services</b> 
	 * dans les tests Java.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>pathServicesTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_SERVICES.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * model/services</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathServicesTestJava : Path.<br/>
	 */
	public static Path getPathServicesTestJava() {	
		return pathServicesTestJava;
	} // Fin de getPathServicesTestJava()._________________________________


	
	/**
	 * method getFileServicesTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>File modélisant le repertoire model/services</b> 
	 * dans les tests Java.</li>
	 * <li>java.io.File.</li>
	 * <li>pathServicesTestJava = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava 
	 * + /pathRelGroupIdString +/PATH_REL_SERVICES.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java/levy/daniel/application/
	 * model/services</code>
	 * </li>
	 * </ul>
	 *
	 * @return fileServicesTestJava : File.<br/>
	 */
	public static File getFileServicesTestJava() {	
		return fileServicesTestJava;
	} // Fin de getFileServicesTestJava()._________________________________


	
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
		
		groupId = null;
		pathRelGroupId = null;
		pathGroupIdMainJavaString = null;
		pathGroupIdMainJava = null;
		fileGroupIdMainJava = null;
		pathGroupIdTestJavaString = null;
		pathGroupIdTestJava = null;
		fileGroupIdTestJava = null;
		
		pathAppTechnicMainJavaString = null;
		pathAppTechnicMainJava = null;
		fileAppTechnicMainJava = null;
		pathAppTechnicTestJavaString = null;
		pathAppTechnicTestJava = null;
		fileAppTechnicTestJava = null;
		
		pathControllersMainJavaString = null;
		pathControllersMainJava = null;
		fileControllersMainJava = null;
		pathControllersTestJavaString = null;
		pathControllersTestJava = null;
		fileControllersTestJava = null;
		
		pathModelMainJavaString = null;
		pathModelMainJava = null;
		fileModelMainJava = null;
		pathModelTestJavaString = null;
		pathModelTestJava = null;
		fileModelTestJava = null;
		
		pathDaoMainJavaString = null;
		pathDaoMainJava = null;
		fileDaoMainJava = null;
		pathDaoTestJavaString = null;
		pathDaoTestJava = null;
		fileDaoTestJava = null;
		
		pathMetierMainJavaString = null;
		pathMetierMainJava = null;
		fileMetierMainJava = null;
		pathMetierTestJavaString = null;
		pathMetierTestJava = null;
		fileMetierTestJava = null;
		
		pathServicesMainJavaString = null;
		pathServicesMainJava = null;
		fileServicesMainJava = null;
		pathServicesTestJavaString = null;
		pathServicesTestJava = null;
		fileServicesTestJava = null;

	} // Fin de reinitialiserAttributs().__________________________________

	

} // FIN DE LA CLASSE GestionnaireProjet.------------------------------------
