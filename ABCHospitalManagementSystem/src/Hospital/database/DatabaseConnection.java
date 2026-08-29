package Hospital.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;"
           +"databaseName=ABCHospitalDatabase;"
           +"encrypt=false;"
           +"trustServerCertificate=true";
    
    private static final String USERNAME = "mainhospital";
    private static final String PASSWORD = "12345";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USERNAME,
                PASSWORD
        );        
    }
}
