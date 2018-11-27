package levy.daniel.application.model.persistence.metier;

import java.io.Serializable;
import java.util.List;

/**
 * INTERFACE IDaoGeneric :<br/>
 * <ul>
 * <li>INTERFACE <b>GENERIQUE</b> pour <b>tous les DAO</b>.</li>
 * <li>Interface générique factorisant les comportements 
 * de tous les Data Access Object (DAO) <i>quelle que soit 
 * la technologie</i> (JPA, JAXB XML, JSON, ...).</li>
 * <li>
 * Comporte les définitions des méthodes <b>CRUD</b> valables 
 * pour <b>tous les objets métier</b>.</li>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 7 sept. 2018
 * 
 * @param <T> : Type paramétré : Classe réelle d'un Objet métier.<br/>
 * @param <ID> : Type paramétré : type de l'ID en base d'un Objet métier 
 * (ID, Integer, String, ...).<br/>
 */
public interface IDaoGeneric<T, ID extends Serializable> {
	
	/**
	 * SAUT_LIGNE_JAVA : Character :<br/>
	 * saut de ligne "\n".<br/>
	 */
	String SAUT_LIGNE_JAVA = "\n";



	/* CREATE ************/

	
	/**
	 * <b>crée un objet métier pObject dans le stockage 
	 * et retourne l'objet METIER persisté</b>.<br/>
	 * <ul>
	 * <li>ne crée <b>pas de doublon</b>.</li>
	 * <li>retourne null si pObject existe déjà dans le stockage.</li>
	 * </ul>
	 * - retourne null si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : T : 
	 * l'objet métier à persister dans le stockage.<br/>
	 * 
	 * @return T : 
	 * l'objet métier persisté dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	T create(T pObject) 
			throws Exception;

	
	
	/**
	 * <b>crée un objet métier pObject dans le stockage 
	 * mais ne retourne rien</b>.<br/>
	 * <ul>
	 * <li>ne crée <b>pas de doublon</b>.</li>
	 * <li>ne fait rien si pObject existe déjà dans le stockage.</li>
	 * </ul>
	 * - ne fait rien si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : T : 
	 * l'objet métier à persister dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	void persist(T pObject) 
			throws Exception;
	
	
	
	/**
	 * <b>crée un objet métier pObject dans le stockage 
	 * et retourne l'identifiant de l'objet persisté</b>.<br/>
	 * <ul>
	 * <li>ne crée <b>pas de doublon</b>.</li>
	 * <li>retourne null si pObject existe déjà dans le stockage.</li>
	 * </ul>
	 * - retourne null si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : T : 
	 * l'objet métier à persister dans le stockage.<br/>
	 * 
	 * @return : ID : 
	 * identifiant (ou index 0-based) 
	 * de l'objet persisté dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	ID createReturnId(T pObject) 
			throws Exception;
	
	
	
	/**
	 * <b>crée un iterable d'objets métier pList 
	 * dans le stockage</b> et retourne l'itérable persisté.<br/>
	 * <ul>
	 * <li>ne crée <b>pas de doublon</b>.</li>
	 * <li>ne fait rien et continue la sauvegarde si un objet 
	 * dans l'itérable existe déjà dans le stockage.</li>
	 * </ul>
	 *
	 * @param pList : Iterable&lt;T&gt; : 
	 * itérable d'objets métier à persister dans le stockage.<br/>
	 * 
	 * @return Iterable&lt;T&gt; : 
	 * itérable d'objets métier persistés dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	Iterable<T> save(
			Iterable<T> pList) 
						throws Exception;



	/* READ *************/

	
	/**
	 * <b>recherche un objet métier pObject 
	 * dans le stockage</b> et retourne l'objet métier persisté.<br/>
	 * recherche l'objet métier par son égalité métier 
	 * (equals()).<br/>
	 * <ul>
	 * <li>retourne null si l'objet métier 
	 * n'existe pas dans le stockage.</li>
	 * </ul>
	 *
	 * @param pObject : T : objet métier à rechercher.<br/>
	 * 
	 * @return : T : objet métier recherché.<br/>
	 * 
	 * @throws Exception
	 */
	T retrieve(
			T pObject) 
					throws Exception;
	

	
	/**
	 * <b>recherche un objet métier pObject via son identifiant (index) 
	 * dans le stockage</b> et retourne l'objet métier persisté.<br/>
	 * <ul>
	 * <li>retourne null si l'objet métier 
	 * n'existe pas dans le stockage.</li>
	 * </ul>
	 *
	 * @param pId : ID : 
	 * index (0-based) ou identifiant en base 
	 * de l'objet métier à rechercher.<br/>
	 * 
	 * @return : T : objet métier recherché.<br/>
	 * 
	 * @throws Exception
	 */
	T findById(ID pId) 
					throws Exception;
	

	
	/**
	 * <b>retourne la liste de tous les objets métier 
	 * persistés dans le stockage</b>.<br/>
	 * - peut retourner null si le stockage ne peut être lu.<br/>
	 *
	 * @return : List&lt;T&gt; : 
	 * liste de tous les objets métier persistés dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	List<T> findAll() 
				throws Exception;
	

	
	/**
	 * <b>retourne la liste des pMax objets métier 
	 * persistés dans le stockage</b> à partir de la 
	 * position pStartPosition (0-based).<br/>
	 * <ul>
	 * <li>retourne par exemple les 50 objets métier stockés 
	 * à partir du 100ème.</li>
	 * <li>retourne null si pStartPosition est hors indexes.</li>
	 * </ul>
	 *
	 * @param pStartPosition : int : index (0-based) de départ.<br/>
	 * @param pMaxResult : int : 
	 * nombre maximum d'objets métier à retourner.<br/>
	 * 
	 * @return : List&lt;T&gt; : 
	 * liste des pMax objets métier persistés dans le stockage 
	 * à partir de pStartPosition (0-based).<br/>
	 * 
	 * @throws Exception
	 */
	List<T> findAllMax(
			int pStartPosition, int pMaxResult) 
					throws Exception;

	
	
