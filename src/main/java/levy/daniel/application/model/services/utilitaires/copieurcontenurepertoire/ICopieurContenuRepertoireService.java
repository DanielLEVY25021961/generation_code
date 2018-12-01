package levy.daniel.application.model.services.utilitaires.copieurcontenurepertoire;

import java.io.File;

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
	 * .<br/>
	 * <br/>
	 * - ne fait rien si pRacineOrigine == null.<br/>
	 * - ne fait rien si pRacineOrigine n'existe pas.<br/>
	 * - ne fait rien si pRacineOrigine n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pRacineOrigine : File : 
	 * répertoire dont on veut copier le contenu 
	 * sous la racine destination.<br/>
	 * @param pCheminDestination : String : 
	 * chemin absolu de la racine destination.<br/>
	 */
	void copierContenu(File pRacineOrigine, String pCheminDestination);

	
	
} // FIN DE L'INTERFACE ICopieurContenuRepertoireService.--------------------