package levy.daniel.application.model.services.utilitaires.copieurconcept.impl;

import java.io.File;
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
	

	
	/**
	 * .<br/>
	 * <br/>
	 * - ne fait rien si pProjetSourcePath n'est pas valide.<br/>
	 * - ne fait rien si pProjetCiblePath n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetSourcePath
	 * @param pProjetCiblePath
	 * @param pNomConcept
	 * @throws Exception : void :  .<br/>
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
	}


	
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
					= "le File désigné par " 
							+ pPath + " est null";
				
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
	
	
	
} // FIN DE LA CLASSE CopieurConceptService.---------------------------------
