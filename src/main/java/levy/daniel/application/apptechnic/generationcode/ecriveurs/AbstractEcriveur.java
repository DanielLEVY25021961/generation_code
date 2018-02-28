package levy.daniel.application.apptechnic.generationcode.ecriveurs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.text.WordUtils;

import levy.daniel.application.apptechnic.generationcode.AbstractGenerateur;
import levy.daniel.application.apptechnic.generationcode.GestionnaireProjet;
import levy.daniel.application.apptechnic.generationcode.IGenerateur;


/**
 * CLASSE ABSTRAITE <b>AbstractEcriveur</b> :<br/>
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
 * @since 28 janv. 2018
 *
 */
public abstract class AbstractEcriveur implements IEcriveur {

	// ************************ATTRIBUTS************************************/
	/**
	 * CLASSE_ABSTRACT_ECRIVEUR : String :<br/>
	 * "Classe AbstractEcriveur".<br/>
	 */
	public static final String CLASSE_ABSTRACT_ECRIVEUR 
		= "Classe AbstractEcriveur";


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
	 * generateurCode : IGenerateur :<br/>
	 * Generateur de code 
	 * (GenerateurMetierToutAbstract, GenerateurDaoToutAbstract, ...).<br/>
	 */
	protected transient IGenerateur generateurCode;

	
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
	protected transient String nomPackage;
	
	
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
	protected transient String conceptModelise;
	
	
	/**
	 * fichierJava : File :<br/>
	 * Fichier Java à générer dans lequel écrire le code.<br/>
	 */
	protected transient File fichierJava;
	
	
	/**
	 * nomSimpleFichierJava : String :<br/>
	 * Nom simple du fichier Java sans extension.<br/>
	 * Par exemple, "IProfil" pour IProfil.java<br/>
	 */
	protected transient String nomSimpleFichierJava;
		
	
	/**
	 * nomSimpleInterface : String :<br/>
	 * Nom simple de l'interface à générer.<br/>
	 * Par exemple "IProfil".<br/>
	 */
	protected transient String nomSimpleInterface;
	
	
	/**
	 * nomSimpleAbstractClass : String :<br/>
	 * Nom simple de la Classe Abstraite à générer.<br/>
	 * Par exemple "AbstractProfil".<br/>
	 */
	protected transient String nomSimpleAbstractClass;

	
	/**
	 * nomSimpleConcreteClass : String :<br/>
	 * Nom simple de la classe concrète à générer.<br/>
	 * Par exemple "ProfilSimple"ou "DaoProfilSimple".<br/>
	 */
	protected transient String nomSimpleConcreteClass;

	
	/**
	 * mapAttributs : Map&lt;String,String&gt; :<br/>
	 * <ul>
	 * Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 */
	protected transient Map<String, String> mapAttributs;
	
	
	
