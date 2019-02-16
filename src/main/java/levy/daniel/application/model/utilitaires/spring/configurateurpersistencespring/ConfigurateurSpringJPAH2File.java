package levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.jpa.boot.spi.Bootstrap;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import levy.daniel.application.model.utilitaires.jpa.afficheurentitymanagerfactory.AfficheurEntityManagerFactory;
import levy.daniel.application.model.utilitaires.jpa.datasource.IMyDataSource;
import levy.daniel.application.model.utilitaires.jpa.datasource.impl.MyDataSourceHikari;
import levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.lecteur.LecteurConfigurationBaseSpring;
import levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.mutablepersistenceunitinfo.MyMutablePersistenceUnitInfo;

/**
 * CLASSE ConfigurateurSpringJPAH2File :<br/>
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
 * @since 27 janv. 2019
 *
 */
@Configuration(value="ConfigurateurSpringJPAH2File")
@PropertySources({@PropertySource("classpath:configurations_bases_jpa/configuration_H2_file.properties")})
@EnableTransactionManagement
@ComponentScans({@ComponentScan("levy.daniel.application.model.persistence")})
public class ConfigurateurSpringJPAH2File {

	// ************************ATTRIBUTS************************************/

	/**
	 * <b>lecteur SPRING du fichier properties</b> proposé 
	 * dans l'annotation sur la présente classe 'PropertySource'.
	 * <ul>
	 * <li><b>injecté par SPRING via le Setter</b> 
	 * <code>setEnvironmentSpring(Environment pEnvironmentSpring)</code>
	 * .</li>
	 * <li>org.springframework.core.env.Environment</li>
	 * </ul>
	 */
	private Environment environmentSpring;


