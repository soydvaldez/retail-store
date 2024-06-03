package com.store.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:datasource.properties")
public class PostgresJPAConfig {

    @Value("${datasource.postgresql.driver-class-name}")
    private String driverClassName;

    @Value("${datasource.postgresql.url}")
    private String url;

    @Value("${datasource.postgresql.username}")
    private String username;

    @Value("${datasource.postgresql.password}")
    private String password;

    @Bean(name = "postgresqlDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(this.driverClassName)
                .url(this.url)
                .username(this.username)
                .password(this.password)
                .build();
    }

    @Bean(name = "postgresqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(
            @Qualifier("postgresqlDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.postgresql.entities");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        // properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");        

        em.setJpaPropertyMap(properties);
        em.setPersistenceUnitName("postgresqlPersistenceUnit");

        return em;
    }

    @Bean(name = "postgresqlTransactionManager")
    public JpaTransactionManager postgresqlTransactionManager(
            @Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
