package service;

import DAO.UserDao;
import DAO.UserHibernateDao;
import DAO.UserJdbcDao;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static UserService userService;
    private UserDao userDao;

    public static UserService getInstance() {
        try {
            if (userService == null) {
                userService = new UserService();
            }
            userService.userDao = UserDaoFactory.getUserDao();
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

    public User getUser(Long id) {
        return userDao.read(id);
    }

    public Long addNewUser(String login, String password, String email) {
        Long id = 0L;
        if (!userDao.existUser(login)) {
            id = userDao.insert(login, password, email);
        }
        return id;
    }

    public Long editUser(Long id, String login, String password, String email) {
        if (userDao.existUser(id)) {
            id = userDao.update(id, login, password, email);
        }
        return id;
    }
    public void deleteUser(Long id) {
        User user = userDao.read(id);
        if (user != null) {
            userDao.delete(user);
        }
    }
    public void deleteAll() {
        userDao.deleteAll();
    }
}
