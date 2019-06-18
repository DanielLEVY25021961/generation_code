package levy.daniel.application.generationcode.generationfichierssource.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * CLASSE GenerateurDTOTest :<br/>
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
 * @since 18 juin 2019
 *
 */
public class GenerateurDTOTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	/**
	 * "unused".
	 */
	public static final String UNUSED 
		= "unused";

	/**
	 * "doit retourner null : ".
	 */
	public static final String DOIT_RETOURNER_NULL 
		= "doit retourner null : ";
	
	/**
	 * "ne doit pas retourner null : ".
	 */
	public static final String NE_DOIT_PAS_RETOURNER_NULL 
		= "ne doit pas retourner null : ";

	/**
	 * "doit retourner la bonne valeur : ".<br/>
	 */
	public static final String DOIT_RETOURNER_BONNE_VALEUR 
		= "doit retourner la bonne valeur : ";

	/**
	 * "Doit retourner la même instance : ".
	 */
	public static final String DOIT_RETOURNER_MEME_INSTANCE 
		= "Doit retourner la même instance : ";

	/**
	 * "doit retourner invalide : ".
	 */
	public static final String DOIT_RETOURNER_INVALIDE 
		= "doit retourner invalide : ";
	
	/**
	 * Paths.get(".").toAbsolutePath().normalize().<br/>
	 */
	public static final Path PATH_ABSOLU_PRESENT_PROJET 
		= Paths.get(".").toAbsolutePath().normalize();
	
	/**
	 * "D:/Donnees/eclipse/eclipseworkspace/traficweb_v1".
	 */
	public static final String CHEMIN_ABSOLU_PROJET_ECLIPSE 
		= "D:/Donnees/eclipse/eclipseworkspace/traficweb_v1";
	
	/**
	 * Paths.get(CHEMIN_ABSOLU_PROJET_ECLIPSE).
	 */
	public static final Path PATH_ABSOLU_PROJET_ECLIPSE 
		= Paths.get(CHEMIN_ABSOLU_PROJET_ECLIPSE);

	/**
	 * "src/main/java/levy/daniel/application/model/metier/
	 * televersement/impl/Televersement.java".
	 */
	public static final String CHEMIN_RELATIF_FICHIER_SOURCE 
		= "src/main/java/levy/daniel/application/model/metier/televersement/impl/Televersement.java";
	
	/**
	 * Paths.get(CHEMIN_RELATIF_FICHIER_SOURCE).
	 */
	public static final Path PATH_RELATIF_FICHIER_SOURCE 
		= Paths.get(CHEMIN_RELATIF_FICHIER_SOURCE);

	/**
	 * new GenerateurDTO();.<br/>
	 */
	private static transient GenerateurDTO generateur;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(GenerateurDTOTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public GenerateurDTOTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * teste la méthode calculerNomSimplePackageConceptMetier(File pFile).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de 
	 * calculerNomSimplePackageConceptMetier(File pFile).</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCalculerNomSimplePackageConceptMetier() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE GenerateurDTOTest - méthode testCalculerNomSimplePackageConceptMetier() ********** ");
		}
		
		// METHODE A TESTER
		final File fichierSource 
		= generateur.obtenirFichierSource(
				PATH_ABSOLU_PROJET_ECLIPSE
					, PATH_RELATIF_FICHIER_SOURCE);
		
		final String resultat 
			= generateur
				.calculerNomSimplePackageConceptMetier(fichierSource);
				
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(resultat);
		}
		
		/* garantit le bon fonctionnement de 
		 * calculerNomSimplePackageConceptMetier(File pFile). */
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "televersement"
						, resultat);

	} // fin de testCalculerNomSimplePackageConceptMetier().____________________________


	
	/**
	 * teste la méthode calculerNomConceptMetier(File pFile).<br/>
	 * <ul>
	 * <li>garantit le bon fonctionnement de 
	 * calculerNomConceptMetier(File pFile).</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCalculerNomConceptMetier() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE GenerateurDTOTest - méthode testCalculerNomConceptMetier() ********** ");
		}
		
		// METHODE A TESTER
		final File fichierSource 
		= generateur.obtenirFichierSource(
				PATH_ABSOLU_PROJET_ECLIPSE
					, PATH_RELATIF_FICHIER_SOURCE);
		
		final String resultat 
			= generateur
				.calculerNomConceptMetier(fichierSource);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(resultat);
		}
		
		/* garantit le bon fonctionnement de 
		 * calculerNomConceptMetier(File pFile). */
		assertEquals(
				DOIT_RETOURNER_BONNE_VALEUR
					, "Televersement"
						, resultat);

	} // fin de testCalculerNomConceptMetier().____________________________


	
	/**
	 * Exécuté avant tout test de la classe.<br/>
	 * 
	 * @throws Exception 
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
				
		generateur 
			= new GenerateurDTO(PATH_ABSOLU_PROJET_ECLIPSE, null);
		
		final File fichierSource 
		= generateur.obtenirFichierSource(
				PATH_ABSOLU_PROJET_ECLIPSE
					, PATH_RELATIF_FICHIER_SOURCE);
		
		generateur.setConceptMetier(fichierSource);
		
		System.out.println(generateur.toString());
		
		System.out.println(
				generateur
				.afficherMapEncapsulationDeclarationMembre(
						generateur.getAttributsPrivateMap()));
		
	} // Fin de beforeClass()._____________________________________________
	
	

} // FIN DE LA CLASSE GenerateurDTOTest.-------------------------------------
