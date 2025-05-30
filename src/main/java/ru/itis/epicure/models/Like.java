package ru.itis.epicure.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    private Long postId;

    private Long userId;
}
