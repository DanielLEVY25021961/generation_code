package levy.daniel.application.model.services.metier.generationcode.ecriveurs;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

import levy.daniel.application.model.services.metier.generationcode.IGenerateur;


/**
 * class IEcriveur :<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 17 janv. 2018
 *
 */
public interface IEcriveur {

	/**
	 * METHODE_ECRIRESTRINGDANSFILE : String :<br/>
	 * "méthode ecrireStringDansFile(
	 * File pFile, String pString, Charset pCharset)".<br/>
	 */
	String METHODE_ECRIRESTRINGDANSFILE 
		= "méthode ecrireStringDansFile(File pFile, ....)";

	
	/**
	 * METHODE_INSERER_LIGNE : String :<br/>
	 * "méthode insererLigneDansFichier(File pFile, ...)".<br/>
	 */
	String METHODE_INSERER_LIGNE 
		= "méthode insererLigneDansFichier("
			+ "File pFile, Charset pCharsetLecture, int pNumLigne"
			+ ", Charset pCharsetEcriture, String pLigneAInserer";
	
	
	/**
	 * METHODE_LIRE_LIGNE_N_DANS_FICHIER : String :<br/>
	 * "méthode lireLigneDansFichier(
	 * File pFile, Charset pCharsetLecture, int pNumLigne)".<br/>
	 */
	String METHODE_LIRE_LIGNE_N_DANS_FICHIER 
		= "méthode lireLigneDansFichier("
			+ "File pFile, Charset pCharsetLecture, int pNumLigne)";
	
	
	/**
	 * METHODE_TROUVERNUMEROLIGNE : String :<br/>
	 * "méthode trouverNumeroLigne(...)".<br/>
	 */
	String METHODE_TROUVERNUMEROLIGNE 
		= "méthode trouverNumeroLigne(...)";
	
	
	/**
	 * METHODE_EXISTLIGNEDANSFICHIER : String :<br/>
	 * "méthode existLigneDansFichier(...)".<br/>
	 */
	String METHODE_EXISTLIGNEDANSFICHIER 
		= "méthode existLigneDansFichier(...)";
	
	
	/**
	 * METHODE_EXISTLIGNECOMMENCANT : String :<br/>
	 * "méthode existLigneCommencant(...)".<br/>
	 */
	String METHODE_EXISTLIGNECOMMENCANT 
		= "méthode existLigneCommencant(...)";
	
	
	/**
	 * METHODE_LIRE_STRINGS_DANS_FILE : String :<br/>
	 * "méthode lireStringsDansFile(File pFile, Charset pCharset)".<br/>
	 */
	String METHODE_LIRE_STRINGS_DANS_FILE 
		= "méthode lireStringsDansFile(File pFile, Charset pCharset)";
	
	
	/**
	 * METHODE_COMPTER_LIGNES : String :<br/>
	 * "méthode compterLignes(File pFile)".<br/>
	 */
	String METHODE_COMPTER_LIGNES 
		= "méthode compterLignes(File pFile)";

	
	// ****************************************************/
	// MESSAGES.
	// ****************************************************/
	/**
	 * MESSAGE_FICHIER_NULL : String :<br/>
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si le fichier est null.<br/>
	 * "Le fichier passé en paramètre est null".<br/>
	 */
	String MESSAGE_FICHIER_NULL 
		= "Le fichier passé en paramètre est null";
	
	
	/**
	 * MESSAGE_FICHIER_INEXISTANT : String :<br/>
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si le fichier est inexistant.<br/>
	 * "Le fichier passé en paramètre est inexistant : "
	 */
	String MESSAGE_FICHIER_INEXISTANT 
		= "Le fichier passé en paramètre est inexistant : ";
	
	
	/**
	 * MESSAGE_FICHIER_REPERTOIRE : String :<br/>
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si le fichier est un répertoire.<br/>
	 * "Le fichier passé en paramètre est un répertoire : ".<br/>
	 */
	String MESSAGE_FICHIER_REPERTOIRE 
		= "Le fichier passé en paramètre est un répertoire : ";
	
	
	/**
	 * MESSAGE_STRING_BLANK : String :<br/>
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si la String passée en paramètre est blank.<br/>
	 * "La chaine de caractères passée en paramètre est blank (null ou vide) : "
	 */
	String MESSAGE_STRING_BLANK 
		= "La chaine de caractères passée en paramètre est blank (null ou vide) : ";
	
	
	/**
	 * MESSAGE_EXCEPTION : String :<br/>
	 * "Exception GRAVE : ".<br/>
	 */
	String MESSAGE_EXCEPTION = "Exception GRAVE : ";
	

	
	// ****************************************************/
	// VARIABLES DE TEMPLATES.
	// ****************************************************/
	
