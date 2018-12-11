package levy.daniel.application.model.services.utilitaires.arboresceurprojet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * CLASSE ArboresceurProjetCibleTest :<br/>
 * Test JUnit de la classe ArboresceurProjetCible.<br/>
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
 * @since 23 nov. 2018
 *
 */
public class ArboresceurProjetCibleTest {

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
		= LogFactory.getLog(ArboresceurProjetCibleTest.class);
	

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ArboresceurProjetCibleTest() {
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
			System.out.println("********** CLASSE ArboresceurProjetCibleTest - méthode testCalculerPaths() ********** ");
		}
		
		// ************************************************* //
		final Path projetCiblePath 
			= Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/copieur_arborescence_maven");
//		final Path projetCiblePath = null;
		// ************************************************* //
		
		/* Détermination du projet cible. */
		ArboresceurProjetCible.selectionnerProjetCible(projetCiblePath);
		
		final Path projetCiblePathStocke 
			= ArboresceurProjetCible.getProjetCiblePath();
		final String projetCibleNom 
			= ArboresceurProjetCible.getProjetCibleNom();
		
		final Path srcMainJavaPath 
			= ArboresceurProjetCible.getSrcMainJavaPath();
		final Path srcMainResourcesPath 
			= ArboresceurProjetCible.getSrcMainResourcesPath();
		final Path srcMainResourcesMetaInfPath 
			= ArboresceurProjetCible.getSrcMainResourcesMetaInfPath();
		final Path srcTestJavaPath 
			= ArboresceurProjetCible.getSrcTestJavaPath();
		final Path srcTestResourcesPath 
			= ArboresceurProjetCible.getSrcTestResourcesPath();
		final Path srcTestResourcesMetaInfPath 
			= ArboresceurProjetCible.getSrcTestResourcesMetaInfPath();
		
		final Path racineSourcesJavaPath 
			= ArboresceurProjetCible.getRacineSourcesJavaPath();
		final Path racineTestsJavaPath 
			= ArboresceurProjetCible.getRacineTestsJavaPath();
		
		// APPTECHNIC
		final Path coucheAppTechnicMainPath 
			= ArboresceurProjetCible.getCoucheAppTechnicMainPath();
		final Path coucheAppTechnicTestPath 
			= ArboresceurProjetCible.getCoucheAppTechnicTestPath();
		
		// CONTROLLERS
		final Path coucheControllersMainPath 
			= ArboresceurProjetCible.getCoucheControllersMainPath();
		final Path coucheControllersTestPath 
			= ArboresceurProjetCible.getCoucheControllersTestPath();
		
		final Path coucheControllersDesktopMainPath 
			= ArboresceurProjetCible.getCoucheControllersDesktopMainPath();
		final Path coucheControllersDesktopTestPath 
			= ArboresceurProjetCible.getCoucheControllersDesktopTestPath();
		final Path coucheControllersDesktopAccueilMainPath 
			= ArboresceurProjetCible.getCoucheControllersDesktopAccueilMainPath();
		final Path coucheControllersDesktopAccueilTestPath 
			= ArboresceurProjetCible.getCoucheControllersDesktopAccueilTestPath();
		final Path coucheControllersDesktopMetierMainPath 
			= ArboresceurProjetCible.getCoucheControllersDesktopMetierMainPath();
		final Path coucheControllersDesktopMetierTestPath 
			= ArboresceurProjetCible.getCoucheControllersDesktopMetierTestPath();
		final Path coucheControllersDesktopUtilitairesMainPath 
			= ArboresceurProjetCible.getCoucheControllersDesktopUtilitairesMainPath();
		final Path coucheControllersDesktopUtilitairesTestPath 
			= ArboresceurProjetCible.getCoucheControllersDesktopUtilitairesTestPath();
	
		final Path coucheControllersWebMainPath 
			= ArboresceurProjetCible.getCoucheControllersWebMainPath();
		final Path coucheControllersWebTestPath 
			= ArboresceurProjetCible.getCoucheControllersWebTestPath();
		final Path coucheControllersWebAccueilMainPath 
			= ArboresceurProjetCible.getCoucheControllersWebAccueilMainPath();
		final Path coucheControllersWebAccueilTestPath 
			= ArboresceurProjetCible.getCoucheControllersWebAccueilTestPath();
		final Path coucheControllersWebMetierMainPath 
			= ArboresceurProjetCible.getCoucheControllersWebMetierMainPath();
		final Path coucheControllersWebMetierTestPath 
			= ArboresceurProjetCible.getCoucheControllersWebMetierTestPath();
		final Path coucheControllersWebUtilitairesMainPath 
			= ArboresceurProjetCible.getCoucheControllersWebUtilitairesMainPath();
		final Path coucheControllersWebUtilitairesTestPath 
			= ArboresceurProjetCible.getCoucheControllersWebUtilitairesTestPath();
		