	/**
	 * mapAttributsEquals : Map&lt;String,String&gt; :<br/>
	 * <ul>
	 * Map&lt;String,String&gt; des attributs 
	 * de l'objet métier (hors id) utilisés dans equals() avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>String : type de l'attribut</li>
	 * </ul>
	 */
	protected transient Map<String, String> mapAttributsEquals;
	
		
	/**
	 * mapRg : Map&lt;String, List&lt;String&gt;&gt; :<br/>
	 * <ul>
	 * Map&lt;String, List&lt;String&gt;&gt; ordonnée 
	 * contenant les listes de RG par attribut avec :
	 * <li>String : nom de l'attribut</li>
	 * <li>List&lt;String&gt; : Liste des RG s'appliquant à l'attribut</li>
	 * </ul>
	 */
	protected transient Map<String, List<String>> mapRg;
	

	
	/**
	 * pathRacineMainJavaString : String :<br/>
	 * Chemin absolu des sources java sous forme de String.<br/>
	 * Exemple : <br/> 
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * generation_code/src/main/java</code><br/>
	 */
	protected transient String pathRacineMainJavaString; 

	
	/**
	 * pathRacineMainJava : Path :<br/>
	 * Chemin absolu des sources java sous forme de Path.<br/>
	 * Exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * generation_code/src/main/java</code><br/>
	 */
	protected transient Path pathRacineMainJava;
	
	
	/**
	 * pathMetier : String :<br/>
	 * Path relatif de model.metier .<br/>
	 * Par exemple : <br/> 
	 * <code>levy.daniel.application.model.metier</code>
	 */
	protected transient String pathMetier;
	

	
	/**
	 * packageMetier : File :<br/>
	 * <ul>
	 * <li>Package metier de la COUCHE <i>model</i> 
	 * (model.metier).</li>
	 * </ul>
	 */
	protected transient File packageMetier;
	
	
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
	protected transient Path pathPackageMetier;
	
	
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
	protected transient Path pathRelMetier;
	
	
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
	protected transient String pathRelMetierString;

	
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
	protected transient Path pathPackageConcept;
	
	
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
	protected transient Path pathRelConcept;
	
	
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
	protected transient String pathRelConceptString;
	
		
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
	protected transient Path pathPackageConceptImpl;
	

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
	protected transient Path pathRelConceptImpl;
	
		
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
	protected transient String pathRelConceptImplString;

	
	/**
	 * fileCouche : File :<br/>
	 * <ul>
	 * <li><b>File modélisant la COUCHE au dessus du package metier 
	 * (model, model.dao, model.services, vues, ...)</b> 
	 * contenant les arborescences à générer pour un Concept
	 * (model, model/dao
	 * , model/services, ...).</li>
	 * <li>par exemple : <br/>
	 * <ul>
	 * <li><code>./src/main/java/levy/daniel/application/model
	 * </code> pour un GenerateurMetierToutAbstract.</li> 
	 * <li><code>./src/main/java/levy/daniel/application/model/dao
	 * </code> pour un GenerateurDaoToutAbstract.</li>
	 * <li><code>./src/test/java/levy/daniel/application/model
	 * </code> pour un GenerateurMetierTest.</li>
	 * </ul>
	 * </ul>
	 */
	protected transient File fileCouche;
	
	
	/**
	 * pathCoucheString : String :<br/>
	 * <ul>
	 * <li><b>path absolu de la COUCHE au dessus du package metier
	 * (model, model.dao, model.services, vues ...)</b> 
	 * contenant les arborescences à générer pour un Concept
	 * (model, model/dao
	 * , model/services, ...).</li>
	 * <li>path sous forme de String.</li>
	 * <li>par exemple : <br/>
	 * <ul>
	 * <li><code>./src/main/java/levy/daniel/application/model
	 * </code> pour un GenerateurMetierToutAbstract.</li> 
	 * <li><code>./src/main/java/levy/daniel/application/model/dao
	 * </code> pour un GenerateurDaoToutAbstract.</li>
	 * <li><code>./src/test/java/levy/daniel/application/model
	 * </code> pour un GenerateurMetierTest.</li>
	 * </ul>
	 * </ul>
	 */
	protected transient String pathCoucheString;
	
	
	/**
	 * pathCouche : Path :<br/>
	 * <ul>
	 * <li><b>path absolu de la COUCHE au dessus du package metier 
	 * (model, model.dao, model.services, vues, ...)</b> 
	 * contenant les arborescences à générer pour un Concept
	 * (model, model/dao
	 * , model/services, ...).</li>
	 * <li>path sous forme de java.nio.file.Path.</li>
	 * <li>par exemple : <br/>
	 * <ul>
	 * <li><code>./src/main/java/levy/daniel/application/model
	 * </code> pour un GenerateurMetierToutAbstract.</li> 
	 * <li><code>./src/main/java/levy/daniel/application/model/dao
	 * </code> pour un GenerateurDaoToutAbstract.</li>
	 * <li><code>./src/test/java/levy/daniel/application/model
	 * </code> pour un GenerateurMetierTest.</li>
	 * </ul>
	 * </ul>
	 */
	protected transient Path pathCouche;

	
	/**
	 * pathRelCouche : Path :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du pathCouche</b> 
	 *  (sous la couche voulue).</li>
	 * <li>path relatif MODE FILE, c'est à dire avec 
	 * des séparateurs slash.</li> 
	 * <li>Par exemple :<br/>
	 * <ul>
	 * <li><code>levy/daniel/application/model
	 * </code> pour un GenerateurMetierToutAbstract.</li> 
	 * <li><code>levy/daniel/application/model/dao
	 * </code> pour un GenerateurDaoToutAbstract.</li>
	 * <li><code>levy/daniel/application/model
	 * </code> pour un GenerateurMetierTest.</li>
	 * </ul>
	 * </ul>
	 */
	protected transient Path pathRelCouche;
	
	
	/**
	 * pathRelCoucheJavaString : String :<br/>
	 * <ul>
	 * <li><b>path RELATIF par rapport à PATH_MAIN_JAVA 
	 * du pathCouche</b> 
	 *  (sous la couche voulue).</li>
	 * <li>path relatif JAVA, c'est à dire avec 
	 * des séparateurs point.</li> 
	 * <li>Par exemple :<br/>
	 * <ul>
	 * <li><code>levy.daniel.application.model
	 * </code> pour un GenerateurMetierToutAbstract.</li> 
	 * <li><code>levy.daniel.application.model.dao
	 * </code> pour un GenerateurDaoToutAbstract.</li>
	 * <li><code>levy.daniel.application.model
	 * </code> pour un GenerateurMetierTest.</li>
	 * </ul>
	 * </ul>
	 */
	protected transient String pathRelCoucheJavaString;
	

	
	/**
	 * filePackage : File :<br/>
	 * <ul>
	 * <li><b>File modélisant la SOUS-COUCHE METIER (package metier) 
	 * sous la COUCHE (model, model.dao, model.services, ...)</b> 
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
	 * <li><b>path absolu de la SOUS-COUCHE METIER (package metier) 
	 * sous la COUCHE (model, model.dao, model.services, ...)</b> 
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
	 * <li><b>path absolu de la SOUS-COUCHE METIER (package metier) 
	 * sous la COUCHE (model, model.dao, model.services, ...)</b> 
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
	 * nomInterfaceMetier : String :<br/>
	 * <b>nom de l'interface de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "IProfil".<br/>
	 */
	protected transient String nomInterfaceMetier;
	
	
	/**
	 * nomAbstractClassMetier : String :<br/>
	 * <b>nom de la classe abstraite de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.nomPackage).<br/>
	 * déduit du nom de l'interface metier 
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "AbstractProfil".<br/>
	 */
	protected transient String nomAbstractClassMetier;
	
	
	/**
	 * nomClassMetier : String :<br/>
	 * <b>nom de la classe concrète de l'Objet metier 
	 * à créer dans la  couche metier</b> 
	 * (sous model.metier.impl).<br/>
	 * passé en paramètre au générateur.<br/>
	 * par exemple : "Profil".<br/>
	 */
	protected transient String nomClassMetier;
	

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
	= LogFactory.getLog(AbstractEcriveur.class);
	

	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR AbstractEcriveur() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractEcriveur() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void ecrireCode(
			final File pFile
				, final IGenerateur pGenerateur) throws Exception {
				
		/* ne fait rien si pFile est null. */
		if (pFile == null) {
			return;
		}
		
		/* ne fait rien si pFile n'existe pas. */
		if (!pFile.exists()) {
			return;
		}
		
		/* ne fait rien si pFile n'est pas un fichier simple. */
		if (!pFile.isFile()) {
			return;
		}

		/* alimente this.generateur. */
		this.generateurCode = pGenerateur;
		
		/* alimente tous les attributs généraux de la classe. */
		this.alimenterAttributsGeneraux();
		
		/* alimente this.nomSimpleInterface. */
		/* alimente this.nomSimpleAbstractClass. */
		/* alimente this.nomSimpleConcreteClass. */
		/* alimente this.fichierJava. */
		/* alimente this.nomSimpleFichierJava. */
		this.alimenterAttributsFichiersAEcrire(pFile);

		
		this.ecrireCodeGenerique(pFile, pGenerateur);
		
	} // Fin de ecrireCode(...).___________________________________________


	
	/**
	 * method alimenterAttributsGeneraux() :<br/>
	 * <ul>
	 * <li>alimente tous les attributs généraux de la classe 
	 * avec les éléments fournis par le Generateur.</li>
	 * </ul>
	 */
	private void alimenterAttributsGeneraux() {
		
		/* alimente this.nomPackage. */
		this.nomPackage = AbstractGenerateur.getNomPackage();
		
		/* alimente this.conceptModelise. */
		this.conceptModelise 
			= AbstractGenerateur.getConceptModelise();
		
		/* alimente this.mapAttributs. */
		this.mapAttributs 
			= AbstractGenerateur.getMapAttributs();
		
		/* alimente this.mapAttributsEquals. */
		this.mapAttributsEquals 
			= AbstractGenerateur.getMapAttributsEquals();
		
		/* alimente this.mapRg. */
		this.mapRg 
			= AbstractGenerateur.getMapRg();
		
		/* Récupère le path absolu des sources Java 
		 * sous forme de String. */
		/* D:\Donnees\eclipse\eclipseworkspace_neon\
		 * generation_code\src\main\java */
		this.pathRacineMainJavaString 
			= GestionnaireProjet.getPathMainJavaString();
		
		/* Récupère le path absolu des sources Java 
		 * sous forme de Path. */
		/* D:\Donnees\eclipse\eclipseworkspace_neon\
		 * generation_code\src\main\java */
		this.pathRacineMainJava 
			= Paths.get(this.pathRacineMainJavaString);
		
		/* Détermine le path relatif de model.metier 
		 * et alimente this.pathMetier. */
		this.fournirPathMetier();
		
		/* alimente this.packageMetier. */
		this.packageMetier = AbstractGenerateur.getPackageMetier();
		
		/* alimente this.pathPackageMetier. */
		this.pathPackageMetier 
			= AbstractGenerateur.getPathPackageMetier();
		
		/* alimente this.pathRelMetier. */
		this.pathRelMetier = AbstractGenerateur.getPathRelMetier();
		
		/* alimente this.pathRelMetierString. */
		this.pathRelMetierString 
			= AbstractGenerateur.getPathRelMetierString();
		
		/* alimente this.pathPackageConcept. */
		this.pathPackageConcept 
			= AbstractGenerateur.getPathPackageConcept();
		
		/* alimente this.pathRelConcept. */
		this.pathRelConcept = AbstractGenerateur.getPathRelConcept();
		
		/* alimente this.pathRelConceptString. */
		this.pathRelConceptString 
			= AbstractGenerateur.getPathRelConceptString();
		
		/* alimente this.pathPackageConceptImpl. */
		this.pathPackageConceptImpl 
			= AbstractGenerateur.getPathPackageConceptImpl();
		
		/* alimente this.pathRelConceptImpl. */
		this.pathRelConceptImpl 
			= AbstractGenerateur.getPathRelConceptImpl();
		
		/* alimente this.pathRelConceptImplString. */
		this.pathRelConceptImplString 
			= AbstractGenerateur.getPathRelConceptImplString();
		
		/* alimente this.fileCouche. */
		this.fileCouche = this.generateurCode.getFileCouche();
		
		/* alimente this.pathCoucheString. */
		this.pathCoucheString 
			= this.generateurCode.getPathCoucheString();
		
		/* alimente this.pathCouche. */
		this.pathCouche = this.generateurCode.getPathCouche();
		
		/* alimente this.pathRelCouche. */
		this.pathRelCouche 
			= this.generateurCode.getPathRelCouche();
		
		/* alimente this.pathRelCoucheJavaString. */
		this.pathRelCoucheJavaString 
			= this.generateurCode.getPathRelCoucheJavaString();
		
		/* alimente this.filePackage. */
		this.filePackage = this.generateurCode.getFilePackage();
		
		/* alimente this.pathPackageString. */
		this.pathPackageString 
			= this.generateurCode.getPathPackageString();
		
		/* alimente this.pathPackage. */
		this.pathPackage = this.generateurCode.getPathPackage();
		
		/* alimente this.pathRelPackage. */
		this.pathRelPackage = this.generateurCode.getPathRelPackage();
		
		/* alimente this.pathRelPackageJavaString. */
		this.pathRelPackageJavaString 
			= this.generateurCode.getPathRelPackageJavaString();
		
		/* alimente this.packageSousCouche. */
		this.packageSousCouche 
			= this.generateurCode.getPackageSousCouche();
		
		/* alimente this.pathSousCoucheString. */
		this.pathSousCoucheString 
			= this.generateurCode.getPathSousCoucheString();
		
		/* alimente this.pathSousCouche. */
		this.pathSousCouche = this.generateurCode.getPathSousCouche();
		
		/* alimente this.pathRelSousCouche. */
		this.pathRelSousCouche 
			= this.generateurCode.getPathRelSousCouche();
		
		/* alimente this.pathRelSousCoucheJavaString. */
		this.pathRelSousCoucheJavaString 
			= this.generateurCode.getPathRelSousCoucheJavaString();
		
		/* alimente this.sousPackageImpl. */
		this.sousPackageImpl = this.generateurCode.getSousPackageImpl();
		
		/* alimente this.pathSousPackageImplString. */
		this.pathSousPackageImplString 
			= this.generateurCode.getPathSousPackageImplString();
		
		/* alimente this.pathSousPackageImpl. */
		this.pathSousPackageImpl 
			= this.generateurCode.getPathSousPackageImpl();
		
		/* alimente this.pathRelSousPackageImpl. */
		this.pathRelSousPackageImpl 
			= this.generateurCode.getPathRelSousPackageImpl();
		
		/* alimente this.pathRelSousPackageImplJavaString. */
		this.pathRelSousPackageImplJavaString 
			= this.generateurCode.getPathRelSousPackageImplJavaString();
		
		/* alimente this.nomInterfaceMetier. */
		this.nomInterfaceMetier 
			= AbstractGenerateur.getNomInterfaceMetier();
		
		/* alimente this.nomAbstractClassMetier. */
		this.nomAbstractClassMetier 
			= AbstractGenerateur.getNomAbstractClassMetier();
		
		/* alimente this.nomClassMetier. */
		this.nomClassMetier 
			= AbstractGenerateur.getNomClassMetier();
		
	} // Fin de alimenterAttributsGeneraux().______________________________
	
	
	
