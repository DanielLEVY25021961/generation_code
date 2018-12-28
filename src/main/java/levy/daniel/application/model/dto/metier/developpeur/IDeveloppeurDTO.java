package levy.daniel.application.model.dto.metier.developpeur;

import java.io.Serializable;

import levy.daniel.application.model.metier.developpeur.impl.Developpeur;

/**
 * INTERFACE IDeveloppeurDTO :<br/>
 * 
 * <p>
 * DTO ABSTRAIT pour les {@link Developpeur}.
 * </p>
 * 
 * 
 * <ul>
 * <li>Les DTO ne servent qu'à véhiculer de l'information 
 * entre les couches VUE, CONTROLLER et MODEL.</li>
 * <li>le DTO ne comprend <b>que des attributs typés String</b>.</li>
 * <li>la VUE alimente le DTO avec les valeurs saisies par l'utilisateur.</li>
 * <li>le DTO sert ensuite de conteneur de données 
 * et est envoyé par la VUE vers le CONTROLLER.</li>
 * </ul>
 * 
 * <p>
 * <b><span style="text-decoration: underline;">
 * Diagramme des classes de DeveloppeurDTO :
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../javadoc/images/model/dto/metier/developpeur/diagramme_classes_DeveloppeurDTO.png" 
 * alt="diagramme de classes de DeveloppeurDTO" border="1" align="center" />
 * </p>
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
 * @author adminLocal Lévy
 * @version 1.0
 * @since 12 nov. 2018
 *
 */
public interface IDeveloppeurDTO extends Serializable {
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();


	
	/**
	 * method getIdString() :<br/>
	 * Getter de l'ID en base sous forme de String.<br/>
	 * <br/>
	 *
	 * @return this.idString : String.<br/>
	 */
	String getIdString();


	
	/**
	 * method setIdString(String pIdString) :<br/>
	 * Setter de l'ID en base sous forme de String.<br/>
	 * <br/>
	 *
	 * @param pIdString : String : 
	 * valeur à passer à this.idString.<br/>
	 */
	void setIdString(String pIdString);


	
	/**
	 * Getter du nomString du développeur.<br/>
	 *
	 * @return nomString : String.<br/>
	 */
	String getNomString();


	
	/**
	 * Setter du nomString du développeur..<br/>
	 *
	 * @param pNomString : String : 
	 * valeur à passer à this.nomString.<br/>
	 */
	void setNomString(String pNomString);


	
	/**
	 * Getter de anneesExperienceString.<br/>
	 *
	 * @return anneesExperienceString : String.<br/>
	 */
	String getAnneesExperienceString();

	

	/**
	 * Setter de anneesExperienceString.<br/>
	 *
	 * @param pAnneesExperienceString : String : 
	 * valeur à passer à this.anneesExperienceString.<br/>
	 */
	void setAnneesExperienceString(String pAnneesExperienceString);

	

} // FIN DE L'INTERFACE IDeveloppeurDTO.-----------------------------------
