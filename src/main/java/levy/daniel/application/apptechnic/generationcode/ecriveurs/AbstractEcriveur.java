package levy.daniel.application.apptechnic.generationcode.ecriveurs;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.text.WordUtils;

import levy.daniel.application.apptechnic.generationcode.GestionnaireProjet;
import levy.daniel.application.apptechnic.generationcode.IGenerateur;
import levy.daniel.application.apptechnic.generationcode.generationfichiersjava.generationmetier.generationobjetmetiersimple.GenerateurMetierToutAbstract;


/**
 * CLASSE ABSTRAITE <b>AbstractEcriveur</b> :<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 28 janv. 2018
 *
 */
public abstract class AbstractEcriveur implements IEcriveur {

	// ************************ATTRIBUTS************************************/
	/**
	 * CLASSE_ABSTRACT_ECRIVEUR : String :<br/>
	 * "Classe AbstractEcriveur".<br/>
	 */
	public static final String CLASSE_ABSTRACT_ECRIVEUR 
		= "Classe AbstractEcriveur";


	/**
	 * PATH_MAIN_JAVA : Path :<br/>
	 * <ul>
	 * <li><b>path du répertoire de la RACINE des sources .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>PATH_MAIN_JAVA = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java
	 * </code></li>
	 * </ul>
	 */
	protected static final Path PATH_MAIN_JAVA 
		= GestionnaireProjet.getPathMainJava();
	

	/**
	 * generateurCode : IGenerateur :<br/>
	 * Generateur de code 
	 * (GenerateurMetierToutAbstract, GenerateurDaoToutAbstract, ...).<br/>
	 */
	protected transient IGenerateur generateurCode;

	
	/**
	 * nomPackage : String :<br/>
	 * <ul>
	 * <li><b>nom du package à créer dans chaque SOUS-COUCHE</b> 
	 * en fonction du concept à modéliser dans le générateur 
	 * (model/metier/profil, model/dao/metier/profil, 
	 * model/services/metier/profil, ...pour un concept Profil).</li>
	 * <li>passé en paramètre au générateur.</li>
	 * <li>par exemple : <br/>
	 * <code>profil</code>.</li>
	 * <li>RG-CONCEPT-01 : le conceptModelise est déduit 
	 * du nomPackage passé en paramètre.</li>
	 * </ul>
	 */
	protected transient String nomPackage;
	
	
	/**
	 * conceptModelise : String :<br/>
	 * <ul>
	 * <li><b>concept modélisé par ce générateur</b>.</li>
	 * <li><b>nom du package à créer dans chaque SOUS-COUCHE</b> 
	 * (model/metier, model/dao/metier, model/services/metier, ...) 
	 * avec une <i>majuscule</i> en première position.</li>
	 * <li>Par exemple : <br/>
	 * <code>Profil</code> pour le nomPackage "profil".</li>
	 * <li>RG-CONCEPT-01 : le conceptModelise est déduit 
	 * du nomPackage passé en paramètre.</li>
	 * </ul>
	 */
	protected transient String conceptModelise;
	

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
	= LogFactory.getLog(AbstractEcriveur.class);
	

	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR AbstractEcriveur() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractEcriveur() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void ecrireCode(
			File pFile, GenerateurMetierToutAbstract pGenerateurMetierToutAbstract);


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherListeString(
			final List<String> pListe) {
					
			/* Retourne null si pListe est null. */
			if (pListe == null) {
				return null;
			}
			
			final StringBuilder stb = new StringBuilder();
			
			for (final String ligne : pListe) {
				
				stb.append(ligne);
				stb.append(NEWLINE);
				
			}
			
			return stb.toString();
			
	} // Fin de afficherListeString(
	 // List<String> pListe).______________________________________________

	
	
	/**
	 * method mettrePremiereEnMajusculeEtGarder(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Met la première lettre de chaque mots séparés 
	 * par des espaces en majuscule.</li>
	 * <li>Conserve les autres lettres de chaque mots séparés 
	 * par un espace.</li>
	 * <li>Par exemple : "premier" est transformé en "Premier".</li>
	 * <li>"PREMIER" est transformé en "PREMIER".</li>
	 * <li>WordUtils.capitalize("i am FINE") = "I Am FINE"</li>
	 * </ul>
	 * retourne null si pString == null.<br/>
	 * <br/>
	 * 
	 *
	 * @param pString : String. <br/>
	 * 
	 * @return : String.<br/>
	 */
	public final String mettrePremiereEnMajusculeEtGarder(
			final String pString) {
		
		/* retourne null si pString == null. */
		if (pString == null) {
			return null;
		}
		
		return WordUtils.capitalize(pString);
		
	} // Fin de mettrePremiereEnMajusculeEtGarder(...).____________________
	

	
	/**
	 * method getPathMainJava() :<br/>
	 * <ul>
	 * <li>Getter du <b>path du répertoire de la 
	 * RACINE des sources .java</b>
	 * dans le projet Eclipse dont on va générer le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>PATH_MAIN_JAVA = pathWorkspace 
	 * + /nomProjet + /nomRepertoireSrc + /pathRelMainJava.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * projet_users/src/main/java
	 * </code></li>
	 * </ul>
	 *
	 * @return PATH_MAIN_JAVA : Path.<br/>
	 */
	public static Path getPathMainJava() {
		return PATH_MAIN_JAVA;
	} // Fin de getPathMainJava()._________________________________________


	
	/**
	 * method getConceptModelise() :<br/>
	 * <ul>
	 * <li>Getter du <b>concept modélisé par ce générateur</b>.</li>
	 * <li><b>nom du package à créer dans chaque SOUS-COUCHE</b> 
	 * (model/metier, model/dao/metier, model/services/metier, ...) 
	 * avec une <i>majuscule</i> en première position.</li>
	 * <li>Par exemple : <br/>
	 * <code>Profil</code> pour le nomPackage "profil".</li>
	 * <li>RG-CONCEPT-01 : le conceptModelise est déduit 
	 * du nomPackage passé en paramètre.</li>
	 * </ul>
	 * 
	 * @return conceptModelise : String.<br/>
	 */
	public final String getConceptModelise() {
		return this.conceptModelise;
	} // Fin de getConceptModelise().______________________________________


	
	/**
	 * method getNomPackage() :<br/>
	 * <ul>
	 * <li>Getter du <b>nom du package à créer dans chaque SOUS-COUCHE</b> 
	 * en fonction du concept à modéliser dans le générateur 
	 * (model/metier/profil, model/dao/metier/profil, 
	 * model/services/metier/profil, ...pour un concept Profil).</li>
	 * <li>passé en paramètre au générateur.</li>
	 * <li>par exemple : <br/>
	 * <code>profil</code>.</li>
	 * <li>RG-CONCEPT-01 : le conceptModelise est déduit 
	 * du nomPackage passé en paramètre.</li>
	 * </ul>
	 * 
	 * @return nomPackage : String.<br/>
	 */
	public final String getNomPackage() {
		return this.nomPackage;
	} // Fin de getNomPackage().___________________________________________



} // FIN DE LA CLASSE AbstractEcriveur.--------------------------------------
