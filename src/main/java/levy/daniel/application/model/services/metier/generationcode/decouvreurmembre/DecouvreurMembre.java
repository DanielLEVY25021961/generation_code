package levy.daniel.application.model.services.metier.generationcode.decouvreurmembre;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierSimpleException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;

/**
 * CLASSE DecouvreurMembre :<br/>
 * <p>
 * Cette classe est chargée de <b>découvrir les membres</b> 
 * (attributs et méthodes) d'une 
 * <b>classe déjà existante dans un autre projet ECLIPSE</b>.
 * </p>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * afficherListString, afficherList<String>, afficher Liste String, <br/>
 * afficherListeString, afficher liste String<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 14 juin 2019
 *
 */
public class DecouvreurMembre {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe DecouvreurMembre".
	 */
	public static final String CLASSE_DECOUVREURMEMBRE 
		= "Classe DecouvreurMembre";

	/**
	 * "méthode obtenirFichierSource(Path pCheminAbsoluProjetEclipseCiblePath
	 * , Path cheminRelatifFichierSourcePath".
	 */
	public static final String METHODE_OBTENIRFICHIERSOURCE 
		= "méthode obtenirFichierSource("
				+ "Path pCheminAbsoluProjetEclipseCiblePath"
				+ ", Path cheminRelatifFichierSourcePath";
	
	/**
	 * "méthode lireFichierSource(File pFichierSource)".
	 */
	public static final String METHODE_LIREFICHIERSOURCE 
		= "méthode lireFichierSource(File pFichierSource)";

	
	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/
		
	/**
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';
	
	/**
	 * Séparateur pour les CSV ";".<br/>
	 */
	public static final String SEP_PV = ";";
	
	/**
	 * '.'.<br/>
	 */
	public static final char POINT = '.';
	
	/**
	 * " - ".
	 */
	public static final String MOINS_ESPACE = " - ";
	
	/**
	 * ", ".<br/>
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	/**
	 * "_".<br/>
	 */
	public static final String UNDERSCORE = "_";
	
	/**
	 * '/'.<br/>
	 */
	public static final char SLASH = '/';
		
	/**
	 * '\'.<br/>
	 * ATTENTION : antislash est un caractère spécial 
	 * qui doit être échappé en Java ('\\')<br/>
	 */
	public static final char ANTISLASH = '\\';

	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/	
	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");
		
	/**
	 * <b>motif REGEX pour détecter les lignes de déclaration des membres</b> 
	 * (attributs et méthodes) d'une classe.<br/>
	 * "^(\\s*)((public?|protected?|private?|)\\s*(static?|)\\s*(final?|)\\s*(transient?|)\\s+(\\S+)\\s+([a-zA-Z0-9_]+)\\s*(.*));$".
	 * <ol>
	 * <li><b>^(\\s*)</b> signifie 
	 * <i>"commence par 0, 1 ou plusieurs espaces</i>.</li>
	 * <li><b>((public?|protected?|private?|)</b> 
	 * signifie <i>"public, ou protected, ou private, ou rien (|)"</i></li>
	 * <li><b>\\s*</b> signifie <i>"0, 1, ou plusieurs espaces"</i></li>
	 * <li><b>(static?|)</b> signifie <i>"static une seule fois au pas (|)"</i></li>
	 * <li><b>\\s*</b> signifie <i>"0, 1, ou plusieurs espaces"</i></li>
	 * <li><b>(final?|)</b> signifie <i>"final une seule fois au pas (|)"</i></li>
	 * <li><b>\\s*</b> signifie <i>"0, 1, ou plusieurs espaces"</i></li>
	 * <li><b>(transient?|)</b> signifie <i>"transient une seule fois au pas (|)"</i></li>
	 * <li><b>\\s+</b> signifie <i>"au moins un espace"</i></li>
	 * <li><b>(\\S+)</b> signifie <i>"au moins 1 caractère non blanc"</i></li>
	 * <li><b>\\s+</b> signifie <i>"au moins un espace"</i></li>
	 * <li><b>([a-zA-Z0-9_]+)</b> signifie <i>"au moins un caractère 
	 * parmi minuscules, majuscules, chiffre ou UNDERSCORE"</i></li>
	 * <li><b>\\s*</b> signifie <i>"0, 1, ou plusieurs espaces"</i></li>
	 * <li><b>(.*)</b> signifie <i>"0, 1, ou plusieurs caractères QUELCONQUES"</i></li>
	 * <li><b>(.*)</b> signifie <i>"termine par un point-virgule"</i></li>
	 * </ol>
	 */
	public static final String MOTIF_REGEX_MEMBRE 
		= "^(\\s*)((public?|protected?|private?|)\\s*(static?|)\\s*(final?|)\\s*(transient?|)\\s+(\\S+)\\s+([a-zA-Z0-9_]+)\\s*(.*));$";
	
