package levy.daniel.application.apptechnic.generationcode.ecriveurs.model.metier;

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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.generationcode.AbstractGenerateur;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveurFichiersJava;
import levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationobjetmetier.generationobjetmetiersimple.GenerateurMetier;

/**
 * CLASSE ABSTRAITE <b>AbstractEcriveurMetier</b> :<br/>
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
 * @since 8 janv. 2018
 *
 */
public abstract class AbstractEcriveurMetier 
					extends AbstractEcriveurFichiersJava {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ABSTRACT_ECRIVEUR_METIER : String :<br/>
	 * "Classe AbstractEcriveurMetier".<br/>
	 */
	public static final String CLASSE_ABSTRACT_ECRIVEUR_METIER 
		= "Classe AbstractEcriveurMetier";

	
	/**
	 * fichierJava : File :<br/>
	 * Fichier Java à générer.<br/>
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
	 * lignePackage : String :<br/>
	 * ligne de code pour le package.<br/>
	 * Par exemple : <br/>
	 * <code>package levy.daniel.application.
	 * model.metier.profil;</code><br/>
	 */
	protected transient String lignePackage;

	
	/**
	 * imports : List&lt;String&gt; :<br/>
	 * Imports.<br/>
	 * Par exemple : <br/>
	 * <code>
	 * import java.io.Serializable;<br/>
	 * <br/>
	 * import levy.daniel.application.model.metier.IExportateurCsv;<br/>
	 * import levy.daniel.application.model.metier.IExportateurJTable;<br/>
	 * </code>
	 */
	protected transient List<String> imports;
	
	
	/**
	 * javadoc : List&lt;String&gt; :<br/>
	 * javadoc du fichier Java.<br/>
	 */
	protected transient List<String> javadoc;
	
	
	/**
	 * entity : List&lt;String&gt; :<br/>
	 * entity du fichier Java.<br/>
	 */
	protected transient List<String> entity;
	
	
	/**
	 * ligneDeclaration : String :<br/>
	 * ligne de code pour la déclaration de l'interface.<br/>
	 * Par exemple : <br/>
	 * <code>public interface IProfil extends 
	 * IExportateurCsv, IExportateurJTable, Comparable&lt;IProfil&gt;
	 * , Cloneable, Serializable {</code><br/>
	 */
	protected transient String ligneDeclaration;


	/**
	 * sepAttributs : List&lt;String&gt; :<br/>
	 * ligne de séparation des attributs.<br/>
	 */
	protected transient List<String> sepAttributs;

	
	/**
	 * stringClasse : List&lt;String&gt; :<br/>
	 * Ligne classe.<br/>
	 */
	protected transient List<String> stringClasse;
	
	
	/**
	 * attributId : List<String> :<br/>
	 * Lignes attribut id.<br/>
	 */
	protected transient List<String> attributId;

	
	/**
	 * sepMethodes : List&lt;String&gt; :<br/>
	 * ligne de séparation des methodes.<br/>
	 */
	protected transient List<String> sepMethodes;

	
	/**
	 * methodCompareTo : List&lt;String&gt; :<br/>
	 * Lignes de la méthode compareTo().<br/>
	 */
	protected transient List<String> methodCompareTo;
	
	
	/**
	 * methodClone : List&lt;String&gt; :<br/>
	 * Lignes de la méthode clone().<br/>
	 */
	protected transient List<String> methodClone;

	
	/**
	 * methodToString : List<String> :<br/>
	 * Lignes de la méthode toString().<br/>
	 */
	protected transient List<String> methodToString;
	
	
	/**
	 * methodGetEnTeteCsv : List&lt;String&gt; :<br/>
	 * Lignes de la méthode getEnTeteCsv().<br/>
	 */
	protected transient List<String> methodGetEnTeteCsv;

	
	/**
	 * methodToStringCsv : List&lt;String&gt; :<br/>
	 * Lignes de la méthode toStringCsv().<br/>
	 */
	protected transient List<String> methodToStringCsv;
	

	/**
	 * methodGetEnTeteColonne : List&lt;String&gt; :<br/>
	 * Lignes de la méthode getEnTeteColonne().<br/>
	 */
	protected transient List<String> methodGetEnTeteColonne;

	
	/**
	 * methodGetValeurColonne : List&lt;String&gt; :<br/>
	 * Lignes de la méthode getValeurColonne().<br/>
	 */
	protected transient List<String> methodGetValeurColonne;
	
	
	/**
	 * methodGetId : List&lt;String&gt; :<br/>
	 * Lignes de la méthode getId().<br/>
	 */
	protected transient List<String> methodGetId;
	
	
	/**
	 * methodSetId : List&lt;String&gt; :<br/>
	 * Lignes de la méthode setId().<br/>
	 */
	protected transient List<String> methodSetId;
	
	
	/**
	 * ligneFinale : String :<br/>
	 * ligne de code pour la ligne finale de l'interface.<br/>
	 */
	protected transient String ligneFinale;
	
	

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
			= LogFactory.getLog(AbstractEcriveurMetier.class);
	

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR AbstractEcriveurMetier() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractEcriveurMetier() {
		
		super();
		
		try {
			
			/* Récupère le path absolu des sources Java 
			 * sous forme de String. */
			/* D:\Donnees\eclipse\eclipseworkspace_neon\
			 * generation_code\src\main\java */
			this.pathRacineMainJavaString 
				= BundleConfigurationProjetManager.getRacineMainJava();
			
			/* Récupère le path absolu des sources Java 
			 * sous forme de Path. */
			/* D:\Donnees\eclipse\eclipseworkspace_neon\
			 * generation_code\src\main\java */
			this.pathRacineMainJava 
				= Paths.get(this.pathRacineMainJavaString);
			
			/* Détermine le path relatif de model.metier 
			 * et alimente this.pathMetier. */
			this.fournirPathMetier();
			
		}
		catch (Exception e) {	
			
			this.pathRacineMainJavaString = null;
			this.pathRacineMainJava = null;
			
		}
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ecrireCode(
			final File pFile
				, final GenerateurMetier pGenerateurMetier) {
		
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
		
		/* alimente this.generateurMetier. */
		this.generateurCode = pGenerateurMetier;
		
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

		/* ********** */
		/* ECRITURES. */
		/* écrit la ligne de code PACKAGE (1ère ligne). */
		this.ecrireLignePackage(pFile);
		
		/* Insère 1 ligne vide sous la ligne de code package.*/
		this.insererLignesVidesSousLigneDansFichier(
				pFile, this.lignePackage, 1, CHARSET_UTF8);
		
		/* Ecrit les IMPORTS à partir de la 3ème ligne. */
		this.ecrireImports(pFile);
		
		final String derniereLigneImports 
			= this.fournirDerniereLigneListe(this.imports); 
							
		/* Insère 3 lignes vides sous la dernière 
		 * ligne d'import.*/
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneImports, 3, CHARSET_UTF8);
		
		/* Ecrit la JAVADOC à la suite. */
		this.ecrireJavaDoc(pFile);
		
		/* Ecrit l'ENTITY à la suite. */
		this.ecrireEntity(pFile);
		
		/* Ecrit la ligne de DECLARATION à la suite. */
		this.ecrireLigneDeclaration(pFile);

		/* Appelle un HOOK pour terminer la génération 
		 * du code dans un Ecriveur concret. */
		this.ecrireCodeHook(this.fichierJava);
		
		/* Ecrit la ligne FINALE. */
		this.ecrireLigneFinale(this.fichierJava);
		
	} // Fin de ecrireCode(...).___________________________________________
	

		
	/**
	 * method ecrireLignePackage(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>Insère la ligne de code <b>package</b> 
	 * à la première ligne du fichier.</li>
	 * <li>N'insère la ligne que si elle n'existe pas déjà</li>
	 * <li>Par exemple : <br/>
	 * <code>package levy.daniel.application.
	 * model.metier.profil;</code>.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireLignePackage(
			final File pFile) {
				
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

		
		/* Crée La ligne package. */
		this.creerLignePackage(pFile);
		
		if (StringUtils.isBlank(this.lignePackage)) {
			return;
		}

		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		if (!this.existLigneDansFichier(
				pFile, CHARSET_UTF8, this.lignePackage)) {
			
			/* Insère la ligne Package à la première ligne. */
			this.ecrireStringDansFile(
					pFile
					, this.lignePackage
					, CHARSET_UTF8, NEWLINE);
			
		}
		
	} // Fin de ecrireLignePackage(...).___________________________________
	

	
	/**
	 * method creerLignePackage(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Crée et retourne la première <b>ligne de code</b> PACKAGE 
	 * d'une classe Java.</li>
	 * <li>alimente this.lignePackage</li>
	 * <li>Déduit la package père Java d'un fichier Java.</li>
	 * <li>Par exemple : creerLignePackage(IProfil.java) retourne :<br/> 
	 * <code>package 
	 * levy.daniel.application.model.metier.profil;</code></li>
	 * </ul>
	 * retourne null si pClasseJava est null.<br/>
	 * retourne null si pClasseJava n'existe pas.<br/>
	 * retourne null si pClasseJava n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : la classe Java dont on veut générer 
	 * la première ligne package.<br/>
	 * 
	 * @return : String : La ligne package à incorporer 
	 * à la première ligne de la classe Java.<br/>
	 */
	protected final String creerLignePackage(
			final File pFile) {
		
		/* retourne null si pFile est null. */
		if (pFile == null) {
			return null;
		}
		
		/* retourne null si pFile n'existe pas. */
		if (!pFile.exists()) {
			return null;
		}
		
		/* retourne null si pFile n'est pas un fichier simple. */
		if (!pFile.isFile()) {
			return null;
		}
		
		/* Récupération du package parent de l'interface. */
		final File packagePere = pFile.getParentFile();
		
		/* Récupération du Path du package parent de l'interface. */
		final Path pathPackagePere = packagePere.toPath();
		
		/* EXTRACTION DU PATH RELATIF DU PACKAGE-PERE PAR RAPPORT 
		 * A LA RACINE DES SOURCES JAVA avec des antislash. */
		final Path pathPackageRelatifPere 
			= this.pathRacineMainJava.relativize(pathPackagePere);
		
		/* Transformation du path relatif en String avec des antislash. */
		final String pathRelatifPereAntiSlash 
			= pathPackageRelatifPere.toString();
		
		/* Transformation en path Java avec des points. */
		final String pathRelatifPerePoint 
			= this.remplacerAntiSlashparPoint(pathRelatifPereAntiSlash);
		
		/* CONSTRUCTION DE LA LIGNE DE CODE. */
		final String resultat 
			= "package " + pathRelatifPerePoint + POINT_VIRGULE;
		
		this.lignePackage = resultat;
		
		return this.lignePackage;
		
	} // Fin de creerLignePackage(...).____________________________________
	
	
	
	/**
	 * method ecrireImports(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>Insère les lignes d'<b>import des dépendances</b> 
	 * après la ligne package.</li>
	 * <li>N'insère les lignes que si elles n'existent pas déjà</li>
	 * <li>Par exemple : <br/>
	 * <code>
	 * import java.io.Serializable;<br/>
	 * <br/>
	 * import levy.daniel.application.model.metier.IExportateurCsv;<br/>
	 * import levy.daniel.application.model.metier.IExportateurJTable;<br/>
	 * </code></li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 * 
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireImports(
			final File pFile) {
		
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

		try {
			
			/* Crée les imports. */
			this.creerLignesImport();
			
			if (this.imports == null) {
				return;
			}
			
			/* ENREGISTREMENT *********/
			/* Recherche la ligne identifiant les imports. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.imports);
			
			/* Ne fait rien si les imports ont été déclarés. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.imports) {
								
				if (StringUtils.isBlank(ligne)) {
					
					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);					
				}				
				else {
					
					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		}
		catch (Exception e) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer les imports", e);
			}
		}
		
	} // Fin de ecrireImports(...).________________________________________
	

	
	/**
	 * method creerLignesImport() :<br/>
	 * <ul>
	 * <li>Crée la liste des imports pour un fichier Java.</li>
	 * <li>alimente this.imports</li>
	 * <li>
	 * Par exemple : <br/>
	 * <code>
	 * import java.io.Serializable;<br/>
	 * <br/>
	 * import levy.daniel.application.model.metier.IExportateurCsv;<br/>
	 * import levy.daniel.application.model.metier.IExportateurJTable;<br/>
	 * </code>.
	 * </li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.imports.<br/>
	 * 
	 * @throws Exception 
	 */
	protected abstract List<String> creerLignesImport() throws Exception;
	

	
	/**
	 * method ecrireJavaDoc(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>Insère les lignes de <b>javadoc</b>
	 * à la suite des imports.</li>
	 * <li>N'insère les lignes que si elles n'existent pas déjà</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 * 
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireJavaDoc(
			final File pFile) {
						
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
		
		try {
			
			this.javadoc = new ArrayList<String>();
			
			/* Crée la javadoc. */
			this.creerLignesJavaDoc(pFile);
			
			if (this.javadoc == null) {
				return;
			}
			
			/* Recherche la ligne identifiant la javadoc. */
			final String ligneJavadoc 
				= this.fournirDebutJavaDoc();
			
			/* Ne fait rien si la javadoc a déjà été écrite. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneJavadoc)) {
				return;
			}

			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.javadoc) {
				
				if (StringUtils.isBlank(ligne)) {
					
					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);					
				}				
				else {
					
					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		}
		catch (Exception e) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer la javadoc", e);
			}
		}
								
	} // Fin de ecrireJavaDoc(...).________________________________________	


	
	/**
	 * method creerLignesJavaDoc(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Crée la liste des lignes de la javadoc.</li>
	 * <li>alimente this.javadoc</li>
	 * </ul>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @return : List&lt;String&gt; : this.javadoc.<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract List<String> creerLignesJavaDoc(File pFile) 
			throws Exception;


	
	/**
	 * method fournirDebutJavaDoc() :<br/>
	 * Fournit le début de la javadoc de la classe Java.<br/>
	 * Par exemple : "* CLASSE ABSTRAITE"<br/>
	 *
	 * @return : String : début de la javadoc.<br/>
	 */
	protected abstract String fournirDebutJavaDoc();

	
	
	/**
	 * method ecrireEntity(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>Insère les lignes de <b>Entity</b> (JPA)
	 * à la suite de la javadoc.</li>
	 * <li>N'insère les lignes que si elles n'existent pas déjà</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 * 
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireEntity(
			final File pFile) {
						
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
		
		try {
			
			/* Crée la javadoc. */
			this.creerLignesEntity(pFile);
			
			if (this.entity == null) {
				return;
			}
			
			/* Recherche la ligne identifiant Entity. */
			final String ligneEntity 
				= "@Entity";
			
			/* Ne fait rien si Entity a déjà été écrite. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneEntity)) {
				return;
			}

			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.entity) {
				
				if (StringUtils.isBlank(ligne)) {
					
					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);					
				}				
				else {
					
					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		}
		catch (Exception e) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer Entity", e);
			}
		}
								
	} // Fin de ecrireEntity(...).________________________________________
	

	
	/**
	 * method creerLignesEntity(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Crée la liste des lignes de l'Entity.</li>
	 * <li>alimente this.entity</li>
	 * </ul>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @return : List&lt;String&gt; : this.entity.<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract List<String> creerLignesEntity(File pFile) 
			throws Exception;
	
	
	
	/**
	 * method ecrireLigneDeclaration(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>Insère la ligne de <b>déclaration</b>
	 * à la suite.</li>
	 * <li>N'insère la ligne que si elle n'existe pas déjà</li>
	 * <li>Par exemple : <br/>
	 * <code>public interface IProfil extends 
	 * IExportateurCsv, IExportateurJTable, 
	 * Comparable&lt;IProfil&gt;, Cloneable, Serializable {</code>.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireLigneDeclaration(
			final File pFile) {
		
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
		
		/* Crée la ligne de déclaration du fichier Java. */
		this.creerLigneDeclaration(pFile);
		
		if (StringUtils.isBlank(this.ligneDeclaration)) {
			return;
		}
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		if (!this.existLigneCommencant(
				pFile, CHARSET_UTF8, this.fournirDebutDeclaration())) {
			
			/* Insère la ligne Déclaration à la suite. */
			this.ecrireStringDansFile(
					pFile
					, this.ligneDeclaration
					, CHARSET_UTF8, NEWLINE);
			
		}
		
	} // Fin de ecrireLigneDeclaration(...)._______________________________
	
	
	
	/**
	 * method creerLigneDeclaration(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Génère la ligne de code pour la 
	 * déclaration du fichier Java.</li>
	 * <li>alimente this.ligneDeclaration.</li>
	 * <li>Par exemple :<br/> 
	 * <code>public interface IProfil extends 
	 * IExportateurCsv, IExportateurJTable, 
	 * Comparable<IProfil>, Cloneable, Serializable {</code>.</li>
	 * </ul>
	 * Retourne null si pFile est null.<br/>
	 * Retourne null si pFile n'existe pas sur le disque.<br/>
	 * Retourne null si pFile est un répertoire.<br/>
	 * <br/>
	 * 
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @return : String : Ligne de code pour la déclaration.<br/>
	 */
	protected abstract String creerLigneDeclaration(File pFile);
	

	
	/**
	 * method fournirDebutDeclaration() :<br/>
	 * Fournit le début de la ligne de déclaration de la classe Java.<br/>
	 * Par exemple : "public interface"<br/>
	 *
	 * @return : String : début de la ligne de déclaration.<br/>
	 */
	protected abstract String fournirDebutDeclaration();
	
	
		
	/**
	 * method ecrireCodeHook(File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li><b>délégation aux classes concrètes</b> du corps 
	 * des fichiers java (attributs et méthodes).</li>
	 * </ul>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected abstract void ecrireCodeHook(File pFile);
	

	
	/**
	 * method ecrireLigneFinale(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>Insère la ligne de <b>fin de fichier</b> 
	 * tout à la fin du fichier java.</li>
	 * <li>N'insère la ligne que si elle n'existe pas déjà</li>
	 * <li>Par exemple : <br/>
	 * <code> // FIN DE L'INTERFACE INommage.-----------
	 * ---------------------------------</code>.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireLigneFinale(
			final File pFile) {
		
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

		
		/* Crée la ligne finale de l'Interface. */
		this.creerLigneFinale(pFile);
		
		if (StringUtils.isBlank(this.ligneFinale)) {
			return;
		}
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		if (!this.existLigneDansFichier(
				pFile, CHARSET_UTF8, this.ligneFinale)) {
			
			/* Insère la ligne finale à la fin du fichier. */
			this.ecrireStringDansFileSansSaut(
					pFile, this.ligneFinale
					, CHARSET_UTF8, NEWLINE);
						
		}
		
	} // Fin de ecrireLigneFinale(...).____________________________________
	

	
	/**
	 * method creerLigneFinale(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Génère la ligne de code pour la 
	 * ligne finale du fichier Java.</li>
	 * <li>Alimente this.ligneFinale.</li>
	 * <li>Par exemple : <br/>
	 * <code> // FIN DE L'INTERFACE INommage.-----------
	 * ---------------------------------</code>.</li>
	 * </ul>
	 * Retourne null si pFile est null.<br/>
	 * Retourne null si pFile n'existe pas sur le disque.<br/>
	 * Retourne null si pFile est un répertoire.<br/>
	 * <br/>
	 * 
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @return : String : Ligne de code pour la ligne finale.<br/>
	 */
	protected final String creerLigneFinale(
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

		
		final StringBuilder stb = new StringBuilder();
		
		stb.append(CROCHET_FERMANT);
		stb.append(" // FIN DE ");
		stb.append(this.fournirTypeFichierJava());
		stb.append(this.nomSimpleFichierJava);
		stb.append(POINT);
		
		final String provisoire = stb.toString();
		final int longueurProvisoire = provisoire.length();
		
		final int nombreTirets = 77 - longueurProvisoire;
		
		for (int i=0; i < nombreTirets; i++) {
			stb.append('-');
		}
		
		this.ligneFinale = stb.toString();
		
		return this.ligneFinale;
		
	} // Fin de creerLigneFinale(...)._____________________________________
	

	
	/**
	 * method fournirTypeFichierJava() :<br/>
	 * <ul>
	 * <li>Fournit le type de fichier Java pour la ligne finale.</li>
	 * <li>Par exemple "L'INTERFACE " pour une interface java.</li>
	 * <li>Par exemple "LA CLASSE " pour une classe java.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 */
	protected abstract String fournirTypeFichierJava();

	
	
	/**
	 * method ecrireLignesSepAttributs() :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit les <b>lignes de séparation des attributs</b>.</li>
	 * <li>Ne fait rien si les lignes existent déjà.</li>
	 * <li>
	 * Par exemple : <br/>
	 * <code>************************ATTRIBUTS*********
	 * ***************************</code>
	 * </li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireLignesSepAttributs(
			final File pFile) {

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

		try {

			/* Crée le Séparateur d'attributs. */
			this.creerSepAttributs();
			
			if (this.sepAttributs == null) {
				return;
			}

			/* Recherche la ligne identifiant sepAttributs. */
			final String ligneAttributs 
				= this.fournirDebutSepAttributs();

			/* Ne fait rien si sepAttributs a déjà été écrit. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneAttributs)) {
				return;
			}

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.sepAttributs) {

				if (StringUtils.isBlank(ligne)) {

					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);
				} else {

					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		} catch (Exception e) {

			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer "
						+ "le séparateur d'attributs", e);
			}
		}

	} // Fin de ecrireLignesSepAttributs().________________________________
	

	
	/**
	 * method creerSepAttributs() :<br/>
	 * <ul>
	 * <li>Crée la liste des lignes du séparateur Attributs.</li>
	 * <li>alimente this.sepAttributs</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.sepAttributs.<br/>
	 * 
	 * @throws Exception 
	 */
	private List<String> creerSepAttributs() 
					throws Exception {
				
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/sep_attributs.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
				
		this.sepAttributs = listeLignes;
		
		return this.sepAttributs;
					
	} // Fin de creerSepAttributs(...).____________________________________
	

	
	/**
	 * method ecrireLignesStringClasse(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit les lignes <b>stringClasse</b>.</li>
	 * <li>ecrit la javadoc et la ligne de code.</li>
	 * <li>Ne fait rien si les ligne existent déjà.</li>
	 * <li>
	 * par exemple : <br/>
	 * <code>public static final String CLASSE_ABSTRACT_PROFIL <br/>
	 * = "Classe AbstractProfil";</code>
	 * </li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireLignesStringClasse(
			final File pFile) {

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

		try {

			/* Crée le stringClasse. */
			this.creerStringClasse();
			
			if (this.stringClasse == null) {
				return;
			}

			/* Recherche la ligne identifiant stringClasse. */
			final String ligneIdentifiant = this.fournirDebutStringClasse();

			/* Ne fait rien si stringClasse a déjà été écrit. */
			if (this.existLigneCommencant(pFile, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.stringClasse) {

				if (StringUtils.isBlank(ligne)) {

					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);
				} else {

					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		} catch (Exception e) {

			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer le StringClasse", e);
			}
		}

	} // Fin de ecrireStringClasse().______________________________________
	

	
	/**
	 * method creerStringClasse() :<br/>
	 * <ul>
	 * <li>Crée la liste des lignes du séparateur Attributs.</li>
	 * <li>alimente this.stringClasse</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.stringClasse.<br/>
	 * 
	 * @throws Exception 
	 */
	private List<String> creerStringClasse() 
					throws Exception {
				
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/string_classe.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubst1 
			= substituerVariablesDansLigne(
					listeLignes
					, "{$NOM_CLASSE}"
					, this.fabriquerNomClasse(
							this.nomSimpleFichierJava));
		
		final List<String> listeLignesSubst2 
			= substituerVariablesDansLigne(
					listeLignesSubst1
					, VARIABLE_NOMSIMPLEFICHIERJAVA
						, this.nomSimpleFichierJava);
				
		this.stringClasse = listeLignesSubst2;
		
		return this.stringClasse;
					
	} // Fin de creerStringClasse(...).____________________________________
	

	
	/**
	 * method ecrireLignesAttributId(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit les lignes <b>attributId</b>.</li>
	 * <li>alimente this.attributId</li>
	 * <li>ecrit la javadoc et la ligne de code.</li>
	 * <li>ajoute 2 lignes vides ensuite.</li>
	 * <li>Ne fait rien si les lignes existent déjà.</li>
	 * <li>
	 * par exemple : <br/>
	 * <code>protected Long id;</code>
	 * </li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireLignesAttributId(
			final File pFile) {

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

		try {

			this.attributId = new ArrayList<String>();
			
			/* Crée le attributId. */
			this.creerAttributId(this.attributId);
			
			/* Recherche la ligne identifiant attributId. */
			final String ligneIdentifiant 
				= this.fournirDebutAttributId();

			/* Ne fait rien si attributId a déjà été écrit. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}

			/* ajoute 2 lignes vides. */
			this.ajouterLignesVides(2, this.attributId);
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.attributId) {

				if (StringUtils.isBlank(ligne)) {

					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);
				} else {

					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		} catch (Exception e) {

			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer attributId", e);
			}
		}

	} // Fin de ecrireLignesAttributId().__________________________________
	

	
	/**
	 * method creerAttributId(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>Crée la liste des lignes attributId.</li>
	 * </ul>
	 * 
	 * @param pListe : List&lt;String&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	protected abstract void creerAttributId(List<String> pListe) 
					throws Exception;
	

	
	/**
	 * method ecrireAttributs(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit tous les <b>attributs</b> stockés dans la 
	 * Map this.mapAttributs.</li>
	 * <li>ecrit la javadoc et la ligne de code pour chaque attribut.</li>
	 * <li>ajoute 2 lignes vides ensuite.</li>
	 * <li>Ne fait rien si les lignes existent déjà.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * ne fait rien si this.mapAttributs == null.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void ecrireAttributs(
			final File pFile) throws Exception {
		
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

		/* ne fait rien si this.mapAttributs == null. */
		if (this.mapAttributs == null) {
			return;
		}
		
		
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributs.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		/* BOUCLE SUR LES ATTRIBUTS. */
		while (ite.hasNext()) {
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			final List<String> attributListe = new ArrayList<String>();
			final List<String> javadocListe = new ArrayList<String>();
			final List<String> codeListe = new ArrayList<String>();
			
			/* Création de la Javadoc. */
			this.creerJavadocAttribut(
					javadocListe, nomAttribut, typeAttribut);
			
			/* Création du code. */
			this.creerCodeAttribut(
					codeListe, nomAttribut, typeAttribut);
			
			/* ajout de la javadoc à attributListe. */
			attributListe.addAll(javadocListe);
			/* ajout du code à attributListe. */
			attributListe.addAll(codeListe);

			
			final String ligneIdentifiant 
				= "	protected " + typeAttribut + SEP_ESPACE + nomAttribut;
			
			try {
				
				/* Ne fait rien si l'attribut a déjà été écrit. */
				if (this.existLigneCommencant(
						pFile, CHARSET_UTF8, ligneIdentifiant)) {
					return;
				}

				/* ajoute 2 lignes vides. */
				this.ajouterLignesVides(2, attributListe);
				
				/* *************** */
				/* ENREGISTREMENT. */
				/* *************** */
				for (final String ligne : attributListe) {

					if (StringUtils.isBlank(ligne)) {

						this.ecrireStringDansFile(
								pFile, "", CHARSET_UTF8, NEWLINE);
					} else {

						this.ecrireStringDansFile(
								pFile, ligne, CHARSET_UTF8, NEWLINE);
					}
				}
				
			} catch (Exception e) {

				if (LOG.isFatalEnabled()) {
					LOG.fatal("Impossible de créer l' attribut " 
								+ nomAttribut, e);
				}
			}
			
		} // Fin de ite.hasNext().________________________________
		
	} // Fin de ecrireAttributs(...).______________________________________
	

	
	/**
	 * method creerJavadocAttribut(
	 * List&lt;String&gt; pListe
	 * , String pNomAttribut
	 * , String pTypeAttribut) :<br/>
	 * <ul>
	 * <li>injecte la javadoc de l'attribut pNomAttribut 
	 * dans la liste pListe.</li>
	 * </ul>
	 * ne fait rien si pListe == null.<br/>
	 * ne fait rien si pNomAttribut est blank.<br/>
	 * <br/>
	 *
	 * @param pListe : List&lt;String&gt; : 
	 * javadoc sous forme de liste de Strings.<br/>
	 * @param pNomAttribut : String : Nom de l'attribut.<br/>
	 * @param pTypeAttribut : String : type de l'attribut.<br/>
	 * 
	 * @throws Exception 
	 */
	protected abstract void creerJavadocAttribut(
			List<String> pListe
				, String pNomAttribut
					, String pTypeAttribut) throws Exception;
	

	
	/**
	 * method creerCodeAttribut(
	 * List&lt;String&gt; pListe 
	 * , String pNomAttribut
	 * , String pTypeAttribut) :<br/>
	 * <ul>
	 * <li>injecte le code de l'attribut pNomAttribut 
	 * dans la liste pListe.</li>
	 * </ul>
	 * ne fait rien si pListe == null.<br/>
	 * ne fait rien si pNomAttribut est blank.<br/>
	 * <br/>
	 *
	 * @param pListe : List&lt;String&gt; : 
	 * code sous forme de liste de Strings.<br/>
	 * @param pNomAttribut : String : Nom de l'attribut.<br/>
	 * @param pTypeAttribut : String : type de l'attribut.<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeAttribut(
			List<String> pListe
				, String pNomAttribut
					, String pTypeAttribut) throws Exception;
	

	
	/**
	 * method ecrireLignesSepMethodes(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la ligne de séparation des methodes.</li>
	 * <li>Ne fait rien si les lignes existent déjà.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * ne fait rien si this.mapAttributs == null.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireLignesSepMethodes(
			final File pFile) {

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

		try {

			/* Crée le Séparateur de méthodes. */
			this.creerSepMethodes();
			
			if (this.sepMethodes == null) {
				return;
			}

			/* Recherche la ligne identifiant sepMethodes. */
			final String ligneMethodes = this.fournirDebutSepMethodes();

			/* Ne fait rien si sepMethodes a déjà été écrit. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneMethodes)) {
				return;
			}

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.sepMethodes) {

				if (StringUtils.isBlank(ligne)) {

					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);
				} else {

					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		} catch (Exception e) {

			if (LOG.isFatalEnabled()) {
				LOG.fatal(
						"Impossible de créer le séparateur de méthodes"
							, e);
			}
		}

	} // Fin de ecrireLignesSepMethodes()._________________________________
	

	
	/**
	 * method creerSepMethodes() :<br/>
	 * <ul>
	 * <li>Crée la liste des lignes du séparateur Methodes.</li>
	 * <li>alimente this.sepMethodes</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.sepMethodes.<br/>
	 * 
	 * @throws Exception 
	 */
	private List<String> creerSepMethodes() 
					throws Exception {
				
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/sep_methodes.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOMSIMPLEFICHIERJAVA, this.nomSimpleFichierJava);
				
		this.sepMethodes = listeLignesSubstitue;
		
		return this.sepMethodes;
					
	} // Fin de creerSepMethodes(...)._____________________________________
	

			
	/**
	 * method ecrireConstructeurNull(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité du <b>constructeur d'arite nulle</b></li>
	 * <li>écrit la javadoc du constructeur d'arite nulle.</li>
	 * <li>écrit le code du constructeur d'arite nulle.</li>
	 * <li>ajoute 3 lignes vides à la suite.<br/>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	protected void ecrireConstructeurNull(
			final File pFile) throws Exception {
		
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
	
		/* écrit la javadoc du constructeur d'arite nulle. */
		this.ecrireJavadocConstructeurNull(pFile);
		
		/* écrit le code du constructeur d'arite nulle. */
		this.ecrireCodeConstructeurNull(pFile);
		
	} // Fin de ecrireConstructeurNull(...)._______________________________
	

	
	/**
	 * method ecrireJavadocConstructeurNull(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>Génère la <b>javadoc du constructeur d'arite nulle</b>.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	private void ecrireJavadocConstructeurNull(
			final File pFile) throws Exception {
		
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
	
		final List<String> listJavadoc = new ArrayList<String>();
		listJavadoc.add(DEBUT_JAVADOC_MEMBRE);
		listJavadoc.add(CONSTR_JAVADOC + this.nomSimpleFichierJava + "() :<br/>");
		listJavadoc.add(LIGNE_CONSTR_NULL_JAVADOC);			
		listJavadoc.add(FIN_JAVADOC_MEMBRE);
		
		
		
		try {
	
			/* Recherche la ligne identifiant. */
			final String ligneIdentifiant = LIGNE_CONSTR_NULL_JAVADOC;
	
			/* Ne fait rien si la javadoc a déjà été écrite. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}
	
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : listJavadoc) {
	
				if (StringUtils.isBlank(ligne)) {
	
					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);
					
				} else {
	
					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		} catch (Exception e) {
	
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer la Javadoc", e);
			}
		}
		
	} // Fin de ecrireJavadocConstructeurNull(...)._________________
	
	
	
	/**
	 * method ecrireCodeConstructeurNull(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>génère le <b>code du constructeur d'arite nulle</b>.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	private void ecrireCodeConstructeurNull(
			final File pFile) throws Exception {
				
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
	
		final List<String> listCode = new ArrayList<String>();
		
		/* DECLARATION du constructeur. */
		listCode.add(PUBLIC + this.nomSimpleFichierJava + "() {");
			
		listCode.add("");
	
		/* CODE du Constructeur. */
		final StringBuilder stb = new StringBuilder();
		
		stb.append("this(null");
		
		int compteur2 = 0;
		
		final int tailleMap = this.mapAttributs.size();
		
		final Set<Entry<String, String>> entrySet2 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite2 
			= entrySet2.iterator();
		
		while (ite2.hasNext()) {
			
			compteur2++;
			
			ite2.next();
			
			String aAjouter = null;
			
			if (compteur2 < tailleMap) {
				aAjouter = ", null";
			} else {
				aAjouter = ", null);";
			}
			
			stb.append(aAjouter);
		}
		
		listCode.add(DECALAGE_CODE + stb.toString());
		
		listCode.add("");
		
		final String ligneIdentifiant 
			= "\t" 
			+ LIGNE_FIN_CONSTR_NULL;
		
		listCode.add(ligneIdentifiant);
		
		/* ajoute 3 lignes vides. */
		this.ajouterLignesVides(3, listCode);
		
		try {
	
			/* Ne fait rien si le code a déjà été écrit. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}
	
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : listCode) {
	
				if (StringUtils.isBlank(ligne)) {
	
					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);
					
				} else {
	
					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		} catch (Exception e) {
	
			if (LOG.isFatalEnabled()) {
				LOG.fatal(
						"Impossible de créer "
						+ "le code du constructeur", e);
			}
		}
		
	} // Fin de ecrireCodeConstructeurNull(...).____________________
	
	
		
	/**
	 * method ecrireConstructeurComplet(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité du <b>constructeur complet</b>.</li>
	 * <li>écrit la javadoc du constructeur complet.</li>
	 * <li>écrit le code du constructeur complet.</li>
	 * <li>ajoute 3 lignes vides à la suite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	protected void ecrireConstructeurComplet(
			final File pFile) throws Exception {
		
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

		/* écrit la javadoc du constructeur complet. */
		this.ecrireJavadocConstructeurComplet(pFile);
		
		/* écrit le code du constructeur complet. */
		this.ecrireCodeConstructeurComplet(pFile);
		
	} // Fin de ecrireConstructeurComplet(...).____________________________
	

	
	/**
	 * method ecrireJavadocConstructeurComplet(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>Génère la <b>javadoc du constructeur complet</b>.</li>
	 * <br/>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	private void ecrireJavadocConstructeurComplet(
			final File pFile) throws Exception {
		
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

		final List<String> listJavadoc = new ArrayList<String>();
		listJavadoc.add(DEBUT_JAVADOC_MEMBRE);
		listJavadoc.add(CONSTR_JAVADOC + this.nomSimpleFichierJava + "(");
		
		final int tailleMap = this.mapAttributs.size();
		
		final Set<Entry<String, String>> entrySet 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		int compteur = 0;
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			String ligneACreerBase = null;
			
			if (compteur == 1) {
				
				ligneACreerBase 
				= LIGNE_VIDE_JAVADOC_MEMBRE
				+ SEP_ESPACE
				+ typeAttribut 
				+ SEP_ESPACE 
				+ this.fournirParametre(nomAttribut);
			}
			else {
				
				ligneACreerBase 
				= VIRGULE_JAVADOC_MEMBRE 
				+ typeAttribut 
				+ SEP_ESPACE 
				+ this.fournirParametre(nomAttribut);
			}
			
			
			String ligneACreer = null;
			
			if (compteur < tailleMap) {
				ligneACreer = ligneACreerBase;
			} else {
				ligneACreer = ligneACreerBase + ") :<br/>";
			}
			
			listJavadoc.add(ligneACreer);
		}
		
		listJavadoc.add(UL_OUVRANT_JAVADOC_MEMBRE);
		listJavadoc.add(LIGNE_CONSTR_COMPLET_JAVADOC);
		listJavadoc.add(SANS_ID_JAVADOC);
		listJavadoc.add(UL_FERMANT_JAVADOC_MEMBRE);
		listJavadoc.add(LIGNE_VIDE_JAVADOC_MEMBRE);

		
		final Set<Entry<String, String>> entrySet2 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite2 = entrySet2.iterator();
		
		while (ite2.hasNext()) {
			
			final Entry<String, String> entry2 = ite2.next();
			
			final String nomAttribut = entry2.getKey();
			final String typeAttribut = entry2.getValue();
			
			final String ligneACreer1 
				= LIGNE_PARAM_JAVADOC 
				+ fournirParametre(nomAttribut) 
				+ SEP_2PTS_AERE 
				+ typeAttribut 
				+ SEP_2PTS_AERE;
			
			final String ligneACreer2 
				= "	 *" 
				+ SEP_ESPACE
				+ nomAttribut 
				+ " du " 
				+ this.nomSimpleFichierJava 
				+ POINT_BR;
			
			listJavadoc.add(ligneACreer1);
			listJavadoc.add(ligneACreer2);
		}

		
		listJavadoc.add(FIN_JAVADOC_MEMBRE);
		
		
		
		try {

			/* Recherche la ligne identifiant. */
			final String ligneIdentifiant = LIGNE_CONSTR_COMPLET_JAVADOC;

			/* Ne fait rien si la javadoc a déjà été écrite. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : listJavadoc) {

				if (StringUtils.isBlank(ligne)) {

					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);
					
				} else {

					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
					
				}
			}
		} catch (Exception e) {

			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer la Javadoc", e);
			}
		}
		
	} // Fin de ecrireJavadocConstructeurComplet(...)._________________
	

	
	/**
	 * method ecrireCodeConstructeurComplet(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>génère le <b>code du constructeur complet</b>.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	private void ecrireCodeConstructeurComplet(
			final File pFile) throws Exception {
				
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

		final List<String> listCode = new ArrayList<String>();
		
		/* DECLARATION du constructeur. */
		listCode.add(PUBLIC + this.nomSimpleFichierJava + "(");

		/* PARAMETRES du constructeur. */
		final String decalageDebut = TAB + TAB + TAB;
		
		final int tailleMap = this.mapAttributs.size();
		
		final Set<Entry<String, String>> entrySet 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		int compteur = 0;
		String decalage = decalageDebut;
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			String ligneACreer = null;
			String ligneACreerBase = null;
			
			if (compteur == 1) {
				
				ligneACreerBase 
					= decalage + FINAL
						+ typeAttribut + SEP_ESPACE 
							+ this.fournirParametre(nomAttribut);
								
			} else {
				
				final StringBuffer stb = new StringBuffer();
				
				stb.append(decalage);
				stb.append(TAB);
				
				decalage = stb.toString();
				
				ligneACreerBase 
					= decalage + ", " + FINAL
						+ typeAttribut + SEP_ESPACE 
						+ this.fournirParametre(nomAttribut);
				
			}
			
						
			if (compteur < tailleMap) {
				ligneACreer = ligneACreerBase;
			} else {
				ligneACreer = ligneACreerBase + ") {";
			}
			
			listCode.add(ligneACreer);
		}


		/* CODE du Constructeur. */
		listCode.add("");

		final StringBuilder stb = new StringBuilder();
		
		stb.append("this(null");
		
		int compteur2 = 0;
		
		final Set<Entry<String, String>> entrySet2 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite2 = entrySet2.iterator();
		
		while (ite2.hasNext()) {
			
			compteur2++;
			
			final Entry<String, String> entry2 = ite2.next();
			
			final String nomAttribut = entry2.getKey();

			String aAjouter = null;
			
			if (compteur2 < tailleMap) {
				aAjouter = ", " + this.fournirParametre(nomAttribut);
			} else {
				aAjouter = ", " + this.fournirParametre(nomAttribut) + ");";
			}
			
			stb.append(aAjouter);
		}
		
		listCode.add(DECALAGE_CODE + stb.toString());
		
		listCode.add("");
		
		final String ligneIdentifiant 
			= "\t" 
			+ LIGNE_FIN_CONSTR_COMPLET;
		
		listCode.add(ligneIdentifiant);
		
		/* ajoute 3 lignes vides. */
		this.ajouterLignesVides(3, listCode);

		
		try {

			/* Ne fait rien si le code a déjà été écrit. */
			if (this.existLigneCommencant(pFile, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : listCode) {

				if (StringUtils.isBlank(ligne)) {

					this.ecrireStringDansFile(pFile, "", CHARSET_UTF8, NEWLINE);
				} else {

					this.ecrireStringDansFile(pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		} catch (Exception e) {

			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer le code du constructeur", e);
			}
		}
		
	} // Fin de ecrireCodeConstructeurComplet(...).____________________
	
	
	
	/**
	 * method ecrireConstructeurCompletBase(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité du <b>constructeur complet base</b>.</li>
	 * <li>écrit la javadoc du constructeur complet base.</li>
	 * <li>écrit le code du constructeur complet base.</li>
	 * <li>ajoute 3 lignes vides à la suite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void ecrireConstructeurCompletBase(
			final File pFile) throws Exception {
		
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

		/* écrit la javadoc du constructeur complet base. */
		this.ecrireJavadocConstructeurCompletBase(pFile);
		
		/* écrit le code du constructeur complet base. */
		this.ecrireCodeConstructeurCompletBase(pFile);
		
	} // Fin de ecrireConstructeurCompletBase(...).________________________
	

	
	/**
	 * method ecrireJavadocConstructeurCompletBase(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>Génère la <b>javadoc du constructeur complet base</b>.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	private void ecrireJavadocConstructeurCompletBase(
			final File pFile) throws Exception {
		
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

		final List<String> listJavadoc = new ArrayList<String>();
		listJavadoc.add(DEBUT_JAVADOC_MEMBRE);
		listJavadoc.add(CONSTR_JAVADOC + this.nomSimpleFichierJava + "(");
		listJavadoc.add(ID_JAVADOC_MEMBRE);
		
		final int tailleMap = this.mapAttributs.size();
		
		final Set<Entry<String, String>> entrySet 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		int compteur = 0;
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			final String ligneACreerBase 
				= VIRGULE_JAVADOC_MEMBRE 
				+ typeAttribut 
				+ SEP_ESPACE 
				+ this.fournirParametre(nomAttribut);
			
			String ligneACreer = null;
			
			if (compteur < tailleMap) {
				ligneACreer = ligneACreerBase;
			} else {
				ligneACreer = ligneACreerBase + ") :<br/>";
			}
			
			listJavadoc.add(ligneACreer);
		}
		
		listJavadoc.add(UL_OUVRANT_JAVADOC_MEMBRE);
		listJavadoc.add(LIGNE_CONSTR_COMPLET_BASE_JAVADOC);
		listJavadoc.add(AVEC_ID_JAVADOC);
		listJavadoc.add(UL_FERMANT_JAVADOC_MEMBRE);
		listJavadoc.add(LIGNE_VIDE_JAVADOC_MEMBRE);
		listJavadoc.add(PARAM_ID_JAVADOC);

		
		final Set<Entry<String, String>> entrySet2 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite2 = entrySet2.iterator();
		
		while (ite2.hasNext()) {
			
			final Entry<String, String> entry2 = ite2.next();
			
			final String nomAttribut = entry2.getKey();
			final String typeAttribut = entry2.getValue();
			
			final String ligneACreer1 
				= LIGNE_PARAM_JAVADOC 
				+ fournirParametre(nomAttribut) 
				+ SEP_2PTS_AERE 
				+ typeAttribut 
				+ SEP_2PTS_AERE;
			
			final String ligneACreer2 
				= "	 *"
				+ SEP_ESPACE
				+ nomAttribut 
				+ " du " 
				+ this.nomSimpleFichierJava 
				+ POINT_BR;
						
			listJavadoc.add(ligneACreer1);
			listJavadoc.add(ligneACreer2);
		}

		
		listJavadoc.add(FIN_JAVADOC_MEMBRE);
		
		
		
		try {

			/* Recherche la ligne identifiant. */
			final String ligneIdentifiant 
				= LIGNE_CONSTR_COMPLET_BASE_JAVADOC;

			/* Ne fait rien si la javadoc a déjà été écrite. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : listJavadoc) {

				if (StringUtils.isBlank(ligne)) {

					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);
					
				} else {

					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
					
				}
			}
		} catch (Exception e) {

			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer la Javadoc", e);
			}
		}
		
	} // Fin de ecrireJavadocConstructeurCompletBase(...)._________________
	

	
	/**
	 * method ecrireCodeConstructeurCompletBase(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>génère le <b>code du constructeur complet base</b>.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	protected abstract void ecrireCodeConstructeurCompletBase(
			File pFile) throws Exception;
	
	
	
	/**
	 * method ecrireHashCode(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>compareTo(...)</b></li>
	 * <li>écrit la javadoc de hashCode().</li>
	 * <li>écrit le code de hashCode().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void ecrireHashCode(
			final File pFile) throws Exception {
						
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

		final List<String> listeMethode = new ArrayList<String>();
		
		/* DEBUT. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/hashcode/debut_hashcode.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes du début. */
		listeMethode.addAll(listeLignesDebut);

		/* CORPS. */
		final String cheminFichierCorps 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/hashcode/hashcode.txt";
	
		final File fichierCorps = new File(cheminFichierCorps);
	
		final List<String> listeLignesCorps 
			= this.lireStringsDansFile(fichierCorps, CHARSET_UTF8);
		
		final Set<Entry<String, String>> entrySetCorps 
		= this.mapAttributsEquals.entrySet();
	
		final Iterator<Entry<String, String>> iteCorps 
			= entrySetCorps.iterator();
		
		while (iteCorps.hasNext()) {
			
			final Entry<String, String> entryCorps = iteCorps.next();
			
			final String nomAttribut = entryCorps.getKey();
			
			final List<String> lignesAAjouter 
				= this.substituerVariablesDansLigne(
						listeLignesCorps, VARIABLE_NOMATTRIBUT, nomAttribut);
			
			/* Ajout des lignes du corps. */
			listeMethode.addAll(lignesAAjouter);

		}
		
		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/hashcode/fin_hashcode.txt";
	
		final File fichierFin = new File(cheminFichierFin);
	
		final List<String> listeLignesFin 
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);
		
		/* Ajout des lignes du fin. */
		listeMethode.addAll(listeLignesFin);

		
		/* ENREGISTREMENT *********/
		
		final String ligneIdentifiant = "	public int hashCode()";
		
		try {

			/* Ne fait rien si le code a déjà été écrit. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : listeMethode) {

				if (StringUtils.isBlank(ligne)) {

					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);
					
				} else {

					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
					
				}
			}
		} catch (Exception e) {

			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer le code du hashcode", e);
			}
		}
				
	} // Fin de ecrireHashCode(...)._______________________________________
	

		
	/**
	 * method ecrireEquals(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>equals(...)</b></li>
	 * <li>écrit la javadoc de equals().</li>
	 * <li>écrit le code de equals().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void ecrireEquals(
			final File pFile) throws Exception {
				
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
		
		final List<String> listeMethode = new ArrayList<String>();

		/* écrit la javadoc. */
		this.creerJavadocEquals(listeMethode);
		
		/* écrit le code. */
		this.creerCodeEquals(listeMethode);
		
		
		/* ENREGISTREMENT *********/
		
		final String ligneIdentifiant = "	public boolean equals";
		
		try {

			/* Ne fait rien si le code a déjà été écrit. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : listeMethode) {

				if (StringUtils.isBlank(ligne)) {

					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);
					
				} else {

					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
					
				}
			}
		} catch (Exception e) {

			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer le code du equals()", e);
			}
		}
				
	} // Fin de ecrireEquals(...)._________________________________________
	

	
	/**
	 * method creerJavadocEquals(
	 * List&lt;String&gt; pListeMethode) :<br/>
	 * <ul>
	 * <li>génère la javadoc de equals().</li>
	 * <li>insère la javadoc générée dans pListeMethode.</li>
	 * </ul>
	 *
	 * @param pListeMethode : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	private void creerJavadocEquals(
			final List<String> pListeMethode) throws Exception {
		
		/* DEBUT. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/equals/debut_javadoc_equals.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		final List<String> listeLignesDebutAAjouter 
			= this.substituerVariablesDansLigne(
					listeLignesDebut
						, VARIABLE_NOMSIMPLEFICHIERJAVA
							, this.nomSimpleFichierJava);
		
		/* Ajout des lignes du début. */
		pListeMethode.addAll(listeLignesDebutAAjouter);
		
		/* CORPS. */
		final String cheminFichierCorps 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/equals/corps_javadoc_equals.txt";
	
		final File fichierCorps = new File(cheminFichierCorps);
	
		final List<String> listeLignesCorps 
			= this.lireStringsDansFile(fichierCorps, CHARSET_UTF8);
		
		final Set<Entry<String, String>> entrySetCorps 
		= this.mapAttributsEquals.entrySet();
	
		final Iterator<Entry<String, String>> iteCorps 
			= entrySetCorps.iterator();
		
		while (iteCorps.hasNext()) {
			
			final Entry<String, String> entryCorps = iteCorps.next();
			
			final String nomAttribut = entryCorps.getKey();
			
			final List<String> lignesAAjouter 
				= this.substituerVariablesDansLigne(
						listeLignesCorps
						, VARIABLE_NOMATTRIBUT, nomAttribut);
			
			/* Ajout des lignes du corps. */
			pListeMethode.addAll(lignesAAjouter);

		}

		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/equals/fin_javadoc_equals.txt";
	
		final File fichierFin = new File(cheminFichierFin);
	
		final List<String> listeLignesFin 
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);
		
		/* Ajout des lignes du fin. */
		pListeMethode.addAll(listeLignesFin);
		
	} // Fin de creerJavadocEquals(...).___________________________________


	
	/**
	 * method creerCodeEquals(
	 * List&lt;String&gt; pListeMethode) :<br/>
	 * <ul>
	 * <li>génère le code de equals().</li>
	 * <li>insère le code généré dans pListeMethode.</li>
	 * </ul>
	 *
	 * @param pListeMethode : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	private void creerCodeEquals(
			final List<String> pListeMethode) throws Exception {
		
		/* DEBUT. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/equals/debut_equals.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		final List<String> listeLignesDebutAAjouter 
			= this.substituerVariablesDansLigne(
					listeLignesDebut
						, VARIABLE_NOMSIMPLEFICHIERJAVA
							, this.nomSimpleFichierJava);
		
		/* Ajout des lignes du début. */
		pListeMethode.addAll(listeLignesDebutAAjouter);
		
		/* CORPS. */
		final String cheminFichierCorps 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/equals/corps_equals.txt";
	
		final File fichierCorps = new File(cheminFichierCorps);
	
		final List<String> listeLignesCorps 
			= this.lireStringsDansFile(fichierCorps, CHARSET_UTF8);
		
		final Set<Entry<String, String>> entrySetCorps 
		= this.mapAttributsEquals.entrySet();
	
		final Iterator<Entry<String, String>> iteCorps 
			= entrySetCorps.iterator();
		
		while (iteCorps.hasNext()) {
			
			final Entry<String, String> entryCorps = iteCorps.next();
			
			final String nomAttribut = entryCorps.getKey();
			
			final List<String> lignesAAjouter 
				= this.substituerVariablesDansLigne(
						listeLignesCorps, VARIABLE_NOMATTRIBUT, nomAttribut);
			
			/* Ajout des lignes du corps. */
			pListeMethode.addAll(lignesAAjouter);

		}

		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/equals/fin_equals.txt";
	
		final File fichierFin = new File(cheminFichierFin);
	
		final List<String> listeLignesFin 
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);
		
		/* Ajout des lignes du fin. */
		pListeMethode.addAll(listeLignesFin);
				
	} // Fin de creerCodeEquals(...).______________________________________

	
	
	/**
	 * method ecrireCompareTo() :<br/>
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>compareTo(...)</b></li>
	 * <li>écrit la javadoc de compareTo().</li>
	 * <li>écrit le code de compareTo().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void ecrireCompareTo(
			final File pFile) throws Exception {
				
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
		
		this.methodCompareTo = new ArrayList<String>();

		/* écrit la javadoc. */
		this.creerJavadocCompareTo(this.methodCompareTo);
		
		/* écrit le code. */
		this.creerCodeCompareTo(this.methodCompareTo);
		
		/* ajoute 3 lignes. */
		this.ajouterLignesVides(3, this.methodCompareTo);
		
		
		/* ENREGISTREMENT *********/		
		final String ligneIdentifiant 
			= this.fournirLigneIdentifiantCompareTo();
		
		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		
		try {


			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodCompareTo) {

				if (StringUtils.isBlank(ligne)) {

					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);
					
				} else {

					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
					
				}
			}
		} catch (Exception e) {

			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer le code du compareTo", e);
			}
		}
				
		
	} // Fin de ecrireCompareTo(...).______________________________________
	

	
	/**
	 * method creerJavadocCompareTo(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de compareTo().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocCompareTo(
			List<String> pListe) throws Exception;


	
	/**
	 * method creerCodeCompareTo(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de compareTo().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeCompareTo(
			List<String> pListe) throws Exception;


	/**
	 * method fournirLigneIdentifiantCompareTo() :<br/>
	 * <ul>
	 * <li>fournit un début de ligne permettant d'identifier 
	 * la méthode compareTo.</li>
	 * </ul>
	 *
	 * @return : String : début de ligne identifiant.<br/>
	 */
	protected abstract String fournirLigneIdentifiantCompareTo();
	
	
	
	/**
	 * method ecrireClone(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>clone()</b></li>
	 * <li>écrit la javadoc de clone().</li>
	 * <li>écrit le code de clone().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireClone(
			final File pFile) {
				
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
		
			
		try {
			
			this.methodClone = new ArrayList<String>();
			
			/* Crée la javadoc. */
			this.creerJavadocClone(this.methodClone);
			
			/* Crée le code. */
			this.creerCodeClone(this.methodClone);
			
			
			/* ENREGISTREMENT *********/
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodClone);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}

			/* ajoute 3 lignes. */
			this.ajouterLignesVides(3, this.methodClone);

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodClone) {
				
				if (StringUtils.isBlank(ligne)) {
					
					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);					
				}				
				else {
					
					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		}
		catch (Exception e) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer la méthode clone()", e);
			}
		}
				
	} // Fin de ecrireClone(...).__________________________________________
	


	/**
	 * method creerJavadocClone(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de clone().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocClone(
			List<String> pListe) throws Exception;
	

	
	/**
	 * method creerCodeClone(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de clone().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeClone(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method ecrireToString(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>toString()</b></li>
	 * <li>écrit la javadoc de toString().</li>
	 * <li>écrit le code de toString().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireToString(
			final File pFile) {
				
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
		
			
		try {
			
			this.methodToString = new ArrayList<String>();
			
			/* Crée la javadoc. */
			this.creerJavadocToString(this.methodToString);
			
			/* Crée le code. */
			this.creerCodeToString(this.methodToString);
			
			
			/* ENREGISTREMENT *********/
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodToString);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}

			/* ajoute 3 lignes. */
			this.ajouterLignesVides(3, this.methodToString);

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodToString) {
				
				if (StringUtils.isBlank(ligne)) {
					
					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);					
				}				
				else {
					
					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		}
		catch (Exception e) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer la méthode toString()", e);
			}
		}
				
	} // Fin de ecrireToString(...).__________________________________________
	


	/**
	 * method creerJavadocToString(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de toString().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocToString(
			List<String> pListe) throws Exception;
	

	
	/**
	 * method creerCodeToString(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de toString().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeToString(
			List<String> pListe) throws Exception;
	

	
	/**
	 * method ecrireGetEnTeteCsv(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>getEnTeteCsv()</b></li>
	 * <li>écrit la javadoc de getEnTeteCsv().</li>
	 * <li>écrit le code de getEnTeteCsv().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireGetEnTeteCsv(
			final File pFile) {
				
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
		
			
		try {
			
			this.methodGetEnTeteCsv = new ArrayList<String>();
			
			/* Crée la javadoc. */
			this.creerJavadocGetEnTeteCsv(this.methodGetEnTeteCsv);
			
			/* Crée le code. */
			this.creerCodeGetEnTeteCsv(this.methodGetEnTeteCsv);
			
			
			/* ENREGISTREMENT *********/
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodGetEnTeteCsv);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}

			/* ajoute 3 lignes. */
			this.ajouterLignesVides(3, this.methodGetEnTeteCsv);

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodGetEnTeteCsv) {
				
				if (StringUtils.isBlank(ligne)) {
					
					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);					
				}				
				else {
					
					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		}
		catch (Exception e) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer la méthode getEnTeteCsv()", e);
			}
		}
				
	} // Fin de ecrireGetEnTeteCsv(...).__________________________________________
	


	/**
	 * method creerJavadocGetEnTeteCsv(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de getEnTeteCsv().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocGetEnTeteCsv(
			List<String> pListe) throws Exception;
	

	
	/**
	 * method creerCodeGetEnTeteCsv(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de getEnTeteCsv().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeGetEnTeteCsv(
			List<String> pListe) throws Exception;
	

	
	/**
	 * method ecrireToStringCsv(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>toStringCsv()</b></li>
	 * <li>écrit la javadoc de toStringCsv().</li>
	 * <li>écrit le code de toStringCsv().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireToStringCsv(
			final File pFile) {
				
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
		
			
		try {
			
			this.methodToStringCsv = new ArrayList<String>();
			
			/* Crée la javadoc. */
			this.creerJavadocToStringCsv(this.methodToStringCsv);
			
			/* Crée le code. */
			this.creerCodeToStringCsv(this.methodToStringCsv);
			
			
			/* ENREGISTREMENT *********/
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodToStringCsv);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}

			/* ajoute 3 lignes. */
			this.ajouterLignesVides(3, this.methodToStringCsv);

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodToStringCsv) {
				
				if (StringUtils.isBlank(ligne)) {
					
					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);					
				}				
				else {
					
					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		}
		catch (Exception e) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer la méthode toStringCsv()", e);
			}
		}
				
	} // Fin de ecrireToStringCsv(...).__________________________________________
	


	/**
	 * method creerJavadocToStringCsv(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de toStringCsv().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocToStringCsv(
			List<String> pListe) throws Exception;
	

	
	/**
	 * method creerCodeToStringCsv(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de toStringCsv().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeToStringCsv(
			List<String> pListe) throws Exception;
	

	
	/**
	 * method ecrireGetEnTeteColonne(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>getEnTeteColonne()</b></li>
	 * <li>écrit la javadoc de getEnTeteColonne().</li>
	 * <li>écrit le code de getEnTeteColonne().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireGetEnTeteColonne(
			final File pFile) {
				
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
		
			
		try {
			
			this.methodGetEnTeteColonne = new ArrayList<String>();
			
			/* Crée la javadoc. */
			this.creerJavadocGetEnTeteColonne(this.methodGetEnTeteColonne);
			
			/* Crée le code. */
			this.creerCodeGetEnTeteColonne(this.methodGetEnTeteColonne);
			
			
			/* ENREGISTREMENT *********/
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodGetEnTeteColonne);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}

			/* ajoute 3 lignes. */
			this.ajouterLignesVides(3, this.methodGetEnTeteColonne);

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodGetEnTeteColonne) {
				
				if (StringUtils.isBlank(ligne)) {
					
					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);					
				}				
				else {
					
					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		}
		catch (Exception e) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer la méthode getEnTeteColonne()", e);
			}
		}
				
	} // Fin de ecrireGetEnTeteColonne(...).__________________________________________
	


	/**
	 * method creerJavadocGetEnTeteColonne(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de getEnTeteColonne().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocGetEnTeteColonne(
			List<String> pListe) throws Exception;
	

	
	/**
	 * method creerCodeGetEnTeteColonne(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de getEnTeteColonne().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeGetEnTeteColonne(
			List<String> pListe) throws Exception;
	

	
	/**
	 * method ecrireGetValeurColonne(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>getValeurColonne()</b></li>
	 * <li>écrit la javadoc de getValeurColonne().</li>
	 * <li>écrit le code de getValeurColonne().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireGetValeurColonne(
			final File pFile) {
				
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
		
			
		try {
			
			this.methodGetValeurColonne = new ArrayList<String>();
			
			/* Crée la javadoc. */
			this.creerJavadocGetValeurColonne(this.methodGetValeurColonne);
			
			/* Crée le code. */
			this.creerCodeGetValeurColonne(this.methodGetValeurColonne);
			
			
			/* ENREGISTREMENT *********/
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodGetValeurColonne);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}

			/* ajoute 3 lignes. */
			this.ajouterLignesVides(3, this.methodGetValeurColonne);

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodGetValeurColonne) {
				
				if (StringUtils.isBlank(ligne)) {
					
					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);					
				}				
				else {
					
					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		}
		catch (Exception e) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer la méthode getValeurColonne()", e);
			}
		}
				
	} // Fin de ecrireGetValeurColonne(...).__________________________________________
	


	/**
	 * method creerJavadocGetValeurColonne(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de getValeurColonne().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocGetValeurColonne(
			List<String> pListe) throws Exception;
	

	
	/**
	 * method creerCodeGetValeurColonne(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de getValeurColonne().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeGetValeurColonne(
			List<String> pListe) throws Exception;
	

	
	/**
	 * method ecrireGetId(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>getId()</b></li>
	 * <li>alimente this.methodGetId</li>
	 * <li>écrit la javadoc de getId().</li>
	 * <li>écrit l'Entity de getId().</li>
	 * <li>écrit le code de getId().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireGetId(
			final File pFile) {
				
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
	
		try {
			
			this.methodGetId = new ArrayList<String>();
			
			/* Crée la methode getId(). */
			this.creerJavadocGetId(
					this.methodGetId);
			this.creerEntityGetId(
					this.methodGetId);
			this.creerCodeGetId(
					this.methodGetId);
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodGetId);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			/* ajoute 3 lignes vide. */
			this.ajouterLignesVides(3, this.methodGetId);
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodGetId) {
				
				if (StringUtils.isBlank(ligne)) {
					
					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);					
				}				
				else {
					
					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		}
		catch (Exception e) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer la"
						+ " méthode getId()", e);
			}
		}
				
	} // Fin de ecrireGetId(...).__________________________________________
	
	
	
	/**
	 * method creerJavadocGetId(
	 * List&lt;String&gt pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de getId().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocGetId(List<String> pListe) 
			throws Exception;

	
	
	/**
	 * method creerEntityGetId(
	 * List&lt;String&gt pListe) :<br/>
	 * <ul>
	 * <li>génère l'Entity de getId().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerEntityGetId(List<String> pListe) 
			throws Exception;

	
	
	/**
	 * method creerCodeGetId(
	 * List&lt;String&gt pListe) :<br/>
	 * <ul>
	 * <li>génère le code de getId().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeGetId(List<String> pListe) 
			throws Exception;
	
	
		
	/**
	 * method ecrireSetId(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>setId()</b></li>
	 * <li>alimente this.methodSetId</li>
	 * <li>écrit la javadoc de setId().</li>
	 * <li>écrit le code de setId().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireSetId(
			final File pFile) {
				
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
	
		try {
			
			this.methodSetId = new ArrayList<String>();
			
			/* Crée la methode setId(). */
			this.creerJavadocSetId(
					this.methodSetId);
			
			this.creerCodeSetId(
					this.methodSetId);
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodSetId);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			/* ajoute 3 lignes vide. */
			this.ajouterLignesVides(3, this.methodSetId);
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodSetId) {
				
				if (StringUtils.isBlank(ligne)) {
					
					this.ecrireStringDansFile(
							pFile, "", CHARSET_UTF8, NEWLINE);					
				}				
				else {
					
					this.ecrireStringDansFile(
							pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		}
		catch (Exception e) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer la"
						+ " méthode setId()", e);
			}
		}
				
	} // Fin de ecrireSetId(...).__________________________________________
	
	
	
	/**
	 * method creerJavadocSetId(
	 * List&lt;String&gt pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de setId().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocSetId(List<String> pListe) 
			throws Exception;


	
	/**
	 * method creerCodeSetId(
	 * List&lt;String&gt pListe) :<br/>
	 * <ul>
	 * <li>génère le code de setId().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeSetId(List<String> pListe) 
			throws Exception;
	

	
	/**
	 * method ecrireAccesseurs(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité des <b>getters</b> 
	 * et des <b>setters</b>.</li>
	 * <li>écrit la javadoc des <b>getters</b> 
	 * et des <b>setters</b>.</li>
	 * <li>écrit les Entity des <b>getters</b>.</li>
	 * <li>écrit le code des <b>getters</b> 
	 * et des <b>setters</b>.</li>
	 * <li>ajoute 3 lignes vides à la suite 
	 * de chaque méthode.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireAccesseurs(
			final File pFile) throws Exception {
		
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
		
		final Set<Entry<String, String>> entrySet 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			final List<String> listeGetter = new ArrayList<String>();
			final List<String> listeSetter = new ArrayList<String>();
			
			this.creerJavadocGetter(
					nomAttribut, typeAttribut, listeGetter);
			this.creerCodeEntityGetter(
					nomAttribut, typeAttribut, listeGetter);
			this.creerCodeGetter(
					nomAttribut, typeAttribut, listeGetter);
			
			/* ENREGISTREMENT *********/
			final String ligneIdentifiantGetter 
				= this.fournirLigneIdentifianteGetter(
						nomAttribut, typeAttribut);
			
			try {

				/* Ne fait rien si le code a déjà été écrit. */
				if (this.existLigneCommencant(
						pFile, CHARSET_UTF8, ligneIdentifiantGetter)) {
					return;
				}

				/* ajoute 3 lignes vides*/
				this.ajouterLignesVides(3, listeGetter);
				
				
				/* *************** */
				/* ENREGISTREMENT. */
				/* *************** */
				for (final String ligne : listeGetter) {

					if (StringUtils.isBlank(ligne)) {

						this.ecrireStringDansFile(
								pFile, "", CHARSET_UTF8, NEWLINE);
						
					} else {

						this.ecrireStringDansFile(
								pFile, ligne, CHARSET_UTF8, NEWLINE);
						
					}
				}
			} catch (Exception e) {

				if (LOG.isFatalEnabled()) {
					LOG.fatal("Impossible de créer le code du getter", e);
				}
			}

			
			this.creerJavadocSetter(
					nomAttribut, typeAttribut, listeSetter);
			this.creerCodeSetter(
					nomAttribut, typeAttribut, listeSetter);
			
			/* ENREGISTREMENT *********/
			final String ligneIdentifiantSetter 
				= this.fournirLigneIdentifianteSetter(
						nomAttribut, typeAttribut);
			
			try {

				/* Ne fait rien si le code a déjà été écrit. */
				if (this.existLigneCommencant(
						pFile, CHARSET_UTF8, ligneIdentifiantSetter)) {
					return;
				}

				/* ajoute 3 lignes vides*/
				this.ajouterLignesVides(3, listeSetter);
				
				/* *************** */
				/* ENREGISTREMENT. */
				/* *************** */
				for (final String ligne : listeSetter) {

					if (StringUtils.isBlank(ligne)) {

						this.ecrireStringDansFile(
								pFile, "", CHARSET_UTF8, NEWLINE);
						
					} else {

						this.ecrireStringDansFile(
								pFile, ligne, CHARSET_UTF8, NEWLINE);
						
					}
				}
			} catch (Exception e) {

				if (LOG.isFatalEnabled()) {
					LOG.fatal("Impossible de créer le code du setter", e);
				}
			}
			
		}

		
	} // Fin de ecrireAccesseurs(...)._____________________________________
	

	
	/**
	 * method creerJavadocGetter(
	 * String pNomAttribut
	 * , String pTypeAttribut
	 * , List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>Crée la liste de lignes pour la javadoc du getter 
	 * de l'attribut pNomAttribut.</li>
	 * <li>Injecte la liste de lignes générées dans pListe.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut
	 * @param pTypeAttribut : String : type de l'attribut
	 * @param pListe : List&lt;String&gt; : 
	 * Liste contenant les lignes de code du Getter.<br/>
	 * 
	 * @throws Exception 
	 */
	protected abstract void creerJavadocGetter(
			String pNomAttribut
				, String pTypeAttribut
					, List<String> pListe) 
							throws Exception;

	
	
	/**
	 * method creerCodeEntityGetter(
	 * String pNomAttribut
	 * , String pTypeAttribut
	 * , List&lt;String&gt; pListeGetter) :<br/>
	 * <ul>
	 * <li>Crée la liste de lignes pour l'entity du getter 
	 * de l'attribut pNomAttribut.</li>
	 * <li>Injecte la liste de lignes générées dans pListeGetter.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut
	 * @param pTypeAttribut : String : type de l'attribut
	 * @param pListeGetter : List&lt;String&gt; : 
	 * Liste contenant les lignes de code du Getter.<br/>
	 * 
	 * @throws Exception 
	 */
	protected abstract void creerCodeEntityGetter(
			String pNomAttribut
				, String pTypeAttribut
					, List<String> pListeGetter)
							throws Exception;
	

	
	/**
	 * method creerCodeGetter(
	 * String pNomAttribut
	 * , String pTypeAttribut
	 * , List&lt;String&gt; pListeGetter) :<br/>
	 * <ul>
	 * <li>Crée la liste de lignes de code du getter 
	 * de l'attribut pNomAttribut.</li>
	 * <li>Injecte la liste de lignes générées dans pListeGetter.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut.<br/>
	 * @param pTypeAttribut : String : type de l'attribut.<br/>
	 * @param pListeGetter : List&lt;String&gt; : 
	 * Liste contenant les lignes de code du Getter.<br/>
	 * 
	 * @throws Exception 
	 */
	protected abstract void creerCodeGetter(
			String pNomAttribut
				, String pTypeAttribut
					, List<String> pListeGetter) 
							throws Exception;


	
	/**
	 * method fournirLigneIdentifianteGetter(
	 * String pNomAttribut
	 * , String pTypeAttribut) :<br/>
	 * <ul>
	 * <li>fournit un début de ligne permettant d'identifier 
	 * le Getter d'un attribut.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut.<br/>
	 * @param pTypeAttribut : String : type de l'attribut.<br/>
	 * 
	 * @return : String : début de ligne permettant d'identifier 
	 * le Getter d'un attribut.<br/>
	 */
	protected abstract String fournirLigneIdentifianteGetter(
			String pNomAttribut
				, String pTypeAttribut);
	
	
	
	/**
	 * method creerJavadocSetter(
	 * String pNomAttribut
	 * , String pTypeAttribut
	 * , List&lt;String&gt; pListeSetter) :<br/>
	 * <ul>
	 * <li>Crée la liste de lignes pour la javadoc du setter 
	 * de l'attribut pNomAttribut.</li>
	 * <li>Injecte la liste de lignes générées dans pListeSetter.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut
	 * @param pTypeAttribut : String : type de l'attribut
	 * @param pListeSetter : List&lt;String&gt; : 
	 * Liste contenant les lignes de code du Setter.<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocSetter(
			String pNomAttribut
				, String pTypeAttribut
					, List<String> pListeSetter) 
							throws Exception;
	

	
	/**
	 * method creerCodeSetter(
	 * String pNomAttribut
	 * , String pTypeAttribut
	 * , List&lt;String&gt; pListeSetter) :<br/>
	 * <ul>
	 * <li>Crée la liste de lignes pour le code du setter 
	 * de l'attribut pNomAttribut.</li>
	 * <li>Injecte la liste de lignes générées dans pListeSetter.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut
	 * @param pTypeAttribut : String : type de l'attribut
	 * @param pListeSetter : List&lt;String&gt; : 
	 * Liste contenant les lignes de code du Setter.<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeSetter(
			String pNomAttribut
				, String pTypeAttribut
					, List<String> pListeSetter) 
							throws Exception;
	

	
	/**
	 * method fournirLigneIdentifianteSetter(
	 * String pNomAttribut
	 * , String pTypeAttribut) :<br/>
	 * <ul>
	 * <li>fournit un début de ligne permettant d'identifier 
	 * le Setter d'un attribut.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut.<br/>
	 * @param pTypeAttribut : String : type de l'attribut.<br/>
	 * 
	 * @return : String : début de ligne permettant d'identifier 
	 * le Setter d'un attribut.<br/>
	 */
	protected abstract String fournirLigneIdentifianteSetter(
			String pNomAttribut
				, String pTypeAttribut);
	
	
	
	/**
	 * method ajouterRGsAJavadoc(
	 * List&lt;String&gt; pListe
	 * , String pNomAttribut) :<br/>
	 * <ul>
	 * <li><b>ajoute les RG</b> à la Javadoc pLIste 
	 * d'un attribut pNomAttribut.</li>
	 * </ul>
	 * ne fait rien si pListe == null.<br/>
	 * ne fait rien si pNomAttribut est blank.<br/>
	 * <br/>
	 *
	 * @param pListe : List&lt;String&gt; : 
	 * javadoc dans laquelle il faut insèrer les RGS 
	 * sous forme de liste de Strings.<br/>
	 * @param pNomAttribut : String : Nom de l'attribut.<br/>
	 */
	protected final void ajouterRGsAJavadoc(
			final List<String> pListe
				, final String pNomAttribut) {
		
		/* ne fait rien si pListe == null. */
		if (pListe == null) {
			return;
		}
		
		/* ne fait rien si pNomAttribut est blank. */
		if (StringUtils.isBlank(pNomAttribut)) {
			return;
		}
		
		/* Récupère la liste des RG de l'attribut. */
		final List<String> listeRgs = this.mapRg.get(pNomAttribut);
		
		/* Ajout des RG. */
		if (!listeRgs.isEmpty()) {
			
			/* ouverture de liste HTML. */
			pListe.add(UL_OUVRANT_JAVADOC_MEMBRE);
			
			for (final String rg : listeRgs) {
				
				final List<String> elementListe 
					= new ArrayList<String>();
				elementListe.add("	 * <li>" 
					+ this.fournirTitreRg(rg) 
						+ SEP_2PTS_AERE);
				elementListe.add("	 * " 
						+ this.fournirMessageRg(rg) 
							+ ".</li>");
				
				pListe.addAll(elementListe);
			}
			
			/* fermeture de liste HTML. */
			pListe.add(UL_FERMANT_JAVADOC_MEMBRE);
			
		}
		
	} // Fin de ajouterRGsAJavadoc(...).___________________________________
	

	
	/**
	 * method ajouterAttributsEqualsAJavadoc(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>Ajoute à pListe sous forme d'éléments de liste 
	 * (numérotée ou à puce) les attributs utilisés 
	 * dans equals(...).</li>
	 * <li>.</li>
	 * </ul>
	 * ne fait rien si pListe == null.<br/>
	 * ne fait rien si this.mapAttributsEquals == null.<br/>
	 * <br/>
	 *
	 * @param pListe : List&lt;String&gt; : 
	 * Liste contenant une Javadoc.<br/>
	 */
	protected final void ajouterAttributsEqualsAJavadoc(
			final List<String> pListe) {
		
		/* ne fait rien si pListe == null. */
		if (pListe == null) {
			return;
		}
		
		/* ne fait rien si this.mapAttributsEquals == null. */
		if (this.mapAttributsEquals == null) {
			return;
		}
		
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributsEquals.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			
			final String aAjouter 
				= "	 * <li>" + nomAttribut + ".</li>";
			
			pListe.add(aAjouter);
		}
		
	} // Fin de ajouterAttributsEqualsAJavadoc(...)._______________________
	
	
	
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
	 * 
	 * @throws Exception
	 */
	private String fournirPathMetier() throws Exception {
		
		/* récupère le chemin absolu vers model/metier. */
		final String pathAbsoluMetierString 
			= BundleConfigurationProjetManager.getPathMetier();
		
		/* transformation du chemin String en Path. */
		final Path pathAbsoluMetier = Paths.get(pathAbsoluMetierString);
		
		/* RELATIVIZE : EXTRACTION DU PATH RELATIF. */
		final Path pathMetierRelatif 
		= this.pathRacineMainJava.relativize(pathAbsoluMetier);
		
		/* Transformation du path relatif en String avec des antislash. */
		final String pathMetierRelatifAntiSlash 
			= pathMetierRelatif.toString();
		
		/* Transformation en path Java avec des points. */
		final String pathMetierRelatifPoint 
			= this.remplacerAntiSlashparPoint(
					pathMetierRelatifAntiSlash);
		
		this.pathMetier = pathMetierRelatifPoint;
		
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
	private String fournirNomFichierSansExtension(
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
	protected String genererNomAbstractClass(
			final String pNomInterface) {
		
		/* retourne null si pNomInterface est blank. */
		if (StringUtils.isBlank(pNomInterface)) {
			return null;
		}
		
		/* Pattern sous forme de String. */
		/* - Commence par I
		 * - poursuit par une Majuscule
		 * - poursuit CamelCase. */
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
	 * method genererNomInterface(
	 * String pNomAbstractClass) :<br/>
	 * <ul>
	 * <li>Génère le nom de l'interface à partir 
	 * du nom d'une classe abstraite de type "AbstractObjetMetier".</li>
	 * <li>Par exemple :  genererNomInterface("AbstractProfil") 
	 * retourne IProfil.</li>
	 * </ul>
	 * retourne null si pNomAbstractClass est blank.<br/>
	 * <br/>
	 *
	 * @param pNomAbstractClass : String : nom de la classe abstraite 
	 * du type "AbstractObjetMetier".<br/>
	 * 
	 * @return : String : nom de l'interface 
	 * du type "IObjetMetie"r.<br/>
	 */
	protected String genererNomInterface(
			final String pNomAbstractClass) {
				
		/* retourne null si pNomAbstractClass est blank. */
		if (StringUtils.isBlank(pNomAbstractClass)) {
			return null;
		}
		
		/* Pattern sous forme de String. */
		/* - Commence par Abstract
		 * - poursuit par une Majuscule
		 * - poursuit CamelCase. */
		final String patternString = "(^Abstract)([A-Z][a-zA-Z0-9]*$)";
		
		/* Instanciation d'un Pattern. */
		final Pattern pattern = Pattern.compile(patternString);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcher = pattern.matcher(pNomAbstractClass);
		
		/* Recherche du Pattern. */
		final boolean trouve = matcher.find();
		
		String group2 = null;
		
		String resultatString = null;
		
		if (trouve) {
			
			group2 = matcher.group(2);
			
			resultatString = "I" + group2;
			
		}
		
		return resultatString;
				
	} // Fin de genererNomInterface(...).__________________________________
	
	
	
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
	 * method fournirDebutSepAttributs() :<br/>
	 * retourne le début du séparateur attributs.<br/>
	 * "	// ************************ATTRIBUTS"<br/>
	 *
	 * @return : String : "	// ************************ATTRIBUTS".<br/>
	 */
	private String fournirDebutSepAttributs() {
		return "	// ************************ATTRIBUTS";
	} // Fin de fournirDebutSepAttributs().________________________________
	

	
	/**
	 * method fournirDebutSepMethodes() :<br/>
	 * retourne le début du séparateur méthodes.<br/>
	 * "	private static final Log LOG"<br/>
	 *
	 * @return : String : "	private static final Log LOG".<br/>
	 */
	private String fournirDebutSepMethodes() {
		return "	private static final Log LOG";
	} // Fin de fournirDebutSepMethodes()._________________________________
	

	
	/**
	 * method fournirDebutStringClasse() :<br/>
	 * retourne le début de la ligne stringClasse.<br/>
	 * "	public static final String CLASSE"<br/>
	 *
	 * @return : String : "	public static final String CLASSE".<br/>
	 */
	private String fournirDebutStringClasse() {
		return "	public static final String CLASSE";
	} // Fin de fournirDebutStringClasse().________________________________
	

	
	/**
	 * method fournirDebutAttributId() :<br/>
	 * retourne le début de la ligne attributId.<br/>
	 * "	protected Long id"<br/>
	 *
	 * @return : String : "	protected Long id".<br/>
	 */
	private String fournirDebutAttributId() {
		return "	protected Long id";
	} // Fin de fournirDebutAttributId().__________________________________
	
	
	
	/**
	 * method fabriquerNomClasse(
	 * String pString) :<br/>
	 * <ul>
	 * <li>fabrique le stringClasse.</li>
	 * <li>Par exemple : <br/>
	 * <code>fabriquerNomClasse("AbstractProfilSimple") 
	 * retourne ABSTRACT_PROFIL_SIMPLE</code>.</li>
	 * </ul>
	 * retourne null si pString est blank.<br/>
	 * retourne null si pString n'est pas CamelCase.<br/>
	 * <br/>
	 *
	 * @param pString : String : nom d'une Classe.<br/>
	 * 
	 * @return : String : stringClasse.<br/>
	 */
	private String fabriquerNomClasse(
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
		
	}
	
	
	
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
	private boolean conformeNomPackage(
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
	private boolean conformeNomInterface(
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
	private boolean conformeNomClasse(
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
	private boolean conformeNomAttribut(
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
	private boolean conformeRg(
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
	private List<String> trouverCamel(
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
	private String mettreEnMajuscules(
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
	private String mettrePremiereEnMajuscule(
			final String pString) {
		
		/* retourne null si pString == null. */
		if (pString == null) {
			return null;
		}
		
		return WordUtils.capitalizeFully(pString);
		
	} // Fin de mettrePremiereEnMajuscule(...).____________________________
	
	
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
	private String mettrePremiereEnMajusculeEtGarder(
			final String pString) {
		
		/* retourne null si pString == null. */
		if (pString == null) {
			return null;
		}
		
		return WordUtils.capitalize(pString);
		
	} // Fin de mettrePremiereEnMajusculeEtGarder(...).____________________
	
	
	
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
	private String mettreEnMinuscules(
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
	 * method fournirParametre(
	 * String pString) :<br/>
	 * <ul>
	 * <li>fournit un paramètre à partir d'un attribut.</li>
	 * <li>par exemple : <br/>
	 * <code>fournirParametre(profilString) 
	 * retourne pProfilString</code></li>
	 * </ul>
	 * retourne null si pString est blank.<br/>
	 * retourne null si pString n'est pas conforme 
	 * aux noms d'attributs.<br/>
	 * <br/>
	 *
	 * @param pString : String : attribut.<br/>
	 * 
	 * @return : String : paramètre généré à partir de l'attribut.<br/>
	 */
	protected final String fournirParametre(
			final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		/* retourne null si pString n'est pas 
		 * conforme aux noms d'attributs. */
		if (!conformeNomAttribut(pString)) {
			return null;
		}
		
		final String resultat 
			= "p" + mettrePremiereEnMajusculeEtGarder(pString);

		return resultat;
		
	} // Fin de fournirParametre(...)._____________________________________
	

		
	/**
	 * method fournirGetter(
	 * String pString) :<br/>
	 * <ul>
	 * <li>fournit un getter à partir d'un attribut.</li>
	 * <li>par exemple : <br/>
	 * <code>fournirGetter(profilString) 
	 * retourne getProfilString()</code></li>
	 * </ul>
	 * retourne null si pString est blank.<br/>
	 * retourne null si pString n'est pas conforme 
	 * aux noms d'attributs.<br/>
	 * <br/>
	 *
	 * @param pString : String : attribut.<br/>
	 * 
	 * @return : String : getter généré à partir de l'attribut.<br/>
	 */
	protected final String fournirGetter(
			final String pString) {
				
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		/* retourne null si pString n'est pas 
		 * conforme aux noms d'attributs. */
		if (!conformeNomAttribut(pString)) {
			return null;
		}
		
		final String resultat 
		= "get" + mettrePremiereEnMajusculeEtGarder(pString) + "()";

		return resultat;
		
	} // Fin de fournirGetter(...).________________________________________
	

			
	/**
	 * method fournirSetter(
	 * String pString) :<br/>
	 * <ul>
	 * <li>fournit un setter à partir d'un attribut.</li>
	 * <li>par exemple : <br/>
	 * <code>fournirSetter(profilString) 
	 * retourne setProfilString</code></li>
	 * </ul>
	 * retourne null si pString est blank.<br/>
	 * retourne null si pString n'est pas conforme 
	 * aux noms d'attributs.<br/>
	 * <br/>
	 *
	 * @param pString : String : attribut.<br/>
	 * 
	 * @return : String : setter généré à partir de l'attribut.<br/>
	 */
	protected final String fournirSetter(
			final String pString) {
				
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		/* retourne null si pString n'est pas 
		 * conforme aux noms d'attributs. */
		if (!conformeNomAttribut(pString)) {
			return null;
		}
		
		final String resultat 
		= "set" + mettrePremiereEnMajusculeEtGarder(pString);
	
		return resultat;
		
	} // Fin de fournirSetter(...).________________________________________
	

	
	/**
	 * method fournirCsv() :<br/>
	 * <ul>
	 * <li>retourne la ligne d'en-tête csv d'un objet Java.</li>
	 * <li>Par exemple :<br/>
	 * <code>id;profil;</code></li>
	 * </ul>
	 *
	 * @return : String : ligne d'en-tête csv d'un objet Java.<br/>
	 */
	protected final String fournirCsv() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append("id;");
		
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributs.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			
			stb.append(nomAttribut);
			stb.append(POINT_VIRGULE);
		}
		
		return stb.toString();
		
	} // Fin de fournirCsv().______________________________________________
	
	
	
	/**
	 * method fournirEntierCompare(
	 * String pString) :<br/>
	 * <ul>
	 * <li>fournit un entier de comparaison à partir d'un attribut.</li>
	 * <li>par exemple : <br/>
	 * <code>fournirEntierCompare(profilString) 
	 * retourne "compareProfilString"</code></li>
	 * </ul>
	 * retourne null si pString est blank.<br/>
	 * retourne null si pString n'est pas conforme 
	 * aux noms d'attributs.<br/>
	 * <br/>
	 *
	 * @param pString : String : attribut.<br/>
	 * 
	 * @return : String : entier de comparaison généré 
	 * à partir de l'attribut.<br/>
	 */
	protected final String fournirEntierCompare(
			final String pString) {
				
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		/* retourne null si pString n'est pas 
		 * conforme aux noms d'attributs. */
		if (!conformeNomAttribut(pString)) {
			return null;
		}
		
		final String resultat 
		= "compare" + mettrePremiereEnMajusculeEtGarder(pString);
	
		return resultat;
		
	} // Fin de fournirEntierCompare(...)._________________________________
	

	
	/**
	 * method fournirTitreRg(
	 * String pString) :<br/>
	 * <ul>
	 * <li>fournit la partie titre d'une RG.</li>
	 * <li>par exemple : <br/>
	 * <code>fournirTitreRg("RG_PROFIL_PROFILSTRING_NOMENCLATURE_02 : 
	 * le profilString du Profil doit respecter un ensemble 
	 * fini de valeurs (nomenclature)") 
	 * retourne "RG_PROFIL_PROFILSTRING_NOMENCLATURE_02"</code></li>
	 * </ul>
	 * retourne null si pString est blank.<br/>
	 * retourne null si pString n'est pas conforme 
	 * aux noms des RG.<br/>
	 * <br/>
	 *
	 * @param pString : String : RG.<br/>
	 * 
	 * @return : String : partie gauche (titre) de la RG.<br/>
	 */
	protected final String fournirTitreRg(
			final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		/* retourne null si pString n'est pas 
		 * conforme aux noms des RG. */
		if (!conformeRg(pString)) {
			return null;
		}
		
		/* Pattern sous forme de String. */
		/* - Commence par "RG_" (^RG_)
		 * - poursuit par des majusculres séparées par des _ ou - ([A-Z-_]*).
		 * - poursuit par des chiffres (\\d+) 
		 * - séparateur " : ".
		 * - poursuit par n'importe quels caractères
		 * */
		final String patternString = "^(RG_(([A-Z_-]*)(\\d+))) : (.+)$";
		
		/* Instanciation d'un Pattern. */
		final Pattern pattern = Pattern.compile(patternString);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcher = pattern.matcher(pString);
		
		/* Recherche du Pattern. */
		final boolean trouve = matcher.matches();
		
		String resultat = null;
		
		if (trouve) {			
			resultat = matcher.group(1);
		}

		return resultat;
		
	} // Fin de fournirTitreRg(...)._______________________________________
	
	
	
	/**
	 * method fournirMessageRg(
	 * String pString) :<br/>
	 * <ul>
	 * <li>fournit la partie message d'une RG.</li>
	 * <li>par exemple : <br/>
	 * <code>fournirTitreRg("RG_PROFIL_PROFILSTRING_NOMENCLATURE_02 : 
	 * le profilString du Profil doit respecter un ensemble 
	 * fini de valeurs (nomenclature)") 
	 * retourne "le profilString du Profil doit respecter un ensemble 
	 * fini de valeurs (nomenclature)"</code></li>
	 * </ul>
	 * retourne null si pString est blank.<br/>
	 * retourne null si pString n'est pas conforme 
	 * aux noms des RG.<br/>
	 * <br/>
	 *
	 * @param pString : String : RG.<br/>
	 * 
	 * @return : String : partie droite (message) de la RG.<br/>
	 */
	protected final String fournirMessageRg(
			final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		/* retourne null si pString n'est pas 
		 * conforme aux noms des RG. */
		if (!conformeRg(pString)) {
			return null;
		}
		
		/* Pattern sous forme de String. */
		/* - Commence par "RG_" (^RG_)
		 * - poursuit par des majusculres séparées par des _ ou - ([A-Z-_]*).
		 * - poursuit par des chiffres (\\d+) 
		 * - séparateur " : ".
		 * - poursuit par n'importe quels caractères
		 * */
		final String patternString = "^(RG_(([A-Z_-]*)(\\d+))) : (.+)$";
		
		/* Instanciation d'un Pattern. */
		final Pattern pattern = Pattern.compile(patternString);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcher = pattern.matcher(pString);
		
		/* Recherche du Pattern. */
		final boolean trouve = matcher.matches();
		
		String resultat = null;
		
		if (trouve) {			
			resultat = matcher.group(5);
		}

		return resultat;
		
	} // Fin de fournirMessageRg(...)._____________________________________

	
	
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
	
	
	
} // FIN DE LA CLASSE AbstractEcriveurMetier.--------------------------------------