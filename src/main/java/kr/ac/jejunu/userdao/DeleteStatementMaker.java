package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementMaker implements StatementMaker {
    private Integer id;

    public DeleteStatementMaker(Integer id) {
        this.id=id;
    }

    @Override
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement =
                connection.prepareStatement("delete from userinfo where id=?");
        preparedStatement.setLong(1,id);
        return preparedStatement;
    }
}
