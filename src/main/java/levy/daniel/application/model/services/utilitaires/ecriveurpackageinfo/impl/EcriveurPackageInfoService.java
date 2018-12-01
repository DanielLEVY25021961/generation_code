package levy.daniel.application.model.services.utilitaires.ecriveurpackageinfo.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.utilitaires.arboresceurprojet.ArboresceurProjetCible;
import levy.daniel.application.model.services.utilitaires.ecriveurpackageinfo.IEcriveurPackageInfoService;
import levy.daniel.application.model.services.utilitaires.generateurprojet.impl.GenerateurProjetService;

/**
 * CLASSE EcriveurPackageInfoService :<br/>
 * Classe concrète SERVICE chargée d'<b>écrire les package-info sur disque 
 * dans un projet cible</b>.<br/>
 * L'arborescence dans le projet cible doit avoir été générée 
 * par un {@link GenerateurProjetService}.<br/>
 * L'arborescence à copier dans le projet cible est fournie 
 * par un {@link ArboresceurProjetCible}.<br/>
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
 * @since 1 déc. 2018
 *
 */
public class EcriveurPackageInfoService implements IEcriveurPackageInfoService {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(EcriveurPackageInfoService.class);

	// *************************METHODES************************************/
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public EcriveurPackageInfoService() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void genererPackageInfo(
			final Map<String, Path> pArboMain
				, final Path projetCiblePath) throws IOException {
		
		/* ne fait rien si pArboMain == null. */
		if (pArboMain == null) {
			return;
		}
		
		final Set<Entry<String, Path>> entrySet = pArboMain.entrySet();
		
		final Iterator<Entry<String, Path>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, Path> entry = ite.next();
			final Path pathDansProjetCible = entry.getValue();
			
			if (pathDansProjetCible != null) {
				
				final Path pathFichierDestination 
					= pathDansProjetCible.resolve("package-info.java");
			
				final File packageInfoACopier = 
					fournirFichierACopier(
							pathDansProjetCible, projetCiblePath);
				
				if (packageInfoACopier != null) {
					Files.copy(
							packageInfoACopier.toPath()
								, pathFichierDestination
									, StandardCopyOption.REPLACE_EXISTING);
				}
			}
			
		}
		
	} // Fin de genererPackageInfo(...).___________________________________
	

	
	/**
	 * <b>fournit le fichier package-info</b> 
	 * correspondant à pPathDansProjetCible.<br/>
	 * Le présent projet suit l'arborescence du projet cible 
	 * et contient tous les bons package-info 
	 * dans les bons répertoires.<br/>
	 * <br/>
	 * - retourne null si il n'existe pas de package-info 
	 * pour pPathDansProjetCible.<br/>
	 * - retourne null si pPathDansProjetCible == null.<br/>
	 * - retourne null si pProjetCiblePath == null.<br/>
	 * <br/>
	 *
	 * @param pPathDansProjetCible : Path : 
	 * path dans le projet cible dans lequel écrire un package-info.<br/>
	 * @param pProjetCiblePath : Path : 
	 * Path du projet cible.<br/>
	 * 
	 * @return : File : 
	 * package-info dans la présent projet correspondant 
	 * à pPathDansProjetCible.<br/>
	 */
	private File fournirFichierACopier(
			final Path pPathDansProjetCible
				, final Path pProjetCiblePath) {
		
		/* retourne null si pPathDansProjetCible == null. */
		if (pPathDansProjetCible == null) {
			return null;
		}
		
		/* retourne null si pProjetCiblePath == null. */
		if (pProjetCiblePath == null) {
			return null;
		}
		
		/* extraction du path relatif de pPathDansProjetCible 
		 * par rapport à pProjetCiblePath. */
		final Path pathRelatif 
			= pProjetCiblePath.relativize(pPathDansProjetCible);
		
		/* récupération du path du présent projet. */
		final Path pathAbsoluPresentProjet = Paths.get(".").toAbsolutePath();

		/* path absolu du répertoire dans le présent projet contenant 
		 * le package-info correspondant à pPathDansProjetCible. */
		final Path pathAbsoluRepFichierACopier 
			= pathAbsoluPresentProjet.resolve(pathRelatif);
		
		/* path absolu package-info dans le présent projet 
		 * correspondant à pPathDansProjetCible. */
		final Path pathAbsoluFichierACopier 
			= pathAbsoluRepFichierACopier.resolve("package-info.java");
		
		final File fichierACopier = pathAbsoluFichierACopier.toFile();
		
		if (fichierACopier.exists()) {
			return fichierACopier;
		}
		
		/* retourne null si il n'existe pas de package-info 
		 * pour pPathDansProjetCible. */
		return null;
		
	} // Fin de fournirFichierACopier(...).________________________________
	
	
	
	
} // FIN DE LA CLASSE EcriveurPackageInfoService.----------------------------
