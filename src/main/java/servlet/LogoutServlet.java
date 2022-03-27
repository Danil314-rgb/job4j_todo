package servlet;

import model.DbStore;
import model.User;
import org.hibernate.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Query<User> nUser = DbStore.instOf().findByUserEmail(email);

        if (nUser.uniqueResult() == null) {
            req.setAttribute("error", "Пользователя с данным email не существует");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            if (password.equals(nUser.getSingleResult().getPassword())) {
                HttpSession session = req.getSession();
                User user = new User();
                user.setName(email);
                user.setEmail(email);
                session.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/login.do");
            } else {
                req.setAttribute("error", "Не верный пароль");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }
    }
}