	/**
	 * VARIABLE_PATHMETIER : String :<br/>
	 * "{$pathMetier}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_PATHMETIER 
		= "{$pathMetier}";

	
	/**
	 * VARIABLE_NOMPACKAGE : String :<br/>
	 * "{$nomPackage}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_NOMPACKAGE 
		= "{$nomPackage}";

	
	/**
	 * VARIABLE_NOMCLASSE : String :<br/>
	 * "{$NOM_CLASSE}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * substituer par <b>this.fabriquerNomClasse(
	 * this.nomSimpleFichierJava)</b>.<br/>
	 */
	String VARIABLE_NOMCLASSE 
		= "{$NOM_CLASSE}";
	
	
	/**
	 * VARIABLE_NOMSIMPLEFICHIERJAVA : String :<br/>
	 * "{$nomSimpleFichierJava}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_NOMSIMPLEFICHIERJAVA 
		= "{$nomSimpleFichierJava}";
	
	
	/**
	 * VARIABLE_NOMSIMPLEINTERFACE : String :<br/>
	 * "{$nomSimpleInterface}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_NOMSIMPLEINTERFACE 
		= "{$nomSimpleInterface}";

	
	/**
	 * VARIABLE_NOMSIMPLEABSTRACTCLASS : String :<br/>
	 * "{$nomSimpleAbstractClass}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * à substituer par <b>this.nomSimpleAbstractClass</b>.<br/>
	 */
	String VARIABLE_NOMSIMPLEABSTRACTCLASS 
		= "{$nomSimpleAbstractClass}";
	
	
	/**
	 * VARIABLE_CONCEPT_MODELISE : String :<br/>
	 * "{$conceptModelise}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * à substituer par <b>this.conceptModelise</b>.<br/>
	 * Exemple : <code>Profil</code>
	 */
	String VARIABLE_CONCEPT_MODELISE 
		= "{$conceptModelise}";
	
	
	/**
	 * VARIABLE_NOM_INTERFACE_METIER : String :<br/>
	 * "{$nomInterfaceMetier}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * substituer par <b>this.nomInterfaceMetier</b>.<br/>
	 */
	String VARIABLE_NOM_INTERFACE_METIER 
		= "{$nomInterfaceMetier}";

	
	/**
	 * VARIABLE_NOM_ABSTRACT_METIER : String :<br/>
	 * "{$nomAbstractClassMetier}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_NOM_ABSTRACT_METIER 
		= "{$nomAbstractClassMetier}";

	
	/**
	 * VARIABLE_NOM_CLASSE_METIER : String :<br/>
	 * "{$nomClassMetier}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * substituer par <b>this.nomClassMetier</b>.<br/>
	 */
	String VARIABLE_NOM_CLASSE_METIER 
		= "{$nomClassMetier}";
	
	
	/**
	 * VARIABLE_PATH_REL_CONCEPT : String :<br/>
	 * "{$pathRelConceptString}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * substituer par <b>this.pathRelConceptString</b>.<br/>
	 */
	String VARIABLE_PATH_REL_CONCEPT 
		= "{$pathRelConceptString}";
	

