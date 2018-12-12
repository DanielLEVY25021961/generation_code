package levy.daniel.application.model.services.utilitaires.arboresceurprojet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * CLASSE ArboresceurProjetSourceTest :<br/>
 * Test JUnit de la classe ArboresceurProjetSource.<br/>
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
public class ArboresceurProjetSourceTest {

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
	 * "groupId dans le projet cible : ".<br/>
	 */
	public static final String GROUPID_DANS_PROJET_CIBLE 
		= "groupId dans le projet cible : ";
	
	/**
	 * "groupIdPathRelatif dans le projet cible : ".<br/>
	 */
	public static final String GROUPIDPATHRELATIF_DANS_PROJET_CIBLE 
		= "groupIdPathRelatif dans le projet cible : ";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(ArboresceurProjetSourceTest.class);
	
	// *************************METHODES************************************/	

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ArboresceurProjetSourceTest() {
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
			System.out.println("********** CLASSE ArboresceurProjetSourceTest - méthode testCalculerPaths() ********** ");
		}
		
		// ************************************************* //
//		final Path projetSourcePath 
//			= Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/copieur_arborescence_maven");
		final Path projetSourcePath = Paths.get(".").toAbsolutePath().normalize();
		// ************************************************* //
		
		/* Détermination du projet cible. */
		ArboresceurProjetSource.selectionnerProjetSource(projetSourcePath);
		
		final Path projetSourcePathStocke 
			= ArboresceurProjetSource.getProjetSourcePath();
		final String projetSourceNom 
			= ArboresceurProjetSource.getProjetSourceNom();
		
		final Path srcMainJavaPath 
			= ArboresceurProjetSource.getSrcMainJavaPath();
		final Path srcMainResourcesPath 
			= ArboresceurProjetSource.getSrcMainResourcesPath();
		final Path srcMainResourcesMetaInfPath 
			= ArboresceurProjetSource.getSrcMainResourcesMetaInfPath();
		final Path srcTestJavaPath 
			= ArboresceurProjetSource.getSrcTestJavaPath();
		final Path srcTestResourcesPath 
			= ArboresceurProjetSource.getSrcTestResourcesPath();
		final Path srcTestResourcesMetaInfPath 
			= ArboresceurProjetSource.getSrcTestResourcesMetaInfPath();
		
		// GROUPID
		final String groupId 
			= ArboresceurProjetSource.getGroupId();
		final Path groupIdPathRelatif 
			= ArboresceurProjetSource.getGroupIdPathRelatif();
		
		// RACINEs DES SOURCES ET TESTS
		final Path racineSourcesJavaPath 
			= ArboresceurProjetSource.getRacineSourcesJavaPath();
		final Path racineTestsJavaPath 
			= ArboresceurProjetSource.getRacineTestsJavaPath();
		
		// APPTECHNIC
		final Path coucheAppTechnicMainPath 
			= ArboresceurProjetSource.getCoucheAppTechnicMainPath();
		final Path coucheAppTechnicTestPath 
			= ArboresceurProjetSource.getCoucheAppTechnicTestPath();
		
		// CONTROLLERS
		final Path coucheControllersMainPath 
			= ArboresceurProjetSource.getCoucheControllersMainPath();
		final Path coucheControllersTestPath 
			= ArboresceurProjetSource.getCoucheControllersTestPath();
		
		final Path coucheControllersDesktopMainPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopMainPath();
		final Path coucheControllersDesktopTestPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopTestPath();
		final Path coucheControllersDesktopAccueilMainPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopAccueilMainPath();
		final Path coucheControllersDesktopAccueilTestPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopAccueilTestPath();
		final Path coucheControllersDesktopMetierMainPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopMetierMainPath();
		final Path coucheControllersDesktopMetierTestPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopMetierTestPath();
		final Path coucheControllersDesktopUtilitairesMainPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopUtilitairesMainPath();
		final Path coucheControllersDesktopUtilitairesTestPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopUtilitairesTestPath();
	
