package ru.itis.firstsemestrovka.services.Implementation;


import ru.itis.firstsemestrovka.exceptions.FileSizeException;
import ru.itis.firstsemestrovka.model.FileInfo;
import ru.itis.firstsemestrovka.repository.FilesRepository;
import ru.itis.firstsemestrovka.repository.PostRepository;
import ru.itis.firstsemestrovka.repository.UsersRepository;
import ru.itis.firstsemestrovka.services.FilesService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class FilesServiceImpl implements FilesService {

    private final String path;
    private final FilesRepository filesRepository;
    private final UsersRepository usersRepository;
    private final PostRepository postRepository;

    public FilesServiceImpl(String path, FilesRepository filesRepository, UsersRepository usersRepository,PostRepository postRepository) {
        this.path = path;
        this.filesRepository = filesRepository;
        this.usersRepository = usersRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void saveFileToStorageUser(Long userId, InputStream inputStream, String originalFileName, String contentType, Long size) {
        if(size > 10_000_000) {
            throw new FileSizeException("File is too large");
        }
        FileInfo fileInfo = new FileInfo(
                null,
                originalFileName,
                UUID.randomUUID().toString(),
                size,
                contentType
        );
        try {
            Files.copy(inputStream, Paths.get(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            fileInfo = filesRepository.save(fileInfo);
            usersRepository.updateAvatarForUser(userId, fileInfo.getId());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void saveFileToStoragePost(Long postId, InputStream inputStream, String originalFileName, String contentType, Long size) {
        if(size > 10_000_000) {
            throw new FileSizeException("File is too large");
        }
        FileInfo fileInfo = new FileInfo(
                null,
                originalFileName,
                UUID.randomUUID().toString(),
                size,
                contentType
        );
        try {
            Files.copy(inputStream, Paths.get(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            fileInfo = filesRepository.save(fileInfo);
            postRepository.setPhotoForPost(postId, fileInfo.getId());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void readFileFromStorage(Long fileId, OutputStream outputStream) throws FileNotFoundException {
        Optional<FileInfo> optionalFileInfo = filesRepository.findById(fileId);
        FileInfo fileInfo = optionalFileInfo.orElseThrow(() -> new FileNotFoundException("File not found"));
        File file = new File(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);
        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public FileInfo getFileInfo(Long fileId)  {
        return filesRepository.findById(fileId).orElseThrow(()
                ->  new NoSuchElementException("This element not found."));
    }


}
