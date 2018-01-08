package levy.daniel.application.apptechnic.generationcode.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.generationcode.AbstractEcriveur;

/**
 * class EcriveurInterface :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Path.relativize(), extraire un chemin relatif, 
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
public class EcriveurInterface extends AbstractEcriveur {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ECRIVEUR_INTERFACE : String :<br/>
	 * "Classe EcriveurInterface".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_INTERFACE
		= "Classe EcriveurInterface";
	


	/**
	 * INTERFACE : String :<br/>
	 * "public interface ".<br/>
	 */
	public static final String INTERFACE = "public interface ";
	
	
	/**
	 * EXTENDS : String :<br/>
	 * " extends IExportateurCsv, IExportateurJTable, ".<br/>
	 */
	public static final String EXTENDS 
		= " extends IExportateurCsv, IExportateurJTable, ";

	
	/**
	 * CLONEABLE : String :<br/>
	 * ", Cloneable, Serializable ".<br/>
	 */
	public static final String CLONEABLE 
		= ", Cloneable, Serializable ";
	
	
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(EcriveurInterface.class);

	// *************************METHODES************************************/
		 
	
	/**
	* method CONSTRUCTEUR EcriveurInterface() :<br/>
	* CONSTRUCTEUR D'ARITE NULLE.<br/>
	*/
	public EcriveurInterface() {
		
		super();
				
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * method ecrireCodeHook(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>.</li>
	 * <li>.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	@Override
	protected final void ecrireCodeHook(
			final File pFile) {
				
			
		/* Ecrit la ligne de déclaration. */
		this.ecrireLigneDeclaration(pFile);
		
		/* Insère 3 lignes vides sous la ligne 
		 * de déclaration.*/
		this.insererLignesVidesSousLigneDansFichier(
				pFile, this.ligneDeclaration, 3, CHARSET_UTF8);
		
		/* Ecrit la méthode compareTo(). */
		this.ecrireMethodCompareTo(pFile);
		
		final String derniereLigneCompareTo 
			= this.fournirDerniereLigneListe(this.methodCompareTo);
					
		/* Insère 3 lignes vides sous la dernière ligne de compareTo(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneCompareTo, 3, CHARSET_UTF8);
		
		/* Ecrit la méthode clone(). */
		this.ecrireMethodClone(pFile);
		
		final String derniereLigneClone 
			= this.fournirDerniereLigneListe(this.methodClone);
			
		
		/* Insère 3 lignes vides sous la dernière ligne
		 *  de clone(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneClone, 3, CHARSET_UTF8);
		
		/* Ecrit la méthode getEnTeteCsv(). */
		this.ecrireMethodGetEnTeteCsv(pFile);
		
		final String derniereLigneGetEnTeteCsv 
			= this.fournirDerniereLigneListe(this.methodGetEnTeteCsv);
					
		/* Insère 3 lignes vides sous la dernière ligne 
		 * de getEnTeteCsv(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneGetEnTeteCsv, 3, CHARSET_UTF8);

		/* Ecrit la méthode toStringCsv(). */
		this.ecrireMethodToStringCsv(pFile);
		
		final String derniereLigneToStringCsv 
			= this.fournirDerniereLigneListe(this.methodToStringCsv);			
		
		/* Insère 3 lignes vides sous la dernière ligne
		 *  de toStringCsv(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneToStringCsv, 3, CHARSET_UTF8);
		
		/* Ecrit la méthode getEnTeteColonne(). */
		this.ecrireMethodGetEnTeteColonne(pFile);
		
		final String derniereLigneGetEnTeteColonne 
			= this.fournirDerniereLigneListe(this.methodGetEnTeteColonne);
		
		/* Insère 3 lignes vides sous la dernière 
		 * ligne de getEnTeteColonne(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneGetEnTeteColonne, 3, CHARSET_UTF8);

		/* Ecrit la méthode getValeurColonne(). */
		this.ecrireMethodGetValeurColonne(pFile);
		
		final String derniereLigneGetValeurColonne 
			= this.fournirDerniereLigneListe(this.methodGetValeurColonne);
		
		/* Insère 3 lignes vides sous la dernière 
		 * ligne de getValeurColonne(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneGetValeurColonne, 3, CHARSET_UTF8);
		
		/* Ecrit la méthode getId(). */
		this.ecrireMethodGetId(pFile);
		
		final String derniereLigneGetId 
			= this.fournirDerniereLigneListe(this.methodGetId);
					
		/* Insère 3 lignes vides sous la dernière 
		 * ligne de getId(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneGetId, 3, CHARSET_UTF8);

		/* Ecrit la méthode setId(). */
		this.ecrireMethodSetId(pFile);
		
		final String derniereLigneSetId 
			= this.fournirDerniereLigneListe(this.methodSetId);
					
		/* Insère 3 lignes vides sous la dernière 
		 * ligne de setId(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneSetId, 3, CHARSET_UTF8);


		/* Ecrit la ligne finale. */
		this.ecrireLigneFinale(pFile);
		
		
		
	} // Fin de ecrireCodeHook(...)._______________________________________

	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesImport() throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/imports_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$pathmetier}", this.pathMetier);
		
		this.imports = listeLignesSubstitue;
		
		return this.imports;
		
	} // Fin de creerLignesImport()._______________________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesJavaDoc(
			final File pFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String creerLigneDeclaration(
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
		
		stb.append(INTERFACE);
		stb.append(this.nomSimpleFichierJava);
		stb.append(EXTENDS);
		stb.append("Comparable<");
		stb.append(this.nomSimpleFichierJava);
		stb.append('>');
		stb.append(CLONEABLE);
		stb.append(CROCHET_OUVRANT);
		
		this.ligneDeclaration = stb.toString();
		
		return this.ligneDeclaration;
		
	} // Fin de creerLigneDeclaration(...).________________________________
	

	
	/**
	 * method ecrireMethodCompareTo(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode compareTo()
	 * après la ligne de déclaration.</li>
	 * <li>N'insère les lignes que si elles n'existent pas déjà</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodCompareTo(
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
			
			/* Crée la methode compareTo(). */
			this.creerLignesMethodCompareTo();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodCompareTo.get(this.methodCompareTo.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			for (final String ligne : this.methodCompareTo) {
				
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
				LOG.fatal("Impossible de créer la méthode compareTo()", e);
			}
		}
				
	} // Fin de ecrireMethodCompareTo(...).________________________________
	


	/**
	 * method creerLignesMethodCompareTo() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode compareTo().</li>
	 * <li>alimente this.methodCompareTo</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodCompareTo.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodCompareTo() throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodCompareTo_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$IObjet}", this.nomSimpleFichierJava); // NOPMD by dan on 08/01/18 08:09
		
		this.methodCompareTo = listeLignesSubstitue;
		
		return this.methodCompareTo;
				
	} // Fin de creerLignesMethodCompareTo().______________________________
	

		
	/**
	 * method ecrireMethodClone(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode clone()
	 * après la méthode compareTo().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodClone(
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
			
			/* Crée la methode clone(). */
			this.creerLignesMethodClone();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodClone.get(this.methodClone.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				
	} // Fin de ecrireMethodClone(...).____________________________________
	


	/**
	 * method creerLignesMethodClone() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode clone().</li>
	 * <li>alimente this.methodClone.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodClone.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodClone() throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodClone_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$IObjet}", this.nomSimpleFichierJava);
		
		this.methodClone = listeLignesSubstitue;
		
		return this.methodClone;
				
	} // Fin de creerLignesMethodClone().__________________________________
	

		
	/**
	 * method ecrireMethodGetEnTeteCsv(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode getEnTeteCsv()
	 * après la méthode clone().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodGetEnTeteCsv(
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
			
			/* Crée la methode getEnTeteCsv(). */
			this.creerLignesMethodGetEnTeteCsv();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodGetEnTeteCsv.get(this.methodGetEnTeteCsv.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				
	} // Fin de ecrireMethodGetEnTeteCsv(...).____________________________________
	
	
	
	/**
	 * method creerLignesMethodGetEnTeteCsv() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode getEnTeteCsv().</li>
	 * <li>alimente this.methodGetEnTeteCsv.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodGetEnTeteCsv.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodGetEnTeteCsv() throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodGetEnTeteCsv_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$IObjet}", this.nomSimpleFichierJava);
		
		this.methodGetEnTeteCsv = listeLignesSubstitue;
		
		return this.methodGetEnTeteCsv;
				
	} // Fin de creerLignesMethodGetEnTeteCsv().__________________________________
	

		
	/**
	 * method ecrireMethodToStringCsv(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode toStringCsv()
	 * après la méthode getEnTeteCsv().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodToStringCsv(
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
			
			/* Crée la methode toStringCsv(). */
			this.creerLignesMethodToStringCsv();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodToStringCsv.get(this.methodToStringCsv.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				
	} // Fin de ecrireMethodToStringCsv(...).______________________________
	
	
	
	/**
	 * method creerLignesMethodToStringCsv() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode toStringCsv().</li>
	 * <li>alimente this.methodToStringCsv.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodToStringCsv.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodToStringCsv() throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodToStringCsv_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$IObjet}", this.nomSimpleFichierJava);
		
		this.methodToStringCsv = listeLignesSubstitue;
		
		return this.methodToStringCsv;
				
	} // Fin de creerLignesMethodToStringCsv().____________________________
	
	
		
	/**
	 * method ecrireMethodGetEnTeteColonne(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode getEnTeteColonne()
	 * après la méthode toStringCsv().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodGetEnTeteColonne(
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
			
			/* Crée la methode getEnTeteColonne(). */
			this.creerLignesMethodGetEnTeteColonne();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodGetEnTeteColonne
					.get(this.methodGetEnTeteColonne.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				LOG.fatal("Impossible de créer la" // NOPMD by dan on 08/01/18 08:09
						+ " méthode getEnTeteColonne()", e);
			}
		}
				
	} // Fin de ecrireMethodGetEnTeteColonne(...)._________________________
	
	
	
	/**
	 * method creerLignesMethodGetEnTeteColonne() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode getEnTeteColonne().</li>
	 * <li>alimente this.methodGetEnTeteColonne.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodGetEnTeteColonne.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodGetEnTeteColonne() 
			throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodGetEnTeteColonne_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$IObjet}", this.nomSimpleFichierJava);
		
		this.methodGetEnTeteColonne = listeLignesSubstitue;
		
		return this.methodGetEnTeteColonne;
				
	} // Fin de creerLignesMethodGetEnTeteColonne()._______________________
	

		
	/**
	 * method ecrireMethodGetValeurColonne(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode getValeurColonne()
	 * après la méthode getEnTeteColonne().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodGetValeurColonne(
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
			
			/* Crée la methode getValeurColonne(). */
			this.creerLignesMethodGetValeurColonne();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodGetValeurColonne
					.get(this.methodGetValeurColonne.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				LOG.fatal("Impossible de créer la"
						+ " méthode getValeurColonne()", e);
			}
		}
				
	} // Fin de ecrireMethodGetValeurColonne(...)._________________________
	
	
	
	/**
	 * method creerLignesMethodGetValeurColonne() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode getValeurColonne().</li>
	 * <li>alimente this.methodGetValeurColonne.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodGetValeurColonne.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodGetValeurColonne() 
			throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodGetValeurColonne_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$IObjet}", this.nomSimpleFichierJava);
		
		this.methodGetValeurColonne = listeLignesSubstitue;
		
		return this.methodGetValeurColonne;
				
	} // Fin de creerLignesMethodGetValeurColonne()._______________________
	

		
	/**
	 * method ecrireMethodGetId(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode getId()
	 * après la méthode getValeurColonne().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodGetId(
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
			
			/* Crée la methode getId(). */
			this.creerLignesMethodGetId();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodGetId
					.get(this.methodGetId.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				
	} // Fin de ecrireMethodGetId(...)._________________________
	
	
	
	/**
	 * method creerLignesMethodGetId() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode getId().</li>
	 * <li>alimente this.methodGetId.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodGetId.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodGetId() 
			throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodGetId_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		this.methodGetId = listeLignes;
		
		return this.methodGetId;
				
	} // Fin de creerLignesMethodGetId()._______________________

	
		
	/**
	 * method ecrireMethodSetId(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode setId()
	 * après la méthode getId().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodSetId(
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
			
			/* Crée la methode setId(). */
			this.creerLignesMethodSetId();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodSetId
					.get(this.methodSetId.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				
	} // Fin de ecrireMethodSetId(...)._________________________
	
	
	
	/**
	 * method creerLignesMethodSetId() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode setId().</li>
	 * <li>alimente this.methodSetId.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodSetId.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodSetId() 
			throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodSetId_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
				
		this.methodSetId = listeLignes;
		
		return this.methodSetId;
				
	} // Fin de creerLignesMethodSetId()._______________________
	


	/**
	 * method ecrireLigneFinale(
	 * ) :<br/>
	 * <ul>
	 * <li>Insère la ligne de déclaration
	 * à la cinquième ligne du fichier.</li>
	 * <li>N'insère la ligne que si elle n'existe pas déjà</li>
	 * <li>Par exemple : <code>public interface IProfil extends 
	 * IExportateurCsv, IExportateurJTable, 
	 * Comparable&lt;IProfil&gt;, Cloneable, Serializable {</code>.</li>
	 * </ul>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireLigneFinale(
			final File pFile) {
		
		/* Crée la ligne finale de l'Interface. */
		this.creerLigneFinale(pFile);
		
		if (!this.existLigneDansFichier(
				pFile, CHARSET_UTF8, this.ligneFinale)) {
			
			/* Insère la ligne finale à la fin du fichier. */
			this.ecrireStringDansFile(
					pFile, this.ligneFinale
					, CHARSET_UTF8, NEWLINE);
						
		}
		
	} // Fin de ecrireLigneFinale(...).____________________________________
	
	
	
	/**
	 * method creerLigneFinale(
	 * ) :<br/>
	 * <ul>
	 * <li>Génère la ligne de code pour la 
	 * ligne finale du fichier Java.</li>
	 * <li>Alimente this.ligneFinale.</li>
	 * <li>Par exemple : <code>public interface IProfil extends 
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
	 * @return : String : Ligne de code pour la ligne finale.<br/>
	 */
	private String creerLigneFinale(
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
		stb.append(" // FIN DE L'INTERFACE ");
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
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirNomClasse() {
		return CLASSE_ECRIVEUR_INTERFACE;
	} // Fin de fournirNomClasse().________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirDebutDeclaration() {
		return INTERFACE;
	} // Fin de fournirDebutDeclaration()._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirDebutJavaDoc() {
		return " * INTERFACE";
	} // Fin de fournirDebutJavaDoc()._____________________________________


	
} // FIN DE LA CLASSE EcriveurInterface.-------------------------------------
