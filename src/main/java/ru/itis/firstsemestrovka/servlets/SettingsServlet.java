package ru.itis.firstsemestrovka.servlets;

import ru.itis.firstsemestrovka.dto.UserDto;

import ru.itis.firstsemestrovka.services.UserService;

import javax.servlet.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/settings")
@MultipartConfig
public class SettingsServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute("user");
        req.setAttribute("user", user);
        req.getRequestDispatcher("settings.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        if (userDto != null) {
            userDto.setFirstName(firstName);
            userDto.setLastName(lastName);
            userService.updateUser(userDto);
            req.getSession().setAttribute("user", userDto);
        } else {
            resp.sendRedirect("sign-in");
        }
    }
}
