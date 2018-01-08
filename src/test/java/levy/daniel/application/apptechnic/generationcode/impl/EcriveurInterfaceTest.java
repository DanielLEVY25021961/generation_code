package levy.daniel.application.apptechnic.generationcode.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


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

	

} // FIN DE LA CLASSE EcriveurInterfaceTest.---------------------------------
