package levy.daniel.application.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;

import levy.daniel.application.model.dao.daoexceptions.AbstractDaoException;
import levy.daniel.application.model.dao.daoexceptions.GestionnaireDaoException;




/**
 * class AbstractDaoGeneric :<br/>
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
 * 
 * @param <T> : Type paramétré : Classe réelle d'un Objet métier.<br/>
 * @param <ID> : Type paramétré : type de l'ID en base d'un Objet métier 
 * (Long, Integer, String, ...).<br/>
 *  
 * @since 8 sept. 2017
 *
 */
public abstract class AbstractDaoGenericJPA<T, ID extends Serializable> 
											implements IDaoGenericJPA<T, ID> {

	// ************************ATTRIBUTS************************************/

	
	/**
	 * entityManager : javax.persistence.EntityManager :<br/>
	 * JPA EntityManager.<br/>
	 */
	protected transient EntityManager entityManager;

		
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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(AbstractDaoGenericJPA.class);



	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR AbstractDaoGeneric() :<br/>
	 * <ul>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
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
		
		/* Lance la persistence. */
		this.buildEntityManager();
		
	} // Fin de  CONSTRUCTEUR D'ARITE NULLE._______________________________
	

	
	/**
	 * method renseignerClassObjetMetier() :<br/>
	 * Impose aux DAO concrets de renseigner le .class de l'objet métier 
	 * concerné par le présent DAO (this.classObjetMetier).<br/>
	 * Par exemple : Book.class.<br/>
	 * <br/>
	 */
	protected abstract void renseignerClassObjetMetier();
	
	
	
	/**
	 * method buildEntityManager() :<br/>
	 * <ul>
	 * <li>Récupère la session Hibernate auprès de HibernateUtilNG.</li>
	 * <li>Récupère la Factory auprès de la Session.</li>
	 * <li>Récupère l'EntityManager auprès de la Factory.</li>
	 * </ul>
	 * 
	 * @throws HibernateException
	 * @throws IllegalStateException
	 */
	private void buildEntityManager() 
			throws HibernateException, IllegalStateException {
				
		/* Récupère l'EntityManager auprès de l'utilitaire. */
		this.entityManager = EntityManagerJPAUtil.getEntityManager();
					
	} // Fin de buildEntityManager().______________________________________
	

	
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
		
		/* https://docs.jboss.org/hibernate/orm/5.0/quickstart/html/ */
		
		T persistentObject = null;
		
		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction 
		 * auprès du entityManager. */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();
		
		try {
			
			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}
			

			/* PERSISTE en base. */
			this.entityManager.persist(pObject);
			
			/* Commit de la Transaction (Réalise le SQL INSERT). */			
			transaction.commit();
						
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
			this.gestionnaireException.gererException(e);
						
		}
