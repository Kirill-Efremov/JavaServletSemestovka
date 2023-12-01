package ru.itis.firstsemestrovka.servlets;

import ru.itis.firstsemestrovka.model.User;
import ru.itis.firstsemestrovka.services.PostService;
import ru.itis.firstsemestrovka.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private UserService userService;
    private PostService postService;

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        postService = (PostService) config.getServletContext().getAttribute("postService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("user_id");
        User user = userService.findById(userId);
        req.setAttribute("user", user);
        req.setAttribute("posts", postService.getAllPostsByUserId(userId));
        req.getRequestDispatcher("profile.ftl").forward(req, resp);
    }
}
