package ru.itis.firstsemestrovka.servlets;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/sign-out")
public class SignOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.getSession().removeAttribute("user");
        resp.setHeader("Cache-Control", "no-store, must-revalidate");
        resp.sendRedirect("sign-in");
    }
}