	/**
	 * utilitaire chargé de lire le fichier properties SPRING 
	 * et de préparer un MyMutablePersistenceUnitInfo.
	 */
	private LecteurConfigurationBaseSpring lecteurConfigurationBaseSpring;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ConfigurateurSpringJPAH2File.class);

	// *************************METHODES************************************/
			
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public ConfigurateurSpringJPAH2File() {
		super();
		System.out.println();
		System.out.println("********* DANS LE CONSTRUCTEUR ConfigurateurSpringJPAH2File***************");
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
	@Bean
	public EntityManagerFactory entityManagerFactory() 
			throws Exception {
		
		EntityManagerFactory entityManagerFactory = null;
		
		final List<String> managedClassNames = new LinkedList<String>();
		
		managedClassNames.add(
				"levy.daniel.application.model.persistence.metier.contactsimple.entities.jpa.ContactSimpleEntityJPA");
		
		final MyMutablePersistenceUnitInfo 
			mutablePersistenceUnitInfo 
				= new MyMutablePersistenceUnitInfo(
						this.lecteurConfigurationBaseSpring
						, this.vendorAdapterHibernate().getClass().getName()
						, null
						, this.dataSource()
						, null
						, null
						, null
						, managedClassNames
						, null
						, false
						, null
						, null
						, null
						, null
						, null
						, null);

		final Map<String, Object> configuration	
			= new HashMap<String, Object>();
		
//		entityManagerFactory 
//		= new HibernatePersistenceProvider().createContainerEntityManagerFactory(
//				mutablePersistenceUnitInfo, configuration);
		
		final EntityManagerFactoryBuilder entityManagerFactoryBuilder 
			= Bootstrap.getEntityManagerFactoryBuilder(
					mutablePersistenceUnitInfo, configuration);
		
		entityManagerFactory 
			= entityManagerFactoryBuilder.build();
		
		System.out.println();
		System.out.println("=======DANS entityManagerFactory() de ConfigurateurSpringJPAH2File() ======");
		System.out.println(AfficheurEntityManagerFactory.afficherEntityManagerFactory(entityManagerFactory));
		
		return entityManagerFactory;
					
	} // Fin de entityManagerFactory().____________________________________
	

	
	/**
	 * <b>Instancie un IMyDataSource, l'alimente
	 * avec [URL, DRIVER, LOGIN, MDP, valeurs de POOL]</b> 
	 * contenu dans <code>this.lecteurConfigurationBaseSpring</code> 
	 * et <b>retourne un javax.sql.DataSource pour l'injecter 
	 * dans le CONTEXTE SPRING</b>.<br/>
	 * <ul>
	 * <li>lit l'URL de la BASE dans le properties 
	 * et l'injecte dans la DataSource.</li>
	 * <li>lit le DRIVER de la BASE dans le properties 
	 * et l'injecte dans la DataSource.</li>
	 * <li>lit le [Login + Mdp] à la base dans le properties 
	 * et l'injecte dans le DataSource.</li>
	 * <li>lit les valeurs du POOL DE CONNEXION à la base dans le properties 
	 * et l'injecte dans le DataSource.</li>
	 * </ul>
	 *
	 * @return : DataSource : javax.sql.DataSource.<br/>
	 */
//	@Bean
//	public DataSource dataSource() {
//		
//		final IMyDataSource myDataSource 
//			= new MyDataSourceC3P0(this.lecteurConfigurationBaseSpring);
//		
//		System.out.println();
//		System.out.println("=======DANS dataSource() de ConfigurateurSpringJPAH2File() ======");
//		System.out.println(myDataSource.afficherDataSource());
//		
//		return myDataSource.getDataSource();
//		
//	} // Fin de dataSource().______________________________________________
	
	
	
	/**
	 * <b>Instancie un IMyDataSource, l'alimente
	 * avec [URL, DRIVER, LOGIN, MDP, valeurs de POOL]</b> 
	 * contenu dans <code>this.lecteurConfigurationBaseSpring</code> 
	 * et <b>retourne un javax.sql.DataSource pour l'injecter 
	 * dans le CONTEXTE SPRING</b>.<br/>
	 * <ul>
	 * <li>lit l'URL de la BASE dans le properties 
	 * et l'injecte dans la DataSource.</li>
	 * <li>lit le DRIVER de la BASE dans le properties 
	 * et l'injecte dans la DataSource.</li>
	 * <li>lit le [Login + Mdp] à la base dans le properties 
	 * et l'injecte dans le DataSource.</li>
	 * <li>lit les valeurs du POOL DE CONNEXION à la base dans le properties 
	 * et l'injecte dans le DataSource.</li>
	 * </ul>
	 *
	 * @return : DataSource : javax.sql.DataSource.<br/>
	 */
	@Bean
	public DataSource dataSource() {
		
		final IMyDataSource myDataSource 
			= new MyDataSourceHikari(this.lecteurConfigurationBaseSpring);
		
		System.out.println();
		System.out.println("=======DANS dataSource() de ConfigurateurSpringJPAH2File() ======");
		System.out.println(myDataSource.afficherDataSource());
		
		return myDataSource.getDataSource();
		
	} // Fin de dataSource().______________________________________________
	

	
	/**
	 * <b>fournit un Bean précisant que l'ORM est HIBERNATE</b>.<br/>
	 *
	 * @return : JpaVendorAdapter : 
	 * org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter.<br/>
	 */
//	@Bean
	public JpaVendorAdapter vendorAdapterHibernate() {
		
		final JpaVendorAdapter vendorAdapter 
      		= new HibernateJpaVendorAdapter();
		
		return vendorAdapter;
		
	} // Fin de vendorAdapterHibernate().__________________________________

	
	
	/**
	 * <b>fournit un TransactionManager pour la 
	 * gestion des transactions au 
	 * CONTEXTE SPRING</b> pour l'injection.<br/>
	 *
	 * @param pEntityManagerFactory : 
	 * javax.persistence.EntityManagerFactory.<br/>
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
	public Environment getEnvironmentSpring() {
		return this.environmentSpring;
	} // Fin de getEnvironmentSpring().____________________________________


	
	/**
	* Setter du <b>lecteur SPRING du fichier properties</b> proposé 
	* dans l'annotation sur la présente classe 'PropertySource'.
	* <ul>
	* <li>injecté par SPRING via le Setter 
	* <code>setEnvironmentSpring(Environment pEnvironmentSpring)</code></li>
	* <li>instancie en lui passant <code>this.environmentSpring</code> 
	* un LecteurPropertiesSpring chargé de lire le fichier 
	* properties SPRING et de retourner un 
	* <b>MutablePersistenceUnitInfoJPASpringSansXML</b>.</li>
	* </ul>
	*
	* @param pEnvironmentSpring : 
	* org.springframework.core.env.Environment. : 
	* valeur à passer à this.environmentSpring.<br/>
	*/
	@Autowired(required=true)
	public void setEnvironmentSpring(
			final Environment pEnvironmentSpring) {
		
		this.environmentSpring = pEnvironmentSpring;
		
		System.out.println();
		System.out.println("****** DANS LE SETTEUR setEnvironmentSpring() de ConfigurateurSpringJPAH2File *******");
		System.out.println();
		
		/* instancie en lui passant this.environmentSpring 
		 * un LecteurConfigurationBaseSpring chargé de lire le fichier 
		 * properties SPRING et de préparer un 
		 * MyMutablePersistenceUnitInfo. */
		this.setLecteurConfigurationBaseSpring(
				new LecteurConfigurationBaseSpring(
						this.environmentSpring));
		
	} // Fin de setEnvironmentSpring(...)._________________________________


	
	/**
	 * Getter de l'utilitaire chargé de lire le fichier properties SPRING 
	 * et de préparer un MyMutablePersistenceUnitInfo.
	 *
	 * @return this.lecteurConfigurationBaseSpring : 
	 * LecteurConfigurationBaseSpring.<br/>
	 */
	public final LecteurConfigurationBaseSpring 
								getLecteurConfigurationBaseSpring() {
		return this.lecteurConfigurationBaseSpring;
	} // Fin de getLecteurConfigurationBaseSpring()._______________________


	
	/**
	* Setter de l'utilitaire chargé de lire le fichier properties SPRING 
	* et de préparer un MyMutablePersistenceUnitInfo.
	*
	* @param pLecteurConfigurationBaseSpring : LecteurConfigurationBaseSpring : 
	* valeur à passer à this.lecteurConfigurationBaseSpring.<br/>
	*/
	public final void setLecteurConfigurationBaseSpring(
			final LecteurConfigurationBaseSpring 
						pLecteurConfigurationBaseSpring) {
		
		this.lecteurConfigurationBaseSpring = pLecteurConfigurationBaseSpring;
		
		System.out.println();
		System.out.println("****** DANS LE SETTEUR setLecteurConfigurationBaseSpring() de ConfigurateurSpringJPAH2File *******");
		System.out.println();
		System.out.println(this.lecteurConfigurationBaseSpring.toString());
		
	} // Fin de setLecteurConfigurationBaseSpring(...).____________________

	
	
}
