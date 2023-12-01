package ru.itis.firstsemestrovka.servlets;

import ru.itis.firstsemestrovka.services.FavouritePostService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/add-to-favourite")
public class AddPostFavouriteServlet extends HttpServlet {
    private FavouritePostService favouritePostService;

    @Override
    public void init(ServletConfig config) {
        favouritePostService = (FavouritePostService) config.getServletContext().getAttribute("favouritePostService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String postId = req.getParameter("postId");
        Long userId = (Long) req.getSession().getAttribute("user_id");
        favouritePostService.save(Long.valueOf(postId), userId);

    }
}
