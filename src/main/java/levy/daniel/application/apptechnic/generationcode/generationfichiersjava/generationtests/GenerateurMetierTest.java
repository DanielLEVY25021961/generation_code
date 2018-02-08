package levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationtests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	protected void genererFichiersJava() throws IOException {
		
		final Path pathRelatif 
		= Paths.get(getPathRelConceptImpl().toString());
	
		final Path pathPackagePath 
			= PATH_TEST_JAVA.resolve(pathRelatif);
		
		this.pathPackage = pathPackagePath.toString();

		
		this.alimenterAttributs();
		
		this.creerConcreteClasseVide();

	} // Fin de genererFichiersJava()._____________________________________

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void alimenterAttributs() {
		
		this.nomSimpleInterface = null;
		this.nomSimpleAbstractClass = null;
		this.nomSimpleConcreteClass = nomClassMetier + "Test";

	} // Fin de alimenterAttributs().______________________________________
	

	
	/**
	 * method creerConcreteClasseVide() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @throws IOException
	 */
	private void creerConcreteClasseVide() throws IOException {
		
		final Path pathPackageTest 
			= Paths.get(this.pathPackage);
		
		final String nomFichierJava 
			= this.nomSimpleConcreteClass + ".java";
		
		final Path pathConcreteClass 
			= pathPackageTest.resolve(nomFichierJava);
		
		final File fileConcreteClass = pathConcreteClass.toFile();
		
		if (!fileConcreteClass.exists()) {
			Files.createFile(pathConcreteClass);
		}
		
	} // Fin de genererConcreteClasseVide()._______________________________


	
} // FIN DE LA CLASSE GenerateurMetierTest.----------------------------------
