package levy.daniel.application.model.persistence.metier.developpeur.entities.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.developpeur.IDeveloppeur;




/**
 * CLASSE DeveloppeurEntityJPA :<br/>
 * Entity JPA modélisant un Objet Métier en vue de son stockage.<br/>
 * l'Entity doit <b>implémenter la même interface 
 * que l'objet métier et être equals</b>.<br/>
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
@Entity(name="DeveloppeurEntityJPA")
@Table(name="DEVELOPPEURS", schema="PUBLIC"
, uniqueConstraints=@UniqueConstraint(name="UNICITE_NOM_EXPERIENCE"
, columnNames={"NOM", "ANNEES_EXPERIENCE"}))
public class DeveloppeurEntityJPA implements IDeveloppeur {
	
	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe DeveloppeurEntityJPA".<br/>
	 */
	public static final String CLASSE_DEVELOPPEUR_ENTITY_JPA 
		= "Classe DeveloppeurEntityJPA";

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
	 * SEPARATEUR_MOINS_AERE : String :<br/>
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";


	/**
	 * UNDERSCORE : String :<br/>
	 * "_".<br/>
	 */
	public static final String UNDERSCORE = "_";


	/**
	 * NULL : String :<br/>
	 * "null".<br/>
	 */
	public static final String NULL = "null";


	/**
	 * serialVersionUID : long :<br/>
	 * serialVersionUID = 1L.<br/>
	 */
	private static final long serialVersionUID = 1L;
	
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
	private static final Log LOG 
		= LogFactory.getLog(DeveloppeurEntityJPA.class);
	
	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public DeveloppeurEntityJPA() {
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
	public DeveloppeurEntityJPA(
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
	public DeveloppeurEntityJPA(
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
	 * <b>instancie un Entity JPA à partir 
	 * d'un Objet Metier</b>.<br/>
	 *
	 * @param pObject : IDeveloppeur
	 */
	public DeveloppeurEntityJPA(
			final IDeveloppeur pObject) {
		
		super();
		
		if (pObject != null) {
			
			this.id = pObject.getId();
			this.nom = pObject.getNom();
			this.anneesExperience = pObject.getAnneesExperience();
			
		}
		
	} // Fin de CONSTRUCTEUR TRANSFORMATEUR._______________________________
	
	
	
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
	public DeveloppeurEntityJPA clone() throws CloneNotSupportedException {

		final DeveloppeurEntityJPA clone 
				= (DeveloppeurEntityJPA) super.clone();

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
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DEVELOPPEUR")
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
	@Column(name = "NOM"
			, unique = false, nullable = false
			, updatable = true, insertable = true)
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
	@Column(name = "ANNEES_EXPERIENCE"
			, unique = false, nullable = true
			, updatable = true, insertable = true)
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

	

}
