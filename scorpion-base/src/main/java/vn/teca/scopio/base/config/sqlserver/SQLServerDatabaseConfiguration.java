package vn.teca.scopio.base.config.sqlserver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "sqlserverEntityManagerFactory",
        transactionManagerRef = "sqlserverTransactionManager",
        basePackages = {
                "vn.teca.scopio.base.repository"
        }
)
public class SQLServerDatabaseConfiguration {
    private final Logger log = LoggerFactory.getLogger(SQLServerDatabaseConfiguration.class);

    @Autowired
    private Environment env;

    @Primary
    @Bean(name = "sqlserverDataSource")
    public DataSource sqlserverDataSource() {
        if (env.getProperty("spring.datasource.sqlserver.url") == null) {
            log.error("Your SQL Server database connection pool configuration is incorrect! The application" +
                            " cannot start. Please check your Spring profile, current profiles are: {}",
                    Arrays.toString(env.getActiveProfiles()));

            throw new ApplicationContextException("SQL Server Database connection pool is not configured correctly");
        }
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getProperty("spring.datasource.sqlserver.driver-class-name"));
        config.setJdbcUrl(env.getProperty("spring.datasource.sqlserver.url"));
        if (env.getProperty("spring.datasource.sqlserver.username") != null) {
            config.addDataSourceProperty("user", env.getProperty("spring.datasource.sqlserver.username"));
        } else {
            config.addDataSourceProperty("user", ""); // HikariCP doesn't allow null user
        }
        if (env.getProperty("spring.datasource.sqlserver.password") != null) {
            config.addDataSourceProperty("password", env.getProperty("spring.datasource.sqlserver.password"));
        } else {
            config.addDataSourceProperty("password", ""); // HikariCP doesn't allow null password
        }
        config.setMaximumPoolSize(env.getProperty("spring.datasource.sqlserver.maximum-pool-size", Integer.class));
        config.setMaxLifetime(env.getProperty("spring.datasource.sqlserver.max-lifetime", Long.class));
        config.setConnectionTimeout(env.getProperty("spring.datasource.sqlserver.connection-timeout", Long.class));
        config.setMinimumIdle(env.getProperty("spring.datasource.sqlserver.minimum-idle", Integer.class));
        config.setIdleTimeout(env.getProperty("spring.datasource.sqlserver.idle-timeout", Long.class));
        config.setAllowPoolSuspension(false);
        config.setPoolName(env.getProperty("spring.datasource.sqlserver.pool-name"));
        config.setLeakDetectionThreshold(0);
        config.setAutoCommit(false);
        return new HikariDataSource(config);
    }

    @Primary
    @Bean(name = "sqlserverEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sqlserverEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("sqlserverDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("vn.teca.scopio.base.model")
                .persistenceUnit("sqlserverPersistenUnitName")
                .build();
    }

    @Primary
    @Bean(name = "sqlserverTransactionManager")
    public PlatformTransactionManager sqlserverTransactionManager(@Qualifier("sqlserverEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

