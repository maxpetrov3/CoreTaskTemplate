package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Session connection;
    public UserDaoHibernateImpl() {
        connection = Util.getHibernateConnection();
    }


    @Override
    public void createUsersTable() {
        connection.createSQLQuery("CREATE TABLE IF NOT EXISTS user (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  name VARCHAR(45) NOT NULL," +
                        "  lastname VARCHAR(45) NOT NULL," +
                        "  age INT NULL," +
                        "  PRIMARY KEY (id))")
                .executeUpdate();
    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        connection.save(new User(name, lastName, age));

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
