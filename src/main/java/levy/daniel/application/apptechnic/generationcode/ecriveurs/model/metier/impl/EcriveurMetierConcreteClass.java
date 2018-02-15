package levy.daniel.application.apptechnic.generationcode.ecriveurs.model.metier.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveurFichiersJavaDetaille;

/**
 * class EcriveurMetierConcreteClass :<br/>
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
public class EcriveurMetierConcreteClass extends AbstractEcriveurFichiersJavaDetaille {

	// ************************ATTRIBUTS************************************/
	/**
	 * CLASSE_ECRIVEUR_CONCRETECLASS : String :<br/>
	 * "Classe EcriveurMetierConcreteClass".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_CONCRETECLASS 
		= "Classe EcriveurMetierConcreteClass";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(EcriveurMetierConcreteClass.class);
	

	// *************************METHODES************************************/

	
	/**
	* method CONSTRUCTEUR EcriveurMetierConcreteClass() :<br/>
	* CONSTRUCTEUR D'ARITE NULLE.<br/>
	*/
	public EcriveurMetierConcreteClass() {
		
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
				, VARIABLE_NOMPACKAGE
					, this.nomPackage);
		
		this.imports = listeLignesSubst3;
		
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
		
		final String cheminFichierAttributs 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/javadoc_interface_attributs.txt";
	
		final File fichierAttributs = new File(cheminFichierAttributs);
		
		final List<String> listeLignesAttributs 
			= this.lireStringsDansFile(fichierAttributs, CHARSET_UTF8);
		
		final Set<Entry<String, String>> entrySetAttributs 
		= this.mapAttributs.entrySet();
	
		final Iterator<Entry<String, String>> iteAttributs 
			= entrySetAttributs.iterator();
		
		while (iteAttributs.hasNext()) {
			
			final Entry<String, String> entryAttributs = iteAttributs.next();
			
			final String nomAttribut = entryAttributs.getKey();
			
			final List<String> listeAttribut 
				= this.substituerVariablesDansLigne(
						listeLignesAttributs
							, VARIABLE_NOMATTRIBUT
								, nomAttribut);
			
			this.javadoc.addAll(listeAttribut);
		}

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
		
		final String cheminFichierEgalite 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/javadoc_concreteclass_egalite.txt";
	
		final File fichierEgalite = new File(cheminFichierEgalite);
		
		final List<String> listeLignesEgalite 
			= this.lireStringsDansFile(fichierEgalite, CHARSET_UTF8);
		
		final List<String> listeLignesEgaliteSubst1 
			= this.substituerVariablesDansLigne(
					listeLignesEgalite
						, VARIABLE_NOMSIMPLEFICHIERJAVA
							, this.nomSimpleFichierJava);
		
		this.javadoc.addAll(listeLignesEgaliteSubst1);
		
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
		
		final String cheminFichierAttributsEquals 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/javadoc_interface_attributs.txt";
	
		final File fichierAttributsEquals = new File(cheminFichierAttributsEquals);
		
		final List<String> listeLignesAttributsEquals 
			= this.lireStringsDansFile(fichierAttributsEquals, CHARSET_UTF8);
		
		final Set<Entry<String, String>> entrySetAttributsEquals 
		= this.mapAttributsEquals.entrySet();
	
		final Iterator<Entry<String, String>> iteAttributsEquals 
			= entrySetAttributsEquals.iterator();
		
		while (iteAttributsEquals.hasNext()) {
			
			final Entry<String, String> entryAttributs = iteAttributsEquals.next();
			
			final String nomAttribut = entryAttributs.getKey();
			
			final List<String> listeAttribut 
				= this.substituerVariablesDansLigne(
						listeLignesAttributsEquals
							, VARIABLE_NOMATTRIBUT
								, nomAttribut);
			
			this.javadoc.addAll(listeAttribut);
		}

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
		
		final String cheminFichierDiagramme
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/javadoc_concreteclass_diagrammes.txt";
	
		final File fichierDiagramme= new File(cheminFichierDiagramme);
		
		final List<String> listeLignesDiagramme
			= this.lireStringsDansFile(fichierDiagramme, CHARSET_UTF8);
		
		final List<String> listeLignesRgSubst1 
			= this.substituerVariablesDansLigne(
					listeLignesDiagramme
						, VARIABLE_CONCEPT_MODELISE
							, this.conceptModelise);
		
		final List<String> listeLignesRgSubst2 
		= this.substituerVariablesDansLigne(
				listeLignesRgSubst1
					, VARIABLE_NOMSIMPLEFICHIERJAVA
						, this.nomSimpleFichierJava);
		
		this.javadoc.addAll(listeLignesRgSubst2);

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
		
		final String cheminFichierRg 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/javadoc_interface_rg_debut.txt";
	
		final File fichierRg = new File(cheminFichierRg);
		
		final List<String> listeLignesRg 
			= this.lireStringsDansFile(fichierRg, CHARSET_UTF8);
		
		final List<String> listeLignesRgSubst1 
			= this.substituerVariablesDansLigne(
					listeLignesRg
						, VARIABLE_CONCEPT_MODELISE
							, this.conceptModelise);
				
		this.javadoc.addAll(listeLignesRgSubst1);

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
		
		final Set<Entry<String, List<String>>> entrySetAttributs2 
		= this.mapRg.entrySet();
	
		final Iterator<Entry<String, List<String>>> iteAttributs2 
			= entrySetAttributs2.iterator();
		
		while (iteAttributs2.hasNext()) {
			
			final Entry<String, List<String>> entryAttributs2 
				= iteAttributs2.next();
			
			final String nomAttribut = entryAttributs2.getKey();
			final List<String> listeRg = entryAttributs2.getValue();
			
			if (listeRg.isEmpty()) {
				continue;
			}
			
			final int nombreRgs = listeRg.size();
			int compteur = 0;
			
			for (final String rG : listeRg) {
				
				compteur++;
				
				if (compteur == 1) {
					
					final String cheminFichierRgLigne1 
					= BundleConfigurationProjetManager.getRacineMainResources() 
					+ "/templates/javadoc_interface_rg_attribut_ligne_1.txt";
				
					final File fichierRgLigne1 = new File(cheminFichierRgLigne1);
					
					final List<String> listeLignesRgLigne1 
						= this.lireStringsDansFile(fichierRgLigne1, CHARSET_UTF8);
					
					final List<String> listeLignesRgLigne1Subst1 
						= this.substituerVariablesDansLigne(
								listeLignesRgLigne1
									, VARIABLE_NOMATTRIBUT
										, nomAttribut);
					
					final List<String> listeLignesRgLigne1Subst2 
						= this.substituerVariablesDansLigne(
							listeLignesRgLigne1Subst1
								, VARIABLE_NOMBRE_RGS
									, String.valueOf(nombreRgs));
					
					final List<String> listeLignesRgLigne1Subst3 
						= this.substituerVariablesDansLigne(
							listeLignesRgLigne1Subst2
								, VARIABLE_TITRE_RG
									, this.fournirTitreRg(rG));
					
					final List<String> listeLignesRgLigne1Subst4 
					= this.substituerVariablesDansLigne(
						listeLignesRgLigne1Subst3
							, VARIABLE_MESSAGE_RG
								, this.fournirMessageRg(rG));
					
					this.javadoc.addAll(listeLignesRgLigne1Subst4);
						
				} else {

					final String cheminFichierRgLigneCourant
					= BundleConfigurationProjetManager.getRacineMainResources() 
					+ "/templates/javadoc_interface_rg_attribut_courant.txt";
				
					final File fichierRgLigneCourant 
						= new File(cheminFichierRgLigneCourant);
					
					final List<String> listeLignesRgLigneCourant
						= this.lireStringsDansFile(
								fichierRgLigneCourant, CHARSET_UTF8);
										
					final List<String> listeLignesRgLigneCourantSubst1 
						= this.substituerVariablesDansLigne(
								listeLignesRgLigneCourant
									, VARIABLE_TITRE_RG
										, this.fournirTitreRg(rG));
					
					final List<String> listeLignesRgLigneCourantSubst2 
						= this.substituerVariablesDansLigne(
								listeLignesRgLigneCourantSubst1
									, VARIABLE_MESSAGE_RG
										, this.fournirMessageRg(rG));
					
					this.javadoc.addAll(listeLignesRgLigneCourantSubst2);
						
				}
								
			} // Fin de l'itération sur les RG d'un attribut.___
			
		} // Fin de l'itération sur les attributs._________

		/* FIN DU TABLEAU. */
		this.javadoc.add(" * </table>");
		
		/* FIN DE LA LISTE. */
		this.javadoc.add(" * </ul>");
		
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
	 * <b>fournirDebutJavaDoc() pour une CLASS CONCRETE</b> :
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
		stb.append(EXTENDS);
		stb.append(this.nomSimpleAbstractClass);
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
		return;		 // NOPMD by daniel.levy on 17/01/18 11:28
	} // Fin de creerJavadocAttribut(...)._________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeAttribut(
			final List<String> pListe
				, final String pNomAttribut
					, final String pTypeAttribut) throws Exception {
		return; // NOPMD by daniel.levy on 17/01/18 11:28
	} // Fin de creerCodeAttribut(...).____________________________________

	
	
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
		
		/* DECLARATION du constructeur. */
		listCode.add(PUBLIC + this.nomSimpleFichierJava + "(");

		/* PARAMETRES du constructeur. */
		final String decalageDebut = TAB + TAB + TAB;
		/* pId. */
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
			final StringBuffer stb = new StringBuffer();
			stb.append(decalage);
			stb.append(TAB);
			
			decalage = stb.toString();
			
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

		/* CODE du Constructeur. */
		listCode.add("");

		final StringBuilder stb = new StringBuilder();
		
		stb.append("super(pId");
		
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
				LOG.fatal("Impossible de créer "
						+ "le code du constructeur", e);
			}
		}
		
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
		return CLASSE_ECRIVEUR_CONCRETECLASS;
	} // Fin de fournirNomClasse().________________________________________

	

} // FIN DE LA CLASSE EcriveurMetierConcreteClass.---------------------------------
