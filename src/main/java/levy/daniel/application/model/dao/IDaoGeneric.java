package levy.daniel.application.model.dao;

import java.io.Serializable;
import java.util.List;

/**
 * class IDaoGeneric :<br/>
 * .<br/>
 * <br/>
 * - Exemple d'utilisation :<br/>
 * <br/>
 * - Mots-clé :<br/>
 * <br/>
 * - Dépendances :<br/>
 * <br/>
 *
 * @author dan Lévy
 * @version 1.0
 * @param <T> : Type paramétré : Classe réelle d'un Objet métier.<br/>
 * @param <ID> : Type paramétré : type de l'ID en base d'un Objet métier 
 * (Long, Integer, String, ...).<br/>
 * 
 * @since 8 sept. 2017
 */
public interface IDaoGeneric<T, ID extends Serializable> {
	
	

	/* CREATE ************/

	/**
	 * method create(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Crée un Objet metier de type paramétré T pObject en base.</li>
	 * <li>retourne l'Objet métier de type paramétré T créé en base.</li>
	 * </ul>
	 * retourne null si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : T : Objet métier de type paramétré T.<br/>
	 * 
	 * @return : T : Objet métier de type paramétré T créé en base.<br/>
	 */
	T create(T pObject);

	
	
	/**
	 * method persist(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Crée un Objet metier de type paramétré T pObject en base.</li>
	 * <li>Ne retourne rien.</li>
	 * </ul>
	 * ne fait rien si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : T : Objet métier de type paramétré T.<br/>
	 */
	void persist(T pObject);
	

	
	/**
	 * method createReturnId(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Crée un Objet metier de type paramétré T pObject.</li>
	 * <li>retourne l'ID de l'Objet métier de type paramétré T créé.</li>
	 * </ul>
	 * retourne null si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : T : Objet métier de type paramétré T.<br/>
	 * 
	 * @return : ID : Objet métier de type paramétré T créé.<br/>
	 */
	ID createReturnId(T pObject);
	
	

	/* READ *************/

	/**
	 * method retrieve(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Recherche un objet métier de Type 
	 * paramétré T pObject en base.</li>
	 * <li>Retourne l'objet métier de Type 
	 * paramétré T pObject trouvé en base.</li>
	 * </ul>
	 * Retourne null si aucun objet equals à pObject 
	 * est retrouvé en base.<br/>
	 * La base doit avoir un index d'unicité sur equals(...).<br/>
	 * <br/>
	 *
	 * @param pObject : T : objet métier de Type paramétré T.<br/>
	 * 
	 * @return : T : objet métier de Type paramétré T 
	 * existant en base.<br/>
	 */
	T retrieve(T pObject);
	

	
	/**
	 * method findById(
	 * ID pId) :<br/>
	 * <ul>
	 * <li>Recherche un Objet métier de Type 
	 * paramétré T via son ID en base.</li>
	 * <li>Retourne l'objet métier de Type 
	 * paramétré T pObject trouvé en base.</li>
	 * <li>ID est de type paramétré ID (Long, Integer, String, ...).</li>
	 * </ul>
	 *
	 * @param pId : ID : ID en base de l'Objet métier.<br/>
	 * 
	 * @return : T : Objet métierde Type paramétré T 
	 * existant en base.<br/>
	 */
	T findById(ID pId);
	
	
	
	/**
	 * method getOne(
	 * Long pId) :<br/>
	 * <ul>
	 * <li>Recherche un Objet métier de Type 
	 * paramétré T via son ID en base.</li>
	 * <li>Retourne l'objet métier de Type 
	 * paramétré T pObject trouvé en base.</li>
	 * <li>Compatible avec SpringData.</li>
	 * </ul>
	 *
	 * @param pId : Long : ID en base de l'Objet métier.
	 * 
	 * @return : T : Objet métierde Type paramétré T 
	 * existant en base.<br/>
	 */
	T getOne(Long pId);
	
	

