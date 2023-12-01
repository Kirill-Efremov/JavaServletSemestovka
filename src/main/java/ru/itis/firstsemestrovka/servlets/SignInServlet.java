package ru.itis.firstsemestrovka.servlets;

import ru.itis.firstsemestrovka.dto.SignInForm;
import ru.itis.firstsemestrovka.dto.UserDto;
import ru.itis.firstsemestrovka.exceptions.AuthorizationException;
import ru.itis.firstsemestrovka.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signInService = (SignInService) config.getServletContext().getAttribute("signInService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("sign-in.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (!(email.isEmpty() || password.isEmpty())) {
            try {
                SignInForm form = SignInForm.builder()
                        .email(email)
                        .password(password)
                        .build();
                UserDto user = signInService.signIn(form);
                req.getSession().setAttribute("user", user);
                req.getSession().setAttribute("user_id", user.getId());
                resp.sendRedirect("posts");
            } catch (AuthorizationException e) {
                req.setAttribute("errorMessage", "Wrong email or password");
                req.getRequestDispatcher("sign-in.ftl").forward(req, resp);
            }
        } else {
            resp.sendRedirect("sign-in");
        }
    }
}
