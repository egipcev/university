package ua.com.foxminded.config;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

@ComponentScan(basePackages = "ua.com.foxminded")
@PropertySource("classpath:config.properties")
public class IntegrationTestConfig {

    @Value("${url}")
    private String url;

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    @Value("${driver}")
    private String driver;

    @Bean
    DataSource dataSource() throws IllegalStateException, NamingException {
        Properties prop = new Properties();
        prop.setProperty("user", user);
        prop.setProperty("password", password);
        final SimpleNamingContextBuilder simpleNamingContextBuilder = new SimpleNamingContextBuilder();
        simpleNamingContextBuilder.activate();

        final InitialContext context = new InitialContext();

        context.bind("java:comp/env/jdbc/datasource", new DriverManagerDataSource(url, prop));
        return (DataSource) context.lookup("java:comp/env/jdbc/datasource");
    }

}
