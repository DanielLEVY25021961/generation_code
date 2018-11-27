package levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationtests;

import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.GestionnaireProjet;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.test.EcriveurMetierTest;
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
public class GenerateurMetierTest  // NOPMD by daniel.levy on 27/11/18 11:40
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
	 * <li><b>alimente this.ecriveurConcreteClass</b>.</li>
	 * </ul>
	 * <br/>
	 */
	public GenerateurMetierTest() {
		
		super();
		
		this.ecriveurConcreteClass = new EcriveurMetierTest();
						
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void genererHook() throws Exception {
		
		/* génère le code de la classe concrète 
		 * dans this.concreteClass. */
		this.ecriveurConcreteClass.ecrireCode(this.concreteClass, this);
	
	} // Fin de genererHook()._____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void alimenterPathPackage() throws Exception {
		
		this.pathCouche = GestionnaireProjet.getPathModelTestJava();
		
		final String pathBaseAntislash 
			= GestionnaireProjet.getPathModelTestJavaString();
		
		this.pathCoucheString = retournerPathGenerique(pathBaseAntislash);
		
		this.fileCouche = this.pathCouche.toFile();
		
		this.pathRelCouche = PATH_TEST_JAVA.relativize(this.pathCouche);
		
		this.pathRelCoucheJavaString 
			= remplacerAntiSlashparPoint(this.pathRelCouche.toString());
		
		final Path pathPackagePath 
			= PATH_TEST_JAVA.resolve(getPathRelMetier());
		
		this.pathPackageString 
			= retournerPathGenerique(pathPackagePath.toString());
		
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
