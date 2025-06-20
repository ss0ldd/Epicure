package ru.itis.epicure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.epicure.dto.PostDto;
import ru.itis.epicure.dto.SignUpForm;
import ru.itis.epicure.dto.UserDto;
import ru.itis.epicure.security.details.UserDetailsImpl;
import ru.itis.epicure.services.PostService;
import ru.itis.epicure.services.UserService;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/profile/{userName}")
    public String getProfilePage(@PathVariable("userName") String userName, Model model, @AuthenticationPrincipal UserDetailsImpl currentUser) {

        UserDto userProfile = userService.getUserByUsername(userName);

        List<PostDto> userPosts = postService.postsByUserId(userProfile.getUserId(), currentUser.getUser().getUserId());

        model.addAttribute("userName", userName);
        model.addAttribute("posts", userPosts);

        return "profile_page";
    }

    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl currentUser) {
        String userName = currentUser.getUsername();
        System.out.println(userName);
        return "redirect:/profile/" + userName;
    }
}
