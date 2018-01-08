package levy.daniel.application.model.dao;

import java.io.Serializable;
import java.util.List;

import levy.daniel.application.model.dao.daoexceptions.AbstractDaoException;

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
public interface IDaoGenericJPA<T, ID extends Serializable> {
	
	

	/* CREATE ************/

	/**
	 * method create(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Crée un Objet metier de type paramétré T pObject en base.</li>
	 * <li>Crée un Objet metier DESCENDANT de type paramétré S
	 * sous-classe de T pObject en base (strategy=InheritanceType.JOINED) 
	 * et le retourne avec la visibilité (Typé) T.</li>
	 * <li>Nécessite un cast si on veut récupérer la visibilité 
	 * de la sous-classe S.</li>
	 * <li>retourne l'Objet métier de type paramétré T créé en base.</li>
	 * </ul>
	 * retourne null si pObject == null.<br/>
	 * jette une AbstractDaoException si l'objet n'a pu être créé 
	 * (doublon, exception, ...).<br/>
	 * <br/>
	 *
	 * @param pObject : T : Objet métier de type paramétré T.<br/>
	 * 
	 * @return : T : Objet métier de type paramétré T créé en base.<br/>
	 * 
	 * @throws AbstractDaoException en cas d'exception de persistence.<br/>
	 */
	T create(T pObject) throws AbstractDaoException;

	

