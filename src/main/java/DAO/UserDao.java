package DAO;

import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.DBHelper;

import java.util.List;

public class UserDao {

    private SessionFactory sessionFactory = DBHelper.getSessionFactory();
    private static UserDao userDao;

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public Long insert(String login, String password, String email) {
        Long id = 0L;
        //if (read(model).size() < 10) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            id = (Long) session.save(new User(login, password, email));
            transaction.commit();
            session.close();
        //}
        return id;
    }
    public Long update(String login, String password, String email) {
        Long id = 0L;
        User user = read(login);
        if (user != null) {
            user.setPassword(password);
            user.setEmail(email);
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
            id  = user.getId();
        }
        return id;
    }

/*
    public List<User> read(String login) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Car.class);
        List<Car> cars = criteria.add(Restrictions.eq("model", model)).list();
        session.close();
        return cars;
    }
*/
    public List<User> read() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createCriteria(User.class).list();
        session.close();
        return users;
    }
    public boolean existUser(String login) {
        return read(login) != null;
    }
    public User read(String login) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        User user = (User) criteria.uniqueResult();
        session.close();
        return user;
    }
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }
    public void deleteAll() {
        List<User> users = read();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for (User user : users) {
            delete(user);
        }
        transaction.commit();
        session.close();
    }

}
