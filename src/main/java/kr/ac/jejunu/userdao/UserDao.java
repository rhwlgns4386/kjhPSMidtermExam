package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext=jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        Object obj[]=new Object[]{id};
        return jdbcContext.get(sql, obj);
    }

    public void insert(User user) throws SQLException {
        String sql = "insert into userinfo(name,password) value (?,?)";
        Object obj[]=new Object[]{user.getName(),user.getPassword()};
        jdbcContext.insert(user, sql, obj);

    }

    public void update(User user) throws SQLException {
        String sql = "update userinfo set name=?,password=? where id=?";
        Object obj[]=new Object[]{user.getName(),user.getPassword(),user.getId()};
        jdbcContext.update(sql, obj);
    }

    public void delete(Integer id) throws SQLException {
        String sql = "delete from userinfo where id=?";
        Object obj[]=new Object[]{id};
        jdbcContext.update(sql, obj);
    }



}
