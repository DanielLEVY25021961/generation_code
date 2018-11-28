/**
 * CLASSE package-info :<br/>
 * Ce package contient toutes les classes <b>VUES</b> 
 * d'une application <b>DESKTOP</b> respectant le modèle MVC.<br/>
 * La couche VUES intercepte les actions de l'UTILISATEUR 
 * et dialogue avec les CONTROLLERS chargés d'interroger 
 * la logique METIER (SERVICES).<br/>
 * Le rôle d'une Vue est de prendre en compte les actions de 
 * l'utilisateur et de présenter à l'utilisateur les résultats 
 * fournis par la logique métier.<br/>
 * <br/>
 * 
 * <div>
 * <img src="../../../../../../../../javadoc/images/architecture/architecture_n_tiers.png" 
 * alt="architecure n-tiers" border="1" align="center" />
 * </div>
 * <br/>
 * 
 * 
 * <br/>
 * <p>
 * <b><span style="text-decoration: underline;">
 * COUCHE VUES :
 * </span></b>
 * </p>
 * <div>
 * <p>
 * La couche vue est <b>sensible à la technologie (desktop ou web)</b>
 * </p>
 * <p>On peut avoir des JSP, des Thymeleaf, ... en mode web MVC, 
 * et des Vues Swing ou Javafx en mode standalone (desktop).</p>
 * <p>On a donc deux sous-couches : 
 * <ul>
 * <li><b>desktop</b></li>
 * <li><b>web</b></li>
 * </ul>
 * </p>
 * </div>
 * 
 * <div>
 * <img src="../../../../../../../../javadoc/images/arboresceurprojet/couche_vues.png" 
 * alt="couche VUES" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <br/>
 * 
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
 * @since 3 nov. 2018
 *
 */
package levy.daniel.application.vues.desktop;