	/**
	 * <b>retourne l'identifiant ou l'index (0-based) 
	 * de l'objet métier pObject dans le stockage</b>.<br/>
	 *
	 * @param pObject : T : 
	 * objet métier dont on recherche l'identifiant.<br/>
	 *  
	 * @return ID : 
	 * identifiant ou index (0-based) dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	ID retrieveId(T pObject) 
					throws Exception;
	
	

	
	/* UPDATE *************/


	
	/**
	 * <b>modifie dans le stockage 
	 * l'objet d'index (0-based) ou d'identifiant pIndex 
	 * avec les valeurs 
	 * contenues dans pObjectModifie</b>.<br/>
	 * <ul>
	 * <li><b>pIndex doit correspondre à l'index (0-based) 
	 * de l'objet métier à modifier</b>.</li>
	 * <li>retourne null si pIndex est en dehors des indexes.</li>
	 * </ul>
	 *
	 * @param pIndex : ID : 
	 * index (0-based) de l'objet métier à modifier.<br/>
	 * @param pObjectModifie : T : 
	 * Objet métier contenant les modifications 
	 * à apporter à l'objet persistant en base.<br/>
	 * 
	 * @return T : objet métier modifié.<br/>
	 * 
	 * @throws Exception
	 */
	T update(
			ID pIndex
				, T pObjectModifie) 
							throws Exception;
	
	
	
	/**
	 * <b>retire l'objet métier pObject dans le stockage</b>.<br/>
	 * retourne true si le retrait a bien été effectué.<br/>
	 * <ul>
	 * <li>retourne false si pObject n'est pas persisté.</li>
	 * </ul>
	 *
	 * @param pObject : T : objet métier à détruire.<br/>
	 * 
	 * @return : boolean : 
	 * true si l'objet métier a été détruit.<br/>
	 * 
	 * @throws Exception
	 */
	boolean delete(
			T pObject) 
					throws Exception;
	
	
	/**
	 * <b>retire l'objet métier d'identifiant ou 
	 * d'index (0-based) pIndex dans le stockage</b>.<br/>
	 * <ul>
	 * <li>ne fait rien si pIndex est hors indexes.</li>
	 * </ul>
	 *
	 * @param pIndex : ID : 
	 * index (0-based) de l'objet métier à modifier.<br/>
	 * 
	 * @throws Exception
	 */
	void deleteById(ID pIndex) 
						throws Exception;
	
	
	