		final Path coucheControllersWebMainPath 
			= ArboresceurProjetSource.getCoucheControllersWebMainPath();
		final Path coucheControllersWebTestPath 
			= ArboresceurProjetSource.getCoucheControllersWebTestPath();
		final Path coucheControllersWebAccueilMainPath 
			= ArboresceurProjetSource.getCoucheControllersWebAccueilMainPath();
		final Path coucheControllersWebAccueilTestPath 
			= ArboresceurProjetSource.getCoucheControllersWebAccueilTestPath();
		final Path coucheControllersWebMetierMainPath 
			= ArboresceurProjetSource.getCoucheControllersWebMetierMainPath();
		final Path coucheControllersWebMetierTestPath 
			= ArboresceurProjetSource.getCoucheControllersWebMetierTestPath();
		final Path coucheControllersWebUtilitairesMainPath 
			= ArboresceurProjetSource.getCoucheControllersWebUtilitairesMainPath();
		final Path coucheControllersWebUtilitairesTestPath 
			= ArboresceurProjetSource.getCoucheControllersWebUtilitairesTestPath();
		
		// VUES
		final Path coucheVuesMainPath 
			= ArboresceurProjetSource.getCoucheVuesMainPath();
		final Path coucheVuesTestPath 
			= ArboresceurProjetSource.getCoucheVuesTestPath();
		
		final Path coucheVuesDesktopMainPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopMainPath();
		final Path coucheVuesDesktopTestPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopTestPath();
		final Path coucheVuesDesktopAccueilMainPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopAccueilMainPath();
		final Path coucheVuesDesktopAccueilTestPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopAccueilTestPath();
		final Path coucheVuesDesktopMetierMainPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopMetierMainPath();
		final Path coucheVuesDesktopMetierTestPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopMetierTestPath();
		final Path coucheVuesDesktopUtilitairesMainPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopUtilitairesMainPath();
		final Path coucheVuesDesktopUtilitairesTestPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopUtilitairesTestPath();

		final Path coucheVuesWebMainPath 
			= ArboresceurProjetSource.getCoucheVuesWebMainPath();
		final Path coucheVuesWebTestPath 
			= ArboresceurProjetSource.getCoucheVuesWebTestPath();
		final Path coucheVuesWebAccueilMainPath 
			= ArboresceurProjetSource.getCoucheVuesWebAccueilMainPath();
		final Path coucheVuesWebAccueilTestPath 
			= ArboresceurProjetSource.getCoucheVuesWebAccueilTestPath();
		final Path coucheVuesWebMetierMainPath 
			= ArboresceurProjetSource.getCoucheVuesWebMetierMainPath();
		final Path coucheVuesWebMetierTestPath 
			= ArboresceurProjetSource.getCoucheVuesWebMetierTestPath();
		final Path coucheVuesWebUtilitairesMainPath 
			= ArboresceurProjetSource.getCoucheVuesWebUtilitairesMainPath();
		final Path coucheVuesWebUtilitairesTestPath 
			= ArboresceurProjetSource.getCoucheVuesWebUtilitairesTestPath();

		
		// MODEL
		final Path coucheModelMainPath 
			= ArboresceurProjetSource.getCoucheModelMainPath();
		final Path coucheModelTestPath 
			= ArboresceurProjetSource.getCoucheModelTestPath();
		
		final Path coucheModelDTOMainPath 
			= ArboresceurProjetSource.getCoucheModelDTOMainPath();
		final Path coucheModelDTOTestPath 
			= ArboresceurProjetSource.getCoucheModelDTOTestPath();
		final Path coucheModelDTOMetierMainPath 
			= ArboresceurProjetSource.getCoucheModelDTOMetierMainPath();
		final Path coucheModelDTOMetierTestPath 
			= ArboresceurProjetSource.getCoucheModelDTOMetierTestPath();
		
