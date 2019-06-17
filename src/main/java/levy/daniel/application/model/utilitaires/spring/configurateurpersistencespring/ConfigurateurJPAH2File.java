package levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.jpa.boot.spi.Bootstrap;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;



/**
 * CLASSE ConfigurateurJPAH2File :<br/>
 * Configuration d'une SGBDR H2 en MODE FILE.<br/>
 * <ul>
 * <li><b>Configure un EntityManagerFactory à partir de valeurs contenues 
 * dans un fichier properties</b>.</li>
 * <li>les propriétés modifiables sont stockées dans un fichier 
 * properties dans src\main\resources\configurations_bases_jpa</li>
 * <li>le fichier properties utilisé est indiqué dans l'annotation 
 * <b>PropertySource</b> en tête de la classe.</li>
 * </ul>
 * <ul>
 * <li>Configure un EntityManagerFactory.</li>
 * <li>Configure un TransactionManager</li>
 * <li>Configure un ExceptionTranslation</li>
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
 * @since 12 janv. 2019
 *
 */
//@Configuration(value="ConfigurateurJPAH2File")
//@PropertySources({@PropertySource("classpath:configurations_bases_jpa/configuration_H2_file.properties")})
//@EnableTransactionManagement
//@ComponentScans({@ComponentScan("levy.daniel.application.model.persistence")})
public class ConfigurateurJPAH2File {

	// ************************ATTRIBUTS************************************/

	/**
	 * <b>lecteur SPRING du fichier properties</b> proposé 
	 * dans l'annotation sur la présente classe 'PropertySource'.
	 * <ul>
	 * <li>injecté par SPRING via le Setter 
	 * <code>setEnvironmentSpring(Environment pEnvironmentSpring)</code>
	 * .</li>
	 * <li>org.springframework.core.env.Environment</li>
	 * </ul>
	 */
	private Environment environmentSpring;

	
	/**
	 * conteneur pour les valeurs lues dans le properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.
	 */
	public PersistenceUnitInfoJPASansXML persistenceUnitInfoJPASansXML 
		= new PersistenceUnitInfoJPASansXML();

