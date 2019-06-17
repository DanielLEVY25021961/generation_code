package levy.daniel.application.model.services.metier.generationcode.decouvreurmembre.impl;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.metier.generationcode.decouvreurmembre.IEncapsulationDeclarationMembre;

/**
 * CLASSE EncapsulationDeclarationMembre :<br/>
 * .<br/>
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
 * @since 16 juin 2019
 *
 */
public class EncapsulationDeclarationMembre 
				implements IEncapsulationDeclarationMembre {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID.<br/>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';
	
	/**
	 * ", ".<br/>
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	/**
	 * "null".<br/>
	 */
	public static final String NULL = "null";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * id en base.
	 */
	private Long id;
	
	/**
	 * ligne entière de la déclaration.
	 */
	private String ligneEntiere;
	
	/**
	 * espaces en début de ligne de déclaration.
	 */
	private String espacesEnDebutLigne;
	
	/**
	 * ligne de déclaration sans les espaces de début.
	 */
	private String contenuLigne;
	
	/**
	 * modérateur (public, protected, private ou rien).
	 */
	private String moderateur;
	
	/**
	 * modificateur static (ou rien).
	 */
	private String modificateurStatic;
	
	/**
	 * modificateur final (ou rien).
	 */
	private String modificateurFinal;
	
	/**
	 * modificateur transient (ou rien).
	 */
	private String modificateurTransient;

	/**
	 * type de l'attribut ou du retour de la méthode.
	 */
	private String type;
	
	/**
	 * nom de l'attribut ou de la méthode.
	 */
	private String nomMembre;
	
	/**
	 * initialisation ou partie de droite de la déclaration.
	 */
	private String reste;
	
	/**
	 * boolean qui stipule si le membre est un attribut.
	 */
	private boolean attribut;
	
	/**
	 * boolean qui stipule si le membre est juste <b>public</b>.
	 */
	private boolean publicSimple;
	
	/**
	 * boolean qui stipule si le membre est <b>public transient</b>.
	 */
	private boolean publicTransient;
	
	/**
	 * boolean qui stipule si le membre est <b>public static final</b>.
	 */
	private boolean publicStaticFinal;
	
	/**
	 * boolean qui stipule si le membre est 
	 * <b>public static final transient</b>.
	 */
	private boolean publicStaticFinalTransient;
	
	/**
	 * boolean qui stipule si le membre est <b>public static</b>.
	 */
	private boolean publicStatic;
	
	/**
	 * boolean qui stipule si le membre est 
	 * <b>public static transient</b>.
	 */
	private boolean publicStaticTransient;
	
	/**
	 * boolean qui stipule si le membre est 
	 * <b>public final</b>.
	 */
	private boolean publicFinal;
	
	/**
	 * boolean qui stipule si le membre est 
	 * <b>public final transient</b>.
	 */
	private boolean publicFinalTransient;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(EncapsulationDeclarationMembre.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public EncapsulationDeclarationMembre() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		
		return Objects.hash(
				this.getModerateur()
				, this.getModificateurStatic()
				, this.getModificateurFinal()
				, this.getModificateurTransient()
				, this.getType()
				, this.getNomMembre());
		
	} // Fin de hashCode().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(
			final Object pObjet) {
		
		if (this == pObjet) {
			return true;
		}
		
		if (pObjet == null) {
			return false;
		}
		
		if (!(pObjet instanceof IEncapsulationDeclarationMembre)) {
			return false;
		}
		
		final IEncapsulationDeclarationMembre other 
			= (IEncapsulationDeclarationMembre) pObjet;
		
		return Objects
				.equals(this.getModerateur(), other.getModerateur())
				&& Objects.equals(this.getModificateurStatic(), other.getModificateurStatic())
				&& Objects.equals(this.getModificateurFinal(), other.getModificateurFinal())
				&& Objects.equals(this.getModificateurTransient(), other.getModificateurTransient())
				&& Objects.equals(this.getType(), other.getType())
				&& Objects.equals(this.getNomMembre(), other.getNomMembre());
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final IEncapsulationDeclarationMembre pObjet) {
		
		if (this == pObjet) {
			return 0;
		}

		if (pObjet == null) {
			return -1;
		}

		int compareNomMembre = 0;

		/* nomMembre. */
		if (this.getNomMembre() == null) {
			if (pObjet.getNomMembre() != null) {
				return +1;
			}
			
			return 0;
		}
		
		if (pObjet.getNomMembre() == null) {
			return -1;
		}
		
		compareNomMembre 
			= this.getNomMembre()
				.compareTo(pObjet.getNomMembre());
		
		return compareNomMembre;

	} // Fin de compareTo(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IEncapsulationDeclarationMembre clone() 
				throws CloneNotSupportedException {
		
		final IEncapsulationDeclarationMembre clone 
			= (IEncapsulationDeclarationMembre) super.clone();
		
		clone.setId(this.getId());
		clone.setLigneEntiere(this.getLigneEntiere());
		clone.setEspacesEnDebutLigne(this.getEspacesEnDebutLigne());
		clone.setContenuLigne(this.getContenuLigne());
		clone.setModerateur(this.getModerateur());
		clone.setModificateurStatic(this.getModificateurStatic());
		clone.setModificateurFinal(this.getModificateurFinal());
		clone.setModificateurTransient(this.getModificateurTransient());
		clone.setType(this.getType());
		clone.setNomMembre(this.getNomMembre());
		clone.setReste(this.getReste());
		clone.setAttribut(this.isAttribut());
		clone.setPublicSimple(this.isPublicSimple());
		clone.setPublicTransient(this.isPublicTransient());
		clone.setPublicStaticFinal(this.isPublicStaticFinal());
		clone.setPublicStaticFinalTransient(this.isPublicStaticFinalTransient());
		clone.setPublicStatic(this.isPublicStatic());
		clone.setPublicStaticTransient(this.isPublicStaticTransient());
		clone.setPublicFinal(this.isPublicFinal());
		clone.setPublicFinalTransient(this.isPublicFinalTransient());
		
		return (EncapsulationDeclarationMembre) clone;
		
	} // Fin de clone().___________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {

		final StringBuilder stb = new StringBuilder();

		stb.append("EncapsulationDeclarationMembre [");

		stb.append("id=");
		if (this.getId() != null) {
			stb.append(this.getId());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("contenuLigne=");
		if (this.getContenuLigne() != null) {
			stb.append(this.getContenuLigne());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("moderateur=");
		if (this.getModerateur() != null) {
			stb.append(this.getModerateur());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("modificateurStatic=");
		if (this.getModificateurStatic() != null) {
			stb.append(this.getModificateurStatic());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("modificateurFinal=");
		if (this.getModificateurFinal() != null) {
			stb.append(this.getModificateurFinal());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("modificateurTransient=");
		if (this.getModificateurTransient() != null) {
			stb.append(this.getModificateurTransient());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("type=");
		if (this.getType() != null) {
			stb.append(this.getType());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("nomMembre=");
		if (this.getNomMembre() != null) {
			stb.append(this.getNomMembre());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("reste=");
		if (this.getReste() != null) {
			stb.append(this.getReste());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("attribut=");
		stb.append(this.isAttribut());
		stb.append(VIRGULE_ESPACE);
		
		stb.append("publicSimple=");
		stb.append(this.isPublicSimple());
		stb.append(VIRGULE_ESPACE);
		
		stb.append("publicTransient=");
		stb.append(this.isPublicTransient());
		stb.append(VIRGULE_ESPACE);
		
		stb.append("publicStaticFinal=");
		stb.append(this.isPublicStaticFinal());
		stb.append(VIRGULE_ESPACE);
		
		stb.append("publicStaticFinalTransient=");
		stb.append(this.isPublicStaticFinalTransient());
		stb.append(VIRGULE_ESPACE);
		
		stb.append("publicStatic=");
		stb.append(this.isPublicStatic());
		stb.append(VIRGULE_ESPACE);
		
		stb.append("publicStaticTransient=");
		stb.append(this.isPublicStaticTransient());
		stb.append(VIRGULE_ESPACE);
		
		stb.append("publicFinal=");
		stb.append(this.isPublicFinal());
		stb.append(VIRGULE_ESPACE);
		
		stb.append("publicFinalTransient=");
		stb.append(this.isPublicFinalTransient());
		stb.append(VIRGULE_ESPACE);
		
		stb.append(']');

		return stb.toString();

	} // Fin de toString().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteCsv() {
		
		return "id;ligneEntiere;espacesEnDebutLigne;contenuLigne;"
				+ "moderateur;modificateurStatic;modificateurFinal;"
				+ "modificateurTransient;type;nomMembre;reste;attribut;"
				+ "publicSimple;publicTransient;publicStaticFinal;"
				+ "publicStaticFinalTransient;publicStatic;publicStaticTransient;"
				+ "publicFinal;publicFinalTransient;";

	} // Fin de fournirEnTeteCsv().________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringCsv() {

		final StringBuilder stb = new StringBuilder();

		stb.append(this.getId());
		stb.append(POINT_VIRGULE);
		stb.append(this.getLigneEntiere());
		stb.append(POINT_VIRGULE);
		stb.append(this.getEspacesEnDebutLigne());
		stb.append(POINT_VIRGULE);
		stb.append(this.getContenuLigne());
		stb.append(POINT_VIRGULE);
		stb.append(this.getModerateur());
		stb.append(POINT_VIRGULE);
		stb.append(this.getModificateurStatic());
		stb.append(POINT_VIRGULE);
		stb.append(this.getModificateurFinal());
		stb.append(POINT_VIRGULE);
		stb.append(this.getModificateurTransient());
		stb.append(POINT_VIRGULE);
		stb.append(this.getType());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNomMembre());
		stb.append(POINT_VIRGULE);
		stb.append(this.getReste());
		stb.append(POINT_VIRGULE);
		stb.append(this.isAttribut());
		stb.append(POINT_VIRGULE);
		stb.append(this.isPublicSimple());
		stb.append(POINT_VIRGULE);
		stb.append(this.isPublicTransient());
		stb.append(POINT_VIRGULE);
		stb.append(this.isPublicStaticFinal());
		stb.append(POINT_VIRGULE);
		stb.append(this.isPublicStaticFinalTransient());
		stb.append(POINT_VIRGULE);
		stb.append(this.isPublicStatic());
		stb.append(POINT_VIRGULE);
		stb.append(this.isPublicStaticTransient());
		stb.append(POINT_VIRGULE);
		stb.append(this.isPublicFinal());
		stb.append(POINT_VIRGULE);
		stb.append(this.isPublicFinalTransient());
		stb.append(POINT_VIRGULE);
		
		return stb.toString();

	} // Fin de fournirStringCsv().________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteColonne(
			final int pI) {

		String entete = null;

		switch (pI) {

		case 0:
			entete = "id";
			break;

		case 1:
			entete = "ligneEntiere";
			break;

		case 2:
			entete = "espacesEnDebutLigne";
			break;

		case 3:
			entete = "contenuLigne";
			break;

		case 4:
			entete = "moderateur";
			break;

		case 5:
			entete = "modificateurStatic";
			break;

		case 6:
			entete = "modificateurFinal";
			break;

		case 7:
			entete = "modificateurTransient";
			break;

		case 8:
			entete = "type";
			break;

		case 9:
			entete = "nomMembre";
			break;

		case 10:
			entete = "reste";
			break;

		case 11:
			entete = "attribut";
			break;

		case 12:
			entete = "publicSimple";
			break;

		case 13:
			entete = "publicTransient";
			break;

		case 14:
			entete = "publicStaticFinal";
			break;

		case 15:
			entete = "publicStaticFinalTransient";
			break;

		case 16:
			entete = "publicStatic";
			break;

		case 17:
			entete = "publicStaticTransient";
			break;

		case 18:
			entete = "publicFinal";
			break;

		case 19:
			entete = "publicFinalTransient";
			break;

		default:
			entete = "invalide";
			break;

		} // Fin du Switch._________________________________

		return entete;

	} // Fin de fournirEnTeteColonne(...)._________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Object fournirValeurColonne(
			final int pI) {

		Object valeur = null;

		switch (pI) {

		case 0:
			final Long idLocal = this.getId();
			if (idLocal != null) {
				valeur = String.valueOf(idLocal);
			}			
			break;

		case 1:
			valeur = this.getLigneEntiere();
			break;

		case 2:
			valeur = this.getEspacesEnDebutLigne();
			break;

		case 3:
			valeur = this.getContenuLigne();
			break;

		case 4:
			valeur = this.getModerateur();
			break;

		case 5:
			valeur = this.getModificateurStatic();
			break;

		case 6:
			valeur = this.getModificateurFinal();
			break;

		case 7:
			valeur = this.getModificateurTransient();
			break;

		case 8:
			valeur = this.getType();
			break;

		case 9:
			valeur = this.getNomMembre();
			break;

		case 10:
			valeur = this.getReste();
			break;

		case 11:
			valeur = String.valueOf(this.isAttribut());
			break;

		case 12:
			valeur = String.valueOf(this.isPublicSimple());
			break;

		case 13:
			valeur = String.valueOf(this.isPublicTransient());
			break;

		case 14:
			valeur = String.valueOf(this.isPublicStaticFinal());
			break;

		case 15:
			valeur = String.valueOf(this.isPublicStaticFinalTransient());
			break;

		case 16:
			valeur = String.valueOf(this.isPublicStatic());
			break;

		case 17:
			valeur = String.valueOf(this.isPublicStaticTransient());
			break;

		case 18:
			valeur = String.valueOf(this.isPublicFinal());
			break;

		case 19:
			valeur = String.valueOf(this.isPublicFinalTransient());
			break;

		default:
			valeur = "invalide";
			break;

		} // Fin du Switch._________________________________

		return valeur;

	} // Fin de fournirValeurColonne(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Long getId() {	
		return this.id;
	} // Fin de getId().___________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setId(
			final Long pId) {	
		this.id = pId;
	} // Fin de setId(...).________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getLigneEntiere() {
		return this.ligneEntiere;
	} // Fin de getLigneEntiere()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setLigneEntiere(
			final String pLigneEntiere) {
		this.ligneEntiere = pLigneEntiere;
	} // Fin de setLigneEntiere(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getEspacesEnDebutLigne() {
		return this.espacesEnDebutLigne;
	} // Fin de getEspacesEnDebutLigne().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setEspacesEnDebutLigne(
			final String pEspacesEnDebutLigne) {
		this.espacesEnDebutLigne = pEspacesEnDebutLigne;
	} // Fin de setEspacesEnDebutLigne(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getContenuLigne() {
		return this.contenuLigne;
	} // Fin de getContenuLigne()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setContenuLigne(
			final String pContenuLigne) {
		this.contenuLigne = pContenuLigne;
	} // Fin de setContenuLigne(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getModerateur() {
		return this.moderateur;
	} // Fin de getModerateur().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setModerateur(
			final String pModerateur) {
		this.moderateur = pModerateur;
	} // Fin de setModerateur(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getModificateurStatic() {
		return this.modificateurStatic;
	} // Fin de getModificateurStatic().___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setModificateurStatic(
			final String pModificateurStatic) {
		this.modificateurStatic = pModificateurStatic;
	} // Fin de setModificateurStatic(...).________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getModificateurFinal() {
		return this.modificateurFinal;
	} // Fin de getModificateurFinal().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setModificateurFinal(
			final String pModificateurFinal) {
		this.modificateurFinal = pModificateurFinal;
	} // Fin de setModificateurFinal(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getModificateurTransient() {
		return this.modificateurTransient;
	} // Fin de getModificateurTransient().________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setModificateurTransient(
			final String pModificateurTransient) {
		this.modificateurTransient = pModificateurTransient;
	} // Fin de setModificateurTransient(...)._____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getType() {
		return this.type;
	} // Fin de getType()._________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setType(
			final String pType) {
		this.type = pType;
	} // Fin de setType(...).______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomMembre() {
		return this.nomMembre;
	} // Fin de getNomMembre().____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNomMembre(
			final String pNomMembre) {
		this.nomMembre = pNomMembre;
	} // Fin de setNomMembre(...)._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getReste() {
		return this.reste;
	} // Fin de getReste().________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setReste(
			final String pReste) {
		this.reste = pReste;
	} // Fin de setReste(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isAttribut() {
		return this.attribut;
	} // Fin de isAttribut().______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAttribut(
			final boolean pAttribut) {
		this.attribut = pAttribut;
	} // Fin de setAttribut(...).__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isPublicSimple() {
		return this.publicSimple;
	} // Fin de isPublicSimple().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPublicSimple(
			final boolean pPublicSimple) {
		this.publicSimple = pPublicSimple;
	} // Fin de setPublicSimple(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isPublicTransient() {
		return this.publicTransient;
	} // Fin de isPublicTransient()._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPublicTransient(
			final boolean pPublicTransient) {
		this.publicTransient = pPublicTransient;
	} // Fin de setPublicTransient(...).___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isPublicStaticFinal() {
		return this.publicStaticFinal;
	} // Fin de isPublicStaticFinal()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPublicStaticFinal(
			final boolean pPublicStaticFinal) {
		this.publicStaticFinal = pPublicStaticFinal;
	} // Fin de setPublicStaticFinal(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isPublicStaticFinalTransient() {
		return this.publicStaticFinalTransient;
	} // Fiin de isPublicStaticFinalTransient().___________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPublicStaticFinalTransient(
			final boolean pPublicStaticFinalTransient) {
		this.publicStaticFinalTransient = pPublicStaticFinalTransient;
	} // Fin de setPublicStaticFinalTransient(...).________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isPublicStatic() {
		return this.publicStatic;
	} // Fin de isPublicStatic().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPublicStatic(
			final boolean pPublicStatic) {
		this.publicStatic = pPublicStatic;
	} // Fin de setPublicStatic(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isPublicStaticTransient() {
		return this.publicStaticTransient;
	} // Fin de isPublicStaticTransient()._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPublicStaticTransient(
			final boolean pPublicStaticTransient) {
		this.publicStaticTransient = pPublicStaticTransient;
	} // Fin de setPublicStaticTransient(...)._____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isPublicFinal() {
		return this.publicFinal;
	} // Fin de isPublicFinal().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPublicFinal(
			final boolean pPublicFinal) {
		this.publicFinal = pPublicFinal;
	} // Fin de setPublicFinal(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isPublicFinalTransient() {
		return this.publicFinalTransient;
	} // Fin de isPublicFinalTransient().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPublicFinalTransient(
			final boolean pPublicFinalTransient) {
		this.publicFinalTransient = pPublicFinalTransient;
	} // Fin de setPublicFinalTransient(...).______________________________
	
	
	
} // FIN DE LA CLASSE EncapsulationDeclarationMembre.------------------------
