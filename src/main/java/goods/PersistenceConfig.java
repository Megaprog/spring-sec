package goods;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class PersistenceConfig {

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public PersistenceProvider persistenceProvider() {
        return new HibernatePersistenceProvider();
    }

    @Bean
    public Properties jpaProperties(@Value("${jpa.properties}") Resource propertiesResource) throws IOException {
        final Properties jpaProperties = new Properties();
        jpaProperties.load(propertiesResource.getInputStream());
        return jpaProperties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(PersistenceProvider persistenceProvider, Properties jpaProperties) throws IOException, SQLException {
        //without it h2 driver is not found when deployed on Tomcat
        DriverManager.registerDriver(new org.h2.Driver());

        final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        localContainerEntityManagerFactoryBean.setPersistenceProvider(persistenceProvider);
        localContainerEntityManagerFactoryBean.setPackagesToScan("goods.model");
        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);

        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(FactoryBean<EntityManagerFactory> entityManagerFactoryFactoryBean) throws Exception {
        return new JpaTransactionManager(entityManagerFactoryFactoryBean.getObject());
    }
}
