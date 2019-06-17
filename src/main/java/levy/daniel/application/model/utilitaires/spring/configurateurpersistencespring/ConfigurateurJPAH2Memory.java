package levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * CLASSE ConfigurateurJPAH2Memory :<br/>
 * Configuration d'une SGBDR H2 en MODE MEMORY.<br/>
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
//@Configuration(value="ConfigurateurJPAH2Memory")
//@PropertySources({@PropertySource("classpath:configurations_bases_jpa/configuration_H2_memory.properties")})
//@EnableTransactionManagement
//@ComponentScans({@ComponentScan("levy.daniel.application.model.persistence")})
public class ConfigurateurJPAH2Memory {

	// ************************ATTRIBUTS************************************/

	/**
	 * lecteur du fichier properties proposé 
	 * dans l'annotation sur la classe 'PropertySource'.
	 */
	@Autowired
	private transient Environment environmentSpring;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ConfigurateurJPAH2Memory.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public ConfigurateurJPAH2Memory() {
		super();
		System.out.println("CONSTRUCTEUR ConfigurateurJPAH2Memory");
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

		
	/**
	 * <b>fournit un bean <i>javax.persistence.EntityManagerFactory</i> 
	 * au CONTEXTE SPRING pour l'injection</b>.<br/>
	 * <ul>
	 * <li><b>fabrique l'EntityManagerFactory</b> en lisant 
	 * les valeurs de configuration de la base directement 
	 * dans la présente classe (pas dans un META-INF/persistence.xml)</li>
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
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() 
			throws Exception {

		final LocalContainerEntityManagerFactoryBean entityManagerFactory 
			= new LocalContainerEntityManagerFactoryBean();

		/*
		 * fixe le nom de l'unité de persistence avec 
		 * la valeur lue dans le fichier properties.
		 */
		entityManagerFactory.setPersistenceUnitName(
				this.environmentSpring.getProperty(
						"javax.persistence.jdbc.persistence-unit.name"));

		/* stipule que l'ORM est HIBERNATE. */
		entityManagerFactory.setJpaVendorAdapter(
				this.vendorAdapterHibernate());

		/* passe la DataSource à l'EntityManagerFactory. */
		entityManagerFactory.setDataSource(this.dataSource());

		/* scanne le package de persistence 
		 * pour trouver les classes annotées. */
		entityManagerFactory.setPackagesToScan(
				new String[] {"levy.daniel.application.model.persistence"});

		/* ajoute des propriétés additionnelles à l'EntityManagerFactory 
		 * (Dialecte Hibernate, stratégie de création de tables, ...). */
		entityManagerFactory.setJpaProperties(additionalProperties());

		return entityManagerFactory;
		
	} // Fin de entityManagerFactory().____________________________________

	
	
	/**
	 * <b>fournit un Bean précisant que l'ORM est HIBERNATE</b>.<br/>
	 *
	 * @return : JpaVendorAdapter.<br/>
	 */
	@Bean
	public JpaVendorAdapter vendorAdapterHibernate() {
		
		final JpaVendorAdapter vendorAdapter 
      		= new HibernateJpaVendorAdapter();
		
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

		final DriverManagerDataSource dataSource 
			= new DriverManagerDataSource();

		/* lit l'URL de la BASE dans le properties 
		 * et l'injecte dans la DataSource. */
		dataSource.setUrl(
				this.environmentSpring.getProperty(
						"javax.persistence.jdbc.connexion.url"));
		
		/* lit le DRIVER de la BASE dans le properties 
		 * et l'injecte dans la DataSource. */
		dataSource.setDriverClassName(
				this.environmentSpring.getProperty(
						"javax.persistence.jdbc.driver"));
		
		/* lit le [Login + Mdp] à la base dans le properties 
		 * et l'injecte dans le DataSource. */
		dataSource.setUsername(
				this.environmentSpring.getProperty(
						"javax.persistence.jdbc.connection.username"));
		dataSource.setPassword(
				this.environmentSpring.getProperty(
						"javax.persistence.jdbc.connection.password"));

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
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	} // Fin de exceptionTranslation().____________________________________
	
	
	
} // FIN DE LA CLASSE ConfigurateurJPAH2Memory.------------------------------
