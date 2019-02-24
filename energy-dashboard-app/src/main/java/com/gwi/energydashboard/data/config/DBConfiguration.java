package com.gwi.energydashboard.data.config;

import com.gwi.energydashboard.data.config.properties.DBProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties(DBProperties.class)
public class DBConfiguration {

    @Autowired
    DBProperties dbProps;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Bean
    @Primary
    public DataSource dataSource() {
        logger.debug("mySQL URL:" + dbProps.getUrl());
        DataSource ds = new DataSource();
        ds.setUrl(dbProps.getUrl());
        ds.setUsername(dbProps.getUsername());
        ds.setPassword(dbProps.getPassword());
        ds.setTestWhileIdle(dbProps.getTestWhileIdle());
        ds.setValidationQuery(dbProps.getValidationQuery());
        ds.setDriverClassName(dbProps.getDriverClassName());
        ds.setMaxIdle(10);
        ds.setMaxWait(10000);
        ds.setInitialSize(10);
        ds.setMaxActive(100);
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.gwi.energydashboard.model"}); // , "org.springframework.data.jpa.convert.threeten"
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties props = new Properties();
        props.setProperty("hibernate.show_sql", dbProps.getShowSql().toString());
        props.setProperty("hibernate.hbm2ddl.auto", dbProps.getDdlAuto());
        props.setProperty("org.hibernate.envers.audit_table_suffix", dbProps.getAuditLogSuffix());
        // props.setProperty("org.hibernate.envers.audit_strategy", "com.entitygroup.halfordspace.audit.HashingAuditStrategy");

        em.setJpaProperties(props);
        return em;
    }

}
