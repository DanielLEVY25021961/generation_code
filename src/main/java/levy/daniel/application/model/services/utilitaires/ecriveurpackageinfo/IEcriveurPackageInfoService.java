package levy.daniel.application.model.services.utilitaires.ecriveurpackageinfo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import levy.daniel.application.model.services.utilitaires.arboresceurprojet.ArboresceurProjetCible;
import levy.daniel.application.model.services.utilitaires.generateurprojet.impl.GenerateurProjetService;

/**
 * INTERFACE IEcriveurPackageInfoService :<br/>
 * Abstraction des SERVICES chargés d'<b>écrire les package-info sur disque 
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
public interface IEcriveurPackageInfoService {

	
	
	/**
	 * <b>génère tous les package-info</b> dans la branche <b>main</b> 
	 * (pas de package-info dans les tests) 
	 * d'une arboresence dans un projet cible 
	 * fournie par un GenerateurProjetService.<br/>
	 * - ne fait rien si pArboMain == null.<br/>
	 * <br/>
	 *
	 * @param pArboMain : Map&lt;String, Path&gt; : 
	 * arborescence de la branche main du projet cible.<br/>
	 * @param projetCiblePath : Path : Path du projet cible.<br/>
	 * 
	 * @throws IOException 
	 */
	void genererPackageInfo(
			Map<String, Path> pArboMain, Path projetCiblePath) 
						throws IOException;
	
	
	
} // FIN DE L'INTERFACE IEcriveurPackageInfoService.-------------------------