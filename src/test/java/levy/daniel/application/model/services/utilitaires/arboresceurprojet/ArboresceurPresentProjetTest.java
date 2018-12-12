package levy.daniel.application.model.services.utilitaires.arboresceurprojet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.services.utilitaires.managerpaths.ManagerPaths;

/**
 * CLASSE ArboresceurPresentProjetTest :<br/>
 * Test JUnit de la classe ArboresceurPresentProjet.<br/>
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
public class ArboresceurPresentProjetTest {

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
	 * "groupId dans le présent projet : ".<br/>
	 */
	public static final String GROUPID_DANS_PRESENT_PROJET 
		= "groupId dans le présent projet : ";
	
	/**
	 * "groupIdPathRelatif dans le présent projet : ".<br/>
	 */
	public static final String GROUPIDPATHRELATIF_DANS_PRESENT_PROJET 
		= "groupIdPathRelatif dans le présent projet : ";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(ArboresceurPresentProjetTest.class);
	
	// *************************METHODES************************************/	

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ArboresceurPresentProjetTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * teste l'ensemble de l'arboresceur.<br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCalculerPaths() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testCalculerPaths() ********** ");
		}

		
		final Path projetSourcePathStocke 
			= ArboresceurPresentProjet.getProjetSourcePath();
		final String projetSourceNom 
			= ArboresceurPresentProjet.getProjetSourceNom();
		
		final Path srcMainJavaPath 
			= ArboresceurPresentProjet.getSrcMainJavaPath();
		final Path srcMainResourcesPath 
			= ArboresceurPresentProjet.getSrcMainResourcesPath();
		final Path srcMainResourcesMetaInfPath 
			= ArboresceurPresentProjet.getSrcMainResourcesMetaInfPath();
		final Path srcTestJavaPath 
			= ArboresceurPresentProjet.getSrcTestJavaPath();
		final Path srcTestResourcesPath 
			= ArboresceurPresentProjet.getSrcTestResourcesPath();
		final Path srcTestResourcesMetaInfPath 
			= ArboresceurPresentProjet.getSrcTestResourcesMetaInfPath();
		
		// GROUPID
		final String groupId 
			= ArboresceurPresentProjet.getGroupId();
		final Path groupIdPathRelatif 
			= ArboresceurPresentProjet.getGroupIdPathRelatif();
		
		// RACINEs DES SOURCES ET TESTS
		final Path racineSourcesJavaPath 
			= ArboresceurPresentProjet.getRacineSourcesJavaPath();
		final Path racineTestsJavaPath 
			= ArboresceurPresentProjet.getRacineTestsJavaPath();
		
		// APPTECHNIC
		final Path coucheAppTechnicMainPath 
			= ArboresceurPresentProjet.getCoucheAppTechnicMainPath();
		final Path coucheAppTechnicTestPath 
			= ArboresceurPresentProjet.getCoucheAppTechnicTestPath();
		
		// CONTROLLERS
		final Path coucheControllersMainPath 
			= ArboresceurPresentProjet.getCoucheControllersMainPath();
		final Path coucheControllersTestPath 
			= ArboresceurPresentProjet.getCoucheControllersTestPath();
		
		final Path coucheControllersDesktopMainPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopMainPath();
		final Path coucheControllersDesktopTestPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopTestPath();
		final Path coucheControllersDesktopAccueilMainPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopAccueilMainPath();
		final Path coucheControllersDesktopAccueilTestPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopAccueilTestPath();
		final Path coucheControllersDesktopMetierMainPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopMetierMainPath();
		final Path coucheControllersDesktopMetierTestPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopMetierTestPath();
		final Path coucheControllersDesktopUtilitairesMainPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopUtilitairesMainPath();
		final Path coucheControllersDesktopUtilitairesTestPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopUtilitairesTestPath();
	
		final Path coucheControllersWebMainPath 
			= ArboresceurPresentProjet.getCoucheControllersWebMainPath();
		final Path coucheControllersWebTestPath 
			= ArboresceurPresentProjet.getCoucheControllersWebTestPath();
		final Path coucheControllersWebAccueilMainPath 
			= ArboresceurPresentProjet.getCoucheControllersWebAccueilMainPath();
		final Path coucheControllersWebAccueilTestPath 
			= ArboresceurPresentProjet.getCoucheControllersWebAccueilTestPath();
		final Path coucheControllersWebMetierMainPath 
			= ArboresceurPresentProjet.getCoucheControllersWebMetierMainPath();
		final Path coucheControllersWebMetierTestPath 
			= ArboresceurPresentProjet.getCoucheControllersWebMetierTestPath();
		final Path coucheControllersWebUtilitairesMainPath 
			= ArboresceurPresentProjet.getCoucheControllersWebUtilitairesMainPath();
		final Path coucheControllersWebUtilitairesTestPath 
			= ArboresceurPresentProjet.getCoucheControllersWebUtilitairesTestPath();
		
		// VUES
		final Path coucheVuesMainPath 
			= ArboresceurPresentProjet.getCoucheVuesMainPath();
		final Path coucheVuesTestPath 
			= ArboresceurPresentProjet.getCoucheVuesTestPath();
		
		final Path coucheVuesDesktopMainPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopMainPath();
		final Path coucheVuesDesktopTestPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopTestPath();
		final Path coucheVuesDesktopAccueilMainPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopAccueilMainPath();
		final Path coucheVuesDesktopAccueilTestPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopAccueilTestPath();
		final Path coucheVuesDesktopMetierMainPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopMetierMainPath();
		final Path coucheVuesDesktopMetierTestPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopMetierTestPath();
		final Path coucheVuesDesktopUtilitairesMainPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopUtilitairesMainPath();
		final Path coucheVuesDesktopUtilitairesTestPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopUtilitairesTestPath();

		final Path coucheVuesWebMainPath 
			= ArboresceurPresentProjet.getCoucheVuesWebMainPath();
		final Path coucheVuesWebTestPath 
			= ArboresceurPresentProjet.getCoucheVuesWebTestPath();
		final Path coucheVuesWebAccueilMainPath 
			= ArboresceurPresentProjet.getCoucheVuesWebAccueilMainPath();
		final Path coucheVuesWebAccueilTestPath 
			= ArboresceurPresentProjet.getCoucheVuesWebAccueilTestPath();
		final Path coucheVuesWebMetierMainPath 
			= ArboresceurPresentProjet.getCoucheVuesWebMetierMainPath();
		final Path coucheVuesWebMetierTestPath 
			= ArboresceurPresentProjet.getCoucheVuesWebMetierTestPath();
		final Path coucheVuesWebUtilitairesMainPath 
			= ArboresceurPresentProjet.getCoucheVuesWebUtilitairesMainPath();
		final Path coucheVuesWebUtilitairesTestPath 
			= ArboresceurPresentProjet.getCoucheVuesWebUtilitairesTestPath();

		
		// MODEL
		final Path coucheModelMainPath 
			= ArboresceurPresentProjet.getCoucheModelMainPath();
		final Path coucheModelTestPath 
			= ArboresceurPresentProjet.getCoucheModelTestPath();
		
		final Path coucheModelDTOMainPath 
			= ArboresceurPresentProjet.getCoucheModelDTOMainPath();
		final Path coucheModelDTOTestPath 
			= ArboresceurPresentProjet.getCoucheModelDTOTestPath();
		final Path coucheModelDTOMetierMainPath 
			= ArboresceurPresentProjet.getCoucheModelDTOMetierMainPath();
		final Path coucheModelDTOMetierTestPath 
			= ArboresceurPresentProjet.getCoucheModelDTOMetierTestPath();
		
		final Path coucheModelMetierMainPath 
			= ArboresceurPresentProjet.getCoucheModelMetierMainPath();
		final Path coucheModelMetierTestPath 
			= ArboresceurPresentProjet.getCoucheModelMetierTestPath();

		final Path coucheModelPersistenceMainPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceMainPath();
		final Path coucheModelPersistenceTestPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceTestPath();
		final Path coucheModelPersistenceAccueilMainPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceAccueilMainPath();
		final Path coucheModelPersistenceAccueilTestPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceAccueilTestPath();
		final Path coucheModelPersistenceDaoexceptionsMainPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceDaoexceptionsMainPath();
		final Path coucheModelPersistenceDaoexceptionsTestPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceDaoexceptionsTestPath();
		final Path coucheModelPersistenceMetierMainPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceMetierMainPath();
		final Path coucheModelPersistenceMetierTestPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceMetierTestPath();

		final Path coucheModelServicesMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesMainPath();
		final Path coucheModelServicesTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesTestPath();
		final Path coucheModelServicesAccueilMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesAccueilMainPath();
		final Path coucheModelServicesAccueilTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesAccueilTestPath();
		final Path coucheModelServicesMetierMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesMetierMainPath();
		final Path coucheModelServicesMetierTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesMetierTestPath();
		final Path coucheModelServicesTransformeursMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesTransformeursMainPath();
		final Path coucheModelServicesTransformeursMetierMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesTransformeursMetierMainPath();
		final Path coucheModelServicesTransformeursTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesTransformeursTestPath();
		final Path coucheModelServicesTransformeursMetierTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesTransformeursMetierTestPath();
		final Path coucheModelServicesUtilitairesMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesUtilitairesMainPath();
		final Path coucheModelServicesUtilitairesTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesUtilitairesTestPath();
		final Path coucheModelServicesValideursMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesValideursMainPath();
		final Path coucheModelServicesValideursMetierMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesValideursMetierMainPath();
		final Path coucheModelServicesValideursTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesValideursTestPath();
		final Path coucheModelServicesValideursMetierTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesValideursMetierTestPath();
		
		final Path coucheModelUtilitairesMainPath 
			= ArboresceurPresentProjet.getCoucheModelUtilitairesMainPath();
		final Path coucheModelUtilitairesTestPath 
			= ArboresceurPresentProjet.getCoucheModelUtilitairesTestPath();
		
		// REPERTOIRES EXTERNES
		final Path conceptionAppliPath 
			= ArboresceurPresentProjet.getConceptionAppliPath();
		final Path dataPath 
			= ArboresceurPresentProjet.getDataPath();
		final Path dataH2Path 
			= ArboresceurPresentProjet.getDataH2Path();
		final Path dataHSQLDBPath 
			= ArboresceurPresentProjet.getDataHSQLDBPath();
		final Path dataJAXBPath 
			= ArboresceurPresentProjet.getDataJAXBPath();
		final Path dataScriptsSqlPath 
			= ArboresceurPresentProjet.getDataScriptsSqlPath();
		final Path javadocPath 
			= ArboresceurPresentProjet.getJavadocPath();
		final Path javadocImagesPath 
			= ArboresceurPresentProjet.getJavadocImagesPath();
		final Path logsPath 
			= ArboresceurPresentProjet.getLogsPath();
		final Path rapportsControlePath 
			= ArboresceurPresentProjet.getRapportsControlePath();
		final Path ressourcesExternesPath 
			= ArboresceurPresentProjet.getRessourcesExternesPath();

		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println("projet cible : " + projetSourcePathStocke);
			System.out.println("Nom du projet cible : " + projetSourceNom);
			
			System.out.println("sources java srcMainJavaPath : " + srcMainJavaPath);
			System.out.println("ressources srcMainResourcesPath : " + srcMainResourcesPath);
			System.out.println("ressources srcMainResourcesPath/META-INF : " + srcMainResourcesMetaInfPath);
			System.out.println("tests JUnit srcTestJavaPath : " + srcTestJavaPath);
			System.out.println("ressources des tests JUnit srcTestResourcesPath : " + srcTestResourcesPath);
			System.out.println("ressources srcTestResourcesPath/META-INF : " + srcTestResourcesMetaInfPath);
			
			System.out.println("groupId : " + groupId);
			System.out.println("groupIdPathRelatif : " + groupIdPathRelatif);
			
			System.out.println("racine des sources java racineSourcesJavaPath : " + racineSourcesJavaPath);
			System.out.println("racine des sources des tests JUnit racineTestsJavaPath : " + racineTestsJavaPath);
			
			// APPTECHNIC
			System.out.println("couche apptechnic : " + coucheAppTechnicMainPath);
			System.out.println("couche test apptechnic : " + coucheAppTechnicTestPath);
			
			// CONTROLLERS
			System.out.println("couche controllers : " + coucheControllersMainPath);
			System.out.println("couche test controllers : " + coucheControllersTestPath);
			
			System.out.println("couche controllers/desktop : " + coucheControllersDesktopMainPath);
			System.out.println("couche test controllers/desktop : " + coucheControllersDesktopTestPath);
			System.out.println("couche controllers/desktop/accueil : " + coucheControllersDesktopAccueilMainPath);
			System.out.println("couche test controllers/desktop/accueil : " + coucheControllersDesktopAccueilTestPath);
			System.out.println("couche controllers/desktop/metier : " + coucheControllersDesktopMetierMainPath);
			System.out.println("couche test controllers/desktop/metier : " + coucheControllersDesktopMetierTestPath);
			System.out.println("couche controllers/desktop/utilitaires : " + coucheControllersDesktopUtilitairesMainPath);
			System.out.println("couche test controllers/desktop/utilitaires : " + coucheControllersDesktopUtilitairesTestPath);

			System.out.println("couche controllers/web : " + coucheControllersWebMainPath);
			System.out.println("couche test controllers/web : " + coucheControllersWebTestPath);
			System.out.println("couche controllers/web/accueil : " + coucheControllersWebAccueilMainPath);
			System.out.println("couche test controllers/web/accueil : " + coucheControllersWebAccueilTestPath);
			System.out.println("couche controllers/web/metier : " + coucheControllersWebMetierMainPath);
			System.out.println("couche test controllers/web/metier : " + coucheControllersWebMetierTestPath);
			System.out.println("couche controllers/web/utilitaires : " + coucheControllersWebUtilitairesMainPath);
			System.out.println("couche test controllers/web/utilitaires : " + coucheControllersWebUtilitairesTestPath);
			
			// VUES
			System.out.println("couche vues : " + coucheVuesMainPath);
			System.out.println("couche test vues : " + coucheVuesTestPath);
			
			System.out.println("couche vues/desktop : " + coucheVuesDesktopMainPath);
			System.out.println("couche test vues/desktop : " + coucheVuesDesktopTestPath);
			System.out.println("couche vues/desktop/accueil : " + coucheVuesDesktopAccueilMainPath);
			System.out.println("couche test vues/desktop/accueil : " + coucheVuesDesktopAccueilTestPath);
			System.out.println("couche vues/desktop/metier : " + coucheVuesDesktopMetierMainPath);
			System.out.println("couche test vues/desktop/metier : " + coucheVuesDesktopMetierTestPath);
			System.out.println("couche vues/desktop/utilitaires : " + coucheVuesDesktopUtilitairesMainPath);
			System.out.println("couche test vues/desktop/utilitaires : " + coucheVuesDesktopUtilitairesTestPath);

			System.out.println("couche vues/web : " + coucheVuesWebMainPath);
			System.out.println("couche test vues/web : " + coucheVuesWebTestPath);
			System.out.println("couche vues/web/accueil : " + coucheVuesWebAccueilMainPath);
			System.out.println("couche test vues/web/accueil : " + coucheVuesWebAccueilTestPath);
			System.out.println("couche vues/web/metier : " + coucheVuesWebMetierMainPath);
			System.out.println("couche test vues/web/metier : " + coucheVuesWebMetierTestPath);
			System.out.println("couche vues/web/utilitaires : " + coucheVuesWebUtilitairesMainPath);
			System.out.println("couche test vues/web/utilitaires : " + coucheVuesWebUtilitairesTestPath);

			// MODEL
			System.out.println("couche model : " + coucheModelMainPath);
			System.out.println("couche test model : " + coucheModelTestPath);
			
			System.out.println("couche model/dto : " + coucheModelDTOMainPath);
			System.out.println("couche test model/dto : " + coucheModelDTOTestPath);
			System.out.println("couche model/dto/metier : " + coucheModelDTOMetierMainPath);
			System.out.println("couche test model/dto/metier : " + coucheModelDTOMetierTestPath);
			
			System.out.println("couche model/metier : " + coucheModelMetierMainPath);
			System.out.println("couche test model/metier : " + coucheModelMetierTestPath);

			System.out.println("couche model/persistence : " + coucheModelPersistenceMainPath);
			System.out.println("couche test model/persistence : " + coucheModelPersistenceTestPath);
			System.out.println("couche model/persistence/accueil : " + coucheModelPersistenceAccueilMainPath);
			System.out.println("couche test model/persistence/accueil : " + coucheModelPersistenceAccueilTestPath);
			System.out.println("couche model/persistence/daoexceptions : " + coucheModelPersistenceDaoexceptionsMainPath);
			System.out.println("couche test model/persistence/daoexceptions : " + coucheModelPersistenceDaoexceptionsTestPath);
			System.out.println("couche model/persistence/metier : " + coucheModelPersistenceMetierMainPath);
			System.out.println("couche test model/persistence/metier : " + coucheModelPersistenceMetierTestPath);

			System.out.println("couche model/services : " + coucheModelServicesMainPath);
			System.out.println("couche test model/services : " + coucheModelServicesTestPath);
			System.out.println("couche model/services/accueil : " + coucheModelServicesAccueilMainPath);
			System.out.println("couche test model/services/accueil : " + coucheModelServicesAccueilTestPath);
			System.out.println("couche model/services/metier : " + coucheModelServicesMetierMainPath);
			System.out.println("couche test model/services/metier : " + coucheModelServicesMetierTestPath);
			System.out.println("couche model/services/transformeurs : " + coucheModelServicesTransformeursMainPath);
			System.out.println("couche model/services/transformeurs/metier : " + coucheModelServicesTransformeursMetierMainPath);
			System.out.println("couche test model/services/transformeurs : " + coucheModelServicesTransformeursTestPath);
			System.out.println("couche test model/services/transformeurs/metier : " + coucheModelServicesTransformeursMetierTestPath);
			System.out.println("couche model/services/utilitaires : " + coucheModelServicesUtilitairesMainPath);
			System.out.println("couche test model/services/utilitaires : " + coucheModelServicesUtilitairesTestPath);
			System.out.println("couche model/services/valideurs : " + coucheModelServicesValideursMainPath);
			System.out.println("couche model/services/valideurs/metier : " + coucheModelServicesValideursMetierMainPath);
			System.out.println("couche test model/services/valideurs : " + coucheModelServicesValideursTestPath);
			System.out.println("couche test model/services/valideurs/metier : " + coucheModelServicesValideursMetierTestPath);

			System.out.println("couche model/utilitaires : " + coucheModelUtilitairesMainPath);
			System.out.println("couche test model/utilitaires : " + coucheModelUtilitairesTestPath);

			// REPERTOIRES EXTERNES
			System.out.println("repertoire externe conception_appli : " + conceptionAppliPath);
			System.out.println("repertoire externe data : " + dataPath);
			System.out.println("repertoire externe data/H2 : " + dataH2Path);
			System.out.println("repertoire externe data/HSQLDB : " + dataHSQLDBPath);
			System.out.println("repertoire externe data/JAXB : " + dataJAXBPath);
			System.out.println("repertoire externe data/scripts_sql : " + dataScriptsSqlPath);
			System.out.println("repertoire externe javadoc : " + javadocPath);
			System.out.println("repertoire externe javadoc/images : " + javadocImagesPath);
			System.out.println("repertoire externe logs : " + logsPath);
			System.out.println("repertoire externe rapports_controle : " + rapportsControlePath);
			System.out.println("repertoire externe ressources_externes : " + ressourcesExternesPath);
			
			System.out.println();
			System.out.println("LISTE ARBORESCENCE");
			System.out.println(ArboresceurPresentProjet.afficherArborescence());
			
			System.out.println();
			System.out.println("NOMBRE DE REPERTOIRES A CREER : " + ArboresceurPresentProjet.fournirNombreRepACreer());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE MAIN");
			System.out.println(ArboresceurPresentProjet.afficherArborescenceMainMap());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE TEST");
			System.out.println(ArboresceurPresentProjet.afficherArborescenceTestMap());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE REPERTOIRES EXTERNES");
			System.out.println(ArboresceurPresentProjet.afficherArborescenceRepExtMap());

		}
		
		assertNotNull(
				"chemin des sources java srcMainJavaPath ne doit pas être null : "
					, srcMainJavaPath);

		assertNotNull(
				"chemin des ressources srcMainResourcesPath ne doit pas être null : "
					, srcMainResourcesPath);

		assertNotNull(
				"chemin des tests JUnit srcTestJavaPath ne doit pas être null : "
					, srcTestJavaPath);

		assertNotNull(
				"chemin des ressources des tests JUnit srcTestResourcesPath ne doit pas être null : "
					, srcTestResourcesPath);
		
		assertNotNull(
				"chemin de la racine des sources java racineSourcesJavaPath ne doit pas être null : "
					, racineSourcesJavaPath);

		assertNotNull(
				"chemin de la racine des sources des tests JUnit racineSourcesJavaPath ne doit pas être null : "
					, racineTestsJavaPath);

		assertNotNull(
				"chemin de la couche apptechnic ne doit pas être null : "
					, coucheAppTechnicMainPath);
		
		assertNotNull(
				"chemin de la couche test apptechnic ne doit pas être null : "
					, coucheAppTechnicTestPath);

		assertNotNull(
				"chemin de la couche controllers ne doit pas être null : "
					, coucheControllersMainPath);
		
		assertNotNull(
				"chemin de la couche test controllers ne doit pas être null : "
					, coucheControllersTestPath);

	} // Fin de testCalculerPaths()._______________________________________


	
	/**
	 * teste l'ensemble de l'arboresceur.<br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCalculerPathsAvecChangementGroupId() {
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testCalculerPathsAvecChangementGroupId() ********** ");
		}
		
		
		/* Changement du groupId. */
		ArboresceurPresentProjet.setGroupId("fr.orsys.demo");

		final Path projetSourcePathStocke 
			= ArboresceurPresentProjet.getProjetSourcePath();
		final String projetSourceNom 
			= ArboresceurPresentProjet.getProjetSourceNom();
		
		final Path srcMainJavaPath 
			= ArboresceurPresentProjet.getSrcMainJavaPath();
		final Path srcMainResourcesPath 
			= ArboresceurPresentProjet.getSrcMainResourcesPath();
		final Path srcMainResourcesMetaInfPath 
			= ArboresceurPresentProjet.getSrcMainResourcesMetaInfPath();
		final Path srcTestJavaPath 
			= ArboresceurPresentProjet.getSrcTestJavaPath();
		final Path srcTestResourcesPath 
			= ArboresceurPresentProjet.getSrcTestResourcesPath();
		final Path srcTestResourcesMetaInfPath 
			= ArboresceurPresentProjet.getSrcTestResourcesMetaInfPath();
		
		// GROUPID
		final String groupId 
			= ArboresceurPresentProjet.getGroupId();
		final Path groupIdPathRelatif 
			= ArboresceurPresentProjet.getGroupIdPathRelatif();
		
		// RACINEs DES SOURCES ET TESTS
		final Path racineSourcesJavaPath 
			= ArboresceurPresentProjet.getRacineSourcesJavaPath();
		final Path racineTestsJavaPath 
			= ArboresceurPresentProjet.getRacineTestsJavaPath();
		
		// APPTECHNIC
		final Path coucheAppTechnicMainPath 
			= ArboresceurPresentProjet.getCoucheAppTechnicMainPath();
		final Path coucheAppTechnicTestPath 
			= ArboresceurPresentProjet.getCoucheAppTechnicTestPath();
		
		// CONTROLLERS
		final Path coucheControllersMainPath 
			= ArboresceurPresentProjet.getCoucheControllersMainPath();
		final Path coucheControllersTestPath 
			= ArboresceurPresentProjet.getCoucheControllersTestPath();
		
		final Path coucheControllersDesktopMainPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopMainPath();
		final Path coucheControllersDesktopTestPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopTestPath();
		final Path coucheControllersDesktopAccueilMainPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopAccueilMainPath();
		final Path coucheControllersDesktopAccueilTestPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopAccueilTestPath();
		final Path coucheControllersDesktopMetierMainPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopMetierMainPath();
		final Path coucheControllersDesktopMetierTestPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopMetierTestPath();
		final Path coucheControllersDesktopUtilitairesMainPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopUtilitairesMainPath();
		final Path coucheControllersDesktopUtilitairesTestPath 
			= ArboresceurPresentProjet.getCoucheControllersDesktopUtilitairesTestPath();
	
		final Path coucheControllersWebMainPath 
			= ArboresceurPresentProjet.getCoucheControllersWebMainPath();
		final Path coucheControllersWebTestPath 
			= ArboresceurPresentProjet.getCoucheControllersWebTestPath();
		final Path coucheControllersWebAccueilMainPath 
			= ArboresceurPresentProjet.getCoucheControllersWebAccueilMainPath();
		final Path coucheControllersWebAccueilTestPath 
			= ArboresceurPresentProjet.getCoucheControllersWebAccueilTestPath();
		final Path coucheControllersWebMetierMainPath 
			= ArboresceurPresentProjet.getCoucheControllersWebMetierMainPath();
		final Path coucheControllersWebMetierTestPath 
			= ArboresceurPresentProjet.getCoucheControllersWebMetierTestPath();
		final Path coucheControllersWebUtilitairesMainPath 
			= ArboresceurPresentProjet.getCoucheControllersWebUtilitairesMainPath();
		final Path coucheControllersWebUtilitairesTestPath 
			= ArboresceurPresentProjet.getCoucheControllersWebUtilitairesTestPath();
		
		// VUES
		final Path coucheVuesMainPath 
			= ArboresceurPresentProjet.getCoucheVuesMainPath();
		final Path coucheVuesTestPath 
			= ArboresceurPresentProjet.getCoucheVuesTestPath();
		
		final Path coucheVuesDesktopMainPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopMainPath();
		final Path coucheVuesDesktopTestPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopTestPath();
		final Path coucheVuesDesktopAccueilMainPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopAccueilMainPath();
		final Path coucheVuesDesktopAccueilTestPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopAccueilTestPath();
		final Path coucheVuesDesktopMetierMainPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopMetierMainPath();
		final Path coucheVuesDesktopMetierTestPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopMetierTestPath();
		final Path coucheVuesDesktopUtilitairesMainPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopUtilitairesMainPath();
		final Path coucheVuesDesktopUtilitairesTestPath 
			= ArboresceurPresentProjet.getCoucheVuesDesktopUtilitairesTestPath();

		final Path coucheVuesWebMainPath 
			= ArboresceurPresentProjet.getCoucheVuesWebMainPath();
		final Path coucheVuesWebTestPath 
			= ArboresceurPresentProjet.getCoucheVuesWebTestPath();
		final Path coucheVuesWebAccueilMainPath 
			= ArboresceurPresentProjet.getCoucheVuesWebAccueilMainPath();
		final Path coucheVuesWebAccueilTestPath 
			= ArboresceurPresentProjet.getCoucheVuesWebAccueilTestPath();
		final Path coucheVuesWebMetierMainPath 
			= ArboresceurPresentProjet.getCoucheVuesWebMetierMainPath();
		final Path coucheVuesWebMetierTestPath 
			= ArboresceurPresentProjet.getCoucheVuesWebMetierTestPath();
		final Path coucheVuesWebUtilitairesMainPath 
			= ArboresceurPresentProjet.getCoucheVuesWebUtilitairesMainPath();
		final Path coucheVuesWebUtilitairesTestPath 
			= ArboresceurPresentProjet.getCoucheVuesWebUtilitairesTestPath();

		
		// MODEL
		final Path coucheModelMainPath 
			= ArboresceurPresentProjet.getCoucheModelMainPath();
		final Path coucheModelTestPath 
			= ArboresceurPresentProjet.getCoucheModelTestPath();
		
		final Path coucheModelDTOMainPath 
			= ArboresceurPresentProjet.getCoucheModelDTOMainPath();
		final Path coucheModelDTOTestPath 
			= ArboresceurPresentProjet.getCoucheModelDTOTestPath();
		final Path coucheModelDTOMetierMainPath 
			= ArboresceurPresentProjet.getCoucheModelDTOMetierMainPath();
		final Path coucheModelDTOMetierTestPath 
			= ArboresceurPresentProjet.getCoucheModelDTOMetierTestPath();
		
		final Path coucheModelMetierMainPath 
			= ArboresceurPresentProjet.getCoucheModelMetierMainPath();
		final Path coucheModelMetierTestPath 
			= ArboresceurPresentProjet.getCoucheModelMetierTestPath();

		final Path coucheModelPersistenceMainPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceMainPath();
		final Path coucheModelPersistenceTestPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceTestPath();
		final Path coucheModelPersistenceAccueilMainPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceAccueilMainPath();
		final Path coucheModelPersistenceAccueilTestPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceAccueilTestPath();
		final Path coucheModelPersistenceDaoexceptionsMainPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceDaoexceptionsMainPath();
		final Path coucheModelPersistenceDaoexceptionsTestPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceDaoexceptionsTestPath();
		final Path coucheModelPersistenceMetierMainPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceMetierMainPath();
		final Path coucheModelPersistenceMetierTestPath 
			= ArboresceurPresentProjet.getCoucheModelPersistenceMetierTestPath();

		final Path coucheModelServicesMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesMainPath();
		final Path coucheModelServicesTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesTestPath();
		final Path coucheModelServicesAccueilMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesAccueilMainPath();
		final Path coucheModelServicesAccueilTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesAccueilTestPath();
		final Path coucheModelServicesMetierMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesMetierMainPath();
		final Path coucheModelServicesMetierTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesMetierTestPath();
		final Path coucheModelServicesTransformeursMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesTransformeursMainPath();
		final Path coucheModelServicesTransformeursMetierMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesTransformeursMetierMainPath();
		final Path coucheModelServicesTransformeursTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesTransformeursTestPath();
		final Path coucheModelServicesTransformeursMetierTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesTransformeursMetierTestPath();
		final Path coucheModelServicesUtilitairesMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesUtilitairesMainPath();
		final Path coucheModelServicesUtilitairesTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesUtilitairesTestPath();
		final Path coucheModelServicesValideursMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesValideursMainPath();
		final Path coucheModelServicesValideursMetierMainPath 
			= ArboresceurPresentProjet.getCoucheModelServicesValideursMetierMainPath();
		final Path coucheModelServicesValideursTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesValideursTestPath();
		final Path coucheModelServicesValideursMetierTestPath 
			= ArboresceurPresentProjet.getCoucheModelServicesValideursMetierTestPath();
		
		final Path coucheModelUtilitairesMainPath 
			= ArboresceurPresentProjet.getCoucheModelUtilitairesMainPath();
		final Path coucheModelUtilitairesTestPath 
			= ArboresceurPresentProjet.getCoucheModelUtilitairesTestPath();
		
		// REPERTOIRES EXTERNES
		final Path conceptionAppliPath 
			= ArboresceurPresentProjet.getConceptionAppliPath();
		final Path dataPath 
			= ArboresceurPresentProjet.getDataPath();
		final Path dataH2Path 
			= ArboresceurPresentProjet.getDataH2Path();
		final Path dataHSQLDBPath 
			= ArboresceurPresentProjet.getDataHSQLDBPath();
		final Path dataJAXBPath 
			= ArboresceurPresentProjet.getDataJAXBPath();
		final Path dataScriptsSqlPath 
			= ArboresceurPresentProjet.getDataScriptsSqlPath();
		final Path javadocPath 
			= ArboresceurPresentProjet.getJavadocPath();
		final Path javadocImagesPath 
			= ArboresceurPresentProjet.getJavadocImagesPath();
		final Path logsPath 
			= ArboresceurPresentProjet.getLogsPath();
		final Path rapportsControlePath 
			= ArboresceurPresentProjet.getRapportsControlePath();
		final Path ressourcesExternesPath 
			= ArboresceurPresentProjet.getRessourcesExternesPath();

		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println("projet cible : " + projetSourcePathStocke);
			System.out.println("Nom du projet cible : " + projetSourceNom);
			
			System.out.println("sources java srcMainJavaPath : " + srcMainJavaPath);
			System.out.println("ressources srcMainResourcesPath : " + srcMainResourcesPath);
			System.out.println("ressources srcMainResourcesPath/META-INF : " + srcMainResourcesMetaInfPath);
			System.out.println("tests JUnit srcTestJavaPath : " + srcTestJavaPath);
			System.out.println("ressources des tests JUnit srcTestResourcesPath : " + srcTestResourcesPath);
			System.out.println("ressources srcTestResourcesPath/META-INF : " + srcTestResourcesMetaInfPath);
			
			System.out.println("groupId : " + groupId);
			System.out.println("groupIdPathRelatif : " + groupIdPathRelatif);
			
			System.out.println("racine des sources java racineSourcesJavaPath : " + racineSourcesJavaPath);
			System.out.println("racine des sources des tests JUnit racineTestsJavaPath : " + racineTestsJavaPath);
			
			// APPTECHNIC
			System.out.println("couche apptechnic : " + coucheAppTechnicMainPath);
			System.out.println("couche test apptechnic : " + coucheAppTechnicTestPath);
			
			// CONTROLLERS
			System.out.println("couche controllers : " + coucheControllersMainPath);
			System.out.println("couche test controllers : " + coucheControllersTestPath);
			
			System.out.println("couche controllers/desktop : " + coucheControllersDesktopMainPath);
			System.out.println("couche test controllers/desktop : " + coucheControllersDesktopTestPath);
			System.out.println("couche controllers/desktop/accueil : " + coucheControllersDesktopAccueilMainPath);
			System.out.println("couche test controllers/desktop/accueil : " + coucheControllersDesktopAccueilTestPath);
			System.out.println("couche controllers/desktop/metier : " + coucheControllersDesktopMetierMainPath);
			System.out.println("couche test controllers/desktop/metier : " + coucheControllersDesktopMetierTestPath);
			System.out.println("couche controllers/desktop/utilitaires : " + coucheControllersDesktopUtilitairesMainPath);
			System.out.println("couche test controllers/desktop/utilitaires : " + coucheControllersDesktopUtilitairesTestPath);

			System.out.println("couche controllers/web : " + coucheControllersWebMainPath);
			System.out.println("couche test controllers/web : " + coucheControllersWebTestPath);
			System.out.println("couche controllers/web/accueil : " + coucheControllersWebAccueilMainPath);
			System.out.println("couche test controllers/web/accueil : " + coucheControllersWebAccueilTestPath);
			System.out.println("couche controllers/web/metier : " + coucheControllersWebMetierMainPath);
			System.out.println("couche test controllers/web/metier : " + coucheControllersWebMetierTestPath);
			System.out.println("couche controllers/web/utilitaires : " + coucheControllersWebUtilitairesMainPath);
			System.out.println("couche test controllers/web/utilitaires : " + coucheControllersWebUtilitairesTestPath);
			
			// VUES
			System.out.println("couche vues : " + coucheVuesMainPath);
			System.out.println("couche test vues : " + coucheVuesTestPath);
			
			System.out.println("couche vues/desktop : " + coucheVuesDesktopMainPath);
			System.out.println("couche test vues/desktop : " + coucheVuesDesktopTestPath);
			System.out.println("couche vues/desktop/accueil : " + coucheVuesDesktopAccueilMainPath);
			System.out.println("couche test vues/desktop/accueil : " + coucheVuesDesktopAccueilTestPath);
			System.out.println("couche vues/desktop/metier : " + coucheVuesDesktopMetierMainPath);
			System.out.println("couche test vues/desktop/metier : " + coucheVuesDesktopMetierTestPath);
			System.out.println("couche vues/desktop/utilitaires : " + coucheVuesDesktopUtilitairesMainPath);
			System.out.println("couche test vues/desktop/utilitaires : " + coucheVuesDesktopUtilitairesTestPath);

			System.out.println("couche vues/web : " + coucheVuesWebMainPath);
			System.out.println("couche test vues/web : " + coucheVuesWebTestPath);
			System.out.println("couche vues/web/accueil : " + coucheVuesWebAccueilMainPath);
			System.out.println("couche test vues/web/accueil : " + coucheVuesWebAccueilTestPath);
			System.out.println("couche vues/web/metier : " + coucheVuesWebMetierMainPath);
			System.out.println("couche test vues/web/metier : " + coucheVuesWebMetierTestPath);
			System.out.println("couche vues/web/utilitaires : " + coucheVuesWebUtilitairesMainPath);
			System.out.println("couche test vues/web/utilitaires : " + coucheVuesWebUtilitairesTestPath);

			// MODEL
			System.out.println("couche model : " + coucheModelMainPath);
			System.out.println("couche test model : " + coucheModelTestPath);
			
			System.out.println("couche model/dto : " + coucheModelDTOMainPath);
			System.out.println("couche test model/dto : " + coucheModelDTOTestPath);
			System.out.println("couche model/dto/metier : " + coucheModelDTOMetierMainPath);
			System.out.println("couche test model/dto/metier : " + coucheModelDTOMetierTestPath);
			
			System.out.println("couche model/metier : " + coucheModelMetierMainPath);
			System.out.println("couche test model/metier : " + coucheModelMetierTestPath);

			System.out.println("couche model/persistence : " + coucheModelPersistenceMainPath);
			System.out.println("couche test model/persistence : " + coucheModelPersistenceTestPath);
			System.out.println("couche model/persistence/accueil : " + coucheModelPersistenceAccueilMainPath);
			System.out.println("couche test model/persistence/accueil : " + coucheModelPersistenceAccueilTestPath);
			System.out.println("couche model/persistence/daoexceptions : " + coucheModelPersistenceDaoexceptionsMainPath);
			System.out.println("couche test model/persistence/daoexceptions : " + coucheModelPersistenceDaoexceptionsTestPath);
			System.out.println("couche model/persistence/metier : " + coucheModelPersistenceMetierMainPath);
			System.out.println("couche test model/persistence/metier : " + coucheModelPersistenceMetierTestPath);

			System.out.println("couche model/services : " + coucheModelServicesMainPath);
			System.out.println("couche test model/services : " + coucheModelServicesTestPath);
			System.out.println("couche model/services/accueil : " + coucheModelServicesAccueilMainPath);
			System.out.println("couche test model/services/accueil : " + coucheModelServicesAccueilTestPath);
			System.out.println("couche model/services/metier : " + coucheModelServicesMetierMainPath);
			System.out.println("couche test model/services/metier : " + coucheModelServicesMetierTestPath);
			System.out.println("couche model/services/transformeurs : " + coucheModelServicesTransformeursMainPath);
			System.out.println("couche model/services/transformeurs/metier : " + coucheModelServicesTransformeursMetierMainPath);
			System.out.println("couche test model/services/transformeurs : " + coucheModelServicesTransformeursTestPath);
			System.out.println("couche test model/services/transformeurs/metier : " + coucheModelServicesTransformeursMetierTestPath);
			System.out.println("couche model/services/utilitaires : " + coucheModelServicesUtilitairesMainPath);
			System.out.println("couche test model/services/utilitaires : " + coucheModelServicesUtilitairesTestPath);
			System.out.println("couche model/services/valideurs : " + coucheModelServicesValideursMainPath);
			System.out.println("couche model/services/valideurs/metier : " + coucheModelServicesValideursMetierMainPath);
			System.out.println("couche test model/services/valideurs : " + coucheModelServicesValideursTestPath);
			System.out.println("couche test model/services/valideurs/metier : " + coucheModelServicesValideursMetierTestPath);

			System.out.println("couche model/utilitaires : " + coucheModelUtilitairesMainPath);
			System.out.println("couche test model/utilitaires : " + coucheModelUtilitairesTestPath);

			// REPERTOIRES EXTERNES
			System.out.println("repertoire externe conception_appli : " + conceptionAppliPath);
			System.out.println("repertoire externe data : " + dataPath);
			System.out.println("repertoire externe data/H2 : " + dataH2Path);
			System.out.println("repertoire externe data/HSQLDB : " + dataHSQLDBPath);
			System.out.println("repertoire externe data/JAXB : " + dataJAXBPath);
			System.out.println("repertoire externe data/scripts_sql : " + dataScriptsSqlPath);
			System.out.println("repertoire externe javadoc : " + javadocPath);
			System.out.println("repertoire externe javadoc/images : " + javadocImagesPath);
			System.out.println("repertoire externe logs : " + logsPath);
			System.out.println("repertoire externe rapports_controle : " + rapportsControlePath);
			System.out.println("repertoire externe ressources_externes : " + ressourcesExternesPath);
			
			System.out.println();
			System.out.println("LISTE ARBORESCENCE");
			System.out.println(ArboresceurPresentProjet.afficherArborescence());
			
			System.out.println();
			System.out.println("NOMBRE DE REPERTOIRES A CREER : " + ArboresceurPresentProjet.fournirNombreRepACreer());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE MAIN");
			System.out.println(ArboresceurPresentProjet.afficherArborescenceMainMap());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE TEST");
			System.out.println(ArboresceurPresentProjet.afficherArborescenceTestMap());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE REPERTOIRES EXTERNES");
			System.out.println(ArboresceurPresentProjet.afficherArborescenceRepExtMap());

		}
		
		assertNotNull(
				"chemin des sources java srcMainJavaPath ne doit pas être null : "
					, srcMainJavaPath);

		assertNotNull(
				"chemin des ressources srcMainResourcesPath ne doit pas être null : "
					, srcMainResourcesPath);

		assertNotNull(
				"chemin des tests JUnit srcTestJavaPath ne doit pas être null : "
					, srcTestJavaPath);

		assertNotNull(
				"chemin des ressources des tests JUnit srcTestResourcesPath ne doit pas être null : "
					, srcTestResourcesPath);
		
		assertNotNull(
				"chemin de la racine des sources java racineSourcesJavaPath ne doit pas être null : "
					, racineSourcesJavaPath);

		assertNotNull(
				"chemin de la racine des sources des tests JUnit racineSourcesJavaPath ne doit pas être null : "
					, racineTestsJavaPath);

		assertNotNull(
				"chemin de la couche apptechnic ne doit pas être null : "
					, coucheAppTechnicMainPath);
		
		assertNotNull(
				"chemin de la couche test apptechnic ne doit pas être null : "
					, coucheAppTechnicTestPath);

		assertNotNull(
				"chemin de la couche controllers ne doit pas être null : "
					, coucheControllersMainPath);
		
		assertNotNull(
				"chemin de la couche test controllers ne doit pas être null : "
					, coucheControllersTestPath);

	} // Fin de testCalculerPathsAvecChangementGroupId().__________________
	
	
	
	/**
	 * teste les méthodes setGroupId(...) et s
	 * etGroupIdPathRelatif(...).<br/>
	 * @throws IOException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testSetGroupId() throws IOException {
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurPresentProjetTest - méthode testSetGroupId() ********** ");
		}
		
		final String groupId = "fr.orsys.demo";
		
		// UTILISATION DE SETTER DU GROUPID
		ArboresceurPresentProjet.setGroupId(groupId);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(GROUPID_DANS_PRESENT_PROJET + ArboresceurPresentProjet.getGroupId());
			System.out.println(GROUPIDPATHRELATIF_DANS_PRESENT_PROJET + ArboresceurPresentProjet.getGroupIdPathRelatif());
		}
		
		assertEquals("le groupId vaut groupId : "
				, groupId
				, ArboresceurPresentProjet.getGroupId());
		
		assertEquals(GROUPIDPATHRELATIF_DANS_PRESENT_PROJET
				, Paths.get("fr/orsys/demo")
				, ArboresceurPresentProjet.getGroupIdPathRelatif());
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Racine des sources java : " + ArboresceurPresentProjet.getRacineSourcesJavaPath());
			System.out.println("Racine des tests java : " + ArboresceurPresentProjet.getRacineTestsJavaPath());
		}
		
		final Path pathSourcesJava 
			= ManagerPaths.getPathPresentProjet().resolve("src/main/java/fr/orsys/demo");
		
		final Path pathTestsJava 
			= ManagerPaths.getPathPresentProjet().resolve("src/test/java/fr/orsys/demo");
		
		assertEquals("racine des sources java src/main/java/fr/orsys/demo : "
				, pathSourcesJava
				, ArboresceurPresentProjet.getRacineSourcesJavaPath());
		
		assertEquals("racine des tests java src/test/java/fr/orsys/demo : "
				, pathTestsJava
				, ArboresceurPresentProjet.getRacineTestsJavaPath());
		
		final Path pathRelatif = Paths.get("levy/daniel/application");
		
		// UTILISATION DE SETTER DU GROUPIDPATHRELATIF
		ArboresceurPresentProjet.setGroupIdPathRelatif(pathRelatif);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(GROUPID_DANS_PRESENT_PROJET + ArboresceurPresentProjet.getGroupId());
			System.out.println(GROUPIDPATHRELATIF_DANS_PRESENT_PROJET + ArboresceurPresentProjet.getGroupIdPathRelatif());
		}
		
		assertEquals("le groupId vaut levy.daniel.application : "
				, "levy.daniel.application"
				, ArboresceurPresentProjet.getGroupId());
		
		assertEquals(GROUPIDPATHRELATIF_DANS_PRESENT_PROJET
				, pathRelatif
				, ArboresceurPresentProjet.getGroupIdPathRelatif());

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Racine des sources java2 : " + ArboresceurPresentProjet.getRacineSourcesJavaPath());
			System.out.println("Racine des tests java2 : " + ArboresceurPresentProjet.getRacineTestsJavaPath());
		}
				
		final Path pathSourcesJava2 
		= ManagerPaths.getPathPresentProjet().resolve("src/main/java/levy/daniel/application");
	
		final Path pathTestsJava2 
			= ManagerPaths.getPathPresentProjet().resolve("src/test/java/levy/daniel/application");
		
		assertEquals("racine des sources java src/main/java/levy/daniel/application : "
				, pathSourcesJava2
				, ArboresceurPresentProjet.getRacineSourcesJavaPath());
		
		assertEquals("racine des tests java src/test/java/levy/daniel/application : "
				, pathTestsJava2
				, ArboresceurPresentProjet.getRacineTestsJavaPath());


	} // Fin de testSetGroupId().__________________________________________
	
	

} // FIN DE LA CLASSE ArboresceurPresentProjetTest.--------------------------
