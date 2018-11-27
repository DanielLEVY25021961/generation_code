package levy.daniel.application.model.persistence.metier.country.dao.jpaspring;

import java.util.List;

import levy.daniel.application.model.metier.country.ICountry;

/**
 * INTERFACE ICountryDAO :<br/>
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
 * @since 11 nov. 2018
 *
 */
public interface ICountryDAO {

	
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
	 * @param pObject : ICountry : 
	 * l'objet métier à persister dans le stockage.<br/>
	 * 
	 * @return ICountry : 
	 * l'objet métier persisté dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	ICountry create(ICountry pObject) throws Exception;
	
	

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
	 * @param pObject : ICountry : 
	 * l'objet métier à persister dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	void persist(ICountry pObject) throws Exception;
	
	

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
	 * @param pObject : ICountry : 
	 * l'objet métier à persister dans le stockage.<br/>
	 * 
	 * @return : Long : 
	 * identifiant (ou index 0-based) 
	 * de l'objet persisté dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	Long createReturnId(ICountry pObject) throws Exception;
	
	

	/**
	 * <b>crée un iterable d'objets métier pList 
	 * dans le stockage</b> et retourne l'itérable persisté.<br/>
	 * <ul>
	 * <li>ne crée <b>pas de doublon</b>.</li>
	 * <li>ne fait rien et continue la sauvegarde si un objet 
	 * dans l'itérable existe déjà dans le stockage.</li>
	 * <li>ne fait rien et continue la sauvegarde si un objet 
	 * null est contenu dans l'itérable.</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : Iterable&lt;ICountry&gt; : 
	 * itérable d'objets métier à persister dans le stockage.<br/>
	 * 
	 * @return Iterable&lt;ICountry&gt; : 
	 * itérable d'objets métier persistés dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	Iterable<ICountry> save(Iterable<ICountry> pList) throws Exception;
	
	

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
	 * @param pObject : ICountry : 
	 * objet métier à rechercher.<br/>
	 * 
	 * @return : ICountry : 
	 * objet métier recherché.<br/>
	 * 
	 * @throws Exception
	 */
	ICountry retrieve(ICountry pObject) throws Exception;
	
	

	/**
	 * <b>recherche un objet métier pObject via son identifiant (index) 
	 * dans le stockage</b> et retourne l'objet métier persisté.<br/>
	 * <ul>
	 * <li>retourne null si l'objet métier 
	 * n'existe pas dans le stockage.</li>
	 * </ul>
	 *
	 * @param pId : Long : 
	 * index (0-based) ou identifiant en base 
	 * de l'objet métier à rechercher.<br/>
	 * 
	 * @return : ICountry : objet métier recherché.<br/>
	 * 
	 * @throws Exception
	 */
	ICountry findById(Long pId) throws Exception;
	
	

	/* READ *************/


	/**
	 * <b>retourne l'identifiant ou l'index (0-based) 
	 * de l'objet métier pObject dans le stockage</b>.<br/>
	 *
	 * @param pObject : ICountry : 
	 * objet métier dont on recherche l'identifiant.<br/>
	 *  
	 * @return Long : 
	 * identifiant ou index (0-based) dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	Long retrieveId(ICountry pObject) throws Exception;
	
	

	/**
	 * <b>retourne la liste de tous les objets métier 
	 * persistés dans le stockage</b>.<br/>
	 * - peut retourner null si le stockage ne peut être lu.<br/>
	 *
	 * @return : List&lt;ICountry&gt; : 
	 * liste de tous les objets métier persistés dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	List<ICountry> findAll() throws Exception;
	
	

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
	 * @return : List&lt;ICountry&gt; : 
	 * liste des pMax objets métier persistés dans le stockage 
	 * à partir de pStartPosition (0-based).<br/>
	 * 
	 * @throws Exception
	 */
	List<ICountry> findAllMax(
			int pStartPosition, int pMaxResult) throws Exception;
	
	