	/**
	 * method findAll() :<br/>
	 * Retourne la liste de tous les objets métier de Type paramétré T 
	 * présents en base.<br/>
	 * <br/>
	 *
	 * @return : List&lt;T&gt; : 
	 * liste de tous les objets métier de Type paramétré T 
	 * présents en base.<br/>
	 */
	List<T> findAll();

	
	
	/**
	 * method findAll(
	 * Long pMax) :<br/>
	 * Retourne la liste des pMax premiers objets métier 
	 * de Type paramétré T présents en base.<br/>
	 * <br/>
	 * 
	 * @param pMax : Long : Nombre maximal d'objets métier 
	 * à remonter de la base.<br/>
	 *
	 * @return : List&lt;T&gt; : liste des pMax premiers objets métier 
	 * de Type paramétré T présents en base.<br/>
	 */
	List<T> findAllMax(Long pMax);
	
	

	/**
	 * method findAll(
	 * Iterable&lt;ID&gt; pIds) :<br/>
	 * Returns all instances of the type T with the given IDs.<br/>
	 * <br/>
	 *
	 * @param pIds : Iterable&lt;ID&gt;.<br/>
	 * 
	 * @return Iterable&lt;T&gt;.<br/>
	 */
	Iterable<T> findAll(Iterable<Long> pIds);
	
	
	

	/* UPDATE *************/

	
	/**
	 * method update(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Modifie un objet métier de Type paramétré T pObject 
	 * existant en base.</li>
	 * <li>Retourne l'objet métier de Type paramétré T 
	 * pObject modifié en base</li>
	 * </ul>
	 *
	 * @param pObject : T : objet métier de Type paramétré T.<br/>
	 * 
	 * @return : T : objet métier de Type paramétré 
	 * T modifié en base.<br/>
	 */
	T update(T pObject);
	
	

	/**
	 * method save(
	 * S pObject) :<br/>
	 * <ul>
	 * <li>Sauvegarde l'objet métier pObject de type paramétré S 
	 * (S pouvant être n'importe quelle sous-classe de T) 
	 * en base.</li>
	 * <li>Retourne l'instance sauvegardée en base.</li>
	 * </ul>
	 *
	 * @param pObject : S : objet métier de Type paramétré S 
	 * où S est une sous-classe de T.<br/>
	 * 
	 * @return : S : Objet métier de type paramétré S 
	 * (n'importe quelle sous-classe de T) créé en base.<br/>
	 */
	<S extends T> S save(S pObject);
	
	

	/**
	 * method save(
	 * Iterable&lt;S&gt; pObjects) :<br/>
	 * <ul>
	 * <li>Sauvegarde en base tous les objets métier de type S 
	 * (S pouvant être n'importe quelle sous-classe de T) 
	 * contenus dans la collection itérable d'objets métier 
	 * de type S "pObjects".</li>
	 * <li>Retourne la Collection itérable d'objets de type S 
	 * (sous-classes de T) sauvegardés en base.</li>
	 * </ul>
	 *
	 * @param pObjects : Iterable&lt;S&gt; : 
	 * collection itérable d'objets métier de type S 
	 * (S pouvant être n'importe quelle sous-classe de T).<br/>
	 *  
	 * @return : Iterable&lt;S&gt; : La Collection itérable d'objets 
	 * de type S (sous-classes de T) sauvegardés en base.<br/>
	 * 
	 * @throws IllegalArgumentException in case the given 
	 * entity is {@literal null}.
	 */
	<S extends T> Iterable<S> save(Iterable<S> pObjects);

	
	
