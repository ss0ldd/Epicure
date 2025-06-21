package ru.itis.epicure.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "restaurant_restaurant_id")
    private Restaurant restaurant;

    @ManyToMany
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();

    private Integer rating;
    private Date postDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Comment> createdComments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Like> likes = new ArrayList<>();

    @ManyToMany(mappedBy = "posts", fetch = FetchType.LAZY)
    private List<FileInfo> files = new ArrayList<>();

    public boolean isLikedByUser(Long userId) {
        return likes.stream()
                .map(Like::getUser)
                .anyMatch(user -> user.getUserId().equals(userId));
    }
}
