package ru.itis.firstsemestrovka.servlets;

import ru.itis.firstsemestrovka.services.FavouritePostService;

import javax.servlet.ServletConfig;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/check-favourite")
public class CheckFavouriteServlet extends HttpServlet {
    private FavouritePostService favouritePostService;

    @Override
    public void init(ServletConfig config) {
        favouritePostService = (FavouritePostService) config.getServletContext().getAttribute("favouritePostService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String postId = req.getParameter("postId");
        Long userId = (Long) req.getSession().getAttribute("user_id");
        if (favouritePostService.checkFavouritePost(Long.valueOf(postId), userId)) {
            resp.getWriter().write("not_added");
        } else {
            resp.getWriter().write("already_added");
        }


    }
}
