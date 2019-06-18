package levy.daniel.application.model.services.metier.generationcode.decouvreurmembre;

import java.io.Serializable;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE IEncapsulationDeclarationMembre :<br/>
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
public interface IEncapsulationDeclarationMembre extends 
			Comparable<IEncapsulationDeclarationMembre>
								, Serializable, Cloneable
									, IExportateurCsv, IExportateurJTable{

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	int hashCode();

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	boolean equals(Object pObjet);

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	int compareTo(IEncapsulationDeclarationMembre pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return IEncapsulationDeclarationMembre
	 * 
	 * @throws CloneNotSupportedException
	 */
	IEncapsulationDeclarationMembre clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un IEncapsulationDeclarationMembre</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un IEncapsulationDeclarationMembre</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un IEncapsulationDeclarationMembre</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un IEncapsulationDeclarationMembre</b> :<br/>
	 * "id;dateTeleversement;utilisateur;gestionnaire;typeFichier;
	 * nomFichierTeleverse;fichierStockeServeur;anneeGestion;"<br/>
	 * <br/>
	 */
	@Override
	Object fournirValeurColonne(int pI);

	
	
	/**
	 * Getter de l'ID en base.<br/>
	 *
	 * @return this.id : Long.<br/>
	 */
	Long getId();

	
	
	/**
	* Setter de l'ID en base.<br/>
	*
	* @param pId : Long : 
	* valeur à passer à this.id.<br/>
	*/
	void setId(Long pId);


	
	/**
	 * Getter de la ligne entière de la déclaration.
	 *
	 * @return this.ligneEntiere : String.<br/>
	 */
	String getLigneEntiere();


	
	/**
	* Setter de la ligne entière de la déclaration.
	*
	* @param pLigneEntiere : String : 
	* valeur à passer à this.ligneEntiere.<br/>
	*/
	void setLigneEntiere(String pLigneEntiere);


	
	/**
	 * Getter des espaces en début de ligne de déclaration.
	 *
	 * @return this.espacesEnDebutLigne : String.<br/>
	 */
	String getEspacesEnDebutLigne();


	
	/**
	* Setter des espaces en début de ligne de déclaration.
	*
	* @param pEspacesEnDebutLigne : String : 
	* valeur à passer à this.espacesEnDebutLigne.<br/>
	*/
	void setEspacesEnDebutLigne(String pEspacesEnDebutLigne);


	
	/**
	 * Getter de la ligne de déclaration sans les espaces de début.
	 *
	 * @return this.contenuLigne : String.<br/>
	 */
	String getContenuLigne();


	
	/**
	* Setter de la ligne de déclaration sans les espaces de début.
	*
	* @param pContenuLigne : String : 
	* valeur à passer à this.contenuLigne.<br/>
	*/
	void setContenuLigne(String pContenuLigne);


	
	/**
	 * Getter du modérateur (public, protected, private ou rien).
	 *
	 * @return this.moderateur : String.<br/>
	 */
	String getModerateur();


	
	/**
	* Setter du modérateur (public, protected, private ou rien).
	*
	* @param pModerateur : String : 
	* valeur à passer à this.moderateur.<br/>
	*/
	void setModerateur(String pModerateur);


	
	/**
	 * Getter du modificateur static (ou rien).
	 *
	 * @return this.modificateurStatic : String.<br/>
	 */
	String getModificateurStatic();


	
	/**
	* Setter du modificateur static (ou rien).
	*
	* @param pModificateurStatic : String : 
	* valeur à passer à this.modificateurStatic.<br/>
	*/
	void setModificateurStatic(String pModificateurStatic);


	
	/**
	 * Getter du modificateur final (ou rien).
	 *
	 * @return this.modificateurFinal : String.<br/>
	 */
	String getModificateurFinal();


	
	/**
	* Setter du modificateur final (ou rien).
	*
	* @param pModificateurFinal : String : 
	* valeur à passer à this.modificateurFinal.<br/>
	*/
	void setModificateurFinal(String pModificateurFinal);


	
	/**
	 * Getter du modificateur transient (ou rien).
	 *
	 * @return this.modificateurTransient : String.<br/>
	 */
	String getModificateurTransient();


	
	/**
	* Setter du modificateur transient (ou rien).
	*
	* @param pModificateurTransient : String : 
	* valeur à passer à this.modificateurTransient.<br/>
	*/
	void setModificateurTransient(String pModificateurTransient);


	
	/**
	 * Getter du type de l'attribut ou du retour de la méthode.
	 *
	 * @return this.type : String.<br/>
	 */
	String getType();


	
	/**
	* Setter du type de l'attribut ou du retour de la méthode.
	*
	* @param pType : String : 
	* valeur à passer à this.type.<br/>
	*/
	void setType(String pType);


	
	/**
	 * Getter du nom de l'attribut ou de la méthode.
	 *
	 * @return this.nomMembre : String.<br/>
	 */
	String getNomMembre();


	
	/**
	* Setter du nom de l'attribut ou de la méthode.
	*
	* @param pNomMembre : String : 
	* valeur à passer à this.nomMembre.<br/>
	*/
	void setNomMembre(String pNomMembre);


	
	/**
	 * Getter de l'initialisation ou partie de droite de la déclaration.
	 *
	 * @return this.reste : String.<br/>
	 */
	String getReste();


	
	/**
	* Setter de l'initialisation ou partie de droite de la déclaration.
	*
	* @param pReste : String : 
	* valeur à passer à this.reste.<br/>
	*/
	void setReste(String pReste);


	
	/**
	 * Getter du boolean qui stipule si le membre est un attribut.
	 *
	 * @return this.attribut : boolean.<br/>
	 */
	boolean isAttribut();


	
	/**
	* Setter du boolean qui stipule si le membre est un attribut.
	*
	* @param pAttribut : boolean : 
	* valeur à passer à this.attribut.<br/>
	*/
	void setAttribut(boolean pAttribut);

	

} // FIN DE L'INTERFACE IEncapsulationDeclarationMembre.---------------------
