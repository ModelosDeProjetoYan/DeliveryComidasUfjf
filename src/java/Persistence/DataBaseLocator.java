/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author yan
 */
public class DataBaseLocator {
    private static DataBaseLocator instance = new DataBaseLocator();

    private DataBaseLocator() {
    }

    public static DataBaseLocator getInstance() {
        return instance;
    }
        public Connection getConnection() throws ClassNotFoundException, SQLException {
      
        Connection conn =
                DriverManager.getConnection("jdbc:derby://localhost:1527/todosProjetosMarco", "root", "senha");    
        return conn;
    }

}
