package ru.itis.firstsemestrovka.services;

import ru.itis.firstsemestrovka.model.FileInfo;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FilesService {
    void saveFileToStorageUser(Long userId, InputStream inputStream, String originalFileName, String contentType, Long size);

    void saveFileToStoragePost(Long postId, InputStream inputStream, String originalFileName, String contentType, Long size);

    void readFileFromStorage(Long fileId, OutputStream outputStream) throws FileNotFoundException;

    FileInfo getFileInfo(Long fileId) throws FileNotFoundException;


}