	/**
	 * method save(
	 * S pObject) :<br/>
	 * <ul>
	 * <li>Sauvegarde l'objet métier pObject de type paramétré S 
	 * (S pouvant être un T ou n'importe quelle sous-classe de T) 
	 * en base.</li>
	 * <li>Crée un Objet metier DESCENDANT de type paramétré S
	 * sous-classe de T pObject en base (strategy=InheritanceType.JOINED) 
	 * et le retourne avec la visibilité (Typé) S.</li>
	 * <li>Utile par rapport à create() pour éviter de caster. 
	 * create() retourne en effet toujours un T</li>
	 * <li>Retourne l'instance sauvegardée en base.</li>
	 * </ul>
	 * retourne null si pObject == null.<br/>
	 * jette une AbstractDaoException si l'objet n'a pu être créé 
	 * (doublon, exception, ...).<br/>
	 * <br/>
	 *
	 * @param pObject : S : objet métier de Type paramétré S 
	 * où S est un T ou une sous-classe de T.<br/>
	 * 
	 * @return : S : Objet métier de type paramétré S 
	 * (T ou n'importe quelle sous-classe de T) créé en base.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	<S extends T> S save(S pObject) throws AbstractDaoException;
	

	
	/**
	 * method persist(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Crée un Objet metier de type paramétré T pObject en base.</li>
	 * <li>Crée un Objet metier DESCENDANT de type paramétré S
	 * sous-classe de T pObject en base 
	 * (strategy=InheritanceType.JOINED).</li>
	 * <li>Ne retourne rien.</li>
	 * </ul>
	 * ne fait rien si pObject == null.<br/>
	 * jette une AbstractDaoException si l'objet n'a pu être créé 
	 * (doublon, exception, ...).<br/>
	 * <br/>
	 *
	 * @param pObject : T : Objet métier de type paramétré T.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	void persist(T pObject) throws AbstractDaoException;
	

	
	/**
	 * method persistSousClasse() :<br/>
	 * <ul>
	 * <li>Crée un Objet metier de type paramétré S 
	 * (S pouvant être un T ou n'importe quelle sous-classe de T) 
	 * en base.</li>
	 * <li>Ne retourne rien.</li>
	 * </ul>
	 * ne fait rien si pObject == null.<br/>
	 * jette une AbstractDaoException si l'objet n'a pu être créé 
	 * (doublon, exception, ...).<br/>
	 * <br/>
	 *
	 * @param pObject : S : objet métier de Type paramétré S 
	 * où S est un T ou une sous-classe de T.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	<S extends T> void persistSousClasse(S pObject) 
			throws AbstractDaoException;

	
	
	/**
	 * method createReturnId(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Crée un Objet metier de type paramétré T pObject.</li>
	 * <li>Crée un Objet metier DESCENDANT de type paramétré S
	 * sous-classe de T pObject en base 
	 * (strategy=InheritanceType.JOINED).</li>
	 * <li>retourne l'ID de l'Objet métier de type paramétré T créé.</li>
	 * </ul>
	 * retourne null si pObject == null.<br/>
	 * jette une AbstractDaoException si pObject n'est pas créé en base 
	 * (Exception, doublon, ...).<br/>
	 * <br/>
	 *
	 * @param pObject : T : Objet métier de type paramétré T.<br/>
	 * 
	 * @return : ID : Objet métier de type paramétré T créé.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	ID createReturnId(T pObject) throws AbstractDaoException;
	
	

	/**
	 * method save(
	 * Iterable&lt;S&gt; pObjects) :<br/>
	 * <ul>
	 * <li>Sauvegarde en base tous les objets métier de type S 
	 * (S pouvant être n'importe quelle sous-classe de T) 
	 * contenus dans la collection itérable d'objets métier 
	 * de type S "pObjects".</li>
	 * <li>Retourne la Collection itérable (List&lt;S&gt;) 
	 * d'objets de type S 
	 * (sous-classes de T) sauvegardés en base.</li>
	 * </ul>
	 * retourne null si pObjects == null.<br/>
	 * jette une AbstractDaoException si pObjects n'a pu être enregistrée 
	 * en base (doublon, Exception, ...).<br/>
	 * <br/>
	 *
	 * @param pObjects : Iterable&lt;S&gt; : 
	 * collection itérable d'objets métier de type S 
	 * (S pouvant être n'importe quelle sous-classe de T).<br/>
	 *  
	 * @return : Iterable&lt;S&gt; : La Collection itérable d'objets 
	 * de type S (sous-classes de T) sauvegardés en base.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	<S extends T> Iterable<S> save(Iterable<S> pObjects) 
				throws AbstractDaoException;

	

	/* READ *************/

	
	/**
	 * method retrieve(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Recherche un objet métier de Type 
	 * paramétré T pObject en base.</li>
	 * <li>Recherche un objet métier DESCENDANT de Type 
	 * paramétré S sous-classe de T pObject en base 
	 * (strategy=InheritanceType.JOINED).</li>
	 * <li>Retourne l'objet métier de Type 
	 * paramétré T pObject (ou sous-classe) trouvé en base 
	 * avec la visibilité (Typé) T.</li>
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
	 * 
	 * @throws AbstractDaoException 
	 */
	T retrieve(T pObject) throws AbstractDaoException;
	

	
	/**
	 * method findById(
	 * ID pId) :<br/>
	 * <ul>
	 * <li>Recherche un Objet métier de Type 
	 * paramétré T via son ID en base.</li>
	 * <li>Recherche un objet métier DESCENDANT de Type 
	 * paramétré S sous-classe de T pObject en base 
	 * (strategy=InheritanceType.JOINED).</li>
	 * <li>Retourne l'objet métier de Type 
	 * paramétré T pObject trouvé en base 
	 * avec la visibilité (Typé) T.</li>
	 * <li>ID est de type paramétré ID (Long, Integer, String, ...).</li>
	 * </ul>
	 * retourne null si pId n'existe pas en base.<br/>
	 * <br/>
	 *
	 * @param pId : ID : ID en base de l'Objet métier.<br/>
	 * 
	 * @return : T : Objet métier de Type paramétré T 
	 * existant en base.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	T findById(ID pId) throws AbstractDaoException;
	
	
	
	/**
	 * method getOne(
	 * Long pId) :<br/>
	 * <ul>
	 * <li>Recherche un Objet métier de Type 
	 * paramétré T via son ID en base.</li>
	 * <li>Recherche un objet métier DESCENDANT de Type 
	 * paramétré S sous-classe de T pObject en base 
	 * (strategy=InheritanceType.JOINED).</li>
	 * <li>Retourne l'objet métier de Type 
	 * paramétré T pObject trouvé en base 
	 * avec la visibilité (Typé) T.</li>
	 * <li>ID est de type paramétré ID (Long, Integer, String, ...).</li>
	 * <li>Compatible avec SpringData.</li>
	 * </ul>
	 * retourne null si pId n'existe pas en base.<br/>
	 * <br/>
	 *
	 * @param pId : ID : ID en base de l'Objet métier.
	 * 
	 * @return : T : Objet métier de Type paramétré T 
	 * existant en base.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	T getOne(ID pId) throws AbstractDaoException;
	
	

	/**
	 * method findAll() :<br/>
	 * <ul>
	 * <li>Retourne la liste de tous les objets métier de Type paramétré T 
	 * présents en base.</li>
	 * <li>Inclut dans la liste les sous-classes S de T 
	 * (strategy=InheritanceType.JOINED) avec la visibilité (Typé) T.</li>
	 * </ul>
	 *
	 * @return : List&lt;T&gt; : 
	 * liste de tous les objets métier de Type paramétré T 
	 * présents en base.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	List<T> findAll() throws AbstractDaoException;

	
	
	/**
	 * method findAll(
	 * Long pMax) :<br/>
	 * <ul>
	 * <li>Retourne la liste des pMax premiers Objets métier 
	 * de Type paramétré T présents en base 
	 * et retournés par listAll().</li>
	 * <li>Inclut dans la liste les sous-classes S de T 
	 * (strategy=InheritanceType.JOINED) avec la visibilité (Typé) T.</li>
	 * </ul>
	 * retourne null si pMax == null.<br/>
	 * <br/>
	 * 
	 * @param pMax : Long : Nombre maximal d'objets métier 
	 * à remonter de la base.<br/>
	 *
	 * @return : List&lt;T&gt; : 
	 * liste des pMax premiers objets métier 
	 * de Type paramétré T présents en base.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	List<T> findAllMax(Long pMax) throws AbstractDaoException;
	
	

	/**
	 * method findAll(
	 * Iterable&lt;ID&gt; pIds) :<br/>
	 * <ul>
	 * <li>retourne une Collection iterable d'Objets métier 
	 * de Type paramétré T (List&lt;T&gt;) dont les IDs appartiennent 
	 * à la Collection itérable d'IDs.</li>
	 * <li>Inclut dans la liste les Type S sous-classe de T 
	 * (strategy=InheritanceType.JOINED) avec la visibilité (Typé) T.</li>
	 * </ul>
	 * retourne null si pIds == null.<br/>
	 * ne retourne que les objets de la collection 
	 * effectivement persistés en base.<br/>
	 * <br/>
	 *
	 * @param pIds : Iterable&lt;ID&gt;.<br/>
	 * 
	 * @return Iterable&lt;T&gt; : List&lt;T&gt;.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	Iterable<T> findAll(Iterable<ID> pIds) throws AbstractDaoException;
	
	
	

	/* UPDATE *************/

	
	/**
	 * method update(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Modifie un objet métier de Type paramétré T pObject 
	 * (ou un sous-type S de T) existant en base.</li>
	 * <li>Retourne l'objet métier de Type paramétré T 
	 * pObject modifié en base avec la visibilité (Typé) T.</li>
	 * </ul>
	 * retourne null si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : T : objet métier de Type paramétré T.<br/>
	 * 
	 * @return : T : objet métier de Type paramétré 
	 * T modifié en base.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	T update(T pObject) throws AbstractDaoException;
	
	
	

	
	/* DELETE *************/

	
	/**
	 * method delete(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Détruit un Objet métier de Type paramétré T pObject
	 * existant en base.</li>
	 * <li>Détruit un Objet métier de Type paramétré S 
	 * sous-classe de T existant en base 
	 * (strategy=InheritanceType.JOINED).</li>
	 * <li>Retourne un boolean (true si OK) précisant 
	 * si l'opération de destruction a eu lieu.</li>
	 * </ul>
	 * retourne false si pObject == null.<br/>
	 * retourne false si l'objet n'existait pas en base.<br/>
	 * <br/>
	 *
	 * @param pObject : T : objet métier de Type paramétré T 
	 * (ou sous-classe de T).<br/>
	 * 
	 * @return : boolean : true si l'objet métier de Type paramétré T 
	 * a été détruit en base.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	boolean delete(T pObject) throws AbstractDaoException;


	
	/**
	 * method deleteById(
	 * ID pId) :<br/>
	 * <ul>
	 * <li>Détruit un Objet métier de Type paramétré T 
	 * existant en base via son ID de Type paramétré ID.</li>
	 * <li>Détruit un Objet métier DESCENDANT de Type paramétré S 
	 * sous-classe de T existant en base 
	 * (strategy=InheritanceType.JOINED) 
	 * via son ID de Type paramétré ID.</li>
	 * </ul>
	 * ne fait rien si pId est null.<br/>
	 * ne fait rien si pId n'existe pas en base.<br/>
	 * <br/>
	 * 
	 *
	 * @param pId : ID : ID en base.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	void deleteById(ID pId) throws AbstractDaoException;
	
	
		
	/**
	 * method deleteBoolean(
	 * ID pId) :<br/>
	 * <ul>
	 * <li>Détruit un Objet métier de Type paramétré T 
	 * existant en base via son ID en base.</li>
	 * <li>Détruit un Objet métier DESCENDANT de Type paramétré S 
	 * sous-classe de T existant en base 
	 * (strategy=InheritanceType.JOINED) 
	 * via son ID de Type paramétré ID.</li>
	 * <li>Retourne un boolean (true si OK) précisant 
	 * si l'opération de destruction a eu lieu.</li>
	 * </ul>
	 * retourne false si pId est null.<br/>
	 * retourne false si pId n'existe pas en base.<br/>
	 * <br/>
	 *
	 * @param pId : ID : ID en base.<br/>
	 * 
	 * @return boolean : true si l'objet d'ID pId 
	 * a été détruit en base.<br:>
	 * 
	 * @throws AbstractDaoException 
	 */
	boolean deleteByIdBoolean(ID pId) throws AbstractDaoException;
	


