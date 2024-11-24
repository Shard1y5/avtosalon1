package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    public static Connection connection = null;

    public static void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/autosalon?useSSL=false&serverTimezone=UTC"; 
        String user = "root";  
        String password = "Ruy20hpmup2)"; 

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully connected to the database");
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
            throw e; 
        }
    }

    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}