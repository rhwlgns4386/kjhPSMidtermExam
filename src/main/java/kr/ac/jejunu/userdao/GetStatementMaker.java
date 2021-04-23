package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetStatementMaker implements StatementMaker {
    private Integer id;

    public GetStatementMaker(Integer id) {
        this.id=id;
    }

    @Override
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement =
                connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }
}
