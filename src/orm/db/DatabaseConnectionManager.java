package orm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private String dbUrl = "jdbc:mysql://localhost:3306/";
    private String username;
    private String password;
    private String dbName;
    private Connection connection;

    public DatabaseConnectionManager(String username, String password, String dbName) {
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        this.dbUrl += dbName;
    }

    public boolean authenticate() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, username, password);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return false;
        }
    }


    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
