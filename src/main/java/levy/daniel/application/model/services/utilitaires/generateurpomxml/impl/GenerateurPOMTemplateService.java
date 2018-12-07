package levy.daniel.application.model.services.utilitaires.generateurpomxml.impl;

import java.io.File;
import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.utilitaires.generateurpomxml.IGenerateurPOMTemplateService;
import levy.daniel.application.model.services.utilitaires.gestionnairestemplates.IGestionnaireTemplatesService;
import levy.daniel.application.model.services.utilitaires.gestionnairestemplates.impl.GestionnaireTemplatesService;
import levy.daniel.application.model.services.utilitaires.managerpaths.ManagerPaths;

/**
 * CLASSE GenerateurPOMTemplateService :<br/>
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
public class GenerateurPOMTemplateService implements IGenerateurPOMTemplateService {

	// ************************ATTRIBUTS************************************/

	/**
	 * SERVICE pour la gestion des templates.<br/>
	 */
	private final transient IGestionnaireTemplatesService templatesService 
		= new GestionnaireTemplatesService();
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GenerateurPOMTemplateService.class);
	

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public GenerateurPOMTemplateService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public File genererPOMAPartirTemplate(
			final Path pPathAbsoluTemplate
				, final String... pSubstituants) 
						throws Exception {
		
		/* extrait le path relatif*/
		final Path pathRelatifTemplate 
			= ManagerPaths
				.getPathAbsoluSrcMainResourcesPresentProjet()
					.relativize(pPathAbsoluTemplate)
						.normalize();
		
		System.out.println("PATH RELATIF TEMPLATE POM : " + pathRelatifTemplate);
		
		String cheminRelatifTemplate = null;
		String pomString = null;
		
		/* tableau des variables à substituer dans le template de POM. */
		final String[] variablesASubstituer 
			= new String[] {"${groupId}", "${nomProjet}", "${version}", "${packaging}"};
		
		if (pathRelatifTemplate != null) {

			cheminRelatifTemplate = pathRelatifTemplate.toString();

			pomString = 
					this.templatesService
					.fournirTemplateSubstitueSousFormeString(
							cheminRelatifTemplate
								, variablesASubstituer
									, pSubstituants);
		}
		
		
		System.out.println();
		System.out.println(pomString);
		
		return null;
		
	}

} // FIN DE LA CLASSE GenerateurPOMTemplateService.--------------------------
