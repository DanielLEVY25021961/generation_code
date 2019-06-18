package levy.daniel.application.generationcode.generationfichierssource.impl;

import java.io.File;
import java.nio.file.Path;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierSimpleException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;
import levy.daniel.application.model.services.utilitaires.arboresceurprojet.ArboresceurProjetCible;

/**
 * CLASSE GenerateurDTO :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * nom simple (sans package et sans extension) d'un concept metier,
 * nom simple (sans package et sans extension) d'un concept métier,
 * Nom Concept Metier,<br/>
 * retirer extension, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 18 juin 2019
 *
 */
public class GenerateurDTO {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe GenerateurDTO".
	 */
	public static final String CLASSE_GENERATEUR_DTO 
		= "Classe GenerateurDTO";

	/**
	 * "méthode obtenirFichierSource(Path pCheminAbsoluProjetEclipseCiblePath
	 * , Path cheminRelatifFichierSourcePath".
	 */
	public static final String METHODE_OBTENIRFICHIERSOURCE 
		= "méthode obtenirFichierSource("
				+ "Path pCheminAbsoluProjetEclipseCiblePath"
				+ ", Path cheminRelatifFichierSourcePath";

	/**
	 * "methode calculerAttributsImposes()".
	 */
	public static final String METHODE_CALCULER_ATTRIBUTS_IMPOSES 
		= "methode calculerAttributsImposes()";
	
	/**
	 * "methode calculerNomSimplePackageConceptMetier(File pConceptMetier)".
	 */
	public static final String METHODE_CALCULER_NOM_SIMPLE_PACKAGE 
		= "methode calculerNomSimplePackageConceptMetier(File pConceptMetier)";
	
	/**
	 * "methode calculerNomConceptMetier(File pConceptMetier)".
	 */
	public static final String METHODE_CALCULER_NOM_CONCEPT_METIER 
		= "methode calculerNomConceptMetier(File pConceptMetier)";
	
	
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
	 * Pattern.compile(".");
	 */
	public static final Pattern PATTERN_SEP_POINT = Pattern.compile("\\.");
	
	/**
	 * 
	 * <b>path ABSOLU du projet CIBLE Eclipse</b> 
	 * dont on va générer le code.
	 * <ul>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users
	 * </code></li>
	 * </ul>
	 */
	private Path projetCiblePath;
	
	/**
	 * concept métier pour lequel on va générer les classes de DTO.
	 */
	private File conceptMetier;
	
	/**
	 * nom simple (sans package et sans impl) 
	 * du package d'un concept métier</b>.<br/>
	 * Par exemple : <br/>
	 * <b>televersement</b> pour 
	 * "D:/Donnees/eclipse/eclipseworkspace/traficweb_v1
	 * /src/main/java
	 * /levy/daniel/application/model
	 * /metier/televersement/impl/Televersement.java".<br/>
	 */
	private transient String nomSimplePackageConceptMetier;
	
	/**
	 * nom simple (sans package et sans extension) 
	 * d'un concept métier.<br/>
	 * Par exemple : <br/>
	 * <b>Televersement</b> pour 
	 * "D:/Donnees/eclipse/eclipseworkspace/traficweb_v1/
	 * src/main/java
	 * /levy/daniel/application/model
	 * /metier/televersement/impl/Televersement.java"<br/>
	 */
	private transient String nomConceptMetier;
	
	/**
	 * path ABSOLU du REPERTOIRE des abstractions éventuelles 
	 * (Interface et Classe Abstraite) du DTO.<br/>
	 */
	private transient Path repertoireAbstractionDTOPath;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(GenerateurDTO.class);