	/**
	 * Lecteur de fichier properties SPRING chargé de fournir 
	 * une PersistenceUnitInfo (MutablePersistenceUnitInfoJPASpringSansXML) 
	 * pour instancier une EntityManagerFactory sans lire de persistence.xml.
	 */
	private LecteurPropertiesSpring lecteurPropertiesSpring;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ConfigurateurJPAH2File.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public ConfigurateurJPAH2File() {
		super();
		System.out.println();
		System.out.println("********* DANS LE CONSTRUCTEUR ConfigurateurJPAH2File***************");
		System.out.println();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

		
	/**
	 * <b>fournit un bean <i>org.springframework.orm.
	 * jpa.LocalContainerEntityManagerFactoryBean</i> 
	 * équivalent à <i>javax.persistence.EntityManagerFactory</i> 
	 * au CONTEXTE SPRING pour l'injection</b>.<br/>
	 * <ul>
	 * <li><b>fabrique l'EntityManagerFactory</b> en lisant 
	 * les valeurs de configuration de la base directement 
	 * dans la présente classe 
	 * <i>(pas dans un META-INF/persistence.xml)</i>.</li>
	 * <li><b>fixe le nom de l'unité de persistence</b> avec 
	 * la valeur lue dans le fichier properties.</li>
	 * <li>stipule que l'ORM est <b>HIBERNATE</b>.</li>
	 * <li><b>passe la DataSource</b> à l'EntityManagerFactory.</li>
	 * <li>scanne le package de persistence pour 
	 * trouver les classes annotées.</li>
	 * <li> ajoute des propriétés additionnelles à l'EntityManagerFactory 
	 * (Dialecte Hibernate, stratégie de création de tables, ...).</li>
	 * <li>le nom de la méthode (ici entityManagerFactory()) 
	 * est le nom du Bean injecté dans le contexte SPRING. 
	 * Donc si la méthode s'appelait "toto()", 
	 * le bean s'appellerait "toto" dans le contexte.</li>
	 * </ul>
	 *
	 * @return : LocalContainerEntityManagerFactoryBean : 
	 * Proxy du EntityManagerFactory.<br/>
	 * 
	 * @throws Exception 
	 */
//	@Bean
	public EntityManagerFactory entityManagerFactoryInfo() 
			throws Exception {
		 		
		final LocalContainerEntityManagerFactoryBean entityManagerFactory 
			= new LocalContainerEntityManagerFactoryBean();
	
				
		// PERSISTENCE UNIT
		/*
		 * fixe le nom de l'unité de persistence avec 
		 * la valeur lue dans le fichier properties.
		 */
		final String persistenceUnitName 
			= this.environmentSpring.getProperty(
				"javax.persistence.jdbc.persistence-unit.name");
		
		if (persistenceUnitName != null) {
			
			entityManagerFactory
				.setPersistenceUnitName(persistenceUnitName);
			
		} else {
			
			final String message 
				= "ConfigurateurJPAH2File "
						+ "- entityManagerFactory "
						+ "- IMPOSSIBLE DE LIRE LE NOM DE "
						+ "L'UNITE DE PERSISTENCE";
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
						
		// JPAVENDORADAPTER
		/* stipule que l'ORM est HIBERNATE. */
		entityManagerFactory.setJpaVendorAdapter(
				this.vendorAdapterHibernate());

		
		// DATASOURCE
		/* passe la DataSource à l'EntityManagerFactory. */
		entityManagerFactory.setDataSource(this.dataSource());		

		
		// PACKAGES SCANNES
		/* scanne le package de persistence 
		 * pour trouver les classes annotées. */
		entityManagerFactory.setPackagesToScan(
				new String[] {"levy.daniel.application.model.persistence"});

		
		// PROPRIETES SPECIFIQUES ORM HIBERNATE
		/* ajoute des propriétés additionnelles à l'EntityManagerFactory 
		 * (Dialecte Hibernate, stratégie de création de tables, ...). */
		entityManagerFactory.setJpaProperties(additionalProperties());
		
		entityManagerFactory.afterPropertiesSet();
		
		return entityManagerFactory.getObject();
		
	} // Fin de entityManagerFactory().____________________________________
	

	/**
	 * .<br/>
	 * <br/>
	 * : void :  .<br/>
	 */
	public void essai() {
		
		final LocalContainerEntityManagerFactoryBean entityManagerFactory 
		= new LocalContainerEntityManagerFactoryBean();
		
		final DefaultPersistenceUnitManager persistenceUnitManager = new DefaultPersistenceUnitManager();
		
		persistenceUnitManager.setDefaultPersistenceUnitName("toto");
		persistenceUnitManager.setDefaultDataSource(this.dataSource());
		
		entityManagerFactory.getPersistenceUnitInfo();
		
	}
	
	
	
	/**
	 * <b>fournit un Bean précisant que l'ORM est HIBERNATE</b>.<br/>
	 *
	 * @return : JpaVendorAdapter : 
	 * org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter.<br/>
	 */
//	@Bean
	public JpaVendorAdapter vendorAdapterHibernate() {
		
//		final JpaVendorAdapter vendorAdapter 
//      		= new HibernateJpaVendorAdapter();
		
		final HibernateJpaVendorAdapter vendorAdapter 
  			= new HibernateJpaVendorAdapter();
		
		vendorAdapter.setDatabase(Database.H2);
		
		return vendorAdapter;
		
	} // Fin de vendorAdapterHibernate().__________________________________

	
	
	/**
	 * <b>Instancie un 
	 * org.springframework.jdbc.datasource.DriverManagerDataSource 
	 * (implémentation SPRING de javax.sql.DataSource), l'alimente
	 * avec [URL, DRIVER, LOGIN, MDP]</b> 
	 * et <b>retourne un javax.sql.DataSource pour l'injecter 
	 * dans le CONTEXTE SPRING</b>.<br/>
	 * <ul>
	 * <li>lit l'URL de la BASE dans le properties 
	 * et l'injecte dans la DataSource.</li>
	 * <li>lit le DRIVER de la BASE dans le properties 
	 * et l'injecte dans la DataSource.</li>
	 * <li>lit le [Login + Mdp] à la base dans le properties 
	 * et l'injecte dans le DataSource.</li>
	 * </ul>
	 *
	 * @return : DataSource : javax.sql.DataSource.<br/>
	 */
	@Bean
	public DataSource dataSource() {

		/* DriverManagerDataSource */
		final SimpleDriverDataSource dataSource 
			= new SimpleDriverDataSource();
		
		
		// URL
		/* lit l'URL de la BASE dans le properties 
		 * et l'injecte dans la DataSource. */
		final String url 
			= this.environmentSpring.getProperty(
				"javax.persistence.jdbc.connexion.url");
		
		if (url != null) {
			
			final String urlNormalisee = this.normaliserURLFile(url);
			System.out.println("URL NORMALISEE DANS DATASOURCE dataSource() : " + urlNormalisee);
			dataSource.setUrl(urlNormalisee);
		}

		
		// DRIVER
		Driver driverH2 = null;
		String driverString = null;
		
		driverString 
			= this.environmentSpring.getProperty(
				"javax.persistence.jdbc.driver");
		
		if (driverString == null) {
			driverString = "org.h2.Driver";
		}
		
		try {
			
			driverH2 = (Driver) Class.forName(driverString).newInstance();
			DriverManager.registerDriver(driverH2);
			
		} catch (Exception e) {
			
			final String message 
				= "méthode dataSource() - impossible de charger le DRIVER H2";
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message, e);
			}
		}
		
		dataSource.setDriver(driverH2);
		
		// LOGIN + MDP
		/* lit le [Login + Mdp] à la base dans le properties 
		 * et l'injecte dans le DataSource. */
		final String userName 
			= this.environmentSpring.getProperty(
				"javax.persistence.jdbc.connection.username");
		
		if (userName != null) {
			dataSource.setUsername(userName);
		} else {
			dataSource.setUsername("sa");
		}
		
		final String password 
			= this.environmentSpring.getProperty(
				"javax.persistence.jdbc.connection.password");
		
		if (password != null) {
			dataSource.setPassword(password);
		} else {
			dataSource.setPassword("sa");
		}
			
		return dataSource;
		
	} // Fin de dataSource().______________________________________________

	
	
