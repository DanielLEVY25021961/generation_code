package levy.daniel.application.apptechnic.generationcode.ecriveurs.model.metier.impl;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.model.metier.AbstractEcriveurMetier;

/**
 * class EcriveurInterface :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Path.relativize(), extraire un chemin relatif, 
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 5 janv. 2018
 *
 */
public class EcriveurInterface extends AbstractEcriveurMetier {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ECRIVEUR_INTERFACE : String :<br/>
	 * "Classe EcriveurInterface".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_INTERFACE
		= "Classe EcriveurInterface";
	


	/**
	 * INTERFACE : String :<br/>
	 * "public interface ".<br/>
	 */
	public static final String INTERFACE = "public interface ";
	
	
	/**
	 * EXTENDS : String :<br/>
	 * " extends IExportateurCsv, IExportateurJTable, ".<br/>
	 */
	public static final String EXTENDS 
		= " extends IExportateurCsv, IExportateurJTable, ";

	
	/**
	 * CLONEABLE : String :<br/>
	 * ", Cloneable, Serializable ".<br/>
	 */
	public static final String CLONEABLE 
		= ", Cloneable, Serializable ";
	
	
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(EcriveurInterface.class);

	// *************************METHODES************************************/
		 
	
	/**
	* method CONSTRUCTEUR EcriveurInterface() :<br/>
	* CONSTRUCTEUR D'ARITE NULLE.<br/>
	*/
	public EcriveurInterface() {
		
		super();
				
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
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
	 * <li>Ecrit la méthode compareTo().</li>
	 * <li>Ecrit la méthode clone().</li>
	 * <li>Ecrit la méthode getEnTeteCsv().</li>
	 * <li>Ecrit la méthode toStringCsv().</li>
	 * <li>Ecrit la méthode getEnTeteColonne().</li>
	 * <li>Ecrit la méthode getValeurColonne().</li>
	 * <li>Ecrit la méthode getId().</li>
	 * <li>Ecrit la méthode setId().</li>
	 * <li>écrit les getters-setters.</li>
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
		this.insererLignesVidesSousLigneDansFichier(
				pFile, this.ligneDeclaration, 3, CHARSET_UTF8);
		
		/* Ecrit la méthode compareTo(). */
		this.ecrireCompareTo(pFile);
		
		/* Ecrit la méthode clone(). */
		this.ecrireClone(pFile);
					
		/* Ecrit la méthode getEnTeteCsv(). */
		this.ecrireGetEnTeteCsv(pFile);

		/* Ecrit la méthode toStringCsv(). */
		this.ecrireToStringCsv(pFile);
				
		/* Ecrit la méthode getEnTeteColonne(). */
		this.ecrireGetEnTeteColonne(pFile);
		
		/* Ecrit la méthode getValeurColonne(). */
		this.ecrireGetValeurColonne(pFile);
		
		/* Ecrit la méthode getId(). */
		this.ecrireGetId(pFile);
		
		/* Ecrit la méthode setId(). */
		this.ecrireSetId(pFile);
				
		/* écrit les getters-setters. */
		this.ecrireAccesseurs(pFile);
						
	} // Fin de ecrireBlocMethodes(...).___________________________________
	

		

		
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesImport() throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/imports_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$pathmetier}", this.pathMetier);
		
		this.imports = listeLignesSubstitue;
		
		return this.imports;
		
	} // Fin de creerLignesImport()._______________________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesJavaDoc(
			final File pFile) throws Exception {
		
		/* DEBUT. */
		final String cheminFichierDebut 
			= BundleConfigurationProjetManager
			.getRacineMainResources() 
			+ "/templates/javadoc_interface_debut.txt";
		
		final File fichierDebut = new File(cheminFichierDebut);
		
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
				
		final List<String> listeLignesDebutSubst1 
		= this.substituerVariablesDansLigne(
				listeLignesDebut
				, VARIABLE_NOMSIMPLEINTERFACE, this.nomSimpleInterface);
		
		final List<String> listeLignesDebutSubst2 
		= this.substituerVariablesDansLigne(
				listeLignesDebutSubst1
				, VARIABLE_CONCEPT_MODELISE, this.conceptModelise);
		
		this.javadoc.addAll(listeLignesDebutSubst2);
		
		/* ATTRIBUTS. */
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

		/* EGALITE METIER. */
		final String cheminFichierEgalite 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/javadoc_interface_egalite.txt";
	
		final File fichierEgalite = new File(cheminFichierEgalite);
		
		final List<String> listeLignesEgalite 
			= this.lireStringsDansFile(fichierEgalite, CHARSET_UTF8);
		
		final List<String> listeLignesEgaliteSubst1 
			= this.substituerVariablesDansLigne(
					listeLignesEgalite
						, VARIABLE_NOMSIMPLEINTERFACE
							, this.nomSimpleInterface);
		
		this.javadoc.addAll(listeLignesEgaliteSubst1);

		/* attributs de equals. */
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

		
		/* REGLES DE GESTION. */
		final String cheminFichierRg 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/javadoc_interface_rg_debut.txt";
	
		final File fichierRg = new File(cheminFichierRg);
		
		final List<String> listeLignesRg 
			= this.lireStringsDansFile(fichierRg, CHARSET_UTF8);
		
		final List<String> listeLignesRgSubst1 
			= this.substituerVariablesDansLigne(
					listeLignesRg
						, VARIABLE_NOMSIMPLEINTERFACE
							, this.nomSimpleInterface);
		
		final List<String> listeLignesRgSubst2 
		= this.substituerVariablesDansLigne(
				listeLignesRgSubst1
					, VARIABLE_CONCEPT_MODELISE
						, this.conceptModelise);
		
		this.javadoc.addAll(listeLignesRgSubst2);
		
		/* corps du tableau de RG. */
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
		
		/* FIN DE LA JAVADOC. */
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
		
		return this.javadoc;
		
	} // Fin de creerLignesJavaDoc(...).___________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesEntity(
			final File pFile) 
			throws Exception {
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
		
		stb.append(INTERFACE);
		stb.append(this.nomSimpleFichierJava);
		stb.append(EXTENDS);
		stb.append("Comparable<");
		stb.append(this.nomSimpleFichierJava);
		stb.append('>');
		stb.append(CLONEABLE);
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
		return; // NOPMD by daniel.levy on 17/01/18 11:18				
	} // Fin de creerAttributId(...).______________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocAttribut(
			final List<String> pListe
				, final String pNomAttribut
					, final String pTypeAttribut) throws Exception {		
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
	protected final void creerJavadocCompareTo(
			final List<String> pListeMethode) throws Exception {
		
		/* DEBUT. */
		final String cheminFichierDebut 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/compareTo/debut_javadoc_interface_compareTo.txt";
	
		final File fichierDebut = new File(cheminFichierDebut);
		
		final List<String> listeLignesDebut	
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
		
		final List<String> listeLignesSubstDebut1 
			= this.substituerVariablesDansLigne(
					listeLignesDebut
						, VARIABLE_NOMSIMPLEINTERFACE
							, this.nomSimpleInterface);
		
		pListeMethode.addAll(listeLignesSubstDebut1);
		
		/* CORPS. */
		pListeMethode.add(OL_OUVRANT_JAVADOC_MEMBRE);
		
		final String ligneComparaison 
			= "	 * Comparaison de 2 " 
					+ this.nomSimpleInterface 
						+ " par rapport à :";
		
		pListeMethode.add(ligneComparaison);
		
		/* ajout des attributs utilisés dans le compareTo(...). */
		this.ajouterAttributsEqualsAJavadoc(pListeMethode);
		
		pListeMethode.add(OL_FERMANT_JAVADOC_MEMBRE);
		
		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/compareTo/fin_javadoc_interface_compareTo.txt";
	
		final File fichierFin = new File(cheminFichierFin);
		
		final List<String> listeLignesFin	
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);
		
		final List<String> listeLignesSubstFin1 
			= this.substituerVariablesDansLigne(
					listeLignesFin
						, VARIABLE_NOMSIMPLEINTERFACE
							, this.nomSimpleInterface);
		
		pListeMethode.addAll(listeLignesSubstFin1);
		
	} // Fin de creerJavadocCompareTo(...).________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeCompareTo(
			final List<String> pListeMethode) throws Exception {
				
		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/compareTo/corps_compareTo_interface.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes	
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_NOMSIMPLEINTERFACE
							, this.nomSimpleInterface);
		
		pListeMethode.addAll(listeLignesSubst1);
		
	} // Fin de creerCodeCompareTo(...).___________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifiantCompareTo() {
		return "	int compareTo";
	} // Fin de fournirLigneIdentifiantCompareTo().________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocClone(
			final List<String> pListe) throws Exception {
		
		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/clone/javadoc_interface_clone.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOMSIMPLEINTERFACE
						, this.nomSimpleInterface);
		
		pListe.addAll(listeLignesSubstitue);
		
	} // Fin de creerJavadocClone(...).____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeClone(
			final List<String> pListe) throws Exception {
		
		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/clone/code_interface_clone.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOMSIMPLEINTERFACE
						, this.nomSimpleInterface);
		
		pListe.addAll(listeLignesSubstitue);
		
	} // Fin de creerCodeClone(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocToString(
			final List<String> pListe) throws Exception {
		return; // NOPMD by daniel.levy on 16/01/18 14:32
	} // Fin de creerJavadocToString(...)._________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeToString(
			final List<String> pListe) throws Exception {
		return; // NOPMD by daniel.levy on 16/01/18 14:32
	} // Fin de creerCodeToString(...).____________________________________
	
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetEnTeteCsv(
			final List<String> pListe) throws Exception {
		
		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/getEnTeteCsv/javadoc_getEnTeteCsv_interface.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue1 
		= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOMSIMPLEINTERFACE
						, this.nomSimpleInterface);
		
		final List<String> listeLignesSubstitue2 
		= this.substituerVariablesDansLigne(
				listeLignesSubstitue1
					, VARIABLE_LIGNECSV
						, this.fournirCsv());
		
		pListe.addAll(listeLignesSubstitue2);
		
	} // Fin de creerJavadocGetEnTeteCsv(...)._____________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetEnTeteCsv(
			final List<String> pListe) throws Exception {
		
		final String aAjouter = "	String getEnTeteCsv();";
		
		pListe.add(aAjouter);
		
	} // Fin de creerCodeGetEnTeteCsv(...).________________________________

		
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocToStringCsv(
			final List<String> pListe) throws Exception {
		
		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/toStringCsv/javadoc_toStringCsv_interface.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue1 
		= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOMSIMPLEINTERFACE
						, this.nomSimpleInterface);
		
		final List<String> listeLignesSubstitue2 
		= this.substituerVariablesDansLigne(
				listeLignesSubstitue1
					, VARIABLE_LIGNECSV
						, this.fournirCsv());
		
		pListe.addAll(listeLignesSubstitue2);
		
	} // Fin de creerJavadocToStringCsv(...).______________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeToStringCsv(
			final List<String> pListe) throws Exception {
		
		final String aAjouter = "	String toStringCsv();";
		
		pListe.add(aAjouter);

	} // Fin de creerCodeToStringCsv(...)._________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetEnTeteColonne(
			final List<String> pListe) throws Exception {
		
		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/getEnTeteColonne/javadoc_getEnTeteColonne_interface.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue1 
		= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOMSIMPLEINTERFACE
						, this.nomSimpleInterface);
		
		final List<String> listeLignesSubstitue2 
		= this.substituerVariablesDansLigne(
				listeLignesSubstitue1
					, VARIABLE_LIGNECSV
						, this.fournirCsv());
		
		pListe.addAll(listeLignesSubstitue2);
		
	} // Fin de creerJavadocGetEnTeteColonne(...)._________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetEnTeteColonne(
			final List<String> pListe) throws Exception {
		
		final String aAjouter = "	String getEnTeteColonne(int pI);";
		
		pListe.add(aAjouter);

	} // Fin de creerCodeGetEnTeteColonne(...).____________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetValeurColonne(
			final List<String> pListe) throws Exception {
		
		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/getValeurColonne/javadoc_getValeurColonne_interface.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue1 
		= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOMSIMPLEINTERFACE
						, this.nomSimpleInterface);
		
		final List<String> listeLignesSubstitue2 
		= this.substituerVariablesDansLigne(
				listeLignesSubstitue1
					, VARIABLE_LIGNECSV
						, this.fournirCsv());
		
		pListe.addAll(listeLignesSubstitue2);

	} // Fin de creerJavadocGetValeurColonne(...)._________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetValeurColonne(
			final List<String> pListe) throws Exception {
		
		final String aAjouter = "	Object getValeurColonne(int pI);";
		
		pListe.add(aAjouter);

	} // Fin de creerCodeGetValeurColonne(...).____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetId(
			final List<String> pListe) throws Exception	{
		
		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/methodGetId_javadoc_interface.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
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
		
		final String code = "	Long getId();";
		
		pListe.add(code);
		
	} // Fin de creerCodeGetId(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocSetId(
			final List<String> pListe) throws Exception	{
		
		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/methodSetId_javadoc_interface.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
				
		pListe.addAll(listeLignes);
		
	} // Fin de creerJavadocSetId(...).____________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeSetId(
			final List<String> pListe) throws Exception {
		
		final String code = "	void setId(Long pId);";
		
		pListe.add(code);
		
	} // Fin de creerCodeSetId(...)._______________________________________
	
	
	
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
		stb.append(" // FIN DE L'INTERFACE ");
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
		return CLASSE_ECRIVEUR_INTERFACE;
	} // Fin de fournirNomClasse().________________________________________
	

	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * <ul>
	 * <b>fournirDebutDeclaration() pour une INTERFACE</b> :
	 * <li>"public interface ".</li>
	 * </ul>
	 * <br/>
	 */
	@Override
	protected final String fournirDebutDeclaration() {
		return INTERFACE;
	} // Fin de fournirDebutDeclaration()._________________________________


	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * <ul>
	 * <b>fournirDebutJavaDoc() pour une INTERFACE</b> :
	 * <li>" * INTERFACE".</li>
	 * </ul>
	 * <br/>
	 */
	@Override
	protected final String fournirDebutJavaDoc() {
		return " * INTERFACE";
	} // Fin de fournirDebutJavaDoc()._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocGetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListe) throws Exception {

		/* DEBUT. */
		final String cheminFichierDebut 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/getter/debut_javadoc_getter.txt";
		
		final File fichierDebut = new File(cheminFichierDebut);
		
		final List<String> listeLignesDebut 
			= this.lireStringsDansFile(fichierDebut, CHARSET_UTF8);
				
		final List<String> listeLignesDebutSubstitue1 
			= this.substituerVariablesDansLigne(
				listeLignesDebut
					, VARIABLE_GETTER
						, this.fournirGetter(pNomAttribut));
		
		final List<String> listeLignesDebutSubstitue2 
			= this.substituerVariablesDansLigne(
				listeLignesDebutSubstitue1
					, VARIABLE_NOMATTRIBUT
						, pNomAttribut);
		
		final List<String> listeLignesDebutSubstitue3 
		= this.substituerVariablesDansLigne(
				listeLignesDebutSubstitue2
				, VARIABLE_CONCEPT_MODELISE
					, this.conceptModelise);

		
		pListe.addAll(listeLignesDebutSubstitue3);
		
		/* CORPS. */
		this.ajouterRGsAJavadoc(pListe, pNomAttribut);
		
		
		/* FIN. */
		final String cheminFichierFin 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/getter/fin_javadoc_getter.txt";
	
		final File fichierFin = new File(cheminFichierFin);
		
		final List<String> listeLignesFin 
			= this.lireStringsDansFile(fichierFin, CHARSET_UTF8);

		final List<String> listeLignesFinSubstitue1 
		= this.substituerVariablesDansLigne(
			listeLignesFin
				, VARIABLE_GETTER
					, this.fournirGetter(pNomAttribut));
	
		final List<String> listeLignesFinSubstitue2 
			= this.substituerVariablesDansLigne(
					listeLignesFinSubstitue1
					, VARIABLE_NOMATTRIBUT
						, pNomAttribut);
		
		final List<String> listeLignesFinSubstitue3 
		= this.substituerVariablesDansLigne(
				listeLignesFinSubstitue2
				, VARIABLE_TYPEATTRIBUT
					, pTypeAttribut);
			
		pListe.addAll(listeLignesFinSubstitue3);
		
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

		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/getter/code_getter_interface.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_TYPEATTRIBUT
							, pTypeAttribut); 
		
		final List<String> listeLignesSubst2 
		= this.substituerVariablesDansLigne(
				listeLignesSubst1
					, VARIABLE_GETTER
						, this.fournirGetter(pNomAttribut)); 
		
		pListe.addAll(listeLignesSubst2);
		
	} // Fin de creerCodeGetter(...).______________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocSetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListe) throws Exception {

		final String cheminFichier 
		= BundleConfigurationProjetManager.getRacineMainResources() 
		+ "/templates/setter/javadoc_setter_interface.txt";
	
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubst1 
		= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_TYPEATTRIBUT
						, pTypeAttribut);
		
		final List<String> listeLignesSubst2 
		= this.substituerVariablesDansLigne(
				listeLignesSubst1
					, VARIABLE_SETTER
						, this.fournirSetter(pNomAttribut));
		
		final List<String> listeLignesSubst3 
		= this.substituerVariablesDansLigne(
				listeLignesSubst2
					, VARIABLE_PARAMATTRIBUT
						, this.fournirParametre(pNomAttribut));
		
		final List<String> listeLignesSubst4 
		= this.substituerVariablesDansLigne(
				listeLignesSubst3
					, VARIABLE_NOMATTRIBUT
						, pNomAttribut);
		
		final List<String> listeLignesSubst5 
		= this.substituerVariablesDansLigne(
				listeLignesSubst4
					, VARIABLE_CONCEPT_MODELISE
						, this.conceptModelise);
		
		pListe.addAll(listeLignesSubst5);
		
	} // Fin de creerJavadocSetter(...).___________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeSetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListe) throws Exception {

		final String code 
		= DECALAGE_METHODE + "void " 
		+ this.fournirSetter(pNomAttribut) 
		+ "(" + pTypeAttribut 
		+ SEP_ESPACE + this.fournirParametre(pNomAttribut) + ");";
		
		pListe.add(code);
		
	} // Fin de creerCodeSetter(...).______________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirLigneIdentifianteGetter(
			final String pNomAttribut
				, final String pTypeAttribut) {
		
		final String ligneIdentifiant 
		= DECALAGE_METHODE 
			+ pTypeAttribut + SEP_ESPACE + this.fournirGetter(pNomAttribut);
		
		return ligneIdentifiant;
		
	} // Fin de fournirLigneIdentifianteGetter(...)._______________________



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
	protected final void ecrireCodeConstructeurCompletBase(
			final File pFile) throws Exception {
		return; // NOPMD by daniel.levy on 16/01/18 11:42
		
	} // Fin de ecrireCodeConstructeurCompletBase(...).____________________


	
} // FIN DE LA CLASSE EcriveurInterface.-------------------------------------
