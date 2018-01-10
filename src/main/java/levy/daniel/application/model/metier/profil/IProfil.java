package levy.daniel.application.model.metier.profil;

import java.io.Serializable;
import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;



/**
 * INTERFACE <b>IProfil</b> :<br/>
 * <p>
 * <b>IProfil</b> modélise un un <i>concept</i> de <b></b> 
 * , c'est à dire  <b></b>  
 * <i>une ou plusieurs</i> <b></b>.
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
 * Garantit que tout IProfil sait :
 * </span>
 * </p>
 * <li>se <b>comparer</b> à un autre IProfil.</li>
 * <li>se <b>cloner</b>.</li>
 * <li>s'exporter sous forme <b>csv</b>.</li>
 * <li>s'exporter sous forme <b>JTable</b>.</li>
 * </ul>
 * 
 * 
 * <ul>
 * <p>
 * <span style="text-decoration: underline;">
 * Garantit que tout IProfil possède à minima :
 * </span>
 * </p>
 * <li><b>id</b> pour la mise en base.</li>
 * <li><b></b>.</li>
 * <li><b></b>.</li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">EGALITE METIER</span>
 * </p>
 * <ul>
 * <li>L'<b>égalité metier</b> d'un IProfil est vérifiée sur :</li>
  * <ul>
 * <li><b></b></li>
 * <li><b></b></li>
 * </ul>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">DIAGRAMME DE CLASSES D'IMPLEMENTATION</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../../javadoc/images/classes_implementation_IProfil.png" 
 * alt="classes d'implémentation des IProfil" border="1" align="center" />
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
 * d'un IProfil sont les suivantes :
 * </li>
 * <br/>
 * <table border="1">
 * <tr>
 * <th>Attribut</th><th>Règle de Gestion</th>
 * </tr>
 * 
 * <tr>
 * <td rowspan="3">
 * prenom
 * </td>
 * <td>
 * "RG_NOMMAGE_PRENOM_RENSEIGNE_01 : 
 * le prénom du Nommage doit être renseigné (obligatoire)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOMMAGE_PRENOM_LITTERAL_02 : 
 * le prénom du Nommage 
 * ne doit contenir que des lettres ou des 
 * caractères spéciaux '-', '_', ... (aucun chiffre)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOMMAGE_PRENOM_LONGUEUR_03 : 
 * le prénom du Nommage doit contenir 
 * entre [1] et [30] lettres"
 * </td>
 * </tr>
 * 
 * <tr>
 * <td rowspan="3">
 * nom
 * </td>
 * <td>
 * "RG_NOMMAGE_NOM_RENSEIGNE_04
 *  : le nom du Nommage doit être renseigné (obligatoire)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOMMAGE_NOM_LITTERAL_05
 *  : le nom du Nommage 
 *  ne doit contenir que des lettres ou des caractères spéciaux 
 *  '-', '_', ... (aucun chiffre)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOMMAGE_NOM_LONGUEUR_06
 *  : le nom du Nommage doit contenir entre 
 *  [1] et [50] lettres"
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
 * @since 27 déc. 2017
 *
 */
public interface IProfil extends IExportateurCsv, IExportateurJTable, Comparable<IProfil>, Cloneable, Serializable {



	/**
	 * method compareTo(
	 * IProfil pObject) :<br/>
	 * <ol>
	 * Comparaison de 2 IProfil par rapport : <br/>
	 * <li>.</li>
	 * </ol>
	 *
	 * @param pObject : IProfil.<br/>
	 * 
	 * @return : int : négatif si la présente instance 
	 * est "avant" pObject.<br/>
	 */
	@Override
	int compareTo(IProfil pObject);



	/**
	 * method clone() :<br/>
	 * Clone un IProfil.<br/>
	 * <br/>
	 *
	 * @return IProfil : clone.<br/>
	 * 
	 * @throws CloneNotSupportedException
	 */
	IProfil clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête csv pour un IProfil</b> :<br/>
	 * ";".<br/>
	 * <br/>
	 */
	@Override
	String getEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>ligne csv pour un IProfil</b> :<br/>
	 * ";".<br/>
	 * <br/>
	 */
	@Override
	String toStringCsv();



	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête Jtable pour un IProfil</b> :<br/>
	 * ";".<br/>
	 * <br/>
	 */
	@Override
	String getEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>ligne Jtable pour un IProfil</b> :<br/>
	 * ";".<br/>
	 * <br/>
	 */
	@Override
	Object getValeurColonne(int pI);



	/**
	 * method getId() :<br/>
	 * Getter de l'ID en base.<br/>
	 * <br/>
	 *
	 * @return id : Long.<br/>
	 */
	Long getId();



	/**
	* method setId(
	* Long pId) :<br/>
	* Setter de l'ID en base.<br/>
	* <br/>
	*
	* @param pId : Long : valeur à passer à id.<br/>
	*/
	void setId(Long pId);



} // FIN DE L'INTERFACE IProfil.---------------------------------------------