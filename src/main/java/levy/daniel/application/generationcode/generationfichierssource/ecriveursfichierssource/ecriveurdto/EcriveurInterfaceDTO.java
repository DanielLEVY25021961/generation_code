package levy.daniel.application.generationcode.generationfichierssource.ecriveursfichierssource.ecriveurdto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.generationcode.generationfichierssource.impl.GenerateurDTO;

/**
 * CLASSE EcriveurInterfaceDTO :<br/>
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
 * @since 18 juin 2019
 *
 */
public class EcriveurInterfaceDTO {

	// ************************ATTRIBUTS************************************/

	private GenerateurDTO generateur;
	
	private Map<Integer, List<String>> contenuMap 
		= new LinkedHashMap<Integer, List<String>>();
	
	/**
	 * contenu de la ligne package.
	 */
	private List<String> lignePackage = new ArrayList<String>();
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(EcriveurInterfaceDTO.class);

	// *************************METHODES************************************/

	
	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 * 
	 * @param pGenerateur : GenerateurDTO
	 */
	public EcriveurInterfaceDTO(
			final GenerateurDTO pGenerateur) {
		
		super();
		
		this.generateur = pGenerateur;
		
	} // Fin du CONSTRUCTEUR COMPLET.______________________________________


	
	private void fabriquerContenu() {
		
		this.fabriquerLignePackage();
	}


	
	/**
	 * fabrique la ligne package (1ère ligne) de l'INTERFACE du DTO.
	 */
	private void fabriquerLignePackage() {
		
		if (this.generateur == null) {
			return;
		}
		
		if (this.generateur.getProjetCiblePath() == null) {
			return;
		}
		
		return;
	}
	
	
	
} // FIN DE LA CLASSE EcriveurInterfaceDTO.----------------------------------