	/**
	 * method deleteAll() :<br/>
	 * <ul>
	 * <li>Détruit en base toutes les instances 
	 * d'Objets métier de Type paramétré T.</li>
	 * <li>Détruit en base toutes les instances DESCENDANTES 
	 * (strategy=InheritanceType.JOINED)
	 * d'Objets métier de Type paramétré S (S sous-classe de T).</li>
	 * </ul>
	 * 
	 * @throws AbstractDaoException 
	 */
	void deleteAll() throws AbstractDaoException;
	
	
	
	/**
	 * method deleteAllBoolean() :<br/>
	 * <ul>
	 * <li>Détruit en base tous les enregistrements 
	 * d'Objets métier de Type paramétré T.</li>
	 * <li>Détruit en base toutes les instances DESCENDANTES 
	 * (strategy=InheritanceType.JOINED)
	 * d'Objets métier de Type paramétré S (S sous-classe de T).</li>
	 * <li>Retourne true si la destruction a bien été effectuée.</li>
	 * </ul>
	 * @return boolean : true si tous les enregistrements 
	 * ont été détruits en base.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	boolean deleteAllBoolean() throws AbstractDaoException;
	
	
	
	/**
	 * method delete(
	 * Iterable&lt;? extends T&gt; pObjects) :<br/>
	 * <ul>
	 * <li>Détruit en base tous les objets métier de type T 
	 * (ou de n'importe quelle sous-classe de T) 
	 * contenus dans la collection itérable pObjects.</li>
	 * </ul>
	 * ne fait rien si pObjects == null.<br/>
	 * ne plante pas si un des objets de la collection 
	 * n'existe pas en base.<br/>
	 * <br/>
	 *
	 * @param pObjects : Iterable&lt;? extends T&gt; : 
	 * collection itérable d'objets de type T ou sous-classes de T.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	void delete(Iterable<? extends T> pObjects) throws AbstractDaoException;
	
	
	

	/* TOOLS *************/

	
	/**
	 * method exists(
	 * T pObject) :<br/>
	 * <ul>
	 * <li>Retourne un boolean permettant de déterminer si l'Objet métier 
	 * de Type paramétré T pObject est persisté en base.</li>
	 * <li>Retourne un boolean permettant de déterminer si l'Objet métier 
	 * DESCENDANT de Type paramétré S (S sous-classe de T) pObject est 
	 * persisté en base (strategy=InheritanceType.JOINED).</li>
	 * <li>Retourne true si l'Objet métier a été trouvé en base.</li>
	 * </ul>
	 * retourne false si l'objet n'existe pas en base.<br/>
	 * <br/>
	 *
	 * @param pObject : T : objet métier de Type paramétré T 
	 * (ou sous-classe de T).<br/>
	 * 
	 * @return boolean : true si l'objet métier de Type paramétré T 
	 * existe en base.<br/>
	 * 
	 * @throws AbstractDaoException
	 */
	boolean exists(T pObject) throws AbstractDaoException;
	
	
	
