package levy.daniel.application.model.dto.metier.country.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.country.ICountryDTO;
import levy.daniel.application.model.metier.country.ICountry;


/**
 * CLASSE CountryDTO :<br/>
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
public class CountryDTO implements ICountryDTO {
	
	// ************************ATTRIBUTS************************************/

	/**
	 * .<br/>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * "Classe CountryDTO".<br/>
	 */
	public static final String CLASSE_COUNTRY_DTO 
		= "Classe CountryDTO";
	
	/**
	 * POINT_VIRGULE : char :<br/>
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';
	
	
	/**
	 * VIRGULE_ESPACE : String :<br/>
	 * ", ".<br/>
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	
	/**
	 * NULL : String :<br/>
	 * "null".<br/>
	 */
	public static final String NULL = "null";
	
	
	/**
	 * identifiant en base sous forme de String.
	 */
	private String idString;

	/**
	 * nom du Country sous forme de String.
	 */
	private String nameString;
	
	/**
	 * nom de la capitale du Country sous forme de String.
	 */
	private String capitalString;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(CountryDTO.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public CountryDTO() {
		this(null, null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 *
	 * @param pIdString : String : 
	 * identifiant en base sous forme de String. 
	 * @param pNameString : String : 
	 * nom du Country sous forme de String.
	 * @param pCapitalString : String : 
	 * nom de la capitale du Country sous forme de String.
	 */
	public CountryDTO(
			final String pIdString
				, final String pNameString
					, final String pCapitalString) {
		
		super();
		
		this.idString = pIdString;
		this.nameString = pNameString;
		this.capitalString = pCapitalString;
		
	} // Fin du CONSTRUCTEUR COMPLET.______________________________________


	
	 /**
	 * CONTROLEUR TRANSFORMATEUR.<br/>
	 * <ul>
	 * <li>Transforme un OBJET METIER en contenu de VUE (DTO) 
	 * pour affichage.</li>
	 * </ul>
	 *
	 * @param pObject : ICountry : Objet métier.<br/>
	 */
	public CountryDTO(
			final ICountry pObject) {
		
		super();
		
		if (pObject != null) {
			
			if (pObject.getId() != null) {
				this.idString = String.valueOf(pObject.getId());
			} else {
				this.idString = null;
			}
			
			this.nameString = pObject.getName();
			this.capitalString = pObject.getCapital();
			
		}
		
	} // Fin de CONTROLEUR TRANSFORMATEUR._________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("CountryDTO [");
		
		builder.append("idString=");
		if (this.idString != null) {
			builder.append(this.idString);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);

		builder.append("nameString=");
		if (this.nameString != null) {			
			builder.append(this.nameString);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("capitalString=");
		if (this.capitalString != null) {			
			builder.append(this.capitalString);
		} else {
			builder.append(NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIdString() {
		return this.idString;
	} // Fin de getIdString()._____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIdString(
			final String pIdString) {
		this.idString = pIdString;
	} // Fin de setIdString(...).__________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNameString() {
		return this.nameString;
	} // Fin de getNameString().___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNameString(
			final String pNameString) {
		this.nameString = pNameString;
	} // Fin de setNameString(...).________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCapitalString() {
		return this.capitalString;
	} // Fin de getCapitalString().________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCapitalString(
			final String pCapitalString) {
		this.capitalString = pCapitalString;
	} // Fin de setCapitalString(...)._____________________________________

	
	
} // FIN DE LA CLASSE CountryDTO.--------------------------------------------
