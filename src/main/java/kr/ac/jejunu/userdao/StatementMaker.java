package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementMaker {
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException;
}
