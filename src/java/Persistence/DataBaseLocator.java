package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseLocator {

    private static DataBaseLocator instance = new DataBaseLocator();
    private Connection conn;

    private DataBaseLocator() {
    }

    public static DataBaseLocator getInstance() {
        return instance;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        conn
                = DriverManager.getConnection("jdbc:derby://localhost:1527/bancoIfood", "usuario", "usuario");
        return conn;
    }

}
