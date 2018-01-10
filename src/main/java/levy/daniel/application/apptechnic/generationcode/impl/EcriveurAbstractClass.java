package levy.daniel.application.apptechnic.generationcode.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.generationcode.AbstractEcriveur;

/**
 * class EcriveurAbstractClass :<br/>
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
public class EcriveurAbstractClass extends AbstractEcriveur {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ECRIVEUR_ABSTRACTCLASS : String :<br/>
	 * "Classe EcriveurAbstractClass".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_ABSTRACTCLASS 
		= "Classe EcriveurAbstractClass";

	
	/**
	 * ABSTRACT_CLASS : String :<br/>
	 * "public abstract class ".<br/>
	 */
	public static final String ABSTRACT_CLASS 
		= "public abstract class ";

	
	/**
	 * IMPLEMENTS : String :<br/>
	 * " implements ".<br/>
	 */
	public static final String IMPLEMENTS = " implements ";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(EcriveurAbstractClass.class);

	
	// *************************METHODES************************************/
	
	
	/**
	* method CONSTRUCTEUR EcriveurAbstractClass() :<br/>
	* CONSTRUCTEUR D'ARITE NULLE.<br/>
	*/
	public EcriveurAbstractClass() {
		
		super();
				
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void ecrireCodeHook(
			final File pFile) {
		
		/* écrit le séparateur attributs. */
		this.ecrireSepAttributs(pFile);
		
		/* écrit la stringClasse. */
		this.ecrireStringClasse(pFile);
		
		final String derniereLigneStringClasse 
			= this.fournirDerniereLigneListe(this.stringClasse);
		
		/* insère 2 lignes sous stringClasse. */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneStringClasse, 2, CHARSET_UTF8);
		
		/* écrit attributId. */
		this.ecrireAttributId(pFile);
		
		
		try {
			
			/* écrit tous les attributs. */
			this.ecrireAttributs(pFile);
			
		} catch (Exception e) {
					
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer les attributs", e);
			}
		}
		
		/* écrit le séparateur methodes. */
		this.ecrireSepMethodes(pFile);
		
		
		try {
			
			/* écrit le constructeur d'arité nulle. */
			this.ecrireConstructeurNull(pFile);
			
			/* écrit le constructeur complet. */
			this.ecrireConstructeurComplet(pFile);
			
			/* écrit le constructeur complet base. */
			this.ecrireConstructeurCompletBase(pFile);
			
			/* écrit la méthode hashcode(). */
			this.ecrireHashCode(pFile);
			
		} catch (Exception e) {
					
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer les attributs", e);
			}
		}

		
	} // Fin de ecrireCodeHook(...)._______________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesImport() throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/imports_abstractclass.txt";
		
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
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/javadoc_abstractclass.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final String subst 
			= this.genererNomInterface(this.nomSimpleFichierJava);
		
		final List<String> listeLignesProvisoire 
			= this.substituerVariablesDansLigne(
					listeLignes, "{$IObjet}", subst);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignesProvisoire
				, "{$AbstractObjet}", this.nomSimpleFichierJava); // NOPMD by dan on 08/01/18 08:09
		
		this.javadoc = listeLignesSubstitue;
		
		return this.javadoc;
				
	} // Fin de creerLignesJavaDoc(...).___________________________________
	

	
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
			
			stb.append(ABSTRACT_CLASS);
			stb.append(this.nomSimpleFichierJava);
			stb.append(IMPLEMENTS);
			stb.append(this.genererNomInterface(this.nomSimpleFichierJava));
			stb.append(' ');
			stb.append(CROCHET_OUVRANT);
			
			this.ligneDeclaration = stb.toString();
			
			return this.ligneDeclaration;
			
		} // Fin de creerLigneDeclaration(...).________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
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
		stb.append(" // FIN DE LA CLASSE ");
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
		return CLASSE_ECRIVEUR_ABSTRACTCLASS;
	} // Fin de fournirNomClasse().________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirDebutDeclaration() {
		return ABSTRACT_CLASS;
	} // Fin de fournirDebutDeclaration()._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirDebutJavaDoc() {
		return " * CLASSE ABSTRAITE";
	} // Fin de fournirDebutJavaDoc()._____________________________________

	

} // FIN DE LA CLASSE EcriveurAbstractClass.---------------------------------
