package levy.daniel.application.apptechnic.generationcode;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.text.WordUtils;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.generationcode.impl.GestionnaireFiles;
import levy.daniel.application.util.gestionnairesiofichiers.GestionnaireFichiers;

/**
 * CLASSE ABSTRAITE <b>AbstractGenerateur</b> :<br/>
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
public abstract class AbstractGenerateur implements IGenerateur {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_ABSTRACT_GENERATEUR : String :<br/>
	 * "Classe AbstractGenerateur".<br/>
	 */
	public static final String CLASSE_ABSTRACT_GENERATEUR 
		= "Classe AbstractGenerateur";
	


	/**
	 * gestionnaireFiles : GestionnaireFiles :<br/>
	 * GestionnaireFiles.<br/>
	 */
	protected final transient IGestionnaireFiles gestionnaireFiles 
		= new GestionnaireFiles();

	/**
	 * PATH_MAIN_JAVA : Path :<br/>
	 * <ul>
	 * <li><b>path du répertoire de la RACINE des sources .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>PATH_MAIN_JAVA = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java
	 * </code></li>
	 * </ul>
	 */
	protected static final Path PATH_MAIN_JAVA 
		= GestionnaireProjet.getPathMainJava();

	
	/**
	 * PATH_TEST_JAVA : Path :<br/>
	 * <ul>
	 * <li><b>path du répertoire de la RACINE des tests .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>PATH_TEST_JAVA = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java
	 * </code></li>
	 * </ul>
	 */
	protected static final Path PATH_TEST_JAVA 
		= GestionnaireProjet.getPathTestJava();
	
	
	/**
	 * conceptModelise : String :<br/>
	 * <ul>
	 * <li><b>concept modélisé par ce générateur</b>.</li>
	 * <li><b>nom du package à créer dans chaque SOUS-COUCHE</b> 
	 * (model/metier, model/dao/metier, model/services/metier, ...) 
	 * avec une <i>majuscule</i> en première position.</li>
	 * <li>Par exemple : <br/>
	 * <code>Profil</code> pour le nomPackage "profil".</li>
	 * <li>RG-CONCEPT-01 : le conceptModelise est déduit 
	 * du nomPackage passé en paramètre.</li>
	 * </ul>
	 */
	protected static String conceptModelise;
	
	
	/**
	 * nomPackage : String :<br/>
	 * <ul>
	 * <li><b>nom du package à créer dans chaque SOUS-COUCHE</b> 
	 * en fonction du concept à modéliser dans le générateur 
	 * (model/metier/profil, model/dao/metier/profil, 
	 * model/services/metier/profil, ...pour un concept Profil).</li>
	 * <li>passé en paramètre au générateur.</li>
	 * <li>par exemple : <br/>
	 * <code>profil</code>.</li>
	 * <li>RG-CONCEPT-01 : le conceptModelise est déduit 
	 * du nomPackage passé en paramètre.</li>
	 * </ul>
	 */
	protected static String nomPackage;
	

	
	/**
	 * packageMetier : File :<br/>
	 * <ul>
	 * <li>Package metier de la COUCHE <i>model</i> 
	 * (model.metier).</li>
	 * </ul>
	 */
	protected static File packageMetier;
	
	
	/**
	 * pathPackageMetier : Path :<br/>
	 * <ul>
	 * <li><b>path absolu du package metier</b> 
	 * de la COUCHE <i>MODEL</i>.</li>
	 * <li>Par exemple :<br/> 
	 * <code>./src/main/java/levy/daniel/application/model/metier
	 * </code>.</li>
	 * </ul>
	 */
	protected static Path pathPackageMetier;
	
	
	/**
	 * pathRelMetier : Path :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du package metier</b> 
	 *  (sous model).</li>
	 * <li>path relatif MODE FILE, c'est à dire avec 
	 * des séparateurs slash.</li> 
	 * <li>Par exemple :<br/>
	 * <code>levy/daniel/application/model/metier
	 * </code>.</li>
	 * </ul>
	 */
	protected static Path pathRelMetier;
	
	
	/**
	 * pathRelMetierString : String :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du package metier</b> 
	 * (sous model).</li>
	 * <li>path relatif JAVA, c'est à dire avec 
	 * des séparateurs point.</li> 
	 * <li>Par exemple :<br/>
	 * <code>levy.daniel.application.model.metier
	 * </code>.</li>
	 * </ul>
	 */
	protected static String pathRelMetierString;

	
	/**
	 * pathPackageConcept : Path :<br/>
	 * <ul>
	 * <li><b>path absolu du package concept</b> 
	 * sous la COUCHE METIER.</li>
	 * <li>Par exemple :<br/> 
	 * <code>./src/main/java/levy/daniel/application/model/metier/profil/
	 * </code>.</li>
	 * </ul>
	 */
	protected static Path pathPackageConcept;
	
	
	/**
	 * pathRelConcept : Path :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du package concept</b> 
	 * sous la COUCHE METIER.</li>
	 * <li>path relatif MODE FILE, c'est à dire avec 
	 * des séparateurs slash.</li> 
	 * <li>Par exemple :<br/>
	 * <code>levy/daniel/application/model/metier/profil
	 * </code>.</li>
	 * </ul>
	 */
	protected static Path pathRelConcept;
	
	
	/**
	 * pathRelConceptString : String :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du package concept</b> 
	 * sous la COUCHE METIER.</li>
	 * <li>path relatif JAVA, c'est à dire avec 
	 * des séparateurs point.</li> 
	 * <li>Par exemple :<br/>
	 * <code>levy.daniel.application.model.metier.profil
	 * </code>.</li>
	 * </ul>
	 */
	protected static String pathRelConceptString;
	
		
	/**
	 * pathPackageConceptImpl : Path :<br/>
	 * <ul>
	 * <li><b>path absolu du sous-package impl du package concept</b> 
	 * sous la COUCHE METIER.</li>
	 * <li>Par exemple :<br/> 
	 * <code>./src/main/java/levy/daniel/application/model/metier/profil/impl/
	 * </code></li>
	 * </ul>
	 */
	protected static Path pathPackageConceptImpl;
	

	/**
	 * pathRelConceptImpl : Path :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du sous-package impl du package concept</b> 
	 * sous la COUCHE METIER.</li>
	 * <li>path relatif MODE FILE, c'est à dire avec 
	 * des séparateurs slash.</li> 
	 * <li>Par exemple :<br/>
	 * <code>levy/daniel/application/model/metier/profil/impl
	 * </code>.</li>
	 * </ul>
	 */
	protected static Path pathRelConceptImpl;
	
		
	/**
	 * pathRelConceptImplString : String :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du sous-package impl du package concept</b> 
	 * sous la COUCHE METIER.</li>
	 * <li>path relatif JAVA, c'est à dire avec 
	 * des séparateurs point.</li> 
	 * <li>Par exemple :<br/>
	 * <code>levy.daniel.application.model.metier.profil.impl
	 * </code>.</li>
	 * </ul>
	 */
	protected static String pathRelConceptImplString;

	
	/**
	 * nomInterfaceMetier : String :<br/>
	 * <b>nom de l'interface de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "IProfil".<br/>
	 */
	protected static String nomInterfaceMetier;
	
	
	/**
	 * nomAbstractClassMetier : String :<br/>
	 * <b>nom de la classe abstraite de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * déduit du nom de l'interface metier 
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "AbstractProfil".<br/>
	 */
	protected static String nomAbstractClassMetier;
	
	
	/**
	 * nomClassMetier : String :<br/>
	 * <b>nom de la classe concrète de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.impl).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "Profil".<br/>
	 */
	protected static String nomClassMetier;
	
			
	/**
	 * nomClassMetierForm : String :<br/>
	 * Nom simple de l'Objet metier "Formulaire" à générer.<br/>
	 * Par exemple "ProfilSimpleForm" pour un ProfilSimple.<br/>
	 */
	protected static String nomClassMetierForm;
	
	
	/**
	 * classMetierForm : File :<br/>
	 * Objet métier "Formulaire" (Fichier Java) à générer.<br/>
	 * Par exemple : ProfilForm.java pour un ProfilSimple.java.<br/>
	 */
	protected static File classMetierForm;

	
	/**
	 * packageDaoMetier : File :<br/>
	 * package metier sous la couche DAO (model.dao.metier).<br/>
	 */
	protected static File packageDaoMetier;
	
	
	/**
	 * packageServicesMetier : File :<br/>
	 * package services sous la couche SERVICES (model.services.metier).<br/>
	 */
	protected static File packageServicesMetier;
	
	
	
	/**
	 * mapAttributs : Map&lt;String,String&gt; :<br/>
	 * <ul>
	 * Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 */
	protected static Map<String, String> mapAttributs;
	
	
	/**
	 * mapAttributsEquals : Map&lt;String,String&gt; :<br/>
	 * <ul>
	 * Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) utilisés dans equals() avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 */
	protected static Map<String, String> mapAttributsEquals;
	
		
	/**
	 * mapRg : Map&lt;String, List&lt;String&gt;&gt; :<br/>
	 * <ul>
	 * Map&lt;String, List&lt;String&gt;&gt; ordonnée 
	 * contenant les listes de RG par attribut avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>List&lt;String&gt; : Liste des RG s'appliquant à l'attribut</li>
	 * </ul>
	 */
	protected static Map<String, List<String>> mapRg;

	
	
	/**
	 * filePackage : File :<br/>
	 * <ul>
	 * <li><b>File modélisant la SOUS-COUCHE METIER (package metier)</b> 
	 * contenant les arborescences à générer pour un Concept
	 * (model/metier, model/dao/metier
	 * , model/services/metier, ...).</li>
	 * <li>par exemple : <br/>
	 * <ul>
	 * <li><code>./src/main/java/levy/daniel/application/model/metier
	 * </code> pour un GenerateurMetierToutAbstract.</li> 
	 * <li><code>./src/main/java/levy/daniel/application/model/dao/metier
	 * </code> pour un GenerateurDaoToutAbstract.</li>
	 * <li><code>./src/test/java/levy/daniel/application/model/metier
	 * </code> pour un GenerateurMetierTest.</li>
	 * </ul>
	 * </ul>
	 */
	protected transient File filePackage;
	
	
	/**
	 * pathPackageString : String :<br/>
	 * <ul>
	 * <li><b>path absolu de la SOUS-COUCHE METIER (package metier)</b> 
	 * contenant les arborescences à générer pour un Concept
	 * (model/metier, model/dao/metier
	 * , model/services/metier, ...).</li>
	 * <li>path sous forme de String.</li>
	 * <li>par exemple : <br/>
	 * <ul>
	 * <li><code>./src/main/java/levy/daniel/application/model/metier
	 * </code> pour un GenerateurMetierToutAbstract.</li> 
	 * <li><code>./src/main/java/levy/daniel/application/model/dao/metier
	 * </code> pour un GenerateurDaoToutAbstract.</li>
	 * <li><code>./src/test/java/levy/daniel/application/model/metier
	 * </code> pour un GenerateurMetierTest.</li>
	 * </ul>
	 * </ul>
	 */
	protected transient String pathPackageString;
	
	
	/**
	 * pathPackage : Path :<br/>
	 * <ul>
	 * <li><b>path absolu de la SOUS-COUCHE METIER (package metier)</b> 
	 * contenant les arborescences à générer pour un Concept
	 * (model/metier, model/dao/metier
	 * , model/services/metier, ...).</li>
	 * <li>path sous forme de java.nio.file.Path.</li>
	 * <li>par exemple : <br/>
	 * <ul>
	 * <li><code>./src/main/java/levy/daniel/application/model/metier
	 * </code> pour un GenerateurMetierToutAbstract.</li> 
	 * <li><code>./src/main/java/levy/daniel/application/model/dao/metier
	 * </code> pour un GenerateurDaoToutAbstract.</li>
	 * <li><code>./src/test/java/levy/daniel/application/model/metier
	 * </code> pour un GenerateurMetierTest.</li>
	 * </ul>
	 * </ul>
	 */
	protected transient Path pathPackage;

	
	/**
	 * pathRelPackage : Path :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du pathPackage</b> 
	 *  (sous la couche voulue).</li>
	 * <li>path relatif MODE FILE, c'est à dire avec 
	 * des séparateurs slash.</li> 
	 * <li>Par exemple :<br/>
	 * <ul>
	 * <li><code>levy/daniel/application/model/metier
	 * </code> pour un GenerateurMetierToutAbstract.</li> 
	 * <li><code>levy/daniel/application/model/dao/metier
	 * </code> pour un GenerateurDaoToutAbstract.</li>
	 * <li><code>levy/daniel/application/model/metier
	 * </code> pour un GenerateurMetierTest.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 */
	protected transient Path pathRelPackage;
	
	
	/**
	 * pathRelPackageJavaString : String :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du pathPackage</b> 
	 *  (sous la couche voulue).</li>
	 * <li>path relatif JAVA, c'est à dire avec 
	 * des séparateurs point.</li> 
	 * <li>Par exemple :<br/>
	 * <ul>
	 * <li><code>levy.daniel.application.model.metier
	 * </code> pour un GenerateurMetierToutAbstract.</li> 
	 * <li><code>levy.daniel.application.model.dao.metier
	 * </code> pour un GenerateurDaoToutAbstract.</li>
	 * <li><code>levy.daniel.application.model.metier
	 * </code> pour un GenerateurMetierTest.</li>
	 * </ul>
	 * </ul>
	 */
	protected transient String pathRelPackageJavaString;
	
	
	
	/**
	 * packageSousCouche : File :<br/>
	 * <ul>
	 * <li><b>Package du fichier java à générer</b> 
	 * sous pathPackageString/packageSousCouche.</li>
	 * <li>par exemple : <br/>
	 * <ul>
	 * <li><code>./src/main/java/
	 * levy/daniel/application/model/metier/profil
	 * </code> pour un GenerateurMetierToutAbstract 
	 * et un Concept Profil.</li> 
	 * <li><code>./src/main/java/
	 * levy/daniel/application/model/dao/metier/profil
	 * </code> pour un GenerateurDaoToutAbstract 
	 * et un Concept Profil.</li>
	 * <li><code>./src/test/java/
	 * levy/daniel/application/model/metier/profil
	 * </code> pour un GenerateurMetierTest 
	 * et un Concept Profil.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 */
	protected transient File packageSousCouche;
	
	
	/**
	 * pathSousCoucheString : String :<br/>
	 * <ul>
	 * <li><b>path absolu de la SOUS-COUCHE CONCEPT</b> 
	 * contenant les arborescences à générer pour un Concept
	 * (model/metier/concept, model/dao/metier/concept
	 * , model/services/metier/concept, ...).</li>
	 * <li>path sous forme de String.</li>
	 * <li>par exemple : <br/>
	 * <ul>
	 * <li><code>./src/main/java/
	 * levy/daniel/application/model/metier/profil
	 * </code> pour un GenerateurMetierToutAbstract 
	 * et un Concept Profil.</li> 
	 * <li><code>./src/main/java/
	 * levy/daniel/application/model/dao/metier/profil
	 * </code> pour un GenerateurDaoToutAbstract 
	 * et un Concept Profil.</li>
	 * <li><code>./src/test/java/
	 * levy/daniel/application/model/metier/profil
	 * </code> pour un GenerateurMetierTest 
	 * et un Concept Profil.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 */
	protected transient String pathSousCoucheString;
	
	
	/**
	 * pathSousCouche : Path :<br/>
	 * <ul>
	 * <li><b>path absolu de la SOUS-COUCHE CONCEPT</b> 
	 * contenant les arborescences à générer pour un Concept
	 * (model/metier/concept, model/dao/metier/concept
	 * , model/services/metier/concept, ...).</li>
	 * <li>path sous forme de java.nio.file.Path.</li>
	 * <li>par exemple : <br/>
	 * <ul>
	 * <li><code>./src/main/java/
	 * levy/daniel/application/model/metier/profil
	 * </code> pour un GenerateurMetierToutAbstract 
	 * et un Concept Profil.</li> 
	 * <li><code>./src/main/java/
	 * levy/daniel/application/model/dao/metier/profil
	 * </code> pour un GenerateurDaoToutAbstract 
	 * et un Concept Profil.</li>
	 * <li><code>./src/test/java/
	 * levy/daniel/application/model/metier/profil
	 * </code> pour un GenerateurMetierTest 
	 * et un Concept Profil.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 */
	protected transient Path pathSousCouche;
	
	
	/**
	 * pathRelSousCouche : Path :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du pathSousCouche</b> 
	 *  (sous la couche voulue).</li>
	 * <li>path relatif MODE FILE, c'est à dire avec 
	 * des séparateurs slash.</li> 
	 * <li>Par exemple :<br/>
	 * <ul>
	 * <li><code>levy/daniel/application/model/metier/profil
	 * </code> pour un GenerateurMetierToutAbstract 
	 * et un Concept Profil.</li> 
	 * <li><code>levy/daniel/application/model/dao/metier/profil
	 * </code> pour un GenerateurDaoToutAbstract 
	 * et un Concept Profil.</li>
	 * <li><code>levy/daniel/application/model/metier/profil
	 * </code> pour un GenerateurMetierTest et un Concept Profil.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 */
	protected transient Path pathRelSousCouche;
	
	
	/**
	 * pathRelSousCoucheJavaString : String :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du pathSousCouche</b> 
	 *  (sous la couche voulue).</li>
	 * <li>path relatif JAVA, c'est à dire avec 
	 * des séparateurs point.</li> 
	 * <li>Par exemple :<br/>
	 * <ul>
	 * <li><code>levy.daniel.application.model.metier.profil
	 * </code> pour un GenerateurMetierToutAbstract 
	 * et un Concept Profil.</li> 
	 * <li><code>levy.daniel.application.model.dao.metier.profil
	 * </code> pour un GenerateurDaoToutAbstract 
	 * et un Concept Profil.</li>
	 * <li><code>levy.daniel.application.model.metier.profil
	 * </code> pour un GenerateurMetierTest et un Concept Profil.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 */
	protected transient String pathRelSousCoucheJavaString;

	
	/**
	 * sousPackageImpl : File :<br/>
	 * <ul>
	 * <li><b>Package du fichier java à générer</b> 
	 * sous pathPackageString/packageSousCouche/impl.</li>
	 * <li>par exemple : <br/>
	 * <ul>
	 * <li><code>./src/main/java/
	 * levy/daniel/application/model/metier/profil/impl
	 * </code> pour un GenerateurMetierToutAbstract 
	 * et un Concept Profil.</li> 
	 * <li><code>./src/main/java/
	 * levy/daniel/application/model/dao/metier/profil/impl
	 * </code> pour un GenerateurDaoToutAbstract 
	 * et un Concept Profil.</li>
	 * <li><code>./src/test/java/
	 * levy/daniel/application/model/metier/profil/impl
	 * </code> pour un GenerateurMetierTest 
	 * et un Concept Profil.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 */
	protected transient File sousPackageImpl;

	
	/**
	 * pathSousPackageImplString : String :<br/>
	 * <ul>
	 * <li><b>path absolu de la SOUS-COUCHE CONCEPT/impl</b> 
	 * contenant les arborescences à générer pour un Concept
	 * (model/metier/concept/impl, model/dao/metier/concept/impl
	 * , model/services/metier/concept/impl, ...).</li>
	 * <li>path sous forme de String.</li>
	 * <li>par exemple : <br/>
	 * <ul>
	 * <li><code>./src/main/java/
	 * levy/daniel/application/model/metier/profil/impl
	 * </code> pour un GenerateurMetierToutAbstract 
	 * et un Concept Profil.</li> 
	 * <li><code>./src/main/java/
	 * levy/daniel/application/model/dao/metier/profil/impl
	 * </code> pour un GenerateurDaoToutAbstract 
	 * et un Concept Profil.</li>
	 * <li><code>./src/test/java/
	 * levy/daniel/application/model/metier/profil/impl
	 * </code> pour un GenerateurMetierTest 
	 * et un Concept Profil.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 */
	protected transient String pathSousPackageImplString;
	
	
	/**
	 * pathSousPackageImpl : Path :<br/>
	 * <ul>
	 * <li><b>path absolu de la SOUS-COUCHE CONCEPT/impl</b> 
	 * contenant les arborescences à générer pour un Concept
	 * (model/metier/concept/impl, model/dao/metier/concept/impl
	 * , model/services/metier/concept/impl, ...).</li>
	 * <li>path sous forme de java.nio.file.Path.</li>
	 * <li>par exemple : <br/>
	 * <ul>
	 * <li><code>./src/main/java/
	 * levy/daniel/application/model/metier/profil/impl
	 * </code> pour un GenerateurMetierToutAbstract 
	 * et un Concept Profil.</li> 
	 * <li><code>./src/main/java/
	 * levy/daniel/application/model/dao/metier/profil/impl
	 * </code> pour un GenerateurDaoToutAbstract 
	 * et un Concept Profil.</li>
	 * <li><code>./src/test/java/
	 * levy/daniel/application/model/metier/profil/impl
	 * </code> pour un GenerateurMetierTest 
	 * et un Concept Profil.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 */
	protected transient Path pathSousPackageImpl;
	
	
	/**
	 * pathRelSousPackageImpl : Path :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du pathSousPackageImpl</b> 
	 *  (sous la couche voulue).</li>
	 * <li>path relatif MODE FILE, c'est à dire avec 
	 * des séparateurs slash.</li> 
	 * <li>Par exemple :<br/>
	 * <ul>
	 * <li><code>levy/daniel/application/model/metier/profil/impl
	 * </code> pour un GenerateurMetierToutAbstract 
	 * et un Concept Profil.</li> 
	 * <li><code>levy/daniel/application/model/dao/metier/profil/impl
	 * </code> pour un GenerateurDaoToutAbstract 
	 * et un Concept Profil.</li>
	 * <li><code>levy/daniel/application/model/metier/profil/impl
	 * </code> pour un GenerateurMetierTest et un Concept Profil.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 */
	protected transient Path pathRelSousPackageImpl;
	
	
	/**
	 * pathRelSousPackageImplJavaString : String :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du pathSousPackageImpl</b> 
	 *  (sous la couche voulue).</li>
	 * <li>path relatif JAVA, c'est à dire avec 
	 * des séparateurs point.</li> 
	 * <li>Par exemple :<br/>
	 * <ul>
	 * <li><code>levy.daniel.application.model.metier.profil.impl
	 * </code> pour un GenerateurMetierToutAbstract 
	 * et un Concept Profil.</li> 
	 * <li><code>levy.daniel.application.model.dao.metier.profil.impl
	 * </code> pour un GenerateurDaoToutAbstract 
	 * et un Concept Profil.</li>
	 * <li><code>levy.daniel.application.model.metier.profil.impl
	 * </code> pour un GenerateurMetierTest et un Concept Profil.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 */
	protected transient String pathRelSousPackageImplJavaString;
	
	
	
	/**
	 * nomSimpleInterface : String :<br/>
	 * Nom simple de l'interface java à générer.<br/>
	 * Par exemple "IProfil" pour l'objet métier 
	 * Profil.java ou "IDaoProfil" pour l'objet DAO 
	 * DaoProfilSimple.java.<br/>
	 */
	protected transient String nomSimpleInterface;
	
	
	/**
	 * interfaceJava : File :<br/>
	 * Interface (Fichier Java) de l'objet métier à générer.<br/>
	 * Par exemple : "IProfil.java" pour l'objet métier 
	 * Profil.java<br/>
	 */
	protected transient File interfaceJava;

	
	/**
	 * nomSimpleAbstractClass : String :<br/>
	 * Nom simple de la Classe Abstraite à générer.<br/>
	 * Par exemple "AbstractProfil".<br/>
	 */
	protected transient String nomSimpleAbstractClass;
	
	
	/**
	 * abstractClass : File :<br/>
	 * Classe Abstraite (Fichier Java) de l'objet métier à générer.<br/>
	 * Par exemple : "AbstractProfil.java" pour l'objet métier 
	 * Profil.java<br/>
	 */
	protected transient File abstractClass;
	
		
	/**
	 * nomSimpleConcreteClass : String :<br/>
	 * Nom simple du Fichier Java concret à générer.<br/>
	 * Par exemple : <br/>
	 * <code>ProfilSimple</code> dans la couche metier.profil 
	 * ou <code>DaoProfilSimple</code> dans 
	 * la couche DAO.metier.profil.<br/>
	 */
	protected transient String nomSimpleConcreteClass;
	
	
	/**
	 * concreteClass : File :<br/>
	 * Fichier Java concret à générer.<br/>
	 * Par exemple : <br/>
	 * <code>Profil.java</code> dans la couche metier.profil 
	 * ou <code>DaoProfil.java</code> dans 
	 * la couche DAO.metier.profil.<br/>
	 */
	protected transient File concreteClass;
	
	


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(AbstractGenerateur.class);

	// *************************METHODES************************************/
	
	 /**
	 * method CONSTRUCTEUR GenerateurMetierToutAbstract() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>alimente dans chaque <b>Generateur concret</b> this.pathPackage 
	 * en demandant le chemin du package au 
	 * <b>BundleConfigurationProjetManager</b>.</li>
	 * </ul>
	 * <br/>
	 */
	public AbstractGenerateur() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * method configurerInterfaceSeule(
	 * String pNomPackage
	 * , String pNomInterface
	 * , String pNomObjetMetier
	 * , Map&lt;String, String&gt; pMapAttributs
	 * , Map<String, String> pMapAttributsEquals
	 * , Map&lt;String, List&lt;String&gt;&gt; pMapRg) :<br/>
	 * <ul>
	 * </ul>
	 *
	 * @param pNomPackage
	 * @param pNomInterface
	 * @param pNomObjetMetier
	 * @param pMapAttributs
	 * @param pMapAttributsEquals
	 * @param pMapRg
	 * 
	 * @throws Exception
	 */
	public static void configurerInterfaceSeule(
			final String pNomPackage
				, final String pNomInterface
					, final String pNomObjetMetier
			, final Map<String, String> pMapAttributs
				, final Map<String, String> pMapAttributsEquals
					, final Map<String, List<String>> pMapRg) 
								throws Exception {
				
		synchronized (AbstractGenerateur.class) {
			
			
			/* retourne si pNomPackage est blank. */
			if (StringUtils.isAllBlank(pNomPackage)) {
				return;
			}
			
			/* retourne si pNomInterface est blank. */
			if (StringUtils.isAllBlank(pNomInterface)) {
				return;
			}
			
			/* retourne si pNomObjetMetier est blank. */
			if (StringUtils.isAllBlank(pNomObjetMetier)) {
				return;
			}
			
			/* retourne si pNomPackage n'est pas conforme. */
			if (!conformeNomPackage(pNomPackage)) {
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal("Nom de package non conforme : " 
							+ pNomPackage);
				}
				
				return;
			}
			
			/* retourne si pNomInterface n'est pas conforme. */
			if (!conformeNomInterface(pNomInterface)) {
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal("Nom d'interface non conforme : " 
							+ pNomInterface);
				}
				
				return;
			}
			
			/* retourne si pNomObjetMetier n'est pas conforme. */
			if (!conformeNomClasse(pNomObjetMetier)) {
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal("Nom de classe non conforme : " 
							+ pNomObjetMetier);
				}
				
				return;
			}

			/* garnit le packageMetier. */
			/* génère et garnit packageDaoMetier. */
			genererEtGarnirPackages();
			
		} // Fin de synchronized.___________________________________
		
	} // Fin de configurerInterfaceSeule(...)._____________________________
	
	
	
	/**
	 * method configurer(
	 * String pNomPackage
	 * , String pNomInterface
	 * , String pNomObjetMetier
	 * , Map&lt;String, String&gt; pMapAttributs
	 * , Map<String, String> pMapAttributsEquals
	 * , Map&lt;String, List&lt;String&gt;&gt; pMapRg) :<br/>
	 * <ul>
	 * <li>alimente tous les attributs static des Générateurs.</li>
	 * </ul>
	 * retourne si pNomPackage est blank.<br/>
	 * retourne si pNomInterface est blank.<br/>
	 * retourne si pNomObjetMetier est blank.<br/>
	 * retourne si pNomPackage n'est pas conforme.<br/>
	 * retourne si pNomInterface n'est pas conforme.<br/>
	 * <br/>
	 *
	 * @param pNomPackage : String : .<br/>
	 * @param pNomInterface : String : .<br/>
	 * @param pNomObjetMetier : String : .<br/>
	 * @param pMapAttributs : Map&lt;String, String&gt; :  .<br/>
	 * @param pMapAttributsEquals : Map&lt;String, String&gt; : .<br/>
	 * @param pMapRg : Map&lt;String, List&lt;String&gt;&gt; :  .<br/>
	 * 
	 * @throws Exception 
	 */
	public static void configurer(
			final String pNomPackage
				, final String pNomInterface
					, final String pNomObjetMetier
			, final Map<String, String> pMapAttributs
				, final Map<String, String> pMapAttributsEquals
					, final Map<String, List<String>> pMapRg) 
								throws Exception {
		
		synchronized (AbstractGenerateur.class) {
			
			/* retourne si pNomPackage est blank. */
			if (StringUtils.isAllBlank(pNomPackage)) {
				return;
			}
			
			/* retourne si pNomInterface est blank. */
			if (StringUtils.isAllBlank(pNomInterface)) {
				return;
			}
			
			/* retourne si pNomObjetMetier est blank. */
			if (StringUtils.isAllBlank(pNomObjetMetier)) {
				return;
			}
			
			/* retourne si pNomPackage n'est pas conforme. */
			if (!conformeNomPackage(pNomPackage)) {
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal("Nom de package non conforme : " 
							+ pNomPackage);
				}
				
				return;
			}
			
			/* retourne si pNomInterface n'est pas conforme. */
			if (!conformeNomInterface(pNomInterface)) {
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal("Nom d'interface non conforme : " 
							+ pNomInterface);
				}
				
				return;
			}
			
			/* retourne si pNomObjetMetier n'est pas conforme. */
			if (!conformeNomClasse(pNomObjetMetier)) {
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal("Nom de classe non conforme : " 
							+ pNomObjetMetier);
				}
				
				return;
			}
						
			/* alimente tous les attributs static des Générateurs. */
			alimenterAttributsStatic(pNomPackage
					, pNomInterface
						, pNomObjetMetier
							, pMapAttributs
								, pMapAttributsEquals
									, pMapRg);

			/* garnit le packageMetier. */
			/* génère et garnit packageDaoMetier. */
			genererEtGarnirPackages();
			
		} // Fin de synchronized.___________________________________
				
	} // Fin de configurer(...).___________________________________________
	

	
	/**
	 * method genererEtGarnirPackages() :<br/>
	 * <ul>
	 * <li><b>génère et garnit le cas échéant 
	 * tous les packages du projet cible</b>.</li>
	 * <li>garnit éventuellement le packageMetier 
	 * avec les interfaces IExportateurCsv et IExportateurJTable.</li>
	 * <li>génère éventuellement le packageDaoMetier 
	 * et le garnit éventuellement avec 
	 * IDaoGenericJPASpring et AbstractDAOGenericJPASpring.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void genererEtGarnirPackages() throws Exception {
		
		synchronized (AbstractGenerateur.class) {
			
			/* garnit le packageMetier. */
			garnirPackageMetier();
			
			/* génère et garnit packageDaoMetier. */
			genererPackageDaoMetier();
			
		} // Fin de synchronized._________________________________
		
	}
	
	
	/**
	 * method garnirPackageMetier() :<br/>
	 * <ul>
	 * <li><b>alimente packageMetier</b> avec la valeur fournie 
	 * par le GestionnaireProjet.</li>
	 * <li>alimente pathPackageMetier.</li>
	 * <li><b>alimente pathPackageConcept</b> à partir 
	 * de packageMetier et nomPackage.</li>
	 * <li><b>alimente pathRelConcept</b> à partir 
	 * de PATH_MAIN_JAVA et pathPackageConcept.</li>
	 * <li><b>alimente pathRelConceptString</b> à partir 
	 * de PATH_MAIN_JAVA et pathPackageConcept.</li>
	 * <li><b>alimente pathPackageConceptImpl</b> à partir 
	 * de packageMetier et nomPackage.</li>
	 * <li><b>alimente pathRelConceptImpl</b> à partir 
	 * de PATH_MAIN_JAVA et pathPackageConceptImpl.</li>
	 * <li><b>alimente pathRelConceptImplString</b> à partir 
	 * de PATH_MAIN_JAVA et pathPackageConceptImpl.</li>
	 * <li>génère si nécessaire l'interface IExportateurCsv 
	 * sous model.metier.</li>
	 * <li>génère si nécessaire l'interface IExportateurJTable 
	 * sous model.metier.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void garnirPackageMetier() throws Exception {
		
		synchronized (AbstractGenerateur.class) {
			
			if (packageMetier == null) {
				
				/* alimente packageMetier avec la valeur fournie 
				 * par le GestionnaireProjet. */
				/* alimente pathPackageMetier. */
				/* alimente pathRelMetier. */
				/* alimente pathRelMetierString. */
				alimenterMetier();
				
				/* alimente pathPackageConcept. */
				alimenterPathPackageConcept();
				
				/* alimente pathRelConcept. */
				alimenterPathRelConcept();
				
				/* alimente pathRelConceptString. */
				alimenterPathRelConceptString();
				
				/* alimente pathPackageConceptImpl. */
				alimenterPathPackageConceptImpl();
				
				/* alimente pathRelConceptImpl. */
				alimenterPathRelConceptImpl();
				
				/* alimente pathPackageConceptImpl. */
				alimenterPathRelConceptImplString();
			}
			
			genererInterfaceIExportateurCsv();
			
			genererInterfaceIExportateurJTable();
			
		} // Fin de synchronized._________________________________
				
	} // Fin de garnirPackageMetier()._____________________________________
	

	
	/**
	 * method alimenterMetier() :<br/>
	 * <ul>
	 * <li><b>alimente packageMetier avec la valeur fournie 
	 * par le GestionnaireProjet</b>.</li>
	 * <li><b>alimente pathPackageMetier</b>.</li>
	 * <li><b>alimente pathRelMetier</b> à partir 
	 * de PATH_MAIN_JAVA et pathPackageMetier.</li>
	 * <li><b>alimente pathRelMetierString</b>.</li>
	 * </ul>
	 */
	private static void alimenterMetier() {
		
		synchronized (AbstractGenerateur.class) {
			
			/* alimente packageMetier avec la valeur fournie 
			 * par le GestionnaireProjet. */
			packageMetier 
				= GestionnaireProjet.getFileMetierMainJava();
			
			/* alimente pathPackageMetier. */
			pathPackageMetier 
				= packageMetier.toPath();
			
			/* alimente pathRelMetier. */
			pathRelMetier 
				= PATH_MAIN_JAVA.relativize(pathPackageMetier);
			
			/* alimente pathRelMetierString. */
			final String pathRelMetierStringAntiSlash 
				= pathRelMetier.toString();
					
			pathRelMetierString 
				= remplacerAntiSlashparPoint(
						pathRelMetierStringAntiSlash);
			
		} // Fin de synchronized._________________________________
		
	} // Fin de alimenterMetier()._________________________________________

	
	
	/**
	 * method alimenterPathPackageConcept() :<br/>
	 * <ul>
	 * <li><b>alimente pathPackageMetier</b> 
	 * à partir de packageMetier.</li>
	 * <li><b>alimente pathPackageConcept</b> à partir 
	 * de packageMetier et nomPackage.</li>
	 * </ul>
	 */
	private static void alimenterPathPackageConcept() {
		
		synchronized (AbstractGenerateur.class) {
						
			pathPackageConcept 
				= pathPackageMetier.resolve(nomPackage);
						
		} // Fin de synchronized._________________________________
					
	} // Fin de alimenterPathPackageConcept()._____________________________
	

	
	/**
	 * method alimenterPathRelConcept() :<br/>
	 * <ul>
	 * <li><b>alimente pathRelConcept</b> à partir 
	 * de PATH_MAIN_JAVA et pathPackageConcept.</li>
	 * </ul>
	 */
	private static void alimenterPathRelConcept() {
		
		synchronized (AbstractGenerateur.class) {
			
			pathRelConcept 
				= PATH_MAIN_JAVA.relativize(pathPackageConcept);
			
		} // Fin de synchronized._________________________________
		
	} // Fin de alimenterPathRelConcept()._________________________________
	
	
	
	/**
	 * method alimenterPathRelConceptString() :<br/>
	 * <ul>
	 * <li><b>alimente pathRelConceptString</b> à partir 
	 * de PATH_MAIN_JAVA et pathPackageConcept.</li>
	 * </ul>
	 */
	private static void alimenterPathRelConceptString() {
		
		synchronized (AbstractGenerateur.class) {
			
			final String pathRelConceptStringAntiSlash 
				= pathRelConcept.toString();
						
			pathRelConceptString 
				= remplacerAntiSlashparPoint(
						pathRelConceptStringAntiSlash);
			
		} // Fin de synchronized._________________________________
		
	} // Fin de alimenterPathRelConceptString().___________________________


	
	/**
	 * method alimenterPathPackageConceptImpl() :<br/>
	 * <ul>
	 * <li><b>alimente pathPackageConceptImpl</b> à partir 
	 * de pathPackageMetier et nomPackage.</li>
	 * </ul>
	 */
	private static void alimenterPathPackageConceptImpl() {
		
		synchronized (AbstractGenerateur.class) {
						
			final Path pathPackageMetierConcept 
				= pathPackageMetier.resolve(nomPackage);
			
			pathPackageConceptImpl 
				= pathPackageMetierConcept.resolve("impl");
			
		} // Fin de synchronized._________________________________
					
	} // Fin de alimenterPathPackageConceptImpl()._________________________
	

	
	/**
	 * method alimenterPathRelConceptImpl() :<br/>
	 * <ul>
	 * <li><b>alimente pathRelConceptImpl</b> à partir 
	 * de PATH_MAIN_JAVA et pathPackageConceptImpl.</li>
	 * </ul>
	 */
	private static void alimenterPathRelConceptImpl() {
		
		synchronized (AbstractGenerateur.class) {
			
			pathRelConceptImpl 
				= PATH_MAIN_JAVA.relativize(pathPackageConceptImpl);
			
		} // Fin de synchronized._________________________________
		
	} // Fin de alimenterPathRelConceptImpl()._____________________________

	
	
	/**
	 * method alimenterPathRelConceptImplString() :<br/>
	 * <ul>
	 * <li><b>alimente pathRelConceptImplString</b> à partir 
	 * de PATH_MAIN_JAVA et pathPackageConceptImpl.</li>
	 * </ul>
	 */
	private static void alimenterPathRelConceptImplString() {
		
		synchronized (AbstractGenerateur.class) {
						
			final String pathRelConceptImplStringAntiSlash 
				= pathRelConceptImpl.toString();
						
			pathRelConceptImplString 
				= remplacerAntiSlashparPoint(
						pathRelConceptImplStringAntiSlash);
			
		} // Fin de synchronized._________________________________
		
	} // Fin de alimenterPathRelConceptImplString()._______________________

	
	
	/**
	 * method genererInterfaceIExportateurCsv() :<br/>
	 * <ul>
	 * <li>génère si nécessaire l'interface IExportateurCsv 
	 * sous model.metier.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void genererInterfaceIExportateurCsv() 
				throws Exception {

		synchronized (AbstractGenerateur.class) {
						
			final String nomFichier = "IExportateurCsv.java";
			
			final IGestionnaireFiles gestionnaireFiles 
				= new GestionnaireFiles();
			
			/* création du fichier vide. */
			final File iExportateurCsv 
				= gestionnaireFiles
				.creerFichierDansPackage(
						nomFichier, packageMetier);
			
			final List<String> listeCode = new ArrayList<String>();
			
			/* ENREGISTREMENT *********/
			creerLignesInterfaceIExportateurCsv(listeCode);
			
			/* Recherche la ligne identifiant le code de l'interface. */
			final String ligneIdentifiant 
				= "public interface IExportateurCsv";
			
			/* Ne fait rien si le code est déjà existant. */
			if (existLigneCommencant(
					iExportateurCsv, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			ecrireCode(listeCode, iExportateurCsv);

		} // Fin de synchronized._________________________________
	
	} // Fin de genererInterfaceIExportateurCsv()._________________________
	

	
	/**
	 * method creerLignesInterfaceIExportateurCsv(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>Crée le code de l'interface IExportateurCsv.</li>
	 * <li>Insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; pListe.<br/>
	 * 
	 * @throws Exception
	 */
	private static void creerLignesInterfaceIExportateurCsv(
			final List<String> pListe) throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/IExportateurCsv.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= lireStringsDansFile(fichier, CHARSET_UTF8);
					
		pListe.addAll(listeLignes);
				
	} // Fin de creerLignesInterfaceIExportateurCsv(...).__________________

	
	
	/**
	 * method genererInterfaceIExportateurJTable() :<br/>
	 * <ul>
	 * <li>génère si nécessaire l'interface IExportateurJTable 
	 * sous model.metier.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void genererInterfaceIExportateurJTable() 
				throws Exception {
			
		synchronized (AbstractGenerateur.class) {	
					
			final String nomFichier = "IExportateurJTable.java";
			
			final IGestionnaireFiles gestionnaireFiles 
				= new GestionnaireFiles();
			
			/* création du fichier vide. */
			final File iExportateurJTable 
				= gestionnaireFiles
				.creerFichierDansPackage(
						nomFichier, packageMetier);
			
			final List<String> listeCode = new ArrayList<String>();
			
			/* ENREGISTREMENT *********/
			creerLignesInterfaceIExportateurJTable(listeCode);
			
			/* Recherche la ligne identifiant le code de l'interface. */
			final String ligneIdentifiant 
				= "public interface IExportateurJTable";
			
			/* Ne fait rien si le code est déjà existant. */
			if (existLigneCommencant(
					iExportateurJTable
						, CHARSET_UTF8
							, ligneIdentifiant)) {
				return;
			}
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			ecrireCode(listeCode, iExportateurJTable);
		
		} // Fin de synchronized._________________________________
		
	} // Fin de genererInterfaceIExportateurJTable().______________________
	

	
	/**
	 * method creerLignesInterfaceIExportateurJTable(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>Crée le code de l'interface IExportateurJTable.</li>
	 * <li>Insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; pListe.<br/>
	 * 
	 * @throws Exception
	 */
	private static void creerLignesInterfaceIExportateurJTable(
			final List<String> pListe) throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/IExportateurJTable.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= lireStringsDansFile(fichier, CHARSET_UTF8);
					
		pListe.addAll(listeLignes);
				
	} // Fin de creerLignesInterfaceIExportateurJTable(...)._______________


	
	/**
	 * method genererPackageDaoMetier() :<br/>
	 * <ul>
	 * <li><b>alimente packageDaoMetier</b> avec la valeur fournie 
	 * par le GestionnaireProjet.</li>
	 * <li>génère si nécessaire le package metier sous DAO
	 * <b>packageDaoMetier</b> (model.dao.metier).</li>
	 * <li>génère si nécessaire l'interface IDaoGenericJPASpring.java 
	 * sous model.dao.metier.</li>
	 * <li>génère si nécessaire la classe abstraite 
	 * AbstractDaoGenericJPASpring.java sous model.dao.metier.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void genererPackageDaoMetier() throws Exception {
		
		synchronized (AbstractGenerateur.class) {
			
			final String pathDaoString 
			= GestionnaireProjet.getPathDaoMainJavaString();
		
			if (packageDaoMetier == null) {
				
				final IGestionnaireFiles gestionnaireFiles 
					= new GestionnaireFiles();
				
				packageDaoMetier 
					= gestionnaireFiles
						.creerSousPackage(pathDaoString, "metier");
			}
			
			genererIDaoGenericJPASpring();
			
			genererAbstractDaoGenericJPASpring();
			
			genererDaoExceptions();
			
		} // Fin de synchronized._________________________________
						
	} // Fin de genererPackageDaoMetier()._________________________________
	

	
	/**
	 * method genererIDaoGenericJPASpring() :<br/>
	 * <ul>
	 * <li>génère si nécessaire l'interface 
	 * IDaoGenericJPASpring sous model.dao.metier.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void genererIDaoGenericJPASpring() 
			throws Exception {

		synchronized (AbstractGenerateur.class) {
						
			final String nomFichier = "IDaoGenericJPASpring.java";
			
			final IGestionnaireFiles gestionnaireFiles 
				= new GestionnaireFiles();
			
			/* récupération du package des DAOs. */
			final String pathPackageDao 
				= GestionnaireProjet.getPathDaoMainJavaString();
			
			final File packageDao = new File(pathPackageDao);
			
			/* création du fichier vide. */
			final File iDaoGenericJPASpring 
				= gestionnaireFiles
				.creerFichierDansPackage(
						nomFichier, packageDao);
			
			final List<String> listeCode = new ArrayList<String>();
			
			/* ENREGISTREMENT *********/
			creerLignesIDaoGenericJPASpring(listeCode);
			
			/* Recherche la ligne identifiant le code de l'interface. */
			final String ligneIdentifiant 
				= "public interface IDaoGenericJPASpring";
			
			/* Ne fait rien si le code est déjà existant. */
			if (existLigneCommencant(
					iDaoGenericJPASpring, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			ecrireCode(listeCode, iDaoGenericJPASpring);

		} // Fin de synchronized._________________________________
	
	} // Fin de genererIDaoGenericJPASpring()._____________________________
	

	
	/**
	 * method creerLignesIDaoGenericJPASpring(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>Crée le code de l'interface IDaoGenericJPASpring.</li>
	 * <li>Insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; pListe.<br/>
	 * 
	 * @throws Exception
	 */
	private static void creerLignesIDaoGenericJPASpring(
			final List<String> pListe) throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/dao/interface_idaogenericjpaspring.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= lireStringsDansFile(fichier, CHARSET_UTF8);
					
		pListe.addAll(listeLignes);
				
	} // Fin de creerLignesIDaoGenericJPASpring(...).______________________
	
	
	
	/**
	 * method genererAbstractDaoGenericJPASpring() :<br/>
	 * <ul>
	 * <li>génère si nécessaire la classe abstraite 
	 * AbstractDaoGenericJPASpring.java sous model.dao.metier.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void genererAbstractDaoGenericJPASpring() 
			throws Exception {

		synchronized (AbstractGenerateur.class) {
						
			final String nomFichier = "AbstractDaoGenericJPASpring.java";
			
			final IGestionnaireFiles gestionnaireFiles 
				= new GestionnaireFiles();
			
			/* récupération du package des DAOs. */
			final String pathPackageDao 
				= GestionnaireProjet.getPathDaoMainJavaString();
			
			final File packageDao = new File(pathPackageDao);
			
			/* création du fichier vide. */
			final File abstractDaoGenericJPASpring 
				= gestionnaireFiles
				.creerFichierDansPackage(
						nomFichier, packageDao);
			
			final List<String> listeCode = new ArrayList<String>();
			
			/* ENREGISTREMENT *********/
			creerLignesAbstractDaoGenericJPASpring(listeCode);
			
			/* Recherche la ligne identifiant le code de l'interface. */
			final String ligneIdentifiant 
				= "public abstract class AbstractDaoGenericJPASpring";
			
			/* Ne fait rien si le code est déjà existant. */
			if (existLigneCommencant(
					abstractDaoGenericJPASpring
						, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			ecrireCode(listeCode, abstractDaoGenericJPASpring);

		} // Fin de synchronized._________________________________
	
	} // Fin de genererAbstractDaoGenericJPASpring().______________________
	

	
	/**
	 * method creerLignesAbstractDaoGenericJPASpring(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>Crée le code de la classe abstraite 
	 * AbstractDaoGenericJPASpring.</li>
	 * <li>Insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; pListe.<br/>
	 * 
	 * @throws Exception
	 */
	private static void creerLignesAbstractDaoGenericJPASpring(
			final List<String> pListe) throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/dao/abstractclass_abstractdaogenericjpaspring.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= lireStringsDansFile(fichier, CHARSET_UTF8);
					
		pListe.addAll(listeLignes);
				
	} // Fin de creerLignesAbstractDaoGenericJPASpring(...)._______________
	

	
	/**
	 * method genererDaoExceptions() :<br/>
	 * <ul>
	 * <li>génère le répertoire daoexceptions 
	 * dans le projet cible si il n'existe pas.</li>
	 * <li>recopie tout le contenu de daoexceptions 
	 * dans le projet cible si il n'existe pas.</li>
	 * </ul>
	 */
	private static void genererDaoExceptions() {
		
		synchronized (AbstractGenerateur.class) {
			
			/* répertoire daoexceptions à recopier. */
			final File repSource 
				= new File("./src/main/java/levy/daniel/"
						+ "application/model/dao/daoexceptions");
			
			final Path pathDao 
				= GestionnaireProjet.getPathDaoMainJava();
			final Path pathDestination 
				= pathDao.resolve("daoexceptions");
			final File repDestination 
				= pathDestination.toFile();
			
			if (!repDestination.exists()) {
				
				/* création du répertoire daoExceptions 
				 * dans le projet cible. */
				GestionnaireFichiers
					.copierRepertoireSansRemplacement(
							repSource, repDestination);
			}
						
		} // Fin de synchronized._________________________________
		
	} // Fin de genererDaoExceptions().____________________________________


	
	/**
	 * method genererPackageServicesMetier() :<br/>
	 * <ul>
	 * <li><b>alimente packageServicesMetier</b> 
	 * avec la valeur fournie 
	 * par le GestionnaireProjet.</li>
	 * <li>génère si nécessaire le package metier sous SERVICES
	 * <b>packageServicesMetier</b> (model.services.metier).</li>
	 * <li>génère si nécessaire l'interface 
	 * IServicesGenericJPASpring.java 
	 * sous model.services.metier.</li>
	 * <li>génère si nécessaire la classe abstraite 
	 * AbstractServicesGenericJPASpring.java 
	 * sous model.services.metier.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void genererPackageServicesMetier() throws Exception {
		
		synchronized (AbstractGenerateur.class) {
			
			final String pathServicesString 
			= GestionnaireProjet.getPathServicesMainJavaString();
		
			if (packageServicesMetier == null) {
				
				final IGestionnaireFiles gestionnaireFiles 
					= new GestionnaireFiles();
				
				packageServicesMetier 
					= gestionnaireFiles
						.creerSousPackage(pathServicesString, "metier");
			}
			
			genererIServicesGenericJPASpring();
			
			genererAbstractServicesGenericJPASpring();
						
		} // Fin de synchronized._________________________________
						
	} // Fin de genererPackageServicesMetier()._________________________________
	

	
	/**
	 * method genererIServicesGenericJPASpring() :<br/>
	 * <ul>
	 * <li>génère si nécessaire l'interface 
	 * IServicesGenericJPASpring sous model.services.metier.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void genererIServicesGenericJPASpring() 
			throws Exception {

		synchronized (AbstractGenerateur.class) {
						
			final String nomFichier = "IServicesGenericJPASpring.java";
			
			final IGestionnaireFiles gestionnaireFiles 
				= new GestionnaireFiles();
			
			/* récupération du package des DAOs. */
			final String pathPackageServices 
				= GestionnaireProjet.getPathServicesMainJavaString();
			
			final File packageServices = new File(pathPackageServices);
			
			/* création du fichier vide. */
			final File iServicesGenericJPASpring 
				= gestionnaireFiles
				.creerFichierDansPackage(
						nomFichier, packageServices);
			
			final List<String> listeCode = new ArrayList<String>();
			
			/* ENREGISTREMENT *********/
			creerLignesIServicesGenericJPASpring(listeCode);
			
			/* Recherche la ligne identifiant le code de l'interface. */
			final String ligneIdentifiant 
				= "public interface IServicesGenericJPASpring";
			
			/* Ne fait rien si le code est déjà existant. */
			if (existLigneCommencant(
					iServicesGenericJPASpring, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			ecrireCode(listeCode, iServicesGenericJPASpring);

		} // Fin de synchronized._________________________________
	
	} // Fin de genererIServicesGenericJPASpring()._____________________________
	

	
	/**
	 * method creerLignesIServicesGenericJPASpring(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>Crée le code de l'interface IServicesGenericJPASpring.</li>
	 * <li>Insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; pListe.<br/>
	 * 
	 * @throws Exception
	 */
	private static void creerLignesIServicesGenericJPASpring(
			final List<String> pListe) throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/services/interface_iservicesgenericjpaspring.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= lireStringsDansFile(fichier, CHARSET_UTF8);
					
		pListe.addAll(listeLignes);
				
	} // Fin de creerLignesIServicesGenericJPASpring(...)._________________
	
	
	
	/**
	 * method genererAbstractServicesGenericJPASpring() :<br/>
	 * <ul>
	 * <li>génère si nécessaire la classe abstraite 
	 * AbstractServicesGenericJPASpring.java sous model.services.metier.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void genererAbstractServicesGenericJPASpring() 
			throws Exception {

		synchronized (AbstractGenerateur.class) {
						
			final String nomFichier = "AbstractServicesGenericJPASpring.java";
			
			final IGestionnaireFiles gestionnaireFiles 
				= new GestionnaireFiles();
			
			/* récupération du package des DAOs. */
			final String pathPackageServices 
				= GestionnaireProjet.getPathServicesMainJavaString();
			
			final File packageServices = new File(pathPackageServices);
			
			/* création du fichier vide. */
			final File abstractServicesGenericJPASpring 
				= gestionnaireFiles
				.creerFichierDansPackage(
						nomFichier, packageServices);
			
			final List<String> listeCode = new ArrayList<String>();
			
			/* ENREGISTREMENT *********/
			creerLignesAbstractServicesGenericJPASpring(listeCode);
			
			/* Recherche la ligne identifiant le code de l'interface. */
			final String ligneIdentifiant 
				= "public abstract class AbstractServicesGenericJPASpring";
			
			/* Ne fait rien si le code est déjà existant. */
			if (existLigneCommencant(
					abstractServicesGenericJPASpring
						, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			ecrireCode(listeCode, abstractServicesGenericJPASpring);

		} // Fin de synchronized._________________________________
	
	} // Fin de genererAbstractServicesGenericJPASpring().______________________
	

	
	/**
	 * method creerLignesAbstractServicesGenericJPASpring(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>Crée le code de la classe abstraite 
	 * AbstractServicesGenericJPASpring.</li>
	 * <li>Insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; pListe.<br/>
	 * 
	 * @throws Exception
	 */
	private static void creerLignesAbstractServicesGenericJPASpring(
			final List<String> pListe) throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/services/abstractclass_abstractservicesgenericjpaspring.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= lireStringsDansFile(fichier, CHARSET_UTF8);
					
		pListe.addAll(listeLignes);
				
	} // Fin de creerLignesAbstractServicesGenericJPASpring(...)._______________
	

	
	/**
	 * method alimenterAttributsStatic(
	 * String pNomPackage
	 * , String pNomInterface
	 * , String pNomObjetMetier
	 * , Map&lt;String, String&gt; pMapAttributs
	 * , Map<String, String> pMapAttributsEquals
	 * , Map&lt;String, List&lt;String&gt;&gt; pMapRg) :<br/>
	 * <ul>
	 * <li><b>Alimente tous les attributs static des Generateurs</b>.</li>
	 * <li>alimente nomPackage.</li>
	 * <li>alimente conceptModelise.</li>
	 * <li>alimente nomInterfaceMetier.</li>
	 * <li>alimente nomAbstractClassMetier.</li>
	 * <li>alimente nomClasseMetier.</li>
	 * <li>alimente nomClasseMetierForm.</li>
	 * <li>alimente mapAttributs.</li>
	 * <li>alimente mapAttributsEquals.</li>
	 * <li>alimente mapRg.</li>
	 * </ul>
	 *
	 * @param pNomPackage : String : .<br/>
	 * @param pNomInterface : String : .<br/>
	 * @param pNomObjetMetier : String : .<br/>
	 * @param pMapAttributs : Map&lt;String, String&gt; :  .<br/>
	 * @param pMapAttributsEquals : Map&lt;String, String&gt; : .<br/>
	 * @param pMapRg : Map&lt;String, List&lt;String&gt;&gt; :  .<br/>
	 */
	private static void alimenterAttributsStatic(
			final String pNomPackage
				, final String pNomInterface
					, final String pNomObjetMetier
			, final Map<String, String> pMapAttributs
				, final Map<String, String> pMapAttributsEquals
					, final Map<String, List<String>> pMapRg) {
		
		/* alimente nomPackage. */
		alimenterNomPackage(pNomPackage);
		
		/* alimente conceptModelise. */
		alimenterConceptModelise(pNomPackage);
		
		/* alimente nomInterfaceMetier. */
		alimenterNomInterfaceMetier(pNomInterface);
		
		/* alimente nomAbstractClassMetier. */
		alimenterNomAbstractClassMetier(pNomInterface);
		
		/* alimente nomClasseMetier. */
		alimenterNomClassMetier(pNomObjetMetier);
		
		/* alimente nomClasseMetierForm. */
		alimenterNomClassMetierForm();
		
		/* alimente mapAttributs. */
		alimenterMapAttributs(pMapAttributs);
		
		/* alimente mapAttributsEquals. */
		alimenterMapAttributsEquals(pMapAttributsEquals);
		
		/* alimente mapRg. */
		alimenterMapRg(pMapRg);

	} // Fin de alimenterAttributsStatic(...)._____________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void generer() 
			throws Exception {
		
		/* GENERATION DES PACKAGES. */
		/* ALIMENTE this.pathPackageString. */
		/* génère le package this.pathPackage 
		 * (model.metier, model.dao.metier, ...). */
		/* génère le package this.packageSousCouche 
		 * (model.metier.concept, model.dao.metier.concept, ...). */
		/* génère le package sousPackageImpl 
		 * (model.metier.concept.impl, model.dao.metier.concept.impl, ...). */
		this.genererPackages(nomPackage);
		
		/* ALIMENTATION DES ATTRIBUTS D'INSTANCE. */
		this.alimenterAttributs();
		
		/* GENERATION DES FICHIERS JAVA VIDES. */
		this.genererFichiersJava();
		
		/* APPEL DU HOOK. */
		this.genererHook();
		
	} // Fin de generer(...).______________________________________________
	

		
	/**
	 * method genererHook(
	 * ) :<br/>
	 * <ul>
	 * <li>.</li>
	 * <li>.</li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	protected abstract void genererHook() throws Exception;
	

	
	/**
	 * method genererPackages(
	 * String pNomPackage) :<br/>
	 * <ul>
	 * <li><b>alimente this.pathPackage</b>.</li>
	 * <li>crée pathPackageString sur disque si nécessaire.<br/>
	 * Par exemple :<br/>
	 * <ul>
	 * <li><code>./src/main/java/levy/daniel/application/model/metier
	 * </code> pour un GenerateurMetierToutAbstract.</li> 
	 * <li><code>./src/main/java/levy/daniel/application/model/dao/metier
	 * </code> pour un GenerateurDaoToutAbstract.</li>
	 * <li><code>./src/test/java/levy/daniel/application/model/metier
	 * </code> pour un GenerateurMetierTest.</li>
	 * </ul>
	 * </li>
	 * <li>génère le package <b>this.packageSousCouche</b> 
	 * correspondant au concept.<br/>
	 * Par exemple :<br/>
	 * <ul>
	 * <li><code>./src/main/java/levy/daniel/application/model/metier/profil</code> pour un concept Profil et un GenerateurMetier.</li>
	 * <li><code>./src/main/java/levy/daniel/application/model/dao/metier/profil</code> pour un concept Profil et un GenerateurDao.</li>
	 * </ul>
	 * </li>
	 * <li>génère le package <b>sousPackageImpl</b>.<br/>
	 * Par exemple :<br/> 
	 * <ul>
	 * <li><code>./src/main/java/levy/daniel/application/model/metier/profil/impl</code> pour un concept Profil et un GenerateurMetier.</li>
	 * <li><code>./src/main/java/levy/daniel/application/model/dao/metier/profil/impl</code> pour un concept Profil et un GenerateurDao.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private void genererPackages(
			final String pNomPackage) 
					throws Exception {
		
		/* alimente this.pathPackage. */
		this.alimenterPathPackage();
		
		/* crée pathPackageString sur disque si nécessaire. */
		this.creerPathPackage();
		
		/* génère le package this.packageSousCouche. */
		this.genererPackageSousCouche(pNomPackage);
		
		/* génère le package sousPackageImpl. */
		this.genererSousPackageImpl();
		
	} // Fin de genererPackages(...).______________________________________
	

	
	/**
	 * method alimenterPathPackage() :<br/>
	 * <ul>
	 * <li><b>alimente this.pathPackage</b>.</li>
	 * <li><b>A PRECISER DANS CHAQUE GENERATEUR CONCRET</b></li>
	 * <li><b>path absolu de la SOUS-COUCHE METIER (package metier)</b> 
	 * contenant les arborescences à générer pour un Concept
	 * (model/metier, model/dao/metier
	 * , model/services/metier, ...).</li>
	 * <li>par exemple : <br/>
	 * <ul>
	 * <li><code>./src/main/java/levy/daniel/application/model/metier
	 * </code> pour un GenerateurMetierToutAbstract.</li> 
	 * <li><code>./src/main/java/levy/daniel/application/model/dao/metier
	 * </code> pour un GenerateurDaoToutAbstract.</li>
	 * <li><code>./src/test/java/levy/daniel/application/model/metier
	 * </code> pour un GenerateurMetierTest.</li>
	 * </ul>
	 * </li>
	 *
	 * @throws Exception
	 */
	protected abstract void alimenterPathPackage() throws Exception;

	
	
	/**
	 * method creerPathPackage() :<br/>
	 * <ul>
	 * <li>alimente this.pathPackage.</li>
	 * <li>alimente this.filePackage.</li>
	 * <li>alimente this.pathRelPackage.</li>
	 * <li>alimente this.pathRelPackageJavaString.</li>
	 * <li><b>crée sur disque le package correspondant 
	 * à this.pathPackage</b>.</li>
	 * <li>Ne crée this.pathPackage que si il n'existe pas déjà.</li>
	 * <li>En principe, ce package à déjà été créé par 
	 * le GestionnaireProjet.</li>
	 * </ul>
	 * ne fait rien si this.pathPackage == null.<br/>
	 * <br/>
	 * 
	 * @throws IOException 
	 */
	private void creerPathPackage() 
			throws IOException {
		
		/* ne fait rien si this.pathPackage == null. */
		if (this.pathPackageString == null) {
			return;
		}
		
		/* alimente this.pathPackage. */
		this.pathPackage = Paths.get(this.pathPackageString);
		/* alimente this.filePackage. */
		this.filePackage = this.pathPackage.toFile();
		/* alimente this.pathRelPackage. */
		if (this.pathPackage.startsWith(PATH_TEST_JAVA)) {
			this.pathRelPackage 
				= PATH_TEST_JAVA.relativize(this.pathPackage);
		} else {
			this.pathRelPackage 
			= PATH_MAIN_JAVA.relativize(this.pathPackage);
		}
		
		/* alimente this.pathRelPackageJavaString. */
		this.pathRelPackageJavaString 
			= remplacerAntiSlashparPoint(this.pathRelPackage.toString());
		
		if (!this.filePackage.exists()) {
			Files.createDirectories(this.pathPackage);
		}
		
	} // Fin de creerPathPackage().________________________________________
	
	
	
	/**
	 * method genererPackageSousCouche(
	 * String pNomPackage) :<br/>
	 * <ul>
	 * <li>alimente this.pathSousCoucheString.</li>
	 * <li>alimente this.pathSousCouche.</li>
	 * <li>alimente this.pathRelSousCouche.</li>
	 * <li>alimente this.pathRelSousCoucheJavaString.</li>
	 * <li>Génère le package pNomPackage devant 
	 * contenir le fichier java généré sous 
	 * pathPackageString/packageSousCouche.</li>
	 * <li><b>alimente this.packageSousCouche</b> 
	 * avec le package généré.</li>
	 * <li>Par exemple : genererPackageSousCouche("profil") 
	 * génère le package model/metier/profil 
	 * pour un Generateur d'Objets metier</li>
	 * </ul>
	 *
	 * @param pNomPackage : String : nom du package devant 
	 * contenir l'objet métier généré.<br/>
	 * 
	 * @throws IOException
	 */
	private void genererPackageSousCouche(
			final String pNomPackage) 
					throws IOException {
		
		/* alimente this.packageSousCouche. */
		this.packageSousCouche 
			= this.gestionnaireFiles
				.creerSousPackage(this.pathPackageString, pNomPackage);
		
		/* alimente this.pathSousCoucheString. */
		this.pathSousCoucheString 
			= retournerPathGenerique(
					this.packageSousCouche.getAbsolutePath());
		
		/* alimente this.pathSousCouche. */
		this.pathSousCouche = Paths.get(this.pathSousCoucheString);
		
		/* alimente this.pathRelSousCouche. */
		if (this.pathSousCouche.startsWith(PATH_TEST_JAVA)) {
			this.pathRelSousCouche 
				= PATH_TEST_JAVA.relativize(this.pathSousCouche);
		} else {
			this.pathRelSousCouche 
			= PATH_MAIN_JAVA.relativize(this.pathSousCouche);
		}
		
		/* alimente this.pathRelSousCoucheJavaString. */
		this.pathRelSousCoucheJavaString 
			= remplacerAntiSlashparPoint(this.pathSousCouche.toString());
		
	} // Fin de genererPackageSousCouche(...)._____________________________
	
	
	
	/**
	 * method genererSousPackageImpl() :<br/>
	 * <ul>
	 * <li>alimente this.pathSousPackageImplString.</li>
	 * <li>alimente this.pathSousPackageImpl.</li>
	 * <li>alimente this.pathRelSousPackageImpl.</li>
	 * <li>alimente this.pathRelSousPackageImplJavaString.</li>
	 * <li>Génère le package "impl" devant 
	 * contenir l'objet métier généré concret sous 
	 * pathPackageString/packageSousCouche.</li>
	 * <li><b>alimente this.sousPackageImpl</b> 
	 * avec le package généré.</li>
	 * <li>Par exemple : genererSousPackageImpl() 
	 * avec this.packageSousCouche = profil 
	 * génère le package model/metier/profil/impl 
	 * pour un Generateur d'Objets metier.</li>
	 * </ul>
	 *
	 * @throws IOException
	 */
	private void genererSousPackageImpl() 
			throws IOException {
		
		/* alimente this.sousPackageImpl. */
		this.sousPackageImpl 
			= this.gestionnaireFiles
				.creerSousPackage(this.packageSousCouche, "impl");
		
		/* alimente this.pathSousPackageImplString. */
		this.pathSousPackageImplString 
			= retournerPathGenerique(
					this.sousPackageImpl.getAbsolutePath());
		
		/* alimente this.pathSousPackageImpl. */
		this.pathSousPackageImpl 
			= Paths.get(this.pathSousPackageImplString);
		
		/* alimente this.pathRelSousPackageImpl. */
		if (this.pathSousPackageImpl.startsWith(PATH_TEST_JAVA)) {
			this.pathRelSousPackageImpl
				= PATH_TEST_JAVA.relativize(this.pathSousPackageImpl);
		} else {
			this.pathRelSousPackageImpl 
				= PATH_MAIN_JAVA.relativize(this.pathSousPackageImpl);
		}
		
		/* alimente this.pathRelSousPackageImplJavaString. */
		this.pathRelSousPackageImplJavaString 
			= remplacerAntiSlashparPoint(
					this.pathSousPackageImpl.toString());
		
	} // Fin de genererSousPackageImpl().__________________________________
	

	/**
	 * method genererFichiersJava() :<br/>
	 * <ul>
	 * <li>génère et alimente this.interfaceJava.</li>
	 * <li>génère et alimente this.abstractClass.</li>
	 * <li>génère et alimente this.concreteClass.</li>
	 * <li>génère et alimente this.objetMetierForm.</li>
	 * </ul>
	 *
	 * @throws IOException 
	 */
	protected abstract void genererFichiersJava() throws IOException;
	
	
	
	/**
	 * method alimenterAttributs() :<br/>
	 * <ul>
	 * <li>alimente this.nomSimpleInterface.</li>
	 * <li>alimente this.nomSimpleAbstractClass.</li>
	 * <li>alimente this.nomSimpleConcreteClass.</li>
	 * </ul>
	 */
	protected abstract void alimenterAttributs();
	

	
	
	/**
	 * method genererNomAbstractClass(
	 * String pNomInterface) :<br/>
	 * <ul>
	 * <li>Génère le nom de la classe abstraite à partir 
	 * du nom d'une Interface de type "IObjetMetier".</li>
	 * <li>Par exemple :  genererNomAbstractClass("IProfil") 
	 * retourne AbstractProfil.</li>
	 * </ul>
	 * retourne null si pNomInterface est blank.<br/>
	 * <br/>
	 *
	 * @param pNomInterface : String : nom de l'interface 
	 * du type "IObjetMetier".<br/>
	 * 
	 * @return : String : nom de la classe abstraite 
	 * du type "AbstractObjetMetie"r.<br/>
	 */
	protected static String genererNomAbstractClass(
			final String pNomInterface) {
		
		/* retourne null si pNomInterface est blank. */
		if (StringUtils.isBlank(pNomInterface)) {
			return null;
		}
		
		/* Pattern sous forme de String. */
		/* Commence par I
		 * poursuit par une Majuscule
		 * poursuit par CamelCase. */
		final String patternString = "(^I)([A-Z][a-zA-Z0-9]*$)";
		
		/* Instanciation d'un Pattern. */
		final Pattern pattern = Pattern.compile(patternString);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcher = pattern.matcher(pNomInterface);
		
		/* Recherche du Pattern. */
		final boolean trouve = matcher.find();
		
		String group2 = null;
		
		String resultatString = null;
		
		if (trouve) {
			
			group2 = matcher.group(2);
			
			resultatString = "Abstract" + group2;
			
		}
		
		return resultatString;
		
	} // Fin de genererNomAbstractClass(...).______________________________
	
	
	
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
	protected static final File ecrireStringDansFile(
			final File pFile
				, final String pString
					, final Charset pCharset
						, final String pSautLigne) {
		
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
			
	} // Fin de ecrireStringDansFile(...)._________________________________
	

	
	/**
	 * method ecrireCode(
	 * List&lt;String&gt; pList, File pFile) :<br/>
	 * <ul>
	 * <li>Ecrit le contenu de la liste pList dans pFile.</li>
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
	protected static final void ecrireCode(
			final List<String> pList
				, final File pFile) throws Exception {
		
		synchronized (AbstractGenerateur.class) {
			
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
	protected static final List<String> lireStringsDansFile(
			final File pFile
				, final Charset pCharset) throws Exception {
		
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
			
	} // Fin de lireStringsDansFile(
	 // File pFile
	 // , Charset pCharset)._______________________________________________
	

	
	/**
	 * method existLigneCommencant(
	 * File pFile
	 * , Charset pCharsetLecture
	 * , String pLigne) :<br/>
	 * <ul>
	 * <li><b>Détermine si une ligne commençant par pLIgne 
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
	protected static final boolean existLigneCommencant(
			final File pFile
				, final Charset pCharsetLecture
					, final String pLigne) {
		
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
		
	} // Fin de existLigneCommencant(...)._________________________________
	

	
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
	protected static final String substituerSautLigne(
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
	protected static final String retournerPathGenerique(
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
	protected static final String remplacerAntiSlashparPoint(
			final String pString) {
		
		synchronized (AbstractGenerateur.class) {
			
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
	protected static final void loggerInfo(
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
	protected static final void loggerInfo(
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
	protected static final void loggerError(
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
	 * method fournirNomClasse() :<br/>
	 * <ul>
	 * <li>Retourne "Classe AbstractGenerateur".</li>
	 * <li>Utile pour les LOGS.</li>
	 * </ul>
	 *
	 * @return : String : "Classe AbstractGenerateur".<br/>
	 */
	protected static final String fournirNomClasse() {
		return CLASSE_ABSTRACT_GENERATEUR;
	} // Fin de fournirNomClasse().________________________________________
	

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
	 * retourne true si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : boolean : true si conforme.<br/>
	 */
	private static boolean conformeNomInterface(
			final String pString) {
		
		/* retourne true si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return true;
		}
		
		boolean resultat = false;
		
		/* Pattern sous forme de String. */
		/* - Commence par I
		 * - poursuit par une Majuscule
		 * - poursuit camelCase. */
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
	 * method mettrePremiereEnMajusculeEtGarder(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Met la première lettre de chaque mots séparés 
	 * par des espaces en majuscule.</li>
	 * <li>Conserve les autres lettres de chaque mots séparés 
	 * par un espace.</li>
	 * <li>Par exemple : "premier" est transformé en "Premier".</li>
	 * <li>"PREMIER" est transformé en "PREMIER".</li>
	 * <li>WordUtils.capitalize("i am FINE") = "I Am FINE"</li>
	 * </ul>
	 * retourne null si pString == null.<br/>
	 * <br/>
	 * 
	 *
	 * @param pString : String. <br/>
	 * 
	 * @return : String.<br/>
	 */
	private static String mettrePremiereEnMajusculeEtGarder(
			final String pString) {
		
		/* retourne null si pString == null. */
		if (pString == null) {
			return null;
		}
		
		return WordUtils.capitalize(pString);
		
	} // Fin de mettrePremiereEnMajusculeEtGarder(...).____________________
	

		
	/**
	 * method alimenterNomPackage(
	 * String pNomPackage) :<br/>
	 * <ul>
	 * <li>ALimente le <b>nom du package à créer 
	 * dans chaque SOUS-COUCHE</b> 
	 * en fonction du concept à modéliser dans le générateur 
	 * (model/metier/profil, model/dao/metier/profil, 
	 * model/services/metier/profil, ...pour un concept Profil).</li>
	 * <li>pNomPackage est passé en paramètre au générateur.</li>
	 * <li>par exemple : <br/>
	 * <code>profil</code>.</li>
	 * <li>RG-CONCEPT-01 : le conceptModelise est déduit 
	 * du nomPackage passé en paramètre.</li>
	 * </ul>
	 * 
	 * @param pNomPackage : String : 
	 * nom du package à créer dans chaque couche.<br/>
	 *
	 * @return nomPackage : String.<br/>
	 */
	private static String alimenterNomPackage(
			final String pNomPackage) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (nomPackage == null) {
				nomPackage = pNomPackage;
			}
			
			return nomPackage;
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterNomPackage(...).__________________________________


		
	/**
	 * method alimenterConceptModelise() :<br/>
	 * <ul>
	 * <li>Alimente le <b>concept modélisé par ce générateur</b>.</li>
	 * <li><b>nom du package à créer dans chaque SOUS-COUCHE</b> 
	 * (model/metier, model/dao/metier, model/services/metier, ...) 
	 * avec une <i>majuscule</i> en première position.</li>
	 * <li>Par exemple : <br/>
	 * <code>Profil</code> pour le nomPackage "profil".</li>
	 * <li>RG-CONCEPT-01 : le conceptModelise est déduit 
	 * du nomPackage passé en paramètre.</li>
	 * </ul>
	 * 
	 * @param pNomPackage : String : 
	 * nom du package à créer dans chaque couche.<br/>
	 *
	 * @return conceptModelise : String.<br/>
	 */
	private static String alimenterConceptModelise(
			final String pNomPackage) {	
		
		synchronized (AbstractGenerateur.class) {
			
			if (conceptModelise == null) {
				conceptModelise = WordUtils.capitalize(pNomPackage);
			}
			
			return conceptModelise;
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterConceptModelise().________________________________


	
	/**
	 * method alimenterNomInterfaceMetier(
	 * String pNomInterface) :<br/>
	 * Alimenteur du <b>nom de l'interface de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "IProfil".<br/>
	 * <br/>
	 * 
	 * @param pNomInterface : String. 
	 *
	 * @return nomInterfaceMetier : String.<br/>
	 */
	private static String alimenterNomInterfaceMetier(
			final String pNomInterface) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (nomInterfaceMetier == null) {
				nomInterfaceMetier = pNomInterface;
			}
			
			return nomInterfaceMetier;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterNomInterfaceMetier(...).__________________________



	/**
	 * method alimenterNomAbstractClassMetier() :<br/>
	 * Alimenteur du <b>nom de la classe abstraite de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * déduit du nom de l'interface metier 
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "AbstractProfil".<br/>
	 *
	 *  @param pNomInterface : String. 
	 * 
	 * @return nomAbstractClassMetier : String .<br/>
	 */
	private static String alimenterNomAbstractClassMetier(
			final String pNomInterface) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (nomAbstractClassMetier == null) {
				nomAbstractClassMetier 
					= genererNomAbstractClass(pNomInterface);
			}
			
			return nomAbstractClassMetier;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterNomAbstractClassMetier(...).______________________


		
	/**
	 * method alimenterNomClassMetier(String)(
	 * String pNomObjetMetier) :<br/>
	 * Alimenteur du <b>nom de la classe concrète de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.impl).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "Profil".<br/>
	 * <br/>
	 *
	 * @param pNomObjetMetier : String.
	 * 
	 * @return nomClassMetier : String.<br/>
	 */
	private static String alimenterNomClassMetier(
			final String pNomObjetMetier) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (nomClassMetier == null) {
				nomClassMetier = pNomObjetMetier;
			}
			
			return nomClassMetier;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterNomClassMetier(String)(...).______________________


		
	/**
	 * method alimenterNomClassMetierForm() :<br/>
	 * Alimenteur du <b>nom de la classe concrète de l'Objet metier 
	 * "Formulaire" à créer dans la  couche metier</b> 
	 * (sous model.metier.impl).<br/>
	 * déduit de this.nomClassMetier.<br/>
	 * par exemple : "ProfilForm".<br/>
	 *
	 * @return nomClassMetierForm : String.<br/>
	 */
	private static String alimenterNomClassMetierForm() {
		
		synchronized (AbstractGenerateur.class) {
			
			if (nomClassMetierForm == null) {
				nomClassMetierForm = nomClassMetier + "Form";
			}
			
			return nomClassMetierForm;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterNomClassMetierForm()._____________________________
	
	
	
	/**
	 * method alimenterMapAttributs(
	 * Map&lt;String,String&gt; pMapAttributs) :<br/>
	 * <ul>
	 * Alimenteur de la Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 *
	 * @param pMapAttributs : Map&lt;String,String&gt;.<br/>
	 * 
	 * @return mapAttributs : Map&lt;String,String&gt;.<br/>
	 */
	private static Map<String, String> alimenterMapAttributs(
			final Map<String, String> pMapAttributs) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (mapAttributs == null) {
				mapAttributs = pMapAttributs;
			}
			
			return mapAttributs;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterMapAttributs().___________________________________
	
	
	
	/**
	 * method alimenterMapAttributsEquals(
	 * Map&lt;String,String&gt; pMapAttributsEquals) :<br/>
	 * <ul>
	 * Alimenteur de la Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) utilisés dans equals() avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 *
	 * @param pMapAttributsEquals : Map&lt;String,String&gt;.<br/>
	 * 
	 * @return mapAttributsEquals : Map&lt;String,String&gt;.<br/>
	 */
	private static Map<String, String> alimenterMapAttributsEquals(
			final Map<String, String> pMapAttributsEquals) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (mapAttributsEquals == null) {
				mapAttributsEquals = pMapAttributsEquals;
			}
			
			return mapAttributsEquals;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterMapAttributsEquals()._____________________________
	
	
		
	/**
	 * method alimenterMapRg(
	 * Map&lt;String, List&lt;String&gt;&gt; pMapRg) :<br/>
	 * <ul>
	 * Alimenteur de la Map&lt;String, List&lt;String&gt;&gt; ordonnée 
	 * contenant les listes de RG par attribut avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>List&lt;String&gt; : Liste des RG s'appliquant à l'attribut</li>
	 * </ul>
	 *
	 * @param pMapRg : Map&lt;String, List&lt;String&gt;&gt;.<br/>
	 * 
	 * @return mapRg : Map&lt;String, List&lt;String&gt;&gt;.<br/>
	 */
	private static Map<String, List<String>> alimenterMapRg(
			final Map<String, List<String>> pMapRg) {
		
		synchronized (AbstractGenerateur.class) {
			
			if (mapRg == null) {
				mapRg = pMapRg;
			}
			
			return mapRg;
			
		} // Fin de synchronized._______________________

	} // Fin de alimenterMapRg().__________________________________________
	
	
	
	/**
	 * method getPathMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du répertoire de la 
	 * RACINE des sources .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>PATH_MAIN_JAVA = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java
	 * </code></li>
	 * </ul>
	 *
	 * @return PATH_MAIN_JAVA : Path.<br/>
	 */
	public static Path getPathMainJava() {
		return PATH_MAIN_JAVA;
	} // Fin de getPathMainJava()._________________________________________


		
	/**
	 * method getPathTestJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du répertoire de la RACINE des tests .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>PATH_TEST_JAVA = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelTestJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/test/java
	 * </code></li>
	 * </ul>
	 *
	 * @return PATH_TEST_JAVA : Path.<br/>
	 */
	public static final Path getPathTestJava() {
		return PATH_TEST_JAVA;
	} // Fin de getPathTestJava()._________________________________________



	/**
	 * method getConceptModelise() :<br/>
	 * <ul>
	 * <li>Getter du <b>concept modélisé par ce générateur</b>.</li>
	 * <li><b>nom du package à créer dans chaque SOUS-COUCHE</b> 
	 * (model/metier, model/dao/metier, model/services/metier, ...) 
	 * avec une <i>majuscule</i> en première position.</li>
	 * <li>Par exemple : <br/>
	 * <code>Profil</code> pour le nomPackage "profil".</li>
	 * <li>RG-CONCEPT-01 : le conceptModelise est déduit 
	 * du nomPackage passé en paramètre.</li>
	 * </ul>
	 * 
	 * @return conceptModelise : String.<br/>
	 */
	public static final String getConceptModelise() {
		return conceptModelise;
	} // Fin de getConceptModelise().______________________________________


	
	/**
	 * method getNomPackage() :<br/>
	 * <ul>
	 * <li>Getter du <b>nom du package à créer dans chaque SOUS-COUCHE</b> 
	 * en fonction du concept à modéliser dans le générateur 
	 * (model/metier/profil, model/dao/metier/profil, 
	 * model/services/metier/profil, ...pour un concept Profil).</li>
	 * <li>passé en paramètre au générateur.</li>
	 * <li>par exemple : <br/>
	 * <code>profil</code>.</li>
	 * <li>RG-CONCEPT-01 : le conceptModelise est déduit 
	 * du nomPackage passé en paramètre.</li>
	 * </ul>
	 * 
	 * @return nomPackage : String.<br/>
	 */
	public static final String getNomPackage() {
		return nomPackage;
	} // Fin de getNomPackage().___________________________________________


	
	/**
	 * method getPackageMetier() :<br/>
	 * <ul>
	 * Getter du Package metier de la COUCHE <i>model</i> 
	 * (model.metier).<br/>
	 * </ul>
	 *
	 * @return packageMetier : File.<br/>
	 */
	public static final File getPackageMetier() {	
		return packageMetier;
	} // Fin de getPackageMetier().________________________________________


		
	/**
	 * method getPathPackageMetier() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du package metier</b> 
	 * de la COUCHE <i>MODEL</i>.</li>
	 * <li>Par exemple :<br/> 
	 * <code>./src/main/java/levy/daniel/application/model/metier
	 * </code>.</li>
	 * </ul>
	 *
	 * @return pathPackageMetier : Path.<br/>
	 */
	public static final Path getPathPackageMetier() {
		return pathPackageMetier;
	} // Fin de getPathPackageMetier().____________________________________


	
	/**
	 * method getPathRelMetier() :<br/>
	 * <ul>
	 * <li>Getter du <b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du package metier</b> 
	 *  (sous model).</li>
	 * <li>path relatif MODE FILE, c'est à dire avec 
	 * des séparateurs slash.</li> 
	 * <li>Par exemple :<br/>
	 * <code>levy/daniel/application/model/metier
	 * </code>.</li>
	 * </ul>
	 *
	 * @return pathRelMetier : Path.<br/>
	 */
	public static final Path getPathRelMetier() {
		return pathRelMetier;
	} // Fin de getPathRelMetier().________________________________________


	
	/**
	 * method getPathRelMetierString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du package metier</b> 
	 * (sous model).</li>
	 * <li>path relatif JAVA, c'est à dire avec 
	 * des séparateurs point.</li> 
	 * <li>Par exemple :<br/>
	 * <code>levy.daniel.application.model.metier
	 * </code>.</li>
	 * </ul>
	 *
	 * @return pathRelMetierString : String.<br/>
	 */
	public static final String getPathRelMetierString() {
		return pathRelMetierString;
	} // Fin de getPathRelMetierString().__________________________________



	/**
	 * method getPathPackageConcept() :<br/>
	 * <ul>
	 * <li>Getter du <b>path absolu du package concept</b> 
	 * sous la COUCHE METIER.</li>
	 * <li>Par exemple :<br/> 
	 * <code>./src/main/java/levy/daniel/application/model/metier/profil/
	 * </code>.</li>
	 * </ul>
	 *
	 * @return pathPackageConcept : Path.<br/>
	 */
	public static final Path getPathPackageConcept() {
		return pathPackageConcept;
	} // Fin de getPathPackageConcept().___________________________________

	
	
	/**
	 * method getPathRelConcept() :<br/>
	 * <ul>
	 * <li>Getter du <b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du package concept</b> 
	 * sous la COUCHE METIER.</li>
	 * <li>path relatif MODE FILE, c'est à dire avec 
	 * des séparateurs slash.</li> 
	 * <li>Par exemple :<br/>
	 * <code>levy/daniel/application/model/metier/profil
	 * </code>.</li>
	 * </ul>
	 *
	 * @return pathRelConcept : Path.<br/>
	 */
	public static final Path getPathRelConcept() {
		return pathRelConcept;
	} // Fin de getPathRelConcept()._______________________________________



	/**
	 * method getPathRelConceptString() :<br/>
	 * <ul>
	 * <li>Getter du <b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du package concept</b> 
	 * sous la COUCHE METIER.</li>
	 * <li>path relatif JAVA, c'est à dire avec 
	 * des séparateurs point.</li> 
	 * <li>Par exemple :<br/>
	 * <code>levy.daniel.application.model.metier.profil
	 * </code>.</li>
	 * </ul>
	 *
	 * @return pathRelConceptString : String.<br/>
	 */
	public static final String getPathRelConceptString() {
		return pathRelConceptString;
	} // Fin de getPathRelConceptString()._________________________________



	/**
	 * method getPathPackageConceptImpl() :<br/>
	 * <ul>
	 * <li>Getter du 
	 * <b>path absolu du sous-package impl du package concept</b> 
	 * sous la COUCHE METIER.</li>
	 * <li>Par exemple :<br/> 
	 * <code>./src/main/java/levy/daniel/application/model/metier/profil/impl/
	 * </code></li>
	 * </ul>
	 *
	 * @return pathPackageConceptImpl : Path.<br/>
	 */
	public static final Path getPathPackageConceptImpl() {
		return pathPackageConceptImpl;
	} // Fin de getPathPackageConceptImpl()._______________________________


	
	/**
	 * method getPathRelConceptImpl() :<br/>
	 * <ul>
	 * <li>Getter du <b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du sous-package impl du package concept</b> 
	 * sous la COUCHE METIER.</li>
	 * <li>path relatif MODE FILE, c'est à dire avec 
	 * des séparateurs slash.</li> 
	 * <li>Par exemple :<br/>
	 * <code>levy/daniel/application/model/metier/profil/impl
	 * </code>.</li>
	 * </ul>
	 *
	 * @return pathRelConceptImpl : Path.<br/>
	 */
	public static final Path getPathRelConceptImpl() {
		return pathRelConceptImpl;
	} // Fin de getPathRelConceptImpl().___________________________________



	/**
	 * method getPathRelConceptImplString() :<br/>
	 *<ul>
	 * <li>Getter du <b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du sous-package impl du package concept</b> 
	 * sous la COUCHE METIER.</li>
	 * <li>path relatif JAVA, c'est à dire avec 
	 * des séparateurs point.</li> 
	 * <li>Par exemple :<br/>
	 * <code>levy.daniel.application.model.metier.profil.impl
	 * </code>.</li>
	 * </ul>
	 *
	 * @return pathRelConceptImplString : String.<br/>
	 */
	public static final String getPathRelConceptImplString() {
		return pathRelConceptImplString;
	} // Fin de getPathRelConceptImplString()._____________________________



	/**
	 * method getNomInterfaceMetier() :<br/>
	 * Getter du <b>nom de l'interface de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "IProfil".<br/>
	 * <br/>
	 * 
	 * @return nomInterfaceMetier : String.<br/>
	 */
	public static final String getNomInterfaceMetier() {
		return nomInterfaceMetier;
	} // Fin de getNomInterfaceMetier().___________________________________


	
	/**
	 * method getNomAbstractClassMetier() :<br/>
	 * Getter du <b>nom de la classe abstraite de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * déduit du nom de l'interface metier 
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "AbstractProfil".<br/>
	 *
	 * @return nomAbstractClassMetier : String .<br/>
	 */
	public static final String getNomAbstractClassMetier() {
		return nomAbstractClassMetier;
	} // Fin de getNomAbstractClassMetier()._______________________________


	
	/**
	 * method getNomClassMetier() :<br/>
	 * Getter du <b>nom de la classe concrète de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.impl).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "Profil".<br/>
	 * <br/>
	 *
	 * @return nomClassMetier : String.<br/>
	 */
	public static final String getNomClassMetier() {
		return nomClassMetier;
	} // Fin de getNomClassMetier()._______________________________________


		
	/**
	 * method getNomClassMetierForm() :<br/>
	 * Getter du <b>nom de la classe concrète de l'Objet metier 
	 * "Formulaire" à créer dans la  couche metier</b> 
	 * (sous model.metier.impl).<br/>
	 * déduit de this.nomClassMetier.<br/>
	 * par exemple : "ProfilForm".<br/>
	 *
	 * @return nomClassMetierForm : String.<br/>
	 */
	public static final String getNomClassMetierForm() {
		return nomClassMetierForm;
	} // Fin de getNomClassMetierForm().___________________________________



	/**
	 * method getMapAttributs() :<br/>
	 * <ul>
	 * Getter de la Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 *
	 * @return mapAttributs : Map&lt;String,String&gt;.<br/>
	 */
	public static final Map<String, String> getMapAttributs() {
		return mapAttributs;
	} // Fin de getMapAttributs()._________________________________________


	
	/**
	 * method getMapAttributsEquals() :<br/>
	 * <ul>
	 * Getter de la Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) utilisés dans equals() avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 *
	 * @return mapAttributsEquals : Map&lt;String,String&gt;.<br/>
	 */
	public static final Map<String, String> getMapAttributsEquals() {
		return mapAttributsEquals;
	} // Fin de getMapAttributsEquals().___________________________________


	
	/**
	 * method getMapRg() :<br/>
	 * <ul>
	 * Getter de la Map&lt;String, List&lt;String&gt;&gt; ordonnée 
	 * contenant les listes de RG par attribut avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>List&lt;String&gt; : Liste des RG s'appliquant à l'attribut</li>
	 * </ul>
	 *
	 * @return mapRg : Map&lt;String, List&lt;String&gt;&gt;.<br/>
	 */
	public static final Map<String, List<String>> getMapRg() {
		return mapRg;
	} // Fin de getMapRg().________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getFilePackage() {
		return this.filePackage;
	} // Fin de getFilePackage().__________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPathPackageString() {
		return this.pathPackageString;
	} // Fin de getPathPackage().__________________________________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Path getPathPackage() {
		return this.pathPackage;
	} // Fin de getPathPackage().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Path getPathRelPackage() {
		return this.pathRelPackage;
	} // Fin de getPathRelPackage()._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPathRelPackageJavaString() {
		return this.pathRelPackageJavaString;
	} // Fin de getPathRelPackageJavaString()._____________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getPackageSousCouche() {
		return this.packageSousCouche;
	} // Fin de getPackageSousCouche().____________________________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPathSousCoucheString() {
		return this.pathSousCoucheString;
	} // Fin de getPathSousCoucheString()._________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Path getPathSousCouche() {
		return this.pathSousCouche;
	} // Fin de getPathSousCouche()._______________________________________



	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Path getPathRelSousCouche() {
		return this.pathRelSousCouche;
	} // Fin de getPathRelSousCouche().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPathRelSousCoucheJavaString() {
		return this.pathRelSousCoucheJavaString;
	} // Fin de getPathRelSousCoucheJavaString().__________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getSousPackageImpl() {
		return this.sousPackageImpl;
	} // Fin de getSousPackageImpl().______________________________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPathSousPackageImplString() {
		return this.pathSousPackageImplString;
	} // Fin de getPathSousPackageImplString().____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Path getPathSousPackageImpl() {
		return this.pathSousPackageImpl;
	} // Fin de getPathSousPackageImpl().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Path getPathRelSousPackageImpl() {
		return this.pathRelSousPackageImpl;
	} // Fin de getPathRelSousPackageImpl()._______________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPathRelSousPackageImplJavaString() {
		return this.pathRelSousPackageImplJavaString;
	} // Fin de getPathRelSousPackageImplJavaString()._____________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomSimpleInterface() {
		return this.nomSimpleInterface;
	} // Fin de getNomSimpleInterface().___________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getInterfaceJava() {
		return this.interfaceJava;
	} // Fin de getInterfaceJava().________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomSimpleAbstractClass() {	
		return this.nomSimpleAbstractClass;
	} // Fin de getNomSimpleAbstractClass()._______________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getAbstractClass() {
		return this.abstractClass;
	} // Fin de getAbstractClass().________________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomSimpleConcreteClass() {	
		return this.nomSimpleConcreteClass;
	} // Fin de getNomSimpleConcreteClass()._______________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getConcreteClass() {
		return this.concreteClass;
	} // Fin de getConcreteClass().________________________________________
	
	
	
} // FIN DE LA CLASSE AbstractGenerateur.------------------------------------
