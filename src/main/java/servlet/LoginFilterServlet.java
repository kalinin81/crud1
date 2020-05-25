package servlet;

import DAO.UserDao;
import model.User;
import service.UserDaoFactory;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebFilter(urlPatterns = "/login")
public class LoginFilterServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Properties properties = new Properties();
        String className = properties.getProperty("daoType");
        HttpSession httpSession = ((HttpServletRequest) request).getSession();
        User user = UserDaoFactory.getUserDao(className).read(request.getParameter("login"));
        if (user == null) {
        if (httpSession.getAttribute("user") == null) {
            httpSession.setAttribute("user",user);
        }
        String role = user.getRole();
        if (role != null) {
            if (role.equals("user")) {
                request.setAttribute("login", request.getParameter("login"));
                request.setAttribute("password", request.getParameter("password"));
                request.getRequestDispatcher("/user1.jsp").forward(request, response);
            } else { //admin
                request.setAttribute("users", UserService.getInstance(className).getAllUsers());
                request.getRequestDispatcher("/user.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
