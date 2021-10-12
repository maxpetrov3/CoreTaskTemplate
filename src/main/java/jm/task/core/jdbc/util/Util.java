package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    //jdbc:mysql://localhost:3307/mentor
    private static final String host = "jdbc:mysql://127.0.0.1:";
    private static final String port = "3307";
    private static final String user = "root";
    private static final String password = "admin";
    private static final String schema = "mentor";

    public static Connection getConnection() {
        String url = host + port + "/" + schema;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.out.println("DataBase connection failed!");
            e.printStackTrace();
        }
        return connection;
    }
}
