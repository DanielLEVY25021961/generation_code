package levy.daniel.application.model.services.utilitaires.copieurcontenurepertoire;

import java.io.File;
import java.nio.file.Path;

/**
 * INTERFACE ICopieurContenuRepertoireService :<br/>
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
 * @since 1 déc. 2018
 *
 */
public interface ICopieurContenuRepertoireService {

	
	
	/**
	 * <b>copie l'intégralité (répertoires et fichiers simples) 
	 * du contenu situé sous un répertoire pRacineOrigine 
	 * sous le répertoire situé à  pDestinationPath</b>.<br/>
	 * <ul>
	 * <li>récupère tout le contenu (répertoires 
	 * et fichiers simples) sous le 
	 * répertoire pRacineOrigine.</li>
	 * <li>copie tout le contenu sous le répertoire 
	 * pRacineOrigine sous pDestinationPath 
	 * en respectant l'arborescence.</li>
	 * <li>ne recopie pas la racine pRacineOrigine.</li>
	 * </ul>
	 * - ne fait rien si pRacineOrigine == null.<br/>
	 * - ne fait rien si pRacineOrigine n'existe pas.<br/>
	 * - ne fait rien si pRacineOrigine n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pRacineOrigine : File : 
	 * répertoire dont on veut copier tout le contenu 
	 * sous la racine destination.<br/>
	 * @param pRepDestinationPath : Path : 
	 * chemin absolu de la racine (répertoire) destination.<br/>
	 * 
	 * @throws Exception 
	 */
	void copierContenu(File pRacineOrigine, Path pRepDestinationPath) 
			throws Exception ;

	
	
} // FIN DE L'INTERFACE ICopieurContenuRepertoireService.--------------------