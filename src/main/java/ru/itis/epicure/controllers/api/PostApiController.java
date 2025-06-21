package ru.itis.epicure.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.epicure.dto.PostDto;
import ru.itis.epicure.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostApiController {
    
    @Autowired
    private PostService postService;
    
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        // Для демонстрации возвращаем посты пользователя с ID 1
        List<PostDto> posts = postService.postsByUserId(1L, 1L);
        return ResponseEntity.ok(posts);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        try {
            PostDto post = postService.getPostById(id, 1L);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
} 