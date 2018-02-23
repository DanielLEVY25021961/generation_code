package levy.daniel.application.apptechnic.generationcode.ecriveurs.model.metier;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.IGenerateur;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveurFichiersJavaDetaille;


/**
 * class AbstractEcriveurMetier :<br/>
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
 * @since 22 févr. 2018
 *
 */
public abstract class AbstractEcriveurMetier 
		extends AbstractEcriveurFichiersJavaDetaille {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ABSTRACT_ECRIVEUR_METIER : String :<br/>
	 * "Classe AbstractEcriveurMetier".<br/>
	 */
	public static final String CLASSE_ABSTRACT_ECRIVEUR_METIER 
		= "Classe AbstractEcriveurMetier";
	

	/**
	 * entity : List&lt;String&gt; :<br/>
	 * entity du fichier Java.<br/>
	 */
	protected transient List<String> entity;
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(AbstractEcriveurMetier.class);


	// *************************METHODES************************************/
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void ecrireCodeGenerique(
			final File pFile
				, final IGenerateur pGenerateur) {
		
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
	 * {@inheritDoc}
	 */
	@Override
	protected List<String> creerLignesImport() throws Exception {

		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<String> creerLignesJavaDoc(File pFile) throws Exception {

		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirDebutJavaDoc() {

		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<String> creerLignesEntity(File pFile) throws Exception {

		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String creerLigneDeclaration(File pFile) {

		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirDebutDeclaration() {

		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void ecrireCodeHook(File pFile) {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerAttributId(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocAttribut(List<String> pListe, String pNomAttribut, String pTypeAttribut)
			throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeAttribut(List<String> pListe, String pNomAttribut, String pTypeAttribut) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void ecrireCodeConstructeurCompletBase(File pFile) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocCompareTo(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeCompareTo(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifiantCompareTo() {

		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocClone(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeClone(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocToString(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeToString(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetEnTeteCsv(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetEnTeteCsv(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocToStringCsv(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeToStringCsv(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetEnTeteColonne(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetEnTeteColonne(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetValeurColonne(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetValeurColonne(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetId(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerEntityGetId(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetId(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocSetId(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeSetId(List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetter(String pNomAttribut, String pTypeAttribut, List<String> pListe) throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeEntityGetter(String pNomAttribut, String pTypeAttribut, List<String> pListeGetter)
			throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetter(String pNomAttribut, String pTypeAttribut, List<String> pListeGetter)
			throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifianteGetter(String pNomAttribut, String pTypeAttribut) {

		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocSetter(String pNomAttribut, String pTypeAttribut, List<String> pListeSetter)
			throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeSetter(String pNomAttribut, String pTypeAttribut, List<String> pListeSetter)
			throws Exception {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifianteSetter(String pNomAttribut, String pTypeAttribut) {

		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirTypeFichierJava() {

		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirNomClasse() {
		return CLASSE_ABSTRACT_ECRIVEUR_METIER;
	} // Fin de fournirNomClasse().________________________________________
	

	
}
