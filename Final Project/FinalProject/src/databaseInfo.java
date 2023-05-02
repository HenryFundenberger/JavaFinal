import java.sql.*;

public class databaseInfo {
    private String username = "root";
    private String password = "1DogChester*";
    private String DatabaseURL = "jdbc:mysql:///kcelectricdb";

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseURL() {
        return DatabaseURL;
    }

    // connect function that returns a connection object
    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DatabaseURL, username, password);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }
        return connection;
    }
}
