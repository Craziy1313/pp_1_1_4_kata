package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;


public class UserDaoHibernateImpl implements UserDao {



    public UserDaoHibernateImpl() {



    }


    @Override
    public void createUsersTable() {
        try (Session session = getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE `userdb`.`userdatabase` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {

        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("drop table userdatabase").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name,lastName,age);
        try (Session session = getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<User> getAllUsers() {
        List studentsList;
        try (Session session = getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            studentsList = session.createQuery("FROM User").list();
        }
        return studentsList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            session.createQuery("delete User ").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
