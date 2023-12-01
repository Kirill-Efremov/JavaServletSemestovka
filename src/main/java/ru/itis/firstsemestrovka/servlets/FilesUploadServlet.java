package ru.itis.firstsemestrovka.servlets;

import ru.itis.firstsemestrovka.exceptions.FileSizeException;
import ru.itis.firstsemestrovka.services.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/file-upload-settings")
@MultipartConfig
public class FilesUploadServlet extends HttpServlet {
    private FilesService filesService;

    @Override
    public void init(ServletConfig config) {
        filesService = (FilesService) config.getServletContext().getAttribute("filesService");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("user_id");
        Part part = req.getPart("file");
        try {
             filesService.saveFileToStorageUser(userId , part.getInputStream(),
                    part.getSubmittedFileName(),
                    part.getContentType(),
                    part.getSize());
        } catch (FileSizeException e) {
            resp.setStatus(400);
            resp.getWriter().println(e.getMessage());}
    }
}
