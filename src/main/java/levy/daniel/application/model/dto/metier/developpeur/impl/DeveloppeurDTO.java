package levy.daniel.application.model.dto.metier.developpeur.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.developpeur.IDeveloppeurDTO;
import levy.daniel.application.model.metier.developpeur.IDeveloppeur;


/**
 * CLASSE DeveloppeurDTO :<br/>
 * Classe CONCRETE <b>Data Transfer Object (DTO)</b> 
 * relative au concept de Developpeur.<br/>
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
 * @since 14 nov. 2018
 *
 */
public class DeveloppeurDTO implements IDeveloppeurDTO {
	

	// ************************ATTRIBUTS************************************/
	

	/**
	 * serialVersionUID : long :<br/>
	 * .<br/>
	 */
	private static final long serialVersionUID = 1L;
	

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
	 * id : String :<br/>
	 * ID en base sous forme de String.<br/>
	 */
	private String idString;


	/**
	 * nom du Developpeur sous forme de String<br/>
	 */
	private String nomString;
	
	
	/**
	 * nombre d'années d'experience du Developpeur 
	 * sous forme de String.<br/>
	 */
	private String anneesExperienceString;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(DeveloppeurDTO.class);
	

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public DeveloppeurDTO() {
		this(null, null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR TOTAL.<br/>
	 *
	 * @param pIdString
	 * @param pNomString
	 * @param pAnneesExperienceString
	 */
	public DeveloppeurDTO(
			final String pIdString
				, final String pNomString
					, final String pAnneesExperienceString) {
		
		super();
		
		this.idString = pIdString;
		this.nomString = pNomString;
		this.anneesExperienceString = pAnneesExperienceString;
		
	} // Fin de CONSTRUCTEUR TOTAL.________________________________________


		
	 /**
	 * CONSTRUCTEUR TRANSFORMATEUR.<br/>
	 * <b>transforme un Objet Metier en DTO</b>.
	 *
	 * @param pObjet : IDeveloppeur.
	 */
	public DeveloppeurDTO(
			final IDeveloppeur pObjet) {
		
		super();
		
		if (pObjet != null) {
			
			String idStringLocal = null;
			
			if (pObjet.getId() != null) {
				idStringLocal = String.valueOf(pObjet.getId());
			}
			
			String anneeExpStringLocal = null;
						
			anneeExpStringLocal 
				= String.valueOf(pObjet.getAnneesExperience());
			
			this.idString = idStringLocal;
			this.nomString = pObjet.getNom();
			this.anneesExperienceString = anneeExpStringLocal;
			
		}
		
	} // Fin de CONSTRUCTEUR TRANSFORMATEUR._______________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("DeveloppeurDTO [");
		
		builder.append("idString=");
		if (this.idString != null) {			
			builder.append(this.idString);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("nomString=");
		if (this.nomString != null) {			
			builder.append(this.nomString);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("anneesExperienceString=");
		if (this.anneesExperienceString != null) {			
			builder.append(this.anneesExperienceString);
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
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIdString(
			final String pIdString) {
		this.idString = pIdString;
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNomString() {
		return this.nomString;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNomString(
			final String pNomString) {
		this.nomString = pNomString;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAnneesExperienceString() {
		return this.anneesExperienceString;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAnneesExperienceString(
			final String pAnneesExperienceString) {
		this.anneesExperienceString = pAnneesExperienceString;
	}



} // FIN DE LA CLASSE DeveloppeurDTO.----------------------------------------
