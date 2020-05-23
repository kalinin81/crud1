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
    public Long addNewUser(String login, String password, String email) {
        Long id = 0L;
        if (!userDao.existUser(login)) {
            id = userDao.insert(login, password, email);
        }
        return id;
    }
    public Long editUser(String login, String password, String email) {
        Long id = 0L;
        if (userDao.existUser(login)) {
            id = userDao.update(login, password, email);
        }
        return id;
    }
    public void deleteUser(String login) {
        User user = userDao.read(login);
        if (user != null) {
            userDao.delete(user);
        }
    }
    public void deleteAll() {
        userDao.deleteAll();
    }
}
