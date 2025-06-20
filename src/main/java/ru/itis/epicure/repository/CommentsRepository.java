package ru.itis.epicure.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.epicure.models.Comment;
import ru.itis.epicure.models.Post;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
