package levy.daniel.application.apptechnic.generationcode.ecriveurs.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveur;

/**
 * class EcriveurConcreteClass :<br/>
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
 * @since 18 janv. 2018
 *
 */
public class EcriveurConcreteClass extends AbstractEcriveur {

	// ************************ATTRIBUTS************************************/
	/**
	 * CLASSE_ECRIVEUR_CONCRETECLASS : String :<br/>
	 * "Classe EcriveurConcreteClass".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_CONCRETECLASS 
		= "Classe EcriveurConcreteClass";

	
	/**
	 * CLASS : String :<br/>
	 * "public class ".<br/>
	 */
	public static final String CLASS 
		= "public class ";

	
	/**
	 * IMPLEMENTS : String :<br/>
	 * " implements ".<br/>
	 */
	public static final String IMPLEMENTS = " implements ";
	
	
	/**
	 * EXTENDS : String :<br/>
	 * " extends ".<br/>
	 */
	public static final String EXTENDS = " extends ";
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(EcriveurConcreteClass.class);
	

	// *************************METHODES************************************/

	
	/**
	* method CONSTRUCTEUR EcriveurConcreteClass() :<br/>
	* CONSTRUCTEUR D'ARITE NULLE.<br/>
	*/
	public EcriveurConcreteClass() {
		
		super();
				
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void ecrireCodeHook(
			final File pFile) {
		
		try {
			
			/* crée tout le bloc comprenant les ATTRIBUTS. */
			this.ecrireBlocAttributs(pFile);
			
		} catch (Exception e1) {
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible d'écrire les attributs", e1);
			}
			
		}
				
		/* écrit le séparateur methodes. */
		this.ecrireLignesSepMethodes(pFile);
				
		try {
			
			/* crée tout le bloc comprenant les METHODES. */
			this.ecrireBlocMethodes(pFile);
			
		} catch (Exception e) {
					
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible d'écrire les méthodes", e);
			}
		}
		
	} // Fin de ecrireCodeHook(...)._______________________________________

	
	
	/**
	 * method ecrireBlocAttributs(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li><b>Crée tout le bloc comprenant les attributs</b>.</li>
	 * <ul>
	 * <li>écrit le séparateur attributs.</li>
	 * <li>écrit la stringClasse.</li>
	 * <li>écrit attributId.</li>
	 * <li>écrit tous les attributs.</li>
	 * </ul>
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
	private void ecrireBlocAttributs(
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

		/* écrit le séparateur attributs. */
		this.ecrireLignesSepAttributs(pFile);
		
		/* écrit la stringClasse. */
		this.ecrireLignesStringClasse(pFile);
		
		final String derniereLigneStringClasse 
			= this.fournirDerniereLigneListe(this.stringClasse);
		
		/* insère 2 lignes sous stringClasse. */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneStringClasse, 2, CHARSET_UTF8);
		
		/* écrit attributId. */
		this.ecrireLignesAttributId(pFile);
		
		
		try {
			
			/* écrit tous les attributs. */
			this.ecrireAttributs(pFile);
			
		} catch (Exception e) {
					
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer les attributs", e);
			}
		}
		
	} // Fin de creerAttributs(...)._______________________________________
	

	
	/**
	 * method ecrireBlocMethodes(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li><b>Crée tout le bloc comprenant les methodes</b>.</li>
	 * <ul>
	 * <li>écrit le constructeur d'arité nulle.</li>
	 * <li>écrit le constructeur complet.</li>
	 * <li>écrit le constructeur complet base.</li>
	 * <li>écrit la méthode hashcode().</li>
	 * <li>écrit la méthode equals().</li>
	 * <li>écrit la méthode compareTo().</li>
	 * </ul>
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
	private void ecrireBlocMethodes(
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

		/* écrit le constructeur d'arité nulle. */
		this.ecrireConstructeurNull(pFile);
		
		/* écrit le constructeur complet. */
		this.ecrireConstructeurComplet(pFile);
		
		/* écrit le constructeur complet base. */
		this.ecrireConstructeurCompletBase(pFile);
		
		/* écrit la méthode hashcode(). */
		this.ecrireHashCode(pFile);
		
		/* écrit la méthode equals(). */
		this.ecrireEquals(pFile);
		
		/* écrit la méthode compareTo(). */
		this.ecrireCompareTo(pFile);
		
		/* écrit la méthode clone(). */
		this.ecrireClone(pFile);
		
		/* écrit la méthode toString(). */
		this.ecrireToString(pFile);
		
		/* écrit la méthode getEnTeteCsv(). */
		this.ecrireGetEnTeteCsv(pFile);
		
		/* écrit la méthode toStringCsv(). */
		this.ecrireToStringCsv(pFile);
		
		/* écrit la méthode getEnTeteColonne(). */
		this.ecrireGetEnTeteColonne(pFile);
		
		/* écrit la méthode getValeurColonne(). */
		this.ecrireGetValeurColonne(pFile);
		
		/* écrit la méthode getId(). */
		this.ecrireGetId(pFile);
		
		/* écrit la méthode setId(). */
		this.ecrireSetId(pFile);
		
		/* écrit les getters-setters. */
		this.ecrireAccesseurs(pFile);
		
	} // Fin de ecrireBlocMethodes(...).___________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesImport() throws Exception {
		
		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/imports_concreteclass.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_PATHMETIER, this.pathMetier);
		
		final List<String> listeLignesSubst2 
			= this.substituerVariablesDansLigne(
				listeLignesSubst1
					, VARIABLE_NOMSIMPLEABSTRACTCLASS
						, this.nomSimpleAbstractClass);
		
		final List<String> listeLignesSubst3 
		= this.substituerVariablesDansLigne(
			listeLignesSubst2
				, VARIABLE_NOMSIMPLEINTERFACE
					, this.nomSimpleInterface);
		
		this.imports = listeLignesSubst3;
		
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
	protected final List<String> creerLignesEntity(
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
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String creerLigneFinale(
			final File pFile) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerAttributId(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocAttribut(
			final List<String> pListe
				, final String pNomAttribut
					, final String pTypeAttribut)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeAttribut(
			final List<String> pListe
				, final String pNomAttribut
					, final String pTypeAttribut) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void ecrireCodeConstructeurCompletBase(
			final File pFile) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocCompareTo(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeCompareTo(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirLigneIdentifiantCompareTo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocClone(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeClone(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocToString(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeToString(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetEnTeteCsv(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetEnTeteCsv(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocToStringCsv(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeToStringCsv(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetEnTeteColonne(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetEnTeteColonne(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetValeurColonne(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetValeurColonne(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetId(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerEntityGetId(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetId(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocSetId(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeSetId(
			final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeEntityGetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListeGetter)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListeGetter)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirLigneIdentifianteGetter(
			final String pNomAttribut
				, final String pTypeAttribut) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocSetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListeSetter)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeSetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListeSetter)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirLigneIdentifianteSetter(
			final String pNomAttribut
				, final String pTypeAttribut) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirNomClasse() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirDebutDeclaration() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirDebutJavaDoc() {
		// TODO Auto-generated method stub
		return null;
	}

	// *************************METHODES************************************/
}
