package com.coviam.finance.backend.configuration.db.postgres;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.coviam.finance.backend.configuration.properties.SpringDataSourceProperties;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
@EntityScan(basePackages = {"com.coviam.finance.backend.entity"})
@ComponentScan(basePackages = {"com.gdn.lts.backend.entity", "com.gdn.lts.backend.repository"})
@EnableJpaRepositories(entityManagerFactoryRef = "postgresEntityManagerFactory",
    transactionManagerRef = "postgresTransactionManager",
    basePackages = {"com.coviam.finance.backend.repository"})
public class PostgresConfiguration {

  @Autowired
  private SpringDataSourceProperties springDataSourceProperties;

  @Bean
  public DataSource dataSource() {
    Properties properties = new Properties();
    properties.setProperty("spring.datasource.initial-size",
        this.springDataSourceProperties.getInitialSize());
    properties.setProperty("spring.datasource.max-active",
        this.springDataSourceProperties.getMaxActive());
    properties.setProperty("spring.datasource.max-wait",
        this.springDataSourceProperties.getMaxWait());
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(this.springDataSourceProperties.getDriverClassName());
    dataSource.setUrl(this.springDataSourceProperties.getUrl());
    dataSource.setUsername(this.springDataSourceProperties.getUsername());
    dataSource.setPassword(this.springDataSourceProperties.getPassword());
    dataSource.setConnectionProperties(properties);
    return dataSource;
  }

  @Bean(name = "postgresEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
        new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPackagesToScan(new String[] {"com.coviam.finance.backend.entity"});

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setShowSql(false);
    vendorAdapter.setGenerateDdl(true);
    entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
    entityManagerFactoryBean.setJpaProperties(jpaProperties());

    return entityManagerFactoryBean;
  }

  @Bean("postgresTransactionManager")
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(emf);

    return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  Properties jpaProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", this.springDataSourceProperties.getDdlAuto());
    properties.setProperty("hibernate.dialect", this.springDataSourceProperties.getDialect());
    properties.setProperty("hibernate.show_sql", this.springDataSourceProperties.getShowSql());
    return properties;
  }
}
