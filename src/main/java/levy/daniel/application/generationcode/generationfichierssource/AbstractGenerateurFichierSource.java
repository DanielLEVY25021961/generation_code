package levy.daniel.application.generationcode.generationfichierssource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierSimpleException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;
import levy.daniel.application.generationcode.generationfichierssource.decouvreurmembre.DecouvreurMembre;
import levy.daniel.application.generationcode.generationfichierssource.decouvreurmembre.IEncapsulationDeclarationMembre;

/**
 * CLASSE AbstractGenerateurFichierSource :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * nom simple (sans package et sans extension) d'un concept metier,<br/>
 * nom simple (sans package et sans extension) d'un concept métier,<br/>
 * Nom Concept Metier, trouver nom simple, <br/>
 * retirer extension, <br/>
 * Créer fichier sur disque, creer fichier sur disque,<br/>
 * creer fichier et arborescence, <br/>
 * transformer path en chemin Java, transformer Path en chemin java,<br/>
 * transforme points chemin Java en slashes, transformer point en slash,<br/>
 * transformer chemin java en Path avec des slashes,<br/>
 * fournir chemin java à partir de path Path,<br/>
 * transforme Path avec slashes ou antislashes en package Java 
 * <br/>avec des points, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 18 juin 2019
 *
 */
public abstract class AbstractGenerateurFichierSource {

	// ************************ATTRIBUTS************************************/

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
	 * "."
	 */
	public static final String POINT = ".";
	
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
	 * "/".
	 */
	public static final String SLASH = "/";

	/**
	 * '/'.<br/>
	 */
	public static final char SLASH_CHAR = '/';
	
	/**
	 * "\\".
	 */
	public static final String ANTISLASH = "\\";
		
	/**
	 * '\'.<br/>
	 * ATTENTION : antislash est un caractère spécial 
	 * qui doit être échappé en Java ('\\')<br/>
	 */
	public static final char ANTISLASH_CHAR = '\\';

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
	 * "null".
	 */
	public static final String NULL = "null";
	
	/**
	 * "impl".
	 */
	public static final String IMPL = "impl";

	/**
	 * new DecouvreurMembre();.
	 */
	private transient DecouvreurMembre decouvreur = new DecouvreurMembre();
	
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
	protected Path projetCiblePath;
	
	/**
	 * concept métier pour lequel on va générer les classes 
	 * DESIGN PATTERN (DTO, DAO, ...).<br/>
	 * Par exemple : <br/>
	 * "D:/Donnees/eclipse/eclipseworkspace/traficweb_v1
	 * /src/main/java
	 * /levy/daniel/application/model
	 * /metier/televersement/impl/Televersement.java".<br/>
	 */
	protected File conceptMetier;
	
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
	protected transient String nomSimplePackageConceptMetier;
	
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
	protected transient String nomConceptMetier;

	/**
	 * Map&lt;Integer,IEncapsulationDeclarationMembre&gt; 
	 * des attributs simplement private du concept métier avec :
	 * <ul>
	 * <li>Integer : le numéro d'ordre déclaratif de l'attribut dans la classe</li>
	 * <li>IEncapsulationDeclarationMembre : encapsulation décrivant l'attribut</li>
	 * </ul>
	 */
	protected transient Map<Integer, IEncapsulationDeclarationMembre> attributsPrivateMap;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(AbstractGenerateurFichierSource.class);

