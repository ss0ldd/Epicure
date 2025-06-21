package ru.itis.epicure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.epicure.dto.ExternalPostDto;
import ru.itis.epicure.services.ExternalApiService;

import java.util.List;

@Controller
@RequestMapping("/external")
public class ExternalApiController {
    
    @Autowired
    private ExternalApiService externalApiService;
    
    @GetMapping("/posts")
    public String getAllPosts(Model model) {
        try {
            List<ExternalPostDto> posts = externalApiService.getAllPosts();
            model.addAttribute("posts", posts);
            return "external_posts";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to fetch posts: " + e.getMessage());
            return "external_posts";
        }
    }
    
    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable Long id, Model model) {
        try {
            ExternalPostDto post = externalApiService.getPostById(id);
            model.addAttribute("post", post);
            return "external_post";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to fetch post: " + e.getMessage());
            return "external_post";
        }
    }
    
    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new ExternalPostDto());
        return "external_create_post";
    }
    
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute ExternalPostDto post, RedirectAttributes redirectAttributes) {
        try {
            ExternalPostDto createdPost = externalApiService.createPost(post);
            redirectAttributes.addFlashAttribute("success", "Post created successfully with ID: " + createdPost.getId());
            return "redirect:/external/posts";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to create post: " + e.getMessage());
            return "redirect:/external/posts/create";
        }
    }
} 