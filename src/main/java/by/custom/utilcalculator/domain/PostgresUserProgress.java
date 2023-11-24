package by.custom.utilcalculator.domain;

import java.sql.*;

public class PostgresUserProgress {

    public void testDatabase() {
        String jdbcURL = "jdbc:postgresql://localhost:5432/test";
        String name = "postgres";
        String password = "postgres";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, name, password);
            System.out.println("Connection postgresql successful");

            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection postgresql exception");
            e.printStackTrace();
        }
    }
}
