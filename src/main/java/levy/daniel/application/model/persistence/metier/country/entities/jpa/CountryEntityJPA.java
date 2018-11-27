package levy.daniel.application.model.persistence.metier.country.entities.jpa;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.country.ICountry;


/**
 * CLASSE CountryEntityJPA :<br/>
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
@Entity(name="CountryEntityJPA")
@Table(name="COUNTRYS", schema="PUBLIC"
, uniqueConstraints=@UniqueConstraint(name="UNICITE_NAME_CAPITAL"
, columnNames={"NAME", "CAPITAL"}))
public class CountryEntityJPA implements ICountry {
	
	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe CountryEntityJPA".<br/>
	 */
	public static final String CLASSE_COUNTRY_ENTITY_JPA 
		= "Classe CountryEntityJPA";

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
	 * nom du Country.<br/>
	 */
	private String name;
	
	/**
	 * capitale du Country.<br/>
	 */
	private String capital;
	


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(CountryEntityJPA.class);
	
	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public CountryEntityJPA() {
		this(null, null, null);
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 *
	 * @param pName : String : nom du pays
	 * @param pCapital : String : capitale du pays
	 */
	public CountryEntityJPA(
				final String pName
					, final String pCapital) {
		this(null, pName, pCapital);
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET BASE.<br/>
	 *
	 * @param pId : Long : identifiant en base
	 * @param pName : String : nom du pays
	 * @param pCapital : String : capitale du pays
	 */
	public CountryEntityJPA(
			final Long pId
				, final String pName
					, final String pCapital) {
		
		super();
		
		this.id = pId;
		this.name = pName;
		this.capital = pCapital;
		
	} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________


	
	 /**
	 * CONSTRUCTEUR TRANSFORMATEUR.<br/>
	 * <b>instancie un Entity JPA à partir 
	 * d'un Objet Metier</b>.<br/>
	 *
	 * @param pObject : ICountry
	 */
	public CountryEntityJPA(
			final ICountry pObject) {
		
		super();
		
		if (pObject != null) {
			
			this.id = pObject.getId();
			this.name = pObject.getName();
			this.capital = pObject.getCapital();
			
		}
		
	} // Fin de CONSTRUCTEUR TRANSFORMATEUR._______________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		
		result = prime * result 
				+ ((this.name == null) ? 0 
						: this.name.toUpperCase(Locale.FRANCE).hashCode());
		result = prime * result 
				+ ((this.capital == null) ? 0 
						: this.capital.toUpperCase(Locale.FRANCE).hashCode());
				
		return result;
		
	} // Fin de hashCode().________________________________________________



	/**
	 * {@inheritDoc}
	 * <br/>
	 * <ul>
	 * <b>equals(...) pour un Country</b> sur :
	 * <li>name.</li>
	 * <li>capital.</li>
	 * </ul>
	 * <b>Bien utiliser l'INTERFACE 
	 * (pas la classe concrète ou une classe abstraite)
	 * pour assurer le equals avec les entities</b>.<br/>
	 * <br/>
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
		if (!(pObject instanceof ICountry)) {
			return false;
		}
		
		final ICountry other = (ICountry) pObject;
		
		/* name. */
		if (this.getName() == null) {
			if (other.getName() != null) {
				return false;
			}
		}
		else if (!this.getName()
				.equalsIgnoreCase(other.getName())) {
			return false;
		}
		
		/* capital. */
		if (this.getCapital() == null) {
			if (other.getCapital() != null) {
				return false;
			}
		}
		else if (!this.getCapital()
				.equalsIgnoreCase(other.getCapital())) {
			return false;
		}
		
		return true;
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(
			final ICountry pObject) {

		if (this == pObject) {
			return 0;
		}

		if (pObject == null) {
			return -1;
		}

		int compareName;
		int compareCapital;

		/* name. */
		if (this.getName() == null) {
			if (pObject.getName() != null) {
				return +1;
			}
		} else {
			if (pObject.getName() == null) {
				return -1;
			}

			compareName 
				= this.getName()
					.compareToIgnoreCase(pObject.getName());

			if (compareName != 0) {
				return compareName;
			}
		}

		/* capital. */
		if (this.getCapital() == null) {
			if (pObject.getCapital() != null) {
				return +1;
			}
			return 0;
		} 

		if (pObject.getCapital() == null) {
			return -1;
		}

		compareCapital 
			= this.getCapital()
				.compareToIgnoreCase(pObject.getCapital());

		return compareCapital;

	} // Fin de compareTo(...).____________________________________________



	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CountryEntityJPA clone() throws CloneNotSupportedException {

		final CountryEntityJPA clone 
				= (CountryEntityJPA) super.clone();

		clone.setId(this.id);	
		clone.setName(this.name);
		clone.setCapital(this.capital);
		
		return clone;

	} // Fin de clone().___________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("Country [");
		
		builder.append("id=");
		if (this.id != null) {			
			builder.append(this.id);			
		} else {
			builder.append(NULL);			
		}
		
		builder.append(VIRGULE_ESPACE);

		builder.append("name=");
		if (this.name != null) {			
			builder.append(this.name);
		} else {
			builder.append(NULL);			
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("capital=");
		if (this.capital != null) {			
			builder.append(this.capital);
		} else {
			builder.append(NULL);			
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête csv pour un Country</b> :<br/>
	 * "id;name;capital;".<br/>
	 * <br/>
	 */
	@Transient
	@Override
	public String getEnTeteCsv() {
		return "id;name;capital;";
	} // Fin de getEnTeteCsv().____________________________________________


	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>ligne csv pour un Country</b> :<br/>
	 * "id;name;capital;".<br/>
	 * <br/>
	 */
	@Override
	public String toStringCsv() {

		final StringBuilder stb = new StringBuilder();

		/* id. */
		stb.append(this.getId());
		stb.append(POINT_VIRGULE);

		/* name. */
		stb.append(this.getName());
		stb.append(POINT_VIRGULE);

		/* capital. */
		stb.append(this.getCapital());
		stb.append(POINT_VIRGULE);

		return stb.toString();

	} // Fin de toStringCsv()._____________________________________________
	


	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête Jtable pour un Country</b> :<br/>
	 * "id;name;capital;".<br/>
	 * <br/>
	 */
	@Transient
	@Override
	public String getEnTeteColonne(
			final int pI) {

		String entete = null;

		switch (pI) {

		case 0:
			entete = "id";
			break;

		case 1:
			entete = "name";
			break;

		case 2:
			entete = "capital";
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
	 * <b>ligne Jtable pour un Country</b> :<br/>
	 * "id;name;capital;".<br/>
	 * <br/>
	 */
	@Transient
	@Override
	public Object getValeurColonne(
			final int pI) {

		Object valeur = null;

		switch (pI) {

		case 0:
			if (this.getId() != null) {
				valeur = String.valueOf(this.getId());
			}
			break;

		case 1:
			valeur = this.getName();		
			break;

		case 2:
			valeur = this.getCapital();		
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
	@Column(name="ID_COUNTRY")
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
	@Column(name = "NAME"
			, unique = false, nullable = false
			, updatable = true, insertable = true)
	@Override
	public String getName() {
		return this.name;
	} // Fin de getName()._________________________________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setName(
			final String pName) {
		this.name = pName;		
	} // Fin de setName(...).______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name = "CAPITAL"
			, unique = false, nullable = true
			, updatable = true, insertable = true)
	@Override
	public String getCapital() {
		return this.capital;
	} // Fin de getCapital().______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCapital(
			final String pCapital) {
		this.capital = pCapital;		
	} // Fin de setCapital(...).___________________________________________
	
	
	
}
