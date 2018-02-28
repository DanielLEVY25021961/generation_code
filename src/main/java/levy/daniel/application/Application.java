package levy.daniel.application;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.AbstractGenerateur;
import levy.daniel.application.apptechnic.generationcode.GestionnaireProjet;
import levy.daniel.application.apptechnic.generationcode.IGenerateur;
import levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationdao.GenerateurDaoToutAbstract;
import levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationmetier.generationobjetmetiersimple.GenerateurMetierToutAbstract;
import levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationtests.GenerateurMetierTest;

/**
 * class Application :<br/>
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
public final class Application {

	// ************************ATTRIBUTS************************************/

		
	/**
	 * SAUT_LIGNE : char :<br/>
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE = '\n';


	/**
	 * STRING : String :<br/>
	 * "String".<br/>
	 */
	public static final String STRING 
		= "String";
	
	
	/**
	 * MAP_ATTRIBUTS : Map&lt;String,String&gt; :<br/>
	 * <ul>
	 * Map&lt;String,String&gt; ordonnée 
	 * contenant tous les attributs avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 */
	private static final Map<String, String> MAP_ATTRIBUTS  // NOPMD by daniel.levy on 10/01/18 10:15
		= new LinkedHashMap<String, String>();

	
	/**
	 * MAP_ATTRIBUTS_EQUALS : Map<String,String> :<br/>
	 * <ul>
	 * Map&lt;String,String&gt; ordonnée 
	 * contenant uniquement les attributs de equals avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 */
	private static final Map<String, String> MAP_ATTRIBUTS_EQUALS  // NOPMD by daniel.levy on 10/01/18 10:15
	= new LinkedHashMap<String, String>();
	
	
	/**
	 * MAP_RG : Map&lt;String, List&lt;String&gt;&gt; :<br/>
	 * <ul>
	 * Map&lt;String, List&lt;String&gt;&gt; ordonnée 
	 * contenant les listes de RG par attribut avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>List&lt;String&gt; : Liste des RG s'appliquant à l'attribut</li>
	 * </ul>
	 */
	private static final Map<String, List<String>> MAP_RG  // NOPMD by daniel.levy on 10/01/18 10:15
	= new LinkedHashMap<String, List<String>>();

	
	/**
	 * PROJET_GENERATION_CODE_TEST : String :<br/>
	 * "generation_code_test".<br/>
	 */
	public static final String PROJET_GENERATION_CODE_TEST 
		= "generation_code_test";

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(Application.class);


	// *************************METHODES************************************/	
	
	 /**
	 * method CONSTRUCTEUR Application() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	private Application() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	 * method main(
	 * String[] pArgs) :<br/>
	 * <ul>
	 * <li>.</li>
	 * <li>.</li>
	 * </ul>
	 *
	 * @param pArgs :  :  .<br/>
	 * @throws Exception 
	 */
	public static void main(
			final String[] pArgs) throws Exception {
		
		
		/* Choisit le projet dans lequel générer le code. */
		GestionnaireProjet.alimenterAttributs(PROJET_GENERATION_CODE_TEST);
		
		/* Instancie des générateurs de code. */
		final IGenerateur generateurMetier = new GenerateurMetierToutAbstract();
		final IGenerateur generateurDao = new GenerateurDaoToutAbstract();
		final IGenerateur generateurTest = new GenerateurMetierTest();
		
		MAP_ATTRIBUTS.put("profilString", STRING);
		MAP_ATTRIBUTS.put("porteeProfil", STRING);
		MAP_ATTRIBUTS.put("restrictionProfil", STRING);
		
		MAP_ATTRIBUTS_EQUALS.put("profilString", STRING);
		MAP_ATTRIBUTS_EQUALS.put("porteeProfil", STRING);
		
		final List<String> listeRGProfilString = new ArrayList<String>();
		final List<String> listeRGPorteeProfil = new ArrayList<String>();
		final List<String> listeRGRestrictionProfil = new ArrayList<String>();
		
		listeRGProfilString.add("RG_PROFIL_PROFILSTRING_RENSEIGNE_01 : le profilString du Profil doit être renseigné (obligatoire)");
		listeRGProfilString.add("RG_PROFIL_PROFILSTRING_NOMENCLATURE_02 : le profilString du Profil doit respecter un ensemble fini de valeurs (nomenclature)");
		
		listeRGPorteeProfil.add("RG_PROFIL_PORTEEPROFIL_RENSEIGNE_01 : le porteeProfil du Profil doit être renseigné (obligatoire)");
		
		MAP_RG.put("profilString", listeRGProfilString);
		MAP_RG.put("porteeProfil", listeRGPorteeProfil);
		MAP_RG.put("restrictionProfil", listeRGRestrictionProfil);
		
		/* CONFIGURATION des générateurs. */
		AbstractGenerateur.configurer("profil"
						, "IProfil"
							, "ProfilCerbere"
								, MAP_ATTRIBUTS
									, MAP_ATTRIBUTS_EQUALS
										, MAP_RG);
		
		/* GENERATION DU CODE. */
		generateurMetier.generer();
		
		generateurDao.generer();
		
		generateurTest.generer();
		
	} // Fin de main(...)._________________________________________________
	

		
	/**
	 * method trouverCamel(
	 * String pString) :<br/>
	 * <ul>
	 * <li><b>Décompose un mot camelCase</b> et retourne une liste 
	 * des sous-mots le composant</li>
	 * <ul>
	 * <li>trouverCamel("abel") retourne "abel".</li>
	 * <li>trouverCamel("Abel") retourne "Abel".</li>
	 * <li>trouverCamel("abel7Medard") retourne "abel7" 
	 * et "Medard".</li>
	 * <li>trouverCamel("AbelMedardGoro") retourne "Abel"
	 * , "Medard", "Goro".</li>
	 * <li>trouverCamel("AbstractProfilSimple") retourne "Abstract"
	 * , "Profil", "Simple".</li>
	 * </ul>
	 * </ul>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : List&lt;String&gt; : Liste des "bosses" du chameau.<br/>
	 */
	private static List<String> trouverCamel(
			final String pString) {
		
		final List<String> resultat = new ArrayList<String>();
		
		/* Pattern sous forme de String. */
		/* - Commence par une Majuscule ou une minuscule
		 * - poursuit par des minuscules. */
		final String patternStringDebut = "^([A-Z][a-z0-9]*|[a-z0-9]*)";
		
		/* Instanciation d'un Pattern. */
		final Pattern patternDebut = Pattern.compile(patternStringDebut);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcherDebut = patternDebut.matcher(pString);
		
		String suite = null;
		
		/* Recherche des ocurrences du Pattern. */
		while (matcherDebut.find()) {
			
			final String lu = matcherDebut.group();
			resultat.add(lu);
			suite = StringUtils.removeStart(pString, lu);
			
		}
		
		if (!StringUtils.isBlank(suite)) {
			
			/* Pattern sous forme de String. */
			/* - Commence par I
			 * - poursuit par une Majuscule
			 * - poursuit CamelCase. */
			final String patternString = "([A-Z][a-z0-9]*)";
			
			/* Instanciation d'un Pattern. */
			final Pattern pattern = Pattern.compile(patternString);
			
			/* Instanciation d'un moteur de recherche Matcher. */
			final Matcher matcher = pattern.matcher(suite);
			
			/* Recherche des ocurrences du Pattern. */
			while (matcher.find()) {
				
				final String lu = matcher.group();
				resultat.add(lu);		
				
			}
			
		}
				
		return resultat;

	} // Fin de trouverCamel(...)._________________________________________
	
	
	
	/**
	 * method conformeNomPackage(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Contrôle que pString est conforme aux noms de package java
	 * , à savoir que des lettres minuscules et des chiffres 
	 * (pas de majuscules ou de caractères spéciaux).</li>
	 * <li>accepte "profil", "profil7", ...</li>
	 * <li>refuse "Profil", "profil_7", ...</li>
	 * </ul>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : boolean : true si conforme.<br/>
	 */
	private static boolean conformeNomPackage(
			final String pString) {
		
		boolean resultat = false;
		
		/* Pattern sous forme de String. */
		/* - Commence par I
		 * - poursuit par une Majuscule
		 * - poursuit CamelCase. */
		final String patternString = "([a-z0-9]*)";
		
		/* Instanciation d'un Pattern. */
		final Pattern pattern = Pattern.compile(patternString);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcher = pattern.matcher(pString);
		
		/* Recherche du Pattern. */
		final boolean trouve = matcher.matches();
		
		if (trouve) {
			resultat = true;
		}
		
		return resultat;
		
	} // Fin de conformeNomPackage(...).___________________________________
	
	
	
	/**
	 * method conformeNomInterface(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Contrôle que pString est conforme aux noms des Interfaces
	 * , à savoir I, puis une majuscule, puis une suite camelCase 
	 * sans caractères spéciaux.</li>
	 * <li>accepte "IProfil", "IProfilSimple", "IProfil7", ...</li>
	 * <li>refuse "Profil", "IProfil_Simple", "iProfil", ...</li>
	 * </ul>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : boolean : true si conforme.<br/>
	 */
	private static boolean conformeNomInterface(
			final String pString) {
		
		boolean resultat = false;
		
		/* Pattern sous forme de String. */
		/* - Commence par I
		 * - poursuit par une Majuscule
		 * - poursuit CamelCase. */
		final String patternString = "(^I)([A-Z][a-z][a-zA-Z0-9]*$)";
		
		/* Instanciation d'un Pattern. */
		final Pattern pattern = Pattern.compile(patternString);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcher = pattern.matcher(pString);
		
		/* Recherche du Pattern. */
		final boolean trouve = matcher.find();
		
		if (trouve) {
			resultat = true;
		}
		
		return resultat;
		
	} // Fin de conformeNomInterface(...)._________________________________
	
	
	
	/**
	 * method conformeNomClasse(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Contrôle que pString est conforme aux noms des Classes
	 * , à savoir une majuscule, puis une suite camelCase 
	 * sans caractères spéciaux.</li>
	 * <li>accepte "Profil", "ProfilSimple", "ProfilSimple7", ...</li>
	 * <li>refuse "IProfil", "profil", "ProfilSimple_7", ...</li>
	 * </ul>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : boolean : true si conforme.<br/>
	 */
	private static boolean conformeNomClasse(
			final String pString) {
		
		boolean resultat = false;
		
		/* Pattern sous forme de String. */
		/* - Commence par une Majuscule
		 * - poursuit camelCase. */
		final String patternString = "(^[A-Z][a-z][a-zA-Z0-9]*$)";
		
		/* Instanciation d'un Pattern. */
		final Pattern pattern = Pattern.compile(patternString);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcher = pattern.matcher(pString);
		
		/* Recherche du Pattern. */
		final boolean trouve = matcher.find();
		
		if (trouve) {
			resultat = true;
		}
		
		return resultat;
		
	} // Fin de conformeNomClasse(...).____________________________________
	

	
	/**
	 * method afficherListString(
	 * List&lt;String&gt; pList) :<br/>
	 * <ul>
	 * fabrique une String pour l'affichage des Strings 
	 * contenus dans la liste pList.
	 * </ul>
	 * retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;String&gt;.<br/>
	 * 
	 * @return : String.<br/>
	 */
	public static String afficherListString(
			final List<String> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		int compteur = 0;
		final int tailleListe = pList.size();
		
		for (final String chaine : pList) {
			
			compteur++;
			
			stb.append(chaine);
			
			if (compteur < tailleListe) {
				stb.append(SAUT_LIGNE);
			}			
		}
		
		return stb.toString();
		
	} // Fin de afficherListString(...).___________________________________
	


} // FIN DE LA CLASSE Application.-------------------------------------------