		// VUES
		final Path coucheVuesMainPath 
			= ArboresceurProjetCible.getCoucheVuesMainPath();
		final Path coucheVuesTestPath 
			= ArboresceurProjetCible.getCoucheVuesTestPath();
		
		final Path coucheVuesDesktopMainPath 
			= ArboresceurProjetCible.getCoucheVuesDesktopMainPath();
		final Path coucheVuesDesktopTestPath 
			= ArboresceurProjetCible.getCoucheVuesDesktopTestPath();
		final Path coucheVuesDesktopAccueilMainPath 
			= ArboresceurProjetCible.getCoucheVuesDesktopAccueilMainPath();
		final Path coucheVuesDesktopAccueilTestPath 
			= ArboresceurProjetCible.getCoucheVuesDesktopAccueilTestPath();
		final Path coucheVuesDesktopMetierMainPath 
			= ArboresceurProjetCible.getCoucheVuesDesktopMetierMainPath();
		final Path coucheVuesDesktopMetierTestPath 
			= ArboresceurProjetCible.getCoucheVuesDesktopMetierTestPath();
		final Path coucheVuesDesktopUtilitairesMainPath 
			= ArboresceurProjetCible.getCoucheVuesDesktopUtilitairesMainPath();
		final Path coucheVuesDesktopUtilitairesTestPath 
			= ArboresceurProjetCible.getCoucheVuesDesktopUtilitairesTestPath();

		final Path coucheVuesWebMainPath 
			= ArboresceurProjetCible.getCoucheVuesWebMainPath();
		final Path coucheVuesWebTestPath 
			= ArboresceurProjetCible.getCoucheVuesWebTestPath();
		final Path coucheVuesWebAccueilMainPath 
			= ArboresceurProjetCible.getCoucheVuesWebAccueilMainPath();
		final Path coucheVuesWebAccueilTestPath 
			= ArboresceurProjetCible.getCoucheVuesWebAccueilTestPath();
		final Path coucheVuesWebMetierMainPath 
			= ArboresceurProjetCible.getCoucheVuesWebMetierMainPath();
		final Path coucheVuesWebMetierTestPath 
			= ArboresceurProjetCible.getCoucheVuesWebMetierTestPath();
		final Path coucheVuesWebUtilitairesMainPath 
			= ArboresceurProjetCible.getCoucheVuesWebUtilitairesMainPath();
		final Path coucheVuesWebUtilitairesTestPath 
			= ArboresceurProjetCible.getCoucheVuesWebUtilitairesTestPath();

		
		// MODEL
		final Path coucheModelMainPath 
			= ArboresceurProjetCible.getCoucheModelMainPath();
		final Path coucheModelTestPath 
			= ArboresceurProjetCible.getCoucheModelTestPath();
		
		final Path coucheModelDTOMainPath 
			= ArboresceurProjetCible.getCoucheModelDTOMainPath();
		final Path coucheModelDTOTestPath 
			= ArboresceurProjetCible.getCoucheModelDTOTestPath();
		final Path coucheModelDTOMetierMainPath 
			= ArboresceurProjetCible.getCoucheModelDTOMetierMainPath();
		final Path coucheModelDTOMetierTestPath 
			= ArboresceurProjetCible.getCoucheModelDTOMetierTestPath();
		
		final Path coucheModelMetierMainPath 
			= ArboresceurProjetCible.getCoucheModelMetierMainPath();
		final Path coucheModelMetierTestPath 
			= ArboresceurProjetCible.getCoucheModelMetierTestPath();

		final Path coucheModelPersistenceMainPath 
			= ArboresceurProjetCible.getCoucheModelPersistenceMainPath();
		final Path coucheModelPersistenceTestPath 
			= ArboresceurProjetCible.getCoucheModelPersistenceTestPath();
		final Path coucheModelPersistenceAccueilMainPath 
			= ArboresceurProjetCible.getCoucheModelPersistenceAccueilMainPath();
		final Path coucheModelPersistenceAccueilTestPath 
			= ArboresceurProjetCible.getCoucheModelPersistenceAccueilTestPath();
		final Path coucheModelPersistenceDaoexceptionsMainPath 
			= ArboresceurProjetCible.getCoucheModelPersistenceDaoexceptionsMainPath();
		final Path coucheModelPersistenceDaoexceptionsTestPath 
			= ArboresceurProjetCible.getCoucheModelPersistenceDaoexceptionsTestPath();
		final Path coucheModelPersistenceMetierMainPath 
			= ArboresceurProjetCible.getCoucheModelPersistenceMetierMainPath();
		final Path coucheModelPersistenceMetierTestPath 
			= ArboresceurProjetCible.getCoucheModelPersistenceMetierTestPath();

