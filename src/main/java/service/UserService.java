package service;

import DAO.UserDao;
import DAO.UserHibernateDao;
import DAO.UserJdbcDao;
import model.User;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static UserService userService;
    private UserDao userDao;

    public static UserService getInstance(String className) {
        try {
            if (userService == null) {
                userService = new UserService();
            }
            userService.userDao = UserDaoFactory.getUserDao(className);
/*
            if (className.equals("DAO.UserJdbcDao")) {
                userService.userDao = UserJdbcDao.getInstance();
            } else {
                userService.userDao = UserHibernateDao.getInstance();
            }
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userService;
    }
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            users = userDao.read();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public Long addNewUser(String login, String password, String email) throws SQLException {
        Long id = 0L;
        if (!userDao.existUser(login)) {
            id = userDao.insert(login, password, email);
        }
        return id;
    }
    public Long editUser(String login, String password, String email) throws SQLException {
        Long id = 0L;
        if (userDao.existUser(login)) {
            id = userDao.update(login, password, email);
        }
        return id;
    }
    public void deleteUser(String login) throws SQLException {
        User user = userDao.read(login);
        if (user != null) {
            userDao.delete(user);
        }
    }
    public void deleteAll() throws SQLException {
        userDao.deleteAll();
    }
}
