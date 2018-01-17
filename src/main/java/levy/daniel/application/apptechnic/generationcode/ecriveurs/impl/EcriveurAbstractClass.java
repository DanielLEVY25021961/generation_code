package levy.daniel.application.apptechnic.generationcode.ecriveurs.impl;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveur;

/**
 * class EcriveurAbstractClass :<br/>
 * .<br/>
 * 
 *  <br/>
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
	 * method creerLignePackage(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Crée et retourne la première <b>ligne de code</b> PACKAGE 
	 * d'une classe Java.</li>
	 * <li>alimente this.lignePackage</li>
	 * <li>Déduit la package père Java d'un fichier Java.</li>
	 * <li>Par exemple : creerLignePackage(IProfil.java) retourne :<br/> 
	 * <code>package 
	 * levy.daniel.application.model.metier.profil;</code></li>
	 * </ul>
	 * retourne null si pClasseJava est null.<br/>
	 * retourne null si pClasseJava n'existe pas.<br/>
	 * retourne null si pClasseJava n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : la classe Java dont on veut générer 
	 * la première ligne package.<br/>
	 * 
	 * @return : String : La ligne package à incorporer 
	 * à la première ligne de la classe Java.<br/>
	 */
	@Override
	protected final String creerLignePackage(
			final File pFile) {
		
		/* retourne null si pFile est null. */
		if (pFile == null) {
			return null;
		}
		
		/* retourne null si pFile n'existe pas. */
		if (!pFile.exists()) {
			return null;
		}
		
		/* retourne null si pFile n'est pas un fichier simple. */
		if (!pFile.isFile()) {
			return null;
		}
		
		/* Récupération du package parent de l'interface. */
		final File packagePere = pFile.getParentFile();
		
		/* Récupération du Path du package parent de l'interface. */
		final Path pathPackagePere = packagePere.toPath();
		
		/* EXTRACTION DU PATH RELATIF DU PACKAGE-PERE PAR RAPPORT 
		 * A LA RACINE DES SOURCES JAVA avec des antislash. */
		final Path pathPackageRelatifPere 
			= this.pathRacineMainJava.relativize(pathPackagePere);
		
		/* Transformation du path relatif en String avec des antislash. */
		final String pathRelatifPereAntiSlash 
			= pathPackageRelatifPere.toString();
		
		/* Transformation en path Java avec des points. */
		final String pathRelatifPerePoint 
			= this.remplacerAntiSlashparPoint(pathRelatifPereAntiSlash);
		
		/* CONSTRUCTION DE LA LIGNE DE CODE. */
		final String resultat 
			= "package " + pathRelatifPerePoint + POINT_VIRGULE;
		
		this.lignePackage = resultat;
		
		return this.lignePackage;
		
	} // Fin de creerLignePackage(...).____________________________________
	

	
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
		
		this.imports = listeLignes;
		
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
	protected final void creerAttributId(
			final List<String> pListe) throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/attributId.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
					
		pListe.addAll(listeLignes);
				
	} // Fin de creerAttributId(...).______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocAttribut(
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
						, pNomAttribut);
		
		final List<String> attributListSubst2 
		= this.substituerVariablesDansLigne(
				attributListSubst1
					, VARIABLE_TYPEATTRIBUT
						, pTypeAttribut);
		
		pListe.addAll(attributListSubst2);
		
	} // Fin de creerCodeAttribut(...).____________________________________
	

	
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
	 * <br/>
	 * <ul>
	 * <b>fournirDebutDeclaration() pour une ABSTRACTCLASS</b> :
	 * <li>"public abstract class ".</li>
	 * </ul>
	 * <br/>
	 */
	@Override
	protected final String fournirDebutDeclaration() {
		return ABSTRACT_CLASS;
	} // Fin de fournirDebutDeclaration()._________________________________


	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * <ul>
	 * <b>fournirDebutJavaDoc() pour une ABSTRACTCLASS</b> :
	 * <li>" * CLASSE ABSTRAITE".</li>
	 * </ul>
	 * <br/>
	 */
	@Override
	protected final String fournirDebutJavaDoc() {
		return " * CLASSE ABSTRAITE";
	} // Fin de fournirDebutJavaDoc()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void ecrireCodeConstructeurCompletBase(
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
		
		listCode.add(PUBLIC + this.nomSimpleFichierJava + "(");

		final String decalageDebut = "\t" + "\t" + "\t";
		final String idString = decalageDebut + FINAL + "Long pId";
		
		listCode.add(idString);
		
		final int tailleMap = this.mapAttributs.size();
		
		final Set<Entry<String, String>> entrySet 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		int compteur = 0;
		String decalage = decalageDebut;
		
		while (ite.hasNext()) {
			
			compteur++;
			decalage = decalage +"\t"; // NOPMD by daniel.levy on 10/01/18 14:43
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			final String ligneACreerBase 
				= decalage + ", " + FINAL
				+ typeAttribut 
				+ SEP_ESPACE + this.fournirParametre(nomAttribut);
			
			String ligneACreer = null;
			
			if (compteur < tailleMap) {
				ligneACreer = ligneACreerBase;
			} else {
				ligneACreer = ligneACreerBase + ") {";
			}
			
			listCode.add(ligneACreer);
		}

		final String decalageCode = "\t" + "\t";
		
		listCode.add("");
		listCode.add(decalageCode + SUPER);
		listCode.add("");
		listCode.add(decalageCode + "this.id = pId;");
		
		
		final Set<Entry<String, String>> entrySet2 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite2 = entrySet2.iterator();
		
		while (ite2.hasNext()) {
			
			final Entry<String, String> entry2 = ite2.next();
			
			final String nomAttribut = entry2.getKey();

			final String ligneACreer 
				= decalageCode 
				+ THIS + nomAttribut + EGAL 
				+ this.fournirParametre(nomAttribut) + SEP_PV;
						
			listCode.add(ligneACreer);
		}
		
		listCode.add("");
		
		final String ligneIdentifiant 
			= "\t" 
			+ LIGNE_FIN_CONSTR_COMPLET_BASE;
		
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
			for (final String ligne : listCode) {

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
				LOG.fatal("Impossible de créer le code du constructeur", e);
			}
		}
		
	} // Fin de ecrireCodeConstructeurCompletBase(...).____________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocCompareTo(
			final List<String> pListe) throws Exception {
		
		/* Création des lignes. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ TEMPLATE_INHERITS_OVERRIDE;
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignes);
		
	} // Fin de creerJavadocCompareTo(...).________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeCompareTo(
			final List<String> pListe) throws Exception {
		
		/* DEBUT. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/compareTo/debut_compareTo.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		final List<String> listeLignesDebutAAjouter 
			= this.substituerVariablesDansLigne(
					listeLignesDebut
						, VARIABLE_NOMSIMPLEINTERFACE
							, this.nomSimpleInterface);
		
		/* Ajout des lignes du début. */
		pListe.addAll(listeLignesDebutAAjouter);
		
		/* ENTIERS DE COMPARAISON. */
		final List<String> listeEntiersComp = new ArrayList<String>();
		final String ligneBase = DECALAGE_CODE + "int ";
		
		final Set<Entry<String, String>> entrySetEntiersComp 
		= this.mapAttributsEquals.entrySet();
	
		final Iterator<Entry<String, String>> iteEntiersComp 
			= entrySetEntiersComp.iterator();
		
		while (iteEntiersComp.hasNext()) {
			
			final Entry<String, String> entryEntiersComp = iteEntiersComp.next();
			
			final String nomAttribut = entryEntiersComp.getKey();
			
			final String ligneAAjouter 
			= ligneBase 
			+ this.fournirEntierCompare(nomAttribut) 
			+ POINT_VIRGULE;
			
			listeEntiersComp.add(ligneAAjouter);

		}

		listeEntiersComp.add("");
		
		/* Ajout des lignes du corps. */
		pListe.addAll(listeEntiersComp);

		
		
		/* CORPS. */
		final String cheminFichierCorps 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/compareTo/corps_compareTo.txt";
	
		final File fichierCorps = new File(cheminFichierCorps);
	
		final List<String> listeLignesCorps 
			= this.lireStringsDansFile(fichierCorps, CHARSET_UTF8);
		
		/* dernier attribut. */
		final String cheminFichierCorpsFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/compareTo/corps_fin_compareTo.txt";
	
		final File fichierCorpsFin = new File(cheminFichierCorpsFin);
	
		final List<String> listeLignesCorpsFin 
			= this.lireStringsDansFile(fichierCorpsFin, CHARSET_UTF8);
		
		
		final Set<Entry<String, String>> entrySetCorps 
		= this.mapAttributsEquals.entrySet();
	
		final Iterator<Entry<String, String>> iteCorps 
			= entrySetCorps.iterator();
		
		final int tailleMapEquals = this.mapAttributsEquals.size();
		int compteur = 0;
		
		while (iteCorps.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entryCorps = iteCorps.next();
			
			final String nomAttribut = entryCorps.getKey();
			
			if (compteur < tailleMapEquals) {
				
				final List<String> lignesAAjouter 
				= this.substituerVariablesDansLigne(
						listeLignesCorps
							, VARIABLE_NOMATTRIBUT
								, nomAttribut);
				
				final List<String> lignesAAjouterSubst1 
				= this.substituerVariablesDansLigne(
						lignesAAjouter
							, VARIABLE_GETTER
								, this.fournirGetter(nomAttribut));
				
				final List<String> lignesAAjouterSubst2 
				= this.substituerVariablesDansLigne(
						lignesAAjouterSubst1
							, VARIABLE_ENTIER_COMPARE
								, this.fournirEntierCompare(nomAttribut));
			
				/* Ajout des lignes du corps. */
				pListe.addAll(lignesAAjouterSubst2);
				
			} else {
				
				final List<String> lignesAAjouter 
				= this.substituerVariablesDansLigne(
						listeLignesCorpsFin
							, VARIABLE_NOMATTRIBUT
								, nomAttribut);
				
				final List<String> lignesAAjouterSubst1 
				= this.substituerVariablesDansLigne(
						lignesAAjouter
							, VARIABLE_GETTER
								, this.fournirGetter(nomAttribut));
				
				final List<String> lignesAAjouterSubst2 
				= this.substituerVariablesDansLigne(
						lignesAAjouterSubst1
							, VARIABLE_ENTIER_COMPARE
								, this.fournirEntierCompare(nomAttribut));
			
				/* Ajout des lignes du corps. */
				pListe.addAll(lignesAAjouterSubst2);
				
			}
			
		}

		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/compareTo/fin_compareTo.txt";
	
		final File fichierFin = new File(cheminFichierFin);
	
		final List<String> listeLignesFin 
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);
		
		/* Ajout des lignes du fin. */
		pListe.addAll(listeLignesFin);
				
	} // Fin de creerCodeCompareTo(...).___________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifiantCompareTo() {
		return "	public int compareTo";
	} // Fin de fournirLigneIdentifiantCompareTo().________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocClone(
			final List<String> pListe) throws Exception {
		
		/* Création des lignes. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ TEMPLATE_INHERITS_OVERRIDE;
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignes);
		
	} // Fin de creerJavadocClone(...).____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeClone(
			final List<String> pListe) throws Exception {
		
		/* DEBUT. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/clone/code_corps_debut_clone_abstractclass.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		final List<String> listeLignesDebutAAjouter 
			= this.substituerVariablesDansLigne(
					listeLignesDebut
						, VARIABLE_NOMSIMPLEFICHIERJAVA
							, this.nomSimpleFichierJava);
		
		/* Ajout des lignes du début. */
		pListe.addAll(listeLignesDebutAAjouter);
		
		/* CORPS. */
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributs.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			
			final String aAjouter 
				= DECALAGE_CODE + "clone." 
						+ this.fournirSetter(nomAttribut) 
						+ "(this." + nomAttribut + ");";
			
			pListe.add(aAjouter);
		}
		
		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/clone/code_corps_fin_clone_abstractclass.txt";
	
		final File fichierFin = new File(cheminFichierFin);
	
		final List<String> listeLignesFin 
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);
		
		/* Ajout des lignes du fin. */
		pListe.addAll(listeLignesFin);
		
	} // Fin de creerCodeClone(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocToString(
			final List<String> pListe) throws Exception {
		
		/* Création des lignes. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ TEMPLATE_INHERITS_OVERRIDE;
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignes);
		
	} // Fin de creerJavadocToString(...)._________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeToString(
			final List<String> pListe) throws Exception {
		
		/* DEBUT. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/toString/code_debut_toString_abstractclass.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		final List<String> listeLignesDebutAAjouter 
			= this.substituerVariablesDansLigne(
					listeLignesDebut
						, VARIABLE_NOMSIMPLEFICHIERJAVA
							, this.nomSimpleFichierJava);
		
		/* Ajout des lignes du début. */
		pListe.addAll(listeLignesDebutAAjouter);

		
		/* CORPS. */
		final String cheminFichierCourant 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/toString/code_corps_courant_toString_abstractclass.txt";
		
		final File fichierCourant = new File(cheminFichierCourant);
		
		final List<String> listeLignesCourant 
		= this.lireStringsDansFile(fichierCourant, CHARSET_UTF8);
		
		final String cheminFichierDernier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/toString/code_corps_dernier_toString_abstractclass.txt";
		
		final File fichierDernier = new File(cheminFichierDernier);
		
		final List<String> listeLignesDernier 
		= this.lireStringsDansFile(fichierDernier, CHARSET_UTF8);
		
		List<String> listeAAjouter = null;
		final int nbreAttributs = this.mapAttributs.size();
		int compteur = 0;
		
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributs.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			
			if (compteur < nbreAttributs) {
				
				listeAAjouter 
				= this.substituerVariablesDansLigne(
						listeLignesCourant
							, VARIABLE_NOMATTRIBUT
								, nomAttribut);
				
				pListe.addAll(listeAAjouter);
				
			} else {
				
				listeAAjouter
				= this.substituerVariablesDansLigne(
						listeLignesDernier
							, VARIABLE_NOMATTRIBUT
								, nomAttribut);
				
				pListe.addAll(listeAAjouter);
				
			}
			
		}
				
		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/toString/code_fin_toString_abstractclass.txt";
	
		final File fichierFin = new File(cheminFichierFin);
	
		final List<String> listeLignesFin 
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);
		
		/* Ajout des lignes du fin. */
		pListe.addAll(listeLignesFin);
		
	} // Fin de creerCodeToString(...).____________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetEnTeteCsv(
			final List<String> pListe) throws Exception {
		
		/* Création des lignes. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ TEMPLATE_INHERITS_OVERRIDE;
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignes);

	} // Fin de creerJavadocGetEnTeteCsv(...)._____________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetEnTeteCsv(
			final List<String> pListe) throws Exception {
				
		/* Création des lignes. */
		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/getEnTeteCsv/code_getEnTeteCsv_abstractclass.txt";
	
		final File fichier = new File(cheminFichier);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubst1 
		= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_LIGNECSV
						, this.fournirCsv());
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignesSubst1);
		
	} // Fin de creerCodeGetEnTeteCsv(...).________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocToStringCsv(
			final List<String> pListe) throws Exception {
		
		/* Création des lignes. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ TEMPLATE_INHERITS_OVERRIDE;
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignes);

	} // Fin de creerJavadocToStringCsv(...).______________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeToStringCsv(
			final List<String> pListe) throws Exception {
		
		/* DEBUT. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/toStringCsv/code_debut_toStringCsv_abstractclass.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignesDebut);
		
		
		/* CORPS. */
		final String cheminFichierCorps 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/toStringCsv/code_corps_toStringCsv_abstractclass.txt";
		
		final File fichierCorps = new File(cheminFichierCorps);
		
		final List<String> listeLignesCorps 
		= this.lireStringsDansFile(fichierCorps, CHARSET_UTF8);
				
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributs.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			
			final List<String> listSubst1 
				= this.substituerVariablesDansLigne(
						listeLignesCorps
						, VARIABLE_NOMATTRIBUT
							, nomAttribut);
			
			final List<String> listSubst2 
			= this.substituerVariablesDansLigne(
					listSubst1
					, VARIABLE_GETTER
						, this.fournirGetter(nomAttribut));
			
			/* Ajout des lignes. */
			pListe.addAll(listSubst2);
			
		}
		

		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/toStringCsv/code_fin_toStringCsv_abstractclass.txt";
	
		final File fichierFin = new File(cheminFichierFin);
	
		final List<String> listeLignesFin 
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignesFin);
		
	} // Fin de creerCodeToStringCsv(...)._________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetEnTeteColonne(
			final List<String> pListe) throws Exception {
		
		/* Création des lignes. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ TEMPLATE_INHERITS_OVERRIDE;
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignes);

	} // Fin de creerJavadocGetEnTeteColonne(...)._________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetEnTeteColonne(
			final List<String> pListe) throws Exception {
		
		/* DEBUT. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/getEnTeteColonne/code_debut_getEnTeteColonne_abstractclass.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignesDebut);
		
		
		/* CORPS. {$iteration}*/
		final String cheminFichierCorps 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/getEnTeteColonne/code_corps_getEnTeteColonne_abstractclass.txt";
		
		final File fichierCorps = new File(cheminFichierCorps);
		
		final List<String> listeLignesCorps 
		= this.lireStringsDansFile(fichierCorps, CHARSET_UTF8);
				
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributs.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		int compteur = 0;
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			
			final List<String> listSubst1 
				= this.substituerVariablesDansLigne(
						listeLignesCorps
						, VARIABLE_NOMATTRIBUT
							, nomAttribut);
			
			final List<String> listSubst2 
			= this.substituerVariablesDansLigne(
					listSubst1
					, "{$iteration}"
						, String.valueOf(compteur));
			
			/* Ajout des lignes. */
			pListe.addAll(listSubst2);
			
		}

		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/getEnTeteColonne/code_fin_getEnTeteColonne_abstractclass.txt";
	
		final File fichierFin = new File(cheminFichierFin);
	
		final List<String> listeLignesFin 
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignesFin);
		
	} // Fin de creerCodeGetEnTeteColonne(...).____________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetValeurColonne(
			final List<String> pListe) throws Exception {
		
		/* Création des lignes. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ TEMPLATE_INHERITS_OVERRIDE;
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignes);

	} // Fin de creerJavadocGetValeurColonne(...)._________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetValeurColonne(
			final List<String> pListe) throws Exception {
		
		/* DEBUT. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/getValeurColonne/code_debut_getValeurColonne_abstractclass.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignesDebut);
		
		
		/* CORPS. {$iteration}*/
		final String cheminFichierCorpsString 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/getValeurColonne/code_corpsString_getValeurColonne_abstractclass.txt";
		
		final File fichierCorpsString = new File(cheminFichierCorpsString);
		
		final List<String> listeLignesCorpsString 
		= this.lireStringsDansFile(fichierCorpsString, CHARSET_UTF8);
		
		final String cheminFichierCorpsSansString 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/getValeurColonne/code_corpsPasString_getValeurColonne_abstractclass.txt";
		
		final File fichierCorpsSansString = new File(cheminFichierCorpsSansString);
		
		final List<String> listeLignesCorpsSansString 
		= this.lireStringsDansFile(fichierCorpsSansString, CHARSET_UTF8);
				
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributs.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		int compteur = 0;
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			if (StringUtils.equalsIgnoreCase(typeAttribut, "String")) {
				
				final List<String> listSubst1 
				= this.substituerVariablesDansLigne(
						listeLignesCorpsString
						, VARIABLE_GETTER
							, this.fournirGetter(nomAttribut));
			
				final List<String> listSubst2 
				= this.substituerVariablesDansLigne(
						listSubst1
						, "{$iteration}"
							, String.valueOf(compteur));
				
				/* Ajout des lignes. */
				pListe.addAll(listSubst2);
			
			} else {
								
				final List<String> listSubst1 
				= this.substituerVariablesDansLigne(
						listeLignesCorpsSansString
						, VARIABLE_GETTER
							, this.fournirGetter(nomAttribut));
			
				final List<String> listSubst2 
				= this.substituerVariablesDansLigne(
						listSubst1
						, "{$iteration}"
							, String.valueOf(compteur));
				
				/* Ajout des lignes. */
				pListe.addAll(listSubst2);				
			}			
		}

		
		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/getValeurColonne/code_fin_getValeurColonne_abstractclass.txt";
	
		final File fichierFin = new File(cheminFichierFin);
	
		final List<String> listeLignesFin 
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignesFin);

	} // Fin de creerCodeGetValeurColonne(...).____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetId(
			final List<String> pListe) throws Exception	{
		
		/* Création des lignes. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ TEMPLATE_INHERITS_OVERRIDE;
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignes);

	} // Fin de creerJavadocGetId(...).____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerEntityGetId(
			final List<String> pListe) throws Exception {
		return; // NOPMD by daniel.levy on 17/01/18 12:28
	} // Fin de creerEntityGetId(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetId(
			final List<String> pListe) throws Exception {
		
		/* Création des lignes. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/methodGetId_code_abstractclass.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignes);

	} // Fin de creerCodeGetId(...)._______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocSetId(
			final List<String> pListe) throws Exception	{

		/* Création des lignes. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ TEMPLATE_INHERITS_OVERRIDE;
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignes);

	} // Fin de creerJavadocSetId(...).____________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeSetId(
			final List<String> pListe) throws Exception {

		/* Création des lignes. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/methodSetId_code_abstractclass.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignes);
		
	} // Fin de creerCodeSetId(...)._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListe) throws Exception {
		
		/* Création des lignes. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ TEMPLATE_INHERITS_OVERRIDE;
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignes);

	} // Fin de creerJavadocGetter(...).___________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeEntityGetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListe) throws Exception {

		// TODO Auto-generated method stub
		
	} // Fin de creerCodeEntityGetter(...).________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListe) throws Exception {

		/* signature. */
		final String signature 
		= PUBLIC 
		+ pTypeAttribut 
		+ SEP_ESPACE 
		+ this.fournirGetter(pNomAttribut) + " {";
	
		pListe.add(signature);
		
		/* code. */
		final String code 
			= DECALAGE_CODE 
			+ "return this." + pNomAttribut + POINT_VIRGULE;
		
		pListe.add(code);
		
		/* ligne de fin. */
		String ligneFin = null;
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append(DECALAGE_METHODE);
		stb.append(CROCHET_FERMANT);
		stb.append(" // Fin de ");
		stb.append(this.fournirGetter(pNomAttribut));
		stb.append(POINT);
		
		final String provisoire = stb.toString();
		final int longueurProvisoire = provisoire.length();
		
		final int nombreTirets 
			= 74 - longueurProvisoire - DECALAGE_CODE.length();
		
		for (int i=0; i < nombreTirets; i++) {
			stb.append('_');
		}

		ligneFin = stb.toString();
		
		pListe.add(ligneFin);
		
	} // Fin de creerCodeGetter(...).______________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocSetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListe) throws Exception {

		/* Création des lignes. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ TEMPLATE_INHERITS_OVERRIDE;
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes. */
		pListe.addAll(listeLignes);
		
	} // Fin de creerJavadocSetter(...).___________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeSetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListe) throws Exception {

		/* signature. */
		final String signature1 
			= PUBLIC + "void " 
						+ this.fournirSetter(pNomAttribut) + "(";
		
		final String signature2 
			= DECALAGE_CODE + "\t" 
					+ FINAL + pTypeAttribut 
					+ SEP_ESPACE 
					+ this.fournirParametre(pNomAttribut) + ") {";
		
		pListe.add(signature1);
		pListe.add(signature2);
		
		/* code. */
		final String code 
			= DECALAGE_CODE 
			+ "this." + pNomAttribut 
			+ EGAL 
			+ this.fournirParametre(pNomAttribut) + POINT_VIRGULE;
		
		pListe.add(code);
		
		/* ligne de fin. */
		String ligneFin = null;
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append(DECALAGE_METHODE);
		stb.append(CROCHET_FERMANT);
		stb.append(" // Fin de ");
		stb.append(this.fournirSetter(pNomAttribut));
		stb.append("()");
		stb.append(POINT);
		
		final String provisoire = stb.toString();
		final int longueurProvisoire = provisoire.length();
		
		final int nombreTirets 
			= 74 - longueurProvisoire - DECALAGE_CODE.length();
		
		for (int i=0; i < nombreTirets; i++) {
			stb.append('_');
		}

		ligneFin = stb.toString();
		
		pListe.add(ligneFin);
		
	} // Fin de creerCodeSetter(...).______________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifianteGetter(
			final String pNomAttribut
				, final String pTypeAttribut) {
		
		final String ligneIdentifiant 
		= PUBLIC
				+ pTypeAttribut 
				+ SEP_ESPACE 
				+ this.fournirGetter(pNomAttribut);
		
		return ligneIdentifiant;
		
	} // Fin de fournirLigneIdentifianteGetter(...)._______________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifianteSetter(
			final String pNomAttribut
				, final String pTypeAttribut) {
		// TODO Auto-generated method stub
		return null;
	}


	

} // FIN DE LA CLASSE EcriveurAbstractClass.---------------------------------
