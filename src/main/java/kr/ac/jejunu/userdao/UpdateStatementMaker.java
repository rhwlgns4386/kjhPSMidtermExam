package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStatementMaker implements StatementMaker {
    private User user;

    public UpdateStatementMaker(User user) {
        this.user=user;
    }

    @Override
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement =
                connection.prepareStatement("update userinfo set name=?,password=? where id=?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setLong(3,user.getId());
        return preparedStatement;
    }
}
