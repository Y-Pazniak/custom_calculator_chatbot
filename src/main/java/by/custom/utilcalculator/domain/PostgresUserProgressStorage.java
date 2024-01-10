package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.exception.UtilsborException;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class PostgresUserProgressStorage implements IUserProgressStorage{
    private Connection conn;

    public static PostgresUserProgressStorage getInstance() {
        return PostgresUserProgressStorageHolder.POSTGRES_USER_PROGRESS_STORAGE;
    }

    private PostgresUserProgressStorage() {
        databaseConnect();
    }

    private void databaseConnect() {
        DataSource dataSource = createDataSource();
        try {
            conn = dataSource.getConnection();
            System.out.println("Connection to DB has been successful");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//        String jdbcURL = "jdbc:postgresql://localhost:5432/custom_chatbot_DB";
//        //String jdbcURL = System.getenv("POSTGRESQL_URL");
//        String name = "postgres";
//        String password = "postgres";
//
//        try {
//            Connection connection = DriverManager.getConnection(jdbcURL, name, password);
//            System.out.println("Connection postgresql successful");
//
////            String sql = "INSERT INTO users (chat_id, country_origin) VALUES (?, ?)";
////
////            PreparedStatement statement = connection.prepareStatement(sql);
////            statement.setString(1, "222");
////            statement.setString(2, "other");
////
////            int rows = statement.executeUpdate();
////            if (rows > 0) {
////                System.out.println("SQL row has been added successfully");
////            }
//
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Connection postgresql exception");
//            e.printStackTrace();
//        }
    }

//    public void read() throws SQLException {
//        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
//        ResultSet rs = stmt.executeQuery();
//
//        while (rs.next()) {
//            System.out.printf("id:%d id:%s description:%n", rs.getLong("chat_id"),
//                    rs.getString("country_origin"));
//        }
//    }

    @Override
    public void save(final UserProgress userProgress) throws UtilsborException {
        try {
            isUserExists(userProgress);
            PreparedStatement insertUser =
                    conn.prepareStatement("INSERT INTO users(chat_id) VALUES (?)");
            insertUser.setString(1, userProgress.getChatID());
            insertUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void isUserExists(UserProgress userProgress) throws SQLException {
        //String sqlRequest = "SELECT EXISTS(SELECT chat_id FROM users WHERE id = " + userProgress.getChatID() + ")";

        String sql_res= "SELECT * FROM users WHERE chat_id = A123";
        Statement checkUser = conn.createStatement();
        ResultSet resultSet = checkUser.executeQuery(sql_res);
        System.out.println(resultSet.next());
    }

    @Override
    public UserProgress get(String chatID) throws UtilsborException {
        return null;
    }

    public void insert(final UserProgress userProgress) {

    }

    public void select() {
    }

    public void update() {
    }

    public void delete() {
    }

    private DataSource createDataSource() {
        final String url = "jdbc:postgresql://localhost:5432/custom_chatbot_DB?user=postgres&password=postgres";
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(url);
        return dataSource;
    }

    private static class PostgresUserProgressStorageHolder {
        private static final PostgresUserProgressStorage POSTGRES_USER_PROGRESS_STORAGE = new PostgresUserProgressStorage();
    }
}
