package levy.daniel.application.model.metier.developpeur.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import levy.daniel.application.model.dto.metier.developpeur.IDeveloppeurDTO;
import levy.daniel.application.model.metier.developpeur.IDeveloppeur;
import levy.daniel.application.model.persistence.metier.developpeur.entities.jpa.DeveloppeurEntityJPA;


/**
 * CLASSE Developpeur :<br/>
 * Cette classe contrète modélise un concept de Developpeur.<br/>
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
@Component("Developpeur")
public class Developpeur implements IDeveloppeur {

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
	 * id : Long :<br/>
	 * ID en base.<br/>
	 */
	private Long id;


	/**
	 * nom du développeur.<br/>
	 */
	private String nom;
	
	
	/**
	 * nombre d'années d'expérience du développeur.<br/>
	 */
	private int anneesExperience;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(Developpeur.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public Developpeur() {
		this(null, null, 0);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 *
	 * @param pNom : String : 
	 * nom du développeur.<br/>
	 * @param pAnneesExperience : int : 
	 * nombre d'années d'expérience du développeur.<br/>
	 */
	public Developpeur(
			final String pNom
					, final int pAnneesExperience) {
		this(null, pNom, pAnneesExperience);
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET BASE.<br/>
	 * 
	 * @param pId : Long : 
	 * ID en base.<br/> 
	 * @param pNom : String : 
	 * nom du développeur.<br/>
	 * @param pAnneesExperience : int : 
	 * nombre d'années d'expérience du développeur.<br/>
	 */
	public Developpeur(
			final Long pId
				, final String pNom
					, final int pAnneesExperience) {
		
		super();
		
		this.id = pId;
		this.nom = pNom;
		this.anneesExperience = pAnneesExperience;
		
	} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR TRANSFORMATEUR.<br/>
	 * <b>transforme une VUE (DTO) en Objet Metier</b>.
	 *
	 * @param pDTO : IDeveloppeurDTO
	 */
	public Developpeur(
			final IDeveloppeurDTO pDTO) {
		
		super();
		
		if (pDTO != null) {
			
			Long idLocal = null;
			int anneeLocal = 0;
			
			try {
				idLocal = Long.valueOf(pDTO.getIdString());
			} catch (Exception e) {
				this.id = null;
			}
			
			try {
				anneeLocal 
					= Integer
						.parseInt(
								pDTO.getAnneesExperienceString());
			} catch (Exception e) {
				this.anneesExperience = 0;
			}
			
			this.id = idLocal;
			this.nom = pDTO.getNomString();
			this.anneesExperience = anneeLocal;
		}
		
	} // Fin de CONSTRUCTEUR TRANSFORMATEUR._______________________________

	
	
	 /**
	 * CONSTRUCTEUR TRANSFORMATEUR AVEC JPA.<br/>
	 * <b>instancie un objet métier à partir d'une Entity JPA</b>.<br/>
	 *
	 * @param pEntityJPA : DeveloppeurEntityJPA
	 */
	public Developpeur(
			final DeveloppeurEntityJPA pEntityJPA) {
		
		super();
		
		if (pEntityJPA != null) {
			
			this.id = pEntityJPA.getId();
			this.nom = pEntityJPA.getNom();
			this.anneesExperience = pEntityJPA.getAnneesExperience();
			
		}
		
	} // Fin de CONSTRUCTEUR TRANSFORMATEUR AVEC JPA.______________________
	


	
	
	/**
	 * méthode exécutée avant la construction du Bean.<br/>
	 */
	public void avantConstruction() {
		System.out.println(
				"CLASSE Developpeur - méthode avantConstruction()");
	} // Fin de avantConstruction()._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + this.anneesExperience;
		result = prime * result 
				+ ((this.nom == null) ? 0 : this.nom.hashCode());
		
		return result;
		
	} // Fin de hashCode().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(
			final Object pObject) {
		
		if (this == pObject) {
			return true;
		}
		if (pObject == null) {
			return false;
		}
		if (!(pObject instanceof IDeveloppeur)) {
			return false;
		}
		
		final IDeveloppeur other = (IDeveloppeur) pObject;
		
		/* nom. */
		if (this.getNom() == null) {
			if (other.getNom() != null) {
				return false;
			}
		} else {
			
			if (other.getNom() == null) {
				return false;
			}
			
			if (!this.getNom().equalsIgnoreCase(other.getNom())) {
				return false;
			}
		}
				
		/* années d'expérience. */
		if (this.getAnneesExperience() 
				!= other.getAnneesExperience()) {
			return false;
		}
		
		return true;
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(
			final IDeveloppeur pObject) {

		if (this == pObject) {
			return 0;
		}

		if (pObject == null) {
			return -1;
		}

		int compareNom;
		int compareAnneesExperience;
		
		/* nom. */
		if (this.getNom() == null) {
			if (pObject.getNom() != null) {
				return +1;
			}
		} else {
			if (pObject.getNom() == null) {
				return -1;
			}

			compareNom 
				= this.getNom()
					.compareToIgnoreCase(pObject.getNom());

			if (compareNom != 0) {
				return compareNom;
			}
		}

		/* anneesExperience. */
		compareAnneesExperience 
			= pObject.getAnneesExperience() 
				- this.getAnneesExperience();
			
		return compareAnneesExperience;

	} // Fin de compareTo(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Developpeur clone() throws CloneNotSupportedException {

		final Developpeur clone 
				= (Developpeur) super.clone();

		clone.setId(this.id);	
		clone.setNom(this.nom);
		clone.setAnneesExperience(this.anneesExperience);
		
		return clone;

	} // Fin de clone().___________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		final StringBuilder builder 
			= new StringBuilder();
		
		builder.append("Developpeur [");
		
		builder.append("id=");
		if (this.id != null) {			
			builder.append(this.id);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("nom=");
		if (this.nom != null) {			
			builder.append(this.nom);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
				
		builder.append("anneesExperience=");
		builder.append(this.anneesExperience);
		
		builder.append(']');
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête csv pour un Developpeur</b> :<br/>
	 * "id;nom;anneesExperience;".<br/>
	 * <br/>
	 */
	@Override
	public String fournirEnTeteCsv() {
		return "id;nom;anneesExperience;";
	} // Fin de getEnTeteCsv().____________________________________________


	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>ligne csv pour un Developpeur</b> :<br/>
	 * "id;nom;anneesExperience;".<br/>
	 * <br/>
	 */
	@Override
	public String fournirStringCsv() {

		final StringBuilder stb = new StringBuilder();

		/* id. */
		stb.append(this.getId());
		stb.append(POINT_VIRGULE);

		/* nom. */
		stb.append(this.getNom());
		stb.append(POINT_VIRGULE);

		/* anneesExperience. */
		stb.append(this.getAnneesExperience());
		stb.append(POINT_VIRGULE);

		return stb.toString();

	} // Fin de toStringCsv()._____________________________________________
	


	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête Jtable pour un Developpeur</b> :<br/>
	 * "id;nom;anneesExperience;".<br/>
	 * <br/>
	 */
	@Override
	public String fournirEnTeteColonne(
			final int pI) {

		String entete = null;

		switch (pI) {

		case 0:
			entete = "id";
			break;

		case 1:
			entete = "nom";
			break;

		case 2:
			entete = "anneesExperience";
			break;

		default:
			entete = "invalide";
			break;

		} // Fin du Switch._________________________________

		return entete;

	} // Fin de getEnTeteColonne(...)._____________________________________
	


	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>ligne Jtable pour un Developpeur</b> :<br/>
	 * "id;nom;anneesExperience;".<br/>
	 * <br/>
	 */
	@Override
	public Object fournirValeurColonne(
			final int pI) {

		Object valeur = null;

		switch (pI) {

		case 0:
			if (this.getId() != null) {
				valeur = String.valueOf(this.getId());
			}
			break;

		case 1:
			valeur = this.getNom();		
			break;

		case 2:
			valeur = String.valueOf(this.getAnneesExperience());		
			break;

		default:
			valeur = "invalide";
			break;

		} // Fin du Switch._________________________________

		return valeur;

	} // Fin de getValeurColonne(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return this.id;
	} // Fin de getId().___________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(
			final Long pId) {
		this.id = pId;		
	} // Fin de setId(...).________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNom() {
		return this.nom;
	} // Fin de getNom().__________________________________________________


	
	 /**
	 * {@inheritDoc}
	 */
	@Override
	public void setNom(
			final String pNom) {
		this.nom = pNom;
	} // Fin de setNom(...)._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getAnneesExperience() {
		return this.anneesExperience;
	} // Fin de getAnneesExperience()._____________________________________


	
	 /**
	 * {@inheritDoc}
	 */
	@Override
	public void setAnneesExperience(
			final int pAnneesExperience) {
		this.anneesExperience = pAnneesExperience;
	} // Fin de setAnneesExperience(...).__________________________________

	

} // FIN DE LA CLASSE Developpeur.-------------------------------------------
