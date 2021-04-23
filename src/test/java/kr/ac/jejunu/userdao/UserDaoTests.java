package kr.ac.jejunu.userdao;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    private static UserDao daoFactory;
    @BeforeAll
    public static void setup(){
        ApplicationContext applicationContext= new AnnotationConfigApplicationContext(DaoFactory.class);
        daoFactory=applicationContext.getBean("userDao",UserDao.class);
    }

    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";

        UserDao userDao = daoFactory;
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void 저장() throws SQLException, ClassNotFoundException {
        User user=new User();

        String name = "언니...";
        String password = "힘내...";
        user.setName(name);
        user.setPassword(password);
        UserDao userDao = daoFactory;

        userDao.insert(user);

        User check = userDao.get(user.getId());
        assertThat(check.getId(), is(user.getId()));
        assertThat(check.getName(), is(user.getName()));
        assertThat(check.getPassword(), is(user.getPassword()));
    }

    @Test
    public void 업데이트() throws SQLException, ClassNotFoundException {
        User user=new User();
        UserDao userDao = daoFactory;
        String name = "언니...";
        String password = "힘내...";
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        user.setName("누나...");
        user.setPassword("...힘내");
        userDao.update(user);

        User check = userDao.get(user.getId());
        assertThat(check.getId(), is(user.getId()));
        assertThat(check.getName(), is(user.getName()));
        assertThat(check.getPassword(), is(user.getPassword()));
    }

    @Test
    public void 삭제() throws SQLException, ClassNotFoundException {
        User user=new User();

        String name = "언니...";
        String password = "힘내...";
        user.setName(name);
        user.setPassword(password);
        UserDao userDao = daoFactory;

        userDao.insert(user);

        userDao.delete(user.getId());
        User check = userDao.get(user.getId());
        assertThat(check,nullValue());

    }
}
