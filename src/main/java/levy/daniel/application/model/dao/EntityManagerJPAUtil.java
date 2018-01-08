package levy.daniel.application.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.stat.Statistics;

/**
 * class EntityManagerJPAUtil :<br/>
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
 * @since 19 sept. 2017
 *
 */
public final class EntityManagerJPAUtil {

	// ************************ATTRIBUTS************************************/

	/**
	 * entityManagerFactory : EntityManagerFactory :<br/>
	 * Lit la configuration d'accès à la base stockée 
	 * dans /META-INF/persistence.sml une fois pour toutes
	 * lors du premier accès à la base.<br/>
	 */
	private static EntityManagerFactory entityManagerFactory;
	
	/**
	 * entityManager : EntityManager :<br/>
	 * Gestionnaire de la persistence JPA .<br/>
	 */
	private static EntityManager entityManager;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(EntityManagerJPAUtil.class);
	
	static {

		try {
			
			/* Lit le fichier persistence.xml et le transmet au 
			 * Provider de l'implémentation Hibernate 
			 * org.hibernate.jpa.HibernatePersistenceProvider. */
			entityManagerFactory = Persistence.createEntityManagerFactory(
						"persistence_unit_base-tuto-jse-fragkouli");
			
			/* Obtention de entityManager auprès de la Factory. */
			entityManager = entityManagerFactory.createEntityManager();

		}
		catch (Throwable pCause) {
			
			final String message 
			= "Problème de Configuration : " + pCause.getMessage();
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Problème GRAVE de Configuration : ", pCause);
			}
		
			throw new RuntimeException(message, pCause);
			
		}

	} // Fin du bloc static___________________________

	
	
	
	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR EntityManagerJPAUtil() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * Utile pour bloquer l'instanciation.<br/>
	 */
	private EntityManagerJPAUtil() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * method logStatistics( Statistics pStatistics) :<br/>
	 * Logge les statistiques Hibernate.<br/>
	 * <br/>
	 *
	 * @param pStatistics : Statistics.<br/>
	 */
	public static void logStatistics(
			final Statistics pStatistics) {

		if (pStatistics == null) {
			return;
		}

		long sessionOpenedCount = 0;
		long sessionClosedCount = 0;

		long transactionCount = 0;
		long transactionSuccessfulCount = 0;

		long flushCount = 0;

		long prepareStatementCount = 0;
		long closeStatementCount = 0;

		/* Elaboration des messages. */
		final String messageSessionOpenedCount =
				"Nombre de sessions Hibernate ouvertes (getSessionOpenCount()) : ";
		final String messageSessionClosedCount =
				"Nombre de sessions Hibernate fermées (getSessionCloseCount()) : ";

		final String messageTransactionCount =
				"Nombre de transactions démarrées (getTransactionCount()) : ";
		final String messageTransactionSuccessfulCount =
				"Nombre de transactions committées (getSuccessfulTransactionCount()) : ";

		final String messageFlushCount = "Nombre de Flushes (getFlushCount()) : ";

		final String messagePreparedStmts =
				"Nombre de Prepared Statements ouverts (getPrepareStatementCount()) : ";
		final String messageCloseStmts =
				"Nombre de Prepared Statements fermés (getCloseStatementCount()) : ";

		/* Calcul des statistiques Hibernate. */
		sessionOpenedCount = pStatistics.getSessionOpenCount();
		sessionClosedCount = pStatistics.getSessionCloseCount();

		transactionCount = pStatistics.getTransactionCount();
		transactionSuccessfulCount 
		= pStatistics.getSuccessfulTransactionCount();

		flushCount = pStatistics.getFlushCount();

		prepareStatementCount = pStatistics.getPrepareStatementCount();
		closeStatementCount = pStatistics.getCloseStatementCount();

		if (LOG.isTraceEnabled()) {

			LOG.trace(messageSessionOpenedCount + sessionOpenedCount);
			LOG.trace(messageSessionClosedCount + sessionClosedCount);

			LOG.trace(messageTransactionCount + transactionCount);
			LOG.trace(messageTransactionSuccessfulCount 
					+ transactionSuccessfulCount);

			LOG.trace(messageFlushCount + flushCount);

			LOG.trace(messagePreparedStmts + prepareStatementCount);
			LOG.trace(messageCloseStmts + closeStatementCount);
		}

	} // Fin de logStatistics(
		// Statistics pStatistics).________________________________________


	
	/**
	 * method getEntityManager() :<br/>
	 * Getter du Gestionnaire de la persistence JPA .<br/>
	 * <br/>
	 *
	 * @return entityManager : EntityManager.<br/>
	 */
	public static EntityManager getEntityManager() {
	
		return entityManager;
		
	} // Fin de getEntityManager().________________________________________

	
	
	
} // FIN DE LA CLASSE EntityManagerJPAUtil.----------------------------------
