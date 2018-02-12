package levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationtests;

import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.generationfichiersjava.AbstractGenerateurClasseSeule;

/**
 * CLASSE <b>GenerateurMetierTest</b> :<br/>
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
 * @since 8 févr. 2018
 *
 */
public class GenerateurMetierTest 
		extends AbstractGenerateurClasseSeule {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GenerateurMetierTest.class);

	// *************************METHODES************************************/

	
	
	 /**
	 * method CONSTRUCTEUR GenerateurMetierTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li><b>alimente this.pathPackage</b>.</li>
	 * </ul>
	 * <br/>
	 */
	public GenerateurMetierTest() {
		
		super();
						
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void genererHook() throws Exception {
		/**/
	
	} // Fin de genererHook()._____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void alimenterPathPackage() throws Exception {
		
		final Path pathPackagePath 
			= PATH_TEST_JAVA.resolve(getPathRelMetier());
		
		this.pathPackage = pathPackagePath.toString();
		
	} // Fin de alimenterPathPackage().____________________________________
	

		
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void alimenterNomSimpleConcreteClass(
			final String pNomObjetMetier) {
		
		this.nomSimpleConcreteClass = nomClassMetier + "Test";
		
	} // Fin de alimenterNomSimpleConcreteClass(...).______________________
	

	
} // FIN DE LA CLASSE GenerateurMetierTest.----------------------------------