		final Path coucheModelMetierMainPath 
			= ArboresceurProjetSource.getCoucheModelMetierMainPath();
		final Path coucheModelMetierTestPath 
			= ArboresceurProjetSource.getCoucheModelMetierTestPath();

		final Path coucheModelPersistenceMainPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceMainPath();
		final Path coucheModelPersistenceTestPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceTestPath();
		final Path coucheModelPersistenceAccueilMainPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceAccueilMainPath();
		final Path coucheModelPersistenceAccueilTestPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceAccueilTestPath();
		final Path coucheModelPersistenceDaoexceptionsMainPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceDaoexceptionsMainPath();
		final Path coucheModelPersistenceDaoexceptionsTestPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceDaoexceptionsTestPath();
		final Path coucheModelPersistenceMetierMainPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceMetierMainPath();
		final Path coucheModelPersistenceMetierTestPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceMetierTestPath();

		final Path coucheModelServicesMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesMainPath();
		final Path coucheModelServicesTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesTestPath();
		final Path coucheModelServicesAccueilMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesAccueilMainPath();
		final Path coucheModelServicesAccueilTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesAccueilTestPath();
		final Path coucheModelServicesMetierMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesMetierMainPath();
		final Path coucheModelServicesMetierTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesMetierTestPath();
		final Path coucheModelServicesTransformeursMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesTransformeursMainPath();
		final Path coucheModelServicesTransformeursMetierMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesTransformeursMetierMainPath();
		final Path coucheModelServicesTransformeursTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesTransformeursTestPath();
		final Path coucheModelServicesTransformeursMetierTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesTransformeursMetierTestPath();
		final Path coucheModelServicesUtilitairesMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesUtilitairesMainPath();
		final Path coucheModelServicesUtilitairesTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesUtilitairesTestPath();
		final Path coucheModelServicesValideursMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesValideursMainPath();
		final Path coucheModelServicesValideursMetierMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesValideursMetierMainPath();
		final Path coucheModelServicesValideursTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesValideursTestPath();
		final Path coucheModelServicesValideursMetierTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesValideursMetierTestPath();
		
		final Path coucheModelUtilitairesMainPath 
			= ArboresceurProjetSource.getCoucheModelUtilitairesMainPath();
		final Path coucheModelUtilitairesTestPath 
			= ArboresceurProjetSource.getCoucheModelUtilitairesTestPath();
		
		// REPERTOIRES EXTERNES
		final Path conceptionAppliPath 
			= ArboresceurProjetSource.getConceptionAppliPath();
		final Path dataPath 
			= ArboresceurProjetSource.getDataPath();
		final Path dataH2Path 
			= ArboresceurProjetSource.getDataH2Path();
		final Path dataHSQLDBPath 
			= ArboresceurProjetSource.getDataHSQLDBPath();
		final Path dataJAXBPath 
			= ArboresceurProjetSource.getDataJAXBPath();
		final Path dataScriptsSqlPath 
			= ArboresceurProjetSource.getDataScriptsSqlPath();
		final Path javadocPath 
			= ArboresceurProjetSource.getJavadocPath();
		final Path javadocImagesPath 
			= ArboresceurProjetSource.getJavadocImagesPath();
		final Path logsPath 
			= ArboresceurProjetSource.getLogsPath();
		final Path rapportsControlePath 
			= ArboresceurProjetSource.getRapportsControlePath();
		final Path ressourcesExternesPath 
			= ArboresceurProjetSource.getRessourcesExternesPath();

		
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
			System.out.println(ArboresceurProjetSource.afficherArborescence());
			
