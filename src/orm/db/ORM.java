package orm.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ORM {
    private DatabaseConnectionManager connectionManager;

    public ORM(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        if (!connectionManager.authenticate()) {
            throw new RuntimeException("User authentication failed!");
        }
    }

    // Method to create a table if it does not exist
    public void createTableIfNotExists(String tableName, String tableSchema) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + tableSchema + ");";
        executeUpdate(sql);
    }

    // Create (Insert)
    public void insert(String tableName, String columns, String values) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ");";
        executeUpdate(sql);
    }

    // Read (Select)
    public ResultSet select(String tableName, String columns, String condition) throws SQLException {
        String sql = "SELECT " + columns + " FROM " + tableName + (condition.isEmpty() ? "" : " WHERE " + condition) + ";";
        return executeQuery(sql);
    }

    // Update
    public void update(String tableName, String setStatement, String condition) throws SQLException {
        String sql = "UPDATE " + tableName + " SET " + setStatement + " WHERE " + condition + ";";
        executeUpdate(sql);
    }

    // Delete
    public void delete(String tableName, String condition) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE " + condition + ";";
        executeUpdate(sql);
    }

    // Execute Update (Insert, Update, Delete)
    private void executeUpdate(String sql) throws SQLException {
    	
    	Connection conn = connectionManager.getConnection(); 
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        		
    }

    // Execute Query (Select)
    private ResultSet executeQuery(String sql) throws SQLException {
    	
    	Connection conn = connectionManager.getConnection(); 
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);        
    	
    }
}
