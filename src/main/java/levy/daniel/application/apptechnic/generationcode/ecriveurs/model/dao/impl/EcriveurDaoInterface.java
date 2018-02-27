package levy.daniel.application.apptechnic.generationcode.ecriveurs.model.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveurFichiersJavaDetaille;

/**
 * CLASSE <b>EcriveurDaoInterface</b> :<br/>
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
 * @since 15 févr. 2018
 *
 */
public class EcriveurDaoInterface 
				extends AbstractEcriveurFichiersJavaDetaille {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ECRIVEUR_DAO_INTERFACE : String :<br/>
	 * "Classe EcriveurDaoInterface".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_DAO_INTERFACE 
		= "Classe EcriveurDaoInterface";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
				= LogFactory.getLog(EcriveurDaoInterface.class);
	

	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR EcriveurDaoInterface() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public EcriveurDaoInterface() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesImport() throws Exception {
		
		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/imports_interface_idao.txt");
		
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
			= this.lireTemplate("dao/javadoc_interface_idao.txt");
		
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
		return " * INTERFACE";
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
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append(INTERFACE);
		stb.append(this.nomSimpleFichierJava);
		stb.append('\n');
		stb.append(DECALAGE_CODE);
		stb.append("extends IDaoGenericJPASpring<");
		stb.append(this.nomInterfaceMetier);
		stb.append(", Long> {");
		
		this.ligneDeclaration = stb.toString();
		
		return this.ligneDeclaration;
		
	} // Fin de creerLigneDeclaration(...).________________________________
	
	

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
	protected final void ecrireCodeHook(
			final File pFile) {
		
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
	 * method ecrireBlocMethodes(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li><b>Crée tout le bloc comprenant les methodes</b>.</li>
	 * <ul>
	 * <li>Ecrit la méthode retrieveByIdMetier().</li>
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

		/* Insère 3 lignes vides sous la ligne 
		 * de déclaration.*/
		final StringBuilder stb = new StringBuilder();
		
		stb.append(DECALAGE_CODE);
		stb.append("extends IDaoGenericJPASpring<");
		stb.append(this.nomInterfaceMetier);
		stb.append(", Long> {");
		
		final String ligneDec = stb.toString();
		
		this.insererLignesVidesSousLigneDansFichier(
				pFile, ligneDec, 3, CHARSET_UTF8);
		
		/* écrit la méthode retrieveByIdMetier(...) */
		this.ecrireMethodeRetrieveById(pFile);
		
		/* écrit la méthode retrieveByAttributs(...) */
		this.ecrireMethodeRetrieveByAttributs(pFile);
						
	} // Fin de ecrireBlocMethodes(...).___________________________________
	

	
	/**
	 * method ecrireMethodeRetrieveById(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Crée la Javadoc de la méthode retrieveByIdMetier(...)</li>
	 * <li>Crée le code de la méthode retrieveByIdMetier(...)</li>
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
	private void ecrireMethodeRetrieveById(
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
			= "		retrieveByIdMetier";
	
		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}

		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/interface_dao_retrievebyidmetier");
		
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

	} // Fin de ecrireMethodeRetrieveById(...).____________________________
	

	
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
	private void ecrireMethodeRetrieveByAttributs(
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
			= "	 * method retrieveByAttributs";
	
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

		final List<String> listeJavadoc = new ArrayList<String>();
		
		listeJavadoc.add(DEBUT_JAVADOC_MEMBRE);
		
		final String premiereLigne 
			= DEBUT_LIGNE_JAVADOC_MEMBRE + "method retrieveByAttributs(";
		
		listeJavadoc.add(premiereLigne);
		
		/* ajoute les paramètres à la méthode dans la Javadoc. */
		this.ajouterParametresAMethodeJavadoc(listeJavadoc);
		
		/* ajoute le corps de la Javadoc. */
		this.ecrireCorpsJavadoc(listeJavadoc);
		
		/* ajoute les paramètres à la Javadoc. */
		this.ajouterParametresAJavadoc(listeJavadoc);
		
		/* ajoute la fin de la Javadoc. */
		this.ecrireFinJavadoc(listeJavadoc);
		
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */		
		this.ecrireCode(listeJavadoc, pFile);
		
	} // Fin de ecrireJavadocMethodeRetrieveByAttributs(...).______________
	

	
	/**
	 * method ajouterParametresAMethodeJavadoc(
	 * List&lt;String&gt; pListeJavadoc) :<br/>
	 * <ul>
	 * <li>Rajoute les paramètres liés aux attributs 
	 * de equals() à la méthode en début de Javadoc.</li>
	 * </ul>
	 * ne fait rien si les attributs du equals ne sont pas définis 
	 * (this.mapAttributsEquals == null).<br/>
	 * <br/>
	 *
	 * @param pListeJavadoc : List&lt;String&gt; .<br/>
	 */
	private void ajouterParametresAMethodeJavadoc(
			final List<String> pListeJavadoc) {
		
		/* ne fait rien si les attributs du equals ne sont pas définis 
		 * (this.mapAttributsEquals == null). */
		if (this.mapAttributsEquals == null) {
			return;
		}
		
		final int tailleMap = this.mapAttributsEquals.size();
		int compteur = 0;
		
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributsEquals.entrySet();
		
		final Iterator<Entry<String, String>> ite 
			= entrySet.iterator();
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			String ligneParametre = null;
			
			if (compteur == 1) {
				
				ligneParametre 
					= DEBUT_LIGNE_JAVADOC_MEMBRE 
					+ typeAttribut 
					+ SEP_ESPACE 
					+ fournirParametre(nomAttribut);
				
				pListeJavadoc.add(ligneParametre);
				
			} else if (compteur > 1 && compteur < tailleMap) {
				
				ligneParametre 
				= VIRGULE_JAVADOC_MEMBRE
				+ typeAttribut 
				+ SEP_ESPACE 
				+ fournirParametre(nomAttribut);
				
				pListeJavadoc.add(ligneParametre);
				
			} else {
				
				ligneParametre 
				= VIRGULE_JAVADOC_MEMBRE
				+ typeAttribut 
				+ SEP_ESPACE 
				+ fournirParametre(nomAttribut) + ") :";
				
				pListeJavadoc.add(ligneParametre);
			}
			
		}
		
	} // Fin de ajouterParametresAMethodeJavadoc(...)._____________________
	

		
	/**
	 * method ecrireCorpsJavadoc(
	 * List&lt;String&gt; pListeJavadoc) :<br/>
	 * <ul>
	 * <li>écrit le corps de la Javadoc de la 
	 * méthode retrieveByAttributs(...).</li>
	 * <li>ajoute le corps dans pListeJavadoc.<br/>
	 * </ul>
	 * Ne fait rien si pListeJavadoc == null.<br/>
	 * <br/>
	 *
	 * @param pListeJavadoc : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	private void ecrireCorpsJavadoc(
			final List<String> pListeJavadoc) throws Exception {
		
		/* Ne fait rien si pListeJavadoc == null. */
		if (pListeJavadoc == null) {
			return;
		}
		
		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate(
					"dao/methode_retrievebyattributs_javadoc_interfacedao_corps.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);

		pListeJavadoc.addAll(listeSubst1);
		
	} // Fin de ecrireCorpsJavadoc(...).___________________________________
	

	
	/**
	 * method ajouterParametresAJavadoc(
	 * List&lt;String&gt; pListeJavadoc) :<br/>
	 * <ul>
	 * <li>Rajoute les paramètres liés aux attributs 
	 * de equals() à la Javadoc.</li>
	 * </ul>
	 * ne fait rien si les attributs du equals ne sont pas définis 
	 * (this.mapAttributsEquals == null).<br/>
	 * <br/>
	 *
	 * @param pListeJavadoc : List&lt;String&gt; .<br/>
	 */
	private void ajouterParametresAJavadoc(
			final List<String> pListeJavadoc) {
		
		/* ne fait rien si les attributs du equals ne sont pas définis 
		 * (this.mapAttributsEquals == null). */
		if (this.mapAttributsEquals == null) {
			return;
		}
		
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributsEquals.entrySet();
		
		final Iterator<Entry<String, String>> ite 
			= entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			final String ligneParametre 
				= DEBUT_LIGNE_JAVADOC_MEMBRE 
					+ "@param "
					+ this.fournirParametre(nomAttribut) 
					+ SEP_2PTS_AERE 
					+ typeAttribut 
					+ SEP_2PTS_AERE + nomAttribut + " du " 
					+ this.nomInterfaceMetier 
					+ ".<br/>";
			
			pListeJavadoc.add(ligneParametre);
			
		}
		
	} // Fin de ajouterParametresAJavadoc(...).____________________________
	
	
	
	/**
	 * method ecrireFinJavadoc(
	 * List&lt;String&gt; pListeJavadoc) :<br/>
	 * <ul>
	 * <li>écrit la fin de la Javadoc de la 
	 * méthode retrieveByAttributs(...).</li>
	 * <li>ajoute la fin dans pListeJavadoc.<br/>
	 * </ul>
	 * Ne fait rien si pListeJavadoc == null.<br/>
	 * <br/>
	 *
	 * @param pListeJavadoc : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	private void ecrireFinJavadoc(
			final List<String> pListeJavadoc) throws Exception {
		
		/* Ne fait rien si pListeJavadoc == null. */
		if (pListeJavadoc == null) {
			return;
		}
		
		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate(
					"dao/methode_retrievebyattributs_javadoc_interfacedao_fin.txt");
		
		/* substitutions. */
		final List<String> listeSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);

		pListeJavadoc.addAll(listeSubst1);
		
	} // Fin de ecrireFinJavadoc(...)._____________________________________

	
	
	/**
	 * method ecrireCodeMethodeRetrieveByAttributs(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>écrit le code de la méthode retrieveByAttributs(...).</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
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
		
		final List<String> listeCode = new ArrayList<String>();
		final StringBuilder stb = new StringBuilder();
		
		final StringBuilder decalageParametreStb = new StringBuilder();
		decalageParametreStb.append(DECALAGE_CODE);
				
		final int tailleMap = this.mapAttributsEquals.size();
		int compteur = 0;
		
		stb.append(DECALAGE_METHODE);
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
					+ typeAttribut 
					+ SEP_ESPACE 
					+ fournirParametre(nomAttribut);
				
				listeCode.add(ligneParametre);
				
			} else if (compteur > 1 && compteur < tailleMap) {
				
				ligneParametre 
				= decalageParametreStb.toString()
				+ ", "
				+ typeAttribut 
				+ SEP_ESPACE 
				+ fournirParametre(nomAttribut);
				
				listeCode.add(ligneParametre);
				
			} else {
				
				ligneParametre 
				= decalageParametreStb.toString()
				+ ", "
				+ typeAttribut 
				+ SEP_ESPACE 
				+ fournirParametre(nomAttribut) + ")";
				
				listeCode.add(ligneParametre);
				
				decalageParametreStb.append(TAB);
				
				final String ligneThrowsException 
					= decalageParametreStb.toString() 
					+ "throws AbstractDaoException;";
				
				listeCode.add(ligneThrowsException);
				
			}
			
		}

		/* ajoute 3 lignes vides sous la méthode. */
		this.ajouterLignesVides(3, listeCode);

		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */		
		this.ecrireCode(listeCode, pFile);
		
	} // Fin de ecrireCodeMethodeRetrieveByAttributs(...)._________________
	
	
	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * retourne pour un EcriveurDaoInterface : 
	 * "L'INTERFACE ".<br/>
	 * <br/>
	 */
	@Override
	protected final String fournirTypeFichierJava() {
		return "L'INTERFACE ";		
	} // Fin de fournirTypeFichierJava(...)._______________________________

	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * retourne pour un EcriveurDaoInterface : 
	 * "Classe EcriveurDaoInterface".<br/>
	 * <br/>
	 */
	@Override
	protected final String fournirNomClasse() {
		return CLASSE_ECRIVEUR_DAO_INTERFACE;
	} // Fin de fournirNomClasse().________________________________________

	
	
} // FIn DE LA CLASSE EcriveurDaoInterface.----------------------------------
