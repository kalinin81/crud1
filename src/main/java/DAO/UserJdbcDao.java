package DAO;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao implements UserDao{

    private static Connection connection;
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
    public Long insert(String login, String password, String email)  {
        Long id = 0L;
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO crud1.users (email, login, password) VALUES (?, ?, ?)");
            stmt.setString(3, password);
            stmt.setString(1, email);
            stmt.setString(2, login);
            stmt.execute();
            stmt = connection.prepareStatement("select id from crud1.users where login = ?");
            stmt.setString(1, login);
            stmt.execute();
            ResultSet result = stmt.getResultSet();
            id = 0L;
            if (result.next()) {
                id = result.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Long update(Long id, String login, String password, String email)   {
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE crud1.users SET password = ?, email = ?, login = ? WHERE id = ?");
            stmt.setString(1, password);
            stmt.setString(2, email);
            stmt.setString(3, login);
            stmt.setString(4, String.valueOf(id));
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<User> read() {
        List<User> list = null;
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select * from crud1.users");
            ResultSet result = stmt.getResultSet();
            list = new ArrayList<>();
            while (result.next()) {
                list.add(new User(result.getString("login"), result.getString("password"), result.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean existUser(Long id) {
            boolean result = false;
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from crud1.users where id = ?");
            stmt.setString(1, String.valueOf(id));
            stmt.execute();
            result = stmt.getResultSet().next();
    } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public boolean existUser(String login) {
        boolean result = false;
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from crud1.users where login = ?");
            stmt.setString(1, login);
            stmt.execute();
            result = stmt.getResultSet().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User read(Long id) {
        User user = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("select id,login,password,email from crud1.users where id = ?");
            stmt.setString(1, String.valueOf(id));
            stmt.execute();
            ResultSet result = stmt.getResultSet();
            user = new User();
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
    public User read(String login) {
        User user = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("select id,login,password,email from crud1.users where login = ?");
            stmt.setString(1, login);
            stmt.execute();
            ResultSet result = stmt.getResultSet();
            user = new User();
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
    public void delete(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from crud1.users where login = ?");
            stmt.setString(1, user.getLogin());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SET SQL_SAFE_UPDATES = 0; delete from crud1.users; SET SQL_SAFE_UPDATES = 1;");
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
