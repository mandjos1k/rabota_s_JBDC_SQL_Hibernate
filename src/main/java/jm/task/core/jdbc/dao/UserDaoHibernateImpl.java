package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



import java.util.List;

public class UserDaoHibernateImpl  implements UserDao {
    public UserDaoHibernateImpl () {
    }





    @Override
    public void createUsersTable() {
        Transaction transaction = null;

        try(Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS USERS " +
                    "(id bigint NOT NULL AUTO_INCREMENT, " +
                    " name text NOT NULL, " +
                    " lastName text NOT NULL, " +
                    " age tinyint, " +
                    " PRIMARY KEY ( id ))";

            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();

           if (transaction!=null){
               transaction.rollback();
           }

        }


    }

    @Override
    public void dropUsersTable() {

        Transaction transaction = null;
        try(Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS USERS";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            if (transaction!=null){
                transaction.rollback();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            if (transaction!=null){
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {

        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("from User").list();
            transaction.commit();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
            }




