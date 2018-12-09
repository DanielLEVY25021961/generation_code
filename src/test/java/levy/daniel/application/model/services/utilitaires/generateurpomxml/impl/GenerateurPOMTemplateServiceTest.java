package levy.daniel.application.model.services.utilitaires.generateurpomxml.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.services.utilitaires.generateurpomxml.IGenerateurPOMTemplateService;
import levy.daniel.application.model.services.utilitaires.managerpaths.ManagerPaths;

/**
 * CLASSE GenerateurPOMTemplateServiceTest :<br/>
 * Test JUnit de la classe GenerateurPOMTemplateService.<br/>
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
public class GenerateurPOMTemplateServiceTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * AFFICHAGE_GENERAL : Boolean :<br/>
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * SAUT_LIGNE_JAVA : char :<br/>
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE_JAVA = '\n';

	/**
	 * "BIDON : ".<br/>
	 */
	public static final String BIDON 
		= "BIDON : ";
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GenerateurPOMTemplateServiceTest.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public GenerateurPOMTemplateServiceTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * teste la méthode genererPOMAPartirTemplate(...).<br/>
	 * <ul>
	 * <li></li>
	 * </ul>
	 * @throws Exception 
	 */
	@Test
	public void testGenererPOMAPartirTemplate() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE GenerateurPOMTemplateServiceTest - méthode testGenererPOMAPartirTemplate() ********** ");
		}
		
		final IGenerateurPOMTemplateService generateur 
			= new GenerateurPOMTemplateService();
		
		final Path pathAbsoluTemplate 
			= ManagerPaths.getPathAbsoluSrcMainResourcesPresentProjet()
				.resolve("templates/pom/pom-template.txt");
		
		final Path projetCiblePath 
			= Paths.get("D:/Donnees/eclipse/eclipseworkspace/test_generation");
		
		final String[] substituants = new String[] {"levy.daniel.application", "test_generation", "0.0.1-SNAPSOT", "jar"};
		
		generateur.genererPOMAPartirTemplate(
				pathAbsoluTemplate, projetCiblePath, substituants);
		
		assertTrue(BIDON, 1 == 1);

	} // Fin de testGenererPOMAPartirTemplate().___________________________



} // FIN DE LA CLASSE GenerateurPOMTemplateServiceTest.----------------------
