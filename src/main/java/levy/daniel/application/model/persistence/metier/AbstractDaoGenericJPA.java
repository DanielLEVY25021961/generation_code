package levy.daniel.application.model.persistence.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.persistence.daoexceptions.AbstractDaoException;
import levy.daniel.application.model.persistence.daoexceptions.GestionnaireDaoException;


/**
 * CLASSE ABSTRAITE <b>AbstractDaoGenericJPA</b> :<br/>
 * <ul>
 * <li><b>DAO ABSTRAIT GENERIQUE</b> pour JPA <b>SANS SPRING</b>.</li>
 * <li>
 * Comporte l'implémentation des méthodes <b>CRUD</b> valables 
 * pour <b>tous les objets métier</b>.
 * </li>
 * <li>l'attribut JPA <b>EntityManager</b> est fabriqué "à la main" 
 * par une classe {@link JPAUtils}.</li>
 * <li><b>Les transactions sont gérées dans chaque méthode</b>.</li>
 * <li>
 * Certaines méthodes (getOne(ID), ...) sont 
 * <b>compatibles SPRING DATA</b>.
 * </li>
 * <br/>
 * <li>
 * <img src="../../../../../../../../javadoc/images/.png" 
 * alt="implémentation des DAOs JPA SANS SPRING" border="1" align="center" />
 * </li>
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
 * 
 * @param <T> : Type paramétré : Classe réelle d'un Objet métier.<br/>
 * @param <ID> : Type paramétré : type de l'ID en base d'un Objet métier 
 * (Long, Integer, String, ...).<br/>
 *
 */
