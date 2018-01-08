package levy.daniel.application.model.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.stat.Statistics;

/**
 * APRES HIBERNATE 4 class HibernateUtil :<br/>
 * - Crée un Singleton de org.hibernate.SessionFactory capable d'ouvrir de nouvelles
 * org.hibernate.Session.<br/>
 * La SessionFactory se charge de lire le fichier hibernate.cfg.xml et d’initialiser Hibernate pour
 * une utilisation ultérieure.<br/>
 * La méthode getSession() utilise la sessionFactory pour créer une nouvelle session Hibernate
 * chargée d' accéder à la base de données.<br/>
 * Une org.hibernate.Session est une unité de travail simplement threadée.<br/>
 * <br/>
 * - Exemple d'utilisation :<br/>
 * <br/>
 * - Mots-clé :<br/>
 * Bloc static,<br/>
 * <br/>
 * - Dépendances :<br/>
 * <br/>
 *
 * @author Developpement
 * @version 1.0
 * @since 18 avr. 2011
 */
public final class HibernateUtil {

	// ---------------------ATTRIBUTS------------------------------------ /

	/**
	 * CLASSE_HIBERNATEUTIL : String :<br/>
	 * "Classe HibernateUtil - ".<br/>
	 */
	public static final String	CLASSE_HIBERNATEUTILNG =
			"Classe HibernateUtil - ";


	/**
	 * registry: <br/>
	 * A SessionFactory is set up once for an application! <br/> 
	 * configures settings from hibernate.cfg.xml.<br/>
	 */
	private static StandardServiceRegistry registry;

	
	/**
	 * sessionFactory : org.hibernate.SessionFactory.<br/>
	 * Lit la configuration d'accès à la base stockée 
	 * dans hibernate.config.xml une fois pour toutes
	 * lors du premier accès à la base.<br/>
	 */
	private static SessionFactory sessionFactory;
	
	

	/**
	 * SESSION : ThreadLocal&lt;Session&gt; :<br/>
	 * ThreadLocal encapsulant une org.hibernate.Session.<br/>
	 */
	private static final ThreadLocal<Session> SESSION 
		= new ThreadLocal<Session>();

	
	/**
	 * LOG : Log : Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG =
			LogFactory.getLog(HibernateUtil.class);

	static {

		try {
			
			/* Instanciation d'un StandardServiceRegistryBuilder. */
			final StandardServiceRegistryBuilder serviceRegistryBuilder 
				= new StandardServiceRegistryBuilder();
			
			/* Lit la configuration depuis hibernate.cfg.xml. */
			serviceRegistryBuilder.configure();
			
			/* Construit le StandardServiceRegistry. */
			registry = serviceRegistryBuilder.build();
			
			/*
			 * Création d'une org.hibernate.SessionFactory 
			 * à partir du StandardServiceRegistry Hibernate.
			 */
			sessionFactory 
			= new MetadataSources(registry)
				.buildMetadata().buildSessionFactory();

		} catch (HibernateException hibEx) {

			/* Détruit manuellement le StandardServiceRegistry 
			 * en cas d'Exception. */
			StandardServiceRegistryBuilder.destroy(registry);
			
			final String message 
				= "Problème de Configuration : " + hibEx.getMessage();
			
			throw new RuntimeException(message, hibEx);

		} catch (Exception e) {
			
			/* Détruit manuellement le StandardServiceRegistry 
			 * en cas d'Exception. */
			StandardServiceRegistryBuilder.destroy(registry);

			throw new RuntimeException(e);
			
		}

	} // Fin du bloc static___________________________

	
	
	// -----------------------METHODES----------------------------------- /

	
	/**
	 * method CONSTRUCTEUR HibernateUtil() : 
	 * Constructeur private pour empêcher l'instanciation.
	 */
	private HibernateUtil() {
		super();
	} // Fin de CONSTRUCTEUR HibernateUtil().________________________________

	
	
	/**
	 * method currentSession():<br/>
	 * Retourne la org.hibernate.Session courante (Singleton) 
	 * ou en ouvre une nouvelle.<br/>
	 *
	 * @return mySession : Session.<br/>
	 * 
	 * @throws HibernateException lorsque : 
	 * la org.hibernate.Session n'a pu être ouverte.<br/>
	 */
	public static Session currentSession() throws HibernateException {

		Session mySession = SESSION.get();

		/*
		 * Ouvre une nouvelle SESSION si ce Thread n'en a aucune.
		 */
		if (mySession == null) {
			mySession = sessionFactory.openSession();
			SESSION.set(mySession);
		}

		return mySession;

	} // Fin de currentSession()._________________________

	
	
	/**
	 * method closeSession() :<br/>
	 * Ferme la org.hibernate.Session courante.<br/>
	 *
	 * @throws HibernateException lorsque : 
	 * la org.hibernate.Session n'a pu être fermée.<br/>
	 */
	public static void closeSession() throws HibernateException {

		final Session mySession = SESSION.get();
		SESSION.set(null);

		if (mySession != null) {
			mySession.close();
		}

	} // Fin de closeSession().____________________________________________

	
	
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
	 * method getSessionFactory() :<br/>
	 * Getter de la SessionFactory.<br/>
	 * <br/>
	 *
	 * @return sessionFactory : SessionFactory.<br/>
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	} // Fin de getSessionFactory()._______________________________________
	
	

} // FIN DE LA CLASSE HibernateUtil.---------------------------------------
