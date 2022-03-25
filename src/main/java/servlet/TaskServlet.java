package servlet;

import model.DbStore;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class TaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setAttribute("allTasks", DbStore.instOf().allTasks());
        //req.setAttribute("taskDoneTrue", DbStore);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //DbStore.instOf().addTask(new Task(req.getParameter("description")));
        DbStore.instOf().addTask(new Task(req.getParameter("description"), LocalDateTime.now(), false));
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
