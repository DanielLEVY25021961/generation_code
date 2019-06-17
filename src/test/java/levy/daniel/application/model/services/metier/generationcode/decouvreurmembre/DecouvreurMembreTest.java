package levy.daniel.application.model.services.metier.generationcode.decouvreurmembre;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * CLASSE DecouvreurMembreTest :<br/>
 * Test JUnit de la classe {@link DecouvreurMembre}.<br/>
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
 * @since 16 juin 2019
 *
 */
public class DecouvreurMembreTest {

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
	 * Paths.get(CHEMIN_ABSOLU_PROJET_ECLIPSE.
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
	 * <b>motif REGEX pour détecter les lignes de déclaration des ATTRIBUTS</b> 
	 * d'une classe.<br/>
	 * <ul>
	 * <li>seuls les attributs public, protected ou private sont détectés.</li>
	 * <li>les attributs "friendly" déclarés sans modificateur 
	 * ne sont pas détectés.</li>
	 * </ul>
	 * <p>
	 * <b>"^(\\s*)((public?|protected?|private?)\\s*(static?|)\\s*(final?|)\\s*(transient?|)\\s+(\\S+)\\s+([a-zA-Z0-9_]+)\\s*(.*));$"</b>.
	 * </p>
	 * <ol>
	 * <li><b>^(\\s*)</b> signifie 
	 * <i>"commence par 0, 1 ou plusieurs espaces</i>.</li>
	 * <li><b>((public|protected|private)</b> 
	 * signifie <i>"public, ou protected, ou private"</i></li>
	 * <li><b>\\s*</b> signifie <i>"0, 1, ou plusieurs espaces"</i></li>
	 * <li><b>(static?|)</b> signifie <i>"static une seule fois au pas (|)"</i></li>
	 * <li><b>\\s*</b> signifie <i>"0, 1, ou plusieurs espaces"</i></li>
	 * <li><b>(final?|)</b> signifie <i>"final une seule fois au pas (|)"</i></li>
	 * <li><b>\\s*</b> signifie <i>"0, 1, ou plusieurs espaces"</i></li>
	 * <li><b>(transient?|)</b> signifie <i>"transient une seule fois au pas (|)"</i></li>
	 * <li><b>\\s+</b> signifie <i>"au moins un espace"</i></li>
	 * <li><b>(\\S+)</b> signifie <i>"au moins 1 caractère non blanc"</i></li>
	 * <li><b>\\s+</b> signifie <i>"au moins un espace"</i></li>
	 * <li><b>([a-zA-Z0-9_]+)</b> signifie <i>"au moins un caractère 
	 * parmi minuscules, majuscules, chiffre ou UNDERSCORE"</i></li>
	 * <li><b>\\s*</b> signifie <i>"0, 1, ou plusieurs espaces"</i></li>
	 * <li><b>(.*)</b> signifie <i>"0, 1, ou plusieurs caractères QUELCONQUES"</i></li>
	 * <li><b>(.*)</b> signifie <i>"termine par un point-virgule"</i></li>
	 * </ol>
	 */
	public static final String MOTIF_REGEX_ATTRIBUT 
		= "^(\\s*)((public|protected|private)\\s*(static?|)\\s*(final?|)\\s*(transient?|)\\s+(\\S+)\\s+([a-zA-Z0-9_]+)\\s*(.*));$";
	
	/**
	 * new DecouvreurMembre().
	 */
	private final transient DecouvreurMembre decouvreur = new DecouvreurMembre();
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(DecouvreurMembreTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public DecouvreurMembreTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * teste la méthode obtenirFichierSource(
	 * Path pCheminAbsoluProjetEclipseCiblePath, 
	 * Path cheminRelatifFichierSourcePath) avec de mauvais Paths.<br/>
	 * <ul>
	 * <li>garantit que la méthode retourne null si 
	 * les Paths ne sont pas bons.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testObtenirFichierSourceMauvais() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE DecouvreurMembreTest - méthode testObtenirFichierSourceMauvais() ********** ");
		}
		
		// METHODE A TESTER
		final File fichierSource 
			= this.decouvreur.obtenirFichierSource(
					PATH_ABSOLU_PRESENT_PROJET
						, PATH_RELATIF_FICHIER_SOURCE);
				
		/* garantit que la méthode retourne null si les Paths ne sont pas bons. */
		assertNull(DOIT_RETOURNER_NULL, fichierSource);
		
	} // Fin de testObtenirFichierSourceMauvais()._________________________


	
	/**
	 * teste la méthode obtenirFichierSource(
	 * Path pCheminAbsoluProjetEclipseCiblePath
	 * , Path cheminRelatifFichierSourcePath) avec de bons path.<br/>
	 * <ul>
	 * <li>garantit que la méthode retourne le bon File.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testObtenirFichierSource() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE DecouvreurMembreTest - méthode testObtenirFichierSource() ********** ");
		}
		
		// METHODE A TESTER
		final File fichierSource 
			= this.decouvreur.obtenirFichierSource(
					PATH_ABSOLU_PROJET_ECLIPSE
						, PATH_RELATIF_FICHIER_SOURCE);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(fichierSource.getAbsolutePath());
		}
		
		/* garantit que la méthode retourne le bon File. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, fichierSource);
		
	} // Fin de testObtenirFichierSource().________________________________
	
	
	
	/**
	 * teste la méthode lireFichierSource(File).<br/>
	 * <ul>
	 * <li>garantit que lireFichierSource(File) lit le fichier.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testLireFichierSource() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE DecouvreurMembreTest - méthode testLireFichierSource() ********** ");
		}
		
		final File fichierSource 
			= this.decouvreur.obtenirFichierSource(
					PATH_ABSOLU_PROJET_ECLIPSE
						, PATH_RELATIF_FICHIER_SOURCE);
		
		// METHODE A TESTER
		final List<String> resultat 
			= this.decouvreur.lireFichierSource(fichierSource);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println(this.decouvreur.afficherListeString(resultat));
		}
		
		/* garantit que lireFichierSource(File) lit le fichier. */
		assertNotNull(NE_DOIT_PAS_RETOURNER_NULL, resultat);
		
	} // Fin de testLireFichierSource().___________________________________

	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception : void :  .<br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testRegex() throws Exception {
		
//		final String motifRegex = "^([public|protected|private]?) (static?) (final?) (transient?) (\\S+) (\\S+)(.*)$";
		final String motifRegex = "^(\\s*)((public?|protected?|private?|)\\s*(static?|)\\s*(final?|)\\s*(transient?|)\\s+(\\S+)\\s+([a-zA-Z0-9_]+)\\s*(.*));$";
		final Pattern pattern = Pattern.compile(motifRegex);
		
//		final String ligneATester = "public static final Boolean AFFICHAGE_GENERAL = true;";
		final String ligneATester = "		private EnumTypeFichierDonnees typeFichier;";
//		final String ligneATester = "public class Televersement implements ITeleversement {";
		
		final Matcher matcher = pattern.matcher(ligneATester);
		
		
		if (matcher.matches()) {
			
			final int nombreMatches = matcher.groupCount();
			
			for (int i = 0; i <= nombreMatches; i++) {
				
				System.out.println("GROUP(" + i + ")= " + matcher.group(i));
			}
			
		} else {
			System.out.println("PAS UN CHAMP");
		}
	}
	
	
		
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception : void :  .<br/>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFonction() throws Exception {
		
		final File fichierSource 
		= this.decouvreur.obtenirFichierSource(
				PATH_ABSOLU_PROJET_ECLIPSE
					, PATH_RELATIF_FICHIER_SOURCE);
	
		// METHODE A TESTER
		final List<String> resultat 
			= this.decouvreur.lireFichierSource(fichierSource);
		
		for (final String ligne : resultat) {
			if (this.estDeclarationAttribut(ligne)) {
				System.out.println(ligne);
			}
		}
	}
	
	
	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pString
	 * @return : boolean :  .<br/>
	 */
	public final boolean estDeclarationAttribut(String pString) {
		
		/* retourne false si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return false;
		}
		
		final Pattern pattern = Pattern.compile(MOTIF_REGEX_ATTRIBUT);
		
		final Matcher matcher = pattern.matcher(pString);
		
		if (matcher.matches()) {
			return true;
		}
		
		return false;
		
	}

	
} // FIN DE LA CLASSE CLASSE DecouvreurMembreTest.---------------------------
