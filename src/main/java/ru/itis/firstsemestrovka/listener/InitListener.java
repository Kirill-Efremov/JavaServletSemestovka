package ru.itis.firstsemestrovka.listener;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.firstsemestrovka.repository.FavouritePostRepository;
import ru.itis.firstsemestrovka.repository.FilesRepository;
import ru.itis.firstsemestrovka.repository.PostRepository;
import ru.itis.firstsemestrovka.repository.UsersRepository;
import ru.itis.firstsemestrovka.repository.implementation.FavouritePostRepositoryImpl;
import ru.itis.firstsemestrovka.repository.implementation.FilesRepositoryImpl;
import ru.itis.firstsemestrovka.repository.implementation.PostRepositoryImpl;
import ru.itis.firstsemestrovka.repository.implementation.UsersRepositoryImpl;
import ru.itis.firstsemestrovka.services.*;
import ru.itis.firstsemestrovka.services.Implementation.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/firstSemestrovkaDB";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String path = "C:\\Users\\comp\\Documents\\server_files\\";


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Postgresql Driver not found.");
        }

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);


        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        PostRepository postRepository = new PostRepositoryImpl(dataSource);
        FavouritePostRepository favouritePostRepository = new FavouritePostRepositoryImpl(dataSource);
        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        FilesRepository filesRepository = new FilesRepositoryImpl(dataSource);

        SignUpService signUpService = new SignUpServiceImpl(usersRepository, passwordEncoder);
        SignInService signInService = new SignInServiceImpl(usersRepository, passwordEncoder);
        UserService userService = new UserServiceImpl(usersRepository);
        PostService postService = new PostServiceImpl(postRepository, favouritePostRepository);
        FavouritePostService favouritePostService = new FavouritePostServiceImpl(favouritePostRepository, postRepository);
        FilesService filesService = new FilesServiceImpl(path, filesRepository, usersRepository, postRepository);



        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("usersRepository", usersRepository);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("postService", postService);
        servletContext.setAttribute("favouritePostService", favouritePostService);
        servletContext.setAttribute("filesService", filesService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
