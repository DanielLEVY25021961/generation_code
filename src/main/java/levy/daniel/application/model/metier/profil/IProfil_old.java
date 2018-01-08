package levy.daniel.application.model.metier.profil;

import java.io.Serializable;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;



/**
 * INTERFACE <b>IProfil_old</b> :<br/>
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
public interface IProfil_old 
		extends IExportateurCsv, IExportateurJTable
			, Comparable<IProfil_old>, Cloneable, Serializable {



	/**
	 * method compareTo(
	 * IProfil_old pObject) :<br/>
	 * <ol>
	 * Comparaison de 2 IProfil_old par rapport : <br/>
	 * <li>.</li>
	 * </ol>
	 *
	 * @param pObject : IProfil_old.<br/>
	 * 
	 * @return : int : négatif si la présente instance 
	 * est "avant" pObject.<br/>
	 */
	@Override
	int compareTo(IProfil_old pObject);



	/**
	 * method clone() :<br/>
	 * Clone un IProfil_old.<br/>
	 * <br/>
	 *
	 * @return IProfil_old : clone.<br/>
	 * 
	 * @throws CloneNotSupportedException
	 */
	IProfil_old clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête csv pour un IProfil_old</b> :<br/>
	 * ";".<br/>
	 * <br/>
	 */
	@Override
	String getEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>ligne csv pour un IProfil_old</b> :<br/>
	 * ";".<br/>
	 * <br/>
	 */
	@Override
	String toStringCsv();



	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête Jtable pour un IProfil_old</b> :<br/>
	 * ";".<br/>
	 * <br/>
	 */
	@Override
	String getEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>ligne Jtable pour un IProfil_old</b> :<br/>
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



} // FIN DE L'INTERFACE IProfil_old.---------------------------------------------