package servlet;

import model.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FlagServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean done = Boolean.parseBoolean(req.getParameter("done"));
        String name = req.getParameter("done");
        DbStore.instOf().updateDone(name);



        /*resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        *//*String id = req.getParameter("id");*//*
        DbStore.instOf().updateDone(name);*/
    }
}
