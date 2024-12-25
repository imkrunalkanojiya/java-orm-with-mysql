package orm.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		 // Connect to the database with user authentication
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager("root", "", "orm_db");
        ORM orm = new ORM(connectionManager);

        // Create a table if it doesn't exist
        String tableSchema = "id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), age INT";
        orm.createTableIfNotExists("users", tableSchema);

        // Insert a new user
        orm.insert("users", "name, age", "'John Doe', 30");

        // Select all users
        ResultSet result = orm.select("users", "*", "");
        try {
            while (result != null && result.next()) {
                System.out.println("User: " + result.getString("name") + ", Age: " + result.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println("Error reading result: " + e.getMessage());
        }

        // Update user
        orm.update("users", "age = 31", "name = 'John Doe'");

        // Delete user
        orm.delete("users", "id = 4");

        // Close connection
        connectionManager.closeConnection();
        
	}

}
