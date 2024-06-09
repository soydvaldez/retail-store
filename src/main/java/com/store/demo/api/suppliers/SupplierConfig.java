package com.store.demo.api.suppliers;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
                "com.store.demo.api.suppliers"
})
@EnableJpaRepositories(basePackages = {
                "com.store.demo.api.suppliers.repository"
}, entityManagerFactoryRef = "H2EntityManagerFactory", transactionManagerRef = "H2TransactionManager")

@EntityScan(basePackages = {
                "com.store.demo.api.suppliers.entity"
})
public class SupplierConfig {
    
}
