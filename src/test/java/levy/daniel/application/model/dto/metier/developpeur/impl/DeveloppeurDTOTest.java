package levy.daniel.application.model.dto.metier.developpeur.impl;

import static org.junit.Assert.assertEquals;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.dto.metier.developpeur.IDeveloppeurDTO;
import levy.daniel.application.model.metier.developpeur.IDeveloppeur;
import levy.daniel.application.model.metier.developpeur.impl.Developpeur;

/**
 * CLASSE DeveloppeurDTOTest :<br/>
 * Test JUnit de la classe DeveloppeurDTO.<br/>
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
 * @since 14 déc. 2018
 *
 */
public class DeveloppeurDTOTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	

	/**
	 * "Papy Gonzales".<br/>
	 */
	public static final String PAPY 
		= "Papy Gonzales";
	
	/**
	 * Objet Metier.
	 */
	public static transient IDeveloppeur objet1 
		= new Developpeur(1L, PAPY, 15);

	/**
	 * DTO.<br/>
	 */
	public static transient IDeveloppeurDTO objet1DTO 
		= new DeveloppeurDTO("1", PAPY, "15");
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(DeveloppeurDTOTest.class);
	

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public DeveloppeurDTOTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * <b>Teste les conctructeurs transformateurs</b> 
	 * dans l'objet métier et le DTO.<br/>
	 * <ul>
	 * <li>vérifie que le constructeur transformateur de DTO 
	 * dans l'objet métier fonctionne.</li>
	 * <li>vérifie que le constructeur transformateur d'objet métier 
	 * dans le DTO fonctionne.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testConstructeursTransformateur() {
					
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
		System.out.println("********** CLASSE DeveloppeurDTOTest - méthode testConstructeursTransformateur() ********** ");		
		}
		
		final IDeveloppeur objetTransforme1 = new Developpeur(objet1DTO);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("Objet créé en transformant le DTO : " 
						+ objetTransforme1.toString());
			System.out.println("objet1 : " + objet1.toString());
		}
		
		/* vérifie que le constructeur transformateur de DTO 
		 * dans l'objet métier fonctionne. */
		assertEquals("objetTransforme1 equals objet1 : "
				, objet1, objetTransforme1);
		
		final IDeveloppeurDTO dtoTransforme1 = new DeveloppeurDTO(objet1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("DTO créé en transformant l'objet métier : " 
						+ dtoTransforme1.toString());
			System.out.println("objet1DTO : " + objet1DTO);
		}
		
		/* vérifie que le constructeur transformateur d'objet métier 
		 * dans le DTO fonctionne. */
		assertEquals("dtoTransforme1 equals objet1DTO : "
				, objet1DTO, dtoTransforme1);


	} // Fin de testConstructeursTransformateur()._________________________
	
	

} // FIN DE LA CLASSE DeveloppeurDTOTest.------------------------------------
