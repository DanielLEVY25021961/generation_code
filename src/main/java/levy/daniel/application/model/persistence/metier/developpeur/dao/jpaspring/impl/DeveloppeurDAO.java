package levy.daniel.application.model.persistence.metier.developpeur.dao.jpaspring.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import levy.daniel.application.model.metier.developpeur.IDeveloppeur;
import levy.daniel.application.model.persistence.daoexceptions.GestionnaireDaoException;
import levy.daniel.application.model.persistence.metier.developpeur.dao.jpaspring.IDeveloppeurDAO;
import levy.daniel.application.model.persistence.metier.developpeur.entities.jpa.DeveloppeurEntityJPA;




/**
 * CLASSE CONCRETE <b>DeveloppeurDAO</b> :<br/>
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
 * DeveloppeurDAO</span>
 * </p>
 * <ul>
 * <li>DAO <b>CONCRET</b> pour les <b>Country</b>.</li>
 * <li>
 * Implémente l'interface <b>IDeveloppeurDAO</b>.
 * </li>
 * <li>
 * Certaines méthodes (getOne(ID), ...) sont 
 * <b>compatibles SPRING DATA</b>.
 * </li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">IMPLEMENTATION DES DeveloppeurDAO</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../../../../../../../javadoc/images/implementation_Country_DAO_JpaSpring.png" 
 * alt="implémentation des DAOs Country JPA SPRING" border="1" align="center" />
 * </li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">UTILISATION DES DeveloppeurDAO</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../../../../javadoc/images/utilisation_Country_DAO_JpaSpring.png" 
 * alt="utilisation des DAOs DeveloppeurDAO JPA SPRING" border="1" align="center" />
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
@Repository("DeveloppeurDAOJPASpring")
public class DeveloppeurDAO implements IDeveloppeurDAO {
	
	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe DeveloppeurDAO".<br/>
	 */
	public static final String CLASSE_DEVELOPPEURDAO_JPA_SPRING 
		= "Classe DeveloppeurDAO";

	/**
	 * SAUT_LIGNE_JAVA : char :<br/>
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE_JAVA = '\n';

	/**
	 * SELECT_OBJET : String :<br/>
	 * "select developpeur from 
	 * DeveloppeurEntityJPA as developpeur ".<br/>
	 */
	public static final String SELECT_OBJET 
		= "select developpeur from "
				+ "DeveloppeurEntityJPA as developpeur ";
	
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
	private static final Log LOG = LogFactory.getLog(DeveloppeurDAO.class);

	
	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public DeveloppeurDAO() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/* CREATE ************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDeveloppeur create(
			final IDeveloppeur pObject) throws Exception {

		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}

		IDeveloppeur persistentObject = null;

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
			final DeveloppeurEntityJPA entity 
				= new DeveloppeurEntityJPA(pObject);

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
						CLASSE_DEVELOPPEURDAO_JPA_SPRING
							, "Méthode create(IDeveloppeur pObject)", e);

		}

		/* retourne l'Objet persistant. */
		return persistentObject;

	} // Fin de create(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persist(
			final IDeveloppeur pObject) throws Exception {

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
			final DeveloppeurEntityJPA entity 
				= new DeveloppeurEntityJPA(pObject);

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
						CLASSE_DEVELOPPEURDAO_JPA_SPRING
							, "Méthode persist(IDeveloppeur Object)", e);

		}

	} // Fin de persist(...).______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long createReturnId(
			final IDeveloppeur pObject) throws Exception {

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
		final IDeveloppeur objectPersistant 
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
	public Iterable<IDeveloppeur> save(
			final Iterable<IDeveloppeur> pList) 
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

		final List<IDeveloppeur> resultat = new ArrayList<IDeveloppeur>();

		final Iterator<IDeveloppeur> iteS = pList.iterator();

		try {

			while (iteS.hasNext()) {

				final IDeveloppeur objet = iteS.next();

				/* Passe les doublons existants en base. */
				if (!this.exists(objet)) {

					/* passe un null dans le lot. */
					if (objet != null) {

						/* Obtention d'une Entity JPA à partir 
						 * de l'objet métier. */
						final DeveloppeurEntityJPA entity 
							= new DeveloppeurEntityJPA(objet);
						
						IDeveloppeur objectPersistant = null;

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
										CLASSE_DEVELOPPEURDAO_JPA_SPRING
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
						CLASSE_DEVELOPPEURDAO_JPA_SPRING
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
	public IDeveloppeur retrieve(
			final IDeveloppeur pObject) throws Exception {

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

		IDeveloppeur objectPersistant = null;

		/* REQUETE HQL PARAMETREE. */
		final String requeteString 
			= SELECT_OBJET
				+ "where developpeur.nom = :pNom" 
					+ "and developpeur.anneesExperiences = :pAnneesExperiences";

		/* Construction de la requête HQL. */
		final Query requete 
			= this.entityManager.createQuery(requeteString);

		/* Passage des paramètres de la requête HQL. */
		requete.setParameter("pNom", pObject.getNom());
		requete.setParameter("pAnneesExperiences", pObject.getAnneesExperience());

		try {

			/* Execution de la requete HQL. */
			objectPersistant 
				= (IDeveloppeur) requete.getSingleResult();

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
						CLASSE_DEVELOPPEURDAO_JPA_SPRING
						, "Méthode retrieve(IDeveloppeur pObject)", e);
		}

		return objectPersistant;

	} // Fin de retrieve(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDeveloppeur findById(
			final Long pId) throws Exception {

		IDeveloppeur objetTrouve = null;

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
					.find(DeveloppeurEntityJPA.class, pId);

		}
		catch (Exception e) {

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_DEVELOPPEURDAO_JPA_SPRING
						, "Méthode findById(Long)", e);

		}

		return objetTrouve;

	} // Fin de findById(...)._____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long retrieveId(
			final IDeveloppeur pObject) throws Exception {
		
		final IDeveloppeur object = this.retrieve(pObject);
		
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
	public List<IDeveloppeur> findAll() throws Exception {

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
			= "from DeveloppeurEntityJPA";

		List<IDeveloppeur> resultat = null;

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
						CLASSE_DEVELOPPEURDAO_JPA_SPRING
						, "Méthode findall()", e);

		}

		/* Retourne la liste résultat. */
		return resultat;

	} // Fin de findAll()._________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IDeveloppeur> findAllMax(
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
			= "from DeveloppeurEntityJPA";

		List<IDeveloppeur> resultat = null;

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
						CLASSE_DEVELOPPEURDAO_JPA_SPRING
						, "Méthode findAllMax(...)", e);

		}

		/* Retourne la liste résultat. */
		return resultat;

	} // Fin de findAllMax(...).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<IDeveloppeur> findAll(
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

		final List<IDeveloppeur> resultat = new ArrayList<IDeveloppeur>();		

		final Iterator<Long> iteratorID = pIds.iterator();

		while (iteratorID.hasNext()) {

			final Long id = iteratorID.next();
			
			/* Recherche en base sur Long. */
			final IDeveloppeur objetEnBase = this.findById(id);

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
	public IDeveloppeur update(
			final IDeveloppeur pObject) throws Exception {

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

		IDeveloppeur persistentObject = null;

		try {

			/* Obtention d'une Entity JPA à partir de l'objet métier. */
			final DeveloppeurEntityJPA entity 
				= new DeveloppeurEntityJPA(pObject);
			
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
						CLASSE_DEVELOPPEURDAO_JPA_SPRING
						, "Méthode update(IDeveloppeur Object)", e);

		}

		/* retourne l'Objet persistant modifié. */
		return persistentObject;

	} // Fin de update(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDeveloppeur update(
			final Long pId, final IDeveloppeur pObjectModifie) 
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
		final IDeveloppeur objectPersistant = this.findById(pId);
		
		/* retourne null s'il n'y a pas d'objet persistant à pId. */
		if (objectPersistant == null) {
			return null;
		}

		IDeveloppeur persistentObject = null;
		
		try {
			
			final Long id = objectPersistant.getId();
			
			/* Obtention d'une Entity JPA à partir de l'objet métier. */
			final DeveloppeurEntityJPA entity 
				= new DeveloppeurEntityJPA(pObjectModifie);
			
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
						CLASSE_DEVELOPPEURDAO_JPA_SPRING
						, "Méthode update(Long pId"
								+ ", IDeveloppeur Object)", e);
			
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
			final IDeveloppeur pObject) throws Exception {

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
		final IDeveloppeur objectPersistant = this.retrieve(pObject);
		
		/* retourne false si pObject n'est pas persisté. */
		if (objectPersistant == null) {
			return false;
		}

		boolean resultat = false;
		
		try {

			/* Obtention d'une Entity JPA à partir de l'objet métier. */
			final DeveloppeurEntityJPA entity 
				= new DeveloppeurEntityJPA(objectPersistant);

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
						CLASSE_DEVELOPPEURDAO_JPA_SPRING
						, "Méthode delete(IDeveloppeur pObject)", e);

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

		IDeveloppeur objectPersistant = null;

		/* REQUETE HQL PARAMETREE. */
		final String requeteString 
		= SELECT_OBJET 
		+ "where developpeur.id = :pId";

		/* Construction de la requête HQL. */
		final Query requete 
			= this.entityManager.createQuery(requeteString);

		/* Passage des paramètres de la requête HQL. */
		requete.setParameter("pId", pId);

		try {
			
			/* Execution de la requete HQL. */
			objectPersistant 
			= (IDeveloppeur) requete.getSingleResult();
		}
		catch (NoResultException noResultExc) {
			objectPersistant = null;
		}

		try {

			if (objectPersistant != null) {

				/* Obtention d'une Entity JPA à partir de l'objet métier. */
				final DeveloppeurEntityJPA entity 
					= new DeveloppeurEntityJPA(objectPersistant);

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
						CLASSE_DEVELOPPEURDAO_JPA_SPRING
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

		IDeveloppeur objectPersistant = null;

		/* REQUETE HQL PARAMETREE. */
		final String requeteString 
		= SELECT_OBJET 
			+ "where developpeur.id = :pId";

		/* Construction de la requête HQL. */
		final Query requete 
			= this.entityManager.createQuery(requeteString);

		/* Passage des paramètres de la requête HQL. */
		requete.setParameter("pId", pId);

		try {
			
			/* Execution de la requete HQL. */
			objectPersistant 
			= (IDeveloppeur) requete.getSingleResult();
			
		}
		catch (NoResultException noResultExc) {
			objectPersistant = null;
			resultat = false;
		}

		try {

			if (objectPersistant != null) {

				/* Obtention d'une Entity JPA à 
				 * partir de l'objet métier. */
				final DeveloppeurEntityJPA entity 
					= new DeveloppeurEntityJPA(objectPersistant);

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
				.gererException(CLASSE_DEVELOPPEURDAO_JPA_SPRING
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
			= "delete from DeveloppeurEntityJPA";

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
						CLASSE_DEVELOPPEURDAO_JPA_SPRING
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
			= "delete from DeveloppeurEntityJPA";

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
						CLASSE_DEVELOPPEURDAO_JPA_SPRING
						, "Méthode deleteAllBoolean()", e);

		}

		return resultat;

	} // Fin de deleteAll()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteIterable(
			final Iterable<IDeveloppeur> pList) throws Exception {

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

		final Iterator<IDeveloppeur> itePersistants = pList.iterator();
		final List<IDeveloppeur> listePersistants 
			= new ArrayList<IDeveloppeur>();

		/* Récupération préalable des objets persistants en base. */
		while (itePersistants.hasNext()) {
			
			final IDeveloppeur objet = itePersistants.next();
			
			/* récupère l'objet persistant dans le stockage. */
			final IDeveloppeur objectPersistant = this.retrieve(objet);

			if (objectPersistant != null) {
				listePersistants.add(objectPersistant);
			}
		}


		/* Itération uniquement sur la liste des Objets persistants. */
		final Iterator<IDeveloppeur> ite = listePersistants.iterator();

		try {

			while (ite.hasNext()) {

				final IDeveloppeur objectPersistant = ite.next();
				
				/* Obtention d'une Entity JPA à 
				 * partir de l'objet métier. */
				final DeveloppeurEntityJPA entity 
					= new DeveloppeurEntityJPA(objectPersistant);
				
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
					CLASSE_DEVELOPPEURDAO_JPA_SPRING
					, "Méthode deleteIterable(Iterable)", e);

		}

	} // Fin de deleteIterable(...)._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteIterableBoolean(
			final Iterable<IDeveloppeur> pList) throws Exception {

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

		final Iterator<IDeveloppeur> itePersistants = pList.iterator();
		final List<IDeveloppeur> listePersistants 
			= new ArrayList<IDeveloppeur>();

		/* Récupération préalable des objets persistants en base. */
		while (itePersistants.hasNext()) {
			
			final IDeveloppeur objet = itePersistants.next();
			
			/* récupère l'objet persistant dans le stockage. */
			final IDeveloppeur objectPersistant = this.retrieve(objet);

			if (objectPersistant != null) {
				listePersistants.add(objectPersistant);
			}
		}


		boolean resultat = false;
		
		/* Itération uniquement sur la liste des Objets persistants. */
		final Iterator<IDeveloppeur> ite = listePersistants.iterator();

		try {

			while (ite.hasNext()) {

				final IDeveloppeur objectPersistant = ite.next();
				
				/* Obtention d'une Entity JPA à 
				 * partir de l'objet métier. */
				final DeveloppeurEntityJPA entity 
					= new DeveloppeurEntityJPA(objectPersistant);
				
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
					CLASSE_DEVELOPPEURDAO_JPA_SPRING
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
			final IDeveloppeur pObject) throws Exception {

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
		IDeveloppeur objectPersistant = null;

		/* REQUETE HQL PARAMETREE. */
		final String requeteString 
			= SELECT_OBJET
				+ "where developpeur.nom = :pNom "
				+ "and developpeur.anneesExperiences = :pAnneesExperiences";

		/* Construction de la requête HQL. */
		final Query requete 
			= this.entityManager.createQuery(requeteString);

		/* Passage des paramètres de la requête HQL. */
		requete.setParameter("pNom", pObject.getNom());
		requete.setParameter("pAnneesExperiences", pObject.getAnneesExperience());

		try {

			/* Execution de la requete HQL. */
			objectPersistant 
			= (IDeveloppeur) requete.getSingleResult();

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
				.gererException(CLASSE_DEVELOPPEURDAO_JPA_SPRING
						, "Méthode exists(IDeveloppeur pObject)", e);
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

		/* Récupère la liste d'Objets métier de Type paramétré IDeveloppeur. */
		final List<IDeveloppeur> listObjects = this.findAll();

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
		final List<IDeveloppeur> contenuStockage = this.findAll();
		
		/* ne fait rien si findAll() retourne null. */
		if (contenuStockage == null) {
			return;
		}
		
		for (final IDeveloppeur objet : contenuStockage) {
			System.out.println(objet.toString());
		}
		
	} // Fin de ecrireStockageDansConsole()._______________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String afficherListeObjetsMetier(
			final List<IDeveloppeur> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb =new StringBuilder();
		
		for (final IDeveloppeur objetMetier : pList) {
			
			stb.append(objetMetier.toString());
			stb.append(SAUT_LIGNE_JAVA);
		}
		
		return stb.toString();
		
	} // Fin de afficherListeObjetsMetier(...).____________________________


	
} // FIN DE LA CLASSE DeveloppeurDAO.--------------------------------------------