	// *************************METHODES************************************/
	
	
	
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
	

	
	
	
//	genererInterfaceEtClasseConcrete() {
//		
//	}


	
	/**
	 * 
	 * <ul>
	 * <li>délègue à un ArboresceurProjetCible le soin 
	 * de calculer les chemins du projet cible.</li>
	 * </ul>
	 * .<br/>
	 * - ne fait rien si <code>this.projetCiblePath</code> == null 
	 * || <code>this.conceptMetier</code> == null.<br/>
	 * - LOG.fatal et jette une Exception circonstanciée si le 
	 * projet cible Eclipse <code>this.projetCiblePath</code> 
	 * est null, vide, inexistant ou pas un répertoire.<br/>
	 * - LOG.fatal et jette une Exception circonstanciée 
	 * si <code>this.conceptMetier</code> est 
	 * null, vide, inexistant ou répertoire.<br/>
	 * <br/>
	 *
	 * @throws Exception : void :  .<br/>
	 */
	private void calculerAttributsImposes() throws Exception {
		
		/* ne fait rien si this.projetCiblePath == null 
		 * || this.conceptMetier == null. */
		if (this.projetCiblePath == null || this.conceptMetier == null) {
			return;
		}
		
		/* LOG.fatal et jette une Exception circonstanciée si le 
		 * projet cible Eclipse this.projetCiblePath 
		 * est null, vide, inexistant 
		 * ou pas un répertoire. */
		this.traiterMauvaisFichierProjetCibleEclipse(
				this.projetCiblePath.toFile()
					, METHODE_CALCULER_ATTRIBUTS_IMPOSES);
		
		/* LOG.fatal et jette une Exception circonstanciée si 
		 * this.conceptMetier est null, vide, inexistant 
		 * ou répertoire. */
		this.traiterMauvaisFichierSource(
				this.conceptMetier, METHODE_CALCULER_ATTRIBUTS_IMPOSES);
		
		/* délègue à un ArboresceurProjetCible le soin de 
		 * calculer les chemins du projet cible. */
		ArboresceurProjetCible.selectionnerProjetCible(this.projetCiblePath);
		
		final Path repertoireMetierDTOProjetCible 
			= ArboresceurProjetCible.getCoucheModelDTOMetierMainPath();
		
	} // Fin de calculerAttributsImposes().________________________________
	

	
	/**
	 * <b>retourne le nom simple (sans package et sans impl) 
	 * du package d'un concept métier</b>.<br/>
	 * Par exemple : <br/>
	 * <b>televersement</b> pour 
	 * "D:/Donnees/eclipse/eclipseworkspace/traficweb_v1
	 * /src/main/java
	 * /levy/daniel/application/model
	 * /metier/televersement/impl/Televersement.java".<br/>
	 * <br/>
	 * - LOG.fatal et jette une Exception circonstanciée 
	 * si pConceptMetier est null, vide, inexistant ou répertoire.<br/>
	 * <br/>
	 *
	 * @param pConceptMetier : File : 
	 * concept métier dans un projet ECLIPSE externe.
	 * 
	 * @return : String : 
	 * nom simple (sans package et sans impl) 
	 * du package du concept métier.<br/>
	 * 
	 * @throws Exception 
	 */
	public final String calculerNomSimplePackageConceptMetier(
			final File pConceptMetier) throws Exception {
		
		/* LOG.fatal et jette une Exception circonstanciée si 
		 * pConceptMetier est null, vide, inexistant 
		 * ou répertoire. */
		this.traiterMauvaisFichierSource(
				pConceptMetier, METHODE_CALCULER_NOM_SIMPLE_PACKAGE);
		
		final Path packageConceptMetierPath = pConceptMetier.toPath();
				
		final Path packageConceptMetierPathParent 
			= packageConceptMetierPath.getParent();
		
		String resultat = null;
		
		if (packageConceptMetierPathParent != null) {
			
			if (packageConceptMetierPathParent.endsWith("impl")) {
				
				final Path packageConceptMetierPathGrandParent 
					= packageConceptMetierPathParent.getParent();
				
				if (packageConceptMetierPathGrandParent != null) {
					
					resultat = this.fournirDernierPath(
							packageConceptMetierPathGrandParent).toString();
					
				}
				
			} else {
				
				resultat = this.fournirDernierPath(
						packageConceptMetierPathParent).toString();
			}
		}
		
		return resultat;
		
	} // Fin de calculerNomSimplePackageConceptMetier(...).________________
	

	
	/**
	 * <b>retourne le nom simple (sans package et sans extension) 
	 * d'un concept métier</b>.<br/>
	 * Par exemple : <br/>
	 * <b>Televersement</b> pour 
	 * "D:/Donnees/eclipse/eclipseworkspace/traficweb_v1
	 * /src/main/java
	 * /levy/daniel/application/model
	 * /metier/televersement/impl/Televersement.java".<br/>
	 * <br/>
	 * - LOG.fatal et jette une Exception circonstanciée 
	 * si pConceptMetier est null, vide, inexistant ou répertoire.<br/>
	 * <br/>
	 *
	 * @param pConceptMetier : File : 
	 * concept métier dans un projet ECLIPSE externe.
	 * 
	 * @return : String : nom simple du concept métier.<br/>
	 * 
	 * @throws Exception 
	 */
	public final String calculerNomConceptMetier(
			final File pConceptMetier) throws Exception {
		
		/* LOG.fatal et jette une Exception circonstanciée si 
		 * pConceptMetier est null, vide, inexistant 
		 * ou répertoire. */
		this.traiterMauvaisFichierSource(
				pConceptMetier, METHODE_CALCULER_NOM_CONCEPT_METIER);
		
		final Path packageConceptMetierPath = pConceptMetier.toPath();
		
		final Path packageSimpleConceptMetierPath 
			= packageConceptMetierPath.getFileName();
		
		String resultat = null;
		String resultatAvecExtension = null;
		
		if (packageSimpleConceptMetierPath != null) {
			resultatAvecExtension = packageSimpleConceptMetierPath.toString();
			resultat = this.retirerExtension(resultatAvecExtension);
		}
		
		return resultat;
		
	} // Fin de calculerNomConceptMetier(...)._____________________________


	
	/**
	 * retourne le dernier path (path le plus lointain) de pPath.<br/>
	 * Par exemple : 
	 * <br/>
	 * - retourne null si pPath == null.<br/>
	 * <br/>
	 *
	 * @param pPath
	 * @return : Path :  .<br/>
	 */
	private Path fournirDernierPath(
			final Path pPath) {
		
		/* retourne null si pPath == null. */
		if (pPath == null) {
			return null;
		}
		
		final int nombrePaths = pPath.getNameCount();
		
		final Path resultat = pPath.getName(nombrePaths - 1);
		
		return resultat;
		
	} // Fin de fournirDernierPath(...).___________________________________
	

	
	/**
	 * retire l'extension d'une String avec des séparateurs POINT "."<br/>
	 * Par exemple : <br/>
	 * retourne <b>Televersement</b> pour "Televersement.java".<br/>
	 * <br/>
	 * - retourne null si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.
	 * 
	 * @return : String : chaine en entrée moins l'extension.<br/>
	 */
	private String retirerExtension(
			final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		final String[] tokens = PATTERN_SEP_POINT.split(pString);
		
		final int nombreTokens = tokens.length;
		
		String resultat = null;
		
		if (nombreTokens < 2) {
			resultat = pString;
		} else {
			resultat = tokens[nombreTokens - 2];
		}
		
		return resultat;
		
	} // Fin de retirerExtension(...)._____________________________________
	
	
	
