package com.store.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.store.demo.api.security.repository"
}, entityManagerFactoryRef = "H2EntityManagerFactory", transactionManagerRef = "H2TransactionManager")
@PropertySource("classpath:datasource.properties")
public class H2JPAConfig {
    @Value("${datasource.h2.driver-class-name}")
    private String driverClassName;

    @Value("${datasource.h2.url}")
    private String url;

    @Value("${datasource.h2.username}")
    private String username;

    @Value("${datasource.h2.password}")
    private String password;

    @Bean(name = "H2DataSource")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(this.driverClassName)
                .url(this.url)
                .username(this.username)
                .password(this.password)
                .build();
    }

    @Primary
    @Bean(name = "H2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("H2DataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(getlistPackageToScan());

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        em.setPersistenceUnitName("H2PersistenceUnit");

        return em;
    }

    @Bean(name = "H2TransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("H2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    private Properties additionalProperties() {
        java.util.Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        // properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        // properties.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        return properties;
    }

    // get all entities package references from modules
    private String[] getlistPackageToScan() {
        return new String[] {
                "com.store.demo.api.products.entity",
                "com.store.demo.api.security.entity",
                "com.store.demo.api.categories.entity",
                "com.store.demo.api.suppliers.entity"
        };
    }
}
