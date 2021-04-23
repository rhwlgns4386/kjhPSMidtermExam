package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcContext {
    private DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    void jdbcContextForinsert(User user, StatementMaker statementMaker) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementMaker.getPreparedStatement(connection);

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();


            user.setId(resultSet.getInt(1));


        } finally {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    void jdbcContextForupdate(StatementMaker statementMaker) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection =dataSource.getConnection();
            preparedStatement = statementMaker.getPreparedStatement(connection);

            preparedStatement.executeUpdate();

        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    User jdbcContextForGet(StatementMaker statementMaker) throws SQLException {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection =dataSource.getConnection();
            preparedStatement = statementMaker.getPreparedStatement(connection);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }

        } finally {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        //리턴
        return user;
    }

    public User get(String sql, Object[] obj) throws SQLException {
        return jdbcContextForGet(connection -> {
            PreparedStatement preparedStatement;
            preparedStatement =
                    connection.prepareStatement(sql);
            for(int i = 0; i< obj.length; i++){
                preparedStatement.setObject(i+1, obj[i]);
            }
            return preparedStatement;
        });
    }

    public void insert(User user, String sql, Object[] obj) throws SQLException {
        jdbcContextForinsert(user, connection -> {
            PreparedStatement preparedStatement;
            preparedStatement =
                    connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for(int i = 0; i< obj.length; i++){
                preparedStatement.setObject(i+1, obj[i]);
            }
            return preparedStatement;
        });
    }

    public void update(String sql, Object[] obj) throws SQLException {
        jdbcContextForupdate(connection -> {
            PreparedStatement preparedStatement;
            preparedStatement =
                    connection.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                preparedStatement.setObject(i + 1, obj[i]);
            }
            return preparedStatement;
        });
    }
}