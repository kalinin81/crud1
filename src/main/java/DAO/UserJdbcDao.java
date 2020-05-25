package DAO;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao implements UserDao{

    private Connection connection;
    private static UserJdbcDao userJdbcDao;

    public static UserJdbcDao getInstance() {
        if (userJdbcDao == null) {
            userJdbcDao = new UserJdbcDao();
        }
        return userJdbcDao;
    }
    private UserJdbcDao() {
        connection = DBHelper.getMysqlConnection();
    }
    @Override
    public Long insert(String login, String password, String email) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO crud1.users (email, login, password) VALUES (?, ?, ?)");
        stmt.setString(3, password);
        stmt.setString(1, email);
        stmt.setString(2, login);
        stmt.execute();
        stmt = connection.prepareStatement("select id from crud1.users where login = ?");
        stmt.setString(1, login);
        stmt.execute();
        ResultSet result = stmt.getResultSet();
        Long id = 0L;
        if (result.next()) {
            id = result.getLong(1);
        }
        return id;
    }

    @Override
    public Long update(String login, String password, String email) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE crud1.users SET password = ?, email = ? WHERE login = ?");
        stmt.setString(1, password);
        stmt.setString(2, email);
        stmt.setString(3, login);
        stmt.execute();
        stmt = connection.prepareStatement("select id from crud1.users where login = ?");
        stmt.setString(1, login);
        stmt.execute();
        ResultSet result = stmt.getResultSet();
        Long id = 0L;
        if (result.next()) {
            id = result.getLong(1);
        }
        return id;
    }

    @Override
    public List<User> read() {
        List<User> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select * from crud1.users");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                list.add(new User(result.getString("login"), result.getString("password"), result.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean existUser(String login) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("select * from crud1.users where login = ?");
        stmt.setString(1, login);
        stmt.execute();
        ResultSet result = stmt.getResultSet();
        return result.next();
    }

    @Override
    public User read(String login) {
        User user = new User();
        try {
            PreparedStatement stmt = connection.prepareStatement("select id,login,password,email from crud1.users where login = ?");
            stmt.setString(1, login);
            stmt.execute();
            ResultSet result = stmt.getResultSet();
            if (result.next()) {
                user.setId(result.getLong(1));
                user.setLogin(result.getString(2));
                user.setPassword(result.getString(3));
                user.setEmail(result.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(User user) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("delete from crud1.users where login = ?");
        stmt.setString(1, user.getLogin());
        stmt.execute();
    }

    @Override
    public void deleteAll() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SET SQL_SAFE_UPDATES = 0; delete from crud1.users; SET SQL_SAFE_UPDATES = 1;");
        stmt.execute();

    }
}