public abstract class AbstractDaoGenericJPA<T, ID extends Serializable> 
							implements IDaoGenericJPASpring<T, ID> {
	
	// ************************ATTRIBUTS************************************/


	/**
	 * CLASSE_ABSTRACTDAOGENERIC_JPA : String :<br/>
	 * "Classe AbstractDaoGenericJPA".<br/>
	 */
	public static final String CLASSE_ABSTRACTDAOGENERIC_JPA 
		= "Classe AbstractDaoGenericJPA";


	/**
	 * METHODE_CREATE : String :<br/>
	 * "Méthode create(T pObject)".<br/>
	 */
	public static final String  METHODE_CREATE 
		= "Méthode create(T pObject)";


	/**
	 * classObjetMetier : Class&lt;T&gt; :<br/>
	 * Class (.Class Reflexion = Introspection) réelle 
	 * de l'Objet métier de Type paramétré T 
	 * concerné par le présent DAO.<br/>
	 */
	protected transient Class<T> classObjetMetier;


	/**
	 * gestionnaireException : GestionnaireDaoException :<br/>
	 * Gestionnaire pour les Exceptions de DAO.<br/>
	 */
	protected transient GestionnaireDaoException gestionnaireException 
		= new GestionnaireDaoException();


	/**
	 * MESSAGE_ENTITYMANAGER_NULL : String :<br/>
	 * "this.entityManager est NULL dans AbstractDaoGenericJPASpring".<br/>
	 */
	public static final String MESSAGE_ENTITYMANAGER_NULL 
	= "this.entityManager est NULL dans AbstractDaoGenericJPASpring";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(AbstractDaoGenericJPA.class);
	
	// *************************METHODES************************************/


	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>renseigne this.classObjetMetier (.class de l'objet métier 
	 * concerné par le présent DAO).</li>
	 * <li>Lance la persistence.</li>
	 * <ul>
	 * <li>Récupère la session Hibernate auprès de HibernateUtilNG.</li>
	 * <li>Récupère la Factory auprès de la Session.</li>
	 * <li>Récupère l'EntityManager auprès de la Factory.</li>
	 * </ul>
	 * </ul>
	 */
	public AbstractDaoGenericJPA() {

		super();

		/* renseigne this.classObjetMetier. */
		this.renseignerClassObjetMetier();

	} // Fin de  CONSTRUCTEUR D'ARITE NULLE._______________________________


	
	/**
	 * <b>fabrique et retourne une Entity JPA à partir 
	 * d'un objet métier</b>.<br/>
	 * <ul>
	 * <li><i>L'entity et l'objet métier doivent avoir 
	 * la même interface de type T</i>.</li>
	 * </ul>
	 *
	 * @param pObject : T : objet métier
	 * @return : T : Entity JPA instanciée à partir de l'objet métier.<br/>
	 */
	protected abstract T entity(T pObject);
	
	
	/**
	 * <b>fabrique et retourne un objet métier à partir 
	 * d'une Entity JPA</b>.<br/>
	 * <ul>
	 * <li><i>L'entity et l'objet métier doivent avoir 
	 * la même interface de type T</i>.</li>
	 * </ul>
	 *
	 * @param pObject : T : Entity JPA
	 * @return : T : Objet métier instancié à partir de l'Entity JPA.<br/>
	 */
	protected abstract T objetMetier(T pObject);
	
	
	
	/* CREATE ************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T create(
			final T pObject) throws AbstractDaoException {

		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}

		/* retourne null si pObject est un doublon. */
		if (this.exists(pObject)) {
			return null;
		}

		T persistentObject = null;
		T persistentEntity = null;
		
		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();

		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction 
		 * auprès du entityManager. */
		final EntityTransaction transaction 
			= entityManager.getTransaction();
		
		try {
			
			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}
			
			/* ***************** */
			/* PERSISTE en base l'ENTITY. */
			/* convertit l'objet métier en Entity pour le persister 
			 * dans le stockage. */
			entityManager.persist(entity(pObject));
			
			/* Commit de la Transaction (Réalise le SQL INSERT). */			
			transaction.commit();
			
			/* recherche l'entité persistée dans le stockage. */
			persistentEntity = this.retrieve(entity(pObject));
			/* convertit l'entité persistée en objet métier. */
			persistentObject = this.objetMetier(persistentEntity);
									
			/* Fermeture de l'EntityManager. */
			if (entityManager.isOpen()) {
				entityManager.close();
			}

		}
		catch (Exception e) {

			/* Rollback de la transaction. */
			if (transaction != null) {
				transaction.rollback();
			}

			/* Fermeture de l'EntityManager. */
			if (entityManager.isOpen()) {
				entityManager.close();
			}
			
			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_ABSTRACTDAOGENERIC_JPA
							, METHODE_CREATE, e);

		}

		/* retourne l'Objet persistant. */
		return persistentObject;

	} // Fin de create(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final <S extends T> S save(
			final S pObject) throws AbstractDaoException {

		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}

		/* retourne null si pObject est un doublon. */
		if (this.exists(pObject)) {
			return null;
		}

		S persistentObject = null;

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();


		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction 
		 * auprès du entityManager. */
		final EntityTransaction transaction 
			= entityManager.getTransaction();

		try {
						
			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}

			/* ***************** */
			/* PERSISTE en base. */
			entityManager.persist(pObject);

			/* Commit de la Transaction (Réalise le SQL INSERT). */			
			transaction.commit();
			
			entityManager.close();

			persistentObject = pObject;

		} 
		catch (Exception e) {

			/* Rollback de la transaction. */
			if (transaction != null) {
				transaction.rollback();
			}

			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_ABSTRACTDAOGENERIC_JPA
							, "Méthode save(S pObject)", e);

		}

		/* retourne l'Objet persistant. */
		return persistentObject;

	} // Fin de save(...)._________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void persist(
			final T pObject) throws AbstractDaoException {

		/* ne fait rien si pObject == null. */
		if (pObject == null) {
			return;
		}

		/* ne fait rien si pObject est un doublon. */
		if (this.exists(pObject)) {
			return;
		}

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();

		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}



		try {

			/* PERSISTE en base. */
			entityManager.persist(pObject);

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_ABSTRACTDAOGENERIC_JPA
							, "Méthode persist(T Object)", e);

		}

	} // Fin de persist(...).______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final <S extends T> void persistSousClasse(
			final S pObject) throws AbstractDaoException {

		/* ne fait rien si pObject == null. */
		if (pObject == null) {
			return;
		}

		/* ne fait rien si pObject est un doublon. */
		if (this.exists(pObject)) {
			return;
		}

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();

		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}
		
		try {

			/* PERSISTE en base. */
			entityManager.persist(pObject);

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(CLASSE_ABSTRACTDAOGENERIC_JPA
						, "Méthode persistSousClasse(S pObject)", e);

		}

	} // Fin de persistSousClasse(...).____________________________________



	/**
	 * {@inheritDoc}
	 * Méthode à implémenter dans une sous-classe dès 
	 * que l'on connait le Type réel de pObject.<br/>
	 * <br/>
	 */
	@Override
	public abstract ID createReturnId(T pObject) 
			throws AbstractDaoException;


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Iterable<T> save(
			final Iterable<T> pList) 
					throws AbstractDaoException {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();

		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		final List<T> resultat = new ArrayList<T>();

		final Iterator<T> iteS = pList.iterator();

		try {

			while (iteS.hasNext()) {

				final T objet = iteS.next();

				/* Passe les doublons existants en base. */
				if (!this.exists(objet)) {

					/* passe un null dans le lot. */
					if (objet != null) {

						T objetPersistant = null;

						try {

							/* PERSISTE en base. */
							entityManager.persist(objet);

							objetPersistant = objet;

						} catch (Exception e) {

							/* LOG. */
							if (LOG.isDebugEnabled()) {
								LOG.debug(e.getMessage(), e);
							}

							/* Gestion de la DAO Exception. */
							this.gestionnaireException
								.gererException(
										CLASSE_ABSTRACTDAOGENERIC_JPA
											, "Méthode save(Iterable)", e);
						}


						/* ne sauvegarde pas un doublon 
						 * présent dans le lot. */
						if (objetPersistant != null) {

							/* Ajoute à l'iterable resultat. */
							resultat.add(objetPersistant);								
						}						
					}					
				}				
			} // Next._____________________________________

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_ABSTRACTDAOGENERIC_JPA
							, "Méthode save(Iterable)", e);

		}

		/* retourne l'iterable resultat. */
		return resultat;

	} // Fin de save(...)._________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final <S extends T> Iterable<S> saveIterableSousClasse(
			final Iterable<S> pObjects) 
			throws AbstractDaoException {

		/* retourne null si pObjects == null. */
		if (pObjects == null) {
			return null;
		}

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();

		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		final List<S> resultat = new ArrayList<S>();

		final Iterator<S> iteS = pObjects.iterator();

		try {

			while (iteS.hasNext()) {

				final S objet = iteS.next();

				/* Passe les doublons existants en base. */
				if (!this.exists(objet)) {

					/* passe un null dans le lot. */
					if (objet != null) {

						S objetPersistant = null;

						try {

							/* PERSISTE en base. */
							entityManager.persist(objet);

							objetPersistant = objet;

						} catch (Exception e) {

							/* LOG. */
							if (LOG.isDebugEnabled()) {
								LOG.debug(e.getMessage(), e);
							}

							/* Gestion de la DAO Exception. */
							this.gestionnaireException
								.gererException(
										CLASSE_ABSTRACTDAOGENERIC_JPA
											, "Méthode saveIterableSousClasse(Iterable)", e);
						}


						/* ne sauvegarde pas un doublon 
						 * présent dans le lot. */
						if (objetPersistant != null) {

							/* Ajoute à l'iterable resultat. */
							resultat.add(objetPersistant);								
						}						
					}					
				}				
			} // Next._____________________________________

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_ABSTRACTDAOGENERIC_JPA
							, "Méthode save(Iterable)", e);

		}

		/* retourne l'iterable resultat. */
		return resultat;

	} // Fin de save(...)._________________________________________________



	/* READ *************/



	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract T retrieve(T pObject) throws AbstractDaoException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findById(
			final ID pId) throws AbstractDaoException {

		T objetTrouve = null;

		/* retourne null si pId == null. */
		if (pId == null) {
			return null;
		}

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();

		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		try {

			objetTrouve 
				= entityManager.find(this.classObjetMetier, pId);

		}
		catch (Exception e) {

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_ABSTRACTDAOGENERIC_JPA
						, "Méthode findById(ID)", e);

		}

		return objetTrouve;

	} // Fin de findById(...)._____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T getOne(
			final ID pId) throws AbstractDaoException {

		return this.findById(pId);

	} // Fin de getOne(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> findAll() throws AbstractDaoException {

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();
		
		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "from " + this.classObjetMetier.getName();
		
		List<T> resultat = null;
		
		try {
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= entityManager.createQuery(requeteString);

			/* Exécute la javax.persistence.Query. */
			resultat = query.getResultList();
			
			entityManager.close();

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_ABSTRACTDAOGENERIC_JPA
						, "Méthode findall()", e);

		}

		/* Retourne la liste résultat. */
		return resultat;

	} // Fin de findAll()._________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<T> findAllMax(
			final int pStartPosition
				, final int pMaxResult) throws AbstractDaoException {

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();

		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "from " + this.classObjetMetier.getName();

		List<T> resultat = null;

		try {

			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= entityManager.createQuery(requeteString)
					.setFirstResult(pStartPosition).setMaxResults(pMaxResult);

			/* Exécute la javax.persistence.Query. */
			resultat = query.getResultList();

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(CLASSE_ABSTRACTDAOGENERIC_JPA
						, "Méthode findAllMax(...)", e);

		}

		/* Retourne la liste résultat. */
		return resultat;

	} // Fin de findAllMax(...).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Iterable<T> findAll(
			final Iterable<ID> pIds) throws AbstractDaoException {

		/* retourne null si pIds == null. */
		if (pIds == null) {
			return null;
		}

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();

		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		final List<T> resultat = new ArrayList<T>();		

		final Iterator<ID> iteratorID = pIds.iterator();

		while (iteratorID.hasNext()) {

			final ID id = iteratorID.next();
			/* Recherche en base sur ID. */
			final T objetEnBase = this.findById(id);

			if (objetEnBase != null) {
				resultat.add(objetEnBase);
			}			
		}

		return resultat;

	} // Fin de findAll(...).______________________________________________



	/* UPDATE *************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T update(
			final T pObject) throws AbstractDaoException {

		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();

		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		} // Fin de this.entityManager == null.____________


		/* retourne pObject si l'objet n'est pas 
		 * déjà persistant en base. */
		if (!this.exists(pObject)) {
			return pObject;
		}

		T persistentObject = null;

		try {

			/* MODIFIE en base. */
			entityManager.merge(pObject);

			persistentObject = pObject;

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_ABSTRACTDAOGENERIC_JPA
						, "Méthode update(T Object)", e);

		}

		/* retourne l'Objet persistant modifié. */
		return persistentObject;

	} // Fin de update(...)._______________________________________________



	/* DELETE *************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean delete(
			final T pObject) throws AbstractDaoException {

		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();

		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}

		boolean resultat = false;

		/* Vérifie qu'il existe une instance persistante. */
		final T persistanceInstance = this.retrieve(pObject);

		try {

			if (persistanceInstance != null) {

				/* merge avant de pouvoir détruire. */
				entityManager.merge(persistanceInstance);

				/* DESTRUCTION. */
				entityManager.remove(persistanceInstance);

				resultat = true;

			}
			else {
				resultat = false;
			}

		} catch (Exception e) {

			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_ABSTRACTDAOGENERIC_JPA
						, "Méthode delete(T pObject)", e);

		}

		return resultat;

	} // Fin de delete(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void deleteById(ID pId) throws AbstractDaoException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract boolean deleteByIdBoolean(ID pId) 
			throws AbstractDaoException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void deleteAll() throws AbstractDaoException {

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();
		
		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}


		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "delete from " + this.classObjetMetier.getName();
		
		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction 
		 * auprès du entityManager. */
		final EntityTransaction transaction 
			= entityManager.getTransaction();

		try {
			
			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}

			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= entityManager.createQuery(requeteString);

			/* EXECUTION DE LA REQUETE. */
			query.executeUpdate();
			
			/* Commit de la Transaction (Réalise l'ordre SQL). */			
			transaction.commit();

			entityManager.close();

		}
		catch (Exception e) {
			
			/* Rollback de la transaction. */
			if (transaction != null) {
				transaction.rollback();
			}

			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_ABSTRACTDAOGENERIC_JPA
						, "Méthode deleteAll()", e);

		}

	} // Fin de deleteAll()._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean deleteAllBoolean() throws AbstractDaoException {

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();

		/* Cas où this.entityManager == null. */
		if (entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}


		boolean resultat = false;

		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "delete from " + this.classObjetMetier.getName();

		try {

			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= entityManager.createQuery(requeteString);

			/* EXECUTION DE LA REQUETE. */
			query.executeUpdate();

			resultat = true;

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_ABSTRACTDAOGENERIC_JPA
						, "Méthode deleteAllBoolean()", e);

		}

		return resultat;

	} // Fin de deleteAll()._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void delete(
			final Iterable<? extends T> pObjects) 
						throws AbstractDaoException {

		/* ne fait rien si pObjects == null. */
		if (pObjects == null) {
			return;
		}

		final Iterator<? extends T> itePersistants = pObjects.iterator();
		final List<T> listePersistants 
			= new ArrayList<T>();

		/* Récupération préalable des objets persistans en base. */
		while (itePersistants.hasNext()) {
			final T objet = itePersistants.next();
			final T objetPersistant = this.retrieve(objet);

			if (objetPersistant != null) {
				listePersistants.add(objetPersistant);
			}
		}

		/* Instanciation d'un entityManager. */
		final EntityManager entityManager 
			= JPAUtils.fournirEntityManager();

		/* Itération uniquement sur la liste des Objets persistants. */
		final Iterator<? extends T> ite = listePersistants.iterator();

		try {

			while (ite.hasNext()) {

				final T objectPersistant = ite.next();

				/* DESTRUCTION. */
				entityManager.remove(objectPersistant);

			}

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(
					CLASSE_ABSTRACTDAOGENERIC_JPA
					, "Méthode delete(Iterable)", e);

		}

	} // Fin de delete(...)._______________________________________________



	/* TOOLS *************/

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract boolean exists(T pObject) throws AbstractDaoException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract boolean exists(ID pId) throws AbstractDaoException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Long count() throws AbstractDaoException {

		/* Récupère la liste d'Objets métier de Type paramétré T. */
		final List<T> listObjects = this.findAll();

		if (listObjects != null) {

			/* Retourne la taille de la liste. */
			return Long.valueOf(listObjects.size()) ;
		}

		return 0L;

	} // Fin de count().___________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Class<T> getClassObjetMetier() {
		return this.classObjetMetier;
	} // Fin de getClassObjetMetier()._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setClassObjetMetier(
			final Class<T> pClassObjetMetier) {
		this.classObjetMetier = pClassObjetMetier;
	} // Fin de setClassObjetMetier(...).__________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract String afficherListe(List<T> pListe);



	/**
	 * method renseignerClassObjetMetier() :<br/>
	 * <ul>
	 * <li>Impose aux DAO <i>descendants</i> de <b>renseigner le .class</b> 
	 * de l'<b>objet métier</b> concerné par le présent DAO 
	 * (this.classObjetMetier).</li>
	 * <li><b>automatiquement appelé</b> dans le constructeur 
	 * AbstractDaoGenericJPASpring().</li>
	 * <li>Par exemple : Book.class ou IUserSimple.class.</li>
	 * </ul>
	 */
	protected abstract void renseignerClassObjetMetier();




	
} // FIN DE LA CLASSE CLASSE AbstractDaoGenericJPA.--------------------------