	/**
	 * method alimenterAttributsFichiersAEcrire(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Alimente tous les attributs de la classe relatifs 
	 * aux fichiers à écrire 
	 * (nomSimpleInterface, nomSimpleAbstractClass, ...).</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 */
	private void alimenterAttributsFichiersAEcrire(
			final File pFile) {
		
		/* alimente this.nomSimpleInterface. */
		this.nomSimpleInterface 
			= this.generateurCode.getNomSimpleInterface();
		
		/* alimente this.nomSimpleAbstractClass. */
		this.nomSimpleAbstractClass 
			= this.generateurCode.getNomSimpleAbstractClass();
		
		/* alimente this.nomSimpleConcreteClass. */
		this.nomSimpleConcreteClass 
			= this.generateurCode.getNomSimpleConcreteClass();

		/* alimente this.fichierJava. */
		this.fichierJava = pFile;
		
		/* alimente this.nomSimpleFichierJava. */
		this.nomSimpleFichierJava 
			= this.fournirNomFichierSansExtension(this.fichierJava);
		
	} // Fin de alimenterAttributsFichiersAEcrire(...).____________________
	
	
		
	/**
	 * method ecrireCodeGenerique() :<br/>
	 * <ul>
	 * <li>écrit la ligne de code PACKAGE (1ère ligne).</li>
	 * <li>Insère 1 ligne vide sous la ligne de code package.</li>
	 * <li>Ecrit les IMPORTS à partir de la 3ème ligne.</li>
	 * <li>Insère 3 lignes vides sous la dernière ligne d'imports.</li>
	 * <li>Ecrit la JAVADOC à la suite.</li>
	 * <li>Ecrit l'ENTITY à la suite.</li>
	 * <li>Ecrit la ligne de DECLARATION de la classe à la suite.</li>
	 * <li>Appelle un HOOK this.ecrireCodeHook(....) pour terminer 
	 * la génération du code dans un Ecriveur concret.</li>
	 * <li>Ecrit la ligne FINALE.</li>
	 * </ul>
	 *
	 * @param pFile : File : fixhier dans lequel on génère le code.<br/>
	 * @param pGenerateur : IGenerateur.<br/>
	 * 
	 * @throws Exception<br/>
	 */
	protected abstract void ecrireCodeGenerique(
			File pFile
				, IGenerateur pGenerateur) throws Exception;
	
	
	
	/**
	 * method fournirPathMetier() :<br/>
	 * <ul>
	 * <li>Détermine le path relatif de model.metier par rapport 
	 * à la racine des sources java.</li>
	 * <li>alimente this.pathMetier.</li>
	 * <li>Par exemple : <code>levy.daniel.application
	 * .model.metier</code>.</li>
	 * </ul>
	 *
	 * @return String : path relatif de metier.<br/>
	 */
	private String fournirPathMetier() {
				
		this.pathMetier = AbstractGenerateur.getPathRelMetierString();
		
		return this.pathMetier;
		
	} // Fin de fournirPathMetier()._______________________________________
	
	
		
