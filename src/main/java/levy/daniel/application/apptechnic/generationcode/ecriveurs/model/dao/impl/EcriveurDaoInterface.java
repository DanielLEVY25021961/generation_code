package levy.daniel.application.apptechnic.generationcode.ecriveurs.model.dao.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveurFichiersJavaDetaille;

/**
 * CLASSE <b>EcriveurDaoInterface</b> :<br/>
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
 * @since 15 févr. 2018
 *
 */
public class EcriveurDaoInterface 
				extends AbstractEcriveurFichiersJavaDetaille {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ECRIVEUR_DAO_INTERFACE : String :<br/>
	 * "Classe EcriveurDaoInterface".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_DAO_INTERFACE 
		= "Classe EcriveurDaoInterface";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
				= LogFactory.getLog(EcriveurDaoInterface.class);
	

	// *************************METHODES************************************/
	
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
	protected String creerLigneDeclaration(File pFile) {
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
	 */
	@Override
	protected void creerAttributId(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocAttribut(List<String> pListe, String pNomAttribut, String pTypeAttribut)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeAttribut(List<String> pListe, String pNomAttribut, String pTypeAttribut) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void ecrireCodeConstructeurCompletBase(File pFile) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocCompareTo(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeCompareTo(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifiantCompareTo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocClone(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeClone(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocToString(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeToString(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetEnTeteCsv(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetEnTeteCsv(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocToStringCsv(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeToStringCsv(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetEnTeteColonne(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetEnTeteColonne(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetValeurColonne(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetValeurColonne(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetId(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerEntityGetId(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetId(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocSetId(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeSetId(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetter(String pNomAttribut, String pTypeAttribut, List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeEntityGetter(String pNomAttribut, String pTypeAttribut, List<String> pListeGetter)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetter(String pNomAttribut, String pTypeAttribut, List<String> pListeGetter)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifianteGetter(String pNomAttribut, String pTypeAttribut) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocSetter(String pNomAttribut, String pTypeAttribut, List<String> pListeSetter)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeSetter(String pNomAttribut, String pTypeAttribut, List<String> pListeSetter)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifianteSetter(String pNomAttribut, String pTypeAttribut) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * retourne pour un EcriveurDaoInterface : 
	 * "L'INTERFACE ".<br/>
	 * <br/>
	 */
	@Override
	protected final String fournirTypeFichierJava() {
		return "L'INTERFACE ";		
	} // Fin de fournirTypeFichierJava(...)._______________________________

	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * retourne pour un EcriveurDaoInterface : 
	 * "Classe EcriveurDaoInterface".<br/>
	 * <br/>
	 */
	@Override
	protected final String fournirNomClasse() {
		return CLASSE_ECRIVEUR_DAO_INTERFACE;
	} // Fin de fournirNomClasse().________________________________________

	
	
} // FIn DE LA CLASSE EcriveurDaoInterface.----------------------------------
