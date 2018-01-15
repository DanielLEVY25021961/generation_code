package levy.daniel.application.apptechnic.generationcode.impl;

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
import levy.daniel.application.apptechnic.generationcode.AbstractEcriveur;

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
public class EcriveurInterface extends AbstractEcriveur {

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
		this.ecrireMethodCompareTo(pFile);
		
		final String derniereLigneCompareTo 
			= this.fournirDerniereLigneListe(this.methodCompareTo);
					
		/* Insère 3 lignes vides sous la dernière ligne de compareTo(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneCompareTo, 3, CHARSET_UTF8);
		
		/* Ecrit la méthode clone(). */
		this.ecrireMethodClone(pFile);
		
		final String derniereLigneClone 
			= this.fournirDerniereLigneListe(this.methodClone);
			
		
		/* Insère 3 lignes vides sous la dernière ligne
		 *  de clone(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneClone, 3, CHARSET_UTF8);
		
		/* Ecrit la méthode getEnTeteCsv(). */
		this.ecrireMethodGetEnTeteCsv(pFile);
		
		final String derniereLigneGetEnTeteCsv 
			= this.fournirDerniereLigneListe(this.methodGetEnTeteCsv);
					
		/* Insère 3 lignes vides sous la dernière ligne 
		 * de getEnTeteCsv(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneGetEnTeteCsv, 3, CHARSET_UTF8);

		/* Ecrit la méthode toStringCsv(). */
		this.ecrireMethodToStringCsv(pFile);
		
		final String derniereLigneToStringCsv 
			= this.fournirDerniereLigneListe(this.methodToStringCsv);			
		
		/* Insère 3 lignes vides sous la dernière ligne
		 *  de toStringCsv(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneToStringCsv, 3, CHARSET_UTF8);
		
		/* Ecrit la méthode getEnTeteColonne(). */
		this.ecrireMethodGetEnTeteColonne(pFile);
		
		final String derniereLigneGetEnTeteColonne 
			= this.fournirDerniereLigneListe(this.methodGetEnTeteColonne);
		
		/* Insère 3 lignes vides sous la dernière 
		 * ligne de getEnTeteColonne(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneGetEnTeteColonne, 3, CHARSET_UTF8);

		/* Ecrit la méthode getValeurColonne(). */
		this.ecrireMethodGetValeurColonne(pFile);
		
		final String derniereLigneGetValeurColonne 
			= this.fournirDerniereLigneListe(this.methodGetValeurColonne);
		
		/* Insère 3 lignes vides sous la dernière 
		 * ligne de getValeurColonne(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneGetValeurColonne, 3, CHARSET_UTF8);
		
		/* Ecrit la méthode getId(). */
		this.ecrireMethodGetId(pFile);
		
		final String derniereLigneGetId 
			= this.fournirDerniereLigneListe(this.methodGetId);
					
		/* Insère 3 lignes vides sous la dernière 
		 * ligne de getId(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneGetId, 3, CHARSET_UTF8);

		/* Ecrit la méthode setId(). */
		this.ecrireMethodSetId(pFile);
		
		final String derniereLigneSetId 
			= this.fournirDerniereLigneListe(this.methodSetId);
					
		/* Insère 3 lignes vides sous la dernière 
		 * ligne de setId(). */
		this.insererLignesVidesSousLigneDansFichier(
				pFile, derniereLigneSetId, 3, CHARSET_UTF8);
		
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
		
		this.javadoc = new ArrayList<String>();
		
		/* DEBUT. */
		final String cheminFichierDebut 
			= BundleConfigurationProjetManager.getRacineMainResources() 
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
		
