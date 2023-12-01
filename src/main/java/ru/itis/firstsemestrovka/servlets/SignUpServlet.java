package ru.itis.firstsemestrovka.servlets;

import ru.itis.firstsemestrovka.dto.SignUpForm;
import ru.itis.firstsemestrovka.exceptions.AuthorizationException;
import ru.itis.firstsemestrovka.services.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-up")

public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signUpService = (SignUpService) config.getServletContext().getAttribute("signUpService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("sign-up.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (!(lastName.isEmpty() || firstName.isEmpty() || email.isEmpty() || password.isEmpty())) {
            if (signUpService.checkEmail(email)) {
                try {
                    SignUpForm form = SignUpForm.builder()
                            .fistName(firstName)
                            .lastName(lastName)
                            .email(email)
                            .password(password)
                            .build();
                    signUpService.signUp(form);
                    resp.sendRedirect("sign-in");
                } catch (AuthorizationException e) {
                    req.setAttribute("errorMessage", "Error with DataBase has been occured.");
                    req.getRequestDispatcher("sign-up.ftl").forward(req, resp);
                }
            } else {
                req.setAttribute("errorMessage", "This email address is busy");
                req.getRequestDispatcher("sign-up.ftl").forward(req, resp);
            }

        } else {
            req.setAttribute("errorMessage", "You have to fill all form fields.");
            req.getRequestDispatcher("sign-up.ftl").forward(req, resp);
        }
    }
}