	/**
	 * VARIABLE_I_DAO : String :<br/>
	 * "{$nomIDao}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * substituer par <b>AbstractGenerateur.getNomIDao()</b>.<br/>
	 */
	String VARIABLE_I_DAO 
		= "{$nomIDao}";
	
	
	/**
	 * VARIABLE_CONCRETE_DAO : String :<br/>
	 * "{$nomConcreteDao}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * substituer par <b>AbstractGenerateur.getNomConcreteDao()</b>.<br/>
	 */
	String VARIABLE_CONCRETE_DAO 
		= "{$nomConcreteDao}";

		
	/**
	 * VARIABLE_NOM_ABSTRACT_TABLE : String :<br/>
	 * "{$nomAbstractTable}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * substituer par <b>AbstractGenerateur.getNomAbstractTable()</b>.<br/>
	 */
	String VARIABLE_NOM_ABSTRACT_TABLE 
		= "{$nomAbstractTable}";

	
	/**
	 * VARIABLE_NOM_CONCRETE_TABLE : String :<br/>
	 * "{$nomConcreteTable}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * substituer par <b>AbstractGenerateur.getNomConcreteTable()</b>.<br/>
	 */
	String VARIABLE_NOM_CONCRETE_TABLE 
		= "{$nomConcreteTable}";
	
	
	/**
	 * VARIABLE_PATH_REL_CONCEPT_IMPL : String :<br/>
	 * "{$pathRelConceptImplString}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * substituer par <b>this.pathRelConceptImplString</b>.<br/>
	 */
	String VARIABLE_PATH_REL_CONCEPT_IMPL 
		= "{$pathRelConceptImplString}";

	/**
	 * VARIABLE_PATH_REL_COUCHE_JAVA_STRING : String :<br/>
	 * "{$pathRelCoucheJavaString}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * substituer par <b>this.pathRelCoucheJavaString</b>.<br/>
	 */
	String VARIABLE_PATH_REL_COUCHE_JAVA_STRING 
		= "{$pathRelCoucheJavaString}";
	
	
	/**
	 * VARIABLE_PATH_REL_SOUS_COUCHE_JAVA_STRING : String :<br/>
	 * "{$pathRelSousCoucheJavaString}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * substituer par <b>this.pathRelSousCoucheJavaString</b>.<br/>
	 */
	String VARIABLE_PATH_REL_SOUS_COUCHE_JAVA_STRING 
		= "{$pathRelSousCoucheJavaString}";
	
	/**
	 * VARIABLE_NOMATTRIBUT : String :<br/>
	 * "{$nomAttribut}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_NOMATTRIBUT 
		= "{$nomAttribut}";
	
	
	/**
	 * VARIABLE_TYPEATTRIBUT : String :<br/>
	 * "{$typeAttribut}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_TYPEATTRIBUT 
		= "{$typeAttribut}";
	
	
	/**
	 * VARIABLE_PARAMATTRIBUT : String :<br/>
	 * "{$paramAttribut}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_PARAMATTRIBUT 
		= "{$paramAttribut}";
	
	
	/**
	 * VARIABLE_GETTER : String :<br/>
	 * "{$getterNomAttribut}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_GETTER 
		= "{$getterNomAttribut}";
	
	
	/**
	 * VARIABLE_SETTER : String :<br/>
	 * "{$setterNomAttribut}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_SETTER 
		= "{$setterNomAttribut}";
	
	
	/**
	 * VARIABLE_ENTIER_COMPARE : String :<br/>
	 * "{$compareNomAttribut}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_ENTIER_COMPARE 
		= "{$compareNomAttribut}";
	
	
	/**
	 * VARIABLE_NOMBRE_RGS : String :<br/>
	 * "{$nombreRgs}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_NOMBRE_RGS 
		= "{$nombreRgs}";
	
	
	/**
	 * VARIABLE_TITRE_RG : String :<br/>
	 * "{$titreRg}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_TITRE_RG 
		= "{$titreRg}";
	
	
	/**
	 * VARIABLE_MESSAGE_RG : String :<br/>
	 * "{$messageRg}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 */
	String VARIABLE_MESSAGE_RG 
		= "{$messageRg}";
	
	
	/**
	 * VARIABLE_DATEDUJOUR : String :<br/>
	 * "{$date}".<br/>
	 * Variable à utiliser dans les templates.<br/>
	 * à substituer par <b>this.afficherDateDuJour()</b>.<br/>
	 * Exemple : <code>19 février 2018</code>
	 */
	String VARIABLE_DATEDUJOUR 
		= "{$date}";
	
	
	/**
	 * VARIABLE_LIGNECSV : String :<br/>
	 * "{$ligneCsv}".<br/>
	 */
	String VARIABLE_LIGNECSV 
		= "{$ligneCsv}";

	


	
	//*****************************************************************/
	//************************* TEMPLATES *****************************/
	//*****************************************************************/				
	/**
	 * TEMPLATE_INHERITS_OVERRIDE : String :<br/>
	 * "/templates/javadoc_inherit_override.txt".<br/>
	 */
	String TEMPLATE_INHERITS_OVERRIDE 
		= "/templates/javadoc_inherit_override.txt";

	
	//*****************************************************************/
	//***************** CROCHETS ET PARENTHESES ***********************/
	//*****************************************************************/	
	/**
	 * CROCHET_OUVRANT : char :<br/>
	 * '{'.<br/>
	 */
	char CROCHET_OUVRANT = '{';
	
