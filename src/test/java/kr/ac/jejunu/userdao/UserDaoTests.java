package kr.ac.jejunu.userdao;


import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";
        UserDao userDao = new JejuUserDao();
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void 추가() throws SQLException, ClassNotFoundException {
        User user=new User();
        user.setName("고지훈");
        user.setPassword("1234");

        UserDao userDao = new JejuUserDao();
        userDao.insert(user);


        User check = userDao.get(user.getId());

        assertThat(check.getId(),greaterThan(0));
        assertThat(check.getId(), is(user.getId()));
        assertThat(check.getName(), is(user.getName()));
        assertThat(check.getPassword(), is(user.getPassword()));
    }

    @Test
    public void 한라testGet() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";
        UserDao userDao = new HalaUserDao();
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void 한라추가() throws SQLException, ClassNotFoundException {
        User user=new User();
        user.setName("고지훈");
        user.setPassword("1234");

        UserDao userDao = new HalaUserDao();
        userDao.insert(user);


        User check = userDao.get(user.getId());

        assertThat(check.getId(),greaterThan(0));
        assertThat(check.getId(), is(user.getId()));
        assertThat(check.getName(), is(user.getName()));
        assertThat(check.getPassword(), is(user.getPassword()));
    }
}
