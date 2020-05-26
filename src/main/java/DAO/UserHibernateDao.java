package DAO;

import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.DBHelper;

import java.util.List;

public class UserHibernateDao implements UserDao{

    private final SessionFactory sessionFactory = DBHelper.getSessionFactory();
    private static UserHibernateDao userHibernateDao;

    public static UserHibernateDao getInstance() {
        if (userHibernateDao == null) {
            userHibernateDao = new UserHibernateDao();
        }
        return userHibernateDao;
    }
    private UserHibernateDao() {

    }
    @Override
    public Long insert(String login, String password, String email) {
        Long id = 0L;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Long) session.save(new User(login, password, email));
        transaction.commit();
        session.close();
        return id;
    }
    @Override
    public Long insert(String login, String password, String email, String role) {
        Long id = 0L;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (Long) session.save(new User(login, password, email, role));
        transaction.commit();
        session.close();
        return id;
    }
    @Override
    public Long update(Long id, String login, String password, String email) {
        User user = read(id);
        if (user != null) {
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
        }
        return id;
    }
    @Override
    public List<User> read() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createCriteria(User.class).list();
        session.close();
        return users;
    }
    @Override
    public boolean existUser(String login) {
        return read(login) != null;
    }
    @Override
    public boolean existUser(Long id) {
        return read(id) != null;
    }
    @Override
    public User read(String login) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        User user = (User) criteria.uniqueResult();
        session.close();
        return user;
    }
    @Override
    public User read(String login, String password) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        criteria.add(Restrictions.eq("password", password));
        User user = (User) criteria.uniqueResult();
        session.close();
        return user;
    }
    @Override
    public User read(Long id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }
    @Override
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }
    @Override
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
