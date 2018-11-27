package levy.daniel.application.model.services.utilitaires.generateurprojet;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * INTERFACE IGenerateurProjetService :<br/>
 * Abstraction factorisant les comportements des 
 * GenerateurProjetService concrets.<br/>
 * GenerateurProjetService est chargé d'écrire 
 * une arborescence sur disque.<br/>
 * <br/>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/generateurprojet/generateurprojet_service.png" 
 * alt="generateurprojet SERVICE" border="1" align="center" />
 * </div>
 * <br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>
 *  // Path du projet cible.<br/>
 * projetCiblePath = Paths.get("D:/Donnees/toto");<br/>
 *  // INSTANCIATION D'UN GenerateurProjetService.<br/>
 * <b>IGenerateurProjetService generateur = new GenerateurProjetService();</b><br/>
 *  // GENERATION DE L'ARBORESCENCE DANS LE PROJET CIBLE.<br/>
 * <b>generateur.generer(projetCiblePath);</b><br/>
 * </code>
 * <br/>
 * 
 * - Mots-clé :<br/>
 * écriture fichier sur disque, ecrire sur disque, ecriture fichier, <br/>
 * ecriture répertoire sur disque, ecrire repertoire sur disque, <br/>
 * Files.createDirectories(path);<br/>
 * Création arborescence sur disque, ecriture arborescence sur disque,<br/>
 * écriture arborescence sur disque,
 * création récursive de répertoires, recursif, récursif,<br/>
 * obtenir Path à partir de String, Paths.get(String), <br/>
 * génération arborescence dans projet cible,<br/>
 * generation arborescence dans projet cible,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 26 nov. 2018
 *
 */
public interface IGenerateurProjetService {

	/**
	 * <b>Ecrit sur disque une arborescence de projet 
	 * MAVEN SIMPLE (sans artefact)</b>.<br/>
	 * <ul>
	 * <li>Ne permet PAS de spécifier un GroupId MAVEN 
	 * pour le projet cible à générer.<br/> 
	 * Utilise le GroupId MAVEN par défaut GROUPID_PAR_DEFAUT 
	 * dans ArboresceurProjetCible (levy.daniel.application).</li>
	 * <li>utilise un ArboresceurProjetCible pour obtenir 
	 * l'arborescence de projet à créer.</li>
	 * </ul>
	 * - ne fait rien si pProjetCiblePath == null.<br/>
	 * - ne fait rien si le projet cible n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pProjetCiblePath : Path : 
	 * path du projet cible dans lequel générer 
	 * une infrastructure de projet.<br/>
	 * 
	 * @throws IOException
	 */
	void generer(Path pProjetCiblePath) throws IOException;

	
	
	/**
	 * <b>Ecrit sur disque une arborescence de projet 
	 * MAVEN SIMPLE (sans artefact)</b>.<br/>
	 * <ul>
	 * <li>Permet de spécifier un GroupId MAVEN 
	 * pour le projet cible à générer.</li>
	 * <li>utilise un ArboresceurProjetCible pour obtenir 
	 * l'arborescence de projet à créer.</li>
	 * </ul>
	 * - ne fait rien si pProjetCiblePath == null.<br/>
	 * - ne fait rien si le projet cible n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pProjetCiblePath : Path : 
	 * path du projet cible dans lequel générer 
	 * une infrastructure de projet.<br/>
	 * @param pGroupId : String : 
	 * GroupId à appliquer dans le projet cible.<br/> 
	 * GroupId par défaut GROUPID_PAR_DEFAUT dans 
	 * ArboresceurProjetCible si pGroupId est blank.<br/>
	 * 
	 * @throws IOException 
	 */
	void generer(Path pProjetCiblePath, String pGroupId) 
											throws IOException;

	
	
	/**
	 * Fournit une String pour l'affichage d'une List&lt;Path&gt;.<br/>
	 * - retourne null si pArborescence == null.<br/>
	 * <br/>
	 *
	 * @param pArborescence : List&lt;Path&gt;.<br/> 
	 * 
	 * @return : String.<br/>
	 */
	String afficherArborescence(List<Path> pArborescence);

	
	
} // FIN DE L'INTERFACE IGenerateurProjetService.----------------------------