package jm.task.core.jdbc.dao;

import static jm.task.core.jdbc.util.Util.*;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() { }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.createSQLQuery(
                    "CREATE TABLE IF NOT EXISTS Users (id bigint AUTO_INCREMENT PRIMARY KEY , name varchar(32), lastName varchar(32), age tinyint)"
            ).executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();

        try (session) {
            session.beginTransaction();

            session.persist(new User(name, lastName, age));

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();

        try (session) {
            session.beginTransaction();

            session.remove(session.get(User.class, id));

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = sessionFactory.openSession();

        try (session) {
            session.beginTransaction();

            users = session.createQuery("FROM User").getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();

        try (session) {
            session.beginTransaction();

            session.createQuery("DELETE FROM User").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}
