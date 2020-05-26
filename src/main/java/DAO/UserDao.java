package DAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    Long insert(String login, String password, String email);
    Long update(Long id, String login, String password, String email);
    List<User> read() throws SQLException;
    boolean existUser(Long id);
    boolean existUser(String login);
    User read(Long id);
    User read(String login);
    void delete(User user);
    void deleteAll();
}
