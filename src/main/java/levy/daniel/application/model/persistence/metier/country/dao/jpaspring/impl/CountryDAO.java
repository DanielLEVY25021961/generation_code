package levy.daniel.application.model.persistence.metier.country.dao.jpaspring.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.country.ICountry;
import levy.daniel.application.model.persistence.daoexceptions.GestionnaireDaoException;
import levy.daniel.application.model.persistence.metier.country.dao.jpaspring.ICountryDAO;
import levy.daniel.application.model.persistence.metier.country.entities.jpa.CountryEntityJPA;


/**
 * CLASSE CONCRETE <b>CountryDAO</b> :<br/>
 * <p>
 * <span style="text-decoration: underline;">CONCEPT 
 * CONCERNE PAR CE DAO</span>
 * </p>
 * <p>
 * <b>Country</b> modélise un un <i>concept</i> 
 * de <b>Country</b> avec ********
 * <b>*****</b>  qui identifie <i>une ou plusieurs</i> <b>****</b>.<br/>
 * </p>
 * 
 * <p>
 * <span style="text-decoration: underline;">DESCRIPTION DE 
 * CountryDAO</span>
 * </p>
 * <ul>
 * <li>DAO <b>CONCRET</b> pour les <b>Country</b>.</li>
 * <li>
 * Implémente l'interface <b>ICountryDAO</b>.
 * </li>
 * <li>
 * Certaines méthodes (getOne(ID), ...) sont 
 * <b>compatibles SPRING DATA</b>.
 * </li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">IMPLEMENTATION DES CountryDAO</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../../../../../../../javadoc/images/implementation_Country_DAO_JpaSpring.png" 
 * alt="implémentation des DAOs Country JPA SPRING" border="1" align="center" />
 * </li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">UTILISATION DES CountryDAO</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../../../../javadoc/images/utilisation_Country_DAO_JpaSpring.png" 
 * alt="utilisation des DAOs CountryDAO JPA SPRING" border="1" align="center" />
 * </li>
 * </ul>
 * 
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
//@Repository("CountryDaoJPASpring")
public class CountryDAO implements ICountryDAO {
	
	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe CountryDAO".<br/>
	 */
	public static final String CLASSE_COUNTRYDAO_JPA_SPRING 
		= "Classe CountryDAO";

	/**
	 * SAUT_LIGNE_JAVA : char :<br/>
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE_JAVA = '\n';

	/**
	 * SELECT_OBJET : String :<br/>
	 * "select country from 
	 * CountryEntityJPA as country ".<br/>
	 */
	public static final String SELECT_OBJET 
		= "select country from "
				+ "CountryEntityJPA as country ";
	
	/**
	 * JPA EntityManager <b>injecté par SPRING</b>.<br/>
	 */
	@PersistenceContext
	private transient EntityManager entityManager;
	
	/**
	 * gestionnaireException : GestionnaireDaoException :<br/>
	 * Gestionnaire pour les Exceptions de DAO.<br/>
	 */
	private final transient GestionnaireDaoException gestionnaireException 
		= new GestionnaireDaoException();
	
	/**
	 * "this.entityManager est NULL dans le présent DAO".<br/>
	 */
	public static final String MESSAGE_ENTITYMANAGER_NULL 
	= "this.entityManager est NULL dans le présent DAO";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(CountryDAO.class);

	
	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public CountryDAO() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/* CREATE ************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ICountry create(
			final ICountry pObject) throws Exception {

		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}

		ICountry persistentObject = null;

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* retourne null si pObject est un doublon. */
		if (this.exists(pObject)) {
			return null;
		}

		try {
			
			/* Obtention d'une Entity JPA à partir de l'objet métier. */
			final CountryEntityJPA entity 
				= new CountryEntityJPA(pObject);

			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);

			persistentObject = entity;

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_COUNTRYDAO_JPA_SPRING
							, "Méthode create(ICountry pObject)", e);

		}

		/* retourne l'Objet persistant. */
		return persistentObject;

	} // Fin de create(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persist(
			final ICountry pObject) throws Exception {

		/* ne fait rien si pObject == null. */
		if (pObject == null) {
			return;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}

		/* ne fait rien si pObject est un doublon. */
		if (this.exists(pObject)) {
			return;
		}


		try {
			
			/* Obtention d'une Entity JPA à partir de l'objet métier. */
			final CountryEntityJPA entity 
				= new CountryEntityJPA(pObject);

			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_COUNTRYDAO_JPA_SPRING
							, "Méthode persist(ICountry Object)", e);

		}

	} // Fin de persist(...).______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long createReturnId(
			final ICountry pObject) throws Exception {

		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* retourne null si pObject est un doublon. */
		if (this.exists(pObject)) {
			return null;
		}

		/* ******************************************************* */
		/* Crée l'Objet en base ou jette une Exception. */
		final ICountry objectPersistant 
			= this.create(pObject);

		/* retourne null si l'objet pObject n'a pu être créé en base. */
		if (objectPersistant == null) {
			return null;
		}

		/* retourne l'Long de l'objet persistant. */
		return objectPersistant.getId();	

	} // Fin de createReturnId(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<ICountry> save(
			final Iterable<ICountry> pList) 
					throws Exception {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		final List<ICountry> resultat = new ArrayList<ICountry>();

		final Iterator<ICountry> iteS = pList.iterator();

		try {

			while (iteS.hasNext()) {

				final ICountry objet = iteS.next();

				/* Passe les doublons existants en base. */
				if (!this.exists(objet)) {

					/* passe un null dans le lot. */
					if (objet != null) {

						/* Obtention d'une Entity JPA à partir 
						 * de l'objet métier. */
						final CountryEntityJPA entity 
							= new CountryEntityJPA(objet);
						
						ICountry objectPersistant = null;

						try {

							/* ***************** */
							/* PERSISTE en base. */
							this.entityManager.persist(entity);

							objectPersistant = objet;

						} catch (Exception e) {

							/* LOG. */
							if (LOG.isFatalEnabled()) {
								LOG.fatal(e.getMessage(), e);
							}

							/* Gestion de la DAO Exception. */
							this.gestionnaireException
								.gererException(
										CLASSE_COUNTRYDAO_JPA_SPRING
											, "Méthode save(Iterable)", e);
						}


						/* ne sauvegarde pas un doublon 
						 * présent dans le lot. */
						if (objectPersistant != null) {

							/* Ajoute à l'iterable resultat. */
							resultat.add(objectPersistant);								
						}						
					}					
				}				
			} // Next._____________________________________

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_COUNTRYDAO_JPA_SPRING
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
	public ICountry retrieve(
			final ICountry pObject) throws Exception {

		/* return null si pObject == null. */
		if (pObject == null) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		ICountry objectPersistant = null;

		/* REQUETE HQL PARAMETREE. */
		final String requeteString 
			= SELECT_OBJET
				+ "where country.name = :pName" 
					+ "and country.capital = :pCapital";

		/* Construction de la requête HQL. */
		final Query requete 
			= this.entityManager.createQuery(requeteString);

		/* Passage des paramètres de la requête HQL. */
		requete.setParameter("pName", pObject.getName());
		requete.setParameter("pCapital", pObject.getCapital());

		try {

			/* Execution de la requete HQL. */
			objectPersistant 
				= (ICountry) requete.getSingleResult();

		}
		catch (NoResultException noResultExc) {

			/* retourne null si l'Objet métier n'existe pas en base. */
			return null;

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_COUNTRYDAO_JPA_SPRING
						, "Méthode retrieve(ICountry pObject)", e);
		}

		return objectPersistant;

	} // Fin de retrieve(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ICountry findById(
			final Long pId) throws Exception {

		ICountry objetTrouve = null;

		/* retourne null si pId == null. */
		if (pId == null) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		try {

			/* ******************** */
			// RECHERCHE EN BASE.
			objetTrouve 
				= this.entityManager
					.find(CountryEntityJPA.class, pId);

		}
		catch (Exception e) {

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_COUNTRYDAO_JPA_SPRING
						, "Méthode findById(Long)", e);

		}

		return objetTrouve;

	} // Fin de findById(...)._____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long retrieveId(
			final ICountry pObject) throws Exception {
		
		final ICountry object = this.retrieve(pObject);
		
		Long resultat = null;
		
		if (object != null) {
			resultat = object.getId();
		}
		
		return resultat;
		
	} // Fin de retrieveId(...).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ICountry> findAll() throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "from CountryEntityJPA";

		List<ICountry> resultat = null;

		try {

			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);

			/* Exécute la javax.persistence.Query. */
			resultat = query.getResultList();

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_COUNTRYDAO_JPA_SPRING
						, "Méthode findall()", e);

		}

		/* Retourne la liste résultat. */
		return resultat;

	} // Fin de findAll()._________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ICountry> findAllMax(
			final int pStartPosition
				, final int pMaxResult) throws Exception {


		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "from CountryEntityJPA";

		List<ICountry> resultat = null;

		try {

			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString)
					.setFirstResult(pStartPosition).setMaxResults(pMaxResult);

			/* Exécute la javax.persistence.Query. */
			resultat = query.getResultList();

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_COUNTRYDAO_JPA_SPRING
						, "Méthode findAllMax(...)", e);

		}

		/* Retourne la liste résultat. */
		return resultat;

	} // Fin de findAllMax(...).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<ICountry> findAll(
			final Iterable<Long> pIds) throws Exception {

		/* retourne null si pIds == null. */
		if (pIds == null) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		final List<ICountry> resultat = new ArrayList<ICountry>();		

		final Iterator<Long> iteratorID = pIds.iterator();

		while (iteratorID.hasNext()) {

			final Long id = iteratorID.next();
			
			/* Recherche en base sur Long. */
			final ICountry objetEnBase = this.findById(id);

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
	public ICountry update(
			final ICountry pObject) throws Exception {

		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

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

		ICountry persistentObject = null;

		try {

			/* Obtention d'une Entity JPA à partir de l'objet métier. */
			final CountryEntityJPA entity 
				= new CountryEntityJPA(pObject);
			
			/* **************** */
			/* MODIFIE en base. */
			this.entityManager.merge(entity);

			persistentObject = pObject;

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_COUNTRYDAO_JPA_SPRING
						, "Méthode update(ICountry Object)", e);

		}

		/* retourne l'Objet persistant modifié. */
		return persistentObject;

	} // Fin de update(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public ICountry update(
			final Long pId, final ICountry pObjectModifie) 
												throws Exception {

		/* retourne null si pId == null. */
		if (pId == null) {
			return null;
		}
		
		/* retourne null si pObjectModifie == null. */
		if (pObjectModifie == null) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* récupération de l'objet persistant à modifier. */
		final ICountry objectPersistant = this.findById(pId);
		
		/* retourne null s'il n'y a pas d'objet persistant à pId. */
		if (objectPersistant == null) {
			return null;
		}

		ICountry persistentObject = null;
		
		try {
			
			final Long id = objectPersistant.getId();
			
			/* Obtention d'une Entity JPA à partir de l'objet métier. */
			final CountryEntityJPA entity 
				= new CountryEntityJPA(pObjectModifie);
			
			/* Passage de l'ID à l'entity contenant les modifications. */
			entity.setId(id);
			
			/* **************** */
			/* MODIFIE en base. */
			this.entityManager.merge(entity);

			persistentObject = pObjectModifie;
			
		} catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_COUNTRYDAO_JPA_SPRING
						, "Méthode update(Long pId"
								+ ", ICountry Object)", e);
			
		}

		/* retourne l'Objet persistant modifié. */
		return persistentObject;
		
	} // Fin de update(...)._______________________________________________



	/* DELETE *************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(
			final ICountry pObject) throws Exception {

		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}

		/* Vérifie qu'il existe une instance persistante. */
		final ICountry objectPersistant = this.retrieve(pObject);
		
		/* retourne false si pObject n'est pas persisté. */
		if (objectPersistant == null) {
			return false;
		}

		boolean resultat = false;
		
		try {

			/* Obtention d'une Entity JPA à partir de l'objet métier. */
			final CountryEntityJPA entity 
				= new CountryEntityJPA(objectPersistant);

			/* merge avant de pouvoir détruire. */
			this.entityManager.merge(entity);

			/* ************ */
			/* DESTRUCTION. */
			this.entityManager.remove(entity);

			resultat = true;

		} catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_COUNTRYDAO_JPA_SPRING
						, "Méthode delete(ICountry pObject)", e);

		}

		return resultat;

	} // Fin de delete(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(
			final Long pId) throws Exception {

		/* ne fait rien si pId == null. */
		if (pId == null) {
			return;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}

		ICountry objectPersistant = null;

		/* REQUETE HQL PARAMETREE. */
		final String requeteString 
		= SELECT_OBJET 
		+ "where country.id = :pId";

		/* Construction de la requête HQL. */
		final Query requete 
			= this.entityManager.createQuery(requeteString);

		/* Passage des paramètres de la requête HQL. */
		requete.setParameter("pId", pId);

		try {
			
			/* Execution de la requete HQL. */
			objectPersistant 
			= (ICountry) requete.getSingleResult();
		}
		catch (NoResultException noResultExc) {
			objectPersistant = null;
		}

		try {

			if (objectPersistant != null) {

				/* Obtention d'une Entity JPA à partir de l'objet métier. */
				final CountryEntityJPA entity 
					= new CountryEntityJPA(objectPersistant);

				/* Merge avant destruction. */
				this.entityManager.merge(entity);

				/* ************ */
				/* DESTRUCTION. */
				this.entityManager.remove(entity);

			}

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_COUNTRYDAO_JPA_SPRING
						, "Méthode deleteById(Long pId)", e);
		}

	} // Fin de deleteById(...).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteByIdBoolean(
			final Long pId) throws Exception {

		/* retourne false si pId == null. */
		if (pId == null) {
			return false;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}

		boolean resultat = false;

		ICountry objectPersistant = null;

		/* REQUETE HQL PARAMETREE. */
		final String requeteString 
		= SELECT_OBJET 
			+ "where country.id = :pId";

		/* Construction de la requête HQL. */
		final Query requete 
			= this.entityManager.createQuery(requeteString);

		/* Passage des paramètres de la requête HQL. */
		requete.setParameter("pId", pId);

		try {
			
			/* Execution de la requete HQL. */
			objectPersistant 
			= (ICountry) requete.getSingleResult();
			
		}
		catch (NoResultException noResultExc) {
			objectPersistant = null;
			resultat = false;
		}

		try {

			if (objectPersistant != null) {

				/* Obtention d'une Entity JPA à 
				 * partir de l'objet métier. */
				final CountryEntityJPA entity 
					= new CountryEntityJPA(objectPersistant);

				/* Merge avant destruction. */
				this.entityManager.merge(entity);

				/* ************ */
				/* DESTRUCTION. */
				this.entityManager.remove(entity);

				resultat = true;
			}

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(CLASSE_COUNTRYDAO_JPA_SPRING
						, "Méthode deleteByIdBoolean(Long pId)", e);
		}

		return resultat;

	} // Fin de deleteByIdBoolean(...).____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}


		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "delete from CountryEntityJPA";

		try {

			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);

			/* EXECUTION DE LA REQUETE. */
			query.executeUpdate();

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_COUNTRYDAO_JPA_SPRING
						, "Méthode deleteAll()", e);

		}

	} // Fin de deleteAll()._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteAllBoolean() throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}

		boolean resultat = false;

		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "delete from CountryEntityJPA";

		try {

			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);

			/* EXECUTION DE LA REQUETE. */
			query.executeUpdate();

			resultat = true;

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_COUNTRYDAO_JPA_SPRING
						, "Méthode deleteAllBoolean()", e);

		}

		return resultat;

	} // Fin de deleteAll()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteIterable(
			final Iterable<ICountry> pList) throws Exception {

		/* ne fait rien si pList == null. */
		if (pList == null) {
			return;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}

		final Iterator<ICountry> itePersistants = pList.iterator();
		final List<ICountry> listePersistants 
			= new ArrayList<ICountry>();

		/* Récupération préalable des objets persistants en base. */
		while (itePersistants.hasNext()) {
			
			final ICountry objet = itePersistants.next();
			
			/* récupère l'objet persistant dans le stockage. */
			final ICountry objectPersistant = this.retrieve(objet);

			if (objectPersistant != null) {
				listePersistants.add(objectPersistant);
			}
		}


		/* Itération uniquement sur la liste des Objets persistants. */
		final Iterator<ICountry> ite = listePersistants.iterator();

		try {

			while (ite.hasNext()) {

				final ICountry objectPersistant = ite.next();
				
				/* Obtention d'une Entity JPA à 
				 * partir de l'objet métier. */
				final CountryEntityJPA entity 
					= new CountryEntityJPA(objectPersistant);
				
				/* Merge avant destruction. */
				this.entityManager.merge(entity);

				/* ************ */
				/* DESTRUCTION. */
				this.entityManager.remove(entity);

			}

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(
					CLASSE_COUNTRYDAO_JPA_SPRING
					, "Méthode deleteIterable(Iterable)", e);

		}

	} // Fin de deleteIterable(...)._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteIterableBoolean(
			final Iterable<ICountry> pList) throws Exception {

		/* retourne false si pList == null. */
		if (pList == null) {
			return false;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}

		final Iterator<ICountry> itePersistants = pList.iterator();
		final List<ICountry> listePersistants 
			= new ArrayList<ICountry>();

		/* Récupération préalable des objets persistants en base. */
		while (itePersistants.hasNext()) {
			
			final ICountry objet = itePersistants.next();
			
			/* récupère l'objet persistant dans le stockage. */
			final ICountry objectPersistant = this.retrieve(objet);

			if (objectPersistant != null) {
				listePersistants.add(objectPersistant);
			}
		}


		boolean resultat = false;
		
		/* Itération uniquement sur la liste des Objets persistants. */
		final Iterator<ICountry> ite = listePersistants.iterator();

		try {

			while (ite.hasNext()) {

				final ICountry objectPersistant = ite.next();
				
				/* Obtention d'une Entity JPA à 
				 * partir de l'objet métier. */
				final CountryEntityJPA entity 
					= new CountryEntityJPA(objectPersistant);
				
				/* Merge avant destruction. */
				this.entityManager.merge(entity);

				/* ************ */
				/* DESTRUCTION. */
				this.entityManager.remove(entity);

			}
			
			resultat = true;

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(
					CLASSE_COUNTRYDAO_JPA_SPRING
					, "Méthode deleteIterableBoolean(Iterable)", e);

		}
		
		return resultat;

	} // Fin de deleteIterableBoolean(...).________________________________



	/* TOOLS *************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(
			final ICountry pObject) throws Exception {

		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}
		
		boolean resultat = false;		
		ICountry objectPersistant = null;

		/* REQUETE HQL PARAMETREE. */
		final String requeteString 
			= SELECT_OBJET
				+ "where country.name = :pName "
				+ "and country.capital = :pCapital";

		/* Construction de la requête HQL. */
		final Query requete 
			= this.entityManager.createQuery(requeteString);

		/* Passage des paramètres de la requête HQL. */
		requete.setParameter("pName", pObject.getName());
		requete.setParameter("pCapital", pObject.getCapital());

		try {

			/* Execution de la requete HQL. */
			objectPersistant 
			= (ICountry) requete.getSingleResult();

			/* retourne true si l'objet existe en base. */
			if (objectPersistant != null) {
				resultat = true;
			}

		}
		catch (NoResultException noResultExc) {

			/* retourne false si l'Objet métier n'existe pas en base. */
			return false;

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(CLASSE_COUNTRYDAO_JPA_SPRING
						, "Méthode exists(ICountry pObject)", e);
		}

		return resultat;

	} // Fin de exists(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(
			final Long pId) throws Exception {

		/* retourne false si pId == null. */
		if (pId == null) {
			return false;
		}

		/* retourne true si l'objet métier existe en base. */
		if (this.findById(pId) != null) {
			return true;
		}

		return false;

	} // Fin de exists(Long...).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long count() throws Exception {

		/* Récupère la liste d'Objets métier de Type paramétré ICountry. */
		final List<ICountry> listObjects = this.findAll();

		if (listObjects != null) {

			/* Retourne la taille de la liste. */
			return Long.valueOf(listObjects.size()) ;
		}

		/* retourne 0L si this.findAll() retourne null. */
		return 0L;

	} // Fin de count().___________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ecrireStockageDansConsole() throws Exception {
		
		/* récupération de tous les objets métier dans le stockage. */
		final List<ICountry> contenuStockage = this.findAll();
		
		/* ne fait rien si findAll() retourne null. */
		if (contenuStockage == null) {
			return;
		}
		
		for (final ICountry objet : contenuStockage) {
			System.out.println(objet.toString());
		}
		
	} // Fin de ecrireStockageDansConsole()._______________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String afficherListeObjetsMetier(
			final List<ICountry> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb =new StringBuilder();
		
		for (final ICountry objetMetier : pList) {
			
			stb.append(objetMetier.toString());
			stb.append(SAUT_LIGNE_JAVA);
		}
		
		return stb.toString();
		
	} // Fin de afficherListeObjetsMetier(...).____________________________


	
} // FIN DE LA CLASSE CountryDAO.--------------------------------------------
