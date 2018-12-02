/**
 * CLASSE package-info :<br/>
 * Package contenant toutes les classes nécessaires 
 * à l'<b>extraction et à la copie 
 * de l'arborescence et des fichiers simples contenus 
 * sous un répertoire (racine origine)</b>.<br/>
 * <ul>
 * <li>Le contenu recopié contient l'arborescence (<b>répertoires</b>) 
 * <i>sous</i> la racine origine <b>et les fichiers simples</b>.</li>
 * <li><b>L'ensemble du contenu sous le répertoire origine 
 * est recopié sous le répertoire destination</b>.</li>
 * <li>La racine origine n'est pas recopiée. Seul son contenu l'est.</li>
 * <li>Ne recopie un fichier de l'arborescence que si il n'existe 
 * pas déjà sous la destination.</li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * PRINCIPE DE FONCTIONNEMENT de copieurContenuRepertoire :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/copieurcontenurepertoire/principe_fonctionnement_copieurContenuRepertoire.png" 
 * alt="principe de fonctionnement de copieurContenuRepertoire" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE CLASSES de copieurcontenurepertoire :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/copieurcontenurepertoire/diagramme_classes_copieurContenuRepertoire.png" 
 * alt="diagramme de classes de copieurContenuRepertoire" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE SEQUENCE de la méthode <b>copierContenu(File racineOrigine, String cheminDestination)</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/copieurcontenurepertoire/methode_copieurContenuRepertoire.png" 
 * alt="diagramme de séquence de copierArborescence(...)" border="1" align="center" />
 * </div>
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
 * @since 1 déc. 2018
 *
 */
package levy.daniel.application.model.services.utilitaires.copieurcontenurepertoire;