	/**
	 * CROCHET_FERMANT : char :<br/>
	 * '}'.<br/>
	 */
	char CROCHET_FERMANT = '}';
	
	/**
	 * PARENTHESE_OUVRANTE : char :<br/>
	 * '('.<br/>
	 */
	char PARENTHESE_OUVRANTE = '(';
	
	/**
	 * PARENTHESE_FERMANTE : char :<br/>
	 * ')'.<br/>
	 */
	char PARENTHESE_FERMANTE = ')';
	
	
	//*****************************************************************/
	//**************************** BOM_UTF-8 **************************/
	//*****************************************************************/
	/**
	 * BOM_UTF : char :<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	char BOM_UTF_8 = '\uFEFF';

	
	
	//*****************************************************************/
	//*********************** SAUTS DE LIGNE **************************/
	//*****************************************************************/	
	/**
	 * SAUTDELIGNE_UNIX : String :<br/>
	 * Saut de ligne généré par les éditeurs Unix.<br/>
	 * "\n" (Retour Ligne = LINE FEED (LF)).
	 */
	String SAUTDELIGNE_UNIX = "\n";
	
	/**
	 * SAUTDELIGNE_MAC : String :<br/>
	 * Saut de ligne généré par les éditeurs Mac.<br/>
	 * "\r" (Retour Chariot RC = CARRIAGE RETURN (CR))
	 */
	String SAUTDELIGNE_MAC = "\r";
	
	/**
	 * SAUTDELIGNE_DOS_WINDOWS : String :<br/>
	 * Saut de ligne généré par les éditeurs DOS/Windows.<br/>
	 * "\r\n" (Retour Chariot RC + Retour Ligne LF).
	 */
	String SAUTDELIGNE_DOS_WINDOWS = "\r\n";
	
	/**
	 * NEWLINE : String :<br/>
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	String NEWLINE = System.getProperty("line.separator");

	
	//*****************************************************************/
	//************************** SEPARATEURS **************************/
	//*****************************************************************/		
	/**
	 * SEP_ESPACE : String :<br/>
	 * " ".<br/>
	 */
	String SEP_ESPACE = " ";
	
	/**
	 * SEP_PV : String :<br/>
	 * Séparateur pour les CSV ";".<br/>
	 */
	String SEP_PV = ";";
	
	/**
	 * SEP_2PTS_AERE : String :<br/>
	 * " : ".<br/>
	 */
	String SEP_2PTS_AERE = " : ";
	
	/**
	 * SEPARATEUR_MOINS_AERE : String :<br/>
	 * " - ".<br/>
	 */
	String SEPARATEUR_MOINS_AERE = " - ";
	
	/**
	 * EGAL : String :<br/>
	 * " = ".<br/>
	 */
	String EGAL = " = ";
	
	/**
	 * UNDERSCORE : String :<br/>
	 * "_".<br/>
	 */
	String UNDERSCORE = "_";
	
	/**
	 * SLASH : char :<br/>
	 * '/'.<br/>
	 */
	char SLASH = '/';
	
	/**
	 * ANTISLASH : char :<br/>
	 * '\'.<br/>
	 * ATTENTION : antislash est un caractère spécial 
	 * qui doit être échappé en Java ('\\')<br/>
	 */
	char ANTISLASH = '\\';
	
