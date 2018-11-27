package levy.daniel.application.model.metier.country;

import java.io.Serializable;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE ICountry :<br/>
 * <p>
 * <b>ICountry</b> modélise un un <i>concept</i> de <b>Country</b> 
 * (Pays) avec un name (nom) et un capital (capitale)
 * , c'est à dire un couple <b>[name - capital]</b> qui identifie 
 * <i>un seul</i> <b>Pays</b>.
 * </p>
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
 * Garantit que tout ICountry sait :
 * </span>
 * </p>
 * <li>se <b>comparer</b> à un autre ICountry.</li>
 * <li>se <b>cloner</b>.</li>
 * <li>s'exporter sous forme <b>csv</b>.</li>
 * <li>s'exporter sous forme <b>JTable</b>.</li>
 * </ul>
 * 
 * 
 * <ul>
 * <p>
 * <span style="text-decoration: underline;">
 * Garantit que tout ICountry possède à minima :
 * </span>
 * </p>
 * <li><b>id</b> pour la mise en base.</li>
 * <li><b>name</b>.</li>
 * <li><b>capital</b>.</li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">EGALITE METIER</span>
 * </p>
 * <ul>
 * <li>L'<b>égalité metier</b> d'un ICountry est vérifiée sur :</li>
  * <ul>
 * <li><b>name</b></li>
 * <li><b>capital</b></li>
 * </ul>
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
public interface ICountry 
			extends IExportateurCsv, IExportateurJTable
				, Comparable<ICountry>, Cloneable, Serializable {
	

	
	/**
	 * method compareTo(
	 * ICountry pObject) :<br/>
	 * <ol>
	 * Comparaison de 2 ICountry par rapport au : <br/>
	 * <li>name.</li>
	 * <li>capital.</li>
	 * </ol>
	 *
	 * @param pObject : ICountry.<br/>
	 * 
	 * @return : int : négatif si la présente instance 
	 * est "avant" pObject.<br/>
	 */
	@Override
	int compareTo(ICountry pObject);

	
		
	/**
	 * method clone() :<br/>
	 * Clone un ICountry.<br/>
	 * <br/>
	 *
	 * @return ICountry : clone.<br/>
	 * 
	 * @throws CloneNotSupportedException
	 */
	ICountry clone() throws CloneNotSupportedException; 
	
	

	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête csv pour un ICountry</b> :<br/>
	 * "id;name;capital;".<br/>
	 * <br/>
	 */
	@Override
	String getEnTeteCsv();

	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>ligne csv pour un ICountry</b> :<br/>
	 * "id;name;capital;".<br/>
	 * <br/>
	 */
	@Override
	String toStringCsv();

	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête Jtable pour un ICountry</b> :<br/>
	 * "id;name;capital;".<br/>
	 * <br/>
	 */
	@Override
	String getEnTeteColonne(int pI);

	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>ligne Jtable pour un ICountry</b> :<br/>
	 * "id;name;capital;".<br/>
	 * <br/>
	 */
	@Override
	Object getValeurColonne(int pI);
	
	

	/**
	 * method getId() :<br/>
	 * Getter de l'ID en base.<br/>
	 * <br/>
	 *
	 * @return this.id : Long.<br/>
	 */
	Long getId();

	
	
	/**
	* method setId(
	* Long pId) :<br/>
	* Setter de l'ID en base.<br/>
	* <br/>
	*
	* @param pId : Long : valeur à passer à this.id.<br/>
	*/
	void setId(Long pId);
	


	/**
	 * method getName() :<br/>
	 * Getter du name du Country.<br/>
	 * <ul>
	 * <li>"RG_NOMMAGE_NAME_RENSEIGNE_01 : 
	 * le name du Country doit être renseigné (obligatoire)".</li>
	 * <li>"RG_NOMMAGE_NAME_LITTERAL_02 : 
	 * le name du Country 
	 * ne doit contenir que des lettres ou des 
	 * caractères spéciaux '-', '_', ... (aucun chiffre)".</li>
	 * <li>"RG_NOMMAGE_NAME_LONGUEUR_03 : 
	 * le name du Country doit contenir 
	 * entre [1] et [30] lettres".</li>
	 * </ul>
	 *
	 * @return this.name : String.<br/>
	 */
	String getName();
	
	

	/**
	* method setName(
	* String pName) :<br/>
	* Setter du name du Country.<br/>
	* <br/>
	*
	* @param pName : String : 
	* valeur à passer à this.name.<br/>
	*/
	void setName(String pName);
	
	

	/**
	 * method getCapital() :<br/>
	 * Getter du capital du Country.<br/>
	 * <ul>
	 * <li>"RG_NOMMAGE_CAPITAL_RENSEIGNE_04
	 *  : le capital du Country doit être renseigné (obligatoire)".<br/></li>
	 *  <li>"RG_NOMMAGE_CAPITAL_LITTERAL_05
	 *  : le capital du Country 
	 *  ne doit contenir que des lettres ou des caractères spéciaux 
	 *  '-', '_', ... (aucun chiffre)".</li>
	 *  <li>
	 *  "RG_NOMMAGE_CAPITAL_LONGUEUR_06
	 *  : le capital du Country doit contenir entre 
	 *  [1] et [50] lettres".
	 *  </li>
	 * </ul>
	 *
	 * @return this.capital : String.<br/>
	 */
	String getCapital();
	
	

	/**
	* method setCapital(
	* String pCapital) :<br/>
	* Setter du capital du Country.<br/>
	* <br/>
	*
	* @param pCapital : String : valeur à passer à this.capital.<br/>
	*/
	void setCapital(String pCapital);
	
		
	
} // FIN DE L'INTERFACE ICountry.--------------------------------------------
