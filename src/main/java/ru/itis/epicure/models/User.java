package ru.itis.epicure.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "UserEpicure")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique=true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique=true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Post> createdPosts;

    @OneToMany(mappedBy = "user")
    private List<Comment> createdComments;

    @OneToMany(mappedBy = "user")
    private List<Like> likes;

    private String confirmCode;
    private Role role;
    private String confirmed;
}
