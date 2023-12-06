package by.custom.utilcalculator.domain;

import java.sql.*;

public class PostgresUserProgressStorage {

    public void databaseConnect() {
        //String jdbcURL = "jdbc:postgresql://localhost:5432/test";
        String jdbcURL = System.getenv("POSTGRESQL_URL");
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
