package ru.itis.epicure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.epicure.dto.CommentDto;
import ru.itis.epicure.dto.PostDto;
import ru.itis.epicure.dto.RestaurantDto;
import ru.itis.epicure.models.User;
import ru.itis.epicure.security.details.UserDetailsImpl;
import ru.itis.epicure.services.CommentService;
import ru.itis.epicure.services.PostService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @GetMapping("/post/{postId}")
    public String getPostPage(@PathVariable Long postId, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostDto postDto = postService.getPostById(postId, userDetails.getUser().getUserId());

        System.out.println(postDto.getFiles());
        List<CommentDto> postComments = commentService.getCommentsByPostId(postId);

        model.addAttribute("post", postDto);
        model.addAttribute("comments", postComments);

        return "post_page";
    }

    @PostMapping("/post/{postId}")
    public String addComment(@PathVariable Long postId,
                          @ModelAttribute CommentDto commentDto,
                          @AuthenticationPrincipal UserDetailsImpl userDetails,
                          RedirectAttributes redirectAttributes) {
        try {
            PostDto post = postService.getPostById(postId, userDetails.getUser().getUserId());
            User user = userDetails.getUser();

            commentDto.setPostId(postId);
            commentDto.setUserId(user.getUserId());
            commentDto.setUserName(user.getUsername());


            commentService.addComment(commentDto, user.getUserId());
            redirectAttributes.addFlashAttribute("success", "Comment created successfully!");} catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error creating comment: " + e.getMessage());
        }
        return "redirect:/post/" + postId;

    }

    @PostMapping("/like/{postId}")
    @Transactional
    public ResponseEntity<Map<String, Object>> likePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl currentUser) {

        boolean isLiked = postService.toggleLikePost(postId, currentUser.getUser().getUserId());
        int likesCount = postService.getPostLikeCount(postId);

        Map<String, Object> response = new HashMap<>();
        response.put("likes", likesCount);
        response.put("isLiked", isLiked);

        return ResponseEntity.ok(response);
    }
}