	/**
	 * method findAll(
	 * Iterable&lt;Long&gt; pIds) :<br/>
	 * <ul>
	 * <li>retourne une Collection iterable d'Objets métier 
	 * (List&lt;ICountry&gt;) dont les IDs appartiennent 
	 * à la Collection itérable d'IDs passée en paramètre.</li>
	 * <li>Inclut dans la liste les sous-classe de ICountry 
	 * (strategy=InheritanceType.JOINED) 
	 * avec la visibilité (Typé) ICountry.</li>
	 * </ul>
	 * retourne null si pIds == null.<br/>
	 * ne retourne que les objets de la collection 
	 * effectivement persistés en base.<br/>
	 * <br/>
	 *
	 * @param pIds : Iterable&lt;Long&gt;.<br/>
	 * 
	 * @return Iterable&lt;ICountry&gt; : List&lt;ICountry&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	Iterable<ICountry> findAll(Iterable<Long> pIds) throws Exception;



	/* UPDATE *************/

	
	/**
	 * method update(
	 * ICountry pObject) :<br/>
	 * <ul>
	 * <li><b>Modifie</b> un objet métier <b>persistant</b> 
	 * existant en base.</li>
	 * <li>Retourne l'objet métier pObject <b>modifié en base</b> 
	 * avec la visibilité (Typé) ICountry.</li>
	 * </ul>
	 * retourne null si pObject == null.<br/>
	 * ne fait rien et retourne l'instance détachée 
	 * si pObject n'est pas déjà persistant en base.<br/>
	 * <br/>
	 * <code>Exemple de code : </code><br/>
	 * <code>// Récupération de l'objet persistant à modifier.</code><br/>
	 * <code>objet1Persistant = this.daoUserSimple.retrieve(objet1);</code><br/>
	 * <code>// Modifications.</code><br/>
	 * <code>objet1Persistant.setPrenom("Jean-Frédéric modifié");</code><br/>
	 * <code>objet1Persistant.setNom("Bôrne modifié");</code><br/>
	 * <code>// Application des modifications en base.</code><br/>
	 * <code>objet1ModifiePersistant = 
	 * this.daoUserSimple.<b>update(objet1Persistant)</b>;</code><br/>
	 * <br/>
	 *
	 * @param pObject : ICountry : objet métier avec ID 
	 * et comportant les modifications.<br/>
	 * 
	 * @return : T : objet métier de Type paramétré 
	 * T modifié en base.<br/>
	 * 
	 * @throws Exception 
	 */
	ICountry update(ICountry pObject) throws Exception;
	
	

	/**
	 * <b>modifie dans le stockage 
	 * l'objet d'index (0-based) ou d'identifiant pId 
	 * avec les valeurs 
	 * contenues dans pObjectModifie</b>.<br/>
	 * <ul>
	 * <li><b>pId doit correspondre à l'index (0-based) 
	 * de l'objet métier à modifier</b>.</li>
	 * <li>pObjectModifie est un conteneur contenant les modifications 
	 * à apporter à un objet persistant dans le stockage. 
	 * Il n'a pas besoin d'avoir d'ID.</li>
	 * <li>retourne null s'il n'y a pas d'objet persistant à pId.</li>
	 * <li>retourne null si pId est en dehors des indexes.</li>
	 * </ul>
	 * - retourne null si pId == null.<br/>
	 * - retourne null si pObjectModifie == null.<br/>
	 * <br/>
	 *
	 * @param pId : Long : 
	 * index (0-based) de l'objet métier à modifier.<br/>
	 * @param pObjectModifie : ICountry : 
	 * Objet métier contenant les modifications 
	 * à apporter à l'objet persistant dans le stockage.<br/>
	 * 
	 * @return ICountry : objet métier persistant modifié.<br/>
	 * 
	 * @throws Exception
	 */
	ICountry update(Long pId, ICountry pObjectModifie) throws Exception;



	/* DELETE *************/


	/**
	 * <b>retire l'objet métier pObject dans le stockage</b>.<br/>
	 * retourne true si le retrait a bien été effectué.<br/>
	 * <ul>
	 * <li>retourne false si pObject n'est pas persisté.</li>
	 * </ul>
	 * - retourne false si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : ICountry : objet métier à détruire.<br/>
	 * 
	 * @return : boolean : 
	 * true si l'objet métier a été détruit.<br/>
	 * 
	 * @throws Exception
	 */
	boolean delete(ICountry pObject) throws Exception;
	
	

