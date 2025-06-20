package ru.itis.epicure.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.epicure.dto.PostDto;
import ru.itis.epicure.dto.PostForm;
import ru.itis.epicure.dto.RestaurantDto;

import java.util.List;

public interface PostService {
    List<PostDto> postsByUserId(Long userId, Long currentUserId);
    PostDto getPostById(Long postId, Long currentUserId);
    void addPost(PostForm postForm, Long currentUserId, List<MultipartFile> files);
    boolean toggleLikePost(Long postId, Long userId);
    int getPostLikeCount(Long postId);
//    void deletePost(Long postId, Long userId);
}