	// *************************METHODES************************************/
	

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 * 
	 * @throws Exception 
	 */
	public AbstractGenerateurFichierSource() throws Exception {
		
		this(null, null);
		
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 * <ul>
	 * calcule les attributs imposés :
	 * <li>calcule <code><b>this.nomSimplePackageConceptMetier</b></code>.</li>
	 * <li>calcule <code><b>this.nomConceptMetier</b></code>.</li>
	 * <li>calcule <code><b>this.attributsPrivateMap</b></code>.</li>
	 * <li>calcule les attributs spécifiques au DESIGN PATTERN 
	 * (DTO, DAO, ...) grâce à un HOOK.</li>
	 * </ul>
	 * 
	 * @param pProjetCiblePath : Path : 
	 * path ABSOLU du projet CIBLE Eclipse dans lequel on va générer le code
	 * @param pConceptMetier : File : 
	 * concept métier pour lequel on va générer les classes 
	 * DESIGN PATTERN (DTO, DAO, ...).
	 * 
	 * @throws Exception 
	 */
	public AbstractGenerateurFichierSource(
			final Path pProjetCiblePath
				, final File pConceptMetier) throws Exception {
		
		super();
		
		this.projetCiblePath = pProjetCiblePath;
		this.conceptMetier = pConceptMetier;
		
		/* calcule les attributs imposés. */
		if (this.projetCiblePath != null && this.conceptMetier != null) {
			this.calculerAttributsImposes();
		}
		
	} // Fin du CONSTRUCTEUR COMPLET.______________________________________


	
	 /**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append("GenerateurDTO [");
		
		stb.append("projetCiblePath=");
		if (this.projetCiblePath != null) {			
			stb.append(this.projetCiblePath.toString());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);
		
		stb.append("conceptMetier=");
		if (this.conceptMetier != null) {			
			stb.append(this.conceptMetier.getAbsolutePath());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("nomSimplePackageConceptMetier=");
		if (this.nomSimplePackageConceptMetier != null) {			
			stb.append(this.nomSimplePackageConceptMetier);
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);
		
		stb.append("nomConceptMetier=");
		if (this.nomConceptMetier != null) {			
			stb.append(this.nomConceptMetier);
		} else {
			stb.append(NULL);
		}
		
		stb.append(']');
		
		return stb.toString();
		
	} // Fin de toString().________________________________________________


	
	/**
	 * <b>Crée sur disque le File pFile <i>vide</i></b> 
	 * si il n'existe pas déjà.<br/>
	 * <ul>
	 * <li>retourne le fichier vide créé.</li>
	 * <li>crée sur disque l'<b>arborescence au dessus de pFile</b> 
	 * si elle n'existe pas.</li>
	 * <li>crée sur disque le fichier vide pFile 
	 * si il n'existe pas.</li>
	 * </ul>
	 * - retourne null si pFile == null.<br/>
	 * - retourne null si pFile existe.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier inexistant sur disque à créer.
	 * 
	 * @return File : le fichier VIDE créé sur disque.<br/>
	 * 
	 * @throws IOException
	 */
	protected final File creerFichierVideEtArborescenceSurDisque(
							final File pFile) throws IOException {
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		/* retourne null si pFile existe. */
		if (pFile.exists()) {
			return null;
		}
		
		final Path pFilePath = pFile.toPath();
		final Path pFileParentPath = pFilePath.getParent();
		
		/* crée l'arborescence au dessus de pFile si elle n'existe pas. */
		if (pFileParentPath != null) {
			
			if (!pFileParentPath.toFile().exists()) {
				Files.createDirectories(pFileParentPath);
			}
			
			/* crée le fichier VIDE pFile si il n'existe pas. */
			Files.createFile(pFilePath);

		}
		
		return pFile;
		
	} // Fin de creerFichierVideEtArborescenceSurDisque(...).______________


	
	/**
	 * calcule tous les attributs dépendants du projet cible ECLIPSE 
	 * et du concept métier.
	 * <ul>
	 * <li>calcule <code><b>this.nomSimplePackageConceptMetier</b></code>.</li>
	 * <li>calcule <code><b>this.nomConceptMetier</b></code>.</li>
	 * <li>calcule <code><b>this.attributsPrivateMap</b></code>.</li>
	 * <li>calcule les attributs spécifiques au DESIGN PATTERN 
	 * (DTO, DAO, ...) grâce à un HOOK.</li>
	 * </ul>
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
				
		/* calcule this.nomSimplePackageConceptMetier. */		
		this.nomSimplePackageConceptMetier 
			= this.calculerNomSimplePackageConceptMetier(this.conceptMetier);
		
		/* calcule this.nomConceptMetier. */
		this.nomConceptMetier 
			= this.calculerNomConceptMetier(this.conceptMetier);
		
		/* calcule this.attributsPrivateMap. */
		this.attributsPrivateMap 
			= this.decouvreur.decouvrirAttributsPrivate(this.conceptMetier);
		
		/* calcule les attributs spécifiques au DESIGN PATTERN 
		 * (DTO, DAO, ...) grâce à un HOOK. */
		this.calculerAttributsImposesHook();
		
	} // Fin de calculerAttributsImposes().________________________________
	

	
	/**
	 * calcule les attributs spécifiques pour 
	 * chaque GenerateurRapide de DESIGN PATTERN (DTO, DAO, ...).<br/>
	 * A IMPLEMENTER DANS CHAQUE GENERATEUR CONCRET.<br/>
	 * <ul>
	 * <li>délègue à un ArboresceurProjetCible le soin 
	 * de calculer les chemins du projet cible.</li>
	 * </ul>
	 * <br/>
	 *
	 * @throws Exception
	 */
	protected abstract void calculerAttributsImposesHook() throws Exception;
	
	
	
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
	 * fournit une String pour l'affichage d'une 
	 * Map&lt;Integer,IEncapsulationDeclarationMembre&gt;.<br/>
	 * <br/>
	 * - retourne null si pMap == null.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;Integer,IEncapsulationDeclarationMembre&gt;
	 * 
	 * @return : String.<br/>
	 */
	public final String afficherMapEncapsulationDeclarationMembre(
			final Map<Integer, IEncapsulationDeclarationMembre> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		final Set<Entry<Integer, IEncapsulationDeclarationMembre>> entrySet 
			= pMap.entrySet();
		
		final Iterator<Entry<Integer, IEncapsulationDeclarationMembre>> ite 
			= entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<Integer, IEncapsulationDeclarationMembre> entry 
				= ite.next();
			
			final Integer numOrdre = entry.getKey();
			final IEncapsulationDeclarationMembre membre = entry.getValue();
			
			final String ligne 
				= String.format(
						Locale.getDefault()
						, "champ numéro %1$-10d : %2$s"
						, numOrdre, membre.toString());
			
			stb.append(ligne);
			stb.append(NEWLINE);
		}
		