			System.out.println();
			System.out.println("NOMBRE DE REPERTOIRES A CREER : " + ArboresceurProjetSource.fournirNombreRepACreer());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE MAIN");
			System.out.println(ArboresceurProjetSource.afficherArborescenceMainMap());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE TEST");
			System.out.println(ArboresceurProjetSource.afficherArborescenceTestMap());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE REPERTOIRES EXTERNES");
			System.out.println(ArboresceurProjetSource.afficherArborescenceRepExtMap());

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
			System.out.println("********** CLASSE ArboresceurProjetSourceTest - méthode testCalculerPathsAvecChangementGroupId() ********** ");
		}
		
		// ************************************************* //
//		final Path projetSourcePath 
//			= Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/copieur_arborescence_maven");
		final Path projetSourcePath = Paths.get(".").toAbsolutePath().normalize();
		// ************************************************* //
		
		/* Changement du groupId. */
		ArboresceurProjetSource.setGroupId("fr.orsys.demo");

		/* Détermination du projet cible. */
		ArboresceurProjetSource.selectionnerProjetSource(projetSourcePath);
		
		final Path projetSourcePathStocke 
			= ArboresceurProjetSource.getProjetSourcePath();
		final String projetSourceNom 
			= ArboresceurProjetSource.getProjetSourceNom();
		
		final Path srcMainJavaPath 
			= ArboresceurProjetSource.getSrcMainJavaPath();
		final Path srcMainResourcesPath 
			= ArboresceurProjetSource.getSrcMainResourcesPath();
		final Path srcMainResourcesMetaInfPath 
			= ArboresceurProjetSource.getSrcMainResourcesMetaInfPath();
		final Path srcTestJavaPath 
			= ArboresceurProjetSource.getSrcTestJavaPath();
		final Path srcTestResourcesPath 
			= ArboresceurProjetSource.getSrcTestResourcesPath();
		final Path srcTestResourcesMetaInfPath 
			= ArboresceurProjetSource.getSrcTestResourcesMetaInfPath();
		
		// GROUPID
		final String groupId 
			= ArboresceurProjetSource.getGroupId();
		final Path groupIdPathRelatif 
			= ArboresceurProjetSource.getGroupIdPathRelatif();
		
		// RACINEs DES SOURCES ET TESTS
		final Path racineSourcesJavaPath 
			= ArboresceurProjetSource.getRacineSourcesJavaPath();
		final Path racineTestsJavaPath 
			= ArboresceurProjetSource.getRacineTestsJavaPath();
		
		// APPTECHNIC
		final Path coucheAppTechnicMainPath 
			= ArboresceurProjetSource.getCoucheAppTechnicMainPath();
		final Path coucheAppTechnicTestPath 
			= ArboresceurProjetSource.getCoucheAppTechnicTestPath();
		
		// CONTROLLERS
		final Path coucheControllersMainPath 
			= ArboresceurProjetSource.getCoucheControllersMainPath();
		final Path coucheControllersTestPath 
			= ArboresceurProjetSource.getCoucheControllersTestPath();
		
		final Path coucheControllersDesktopMainPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopMainPath();
		final Path coucheControllersDesktopTestPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopTestPath();
		final Path coucheControllersDesktopAccueilMainPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopAccueilMainPath();
		final Path coucheControllersDesktopAccueilTestPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopAccueilTestPath();
		final Path coucheControllersDesktopMetierMainPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopMetierMainPath();
		final Path coucheControllersDesktopMetierTestPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopMetierTestPath();
		final Path coucheControllersDesktopUtilitairesMainPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopUtilitairesMainPath();
		final Path coucheControllersDesktopUtilitairesTestPath 
			= ArboresceurProjetSource.getCoucheControllersDesktopUtilitairesTestPath();
	
		final Path coucheControllersWebMainPath 
			= ArboresceurProjetSource.getCoucheControllersWebMainPath();
		final Path coucheControllersWebTestPath 
			= ArboresceurProjetSource.getCoucheControllersWebTestPath();
		final Path coucheControllersWebAccueilMainPath 
			= ArboresceurProjetSource.getCoucheControllersWebAccueilMainPath();
		final Path coucheControllersWebAccueilTestPath 
			= ArboresceurProjetSource.getCoucheControllersWebAccueilTestPath();
		final Path coucheControllersWebMetierMainPath 
			= ArboresceurProjetSource.getCoucheControllersWebMetierMainPath();
		final Path coucheControllersWebMetierTestPath 
			= ArboresceurProjetSource.getCoucheControllersWebMetierTestPath();
		final Path coucheControllersWebUtilitairesMainPath 
			= ArboresceurProjetSource.getCoucheControllersWebUtilitairesMainPath();
		final Path coucheControllersWebUtilitairesTestPath 
			= ArboresceurProjetSource.getCoucheControllersWebUtilitairesTestPath();
		
		// VUES
		final Path coucheVuesMainPath 
			= ArboresceurProjetSource.getCoucheVuesMainPath();
		final Path coucheVuesTestPath 
			= ArboresceurProjetSource.getCoucheVuesTestPath();
		
		final Path coucheVuesDesktopMainPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopMainPath();
		final Path coucheVuesDesktopTestPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopTestPath();
		final Path coucheVuesDesktopAccueilMainPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopAccueilMainPath();
		final Path coucheVuesDesktopAccueilTestPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopAccueilTestPath();
		final Path coucheVuesDesktopMetierMainPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopMetierMainPath();
		final Path coucheVuesDesktopMetierTestPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopMetierTestPath();
		final Path coucheVuesDesktopUtilitairesMainPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopUtilitairesMainPath();
		final Path coucheVuesDesktopUtilitairesTestPath 
			= ArboresceurProjetSource.getCoucheVuesDesktopUtilitairesTestPath();

		final Path coucheVuesWebMainPath 
			= ArboresceurProjetSource.getCoucheVuesWebMainPath();
		final Path coucheVuesWebTestPath 
			= ArboresceurProjetSource.getCoucheVuesWebTestPath();
		final Path coucheVuesWebAccueilMainPath 
			= ArboresceurProjetSource.getCoucheVuesWebAccueilMainPath();
		final Path coucheVuesWebAccueilTestPath 
			= ArboresceurProjetSource.getCoucheVuesWebAccueilTestPath();
		final Path coucheVuesWebMetierMainPath 
			= ArboresceurProjetSource.getCoucheVuesWebMetierMainPath();
		final Path coucheVuesWebMetierTestPath 
			= ArboresceurProjetSource.getCoucheVuesWebMetierTestPath();
		final Path coucheVuesWebUtilitairesMainPath 
			= ArboresceurProjetSource.getCoucheVuesWebUtilitairesMainPath();
		final Path coucheVuesWebUtilitairesTestPath 
			= ArboresceurProjetSource.getCoucheVuesWebUtilitairesTestPath();

		
		// MODEL
		final Path coucheModelMainPath 
			= ArboresceurProjetSource.getCoucheModelMainPath();
		final Path coucheModelTestPath 
			= ArboresceurProjetSource.getCoucheModelTestPath();
		
		final Path coucheModelDTOMainPath 
			= ArboresceurProjetSource.getCoucheModelDTOMainPath();
		final Path coucheModelDTOTestPath 
			= ArboresceurProjetSource.getCoucheModelDTOTestPath();
		final Path coucheModelDTOMetierMainPath 
			= ArboresceurProjetSource.getCoucheModelDTOMetierMainPath();
		final Path coucheModelDTOMetierTestPath 
			= ArboresceurProjetSource.getCoucheModelDTOMetierTestPath();
		
		final Path coucheModelMetierMainPath 
			= ArboresceurProjetSource.getCoucheModelMetierMainPath();
		final Path coucheModelMetierTestPath 
			= ArboresceurProjetSource.getCoucheModelMetierTestPath();

		final Path coucheModelPersistenceMainPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceMainPath();
		final Path coucheModelPersistenceTestPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceTestPath();
		final Path coucheModelPersistenceAccueilMainPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceAccueilMainPath();
		final Path coucheModelPersistenceAccueilTestPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceAccueilTestPath();
		final Path coucheModelPersistenceDaoexceptionsMainPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceDaoexceptionsMainPath();
		final Path coucheModelPersistenceDaoexceptionsTestPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceDaoexceptionsTestPath();
		final Path coucheModelPersistenceMetierMainPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceMetierMainPath();
		final Path coucheModelPersistenceMetierTestPath 
			= ArboresceurProjetSource.getCoucheModelPersistenceMetierTestPath();

		final Path coucheModelServicesMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesMainPath();
		final Path coucheModelServicesTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesTestPath();
		final Path coucheModelServicesAccueilMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesAccueilMainPath();
		final Path coucheModelServicesAccueilTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesAccueilTestPath();
		final Path coucheModelServicesMetierMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesMetierMainPath();
		final Path coucheModelServicesMetierTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesMetierTestPath();
		final Path coucheModelServicesTransformeursMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesTransformeursMainPath();
		final Path coucheModelServicesTransformeursMetierMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesTransformeursMetierMainPath();
		final Path coucheModelServicesTransformeursTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesTransformeursTestPath();
		final Path coucheModelServicesTransformeursMetierTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesTransformeursMetierTestPath();
		final Path coucheModelServicesUtilitairesMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesUtilitairesMainPath();
		final Path coucheModelServicesUtilitairesTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesUtilitairesTestPath();
		final Path coucheModelServicesValideursMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesValideursMainPath();
		final Path coucheModelServicesValideursMetierMainPath 
			= ArboresceurProjetSource.getCoucheModelServicesValideursMetierMainPath();
		final Path coucheModelServicesValideursTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesValideursTestPath();
		final Path coucheModelServicesValideursMetierTestPath 
			= ArboresceurProjetSource.getCoucheModelServicesValideursMetierTestPath();
		
		final Path coucheModelUtilitairesMainPath 
			= ArboresceurProjetSource.getCoucheModelUtilitairesMainPath();
		final Path coucheModelUtilitairesTestPath 
			= ArboresceurProjetSource.getCoucheModelUtilitairesTestPath();
		
		// REPERTOIRES EXTERNES
		final Path conceptionAppliPath 
			= ArboresceurProjetSource.getConceptionAppliPath();
		final Path dataPath 
			= ArboresceurProjetSource.getDataPath();
		final Path dataH2Path 
			= ArboresceurProjetSource.getDataH2Path();
		final Path dataHSQLDBPath 
			= ArboresceurProjetSource.getDataHSQLDBPath();
		final Path dataJAXBPath 
			= ArboresceurProjetSource.getDataJAXBPath();
		final Path dataScriptsSqlPath 
			= ArboresceurProjetSource.getDataScriptsSqlPath();
		final Path javadocPath 
			= ArboresceurProjetSource.getJavadocPath();
		final Path javadocImagesPath 
			= ArboresceurProjetSource.getJavadocImagesPath();
		final Path logsPath 
			= ArboresceurProjetSource.getLogsPath();
		final Path rapportsControlePath 
			= ArboresceurProjetSource.getRapportsControlePath();
		final Path ressourcesExternesPath 
			= ArboresceurProjetSource.getRessourcesExternesPath();

		
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
			System.out.println(ArboresceurProjetSource.afficherArborescence());
			
			System.out.println();
			System.out.println("NOMBRE DE REPERTOIRES A CREER : " + ArboresceurProjetSource.fournirNombreRepACreer());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE MAIN");
			System.out.println(ArboresceurProjetSource.afficherArborescenceMainMap());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE TEST");
			System.out.println(ArboresceurProjetSource.afficherArborescenceTestMap());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE REPERTOIRES EXTERNES");
			System.out.println(ArboresceurProjetSource.afficherArborescenceRepExtMap());

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
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testSetGroupId() {
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ArboresceurProjetSourceTest - méthode testSetGroupId() ********** ");
		}
		
		// ************************************************* //
		final Path projetSourcePath 
			= Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/copieur_arborescence_maven");
