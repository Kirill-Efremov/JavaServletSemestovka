package ru.itis.firstsemestrovka.servlets;

import ru.itis.firstsemestrovka.services.FavouritePostService;
import ru.itis.firstsemestrovka.services.PostService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/delete-post")
public class DeletePostServlet extends HttpServlet {
    private PostService postService;
    private FavouritePostService favouritePostService;

    @Override
    public void init(ServletConfig config) {
        postService = (PostService) config.getServletContext().getAttribute("postService");
        favouritePostService = (FavouritePostService) config.getServletContext().getAttribute("favouritePostService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String postId = req.getParameter("postId");
        if (!favouritePostService.getAllFavouritePostsByPost(Long.valueOf(postId)).isEmpty()) {
            favouritePostService.deleteByPostId(Long.valueOf(postId));
        }
        postService.delete(Long.valueOf(postId));

    }
}
