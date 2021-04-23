package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext=jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        StatementMaker statementMaker=new GetStatementMaker(id);
        return jdbcContext.jdbcContextForGet(statementMaker);
    }

    public void insert(User user) throws SQLException {
        StatementMaker statementMaker=new InsertStatementMaker(user);
        jdbcContext.jdbcContextForinsert(user, statementMaker);

    }

    public void update(User user) throws SQLException {
        StatementMaker statementMaker=new UpdateStatementMaker(user);
        jdbcContext.jdbcContextForupdate(statementMaker);
    }

    public void delete(Integer id) throws SQLException {
        StatementMaker statementMaker=new DeleteStatementMaker(id);
        jdbcContext.jdbcContextForupdate(statementMaker);
    }

}
