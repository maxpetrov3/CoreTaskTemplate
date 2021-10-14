package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Session connection;
    public UserDaoHibernateImpl() {
        connection = Util.getHibernateConnection();
    }


    @Override
    public void createUsersTable() {
        try {
            connection.beginTransaction();
            connection.createSQLQuery("CREATE TABLE IF NOT EXISTS user (" +
                    "  id INT NOT NULL AUTO_INCREMENT," +
                    "  name VARCHAR(45) NOT NULL," +
                    "  lastname VARCHAR(45) NOT NULL," +
                    "  age INT NULL," +
                    "  PRIMARY KEY (id))")
                    .executeUpdate();
            connection.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Failed to create table.");
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            connection.beginTransaction();
            connection.createSQLQuery("DROP TABLE IF EXISTS  user").executeUpdate();
            connection.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Failed to drop table!");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            connection.beginTransaction();
            connection.save(new User(name, lastName, age));
            connection.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Failed to save user" + name);
        }

    }

    @Override
    public void removeUserById(long id) {
        try {
            connection.beginTransaction();
            Query deleteByIdQuery = connection.createQuery("delete from User where id= :name");
            deleteByIdQuery.setParameter("name", id);
            deleteByIdQuery.executeUpdate();
            connection.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Failed to remove user by id: " + id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List result = null;
        try {
            Query selectQuery = connection.createQuery("from User");
            result = selectQuery.list();

        } catch (HibernateException e) {
            System.out.println("Failed to get all users!");
        }

        return result;
    }

    @Override
    public void cleanUsersTable() {
        try {
            connection.beginTransaction();
            Query deleteAllQuery = connection.createQuery("delete from User");
            deleteAllQuery.executeUpdate();
            connection.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Failed to clear table");
        }
    }
}
