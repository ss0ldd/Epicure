package ru.itis.epicure.services;

import ru.itis.epicure.dto.CommentDto;
import ru.itis.epicure.models.Comment;

import java.util.List;

public interface CommentService{
    List<CommentDto> getCommentsByPostId(Long postId);
    CommentDto addComment(CommentDto commentDto, Long userId);
}