		this.javadoc.addAll(listeLignesRgSubst1);
		
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
	 * method ecrireMethodCompareTo(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li>Insère les lignes de la méthode <b>compareTo()</b>
	 * après la ligne de déclaration.</li>
	 * <li>N'insère les lignes que si elles n'existent pas déjà</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodCompareTo(
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
			
			/* Crée la methode compareTo(). */
			this.creerLignesMethodCompareTo();
			
			if (this.methodCompareTo == null) {
				return;
			}
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodCompareTo);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			/* *************** */
			/* ENREGISTREMENT. */
			/* *************** */
			for (final String ligne : this.methodCompareTo) {
				
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
				LOG.fatal("Impossible de créer la méthode compareTo()", e);
			}
		}
				
	} // Fin de ecrireMethodCompareTo(...).________________________________
	


	/**
	 * method creerLignesMethodCompareTo() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode compareTo().</li>
	 * <li>alimente this.methodCompareTo</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodCompareTo.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodCompareTo() throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodCompareTo_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$IObjet}", this.nomSimpleFichierJava); // NOPMD by dan on 08/01/18 08:09
		
		this.methodCompareTo = listeLignesSubstitue;
		
		return this.methodCompareTo;
				
	} // Fin de creerLignesMethodCompareTo().______________________________
	

		
	/**
	 * method ecrireMethodClone(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode clone()
	 * après la méthode compareTo().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodClone(
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
			
			/* Crée la methode clone(). */
			this.creerLignesMethodClone();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodClone.get(this.methodClone.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				
	} // Fin de ecrireMethodClone(...).____________________________________
	


	/**
	 * method creerLignesMethodClone() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode clone().</li>
	 * <li>alimente this.methodClone.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodClone.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodClone() throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodClone_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$IObjet}", this.nomSimpleFichierJava);
		
		this.methodClone = listeLignesSubstitue;
		
		return this.methodClone;
				
	} // Fin de creerLignesMethodClone().__________________________________
	

		
	/**
	 * method ecrireMethodGetEnTeteCsv(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode getEnTeteCsv()
	 * après la méthode clone().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodGetEnTeteCsv(
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
			
			/* Crée la methode getEnTeteCsv(). */
			this.creerLignesMethodGetEnTeteCsv();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodGetEnTeteCsv.get(this.methodGetEnTeteCsv.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				
	} // Fin de ecrireMethodGetEnTeteCsv(...).____________________________________
	
	
	
	/**
	 * method creerLignesMethodGetEnTeteCsv() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode getEnTeteCsv().</li>
	 * <li>alimente this.methodGetEnTeteCsv.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodGetEnTeteCsv.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodGetEnTeteCsv() throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodGetEnTeteCsv_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$IObjet}", this.nomSimpleFichierJava);
		
		this.methodGetEnTeteCsv = listeLignesSubstitue;
		
		return this.methodGetEnTeteCsv;
				
	} // Fin de creerLignesMethodGetEnTeteCsv().__________________________________
	

		
	/**
	 * method ecrireMethodToStringCsv(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode toStringCsv()
	 * après la méthode getEnTeteCsv().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodToStringCsv(
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
			
			/* Crée la methode toStringCsv(). */
			this.creerLignesMethodToStringCsv();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodToStringCsv.get(this.methodToStringCsv.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				
	} // Fin de ecrireMethodToStringCsv(...).______________________________
	
	
	
	/**
	 * method creerLignesMethodToStringCsv() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode toStringCsv().</li>
	 * <li>alimente this.methodToStringCsv.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodToStringCsv.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodToStringCsv() throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodToStringCsv_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$IObjet}", this.nomSimpleFichierJava);
		
		this.methodToStringCsv = listeLignesSubstitue;
		
		return this.methodToStringCsv;
				
	} // Fin de creerLignesMethodToStringCsv().____________________________
	
	
		
	/**
	 * method ecrireMethodGetEnTeteColonne(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode getEnTeteColonne()
	 * après la méthode toStringCsv().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodGetEnTeteColonne(
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
			
			/* Crée la methode getEnTeteColonne(). */
			this.creerLignesMethodGetEnTeteColonne();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodGetEnTeteColonne
					.get(this.methodGetEnTeteColonne.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				LOG.fatal("Impossible de créer la" // NOPMD by dan on 08/01/18 08:09
						+ " méthode getEnTeteColonne()", e);
			}
		}
				
	} // Fin de ecrireMethodGetEnTeteColonne(...)._________________________
	
	
	
	/**
	 * method creerLignesMethodGetEnTeteColonne() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode getEnTeteColonne().</li>
	 * <li>alimente this.methodGetEnTeteColonne.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodGetEnTeteColonne.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodGetEnTeteColonne() 
			throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodGetEnTeteColonne_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$IObjet}", this.nomSimpleFichierJava);
		
		this.methodGetEnTeteColonne = listeLignesSubstitue;
		
		return this.methodGetEnTeteColonne;
				
	} // Fin de creerLignesMethodGetEnTeteColonne()._______________________
	

		
	/**
	 * method ecrireMethodGetValeurColonne(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode getValeurColonne()
	 * après la méthode getEnTeteColonne().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodGetValeurColonne(
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
			
			/* Crée la methode getValeurColonne(). */
			this.creerLignesMethodGetValeurColonne();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodGetValeurColonne
					.get(this.methodGetValeurColonne.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				LOG.fatal("Impossible de créer la"
						+ " méthode getValeurColonne()", e);
			}
		}
				
	} // Fin de ecrireMethodGetValeurColonne(...)._________________________
	
	
	
	/**
	 * method creerLignesMethodGetValeurColonne() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode getValeurColonne().</li>
	 * <li>alimente this.methodGetValeurColonne.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodGetValeurColonne.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodGetValeurColonne() 
			throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodGetValeurColonne_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		final List<String> listeLignesSubstitue 
		= this.substituerVariablesDansLigne(
				listeLignes, "{$IObjet}", this.nomSimpleFichierJava);
		
		this.methodGetValeurColonne = listeLignesSubstitue;
		
		return this.methodGetValeurColonne;
				
	} // Fin de creerLignesMethodGetValeurColonne()._______________________
	

		
	/**
	 * method ecrireMethodGetId(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode getId()
	 * après la méthode getValeurColonne().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodGetId(
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
			
			/* Crée la methode getId(). */
			this.creerLignesMethodGetId();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.fournirDerniereLigneListe(this.methodGetId);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				
	} // Fin de ecrireMethodGetId(...)._________________________
	
	
	
	/**
	 * method creerLignesMethodGetId() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode getId().</li>
	 * <li>alimente this.methodGetId.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodGetId.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodGetId() 
			throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodGetId_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
		
		this.methodGetId = listeLignes;
		
		return this.methodGetId;
				
	} // Fin de creerLignesMethodGetId()._______________________

	
		
	/**
	 * method ecrireMethodSetId(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Insère les lignes de la méthode setId()
	 * après la méthode getId().</li>
	 * <li>Ne fait rien si la méthode a déjà été déclarée.</li>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 */
	private void ecrireMethodSetId(
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
			
			/* Crée la methode setId(). */
			this.creerLignesMethodSetId();
			
			/* Recherche la ligne contenant la signature de la methode. */
			final String dernierLigne 
				= this.methodSetId
					.get(this.methodSetId.size() - 1);
			
			/* Ne fait rien si la méthode a déjà été déclarée. */
			if (this.existLigneDansFichier(
					pFile, CHARSET_UTF8, dernierLigne)) {
				return;
			}
			
			
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
				
	} // Fin de ecrireMethodSetId(...)._________________________
	
	
	
	/**
	 * method creerLignesMethodSetId() :
	 * <ul>
	 * <li>Crée la liste des lignes de la méthode setId().</li>
	 * <li>alimente this.methodSetId.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : this.methodSetId.<br/>
	 * 
	 * @throws Exception
	 */
	private List<String> creerLignesMethodSetId() 
			throws Exception {
		
		final String cheminFichier 
			= BundleConfigurationProjetManager.getRacineMainResources() 
			+ "/templates/methodSetId_interface.txt";
		
		final File fichier = new File(cheminFichier);
		
		final List<String> listeLignes 
			= this.lireStringsDansFile(fichier, CHARSET_UTF8);
				
		this.methodSetId = listeLignes;
		
		return this.methodSetId;
				
	} // Fin de creerLignesMethodSetId()._______________________
	
	
	
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
					, final List<String> pListeGetter) throws Exception {

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

		
		pListeGetter.addAll(listeLignesDebutSubstitue3);
		
		/* CORPS. */
		this.ajouterRGsAJavadoc(pListeGetter, pNomAttribut);
		
		
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
			
		pListeGetter.addAll(listeLignesFinSubstitue3);
		
	} // Fin de creerJavadocGetter(...).___________________________________


	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeEntityGetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListeGetter) {

		// TODO Auto-generated method stub
		
	} // Fin de creerCodeEntityGetter(...).________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeGetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListeGetter) throws Exception {

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
		
		pListeGetter.addAll(listeLignesSubst2);
		
	} // Fin de creerCodeGetter(...).______________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocSetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListeSetter) {

		// TODO Auto-generated method stub
		
	} // Fin de creerJavadocSetter(...).___________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerCodeSetter(
			final String pNomAttribut
				, final String pTypeAttribut
					, final List<String> pListeSetter) {

		// TODO Auto-generated method stub
		
	} // Fin de creerCodeSetter(...).______________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirLigneIdentifianteGetter(
			final String pNomAttribut
				, final String pTypeAttribut) {
		
		final String ligneIdentifiant 
		= "\t" + pTypeAttribut + " " + this.fournirGetter(pNomAttribut);
		
		return ligneIdentifiant;
		
	} // Fin de fournirLigneIdentifianteGetter(...)._______________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifianteSetter(String pNomAttribut, String pTypeAttribut) {
		// TODO Auto-generated method stub
		return null;
	}


	
} // FIN DE LA CLASSE EcriveurInterface.-------------------------------------
