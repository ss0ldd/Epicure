package ru.itis.epicure.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private Long userId;

    private String title;

    private String content;

    private Long restaurantId;

    private Date postDate;

    private Long fileId;
}
