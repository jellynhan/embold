/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhant.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class DBHelper {
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        //1.Load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2.Create connection string
            //syntax: protocol:DBMS_name://ip:port;databaseName=....
        String con = "jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName=PRJ301";
        //3.Open connection
        Connection connection = DriverManager.getConnection(con,"sa","12345");
        return connection;
    }
}
