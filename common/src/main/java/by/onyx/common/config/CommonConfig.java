package by.onyx.common.config;

import by.onyx.common.dataSource.DataSourceInterface;
import lombok.AllArgsConstructor;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource("classpath:app.properties")
@ComponentScan("by.onyx.common")
@EnableJpaRepositories("by.onyx.common.repositories")
public class CommonConfig {
    private static final String PR_NM_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PR_NM_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PR_NM_HIBERNATE_HBM2DDL = "hibernate.hbm2ddl.auto";
    private static final String PR_NM_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    @Resource
    private Environment env;
    @Autowired
    private DataSourceInterface dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource.dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PR_NM_ENTITYMANAGER_PACKAGES_TO_SCAN));

        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(PR_NM_HIBERNATE_DIALECT, env.getRequiredProperty(PR_NM_HIBERNATE_DIALECT));
        properties.put(PR_NM_HIBERNATE_HBM2DDL, env.getRequiredProperty(PR_NM_HIBERNATE_HBM2DDL));
        properties.put(PR_NM_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PR_NM_HIBERNATE_SHOW_SQL));

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }


}
