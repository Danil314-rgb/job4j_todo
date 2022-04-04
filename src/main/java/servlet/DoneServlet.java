package servlet;

import model.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        DbStore.instOf().updateDone(
                Boolean.parseBoolean(req.getParameter("done")),
                Integer.parseInt(req.getParameter("id"))
        );
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