	/**
	 * method exists(
	 * ID pId) :<br/>
	 * <ul>
	 * <li>Retourne un boolean permettant de déterminer si un Objet métier 
	 * de Type paramétré T avec ID == pId  est persisté en base.</li>
	 * <li>Retourne un boolean permettant de déterminer si un Objet métier 
	 * DESCENDANT de Type paramétré S (S sous-classe de T) avec ID == pId 
	 * est persisté en base (strategy=InheritanceType.JOINED).</li>
	 * <li>Retourne true si l'Objet métier a été trouvé en base.</li>
	 * </ul>
	 * retourne false si l'objet n'existe pas en base.<br/>
	 * <br/>
	 *
	 * @param pId : ID .<br/>
	 * 
	 * @return boolean : true si l'objet métier de Type paramétré T 
	 * existe en base.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	boolean exists(ID pId) throws AbstractDaoException;
	
	

	/**
	 * method count() :<br/>
	 * <ul>
	 * <li>Retourne le nombre d'Objets metier 
	 * de type paramétré T présents en base.</li>
	 * <li>Compte les Objets metier de Type paramétré T 
	 * et toutes les sous-classes de T.</li>
	 * </ul>
	 *
	 * @return : Long : 
	 * le nombre d'Objets metier de type paramétré T 
	 * (ou sous-classes de T) présents en base.<br/>
	 * 
	 * @throws AbstractDaoException 
	 */
	Long count() throws AbstractDaoException;
	

	 
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


	
	/**
	 * method afficherListe(
	 * List&lt;T&gt; pListe) :<br/>
	 * Retourne une String pour l'affichage à la console 
	 * d'une liste d'Objets métier de type paramétré T.<br/>
	 * <br/>
	 * retourne null si pListe == null.<br/>
	 * <br/>
	 *
	 * @param pListe : List&lt;T&gt;.<br/>
	 * 
	 * @return : String : String pour affichage à la console.<br/>
	 */
	String afficherListe(List<T> pListe);

	

} // FIN DE L'INTERFACE IDaoGeneric.---------------------------------------
