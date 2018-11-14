package by.onyx.common.config;

import by.onyx.common.dataSource.DataSourceInterface;
import lombok.AllArgsConstructor;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource("classpath:app.properties")
@ComponentScan("by.onyx.common")
@EnableJpaRepositories("by.onyx.common.repositories")
@AllArgsConstructor
public class CommonConfig {
    @Value("${hibernate.dialect}")
    private final String PR_NM_HIBERNATE_DIALECT;
    @Value("${hibernate.show_sql}")
    private final String PR_NM_HIBERNATE_SHOW_SQL;
    @Value("${hibernate.hbm2ddl.auto}")
    private final String PR_NM_HIBERNATE_HBM2DDL;
    @Value("${entitymanager.packages.to.scan}")
    private final String PR_NM_ENTITYMANAGER_PACKAGES_TO_SCAN;

    private DataSourceInterface dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource.dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(PR_NM_ENTITYMANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        return entityManagerFactoryBean;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(PR_NM_HIBERNATE_DIALECT, PR_NM_HIBERNATE_DIALECT);
        properties.put(PR_NM_HIBERNATE_HBM2DDL, PR_NM_HIBERNATE_HBM2DDL);
        properties.put(PR_NM_HIBERNATE_SHOW_SQL, PR_NM_HIBERNATE_SHOW_SQL);
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
