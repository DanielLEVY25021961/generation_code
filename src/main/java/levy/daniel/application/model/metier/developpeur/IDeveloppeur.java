package levy.daniel.application.model.metier.developpeur;

import java.io.Serializable;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;


/**
 * INTERFACE IDeveloppeur :<br/>
 * Cette interface modélise un concept de Developpeur.<br/>
 * 
 * <ul>
 * <p>
 * <span style="text-decoration: underline;">
 * HERITE de :
 * </span>
 * </p>
 * <li><b>IExportateurCsv</b> pour l'export d'un Objet 
 * métier en csv.</li>
 * <li><b>IExportateurJTable</b> pour l'affichage dans 
 * une JTable (Swing).</li>
 * <li><b>Comparable</b> pour l'affichage des Collections 
 * sous forme triée.</li>
 * <li><b>Cloneable</b> pour garantir que tout objet métier 
 * implémentant cette interface saura se cloner.</li>
 * <li><b>Serializable</b> pour garantir que tout objet métier 
 * implémentant cette interface pourra être serialisé.</li>
 * </ul>
 * 
 * <ul>
 * <p>
 * <span style="text-decoration: underline;">
 * Garantit que tout IDeveloppeur sait :
 * </span>
 * </p>
 * <li>se <b>comparer</b> à un autre IDeveloppeur.</li>
 * <li>se <b>cloner</b>.</li>
 * <li>s'exporter sous forme <b>csv</b>.</li>
 * <li>s'exporter sous forme <b>JTable</b>.</li>
 * </ul>
 * 
 * 
 * <ul>
 * <p>
 * <span style="text-decoration: underline;">
 * Garantit que tout IDeveloppeur possède à minima :
 * </span>
 * </p>
 * <li><b>id</b> pour la mise en base.</li>
 * <li><b>nom</b>.</li>
 * <li><b>anneesExperience</b>.</li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">EGALITE METIER</span>
 * </p>
 * <ul>
 * <li>L'<b>égalité metier</b> d'un IDeveloppeur est vérifiée sur :</li>
  * <ul>
 * <li><b>nom</b></li>
 * <li><b>anneesExperience</b></li>
 * </ul>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">COMPARAISON</span>
 * </p>
 * <ul>
 * <li>La <b>comparaison</b> d'un IDeveloppeur est réalisée sur :</li>
  * <ol>
 * <li><b>nom</b></li>
 * <li><b>anneesExperience</b></li>
 * </ol>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">DIAGRAMME DE CLASSES D'IMPLEMENTATION</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../../javadoc/images/metier/developpeur/classes_implementation_developpeur.png" 
 * alt="classes d'implémentation des IDeveloppeur" border="1" align="center" />
 * </li>
 * </ul>
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">REGLES DE GESTION</span>
 * </p>
 * <ul>
 * <li>
 * Les <b>Règles de Gestion (RG)</b> applicables aux attributs 
 * d'un IDeveloppeur sont les suivantes :
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
 * nom
 * </td>
 * <td>
 * "RG_NOM_RENSEIGNE_04
 *  : le nom du Developpeur doit être renseigné (obligatoire)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOM_LITTERAL_05
 *  : le nom du Developpeur 
 *  ne doit contenir que des lettres ou des caractères spéciaux 
 *  '-', '_', ... (aucun chiffre)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOM_LONGUEUR_06
 *  : le nom du Developpeur doit contenir entre 
 *  [1] et [50] lettres"
 * </td>
 * </tr>

 * <tr>
 * <td rowspan="1">
 * anneesExperience
 * </td>
 * <td>
 * "RG_ANNEES_NON_RENSEIGNE_01 : 
 * le anneesExperience du Developpeur n'est pas obligatoirement renseigné"
 * </td>
 * </tr>
 * 
 * 
 * </table>
 * </ul>
 * 
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
 * @author adminLocal Lévy
 * @version 1.0
 * @since 12 nov. 2018
 *
 */
public interface IDeveloppeur 
		extends IExportateurCsv, IExportateurJTable
				, Comparable<IDeveloppeur>, Cloneable, Serializable {

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	int hashCode();
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	boolean equals(Object pObject);
	
	
	
	/**
	 * method compareTo(
	 * IDeveloppeur pObject) :<br/>
	 * <ol>
	 * Comparaison de 2 IDeveloppeur par rapport au : <br/>
	 * <li>nom.</li>
	 * <li>anneesExperience.</li>
	 * </ol>
	 *
	 * @param pObject : IDeveloppeur.<br/>
	 * 
	 * @return : int : négatif si la présente instance 
	 * est "avant" pObject.<br/>
	 */
	@Override
	int compareTo(IDeveloppeur pObject);

	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();
	

	
	/**
	 * method clone() :<br/>
	 * Clone un IDeveloppeur.<br/>
	 * <br/>
	 *
	 * @return IDeveloppeur : clone.<br/>
	 * 
	 * @throws CloneNotSupportedException
	 */
	IDeveloppeur clone() throws CloneNotSupportedException; 
	
	

	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête csv pour un IDeveloppeur</b> :<br/>
	 * "id;nom;anneesExperience;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();

	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>ligne csv pour un IDeveloppeur</b> :<br/>
	 * "id;nom;anneesExperience;".<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();

	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête Jtable pour un IDeveloppeur</b> :<br/>
	 * "id;nom;anneesExperience;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);

	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>ligne Jtable pour un IDeveloppeur</b> :<br/>
	 * "id;nom;anneesExperience;".<br/>
	 * <br/>
	 */
	@Override
	Object fournirValeurColonne(int pI);

	

	/**
	 * method getId() :<br/>
	 * Getter de l'ID en base.<br/>
	 * <br/>
	 *
	 * @return this.id : Long.<br/>
	 */
	Long getId();


	/**
	 * method setId( Long pId) :<br/>
	 * Setter de l'ID en base.<br/>
	 * <br/>
	 *
	 * @param pId : Long : 
	 * valeur à passer à this.id.<br/>
	 */
	void setId(Long pId);


	/**
	 * Getter .<br/>
	 *
	 * @return nom : String.<br/>
	 */
	String getNom();


	/**
	 * .<br/>
	 *
	 * @param pNom : String : 
	 * valeur à passer à this.nom.<br/>
	 */
	void setNom(String pNom);


	/**
	 * Getter .<br/>
	 *
	 * @return anneesExperience : int.<br/>
	 */
	int getAnneesExperience();


	/**
	 * .<br/>
	 *
	 * @param pAnneesExperience : int : 
	 * valeur à passer à
	 * this.anneesExperience.<br/>
	 */
	void setAnneesExperience(int pAnneesExperience);

}
