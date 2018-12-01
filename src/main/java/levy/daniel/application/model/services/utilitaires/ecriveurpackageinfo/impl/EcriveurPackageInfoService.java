package levy.daniel.application.model.services.utilitaires.ecriveurpackageinfo.impl;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
				, final Path projetCiblePath) {
		
		/* ne fait rien si pArboMain == null. */
		if (pArboMain == null) {
			return;
		}
		
		final Set<Entry<String, Path>> entrySet = pArboMain.entrySet();
		
		final Iterator<Entry<String, Path>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, Path> entry = ite.next();
			final Path pathDansProjetCible = entry.getValue();
			
			fournirFichierACopier(pathDansProjetCible, projetCiblePath);
		}
		
	} // Fin de genererPackageInfo(...).___________________________________
	
	
	private File fournirFichierACopier(
			final Path pPathDansProjetCible
				, final Path pProjetCiblePath) {
		
		System.out.println();
		System.out.println("PATH ABSOLU DANS PROJET CIBLE : " + pPathDansProjetCible);
		System.out.println("PATH ABSOLU DU PROJET CIBLE : " + pProjetCiblePath);
		final Path pathRelatif = pProjetCiblePath.relativize(pPathDansProjetCible);
		System.out.println("PATH RELATIF : " + pathRelatif);
		final Path pathAbsoluPresentProjet = Paths.get(".").toAbsolutePath();
		System.out.println("PATH ABSOLU PRESENT PROJET : " + pathAbsoluPresentProjet);
		final Path pathAbsoluRepFichierACopier = pathAbsoluPresentProjet.resolve(pathRelatif);
		System.out.println("PATH ABSOLU REPERTOIRE FICHIER A COPIER : " + pathAbsoluRepFichierACopier);
		final Path pathAbsoluFichierACopier = pathAbsoluRepFichierACopier.resolve("package-info.java");
		System.out.println("PATH ABSOLU FICHIER A COPIER : " + pathAbsoluFichierACopier);
		
		System.out.println("LE FICHIER A COPIER EXISTE : " + pathAbsoluFichierACopier.toFile().exists());
		
		return pathAbsoluFichierACopier.toFile();
		
	}
	
	
} // FIN DE LA CLASSE EcriveurPackageInfoService.----------------------------
