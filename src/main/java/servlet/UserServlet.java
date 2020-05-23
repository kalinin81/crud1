package servlet;

//import com.google.gson.Gson;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("users", UserService.getInstance().getAllUsers());
        req.getRequestDispatcher("/user.jsp").forward(req, resp);

/*
        Gson gson = new Gson();
        String json = "";
        if (req.getPathInfo().contains("all")) {
            json = gson.toJson(DailyReportService.getInstance().getAllDailyReports());
        } else if (req.getPathInfo().contains("last")) {
            //json = gson.toJson(DailyReportService.getInstance().getLastReport());
            json = gson.toJson(DailyReportService.getInstance().getReportNewDay());
        }
        resp.getWriter().write(json);
        resp.setStatus(200);
*/
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("add") != null) {
            UserService.getInstance().addNewUser(req.getParameter("login"), req.getParameter("password"), req.getParameter("email"));
        } else if (req.getParameter("edit") != null) {
            UserService.getInstance().editUser(req.getParameter("login"), req.getParameter("password"), req.getParameter("email"));
        } else if (req.getParameter("delete") != null) {
            UserService.getInstance().deleteUser(req.getParameter("login"));
        }
        resp.setStatus(200);
        req.setAttribute("users", UserService.getInstance().getAllUsers());
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
