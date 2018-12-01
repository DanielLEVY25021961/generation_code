package levy.daniel.application.model.services.utilitaires.generateurprojet.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.utilitaires.arboresceurprojet.ArboresceurProjetCible;
import levy.daniel.application.model.services.utilitaires.ecriveurpackageinfo.IEcriveurPackageInfoService;
import levy.daniel.application.model.services.utilitaires.ecriveurpackageinfo.impl.EcriveurPackageInfoService;
import levy.daniel.application.model.services.utilitaires.generateurprojet.IGenerateurProjetService;

/**
 * CLASSE GenerateurProjetService :<br/>
 * Classe concrète SERVICE chargée d'<b>écrire une arborescence de REPERTOIRES
 * de projet MAVEN SIMPLE (sans artéfact) sur disque 
 * dans un projet cible</b>.<br/>
 * Toutes les couches (GroupId, apptechnic, controllers, model, vues...)
 * ainsi que leurs sous-couches et les répertoires externes (data, logs, ...) 
 * sont générés dans le projet cible.<br/>
 * L'arborescence à copier dans le projet cible est fournie 
 * par un {@link ArboresceurProjetCible}.<br/>
 * <br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * Création des répértoires et packages sous un projet cible par GenerateurProjetService :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/generateurprojet/generateurprojet_resultat.png" 
 * alt="création des répertoires par GenerateurProjetService" border="1" align="center" />
 * </div>
 * <br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE CLASSES de GenerateurProjetService :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/generateurprojet/generateurprojet_service.png" 
 * alt="generateurprojet SERVICE" border="1" align="center" />
 * </div>
 * <br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>
 *  // Path du projet cible.<br/>
 * projetCiblePath = Paths.get("D:/Donnees/toto");<br/>
 *  // INSTANCIATION D'UN GenerateurProjetService.<br/>
 * <b>IGenerateurProjetService generateur = new GenerateurProjetService();</b><br/>
 *  // GENERATION DE L'ARBORESCENCE DANS LE PROJET CIBLE.<br/>
 * <b>generateur.generer(projetCiblePath);</b><br/>
 * </code>
 *<br/>
 * 
 * 
 * - Mots-clé :<br/>
 * écriture fichier sur disque, ecrire sur disque, ecriture fichier, <br/>
 * ecriture répertoire sur disque, ecrire repertoire sur disque, <br/>
 * Files.createDirectories(path);<br/>
 * Création arborescence sur disque, ecriture arborescence sur disque,<br/>
 * écriture arborescence sur disque,
 * création récursive de répertoires, recursif, récursif,<br/>
 * obtenir Path à partir de String, Paths.get(String), <br/>
 * génération arborescence dans projet cible,<br/>
 * generation arborescence dans projet cible,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel Lévy
 * @version 1.0
 * @since 26 nov. 2018
 *
 */
