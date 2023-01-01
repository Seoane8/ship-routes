package com.shiproutes.ports.shared.infrastructure;

import com.shiproutes.shared.infrastructure.config.Parameter;
import com.shiproutes.shared.infrastructure.config.ParameterNotExist;
import com.shiproutes.shared.infrastructure.hibernate.HibernateConfigurationFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableTransactionManagement
public class PortsHibernateConfiguration {
    private final HibernateConfigurationFactory factory;
    private final Parameter config;
    private final String CONTEXT_NAME = "ports";

    public PortsHibernateConfiguration(HibernateConfigurationFactory factory, Parameter config) {
        this.factory = factory;
        this.config = config;
    }

    @Bean("ports-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() throws IOException, ParameterNotExist {
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("ports-session_factory")
    public LocalSessionFactoryBean sessionFactory() throws IOException, ParameterNotExist {
        return factory.sessionFactory(CONTEXT_NAME, dataSource());
    }

    @Bean("ports-data_source")
    public DataSource dataSource() throws IOException, ParameterNotExist {
        return factory.dataSource(
            config.get("PORTS_DATABASE_HOST"),
            config.getInt("PORTS_DATABASE_PORT"),
            config.get("PORTS_DATABASE_NAME"),
            config.get("PORTS_DATABASE_USER"),
            config.get("PORTS_DATABASE_PASSWORD")
        );
    }
}