	/**
	 * <b>ajoute des propriétés spécifiques 
	 * à l'ORM, à SPRING, à H2, ...</b>.<br/>
	 * <ul>
	 * <li>DIALECTE HIBERNATE : lit la valeur de hibernate.dialect 
	 * dans le properties et l'injecte 
	 * dans les propriétés additionnelles.</li>
	 * <li>VOIR LE SQL : lit la valeur de hibernate.show_sql 
	 * dans le properties et l'injecte 
	 * dans les propriétés additionnelles.</li>
	 * <li>FORMATER LE SQL : lit la valeur de hibernate.format_sql 
	 * dans le properties et l'injecte 
	 * dans les propriétés additionnelles.</li>
	 * <li>COMMENTER LE SQL : lit la valeur de hibernate.use_sql_comments 
	 * dans le properties et l'injecte 
	 * dans les propriétés additionnelles.</li>
	 * <li> GENERER LES STATISTIQUES ORM : 
	 * lit la valeur de hibernate.generate_statistics 
	 * dans le properties et l'injecte 
	 * dans les propriétés additionnelles.</li>
	 * <li>UTILISER UN CACHE DE 2nd NIVEAU : 
	 * lit la valeur de cache.provider_class dans le properties 
	 * et l'injecte dans les propriétés additionnelles.</li>
	 * <li>POOL de CONNEXION C3P0 : 
	 * lit les éventuelles valeurs liées au POOL C3P0 dans le properties 
	 * et l'injecte dans les propriétés additionnelles.</li>
	 * <li>INTERRUPTEUR GENERAL SPRING DE LA GENERATION DES TABLES : 
	 * lit la valeur de spring.jpa.generate-ddl 
	 * dans le properties et l'injecte 
	 * dans les propriétés additionnelles.</li>
	 * <li>STRATEGIE DE GENERATION DES TABLES : 
	 * lit la valeur de hibernate.hbm2ddl.auto dans le properties 
	 * et l'injecte dans les propriétés additionnelles.</li>
	 * <li>VOIR LA CONSOLE H2 : 
	 * lit les valeurs de spring.h2.console.enabled 
	 * et spring.h2.console.path dans le properties.</li>
	 * </ul>
	 *
	 * @return : Properties : java.util.Properties.<br/>
	 */
	public Properties additionalProperties() {
		
		final Properties properties = new Properties();
		
		/* lit le DIALECTE HIBERNATE de la BASE dans le properties 
		 * et l'injecte dans les propriétés additionnelles. */
		properties.setProperty("hibernate.dialect"
				, this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.dialect"));
		
		/* VOIR LE SQL : lit la valeur de hibernate.show_sql 
		 * dans le properties et l'injecte 
		 * dans les propriétés additionnelles. */
		properties.setProperty("hibernate.show_sql"
				, this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.show_sql"));
		
		/* FORMATER LE SQL : lit la valeur de hibernate.format_sql 
		 * dans le properties et l'injecte 
		 * dans les propriétés additionnelles. */
		properties.setProperty("hibernate.format_sql"
				, this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.format_sql"));
		
		/* COMMENTER LE SQL : lit la valeur de hibernate.use_sql_comments 
		 * dans le properties et l'injecte 
		 * dans les propriétés additionnelles. */
		properties.setProperty("hibernate.use_sql_comments"
				, this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.use_sql_comments"));
		
		/* GENERER LES STATISTIQUES ORM : 
		 * lit la valeur de hibernate.generate_statistics 
		 * dans le properties et l'injecte 
		 * dans les propriétés additionnelles. */
		properties.setProperty("hibernate.generate_statistics"
				, this.environmentSpring.getProperty(
					"spring.jpa.properties.hibernate.generate_statistics"));
		
		
		/* UTILISER UN CACHE DE 2nd NIVEAU : 
		 * lit la valeur de cache.provider_class 
		 * dans le properties et l'injecte 
		 * dans les propriétés additionnelles. */
		String noCacheProvider = null;
		String cacheProvider = null;
		String useSecondLevelCache = null;
		String useQueryCache = null;
		String resourceEhCache = null;
		
		noCacheProvider 
			= this.environmentSpring.getProperty(
					"spring.jpa.properties.cache.NoCacheProvider");

		if (noCacheProvider != null) {
			properties.setProperty("cache.provider_class", noCacheProvider);
		} else {

			/* cache.provider_class */
			cacheProvider 
				= this.environmentSpring.getProperty(
						"spring.jpa.properties.cache.provider_class");

			if (cacheProvider != null) {
				properties.setProperty(
						"cache.provider_class"
							, cacheProvider);
			}
			
			/* cache.use_second_level_cache */
			useSecondLevelCache = this.environmentSpring.getProperty(
					"spring.jpa.properties.cache.use_second_level_cache");
			
			if (useSecondLevelCache != null) {
				properties.setProperty(
						"cache.use_second_level_cache"
							, useSecondLevelCache);
			}
			
			/* cache.use_query_cache */
			useQueryCache = this.environmentSpring.getProperty(
					"spring.jpa.properties.cache.use_query_cache");
			
			if (useQueryCache != null) {
				properties.setProperty(
						"cache.use_query_cache"
							, useQueryCache);
			}
			
			/* net.sf.ehcache.configurationResourcename */
			resourceEhCache = this.environmentSpring.getProperty(
					"net.sf.ehcache.configurationResourcename");
			
			if (resourceEhCache != null) {
				properties.setProperty(
						"net.sf.ehcache.configurationResourcename"
							, resourceEhCache);
			}
			
		}
		
		// POOL DE CONNEXION C3P0
		properties.setProperty("hibernate.c3p0.min_size"
				, this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.min_size"));
		properties.setProperty("hibernate.c3p0.max_size"
				, this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.max_size"));
		properties.setProperty("hibernate.c3p0.timeout"
				, this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.timeout"));
		properties.setProperty("hibernate.c3p0.max_statements"
				, this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.max_statements"));
		properties.setProperty("hibernate.c3p0.idle_test_period"
				, this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.idle_test_period"));
			
		/* INTERRUPTEUR GENERAL SPRING DE LA GENERATION DES TABLES : 
		 * lit la valeur de spring.jpa.generate-ddl 
		 * dans le properties et l'injecte 
		 * dans les propriétés additionnelles. */
		String generateDll = null;		
		generateDll = this.environmentSpring.getProperty(
				"spring.jpa.generate-ddl");
		
		if (generateDll != null) {
			properties.setProperty("spring.jpa.generate-ddl", generateDll);
		}
		
		/* STRATEGIE DE GENERATION DES TABLES : 
		 * lit la valeur de hibernate.hbm2ddl.auto 
		 * dans le properties et l'injecte 
		 * dans les propriétés additionnelles. */
		String strategieCreation = null;
		strategieCreation = this.environmentSpring.getProperty(
				"spring.jpa.properties.hibernate.ddl-auto");
		
		if (strategieCreation != null) {
			properties.setProperty(
					"hibernate.hbm2ddl.auto"
						, strategieCreation);
		}
		
		/* VOIR LA CONSOLE H2 : lit les valeurs de 
		 * spring.h2.console.enabled et 
		 * spring.h2.console.path dans le properties. */
		String consoleH2 = null;
		String pathConsoleH2 = null;
		
		consoleH2 = this.environmentSpring.getProperty(
				"spring.h2.console.enabled");
		
		pathConsoleH2 = this.environmentSpring.getProperty(
				"spring.h2.console.path");
		
		if (consoleH2 != null) {
			properties.setProperty(
					"spring.h2.console.enabled"
						, consoleH2);
		}
		
		if (pathConsoleH2 != null) {
			properties.setProperty(
					"spring.h2.console.path"
						, pathConsoleH2);
		}

		return properties;
		
	} // Fin de additionalProperties().____________________________________

	
	
	/**
	 * <b>fournit un TransactionManager pour la 
	 * gestion des transactions au 
	 * CONTEXTE SPRING</b> pour l'injection.<br/>
	 *
	 * @param pEntityManagerFactory : EntityManagerFactory.<br/>
	 * 
	 * @return : PlatformTransactionManager.<br/>
	 */
	@Bean
	public PlatformTransactionManager transactionManager(
			final EntityManagerFactory pEntityManagerFactory) {

		final JpaTransactionManager transactionManager 
			= new JpaTransactionManager();

		transactionManager.setEntityManagerFactory(pEntityManagerFactory);

		return transactionManager;
		
	} // Fin de transactionManager(...).___________________________________
	

	
	/**
	 * <b>fournit un PersistenceExceptionTranslationPostProcessor
	 * au CONTEXTE SPRING</b> pour la gestion des Exceptions.<br/>
	 *
	 * @return : PersistenceExceptionTranslationPostProcessor.<br/>
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor 
						persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	} // Fin de persistenceExceptionTranslationPostProcessor().____________
	

	
	/**
	 * <b>fournit un PersistenceAnnotationBeanPostProcessor
	 * au CONTEXTE SPRING</b> pour la découverte des annotations JPA.<br/>
	 *
	 * @return : PersistenceAnnotationBeanPostProcessor.<br/>
	 */
//	@Bean
//	public PersistenceAnnotationBeanPostProcessor 
//						persistenceAnnotationBeanPostProcessor() {
//		return new PersistenceAnnotationBeanPostProcessor();
//	} // Fin de persistenceAnnotationBeanPostProcessor().__________________

	
	
	/**
	 * transforme une URL pour base en MODE FILE contenant 
	 * un chemin relatif lue dans un properties en URL 
	 * acceptable pour la DataSource.<br/>
	 * Par exemple :
	 * <ul>
	 * <li>jdbc:h2:file:./data/base-adresses_javafx-h2/base-adresses_javafx</li>
	 * devient
	 * <li>jdbc:h2:file:D:\Donnees\eclipse\eclipseworkspace\adresses_javafx\
	 * data\base-adresses_javafx-h2\base-adresses_javafx;DB_CLOSE_ON_EXIT=FALSE</li>
	 * </ul>
	 *
	 * @param pUrlFile : String : 
	 * contenu relatif dans un properties.<br/>
	 * 
	 * @return : String : URL acceptable.<br/>
	 */
	private String normaliserURLFile(
			final String pUrlFile) {
		
		final String prefixe = "jdbc:h2:file:";
		
		final String cheminBase 
			= StringUtils.difference(prefixe, pUrlFile);
		
		final Path cheminBasePath 
			= Paths.get(cheminBase).toAbsolutePath().normalize();
		
		final String urlNormalisee 
			= prefixe + cheminBasePath.toString() 
				+ ";DB_CLOSE_ON_EXIT=FALSE";
		
		return urlNormalisee;
		
	} // Fin de normaliserURLFile(...).____________________________________



	
	/**
	 * <b>fournit un bean <i>org.springframework.orm.
	 * jpa.LocalContainerEntityManagerFactoryBean</i> 
	 * équivalent à <i>javax.persistence.EntityManagerFactory</i> 
	 * au CONTEXTE SPRING pour l'injection</b>.<br/>
	 * <ul>
	 * <li><b>fabrique l'EntityManagerFactory</b> en lisant 
	 * les valeurs de configuration de la base directement 
	 * dans la présente classe 
	 * <i>(pas dans un META-INF/persistence.xml)</i>.</li>
	 * <li><b>fixe le nom de l'unité de persistence</b> avec 
	 * la valeur lue dans le fichier properties.</li>
	 * <li>stipule que l'ORM est <b>HIBERNATE</b>.</li>
	 * <li><b>passe la DataSource</b> à l'EntityManagerFactory.</li>
	 * <li>scanne le package de persistence pour 
	 * trouver les classes annotées.</li>
	 * <li> ajoute des propriétés additionnelles à l'EntityManagerFactory 
	 * (Dialecte Hibernate, stratégie de création de tables, ...).</li>
	 * <li>le nom de la méthode (ici entityManagerFactory()) 
	 * est le nom du Bean injecté dans le contexte SPRING. 
	 * Donc si la méthode s'appelait "toto()", 
	 * le bean s'appellerait "toto" dans le contexte.</li>
	 * </ul>
	 *
	 * @return : LocalContainerEntityManagerFactoryBean : 
	 * Proxy du EntityManagerFactory.<br/>
	 * 
	 * @throws Exception 
	 */
	@Bean
	public EntityManagerFactory entityManagerFactory() 
			throws Exception {
		
		EntityManagerFactory entityManagerFactory = null;
		
		// PERSISTENCE UNIT
		/*
		 * fixe le nom de l'unité de persistence avec 
		 * la valeur lue dans le fichier properties.
		 */
		final String persistenceUnitName 
			= this.environmentSpring.getProperty(
				"javax.persistence.jdbc.persistence-unit.name");

		
		// JPAVENDORADAPTER
		/* stipule que l'ORM est HIBERNATE. */
		final String persistenceProviderClassName 
			= this.vendorAdapterHibernate().getClass().getName();

		
		// TYPE DE TRANSACTION
		final String transactionTypeString 
			= this.environmentSpring.getProperty(
				"javax.persistence.jdbc.persistence-unit.transaction-type");
				
		PersistenceUnitTransactionType transactionType = null;
		
		if ("RESOURCE_LOCAL".equalsIgnoreCase(transactionTypeString)) {
			transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;
		} else if ("JTA".equalsIgnoreCase(transactionTypeString)) {
			transactionType = PersistenceUnitTransactionType.JTA;
		}
		
		
		// DATASOURCE
		DataSource jtaDataSource = null;
		DataSource nonJtaDataSource = null;
		
		if (transactionType == null) {
			
			nonJtaDataSource = this.dataSource();
			
		} else if (transactionType
				.equals(PersistenceUnitTransactionType.RESOURCE_LOCAL)) {
			
			nonJtaDataSource = this.dataSource();
			
		} else if (transactionType
				.equals(PersistenceUnitTransactionType.JTA)) {
			
			jtaDataSource = this.dataSource();
			
		}
		
		// PROPRIETES ADDITIONNELLES
//		final Properties propsAdditionnelles 
//			= this.additionalProperties();
//		
//		final ArrayList<String > managedClassNames 
//			= new ArrayList<String>();
//		
//		managedClassNames.add("levy.daniel.application.model.persistence.metier.contactsimple.entities.jpa.ContactSimpleEntityJPA");
		
		// INSTANCIATION D'UN PersistenceUnitInfo
//		final PersistenceUnitInfo persistenceUnitInfo 
//			= new PersistenceUnitInfoJPASansXML(
//					persistenceUnitName
//					, persistenceProviderClassName
//					, transactionType
//					, jtaDataSource
//					, nonJtaDataSource
//					, managedClassNames
//					, propsAdditionnelles);
		
//		MutablePersistenceUnitInfo mutablePersistenceUnitInfo 
//			= new MutablePersistenceUnitInfoJPASpringSansXML(
//				persistenceUnitName
//				, persistenceProviderClassName
//				, transactionType
//				, jtaDataSource
//				, nonJtaDataSource
//				, managedClassNames
//				, propsAdditionnelles);
		
		
//		MutablePersistenceUnitInfoJPASpringSansXML mutablePersistenceUnitInfo 
//		= new MutablePersistenceUnitInfoJPASpringSansXML(
//			persistenceUnitName
//			, persistenceProviderClassName
//			, transactionType
//			, jtaDataSource
//			, nonJtaDataSource
//			, managedClassNames
//			, propsAdditionnelles);
		
//		mutablePersistenceUnitInfo.addMappingFileName("model.persistence.metier.contactsimple.entities.jpa.ContactSimpleEntityJPA");
		
//		mutablePersistenceUnitInfo.setPersistenceUnitName(persistenceUnitName);
//		mutablePersistenceUnitInfo.setPersistenceProviderClassName(persistenceProviderClassName);
//		mutablePersistenceUnitInfo.setTransactionType(transactionType);
//		mutablePersistenceUnitInfo.setJtaDataSource(null);
//		mutablePersistenceUnitInfo.setNonJtaDataSource(nonJtaDataSource);
//		mutablePersistenceUnitInfo.addManagedClassName("ContactSimpleEntityJPA");
//		mutablePersistenceUnitInfo.setProperties(propsAdditionnelles);

		
		
		
		final MutablePersistenceUnitInfoJPASpringSansXML mutablePersistenceUnitInfo 
			= this.lecteurPropertiesSpring.getPersistenceUnitInfoJPASansXML();

		mutablePersistenceUnitInfo.addManagedClassName("levy.daniel.application.model.persistence.metier.contactsimple.entities.jpa.ContactSimpleEntityJPA");
		
		System.out.println("PERSISTENCEUNITINFO DANS ConfigurateurJPASansXML : " + mutablePersistenceUnitInfo.toString());
		
		final Map<String, Object> configuration	
			= new HashMap<String, Object>();
		
		/* surchargerait les valeurs déjà dans le persistenceUnitInfo. */
		configuration.put("javax.persistence.jdbc.url", "jdbc:h2:file:D:/Donnees/eclipse/eclipseworkspace/adresses_javafx/data/base-adresses_javafx-h2/base-adresses_javafx");
		configuration.put("javax.persistence.jdbc.driver", "org.h2.Driver");
		configuration.put("javax.persistence.jdbc.user", "sa");
		configuration.put("javax.persistence.jdbc.password", "sa");
		
//		entityManagerFactory 
//		= new HibernatePersistenceProvider().createContainerEntityManagerFactory(
//				mutablePersistenceUnitInfo, configuration);
		
		final EntityManagerFactoryBuilder entityManagerFactoryBuilder 
			= Bootstrap.getEntityManagerFactoryBuilder(
					mutablePersistenceUnitInfo, configuration);
		
		entityManagerFactory = entityManagerFactoryBuilder.build();
		
		return entityManagerFactory;
					
	}
	

	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @return EntityManagerFactory
	 * 
	 * @throws Exception
	 */
	public EntityManagerFactory entityManagerFactoryInfo2() 
			throws Exception {
		
		EntityManagerFactory entityManagerFactory = null;
		
		// PERSISTENCE UNIT
		/*
		 * fixe le nom de l'unité de persistence avec 
		 * la valeur lue dans le fichier properties.
		 */
		final String persistenceUnitName 
			= this.environmentSpring.getProperty(
				"javax.persistence.jdbc.persistence-unit.name");

		
		// JPAVENDORADAPTER
		/* stipule que l'ORM est HIBERNATE. */
		final String persistenceProviderClassName 
			= this.vendorAdapterHibernate().getClass().getName();

		
		// TYPE DE TRANSACTION
		final String transactionTypeString 
			= this.environmentSpring.getProperty(
				"javax.persistence.jdbc.persistence-unit.transaction-type");
				
		PersistenceUnitTransactionType transactionType = null;
		
		if ("RESOURCE_LOCAL".equalsIgnoreCase(transactionTypeString)) {
			transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;
		} else if ("JTA".equalsIgnoreCase(transactionTypeString)) {
			transactionType = PersistenceUnitTransactionType.JTA;
		}
		
		// DATASOURCE
		DataSource jtaDataSource = null;
		DataSource nonJtaDataSource = null;
		
		if (transactionType == null) {
			
			nonJtaDataSource = this.dataSource();
			
		} else if (transactionType
				.equals(PersistenceUnitTransactionType.RESOURCE_LOCAL)) {
			
			nonJtaDataSource = this.dataSource();
			
		} else if (transactionType
				.equals(PersistenceUnitTransactionType.JTA)) {
			
			jtaDataSource = this.dataSource();
			
		}
		
		
		// PROPRIETES ADDITIONNELLES
		final Properties propsAdditionnelles 
			= this.additionalProperties();
		
		final ArrayList<String > managedClassNames 
			= new ArrayList<String>();
		
		managedClassNames.add("ContactSimpleEntityJPA");
		
		// INSTANCIATION D'UN PersistenceUnitInfo
		final PersistenceUnitInfo persistenceUnitInfo 
			= new PersistenceUnitInfoJPASansXML(
					persistenceUnitName
					, persistenceProviderClassName
					, transactionType
					, jtaDataSource
					, nonJtaDataSource
					, managedClassNames
					, propsAdditionnelles);
		
		System.out.println("PERSISTENCEUNITINFO DANS ConfigurateurJPASansXML : " + persistenceUnitInfo.toString());
		
		final Map<String, Object> properties	
			= new HashMap<String, Object>();
		
		entityManagerFactory 
		= new HibernatePersistenceProvider().createContainerEntityManagerFactory(
				persistenceUnitInfo, properties);
		
		
		
		return entityManagerFactory;
		
	}

	
	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @return EntityManagerFactory
	 * @throws Exception : EntityManagerFactory :  .<br/>
	 */
//	@Bean
	public EntityManagerFactory entityManagerFactoryInfo3() 
			throws Exception {
		
		EntityManagerFactory entityManagerFactory = null;
		
		// PERSISTENCE UNIT
		/*
		 * fixe le nom de l'unité de persistence avec 
		 * la valeur lue dans le fichier properties.
		 */
		final String persistenceUnitName 
			= this.environmentSpring.getProperty(
				"javax.persistence.jdbc.persistence-unit.name");

		
		// JPAVENDORADAPTER
		/* stipule que l'ORM est HIBERNATE. */
		final String persistenceProviderClassName 
			= this.vendorAdapterHibernate().getClass().getName();

		
		// TYPE DE TRANSACTION
		final String transactionTypeString 
			= this.environmentSpring.getProperty(
				"javax.persistence.jdbc.persistence-unit.transaction-type");
				
		PersistenceUnitTransactionType transactionType = null;
		
		if ("RESOURCE_LOCAL".equalsIgnoreCase(transactionTypeString)) {
			transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;
		} else if ("JTA".equalsIgnoreCase(transactionTypeString)) {
			transactionType = PersistenceUnitTransactionType.JTA;
		}
		
		// DATASOURCE
		DataSource jtaDataSource = null;
		DataSource nonJtaDataSource = null;
		
		if (transactionType == null) {
			
			nonJtaDataSource = this.dataSource();
			
		} else if (transactionType
				.equals(PersistenceUnitTransactionType.RESOURCE_LOCAL)) {
			
			nonJtaDataSource = this.dataSource();
			
		} else if (transactionType
				.equals(PersistenceUnitTransactionType.JTA)) {
			
			jtaDataSource = this.dataSource();
			
		}
				
		// PROPRIETES ADDITIONNELLES
		final Properties propsAdditionnelles 
			= this.additionalProperties();
		
		final ArrayList<String > managedClassNames 
			= new ArrayList<String>();
		
		managedClassNames.add("ContactSimpleEntityJPA");
		
		// INSTANCIATION D'UN PersistenceUnitInfo
		final PersistenceUnitInfo persistenceUnitInfo 
			= new PersistenceUnitInfoJPASansXML(
					persistenceUnitName
					, persistenceProviderClassName
					, transactionType
					, jtaDataSource
					, nonJtaDataSource
					, managedClassNames
					, propsAdditionnelles);
		
		System.out.println("PERSISTENCEUNITINFO DANS ConfigurateurJPASansXML : " + persistenceUnitInfo.toString());
		
		final Map<String, Object> properties	
			= new HashMap<String, Object>();
		
		/* org.hibernate.internal.SessionFactoryImpl */
		entityManagerFactory 
		= new HibernatePersistenceProvider().createContainerEntityManagerFactory(
				persistenceUnitInfo, properties);
		
		
		System.out.println(entityManagerFactory.toString());
		
		return entityManagerFactory;
		
	}
	
	
	
	/**
	 * Getter du <b>lecteur SPRING du fichier properties</b> proposé 
	 * dans l'annotation sur la présente classe 'PropertySource'.
	 * <ul>
	 * <li>injecté par SPRING via le Setter 
	 * <code>setEnvironmentSpring(Environment pEnvironmentSpring)</code>
	 * .</li>
	 * </ul>
	 *
	 * @return this.environmentSpring : 
	 * org.springframework.core.env.Environment.<br/>
	 */
	public final Environment getEnvironmentSpring() {
		return this.environmentSpring;
	} // Fin de getEnvironmentSpring().____________________________________


	
	/**
	* Setter du <b>lecteur SPRING du fichier properties</b> proposé 
	* dans l'annotation sur la présente classe 'PropertySource'.
	* <ul>
	* <li>injecté par SPRING via le Setter 
	* <code>setEnvironmentSpring(Environment pEnvironmentSpring)</code>
	* .</li>
	* </ul>
	*
	* @param pEnvironmentSpring : 
	* org.springframework.core.env.Environment. : 
	* valeur à passer à this.environmentSpring.<br/>
	*/
	@Autowired(required=true)
	public final void setEnvironmentSpring(
			final Environment pEnvironmentSpring) {
		this.environmentSpring = pEnvironmentSpring;
		System.out.println("DANS LE SETTEUR setEnvironmentSpring de ConfigurateurJPAH2File");
		this.setLecteurPropertiesSpring(new LecteurPropertiesSpring(this.environmentSpring));
	} // Fin de setEnvironmentSpring(...)._________________________________

	
	
	/**
	 * Getter du Lecteur de fichier properties SPRING chargé de fournir 
	 * une PersistenceUnitInfo (MutablePersistenceUnitInfoJPASpringSansXML) 
	 * pour instancier une EntityManagerFactory sans lire de persistence.xml.
	 *
	 * @return this.lecteurPropertiesSpring : LecteurPropertiesSpring.<br/>
	 */
	public LecteurPropertiesSpring getLecteurPropertiesSpring() {
		return this.lecteurPropertiesSpring;
	} // Fin de getLecteurPropertiesSpring().______________________________



	/**
	* Setter du Lecteur de fichier properties SPRING chargé de fournir 
	* une PersistenceUnitInfo (MutablePersistenceUnitInfoJPASpringSansXML) 
	* pour instancier une EntityManagerFactory sans lire de persistence.xml.
	*
	* @param pLecteurPropertiesSpring : LecteurPropertiesSpring : 
	* valeur à passer à this.lecteurPropertiesSpring.<br/>
	*/
	public void setLecteurPropertiesSpring(
			final LecteurPropertiesSpring pLecteurPropertiesSpring) {
		System.out.println("LECTEURPROPERTIESSPRING injecté dans ConfigurateurJPAH2File");
		this.lecteurPropertiesSpring = pLecteurPropertiesSpring;
	} // Fin de setLecteurPropertiesSpring(...).___________________________
	

	
//	public EntityManager createEntityManager() {
//		
//		Properties properties = new Properties();
//		properties.put("javax.persistence.provider", "org.hibernate.ejb.HibernatePersistence");
//		properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
//		properties.put("hibernate.connection.username", "sa");
//		properties.put("hibernate.connection.password" ,"");
//		properties.put("hibernate.connection.driver_class","org.hsqldb.jdbcDriver");			
//		properties.put("hibernate.connection.url", "jdbc:hsqldb:." );
//		properties.put("hibernate.dialect" ,"org.hibernate.dialect.HSQLDialect");
//		properties.put("hibernate.hbm2ddl.auto","create-drop");
//		properties.put("hibernate.show_sql","true");
//		properties.put("hibernate.format_sql" ,"true");	
//		//
//		Ejb3Configuration cfg = new Ejb3Configuration();
//		
//		cfg.addProperties(properties);
//		cfg.addAnnotatedClass(City.class);
//		cfg.addAnnotatedClass(LocalCustomer.class);
//		//
//		EntityManagerFactory factory = cfg.buildEntityManagerFactory();
//		return factory.createEntityManager();
//	}
	
} // FIN DE LA CLASSE ConfigurateurJPAH2File.--------------------------------
