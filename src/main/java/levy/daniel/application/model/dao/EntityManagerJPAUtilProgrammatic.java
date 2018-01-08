package levy.daniel.application.model.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
public final class EntityManagerJPAUtilProgrammatic {

	// ************************ATTRIBUTS************************************/

	/**
	 * TRUE : String :<br/>
	 * "true".<br/>
	 */
	public static final String TRUE = "true";
	
	
	/**
	 * FALSE : String :<br/>
	 * "false".<br/>
	 */
	public static final String FALSE = "false";
	
	
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
	 * properties : Map<String,String> :<br/>
	 * Map contenant tous les éléments de connexions à la base.<br/>
	 * Remplace le persistence.xml.<br/>
	 */
	private static Map<String, String> properties 
		= new ConcurrentHashMap<String, String>();

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(EntityManagerJPAUtilProgrammatic.class);
	
	static {

		try {
			
			/* Lit le fichier persistence.xml et le transmet au 
			 * Provider de l'implémentation Hibernate 
			 * org.hibernate.jpa.HibernatePersistenceProvider. */
			entityManagerFactory = Persistence.createEntityManagerFactory("persistence_Hibernate", properties);
			
			remplirProperties();
			
			/* Obtention de entityManager auprès de la Factory 
			 * en lui passant la Map properties. */
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
	private EntityManagerJPAUtilProgrammatic() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * method remplirProperties() :<br/>
	 * Insère la configuration de la persistence 
	 * dans la Map properties.<br/>
	 * <br/>
	 */
	private static void remplirProperties() {
		
		/* PROVIDER Hibernate********/
		properties.put("provider", "org.hibernate.jpa.HibernatePersistenceProvider");
		
		/* DRIVER. ***************/
		// postgresql 9.6.4 - port : 5432 - [postgres, postgres]
		properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
		// mySql 5.7.19.0 - port : 3306 - [root, root]
//		properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
		// hsqldb 2.4.0 ["sa", ""]
//		properties.put("javax.persistence.jdbc.driver", "org.hsqldb.jdbcDriver");
		
		/* CONNEXION A LA BASE. **********/
		// postgresql 9.6.4 - port : 5432 - [postgres, postgres]
	    properties.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/tuto_fragkouli");
	    // mySql 5.7.19.0 - port : 3306 - [root, root]
//	    properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/xxx");
	    // hsqldb 2.4.0 ["sa", ""]
//	    properties.put("javax.persistence.jdbc.url", "jdbc:hsqldb:hsql://localhost/xxx");
	    
	    /* LOGIN (username). *************/
	    // postgresql 9.6.4 - port : 5432 - [postgres, postgres]
	    properties.put("javax.persistence.jdbc.user", "postgres");
	    // mySql 5.7.19.0 - port : 3306 - [root, root]
//	    properties.put("javax.persistence.jdbc.user", "root");
	    // hsqldb 2.4.0 ["sa", ""]
//	    properties.put("javax.persistence.jdbc.user", "sa");
	    
	    /* MOT DE PASSE. ************/
	    // postgresql 9.6.4 - port : 5432 - [postgres, postgres]
	    properties.put("javax.persistence.jdbc.password", "postgres");
	    // mySql 5.7.19.0 - port : 3306 - [root, root]
//	    properties.put("javax.persistence.jdbc.password", "root");
	    // hsqldb 2.4.0 ["sa", ""]
//	    properties.put("javax.persistence.jdbc.password", "");
	    
	    /* DIALECTE Hibernate. *************/
	    // postgresql 9.6.4 - port : 5432 - [postgres, postgres]
	    properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
	    // mySql 5.7.19.0 - port : 3306 - [root, root]
//	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
	    // hsqldb 2.4.0 ["sa", ""]
//	    properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
	    
	    /* pour voir les requetes SQL. ***********/
	    properties.put("hibernate.show-sql", TRUE);
//	    properties.put("hibernate.show-sql", FALSE);
	    
	    /* Formater le SQL de sortie. ******/
	    properties.put("hibernate.format_sql", TRUE);
//	    properties.put("hibernate.format_sql", FALSE);
	    
	    /* faciliter le débogage du SQL. *********/	    
	    properties.put("hibernate.use_sql_comments", TRUE);
//	    properties.put("hibernate.use_sql_comments", FALSE);
	    	    
	    /* Pour générer des statistiques Hibernate. *******/
	    properties.put("hibernate.generate_statistics", TRUE);
//	    properties.put("hibernate.generate_statistics", FALSE);
	    
	    
	    /* Désactiver le cache de second niveau. ********/	    
	    properties.put("cache.provider_class", "org.hibernate.cache.NoCacheProvider");
	    
	    /* Activer le cache de second niveau. **********/
//	    properties.put("hibernate.cache.provider_class", "net.sf.ehcache.hibernate.EhCacheProvider");
//	    properties.put("hibernate.cache.use_second_level_cache", TRUE);
//	    properties.put("hibernate.cache.use_query_cache", TRUE);
//	    properties.put("net.sf.ehcache.configurationResourcename", "ehcache.xml");
		
	    
	    /* STRATEGIE DE CONSERVATION DES TABLES. ***********/
	    /* Recrée le schéma à chaque démarrage. Drop à la fermeture. */
//	    properties.put("hibernate.hbm2ddl.auto", "create-drop");
	    /* Supprime et re-créée le schéma de base de données au démarrage. */
//	    properties.put("hibernate.hbm2ddl.auto", "create");
	    /* Met à jour le schéma (sans destruction de données) si modification d'une Entity. */
//	    properties.put("hibernate.hbm2ddl.auto", "update");
	    /* VALIDE LE SCHEMA ET CONSERVE LES DONNEES. POUR LA PROD. */
	    properties.put("hibernate.hbm2ddl.auto", "validate");
	    
	    
	    /* POOL DE CONNEXION. ************/
	    properties.put("connection.provider_class", "org.hibernate.connection.C3P0ConnectionProvider");
	    properties.put("hibernate.c3p0.min_size", "10");
	    properties.put("hibernate.c3p0.max_size", "50");
	    properties.put("hibernate.c3p0.timeout", "30");
	    properties.put("hibernate.c3p0.acquire_increment", "1");	    
	    properties.put("hibernate.c3p0.max_statements", "50");
	    properties.put("hibernate.c3p0.idle_test_period", "2000");
		properties.put("hibernate.c3p0.unreturnedConnectionTimeout", "400");
		properties.put("hibernate.c3p0.debugUnreturnedConnectionStackTraces", "true");
		properties.put("hibernate.c3p0.acquireRetryAttempts", "1");
        properties.put("hibernate.c3p0.acquireRetryDelay", "250");

	    
	} // Fin de remplirProperties()._______________________________________
	
	
	
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


	
	/**
	 * method getProperties() :<br/>
	 * Getter de la Map contenant tous les éléments 
	 * de connexions à la base.<br/>
	 * Remplace le persistence.xml.<br/>
	 * <br/>
	 *
	 * @return properties : Map<String,String>.<br/>
	 */
	public static Map<String, String> getProperties() {
	
		return properties;
		
	} // Fin de getProperties().___________________________________________

	
		
} // FIN DE LA CLASSE EntityManagerJPAUtil.----------------------------------
