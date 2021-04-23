package kr.ac.jejunu.userdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Driver;

@Configuration
public class DaoFactory {
    @Value("${db.driver}")
    private String className;
    @Value("${db.userName}")
    private String userName;
    @Value("${db.password}")
    private String password;
    @Value("${db.url}")
    private String url;
    @Bean
    public UserDao userDao() throws ClassNotFoundException {
        return new UserDao(getJdbcTemplate());
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() throws ClassNotFoundException {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public SimpleDriverDataSource getDataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource=new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }
}
