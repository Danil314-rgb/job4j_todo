package servlet;

import model.DbStore;
import model.User;
import org.hibernate.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("mail");
        String password = req.getParameter("password");
        Query<User> nUser = DbStore.instOf().findByUserEmail(email);

        if (nUser.uniqueResult() != null) {
            req.setAttribute("error", "Такой email уже сущуствует");
            req.getRequestDispatcher("regist.jsp").forward(req, resp);
        } else {
            DbStore.instOf().create(User.of(name, email, password));
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
