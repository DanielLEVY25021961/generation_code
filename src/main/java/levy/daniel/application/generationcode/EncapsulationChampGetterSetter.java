package levy.daniel.application.generationcode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE EncapsulationChampGetterSetter :<br/>
 * Encapsulation chargée de stocker :
 * <ul>
 * <li>un champ</li>
 * <li>son getter</li>
 * <li>son setter</li>
 * </ul>
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
 * @since 8 juin 2019
 *
 */
public class EncapsulationChampGetterSetter {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "null".
	 */
	public static final String NULL = "null";
	
	/**
	 * ", ".
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	/**
	 * java.lang.reflect.Field dont on veut connaitre 
	 * le getter et le setter.
	 */
	private Field champ;
	
	/**
	 * nom du champ dont on veut connaitre 
	 * le getter et le setter.
	 */
	private String nomChamp;
	
	/**
	 * getter du champ sous forme de java.lang.reflect.Method.
	 */
	private Method getter;
	
	/**
	 * nom du getter du champ.
	 */
	private String nomGetter;
	
	/**
	 * setter du champ sous forme de java.lang.reflect.Method.
	 */
	private Method setter;
	
	/**
	 * nom du Setter du champ.
	 */
	private String nomSetter;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(EncapsulationChampGetterSetter.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public EncapsulationChampGetterSetter() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("EncapsulationChampGetterSetter [");
		
		builder.append("nomChamp=");
		if (this.nomChamp != null) {		
			builder.append(this.nomChamp);			
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("nomGetter=");
		if (this.nomGetter != null) {
			builder.append(this.nomGetter);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("nomSetter=");
		if (this.nomSetter != null) {
			builder.append(this.nomSetter);
		} else {
			builder.append(NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * Getter du java.lang.reflect.Field dont on veut connaitre 
	 * le getter et le setter.
	 *
	 * @return this.champ : java.lang.reflect.Field.<br/>
	 */
	public final Field getChamp() {
		return this.champ;
	} // Fin de getChamp().________________________________________________
	

	
	/**
	* Setter du java.lang.reflect.Field dont on veut connaitre 
	* le getter et le setter.
	*
	* @param pChamp : java.lang.reflect.Field : 
	* valeur à passer à this.champ.<br/>
	*/
	public final void setChamp(
			final Field pChamp) {
		this.champ = pChamp;
	} // Fin de setChamp(...)._____________________________________________


	
	/**
	 * Getter du nom du champ dont on veut connaitre 
	 * le getter et le setter.
	 *
	 * @return this.nomChamp : String.<br/>
	 */
	public final String getNomChamp() {
		return this.nomChamp;
	} // Fin de setChamp(...)._____________________________________________

	
	
	/**
	* Setter du nom du champ dont on veut connaitre 
	* le getter et le setter.
	*
	* @param pNomChamp : String : 
	* valeur à passer à this.nomChamp.<br/>
	*/
	public final void setNomChamp(
			final String pNomChamp) {
		this.nomChamp = pNomChamp;
	} // Fin de setNomChamp(...).__________________________________________
	

	
	/**
	 * Getter du getter du champ sous forme de java.lang.reflect.Method.
	 *
	 * @return this.getter : Method.<br/>
	 */
	public final Method getGetter() {
		return this.getter;
	} // Fin de getGetter()._______________________________________________

	
	
	/**
	* Setter du getter du champ sous forme de java.lang.reflect.Method.
	*
	* @param pGetter : Method : 
	* valeur à passer à this.getter.<br/>
	*/
	public final void setGetter(
			final Method pGetter) {
		this.getter = pGetter;
	} // Fin de setGetter(...).____________________________________________

	
	
	/**
	 * Getter du nom du getter du champ.
	 *
	 * @return this.nomGetter : String.<br/>
	 */
	public final String getNomGetter() {
		return this.nomGetter;
	} // Fin de getNomGetter().____________________________________________

	
	
	/**
	* Setter du nom du getter du champ.
	*
	* @param pNomGetter : String : 
	* valeur à passer à this.nomGetter.<br/>
	*/
	public final void setNomGetter(
			final String pNomGetter) {
		this.nomGetter = pNomGetter;
	} // Fin de setNomGetter(...)._________________________________________

	
	
	/**
	 * Getter du setter du champ sous forme de java.lang.reflect.Method.
	 *
	 * @return this.setter : Method.<br/>
	 */
	public final Method getSetter() {
		return this.setter;
	} // Fin de getSetter()._______________________________________________


	
	/**
	* Setter du setter du champ sous forme de java.lang.reflect.Method.
	*
	* @param pSetter : Method : 
	* valeur à passer à this.setter.<br/>
	*/
	public final void setSetter(
			final Method pSetter) {
		this.setter = pSetter;
	} // Fin de setSetter(...).____________________________________________

	
	
	/**
	 * Getter du nom du Setter du champ.
	 *
	 * @return this.nomSetter : String.<br/>
	 */
	public final String getNomSetter() {
		return this.nomSetter;
	} // Fin de getNomSetter().____________________________________________


	
	/**
	* Setter du nom du Setter du champ.
	*
	* @param pNomSetter : String : 
	* valeur à passer à this.nomSetter.<br/>
	*/
	public final void setNomSetter(
			final String pNomSetter) {
		this.nomSetter = pNomSetter;
	} // Fin de setNomSetter(...)._________________________________________


		
} // FIN DE LA CLASSE EncapsulationChampGetterSetter.------------------------
