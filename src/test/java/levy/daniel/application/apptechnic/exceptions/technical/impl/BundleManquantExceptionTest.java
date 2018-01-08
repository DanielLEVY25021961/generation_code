package levy.daniel.application.apptechnic.exceptions.technical.impl;

import static org.junit.Assert.assertNotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.apptechnic.exceptions.technical.AbstractTechnicalException;


/**
 * class BundleManquantExceptionTest :<br/>
 * Classe de test des Exceptions Typées de l'application.<br/>
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
 * @since 12 oct. 2017
 *
 */
public class BundleManquantExceptionTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * AFFICHAGE_GENERAL : Boolean :<br/>
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(BundleManquantExceptionTest.class);


	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR BundleManquantExceptionTest() :<br/>
	 * <ul>
	 * <li>CONSTRUCTEUR D'ARITE NULLE.</li>
	 * <ul>
	 */
	public BundleManquantExceptionTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method testExceptions() :<br/>
	 * <ul>
	 * Teste le comportement des Exceptions définies dans l'application.
	 * <li>Garantit que le SINGLETON 'listeExceptions' encapsule 
	 * les messages de toutes les Exceptions levées à la suite.</li>
	 * </ul>
	 */
	@Test
	public void testExceptions() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************


		try {
			
			throw new BundleManquantException(
					"un properties est manquant");
		}
		catch (BundleManquantException e) {
			
			final AbstractTechnicalException exc 
			= new FichierInexistantException("Un fichier est manquant", e);
			
			/* AFFICHAGE A LA CONSOLE. */
			if (AFFICHAGE_GENERAL && affichage) {
				System.out.println(exc.listeExceptionsToString());
			}
			
			
			assertNotNull(
					"La liste des Exceptions ne doitpas être null : "
					, exc.listeExceptionsToString());
		}
		
	} // Fin de testExceptions().__________________________________________


	
} // FIN DE LA CLASSE BundleManquantExceptionTest.---------------------------
