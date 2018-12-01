package levy.daniel.application.model.services.utilitaires.copieurarboresence.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.services.utilitaires.copieurarborescence.IChercheurRepertoires;
import levy.daniel.application.model.services.utilitaires.copieurarborescence.impl.ChercheurRepertoires;

/**
 * class ChercheurRepertoiresTest :<br/>
 * Test JUnit de la classe ChercheurRepertoires.<br/>
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
 * @since 18 nov. 2018
 *
 */
public class ChercheurRepertoiresTest {

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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ChercheurRepertoiresTest.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ChercheurRepertoiresTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________



	/**
	 * teste la méthode chercherArborescenceSous(repertoire).<br/>
	 * .<br/>
	 * <br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testChercherArborescenceSous() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE ChercheurRepertoiresTest - méthode testChercherArborescenceSous() ********** ");
		}
		
		/* répertoire racine contenant l'arboresence à recopier 
		 * (tout sous src/main/java/levy/daniel/application du présent projet). */
		final String cheminRepertoire = "./src/main/java/levy/daniel/application";
		
		final File repertoire = new File(cheminRepertoire);
		
		final IChercheurRepertoires chercheur = new ChercheurRepertoires();
		
		final List<File> listeSousRepertoires 
			= chercheur.chercherArborescenceSous(repertoire);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("Répertoire racine dont il faut recopier l'arborescence : " + repertoire.getAbsolutePath());
			System.out.println();
			System.out.println("Liste des répertoires sous la racine (afficherListeFiles) : ");
			System.out.println(chercheur.afficherListeFiles(listeSousRepertoires)); 
			System.out.println(); 
		}
		
		assertNotNull(
				"la liste des répertoires sous repertoire ne doit pas être null : "
					, listeSousRepertoires);
		
		assertFalse(
				"la liste des répertoires sous repertoire ne doit pas être vide : "
					, listeSousRepertoires.isEmpty());
		
		final List<String> listeCheminsSousRepertoires 
			= chercheur.chercherCheminsRepertoiresSous(repertoire);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("Liste des répertoires sous la racine (afficherListeChemins) : ");
			System.out.println(chercheur.afficherListeChemins(listeCheminsSousRepertoires)); 
		}
		
		assertNotNull(
				"la liste des répertoires sous repertoire ne doit pas être null : "
					, listeCheminsSousRepertoires);
		
		assertFalse(
				"la liste des répertoires sous repertoire ne doit pas être vide : "
					, listeCheminsSousRepertoires.isEmpty());
	} // Fin de testChercherArborescenceSous().____________________________


	
} // FIN DE LA CLASSE ChercheurRepertoiresTest.------------------------------
