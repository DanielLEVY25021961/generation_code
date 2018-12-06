package levy.daniel.application.model.services.metier.generationcode.ecriveurs.model.metier;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesbundles.BundleConfigurationProjetManager;
import levy.daniel.application.model.services.metier.generationcode.ecriveurs.AbstractEcriveurFichiersJavaDetaille;




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
	 * methodCompareTo : List&lt;String&gt; :<br/>
	 * Lignes de la méthode compareTo().<br/>
	 */
	protected transient List<String> methodCompareTo;
	
	
	/**
	 * methodClone : List&lt;String&gt; :<br/>
	 * Lignes de la méthode clone().<br/>
	 */
	protected transient List<String> methodClone;

	
	/**
	 * methodToString : List<String> :<br/>
	 * Lignes de la méthode toString().<br/>
	 */
	protected transient List<String> methodToString;
	
	
	/**
	 * methodGetEnTeteCsv : List&lt;String&gt; :<br/>
	 * Lignes de la méthode getEnTeteCsv().<br/>
	 */
	protected transient List<String> methodGetEnTeteCsv;

	
	/**
	 * methodToStringCsv : List&lt;String&gt; :<br/>
	 * Lignes de la méthode toStringCsv().<br/>
	 */
	protected transient List<String> methodToStringCsv;
	

	/**
	 * methodGetEnTeteColonne : List&lt;String&gt; :<br/>
	 * Lignes de la méthode getEnTeteColonne().<br/>
	 */
	protected transient List<String> methodGetEnTeteColonne;

	
	/**
	 * methodGetValeurColonne : List&lt;String&gt; :<br/>
	 * Lignes de la méthode getValeurColonne().<br/>
	 */
	protected transient List<String> methodGetValeurColonne;
	
	
	/**
	 * methodGetId : List&lt;String&gt; :<br/>
	 * Lignes de la méthode getId().<br/>
	 */
	protected transient List<String> methodGetId;
	
	
	/**
	 * methodSetId : List&lt;String&gt; :<br/>
	 * Lignes de la méthode setId().<br/>
	 */
	protected transient List<String> methodSetId;
	
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(AbstractEcriveurMetier.class);


	// *************************METHODES************************************/
	
	
	
	 /**
	 * method CONSTRUCTEUR AbstractEcriveurMetier() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractEcriveurMetier() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * method ecrireLignesAttributId(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit les lignes <b>attributId</b>.</li>
	 * <li>alimente this.attributId</li>
	 * <li>ecrit la javadoc et la ligne de code.</li>
	 * <li>ajoute 2 lignes vides ensuite.</li>
	 * <li>Ne fait rien si les lignes existent déjà.</li>
	 * <li>
	 * par exemple : <br/>
	 * <code>protected Long id;</code>
	 * </li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireLignesAttributId(
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
	
			this.attributId = new ArrayList<String>();
			
			/* Crée le attributId. */
			this.creerAttributId(this.attributId);
			
			/* Recherche la ligne identifiant attributId. */
			final String ligneIdentifiant 
				= this.fournirDebutAttributId();
	
			/* Ne fait rien si attributId a déjà été écrit. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}
	
			/* ajoute 2 lignes vides. */
			this.ajouterLignesVides(2, this.attributId);
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.attributId) {
	
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
				LOG.fatal("Impossible de créer attributId", e);
			}
		}
	
	} // Fin de ecrireLignesAttributId().__________________________________
	
	
	
	/**
	 * method creerAttributId(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>Crée la liste des lignes attributId.</li>
	 * </ul>
	 * 
	 * @param pListe : List&lt;String&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	protected abstract void creerAttributId(List<String> pListe) 
					throws Exception;
	
	
	
	/**
	 * method ecrireAttributs(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit tous les <b>attributs</b> stockés dans la 
	 * Map this.mapAttributs.</li>
	 * <li>ecrit la javadoc et la ligne de code pour chaque attribut.</li>
	 * <li>ajoute 2 lignes vides ensuite.</li>
	 * <li>Ne fait rien si les lignes existent déjà.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * ne fait rien si this.mapAttributs == null.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception 
	 */
	protected final void ecrireAttributs(
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
	
		/* ne fait rien si this.mapAttributs == null. */
		if (this.mapAttributs == null) {
			return;
		}
		
		
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributs.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		/* BOUCLE SUR LES ATTRIBUTS. */
		while (ite.hasNext()) {
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			final List<String> attributListe = new ArrayList<String>();
			final List<String> javadocListe = new ArrayList<String>();
			final List<String> codeListe = new ArrayList<String>();
			
			/* Création de la Javadoc. */
			this.creerJavadocAttribut(
					javadocListe, nomAttribut, typeAttribut);
			
			/* Création du code. */
			this.creerCodeAttribut(
					codeListe, nomAttribut, typeAttribut);
			
			/* ajout de la javadoc à attributListe. */
			attributListe.addAll(javadocListe);
			/* ajout du code à attributListe. */
			attributListe.addAll(codeListe);
	
			
			final String ligneIdentifiant 
				= "	protected " + typeAttribut + SEP_ESPACE + nomAttribut;
			
			try {
				
				/* Ne fait rien si l'attribut a déjà été écrit. */
				if (this.existLigneCommencant(
						pFile, CHARSET_UTF8, ligneIdentifiant)) {
					return;
				}
	
				/* ajoute 2 lignes vides. */
				this.ajouterLignesVides(2, attributListe);
				
				/* *************** */
				/* ENREGISTREMENT. */
				/* *************** */
				for (final String ligne : attributListe) {
	
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
					LOG.fatal("Impossible de créer l' attribut " 
								+ nomAttribut, e);
				}
			}
			
		} // Fin de ite.hasNext().________________________________
		
	} // Fin de ecrireAttributs(...).______________________________________
	
	
	
	/**
	 * method creerJavadocAttribut(
	 * List&lt;String&gt; pListe
	 * , String pNomAttribut
	 * , String pTypeAttribut) :<br/>
	 * <ul>
	 * <li>injecte la javadoc de l'attribut pNomAttribut 
	 * dans la liste pListe.</li>
	 * </ul>
	 * ne fait rien si pListe == null.<br/>
	 * ne fait rien si pNomAttribut est blank.<br/>
	 * <br/>
	 *
	 * @param pListe : List&lt;String&gt; : 
	 * javadoc sous forme de liste de Strings.<br/>
	 * @param pNomAttribut : String : Nom de l'attribut.<br/>
	 * @param pTypeAttribut : String : type de l'attribut.<br/>
	 * 
	 * @throws Exception 
	 */
	protected abstract void creerJavadocAttribut(
			List<String> pListe
				, String pNomAttribut
					, String pTypeAttribut) throws Exception;
	
	
	
	/**
	 * method creerCodeAttribut(
	 * List&lt;String&gt; pListe 
	 * , String pNomAttribut
	 * , String pTypeAttribut) :<br/>
	 * <ul>
	 * <li>injecte le code de l'attribut pNomAttribut 
	 * dans la liste pListe.</li>
	 * </ul>
	 * ne fait rien si pListe == null.<br/>
	 * ne fait rien si pNomAttribut est blank.<br/>
	 * <br/>
	 *
	 * @param pListe : List&lt;String&gt; : 
	 * code sous forme de liste de Strings.<br/>
	 * @param pNomAttribut : String : Nom de l'attribut.<br/>
	 * @param pTypeAttribut : String : type de l'attribut.<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeAttribut(
			List<String> pListe
				, String pNomAttribut
					, String pTypeAttribut) throws Exception;
	
	
			
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
		
		stb.append("this(null");
		
		int compteur2 = 0;
		
		final int tailleMap = this.mapAttributs.size();
		
		final Set<Entry<String, String>> entrySet2 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite2 
			= entrySet2.iterator();
		
		while (ite2.hasNext()) {
			
			compteur2++;
			
			ite2.next();
			
			String aAjouter = null;
			
			if (compteur2 < tailleMap) {
				aAjouter = ", null";
			} else {
				aAjouter = ", null);";
			}
			
			stb.append(aAjouter);
		}
		
		listCode.add(DECALAGE_CODE + stb.toString());
		
		listCode.add("");
		
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
						+ "le code du constructeur", e);
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
			final String typeAttribut = entry.getValue();
			
			String ligneACreerBase = null;
			
			if (compteur == 1) {
				
				ligneACreerBase 
				= LIGNE_VIDE_JAVADOC_MEMBRE
				+ SEP_ESPACE
				+ typeAttribut 
				+ SEP_ESPACE 
				+ this.fournirParametre(nomAttribut);
			}
			else {
				
				ligneACreerBase 
				= VIRGULE_JAVADOC_MEMBRE 
				+ typeAttribut 
				+ SEP_ESPACE 
				+ this.fournirParametre(nomAttribut);
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
			final String typeAttribut = entry2.getValue();
			
			final String ligneACreer1 
				= LIGNE_PARAM_JAVADOC 
				+ fournirParametre(nomAttribut) 
				+ SEP_2PTS_AERE 
				+ typeAttribut 
				+ SEP_2PTS_AERE;
			
			final String ligneACreer2 
				= "	 *" 
				+ SEP_ESPACE
				+ nomAttribut 
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
			final String typeAttribut = entry.getValue();
			
			String ligneACreer = null;
			String ligneACreerBase = null;
			
			if (compteur == 1) {
				
				ligneACreerBase 
					= decalage + FINAL
						+ typeAttribut + SEP_ESPACE 
							+ this.fournirParametre(nomAttribut);
								
			} else {
				
				final StringBuffer stb = new StringBuffer();
				
				stb.append(decalage);
				stb.append(TAB);
				
				decalage = stb.toString();
				
				ligneACreerBase 
					= decalage + ", " + FINAL
						+ typeAttribut + SEP_ESPACE 
						+ this.fournirParametre(nomAttribut);
				
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
	
		final StringBuilder stb = new StringBuilder();
		
		stb.append("this(null");
		
		int compteur2 = 0;
		
		final Set<Entry<String, String>> entrySet2 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite2 = entrySet2.iterator();
		
		while (ite2.hasNext()) {
			
			compteur2++;
			
			final Entry<String, String> entry2 = ite2.next();
			
			final String nomAttribut = entry2.getKey();
	
			String aAjouter = null;
			
			if (compteur2 < tailleMap) {
				aAjouter = ", " + this.fournirParametre(nomAttribut);
			} else {
				aAjouter = ", " + this.fournirParametre(nomAttribut) + ");";
			}
			
			stb.append(aAjouter);
		}
		
		listCode.add(DECALAGE_CODE + stb.toString());
		
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
	 * method ecrireConstructeurCompletBase(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité du <b>constructeur complet base</b>.</li>
	 * <li>écrit la javadoc du constructeur complet base.</li>
	 * <li>écrit le code du constructeur complet base.</li>
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
	protected final void ecrireConstructeurCompletBase(
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
	
		/* écrit la javadoc du constructeur complet base. */
		this.ecrireJavadocConstructeurCompletBase(pFile);
		
		/* écrit le code du constructeur complet base. */
		this.ecrireCodeConstructeurCompletBase(pFile);
		
	} // Fin de ecrireConstructeurCompletBase(...).________________________
	
	
	
	/**
	 * method ecrireJavadocConstructeurCompletBase(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>Génère la <b>javadoc du constructeur complet base</b>.</li>
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
	private void ecrireJavadocConstructeurCompletBase(
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
		listJavadoc.add(ID_JAVADOC_MEMBRE);
		
		final int tailleMap = this.mapAttributs.size();
		
		final Set<Entry<String, String>> entrySet 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		int compteur = 0;
		
		while (ite.hasNext()) {
			
			compteur++;
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			final String ligneACreerBase 
				= VIRGULE_JAVADOC_MEMBRE 
				+ typeAttribut 
				+ SEP_ESPACE 
				+ this.fournirParametre(nomAttribut);
			
			String ligneACreer = null;
			
			if (compteur < tailleMap) {
				ligneACreer = ligneACreerBase;
			} else {
				ligneACreer = ligneACreerBase + ") :<br/>";
			}
			
			listJavadoc.add(ligneACreer);
		}
		
		listJavadoc.add(UL_OUVRANT_JAVADOC_MEMBRE);
		listJavadoc.add(LIGNE_CONSTR_COMPLET_BASE_JAVADOC);
		listJavadoc.add(AVEC_ID_JAVADOC);
		listJavadoc.add(UL_FERMANT_JAVADOC_MEMBRE);
		listJavadoc.add(LIGNE_VIDE_JAVADOC_MEMBRE);
		listJavadoc.add(PARAM_ID_JAVADOC);
	
		
		final Set<Entry<String, String>> entrySet2 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite2 = entrySet2.iterator();
		
		while (ite2.hasNext()) {
			
			final Entry<String, String> entry2 = ite2.next();
			
			final String nomAttribut = entry2.getKey();
			final String typeAttribut = entry2.getValue();
			
			final String ligneACreer1 
				= LIGNE_PARAM_JAVADOC 
				+ fournirParametre(nomAttribut) 
				+ SEP_2PTS_AERE 
				+ typeAttribut 
				+ SEP_2PTS_AERE;
			
			final String ligneACreer2 
				= "	 *"
				+ SEP_ESPACE
				+ nomAttribut 
				+ " du " 
				+ this.nomSimpleFichierJava 
				+ POINT_BR;
						
			listJavadoc.add(ligneACreer1);
			listJavadoc.add(ligneACreer2);
		}
	
		
		listJavadoc.add(FIN_JAVADOC_MEMBRE);
		
		
		
		try {
	
			/* Recherche la ligne identifiant. */
			final String ligneIdentifiant 
				= LIGNE_CONSTR_COMPLET_BASE_JAVADOC;
	
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
		
	} // Fin de ecrireJavadocConstructeurCompletBase(...)._________________
	
	
	
	/**
	 * method ecrireCodeConstructeurCompletBase(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>génère le <b>code du constructeur complet base</b>.</li>
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
	protected abstract void ecrireCodeConstructeurCompletBase(
			File pFile) throws Exception;
	
	
	
	/**
	 * method ecrireHashCode(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>compareTo(...)</b></li>
	 * <li>écrit la javadoc de hashCode().</li>
	 * <li>écrit le code de hashCode().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
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
	protected final void ecrireHashCode(
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
	
		final List<String> listeMethode = new ArrayList<String>();
		
		/* DEBUT. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/hashcode/debut_hashcode.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		/* Ajout des lignes du début. */
		listeMethode.addAll(listeLignesDebut);
	
		/* CORPS. */
		final String cheminFichierCorps 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/hashcode/hashcode.txt";
	
		final File fichierCorps = new File(cheminFichierCorps);
	
		final List<String> listeLignesCorps 
			= this.lireStringsDansFile(fichierCorps, CHARSET_UTF8);
		
		final Set<Entry<String, String>> entrySetCorps 
		= this.mapAttributsEquals.entrySet();
	
		final Iterator<Entry<String, String>> iteCorps 
			= entrySetCorps.iterator();
		
		while (iteCorps.hasNext()) {
			
			final Entry<String, String> entryCorps = iteCorps.next();
			
			final String nomAttribut = entryCorps.getKey();
			
			final List<String> lignesAAjouter 
				= this.substituerVariablesDansLigne(
						listeLignesCorps, VARIABLE_NOMATTRIBUT, nomAttribut);
			
			/* Ajout des lignes du corps. */
			listeMethode.addAll(lignesAAjouter);
	
		}
		
		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/hashcode/fin_hashcode.txt";
	
		final File fichierFin = new File(cheminFichierFin);
	
		final List<String> listeLignesFin 
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);
		
		/* Ajout des lignes du fin. */
		listeMethode.addAll(listeLignesFin);
	
		
		/* ENREGISTREMENT *********/
		
		final String ligneIdentifiant = "	public int hashCode()";
		
		try {
	
			/* Ne fait rien si le code a déjà été écrit. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : listeMethode) {
	
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
				LOG.fatal("Impossible de créer le code du hashcode", e);
			}
		}
				
	} // Fin de ecrireHashCode(...)._______________________________________
	
	
		
	/**
	 * method ecrireEquals(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>equals(...)</b></li>
	 * <li>écrit la javadoc de equals().</li>
	 * <li>écrit le code de equals().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
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
	protected final void ecrireEquals(
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
		
		final List<String> listeMethode = new ArrayList<String>();
	
		/* écrit la javadoc. */
		this.creerJavadocEquals(listeMethode);
		
		/* écrit le code. */
		this.creerCodeEquals(listeMethode);
		
		
		/* ENREGISTREMENT *********/
		
		final String ligneIdentifiant = "	public boolean equals";
		
		try {
	
			/* Ne fait rien si le code a déjà été écrit. */
			if (this.existLigneCommencant(
					pFile, CHARSET_UTF8, ligneIdentifiant)) {
				return;
			}
	
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : listeMethode) {
	
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
				LOG.fatal("Impossible de créer le code du equals()", e);
			}
		}
				
	} // Fin de ecrireEquals(...)._________________________________________
	
	
	
	/**
	 * method creerJavadocEquals(
	 * List&lt;String&gt; pListeMethode) :<br/>
	 * <ul>
	 * <li>génère la javadoc de equals().</li>
	 * <li>insère la javadoc générée dans pListeMethode.</li>
	 * </ul>
	 *
	 * @param pListeMethode : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	private void creerJavadocEquals(
			final List<String> pListeMethode) throws Exception {
		
		/* DEBUT. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/equals/debut_javadoc_equals.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		final List<String> listeLignesDebutAAjouter 
			= this.substituerVariablesDansLigne(
					listeLignesDebut
						, VARIABLE_NOMSIMPLEFICHIERJAVA
							, this.nomSimpleFichierJava);
		
		/* Ajout des lignes du début. */
		pListeMethode.addAll(listeLignesDebutAAjouter);
		
		/* CORPS. */
		final String cheminFichierCorps 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/equals/corps_javadoc_equals.txt";
	
		final File fichierCorps = new File(cheminFichierCorps);
	
		final List<String> listeLignesCorps 
			= this.lireStringsDansFile(fichierCorps, CHARSET_UTF8);
		
		final Set<Entry<String, String>> entrySetCorps 
		= this.mapAttributsEquals.entrySet();
	
		final Iterator<Entry<String, String>> iteCorps 
			= entrySetCorps.iterator();
		
		while (iteCorps.hasNext()) {
			
			final Entry<String, String> entryCorps = iteCorps.next();
			
			final String nomAttribut = entryCorps.getKey();
			
			final List<String> lignesAAjouter 
				= this.substituerVariablesDansLigne(
						listeLignesCorps
						, VARIABLE_NOMATTRIBUT, nomAttribut);
			
			/* Ajout des lignes du corps. */
			pListeMethode.addAll(lignesAAjouter);
	
		}
	
		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/equals/fin_javadoc_equals.txt";
	
		final File fichierFin = new File(cheminFichierFin);
	
		final List<String> listeLignesFin 
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);
		
		/* Ajout des lignes du fin. */
		pListeMethode.addAll(listeLignesFin);
		
	} // Fin de creerJavadocEquals(...).___________________________________
	
	
	
	/**
	 * method creerCodeEquals(
	 * List&lt;String&gt; pListeMethode) :<br/>
	 * <ul>
	 * <li>génère le code de equals().</li>
	 * <li>insère le code généré dans pListeMethode.</li>
	 * </ul>
	 *
	 * @param pListeMethode : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	private void creerCodeEquals(
			final List<String> pListeMethode) throws Exception {
		
		/* DEBUT. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/equals/debut_equals.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
	
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		final List<String> listeLignesDebutAAjouter 
			= this.substituerVariablesDansLigne(
					listeLignesDebut
						, VARIABLE_NOMSIMPLEFICHIERJAVA
							, this.nomSimpleFichierJava);
		
		/* Ajout des lignes du début. */
		pListeMethode.addAll(listeLignesDebutAAjouter);
		
		/* CORPS. */
		final String cheminFichierCorps 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/equals/corps_equals.txt";
	
		final File fichierCorps = new File(cheminFichierCorps);
	
		final List<String> listeLignesCorps 
			= this.lireStringsDansFile(fichierCorps, CHARSET_UTF8);
		
		final Set<Entry<String, String>> entrySetCorps 
		= this.mapAttributsEquals.entrySet();
	
		final Iterator<Entry<String, String>> iteCorps 
			= entrySetCorps.iterator();
		
		while (iteCorps.hasNext()) {
			
			final Entry<String, String> entryCorps = iteCorps.next();
			
			final String nomAttribut = entryCorps.getKey();
			
			final List<String> lignesAAjouter 
				= this.substituerVariablesDansLigne(
						listeLignesCorps, VARIABLE_NOMATTRIBUT, nomAttribut);
			
			/* Ajout des lignes du corps. */
			pListeMethode.addAll(lignesAAjouter);
	
		}
	
		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/equals/fin_equals.txt";
	
		final File fichierFin = new File(cheminFichierFin);
	
		final List<String> listeLignesFin 
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);
		
		/* Ajout des lignes du fin. */
		pListeMethode.addAll(listeLignesFin);
				
	} // Fin de creerCodeEquals(...).______________________________________
	
	
	
	/**
	 * method ecrireCompareTo() :<br/>
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>compareTo(...)</b></li>
	 * <li>écrit la javadoc de compareTo().</li>
	 * <li>écrit le code de compareTo().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
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
	protected final void ecrireCompareTo(
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
		
		this.methodCompareTo = new ArrayList<String>();
	
		/* écrit la javadoc. */
		this.creerJavadocCompareTo(this.methodCompareTo);
		
		/* écrit le code. */
		this.creerCodeCompareTo(this.methodCompareTo);
		
		/* ajoute 3 lignes. */
		this.ajouterLignesVides(3, this.methodCompareTo);
		
		
		/* ENREGISTREMENT *********/		
		final String ligneIdentifiant 
			= this.fournirLigneIdentifiantCompareTo();
		
		/* Ne fait rien si le code a déjà été écrit. */
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}
	
		
		try {
	
	
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodCompareTo) {
	
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
				LOG.fatal("Impossible de créer le code du compareTo", e);
			}
		}
				
		
	} // Fin de ecrireCompareTo(...).______________________________________
	
	
	
	/**
	 * method creerJavadocCompareTo(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de compareTo().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocCompareTo(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method creerCodeCompareTo(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de compareTo().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeCompareTo(
			List<String> pListe) throws Exception;
	
	
	/**
	 * method fournirLigneIdentifiantCompareTo() :<br/>
	 * <ul>
	 * <li>fournit un début de ligne permettant d'identifier 
	 * la méthode compareTo.</li>
	 * </ul>
	 *
	 * @return : String : début de ligne identifiant.<br/>
	 */
	protected abstract String fournirLigneIdentifiantCompareTo();
	
	
	
	/**
	 * method ecrireClone(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>clone()</b></li>
	 * <li>écrit la javadoc de clone().</li>
	 * <li>écrit le code de clone().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireClone(
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
			
			this.methodClone = new ArrayList<String>();
			
			/* Crée la javadoc. */
			this.creerJavadocClone(this.methodClone);
			
			/* Crée le code. */
			this.creerCodeClone(this.methodClone);
			
			
			/* ENREGISTREMENT *********/
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodClone);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
	
			/* ajoute 3 lignes. */
			this.ajouterLignesVides(3, this.methodClone);
	
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodClone) {
				
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
				LOG.fatal("Impossible de créer la méthode clone()", e);
			}
		}
				
	} // Fin de ecrireClone(...).__________________________________________
	
	
	
	/**
	 * method creerJavadocClone(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de clone().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocClone(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method creerCodeClone(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de clone().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeClone(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method ecrireToString(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>toString()</b></li>
	 * <li>écrit la javadoc de toString().</li>
	 * <li>écrit le code de toString().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireToString(
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
			
			this.methodToString = new ArrayList<String>();
			
			/* Crée la javadoc. */
			this.creerJavadocToString(this.methodToString);
			
			/* Crée le code. */
			this.creerCodeToString(this.methodToString);
			
			
			/* ENREGISTREMENT *********/
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodToString);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
	
			/* ajoute 3 lignes. */
			this.ajouterLignesVides(3, this.methodToString);
	
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodToString) {
				
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
				LOG.fatal("Impossible de créer la méthode toString()", e);
			}
		}
				
	} // Fin de ecrireToString(...).__________________________________________
	
	
	
	/**
	 * method creerJavadocToString(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de toString().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocToString(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method creerCodeToString(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de toString().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeToString(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method ecrireGetEnTeteCsv(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>getEnTeteCsv()</b></li>
	 * <li>écrit la javadoc de getEnTeteCsv().</li>
	 * <li>écrit le code de getEnTeteCsv().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireGetEnTeteCsv(
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
			
			this.methodGetEnTeteCsv = new ArrayList<String>();
			
			/* Crée la javadoc. */
			this.creerJavadocGetEnTeteCsv(this.methodGetEnTeteCsv);
			
			/* Crée le code. */
			this.creerCodeGetEnTeteCsv(this.methodGetEnTeteCsv);
			
			
			/* ENREGISTREMENT *********/
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodGetEnTeteCsv);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
	
			/* ajoute 3 lignes. */
			this.ajouterLignesVides(3, this.methodGetEnTeteCsv);
	
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodGetEnTeteCsv) {
				
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
				LOG.fatal("Impossible de créer la méthode getEnTeteCsv()", e);
			}
		}
				
	} // Fin de ecrireGetEnTeteCsv(...).__________________________________________
	
	
	
	/**
	 * method creerJavadocGetEnTeteCsv(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de getEnTeteCsv().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocGetEnTeteCsv(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method creerCodeGetEnTeteCsv(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de getEnTeteCsv().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeGetEnTeteCsv(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method ecrireToStringCsv(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>toStringCsv()</b></li>
	 * <li>écrit la javadoc de toStringCsv().</li>
	 * <li>écrit le code de toStringCsv().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireToStringCsv(
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
			
			this.methodToStringCsv = new ArrayList<String>();
			
			/* Crée la javadoc. */
			this.creerJavadocToStringCsv(this.methodToStringCsv);
			
			/* Crée le code. */
			this.creerCodeToStringCsv(this.methodToStringCsv);
			
			
			/* ENREGISTREMENT *********/
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodToStringCsv);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
	
			/* ajoute 3 lignes. */
			this.ajouterLignesVides(3, this.methodToStringCsv);
	
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodToStringCsv) {
				
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
				LOG.fatal("Impossible de créer la méthode toStringCsv()", e);
			}
		}
				
	} // Fin de ecrireToStringCsv(...).__________________________________________
	
	
	
	/**
	 * method creerJavadocToStringCsv(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de toStringCsv().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocToStringCsv(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method creerCodeToStringCsv(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de toStringCsv().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeToStringCsv(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method ecrireGetEnTeteColonne(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>getEnTeteColonne()</b></li>
	 * <li>écrit la javadoc de getEnTeteColonne().</li>
	 * <li>écrit le code de getEnTeteColonne().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireGetEnTeteColonne(
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
			
			this.methodGetEnTeteColonne = new ArrayList<String>();
			
			/* Crée la javadoc. */
			this.creerJavadocGetEnTeteColonne(this.methodGetEnTeteColonne);
			
			/* Crée le code. */
			this.creerCodeGetEnTeteColonne(this.methodGetEnTeteColonne);
			
			
			/* ENREGISTREMENT *********/
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodGetEnTeteColonne);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
	
			/* ajoute 3 lignes. */
			this.ajouterLignesVides(3, this.methodGetEnTeteColonne);
	
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodGetEnTeteColonne) {
				
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
				LOG.fatal("Impossible de créer la méthode getEnTeteColonne()", e);
			}
		}
				
	} // Fin de ecrireGetEnTeteColonne(...).__________________________________________
	
	
	
	/**
	 * method creerJavadocGetEnTeteColonne(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de getEnTeteColonne().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocGetEnTeteColonne(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method creerCodeGetEnTeteColonne(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de getEnTeteColonne().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeGetEnTeteColonne(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method ecrireGetValeurColonne(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>getValeurColonne()</b></li>
	 * <li>écrit la javadoc de getValeurColonne().</li>
	 * <li>écrit le code de getValeurColonne().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireGetValeurColonne(
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
			
			this.methodGetValeurColonne = new ArrayList<String>();
			
			/* Crée la javadoc. */
			this.creerJavadocGetValeurColonne(this.methodGetValeurColonne);
			
			/* Crée le code. */
			this.creerCodeGetValeurColonne(this.methodGetValeurColonne);
			
			
			/* ENREGISTREMENT *********/
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodGetValeurColonne);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
	
			/* ajoute 3 lignes. */
			this.ajouterLignesVides(3, this.methodGetValeurColonne);
	
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodGetValeurColonne) {
				
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
				LOG.fatal("Impossible de créer la méthode getValeurColonne()", e);
			}
		}
				
	} // Fin de ecrireGetValeurColonne(...).__________________________________________
	
	
	
	/**
	 * method creerJavadocGetValeurColonne(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de getValeurColonne().</li>
	 * <li>insère la javadoc générée dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocGetValeurColonne(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method creerCodeGetValeurColonne(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>génère le code de getValeurColonne().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeGetValeurColonne(
			List<String> pListe) throws Exception;
	
	
	
	/**
	 * method ecrireGetId(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>getId()</b></li>
	 * <li>alimente this.methodGetId</li>
	 * <li>écrit la javadoc de getId().</li>
	 * <li>écrit l'Entity de getId().</li>
	 * <li>écrit le code de getId().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireGetId(
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
			
			this.methodGetId = new ArrayList<String>();
			
			/* Crée la methode getId(). */
			this.creerJavadocGetId(
					this.methodGetId);
			this.creerEntityGetId(
					this.methodGetId);
			this.creerCodeGetId(
					this.methodGetId);
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodGetId);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			/* ajoute 3 lignes vide. */
			this.ajouterLignesVides(3, this.methodGetId);
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodGetId) {
				
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
				LOG.fatal("Impossible de créer la"
						+ " méthode getId()", e);
			}
		}
				
	} // Fin de ecrireGetId(...).__________________________________________
	
	
	
	/**
	 * method creerJavadocGetId(
	 * List&lt;String&gt pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de getId().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocGetId(List<String> pListe) 
			throws Exception;
	
	
	
	/**
	 * method creerEntityGetId(
	 * List&lt;String&gt pListe) :<br/>
	 * <ul>
	 * <li>génère l'Entity de getId().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerEntityGetId(List<String> pListe) 
			throws Exception;
	
	
	
	/**
	 * method creerCodeGetId(
	 * List&lt;String&gt pListe) :<br/>
	 * <ul>
	 * <li>génère le code de getId().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeGetId(List<String> pListe) 
			throws Exception;
	
	
		
	/**
	 * method ecrireSetId(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité de la méthode <b>setId()</b></li>
	 * <li>alimente this.methodSetId</li>
	 * <li>écrit la javadoc de setId().</li>
	 * <li>écrit le code de setId().</li>
	 * <li>ajoute 3 lignes vides ensuite.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireSetId(
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
			
			this.methodSetId = new ArrayList<String>();
			
			/* Crée la methode setId(). */
			this.creerJavadocSetId(
					this.methodSetId);
			
			this.creerCodeSetId(
					this.methodSetId);
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodSetId);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			/* ajoute 3 lignes vide. */
			this.ajouterLignesVides(3, this.methodSetId);
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodSetId) {
				
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
				LOG.fatal("Impossible de créer la"
						+ " méthode setId()", e);
			}
		}
				
	} // Fin de ecrireSetId(...).__________________________________________
	
	
	
	/**
	 * method creerJavadocSetId(
	 * List&lt;String&gt pListe) :<br/>
	 * <ul>
	 * <li>génère la javadoc de setId().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocSetId(List<String> pListe) 
			throws Exception;
	
	
	
	/**
	 * method creerCodeSetId(
	 * List&lt;String&gt pListe) :<br/>
	 * <ul>
	 * <li>génère le code de setId().</li>
	 * <li>insère le code généré dans pListe.</li>
	 * </ul>
	 *
	 * @param pListe : List&lt;String&gt; .<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeSetId(List<String> pListe) 
			throws Exception;
	
	
	
	/**
	 * method ecrireAccesseurs(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>écrit la totalité des <b>getters</b> 
	 * et des <b>setters</b>.</li>
	 * <li>écrit la javadoc des <b>getters</b> 
	 * et des <b>setters</b>.</li>
	 * <li>écrit les Entity des <b>getters</b>.</li>
	 * <li>écrit le code des <b>getters</b> 
	 * et des <b>setters</b>.</li>
	 * <li>ajoute 3 lignes vides à la suite 
	 * de chaque méthode.</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	protected final void ecrireAccesseurs(
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
		
		final Set<Entry<String, String>> entrySet 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			final String typeAttribut = entry.getValue();
			
			final List<String> listeGetter = new ArrayList<String>();
			final List<String> listeSetter = new ArrayList<String>();
			
			this.creerJavadocGetter(
					nomAttribut, typeAttribut, listeGetter);
			this.creerCodeEntityGetter(
					nomAttribut, typeAttribut, listeGetter);
			this.creerCodeGetter(
					nomAttribut, typeAttribut, listeGetter);
			
			/* ENREGISTREMENT *********/
			final String ligneIdentifiantGetter 
				= this.fournirLigneIdentifianteGetter(
						nomAttribut, typeAttribut);
			
			try {
	
				/* Ne fait rien si le code a déjà été écrit. */
				if (this.existLigneCommencant(
						pFile, CHARSET_UTF8, ligneIdentifiantGetter)) {
					return;
				}
	
				/* ajoute 3 lignes vides*/
				this.ajouterLignesVides(3, listeGetter);
				
				
				/* *************** */
				/* ENREGISTREMENT. */
				/* *************** */
				for (final String ligne : listeGetter) {
	
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
					LOG.fatal("Impossible de créer le code du getter", e);
				}
			}
	
			
			this.creerJavadocSetter(
					nomAttribut, typeAttribut, listeSetter);
			this.creerCodeSetter(
					nomAttribut, typeAttribut, listeSetter);
			
			/* ENREGISTREMENT *********/
			final String ligneIdentifiantSetter 
				= this.fournirLigneIdentifianteSetter(
						nomAttribut, typeAttribut);
			
			try {
	
				/* Ne fait rien si le code a déjà été écrit. */
				if (this.existLigneCommencant(
						pFile, CHARSET_UTF8, ligneIdentifiantSetter)) {
					return;
				}
	
				/* ajoute 3 lignes vides*/
				this.ajouterLignesVides(3, listeSetter);
				
				/* *************** */
				/* ENREGISTREMENT. */
				/* *************** */
				for (final String ligne : listeSetter) {
	
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
					LOG.fatal("Impossible de créer le code du setter", e);
				}
			}
			
		}
	
		
	} // Fin de ecrireAccesseurs(...)._____________________________________
	
	
	
	/**
	 * method creerJavadocGetter(
	 * String pNomAttribut
	 * , String pTypeAttribut
	 * , List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>Crée la liste de lignes pour la javadoc du getter 
	 * de l'attribut pNomAttribut.</li>
	 * <li>Injecte la liste de lignes générées dans pListe.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut
	 * @param pTypeAttribut : String : type de l'attribut
	 * @param pListe : List&lt;String&gt; : 
	 * Liste contenant les lignes de code du Getter.<br/>
	 * 
	 * @throws Exception 
	 */
	protected abstract void creerJavadocGetter(
			String pNomAttribut
				, String pTypeAttribut
					, List<String> pListe) 
							throws Exception;
	
	
	
	/**
	 * method creerCodeEntityGetter(
	 * String pNomAttribut
	 * , String pTypeAttribut
	 * , List&lt;String&gt; pListeGetter) :<br/>
	 * <ul>
	 * <li>Crée la liste de lignes pour l'entity du getter 
	 * de l'attribut pNomAttribut.</li>
	 * <li>Injecte la liste de lignes générées dans pListeGetter.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut
	 * @param pTypeAttribut : String : type de l'attribut
	 * @param pListeGetter : List&lt;String&gt; : 
	 * Liste contenant les lignes de code du Getter.<br/>
	 * 
	 * @throws Exception 
	 */
	protected abstract void creerCodeEntityGetter(
			String pNomAttribut
				, String pTypeAttribut
					, List<String> pListeGetter)
							throws Exception;
	
	
	
	/**
	 * method creerCodeGetter(
	 * String pNomAttribut
	 * , String pTypeAttribut
	 * , List&lt;String&gt; pListeGetter) :<br/>
	 * <ul>
	 * <li>Crée la liste de lignes de code du getter 
	 * de l'attribut pNomAttribut.</li>
	 * <li>Injecte la liste de lignes générées dans pListeGetter.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut.<br/>
	 * @param pTypeAttribut : String : type de l'attribut.<br/>
	 * @param pListeGetter : List&lt;String&gt; : 
	 * Liste contenant les lignes de code du Getter.<br/>
	 * 
	 * @throws Exception 
	 */
	protected abstract void creerCodeGetter(
			String pNomAttribut
				, String pTypeAttribut
					, List<String> pListeGetter) 
							throws Exception;
	
	
	
	/**
	 * method fournirLigneIdentifianteGetter(
	 * String pNomAttribut
	 * , String pTypeAttribut) :<br/>
	 * <ul>
	 * <li>fournit un début de ligne permettant d'identifier 
	 * le Getter d'un attribut.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut.<br/>
	 * @param pTypeAttribut : String : type de l'attribut.<br/>
	 * 
	 * @return : String : début de ligne permettant d'identifier 
	 * le Getter d'un attribut.<br/>
	 */
	protected abstract String fournirLigneIdentifianteGetter(
			String pNomAttribut
				, String pTypeAttribut);
	
	
	
	/**
	 * method creerJavadocSetter(
	 * String pNomAttribut
	 * , String pTypeAttribut
	 * , List&lt;String&gt; pListeSetter) :<br/>
	 * <ul>
	 * <li>Crée la liste de lignes pour la javadoc du setter 
	 * de l'attribut pNomAttribut.</li>
	 * <li>Injecte la liste de lignes générées dans pListeSetter.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut
	 * @param pTypeAttribut : String : type de l'attribut
	 * @param pListeSetter : List&lt;String&gt; : 
	 * Liste contenant les lignes de code du Setter.<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerJavadocSetter(
			String pNomAttribut
				, String pTypeAttribut
					, List<String> pListeSetter) 
							throws Exception;
	
	
	
	/**
	 * method creerCodeSetter(
	 * String pNomAttribut
	 * , String pTypeAttribut
	 * , List&lt;String&gt; pListeSetter) :<br/>
	 * <ul>
	 * <li>Crée la liste de lignes pour le code du setter 
	 * de l'attribut pNomAttribut.</li>
	 * <li>Injecte la liste de lignes générées dans pListeSetter.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut
	 * @param pTypeAttribut : String : type de l'attribut
	 * @param pListeSetter : List&lt;String&gt; : 
	 * Liste contenant les lignes de code du Setter.<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract void creerCodeSetter(
			String pNomAttribut
				, String pTypeAttribut
					, List<String> pListeSetter) 
							throws Exception;
	
	
	
	/**
	 * method fournirLigneIdentifianteSetter(
	 * String pNomAttribut
	 * , String pTypeAttribut) :<br/>
	 * <ul>
	 * <li>fournit un début de ligne permettant d'identifier 
	 * le Setter d'un attribut.</li>
	 * </ul>
	 *
	 * @param pNomAttribut : String : nom de l'attribut.<br/>
	 * @param pTypeAttribut : String : type de l'attribut.<br/>
	 * 
	 * @return : String : début de ligne permettant d'identifier 
	 * le Setter d'un attribut.<br/>
	 */
	protected abstract String fournirLigneIdentifianteSetter(
			String pNomAttribut
				, String pTypeAttribut);
	
	
	
	/**
	 * method ajouterRGsAJavadoc(
	 * List&lt;String&gt; pListe
	 * , String pNomAttribut) :<br/>
	 * <ul>
	 * <li><b>ajoute les RG</b> à la Javadoc pLIste 
	 * d'un attribut pNomAttribut.</li>
	 * </ul>
	 * ne fait rien si pListe == null.<br/>
	 * ne fait rien si pNomAttribut est blank.<br/>
	 * <br/>
	 *
	 * @param pListe : List&lt;String&gt; : 
	 * javadoc dans laquelle il faut insèrer les RGS 
	 * sous forme de liste de Strings.<br/>
	 * @param pNomAttribut : String : Nom de l'attribut.<br/>
	 */
	protected final void ajouterRGsAJavadoc(
			final List<String> pListe
				, final String pNomAttribut) {
		
		/* ne fait rien si pListe == null. */
		if (pListe == null) {
			return;
		}
		
		/* ne fait rien si pNomAttribut est blank. */
		if (StringUtils.isBlank(pNomAttribut)) {
			return;
		}
		
		/* Récupère la liste des RG de l'attribut. */
		final List<String> listeRgs = this.mapRg.get(pNomAttribut);
		
		/* Ajout des RG. */
		if (!listeRgs.isEmpty()) {
			
			/* ouverture de liste HTML. */
			pListe.add(UL_OUVRANT_JAVADOC_MEMBRE);
			
			for (final String rg : listeRgs) {
				
				final List<String> elementListe 
					= new ArrayList<String>();
				elementListe.add("	 * <li>" 
					+ this.fournirTitreRg(rg) 
						+ SEP_2PTS_AERE);
				elementListe.add("	 * " 
						+ this.fournirMessageRg(rg) 
							+ ".</li>");
				
				pListe.addAll(elementListe);
			}
			
			/* fermeture de liste HTML. */
			pListe.add(UL_FERMANT_JAVADOC_MEMBRE);
			
		}
		
	} // Fin de ajouterRGsAJavadoc(...).___________________________________
	
	
	
	/**
	 * method ajouterAttributsEqualsAJavadoc(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>Ajoute à pListe sous forme d'éléments de liste 
	 * (numérotée ou à puce) les attributs utilisés 
	 * dans equals(...).</li>
	 * <li>.</li>
	 * </ul>
	 * ne fait rien si pListe == null.<br/>
	 * ne fait rien si this.mapAttributsEquals == null.<br/>
	 * <br/>
	 *
	 * @param pListe : List&lt;String&gt; : 
	 * Liste contenant une Javadoc.<br/>
	 */
	protected final void ajouterAttributsEqualsAJavadoc(
			final List<String> pListe) {
		
		/* ne fait rien si pListe == null. */
		if (pListe == null) {
			return;
		}
		
		/* ne fait rien si this.mapAttributsEquals == null. */
		if (this.mapAttributsEquals == null) {
			return;
		}
		
		final Set<Entry<String, String>> entrySet 
			= this.mapAttributsEquals.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, String> entry = ite.next();
			
			final String nomAttribut = entry.getKey();
			
			final String aAjouter 
				= "	 * <li>" + nomAttribut + ".</li>";
			
			pListe.add(aAjouter);
		}
		
	} // Fin de ajouterAttributsEqualsAJavadoc(...)._______________________
	
		
	
	/**
	 * method fournirDebutAttributId() :<br/>
	 * retourne le début de la ligne attributId.<br/>
	 * "	protected Long id"<br/>
	 *
	 * @return : String : "	protected Long id".<br/>
	 */
	private String fournirDebutAttributId() {
		return "	protected Long id";
	} // Fin de fournirDebutAttributId().__________________________________
	
	

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
