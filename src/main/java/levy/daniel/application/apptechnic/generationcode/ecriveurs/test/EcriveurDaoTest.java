package levy.daniel.application.apptechnic.generationcode.ecriveurs.test;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveurFichiersJavaDetaille;

/**
 * class EcriveurDaoTest :<br/>
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
public class EcriveurDaoTest 
		extends AbstractEcriveurFichiersJavaDetaille {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * CLASSE_ECRIVEUR_DAO_TEST : String :<br/>
	 * "Classe EcriveurDaoTest".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_DAO_TEST 
		= "Classe EcriveurDaoTest";
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
				= LogFactory.getLog(EcriveurDaoTest.class);
	
	
	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR EcriveurDaoTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public EcriveurDaoTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<String> creerLignesImport() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<String> creerLignesJavaDoc(File pFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirDebutJavaDoc() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<String> creerLignesEntity(File pFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String creerLigneDeclaration(File pFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirDebutDeclaration() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void ecrireCodeHook(File pFile) {
		// TODO Auto-generated method stub

	}

	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * retourne pour un EcriveurDaoTest : 
	 * "LA CLASSE ".<br/>
	 * <br/>
	 */
	@Override
	protected final String fournirTypeFichierJava() {	
		return "LA CLASSE ";		
	} // Fin de fournirTypeFichierJava(...)._______________________________
	

	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * retourne pour un EcriveurDaoTest : 
	 * "Classe EcriveurMetierTest".<br/>
	 * <br/>
	 */
	@Override
	protected final String fournirNomClasse() {
		return CLASSE_ECRIVEUR_DAO_TEST;
	} // Fin de fournirNomClasse().________________________________________


	
} // FIN DE LA CLASSE EcriveurDaoTest.---------------------------------------
