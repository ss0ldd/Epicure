package ru.itis.epicure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.epicure.services.FileStorageService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FileController {
    
    @Autowired
    private FileStorageService fileStorageService;
    
    @GetMapping("/files/{fileName}")
    public void getFile(@PathVariable String fileName, HttpServletResponse response) {
        fileStorageService.writeFileToResponse(fileName, response);
    }
} 