package levy.daniel.application.apptechnic.generationcode.ecriveurs.test;

import java.io.File;
import java.util.List;

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
public class EcriveurMetierTest extends AbstractEcriveurFichiersJava { // NOPMD by daniel.levy on 27/11/18 11:40

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
	 * <br/>
	 * ne fait rien si le test a déjà été écrit.<br/>
	 * <br/>
	 */
	@Override
	protected void ecrireCodeGenerique(
			final File pFile
				, final IGenerateur pGenerateur) throws Exception {
		
		final String ligneIdentifiant = "public class";
		
		/* ne fait rien si le test a déjà été écrit. */
		if (existLigneCommencant(pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}
		
		/* lecture du Template. */
		final List<String> templateList 
			= this.lireTemplate("test_java/test_junit.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
					templateList
						, VARIABLE_PATH_REL_CONCEPT_IMPL
							, this.pathRelConceptImplString);
		
		final List<String> listSubst2 
		= this.substituerVariablesDansLigne(
				listSubst1
					, VARIABLE_PATH_REL_CONCEPT
						, this.pathRelConceptString);
		
		final List<String> listSubst3 
		= this.substituerVariablesDansLigne(
				listSubst2
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);
		
		final List<String> listSubst4 
		= this.substituerVariablesDansLigne(
				listSubst3
					, VARIABLE_NOM_CLASSE_METIER
						, this.nomClassMetier);
		
		this.ecrireCode(listSubst4, pFile);

	} // Fin de ecrireCodeGenerique(...).__________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * retourne pour un EcriveurMetierTest : 
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
	 * retourne pour un EcriveurMetierTest : 
	 * "Classe EcriveurMetierTest".<br/>
	 * <br/>
	 */
	@Override
	protected final String fournirNomClasse() {
		return CLASSE_ECRIVEUR_METIER_TEST;
	} // Fin de fournirNomClasse().________________________________________




} // FIN DE LA CLASSE EcriveurMetierTest.------------------------------------