	/* DELETE *************/

	
	/**
	 * method delete(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Détruit un un objet métier de Type paramétré T pObject
	 * existant en base.</li>
	 * <li>Retourne un boolean (true si OK) précisant 
	 * si l'opération de destruction a eu lieu.</li>
	 * </ul>
	 *
	 * @param pObject : T : objet métier de Type paramétré T.<br/>
	 * 
	 * @return : boolean : true si l'objet métier de Type paramétré T 
	 * a été détruit en base.<br/>
	 */
	boolean delete(T pObject);


	
	/**
	 * method deleteById(
	 * ID pId) :<br/>
	 * Détruit un un objet métier de Type paramétré T 
	 * existant en base via son ID de Type paramétré ID.<br/>
	 * <br/>
	 *
	 * @param pId : ID : ID en base.<br/>
	 */
	void deleteById(ID pId);
	
	
	
	/**
	 * method delete(
	 * Long pId) :<br/>
	 * Détruit un un objet métier de Type paramétré T 
	 * existant en base via son ID.<br/>
	 * <br/>
	 *
	 * @param pId : Long : ID en base.<br/>
	 */
	void delete(Long pId);
	
	
	
	/**
	 * method deleteBoolean(
	 * Long pId) :<br/>
	 * <ul>
	 * <li>Détruit un un objet métier de Type paramétré T 
	 * existant en base via son ID en base.</li>
	 * <li>Retourne un boolean (true si OK) précisant 
	 * si l'opération de destruction a eu lieu.</li>
	 * </ul>
	 *
	 * @param pId : Long : ID en base.<br/>
	 * 
	 * @return boolean : true si l'objet d'ID pId 
	 * a été détruit en base.<br:>
	 */
	boolean deleteBoolean(Long pId);
	


	/**
	 * method deleteAll() :<br/>
	 * Deletes all entities managed by the repository.<br/>
	 * <br/>
	 */
	void deleteAll();
	
	
	
	/**
	 * method delete(
	 * Iterable&lt;? extends T&gt; entities) :<br/>
	 * Détruit en base tous les objets métier de type T 
	 * (ou de n'importe quelle sous-classe de T) 
	 * contenus dans la collection itérable pObjects.<br/>
	 * <br/>
	 *
	 * @param pObjects : Iterable&lt;? extends T&gt; : 
	 * collection itérable d'objets de type T ou sous-classes de T.<br/>
	 * 
	 * @throws IllegalArgumentException in case 
	 * the given {@link Iterable} is {@literal null}.<br/>
	 */
	void delete(Iterable<? extends T> pObjects);
	
	
	

	/* TOOLS *************/

	
	/**
	 * method exists(
	 * ID pId) :<br/>
	 * Returns whether an entity with the given id exists.<br/>
	 * <br/>
	 *
	 * @param pId : ID : must not be {@literal null}.
	 * 
	 * @return true if an entity with the given id exists
	 * , {@literal false} otherwise.<br/>
	 * 
	 * @throws IllegalArgumentException 
	 * if {@code id} is {@literal null}
	 */
	boolean exists(Long pId);
	
	

	/**
	 * method count() :<br/>
	 * Retourne le nombre d'Objets metier 
	 * de type paramétré T présents en base.<br/>
	 * <br/>
	 *
	 * @return : Long : 
	 * le nombre d'Objets metier de type paramétré T présents en base.<br/>
	 */
	Long count();
	

	 
	/**
	 * method getClassObjetMetier() :<br/>
	 * Getter du Class (.Class Reflexion = Introspection) réelle 
	 * de l'Objet métier de Type paramétré T 
	 * concerné par le présent DAO.<br/>
	 * <br/>
	 *
	 * @return classObjetMetier : Class<T>.<br/>
	 */
	Class<T> getClassObjetMetier();



	
	/**
	* method setClassObjetMetier(
	* Class<T> pClassObjetMetier) :<br/>
	* Setter du Class (.Class Reflexion = Introspection) réelle 
	* de l'Objet métier de Type paramétré T 
	* concerné par le présent DAO.<br/>
	* <br/>
	*
	* @param pClassObjetMetier : Class<T> : 
	* valeur à passer à classObjetMetier.<br/>
	*/
	void setClassObjetMetier(Class<T> pClassObjetMetier);



} // FIN DE L'INTERFACE IDaoGeneric.---------------------------------------
