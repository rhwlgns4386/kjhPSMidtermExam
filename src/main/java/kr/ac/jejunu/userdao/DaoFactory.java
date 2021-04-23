package kr.ac.jejunu.userdao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean(name="userDao")
    public UserDao getUserDao() {
        return new UserDao(getJejuUserDao());
    }

    @Bean
    public ConnectionMaker getJejuUserDao() {
        return new JejuUserDao();
    }
}
