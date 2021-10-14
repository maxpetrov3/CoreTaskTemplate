package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
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

    public static Connection getJDBCConnection() {
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

    public static Session getHibernateConnection() {
        Configuration config = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", host + port + "/" + schema)
                .setProperty("hibernate.connection.username", user)
                .setProperty("hibernate.connection.password", password)
                .addAnnotatedClass(User.class);


        Session session = null;
        try {
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            SessionFactory sessionFactory = config.buildSessionFactory(sr);
            session = sessionFactory.openSession();

        } catch (Exception e) {
            System.out.println("Hibernate. Connection failed!");
            // e.printStackTrace();
        }

        return session;

    }
}
