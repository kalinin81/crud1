package servlet;

//import com.google.gson.Gson;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/edit")
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserService.getInstance().getUser(Long.parseLong(req.getParameter("id")));
        req.setAttribute("login", user.getLogin());
        req.setAttribute("password", user.getPassword());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("id", user.getId());
        req.getRequestDispatcher("/user_edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            UserService.getInstance().editUser(Long.parseLong(req.getParameter("id")), req.getParameter("login"), req.getParameter("password"), req.getParameter("email"));
            req.setAttribute("users", UserService.getInstance().getAllUsers());
            req.getRequestDispatcher("/user.jsp").forward(req, resp);
    }
}
