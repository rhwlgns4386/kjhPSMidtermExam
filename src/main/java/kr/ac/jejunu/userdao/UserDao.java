package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private DataSource dataSource;
    public UserDao(DataSource dataSource) {
        this.dataSource=dataSource;
    }

    public User get(Integer id) throws SQLException {
        StatementMaker statementMaker=new GetStatementMaker(id);
        User user=null;
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementMaker.getPreparedStatement(connection);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
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

    public void insert(User user) throws SQLException {
        StatementMaker statementMaker=new InsertStatementMaker(user);
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
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


    public void update(User user) throws SQLException {
        StatementMaker statementMaker=new UpdateStatementMaker(user);
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        try {
            connection = dataSource.getConnection();
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

    public void delete(Integer id) throws SQLException {
        StatementMaker statementMaker=new DeleteStatementMaker(id);
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        try {
            connection = dataSource.getConnection();
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
}
