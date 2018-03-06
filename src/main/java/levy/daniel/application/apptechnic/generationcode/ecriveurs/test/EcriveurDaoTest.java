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
	 * <li>écrit le constructeur d'arité nulle.</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode findById(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode findAllSousCLasse(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode findAll...(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode findAllMaxSousCLasse(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode findAllMax...(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode deleteAllSousCLasse(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode deleteAll...(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode deleteAllBooleanSousCLasse(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode deleteAllBoolean...(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode countSousClasse(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode count...(...).</li>
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
