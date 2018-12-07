package levy.daniel.application.model.services.utilitaires.generateurpomxml;

import java.io.File;
import java.nio.file.Path;

/**
 * INTERFACE IGenerateurPOMTemplateService :<br/>
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
 * @since 7 déc. 2018
 *
 */
public interface IGenerateurPOMTemplateService {

	
	
	/**
	 * <b>génère un POM à partir d'un template.</b>.<br/>
	 * <ul>
	 * <li>String[] pSubstituants doit respecter 
	 * <code>new String[] {"${groupId}", "${nomProjet}"
	 * , "${version}", "${packaging}"};</code></li>
	 * </ul>
	 *
	 * @param pPathAbsoluTemplate : Path :
	 * @param pSubstituants : String[] :
	 * 
	 * @return  POM : File : 
	 *  
	 * @throws Exception
	 */
	File genererPOMAPartirTemplate(Path pPathAbsoluTemplate, String... pSubstituants) throws Exception;
	

} // FIN DE L'INTERFACE IGenerateurPOMTemplateService.-----------------------