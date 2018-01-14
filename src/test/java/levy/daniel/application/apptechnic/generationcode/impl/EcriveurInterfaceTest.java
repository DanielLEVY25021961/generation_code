package levy.daniel.application.apptechnic.generationcode.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


/**
 * class EcriveurInterfaceTest :<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 5 janv. 2018
 *
 */
public class EcriveurInterfaceTest {

	// ************************ATTRIBUTS************************************/

	
	/**
	 * AFFICHAGE_GENERAL : Boolean :<br/>
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	
	/**
	 * TIRETS : String :<br/>
	 * "--------------------------------------------------------".<br/>
	 */
	public static final String TIRETS 
	= "--------------------------------------------------------";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(EcriveurInterfaceTest.class);


	// *************************METHODES************************************/
	
	/**
	* method CONSTRUCTEUR EcriveurInterfaceTest() :<br/>
	* CONSTRUCTEUR D'ARITE NULLE.<br/>
	*/
	public EcriveurInterfaceTest() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method testCreerLignePackage() :<br/>
	 * <ul>
	 * <li>.</li>
	 * <li>.</li>
	 * </ul>
	 * :  :  .<br/>
	 */
//	@Test
//	public void testCreerLignePackage() {
//
//		
//		// **********************************
//		// AFFICHAGE DANS LE TEST ou NON
//		final boolean affichage = true;
//		// **********************************
//		
//		final String pathInterface 
//			= "D:/Donnees/eclipse/eclipseworkspace_neon/"
//					+ "generation_code/"
//					+ "src/main/java/"
//					+ "levy/daniel/application/"
//					+ "model/metier/profil/IProfil.java";
//		
//		final File interfaceP = new File(pathInterface);
//		
//		final EcriveurInterface ecriveur = new EcriveurInterface();
//		
//		final String resultat = ecriveur.creerLignePackage(interfaceP);
//		
//		/* AFFICHAGE A LA CONSOLE. */
//		if (AFFICHAGE_GENERAL && affichage) {
//			
//			System.out.println();
//			System.out.println(TIRETS);
//			System.out.println("testCreerLignePackage()");
//			System.out.println("1ere LIGNE PACKAGE : " + resultat);
//			System.out.println(TIRETS);
//			System.out.println();
//			
//		}
//		
//	}


	
	
	/**
	 * method testExpressionReguliere() :<br/>
	 * .<br/>
	 * <br/>
	 * : void :  .<br/>
	 */
	@Test
	public void testExpressionReguliere() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		final String chaine = "RG_PROFIL_PROFILSTRING_NOMENCLATURE_02 : le profilString du Profil doit respecter un ensemble fini de valeurs (nomenclature)";
		
		final boolean resultat = this.conformeRg(chaine);
		
		if (AFFICHAGE_GENERAL && affichage) {			
			System.out.println("RESULTAT : " + resultat);
		}
		
	}
	
	
	
	/**
	 * method conformeRg(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Contrôle que pString est conforme aux noms des RG
	 * , à savoir 
	 * <ul>
	 * <li>"RG_"</li>
	 * <li>suivi par des majuscules 
	 * (éventuellement séparées par des _ ou -), </li>
	 * <li>puis un séparateur " : "</li>
	 * <li>puis un ensemble de caractères quelconques.</li>
	 * </ul>
	 * <li>Par exemple :<br/> 
	 * "RG_PROFIL_PROFILSTRING_NOMENCLATURE_02 : 
	 * le profilString du Profil doit respecter 
	 * un ensemble fini de valeurs (nomenclature)"</li>
	 * </ul>
	 * 
	 * "RG_PROFIL_PROFILSTRING_NOMENCLATURE_02 : le profilString du Profil doit respecter un ensemble fini de valeurs (nomenclature)";
	 * 
	 * NOMBRE DE MATCHES : 4
	 * GROUP(0) : RG_PROFIL_PROFILSTRING_NOMENCLATURE_02 : le profilString du Profil doit respecter un ensemble fini de valeurs (nomenclature)
	 * GROUP(1) : PROFIL_PROFILSTRING_NOMENCLATURE_02
	 * GROUP(2) : PROFIL_PROFILSTRING_NOMENCLATURE_
	 * GROUP(3) : 02
	 * GROUP(4) : le profilString du Profil doit respecter un ensemble fini de valeurs (nomenclature)
	 * 
	 * RESULTAT : true
	 * 
	 * @param pString : String.<br/>
	 * 
	 * @return : boolean : true si conforme.<br/>
	 */
	private boolean conformeRg(
			final String pString) {
		
		boolean resultat = false;
		
		/* Pattern sous forme de String. */
		/* - Commence par "RG_" (^RG_)
		 * - poursuit par des majusculres séparées par des _ ou - ([A-Z-_]*).
		 * - poursuit par des chiffres (\\d+) 
		 * - séparateur " : ".
		 * - poursuit par n'importe quels caractères
		 * */
		final String patternString = "^(RG_(([A-Z_-]*)(\\d+))) : (.+)$";
		
		/* Instanciation d'un Pattern. */
		final Pattern pattern = Pattern.compile(patternString);
		
		/* Instanciation d'un moteur de recherche Matcher. */
		final Matcher matcher = pattern.matcher(pString);
		
		/* Recherche du Pattern. */
		final boolean trouve = matcher.matches();
		
		if (trouve) {

			final int nbreMatches = matcher.groupCount();
			
			System.out.println("NOMBRE DE MATCHES : " + nbreMatches);
			
			for (int i = 0; i <= nbreMatches; i++) {
				
				System.out.println("GROUP(" + i + ") : " + matcher.group(i));
			}

			resultat = true;
		}
		
		return resultat;
		
	} // Fin de conformeRg(...).___________________________________________
	

	
	/**
	 * method testDateDuJour() :<br/>
	 * <ul>
	 * <li>.</li>
	 * <li>.</li>
	 * </ul>
	 * :  :  .<br/>
	 */
	@Test
	public void testDateDuJour() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		if (AFFICHAGE_GENERAL && affichage) {			
			System.out.println("RESULTAT : " + this.afficherDateDuJour());
		}

		
	} // Fin de testDateDuJour().__________________________________________
	
	

	/**
	 * method afficherDateDuJour() :<br/>
	 * <ul>
	 * <li>Retourne une String pour l'affichage de la date du jour.</li>
	 * <li>Par exemple : 12 janvier 2018.</li>
	 * </ul>
	 *
	 * @return : String : Date du jour.<br/>
	 */
	private String afficherDateDuJour() {
		
		final Date dateDuJour = new Date();
		
		final Locale localeFr = new Locale("fr", "FR");
		
		/* 12 janvier 2018. */
		final DateFormat dfDateFrancaise 
		= new SimpleDateFormat("dd MMMM yyyy", localeFr);
		
		return dfDateFrancaise.format(dateDuJour);
		
	} // Fin de afficherDateDuJour().______________________________________
	


} // FIN DE LA CLASSE EcriveurInterfaceTest.---------------------------------
