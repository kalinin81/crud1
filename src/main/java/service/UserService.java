package service;

import DAO.UserDao;
import model.User;

import java.util.List;

public class UserService {

    private static UserService userService;
    private UserDao userDao = UserDao.getInstance();

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }
    public List<User> getAllUsers() {
        return userDao.read();
    }
    public User getUser(String login) {
        return userDao.read(login);
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
        //Long id = 0L;
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