//		finally {
//			
//			/* Clôture de la Session. */
//			if (this.session != null) {
//				this.session.close();
//			}
//					
//        }
		
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
		
		/* https://docs.jboss.org/hibernate/orm/5.0/quickstart/html/ */
		
		S persistentObject = null;
		
		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction auprès du entityManager. */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();
		
		try {
			
			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}

			/* PERSISTE en base. */
			this.entityManager.persist(pObject);
			
			/* Commit de la Transaction (Réalise le SQL INSERT). */
			transaction.commit();
						
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
			this.gestionnaireException.gererException(e);
			
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
		
		/* http://cristal.univ-lille.fr/~dumoulin/
		 * enseign/ipint/6.orm/Cours-JPA-v1.2.pdf */
		
		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction auprès du entityManager. */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();
		
		try {
			
			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}

			/* PERSISTE en base. */
			this.entityManager.persist(pObject);
			
			/* Commit de la Transaction (Réalise le SQL INSERT). */
			transaction.commit();

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
			this.gestionnaireException.gererException(e);
			
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

		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction auprès du entityManager. */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();
		
		try {
			
			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}
			
			/* PERSISTE en base. */
			this.entityManager.persist(pObject);
			
			/* Commit de la Transaction (Réalise le SQL INSERT). */
			transaction.commit();
			
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
			this.gestionnaireException.gererException(e);
			
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
	public final <S extends T> Iterable<S> save(
			final Iterable<S> pObjects) 
			throws AbstractDaoException {

		/* retourne null si pObjects == null. */
		if (pObjects == null) {
			return null;
		}

		/*
		 * Récupération d'une TransactionJPA
		 * javax.persistence.EntityTransaction auprès du entityManager.
		 */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();

		List<S> resultat = new ArrayList<S>();

		final Iterator<S> iteS = pObjects.iterator();

		try {

			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}

			while (iteS.hasNext()) {

				final S objet = iteS.next();

				if (objet != null) {

					/* PERSISTE en base. */
					this.entityManager.persist(objet);

					/* Ajoute à l'iterable resultat. */
					resultat.add(objet);
				}

			}

			/* Commit de la Transaction (Réalise le SQL INSERT). */
			transaction.commit();

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

			resultat = null;

			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(e);

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
	public final T findById(
			final ID pId) throws AbstractDaoException {
		
		T objetTrouve = null;
		
		try {
			
			objetTrouve 
				= this.entityManager.find(this.classObjetMetier, pId);
			
		}
		catch (Exception e) {
			
			objetTrouve = null;
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(e);
			
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
	public final List<T> findAll() throws AbstractDaoException {
		
		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "from " + this.classObjetMetier.getName();
		
		/* https://docs.jboss.org/hibernate/orm/5.0/quickstart/html/ */
		
		List<T> resultat = null;
		
		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction auprès du entityManager. */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();
		
		try {
			
			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);
			
			/* Exécute la javax.persistence.Query. */
			resultat = query.getResultList();

			/* Commit de la Transaction. */
			transaction.commit();
			
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
			
			resultat = null;
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(e);
			
		}
		
		/* Retourne la liste résultat. */
		return resultat;
		
	} // Fin de findAll()._________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<T> findAllMax(
			final Long pMax) throws AbstractDaoException {
		
		
		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "from " + this.classObjetMetier.getName();
		
		/* https://docs.jboss.org/hibernate/orm/5.0/quickstart/html/ */
		
		List<T> resultat = null;
		
		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction auprès du entityManager. */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();
		
		try {
			
			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString)
					.setFirstResult(0).setMaxResults(pMax.intValue());
			
			/* Exécute la javax.persistence.Query. */
			resultat = query.getResultList();

			/* Commit de la Transaction. */
			transaction.commit();
			
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
			
			resultat = null;
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(e);
			
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

		T persistentObject = null;
		
		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction auprès du entityManager. */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();
		
		try {
			
			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}

			/* MODIFIE en base. */
			this.entityManager.merge(pObject);
			
			/* Commit de la Transaction (Réalise le SQL INSERT). */
			transaction.commit();

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
			this.gestionnaireException.gererException(e);
						
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

		boolean resultat = false;
		
		/* Vérifie qu'il existe une instance persistante. */
		final T persistanceInstance = this.retrieve(pObject);
		
		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction 
		 * auprès du entityManager. */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();
		
		try {
								
			if (persistanceInstance != null) {
								
				/* Début de la Transaction. */
				if (!transaction.isActive()) {
					transaction.begin();
				}
				
				/* DESTRUCTION. */
				this.entityManager.remove(persistanceInstance);
				
				/* Commit de la Transaction (Réalise le SQL INSERT). */
				transaction.commit();
				
				resultat = true;
				
			}
			else {
				resultat = false;
			}
			
		} catch (Exception e) {
			
			/* Rollback de la transaction. */
			if (transaction != null) {
				transaction.rollback();
			}
			
			/* LOG. */
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(e);
									
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
		
		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "delete from " + this.classObjetMetier.getName();
		
		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction auprès du entityManager. */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();
		
		try {
			
			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);
			
			/* EXECUTION DE LA REQUETE. */
			query.executeUpdate();
			
			/* Commit de la Transaction. */
			transaction.commit();
			
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
			this.gestionnaireException.gererException(e);
			
		}
		
	} // Fin de deleteAll()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean deleteAllBoolean() throws AbstractDaoException {
		
		boolean resultat = false;
		
		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "delete from " + this.classObjetMetier.getName();
		
		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction auprès du entityManager. */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();
		
		try {
			
			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);
			
			/* EXECUTION DE LA REQUETE. */
			query.executeUpdate();
			
			/* Commit de la Transaction. */
			transaction.commit();
			
			resultat = true;
			
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
			this.gestionnaireException.gererException(e);
			
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
		
		
		/* Itération uniquement sur la l iste des Objets persistants. */
		final Iterator<? extends T> ite = listePersistants.iterator();
		
		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction auprès du entityManager. */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();
		
		try {
			
			/* Début de la Transaction. */
			if (!transaction.isActive()) {
				transaction.begin();
			}
			
			while (ite.hasNext()) {
				
				final T objectPersistant = ite.next();
				
				/* DESTRUCTION. */
				this.entityManager.remove(objectPersistant);
				
			}
			
			/* Commit de la Transaction (Réalise le SQL INSERT). */
			transaction.commit();
			
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
			this.gestionnaireException.gererException(e);
			
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

	
	
} // FIN DE LA CLASSE AbstractDaoGeneric.------------------------------------
