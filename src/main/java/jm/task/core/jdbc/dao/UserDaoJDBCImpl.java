package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;


    public UserDaoJDBCImpl() {
        connection = Util.getJDBCConnection();
    }

    public void createUsersTable() {
        String query = "CREATE TABLE user (" +
                "  id INT NOT NULL AUTO_INCREMENT," +
                "  name VARCHAR(45) NOT NULL," +
                "  lastname VARCHAR(45) NOT NULL," +
                "  age INT NULL," +
                "  PRIMARY KEY (id))";
        try {
            PreparedStatement createTableStatement = connection.prepareStatement(query);
            createTableStatement.execute();

        } catch (SQLException e) {
            System.out.println("Table creation failed! Table already exist!");
          //  e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            PreparedStatement dropTable = connection.prepareStatement(
                    "DROP TABLE IF EXISTS user");
            dropTable.execute();

        } catch (SQLException e) {
            System.out.println("Nothing to drop!");
           // e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement saveElement = connection.prepareStatement(
                    "INSERT INTO user (name, lastname, age) VALUES (?, ?, ?)");
            saveElement.setString(1, name);
            saveElement.setString(2, lastName);
            saveElement.setByte(3, age);
            saveElement.execute();

        } catch (SQLException e) {
            System.out.println("Can't add user!");
        }

    }

    public void removeUserById(long id) {
        try {
            PreparedStatement deleteUser = connection.prepareStatement("DELETE FROM user WHERE (id = ?)");
            deleteUser.setLong(1, id);
            deleteUser.execute();

        } catch (SQLException e) {
            System.out.println("The " + id + " id doesn't match any user!");
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            PreparedStatement getUsers = connection.prepareStatement("SELECT * FROM user");
            ResultSet result = getUsers.executeQuery();
            while (result.next()) {
                allUsers.add(new User (
                        result.getString("name"),
                        result.getString("lastname"),
                        result.getByte("age")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Can't get all users!");
           // e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try {
            PreparedStatement deleteAll = connection.prepareStatement("DELETE FROM user");
            deleteAll.execute();

        } catch (SQLException e) {
            System.out.println("Can't clear all table");
        }
    }
}
