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
	 * 
	 * @throws Exception 
	 */
	@Override
	protected void ecrireCodeGenerique(
			final File pFile
				, final IGenerateur pGenerateur) throws Exception {
		
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
		
		/* Ecrit la ligne de DECLARATION de la classe à la suite. */
		this.ecrireLigneDeclaration(pFile);

		/* Appelle un HOOK this.ecrireCodeHook(....) pour terminer la génération 
		 * du code dans un Ecriveur concret. */
		this.ecrireCodeHook(this.fichierJava);
		
		/* Ecrit la ligne FINALE. */
		this.ecrireLigneFinale(this.fichierJava);
		
	} // Fin de ecrireCodeGenerique(...).__________________________________
	

	
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
			this.ecrireCode(this.javadoc, pFile);
			
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
	 * 
	 * @throws Exception 
	 */
	private void ecrireLigneDeclaration(
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
	 * 
	 * @throws Exception
	 */
	protected abstract String creerLigneDeclaration(File pFile) 
				throws Exception;
	

	
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
			this.ecrireCode(this.sepAttributs, pFile);
			
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
				
		final List<String> listeLignes 
			= this.lireTemplate("sep_attributs.txt");
				
		this.sepAttributs = listeLignes;
		
		return this.sepAttributs;
					
	} // Fin de creerSepAttributs(...).____________________________________
	
	
	
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
			this.ecrireCode(this.stringClasse, pFile);
			
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
		
		/* Lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("string_classe.txt");
		
		/* substitutions. */
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
	 * method ecrireSautLigneJava(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit l'attribut SAUT_LIGNE_JAVA dans pFile.</li>
	 * <li>ajoute 2 lignes vides sous l'attribut.</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception
	 */
	protected final void ecrireSautLigneJava(
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

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("attribut_saut_ligne_java.txt");
		
		/* Recherche la ligne identifiant. */
		final String ligneIdentifiant 
			= this.fournirIdentifiantDebutSautLigneJava();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}
		
		/* ajoute 2 lignes vides sous l'attribut. */
		listeLignes.add("");
		listeLignes.add("");
	
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeLignes, pFile);
		
	} // Fin de ecrireSautLigneJava(...).__________________________________
	

	
	/**
	 * method fournirIdentifiantDebutSautLigneJava() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant l'attribut 
	 * SAUT_LIGNE_JAVA pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutSautLigneJava() {
		return "	public static final char SAUT_LIGNE_JAVA";
	} // Fin de fournirIdentifiantDebutSautLigneJava().____________________
	
	
	
	/**
	 * method ecrireAttributSerialVersionUid(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit l'attribut serialVersionUID dans pFile.</li>
	 * <li>ajoute 2 lignes vides sous l'attribut.</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception
	 */
	protected final void ecrireAttributSerialVersionUid(
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

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("attribut_serialversionuid.txt");
		
		/* Recherche la ligne identifiant. */
		final String ligneIdentifiant 
			= this.fournirIdentifiantDebutSerialVersionUid();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}
		
		/* ajoute 2 lignes vides sous l'attribut. */
		listeLignes.add("");
		listeLignes.add("");
	
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeLignes, pFile);
		
	} // Fin de ecrireSautLigneJava(...).__________________________________
	

	
	/**
	 * method fournirIdentifiantDebutSerialVersionUid() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant l'attribut 
	 * serialVersionUid pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutSerialVersionUid() {
		return "	private static final long serialVersionUID";
	} // Fin de fournirIdentifiantDebutSerialVersionUid()._________________
	

	
	
	/**
	 * method ecrireAttributLog(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit l'attribut LOG dans pFile.</li>
	 * <li>ajoute 2 lignes vides sous l'attribut.</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception
	 */
	protected final void ecrireAttributLog(
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

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("attribut_log.txt");
		
		/* substitution. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOMSIMPLEFICHIERJAVA
							, this.nomSimpleFichierJava);
		
		/* Recherche la ligne identifiant. */
		final String ligneIdentifiant 
			= this.fournirIdentifiantDebutAttributLog();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}
		
		/* ajoute 2 lignes vides sous l'attribut. */
		listeSubst1.add("");
		listeSubst1.add("");
	
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst1, pFile);
		
	} // Fin de ecrireAttributLog(...).____________________________________
	

	
	/**
	 * method fournirIdentifiantDebutAttributLog() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant l'attribut 
	 * LOG pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutAttributLog() {
		return "	private static final Log LOG";
	} // Fin de fournirIdentifiantDebutAttributLog().______________________
	
	
	
	/**
	 * method ecrireLignesSepMethodes(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la ligne de séparation des methodes.</li>
	 * <li>rajoute 2 lignes vides sous le séparateur.</li>
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
	
			/* rajoute 2 lignes vides sous le séparateur. */
			this.sepMethodes.add("");
			this.sepMethodes.add("");
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			this.ecrireCode(this.sepMethodes, pFile);
			
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
		
		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("sep_methodes.txt");
						
		this.sepMethodes = listeLignes;
		
		return this.sepMethodes;
					
	} // Fin de creerSepMethodes(...)._____________________________________
	

	
	/**
	 * method fournirDebutSepMethodes() :<br/>
	 * retourne le début du séparateur méthodes.<br/>
	 * "	// *************************METHODES"<br/>
	 *
	 * @return : String : "	// *************************METHODES".<br/>
	 */
	private String fournirDebutSepMethodes() {
		return "	// *************************METHODES";
	} // Fin de fournirDebutSepMethodes()._________________________________
	

	
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
	protected final String genererNomAbstractClass(
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
	protected final String genererNomInterface(
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
