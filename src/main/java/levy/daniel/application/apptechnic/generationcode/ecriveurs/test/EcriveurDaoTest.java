package levy.daniel.application.apptechnic.generationcode.ecriveurs.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.AbstractGenerateur;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveurFichiersJavaDetaille;

/**
 * CLASSE <b>EcriveurDaoTest</b> :<br/>
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
 * @since 5 mars 2018
 *
 */
public class EcriveurDaoTest 
		extends AbstractEcriveurFichiersJavaDetaille {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_ECRIVEUR_DAO_TEST : String :<br/>
	 * "Classe EcriveurDaoTest".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_DAO_TEST 
		= "Classe EcriveurDaoTest";
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
				= LogFactory.getLog(EcriveurDaoTest.class);
	
	
	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR EcriveurDaoTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public EcriveurDaoTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesImport() throws Exception {
		
		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/imports_testdao.txt");
		
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
				, VARIABLE_I_DAO
					, AbstractGenerateur.getNomIDao());
		
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
					"dao/javadoc_testdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOMSIMPLEFICHIERJAVA
						, this.nomSimpleFichierJava);
		
		final List<String> listSubst2 
		= this.substituerVariablesDansLigne(
				listSubst1
				, VARIABLE_CONCRETE_DAO
					, AbstractGenerateur.getNomConcreteDao());
		
		final List<String> listSubst3 
		= this.substituerVariablesDansLigne(
			listSubst2
				, VARIABLE_DATEDUJOUR
					, this.afficherDateDuJour());
		
		/* alimentation de this.javadoc. */
		this.javadoc = listSubst3;
		
		return this.javadoc;
		
	} // Fin de creerLignesJavaDoc(...).___________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirDebutJavaDoc() {
		return " * classe";
	} // Fin de fournirDebutJavaDoc()._____________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesEntity(
			final File pFile) throws Exception {
		
		/* Lecture du template. */
		this.entity = this.lireTemplate(
					"dao/entity_testdao.txt");
		
		return this.entity;
		
	} // Fin de creerLignesEntity(...).____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirIdentifiantDebutEntity() {
		return "@RunWith";
	} // Fin de fournirIdentifiantDebutEntity().___________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String creerLigneDeclaration(
			final File pFile) throws Exception {
		
		this.ligneDeclaration 
			= "public class " 
					+ this.nomSimpleFichierJava 
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
		
		this.ecrireAttributsPrincipaux(pFile);
				
		/* écrit l'attribut LOG. */
		this.ecrireAttributLog(pFile);
		
	} // Fin de ecrireBlocAttributs(...).__________________________________
	
	
	
	/**
	 * method ecrireAttributsPrincipaux(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la Javadoc et le code des attributs 
	 * du test JUnit du DAO.</li>
	 * <li>rajoute 3 lignes vides à la suite.</li>
	 * <li>Ne fait rien si le code a déjà été écrit.</li>
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
	private void ecrireAttributsPrincipaux(
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
			= this.fournirIdentifiantDebutAttributsTestDao();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}
		
		
		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/attributs_testdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_I_DAO
							, AbstractGenerateur.getNomIDao());
		
		final List<String> listSubst2 
		= this.substituerVariablesDansLigne(
				listSubst1
					, VARIABLE_CONCRETE_DAO
						, AbstractGenerateur.getNomConcreteDao());
		
		final List<String> listSubst3 
		= this.substituerVariablesDansLigne(
			listSubst2
				, VARIABLE_NOM_CLASSE_METIER
					, this.nomClassMetier);
		
		final List<String> listSubst4 
		= this.substituerVariablesDansLigne(
			listSubst3
				, VARIABLE_NOM_INTERFACE_METIER
					, this.nomInterfaceMetier);
		
		/* ajoute 3 lignes vides sous le code. */
		this.ajouterLignesVides(3, listSubst4);
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listSubst4, pFile);
				
	} // Fin de ecrireAttributsPrincipaux()._______________________________
	
	
	
	/**
	 * method fournirIdentifiantDebutAttributsTestDao() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant les attributs
	 *  d'un test JUnit de DAO pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutAttributsTestDao() {
		
		final String ligne 
			= "	public static final Boolean AFFICHAGE_GENERAL";
		
		return ligne;
		
	} // Fin de fournirIdentifiantDebutAttributsTestDao()._________________


	
	/**
	 * method ecrireBlocMethodes(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li><b>Crée tout le bloc comprenant les methodes</b>.</li>
	 * <ul>
	 * <li>écrit le <b>constructeur d'arité nulle</b>.</li>
	 * <li>écrit la javadoc et le code de la méthode 
	 * <b>avantTests()</b>.</li>
	 * <li>écrit la javadoc et le code de la 
	 * méthode <b>remplirTable()</b></li>
	 * <li>écrit la javadoc et le code de la 
	 * méthode <b>viderTable()</b></li>
	 * <li>écrit la javadoc et le code de la 
	 * méthode <b>afficherDaoNonInstancie()</b></li>
	 * <li>écrit la javadoc et le code de la 
	 * méthode <b>afficherNbreObjetsInitial()</b></li>
	 * <li>écrit la javadoc et le code de la 
	 * méthode <b>afficherObjetPersistant()</b></li>
	 * <li>écrit la javadoc et le code de la 
	 * méthode <b>afficherObjetNonPersistant()</b></li>
	 * <li>écrit la javadoc et le code de la 
	 * méthode <b>afficherAbstractDaoException()</b></li>
	 * <li>écrit la javadoc et le code de la 
	 * méthode <b>fabriquerList()</b></li>
	 * <li></li>
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
		
		/* écrit la javadoc et le code de la méthode avantTests() */
		this.ecrireMethodeAvantTests(pFile);
		
		/* écrit la javadoc et le code de la méthode remplirTable() */
		this.ecrireMethodeRemplirTable(pFile);
		
		/* écrit la javadoc et le code de la méthode viderTable() */
		this.ecrireMethodeViderTable(pFile);
		
		/* écrit la javadoc et le code de la 
		 * méthode afficherDaoNonInstancie() */
		this.ecrireMethodeAfficherDaoNonInstancie(pFile);
		
		/* écrit la javadoc et le code de la 
		 * méthode afficherNbreObjetsInitial() */
		this.ecrireMethodeAfficherNbreObjetsInitial(pFile);

		/* écrit la javadoc et le code de la 
		 * méthode afficherObjetPersistant() */
		this.ecrireMethodeAfficherObjetPersistant(pFile);
		
		/* écrit la javadoc et le code de la 
		 * méthode afficherObjetNonPersistant() */
		this.ecrireMethodeAfficherObjetNonPersistant(pFile);
		
		/* écrit la javadoc et le code de la 
		 * méthode afficherAbstractDaoException() */
		this.ecrireMethodeAfficherAbstractDaoException(pFile);
		
		/* écrit la javadoc et le code de la 
		 * méthode fabriquerList() */
		this.ecrireMethodeFabriquerList(pFile);
				
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
	 * method ecrireMethodeAvantTests() :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la méthode <b>avantTests()</b></li>
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
	private void ecrireMethodeAvantTests(
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
			= this.fournirStringIdentitfiantAvantTest();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_avanttests_testconcretedao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOM_CLASSE_METIER
						, this.nomClassMetier);
		
		/* ajoute 3 lignes vides sous le code. */
		this.ajouterLignesVides(3, listSubst1);
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listSubst1, pFile);
		
	} // Fin de ecrireMethodeAvantTests()._________________________________
	

	
	/**
	 * method fournirStringIdentitfiantAvantTest() :<br/>
	 * <ul>
	 * <li>fournit une String identifiant la méthode avantTests().</li>
	 * <li>permet de ne pas générer 2 fois le code.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 */
	private String fournirStringIdentitfiantAvantTest() {
		return "    public static void avantTests";
	} // Fin de fournirStringIdentitfiantAvantTest().______________________
	

	
	/**
	 * method ecrireMethodeRemplirTable() :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la méthode <b>remplirTable()</b></li>
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
	private void ecrireMethodeRemplirTable(
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
			= this.fournirStringIdentitfiantRemplirTable();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_remplirtable_testdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);
		
		final List<String> listSubst2 
			= this.substituerVariablesDansLigne(
				listSubst1
					, VARIABLE_NOM_CLASSE_METIER
						, this.nomClassMetier);
		
		final List<String> listSubst3 
			= this.substituerVariablesDansLigne(
				listSubst2
					, VARIABLE_NOM_ABSTRACT_TABLE
						, AbstractGenerateur.getNomAbstractTable());
		
		final List<String> listSubst4 
			= this.substituerVariablesDansLigne(
				listSubst3
					, VARIABLE_NOM_CONCRETE_TABLE
						, AbstractGenerateur.getNomConcreteTable());
		
		/* ajoute 3 lignes vides sous le code. */
		this.ajouterLignesVides(3, listSubst4);
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listSubst4, pFile);
		
	} // Fin de ecrireMethodeRemplirTable()._______________________________
	

	
	/**
	 * method fournirStringIdentitfiantRemplirTable() :<br/>
	 * <ul>
	 * <li>fournit une String identifiant la méthode remplirTable().</li>
	 * <li>permet de ne pas générer 2 fois le code.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 */
	private String fournirStringIdentitfiantRemplirTable() {
		return DECALAGE_METHODE + "private void remplirTable";
	} // Fin de fournirStringIdentitfiantRemplirTable().___________________
	

		
	/**
	 * method ecrireMethodeViderTable() :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la méthode <b>viderTable()</b></li>
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
	private void ecrireMethodeViderTable(
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
			= this.fournirStringIdentitfiantViderTable();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_vidertable_testdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOM_CLASSE_METIER
						, this.nomClassMetier);
				
		/* ajoute 3 lignes vides sous le code. */
		this.ajouterLignesVides(3, listSubst1);
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listSubst1, pFile);
		
	} // Fin de ecrireMethodeViderTable()._________________________________
	

	
	/**
	 * method fournirStringIdentitfiantViderTable() :<br/>
	 * <ul>
	 * <li>fournit une String identifiant la méthode viderTable().</li>
	 * <li>permet de ne pas générer 2 fois le code.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 */
	private String fournirStringIdentitfiantViderTable() {
		return DECALAGE_METHODE + "private void viderTable";
	} // Fin de fournirStringIdentitfiantViderTable()._____________________
	

	
	/**
	 * method ecrireMethodeAfficherDaoNonInstancie() :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la méthode <b>afficherDaoNonInstancie()</b></li>
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
	private void ecrireMethodeAfficherDaoNonInstancie(
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
			= this.fournirStringIdentitfiantAfficherDaoNonInstancie();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_afficherdaononinstancie_testdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOM_CLASSE_METIER
						, this.nomClassMetier);
				
		/* ajoute 3 lignes vides sous le code. */
		this.ajouterLignesVides(3, listSubst1);
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listSubst1, pFile);
		
	} // Fin de ecrireMethodeAfficherDaoNonInstancie().____________________
	

	
	/**
	 * method fournirStringIdentitfiantAfficherDaoNonInstancie() :<br/>
	 * <ul>
	 * <li>fournit une String identifiant la méthode afficherDaoNonInstancie().</li>
	 * <li>permet de ne pas générer 2 fois le code.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 */
	private String fournirStringIdentitfiantAfficherDaoNonInstancie() {
		return DECALAGE_METHODE + "private void afficherDAONonInstancie";
	} // Fin de fournirStringIdentitfiantAfficherDaoNonInstancie().________
	

	
	/**
	 * method ecrireMethodeAfficherNbreObjetsInitial() :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la méthode <b>afficherNbreObjetsInitial()</b></li>
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
	private void ecrireMethodeAfficherNbreObjetsInitial(
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
			= this.fournirStringIdentitfiantAfficherNbreObjetsInitial();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_affichernbreobjetsinitial_testdao.txt");
		
		/* substitutions. */

				
		/* ajoute 3 lignes vides sous le code. */
		this.ajouterLignesVides(3, listeLignes);
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeLignes, pFile);
		
	} // Fin de ecrireMethodeAfficherNbreObjetsInitial().__________________
	

	
	/**
	 * method fournirStringIdentitfiantAfficherNbreObjetsInitial() :<br/>
	 * <ul>
	 * <li>fournit une String identifiant la méthode afficherNbreObjetsInitial().</li>
	 * <li>permet de ne pas générer 2 fois le code.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 */
	private String fournirStringIdentitfiantAfficherNbreObjetsInitial() {
		return DECALAGE_METHODE + "private void afficherNbreObjetsInitial";
	} // Fin de fournirStringIdentitfiantAfficherNbreObjetsInitial().______


	
	/**
	 * method ecrireMethodeAfficherObjetPersistant() :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la méthode <b>afficherObjetPersistant()</b></li>
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
	private void ecrireMethodeAfficherObjetPersistant(
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
			= this.fournirStringIdentitfiantAfficherObjetPersistant();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_afficherobjetpersistant_testdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);
				
		/* ajoute 3 lignes vides sous le code. */
		this.ajouterLignesVides(3, listSubst1);
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listSubst1, pFile);
		
	} // Fin de ecrireMethodeAfficherObjetPersistant().____________________
	

	
	/**
	 * method fournirStringIdentitfiantAfficherObjetPersistant() :<br/>
	 * <ul>
	 * <li>fournit une String identifiant la méthode afficherObjetPersistant().</li>
	 * <li>permet de ne pas générer 2 fois le code.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 */
	private String fournirStringIdentitfiantAfficherObjetPersistant() {
		return DECALAGE_METHODE + "private void afficherObjetPersistant";
	} // Fin de fournirStringIdentitfiantAfficherObjetPersistant().________


	
	/**
	 * method ecrireMethodeAfficherObjetNonPersistant() :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la méthode <b>afficherObjetNonPersistant()</b></li>
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
	private void ecrireMethodeAfficherObjetNonPersistant(
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
			= this.fournirStringIdentitfiantAfficherObjetNonPersistant();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_afficherobjetnonpersistant_testdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);
				
		/* ajoute 3 lignes vides sous le code. */
		this.ajouterLignesVides(3, listSubst1);
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listSubst1, pFile);
		
	} // Fin de ecrireMethodeAfficherObjetNonPersistant().____________________
	

	
	/**
	 * method fournirStringIdentitfiantAfficherObjetNonPersistant() :<br/>
	 * <ul>
	 * <li>fournit une String identifiant la méthode afficherObjetNonPersistant().</li>
	 * <li>permet de ne pas générer 2 fois le code.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 */
	private String fournirStringIdentitfiantAfficherObjetNonPersistant() {
		return DECALAGE_METHODE + "private void afficherObjetNonPersistant";
	} // Fin de fournirStringIdentitfiantAfficherObjetNonPersistant().________


	
	/**
	 * method ecrireMethodeAfficherAbstractDaoException() :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la méthode <b>afficherAbstractDaoException()</b></li>
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
	private void ecrireMethodeAfficherAbstractDaoException(
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
			= this.fournirStringIdentitfiantAfficherAbstractDaoException();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_afficherabstractdaoexception_testdao.txt");
		
		/* substitutions. */
				
		/* ajoute 3 lignes vides sous le code. */
		this.ajouterLignesVides(3, listeLignes);
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeLignes, pFile);
		
	} // Fin de ecrireMethodeAfficherAbstractDaoException()._______________
	

	
	/**
	 * method fournirStringIdentitfiantAfficherAbstractDaoException() :<br/>
	 * <ul>
	 * <li>fournit une String identifiant la méthode afficherAbstractDaoException().</li>
	 * <li>permet de ne pas générer 2 fois le code.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 */
	private String fournirStringIdentitfiantAfficherAbstractDaoException() {
		return DECALAGE_METHODE + "private void afficherAbstractDaoException";
	} // Fin de fournirStringIdentitfiantAfficherAbstractDaoException().___


	
	/**
	 * method ecrireMethodeFabriquerList() :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la méthode <b>fabriquerList()</b></li>
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
	private void ecrireMethodeFabriquerList(
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
			= this.fournirStringIdentitfiantFabriquerList();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_fabriquerlist_testdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);
				
		/* ajoute 3 lignes vides sous le code. */
		this.ajouterLignesVides(3, listSubst1);
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listSubst1, pFile);
		
	} // Fin de ecrireMethodeFabriquerList().____________________
	

	
	/**
	 * method fournirStringIdentitfiantFabriquerList() :<br/>
	 * <ul>
	 * <li>fournit une String identifiant la méthode fabriquerList().</li>
	 * <li>permet de ne pas générer 2 fois le code.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 */
	private String fournirStringIdentitfiantFabriquerList() {
		return DECALAGE_METHODE + "public final List<" 
				+ this.nomInterfaceMetier + "> fabriquerList";
	} // Fin de fournirStringIdentitfiantFabriquerList().________


	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * retourne pour un EcriveurDaoTest : 
	 * "LA CLASSE ".<br/>
	 * <br/>
	 */
	@Override
	protected final String fournirTypeFichierJava() {	
		return "LA CLASSE ";		
	} // Fin de fournirTypeFichierJava(...)._______________________________
	

	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * retourne pour un EcriveurDaoTest : 
	 * "Classe EcriveurMetierTest".<br/>
	 * <br/>
	 */
	@Override
	protected final String fournirNomClasse() {
		return CLASSE_ECRIVEUR_DAO_TEST;
	} // Fin de fournirNomClasse().________________________________________


	
} // FIN DE LA CLASSE EcriveurDaoTest.---------------------------------------
