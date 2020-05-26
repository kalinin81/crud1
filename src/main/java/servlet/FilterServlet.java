package servlet;

import DAO.UserDao;
import model.User;
import service.UserDaoFactory;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin"})
public class FilterServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            httpServletResponse.sendRedirect("/login");
        } else {
            String role = user.getRole();
            if (role.equals("user")) {
                httpServletResponse.sendRedirect("/user");
            } else { //admin
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
