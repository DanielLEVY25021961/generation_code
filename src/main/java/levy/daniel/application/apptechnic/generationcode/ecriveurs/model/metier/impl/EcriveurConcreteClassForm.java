package levy.daniel.application.apptechnic.generationcode.ecriveurs.model.metier.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.model.metier.AbstractEcriveurMetier;

/**
 * class EcriveurConcreteClassForm :<br/>
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
public class EcriveurConcreteClassForm extends AbstractEcriveurMetier {

	// ************************ATTRIBUTS************************************/
	/**
	 * CLASSE_ECRIVEUR_CONCRETECLASSFORM : String :<br/>
	 * "Classe EcriveurConcreteClassForm".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_CONCRETECLASSFORM 
		= "Classe EcriveurConcreteClassForm";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(EcriveurConcreteClassForm.class);
	

	// *************************METHODES************************************/

	
	/**
	* method CONSTRUCTEUR EcriveurConcreteClass() :<br/>
	* CONSTRUCTEUR D'ARITE NULLE.<br/>
	*/
	public EcriveurConcreteClassForm() {
		
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
						
	} // Fin de ecrireBlocMethodes(...).___________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesImport() throws Exception {
		
		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/imports_concreteclassform.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
				
		this.imports = listeLignes;
		
		return this.imports;

	} // Fin de creerLignesImport()._______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesJavaDoc(
			final File pFile) throws Exception {

		/* DEBUT. */
		this.creerLignesJavaDocDebut();
		
		
		/* ATTRIBUTS. */
		this.creerLignesJavaDocAttributs();
		

		/* EGALITE METIER. */
		/* en-tête. */
		this.creerLignesJavaDocEnTeteEgaliteMetier();
		
		/* attributs de equals. */
		this.creerLignesJavaDocAttributsEgaliteMetier();

		
		/* DIAGRAMMES. */
		this.creerLignesJavaDocDiagrammes();

		
		/* REGLES DE GESTION. */
		/* en-tête. */
		this.creerLignesJavaDocEnTeteRg();

		/* corps du tableau de RG. */
		this.creerLignesJavaDocTableauRg();
		
		
		/* FIN DE LA JAVADOC. */
		this.creerLignesJavaDocFin();
				
		return this.javadoc;
		
	} // Fin de creerLignesJavaDoc(...).___________________________________

	
	
	/**
	 * method creerLignesJavaDocDebut() :<br/>
	 * <ul>
	 * Crée le début de la Javadoc du fichier :
	 * <li>CONCEPT MODELISE.</li>
	 * <li>HERITAGE.</li>
	 * <li>RESPONSABILITES (SAVOIR-FAIRE).</li>
	 * <li>ATTRIBUTS (SAVOIR).</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private void creerLignesJavaDocDebut() throws Exception {
		
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager
			.getRacineMainResources() 
			+ "/templates/javadoc_concreteclass_debut.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
		
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		final List<String> listeLignesDebutSubst1 
		= this.substituerVariablesDansLigne(
				listeLignesDebut
				, VARIABLE_NOMSIMPLEABSTRACTCLASS
					, this.nomSimpleAbstractClass);
		
		final List<String> listeLignesDebutSubst2 
		= this.substituerVariablesDansLigne(
				listeLignesDebutSubst1
				, VARIABLE_CONCEPT_MODELISE
					, this.conceptModelise);
		
		final List<String> listeLignesDebutSubst3 
		= this.substituerVariablesDansLigne(
				listeLignesDebutSubst2
				, VARIABLE_NOMSIMPLEFICHIERJAVA
					, this.nomSimpleFichierJava); 
		
		this.javadoc.addAll(listeLignesDebutSubst3);

	} // Fin de creerLignesJavaDocDebut()._________________________________
	
	
	
	/**
	 * method creerLignesJavaDocAttributs() :<br/>
	 * <ul>
	 * Crée les lignes ATTRIBUT (SAVOIR) de la Javadoc du fichier :
	 * <li>se colle sous le début de la javadoc du fichier java.</li>
	 * <li>insère une ligne de liste HTML par attribut 
	 * de la map this.mapAttributs.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private void creerLignesJavaDocAttributs() throws Exception {
		return; // NOPMD by daniel.levy on 19/01/18 10:15
	} // Fin de creerLignesJavaDocAttributs()._____________________________
	

	
	/**
	 * method creerLignesJavaDocEnTeteEgaliteMetier() :<br/>
	 * <ul>
	 * <li>EGALITE METIER (en-tête).</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private void creerLignesJavaDocEnTeteEgaliteMetier() 
			throws Exception {
		return; // NOPMD by daniel.levy on 19/01/18 10:15
	} // Fin de creerLignesJavaDocEnTeteEgaliteMetier().___________________
	
	
	
	/**
	 * method creerLignesJavaDocAttributsEgaliteMetier() :<br/>
	 * <ul>
	 * <li>EGALITE METIER (attributs).</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private void creerLignesJavaDocAttributsEgaliteMetier() 
			throws Exception {
		return; // NOPMD by daniel.levy on 19/01/18 10:15
	} // Fin de creerLignesJavaDocAttributsEgaliteMetier().________________
	

	
	/**
	 * method creerLignesJavaDocDiagrammes() :<br/>
	 * <ul>
	 * <li>DIAGRAMMES.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private void creerLignesJavaDocDiagrammes() 
			throws Exception {
		return; // NOPMD by daniel.levy on 19/01/18 10:15
	} // Fin de creerLignesJavaDocDiagrammes().____________________________
	

	
	/**
	 * method creerLignesJavaDocEnTeteRg() :<br/>
	 * <ul>
	 * <li>REGLES DE GESTION (en-tête).</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private void creerLignesJavaDocEnTeteRg() 
			throws Exception {
		return; // NOPMD by daniel.levy on 19/01/18 10:15
	} // Fin de creerLignesJavaDocEnTeteRg().______________________________
	

	
	/**
	 * method creerLignesJavaDocTableauRg() :<br/>
	 * <ul>
	 * <li>REGLES DE GESTION (tableau).</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private void creerLignesJavaDocTableauRg() 
			throws Exception {
		return; // NOPMD by daniel.levy on 19/01/18 10:15
	} // Fin de creerLignesJavaDocTableauRg()._____________________________
	

	
	/**
	 * method creerLignesJavaDocFin() :<br/>
	 * <ul>
	 * <li>FIN DE LA JAVADOC DE NIVEAU FICHIER.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private void creerLignesJavaDocFin() 
			throws Exception {
		
		final String cheminFichierFinJavadoc
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/javadoc_interface_fin.txt";
	
		final File fichierFinJavadoc = new File(cheminFichierFinJavadoc);
		
		final List<String> listeLignesFinJavadoc 
			= this.lireStringsDansFile(fichierFinJavadoc, CHARSET_UTF8);
		
		final List<String> listeLignesFinJavadocSubst1 
			= this.substituerVariablesDansLigne(
					listeLignesFinJavadoc
						, VARIABLE_DATEDUJOUR
							, this.afficherDateDuJour());
		
		this.javadoc.addAll(listeLignesFinJavadocSubst1);

	} // Fin de creerLignesJavaDocFin().___________________________________
	

	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * <ul>
	 * <b>fournirDebutJavaDoc() pour une CLASS CONCRETE FORMULAIRE</b> :
	 * <li>" * CLASSE".</li>
	 * </ul>
	 * <br/>
	 */
	@Override
	protected final String fournirDebutJavaDoc() {
		return " * CLASSE";
	} // Fin de fournirDebutJavaDoc()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesEntity(
			final File pFile) throws Exception {
		return null;
	} // Fin de creerLignesEntity(...).____________________________________

	
	
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

		stb.append(CLASS);
		stb.append(this.nomSimpleFichierJava);
		stb.append(SEP_ESPACE);
		stb.append(CROCHET_OUVRANT);

		this.ligneDeclaration = stb.toString();

		return this.ligneDeclaration;

	} // Fin de creerLigneDeclaration(...).________________________________


		
	/**
	 * {@inheritDoc}
	 * <br/>
	 * <ul>
	 * <b>fournirDebutDeclaration() pour une CLASS</b> :
	 * <li>"public class ".</li>
	 * </ul>
	 * <br/>
	 */
	@Override
	protected final String fournirDebutDeclaration() {
		return CLASS;
	} // Fin de fournirDebutDeclaration()._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerAttributId(
			final List<String> pListe) throws Exception {
		return; // NOPMD by daniel.levy on 17/01/18 11:18	
	} // Fin de creerAttributId(...).______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocAttribut(
			final List<String> pListe
				, final String pNomAttribut
					, final String pTypeAttribut)
			throws Exception {
		
		/* ne fait rien si pListe == null. */
		if (pListe == null) {
			return;
		}
		
		/* ne fait rien si pNomAttribut est blank. */
		if (StringUtils.isBlank(pNomAttribut)) {
			return;
		}
		
		/* DEBUT. */
		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/attribut/javadoc_attribut_debut.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listProv 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> attributListSubst1 
			= this.substituerVariablesDansLigne(
					listProv
					, VARIABLE_NOMATTRIBUT
						, pNomAttribut);
		
		final List<String> attributListSubst2 
		= this.substituerVariablesDansLigne(
				attributListSubst1
					, VARIABLE_TYPEATTRIBUT
						, pTypeAttribut);
		
		/* Liste des lignes à générer. */
		final List<String> attributList 
		= this.substituerVariablesDansLigne(
				attributListSubst2
					, VARIABLE_CONCEPT_MODELISE
						, this.conceptModelise);
		
		pListe.addAll(attributList);
		
		/* CORPS.*/
		this.ajouterRGsAJavadoc(pListe, pNomAttribut);
		
		/* FIN. */
		pListe.add(FIN_JAVADOC_MEMBRE);
		
	} // Fin de creerJavadocAttribut(...)._________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeAttribut(
			final List<String> pListe
				, final String pNomAttribut
					, final String pTypeAttribut) throws Exception {
		
		/* ne fait rien si pListe == null. */
		if (pListe == null) {
			return;
		}
		
		/* ne fait rien si pNomAttribut est blank. */
		if (StringUtils.isBlank(pNomAttribut)) {
			return;
		}
		

		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/attribut/code_attribut.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listProv 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> attributListSubst1 
			= this.substituerVariablesDansLigne(
					listProv
					, VARIABLE_NOMATTRIBUT
						, pNomAttribut + STRING);
		
		final List<String> attributListSubst2 
		= this.substituerVariablesDansLigne(
				attributListSubst1
					, VARIABLE_TYPEATTRIBUT
						, STRING);
		
		pListe.addAll(attributListSubst2);
		
	} // Fin de creerCodeAttribut(...).____________________________________

	
		
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
	@Override
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
			if (this.existLigneCommencant(pFile, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}

			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : listJavadoc) {

				if (StringUtils.isBlank(ligne)) {

					this.ecrireStringDansFile(pFile, "", CHARSET_UTF8, NEWLINE);

				}
				else {

					this.ecrireStringDansFile(pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		}
		catch (Exception e) {

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

		stb.append("this(");

		int compteur2 = 0;

		final int tailleMap = this.mapAttributs.size();

		final Set<Entry<String, String>> entrySet2 = this.mapAttributs.entrySet();

		final Iterator<Entry<String, String>> ite2 = entrySet2.iterator();

		while (ite2.hasNext()) {

			compteur2++;

			ite2.next();

			String aAjouter = null;

			if (compteur2 < tailleMap) {
				if (compteur2 == 1) {
					aAjouter = "null";
				} else {
					aAjouter = ", null";
				}				
			}
			else {
				aAjouter = ", null);";
			}

			stb.append(aAjouter);
		}

		listCode.add(DECALAGE_CODE + stb.toString());

		listCode.add("");

		final String ligneIdentifiant = "\t" + LIGNE_FIN_CONSTR_NULL;

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

				}
				else {

					this.ecrireStringDansFile(pFile, ligne, CHARSET_UTF8, NEWLINE);
				}
			}
		}
		catch (Exception e) {

			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible de créer " + "le code du constructeur", e);
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
	@Override
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
		
		String ligneACreerBase = null;
		
		if (compteur == 1) {
			
			ligneACreerBase 
			= LIGNE_VIDE_JAVADOC_MEMBRE
			+ SEP_ESPACE
			+ STRING 
			+ SEP_ESPACE 
			+ this.fournirParametre(nomAttribut) + STRING;
		}
		else {
			
			ligneACreerBase 
			= VIRGULE_JAVADOC_MEMBRE 
			+ "String" 
			+ SEP_ESPACE 
			+ this.fournirParametre(nomAttribut) + STRING;
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
		
		final String ligneACreer1 
			= LIGNE_PARAM_JAVADOC 
			+ fournirParametre(nomAttribut) + STRING 
			+ SEP_2PTS_AERE 
			+ STRING 
			+ SEP_2PTS_AERE;
		
		final String ligneACreer2 
			= "	 *" 
			+ SEP_ESPACE
			+ nomAttribut + STRING 
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
			
			String ligneACreer = null;
			String ligneACreerBase = null;
			
			if (compteur == 1) {
				
				ligneACreerBase 
					= decalage + FINAL
						+ STRING + SEP_ESPACE 
							+ this.fournirParametre(nomAttribut) + STRING;
								
			} else {
				
				final StringBuffer stb = new StringBuffer();
				
				stb.append(decalage);
				stb.append(TAB);
				
				decalage = stb.toString();
				
				ligneACreerBase 
					= decalage + ", " + FINAL
						+ STRING + SEP_ESPACE 
						+ this.fournirParametre(nomAttribut) + STRING;
				
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
		listCode.add(DECALAGE_CODE + "super();");
		listCode.add("");

		final Set<Entry<String, String>> entrySet2 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite2 = entrySet2.iterator();
		
		while (ite2.hasNext()) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append("this.");
						
			final Entry<String, String> entry2 = ite2.next();
			
			final String nomAttribut = entry2.getKey() + STRING;

			stb.append(nomAttribut);
			stb.append(EGAL);
			stb.append(this.fournirParametre(nomAttribut));
			stb.append(POINT_VIRGULE);
			
			listCode.add(DECALAGE_CODE + stb.toString());
			
		}
				
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
	 * {@inheritDoc}
	 */
	@Override
	protected final void ecrireCodeConstructeurCompletBase(
			final File pFile) throws Exception {		
		return;		 // NOPMD by daniel.levy on 22/01/18 13:07
	} // Fin de ecrireCodeConstructeurCompletBase(...).____________________

	
	
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
	protected final String fournirTypeFichierJava() {	
		return "LA CLASSE ";		
	} // Fin de fournirTypeFichierJava(...)._______________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirNomClasse() {
		return CLASSE_ECRIVEUR_CONCRETECLASSFORM;
	} // Fin de fournirNomClasse().________________________________________



} // FIN DE LA CLASSE EcriveurConcreteClassForm.-----------------------------