		final Path coucheModelServicesMainPath 
			= ArboresceurProjetCible.getCoucheModelServicesMainPath();
		final Path coucheModelServicesTestPath 
			= ArboresceurProjetCible.getCoucheModelServicesTestPath();
		final Path coucheModelServicesAccueilMainPath 
			= ArboresceurProjetCible.getCoucheModelServicesAccueilMainPath();
		final Path coucheModelServicesAccueilTestPath 
			= ArboresceurProjetCible.getCoucheModelServicesAccueilTestPath();
		final Path coucheModelServicesMetierMainPath 
			= ArboresceurProjetCible.getCoucheModelServicesMetierMainPath();
		final Path coucheModelServicesMetierTestPath 
			= ArboresceurProjetCible.getCoucheModelServicesMetierTestPath();
		final Path coucheModelServicesTransformeursMainPath 
			= ArboresceurProjetCible.getCoucheModelServicesTransformeursMainPath();
		final Path coucheModelServicesTransformeursMetierMainPath 
			= ArboresceurProjetCible.getCoucheModelServicesTransformeursMetierMainPath();
		final Path coucheModelServicesTransformeursTestPath 
			= ArboresceurProjetCible.getCoucheModelServicesTransformeursTestPath();
		final Path coucheModelServicesTransformeursMetierTestPath 
			= ArboresceurProjetCible.getCoucheModelServicesTransformeursMetierTestPath();
		final Path coucheModelServicesUtilitairesMainPath 
			= ArboresceurProjetCible.getCoucheModelServicesUtilitairesMainPath();
		final Path coucheModelServicesUtilitairesTestPath 
			= ArboresceurProjetCible.getCoucheModelServicesUtilitairesTestPath();
		final Path coucheModelServicesValideursMainPath 
			= ArboresceurProjetCible.getCoucheModelServicesValideursMainPath();
		final Path coucheModelServicesValideursMetierMainPath 
			= ArboresceurProjetCible.getCoucheModelServicesValideursMetierMainPath();
		final Path coucheModelServicesValideursTestPath 
			= ArboresceurProjetCible.getCoucheModelServicesValideursTestPath();
		final Path coucheModelServicesValideursMetierTestPath 
			= ArboresceurProjetCible.getCoucheModelServicesValideursMetierTestPath();
		
		final Path coucheModelUtilitairesMainPath 
			= ArboresceurProjetCible.getCoucheModelUtilitairesMainPath();
		final Path coucheModelUtilitairesTestPath 
			= ArboresceurProjetCible.getCoucheModelUtilitairesTestPath();
		
		// REPERTOIRES EXTERNES
		final Path conceptionAppliPath 
			= ArboresceurProjetCible.getConceptionAppliPath();
		final Path dataPath 
			= ArboresceurProjetCible.getDataPath();
		final Path dataH2Path 
			= ArboresceurProjetCible.getDataH2Path();
		final Path dataHSQLDBPath 
			= ArboresceurProjetCible.getDataHSQLDBPath();
		final Path dataJAXBPath 
			= ArboresceurProjetCible.getDataJAXBPath();
		final Path dataScriptsSqlPath 
			= ArboresceurProjetCible.getDataScriptsSqlPath();
		final Path javadocPath 
			= ArboresceurProjetCible.getJavadocPath();
		final Path javadocImagesPath 
			= ArboresceurProjetCible.getJavadocImagesPath();
		final Path logsPath 
			= ArboresceurProjetCible.getLogsPath();
		final Path rapportsControlePath 
			= ArboresceurProjetCible.getRapportsControlePath();
		final Path ressourcesExternesPath 
			= ArboresceurProjetCible.getRessourcesExternesPath();

		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println("projet cible : " + projetCiblePathStocke);
			System.out.println("Nom du projet cible : " + projetCibleNom);
			
			System.out.println("sources java srcMainJavaPath : " + srcMainJavaPath);
			System.out.println("ressources srcMainResourcesPath : " + srcMainResourcesPath);
			System.out.println("ressources srcMainResourcesPath/META-INF : " + srcMainResourcesMetaInfPath);
			System.out.println("tests JUnit srcTestJavaPath : " + srcTestJavaPath);
			System.out.println("ressources des tests JUnit srcTestResourcesPath : " + srcTestResourcesPath);
			System.out.println("ressources srcTestResourcesPath/META-INF : " + srcTestResourcesMetaInfPath);
			
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
			System.out.println(ArboresceurProjetCible.afficherArborescence());
			