	/**
	 * <b>retire l'objet métier d'identifiant ou 
	 * d'index (0-based) pId dans le stockage</b>.<br/>
	 * <ul>
	 * <li>ne fait rien si pId est hors indexes.</li>
	 * </ul>
	 * - ne fait rien si pId == null.<br/>
	 * <br/>
	 *
	 * @param pId : Long : 
	 * index (0-based) de l'objet métier à modifier.<br/>
	 * 
	 * @throws Exception
	 */
	void deleteById(Long pId) throws Exception;
	
	

	/**
	 * <b>retire l'objet métier d'identifiant ou 
	 * d'index (0-based) pId dans le stockage</b>.<br/>
	 * retourne true si le retrait à bien été effectué.<br/>
	 * <ul>
	 * <li>retourne false si pId est hors indexes.</li>
	 * </ul>
	 * - retourne false si pId == null.<br/>
	 * <br/>
	 *
	 * @param pId : Long : 
	 * index (0-based) de l'objet métier à modifier.<br/>
	 * 
	 * @return boolean : true si le retrait à bien été effectué.<br/>
	 * 
	 * @throws Exception 
	 */
	boolean deleteByIdBoolean(Long pId) throws Exception;
	
	

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
	 * ne retourne rien.<br/>
	 * <ul>
	 * <li>ne fait rien et continue le processus de retrait 
	 * si un objet de l'itérable n'est pas persisté.</li>
	 * </ul>
	 * - ne fait rien si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : Iterable&lt;ICountry&gt; : 
	 * itérable d'objets à retirer du stockage.<br/>
	 *  
	 * @throws Exception
	 */
	void deleteIterable(Iterable<ICountry> pList) throws Exception;
	
	

	/**
	 * <b>retire tous les objets de l'itérable pList déjà persistés 
	 * dans le stockage</b>.<br/>
	 * retourne true si le retrait a bien été effectué.<br/>
	 * <ul>
	 * <li>ne fait rien et continue le processus de retrait 
	 * si un objet de l'itérable n'est pas persisté.</li>
	 * </ul>
	 * - retourne false si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : Iterable&lt;ICountry&gt; : 
	 * itérable d'objets àretirer du stockage?<br/>
	 * 
	 * @return : boolean : true si le retrait a bien été effectué.<br/>
	 * 
	 * @throws Exception
	 */
	boolean deleteIterableBoolean(Iterable<ICountry> pList) throws Exception;



	/* TOOLS *************/


	/**
	 * <b>retourne true si l'objet métier pObject 
	 * existe dans le stockage</b>.<br/>
	 * - retourne false si pObject == null.<br/>
	 * - retourne false si l'Objet métier pObject n'existe pas en base.<br/>
	 * <br/>
	 *
	 * @param pObject : ICountry : objet métier à rechercher.<br/>
	 * 
	 * @return boolean : 
	 * true si l'objet métier pObject existe dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	boolean exists(ICountry pObject) throws Exception;
	
	

	/**
	 * <b>retourne true si l'objet métier pObject 
	 * d'identifiant ou d'index (0-based) pId 
	 * existe dans le stockage</b>.<br/>
	 * - retourne false si pId == null.<br/>
	 * <br/>
	 *
	 * @param pId : Long : 
	 * identifiant ou index (0-based) de l'objet métier à 
	 * trouver dans le stockage.<br/>
	 * 
	 * @return boolean : 
	 * true si l'objet métier existe dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	boolean exists(Long pId) throws Exception;
	
	

	/**
	 * <b>retourne le nombre total d'objets métier</b> 
	 * stockés dans le stockage.<br/>
	 * - retourne 0L si this.findAll() retourne null.<br/>
	 * <br/>
	 *
	 * @return : Long : 
	 * nombre d'enregistrements dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	Long count() throws Exception;
	
	

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
	 * @param pList : List&lt;ICountry&gt;.<br/>
	 * 
	 * @return : String.<br/>
	 */
	String afficherListeObjetsMetier(List<ICountry> pList);
	
	
	
} // FIN DE L'INTERFACE ICountryDAO.-----------------------------------------
