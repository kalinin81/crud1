package service;

import DAO.UserDao;
import DAO.UserHibernateDao;
import DAO.UserJdbcDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {
    public static UserDao getUserDao() {
        UserDao userDao = null;
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("/Users/jimmim/Documents/java/JavaMentor/preproject/crud1/src/main/resources/config.properties"));

            if (properties.getProperty("daoType").equals("DAO.UserJdbcDao")) {
                userDao = UserJdbcDao.getInstance();
            } else {
                userDao = UserHibernateDao.getInstance();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userDao;
    }
}