			System.out.println();
			System.out.println("NOMBRE DE REPERTOIRES A CREER : " + ArboresceurProjetCible.fournirNombreRepACreer());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE MAIN");
			System.out.println(ArboresceurProjetCible.afficherArborescenceMainMap());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE TEST");
			System.out.println(ArboresceurProjetCible.afficherArborescenceTestMap());
			
			System.out.println();
			System.out.println("MAP ARBORESCENCE REPERTOIRES EXTERNES");
			System.out.println(ArboresceurProjetCible.afficherArborescenceRepExtMap());

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
			System.out.println("********** CLASSE ArboresceurProjetCibleTest - méthode testSetGroupId() ********** ");
		}
		
		// ************************************************* //
		final Path projetCiblePath 
			= Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/copieur_arborescence_maven");
//		final Path projetCiblePath = null;
		// ************************************************* //
		
		final String groupId = "fr.orsys.demo";
		
		// UTILISATION DE SETTER DU GROUPID
		ArboresceurProjetCible.setGroupId(groupId);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(GROUPID_DANS_PROJET_CIBLE + ArboresceurProjetCible.getGroupId());
			System.out.println(GROUPIDPATHRELATIF_DANS_PROJET_CIBLE + ArboresceurProjetCible.getGroupIdPathRelatif());
		}
		
		assertEquals("le groupId vaut groupId : "
				, groupId
				, ArboresceurProjetCible.getGroupId());
		
		assertEquals(GROUPIDPATHRELATIF_DANS_PROJET_CIBLE
				, Paths.get("fr/orsys/demo")
				, ArboresceurProjetCible.getGroupIdPathRelatif());
		
		/* Selection du projet cible. */
		ArboresceurProjetCible.selectionnerProjetCible(projetCiblePath);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Racine des sources java : " + ArboresceurProjetCible.getRacineSourcesJavaPath());
			System.out.println("Racine des tests java : " + ArboresceurProjetCible.getRacineTestsJavaPath());
		}
		
		final Path pathSourcesJava 
			= Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/copieur_arborescence_maven/src/main/java/fr/orsys/demo");
		
		final Path pathTestsJava 
		= Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/copieur_arborescence_maven/src/test/java/fr/orsys/demo");
		
		assertEquals("racine des sources java src/main/java/fr/orsys/demo : "
				, pathSourcesJava
				, ArboresceurProjetCible.getRacineSourcesJavaPath());
		
		assertEquals("racine des tests java src/test/java/fr/orsys/demo : "
				, pathTestsJava
				, ArboresceurProjetCible.getRacineTestsJavaPath());
		
		final Path pathRelatif = Paths.get("levy/daniel/application");
		
		// UTILISATION DE SETTER DU GROUPIDPATHRELATIF
		ArboresceurProjetCible.setGroupIdPathRelatif(pathRelatif);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(GROUPID_DANS_PROJET_CIBLE + ArboresceurProjetCible.getGroupId());
			System.out.println(GROUPIDPATHRELATIF_DANS_PROJET_CIBLE + ArboresceurProjetCible.getGroupIdPathRelatif());
		}
		
		assertEquals("le groupId vaut levy.daniel.application : "
				, "levy.daniel.application"
				, ArboresceurProjetCible.getGroupId());
		
		assertEquals(GROUPIDPATHRELATIF_DANS_PROJET_CIBLE
				, pathRelatif
				, ArboresceurProjetCible.getGroupIdPathRelatif());

		/* Selection du projet cible. */
		ArboresceurProjetCible.selectionnerProjetCible(projetCiblePath);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("Racine des sources java2 : " + ArboresceurProjetCible.getRacineSourcesJavaPath());
			System.out.println("Racine des tests java2 : " + ArboresceurProjetCible.getRacineTestsJavaPath());
		}
		
		final Path pathSourcesJava2 
			= Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/copieur_arborescence_maven/src/main/java/levy/daniel/application");
		
		final Path pathTestsJava2 
		= Paths.get("D:/Donnees/eclipse/eclipseworkspace_oxygen/copieur_arborescence_maven/src/test/java/levy/daniel/application");
		
		assertEquals("racine des sources java src/main/java/levy/daniel/application : "
				, pathSourcesJava2
				, ArboresceurProjetCible.getRacineSourcesJavaPath());
		
		assertEquals("racine des tests java src/test/java/levy/daniel/application : "
				, pathTestsJava2
				, ArboresceurProjetCible.getRacineTestsJavaPath());


	} // Fin de testSetGroupId().___________________________________________
	
	

} // FIN DE LA CLASSE ArboresceurProjetCibleTest.----------------------------