//		final Path projetSourcePath = null;
		// ************************************************* //
		
		final String groupId = "fr.orsys.demo";
		
		// UTILISATION DE SETTER DU GROUPID
		ArboresceurProjetSource.setGroupId(groupId);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(GROUPID_DANS_PROJET_CIBLE + ArboresceurProjetSource.getGroupId());
			System.out.println(GROUPIDPATHRELATIF_DANS_PROJET_CIBLE + ArboresceurProjetSource.getGroupIdPathRelatif());
		}
		
		assertEquals("le groupId vaut groupId : "
				, groupId
				, ArboresceurProjetSource.getGroupId());
		
		assertEquals(GROUPIDPATHRELATIF_DANS_PROJET_CIBLE
				, Paths.get("fr/orsys/demo")
				, ArboresceurProjetSource.getGroupIdPathRelatif());
		
		/* Selection du projet cible. */
		ArboresceurProjetSource.selectionnerProjetSource(projetSourcePath);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Racine des sources java : " + ArboresceurProjetSource.getRacineSourcesJavaPath());
			System.out.println("Racine des tests java : " + ArboresceurProjetSource.getRacineTestsJavaPath());
		}
		
		final Path pathSourcesJava 
			= Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/copieur_arborescence_maven/src/main/java/fr/orsys/demo");
		
		final Path pathTestsJava 
		= Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/copieur_arborescence_maven/src/test/java/fr/orsys/demo");
		
		assertEquals("racine des sources java src/main/java/fr/orsys/demo : "
				, pathSourcesJava
				, ArboresceurProjetSource.getRacineSourcesJavaPath());
		
		assertEquals("racine des tests java src/test/java/fr/orsys/demo : "
				, pathTestsJava
				, ArboresceurProjetSource.getRacineTestsJavaPath());
		
		final Path pathRelatif = Paths.get("levy/daniel/application");
		
		// UTILISATION DE SETTER DU GROUPIDPATHRELATIF
		ArboresceurProjetSource.setGroupIdPathRelatif(pathRelatif);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(GROUPID_DANS_PROJET_CIBLE + ArboresceurProjetSource.getGroupId());
			System.out.println(GROUPIDPATHRELATIF_DANS_PROJET_CIBLE + ArboresceurProjetSource.getGroupIdPathRelatif());
		}
		
		assertEquals("le groupId vaut levy.daniel.application : "
				, "levy.daniel.application"
				, ArboresceurProjetSource.getGroupId());
		
		assertEquals(GROUPIDPATHRELATIF_DANS_PROJET_CIBLE
				, pathRelatif
				, ArboresceurProjetSource.getGroupIdPathRelatif());

		/* Selection du projet cible. */
		ArboresceurProjetSource.selectionnerProjetSource(projetSourcePath);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Racine des sources java2 : " + ArboresceurProjetSource.getRacineSourcesJavaPath());
			System.out.println("Racine des tests java2 : " + ArboresceurProjetSource.getRacineTestsJavaPath());
		}
		
		final Path pathSourcesJava2 
			= Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/copieur_arborescence_maven/src/main/java/levy/daniel/application");
		
		final Path pathTestsJava2 
		= Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/copieur_arborescence_maven/src/test/java/levy/daniel/application");
		
		assertEquals("racine des sources java src/main/java/levy/daniel/application : "
				, pathSourcesJava2
				, ArboresceurProjetSource.getRacineSourcesJavaPath());
		
		assertEquals("racine des tests java src/test/java/levy/daniel/application : "
				, pathTestsJava2
				, ArboresceurProjetSource.getRacineTestsJavaPath());


	} // Fin de testSetGroupId().__________________________________________
	
	

} // FIN DE LA CLASSE ArboresceurProjetSourceTest.---------------------------
