package levy.daniel.application.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;




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
public abstract class AbstractDaoGeneric<T, ID extends Serializable> 
											implements IDaoGeneric<T, ID> {

	// ************************ATTRIBUTS************************************/

	/**
	 * session : Session :<br/>
	 * org.hibernate.session.<br/>
	 */
	private transient Session session;

	
	/**
	 * entityManagerFactory : 
	 * javax.persistence.EntityManagerFactory :<br/>
	 * JPA EntityManagerFactory.<br/>
	 */
	private transient EntityManagerFactory entityManagerFactory;
	
	
	/**
	 * entityManager : javax.persistence.EntityManager :<br/>
	 * JPA EntityManager.<br/>
	 */
	private transient EntityManager entityManager;

		
	/**
	 * classObjetMetier : Class&lt;T&gt; :<br/>
	 * Class (.Class Reflexion = Introspection) réelle 
	 * de l'Objet métier de Type paramétré T 
	 * concerné par le présent DAO.<br/>
	 */
	protected transient Class<T> classObjetMetier;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(AbstractDaoGeneric.class);



	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR AbstractDaoGeneric() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractDaoGeneric() {
		
		super();
		
		/* Lance la persistence. */
		this.buildEntityManager();
		
	} // Fin de  CONSTRUCTEUR D'ARITE NULLE._______________________________
	
	
	
	/**
	 * method buildEntityManager() :<br/>
	 * <ul>
	 * <li>Récupère la session Hibernate auprès de HibernateUtil.</li>
	 * <li>Récupère la Factory auprès de la Session.</li>
	 * <li>Récupère l'EntityManager auprès de la Factory.</li>
	 * </ul>
	 * 
	 * @throws HibernateException
	 * @throws IllegalStateException
	 */
	private void buildEntityManager() 
			throws HibernateException, IllegalStateException {
		
		/* Récupère la session Hibernate auprès de HibernateUtil. */
		this.session = HibernateUtil.currentSession();
		
		/* Récupère la Factory auprès de la Session. */
		this.entityManagerFactory 
			= this.session.getEntityManagerFactory();
		
		/* Récupère l'EntityManager auprès de la Factory. */
		this.entityManager 
			= this.entityManagerFactory.createEntityManager();
				
	} // Fin de buildEntityManager().______________________________________
	

	
	/* CREATE ************/

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T create(
			final T pObject) {
		
		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		/* https://docs.jboss.org/hibernate/orm/5.0/quickstart/html/ */

		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction auprès du entityManager. */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();
		
		/* Début de la Transaction. */
		transaction.begin();

		/* Persiste en base. */
		this.entityManager.persist(pObject);
		
		/* Commit de la Transaction (Réalise le SQL INSERT). */
		transaction.commit();
		
		/* Clôture de la Session. */
		this.session.close();
		
		/* retourne l'Objet persistent. . */
		return pObject;
				
	} // Fin de create(...)._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persist(
			final T pObject) {
		
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
		
		/* Début de la Transaction. */
		transaction.begin();
		
		/* Persiste en base. */
		this.entityManager.persist(pObject);
		
		/* Commit de la Transaction (Réalise le SQL INSERT). */
		transaction.commit();
		
		/* Clôture de la Session. */
		this.session.close();

	} // Fin de persist(...).______________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * Méthode à implémenter dans une sous-classe dès 
	 * que l'on connait le Type réel de pObject.<br/>
	 * <br/>
	 */
	@Override
	public abstract ID createReturnId(T pObject);


	
	/* READ *************/
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T retrieve(
			final T pObject) {
		
		/* Récupère la liste d'Objets métier de Type paramétré T. */
		final List<T> listObjects = this.findAll();
		
		/* parcourt la table et teste l'égalité métier. */
		if (listObjects != null) {
			
			for (final T objet : listObjects) {
				
				if (objet.equals(pObject)) {
					return objet;
				}
			}
						
		}
		
		return null;
		
	} // Fin de retrieve(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T findById(
			final ID pId) {
		
		return this.entityManager.find(this.classObjetMetier, pId);
				
	} // Fin de findById(...)._____________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T getOne(
			final Long pId) {
		
		return this.entityManager.find(this.classObjetMetier, pId);
		
	} // Fin de getOne(...)._______________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> findAll() {
		
		final String requeteString 
			= "from " + this.classObjetMetier.getName();
		
		/* https://docs.jboss.org/hibernate/orm/5.0/quickstart/html/ */
		
		/* Récupération d'une TransactionJPA 
		 * javax.persistence.EntityTransaction auprès du entityManager. */
		final EntityTransaction transaction 
			= this.entityManager.getTransaction();
		
		/* Début de la Transaction. */
		transaction.begin();
		
		/* Crée la requête javax.persistence.Query. */
		final Query query = this.entityManager.createQuery(requeteString);
		
		/* Exécute la javax.persistence.Query. */
		final List<T> resultat = query.getResultList();

		/* Commit de la Transaction. */
		transaction.commit();
		
		/* Clôture de la Session. */
		this.session.close();
		
		/* Retourne la liste résultat. */
		return resultat;
		
	} // Fin de findAll()._________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> findAllMax(
			final Long pMax) {
		
		// TODO Auto-generated method stub
		return null;
		
	} // Fin de findAllMax(...).___________________________________________
	

		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<T> findAll(
			final Iterable<Long> pIds) {
		
		// TODO Auto-generated method stub
		return null;
		
	} // Fin de findAll(...).______________________________________________


	
	/* UPDATE *************/
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T update(
			final T pObject) {
		
		this.entityManager.merge(pObject);
		return pObject;
		
	} // Fin de update(...)._______________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <S extends T> S save(
			final S pObject) {
		
		// TODO Auto-generated method stub
		return null;
		
	} // Fin de save(...)._________________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <S extends T> Iterable<S> save(
			final Iterable<S> pEntities) {
		
		// TODO Auto-generated method stub
		return null;
		
	} // Fin de save(...)._________________________________________________

	
	
	/* DELETE *************/
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(
			final T pObject) {
		
		try {
			
			this.entityManager.remove(pObject);
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
				
	} // Fin de delete(...)._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(
			final ID pId) {
		
		 /****/
		
	} // Fin de deleteById(...).___________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(
			final Long pId) {
		
		/***/	
		
	} // Fin de delete(...)._______________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteBoolean(
			final Long pId) {
		
		return false;
		
	} // Fin de deleteBoolean(...).________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() {
		
		/***/
		
	} // Fin de deleteAll()._______________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(
			final Iterable<? extends T> pEntities) {
		
		// TODO Auto-generated method stub
		
	} // Fin de delete(...)._______________________________________________

	
	
	/* TOOLS *************/
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(
			final Long pId) {
		
		// TODO Auto-generated method stub
		return false;
		
	} // Fin de exists(...)._______________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long count() {
		
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
	public Class<T> getClassObjetMetier() {
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


	
} // FIN DE LA CLASSE AbstractDaoGeneric.------------------------------------