	/**
	 * Getter du <b>path ABSOLU du projet CIBLE Eclipse</b> 
	 * dont on va générer le code.
	 * <ul>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users
	 * </code></li>
	 * </ul>
	 *
	 * @return this.projetCiblePath : Path.<br/>
	 */
	public final Path getProjetCiblePath() {
		return this.projetCiblePath;
	} // Fin de getProjetCiblePath().______________________________________


	
	/**
	* Setter du <b>path ABSOLU du projet CIBLE Eclipse</b> 
	* dont on va générer le code.
	* <ul>
	* <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	* <li>Par exemple : <br/>
	* <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users
	* </code></li>
	* </ul>
	*
	* @param pProjetCiblePath : Path : 
	* valeur à passer à this.projetCiblePath.<br/>
	*/
	public final void setProjetCiblePath(
			final Path pProjetCiblePath) {
		this.projetCiblePath = pProjetCiblePath;
	} // Fin de setProjetCiblePath(...).___________________________________


	
	/**
	 * Getter du concept métier pour lequel on va générer les classes de DTO.
	 *
	 * @return this.conceptMetier : File.<br/>
	 */
	public final File getConceptMetier() {
		return this.conceptMetier;
	} // Fin de getConceptMetier().________________________________________


	
	/**
	* Setter du concept métier pour lequel on va générer les classes de DTO.
	*
	* @param pConceptMetier : File : 
	* valeur à passer à this.conceptMetier.<br/>
	*/
	public final void setConceptMetier(
			final File pConceptMetier) {
		this.conceptMetier = pConceptMetier;
	} // Fin de setConceptMetier(...)._____________________________________
	
	
	
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
			= CLASSE_GENERATEUR_DTO
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
			= CLASSE_GENERATEUR_DTO 
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
			= CLASSE_GENERATEUR_DTO 
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
			= CLASSE_GENERATEUR_DTO 
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
			= CLASSE_GENERATEUR_DTO 
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
	
	
		
} // FIN DE LA CLASSE GenerateurDTO.-----------------------------------------
