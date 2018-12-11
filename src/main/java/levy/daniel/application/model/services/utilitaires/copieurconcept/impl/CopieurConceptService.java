package levy.daniel.application.model.services.utilitaires.copieurconcept.impl;

import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
public class CopieurConceptService {

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
	 * .<br/>
	 * <ul>
	 * <li></li>
	 * </ul>
	 *
	 * @param pProjetSourcePath
	 * @param pProjetCiblePath
	 * @param pNomConcept
	 * @throws Exception : void :  .<br/>
	 */
	public void copierConcept(
			final Path pProjetSourcePath
				, final Path pProjetCiblePath
					, final String pNomConcept) 
									throws Exception {
		
		/**/
	}

	
	
} // FIN DE LA CLASSE CopieurConceptService.---------------------------------
