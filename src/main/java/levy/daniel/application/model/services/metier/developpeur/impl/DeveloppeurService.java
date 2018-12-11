package levy.daniel.application.model.services.metier.developpeur.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import levy.daniel.application.model.metier.developpeur.IDeveloppeur;
import levy.daniel.application.model.persistence.metier.developpeur.dao.jpaspring.IDeveloppeurDAO;
import levy.daniel.application.model.services.metier.developpeur.IDeveloppeurService;


/**
 * CLASSE DeveloppeurService :<br/>
 * SERVICE.<br/>
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
@Service("DeveloppeurServiceJPASpring")
public class DeveloppeurService implements IDeveloppeurService {
	
	// ************************ATTRIBUTS************************************/

	/**
	 * DAO pour les IDeveloppeur.<br/>
	 * INJECTE PAR SPRING.<br/>
	 */
	@Autowired
	@Qualifier("DeveloppeurDAOJPASpring")
	private transient IDeveloppeurDAO developpeurDAO;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(DeveloppeurService.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public DeveloppeurService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public IDeveloppeur create(
			final IDeveloppeur pObject) throws Exception {
		return this.developpeurDAO.create(pObject);
	} // Fin de create(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persist(
			final IDeveloppeur pObject) throws Exception {
		this.developpeurDAO.persist(pObject);
	} // Fin de persist(...).______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long createReturnId(
			final IDeveloppeur pObject) throws Exception {
		return this.developpeurDAO.createReturnId(pObject);
	} // Fin de createReturnId(...)._______________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<IDeveloppeur> save(
			final Iterable<IDeveloppeur> pList) throws Exception {
		return this.developpeurDAO.save(pList);
	} // Fin de save(...)._________________________________________________



	/* READ *************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDeveloppeur retrieve(
			final IDeveloppeur pObject) throws Exception {
		return this.developpeurDAO.retrieve(pObject);
	} // Fin de retrieve(...)._____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDeveloppeur findById(
			final Long pId) throws Exception {
		return this.developpeurDAO.findById(pId);
	} // Fin de findById(...)._____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long retrieveId(
			final IDeveloppeur pObject) throws Exception {
		return this.developpeurDAO.retrieveId(pObject);
	} // Fin de retrieveId(...).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IDeveloppeur> findAll() throws Exception {
		return this.developpeurDAO.findAll();
	} // Fin de findAll()._________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IDeveloppeur> findAllMax(
			final int pStartPosition
				, final int pMaxResult) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<IDeveloppeur> findAll(
			final Iterable<Long> pIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDeveloppeur update(
			final Long pId
				, final IDeveloppeur pObjectModifie) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDeveloppeur update(
			final IDeveloppeur pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(
			final IDeveloppeur pObject) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(
			final Long pId) throws Exception {
		// TODO Auto-generated method stub
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteByIdBoolean(
			final Long pId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteAllBoolean() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteIterable(
			final Iterable<IDeveloppeur> pList) throws Exception {
		// TODO Auto-generated method stub
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteIterableBoolean(
			final Iterable<IDeveloppeur> pList) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(
			final IDeveloppeur pObject) throws Exception {
		return this.developpeurDAO.exists(pObject);
	} // Fin de exists(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(
			final Long pId) throws Exception {
		return this.developpeurDAO.exists(pId);
	} // Fin de exists(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long count() throws Exception {
		return this.developpeurDAO.count();
	} // Fin de count().___________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ecrireStockageDansConsole() throws Exception {
		this.developpeurDAO.ecrireStockageDansConsole();
	} // Fin de ecrireStockageDansConsole()._______________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String afficherListeObjetsMetier(
			final List<IDeveloppeur> pList) {
		return this.developpeurDAO.afficherListeObjetsMetier(pList);
	} // Fin de afficherListeObjetsMetier(...).____________________________

	
	
} // FIN DE LA CLASSE DeveloppeurService.----------------------------------------
