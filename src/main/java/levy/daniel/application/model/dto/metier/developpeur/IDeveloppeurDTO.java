package levy.daniel.application.model.dto.metier.developpeur;

import java.io.Serializable;

/**
 * INTERFACE IDeveloppeurDTO :<br/>
 * Interface <b>Data Transfer Object (DTO)</b> 
 * relative au concept de <b>Developpeur</b>.<br/>
 * Les DTO ne servent qu'à <b>véhiculer de l'information</b> 
 * entre les couches VUE, CONTROLLER et MODEL.<br/>
 * <br/>
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