public class GenerateurProjetService implements IGenerateurProjetService {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Service chargé d'écrire les package-info.
	 */
	private final transient IEcriveurPackageInfoService ecriveurPackageInfoService 
		= new EcriveurPackageInfoService();

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
	= LogFactory.getLog(GenerateurProjetService.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public GenerateurProjetService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void generer(
			final Path pProjetCiblePath) throws IOException {
		
		this.generer(pProjetCiblePath, null);
		
	} // Fin de generer(...).______________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void generer(
			final Path pProjetCiblePath
				, final String pGroupId) throws IOException {
		
		/* ne fait rien si pProjetCiblePath == null. */
		if (pProjetCiblePath == null) {
			return;
		}
		
		final File projetCibleFile = pProjetCiblePath.toFile();
		
		/* ne fait rien si le projet cible n'existe pas. */
		if (!projetCibleFile.exists()) {
			return;
		}
		
		
		List<Path> arborescence = null;
		Path projetCiblePath = null;
		Map<String, Path> arborescenceMainProjetCibleMap = null;
		Map<String, Path> arborescenceTestProjetCibleMap = null;
		Map<String, Path> arborescenceRepExtProjetCibleMap = null;
		
		/* GroupId par défaut GROUPID_PAR_DEFAUT dans 
		 * ArboresceurProjetCible si pGroupId est blank. */
		if (StringUtils.isBlank(pGroupId)) {
			
			ArboresceurProjetCible.selectionnerProjetCible(pProjetCiblePath);
			arborescence = ArboresceurProjetCible.getArborescenceProjetCible();
			projetCiblePath = ArboresceurProjetCible.getProjetCiblePath();
			arborescenceMainProjetCibleMap 
				= ArboresceurProjetCible.getArborescenceMainProjetCibleMap();
			arborescenceTestProjetCibleMap 
				= ArboresceurProjetCible.getArborescenceTestProjetCibleMap();
			arborescenceRepExtProjetCibleMap 
				= ArboresceurProjetCible.getArborescenceRepertoiresExternesProjetCibleMap();
			
		} else {
			
			final Path groupIdPath = Paths.get(pGroupId);
			ArboresceurProjetCible.setGroupIdPathRelatif(groupIdPath);
			ArboresceurProjetCible.selectionnerProjetCible(pProjetCiblePath);			
			arborescence = ArboresceurProjetCible.getArborescenceProjetCible();
			arborescenceMainProjetCibleMap 
				= ArboresceurProjetCible.getArborescenceMainProjetCibleMap();
			arborescenceTestProjetCibleMap 
				= ArboresceurProjetCible.getArborescenceTestProjetCibleMap();
			arborescenceRepExtProjetCibleMap 
				= ArboresceurProjetCible.getArborescenceRepertoiresExternesProjetCibleMap();
			
		}
		
		/* écrit l'arborescence sur disque. */
		this.ecrireSurDisque(arborescence);
		
		/* écrit tous les package-info sur disque. */
		this.ecriveurPackageInfoService
			.genererPackageInfo(arborescenceMainProjetCibleMap, projetCiblePath);
		
	} // Fin de generer(...).______________________________________________
	

	
	/**
	 * <b>écrit sur disque l'ensemble de 
	 * l'arborescence pArborescence</b>.<br/>
	 * <ul>
	 * <li><b>ne crée jamais de doublons.</b></li>
	 * <li>crée chaque répertoire et son ascendance si nécessaire.</li>
	 * </ul>
	 * - ne fait rien si pArborescence == null.<br/>
	 * - ne crée pas un élément de l'arborescence 
	 * si il est déjà existant sur disque.<br/>
	 * <br/>
	 *
	 * @param pArborescence : List&lt;Path&gt;.<br/>
	 * @throws IOException
	 */
	private void ecrireSurDisque(
			final List<Path> pArborescence) 
										throws IOException {
		
		/* ne fait rien si pArborescence == null. */
		if (pArborescence == null) {
			return;
		}
		
		for (final Path path : pArborescence) {
			
			if (path == null) {
				continue;
			}
			
			/* ne crée pas un élément de l'arborescence 
			 * si il est déjà existant sur disque. */
			final File file = path.toFile();
			if (file.exists()) {
				continue;
			}
			
			/* crée chaque répertoire et son ascendance si nécessaire. */
			Files.createDirectories(path);
			
		}
		
	} // Fin de ecrireSurDisque(...).______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String afficherArborescence(
			final List<Path> pArborescence) {

		/* retourne null si pArborescence == null. */
		if (pArborescence == null) {
			return null;
		}
		final StringBuilder stb = new StringBuilder();

		for (final Path path : pArborescence) {
			if (path != null) {
				stb.append(path.toString());
				stb.append(System.getProperty("line.separator"));
			}
		}

		return stb.toString();

	} // Fin de afficherArborescence(...)._________________________________
	
	

} // FIN DE LA CLASSE GenerateurProjetService.-------------------------------