	/**
	 * POINT : char :<br/>
	 * '.'.<br/>
	 */
	char POINT = '.';
	
	/**
	 * POINT_VIRGULE : char :<br/>
	 * ';'.<br/>
	 */
	char POINT_VIRGULE = ';';
	
	//*****************************************************************/
	//**************************** LOCALE *****************************/
	//*****************************************************************/
	/**
	 * LOCALE_FR : Locale : <br/>
	 * Locale France.<br/>
	 */
	Locale LOCALE_FR = new Locale("fr", "FR");

	
	//*****************************************************************/
	//************************** CHARSETS *****************************/
	//*****************************************************************/	
	/**
	 * CHARSET_UTF8 : Charset :<br/>
	 * Charset.forName("UTF-8").<br/>
	 * Eight-bit Unicode (or UCS) Transformation Format.<br/> 
	 */
	Charset CHARSET_UTF8 = Charset.forName("UTF-8");

	
	//*****************************************************************/
	//************************ TABULATIONS ****************************/
	//*****************************************************************/	
	/**
	 * DECALAGE_METHODE : String :<br/>
	 * "\t".<br/>
	 */
	String DECALAGE_METHODE = "\t";

	
	/**
	 * TAB : String :<br/>
	 * Tabulation "\t".<br/>
	 */
	String TAB = "\t";
	
	/**
	 * DECALAGE_CODE : String :<br/>
	 * DECALAGE_METHODE + "\t".<br/>
	 */
	String DECALAGE_CODE = DECALAGE_METHODE + TAB;

	
	//*****************************************************************/
	//*************************** JAVADOC *****************************/
	//*****************************************************************/			
	/**
	 * DEBUT_JAVADOC_FICHIER : String :<br/>
	 * début de la javadoc de niveau fichier.<br/>
	 * "/**".<br/>
	 */
	String DEBUT_JAVADOC_FICHIER = "/**";
	
	/**
	 * DEBUT_JAVADOC_MEMBRE : String :<br/>
	 * début de la javadoc de niveau membre 
	 * (attribut ou méthode).<br/>
	 * DECALAGE_METHODE + "/**".<br/>
	 */
	String DEBUT_JAVADOC_MEMBRE 
		= DECALAGE_METHODE + "/**";
	
	
	/**
	 * DEBUT_LIGNE_JAVADOC : String :<br/>
	 * " * ".<br/>
	 */
	String DEBUT_LIGNE_JAVADOC = " * ";

	
	/**
	 * DEBUT_LIGNE_JAVADOC_FICHIER : String :<br/>
	 * début d'une ligne courante de javadoc de niveau fichier.<br/>
	 * " * ".<br/>
	 */
	String DEBUT_LIGNE_JAVADOC_FICHIER 
		= DEBUT_LIGNE_JAVADOC;


