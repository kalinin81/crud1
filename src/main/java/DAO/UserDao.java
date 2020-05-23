package DAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //UserHibernateDao getInstance();
    Long insert(String login, String password, String email) throws SQLException;
    Long update(String login, String password, String email) throws SQLException;
    List<User> read() throws SQLException;
    boolean existUser(String login) throws SQLException;
    User read(String login) throws SQLException;
    void delete(User user) throws SQLException;
    void deleteAll() throws SQLException;
}
