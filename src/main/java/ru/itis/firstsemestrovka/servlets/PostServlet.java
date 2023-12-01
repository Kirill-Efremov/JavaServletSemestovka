package ru.itis.firstsemestrovka.servlets;

import ru.itis.firstsemestrovka.services.PostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/posts")
public class PostServlet extends HttpServlet {
    private PostService postService;

    public void init(ServletConfig config) {
        postService = (PostService) config.getServletContext().getAttribute("postService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("posts", postService.getAllPosts());
        req.getRequestDispatcher("posts.ftl").forward(req, resp);
    }
}
