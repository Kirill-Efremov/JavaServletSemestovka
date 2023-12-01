package ru.itis.firstsemestrovka.repository;


import ru.itis.firstsemestrovka.model.FileInfo;
import ru.itis.firstsemestrovka.repository.base.CrudRepository;

public interface FilesRepository extends CrudRepository<FileInfo, Long> {}
