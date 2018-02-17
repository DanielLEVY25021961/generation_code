package levy.daniel.application.apptechnic.generationcode.ecriveurs;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.generationcode.IGenerateur;

/**
 * CLASSE ABSTRAITE <b>AbstractEcriveurFichiersJavaDetaille</b> :<br/>
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
public abstract class AbstractEcriveurFichiersJavaDetaille 
						extends AbstractEcriveurFichiersJava {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ABSTRACT_ECRIVEUR_FICHIERS_JAVA_DETAILLE : String :<br/>
	 * "Classe AbstractEcriveurFichiersJavaDetaille".<br/>
	 */
	public static final String CLASSE_ABSTRACT_ECRIVEUR_FICHIERS_JAVA_DETAILLE 
		= "Classe AbstractEcriveurFichiersJavaDetaille";

	
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
			= LogFactory.getLog(AbstractEcriveurFichiersJavaDetaille.class);
	

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR AbstractEcriveurFichiersJavaDetaille() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractEcriveurFichiersJavaDetaille() {
		
		super();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void ecrireCodeGenerique(
			final File pFile
				, final IGenerateur pGenerateur) {
		
		/* alimente this.nomSimpleInterface. */
		/* alimente this.nomSimpleAbstractClass. */
		/* alimente this.nomSimpleConcreteClass. */
		/* alimente this.fichierJava. */
		/* alimente this.nomSimpleFichierJava. */
		this.alimenterAttributsFichiersAEcrire(pFile);
		

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
		
	} // Fin de ecrireCodeGenerique(...).__________________________________
	

	
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
			this.ecrireCode(this.imports, pFile);
			
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

			
	
} // FIN DE LA CLASSE AbstractEcriveurFichiersJavaDetaille.--------------------------------------
