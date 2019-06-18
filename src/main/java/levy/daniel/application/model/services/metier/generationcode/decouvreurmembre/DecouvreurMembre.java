package levy.daniel.application.model.services.metier.generationcode.decouvreurmembre;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierSimpleException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;
import levy.daniel.application.model.services.metier.generationcode.decouvreurmembre.impl.EncapsulationDeclarationMembre;

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
	
	/**
	 * "méthode decouvrirAttributs(File pFile)".<br/>
	 */
	public static final String METHODE_DECOUVRIR_ATTRIBUTS 
		= "méthode decouvrirAttributs(File pFile)";
	
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
	 * <b>motif REGEX pour détecter les lignes de déclaration des ATTRIBUTS</b> 
	 * d'une classe.<br/>
	 * <ul>
	 * <li>seuls les attributs public, protected ou private sont détectés.</li>
	 * <li>les attributs "friendly" déclarés sans modificateur 
	 * ne sont pas détectés.</li>
	 * </ul>
	 * <p>
	 * <b>"^(\\s*)((public?|protected?|private?)\\s*(static?|)\\s*(final?|)\\s*(transient?|)\\s+(\\S+)\\s+([a-zA-Z0-9_]+)\\s*(.*));$"</b>.
	 * </p>
	 * <ol>
	 * <li><b>^(\\s*)</b> signifie 
	 * <i>"commence par 0, 1 ou plusieurs espaces</i>.</li>
	 * <li><b>((public|protected|private)</b> 
	 * signifie <i>"public, ou protected, ou private"</i></li>
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
	public static final String MOTIF_REGEX_ATTRIBUT 
		= "^(\\s*)((public|protected|private)\\s*(static?|)\\s*(final?|)\\s*(transient?|)\\s+(\\S+)\\s+([a-zA-Z0-9_]+)\\s*(.*));$";
	
	/**
	 * Pattern.compile(MOTIF_REGEX_ATTRIBUT);
	 */
	public static final Pattern PATTERN_MOTIF_REGEX_ATTRIBUT 
		= Pattern.compile(MOTIF_REGEX_ATTRIBUT);
		
	/**
	 * Pattern.compile(".");
	 */
	public static final Pattern PATTERN_SEP_POINT = Pattern.compile("\\.");
	
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
	 * <b>détecte les attributs d'une classe</b> pFichierSource et les retourne 
	 * sous forme de Map&lt;Integer,IEncapsulationDeclarationMembre&gt; avec :
	 * <ul>
	 * <li>Integer : le numéro d'ordre déclaratif de l'attribut dans la classe</li>
	 * <li>IEncapsulationDeclarationMembre : encapsulation décrivant l'attribut</li>
	 * </ul>
	 * - LOG.fatal et jette une Exception circonstanciée 
	 * si pFichierSource est null, vide, inexistant ou répertoire.<br/>
	 * <br/>
	 *
	 * @param pFichierSource : File : classe dont on détecte les membres.
	 * 
	 * @return : Map&lt;Integer,IEncapsulationDeclarationMembre&gt; :  
	 * Map des attributs.<br/>
	 * 
	 * @throws Exception 
	 */
	public final Map<Integer, IEncapsulationDeclarationMembre> 
					decouvrirAttributs(final File pFichierSource) 
													throws Exception {
		
		/* LOG.fatal et jette une Exception circonstanciée si 
		 * pFichierSource est null, vide, inexistant 
		 * ou répertoire. */
		this.traiterMauvaisFichierSource(
				pFichierSource, METHODE_DECOUVRIR_ATTRIBUTS);
		
		final Map<Integer, IEncapsulationDeclarationMembre> resultat 
			= new LinkedHashMap<Integer, IEncapsulationDeclarationMembre>();
		
		final List<String> lignes = this.lireFichierSource(pFichierSource);
		
		int compteur = 0;
		
		for (final String ligne : lignes) {
			
			final Matcher matcher 
				= PATTERN_MOTIF_REGEX_ATTRIBUT.matcher(ligne);
			
			if (matcher.matches()) {
				
				compteur ++;
				
				final IEncapsulationDeclarationMembre membre 
					= new EncapsulationDeclarationMembre();
				
				membre.setLigneEntiere(matcher.group(0));
				membre.setEspacesEnDebutLigne(matcher.group(1));
				membre.setContenuLigne(matcher.group(2));
				membre.setModerateur(matcher.group(3));
				membre.setModificateurStatic(matcher.group(4));
				membre.setModificateurFinal(matcher.group(5));
				membre.setModificateurTransient(matcher.group(6));
				membre.setType(matcher.group(7));
				membre.setNomMembre(matcher.group(8));
				membre.setReste(matcher.group(9));
				
				membre.setAttribut(true);
				
				resultat.put(compteur, membre);
				
			}
			
		}
		
		return resultat;
		
	} // Fin de decouvrirAttributs(...).___________________________________
	
	
	
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
	 * détecte si une ligne d'un fichier source est une déclaration 
	 * d'un ATTRIBUT conformément au MOTIF_REGEX_ATTRIBUT.<br/>
	 * <br/>
	 * - retourne false si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.
	 * 
	 * @return : boolean : 
	 * true si pString est une déclaration d'attribut.<br/>
	 */
	private boolean estDeclarationAttribut(
			final String pString) {
		
		/* retourne false si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return false;
		}
		
		final Pattern pattern = Pattern.compile(MOTIF_REGEX_ATTRIBUT);
		
		final Matcher matcher = pattern.matcher(pString);
		
		if (matcher.matches()) {
			return true;
		}
		
		return false;
		
	} // Fin de estDeclarationAttribut(...)._______________________________
		
	
	
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
