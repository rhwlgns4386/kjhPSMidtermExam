package kr.ac.jejunu.userdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {
    @Value("${db.driver}")
    private String className;
    @Value("${db.username}")
    private String userName;
    @Value("${db.password}")
    private String password;
    @Value("${db.url}")
    private String url;
    @Bean(name="userDao")
    public UserDao getUserDao() throws ClassNotFoundException {
        return new UserDao(getDataSource());
    }

//    @Bean
//    public ConnectionMaker getJejuUserDao() {
//        return new JejuUserDao();
//    }
    @Bean
    DataSource getDataSource() throws ClassNotFoundException {
        SimpleDriverDataSource simpleDriverDataSource=new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        simpleDriverDataSource.setUsername(userName);
        simpleDriverDataSource.setPassword(password);
        simpleDriverDataSource.setUrl(url);
        return simpleDriverDataSource;
    }
}