		return stb.toString();
		
	} // Fin de afficherMapEncapsulationDeclarationMembre(...).____________


	
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
	protected final void traiterMauvaisFichierProjetCibleEclipse(
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
	protected final void traiterMauvaisFichierSource(
			final File pFile, final String pMethode) 
											throws Exception {
		
		this.traiterFichierNull(pFile, pMethode);
		this.traiterFichierVide(pFile, pMethode);
		this.traiterFichierInexistant(pFile, pMethode);
		this.traiterFichierPasNormal(pFile, pMethode);
		
	} // Fin de traiterMauvaisFichierSource(...).__________________________
	

	
	/**
	 * transforme un Path avec des slashes ou antislashes
	 * en package Java avec des points.<br/>
	 * Par exemple :<br/>
	 * <code><b>model/dto/metier/televersement/impl</b></code> 
	 * est transformé en 
	 * <code><b>model.dto.metier.televersement.impl</b></code>
	 * <br/>
	 *
	 * @param pPath : java.nio.file.Path
	 * @return : String : chemin Java avec des points.<br/>
	 */
	protected final String transformerPathEnCheminJava(
			final Path pPath) {
		
		/* retourne null si pPath == null. */
		if (pPath == null) {
			return null;
		}
		
		final String pathString = pPath.toString();
		String resultat = null;
		
		if (StringUtils.contains(pathString, ANTISLASH)) {
			
			resultat = StringUtils.replace(pathString, ANTISLASH, POINT);
			
		} else if (StringUtils.contains(pathString, SLASH)) {
			
			resultat = StringUtils.replace(pathString, SLASH, POINT);
			
		} else {
			
			resultat = pathString;
			
		}
				
		return resultat;
		
	} // Fin de transformerPathEnCheminJava(...).__________________________
	
	
	
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
				= this.fournirClassePourMessage()
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
				= this.fournirClassePourMessage() 
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
				= this.fournirClassePourMessage() 
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
				= this.fournirClassePourMessage() 
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
				= this.fournirClassePourMessage() 
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


	
	/**
	 * retourne une String affichant le nom de la 
	 * classe pour le traitement des Exceptions.<br/>
	 * Par exemple : <br/>
	 * "Classe GenerateurDTO".<br/>
	 *
	 * @return : String : "Classe ClasseConcrete".<br/>
	 */
	protected abstract String fournirClassePourMessage();
	
	
	
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
	* <li>calcule automatiquement les attributs imposés.</li>
	* </ul>
	*
	* @param pProjetCiblePath : Path : 
	* valeur à passer à this.projetCiblePath.<br/>
	* 
	 * @throws Exception 
	*/
	public final void setProjetCiblePath(
			final Path pProjetCiblePath) throws Exception {
		
		this.projetCiblePath = pProjetCiblePath;
		
		/* calcule automatiquement les attributs imposés. */
		this.calculerAttributsImposes();
		
	} // Fin de setProjetCiblePath(...).___________________________________


	
	/**
	 * Getter du concept métier pour lequel on va générer les 
	 * classes DESIGN PATTERN (DTO, DAO, ...).<br/>
	 * Par exemple : <br/>
	 * "D:/Donnees/eclipse/eclipseworkspace/traficweb_v1
	 * /src/main/java
	 * /levy/daniel/application/model
	 * /metier/televersement/impl/Televersement.java".<br/>
	 *
	 * @return this.conceptMetier : File.<br/>
	 */
	public final File getConceptMetier() {
		return this.conceptMetier;
	} // Fin de getConceptMetier().________________________________________


	
	/**
	* Setter du concept métier pour lequel on va générer les 
	* classes DESIGN PATTERN (DTO, DAO, ...).<br/>
	* Par exemple : <br/>
	* "D:/Donnees/eclipse/eclipseworkspace/traficweb_v1
	* /src/main/java
	* /levy/daniel/application/model
	* /metier/televersement/impl/Televersement.java".<br/>
	* <ul>
	* <li>calcule automatiquement les attributs imposés.</li>
	* </ul>
	*
	* @param pConceptMetier : File : 
	* valeur à passer à this.conceptMetier.<br/>
	* 
	 * @throws Exception 
	*/
	public final void setConceptMetier(
			final File pConceptMetier) throws Exception {
		
		this.conceptMetier = pConceptMetier;
		
		/* calcule automatiquement les attributs imposés. */
		this.calculerAttributsImposes();

	} // Fin de setConceptMetier(...)._____________________________________


	
	/**
	 * Getter du nom simple (sans package et sans impl) 
	 * du package d'un concept métier</b>.<br/>
	 * Par exemple : <br/>
	 * <b>televersement</b> pour 
	 * "D:/Donnees/eclipse/eclipseworkspace/traficweb_v1
	 * /src/main/java
	 * /levy/daniel/application/model
	 * /metier/televersement/impl/Televersement.java".<br/>
	 *
	 * @return this.nomSimplePackageConceptMetier : String.<br/>
	 */
	public final String getNomSimplePackageConceptMetier() {
		return this.nomSimplePackageConceptMetier;
	} // Fin de getNomSimplePackageConceptMetier().________________________


	
	/**
	 * Getter du nom simple (sans package et sans extension) 
	 * d'un concept métier.<br/>
	 * Par exemple : <br/>
	 * <b>Televersement</b> pour 
	 * "D:/Donnees/eclipse/eclipseworkspace/traficweb_v1/
	 * src/main/java
	 * /levy/daniel/application/model
	 * /metier/televersement/impl/Televersement.java"<br/>
	 *
	 * @return this.nomConceptMetier : String.<br/>
	 */
	public final String getNomConceptMetier() {
		return this.nomConceptMetier;
	} // Fin de getNomConceptMetier()._____________________________________



	
	/**
	 * Getter de la Map&lt;Integer,IEncapsulationDeclarationMembre&gt; 
	 * des attributs simplement private du concept métier avec :
	 * <ul>
	 * <li>Integer : le numéro d'ordre déclaratif de l'attribut dans la classe</li>
	 * <li>IEncapsulationDeclarationMembre : encapsulation décrivant l'attribut</li>
	 * </ul>
	 *
	 * @return this.attributsPrivateMap : 
	 * Map&lt;Integer,IEncapsulationDeclarationMembre&gt;.<br/>
	 */
	public final Map<Integer, IEncapsulationDeclarationMembre> getAttributsPrivateMap() {
		return this.attributsPrivateMap;
	}
	
	
	
} // FIN DE LA CLASSE AbstractGenerateurFichierSource.-----------------------
