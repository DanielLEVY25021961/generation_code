package levy.daniel.application.model.metier.profil;

import java.io.Serializable;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;



/**
 * INTERFACE <b>IProfil</b> :<br/>
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
 * @since 7 janv. 2018
 *
 */
public interface IProfil 
		extends IExportateurCsv, IExportateurJTable
			, Comparable<IProfil>, Cloneable, Serializable {



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
