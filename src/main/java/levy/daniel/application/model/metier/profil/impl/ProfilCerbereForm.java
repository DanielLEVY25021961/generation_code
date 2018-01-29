package levy.daniel.application.model.metier.profil.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * CLASSE CONCRETE <b>ProfilCerbereForm</b> :<br/>
 * <p>
 * <span style="text-decoration: underline;">CONCEPT MODELISE</span>
 * </p>
 * <p>
 * <b>ProfilCerbereForm</b> modélise un un <i>concept</i> 
 * de <b>Profil</b>, c'est à dire 
 * <b></b> ou  <b></b> 
 * qui  <i></i> <b></b>.
 * </p>
 * 
 * <ul>
 * <p>
 * <span style="text-decoration: underline;">
 * HERITE DE :
 * </span>
 * </p>
 * <li><b>AbstractProfil</b>.</li>
 * </ul>
 * 
 * <ul>
 * <p>
 * <span style="text-decoration: underline;">
 * ATTRIBUTS :
 * </span>
 * </p>
 * <li><b>id</b> pour la mise en base.</li>
 * 
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
 * @since 22 janvier 2018
 *
 */
public class ProfilCerbereForm {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_PROFIL_CERBERE_FORM : String :<br/>
	 * "Classe ProfilCerbereForm".<br/>
	 */
	public static final String CLASSE_PROFIL_CERBERE_FORM 
		= "Classe ProfilCerbereForm";




	/**
	 * profilString : String :<br/>
	 * profilString du Profil.<br/>
	 * <ul>
	 * <li>RG_PROFIL_PROFILSTRING_RENSEIGNE_01 : 
	 * le profilString du Profil doit être renseigné (obligatoire).</li>
	 * <li>RG_PROFIL_PROFILSTRING_NOMENCLATURE_02 : 
	 * le profilString du Profil doit respecter un ensemble fini de valeurs (nomenclature).</li>
	 * </ul>
	 */
	protected String profilStringString;


	/**
	 * porteeProfil : String :<br/>
	 * porteeProfil du Profil.<br/>
	 * <ul>
	 * <li>RG_PROFIL_PORTEEPROFIL_RENSEIGNE_01 : 
	 * le porteeProfil du Profil doit être renseigné (obligatoire).</li>
	 * </ul>
	 */
	protected String porteeProfilString;


	/**
	 * restrictionProfil : String :<br/>
	 * restrictionProfil du Profil.<br/>
	 */
	protected String restrictionProfilString;


	/**
	 * serialVersionUID : long :<br/>
	 * serialVersionUID = 1L.<br/>
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(ProfilCerbereForm.class);


	// *************************METHODES************************************/


	/**
	 * method CONSTRUCTEUR ProfilCerbereForm() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ProfilCerbereForm() {

		this(null, null, null);

	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________



	/**
	 * method CONSTRUCTEUR ProfilCerbereForm(
	 *  String pProfilStringString
	 * , String pPorteeProfilString
	 * , String pRestrictionProfilString) :<br/>
	 * <ul>
	 * <li>CONSTRUCTEUR COMPLET.</li>
	 * <li>SANS ID en base.</li>
	 * </ul>
	 * 
	 * @param pProfilStringString : String : 
	 * profilStringString du ProfilCerbereForm.<br/>
	 * @param pPorteeProfilString : String : 
	 * porteeProfilString du ProfilCerbereForm.<br/>
	 * @param pRestrictionProfilString : String : 
	 * restrictionProfilString du ProfilCerbereForm.<br/>
	 */
	public ProfilCerbereForm(
			final String pProfilStringString
				, final String pPorteeProfilString
					, final String pRestrictionProfilString) {

		super();

		this.profilStringString = pProfilStringString;
		this.porteeProfilString = pPorteeProfilString;
		this.restrictionProfilString = pRestrictionProfilString;

	} // Fin de CONSTRUCTEUR COMPLET.______________________________________



} // FIN DE LA CLASSE ProfilCerbereForm.-------------------------------------









