package org.ims.util;

import org.ims.constant.IConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() {
        String connectionUrl = "jdbc:sqlserver://"+ IConnection.HOSTNAME+":"
                +IConnection.PORT+";"+"databaseName="+IConnection.DATABASE_NAME+
                ";encrypt=true;trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(connectionUrl, IConnection.USERNAME,
                    IConnection.PASSWORD);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

//    public static void main(String[] args) {
//        if(getConnection() != null) {
//            System.out.println("Connected");
//        } else {
//            System.out.println("Not connected");
//        }
//    }
}
