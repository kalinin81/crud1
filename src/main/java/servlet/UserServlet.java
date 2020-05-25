package servlet;

import DAO.UserJdbcDao;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(value = "/vvv")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties properties = new Properties();
        String className = properties.getProperty("daoType");
/*
        String className = req.getParameter("className");
        if (className == null) {
            className = "DAO.UserJdbcDao";
        }
*/
        req.setAttribute("users", UserService.getInstance(className).getAllUsers());
        req.getRequestDispatcher("/user.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String className = req.getParameter("className");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        try {
            if (req.getParameter("add") != null) {
                UserService.getInstance(className).addNewUser(login, password, email);
            } else if (req.getParameter("edit") != null) {
                UserService.getInstance(className).editUser(login, password, email);
            } else if (req.getParameter("delete") != null) {
                UserService.getInstance(className).deleteUser(login);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.setStatus(200);
        req.setAttribute("users", UserService.getInstance(className).getAllUsers());
        req.getRequestDispatcher("/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
