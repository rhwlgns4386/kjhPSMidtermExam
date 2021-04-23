package kr.ac.jejunu.userdao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public User get(Integer id) throws SQLException {
        String sql="select * from userinfo where id = ?";
        return jdbcTemplate.query(sql,rs -> {
            User user=null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        },id);
    }


    public void insert(User user) throws SQLException {
        String sql="insert into userinfo(name,password) value (?,?)";
        Object obj[]=new Object[]{user.getName(),user.getPassword()};
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement;
            preparedStatement =
                    con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i< obj.length; i++){
                preparedStatement.setObject(i+1, obj[i]);
            }
            return preparedStatement;
        },keyHolder);
        user.setId(keyHolder.getKey().intValue());
    }

    public void update(User user) throws SQLException {
        String sql="update userinfo set name=?,password=? where id=?";
        Object obj[]=new Object[]{user.getName(),user.getPassword(),user.getId()};
        jdbcTemplate.update(sql,obj);
    }

    public void delete(Integer id) throws SQLException {
        String sql="delete from userinfo where id=?";
        Object obj[]=new Object[]{id};
        jdbcTemplate.update(sql,obj);
    }
}
