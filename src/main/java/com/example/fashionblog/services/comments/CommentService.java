package com.example.fashionblog.services.comments;

import com.example.fashionblog.models.Comment;

public interface CommentService {
    Comment postComment(Integer designId, Comment comment);
}
