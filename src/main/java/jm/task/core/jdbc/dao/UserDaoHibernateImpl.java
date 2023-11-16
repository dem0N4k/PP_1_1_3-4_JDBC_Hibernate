package jm.task.core.jdbc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;

import jm.task.core.jdbc.model.User;

import javax.persistence.Query;

import static jm.task.core.jdbc.util.Util.getSessionFactory;


public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE user (Id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "Name VARCHAR(45), LastName VARCHAR(60), Age TINYINT)";

        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.createSQLQuery(sql).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE user";

        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);

        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, id);

            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Session session = getSessionFactory().openSession()) {
            userList = session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("delete from User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUserName() {
        String sql = "select NAME from USER";
        User userName = new User();

        Query query;
        try (Session session = sessionFactory.openSession()) {
            query = session.createSQLQuery(sql);

            List<Object> rows = query.getResultList();
            for (Object row : rows) {
                userName.setName(row.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userName.getName();
    }
}
