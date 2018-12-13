package levy.daniel.application.model.services.utilitaires.copieurconcept.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.utilitaires.arboresceurprojet.ArboresceurProjetCible;
import levy.daniel.application.model.services.utilitaires.arboresceurprojet.ArboresceurProjetSource;
import levy.daniel.application.model.services.utilitaires.copieurconcept.ICopieurConceptService;

/**
 * CLASSE CopieurConceptService :<br/>
 * SERVICE CopieurConcept concret.<br/>
 * Possède une méthode copierConcept(
 * Path pProjetSourcePath, Path pProjetCiblePath, String pNomConcept) 
 * qui copie tous les packages et classes liées au concept pNomConcept 
 * depuis pProjetSourcePath vers pProjetCiblePath.<br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * PRINCIPE DE FONCTIONNEMENT de copieurconcept :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/copieurconcept/fonctionnement_copieurconcept.png" 
 * alt="principe de fonctionnement de copieurconcept" border="1" align="center" />
 * </div>
 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * valider répertoire, valider repertoire,<br/>
 * valider path répertoire, valider path repertoire,<br/>
 * valider Path répertoire, valider Path repertoire,<br/>
 * répertoire non null, repertoire non null, <br/>
 * répertoire existant, repertoire existant, <br/>
 * répertoire Directory, repertoire isDirectory(), <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 11 déc. 2018
 *
 */
