package service;

import DAO.UserDao;
import DAO.UserHibernateDao;
import DAO.UserJdbcDao;

public class UserDaoFactory {
    public static UserDao getUserDao(String daoType) {
        //property,
        UserDao userDao;
        if (daoType.equals("DAO.UserJdbcDao")) {
            userDao = UserJdbcDao.getInstance();
        } else {
            userDao = UserHibernateDao.getInstance();
        }
        return userDao;
    }
}