	/**
	 * DEBUT_LIGNE_JAVADOC_MEMBRE : String :<br/>
	 *  début d'une ligne courante de javadoc de niveau membre 
	 *  (attribut ou méthode).<br/>
	 * DECALAGE_METHODE + DEBUT_LIGNE_JAVADOC.<br/>
	 */
	String DEBUT_LIGNE_JAVADOC_MEMBRE 
		= DECALAGE_METHODE + DEBUT_LIGNE_JAVADOC;

	
	/**
	 * FIN_JAVADOC : String :<br/>
	 * .<br/>
	 */
	String FIN_JAVADOC = " */";
	
	
	/**
	 * FIN_JAVADOC_FICHIER : String :<br/>
	 * Fin d'une javadoc de niveau fichier.<br/>
	 */
	String FIN_JAVADOC_FICHIER = FIN_JAVADOC;
	
	
	/**
	 * FIN_JAVADOC_MEMBRE : String :<br/>
	 * DECALAGE_METHODE + FIN_JAVADOC<br/>
	 */
	String FIN_JAVADOC_MEMBRE 
		= DECALAGE_METHODE + FIN_JAVADOC;
	
	
	/**
	 * VIRGULE_JAVADOC_FICHIER : String :<br/>
	 * " * , ".<br/>
	 */
	String VIRGULE_JAVADOC_FICHIER 
		= DEBUT_LIGNE_JAVADOC_FICHIER + ", ";
	
	
	/**
	 * VIRGULE_JAVADOC_MEMBRE : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + ", "<br/>
	 * "	 * , ".<br/>
	 */
	String VIRGULE_JAVADOC_MEMBRE 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + ", ";
	
	
	/**
	 * UL_OUVRANT_JAVADOC : String :<br/>
	 * "<ul>".<br/>
	 */
	String UL_OUVRANT_JAVADOC = "<ul>";

	
	/**
	 * UL_OUVRANT_JAVADOC_FICHIER : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_FICHIER + UL_OUVRANT_JAVADOC.<br/>
	 * " * <ul>"
	 */
	String UL_OUVRANT_JAVADOC_FICHIER 
		= DEBUT_LIGNE_JAVADOC_FICHIER + UL_OUVRANT_JAVADOC;
	
	
	/**
	 * UL_OUVRANT_JAVADOC_MEMBRE : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + UL_OUVRANT_JAVADOC.<br/>
	 * "	 * <ul>".<br/>
	 */
	String UL_OUVRANT_JAVADOC_MEMBRE 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + UL_OUVRANT_JAVADOC;
	
	
	/**
	 * OL_OUVRANT_JAVADOC : String :<br/>
	 * "<ol>".<br/>
	 */
	String OL_OUVRANT_JAVADOC = "<ol>";
	
	
	/**
	 * OL_OUVRANT_JAVADOC_FICHIER : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_FICHIER + OL_OUVRANT_JAVADOC.<br/>
	 * " * <ol>"
	 */
	String OL_OUVRANT_JAVADOC_FICHIER 
		= DEBUT_LIGNE_JAVADOC_FICHIER + OL_OUVRANT_JAVADOC;
	
	
	/**
	 * OL_OUVRANT_JAVADOC_MEMBRE : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + OL_OUVRANT_JAVADOC.<br/>
	 * "	 * <ol>".<br/>
	 */
	String OL_OUVRANT_JAVADOC_MEMBRE 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + OL_OUVRANT_JAVADOC;

	
	/**
	 * UL_FERMANT_JAVADOC : String :<br/>
	 * "</ul>".<br/>
	 */
	String UL_FERMANT_JAVADOC = "</ul>";
	
	
	/**
	 * UL_FERMANT_JAVADOC_FICHIER : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_FICHIER + UL_FERMANT_JAVADOC.<br/>
	 * " * </ul>"
	 */
	String UL_FERMANT_JAVADOC_FICHIER 
		= DEBUT_LIGNE_JAVADOC_FICHIER + UL_FERMANT_JAVADOC;
	
	
	/**
	 * UL_FERMANT_JAVADOC_MEMBRE : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + UL_FERMANT_JAVADOC.<br/>
	 * "	 * </ul>"
	 */
	String UL_FERMANT_JAVADOC_MEMBRE 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + UL_FERMANT_JAVADOC;

	
	/**
	 * OL_FERMANT_JAVADOC : String :<br/>
	 * "</ol>".<br/>
	 */
	String OL_FERMANT_JAVADOC = "</ol>";
	
	
	/**
	 * OL_FERMANT_JAVADOC_FICHIER : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_FICHIER + OL_FERMANT_JAVADOC.<br/>
	 * " * </ol>"
	 */
	String OL_FERMANT_JAVADOC_FICHIER 
		= DEBUT_LIGNE_JAVADOC_FICHIER + OL_FERMANT_JAVADOC;
	
	
	/**
	 * OL_FERMANT_JAVADOC_MEMBRE : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + OL_FERMANT_JAVADOC
	 * "	 * </ol>".<br/>
	 */
	String OL_FERMANT_JAVADOC_MEMBRE 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + OL_FERMANT_JAVADOC;
	
	
	/**
	 * LIGNE_VIDE_JAVADOC_FICHIER : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_FICHIER.<br/>
	 * " * "
	 */
	String LIGNE_VIDE_JAVADOC_FICHIER 
		= DEBUT_LIGNE_JAVADOC_FICHIER;
	
	
	/**
	 * LIGNE_VIDE_JAVADOC_MEMBRE : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE.<br/>
	 * "	 * ".<br/>
	 */
	String LIGNE_VIDE_JAVADOC_MEMBRE 
		= DEBUT_LIGNE_JAVADOC_MEMBRE;


