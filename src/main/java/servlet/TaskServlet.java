package servlet;

import model.DbStore;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tasks", new ArrayList<>(DbStore.instOf().allTasks()));
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        /*var user =  req.getSession();
        User user = DbStore.instOf().create(User.of("Dan", "dan.@mail.ru", "piz"));
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        var item = session.getAttribute("user");*/

        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");

        DbStore.instOf().create(Task.of(
                req.getParameter("description"),
                LocalDateTime.now(),
                false,
                user));
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
