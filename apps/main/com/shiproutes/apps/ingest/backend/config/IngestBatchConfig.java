package com.shiproutes.apps.ingest.backend.config;

import com.shiproutes.shared.infrastructure.config.Parameter;
import com.shiproutes.shared.infrastructure.config.ParameterNotExist;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@Configuration
@EnableBatchProcessing
public class IngestBatchConfig {

    private final Parameter config;

    private final ResourcePatternResolver resourceResolver;

    public IngestBatchConfig(Parameter config, ResourcePatternResolver resourceResolver) {
        this.config = config;
        this.resourceResolver = resourceResolver;
    }

    public DataSource dataSource() throws ParameterNotExist, IOException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(
            String.format(
                "jdbc:mysql://%s:%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false" +
                    "&serverTimezone=UTC",
                config.get("INGEST_DATABASE_HOST"),
                config.getInt("INGEST_DATABASE_PORT"),
                config.get("INGEST_DATABASE_NAME")
            )
        );
        dataSource.setUsername(config.get("INGEST_DATABASE_USER"));
        dataSource.setPassword(config.get("INGEST_DATABASE_PASSWORD"));


        Resource dropResource = resourceResolver.getResource("classpath:org/springframework/batch/core/schema-drop-mysql.sql");
        String dropSentences = new Scanner(dropResource.getInputStream(), StandardCharsets.UTF_8).useDelimiter("\\A").next();

        Resource createResource = resourceResolver.getResource("classpath:org/springframework/batch/core/schema-mysql.sql");
        String createSentences = new Scanner(createResource.getInputStream(), StandardCharsets.UTF_8).useDelimiter("\\A").next();

        String mysqlSentences = dropSentences + ";" + createSentences;

        dataSource.setConnectionInitSqls(new ArrayList<>(Arrays.asList(mysqlSentences.split(";"))));

        return dataSource;
    }

    @Bean(name = "ingest-batch-transaction_manager")
    public PlatformTransactionManager getTransactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public JobRepository getJobRepository(PlatformTransactionManager transactionManager) throws ParameterNotExist, Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource());
        factory.setTransactionManager(transactionManager);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public JobLauncher getJobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }
}
