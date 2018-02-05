package levy.daniel.application.apptechnic.generationcode;

import java.io.File;
import java.nio.charset.Charset;

/**
 * INTERFACE <b>IGenerateur</b> :<br/>
 * <p>
 * <b>IGenerateur</b> modélise un <i>concept</i> 
 * de <b>Générateur de code</b> 
 * , c'est à dire un objet <b>générant des fichiers java</b>  
 * (IConcept, AbstractConcept, Concept, IDaoConcept
 * , AbstractDaoConcept, ...) 
 * ou des <b>ressources</b> (jsp, properties, ...).
 * </p>
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
 * @since 17 janv. 2018
 *
 */
public interface IGenerateur {

	
	/**
	 * METHODE_LIRE_STRINGS_DANS_FILE : String :<br/>
	 * "méthode lireStringsDansFile(File pFile, Charset pCharset)".<br/>
	 */
	String METHODE_LIRE_STRINGS_DANS_FILE 
		= "méthode lireStringsDansFile(File pFile, Charset pCharset)";
	
	
	/**
	 * METHODE_EXISTLIGNECOMMENCANT : String :<br/>
	 * "méthode existLigneCommencant(...)".<br/>
	 */
	String METHODE_EXISTLIGNECOMMENCANT 
		= "méthode existLigneCommencant(...)";
	
	/**
	 * METHODE_ECRIRESTRINGDANSFILE : String :<br/>
	 * "méthode ecrireStringDansFile(
	 * File pFile, String pString, Charset pCharset)".<br/>
	 */
	String METHODE_ECRIRESTRINGDANSFILE 
		= "méthode ecrireStringDansFile(File pFile, ....)";


	
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
	//************************** CHARSETS *****************************/
	//*****************************************************************/	
	/**
	 * CHARSET_UTF8 : Charset :<br/>
	 * Charset.forName("UTF-8").<br/>
	 * Eight-bit Unicode (or UCS) Transformation Format.<br/> 
	 */
	Charset CHARSET_UTF8 = Charset.forName("UTF-8");


	
	/**
	 * method generer() :<br/>
	 * <ul>
	 * <li><b>Génère le code model.metier</b>.</li>
	 * <li>génère packageMetier.</li>
	 * <li>Génère les packages this.packageSousCouche 
	 * et this.sousPackageImpl.</li>
	 * <li>alimente tous les attributs d'instance.</li>
	 * <li>Génère tous les fichiers java vides.</li>
	 * 
	 * <li>Génère le code de l'interface 
	 * dans this.iObjetMetier.</li>
	 * <li>génère le code de la classe abstraite 
	 * dans this.abstractObjetMetier.</li>
	 * </ul>	 *
	 * 
	 * @throws Exception
	 */
	void generer() throws Exception;



	/**
	 * method getPathPackage() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu de la SOUS-COUCHE (package)</b> 
	 * contenant les arborescences à générer 
	 * (model/metier, model/dao/metier
	 * , model/services/metier, ...).</li>
	 * <li>par exemple : <br/>
	 * <code>model/metier</code> pour un GenerateurMetierToutAbstract 
	 * ou <code>model/dao/metier</code> 
	 * pour un GenerateurDaoToutAbstract.</li>
	 * </ul>
	 *
	 * @return pathPackage : String.<br/>
	 */
	String getPathPackage();
	


	/**
	 * method getPackageSousCouche() :<br/>
	 * <ul>
	 * <li><b>Package du fichier java à générer</b> 
	 * sous pathPackage/packageSousCouche.<br/>
	 * <li>Par exemple : <br/>
	 * <code>model/metier/profil</code> pour un 
	 * GenerateurMetierToutAbstract générant un concept Profil
	 * ou <code>model/dao/metier/profil</code> pour un 
	 * GenerateurDaoToutAbstract générant un concept Profil.</li> 
	 * </ul>
	 *
	 * @return packageSousCouche : File.<br/>
	 */
	File getPackageSousCouche();



	/**
	 * method getSousPackageImpl() :<br/>
	 * <ul>
	 * <li>Getter du <b>Sous-Package "impl" du Concept à générer</b> 
	 * sous model/metier/packageSousCouche/sousPackageImpl.</li>
	 * <li>Par exemple : <br/>
	 * <code>model/metier/profil/impl</code> 
	 * pour un GenerateurMetierToutAbstract générant un concept Profil
	 * ou <code>model/metier/dao/profil/impl</code> pour un 
	 * GenerateurDaoToutAbstract générant un concept Profil.</li>
	 * </ul>
	 *
	 * @return sousPackageImpl : File.<br/>
	 */
	File getSousPackageImpl();


		
	/**
	* method getNomSimpleInterface() :<br/>
	* <ul>
	* <li>Getter de this.nomSimpleInterface.</li>
	* </ul>
	*
	* @return this.nomSimpleInterface : String.<br/>
	*/
	String getNomSimpleInterface();
	

	
	/**
	* method getInterfaceJava() :<br/>
	* <ul>
	* <li>Getter de this.interfaceJava.</li>
	* </ul>
	*
	* @return this.interfaceJava : File.<br/>
	*/
	
	File getInterfaceJava();
	

	
	/**
	* method getNomSimpleAbstractClass() :<br/>
	* <ul>
	* <li>Getter de this.nomSimpleAbstractClass.</li>
	* </ul>
	*
	* @return this.nomSimpleAbstractClass : String.<br/>
	*/
	String getNomSimpleAbstractClass();


		
	/**
	* method getAbstractClass() :<br/>
	* <ul>
	* <li>Getter de this.abstractClass.</li>
	* </ul>
	*
	* @return this.abstractClass : File.<br/>
	*/
	File getAbstractClass();


		
	/**
	* method getNomSimpleConcreteClass() :<br/>
	* <ul>
	* <li>Getter de this.nomSimpleConcreteClass.</li>
	* </ul>
	*
	* @return this.nomSimpleConcreteClass : String.<br/>
	*/
	String getNomSimpleConcreteClass();


	
	/**
	* method getConcreteClass() :<br/>
	* <ul>
	* <li>Getter de this.concreteClass.</li>
	* </ul>
	*
	* @return this.concreteClass : File.<br/>
	*/
	File getConcreteClass();
	


} // FIN DE L'INTERFACE IGenerateur.-----------------------------------------
