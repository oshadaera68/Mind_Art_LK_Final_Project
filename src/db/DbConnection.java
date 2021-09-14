package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection=null;
    private Connection connection;

    /*2nd Rule*/
    private DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/TimberMill",
                "root",
                "1234");
    }

    /*3rd Rule*/
    public static DbConnection getInstance() throws ClassNotFoundException, SQLException {
         return (dbConnection==null)?(dbConnection= new DbConnection()):(dbConnection);
    }

    public Connection getConnection(){
        return connection;
    }

}
