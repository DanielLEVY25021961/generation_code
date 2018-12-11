package levy.daniel.application.model.metier.country.impl;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.country.ICountryDTO;
import levy.daniel.application.model.metier.country.ICountry;
import levy.daniel.application.model.persistence.metier.country.entities.jpa.CountryEntityJPA;


/**
 * CLASSE Country :<br/>
 * CLASSE CONCRETE <b>Country</b> :<br/>
 * <p>
 * <span style="text-decoration: underline;">CONCEPT MODELISE</span>
 * </p>
 * <p>
 * <b>Country</b> Modélise un <i>concept</i> de <b>Pays</b>, 
 * (FRANCE, ANGLETERRE, US ...).
 * </p>
 * 
 * <p>
 * <span style="text-decoration: underline;">DESCRIPTION DE Country</span>
 * </p>
 * <ul>
 * <li>Classe CONCRETE modélisant un <b>Country</b> 
 * (FRANCE, ANGLETERRE, US ...).</li>
 * </ul>
 * 
 * <ul>
 * <li>Implémentation <b>CONCRETE</b> de ICountry.</li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">ATTRIBUTS</span>
 * </p>
 * <ul>
 * <li>Les <b>attributs</b> d'un Country sont : </li>
 * <ul>
 * <li><b>id</b> pour la mise en base.</li>
 * <li><b>name</b> pour le nom du Pays.</li>
 * <li><b>capital</b> pour la capitale du Pays.</li>
 * </ul>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">EGALITE METIER</span>
 * </p>
 * <ul>
 * <li>L'<b>égalité metier</b> d'un Country est vérifiée sur :</li>
  * <ul>
 * <li><b>name</b></li>
 * <li><b>capital</b></li>
 * </ul>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">COMPARAISON</span>
 * </p>
 * <ul>
 * <li>Le <b>tri (comparable)</b> d'un Country est réalisé sur :</li>
  * <ol>
 * <li><b>name</b></li>
 * <li><b>capital</b></li>
 * </0l>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">DIAGRAMME DE CLASSES D'IMPLEMENTATION</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../../javadoc/images/classes_implementation_country.png" 
 * alt="classes d'implémentation des ICountry" border="1" align="center" />
 * </li>
 * </ul>
 * 
 * 
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">REGLES DE GESTION</span>
 * </p>
 * <ul>
 * <li>
 * Les <b>Règles de Gestion (RG)</b> applicables aux attributs 
 * d'un ICountry sont les suivantes :
 * </li>
 * <br/>
 * <table border="1">
 * <tr>
 * <th>Attribut</th><th>Règle de Gestion</th>
 * </tr>
 * 
 *  
 * <tr>
 * <td rowspan="3">
 * name
 * </td>
 * <td>
 * "RG_NOMMAGE_NAME_RENSEIGNE_04
 *  : le name du Country doit être renseigné (obligatoire)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOMMAGE_NAME_LITTERAL_05
 *  : le name du Country 
 *  ne doit contenir que des lettres ou des caractères spéciaux 
 *  '-', '_', ... (aucun chiffre)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOMMAGE_NAME_LONGUEUR_06
 *  : le name du Country doit contenir entre 
 *  [1] et [50] lettres"
 * </td>
 * </tr>

 * <tr>
 * <td rowspan="3">
 * capital
 * </td>
 * <td>
 * "RG_NOMMAGE_CAPITAL_RENSEIGNE_01 : 
 * le capital du Country doit être renseigné (obligatoire)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOMMAGE_CAPITAL_LITTERAL_02 : 
 * le capital du Country 
 * ne doit contenir que des lettres ou des 
 * caractères spéciaux '-', '_', ... (aucun chiffre)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOMMAGE_CAPITAL_LONGUEUR_03 : 
 * le capital du Country doit contenir 
 * entre [1] et [30] lettres"
 * </td>
 * </tr>
 * 
 * </table>
 * </ul>
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
 * @since 11 nov. 2018
 *
 */
public class Country implements ICountry {
	
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
		= LogFactory.getLog(Country.class);

	
	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public Country() {
		this(null, null, null);
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 *
	 * @param pName : String : nom du pays
	 * @param pCapital : String : capitale du pays
	 */
	public Country(
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
	public Country(
			final Long pId
				, final String pName
					, final String pCapital) {
		
		super();
		
		this.id = pId;
		this.name = pName;
		this.capital = pCapital;
		
	} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________



	 /**
	 * CONSTRUCTEUR TRANSFORMATEUR AVEC DTO.<br/>
	 * <ul>
	 * <li>un DTO est un moyen commode de stocker 
	 * tous les champs d'un formulaire.</li>
	 * <li>instancie un objet métier à partir du contenu 
	 * d'une VUE (DTO).</li>
	 * </ul>
	 *
	 * @param pDTO : ICountryDTO
	 */
	public Country(
			final ICountryDTO pDTO) {
		
		super();
		
		if (pDTO != null) {
			
			final String idString = pDTO.getIdString();
			
			if (!StringUtils.isBlank(idString)) {
				this.id = Long.valueOf(idString);
			} else {
				this.id = null;
			}
			
			this.name = pDTO.getNameString();
			this.capital = pDTO.getCapitalString();

		}
		
	} // Fin de CONSTRUCTEUR TRANSFORMATEUR AVEC DTO.______________________
	

	
	 /**
	 * CONSTRUCTEUR TRANSFORMATEUR AVEC JPA.<br/>
	 * <b>instancie un objet métier à partir d'une Entity JPA</b>.<br/>
	 *
	 * @param pEntityJPA : CountryEntityJPA
	 */
	public Country(
			final CountryEntityJPA pEntityJPA) {
		
		super();
		
		if (pEntityJPA != null) {
			
			this.id = pEntityJPA.getId();
			this.name = pEntityJPA.getName();
			this.capital = pEntityJPA.getCapital();
			
		}
		
	} // Fin de CONSTRUCTEUR TRANSFORMATEUR AVEC JPA.______________________
	

	
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
	public Country clone() throws CloneNotSupportedException {

		final Country clone 
				= (Country) super.clone();

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
	@Override
	public String fournirEnTeteCsv() {
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
	public String fournirStringCsv() {

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
	@Override
	public String fournirEnTeteColonne(
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
	
	
	
} // FIN DE LA CLASSE Country.-----------------------------------------------