	/**
	 * CONSTR_JAVADOC : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + "method CONSTRUCTEUR "<br/>
	 * "	 * method CONSTRUCTEUR ".<br/>
	 */
	String CONSTR_JAVADOC 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + "method CONSTRUCTEUR ";
	
	
	/**
	 * ID_JAVADOC_MEMBRE : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + "Long pId"<br/>
	 * "	 * Long pId".<br/>
	 */
	String ID_JAVADOC_MEMBRE 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + "Long pId";
	

	/**
	 * LIGNE_CONSTR_NULL_JAVADOC : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + "CONSTRUCTEUR D'ARITE NULLE.<br/>".<br/>
	 * "	 * CONSTRUCTEUR D'ARITE NULLE.<br/>".<br/>
	 */
	String LIGNE_CONSTR_NULL_JAVADOC 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + "CONSTRUCTEUR D'ARITE NULLE.<br/>";
	
	
	/**
	 * LIGNE_FIN_CONSTR_NULL : String :<br/>
	 * "} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________".<br/>
	 */
	String LIGNE_FIN_CONSTR_NULL = "} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________";
	
	
	/**
	 * LIGNE_CONSTR_COMPLET_JAVADOC : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + "<li>CONSTRUCTEUR COMPLET.</li>"
	 * "	 * <li>CONSTRUCTEUR COMPLET.</li>".<br/>
	 */
	String LIGNE_CONSTR_COMPLET_JAVADOC 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + "<li>CONSTRUCTEUR COMPLET.</li>";
	
	
	/**
	 * LIGNE_FIN_CONSTR_COMPLET : String :<br/>
	 * "} // Fin de CONSTRUCTEUR COMPLET.______________________________________".<br/>
	 */
	String LIGNE_FIN_CONSTR_COMPLET = "} // Fin de CONSTRUCTEUR COMPLET.______________________________________";
	
	
	/**
	 * LIGNE_CONSTR_COMPLET_BASE_JAVADOC : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + "<li>CONSTRUCTEUR COMPLET BASE.</li>"
	 * "	 * <li>CONSTRUCTEUR COMPLET BASE.</li>".<br/>
	 */
	String LIGNE_CONSTR_COMPLET_BASE_JAVADOC 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + "<li>CONSTRUCTEUR COMPLET BASE.</li>";
	
	
	/**
	 * LIGNE_FIN_CONSTR_COMPLET_BASE : String :<br/>
	 * "} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________".<br/>
	 */
	String LIGNE_FIN_CONSTR_COMPLET_BASE = "} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________";
	
	
	/**
	 * SANS_ID_JAVADOC : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + "<li>SANS ID en base.</li>"
	 * "	 * <li>SANS ID en base.</li>".<br/>
	 */
	String SANS_ID_JAVADOC 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + "<li>SANS ID en base.</li>";
	
	
	/**
	 * AVEC_ID_JAVADOC : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + "<li>AVEC ID en base.</li>"
	 * "	 * <li>AVEC ID en base.</li>".<br/>
	 */
	String AVEC_ID_JAVADOC 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + "<li>AVEC ID en base.</li>";
	
	
	/**
	 * PARAM_ID_JAVADOC : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + "@param pId : Long : ID en base.<br/>"
	 * "	 * @param pId : Long : ID en base.<br/>".<br/>
	 */
	String PARAM_ID_JAVADOC 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + "@param pId : Long : ID en base.<br/>";
	
	
	/**
	 * LIGNE_PARAM_JAVADOC : String :<br/>
	 * DEBUT_LIGNE_JAVADOC_MEMBRE + "@param ".<br/>
	 * "	 * @param ".<br/>
	 */
	String LIGNE_PARAM_JAVADOC 
		= DEBUT_LIGNE_JAVADOC_MEMBRE + "@param ";

	
	
