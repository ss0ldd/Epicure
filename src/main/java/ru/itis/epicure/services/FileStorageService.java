package ru.itis.epicure.services;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.epicure.models.FileInfo;

public interface FileStorageService {
    FileInfo saveFile(MultipartFile uploadFile);
    void writeFileToResponse (String fileName, HttpServletResponse response);
}