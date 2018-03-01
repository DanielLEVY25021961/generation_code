package levy.daniel.application.apptechnic.generationcode.ecriveurs.model.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveurFichiersJavaDetaille;

/**
 * classe <b>EcriveurDaoConcrete</b> :<br/>
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
 * @since 28 févr. 2018
 *
 */
public class EcriveurDaoConcrete 
	extends AbstractEcriveurFichiersJavaDetaille {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ECRIVEUR_DAO_CONCRETE : String :<br/>
	 * "Classe EcriveurDaoConcrete".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_DAO_CONCRETE 
		= "Classe EcriveurDaoConcrete";
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(EcriveurDaoConcrete.class);

	
	// *************************METHODES************************************/
	
	
	
	 /**
	 * method CONSTRUCTEUR EcriveurDaoConcrete() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public EcriveurDaoConcrete() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesImport() 
										throws Exception {
		
		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/imports_concreteclass_concretedao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_PATH_REL_COUCHE_JAVA_STRING
							, this.pathRelCoucheJavaString);
		
		final List<String> listSubst2 
			= this.substituerVariablesDansLigne(
				listSubst1
					, VARIABLE_PATH_REL_SOUS_COUCHE_JAVA_STRING
						, this.pathRelSousCoucheJavaString);
		
		final List<String> listSubst3 
			= this.substituerVariablesDansLigne(
				listSubst2
					, VARIABLE_NOMSIMPLEABSTRACTCLASS
						, this.nomSimpleAbstractClass);
		
		final List<String> listSubst4 
			= this.substituerVariablesDansLigne(
				listSubst3
					, VARIABLE_PATH_REL_CONCEPT
						, this.pathRelConceptString);

		final List<String> listSubst5 
			= this.substituerVariablesDansLigne(
				listSubst4
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);

		final List<String> listSubst6 
			= this.substituerVariablesDansLigne(
				listSubst5
					, VARIABLE_PATH_REL_CONCEPT_IMPL
						, this.pathRelConceptImplString);
		
		final List<String> listSubst7 
			= this.substituerVariablesDansLigne(
				listSubst6
					, VARIABLE_NOM_CLASSE_METIER
						, this.nomClassMetier);

		/* alimentation de this.imports. */
		this.imports = listSubst7;
		
		return this.imports;
		
	} // Fin de creerLignesImport()._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesJavaDoc(
			final File pFile) throws Exception {
		
		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate(
					"dao/javadoc_concreteclass_concretdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOMSIMPLEFICHIERJAVA
						, this.nomSimpleFichierJava);
		
		final List<String> listSubst2 
			= this.substituerVariablesDansLigne(
				listSubst1
					, VARIABLE_CONCEPT_MODELISE
						, this.conceptModelise);

		final List<String> listSubst3 
			= this.substituerVariablesDansLigne(
				listSubst2
					, VARIABLE_NOMSIMPLEABSTRACTCLASS
						, this.nomSimpleAbstractClass);
		
		final List<String> listSubst4 
			= this.substituerVariablesDansLigne(
				listSubst3
					, VARIABLE_NOM_CLASSE_METIER
						, this.nomClassMetier);

		
		final List<String> listSubst5 
			= this.substituerVariablesDansLigne(
				listSubst4
					, VARIABLE_DATEDUJOUR
						, this.afficherDateDuJour());
		
		/* alimentation de this.javadoc. */
		this.javadoc = listSubst5;
		
		return this.javadoc;
		
	} // Fin de creerLignesJavaDoc(...).___________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirDebutJavaDoc() {
		return " * CLASSE CONCRETE";
	} // Fin de fournirDebutJavaDoc()._____________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesEntity(
			final File pFile) throws Exception {
				
		this.entity = new ArrayList<String>();
		
		final String repository 
			= "@Repository(value=\"" + this.nomSimpleFichierJava + "\")";
	
		this.entity.add(repository);
		
		return this.entity;
		
	} // Fin de creerLignesEntity(...).____________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String creerLigneDeclaration(
			final File pFile) throws Exception {
		
		this.ligneDeclaration 
			= "public class " 
					+ this.nomSimpleFichierJava 
						+ " extends " 
							+ this.nomSimpleAbstractClass 
								+ " {";
				
		return this.ligneDeclaration;
		
	} // Fin de creerLigneDeclaration(...).________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirDebutDeclaration() {
		return CLASS;
	} // Fin de fournirDebutDeclaration()._________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void ecrireCodeHook(
			final File pFile) {
		
		try {
			
			/* crée tout le bloc comprenant les ATTRIBUTS. */
			this.ecrireBlocAttributs(pFile);
			
		} catch (Exception e) {
					
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible d'écrire les attributs", e);
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
	 * <li>écrit l'attribut LOG.</li>
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
				
		/* écrit l'attribut LOG. */
		this.ecrireAttributLog(pFile);
		
	} // Fin de ecrireBlocAttributs(...).__________________________________
	
	
	
	/**
	 * method ecrireBlocMethodes(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li><b>Crée tout le bloc comprenant les methodes</b>.</li>
	 * <ul>
	 * <li>écrit le constructeur d'arité nulle.</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode findById(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode findAllSousCLasse(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode findAll...(...).</li>
	 * 
	 * <li>écrit la Javadoc et le code de la 
	 * méthode deleteById(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode deleteByIdBoolean(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode exists(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode afficherListe(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode renseignerClassObjetMetier(...).</li>
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
								
		/* écrit la Javadoc et le code de la 
		 * méthode findById(...). */
		this.ecrireMethodeFindById(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode findAllSousCLasse(...). */
		this.ecrireMethodeFindAllSousClasse(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode findAllPrivate(...). */
		this.ecrireMethodeFindAllPrivate(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode deleteById(...). */
//		this.ecrireMethodeDeleteById(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode deleteByIdBoolean(...). */
//		this.ecrireMethodeDeleteByIdBoolean(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode exists(...). */
//		this.ecrireMethodeExists(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode exists(Long..). */
//		this.ecrireMethodeExistsLong(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode afficherListe(...). */
//		this.ecrireMethodeAfficherListe(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode renseignerClassObjetMetier(...). */
//		this.ecrireMethodeRenseignerClassObjetMetier(pFile);
		
	} // Fin de ecrireBlocMethodes(...).___________________________________
	

		
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
	protected final void ecrireConstructeurNull(
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
			this.ecrireCode(listJavadoc, pFile);
			
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
	* <li>rajoute 3 lignes vides à la suite.</li>
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
			
		/* CODE du Constructeur. */
		final StringBuilder stb = new StringBuilder();
		
		stb.append("super();");
				
		listCode.add(DECALAGE_CODE + stb.toString());
				
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
			this.ecrireCode(listCode, pFile);
			
		} catch (Exception e) {
		
			if (LOG.isFatalEnabled()) {
				LOG.fatal(
						"Impossible de créer "
						+ "le code du constructeur d'arité nulle", e);
			}
		}
		
	} // Fin de ecrireCodeConstructeurNull(...).____________________
	

	
	/**
	 * method ecrireMethodeFindById(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode findById(...).</li>
	 * <li>rajoute 3 lignes vides à la suite.</li>
	 * <li>Ne fait rien si la méthode a déjà été écrite.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void ecrireMethodeFindById(
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

		/* Recherche la ligne identifiant. */
		final String ligneIdentifiant 
			= this.fournirIdentifiantDebutMethodeFindById();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate(
					"dao/methode_findbyid_concretedao.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);
		
		final List<String> listeSubst2 
			= this.substituerVariablesDansLigne(
					listeSubst1
						, VARIABLE_NOM_CLASSE_METIER
							, this.nomClassMetier);

		final List<String> listeSubst3 
			= this.substituerVariablesDansLigne(
				listeSubst2
					, VARIABLE_NOMCLASSE
						, this.fabriquerNomClasse(
								this.nomSimpleFichierJava));

		/* ajoute 3 lignes vides sous la méthode. */
		this.ajouterLignesVides(3, listeSubst3);
		
	
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst3, pFile);
		
	} // Fin de ecrireMethodeFindById(...).________________________________
	

	
	/**
	 * method fournirIdentifiantDebutMethodeFindById() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant la méthode 
	 * findById(...) pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutMethodeFindById() {
		return "	public " + this.nomInterfaceMetier + " findById";
	} // Fin de fournirIdentifiantDebutMethodeFindById().__________________


		
	/**
	 * method ecrireMethodeFindAllSousClasse(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode findAllSousClasse(...).</li>
	 * <li>rajoute 3 lignes vides à la suite.</li>
	 * <li>Ne fait rien si la méthode a déjà été écrite.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void ecrireMethodeFindAllSousClasse(
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

		/* Recherche la ligne identifiant. */
		final String ligneIdentifiant 
			= this.fournirIdentifiantDebutMethodeFindAllSousClasse();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate(
					"dao/methode_findallsousclasse_concretedao.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);
		
		final List<String> listeSubst2 
			= this.substituerVariablesDansLigne(
					listeSubst1
						, VARIABLE_NOM_CLASSE_METIER
							, this.nomClassMetier);

		/* ajoute 3 lignes vides sous la méthode. */
		this.ajouterLignesVides(3, listeSubst2);
		
	
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst2, pFile);
		
	} // Fin de ecrireMethodeFindAllSousClasse(...).________________________________
	

	
	/**
	 * method fournirIdentifiantDebutMethodeFindAllSousClasse() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant la méthode 
	 * findAllSousClasse(...) pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutMethodeFindAllSousClasse() {
		
		final String ligne 
			= DECALAGE_METHODE 
			+ "public final List<" 
					+ this.nomInterfaceMetier 
						+ "> findAllSousClasse";
		
		return ligne;
		
	} // Fin de fournirIdentifiantDebutMethodeFindAllSousClasse().__________________


	
	/**
	 * method ecrireMethodeFindAllPrivate(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode findAll...(...).</li>
	 * <li>rajoute 3 lignes vides à la suite.</li>
	 * <li>Ne fait rien si la méthode a déjà été écrite.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void ecrireMethodeFindAllPrivate(
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

		/* Recherche la ligne identifiant. */
		final String ligneIdentifiant 
			= this.fournirIdentifiantDebutMethodeFindAllPrivate();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate(
					"dao/methode_findall_concretedao.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);
		
		final List<String> listeSubst2 
			= this.substituerVariablesDansLigne(
					listeSubst1
						, VARIABLE_NOM_CLASSE_METIER
							, this.nomClassMetier);

		final List<String> listeSubst3 
			= this.substituerVariablesDansLigne(
				listeSubst2
					, VARIABLE_NOMCLASSE
						, this.fabriquerNomClasse(
								this.nomSimpleFichierJava));

		/* ajoute 3 lignes vides sous la méthode. */
		this.ajouterLignesVides(3, listeSubst3);
		
	
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst3, pFile);
		
	} // Fin de ecrireMethodeFindAllPrivate(...).__________________________
	

	
	/**
	 * method fournirIdentifiantDebutMethodeFindAllPrivate() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant la méthode 
	 * findAll...(...) pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutMethodeFindAllPrivate() {
		
		final String ligne 
			= DECALAGE_METHODE 
				+ "private List<" 
					+ this.nomInterfaceMetier 
						+ "> findAll" 
						+ this.nomClassMetier;
		
		return ligne;
		
	} // Fin de fournirIdentifiantDebutMethodeFindAllPrivate().____________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirTypeFichierJava() {
		return "LA CLASSE ";		
	} // Fin de fournirTypeFichierJava(...)._______________________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirNomClasse() {
		return CLASSE_ECRIVEUR_DAO_CONCRETE;
	} // Fin de fournirNomClasse().________________________________________


	
} // FIN DE LA CLASSE EcriveurDaoConcrete.-----------------------------------