	/**
	 * "null".<br/>
	 */
	public static final String NULL = "null";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(DecouvreurMembre.class);

	// *************************METHODES************************************/

	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public DecouvreurMembre() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * <b>retourne le File correspondant à un fichier source situé 
	 * à pCheminRelatifFichierSourcePath dans un projet cible ECLIPSE externe 
	 * pCheminAbsoluProjetEclipseCiblePath</b>.<br/>
	 * Par exemple :<br/>
	 * <code><b>obtenirFichierSource(Paths.get(
	 * "D:/Donnees/eclipse/eclipseworkspace/traficweb_v1")
	 * , Paths.get("src/main/java/levy/daniel/application/
	 * model/metier/televersement/impl/Televersement.java"))</b></code>
	 * retourne le File situé à
	 * <code>D:/Donnees/eclipse/eclipseworkspace/traficweb_v1/
	 * src/main/java/
	 * levy/daniel/application/model/metier/televersement/impl/
	 * Televersement.java</code>
	 * <br/>
	 * <br/>
	 * - retourne null si pCheminAbsoluProjetEclipseCiblePath == null.<br/>
	 * - LOG.fatal et jette une Exception circonstanciée si le 
	 * projet cible Eclipse est null, vide, inexistant ou pas un répertoire.<br/>
	 * - retourne null si le fichier source cible n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pCheminAbsoluProjetEclipseCiblePath : Path : 
	 * Path ABSOLU du projet cible ECLIPSE
	 * @param pCheminRelatifFichierSourcePath : Path : 
	 * Path RELATIF du fichier source par rapport au projet cible Eclipse
	 * 
	 * @return File : le fichier source situé 
	 * dans le projet cible ECLIPSE externe.<br/>
	 * 
	 * @throws Exception
	 */
	public final File obtenirFichierSource(
			final Path pCheminAbsoluProjetEclipseCiblePath
				, final Path pCheminRelatifFichierSourcePath) 
													throws Exception {
		
		/* retourne null si pCheminAbsoluProjetEclipseCiblePath == null. */
		if (pCheminAbsoluProjetEclipseCiblePath == null) {
			return null;
		}
		
		final File projetEclipseCible 
			= pCheminAbsoluProjetEclipseCiblePath.toFile();
		
		/* LOG.fatal et jette une Exception circonstanciée si le 
		 * projet cible Eclipse est null, vide, inexistant 
		 * ou pas un répertoire. */
		this.traiterMauvaisFichierProjetCibleEclipse(
				projetEclipseCible
				, METHODE_OBTENIRFICHIERSOURCE);
		
		final Path pathAbsoluFichierSourceCible 
			= pCheminAbsoluProjetEclipseCiblePath
					.resolve(pCheminRelatifFichierSourcePath);
		
		final File fichierSourceCible = pathAbsoluFichierSourceCible.toFile();
		
		/* retourne null si le fichier source cible n'existe pas. */
		if (!fichierSourceCible.exists()) {
			return null;
		}
		
		return fichierSourceCible;
		
	} // Fin de obtenirFichierSource(...)._________________________________
	
	
	
	/**
	 * <b>lit en UTF-8 le contenu d'un fichier source pFichierSource 
	 * et le retourne sous forme de List&lt;String&gt;</b>.<br/>
	 * <br/>
	 * - LOG.fatal et jette une Exception circonstanciée 
	 * si pFichierSource est null, vide, inexistant ou répertoire.<br/>
	 * <br/>
	 *
	 * @param pFichierSource : File : fichier source à lire.
	 * 
	 * @return List&lt;String&gt; : 
	 * contenu du fichier source sous forme de de liste de String.
	 * 
	 * @throws Exception
	 */
	public final List<String> lireFichierSource(final File pFichierSource) 
														throws Exception {
		
		/* LOG.fatal et jette une Exception circonstanciée si 
		 * pFichierSource est null, vide, inexistant 
		 * ou répertoire. */
		this.traiterMauvaisFichierSource(
				pFichierSource, METHODE_LIREFICHIERSOURCE);
		
		final List<String> listeLignes 
			= Files.readAllLines(
					pFichierSource.toPath(), StandardCharsets.UTF_8);
		
		return listeLignes;
		
	} // Fin de lireFichierSource(...).____________________________________
	
	
	
	/**
	 * Fournit une String pour l'affichage 
	 * d'une List&lt;String&gt;.<br/>
	 * <br/>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;String&gt;
	 * 
	 * @return : String.<br/>
	 */
	public final String afficherListeString(
			final List<String> pList) {

		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}

		final StringBuffer stb = new StringBuffer();

		for (final String ligne : pList) {
			stb.append(ligne);
			stb.append(NEWLINE);
		}

		return stb.toString();

	} // Fin de afficherListeString(...).__________________________________
		
	
	
	/**
	 * <ul>
	 * <li>LOG.fatal et jette une FichierNullException si pFile est null.</li>
	 * <li>LOG.fatal et jette une FichierVideException si pFile est vide.</li>
	 * <li>LOG.fatal et jette une FichierInexistantException 
	 * si pFile est inexistant.</li>
	 * <li>LOG.fatal et jette une FichierSimpleException 
	 * si pFile est un fichier simple (pas un répertoire).</li>
	 * </ul>
	 *
	 * @param pFile : File.
	 * @param pMethode : String : nom de la méthode appelante.
	 * 
	 * @throws Exception
	 */
	private void traiterMauvaisFichierProjetCibleEclipse(
			final File pFile, final String pMethode) 
											throws Exception {
		
		this.traiterFichierNull(pFile, pMethode);
		this.traiterFichierVide(pFile, pMethode);
		this.traiterFichierInexistant(pFile, pMethode);
		this.traiterFichierSimple(pFile, pMethode);
		
	} // Fin de traiterMauvaisFichierProjetCibleEclipse(...).______________

	
	
	/**
	 * <ul>
	 * <li>LOG.fatal et jette une FichierNullException si pFile est null.</li>
	 * <li>LOG.fatal et jette une FichierVideException si pFile est vide.</li>
	 * <li>LOG.fatal et jette une FichierInexistantException 
	 * si pFile est inexistant.</li>
	 * <li>LOG.fatal et jette une FichierPasNormalException 
	 * si pFile est un répertoire (pas un fichier simple).</li>
	 * </ul>
	 *
	 * @param pFile : File.
	 * @param pMethode : String : nom de la méthode appelante.
	 * 
	 * @throws Exception
	 */
	private void traiterMauvaisFichierSource(
			final File pFile, final String pMethode) 
											throws Exception {
		
		this.traiterFichierNull(pFile, pMethode);
		this.traiterFichierVide(pFile, pMethode);
		this.traiterFichierInexistant(pFile, pMethode);
		this.traiterFichierPasNormal(pFile, pMethode);
		
	} // Fin de traiterMauvaisFichierSource(...).__________________________
	
	
	
	/**
	 * LOG.fatal et jette une FichierNullException si pFile est null.
	 *
	 * @param pFile : File.
	 * @param pMethode : String : nom de la méthode appelante.
	 * 
	 * @throws FichierNullException
	 */
	private void traiterFichierNull(
			final File pFile, final String pMethode) 
								throws FichierNullException {
		
		if (pFile == null) {
			
			final String message 
				= CLASSE_DECOUVREURMEMBRE 
					+ MOINS_ESPACE 
					+ pMethode 
					+ MOINS_ESPACE 
					+ "le File passé en paramètre est null";
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			throw new FichierNullException(message);
		}
		
	} // Fin de traiterFichierNull(...).___________________________________
	

	
	/**
	 * LOG.fatal et jette une FichierVideException si pFile est vide.
	 *
	 * @param pFile : File.
	 * @param pMethode : String : nom de la méthode appelante.
	 * 
	 * @throws FichierVideException
	 */
	private void traiterFichierVide(
			final File pFile, final String pMethode) 
									throws FichierVideException {
		
		if (pFile.length() == 0) {
			
			final String message 
				= CLASSE_DECOUVREURMEMBRE 
						+ MOINS_ESPACE 
						+ pMethode 
						+ MOINS_ESPACE 
						+"le File passé en paramètre est vide : " 
						+ pFile.getAbsolutePath();
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			throw new FichierVideException(message);
		}
				
	} // Fin de traiterFichierVide(...).___________________________________
	
	

	/**
	 * LOG.fatal et jette une FichierInexistantException 
	 * si pFile est inexistant.
	 *
	 * @param pFile : File.
	 * @param pMethode : String : nom de la méthode appelante.
	 * 
	 * @throws FichierInexistantException
	 */
	private void traiterFichierInexistant(
			final File pFile, final String pMethode) 
						throws FichierInexistantException {
		
		if (!pFile.exists()) {
			
			final String message 
				= CLASSE_DECOUVREURMEMBRE 
						+ MOINS_ESPACE 
						+ pMethode 
						+ MOINS_ESPACE 
						+"le File passé en paramètre est inexistant : " 
						+ pFile.getAbsolutePath();
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			throw new FichierInexistantException(message);
		}			
		
	} // Fin de traiterFichierInexistant(...)._____________________________
	
	

	/**
	 * LOG.fatal et jette une FichierSimpleException 
	 * si pFile est un fichier simple (pas un répertoire).
	 *
	 * @param pFile : File.
	 * @param pMethode : String : nom de la méthode appelante.
	 * 
	 * @throws FichierSimpleException
	 */
	private void traiterFichierSimple(
			final File pFile, final String pMethode) 
							throws FichierSimpleException {
		
		if (!pFile.exists()) {
			
			final String message 
				= CLASSE_DECOUVREURMEMBRE 
				+ MOINS_ESPACE 
				+ pMethode 
				+ MOINS_ESPACE 
				+"le File passé en paramètre est simple (pas un répertoire) : " 
				+ pFile.getAbsolutePath();
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			throw new FichierSimpleException(message);
		}					
		
	} // Fin de traiterFichierSimple(...)._________________________________
	
	

	/**
	 * LOG.fatal et jette une FichierPasNormalException 
	 * si pFile est un répertoire (pas un fichier simple).
	 *
	 * @param pFile : File.
	 * @param pMethode : String : nom de la méthode appelante.
	 * 
	 * @throws FichierPasNormalException
	 */
	private void traiterFichierPasNormal(
			final File pFile, final String pMethode) 
							throws FichierPasNormalException {
		
		if (!pFile.exists()) {
			
			final String message 
				= CLASSE_DECOUVREURMEMBRE 
				+ MOINS_ESPACE 
				+ pMethode 
				+ MOINS_ESPACE 
				+"le File passé en paramètre est un répertoire (pas un fichier simple) : " 
				+ pFile.getAbsolutePath();
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			throw new FichierPasNormalException(message);
		}					
		
	} // Fin de traiterFichierPasNormal(...).______________________________
	
	
	
} // FIN DE LA CLASSE DecouvreurMembre.--------------------------------------
