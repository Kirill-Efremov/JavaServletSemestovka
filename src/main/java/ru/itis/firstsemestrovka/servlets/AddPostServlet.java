package ru.itis.firstsemestrovka.servlets;

import ru.itis.firstsemestrovka.dto.PostDto;
import ru.itis.firstsemestrovka.dto.UserDto;
import ru.itis.firstsemestrovka.exceptions.FileSizeException;
import ru.itis.firstsemestrovka.services.FilesService;
import ru.itis.firstsemestrovka.services.PostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/add-post")
@MultipartConfig
public class AddPostServlet extends HttpServlet {
    private PostService postService;
    private FilesService filesService;

    public void init(ServletConfig config) {
        postService = (PostService) config.getServletContext().getAttribute("postService");
        filesService = (FilesService) config.getServletContext().getAttribute("filesService");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("add-post.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto) req.getSession(true).getAttribute("user");
        PostDto form = PostDto.builder().title(req.getParameter("title")).content(req.getParameter("content")).price(Integer.parseInt(req.getParameter("price"))).authorId(userDto.getId()).build();
        PostDto createdPost = postService.addPost(form);
        Long postId = createdPost.getId();
        Part part = req.getPart("file");
        if (part != null && part.getSize() > 0) {
            try {
                filesService.saveFileToStoragePost(postId, part.getInputStream(), part.getSubmittedFileName(), part.getContentType(), part.getSize());
            } catch (FileSizeException e) {
                resp.setStatus(400);
                resp.getWriter().println(e.getMessage());
            }
        }
        resp.sendRedirect("posts");
    }
}