	//*****************************************************************/
	//*************************** COMMUNS *****************************/
	//*****************************************************************/
	

	/**
	 * INTERFACE : String :<br/>
	 * "public interface ".<br/>
	 */
	String INTERFACE = "public interface ";
	
	
	/**
	 * ABSTRACT_CLASS : String :<br/>
	 * "public abstract class ".<br/>
	 */
	String ABSTRACT_CLASS 
		= "public abstract class ";
	
	
	/**
	 * CLASS : String :<br/>
	 * "public class ".<br/>
	 */
	String CLASS 
		= "public class ";

	
	/**
	 * IMPLEMENTS : String :<br/>
	 * " implements ".<br/>
	 */
	String IMPLEMENTS = " implements ";
	
	
	/**
	 * EXTENDS : String :<br/>
	 * " extends ".<br/>
	 */
	String EXTENDS = " extends ";
	

	/**
	 * POINT_BR : String :<br/>
	 * ".<br/>".<br/>
	 */
	String POINT_BR = ".<br/>";

	/**
	 * PUBLIC : String :<br/>
	 * "	public ".<br/>
	 */
	String PUBLIC = "	public ";
	
	/**
	 * FINAL : String :<br/>
	 * "final ".<br/>
	 */
	String FINAL = "final ";
	
	/**
	 * SUPER : String :<br/>
	 * "super();".<br/>
	 */
	String SUPER = "super();";
	
	/**
	 * THIS : String :<br/>
	 * "this.".<br/>
	 */
	String THIS = "this.";

	/**
	 * STRING : String :<br/>
	 * "String".<br/>
	 */
	String STRING = "String";
	
	
	/**
	 * method ecrireCode(
	 * File pFile
	 * , IGenerateur pGenerateur) :<br/>
	 * <ul>
	 * <li><b>Génère le code dans le fichier java pFile</b>.</li>
	 * <li>Traite le cas des mauvais fichiers.</li>
	 * <li>alimente this.generateurMetier.</li>
	 * <li>alimente this.nomPackage.</li>
	 * <li>alimente this.conceptModelise.</li>
	 * <li>alimente this.mapAttributs.</li>
	 * <li>alimente this.mapAttributsEquals.</li>
	 * <li>alimente this.mapRg.</li>
	 * <li>alimente this.nomSimpleInterface.</li>
	 * <li>alimente this.nomSimpleAbstractClass.</li>
	 * <li>alimente this.nomSimpleObjetMetier.</li>
	 * <li>alimente this.fichierJava.</li>
	 * <li>alimente this.nomSimpleFichierJava.</li>
	 * <li>écrit la ligne de code <b>package</b> (1ère ligne).</li>
	 * <li>Insère 1 ligne vide sous la ligne de code package.</li>
	 * <li>Ecrit les <b>imports</b> à partir de la 3ème ligne.</li>
	 * <li>Insère 3 lignes vides sous la dernière ligne d'imports.</li>
	 * <li>écrit les lignes de <b>javadoc</b>.</li>
	 * <li>écrit la ligne de <b>DECLARATION</b> à la suite.
	 * <li><b>Appelle un HOOK</b> pour terminer la génération du code 
	 * dans un Ecriveur concret.</li> 
	 * <li>écrit la ligne <b>finale</b>.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * @param pGenerateur : IGenerateur.<br/>
	 * 
	 * @throws Exception 
	 */
	void ecrireCode(
			File pFile
				, IGenerateur pGenerateur) throws Exception;
	
	

	/**
	 * method afficherListeString(
	 * List&lt;String&gt; pListe) :<br/>
	 * Fabrique une String à partir d'une List&lt;String&gt;.<br/>
	 * <br/>
	 * - Retourne null si pListe est null.<br/>
	 * <br/>
	 *
	 * @param pListe : List&lt;String&gt; : liste de lignes.
	 * 
	 * @return : String : Pour affichage à la console.<br/>
	 */
	String afficherListeString(
			List<String> pListe);
	
	

} // FIN DE L'INTERFACE IEcriveur--------------------------------------------