	/**
	 * method trouverNumeroLigne(
	 * File pFile
	 * , Charset pCharsetLecture
	 * , String pLigne) :<br/>
	 * <ul>
	 * <li><b>Trouvel le numéro (1-based)</b> de la ligne pLigne 
	 * dans le fichier pFile.</li>
	 * <li>Trouvel la première ocurrence de la ligne pLigne 
	 * dans le fichier pFile.</li>
	 * <li>Lit le fichier textuel simple 
	 * pFile avec l'encodage pCharsetLecture.</li>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour la lecture 
	 * si pCharsetLecture est null.</li>
	 * </ul>
	 * Retourne 0 si pFile est null.<br/>
	 * Retourne 0 si pFile n'existe pas sur le disque.<br/>
	 * Retourne 0 si pFile est un répertoire.<br/>
	 * Retourne 0 si pLigne est blank.<br/>
	 * Retourne 0 si pLigne n'est pas trouvée.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier textuel simple dans lequel 
	 * on veut trouver le numéro (1-based) de la ligne pLigne.<br/>
	 * @param pCharsetLecture : Charset : Charset dans lequel 
	 * le fichier simple textuel pFile est encodé. 
	 * On le lit donc avec ce Charset.<br/>
	 * @param pLigne : String : ligne à trouver.<br/>
	 * 
	 * @return : iint : numéro 1-based de la ligne pLigne.<br/>
	 */
	protected final int trouverNumeroLigne(
			final File pFile
				, final Charset pCharsetLecture
					, final String pLigne) {
		
		/* Retourne 0 si pFile est null. */
		if (pFile == null) {
			return 0;
		}
		
		/* Retourne 0 si pFile n'existe pas sur le disque. */
		if (!pFile.exists()) {
			return 0;
		}
		
		/* Retourne 0 si pFile est un répertoire. */
		if (pFile.isDirectory()) {
			return 0;
		}
		
		/* Retourne 0 si pLigne est blank. */
		if (StringUtils.isBlank(pLigne)) {
			return 0;
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
	
		int numeroLigne = 0;
		
		// ****************************************************
		/* LECTURE. */
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		
		try {
	
			/* LECTURE DU FICHIER AVEC CHARSET charsetLecture. */
			inputStream = new FileInputStream(pFile);
			inputStreamReader 
				= new InputStreamReader(inputStream, charsetLecture);
			bufferedReader = new BufferedReader(inputStreamReader);
	
	
			String ligneLue = null;
			int numeroLigneLue = 0;
			
			/* BOUCLE SUR LES LIGNES DE pFile. */
			while (true) {
														
				/* Lecture de la ligne. */
				ligneLue = bufferedReader.readLine();
				
				/* Fin de boucle à la fin du fichier. */
				if (ligneLue == null) {
					break;
				}
				
				/* Incrémentation du compteur de ligne. */
				numeroLigneLue++;
	
				/* capture le numéro de ligne si pLigne est trouvée. */
				if (StringUtils.equals(pLigne, ligneLue)) {
					numeroLigne = numeroLigneLue;
					break;
				}
	
			} // Fin de BOUCLE SUR LES LIGNES DE pFile._____
			
		} catch (FileNotFoundException e) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
					, METHODE_TROUVERNUMEROLIGNE
					, e);
			
			/* retourne 0. */
			return 0;
			
		} catch (IOException e) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
					, METHODE_TROUVERNUMEROLIGNE
					, e);
			
			/* retourne 0. */
			return 0;
			
		}
		
		finally {
			
			/* Fermeture des flux de lecture. */
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_TROUVERNUMEROLIGNE
							, e);
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_TROUVERNUMEROLIGNE
							, e);
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_TROUVERNUMEROLIGNE
							, e);
				}
			}
											
		} // Fin du finally._______________________________
	
		return numeroLigne;
	
	} // Fin de trouverNumeroLigne(...).___________________________________
	
	
	
	
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
	protected final List<String> lireStringsDansFile(
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
			loggerError(this.fournirNomClasse()
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
			loggerError(this.fournirNomClasse()
					, METHODE_LIRE_STRINGS_DANS_FILE
					, ioe);
			
			final String message 
			= this.fournirNomClasse() 
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
	 * method lireLigneDansFichier(
	 * File pFile
	 * , Charset pCharsetLecture
	 * , int pNumLigne) :<br/>
	 * <ul>
	 * <li><b>Lit et retourne la pNumLigne-ième ligne 
	 * (1-based)</b> du fichier textuel simple pFile.</li>
	 * <li>Lit le fichier textuel simple 
	 * pFile avec l'encodage pCharsetLecture.</li>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour la lecture 
	 * si pCharsetLecture est null.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas sur le disque.<br/>
	 * - Retourne null si pFile est un répertoire.<br/>
	 * - Retourne null si pNumLigne == 0.<br/>
	 * - Retourne null en cas d'Exception loggée (IOException, ...).<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier textuel simple dans lequel 
	 * on veut lire la pNumLigne-ième (1-based) ligne.<br/>
	 * @param pCharsetLecture : Charset : Charset dans lequel 
	 * le fichier simple textuel pFile est encodé. 
	 * On le lit donc avec ce Charset.<br/>
	 * @param pNumLigne : int : numéro (1-based) 
	 * de la ligne à lire dans pFile.<br/>
	 * 
	 * @return : String : la pNumLigne-ième ligne (1-based) de pFile.<br/>
	 */
	protected final String lireLigneDansFichier(
			final File pFile
				, final Charset pCharsetLecture
					, final int pNumLigne) {
		
		/* Retourne null si pFile est null. */
		if (pFile == null) {
			return null;
		}
		
		/* Retourne null si pFile n'existe pas sur le disque. */
		if (!pFile.exists()) {
			return null;
		}
		
		/* Retourne null si pFile est un répertoire. */
		if (pFile.isDirectory()) {
			return null;
		}
		
		/* Retourne null si pNumLigne == 0. */
		if (pNumLigne == 0) {
			return null;
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
		
		String ligneNumerotee = null;
		
		// ****************************************************
		/* LECTURE. */
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		
		try {
	
			/* LECTURE DU FICHIER AVEC CHARSET charsetLecture. */
			inputStream = new FileInputStream(pFile);
			inputStreamReader 
				= new InputStreamReader(inputStream, charsetLecture);
			bufferedReader = new BufferedReader(inputStreamReader);
	
	
			String ligneLue = null;
			int numeroLigneLue = 0;
			
			/* BOUCLE SUR LES LIGNES DE pFile. */
			while (true) {
														
				/* Lecture de la ligne. */
				ligneLue = bufferedReader.readLine();
				
				/* Fin de boucle à la fin du fichier. */
				if (ligneLue == null) {
					break;
				}
				
				/* Incrémentation du compteur de ligne. */
				numeroLigneLue++;
	
				/* Saute la ligne 
				 * si la position dans le fichier pFile est pNumLigne. */
				if (numeroLigneLue == pNumLigne) {
					ligneNumerotee = ligneLue;
				}
	
			} // Fin de BOUCLE SUR LES LIGNES DE pFile._____
			
		} catch (FileNotFoundException e) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
					, METHODE_LIRE_LIGNE_N_DANS_FICHIER
					, e);
			
			/* retourne null. */
			return null;
			
		} catch (IOException e) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
					, METHODE_LIRE_LIGNE_N_DANS_FICHIER
					, e);
			
			/* retourne null. */
			return null;
			
		}
		
		finally {
			
			/* Fermeture des flux de lecture. */
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_LIRE_LIGNE_N_DANS_FICHIER
							, e);
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_LIRE_LIGNE_N_DANS_FICHIER
							, e);
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_LIRE_LIGNE_N_DANS_FICHIER
							, e);
				}
			}
											
		} // Fin du finally._______________________________
	
		return ligneNumerotee;
			
	} // Fin de lireLigneDansFichier(
	 // File pFile
	 // , Charset pCharsetLecture
	 // , int pNumLigne).__________________________________________________
	
	
	
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
	protected final boolean existLigneDansFichier(
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
				if (StringUtils.equals(pLigne, ligneLue)) {
					resultat = true;
					break;
				}
	
			} // Fin de BOUCLE SUR LES LIGNES DE pFile._____
			
		} catch (FileNotFoundException e) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
					, METHODE_EXISTLIGNEDANSFICHIER
					, e);
			
			/* retourne false. */
			return false;
			
		} catch (IOException e) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
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
							this.fournirNomClasse()
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
							this.fournirNomClasse()
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
							this.fournirNomClasse()
							, METHODE_EXISTLIGNEDANSFICHIER
							, e);
				}
			}
											
		} // Fin du finally._______________________________
	
		return resultat;
		
	} // Fin de existLigneDansFichier(...).________________________________
	
	
	
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
	protected final boolean existLigneCommencant(
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
					this.fournirNomClasse()
					, METHODE_EXISTLIGNECOMMENCANT
					, e);
			
			/* retourne false. */
			return false;
			
		} catch (IOException e) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
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
							this.fournirNomClasse()
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
							this.fournirNomClasse()
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
							this.fournirNomClasse()
							, METHODE_EXISTLIGNECOMMENCANT
							, e);
				}
			}
											
		} // Fin du finally._______________________________
	
		return resultat;
		
	} // Fin de existLigneCommencant(...)._________________________________
	
	
	
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
	protected final File ecrireStringDansFile(
			final File pFile
				, final String pString
					, final Charset pCharset
						, final String pSautLigne) {
		
		/* retourne null si le pFile est null. */
		if (pFile == null) {
			
			/* LOG de niveau INFO. */
			this.loggerInfo(
					this.fournirNomClasse()
						, METHODE_ECRIRESTRINGDANSFILE
							, MESSAGE_FICHIER_NULL);
			
			/* retour de null. */
			return null;
			
		} // Fin de if (pFile == null).______________________
		
		/* retourne null si le pFile est un répertoire. */
		if (pFile.isDirectory()) {
			
			/* LOG de niveau INFO. */
			this.loggerInfo(
					this.fournirNomClasse()
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
						this.fournirNomClasse()
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
					this.fournirNomClasse()
						, MESSAGE_EXCEPTION				
							, fnfe);
			
			/* retour de null. */
			return null;
			
		} catch (IOException ioe) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
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
							this.fournirNomClasse()
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
							this.fournirNomClasse()
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
							this.fournirNomClasse()
								, MESSAGE_EXCEPTION				
									, ioe3);
				}
			}
			
		} // Fin du finally.____________________________
			
	} // Fin de ecrireStringDansFile(...)._________________________________
	
	
		
	/**
	 * method ecrireStringDansFileSansSaut(
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
	 * <li>ne <b>rajoute pas</b> automatiquement 
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
	protected final File ecrireStringDansFileSansSaut(
			final File pFile
				, final String pString
					, final Charset pCharset
						, final String pSautLigne) {
		
		/* retourne null si le pFile est null. */
		if (pFile == null) {
			
			/* LOG de niveau INFO. */
			this.loggerInfo(
					this.fournirNomClasse()
						, METHODE_ECRIRESTRINGDANSFILE
							, MESSAGE_FICHIER_NULL);
			
			/* retour de null. */
			return null;
			
		} // Fin de if (pFile == null).______________________
		
		/* retourne null si le pFile est un répertoire. */
		if (pFile.isDirectory()) {
			
			/* LOG de niveau INFO. */
			this.loggerInfo(
					this.fournirNomClasse()
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
						this.fournirNomClasse()
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
			
			bufferedWriter.flush();
			
			// Retour du fichier. 
			return pFile;
			
		} catch (FileNotFoundException fnfe) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
						, MESSAGE_EXCEPTION				
							, fnfe);
			
			/* retour de null. */
			return null;
			
		} catch (IOException ioe) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
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
							this.fournirNomClasse()
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
							this.fournirNomClasse()
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
							this.fournirNomClasse()
								, MESSAGE_EXCEPTION				
									, ioe3);
				}
			}
			
		} // Fin du finally.____________________________
			
	} // Fin de ecrireStringDansFileSansSaut(...)._________________________
	
	
	
	/**
	 * method lireRessource(
	 * String pString) :<br/>
	 * <ul>
	 * <li><b>Lit le contenu d'une ressource pString</b> 
	 * sous src/main/resources dans le présent projet 
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
	protected final List<String> lireRessource(
			final String pString) throws Exception {
		
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
			= this.lireStringsDansFile(fileRessource, CHARSET_UTF8);
		
		return listeLignes;
			
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
	protected final List<String> lireTemplate(
			final String pString) throws Exception {
		
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
			= this.lireStringsDansFile(fileRessource, CHARSET_UTF8);
		
		return listeLignes;
			
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
	protected final void ecrireCode(
			final List<String> pList
				, final File pFile) throws Exception {
		
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
				
				this.ecrireStringDansFile(
						pFile, "", CHARSET_UTF8, NEWLINE);					
			}				
			else {
				
				this.ecrireStringDansFile(
						pFile, ligne, CHARSET_UTF8, NEWLINE);
			}
		}
			
	} // Fin de ecrireCode(...).___________________________________________


	
	/**
	 * method insererLigneDansFichier(
	 * File pFile
	 * , Charset pCharsetLecture
	 * , int pNumLigne
	 * , Charset pCharsetEcriture
	 * , String pLigneAInserer) :<br/>
	 * <ul>
	 * <li><b>Insère la ligne pLigneAInserer</b> à la pNumLigne-ième ligne 
	 * (1-based) du fichier textuel simple pFile.</li>
	 * <li>Lit le fichier textuel simple 
	 * pFile avec l'encodage pCharsetLecture.</li>
	 * <li>Ecrit la ligne pLigneAInserer dans le fichier simple textuel pFile 
	 * avec l'encodage pCharsetEcriture.</li>
	 * <li>insère un saut de ligne à la fin de la ligne insérée.</li>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour la lecture 
	 * si pCharsetLecture est null.</li>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour l'écriture
	 * si pCharsetEcriture est null.</li>
	 * </ul> 
	 * </ul>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas sur le disque.<br/>
	 * - Retourne null si pFile est un répertoire.<br/>
	 * - Retourne null si pNumLigne == 0.<br/>
	 * - Retourne null en cas d'Exception loggée (IOException, ...).<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier textuel simple dans lequel 
	 * on veut insérer pLigneAInserer.<br/>
	 * @param pCharsetLecture : Charset : Charset dans lequel 
	 * le fichier simple textuel pFile est encodé. 
	 * On le lit donc avec ce Charset.<br/>
	 * @param pNumLigne : int : numéro (1-based) 
	 * de la ligne à laquelle il faut insérer pLigneAInserer.<br/>
	 * @param pCharsetEcriture : Charset : Charset avec lequel 
	 * on écrit pLigneAInserer dans pFile.<br/>
	 * @param pLigneAInserer : String : ligne à insérer dans pFile.<br/>
	 * 
	 * @return : File : Fichier simple textuel dans lequel 
	 * on a inséré la ligne pLigneAInserer à la pNumLigne-ième ligne.<br/>
	 */
	protected final File insererLigneDansFichier(
			final File pFile
				, final Charset pCharsetLecture
					, final int pNumLigne
						, final Charset pCharsetEcriture
							, final String pLigneAInserer) {
	
		/* Retourne null si pFile est null. */
		if (pFile == null) {
			return null;
		}
		
		/* Retourne null si pFile n'existe pas sur le disque. */
		if (!pFile.exists()) {
			return null;
		}
		
		/* Retourne null si pFile est un répertoire. */
		if (pFile.isDirectory()) {
			return null;
		}
		
		/* Retourne null si pNumLigne == 0. */
		if (pNumLigne == 0) {
			return null;
		}
				
		Charset charsetLecture = null;
		Charset charsetEcriture = null;
		
		/* Utilise automatiquement le CHARSET_UTF8 si 
		 * pCharsetLecture est null. */			
		if (pCharsetLecture == null) {
			charsetLecture = CHARSET_UTF8;
		}
		else {
			charsetLecture = pCharsetLecture;
		}
		
		/* Utilise automatiquement le CHARSET_UTF8 si 
		 * pCharsetEcriture est null. */			
		if (pCharsetEcriture == null) {
			charsetEcriture = CHARSET_UTF8;
		}
		else {
			charsetEcriture = pCharsetEcriture;
		}
	
		File fichierProvisoire = null;
		final Path pathPFile = pFile.toPath();
		
		try {
			
			/* Création d'un fichier provisoire pour l'insertion. */
			fichierProvisoire = fournirFichierAPartirDeFile(pFile, "1");
			
			if (!fichierProvisoire.exists()) {
				Files.createFile(fichierProvisoire.toPath());
			}
			
			
		} catch (IOException e1) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
					, METHODE_INSERER_LIGNE
					, e1);
			
			/* retourne null. */
			return null;
		}
		
		
		// ****************************************************
		/* LECTURE. */
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		
		/* ECRITURE. */
		OutputStream outputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		
		
		try {
	
			/* LECTURE DU FICHIER AVEC CHARSET charsetLecture. */
			inputStream = new FileInputStream(pFile);
			inputStreamReader 
				= new InputStreamReader(inputStream, charsetLecture);
			bufferedReader = new BufferedReader(inputStreamReader);
	
			/*
			 * ECRITURE DANS LE FICHIER DESTINATION AVEC LE CHARSET
			 * charsetEcriture.
			 */
			/* Ecrit au début de fichierProvisoire. */
			outputStream = new FileOutputStream(fichierProvisoire);
			outputStreamWriter = new OutputStreamWriter(outputStream,
					charsetEcriture);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
	
			String ligneLue = null;
			int numeroLigneLue = 0;
			
			/* BOUCLE SUR LES LIGNES DE pFile. */
			while (true) {
				
				/* Incrémentation du compteur de ligne. */
				numeroLigneLue++;
				
				/* Insère la ligne pLigneAInserer dans fichierProvisoire 
				 * si la position dans le fichier pFile est pNumLigne. */
				if (numeroLigneLue == pNumLigne) {
					bufferedWriter.write(pLigneAInserer);
					/* insère un saut de ligne à la fin 
					 * de la ligne insérée. */
					bufferedWriter.write(NEWLINE);
				}
				
				/* Lecture de la ligne. */
				ligneLue = bufferedReader.readLine();
				
				/* Fin de boucle à la fin du fichier. */
				if (ligneLue == null) {
					break;
				}
				
				/* Ecrit la ligne lue dans le fichierProvisoire. */
				bufferedWriter.write(ligneLue);
				bufferedWriter.write(NEWLINE);
				
				/* Flush du tampon. */
				bufferedWriter.flush();
				
			} // Fin de BOUCLE SUR LES LIGNES DE pFile._____
			
		} catch (FileNotFoundException e) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
					, METHODE_INSERER_LIGNE
					, e);
			
			/* retourne null. */
			return null;
			
		} catch (IOException e) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
					, METHODE_INSERER_LIGNE
					, e);
			
			/* retourne null. */
			return null;
			
		}
		
		finally {
			
			/* Fermeture des flux de lecture. */
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_INSERER_LIGNE
							, e);
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_INSERER_LIGNE
							, e);
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_INSERER_LIGNE
							, e);
				}
			}
			
			/* Fermeture des flux d'écriture. */
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_INSERER_LIGNE
							, e);
					
				}
			}
			if (outputStreamWriter != null) {
				try {
					outputStreamWriter.close();
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_INSERER_LIGNE
							, e);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_INSERER_LIGNE
							, e);
					
				}
			}
			
			
			try {
				
				/* Destruction du fichier original. */
				Files.deleteIfExists(pathPFile);
				
				/* Renommage du fichier provisoire en pFile. */
				Files.move(
						fichierProvisoire.toPath()
						, pathPFile
						, StandardCopyOption.REPLACE_EXISTING);
				
			} catch (IOException e) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						this.fournirNomClasse()
						, METHODE_INSERER_LIGNE
						, e);
			}
							
		} // Fin du finally._______________________________
		
		return pFile;
				
	} // Fin de insererLigneDansFichier(
	 // File pFile
	 // , Charset pCharsetLecture
	 // , int pNumLigne
	 // , Charset pCharsetEcriture
	 // , String pLigneAInserer.___________________________________________
	
	
		
	/**
	 * method insererLignesVidesSousLigneDansFichier(
	 * File pFile
	 * , String pLigne
	 * , int pNombreLignesVides
	 * , Charset pCharsetLecture) :<br/>
	 * <ul>
	 * <li><b>Insère pNombreLignesVides lignes vides 
	 * sous la ligne pLigne</b>.</li>
	 * <li>N'insère que si les lignes vides n'existent pas déjà sous pLigne.</li>
	 * <li>retourne null et ne fait rien si la ligne pLigne 
	 * n'est pas trouvée.</li>
	 * </ul>
	 * Retourne null si pFile est null.<br/>
	 * Retourne null si pFile n'existe pas sur le disque.<br/>
	 * Retourne null si pFile est un répertoire.<br/>
	 * <br/>
	 * 
	 *
	 * @param pFile : File : fichier Java.<br/>
	 * @param pLigne : String : ligne sous laquelle insérer.<br/>
	 * @param pNombreLignesVides : int : nombre de lignes 
	 * vides à insérer sous pLigne.<br/>
	 * 
	 * @return : File : fichier Java.<br/>
	 */
	protected final File insererLignesVidesSousLigneDansFichier(
			final File pFile
				, final String pLigne
					, final int pNombreLignesVides
						, final Charset pCharsetLecture) {
		
		
		/* Retourne null si pFile est null. */
		if (pFile == null) {
			return null;
		}
		
		/* Retourne null si pFile n'existe pas sur le disque. */
		if (!pFile.exists()) {
			return null;
		}
		
		/* Retourne null si pFile est un répertoire. */
		if (pFile.isDirectory()) {
			return null;
		}
		
		/* Récupère le numéro (1-based) de la ligne 
		 * sous laquelle insérer des lignes vides. */
		final int numeroLigne 
			= this.trouverNumeroLigne(pFile, pCharsetLecture, pLigne);
		
		/* retourne null et ne fait rien si la 
		 * ligne pLigne n'est pas trouvée. */
		if (numeroLigne == 0) {
			return null;
		}
		
		final int numPremiereLigneAInserer = numeroLigne + 1;
		
		for (int i = 0; i < pNombreLignesVides; i++) {
			
			final int numLigne = numPremiereLigneAInserer + i;
			
			final String ligne 
			= this.lireLigneDansFichier(
					pFile, pCharsetLecture, numLigne);
			
			if (ligne==null || ligne.length() != 0) {
				
				/* Insertion des lignes vides. */
				this.insererLigneDansFichier(
						pFile
						, pCharsetLecture
						, numLigne
						, pCharsetLecture
						, "");
				
			}
						
		}
	
		return pFile;
		
	} // Fin de insererLignesVidesSousLigneDansFichier(...)._______________
	
	
	
	/**
	 * method fournirFichierAPartirDeFile(
	 * File pFile
	 * , String pSuffixe) :<br/>
	 * <ul>
	 * <li><b>Fournit un fichier (vide) dans le même répertoire 
	 * que pFile en le nommant pFile_suffixe.ext</b>.</li>
	 * <ul>
	 * <li>Récupère le chemin de pFile avec 
	 * la méthode pFile.getParent().</li>
	 * <li>Récupère le nom simple de pFile 
	 * avec la méthode pFile.getName().</li>
	 * <li>Décompose le nom simple de pFile en nom de base et extension 
	 * avec la méthode StringUtils.split(nomSimple, '.').</li>
	 * <li>Récupère le séparateur de fichiers auprès de la plateforme 
	 * avec la méthode System.getProperty("file.separator").</li>
	 * <li>Construit le fichier suffixé dans 
	 * le même répertoire que pFile.</li>
	 * </ul>
	 * </ul>
	 * <br/>
	 * - Retourne null si pFile == null.<br/>
	 * - Retourne null si pSuffixe est blank.<br/>
	 * - Retourne null si le chemin de pFile == null.<br/>
	 * - Retourne null si le nom simple de pFile est blank.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à partir duquel 
	 * on va créer un fichier suffixé 
	 * avec pSuffixe dans le même répertoire.<br/>
	 * @param pSuffixe : String : suffixe à ajouter.<br/>
	 * 
	 * @return : File : Fichier résultat.<br/>
	 */
	protected final File fournirFichierAPartirDeFile(
			final File pFile
				, final String pSuffixe) {
		
		/* Retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		/* Retourne null si pSuffixe est blank. */
		if (StringUtils.isBlank(pSuffixe)) {
			return null;
		}
		
		/* Récupère le chemin de pFile 
		 * avec la méthode pFile.getParent(). */
		final String cheminFile = pFile.getParent();
		
		/* Retourne null si le chemin de pFile == null. */
		if (cheminFile == null) {
			return null;
		}
		
		/* Récupère le nom simple de pFile 
		 * avec la méthode pFile.getName(). */		
		final String nomSimpleFile = pFile.getName();
		
		/* Retourne null si le nom simple de pFile est blank. */
		if (StringUtils.isBlank(nomSimpleFile)) {
			return null;
		}
		
		/* Décompose le nom simple en nom de base et extension 
		 * avec la méthode StringUtils.split(nomSimple, '.'). */
		final String[] nomsTableau = StringUtils.split(nomSimpleFile, '.');
		
		if (nomsTableau == null) {
			return null;
		}
		
		if (nomsTableau.length == 0) {
			return null;
		}
		
		/* Récupère le séparateur de fichiers auprès de la plateforme 
		 * avec la méthode System.getProperty("file.separator"). */
		final String separateurFichiers 
			= System.getProperty("file.separator");
		String nomBase = null;
		String extension = null;
		String nomSuffixe = null;
				
		if (nomsTableau.length == 1) {
			nomBase = nomsTableau[0];
			nomSuffixe = nomBase + '_' + pSuffixe;
		}
		else if (nomsTableau.length >= 2) {
			nomBase = nomsTableau[0];
			extension = nomsTableau[nomsTableau.length - 1];
			nomSuffixe = nomBase + '_' + pSuffixe + '.' + extension;
		}
		
		/* Construit le chemin du fichier suffixé. */
		final String cheminFichierResultat 
			= cheminFile + separateurFichiers + nomSuffixe;
		
		/* Construit le fichier suffixé 
		 * dans le même répertoire que pFile. */
		final File resultat = new File(cheminFichierResultat);
				
		return resultat;
			
	} // Fin de fournirFichierAPartirDeFile(
	 // File pFile
	 // , String pSuffixe).________________________________________________
	
	
	
	/**
	 * method fournirFichierAPartirDeFile(
	 * File pFile
	 * , String pSuffixe
	 * , File pRepertoire) :<br/>
	 * <ul>
	 * <li><b>Fournit un fichier (vide) dans pRepertoire 
	 * en le nommant pFile_suffixe.ext</b>.</li>
	 * <ul>
	 * <li>Récupère le nom simple de pFile 
	 * avec la méthode pFile.getName().</li>
	 * <li>Décompose le nom simple de pFile en nom de base et extension 
	 * avec la méthode StringUtils.split(nomSimple, '.').</li>
	 * <li>Récupère le séparateur de fichiers auprès de la plateforme 
	 * avec la méthode System.getProperty("file.separator").</li>
	 * <li>Construit le fichier suffixé dans 
	 * pRepertoire.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si pFile == null.<br/>
	 * - Retourne null si pSuffixe est blank.<br/>
	 * - Retourne null si pRepertoire == null.<br/>
	 * - Retourne null si le nom simple de pFile est blank.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à partir duquel 
	 * on va créer un fichier suffixé 
	 * avec pSuffixe dans pRepertoire.<br/>
	 * @param pSuffixe : String : suffixe à ajouter.<br/>
	 * @param pRepertoire : File : répertoire dans lequel 
	 * il faut créer le nouveau fichier suffixé.<br/>
	 * 
	 * @return : File : Fichier résultat.<br/>
	 */
	protected final File fournirFichierAPartirDeFile(
			final File pFile
				, final String pSuffixe
					, final File pRepertoire) {
		
		/* Retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		/* Retourne null si pSuffixe est blank. */
		if (StringUtils.isBlank(pSuffixe)) {
			return null;
		}
		
		/* Retourne null si pRepertoire == null. */
		if (pRepertoire == null) {
			return null;
		}
				
		/* Récupère le nom simple de pFile 
		 * avec la méthode pFile.getName(). */		
		final String nomSimpleFile = pFile.getName();
		
		/* Retourne null si le nom simple de pFile est blank. */
		if (StringUtils.isBlank(nomSimpleFile)) {
			return null;
		}
		
		/* Décompose le nom simple en nom de base et extension 
		 * avec la méthode StringUtils.split(nomSimple, '.'). */
		final String[] nomsTableau = StringUtils.split(nomSimpleFile, '.');
		
		if (nomsTableau == null) {
			return null;
		}
		
		if (nomsTableau.length == 0) {
			return null;
		}
		
		/* Récupère le séparateur de fichiers auprès de la plateforme 
		 * avec la méthode System.getProperty("file.separator"). */
		final String separateurFichiers 
			= System.getProperty("file.separator");
		String nomBase = null;
		String extension = null;
		String nomSuffixe = null;
				
		if (nomsTableau.length == 1) {
			nomBase = nomsTableau[0];
			nomSuffixe = nomBase + '_' + pSuffixe;
		}
		else if (nomsTableau.length >= 2) {
			nomBase = nomsTableau[0];
			extension = nomsTableau[nomsTableau.length - 1];
			nomSuffixe = nomBase + '_' + pSuffixe + '.' + extension;
		}
		
		/* Construit le chemin du fichier suffixé. */
		final String cheminFichierResultat 
			= pRepertoire.getAbsolutePath() 
			+ separateurFichiers 
			+ nomSuffixe;
		
		/* Construit le fichier suffixé dans pRepertoire. */
		final File resultat = new File(cheminFichierResultat);
				
		return resultat;
			
	} // Fin de fournirFichierAPartirDeFile(
	 // File pFile
	 // , String pSuffixe
	 // , File pRepertoire)._______________________________________________
	
	
	
	/**
	 * method compterLignes(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>Compte le nombre de lignes dans un fichier texte</b>.</li>
	 * </ul>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas sur le disque.<br/>
	 * - Retourne null si pFile est un répertoire.<br/>
	 * - Retourne null en cas d'Exception loggée (IOException, ...).<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @return : Integer : Nombre de lignes dans le fichier pFile.<br/>
	 */
	protected final Integer compterLignes(
			final File pFile) {
		
		/* Retourne null si pFile est null. */
		if (pFile == null) {
			return null;
		}
		
		/* Retourne null si pFile n'existe pas sur le disque. */
		if (!pFile.exists()) {
			return null;
		}
		
		/* Retourne null si pFile est un répertoire. */
		if (pFile.isDirectory()) {
			return null;
		}
		
		String ligneLue = null;
		Integer numeroLigneLue = 0;
		
		// ****************************************************
		/* LECTURE. */
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		
		
		try {
			
			/* LECTURE DU FICHIER. */
			inputStream = new FileInputStream(pFile);
			inputStreamReader 
			= new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			
			/* BOUCLE SUR LES LIGNES DE pFile. */
			while (true) {
													
				/* Lecture de la ligne. */
				ligneLue = bufferedReader.readLine();
				
				/* Fin de boucle à la fin du fichier. */
				if (ligneLue == null) {
					break;
				}
				
				/* Incrémentation du compteur de ligne. */
				numeroLigneLue++;
								
			} // Fin de BOUCLE SUR LES LIGNES DE pFile._____
			
		} catch (FileNotFoundException e) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
					, METHODE_COMPTER_LIGNES
					, e);
			
			/* retourne null. */
			return null;
			
		} catch (IOException e) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasse()
					, METHODE_COMPTER_LIGNES
					, e);
			
			/* retourne null. */
			return null;
			
		}
		finally {
			
			/* Fermeture des flux. */
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_COMPTER_LIGNES
							, e);
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_COMPTER_LIGNES
							, e);
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasse()
							, METHODE_COMPTER_LIGNES
							, e);
				}
			}
		} // Fin du finally._____________________________
					
		return numeroLigneLue;
					
	} // Fin de compterLignes(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * method ajouterLignesVides(
	 * int pNombre
	 * , List&lt;String&gt; pList) :<br/>
	 * <ul>
	 * <li>Ajoute pNombre lignes vides à la fin de la liste pList.</li>
	 * </ul>
	 * ne fait rien si pNombre < 1.<br/>
	 * ne fait rien si pList == null.<br/>
	 * <br/>
	 *
	 * @param pNombre : int :  .<br/>
	 * @param pList : List&lt;String&gt; :  .<br/>
	 */
	protected final void ajouterLignesVides(
			final int pNombre
				, final List<String> pList) {
		
		/* ne fait rien si pNombre < 1. */
		if (pNombre < 1) {
			return;
		}
		
		/* ne fait rien si pList == null. */
		if (pList == null) {
			return;
		}
		
		for (int i = 0; i < pNombre; i++) {
			pList.add("");
		}
		
	} // Fin de ajouterLignesVides(...).___________________________________
	
	
	
	/**
	 * method substituerSautLignePlateforme(
	 * String pString) :<br/>
	 * <ul>
	 * <li><b>Substitue les sauts</b> de ligne <b>à l'intérieur de</b> 
	 * pString 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * par les sauts de ligne de la plate-forme
	 * sur laquelle le programme s'exécute.</li>
	 * </ul>
	 * - retourne null si pString est blank (null ou vide).<br/>
	 * <br/>
	 *
	 * @param pString : String : String à corriger.<br/>
	 * 
	 * @return : String : La String dans laquelle les sauts de ligne 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * ont été substitués par les sauts de ligne de la plate-forme.<br/>
	 */
	protected final String substituerSautLignePlateforme(
			final String pString) {
		
		return substituerSautLigne(pString, NEWLINE);
		
	} // Fin de method substituerSautLignePlateforme(
	 // String pString).___________________________________________________
	
	
	
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
	protected final String substituerSautLigne(
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
	protected final List<String> substituerVariablesDansLigne(
			final List<String> pListe
				, final String pVariable
					, final String pSubstituant) {
		
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
		
	} // Fin de substituerVariablesDansLigne.______________________________
	
	
	
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
	protected final void loggerInfo(
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
	protected final void loggerInfo(
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
	protected final void loggerError(
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
	 * method remplacerPointparSlash(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Remplace les points '.' dans pString par des slashs '/'.</li>
	 * <li>Exemple : "levy.daniel.application" 
	 * remplacé par "levy/daniel/application".</li>
	 * </ul>
	 * retourne null si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : String : String dans laquelle les points 
	 * ont été remplacés par des slash.<br/>
	 */
	protected final String remplacerPointparSlash(
			final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		String resultat = null;
		
		resultat = StringUtils.replaceChars(pString, POINT, SLASH);
		
		return resultat;
		
	} // Fin de remplacerPointparSlash(...)._______________________________
	
	
	
	/**
	 * method remplacerSlashparPoint(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Remplace les slashs '/' dans pString par des points '.'.</li>
	 * <li>Exemple : "levy/daniel/application" 
	 * remplacé par "levy.daniel.application".</li>
	 * </ul>
	 * retourne null si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : String : String dans laquelle les slashs 
	 * ont été remplacés par des points.<br/>
	 */
	protected final String remplacerSlashparPoint(
			final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		String resultat = null;
		
		resultat = StringUtils.replaceChars(pString, SLASH, POINT);
		
		return resultat;
		
	} // Fin de remplacerSlashparPoint(...)._______________________________
	
	
		
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
	protected final String remplacerAntiSlashparPoint(
			final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		String resultat = null;
		
		resultat = StringUtils.replaceChars(pString, ANTISLASH, POINT);
		
		return resultat;
		
	} // Fin de remplacerAntiSlashparPoint(...)._______________________________
	
	
	
	/**
	 * method fournirDerniereLigneListe(
	 * List&lt;String&gt; pList) :<br/>
	 * <ul>
	 * <li>Fournit la dernière entrée d'une List&lt;String&gt;.</li>
	 * </ul>
	 * retourne null si pList == null.<br/>
	 * retourne null si pList est vide.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;String&gt;
	 * 
	 * @return : String : dernière entrée d'une liste.<br/>
	 */
	protected final String fournirDerniereLigneListe(
			final List<String> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		/* retourne null si pList est vide. */
		if (pList.isEmpty()) {
			return null;
		}
		
		
		final String derniereLigne 
			= pList.get(pList.size() - 1);
		
		return derniereLigne;
		
	} // Fin de fournirDerniereLigneListe(...).____________________________
	
	
	
	/**
	 * method fournirNomFichierSansExtension(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Retourne le nom d'un fichier sans son extension.</li>
	 * <li>Par exemple : fournirNomFichierSansExtension(IProfil.java) 
	 * retourne "IProfil".</li>
	 * </ul>
	 * Retourne null si pFile est null.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @return : String : nom du fichier sans extension.<br/>
	 */
	protected final String fournirNomFichierSansExtension(
			final File pFile) {
		
		/* Retourne null si pFile est null. */
		if (pFile == null) {
			return null;
		}
		
		final String nomSimpleFile = pFile.getName();
		
		/* Décompose le nom simple en nom de base et extension 
		 * avec la méthode StringUtils.split(nomSimple, '.'). */
		final String[] nomsTableau 
			= StringUtils.split(nomSimpleFile, '.');
		
		if (nomsTableau == null) {
			return null;
		}
		
		if (nomsTableau.length == 0) {
			return null;
		}
		
		return nomsTableau[0];
		
	} // Fin de fournirNomFichierSansExtension(...)._______________________
	
	
	
	/**
	 * method afficherDateDuJour() :<br/>
	 * <ul>
	 * <li>Retourne une String pour l'affichage de la date du jour.</li>
	 * <li>Par exemple : 12 janvier 2018.</li>
	 * </ul>
	 *
	 * @return : String : Date du jour.<br/>
	 */
	protected final String afficherDateDuJour() {
		
		final Date dateDuJour = new Date();
		
		final Locale localeFr = new Locale("fr", "FR");
		
		/* 12 janvier 2018. */
		final DateFormat dfDateFrancaise 
		= new SimpleDateFormat("dd MMMM yyyy", localeFr);
		
		return dfDateFrancaise.format(dateDuJour);
			
	} // Fin de afficherDateDuJour().______________________________________

	
	
	/**
	 * method afficherDateEnFrançais(
	 * Date pDate) :<br/>
	 * Prend en paramètre une Date et retourne une String
	 * au format français complet comme 'samedi 25 février 1961'.<br/>
	 * <br/>
	 *
	 * @param pDate : Date : la Date à afficher.<br/>
	 * 
	 * @return String : 'samedi 25 février 1961' par exemple.<br/>
	 */
	protected final String afficherDateEnFrancais(
												final Date pDate) {
		
		final Locale localeFr = new Locale("fr", "FR");
		
		final DateFormat dfDateCompleteFrancaise 
		= new SimpleDateFormat("EEEE' 'dd' 'MMMM' 'yyyy"
			, localeFr);
		
		return dfDateCompleteFrancaise.format(pDate);
		
	} // Fin de afficherDateEnFrançais(
	 // Date pDate)._______________________________________________________
	

	
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
	protected final Date fournirDate(
							final String pDateString) 
												throws ParseException {
		
		final Locale localeFr = new Locale("fr", "FR");
		
		final DateFormat dfDateFrancaise 
		= new SimpleDateFormat("dd/MM/yyyy", localeFr);
				
		/* Indispensable pour générer une exception si la chaine
		 * de caractères ne peut être une date comme 322/47/2011. */
		dfDateFrancaise.setLenient(false);
		
		return dfDateFrancaise.parse(pDateString);
		
	} // Fin de fournirDate(
	 // String pDateString)._______________________________________________
	

	
	/**
	 * method fabriquerNomClasse(
	 * String pString) :<br/>
	 * <ul>
	 * <li>fabrique le stringClasse.</li>
	 * <li>Par exemple : <br/>
	 * <code>fabriquerNomClasse("AbstractProfilSimple") 
	 * retourne CLASSE_ABSTRACT_PROFIL_SIMPLE</code>.</li>
	 * </ul>
	 * retourne null si pString est blank.<br/>
	 * retourne null si pString n'est pas CamelCase.<br/>
	 * <br/>
	 *
	 * @param pString : String : nom d'une Classe.<br/>
	 * 
	 * @return : String : stringClasse.<br/>
	 */
	protected final String fabriquerNomClasse(
			final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		/* retourne null si pString n'est pas CamelCase. */
		if (!conformeNomClasse(pString)) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		stb.append("CLASSE_");
		
		int tailleListe = 0;
		int compteur = 0;
		
		/* récupère chaque mot dans le nom de la classe. */
		final List<String> liste = trouverCamel(pString);
		tailleListe = liste.size();
		
		for (final String mot : liste) {
			
			compteur++;
			
			final String motMajuscule = this.mettreEnMajuscules(mot);
			
			stb.append(motMajuscule);
			
			if (compteur < tailleListe) {
				stb.append(UNDERSCORE);
			}
		}
		
		return stb.toString();
		
	} // Fin de fabriquerNomClasse(...).___________________________________
	
	
	
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
	protected final boolean conformeNomPackage(
			final String pString) {
		
		boolean resultat = false;
		
		/* Pattern sous forme de String. */
		/* - Commence par une minuscule ou chiffre
		 * - poursuit par une minuscule ou chiffre. */
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
	protected final boolean conformeNomInterface(
			final String pString) {
		
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
		final boolean trouve = matcher.matches();
		
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
	protected final boolean conformeNomClasse(
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
		final boolean trouve = matcher.matches();
		
		if (trouve) {
			resultat = true;
		}
		
		return resultat;
		
	} // Fin de conformeNomClasse(...).____________________________________
	

	
	/**
	 * method conformeNomAttribut(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Contrôle que pString est conforme aux noms des attributs
	 * , à savoir une minuscule, puis une suite camelCase 
	 * sans caractères spéciaux.</li>
	 * <li>accepte "profil", "profilSimple", "profilSimple7", ...</li>
	 * <li>refuse "IProfil", "Profil", "ProfilSimple_7", ...</li>
	 * </ul>
	 *
	 * @param pString : String.<br/>
	 * 
	 * @return : boolean : true si conforme.<br/>
	 */
	protected final boolean conformeNomAttribut(
			final String pString) {
		
		boolean resultat = false;
		
		/* Pattern sous forme de String. */
		/* - Commence par une minuscule
		 * - poursuit camelCase. */
		final String patternString = "(^[a-z][a-zA-Z0-9]*$)";
		
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
		
	} // Fin de conformeNomAttribut(...).__________________________________
	

	
	
	/**
	 * method conformeRg(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Contrôle que pString est conforme aux noms des RG
	 * , à savoir 
	 * <ul>
	 * <li>"RG_"</li>
	 * <li>suivi par des majuscules 
	 * (éventuellement séparées par des _ ou -), </li>
	 * <li>puis un séparateur " : "</li>
	 * <li>puis un ensemble de caractères quelconques.</li>
	 * </ul>
	 * <li>Par exemple :<br/> 
	 * "RG_PROFIL_PROFILSTRING_NOMENCLATURE_02 : 
	 * le profilString du Profil doit respecter 
	 * un ensemble fini de valeurs (nomenclature)"</li>
	 * </ul>
	 * 
	 * "RG_PROFIL_PROFILSTRING_NOMENCLATURE_02 : le profilString du Profil doit respecter un ensemble fini de valeurs (nomenclature)";
	 * 
	 * NOMBRE DE MATCHES : 4
	 * GROUP(0) : RG_PROFIL_PROFILSTRING_NOMENCLATURE_02 : le profilString du Profil doit respecter un ensemble fini de valeurs (nomenclature)
	 * GROUP(1) : PROFIL_PROFILSTRING_NOMENCLATURE_02
	 * GROUP(2) : PROFIL_PROFILSTRING_NOMENCLATURE_
	 * GROUP(3) : 02
	 * GROUP(4) : le profilString du Profil doit respecter un ensemble fini de valeurs (nomenclature)
	 * 
	 * RESULTAT : true
	 * 
	 * @param pString : String.<br/>
	 * 
	 * @return : boolean : true si conforme.<br/>
	 */
	protected final boolean conformeRg(
			final String pString) {
		
		boolean resultat = false;
		
		/* Pattern sous forme de String. */
		/* - Commence par "RG_" (^RG_)
		 * - poursuit par des majusculres séparées par des _ ou - ([A-Z-_]*).
		 * - poursuit par des chiffres (\\d+) 
		 * - séparateur " : ".
		 * - poursuit par n'importe quels caractères
		 * */
		final String patternString = "^RG_(([A-Z_-]*)(\\d+)) : (.+)$";
		
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
		
	} // Fin de conformeRg(...).___________________________________________
	


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
	protected final List<String> trouverCamel(
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
	 * method mettreEnMajuscules(
	 * String pString) :<br/>
	 * <ul>
	 * <li>passe tous les caractères de pString en <b>majuscules</b>.</li>
	 * </ul>
	 * retourne null si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String : chaine à passer 
	 * intégralement en majuscules.<br/>
	 * 
	 * @return : String : chaine entièrement en majuscules.<br/>
	 */
	protected final String mettreEnMajuscules(
			final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		String resultat = null;
		
		/* Met en majuscules. */
		resultat = pString.toUpperCase(Locale.getDefault());
		
		return resultat;
		
	} // Fin de mettreEnMajuscules(...).___________________________________


	
	/**
	 * method mettrePremiereEnMajuscule() :<br/>
	 * <ul>
	 * <li>Met la première lettre de chaque mots séparés 
	 * par des espaces en majuscule.</li>
	 * <li>Met les autres lettres de chaque mots séparés 
	 * par un espace en minuscule.</li>
	 * <li>Par exemple : "premier" est transformé en "Premier".</li>
	 * <li>"PREMIER" est transformé en "Premier".</li>
	 * <li>WordUtils.capitalizeFully("i am FINE") = "I Am Fine"</li>
	 * </ul>
	 * retourne null si pString == null.<br/>
	 * <br/>
	 * 
	 *
	 * @param pString : String. <br/>
	 * 
	 * @return : String.<br/>
	 */
	protected final String mettrePremiereEnMajuscule(
			final String pString) {
		
		/* retourne null si pString == null. */
		if (pString == null) {
			return null;
		}
		
		return WordUtils.capitalizeFully(pString);
		
	} // Fin de mettrePremiereEnMajuscule(...).____________________________
	
	
	
	/**
	 * method mettreEnMinuscules(
	 * String pString) :<br/>
	 * <ul>
	 * <li>passe tous les caractères de pString en <b>minuscules</b>.</li>
	 * </ul>
	 * retourne null si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String : chaine à passer 
	 * intégralement en minuscules.<br/>
	 * 
	 * @return : String : chaine entièrement en minuscules.<br/>
	 */
	protected final String mettreEnMinuscules(
			final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		String resultat = null;
		
		/* Met en minuscules. */
		resultat = pString.toLowerCase(Locale.getDefault());
		
		return resultat;
		
	} // Fin de mettreEnMinuscules(...).___________________________________
	

	
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
	protected final String mettrePremiereEnMajusculeEtGarder(
			final String pString) {
		
		/* retourne null si pString == null. */
		if (pString == null) {
			return null;
		}
		
		return WordUtils.capitalize(pString);
		
	} // Fin de mettrePremiereEnMajusculeEtGarder(...).____________________
	

	
	/**
	 * method retournerStringAPartirDeListe(
	 * List&lt;String&gt; pList) :<br/>
	 * <ul>
	 * <li>Transforme une liste de String pList en String.</li>
	 * <li>Incorpore les sauts de lignes dans la String à 
	 * chaque élément de la liste.</li>
	 * </ul>
	 * retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;String&gt;.<br/>
	 * 
	 * @return : String.<br/>
	 */
	protected final String retournerStringAPartirDeListe(
			final List<String> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final String string : pList) {
			
			stb.append(string);
			stb.append('\n');
			
		}
		
		return stb.toString();
		
	} // Fin de retournerStringAPartirDeListe(...).________________________
	

	
	/**
	 * method fournirNomClasse() :<br/>
	 * Fournit le nom de la classe concrète 
	 * pour les messages des logs.<br/>
	 * Par exemple : "Classe EcriveurMetierInterface".<br/>
	 * <br/>
	 *
	 * @return : String : nom de la classe concrète.<br/>
	 */
	protected abstract String fournirNomClasse();
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherListeString(
			final List<String> pListe) {
					
			/* Retourne null si pListe est null. */
			if (pListe == null) {
				return null;
			}
			
			final StringBuilder stb = new StringBuilder();
			
			for (final String ligne : pListe) {
				
				stb.append(ligne);
				stb.append(NEWLINE);
				
			}
			
			return stb.toString();
			
	} // Fin de afficherListeString(
	 // List<String> pListe).______________________________________________

	
	
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
	public final String getConceptModelise() {
		return this.conceptModelise;
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
	public final String getNomPackage() {
		return this.nomPackage;
	} // Fin de getNomPackage().___________________________________________



} // FIN DE LA CLASSE AbstractEcriveur.--------------------------------------
