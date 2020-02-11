package spittr.db.hibernate4;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan
public class RepositoryTestConfigOfMysql implements TransactionManagementConfigurer {

  @Inject
  private SessionFactory sessionFactory;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
    dataSource.setUrl("jdbc:mysql://10.93.57.154:3306/hcs_db?useUnicode=true&characterEncoding=UTF-8");
    dataSource.setUsername("hcsdb");
    dataSource.setPassword("}]rKM:ED");
    return dataSource;
  }

  public PlatformTransactionManager annotationDrivenTransactionManager() {
    System.out.println(sessionFactory);
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory);
    return transactionManager;
  }

  @Bean
  public SessionFactory sessionFactoryBean() {
    try {
      LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
      lsfb.setDataSource(dataSource());
      lsfb.setPackagesToScan("spittr.domain");
      Properties props = new Properties();
      props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
      lsfb.setHibernateProperties(props);
      lsfb.afterPropertiesSet();
      SessionFactory object = lsfb.getObject();
      return object;
    } catch (IOException e) {
      return null;
    }
  }
}
