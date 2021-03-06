package levy.daniel.application.model.services.metier.generationcode.ecriveurs.model.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.generationcode.ecriveurs.AbstractEcriveurFichiersJavaDetaille;



/**
 * classe <b>EcriveurDaoAbstract</b> :<br/>
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
 * @since 24 févr. 2018
 *
 */
public class EcriveurDaoAbstract 
	extends AbstractEcriveurFichiersJavaDetaille {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ECRIVEUR_DAO_ABSTRACT : String :<br/>
	 * "Classe EcriveurDaoAbstract".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_DAO_ABSTRACT 
		= "Classe EcriveurDaoAbstract";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(EcriveurDaoAbstract.class);


	// *************************METHODES************************************/

	
	
	
	 /**
	 * method CONSTRUCTEUR EcriveurDaoAbstract() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public EcriveurDaoAbstract() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesImport() throws Exception {
		
		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/imports_abstractclass_abstractdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_PATH_REL_COUCHE_JAVA_STRING
							, this.pathRelCoucheJavaString);
		
		final List<String> listSubst2 
			= this.substituerVariablesDansLigne(
				listSubst1
					, VARIABLE_PATH_REL_CONCEPT
						, this.pathRelConceptString);
		
		final List<String> listSubst3 
			= this.substituerVariablesDansLigne(
				listSubst2
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);
		
		/* alimentation de this.imports. */
		this.imports = listSubst3;
		
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
			= this.lireTemplate("dao/javadoc_abstractclass_abstractdao.txt");
		
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
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);
		
		final List<String> listSubst4 
			= this.substituerVariablesDansLigne(
				listSubst3
					, VARIABLE_NOMSIMPLEINTERFACE
						, this.nomSimpleInterface);

		
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
	protected final String fournirDebutJavaDoc() {
		return " * CLASSE ABSTRAITE";
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
	protected final String fournirIdentifiantDebutEntity() {
		return null;
	} // Fin de fournirIdentifiantDebutEntity().___________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String creerLigneDeclaration(
			final File pFile) throws Exception {
		
		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate(
					"dao/declaration_abstractclass_abstractdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOMSIMPLEFICHIERJAVA
						, this.nomSimpleFichierJava);
		
		final List<String> listSubst2 
			= this.substituerVariablesDansLigne(
				listSubst1
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);
		
		final List<String> listSubst3 
			= this.substituerVariablesDansLigne(
				listSubst2
					, VARIABLE_NOMSIMPLEINTERFACE
						, this.nomSimpleInterface);

		final String declaration 
			= this.retournerStringAPartirDeListe(listSubst3);
		
		this.ligneDeclaration = declaration;
		
		return this.ligneDeclaration;
		
	} // Fin de creerLigneDeclaration(...).________________________________

	

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
	 * <li>écrit l'attribut saut de ligne Java.</li>
	 * <li>écrit l'attribut SELECT_OBJET.</li>
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
		
		/* écrit l'attribut saut de ligne Java. */
		this.ecrireSautLigneJava(pFile);
		
		/* écrit l'attribut SELECT_OBJET. */
		this.ecrireAttributSelectObjet(pFile);
		
		/* écrit l'attribut LOG. */
		this.ecrireAttributLog(pFile);
		
	} // Fin de ecrireBlocAttributs(...).__________________________________
	

	/**
	 * method ecrireAttributSelectObjet(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit l'attribut SELECT_OBJET dans pFile.</li>
	 * <li>ajoute 2 lignes vides sous l'attribut.</li>
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
	protected final void ecrireAttributSelectObjet(
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
			= this.lireTemplate("dao/attribut_select_object.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOMPACKAGE
							, this.nomPackage); 
		
		final List<String> listeSubst2 
		= this.substituerVariablesDansLigne(
				listeSubst1
					, VARIABLE_NOM_ABSTRACT_METIER
						, this.nomAbstractClassMetier);
		
		/* Recherche la ligne identifiant. */
		final String ligneIdentifiant 
			= this.fournirIdentifiantDebutSelectObjet();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}
		
		/* ajoute 2 lignes vides sous l'attribut. */
		listeSubst2.add("");
		listeSubst2.add("");
	
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst2, pFile);
		
	} // Fin de ecrireAttributSelectObjet(...).____________________________
	

	
	/**
	 * method fournirIdentifiantDebutSelectObjet() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant l'attribut 
	 * SELECT_OBJET pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutSelectObjet() {
		return "	public static final String SELECT_OBJET";
	} // Fin de fournirIdentifiantDebutSelectObjet().______________________
	
	
	
	/**
	 * method ecrireBlocMethodes(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li><b>Crée tout le bloc comprenant les methodes</b>.</li>
	 * <ul>
	 * <li>écrit le constructeur d'arité nulle.</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode createReturnId(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode retrieve(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode retrieveByAttributs(...).</li>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode findById(...).</li>
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
		 * méthode createReturnId(...). */
		this.ecrireMethodeCreateReturnId(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode retrieve(...). */
		this.ecrireMethodeRetrieve(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode retrieveByIdMetier(...). */
		this.ecrireMethodeRetrieveByIdMetier(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode retrieveByAttributs(...). */
		this.ecrireMethodeRetrieveByAttributs(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode findById(...). */
		this.ecrireMethodeFindById(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode deleteById(...). */
		this.ecrireMethodeDeleteById(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode deleteByIdBoolean(...). */
		this.ecrireMethodeDeleteByIdBoolean(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode exists(...). */
		this.ecrireMethodeExists(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode exists(Long..). */
		this.ecrireMethodeExistsLong(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode afficherListe(...). */
		this.ecrireMethodeAfficherListe(pFile);
		
		/* écrit la Javadoc et le code de la 
		 * méthode renseignerClassObjetMetier(...). */
		this.ecrireMethodeRenseignerClassObjetMetier(pFile);
		
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
	 * method ecrireMethodeCreateReturnId(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode createReturnId(...).</li>
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
	protected final void ecrireMethodeCreateReturnId(
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
			= this.fournirIdentifiantDebutMethodeCreateReturnId();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_createreturnid.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);
		
		
		/* ajoute 3 lignes vides sous la méthode. */
		this.ajouterLignesVides(3, listeSubst1);
		
	
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst1, pFile);
		
	} // Fin de ecrireMethodeCreateReturnId(...).__________________________
	
	
	
	/**
	 * method fournirIdentifiantDebutMethodeCreateReturnId() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant la méthode 
	 * createReturnId(...) pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutMethodeCreateReturnId() {
		return "	public final Long createReturnId";
	} // Fin de fournirIdentifiantDebutMethodeCreateReturnId().____________
	

	
	/**
	 * method ecrireMethodeRetrieve(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>ecrit la méthode retrieve(...).</li>
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
	protected final void ecrireMethodeRetrieve(
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
			= this.fournirIdentifiantDebutMethodeRetrieve();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}
		
		/* écrit le début de la méthode. */
		this.ecrireDebutMethodeRetrieve(pFile);
		
		/* écrit la requête HQL. */
		this.ecrireRequeteHQL(pFile);
		
		/* construit la requête HQL. */
		this.ecrireConstructionRequeteHql(pFile);
		
		/* passe les paramètres à la requête HQL. */
		this.ecrirePassageParametresHQL(pFile);
		
		/* écrit la fin de la méthode retrieve(...). */
		this.ecrireFinMethodeRetrieve(pFile);
		
	} // Fin de ecrireMethodeRetrieve(...).________________________________
	

	
	/**
	 * method ecrireDebutMethodeRetrieve(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit le début de la méthode retrieve(...) dans pFile.</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 */
	private void ecrireDebutMethodeRetrieve(
			final File pFile) throws Exception {
		
		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_retrieve_debut.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);

		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst1, pFile);
		
	} // Fin de ecrireDebutMethodeRetrieve(...).___________________________
	

	
	/**
	 * method ecrireRequeteHQL(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la requête HQL en listant les attributs de equals.</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception
	 */
	private void ecrireRequeteHQL(
			final File pFile) throws Exception {
		
		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_retrieve_clause_hql.txt");
		
		final StringBuilder stb = new StringBuilder();
		
		if (this.mapAttributsEquals == null) {
			return;
		}
		
		final int tailleMap = this.mapAttributsEquals.size();
		int compteur = 0;
		
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributsEquals.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			
			stb.append(this.nomPackage);
			stb.append(POINT);
			stb.append(nomAttribut);
			stb.append(EGAL);
			stb.append(':');
			stb.append(this.fournirParametre(nomAttribut));
			
			if (compteur < tailleMap) {
				stb.append(" and ");
			} else {
				stb.append(';');
			}
			
		}
		
		final String clause = stb.toString();
		
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, "{$clause}"
							, clause);
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst1, pFile);
		
	} // Fin de ecrireRequeteHQL(...)._____________________________________
	

	
	/**
	 * method ecrireConstructionRequeteHql(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la construction de la requete dans retrieve(...) dans pFile.</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 */
	private void ecrireConstructionRequeteHql(
			final File pFile) throws Exception {
		
		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate(
					"dao/methode_retrieve_construction_requete_hql.txt");
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeLignes, pFile);
		
	} // Fin de ecrireConstructionRequeteHql(...).___________________________


	
	/**
	 * method ecrirePassageParametresHQL(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>passe les paramètres de la requête HQL 
	 * en listant les attributs de equals.</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception
	 */
	private void ecrirePassageParametresHQL(
			final File pFile) throws Exception {
				
		if (this.mapAttributsEquals == null) {
			return;
		}
		
		final List<String> listeLignes = new ArrayList<String>();
		
		final StringBuilder stb = new StringBuilder();
		stb.append(DECALAGE_CODE);
		stb.append("/* Passage des paramètres de la requête HQL. */");
		listeLignes.add(stb.toString());
				
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributsEquals.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final StringBuilder stb1 = new StringBuilder();
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			
			stb1.append(DECALAGE_CODE);
			stb1.append("requete.setParameter(\"");
			stb1.append(this.fournirParametre(nomAttribut));
			stb1.append("\", pObject.");
			stb1.append(this.fournirGetter(nomAttribut));
			stb1.append(");");

			listeLignes.add(stb1.toString());
		}
		
		listeLignes.add("");
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeLignes, pFile);
		
	} // Fin de ecrireRequeteHQL(...)._____________________________________
	

	
	/**
	 * method ecrireFinMethodeRetrieve(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la fin de la méthode retrieve(...) dans pFile.</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 */
	private void ecrireFinMethodeRetrieve(
			final File pFile) throws Exception {
		
		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_retrieve_fin.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);
		
		final List<String> listeSubst2 
		= this.substituerVariablesDansLigne(
				listeSubst1
					, VARIABLE_NOMCLASSE
						, this.fabriquerNomClasse(
								this.nomSimpleFichierJava));
		
		/* ajoute 3 lignes vides. */
		this.ajouterLignesVides(3, listeSubst2);

		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst2, pFile);
		
	} // Fin de ecrireFinMethodeRetrieve(...)._____________________________
	
	
	
	/**
	 * method fournirIdentifiantDebutMethodeRetrieve() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant la méthode 
	 * retrieve(...) pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutMethodeRetrieve() {
		return "	public " + this.nomInterfaceMetier + " retrieve";
	} // Fin de fournirIdentifiantDebutMethodeRetrieve().__________________
	

	
	/**
	 * method ecrireMethodeRetrieveByIdMetier(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode retrieveByIdMetier(...).</li>
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
	protected final void ecrireMethodeRetrieveByIdMetier(
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
			= this.fournirIdentifiantDebutMethodeRetrieveByIdMetier();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_retrievebyidmetier.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);
		
		
		/* ajoute 3 lignes vides sous la méthode. */
		this.ajouterLignesVides(3, listeSubst1);
		
	
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst1, pFile);
		
	} // Fin de ecrireMethodeRetrieveByIdMetier(...).______________________
	
	
	
	/**
	 * method fournirIdentifiantDebutMethodeRetrieveByIdMetier() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant la méthode 
	 * retrieveByIdMetier(...) pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutMethodeRetrieveByIdMetier() {
		return "	public final " + this.nomInterfaceMetier + " retrieveByIdMetier";
	} // Fin de fournirIdentifiantDebutMethodeRetrieveByIdMetier().________
	

	
	/**
	 * method ecrireMethodeRetrieveByAttributs(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la javadoc de la méthode retrieveByAttributs(...).</li>
	 * <li>écrit le code de la méthode retrieveByAttributs(...).</li>
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
	protected final void ecrireMethodeRetrieveByAttributs(
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
			= this.fournirIdentifiantDebutMethodeRetrieveByAttributs();
	
		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}
				
		/* écrit la Javadoc. */
		this.ecrireJavadocMethodeRetrieveByAttributs(pFile);

		/* écrit le code. */
		this.ecrireCodeMethodeRetrieveByAttributs(pFile);
		
	} // Fin de ecrireMethodeRetrieveByAttributs(...)._____________________
	

	
	/**
	 * method ecrireJavadocMethodeRetrieveByAttributs(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la javadoc de la méthode retrieveByAttributs(...).</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * ne fait rien si les attributs du equals ne sont pas définis 
	 * (this.mapAttributsEquals == null).<br/>
	 * <br/>
	 *
	 *  @param pFile : File.<br/>
	 *  
	 * @throws Exception
	 */
	private void ecrireJavadocMethodeRetrieveByAttributs(
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
		
		/* ne fait rien si les attributs du equals ne sont pas définis 
		 * (this.mapAttributsEquals == null). */
		if (this.mapAttributsEquals == null) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("javadoc_inherit_override.txt");
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */		
		this.ecrireCode(listeLignes, pFile);
		
	} // Fin de ecrireJavadocMethodeRetrieveByAttributs(...).______________
	

	
	/**
	 * method ecrireCodeMethodeRetrieveByAttributs(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit le code de la méthode retrieveByAttributs(...).</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * ne fait rien si les attributs du equals ne sont pas définis 
	 * (this.mapAttributsEquals == null).<br/>
	 * <br/>
	 *
	 *  @param pFile : File.<br/>
	 *  
	 * @throws Exception
	 */
	private void ecrireCodeMethodeRetrieveByAttributs(
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
		
		/* ne fait rien si les attributs du equals ne sont pas définis 
		 * (this.mapAttributsEquals == null). */
		if (this.mapAttributsEquals == null) {
			return;
		}
		
		/* écrit la déclaration de la méthode. */
		this.ecrireDeclarationMethodeRetrieveByAttributs(pFile);
		
		/* écrit le début de la méthode. */
		this.ecrireDebutMethodeRetrieveByAttributs(pFile);
		
		/* écrit la requête HQL. */
		this.ecrireRequeteHQL(pFile);
		
		/* construit la requête HQL. */
		this.ecrireConstructionRequeteHql(pFile);
		
		/* passe les paramètres à la requête HQL. */
		this.ecrirePassageParametresHQLByAttributs(pFile);
		
		/* écrit la fin de la méthode retrieveByAttributs(...). */
		this.ecrireFinMethodeRetrieveByAttributs(pFile);

		
	} // Fin de ecrireCodeMethodeRetrieveByAttributs(...)._________________
	

	
	/**
	 * method ecrireDeclarationMethodeRetrieveByAttributs(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la ligne de déclaration de la 
	 * méthode retrieveByAttributs(...).</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * ne fait rien si les attributs du equals ne sont pas définis 
	 * (this.mapAttributsEquals == null).<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception
	 */
	private void ecrireDeclarationMethodeRetrieveByAttributs(
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
		
		/* ne fait rien si les attributs du equals ne sont pas définis 
		 * (this.mapAttributsEquals == null). */
		if (this.mapAttributsEquals == null) {
			return;
		}
		
		final List<String> listeCode = new ArrayList<String>();
		final StringBuilder stb = new StringBuilder();
		
		final StringBuilder decalageParametreStb = new StringBuilder();
		decalageParametreStb.append(DECALAGE_CODE);
				
		final int tailleMap = this.mapAttributsEquals.size();
		int compteur = 0;
		
		stb.append(DECALAGE_METHODE);
		stb.append("public final ");
		stb.append(this.nomInterfaceMetier);
		stb.append(SEP_ESPACE);
		stb.append("retrieveByAttributs(");
		
		listeCode.add(stb.toString());
		
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributsEquals.entrySet();
		
		final Iterator<Entry<String, String>> ite 
			= entrySet.iterator();
		
		while (ite.hasNext()) {
			
			compteur++;
			decalageParametreStb.append(TAB);
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			String ligneParametre = null;
			
			if (compteur == 1) {
				
				ligneParametre 
					= decalageParametreStb.toString() 
					+ FINAL
					+ typeAttribut 
					+ SEP_ESPACE 
					+ fournirParametre(nomAttribut);
				
				listeCode.add(ligneParametre);
				
			} else if (compteur > 1 && compteur < tailleMap) {
				
				ligneParametre 
				= decalageParametreStb.toString()
				+ ", "
				+ FINAL
				+ typeAttribut 
				+ SEP_ESPACE 
				+ fournirParametre(nomAttribut);
				
				listeCode.add(ligneParametre);
				
			} else {
				
				ligneParametre 
				= decalageParametreStb.toString()
				+ ", "
				+ FINAL
				+ typeAttribut 
				+ SEP_ESPACE 
				+ fournirParametre(nomAttribut) + ")";
				
				listeCode.add(ligneParametre);
				
				decalageParametreStb.append(TAB);
				
				final String ligneThrowsException 
					= decalageParametreStb.toString() 
					+ "throws AbstractDaoException {";
				
				listeCode.add(ligneThrowsException);
				
			}
			
		}
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */		
		this.ecrireCode(listeCode, pFile);
						
	} // Fin de ecrireDeclarationMethodeRetrieveByAttributs(...).__________
	

	
	/**
	 * method ecrireDebutMethodeRetrieveByAttributs(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit le début de la 
	 * méthode retrieveByAttributs(...).</li>
	 * <li>n'insère une ligne que si le type de paramètre est Objet.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * ne fait rien si les attributs du equals ne sont pas définis 
	 * (this.mapAttributsEquals == null).<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception
	 */
	private void ecrireDebutMethodeRetrieveByAttributs(
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
		
		/* ne fait rien si les attributs du equals ne sont pas définis 
		 * (this.mapAttributsEquals == null). */
		if (this.mapAttributsEquals == null) {
			return;
		}
		
		final List<String> listeCode = new ArrayList<String>();
		
		/* ajoute une ligne vide sous la déclaration. */
		listeCode.add("");
		
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributsEquals.entrySet();
		
		final Iterator<Entry<String, String>> ite 
			= entrySet.iterator();
		
		while (ite.hasNext()) {
						
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			/* n'insère une ligne que si le type de paramètre est Objet. */
			if (this.conformeNomClasse(typeAttribut)) {
				
				/* Cas de String. */
				if (StringUtils.equals(typeAttribut, "String")) {
					
					/* lecture du Template. */
					final List<String> listeLignes 
						= this.lireTemplate(
								"test_param_return_null_if_stringutils_isblank.txt");
					
					/* substitutions. */
					final List<String> listeSubst1 
						= this.substituerVariablesDansLigne(
								listeLignes
									, "{$param}"
										, this.fournirParametre(nomAttribut));
					
					listeCode.addAll(listeSubst1);
					listeCode.add("");
					
				}
				else {
					
					/* lecture du Template. */
					final List<String> listeLignes 
						= this.lireTemplate(
								"test_param_return_null_if_param_isnull.txt");
					
					/* substitutions. */
					final List<String> listeSubst1 
						= this.substituerVariablesDansLigne(
								listeLignes
									, "{$param}"
										, this.fournirParametre(nomAttribut));
					
					listeCode.addAll(listeSubst1);
					listeCode.add("");
										
				}				
			}
		}
		
		final String ligneDecObjet 
			= DECALAGE_CODE 
				+ this.nomInterfaceMetier 
					+ " objetResultat = null;";
		listeCode.add(ligneDecObjet);
		listeCode.add("");
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */		
		this.ecrireCode(listeCode, pFile);
						
	} // Fin de ecrireDebutMethodeRetrieveByAttributs(...).________________
	
	
	
	/**
	 * method ecrirePassageParametresHQLByAttributs(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>passe les paramètres de la requête HQL 
	 * en listant les attributs de equals pour retrieveByAttributs.</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception
	 */
	private void ecrirePassageParametresHQLByAttributs(
			final File pFile) throws Exception {
				
		if (this.mapAttributsEquals == null) {
			return;
		}
		
		final List<String> listeLignes = new ArrayList<String>();
		
		final StringBuilder stb = new StringBuilder();
		stb.append(DECALAGE_CODE);
		stb.append("/* Passage des paramètres de la requête HQL. */");
		listeLignes.add(stb.toString());
				
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributsEquals.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final StringBuilder stb1 = new StringBuilder();
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			
			stb1.append(DECALAGE_CODE);
			stb1.append("requete.setParameter(\"");
			stb1.append(this.fournirParametre(nomAttribut));
			stb1.append("\", ");
			stb1.append(this.fournirParametre(nomAttribut));
			stb1.append(");");

			listeLignes.add(stb1.toString());
		}
		
		listeLignes.add("");
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeLignes, pFile);
		
	} // Fin de ecrirePassageParametresHQLByAttributs(...).________________
	

	
	/**
	 * method ecrireFinMethodeRetrieveByAttributs(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la fin de la méthode retrieveByAttributs(...) 
	 * dans pFile.</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 */
	private void ecrireFinMethodeRetrieveByAttributs(
			final File pFile) throws Exception {
		
		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_retrievebyattributs_fin.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);
		
		final List<String> listeSubst2 
		= this.substituerVariablesDansLigne(
				listeSubst1
					, VARIABLE_NOMCLASSE
						, this.fabriquerNomClasse(
								this.nomSimpleFichierJava));
		
		/* ajoute 3 lignes vides. */
		this.ajouterLignesVides(3, listeSubst2);

		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst2, pFile);
		
	} // Fin de ecrireFinMethodeRetrieveByAttributs(...).__________________
	
	
		
	/**
	 * method fournirIdentifiantDebutMethodeRetrieveByAttributs() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant la méthode 
	 * retrieveByAttributs(...) pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutMethodeRetrieveByAttributs() {
		return "	public final " + this.nomInterfaceMetier + " retrieveByAttributs";
	} // Fin de fournirIdentifiantDebutMethodeRetrieveByAttributs()._______
	

	
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
		if (this.existLigneCommencant(pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_findbyid_abstractdao.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);
		
		
		/* ajoute 3 lignes vides sous la méthode. */
		this.ajouterLignesVides(3, listeSubst1);
		
	
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst1, pFile);
		
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
		return "	public abstract " + this.nomInterfaceMetier + " findById";
	} // Fin de fournirIdentifiantDebutMethodeFindById().__________________


	
	/**
	 * method ecrireMethodeDeleteById(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode deleteById(...).</li>
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
	protected final void ecrireMethodeDeleteById(
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
			= this.fournirIdentifiantDebutMethodeDeleteById();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_deletebyid_abstractdao.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);
		
		final List<String> listeSubst2 
			= this.substituerVariablesDansLigne(
					listeSubst1
						, VARIABLE_NOMPACKAGE
							, this.nomPackage);
		
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
		
	} // Fin de ecrireMethodeDeleteById(...).______________________________
	

	
	/**
	 * method fournirIdentifiantDebutMethodeDeleteById() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant la méthode 
	 * deleteById(...) pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutMethodeDeleteById() {
		return DECALAGE_METHODE + "public void deleteById";
	} // Fin de fournirIdentifiantDebutMethodeDeleteById().________________


	
	/**
	 * method ecrireMethodeDeleteByIdBoolean(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode deleteByIdBoolean(...).</li>
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
	protected final void ecrireMethodeDeleteByIdBoolean(
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
			= this.fournirIdentifiantDebutMethodeDeleteByIdBoolean();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate(
					"dao/methode_deletebyidboolean_abstractdao.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);
		
		final List<String> listeSubst2 
			= this.substituerVariablesDansLigne(
					listeSubst1
						, VARIABLE_NOMPACKAGE
							, this.nomPackage);
		
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
		
	} // Fin de ecrireMethodeDeleteByIdBoolean(...)._______________________
	

	
	/**
	 * method fournirIdentifiantDebutMethodeDeleteByIdBoolean() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant la méthode 
	 * deleteByIdBoolean(...) pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutMethodeDeleteByIdBoolean() {
		return DECALAGE_METHODE + "public boolean deleteByIdBoolean";
	} // Fin de fournirIdentifiantDebutMethodeDeleteByIdBoolean()._________


	
	/**
	 * method ecrireMethodeExists(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>ecrit la méthode exists(...).</li>
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
	protected final void ecrireMethodeExists(
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
			= this.fournirIdentifiantDebutMethodeExists();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}
		
		/* écrit le début de la méthode. */
		this.ecrireDebutMethodeExists(pFile);
		
		/* écrit la requête HQL. */
		this.ecrireRequeteHQL(pFile);
		
		/* construit la requête HQL. */
		this.ecrireConstructionRequeteHql(pFile);
		
		/* passe les paramètres à la requête HQL. */
		this.ecrirePassageParametresHQL(pFile);
		
		/* écrit la fin de la méthode exists(...). */
		this.ecrireFinMethodeExists(pFile);
		
	} // Fin de ecrireMethodeExists(...).________________________________
	

	
	/**
	 * method ecrireDebutMethodeExists(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit le début de la méthode exists(...) dans pFile.</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 */
	private void ecrireDebutMethodeExists(
			final File pFile) throws Exception {
		
		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_exists_debut.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);

		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst1, pFile);
		
	} // Fin de ecrireDebutMethodeExists(...).___________________________
	

	
	/**
	 * method ecrireFinMethodeExists(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la fin de la méthode exists(...) dans pFile.</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 */
	private void ecrireFinMethodeExists(
			final File pFile) throws Exception {
		
		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_exists_fin.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);
		
		final List<String> listeSubst2 
		= this.substituerVariablesDansLigne(
				listeSubst1
					, VARIABLE_NOMCLASSE
						, this.fabriquerNomClasse(
								this.nomSimpleFichierJava));
		
		/* ajoute 3 lignes vides. */
		this.ajouterLignesVides(3, listeSubst2);

		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst2, pFile);
		
	} // Fin de ecrireFinMethodeExists(...)._______________________________
	

	
	/**
	 * method fournirIdentifiantDebutMethodeExists() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant la méthode 
	 * exists(...) pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutMethodeExists() {
		return DECALAGE_METHODE + "public boolean exists";
	} // Fin de fournirIdentifiantDebutMethodeExists().____________________


	
	/**
	 * method ecrireMethodeExistsLong(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode exists(...).</li>
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
	protected final void ecrireMethodeExistsLong(
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
			= "	} // Fin de exists(Long.";

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/methode_exists_long_abstractdao.txt");
		
		/* substitutions. */
				
		/* ajoute 3 lignes vides sous la méthode. */
		this.ajouterLignesVides(3, listeLignes);
		
	
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeLignes, pFile);
		
	} // Fin de ecrireMethodeExistsLong(...).______________________________
	

	
	/**
	 * method ecrireMethodeAfficherListe(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode afficherListe(...).</li>
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
	protected final void ecrireMethodeAfficherListe(
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
			= this.fournirIdentifiantDebutMethodeAfficherListe();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate(
					"dao/methode_afficherliste_abstractdao.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);
		
		
		/* ajoute 3 lignes vides sous la méthode. */
		this.ajouterLignesVides(3, listeSubst1);
		
	
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst1, pFile);
		
	} // Fin de ecrireMethodeAfficherListe(...).___________________________

	
	
	/**
	 * method fournirIdentifiantDebutMethodeAfficherListe() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant la méthode 
	 * afficherListe(...) pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutMethodeAfficherListe() {
		return DECALAGE_METHODE + "public String afficherListe";
	} // Fin de fournirIdentifiantDebutMethodeAfficherListe()._____________


	
	/**
	 * method ecrireMethodeRenseignerClassObjetMetier(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit la Javadoc et le code de la 
	 * méthode renseignerClassObjetMetier(...).</li>
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
	protected final void ecrireMethodeRenseignerClassObjetMetier(
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
			= this.fournirIdentifiantDebutMethodeRenseignerClassObjetMetier();

		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* lecture du Template. */
		final List<String> listeLignes 
			= this.lireTemplate(
					"dao/methode_renseignerclassobjetmetier_abstractdao.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOM_INTERFACE_METIER
							, this.nomInterfaceMetier);
		
		final List<String> listeSubst2 
			= this.substituerVariablesDansLigne(
					listeSubst1
					, VARIABLE_NOMSIMPLEFICHIERJAVA
						, this.nomSimpleFichierJava);
		
		
		/* ajoute 3 lignes vides sous la méthode. */
		this.ajouterLignesVides(3, listeSubst2);
		
	
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		this.ecrireCode(listeSubst2, pFile);
		
	} // Fin de ecrireMethodeRenseignerClassObjetMetier(...).______________

	
	
	/**
	 * method fournirIdentifiantDebutMethodeRenseignerClassObjetMetier() :<br/>
	 * <ul>
	 * <li>retourne le début de la ligne identifiant la méthode 
	 * renseignerClassObjetMetier(...) pour ne jamais l'écrire deux fois.</li>
	 * </ul>
	 *
	 * @return : String : identifiant.<br/>
	 */
	private String fournirIdentifiantDebutMethodeRenseignerClassObjetMetier() {
		return DECALAGE_METHODE + "protected final void renseignerClassObjetMetier";
	} // Fin de fournirIdentifiantDebutMethodeRenseignerClassObjetMetier().


	
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
		return CLASSE_ECRIVEUR_DAO_ABSTRACT;
	} // Fin de fournirNomClasse().________________________________________


	
} // FIn DE LA CLASSE EcriveurDaoAbstract.-----------------------------------