	/**
	 * <b>retire l'objet métier d'identifiant ou 
	 * d'index (0-based) pIndex dans le stockage</b>.<br/>
	 * retourne true si le retrait à bien été effectué.<br/>
	 * <ul>
	 * <li>retourne false si pIndex est hors indexes.</li>
	 * </ul>
	 *
	 * @param pIndex : ID : 
	 * index (0-based) de l'objet métier à modifier.<br/>
	 * 
	 * @return boolean : true si le retrait à bien été effectué.<br/>
	 * 
	 * @throws Exception 
	 */
	boolean deleteByIdBoolean(ID pIndex) 
								throws Exception;

	
	
	/**
	 * <b>retire tous les objets métier dans le stockage</b>.<br/>
	 *
	 * @throws Exception
	 */
	void deleteAll() throws Exception;
	

	
	/**
	 * <b>retire tous les objets métier dans le stockage</b>.<br/>
	 * retourne true si le retrait a bien été effectué.<br/>
	 *
	 * @return : boolean : 
	 * true si le retrait a bien été effectué.<br/>
	 * 
	 * @throws Exception
	 */
	boolean deleteAllBoolean() throws Exception;
	

	
	/**
	 * <b>retire tous les objets de l'itérable pList déjà persistés 
	 * dans le stockage</b>.<br/>
	 * <ul>
	 * <li>ne fait rien et continue le processus de retrait 
	 * si un objet de l'itérable n'est pas persisté.</li>
	 * </ul>
	 *
	 * @param pList : Iterable&lt;T&gt; : 
	 * itérable d'objets à retirer du stockage.<br/>
	 *  
	 * @throws Exception
	 */
	void deleteIterable(Iterable<T> pList) throws Exception;
	

	
	/**
	 * <b>retire tous les objets de l'itérable pList déjà persistés 
	 * dans le stockage</b>.<br/>
	 * retourne true si le retrait a bien été effectué.<br/>
	 * <ul>
	 * <li>ne fait rien et continue le processus de retrait 
	 * si un objet de l'itérable n'est pas persisté.</li>
	 * </ul>
	 *
	 * @param pList : Iterable&lt;T&gt; : 
	 * itérable d'objets àretirer du stockage?<br/>
	 * 
	 * @return : boolean : true si le retrait a bien été effectué.<br/>
	 * 
	 * @throws Exception
	 */
	boolean deleteIterableBoolean(Iterable<T> pList) 
				throws Exception;
	
	
	

	/* TOOLS *************/

	
	/**
	 * <b>retourne true si l'objet métier pObject 
	 * existe dans le stockage</b>.<br/>
	 *
	 * @param pObject : T : objet métier à rechercher.<br/>
	 * 
	 * @return boolean : 
	 * true si l'objet métier existe dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	boolean exists(
			T pObject) 
					throws Exception;
	
	
	
	/**
	 * <b>retourne true si l'objet métier pObject 
	 * d'identifiant ou d'index (0-based) pIndex 
	 * existe dans le stockage</b>.<br/>
	 *
	 * @param pIndex : ID : 
	 * identifiant ou index (0-based) de l'objet métier à 
	 * trouver dans le stockage.<br/>
	 * 
	 * @return boolean : 
	 * true si l'objet métier existe dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	boolean exists(
			ID pIndex) 
					throws Exception;
	
	
	
	/**
	 * <b>retourne le nombre total d'objets métier</b> 
	 * stockés dans le stockage.<br/>
	 *
	 * @return : Long : 
	 * nombre d'enregistrements dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	Long count() throws Exception;

	
	
	/* AFFICHAGE *************/

	
	
	/**
	 * <b>écrit le contenu du stockage dans la console</b>.<br/>
	 * - ne fait rien si findAll() retourne null.<br/>
	 * <br/>
	 *
	 * @throws Exception
	 */
	void ecrireStockageDansConsole() throws Exception;
	
	

	/**
	 * fournit une String pour l'affichage à la console 
	 * d'une Liste d'Objets métier.<br/>
	 * <br/>
	 * retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;T&gt;.<br/>
	 * 
	 * @return : String.<br/>
	 */
	String afficherListeObjetsMetier(List<T> pList);



} // FIN DE L'INTERFACE IDaoGeneric.-----------------------------------------
