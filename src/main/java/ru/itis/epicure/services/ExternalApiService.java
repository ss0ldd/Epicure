package ru.itis.epicure.services;

import ru.itis.epicure.dto.ExternalPostDto;

import java.util.List;

public interface ExternalApiService {
    List<ExternalPostDto> getAllPosts();
    ExternalPostDto getPostById(Long id);
    ExternalPostDto createPost(ExternalPostDto post);
} 