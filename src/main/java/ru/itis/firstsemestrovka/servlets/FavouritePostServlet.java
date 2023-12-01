package ru.itis.firstsemestrovka.servlets;

import ru.itis.firstsemestrovka.services.FavouritePostService;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/favourite")
public class FavouritePostServlet extends HttpServlet {
    private FavouritePostService favouritePostService;

    public void init(ServletConfig config) {
        favouritePostService = (FavouritePostService) config.getServletContext().getAttribute("favouritePostService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("user_id");
        req.setAttribute("posts", favouritePostService.getAllFavouritePostsByUser(userId));
        req.getRequestDispatcher("favourite.ftl").forward(req, resp);
    }
}