public class CopieurConceptService implements ICopieurConceptService {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(CopieurConceptService.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public CopieurConceptService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void copierConcept(
			final Path pProjetSourcePath
				, final Path pProjetCiblePath
					, final String pNomConcept) 
									throws Exception {
		
		/* copie l'objet métier. */
		this.copierConceptCoucheMetier(
				pProjetSourcePath, pProjetCiblePath, pNomConcept);
		
	} // Fin de copierConcept(...).________________________________________
	

	
	/**
	 * <b>copie tout le package (interface, classe concrète, ...) 
	 * de l'objet métier pNomConcept</b> 
	 * depuis le projet source dans le projet cible.<br/>
	 * Par exemple :<br/>
	 * copie tout le package model/metier/user depuis 
	 * le projet source vers le projet cible.<br/>
	 * <br/>
	 * - ne fait rien si pProjetSourcePath n'est pas valide.<br/>
	 * - ne fait rien si pProjetCiblePath n'est pas valide.<br/>
	 * - ne fait rien si pNomConcept n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetSourcePath : Path : 
	 * Path absolu du projet source.
	 * @param pProjetCiblePath : Path : 
	 * Path absolu du projet cible.
	 * @param pNomConcept : String : 
	 * nom de l'objet métier et de son package dans le projet source.
	 * 
	 * @throws Exception
	 */
	private void copierConceptCoucheMetier(
			final Path pProjetSourcePath
				, final Path pProjetCiblePath
					, final String pNomConcept) 
									throws Exception {
		
		/* ne fait rien si pProjetSourcePath n'est pas valide. */
		if (!this.validerPathRepertoire(pProjetSourcePath)) {
			return;
		}
		
		/* ne fait rien si pProjetCiblePath n'est pas valide. */
		if (!this.validerPathRepertoire(pProjetCiblePath)) {
			return;
		}
		
		/* ne fait rien si pNomConcept n'est pas valide. */
		if (!validerNomConcept(pNomConcept, pProjetSourcePath)) {
			return;
		}
		
		ArboresceurProjetSource
			.selectionnerProjetSource(pProjetSourcePath);
		
		ArboresceurProjetCible
			.selectionnerProjetCible(pProjetCiblePath);
		
		final Path pathObjetMetierSource 
			= ArboresceurProjetSource
				.getCoucheModelMetierMainPath()
				.resolve(pNomConcept)
				.toAbsolutePath().normalize();
				
		final Path pathObjetMetierCible 
			= ArboresceurProjetCible
				.getCoucheModelMetierMainPath()
				.resolve(pNomConcept)
				.toAbsolutePath().normalize();
		
		this.recopierPackageEtContenu(
				pathObjetMetierSource, pathObjetMetierCible);
		
	} // Fin de copierConceptCoucheMetier(...).____________________________


	
	/**
	 * <b>recopie intégralement un répertoire et son contenu</b> 
	 * depuis pPathPackageSource dans pPathPackageCible.<br/>
	 * Par exemple : <br/>
	 * recopie intégralement le package 
	 * <code>${projetSource}/sourcesJava/model/metier/user</code> 
	 * dans <code>${projetcIBLE}/sourcesJava/model/metier/user</code>
	 * <ul>
	 * <li>crée le repertoire cible si il n'existe pas déjà.</li>
	 * <li>copie le contenu du répertoire source 
	 * sous le répertoire cible.</li>
	 * </ul>
	 * - ne fait rien si pPathPackageSource n'est pas valide.<br/>
	 * - ne fait rien si pPathPackageCible == null.<br/>
	 * <br/>
	 *
	 * @param pPathPackageSource : Path : 
	 * Path du répertoire source à recopier intégralement.<br/>
	 * @param pPathPackageCible : Path : 
	 * Path du répertoire cible dans lequel on recopie 
	 * intégralement le contenu du répertoire source.<br/>
	 * 
	 * @throws Exception
	 */
	private void recopierPackageEtContenu(
			final Path pPathPackageSource
				, final Path pPathPackageCible) throws Exception {
		
		/* ne fait rien si pPathPackageSource n'est pas valide. */
		if (!this.validerPathRepertoire(pPathPackageSource)) {
			return;
		}
		
		/* ne fait rien si pPathPackageCible == null. */
		if (pPathPackageCible == null) {
			return;
		}
		
		/* crée le repertoire cible si il n'existe pas déjà. */
		final File filePackageCible = pPathPackageCible.toFile();
		
		if (!filePackageCible.exists()) {
			Files.createDirectories(pPathPackageCible);
		}
		
		final File filePackageSource = pPathPackageSource.toFile();
		
		/* récupère le contenu sous le répertoire packageSource. */
		final Map<Path, Boolean> contenuMap 
			= this.recupererContenuSous(filePackageSource);

		/* copie le contenu sous le répertoire filePackageSource 
		 * sous le répertoire situé à pPathPackageCible. */
		this.copierContenuVersDestination(
				contenuMap, pPathPackageCible, filePackageSource);

		
	}
	
	
		
	/**
	 * <b>retourne une Map&lt;Path, Boolean&gt; des Paths RELATIFS 
	 * <i>(par rapport à pRacineOrigine)</i>  de toute l'arborescence 
	 * <i>(répertoires et fichiers simples)</i> contenue
	 * sous pRacineOrigine et qui contient 
	 * la nature (Directory ou SimpleFile) des paths relatifs</b>.<br/>
	 * <ul>
	 * La Map&lt;Path, Boolean&gt; précise :
	 * <li>Path : le Path RELATIF 
	 * (par rapport à pRacineOrigine).</li>
	 * <li>Boolean : 
	 * true si le Path RELATIF correspond à un Directory.</li>
	 * </ul>
	 * par exemple :<br/>
	 * <code>recupererContenuSous(".../javadoc)</code> retourne :<br/>
	 * [images, true]<br/>
	 * [images\abstract_ids_insee.png, false]<br/>
	 * [images\apptechnic, true]<br/>
	 * [images\apptechnic\LocaleManager.png, false]<br/>
	 * ...<br/>
	 * 
	 * <ul>
	 * <li><b>ne RETOURNE PAS pRacineOrigine</b>.</li>
	 * <li>originePath.relativize(path).</li>
	 * <li>retourne null si une IOException se produit 
	 * lors de la lecture de l'arborescence.</li>
	 * </ul>
	 * - retourne null si pRacineOrigine == null.<br/>
	 * - retourne null si pRacineOrigine n'existe pas.<br/>
	 * - retourne null si pRacineOrigine n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pRacineOrigine : File : 
	 * répertoire dont on récupère le contenu.<br/> 
	 * 
	 * @return : Map&lt;Path, Boolean&gt; : 
	 * Map des Paths RELATIFS (par rapport à pRacineOrigine) 
	 * avec leur nature
	 *  de toute l'arborescence 
	 * <i>(répertoires et fichiers simples)</i> 
	 * contenue sous pRacineOrigine.<br/>
	 */
	private Map<Path, Boolean> recupererContenuSous(
			final File pRacineOrigine) {
		
		/* retourne null si pRacineOrigine == null. */
		if (pRacineOrigine == null) {
			return null;
		}
				
		/* retourne null si pRacineOrigine n'existe pas. */
		if (!pRacineOrigine.exists()) {
			return null;
		}
		
		/* retourne null si pRacineOrigine n'est pas un répertoire. */
		if (!pRacineOrigine.isDirectory()) {
			return null;
		}

		final Path originePath 
			= pRacineOrigine.toPath()
				.toAbsolutePath().normalize();

		/* retourne la liste des Paths RELATIFS 
		 * des Files sous originePath. */
		/* utilise un try-with-resources. */
		try (Stream<Path> stream 
				= Files.walk(originePath)) {

			/* calcule chaque path RELATIF par 
			 * rapport à la racine origine. */
			/* détermine si le path designait 
			 * un Directory à l'origine. */
			final Map<Path, Boolean> map = stream
					.filter(path -> !path.equals(originePath))
					.collect(Collectors.toMap(
							path -> originePath.relativize(path)
							, path -> path.toFile().isDirectory()));
			
			/* trie la map sur les keys. */
			final SortedMap<Path, Boolean> mapTriee 
				= new TreeMap<Path, Boolean>(map);
			
			return mapTriee;
			
		} catch (IOException e) {
			return null;
		}
		
	} // Fin de recupererContenuSous(...)._________________________________
	

	
	/**
	 * <b>Copie une Map&lt;Path, Boolean&gt; de Paths RELATIFS 
	 * sous un répertoire destination</b>.<br/>
	 * <b>Ecrit sur disque</b>.<br/>
	 * <ul>
	 * <li>calcule le path ABSOLU de chaque File à copier dans 
	 * le répertoire destination 
	 * (<code>pathFileACopier 
	 * = pRepDestinationPath.resolve(pathRelatif);</code>).</li>
	 * <li>ne copie le File à copier que si il n'est pas déjà 
	 * existant dans le répertoire destination.</li>
	 * <li>crée un répertoire si le Boolean 
	 * dans la Map est à true.</li>
	 * <li>crée un fichier simple si le Boolean 
	 * dans la Map est à false.</li>
	 * </ul>
	 * - ne fait rien si pContenuMap == null.<br/>
	 * - ne fait rien si pRepDestinationPath == null.<br/>
	 * - crée pRepDestinationPath et son ascendance 
	 * si il n'existe pas sur le disque.<br/>
	 * <br/>
	 *
	 * @param pContenuMap : Map&lt;Path, Boolean&gt;.<br/>
	 * @param pRepDestinationPath : Path.<br/>
	 * @param pRacineOrigine : File : racine origine.<br/>
	 * 
	 * @throws Exception
	 */
	private void copierContenuVersDestination(
			final Map<Path, Boolean> pContenuMap
				, final Path pRepDestinationPath
					, final File pRacineOrigine) 
							throws Exception {
		
		/* ne fait rien si pContenuMap == null. */
		if (pContenuMap == null) {
			return;
		}
		
		/* ne fait rien si pRepDestinationPath == null. */
		if (pRepDestinationPath == null) {
			return;
		}
				
		// Boucle sur tous les Paths RELATIFS de la Map.
		final Set<Entry<Path, Boolean>> entrySet 
			= pContenuMap.entrySet();
		
		final Iterator<Entry<Path, Boolean>> ite 
			= entrySet.iterator();
				
		while (ite.hasNext()) {
			
			final Entry<Path, Boolean> entry = ite.next();
			final Path pathRelatif = entry.getKey();
			final Boolean isDirectory = entry.getValue();
			
			if (pathRelatif != null) {
							
				/* calcule le path ABSOLU de chaque File à copier 
				 * dans le répertoire destination. */
				final Path pathFileACopier 
					= pRepDestinationPath.resolve(pathRelatif)
						.toAbsolutePath().normalize();
				
				final File fileACopier = pathFileACopier.toFile();
				
				/* ne copie le File à copier que si il n'est 
				 * pas déjà existant dans le répertoire destination. */
				if (!fileACopier.exists()) {
					
					/* crée un répertoire si le path relatif 
					 * pointait sur un répertoire à l'origine. */
					/* crée pRepDestinationPath et son ascendance 
					 * si il n'existe pas sur le disque. */
					if (isDirectory) {
						
						Files.createDirectories(pathFileACopier);
					
					/* crée un fichier simple si le path relatif 
					 * pointait sur un fichier simple à l'origine. */
					} else {
						
						final Path fileACopierParentPath 
							= pathFileACopier.getParent();
				
						if (fileACopierParentPath != null) {
							if (!fileACopierParentPath.toFile().exists()) {
								Files.createDirectories(fileACopierParentPath);
							}
						}

						final Path pathOrigine 
							= pRacineOrigine.toPath()
								.toAbsolutePath().normalize();
						
						final Path fichierOriginePath 
							= pathOrigine.resolve(pathRelatif)
								.toAbsolutePath().normalize();
						
						Files.copy(fichierOriginePath, pathFileACopier);
						
					}	
					
				} // Fin de File existant._______
				
			} // Fin de if (path != null).___________
			
		} // Fin de la boucle._________________________
		
	} // Fin de copierContenuVersDestination(...)._________________________
	

	
	/**
	 * <b>valide pPath</b> 
	 * en retournant <b>true si le Path est valide 
	 * pour un REPERTOIRE (Directory)</b> 
	 * parce qu'il est <i>non null</i> et représente 
	 * un <i>répertoire</i> déjà 
	 * <i>existant</i> dans le stockage.<br/>
	 * <ul>
	 * <li>pPath est <b>invalide</b> si il est <b>null</b>.
	 * <br/>LOG.fatal et retourne false 
	 * si pPath == null.</li>
	 * <li>pPath est <b>invalide</b> si il 
	 * désigne un File <b>inexistant</b> dans le stockage.<br/>
	 * LOG.fatal et retourne false 
	 * si pPath désigne un File inexistant.</li>
	 * <li>pPath est <b>invalide</b> si il ne désigne 
	 * <b>pas un répertoire</b>.<br/>
	 * LOG.fatal et retourne false si pPath désigne un File 
	 * qui n'est pas un répertoire.</li>
	 * </ul>
	 *
	 * @param pPath : Path.<br/>
	 * 
	 * @return : boolean : 
	 * true si le Path est valide pour un répertoire (Directory).<br/>
	 */
	private boolean validerPathRepertoire(
			final Path pPath) {
		
		/* LOG.fatal et retourne false si pPath == null. */
		if (pPath == null) {
			
			if (LOG.isFatalEnabled()) {
				
				final String message 
					= "pPath est null";
				
				/* LOG fatal. */
				LOG.fatal(message);
				
			}
			
			return false;
			
		}
		
		final File file = pPath.toFile();
		
		/* LOG.fatal et retourne false si pPath désigne 
		 * un File inexistant. */
		if (!file.exists()) {
			
			if (LOG.isFatalEnabled()) {
				
				final String message 
					= "le File désigné par " + pPath 
					+ " n'existe pas sur le stockage";
				
				/* LOG fatal. */
				LOG.fatal(message);
				
			}
			
			return false;

		}
		
		/* LOG.fatal et retourne false si pPath désigne 
		 * un File qui n'est pas un répertoire. */
		if (!file.isDirectory()) {
			
			if (LOG.isFatalEnabled()) {
				
				final String message 
					= "le File désigné par " + pPath 
					+ " n'est pas un répertoire (Directory)";
				
				/* LOG fatal. */
				LOG.fatal(message);
				
			}
			
			return false;
			
		}
		
		/* retourne true si pPath designe 
		 * un répertoire (Directory) valide. */
		return true;
		
	} // Fin de validerPathRepertoire(...).________________________________
	

	
	/**
	 * <b>valide pNomConcept</b> 
	 * en retournant <b>true si pNomConcept
	 * représente un objet métier existant</b> 
	 * dans le projet source.<br/>
	 * Un package pour le concept doit exister à 
	 * <code>"model/metier/" + pNomConcept</code>.<br/>
	 * <ul>
	 * <li>retourne false si pProjetSourcePath n'est pas valide.</li>
	 * <li>LOG.fatal et retourne false si pNomConcept est blank.</li>
	 * <li>LOG.fatal et retourne false si il n'y a 
	 * pas de package metier pour le concept.</li>
	 * </ul>
	 *
	 * @param pNomConcept : String : 
	 * nom de l'objet métier et de son package dans le projet source.
	 * @param pProjetSourcePath : Path : 
	 * Path absolu du projet source.
	 * 
	 * @return : boolean : 
	 * true si pNomConcept est valide et désigne 
	 * un objet métier existant dans le projet source.<br/>
	 */
	private boolean validerNomConcept(
			final String pNomConcept
				, final Path pProjetSourcePath) {
		
		/* retourne false si pProjetSourcePath n'est pas valide. */
		if (!this.validerPathRepertoire(pProjetSourcePath)) {
			return false;
		}
		
		/* LOG.fatal et retourne false si pNomConcept est blank. */
		if (StringUtils.isBlank(pNomConcept)) {
			
			if (LOG.isFatalEnabled()) {
				
				final String message 
					= "pNomConcept est null ou vide";
				
				/* LOG fatal. */
				LOG.fatal(message);
				
			}
			
			return false;
			
		}

		ArboresceurProjetSource
			.selectionnerProjetSource(pProjetSourcePath);
	
		final Path pathObjetMetier 
			= ArboresceurProjetSource
					.getCoucheModelMetierMainPath()
					.resolve(pNomConcept)
					.toAbsolutePath().normalize();
		
		final File packageObjetMetier = pathObjetMetier.toFile();
		
		/* LOG.fatal et retourne false si il n'y a 
		 * pas de package metier pour le concept. */
		if (!packageObjetMetier.exists()) {
			
			if (LOG.isFatalEnabled()) {
				
				final String message 
					= "Il n'existe pas de package pour l'objet métier à : " 
							+ pathObjetMetier;
				
				/* LOG fatal. */
				LOG.fatal(message);
				
			}
			
			return false;
			
		}
		
		/* true si pNomConcept est valide et désigne 
		 * un objet métier existant dans le projet source. */
		return true;
		
	} // Fin de validerNomConcept(...).____________________________________
	
	
	
} // FIN DE LA CLASSE CopieurConceptService.---------------------------------
