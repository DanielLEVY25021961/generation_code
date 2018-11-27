package levy.daniel.application.model.dto.metier.country;

import java.io.Serializable;

/**
 * INTERFACE ICountryDTO :<br/>
 * DTO servant à encapsuler le contenu d'une VUE.<br/>
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
 * @since 11 nov. 2018
 *
 */
public interface ICountryDTO extends Serializable {
	

	
	/**
	 * Getter de l'identifiant en base sous forme de String.<br/>
	 *
	 * @return  this.idString : String.<br/>
	 */
	String getIdString();

	
	
	/**
	* Setter de l'identifiant en base sous forme de String.<br/>
	*
	* @param pIdString : String : 
	* valeur à passer à this.idString.<br/>
	*/
	void setIdString(String pIdString);

	
	
	/**
	 * Getter de nameString.<br/>
	 *
	 * @return : String : this.nameString.<br/>
	 */
	String getNameString();
	
	

	/**
	 * Setter de nameString.<br/>
	 *
	 * @param pNameString : String : 
	 * valeur à passer à this.nameString.<br/>
	 */
	void setNameString(String pNameString);
	
	

	/**
	 * Getter de capitalString.<br/>
	 *
	 * @return : String : this.capitalString.<br/>
	 */
	String getCapitalString();
	
	

	/**
	 * Setter de capitalString.<br/>
	 *
	 * @param pCapitalString : String : 
	 * valeur à passer à this.capitalString.<br/>
	 */
	void setCapitalString(String pCapitalString);
	
		
	
} // FIN DE L'INTERFACE ICountryDTO.-----------------------------------------
