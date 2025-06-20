package ru.itis.epicure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.itis.epicure.dto.CommentDto;
import ru.itis.epicure.dto.PostDto;
import ru.itis.epicure.models.Comment;
import ru.itis.epicure.models.Post;
import ru.itis.epicure.models.Restaurant;
import ru.itis.epicure.models.User;
import ru.itis.epicure.repository.CommentsRepository;
import ru.itis.epicure.repository.PostsRepository;
import ru.itis.epicure.repository.UsersRepository;

import java.util.Date;
import java.util.List;

@Component
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        Post post = postsRepository.getPostByPostId(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return CommentDto.from(commentsRepository.findByPost(post));
    }

    @Override
    public CommentDto addComment(CommentDto commentDto, Long userId) {
        User author = usersRepository.findUserByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found")); //////???? в другом месте пофиксить
        Post post = postsRepository.getPostByPostId(commentDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = Comment.builder()
                .comment(commentDto.getComment())
                .post(post)
                .user(author)
                .commentDate(new Date())
                .build();
        Comment savedComment = commentsRepository.save(comment);
        return CommentDto.from(savedComment);
    }


}
