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
		
		this.ecrireJavaDoc(pFile);

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
	protected List<String> creerLignesJavaDoc(
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
		// TODO Auto-generated method stub
		return null;
	}

	
	
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
		return "* CLASSE ABSTRAITE";
	} // Fin de fournirDebutJavaDoc()._____________________________________

	

} // FIN DE LA CLASSE EcriveurAbstractClass.---------------------------------
