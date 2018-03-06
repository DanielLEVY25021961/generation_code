package levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationtests;

import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.GestionnaireProjet;
import levy.daniel.application.apptechnic.generationcode.ecriveurs.test.EcriveurDaoTest;
import levy.daniel.application.apptechnic.generationcode.generationfichiersjava.AbstractGenerateurClasseSeule;

/**
 * CLASSE <b>GenerateurDaoTest</b> :<br/>
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
 * @since 5 mars 2018
 *
 */
public class GenerateurDaoTest 
						extends AbstractGenerateurClasseSeule {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GenerateurDaoTest.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR GenerateurDaoTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li><b>alimente this.ecriveurConcreteClass</b>.</li>
	 * </ul>
	 * <br/>
	 */
	public GenerateurDaoTest() {
		
		super();
		
		this.ecriveurConcreteClass = new EcriveurDaoTest();
						
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
		
		this.pathCouche = GestionnaireProjet.getPathDaoTestJava();
		
		final String pathBaseAntislash 
			= GestionnaireProjet.getPathDaoTestJavaString();
		
		this.pathCoucheString = retournerPathGenerique(pathBaseAntislash);
		
		this.fileCouche = this.pathCouche.toFile();
		
		this.pathRelCouche = PATH_TEST_JAVA.relativize(this.pathCouche);
		
		this.pathRelCoucheJavaString 
			= remplacerAntiSlashparPoint(this.pathRelCouche.toString());
		
		final Path pathPackagePath 
			= PATH_TEST_JAVA.resolve(getPathRelCoucheDaoMetier());
		
		this.pathPackageString 
			= retournerPathGenerique(pathPackagePath.toString());
		
	} // Fin de alimenterPathPackage().____________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void alimenterNomSimpleConcreteClass(
			final String pNomObjetMetier) {
		
		this.nomSimpleConcreteClass = nomConcreteDao + "Test";
		
	} // Fin de alimenterNomSimpleConcreteClass(...).______________________
	

	
} // FIN DE LA CLASSE GenerateurDaoTest.-------------------------------------
