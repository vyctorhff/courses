package br.course.elite.java.jdbc;

import java.sql.*;
import java.util.List;

public class DataBase {

    public List<?> list() throws SQLException {
        String sql = """
                SELECT * from tb_usuario
                """;

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong(0);
            }

        }

        return List.of();
    }

    private Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/mysql-pos-elite?serverTimezone=UTC";
            String user = "user";
            String pass = "pass";

            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
