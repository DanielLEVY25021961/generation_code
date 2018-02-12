package levy.daniel.application.apptechnic.generationcode.ecriveurs.test;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.IGenerateur;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveurFichiersJava;

/**
 * class EcriveurMetierTest :<br/>
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
 * @since 12 févr. 2018
 *
 */
public class EcriveurMetierTest extends AbstractEcriveurFichiersJava {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ECRIVEUR_METIER_TEST : String :<br/>
	 * "Classe EcriveurMetierTest".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_METIER_TEST 
		= "Classe EcriveurMetierTest";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(EcriveurMetierTest.class);

	// *************************METHODES************************************/
	
	
	
	 /**
	 * method CONSTRUCTEUR EcriveurMetierTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public EcriveurMetierTest() {
		
		super();
		
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void ecrireCodeGenerique(
			final File pFile
				, final IGenerateur pGenerateur) {
		// TODO Auto-generated method stub

	} // Fin de ecrireCodeGenerique(...).__________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirTypeFichierJava() {	
		return "LA CLASSE ";		
	} // Fin de fournirTypeFichierJava(...)._______________________________
	

	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * retourne pour un EcriveurMetierTest : "Classe EcriveurMetierTest".<br/>
	 * <br/>
	 */
	@Override
	protected final String fournirNomClasse() {
		return CLASSE_ECRIVEUR_METIER_TEST;
	} // Fin de fournirNomClasse().________________________________________




} // FIN DE LA CLASSE EcriveurMetierTest.------------------------------------
