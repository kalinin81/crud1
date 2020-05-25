package servlet;

//import com.google.gson.Gson;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService.getInstance().deleteUser(Long.parseLong(req.getParameter("id")));
        req.setAttribute("users", UserService.getInstance().getAllUsers());
        req.getRequestDispatcher("/user.jsp").forward(req, resp);
    }
}
